package org.drawx.gef.sample.client.tool.example.editparts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.drawx.gef.sample.client.tool.example.editpolicies.MyXYLayoutEditPolicy;
import org.drawx.gef.sample.client.tool.example.model.CanvasModel;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;



public class CanvasEditPart extends EditPartWithListener {

	/* (�� Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
//		IFigure figure = new LayeredPane();
//		figure.setLayoutManager(new XYLayout());
		IFigure figure = new FreeformLayer();
        figure.setLayoutManager(new FreeformLayout());
		return figure;
	}

	/* (�� Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new MyXYLayoutEditPolicy());
	}

	/* (�� Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(CanvasModel.P_CHILDREN))
			refreshChildren();
	}

	private CanvasModel getCanvasModel(){
		return (CanvasModel)getModel();
	}
	
	/* (�� Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
	 */
	protected List getModelChildren() {
		return getCanvasModel().getChildren();
	}
}
