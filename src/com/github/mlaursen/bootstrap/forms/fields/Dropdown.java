package com.github.mlaursen.bootstrap.forms.fields;

import java.util.List;

import com.github.mlaursen.bootstrap.forms.fields.errors.BasicValidation;
import com.github.mlaursen.bootstrap.forms.fields.errors.FormError;
import com.github.mlaursen.bootstrap.forms.fields.input.HiddenField;
import com.github.mlaursen.bootstrap.utils.Util;

import static com.github.mlaursen.bootstrap.HtmlTab.tab;

/**
 * Generates a dropdown selection in bootstrap format
 * @author mikkel.laursen
 *
 */
public class Dropdown implements Cleanable, Errorable, Labelable {

	private List<DropdownChoice> choices;
	private String buttonId, choicesId, onclick, label;
	private HiddenField groupValue;
	private Integer chosen;
	private boolean canBe0;
	public Dropdown(String name, List<DropdownChoice> choices) {
		this.choices = choices;
		this.groupValue = new HiddenField(name);
		this.buttonId = name + "_button";
		this.choicesId = name + "_choices";
		this.onclick = "selectItemDropdown";
		this.chosen = 0;
		this.label = Util.capitalizeFirst(name) +":";
		canBe0 = false;
	}

	public boolean getCanBe0() {
		return canBe0;
	}
	
	public void setCanBe0(boolean b) {
		canBe0 = b;
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
	public boolean isValidField() {
		String val = groupValue.getValue();
		if(val == null || val.equals(""))
			return false;
		else {
			for(DropdownChoice c : choices) {
				if(c.getKey().equals(val))
					return canBe0 || (!canBe0 && Integer.parseInt(val) > 0);
			}
			groupValue.addError(String.format("'%s' is an invalid choice.", choices.get(Integer.parseInt(val)).getValue()));
			return false;
		}
	}
	
	@Override
	public void addError(String err) {
		groupValue.addError(err);
	}

	@Override
	public List<String> getErrors() {
		return groupValue.getErrors();
	}

	@Override
	public FormError getFormError() {
		return groupValue.getFormError();
	}

	@Override
	public void clean() {
		groupValue.clean();
	}

	@Override
	public String toHtml() {
		DropdownChoice def = this.choices.get(this.chosen);
		String h = "";
		h += tab() + "<div class=\"input-append\">\n";
		h += tab(2) + "<div class=\"btn-group\">\n";
		h += tab(3) + String.format("<button id=\"%s\" class=\"btn dropdown-toggle\"", this.buttonId);
		h += String.format(" data-toggle=\"dropdown\">%s", def.getValue());
		h += " <span class=\"caret\"></span></button>\n";
		
		groupValue.setValue(def.getKey());
		h += tab(3) + groupValue.toHtml() + "\n";
		h += tab(3) + String.format("<ul class=\"dropdown-menu\" id=\"%s\">\n", this.choicesId);
		for (DropdownChoice c : choices) {
			h += tab(4) + "<li><a href=\"#\"";
			h += String.format(" id=\"id_%s\" onclick=\"%s(this)\">%s</a></li>\n", c.getKey(), onclick, c.getValue());
			if (c.getValue().contains("Select"))
				h += tab(4) + "<li class=\"divider\"></li>\n";
		}
		h += tab(3) + "</ul>\n";
		h += tab(2) + "</div>\n";
		h += tab(1) + "</div>\n";
		h += tab(1) + "<span class=\"error help-inline\" id=\"" + getName() + "_help\">" + getFormError().asText() + "</span>\n";
		return h;
	}

	@Override
	public String getName() {
		return groupValue.getName();
	}

	@Override
	public String getId() {
		return groupValue.getId();
	}

	@Override
	public void setName(String n) {
		groupValue.setName(n);
	}

	@Override
	public void setId(String id) {
		groupValue.setId(id);
	}

	@Override
	public void setValue(String v) {
		if(BasicValidation.isAtLeastXCharacters(v, 1)) {
			groupValue.setValue(v);
			setChosen(v);
		}
	}

	@Override
	public String getValue() {
		return groupValue.getValue();
	}

	/**
	 * Sets the current selection. Converts the num into an integer
	 * {@link #setChosen(Integer)}
	 * @param num	String new id of the selection
	 */
	public void setChosen(String num) {
		try {
			setChosen(Integer.parseInt(num));
		}
		catch(NumberFormatException e) {
			for(int i = 0; i < choices.size(); i++)
				if(choices.get(i).getValue().equals(num)) {
					setChosen(i);
					return;
				}
		}
	}

	/**
	 * Sets the current selection to the new integer.
	 * @param i	Current selection
	 */
	public void setChosen(Integer i) {
		chosen = i;
	}
	
	/**
	 * Gets the current selection id
	 * @return
	 */
	public Integer getChosen() {
		return chosen;
	}
	
	public List<DropdownChoice> getChoices() {
		return choices;
	}
	
	public void addChoice(DropdownChoice c) {
		choices.add(c);
	}
}
