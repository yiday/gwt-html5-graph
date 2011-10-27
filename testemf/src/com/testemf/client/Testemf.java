package com.testemf.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.java.io2.GWTOutputStream;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Testemf implements EntryPoint {

	
	private void clear(TextArea log) {
		log.setText("-------------log-----------------------");
	}

	private void println(TextArea log, String text) {
		log.setText(log.getText() + "\r\n" + text);
	}

	private void initEMF(TextArea log) {
		clear(log);
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		EcorePackage ecorePackage = EcorePackage.eINSTANCE;
		// 创建一Company类
		EClass companyClass = ecoreFactory.createEClass();
		companyClass.setName("Company");
		// 创建公司名
		EAttribute companyName = ecoreFactory.createEAttribute();
		companyName.setName("name");
		companyName.setEType(ecorePackage.getEString());
		companyClass.getEStructuralFeatures().add(companyName);
		// 创建一Employee类
		EClass employeeClass = ecoreFactory.createEClass();
		employeeClass.setName("Employee");
		// 在Employee类上添加一个名字属性
		EAttribute employeeName = ecoreFactory.createEAttribute();
		employeeName.setName("name");
		employeeName.setEType(ecorePackage.getEString());
		employeeClass.getEStructuralFeatures().add(employeeName);
		// 创建一Department类
		EClass departmentClass = ecoreFactory.createEClass();
		departmentClass.setName("Department");
		// 添加department标志数字
		EAttribute departmentNumber = ecoreFactory.createEAttribute();
		departmentNumber.setName("number");
		departmentNumber.setEType(ecorePackage.getEInt());
		departmentClass.getEStructuralFeatures().add(departmentNumber);
		// department类能够包含到一个或多个employee的参考
		EReference departmentEmployees = ecoreFactory.createEReference();
		departmentEmployees.setName("employees");
		departmentEmployees.setEType(employeeClass);
		// 指定它可能是一个或多个employee
		departmentEmployees.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
		departmentEmployees.setContainment(true);
		departmentClass.getEStructuralFeatures().add(departmentEmployees);
		// company能够包含到一个或多个departments的参考
		EReference companyDepartments = ecoreFactory.createEReference();
		companyDepartments.setName("department");
		companyDepartments.setEType(departmentClass);
		companyDepartments.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
		companyDepartments.setContainment(true);
		companyClass.getEStructuralFeatures().add(companyDepartments);
		// 创建一个包-描述company
		EPackage companyPackage = ecoreFactory.createEPackage();
		companyPackage.setName("company");
		companyPackage.setNsPrefix("company");
		companyPackage.setNsURI("http:///com.example.company.ecore");
		companyPackage.getEClassifiers().add(employeeClass);
		companyPackage.getEClassifiers().add(departmentClass);
		companyPackage.getEClassifiers().add(companyClass);
		// 通过使用反射API，你能创建并且初始化一个你的模型的实例：
		// 得到company工厂
		EFactory companyFactory = companyPackage.getEFactoryInstance();
		// 使用工厂来创建company类的实例并且
		// 设置company名字
		EObject company = companyFactory.create(companyClass);
		company.eSet(companyName, "MyCompany");
		// 创建一个employee类的实例
		EObject employee = companyFactory.create(employeeClass);
		// 使用反射API初始化employee的名字
		employee.eSet(employeeName, "keven");
		// 创建一个department类的实例
		EObject department = companyFactory.create(departmentClass);
		department.eSet(departmentNumber, new Integer(123));
		// 添加"John"到department
		((List) department.eGet(departmentEmployees)).add(employee);
		// 添加department到company
		((List) company.eGet(companyDepartments)).add(department);

		List l = ((List) company.eGet(companyDepartments));

		XMIResourceImpl resource = new XMIResourceImpl();
		resource.getContents().add(company);
		GWTOutputStream bs = new GWTOutputStream();
		try {
			HashMap options = new HashMap();
			options.put(XMIResource.OPTION_ENCODING, "UTF-8");
			resource.save(bs, options);
		} catch (IOException e) {
			e.printStackTrace();
		}
		println(log,
				"--------Save emf object to xmi resource------------------");
		println(log, new String(bs.toString()));

		ResourceSetImpl rs = new ResourceSetImpl();
		XMIResourceImpl r = new XMIResourceImpl();
		rs.getResources().add(r);
		r.getResourceSet().getPackageRegistry()
				.put(companyPackage.getNsURI(), companyPackage);
		// GWTInputStream is = new GWTInputStream();
		ByteArrayInputStream is = new ByteArrayInputStream(bs.toString()
				.getBytes());
		try {
			r.load(is, null);
			if (resource.getContents().size() > 0) {

				println(log,
						"--------load emf object from xmi resource------------------");
				println(log, resource.getContents().get(0) + "");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onModuleLoad() {
		DockLayoutPanel root = new DockLayoutPanel(Unit.EM);
		RootLayoutPanel.get().add(root);
		TabLayoutPanel p = new TabLayoutPanel(1.5, Unit.EM);
		root.addNorth(
				new com.google.gwt.user.client.ui.HTML(
						"<p align=\"center\"><font size=\"6\">gwt-html5-graph EMF/UML2 Sample</font></p>"),
				5);
		root.add(p);
		{
			VerticalPanel page = new VerticalPanel();
			TextArea src = new TextArea();
			src.setText(Strings.INSTANCE.emf().getText());
			final TextArea log = new TextArea();
			log.setText("-------------log-----------------------");
			Button btn = new Button("Run Test", new ClickHandler() {

				public void onClick(ClickEvent event) {
					Testemf.this.initEMF(log);
				}

			});
			page.add(btn);
			src.setSize("800px", "150px");
			log.setSize("800px", "300px");
			page.add(src);
			page.add(log);
			p.add(page, "GWT EMF Test");
			
		}
		
	}
}
