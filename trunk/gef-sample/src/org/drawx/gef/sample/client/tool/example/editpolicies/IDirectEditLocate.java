package org.drawx.gef.sample.client.tool.example.editpolicies;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

public interface IDirectEditLocate {
	Rectangle getDirectEditRectangle();
	void translateToAbsoluteDirect(Dimension dimension);
	void setCurrentEditValue(String text);
	String getCurrentEditValue();
}
