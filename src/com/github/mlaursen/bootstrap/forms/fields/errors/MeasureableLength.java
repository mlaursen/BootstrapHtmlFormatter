package com.github.mlaursen.bootstrap.forms.fields.errors;

import com.github.mlaursen.bootstrap.forms.fields.Errorable;

public interface MeasureableLength extends Errorable {

	void setMinlength(int m);
	void setMaxlength(int m);
	int getMinlength();
	int getMaxlength();
}
