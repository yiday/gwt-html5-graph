package org.drawx.gef.sample.client.tool.example.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class OrangeModel extends AbstractModel {
	public static final String P_CONSTRAINT = "_constraint";
	public static final String P_CONNX_SOURCE = "_connx_source";
	public static final String P_CONNX_TARGET = "_connx_target";
	
	private String labelText;
	private Rectangle constraint;

	private List sourceConnections = new ArrayList();
	private List targetConnections = new ArrayList();
	
	public OrangeModel() {
		this(new Rectangle(0,0,-1,-1));
	}
	
	public OrangeModel(Point pt) {
		this(new Rectangle(pt,new Dimension(100,100)));	
	}
	
	public OrangeModel(Rectangle rect){
		setConstraint(rect);		
		labelText = "test1";
	}
	
	public String getLabelText() {
		return labelText;
	}

	public void setLabelText(String string) {
		labelText = string;
	}

	public Rectangle getConstraint() {
		return constraint;
	}

	public void setConstraint(Rectangle rectangle) {
		constraint = rectangle;
		firePropertyChange(P_CONSTRAINT,null,rectangle);
	}
	
	// ���̃��f������o��R�l�N�V���� ���f���̒ǉ�
	public void addSourceConnection(MyConnectionModel connx) {
		sourceConnections.add(connx);
		firePropertyChange(P_CONNX_SOURCE,null,null);
	}
	
	// ���̃��f���ɐڑ������R�l�N�V���� ���f���̒ǉ�
	public void addTargetConnection(MyConnectionModel connx) {
		targetConnections.add(connx);
		firePropertyChange(P_CONNX_TARGET,null,null);
	}
	
	// ���̃��f����ڑ����Ƃ���R�l�N�V�����̃��X�g��Ԃ�
	public List getModelSourceConnections() {
		return sourceConnections;
	}
	
	// ���̃��f����ڑ���Ƃ���R�l�N�V�����̃��X�g��Ԃ�
	public List getModelTargetConnections() {
		return targetConnections;
	}
}
