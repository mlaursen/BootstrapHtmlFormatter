package com.github.mlaursen.bootstrap.forms.fields;

import com.github.mlaursen.bootstrap.Htmlable;

public interface HtmlFieldable extends Htmlable {
	
	void setId(String id);
	void setName(String n);
	void setValue(String v);
	String getId();
	String getName();
	String getValue();
}
