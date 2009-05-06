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
		}
		return nomMachine;
	}
	
	public static String getHostWithHash(){
		try {
			return MD5Hasher.hash(getHostName());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

}
