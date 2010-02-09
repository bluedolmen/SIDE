package com.bluexml.side.form.editor.views.service;

import java.util.HashMap;
import java.util.Map;

public class HTMLEncoder {
	private static Map<Character, String> mapChar2HTMLEntity;
	
	private final static char[] characters = { '\u00DC', '\u00C4', '\u00D6',
			'\u00CB', '\u00C7', '\u00C6', '\u00C5', '\u00D8', '\u00FC',
			'\u00E4', '\u00F6', '\u00EB', '\u00E7', '\u00E5', '\u00F8',
			'\u0060', '\u00E0', '\u00E8', '\u00EC', '\u00F2', '\u00F9',
			'\u0026', '\u00DF', '\u00A0', '\u003E', '\u003C', '\u009A',
			'\u00A2', '\u00A3', '\u00AB', '\u00BB', '\u00AE', '\u00AD', '\'',
			'\u00E1', '\u00FA', '\u00F3', '\u00E9', '\u00ED', '\u00F1',
			'\u00A7', '\u00E8', '\u00EE', '\u00F4', '\u00E2', '\u00FB',
			'\u00EA', '\u00E6', '\u00A1', '\u0022', '\u00AA', '\u00D7',
			'\u00B0', '\u20AC', '\u007C', '\u00B5', '\u00BA', '\u00F7',
			'\u00B2', '\u00B3', '\u00B1' };
	private final static String[] entities = { "&Uuml;", "&Auml;", "&Ouml;",
			"&Euml;", "&Ccedil;", "&AElig;", "&Aring;", "&Oslash;", "&uuml;",
			"&auml;", "&ouml;", "&euml;", "&ccedil;", "&aring;", "&oslash;",
			"&grave;", "&agrave;", "&egrave;", "&igrave;", "&ograve;",
			"&ugrave;", "&amp;", "&szlig;", "&nbsp;", "&gt;", "&lt;", "&copy;",
			"&cent;", "&pound;", "&laquo;", "&raquo;", "&reg;", "&middot;",
			"&acute;", "&aacute;", "&uacute;", "&oacute;", "&eacute;",
			"&iacute;", "&ntilde;", "&sect;", "&egrave;", "&icirc;", "&ocirc;",
			"&acirc;", "&ucirc;", "&ecirc;", "&aelig;", "&iexcl;", "&quot;",
			"&ordf;", "&times;", "&deg;", "&euro;", "&brvbar;", "&micro;",
			"&ordm;", "&divide", "&sup2;", "&sup3;", "&plusmn;" };

	public HTMLEncoder() {
		mapChar2HTMLEntity = new HashMap<Character, String>();
		int longueur = characters.length;
		for (int i = 0; i < longueur; i++)
			mapChar2HTMLEntity.put(new Character(characters[i]), entities[i]);
	}

	public String encode(String s) {
		int longueur = s.length();
		final StringBuffer sb = new StringBuffer(longueur * 2);
		char ch;
		for (int i = 0; i < longueur; ++i) {
			ch = s.charAt(i);
			if ((ch >= 63 && ch <= 90) || (ch >= 97 && ch <= 122))
				sb.append(ch);
			else {
				String ss = (String) mapChar2HTMLEntity.get(new Character(ch));
				if (ss == null)
					sb.append(ch);
				else
					sb.append(ss);
			}
		}
		return sb.toString();
	}
}