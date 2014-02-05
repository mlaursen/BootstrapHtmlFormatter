package com.github.mlaursen.bootstrap.forms.fields.input;

/**
 * Html password field.  It hides the value in the form.
 * @author mikkel.laursen
 *
 */
public class PasswordField extends TextField {

	public PasswordField(String name) {
		super(name);
		this.type = "password";
	}

	public PasswordField(String name, int minlength, int maxlength) {
		super(name, minlength, maxlength);
		this.type = "password";
	}
	
	public PasswordField(String name, int minlength, int maxlength, String css) {
		super(name, minlength, maxlength, css);
		this.type = "password";
	}

}
