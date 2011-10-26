/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.drawx.gef.sample.client.tool.example.editpolicies;

import org.drawx.gef.sample.client.tool.example.model.command.LabelCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;



public class LabelDirectEditPolicy 
	extends DirectEditPolicy {

/**
 * @see DirectEditPolicy#getDirectEditCommand(DirectEditRequest)
 */
protected Command getDirectEditCommand(DirectEditRequest edit) {
	String labelText = (String)edit.getCellEditor().getValue();
	EditPart label = (EditPart)getHost();
	LabelCommand command = new LabelCommand(label.getModel(),labelText);
	return command;
}

/**
 * @see DirectEditPolicy#showCurrentEditValue(DirectEditRequest)
 */
protected void showCurrentEditValue(DirectEditRequest request) {
	String value = (String)request.getCellEditor().getValue();
	((IDirectEditLocate)getHostFigure()).setCurrentEditValue(value);
	//hack to prevent async layout from placing the cell editor twice.
	getHostFigure().getUpdateManager().performUpdate();
	
}

}
