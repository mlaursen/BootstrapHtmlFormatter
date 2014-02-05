package com.github.mlaursen.bootstrap.sidebar;

import com.github.mlaursen.bootstrap.Htmlable;


public class SidebarNav implements Htmlable {

	private SidebarItemList<? extends SidebarItemable>[] items;
	
	@SafeVarargs
	public SidebarNav(SidebarItemList<? extends SidebarItemable>... items) {
		this.items = items;
	}
	
	
	public String toHtml() {
		String h = "";
		int size = items.length;
		for(int i = 0; i < size; i++) {
			h += items[i].toHtml();
			if(i+1 < size)
				h += "<hr />\n";
		}
		return h;
	}
}
