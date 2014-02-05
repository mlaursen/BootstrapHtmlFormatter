package com.github.mlaursen.bootstrap.forms.fields;


/**
 * Interface to set a field to have a label
 * @author mikkel.laursen
 *
 */
public interface Labelable extends HtmlFieldable {

	String getLabel();
	void setLabel(String l);
	
}
