package com.github.mlaursen.bootstrap.forms.fields;

import java.util.List;


/**
 * Allows something to be in a Dropdown
 * @author mikkel.laursen
 *
 */
public interface DropdownChoice {

	String getDropdownValue();
	String getDropdownKey();
	List<DropdownChoice> getAllChoices();
	
}
