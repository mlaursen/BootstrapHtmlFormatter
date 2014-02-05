package com.github.mlaursen.bootstrap.forms.fields.input;


import com.github.mlaursen.bootstrap.forms.fields.CssAble;
import com.github.mlaursen.bootstrap.forms.fields.Labelable;
import com.github.mlaursen.bootstrap.forms.fields.Placeholderable;
import com.github.mlaursen.bootstrap.forms.fields.errors.BasicValidation;
import com.github.mlaursen.bootstrap.forms.fields.errors.MeasureableLength;
import com.github.mlaursen.bootstrap.utils.Util;

/**
 * Html Text field.
 * 
 * @author mikkel.laursen
 *
 */
public class TextField extends InputField implements Labelable, Placeholderable, MeasureableLength, CssAble {

	private String label, placeholder, css;
	private int minlength, maxlength;
	public TextField(String name) {
		super(name, "text");
		this.placeholder = Util.capitalizeFirst(name);
		this.label = this.placeholder + ":";
		css = null;
	}
	
	public TextField(String name, int minlength, int maxlength) {
		this(name);
		this.minlength = minlength;
		this.maxlength = maxlength;
	}
	
	public TextField(String name, int minlength, int maxlength, String css) {
		this(name, minlength, maxlength);
		this.css = css;
	}
	
	@Override
	public String toHtml() {
		String s = "";
		s += (minlength == 0 ? "" : " minlength=\"" + minlength + "\"");
		s += (maxlength == 0 ? "" : " maxlength=\"" + maxlength + "\"" );
		s += (placeholder == null ? "" : " placeholder=\"" + placeholder + "\"");
		s += (css == null ? "" : " class=\"" + css + "\"");
		return super.toHtml(s);
	}

	@Override
	public boolean isValidField() {
		boolean valid = true;
		if(!BasicValidation.isAtLeastXCharacters(getValue(), 1)) {
			this.errors.add("must not be blank.");
			valid = false;
		}
		else {
			if (!BasicValidation.isAtLeastXCharacters(this)) {
				this.errors.add(String.format("must be at least %d characters.", this.minlength));
				valid = false;
			}
			if (!BasicValidation.isLessThanEqualToXCharacters(this)) {
				this.errors.add(String.format("must be no more than %d characters.", this.maxlength));
				valid = false;
			}
		}
		return valid;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public void setLabel(String l) {
		label = l;
	}

	@Override
	public void setPlaceholder(String ph) {
		placeholder = ph;		
	}

	@Override
	public String getPlaceholder() {
		return placeholder;
	}
	
	@Override
	public void setMinlength(int m) {
		minlength = m;
	}
	
	@Override
	public void setMaxlength(int m) {
		maxlength = m;
	}
	
	@Override
	public int getMinlength() {
		return minlength;
	}
	
	@Override
	public int getMaxlength() {
		return maxlength;
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
