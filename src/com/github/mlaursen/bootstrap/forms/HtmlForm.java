package com.github.mlaursen.bootstrap.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.mlaursen.bootstrap.Htmlable;
import com.github.mlaursen.bootstrap.forms.fields.Cleanable;
import com.github.mlaursen.bootstrap.forms.fields.ControlDropdownGroup;
import com.github.mlaursen.bootstrap.forms.fields.ControlFieldGroup;
import com.github.mlaursen.bootstrap.forms.fields.ControlGroup;
import com.github.mlaursen.bootstrap.forms.fields.Errorable;
import com.github.mlaursen.bootstrap.forms.fields.HtmlFieldable;
import com.github.mlaursen.bootstrap.forms.fields.button.Button;
import com.github.mlaursen.bootstrap.forms.fields.button.SubmitButton;
import com.github.mlaursen.bootstrap.forms.fields.errors.FormError;
import com.github.mlaursen.bootstrap.forms.fields.input.MiddlewareToken;
import com.github.mlaursen.bootstrap.utils.Util;

/**
 * Basic Outline for a bootstrap formatted HTML Form
 * @author mikkel.laursen
 *
 */
public abstract class HtmlForm implements Htmlable {

	private List<HtmlFieldable> fields = new ArrayList<HtmlFieldable>();
	private MiddlewareToken middlewareToken;
	private List<FormError> errors = new ArrayList<FormError>();
	private Map<String, Integer> fieldKeys = new HashMap<String, Integer>();
	private String action, modalName;
	
	/**
	 * Creates an html form with the posting action to be the action given
	 * {@link #action} = @param action
	 * {@link #middlewareToken} = new basic middleware token with a random token and name of "middlewaretoken"
	 * {@link #fields} = a list with only the middleware token
	 * {@link #modalName} = "modal"
	 * @param action
	 */
	public HtmlForm(String action) {
		this.action = action;
		middlewareToken = new MiddlewareToken("middlewaretoken", Util.randomToken(32), this);
		addField(middlewareToken);
		modalName = "modal";
	}
	
	/**
	 * Creates an html form with the posting action to the action given
	 * and the middleware token name to the given token name
	 * {@link #HtmlForm}
	 * {@link #middlewareToken} with name @param middlewareTokenName
	 * @param action
	 * @param middlewareTokenName
	 */
	public HtmlForm(String action, String middlewareTokenName) {
		this(action);
		middlewareToken.setName(middlewareTokenName);
	}
	
	/**
	 * Adds a field to the form
	 * @param f	Field to add.
	 */
	public void addField(HtmlFieldable f) {
		this.fields.add(f);
		int size = fields.size() - 1;
		if(f instanceof ControlFieldGroup) {
			ControlFieldGroup field = (ControlFieldGroup) f;
			for(HtmlFieldable fld : field.getFields())
				this.fieldKeys.put(fld.getName(), size);
		}
		else
			this.fieldKeys.put(f.getName(), this.fields.size() - 1);
	}

	/**
	 * Add a list of fields to the form.
	 * @param fields	List of fields to add.
	 */
	public void addFields(List<HtmlFieldable> fields) {
		for (HtmlFieldable f : fields) {
			addField(f);
		}
	}
	
	/**
	 * Adds the fields to the list of fields on the form
	 * @param fields
	 */
	public void addFields(HtmlFieldable... fields) {
		for(HtmlFieldable f : fields) {
			addField(f);
		}
	}

	/**
	 * Set the modal name. Used with {@link #asModal()}
	 * @param n	Modal Name
	 */
	public void setModalName(String n) {
		modalName = n;
	}

	/**
	 * Returns the modal name;
	 * @return
	 */
	public String getModalName() {
		return modalName;
	}

	/**
	 * Sets the middleware token to the new Hidden field
	 * @param f	New field
	 */
	public void setMiddlewareToken(MiddlewareToken f) {
		middlewareToken = f;
	}
	
	/**
	 * Returns the middlewareToken
	 * @return	middlewareToken 
	 */
	public MiddlewareToken getMiddlewareToken() {
		return middlewareToken;
	}
	
	/**
	 * Sets the middleware token's value
	 * @param v	The new value
	 */
	public void setMiddlewareTokenValue(String v) {
		middlewareToken.setValue(v);
	}

	/**
	 * Gets the value of the middleware token's value
	 * @return value as String
	 */
	public String getMiddlewareTokenValue() {
		return middlewareToken.getValue();
	}
	
	/**
	 * Creates a string of errors on the form.
	 * @return	A string
	 */
	public String getErrorsAsText() {
		StringBuilder sb = new StringBuilder("");
		for(FormError e : errors) {
			sb.append(e.asText());
		}
		return sb.toString();
	}

	/**
	 * Basic HTML Format with no additions
	 * @return Valid HTML for a form
	 */
	@Override
	public String toHtml() {
		return toHtml("");
	}

	/**
	 * Creates the html for the form with extra html before the close of form
	 * 
	 * @param end  Extra html
	 * @return valid HTML
	 */
	public String toHtml(String end) {
		StringBuilder sb = new StringBuilder("");
		sb.append(String.format("<form class=\"form-horizontal\" method=\"post\" action=\"%s\">\n", this.action));
		for (HtmlFieldable f : fields) {
			sb.append(f.toHtml());
		}
		sb.append(end);
		sb.append("</form>\n");
		return sb.toString();
	}
	
