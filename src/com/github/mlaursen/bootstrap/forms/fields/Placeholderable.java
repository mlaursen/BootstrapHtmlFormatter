package com.github.mlaursen.bootstrap.forms.fields;


/**
 * A field has a placeholder
 * @author mikkel.laursen
 *
 */
public interface Placeholderable extends HtmlFieldable {

	void setPlaceholder(String ph);
	String getPlaceholder();
}
