package com.github.mlaursen.bootstrap.forms.fields;

import java.util.List;

import com.github.mlaursen.bootstrap.forms.fields.errors.FormError;

/**
 * States that some form of BasicHtmlable has the ability
 * to have errors
 * 
 * @author mikkel.laursen
 *
 */
public interface Errorable extends HtmlFieldable {

	boolean isValidField();
	List<String> getErrors();
	FormError getFormError();
	void addError(String err);
	
}
