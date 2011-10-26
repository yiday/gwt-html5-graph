package org.drawx.gef.sample.client.tool.example.model.command;

import org.drawx.gef.sample.client.tool.example.model.CanvasModel;
import org.drawx.gef.sample.client.tool.example.model.OrangeModel;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;



public class CreateCommand extends Command {
	private Object orange,canvas;
	
	public CreateCommand(Object parent, Object child) {
		canvas = parent;
		orange = child;
	}
	
	/* (�� Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
//		System.out.println("createcommand"+((OrangeModel)orange).getConstraint());
		((CanvasModel)canvas).addChild(orange);
	}
	
	@Override
	public boolean canUndo() {
		return false;
	}

	public void setLocation(Point pt){
		setLocation(new Rectangle(pt,new Dimension(-1,-1)));
	}
	
	public void setLocation(Rectangle rect){
		((OrangeModel)orange).setConstraint(rect);
	}
}
