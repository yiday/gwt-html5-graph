package org.drawx.gef.sample.client.tool.example.model;

import java.util.ArrayList;
import java.util.List;

public class CanvasModel extends AbstractModel {
	public static final String P_CHILDREN = "_children";
	
	protected List children = new ArrayList();
	
	public void addChild(Object o){
		children.add(o);
		firePropertyChange(P_CHILDREN,null,null);
	}
	
	public List getChildren(){
		return children;
	}
	
	public void removeChild(Object o){
		children.remove(o);
		firePropertyChange(P_CHILDREN,null,null);
	}
}
