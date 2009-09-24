package com.bluexml.side.util.libs;

import org.apache.commons.lang.WordUtils;

public class StringHelper {
	/**
	 * take a string like "word<token>word2<token>word3"
	 * 
	 * @param st
	 * @return "wordWord2Word3"
	 */
	public static String getJavaQName(String st, String token) {
		String token_ = token.replaceAll("\\\\", "");
		String first = st.substring(0, st.indexOf(token_));
		String other = st.substring(st.indexOf(token_));
		String intermediate = other.replaceAll(token, " ");
		String out = WordUtils.capitalize(intermediate);
		return first + out.replaceAll(" ", "");

	}
}
