package com.github.mlaursen.bootstrap.forms.fields;


/**
 * Basic Version of BasicHtmlable.
 * 
 * @author mikkel.laursen
 *
 */
public abstract class HtmlField implements HtmlFieldable {

	private String name, id, value;
	public HtmlField(String name) {
		this.name = name;
		this.id = "id_" + name;
		this.value = null;
	}
	
	@Override
	public void setName(String n) {
		name = n;
	}
	
	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public void setValue(String v) {
		value = v;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public String getValue() {
		return value;
	}

}
