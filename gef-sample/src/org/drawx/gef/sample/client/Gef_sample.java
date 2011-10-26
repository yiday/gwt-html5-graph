package org.drawx.gef.sample.client;

import org.drawx.gef.sample.client.tool.example.model.CanvasModel;
import org.drawx.gef.sample.client.tool.example.model.MyConnectionModel;
import org.drawx.gef.sample.client.tool.example.model.OrangeModel;
import org.drawx.gef.ui.editor.DiagramEditorWithPalette;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ScrollableThumbnailEx;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.ConnectionCreationTool;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.gef.tools.SelectionTool;
import org.eclipse.gef.ui.DiagramEditor;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.swing.CanvasUtil;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gef_sample implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		if(!CanvasUtil.isHtml5())
		{
			show("Your navigator not support html5! Please use IE9/chrome/firefox/opera/safari!");
			return;
		}
		DockLayoutPanel root = new DockLayoutPanel(Unit.EM);
		RootLayoutPanel.get().add(root);
		TabLayoutPanel p = new TabLayoutPanel(1.5, Unit.EM);
		root.addNorth(new 
				com.google.gwt.user.client.ui.HTML(
						"<p align=\"center\"><font size=\"6\">gwt-html5-graph GEF Sample</font></p>"), 5);
		root.add(p);

		createTab3(p);
		createTab2(p);
		createTab1(p);
	}

	private void show(String text)
	{
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Warning");
		dialogBox.setAnimationEnabled(true);
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML(text));
		
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		
		dialogBox.setWidget(dialogVPanel);
		dialogBox.center();
	}
	private void createTab3(TabLayoutPanel p) {
		org.eclipse.swt.widgets.Canvas canvas = new org.eclipse.swt.widgets.Canvas();
		LightweightSystem lws = new LightweightSystem(canvas);
		p.add(canvas.getWidget(),"UML2 Sample");

		Figure contents = new Figure();
		XYLayout contentsLayout = new XYLayout();
		contents.setLayoutManager(contentsLayout);
		
		Image image1 = new Image(
				null, Images.INSTANCE.icon_class());
		Image image2 = new Image(
				null, Images.INSTANCE.icon_field());
		Image image3 = new Image(
				null, Images.INSTANCE.icon_method());

		Font classFont = new Font(null, "Arial", 12, SWT.BOLD);
		Label classLabel1 = new Label("Table", image1);//
		// UMLClassFigureTest.class.getResourceAsStream("class_obj.gif")));
		classLabel1.setFont(classFont);

		Label classLabel2 = new Label("Table2",image1);// , new Image(d,
		// UMLClassFigureTest.class.getResourceAsStream("class_obj.gif")));
		classLabel2.setFont(classFont);

		final UMLClassFigure classFigure = new UMLClassFigure(classLabel1);
		final UMLClassFigure classFigure2 = new UMLClassFigure(classLabel2);

		Label attribute1 = new Label("columns: Column[]", image2);// );// , new Image(d,
		// UMLClassFigure.class.getResourceAsStream("field_private_obj.gif")));
		Label attribute2 = new Label("rows: Row[]", image2);// );// , new Image(d,
		// UMLClassFigure.class.getResourceAsStream("field_private_obj.gif")));
		Label attribute3 = new Label("columnID: int", image2);// );// , new Image(d,
		// UMLClassFigure.class.getResourceAsStream("field_private_obj.gif")));
		Label attribute4 = new Label("items: List", image2);// );// , new Image(d,
		// UMLClassFigure.class.getResourceAsStream("field_private_obj.gif")));

		classFigure.getAttributesCompartment().add(attribute1);
		classFigure.getAttributesCompartment().add(attribute2);
		classFigure2.getAttributesCompartment().add(attribute3);
		classFigure2.getAttributesCompartment().add(attribute4);

		Label method1 = new Label("getColumns(): Column[]",image3);
		Label method2 = new Label("getRows(): Row[]",image3);
		Label method3 = new Label("getColumnID(): int",image3);
		Label method4 = new Label("getItems(): List",image3);

		classFigure.getMethodsCompartment().add(method1);
		classFigure.getMethodsCompartment().add(method2);
		classFigure2.getMethodsCompartment().add(method3);
		classFigure2.getMethodsCompartment().add(method4);

		contentsLayout
				.setConstraint(classFigure, new Rectangle(10, 10, -1, -1));
		contentsLayout.setConstraint(classFigure2, new Rectangle(200, 200, -1,
				-1));

		/* Creating the connection */
		PolylineConnection c = new PolylineConnection();
		ChopboxAnchor sourceAnchor = new ChopboxAnchor(classFigure);
		ChopboxAnchor targetAnchor = new ChopboxAnchor(classFigure2);
		c.setSourceAnchor(sourceAnchor);
		c.setTargetAnchor(targetAnchor);

		/* Creating the decoration */
		PolygonDecoration decoration = new PolygonDecoration();
		PointList decorationPointList = new PointList();
		decorationPointList.addPoint(0, 0);
		decorationPointList.addPoint(-2, 2);
		decorationPointList.addPoint(-4, 0);
		decorationPointList.addPoint(-2, -2);
		decoration.setTemplate(decorationPointList);
		c.setSourceDecoration(decoration);

		/* Adding labels to the connection */
		ConnectionEndpointLocator targetEndpointLocator = new ConnectionEndpointLocator(
				c, true);
		targetEndpointLocator.setVDistance(15);
		Label targetMultiplicityLabel = new Label("1..*");
		c.add(targetMultiplicityLabel, targetEndpointLocator);

		ConnectionEndpointLocator sourceEndpointLocator = new ConnectionEndpointLocator(
				c, false);
		sourceEndpointLocator.setVDistance(15);
		Label sourceMultiplicityLabel = new Label("1");
		c.add(sourceMultiplicityLabel, sourceEndpointLocator);

		ConnectionEndpointLocator relationshipLocator = new ConnectionEndpointLocator(
				c, true);
		relationshipLocator.setUDistance(10);
		relationshipLocator.setVDistance(-20);
		Label relationshipLabel = new Label("contains");
		c.add(relationshipLabel, relationshipLocator);

		contents.add(classFigure);
		contents.add(classFigure2);
		contents.add(c);

		lws.setContents(contents);

		canvas.setSize(500, 300);
	}
	private void createTab1(TabLayoutPanel p) {
		DiagramEditorWithPalette editor = new MyDiagramEditor(true);
		p.add(editor.getWidget(), "Fill Canvas");
	}

	private void createTab2(TabLayoutPanel tab) {
		ScrollingGraphicalViewer viewer;

		try {
			viewer = new ScrollingGraphicalViewer();
			viewer.createControl();
				ScalableFreeformRootEditPart root = new ScalableFreeformRootEditPart();
				viewer.setRootEditPart(root);
			viewer.setEditDomain(new EditDomain());

			viewer.setEditPartFactory(new org.drawx.gef.sample.client.tool.example.editparts.MyEditPartFactory());
			CanvasModel model = new CanvasModel();

			for (int i = 0; i < 1; i++) {
				MyConnectionModel conn = new MyConnectionModel();
				OrangeModel m1 = new OrangeModel(new Point(30, 230));
				OrangeModel m2 = new OrangeModel(new Point(0, 0));
				model.addChild(m1);
				model.addChild(m2);
				m1.addSourceConnection(conn);
				m2.addTargetConnection(conn);
				viewer.setContents(model);

			}

			DiagramEditor p = new DiagramEditor(viewer);
			viewer.setContextMenu(new MyContextMenuProvider(viewer, p
					.getActionRegistry()));

			VerticalPanel panel = new VerticalPanel();

			addToolbox(viewer.getEditDomain(), viewer,panel);

			panel.add(viewer.getControl().getWidget());
			tab.add(panel, "Fixed Size Canvas(+Overview)");

			addOverview(viewer,panel);

			viewer.getControl().setSize(400, 300);

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private void addToolbox(final EditDomain editDomain,
			final GraphicalViewer viewer, VerticalPanel panel) {
		final SelectionTool tool1 = new SelectionTool();
		editDomain.setDefaultTool(tool1);
		final CreationTool tool = new CreationTool();
		tool.setFactory(new CreationFactory() {

			public Object getNewObject() {
				return new OrangeModel();
			}

			public Object getObjectType() {
				return OrangeModel.class;
			}
		});// (OrangeModel.class));
		final ConnectionCreationTool tool2 = new ConnectionCreationTool();
		tool2.setUnloadWhenFinished(true);

		HorizontalPanel s = new HorizontalPanel();
		s.setSpacing(5);

		panel.add(s);
		tool2.setFactory(new CreationFactory() {

			public Object getNewObject() {
				return new MyConnectionModel();
			}

			public Object getObjectType() {
				return MyConnectionModel.class;
			}
		});
		{
			PushButton pb = new PushButton("Select", new ClickHandler() {

				public void onClick(ClickEvent event) {
					editDomain.setActiveTool(tool1);
				}

			});
			s.add(pb);
		}
		{
			PushButton pb = new PushButton("Create Node", new ClickHandler() {

				public void onClick(ClickEvent event) {
					editDomain.setActiveTool(tool);
				}

			});
			s.add(pb);
		}
		{
			PushButton pb = new PushButton("CreateConnection",
					new ClickHandler() {

						public void onClick(ClickEvent event) {
							editDomain.setActiveTool(tool2);
						}

					});
			s.add(pb);
		}
//		{
//			PushButton pb = new PushButton("set size", new ClickHandler() {
//
//				public void onClick(ClickEvent event) {
//					int w = viewer.getControl().getBounds().width;
//					int h = viewer.getControl().getBounds().height;
//					viewer.getControl().setSize(w + 20, h + 20);
//				}
//
//			});
//			s.add(pb);
//		}
	}

	private void addOverview(GraphicalViewer viewer, VerticalPanel panel) {
		org.eclipse.swt.widgets.Canvas c = new org.eclipse.swt.widgets.Canvas(
				false);
		LightweightSystem lws = new LightweightSystem(c);

		panel.add(c.getWidget());
		c.setSize(150, 150);
		RootEditPart rep = viewer.getRootEditPart();
		if (rep instanceof ScalableFreeformRootEditPart) {
			ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) rep;
			ScrollableThumbnailEx thumbnail = new ScrollableThumbnailEx(
					(Viewport) root.getFigure());
			thumbnail.setBorder(new MarginBorder(3));
			thumbnail.setSource(root.getLayer(LayerConstants.PRINTABLE_LAYERS));
			lws.setContents(thumbnail);
		}
	}

}
