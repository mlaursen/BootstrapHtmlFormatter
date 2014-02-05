package com.github.mlaursen.bootstrap.forms.fields.input;

import com.github.mlaursen.bootstrap.forms.fields.errors.BasicValidation;

/**
 * Number field.
 * 
 * The {@link #isValidField()} additionally checks if the value is a number
 * @author mikkel.laursen
 *
 */
public class NumberField extends TextField {

	public NumberField(String name) {
		super(name);
	}

	public NumberField(String name, int minlength, int maxlength) {
		super(name, minlength, maxlength);
	}
	
	public NumberField(String name, int minlength, int maxlength, String css) {
		super(name, minlength, maxlength, css);
	}
	
	@Override
	public boolean isValidField() {
		boolean valid = super.isValidField();
		if(!BasicValidation.isNumber(getValue())) {
			valid = false;
			this.errors.add("must be a number.");
		}
		return valid;
	}

}
