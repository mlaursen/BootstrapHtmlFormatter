package com.github.mlaursen.bootstrap.sidebar;

public interface SidebarItemable {
	
	/**
	 * The name to display in the sidebar.
	 * The same name if it is a database object
	 * @return	String name
	 */
	String getName();
	
	/**
	 * The idnumber to be placed in the sidebar. "0" would display id_0.
	 * The same id if it is a database object.
	 * @return
	 */
	String getId();
}
