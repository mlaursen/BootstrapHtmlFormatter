package com.github.mlaursen.bootstrap.forms.fields.button;

/**
 * A version of a button that is just for submitting.
 * 
 * @author mikkel.laursen
 *
 */
public class SubmitButton extends Button {

	public SubmitButton() {
		super("submit", "submit");
	}
	public SubmitButton(String name) {
		super(name, "submit");
	}

}
