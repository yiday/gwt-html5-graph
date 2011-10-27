package com.testemf.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.resources.client.ClientBundle.Source;

public interface Strings extends ClientBundle {

	public static final Strings INSTANCE =  GWT.create(Strings.class);

	  @Source("emf.txt")
	  public TextResource  emf();


}
