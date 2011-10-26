package org.drawx.gef.sample.client.tool.example.editparts;

import org.drawx.gef.sample.client.tool.example.model.*;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;



public class MyEditPartFactory implements EditPartFactory {

	/* (�� Javadoc)
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		
		if(model instanceof CanvasModel) 
			part = new CanvasEditPart();
		else if(model instanceof OrangeModel)
			part = new OrangeEditPart();
		else if(model instanceof MyConnectionModel)
			part = new MyConnectionEditPart();
		part.setModel(model);
		return part;
	}

}
