/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
