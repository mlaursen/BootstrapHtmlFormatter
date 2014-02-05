package com.github.mlaursen.bootstrap.utils;

import java.util.Random;

public class Util {
	
	/**
	 * Capitalize the first letter of each word in a string.
	 * Splits the string on underscores be default
	 * @param str
	 * @return
	 */
	public static String capitalizeFirst(String str) {
		return capitalizeFirst(str, "_");
	}
	
	/**
	 * 
	 * @param str
	 * @param split
	 * @return
	 */
	public static String capitalizeFirst(String str, String split) {
		String[] words = str.split(split);
		String s = "";
		for (String word : words) {
			s += capitalizeFirstLetter(word) + " ";
		}
		return s.trim();
	}

	/**
	 * Capitalizes the first letter of a word
	 * @param s
	 * @return
	 */
	public static String capitalizeFirstLetter(String s) {
		String s2 = s.substring(1).toLowerCase();
		return s.substring(0, 1).toUpperCase() + s2;
	}
	

	/**
	 * Creates a random token with the characters "A-z@#$&"
	 * @param length	Length of the code
	 * @return	code String
	 */
	public static String randomToken(int length) {
		return randomToken(length, ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890@#$&").toCharArray());
	}
	
	/**
	 * Creates  arandom token out of the tokenChoices
	 * @param length		Length of the code
	 * @param tokenChoices	Available characters to be in the token
	 * @return	code String
	 */
	public static String randomToken(int length, char[] tokenChoices) {
		StringBuilder sb = new StringBuilder("");
		Random r = new Random();
		int len = tokenChoices.length;
		for(int i = 0; i < length; i++)
			sb.append(tokenChoices[r.nextInt(len)]);
		return sb.toString();
	}
}
