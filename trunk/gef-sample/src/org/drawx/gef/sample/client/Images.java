package org.drawx.gef.sample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.resources.client.ClientBundle.Source;

public interface Images extends ClientBundle {

	public static final Images INSTANCE =  GWT.create(Images.class);

	  @Source("class_obj.gif")
	  public ImageResource  icon_class();
	  
	  @Source("methpub_obj.gif")
	  public ImageResource  icon_method();
	  
	  @Source("field_private_obj.gif")
	  public ImageResource  icon_field();


	  @Source("newConnection.gif")
	  public ImageResource  newConnection();

	  @Source("newModel.gif")
	  public ImageResource  newModel();

}
