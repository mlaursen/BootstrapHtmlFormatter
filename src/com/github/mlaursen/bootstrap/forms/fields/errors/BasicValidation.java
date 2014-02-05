package com.github.mlaursen.bootstrap.forms.fields.errors;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.List;

import com.github.mlaursen.bootstrap.forms.fields.HtmlField;
import com.github.mlaursen.bootstrap.forms.fields.input.InputField;


public class BasicValidation {
	/**
	 * Checks if a field is at least the minimum length for the given field.
	 * The field MUST have a MeasureableLength
	 * @param f	The field to check
	 * @return	field value is at least the minimum length
	 */
	public static boolean isAtLeastXCharacters(MeasureableLength f) {
		Integer minlength = f.getMinlength();
		if(minlength == 0 || minlength == null) return true;
		else if(f.getValue().equals(null)) return false;
		else return isAtLeastXCharacters(f.getValue(), minlength);
	}

	/**
	 * Checks if a string is at least x characters long.
	 * @param value	The string to check
	 * @param x	The minumum length
	 * @return	Value's length >= x
	 */
	public static boolean isAtLeastXCharacters(String value, int x) {
		try {
			return value.length() >= x;
		}
		catch(Exception e) {
			return false;
		}
	}

	/**
	 * Checks if a field's value is in a List of enum choices 
	 * @param f	The field to check
	 * @param choices	List of enum choices
	 * @return	true if the value is in the list of enums
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isLegalResult(HtmlField f, List<Enum> choices) {
		try {
			for (Enum c : choices) {
				if (c.ordinal() == Integer.parseInt(f.getValue()))
					return true;
			}
		}
		catch (Exception e) {
			return false;
		}
		return false;
	}

	public static boolean isLessThanEqualToXCharacters(MeasureableLength f) {
		Integer maxlength = f.getMaxlength();
		if(maxlength == 0 || maxlength == null) return true;
		else if(f.getValue().equals(null)) return false;
		else return isLessThanEqualToXCharacters(f.getValue(), maxlength);
	}

	public static boolean isLessThanEqualToXCharacters(String s, int x) {
		try {
			return s.length() <= x;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Checks if a field is in the date format of MMddyyy.
	 * {@link #isDate(InputField, String)}.
	 * @param f The field
	 * @return	
	 */
	public static boolean isDate(InputField f) {
		return isDate(f, "MMddyyy");
	}
	
	/**
	 * Checks if a field is a date in the format given
	 * @param f			The field to check
	 * @param format	The date format
	 * @return
	 */
	public static boolean isDate(InputField f, String format) {
		String d = f.getValue().replace("-", "").replace("/", "");
		try {
			new SimpleDateFormat(format).parse(d);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * http://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-a-numeric-type-in-java
	 * @param val
	 * @return
	 */
	public static boolean isNumber(String val) {
		try {
			NumberFormat nf = NumberFormat.getInstance();
			ParsePosition p = new ParsePosition(0);
			nf.parse(val, p);
			return val.length() == p.getIndex();
		}
		catch(Exception e) {
			return false;
		}
	}
}
