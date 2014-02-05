package com.github.mlaursen.bootstrap.forms.fields.link;

import com.github.mlaursen.bootstrap.forms.fields.CssAble;
import com.github.mlaursen.bootstrap.forms.fields.HtmlField;

public class Link extends HtmlField implements CssAble {

	protected String href, dataToggle, css;
	public Link(String name) {
		super(name);
	}

	@Override
	public String toHtml() {
		String h = "<a href=\"" + href + "\"";
		h += (css == null ? "" : " class=\"" + css + "\"");
		h += (dataToggle == null ? "" : " data-toggle=\"" + dataToggle + "\"");
		h += " name=\"" + getName() + "\">" + getValue() + "</a>\n";
		return h;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	public void setDataToggle() {
		setDataToggle("modal");
	}
	
	public void setDataToggle(String dt) {
		dataToggle = dt;
	}
	
	public String getHref() {
		return href;
	}
	
	public String getDataToggle() {
		return dataToggle;
	}

	@Override
	public void setCss(String css) {
		this.css = css;		
	}

	@Override
	public String getCss() {
		return css;
	}
}
