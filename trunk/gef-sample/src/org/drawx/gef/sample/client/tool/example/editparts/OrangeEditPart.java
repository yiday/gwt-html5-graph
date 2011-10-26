package org.drawx.gef.sample.client.tool.example.editparts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.drawx.gef.sample.client.tool.example.editpolicies.*;
import org.drawx.gef.sample.client.tool.example.model.OrangeModel;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;



public class OrangeEditPart extends EditPartWithListener implements
		NodeEditPart {
	protected DirectEditManager manager;
//	Image image = new Image(null,"class_obj.gif",16, 16);
//	boolean b = false;
//	
//	int c = getColor(100,255,0,0);
//
//	public static int getColor(int a, int r, int g, int b)
//	{
//		return ((a & 0xFF) << 24) |
//                ((r & 0xFF) << 16) |
//                ((g & 0xFF) << 8)  |
//                ((b & 0xFF) << 0);
//	}
//	protected IFigure createFigure() {
//		Figure f = new Figure() {
//			public void paintFigure(Graphics g) {
//				if (!b) {
//					ImageData data = image.getImageData();
//					if (data != null) {
//						for (int i = 0; i < data.width; i++) {
//							for (int j = 0; j < data.height; j++)
//							{
//								data.setPixel(i, j, c);//220);
//							}
//						}
//						image = new Image(null, data);
//						b = true;
//					}
//				} else
//					g.drawImage(image, this.getBounds().x, this.getBounds().y, this);
//				// g.setBackgroundColor(ColorConstants.orange);
//				// g.fillRoundRectangle(this.getBounds(), 15, 15);
//				// g.drawRoundRectangle(this.getBounds(), 15, 15);
//			}
//		};
//		return f;
//	}

	/*
	 * (�� Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		Label label = new DirectLabel(getOrangeModel().getLabelText(), new Image(
				null, org.drawx.gef.sample.client.Images.INSTANCE.icon_class()));//"class_obj.gif", 16, 16));
		label.setBackgroundColor(ColorConstants.orange);
		label.setOpaque(true);
//		FontData fd = new FontData("",40,0);//label.getFont().getFontData()[0];
//		//fd.setHeight(20);
//		Font f = new Font(null,fd);
//		label.setFont(f);
		label.setText("Class1");

		CompoundBorder border = new CompoundBorder(new LineBorder(),
				new MarginBorder(3));
		label.setBorder(border);
		return label;
	}
	class DirectLabel extends Label implements IDirectEditLocate
	{

		public DirectLabel(String labelText, Image image) {
			super(labelText,image);
		}

		public Rectangle getDirectEditRectangle() {
			Rectangle rect = this.getClientArea().getCopy();
			this.translateToAbsolute(rect);
			return rect;
		}

		public void translateToAbsoluteDirect(Dimension dimension) {
			this.translateToAbsolute(dimension);
			
		}


		String text;
		public void setCurrentEditValue(String text) {
			
			this.text = text;
		}

		public String getCurrentEditValue() {
			
			return text;
		}
		
	}
	public void performDirectEdit() {
		

		if (manager == null)
			manager = new SingleLineLabelEditManager(this,
					new LabelCellEditorLocator((IDirectEditLocate) getFigure()));
		manager.show();
	}

	public void requestDirectEdit() {

		this.getFigure().getUpdateManager().performUpdate();
		performRequest(new Request(RequestConstants.REQ_DIRECT_EDIT));
	}

	public void performRequest(Request req) {
		if (req.getType() == RequestConstants.REQ_DIRECT_EDIT)
			performDirectEdit();
		else {
			Command command = getCommand(req);
			if (command != null) {
				if (command.canExecute()) {
					CommandStack stack = getViewer().getEditDomain()
							.getCommandStack();
					stack.execute(command);
				}
				return;
			}
		}

		super.performRequest(req);
	}
	/*
	 * (�� Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new MyGraphicalNodeEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new LabelDirectEditPolicy());
	}

	private OrangeModel getOrangeModel() {
		return (OrangeModel) getModel();
	}

	/*
	 * (�� Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(OrangeModel.P_CONSTRAINT))
			refreshVisuals();
		else if (evt.getPropertyName().equals(OrangeModel.P_CONNX_SOURCE))
			refreshSourceConnections();
		else if (evt.getPropertyName().equals(OrangeModel.P_CONNX_TARGET))
			refreshTargetConnections();
	}

	/*
	 * (�� Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	protected void refreshVisuals() {
		Rectangle constraint = getOrangeModel().getConstraint();

		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), constraint);
	}

	/*
	 * (�� Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelSourceConnections
	 * ()
	 */
	protected List getModelSourceConnections() {
		return getOrangeModel().getModelSourceConnections();
	}

	/*
	 * (�� Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelTargetConnections
	 * ()
	 */
	protected List getModelTargetConnections() {
		return getOrangeModel().getModelTargetConnections();
	}

	/*
	 * (�� Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef
	 * .ConnectionEditPart)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	/*
	 * (�� Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef
	 * .ConnectionEditPart)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	/*
	 * (�� Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef
	 * .Request)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	/*
	 * (�� Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef
	 * .Request)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

}
