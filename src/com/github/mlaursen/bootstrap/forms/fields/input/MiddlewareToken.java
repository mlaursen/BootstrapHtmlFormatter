package com.github.mlaursen.bootstrap.forms.fields.input;

import com.github.mlaursen.bootstrap.forms.HtmlForm;

public class MiddlewareToken extends HiddenField {

	private HtmlForm referenceForm;
	public MiddlewareToken(String name) {
		super(name);
	}

	public MiddlewareToken(String name, String value) {
		super(name, value);
	}
	
	public MiddlewareToken(String name, HtmlForm f) {
		super(name);
		referenceForm = f;
		setValue("");
	}

	public MiddlewareToken(String name, String value, HtmlForm f) {
		super(name, value);
		referenceForm = f;
	}

	/**
	 * Set the reference form to a new form
	 * @param f	The form
	 */
	public void setReferenceForm(HtmlForm f) {
		referenceForm = f;
	}
	
	/**
	 * Get the middleware token's reference form
	 * @return	HtmlForm 
	 */
	public HtmlForm getReferenceForm() {
		return referenceForm;
	}
	
	@Override
	public boolean isValidField() {
		boolean valid = super.isValidField();
		if(!referenceForm.getMiddlewareToken().getValue().equals(getValue())) {
			valid = false;
			this.addError("Bad middleware token. Tisk tisk!");
		}
		return valid;
	}
	
	
}
