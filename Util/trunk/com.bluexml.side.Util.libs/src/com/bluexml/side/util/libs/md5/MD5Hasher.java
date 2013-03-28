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
package com.bluexml.side.util.libs.md5;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hasher {
	
	private MD5Hasher(){}
	
	public static String hash(String strToHash) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest m = MessageDigest.getInstance("MD5");
	    m.update(strToHash.getBytes("UTF-8"));
	    byte s[] = m.digest();
	    String result = "";
	    for (int i = 0; i < s.length; i++) {
	      result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
	    }
	    BigInteger temp = new BigInteger(result, 16);
		return temp.toString(36);
	}
}
