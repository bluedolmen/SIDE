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
			if (nomMachine != null)
				nomMachine = nomMachine.split("/")[1];
		}
		if (nomMachine == null)
			nomMachine = System.getProperty("user.name");
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
