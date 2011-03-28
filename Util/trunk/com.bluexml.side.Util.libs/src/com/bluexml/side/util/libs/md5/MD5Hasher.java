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
