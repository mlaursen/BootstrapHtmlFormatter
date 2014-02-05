package com.github.mlaursen.bootstrap;

public final class HtmlTab {
	/**
	 * Creates a tab character for html.
	 * @return
	 */
	public static String tab() {
		return tab(1);
	}
	
	/**
	 * Creates multiple tabs for html
	 * @param amt	Amount of tabs
	 * @return
	 */
	public static String tab(int amt) {
		return tab(amt, 2);
	}
	
	/**
	 * Creates multiple tabs for html.
	 * @param amt		Amount of tabs
	 * @param tabSpaces	How many spaces a tab character is
	 * @return
	 */
	public static String tab(int amt, int tabSpaces) {
		String t = new String(new char[tabSpaces]).replace("\0", " ");
		return new String(new char[amt]).replace("\0", t);
	}

}
