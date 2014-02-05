package com.github.mlaursen.bootstrap.forms.fields;


/**
 * Interface to know that a field has CSS.
 * @author mikkel.laursen
 *
 */
public interface CssAble extends HtmlFieldable {

	void setCss(String css);
	String getCss();
	
}
