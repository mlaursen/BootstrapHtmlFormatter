package com.github.mlaursen.bootstrap.sidebar;

import java.util.List;

import com.github.mlaursen.bootstrap.utils.Util;

import static com.github.mlaursen.bootstrap.HtmlTab.tab;

/**
 * A list of sidebar items.
 * 
 * 
 * @author mikkel.laursen
 *
 * @param <T>	Any generic type that extends SidebarItemable interface
 */
public class SidebarItemList<T extends SidebarItemable>  {

	private List<T> items;
	private String sidebarName, header, onclick, groupName;
	public SidebarItemList(String sidebarName, String header, String groupName) {
		this.sidebarName = sidebarName;
		this.header = header;
		this.onclick = "activate(this)";
		this.groupName = groupName;
	}
	public SidebarItemList(String sidebarName, String header, String onclick, String groupName) {
		this(sidebarName, header, groupName);
		this.onclick = onclick;
	}
	
	public SidebarItemList(String sidebarName, String header, String groupName, List<T> items) {
		this(sidebarName, header, groupName);
		this.items = items;
	}


	
	public String getSidebarName() {
		return sidebarName;
	}

	
	public void setSidebarName(String n) {
		sidebarName = n;
	}

	
	public String getOnclick() {
		return onclick;
	}

	
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
	
	
	public void setHeader(String h) {
		header = h;
	}
	
	
	public String getHeader() {
		return header;
	}
	
	
	public void setGroupName(String g) {
		groupName = g;
	}
	
	
	public String getGroupName() {
		return groupName;
	}
	
	/**
	 * Creates html for the list of sidebar items.
	 * 
	 * @return
	 */
	public String toHtml() {
		String s = tab(3) + String.format("<ul name=\"%s_nav\" class=\"nav nav-list\">\n", sidebarName);
		s += tab(4) + String.format("<li class=\"nav-header\">%s</li>\n", header);
		s += tab(5) + "<li id=\"" + groupName + "_all\" class=\"active\" name=\"" + groupName + "\"";
		s += " onclick=\"" + onclick + "\">";
		s += String.format("<a href=\"#\">All %s</a></li>\n", Util.capitalizeFirst(groupName));
		for(SidebarItemable i : items) {
			s += tab(5);
			s += String.format("<li id=\"%s_%s\" name=\"%s\"", groupName, i.getName().toLowerCase(), groupName);
			s += String.format(" onclick=\"%s\"><a href=\"#\">%s</a></li>\n", onclick, i.getName());
		}
		s += tab(4) + "</ul>";
		return s;
	}
}
