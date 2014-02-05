package com.github.mlaursen.bootstrap.forms.fields;

import static com.github.mlaursen.bootstrap.HtmlTab.tab;

/**
 * Creates a bootstrap controlgroup out of the fields
 * @author mikkel.laursen
 *
 */
public abstract class ControlGroup implements Cleanable, Errorable {
	
	/**
	 * Creates an array of Htmlable fields
	 * It's so the html code can be printed out bootstrap-y
	 * 
	 * Produces something like this.
	 * <div class="control-group">
	 *  {@code any labels and errors}
	 * 	<div class="controls">
	 * 	  {@code all fields as html}
	 * 	</div>
	 * </div>
	 * 
	 * @param fields
	 * @return
	 */
	public static HtmlFieldable[] wrap(HtmlFieldable... fields) {
		int l = fields.length;
		HtmlFieldable[] fs = new HtmlFieldable[l];
		for(int i = 0; i < l; i++) {
			HtmlFieldable f = fields[i];
			if(f instanceof HtmlField)
				fs[i] = new ControlFieldGroup((HtmlField) f);
			else if(f instanceof Dropdown)
				fs[i] = new ControlDropdownGroup((Dropdown) f);
		}
		return fs;
	}
	
	/**
	 * Part for adding the controls.  This allows any fields with labels to be 
	 * added correctly.
	 * @param controls
	 * @return
	 */
	public String toHtml(String controls) {
		String h = "<div class=\"control-group\">\n";
		h += controls;
		h += tab(1) + "</div>\n";
		h += "</div>\n";
		return h;
	}
	
	protected String controls() {
		return tab(1) + "<div class=\"controls\">\n";
	}
	
	public abstract HtmlFieldable getField();
}
