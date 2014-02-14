package com.github.mlaursen.bootstrap.forms.fields.input;

import com.github.mlaursen.bootstrap.forms.fields.errors.BasicValidation;
import com.github.mlaursen.bootstrap.utils.Util;


public class DateField extends TextField {

	public DateField(String name) {
		super(name);
	}

	public DateField(String name, int minlength, int maxlength) {
		super(name, minlength, maxlength);
	}

	public DateField(String name, int minlength, int maxlength, String css) {
		super(name, minlength, maxlength, css);
	}

	@Override
	public boolean isValidField() {
		boolean valid = super.isValidField();
		if(!BasicValidation.isDate(this)) {
			valid = false;
			this.errors.add("must be a valid date. mm/dd/yyyy");
		}
		return valid;
	}
	
	public void setValue(java.sql.Date d) {
		super.setValue(Util.sqlDateToString(d));
	}
}
