package com.github.mlaursen.bootstrap.forms.fields;

import java.util.List;

import com.github.mlaursen.bootstrap.forms.fields.button.Button;
import com.github.mlaursen.bootstrap.forms.fields.errors.BasicValidation;
import com.github.mlaursen.bootstrap.forms.fields.input.TextField;

import static com.github.mlaursen.bootstrap.HtmlTab.tab;

public class TextAction extends Dropdown implements CssAble {

	private TextField f;
	private String onclick;
	public TextAction(String name, List<DropdownChoice> choices) {
		super(name, choices);
		f = new TextField(name);
		onclick = "selectItem(this)";
		setChosen(0);
	}
	
	public void setReadOnly() {
		f.setAttrs("readonly=\"readonly\"");
	}

	@Override
	public String toHtml() {
		List<DropdownChoice> choices = getChoices();
		int choice = getChosen();
		if(choice > 0)
			setReadOnly();
		String h = tab() + "<div class=\"input-append\">\n";
		h += tab(2) + f.toHtml();
		h += tab(2) + "<div class=\"btn-group\">\n";
		Button b = new Button(getName());
		b.setCss("btn dropdown-toggle");
		b.setDataToggle("dropdown");
		b.setValue(choices.get(choice).getValue() + " <span class=\"caret\"></span>");
		h += tab(3) + b.toHtml();
		h += tab(3) + "<ul class=\"dropdown-menu\" id=\"" + getName() + "_list\">\n";
		for(int i = 0; i < choices.size(); i++) {
			DropdownChoice c = choices.get(i);
			h += tab(4) + "<li><a href=\"#\" onclick=\"" + onclick + "\">" + c.getValue() + "</a></li>\n";
			if(i == 0) 
				h += tab(4) + "<li class=\"divider\"></li>\n";
		}
		h += tab(3) + "</ul>\n";
		h += tab(2) + "</div>\n";
		h += tab() + "</div>\n";
		return h;
	}

	@Override
	public void setCss(String css) {
		f.setCss(css);
	}

	@Override
	public String getCss() {
		return f.getCss();
	}
	
	public String getOnclick() {
		return onclick;
	}
	
	public void setOnclick(String o) {
		onclick = o;
	}
	


	@Override
	public String getName() {
		return f.getName();
	}

	@Override
	public String getId() {
		return f.getId();
	}

	@Override
	public void setName(String n) {
		f.setName(n);
	}

	@Override
	public void setId(String id) {
		f.setId(id);
	}

	@Override
	public void setValue(String v) {
		if(BasicValidation.isAtLeastXCharacters(v, 1)) {
			f.setValue(v);
			setChosen(v);
		}
	}

	@Override
	public String getValue() {
		return f.getValue();
	}
	
	@Override
	public boolean isValidField() {
		boolean valid = f.isValidField();
		String val = f.getValue();
		if(valid && BasicValidation.isNumber(val)) {
			for(DropdownChoice c : getChoices()) {
				if(c.getKey().equals(val)) {
					return true;
				}
			}
			f.addError(String.format("'%s' is an invalid choice.", getChoices().get(Integer.parseInt(val)).getValue()));
			return false;
		}
		return valid;
	}
}
