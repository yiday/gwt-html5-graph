package org.drawx.gef.sample.client.tool.example.model.command;

import org.drawx.gef.sample.client.tool.example.model.MyConnectionModel;
import org.drawx.gef.sample.client.tool.example.model.OrangeModel;
import org.eclipse.gef.commands.Command;





public class CreateConnectionCommand extends Command {
	private MyConnectionModel connection;
	private OrangeModel source,target;
	
	public void setConnection(Object connx){
		connection = (MyConnectionModel)connx;
	}

	@Override
	public boolean canUndo() {
		return false;
	}
	public void setSource(Object model) {
		source = (OrangeModel)model;
	}

	public void setTarget(Object model) {
		target = (OrangeModel)model;
	}

	/* (�� Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		source.addSourceConnection(connection);
		target.addTargetConnection(connection);
	}
}