	/**
	 * Creates a modal version of the form
	 * Used with {@link #toHtml(String)}.
	 * @return	Valid html for a modal
	 */
	public String asModal() {
		String h = String.format("<div id=\"%s\" class=\"modal hide fade\" tabindex=\"-1\" ", this.modalName);
		h += String.format("role=\"dialog\" aria-labelledby=\"%s_label\" aria-hidden=\"true\">\n", this.modalName);
		h += "  <div class=\"modal-header\">\n";
		h += "    <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">x</button>\n";
		h += String.format("    <h3 id=\"%s_label\">%s</h3>\n", this.modalName, Util.capitalizeFirst(this.modalName));
		h += "  </div>\n";
		h += "  <div class=\"modal-body\">\n";
		String footer = "  </div>\n  <div class=\"modal-footer\">\n";
		
		Button close = new Button("");
		close.setDataDismiss();
		close.setCss("btn");
		close.setValue("Close");
		Button create = new SubmitButton();
		create.setValue("Create");
		footer += new ControlFieldGroup(close, create).toHtml();
		
		footer += "  </div>\n";
		footer += "</div>\n";
		h += this.toHtml(footer);
		return h;
	}
	
	/**
	 * Basic check for if it is a valid form.  Checks all Errorable fields if they are valid.
	 * If they aren't, they add an error message to this form.
	 * It also cleans the cleanable fields.
	 * @return	If the error list is empty.
	 */
	public boolean isValid() {
		for(HtmlFieldable f : fields) {
			if(f instanceof Cleanable)
				((Cleanable) f).clean();
			if(f instanceof Errorable && !((Errorable) f).isValidField()) {
				errors.add(((Errorable) f).getFormError());
			}
		}
		return errors.isEmpty();
	}
	
	/**
	 * Gets a field by the field name
	 * @param id	Lookup name
	 * @return	HtmlFieldable requested
	 */
	public HtmlFieldable getField(String id) {
		HtmlFieldable c = fields.get(getFieldKey(id));
		if(c instanceof ControlFieldGroup)
			return ((ControlFieldGroup) c).getField(id);
		else if(c instanceof ControlDropdownGroup) {
			return ((ControlDropdownGroup) c).getField();
		}
		else if(c instanceof ControlGroup)
			return ((ControlGroup) c).getField();
		else
			return (HtmlFieldable) c;
	}
	
	/**
	 * Gets the value from a field by id
	 * @param id	Lookup id
	 * @return	Field value
	 */
	public String getFieldValue(String id) {
		return getField(id).getValue();
	}
	
	/**
	 * Gets the field key from Key/Value set
	 * @param key	Lookup key
	 * @return	Key's value
	 */
	public Integer getFieldKey(String key) {
		return fieldKeys.get(key);
	}
	
	public List<HtmlFieldable> getFields() {
		return fields;
	}
	
	/**
	 * Gets the Set of field keys
	 * @return	
	 */
	public Map<String, Integer> getFieldKeys() {
		return fieldKeys;
	}
	
	/**
	 * Updates a field in the list of fields
	 * @param c	The field to replace the existing field
	 */
	public void updateField(HtmlFieldable c) {
		fields.set(getFieldKey(c.getName()), c);
	}
	
	/**
	 * Updates multiple fields.
	 * {@link #updateField(HtmlFieldable)}
	 * @param codes
	 */
	public void updateFields(HtmlFieldable... codes) {
		for(HtmlFieldable c : codes)
			updateField(c);
	}
	
	/**
	 * Updates multiple fields
	 * {@link #updateField(HtmlFieldable)}
	 * @param codes
	 */
	public void updateFields(List<HtmlFieldable> codes) {
		for(HtmlFieldable c : codes)
			updateField(c);
	}
	
	/**
	 * Updates a field's value from a servlet request and then calls
	 * {@link #updateField(HtmlFieldable)}
	 * @param fieldName	Field to update
	 * @param request	The HttpServletRequest to lookup the value of
	 */
	public void updateValue(String fieldName, HttpServletRequest request) {
		updateValue(fieldName, request, true);
	}
	
	/**
	 * Updates a field's value by field name.
	 * it re-wraps the field in the control group if the argument wrap is set to true
	 * @param fieldName
	 * @param request
	 * @param wrap
	 */
	public void updateValue(String fieldName, HttpServletRequest request, boolean wrap) {
		HtmlFieldable f = this.getField(fieldName);
		String val = (String) request.getParameter(fieldName);
		if(f instanceof ControlFieldGroup)
			((ControlFieldGroup) f).setValue(val, fieldName);
		else
			f.setValue(val);
		if(wrap)
			updateFields(ControlGroup.wrap(f));
		else
			updateField(f);
	}
	
	/**
	 * Checks if the form was the source of the request.
	 * This is mostly used if there are multiple forms on a page.
	 * @param request	HttpRequest
	 * @return	if it was from the httprequest
	 */
	public boolean isFromRequest(HttpServletRequest request) {
		return request.getParameter(middlewareToken.getName()) != null;
	}
}
