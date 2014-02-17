package com.github.mlaursen.bootstrap.forms.fields;

import static com.github.mlaursen.bootstrap.HtmlTab.tab;

import java.util.List;

import com.github.mlaursen.bootstrap.forms.fields.errors.FormError;

public class ControlDropdownGroup extends ControlGroup {

	private Dropdown d;
	public ControlDropdownGroup(Dropdown d) {
		this.d = d;
	}
	
	@Override
	public void clean() {
		d.clean();
	}
	
	@Override
	public void setId(String id) {
		d.setId(id);
	}
	
	@Override
	public void setName(String n) {
		d.setName(n);
	}
	
	@Override
	public void setValue(String v) {
		d.setValue(v);
	}
	@Override
	public String getId() {
		return d.getId();
	}
	
	@Override
	public String getName() {
		return d.getName();
	}
	
	@Override
	public String getValue() {
		return d.getValue();
	}
	
	@Override
	public String toHtml() {
		String h = tab(1) + "<label class=\"control-label\" for=\"" + d.getName() + "\">";
		h += d.getLabel() + "</label>\n";
		h += super.controls();
		h += d.toHtml();
		return super.toHtml(h);
	}
	
	@Override
	public HtmlFieldable getField() {
		return d;
	}

	@Override
	public boolean isValidField() {
		return d.isValidField();
	}

	@Override
	public List<String> getErrors() {
		return d.getErrors();
	}

	@Override
	public FormError getFormError() {
		return d.getFormError();
	}

	@Override
	public void addError(String err) {
		d.addError(err);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ControlDropdownGroup [d=" + d + ", getId()=" + getId() + ", getName()=" + getName() + ", getValue()=" + getValue() + "]";
	}

}
