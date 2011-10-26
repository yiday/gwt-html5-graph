package org.drawx.gef.sample.client.tool.example.editpolicies;

import org.drawx.gef.sample.client.tool.example.model.command.CreateCommand;
import org.drawx.gef.sample.client.tool.example.model.command.MoveCommand;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;



public class MyXYLayoutEditPolicy extends XYLayoutEditPolicy {

	/* (�� Javadoc)
	 * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createAddCommand(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	protected Command createAddCommand(EditPart child, Object constraint) {
		return null;
	}

	/* (�� Javadoc)
	 * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	protected Command createChangeConstraintCommand(
		EditPart child,
		Object constraint) {

		Command command = new MoveCommand(child.getModel(),(Rectangle)constraint);
		return command;
	}

	/* (�� Javadoc)
	 * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
	 */
	protected Command getCreateCommand(CreateRequest request) {
		CreateCommand command = new CreateCommand(getHost().getModel(),request.getNewObject());
		Dimension size = request.getSize();
		if(size == null){
			command.setLocation(request.getLocation());
		} else {
			command.setLocation(new Rectangle(request.getLocation(),request.getSize()));
		}
		return command;
	}

	/* (�� Javadoc)
	 * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getDeleteDependantCommand(org.eclipse.gef.Request)
	 */
	protected Command getDeleteDependantCommand(Request request) {
		return null;
	}

}
