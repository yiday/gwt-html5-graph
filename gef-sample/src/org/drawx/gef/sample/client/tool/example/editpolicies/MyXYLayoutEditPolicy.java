package org.drawx.gef.sample.client.tool.example.editpolicies;

import org.drawx.gef.sample.client.tool.example.model.command.CreateCommand;
import org.drawx.gef.sample.client.tool.example.model.command.MoveCommand;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
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
		Rectangle constraint = (Rectangle) getConstraintFor(request);
		if (constraint == null) {

			constraint = (Rectangle) getConstraintFor(request.getLocation());
			// System.out.println(constraint.getLocation());
		}
		Dimension size = constraint.getSize();//request.getSize();
		if(size == null){
			command.setLocation(constraint.getLocation());
		} else {
			command.setLocation(new Rectangle(constraint.getLocation(),constraint.getSize()));
		}
		return command;
	}
	protected Object getConstraintFor(CreateRequest request) {
		IFigure figure = getLayoutContainer();

		Point where = request.getLocation().getCopy();
		Dimension size = request.getSize();

		figure.translateToRelative(where);
		figure.translateFromParent(where);
		try {
			where.translate(getLayoutOrigin().getNegated());
		} catch (Exception e) {

			return new Rectangle(where, new Dimension(-1, -1));
		}

		if (size == null || size.isEmpty())
			return getConstraintFor(where);
		else {
			// $TODO Probably should use PrecisionRectangle at some point
			// instead of two
			// geometrical objects
			size = size.getCopy();
			figure.translateToRelative(size);
			figure.translateFromParent(size);
			return getConstraintFor(new Rectangle(where, size));
		}
	}

	/* (�� Javadoc)
	 * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getDeleteDependantCommand(org.eclipse.gef.Request)
	 */
	protected Command getDeleteDependantCommand(Request request) {
		return null;
	}

}
