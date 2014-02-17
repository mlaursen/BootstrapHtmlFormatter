package com.github.mlaursen.bootstrap.forms.fields.input;

import java.util.ArrayList;
import java.util.List;

import com.github.mlaursen.bootstrap.forms.fields.Cleanable;
import com.github.mlaursen.bootstrap.forms.fields.Errorable;
import com.github.mlaursen.bootstrap.forms.fields.HtmlField;
import com.github.mlaursen.bootstrap.forms.fields.errors.BasicValidation;
import com.github.mlaursen.bootstrap.forms.fields.errors.FormError;

/**
 * Html Input field.
 * 
 * @author mikkel.laursen
 *
 */
public abstract class InputField extends HtmlField implements Errorable, Cleanable {
	
	protected List<String> errors = new ArrayList<String>();
	protected String type, attrs;
	public InputField(String name, String type) {
		super(name);
		this.type = type;
		attrs = null;
	}
	
	@Override
	public String toHtml() {
		return toHtml("");
	}
	
	/**
	 * 
	 * @param attrs Additional attributes to be given to the Input Feild
	 * @return
	 */
	public String toHtml(String attrs) {
		String v = getValue();
		String id = getId();
		String name = getName();
		String s =  String.format("<input type=\"%s\" id=\"%s\" name=\"%s\"%s", type, id, name, attrs);
		s += this.attrs == null ? "" : " " + this.attrs;
		return s + (type.equals("password") || v==null ? "" : " value=\""+v+"\"") + " />\n";
	}
	
	public void setAttrs(String as) {
		attrs = as;
	}
	
	public String getAttrs() {
		return attrs;
	}
	
	@Override
	public void addError(String err) {
		this.errors.add(err);
	}

	@Override
	public List<String> getErrors() {
		return this.errors;
	}

	@Override
	public FormError getFormError() {
		return new FormError(this);
	}
	
	@Override
	public void clean() {
		if(BasicValidation.isAtLeastXCharacters(getValue(), 1))
			setValue(getValue().trim());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InputField [errors=" + errors + ", type=" + type + ", attrs=" + attrs + ", getName()=" + getName() + ", getId()=" + getId()
				+ ", getValue()=" + getValue() + "]";
	}
	
	
}
