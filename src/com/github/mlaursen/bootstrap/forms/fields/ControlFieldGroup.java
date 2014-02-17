package com.github.mlaursen.bootstrap.forms.fields;

import static com.github.mlaursen.bootstrap.HtmlTab.tab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.mlaursen.bootstrap.forms.fields.errors.FormError;

public class ControlFieldGroup extends ControlGroup {

	private HtmlFieldable[] fields;
	public ControlFieldGroup(HtmlFieldable... fields) {
		this.fields = fields;
	}
	
	@Override
	public void clean() {
		for(HtmlFieldable f : fields) {
			if(f instanceof Cleanable)
				((Cleanable) f).clean();
		}
	}
	@Override
	public void setId(String id) {
		if(fields.length >= 1)
			fields[0].setId(id);
	}
	
	@Override
	public void setName(String n) {
		if(fields.length >= 1)
			fields[0].setName(n);
	}
	
	@Override
	public void setValue(String v) {
		if(fields.length >= 1)
			fields[0].setName(v);
	}
	
	public void setValue(String v, String fieldName) {
		for(HtmlFieldable f : fields) {
			if(f.getName().equals(fieldName)) {
				f.setValue(v);
				return;
			}
		}
	}
	public void setValues(String[] vs) {
		for(int i = 0; i < fields.length; i++) {
			fields[i].setValue(vs[i]);
		}
	}
	
	public String[] getValues() {
		String[] values = new String[fields.length];
		for(int i = 0; i < fields.length; i++) {
			values[i] = fields[i].getValue();
		}
		return values;
	}
	
	@Override
	public String getId() {
		return fields.length >= 1 ? fields[0].getId() : "";
	}
	@Override
	public String getName() {
		return fields.length >= 1 ? fields[0].getName() : "";
	}
	@Override
	public String getValue() {
		return fields.length >= 1 ? fields[0].getValue() : "";
	}
	
	@Override
	public String toHtml() {
		String h = "";
		String inControls = "";
		// adds all the labels before creating controls
		for(HtmlFieldable f : fields) {
			if(f instanceof Labelable && ((Labelable) f).getLabel() != null) {
				h += tab(1) + "<label class=\"control-label\" for=\"" + f.getName() + "\">";
				h += ((Labelable) f).getLabel() + "</label>\n";
			}
			inControls += tab(2) + f.toHtml();
			if(f instanceof Errorable) {
				inControls += tab(2) + "<span class=\"error help-inline\" id=\"" + f.getName() + "_help\">";
				inControls += ((Errorable) f).getFormError().asText() + "</span>\n";
			}
		}
		h += super.controls() + inControls;
		return super.toHtml(h);
	}
	
	public void addField(HtmlFieldable f) {
		int len = fields.length;
		HtmlFieldable[] newFields = new HtmlFieldable[len+1];
		for(int i = 0; i < len; i++) {
			newFields[i] = fields[i];
		}
		newFields[len] = f;
		fields = newFields;
	}

	@Override
	public HtmlFieldable getField() {
		return getField(0);
	}
	
	public HtmlFieldable getField(String name) {
		for(HtmlFieldable f : fields) {
			if(f.getName().equals(name))
				return f;
		}
		return null;
	}
	public HtmlFieldable getField(int i) {
		return fields.length < i ? null : fields[i];
	}
	
	public HtmlFieldable[] getFields() {
		return fields;
	}

	@Override
	public boolean isValidField() {
		boolean valid = true;
		for(HtmlFieldable f : fields) {
			if(f instanceof Errorable) {
				boolean valid2 = ((Errorable) f).isValidField();
				valid = valid ? valid2 : false;
			}
		}
		return valid;
	}

	@Override
	public List<String> getErrors() {
		List<String> errs = new ArrayList<String>();
		for(HtmlFieldable f : fields) {
			if(f instanceof Errorable) {
				Errorable e = (Errorable) f;
				errs.addAll(e.getErrors());
			}
		}
		return errs;
	}

	@Override
	public FormError getFormError() {
		for(HtmlFieldable f : fields) {
			if(f instanceof Errorable) {
				return ((Errorable) f).getFormError();
			}
		}
		return null;
	}

	@Override
	public void addError(String err) {
		for(HtmlFieldable f : fields) {
			if(f instanceof Errorable) {
				((Errorable) f).addError(err);
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ControlFieldGroup [fields=" + Arrays.toString(fields) + "]";
	}
	
}
