package com.bluexml.side.util.libs;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.bluexml.side.util.libs.md5.MD5Hasher;

public class SystemInfoGetter {
	
	private SystemInfoGetter(){}
	
	public static String getHostName(){
		String nomMachine;
		String system = System.getProperty("os.name").split(" ")[0];
		if (system.equals("Windows")){
			
			nomMachine =System.getenv("COMPUTERNAME");
		}
		else{
			nomMachine = System.getenv("HOST");
			if (!nomMachine.equals(null))
				nomMachine = nomMachine.split("/")[1];
		}
		if (nomMachine.equals(null))
			System.getProperty("user.name");
		return nomMachine;
	}
	
	/**
	 * Parse the 5 first letters of the host name and hash it with md5
	 * @return the host name hashed in md5
	 */
	public static String getHostWithHash(){
		String result  = "";
		try {
			result=getHostName();
			if (result.length()>=5)
				result.substring(0, 5);
			result =MD5Hasher.hash(result);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

}
