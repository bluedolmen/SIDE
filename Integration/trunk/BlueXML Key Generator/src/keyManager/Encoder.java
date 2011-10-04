/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2009
 *
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package keyManager;

import java.math.BigInteger;

/**
 * A class who encode an Integer converted into String in base 36
 * @author <a href="mailto:pbertrand@bluexml.com">Pierre BERTRAND</a>
 * 
 */
public class Encoder {
	/**
	 * Utility classes don't need to (and shouldn't) be instantiated.
	 */
	private Encoder() {
		// prevents instantiation
	}

	// the number base to encore : 36 = 0123456789abcdefghijklmnopqrstuvwxyz
	public static int ENCODE_BASE = 36;

	/**
	 * Encode the String given in base 36
	 * @param s the string to encode
	 * @return s in base 36
	 */
	public static String encode(String s) {
		BigInteger temp = new BigInteger(s);
		return temp.toString(ENCODE_BASE);
	}

	/**
	 * Encode a String (base 36) in base 10
	 * @param s the string to encode
	 * @return s in base 10
	 */
	public static String desencode(String s) {
		BigInteger temp = new BigInteger(s, ENCODE_BASE);
		return temp.toString();
	}
}
