package com.github.mlaursen.bootstrap.forms.fields.errors;

import java.util.List;

import com.github.mlaursen.bootstrap.forms.fields.Errorable;

/**
 * A form error for a form.
 * 
 * 
 * @author mikkel.laursen
 *
 */
public class FormError {

	private Errorable field;
	private List<String> errors;
	private String id;	
	public FormError(Errorable f) {
		this.id = f.getId();
		this.field = f;
		this.errors = f.getErrors();
	}
	
	public void setField(Errorable f) {
		field = f;
	}
	
	public void addError(String err) {
		this.errors.add(err);
	}
	
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	/**
	 * Gets the field of the formerror
	 * @return
	 */
	public Errorable getField() { 
		return this.field;
	}
	
	/**
	 * Gets the list of errors
	 * @return
	 */
	public List<String> getErrors() { 
		return this.errors;
	}
	
	/**
	 * Generetes text for all the errors for the form error
	 * @return
	 */
	public String asText() {
		StringBuilder sb = new StringBuilder("");
		for(String e : errors) {
			sb.append("Error: " + e +"<br />\n");
		}
		return sb.toString();
	}

}
