package com.github.mlaursen.bootstrap.forms.fields.button;

import com.github.mlaursen.bootstrap.forms.fields.CssAble;
import com.github.mlaursen.bootstrap.forms.fields.HtmlField;

/**
 * An HtmlButton formatted with bootstrap style
 * 
 * @author mikkel.laursen
 *
 */
public class Button extends HtmlField implements CssAble {

	protected String css, dataDismiss, type, dataToggle;
	public Button(String name) {
		super(name);
		type = "";
		css = "btn btn-primary";
	}
	public Button(String name, String type) {
		super(name);
		this.type = type;
		css = "btn btn-primary";
	}

	@Override
	public String toHtml() {
		String h = "<button name=\"" + getName() + "_button\"";
		h += " id=\"" + getName() + "_button\"";
		h += (type == null ? "" : " type=\"" + type + "\"");
		h += (dataDismiss == null ? "" : " data-dismiss=\"" + dataDismiss + "\"");
		h += (dataToggle == null ? "" : " data-toggle=\"" + dataToggle + "\"");
		h += (css == null ? "" : " class=\"" + css + "\"");
		h += ">" + getValue() + "</button>\n";
		return h;
	}
	
	public void setDataToggle() {
		setDataToggle("dropdown");
	}
	
	public void setDataToggle(String t) {
		dataToggle = t;
	}
	
	public String getDataToggle() {
		return dataToggle;
	}
	
	/**
	 * Set the data dismiss to "modal"
	 */
	public void setDataDismiss() { 
		setDataDismiss("modal");
	}
	
	/**
	 * Set the dataDismiss to a new value
	 * @param dataDismiss New data dismiss value
	 */
	public void setDataDismiss(String dataDismiss) {
		this.dataDismiss = dataDismiss;
	}

	@Override
	public String getCss() {
		return css;
	}
	
	@Override
	public void setCss(String css) {
		this.css = css;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Button [css=" + css + ", dataDismiss=" + dataDismiss + ", type=" + type + ", dataToggle=" + dataToggle + ", getName()="
				+ getName() + ", getId()=" + getId() + ", getValue()=" + getValue() + "]";
	}

	
	
}
