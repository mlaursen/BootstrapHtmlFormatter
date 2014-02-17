package com.github.mlaursen.bootstrap.forms.fields.input;

import com.github.mlaursen.bootstrap.forms.fields.errors.BasicValidation;

/**
 * An Html hidden field
 * @author mikkel.laursen
 *
 */
public class HiddenField extends InputField {

	public HiddenField(String name) {
		super(name, "hidden");
		setValue(null);
	}
	public HiddenField(String name, String value) {
		this(name);
		setValue(value);
	}
	
	@Override
	public boolean isValidField() {
		boolean valid = true;
		if(!BasicValidation.isAtLeastXCharacters(getValue(), 1)) {
			valid = false;
			this.addError("must not be blank.");
		}
		return valid;
	}
	
	
}
