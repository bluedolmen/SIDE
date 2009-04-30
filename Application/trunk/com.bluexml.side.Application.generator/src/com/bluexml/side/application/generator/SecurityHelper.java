package com.bluexml.side.application.generator;

import com.bluexml.side.application.security.KeyInformation;
import com.bluexml.side.settings.SidePreferences;

/**
 * 
 * @author Pierre BERTRAND
 * @author Benjamin CHEVALLEREAU
 */
public class SecurityHelper {

	/**
	 *  return true if the key of the KeyInformation is valid an possess the code
	 * @param ki
	 * @param code
	 * @return true if valid
	 */
	private static boolean checkValidity(KeyInformation ki, String code){
		boolean validity = ki.getValidity();
		boolean hascode = ki.hasCode(code);
		return (hascode && validity);
	}
	
	/**
	 * return true if the generator can be lanched
	 * @param generatorCode
	 * @return true if the key is valid and has the code of the generator
	 */
	public static boolean check(String generatorCode) {
		//Décoder la clef
		KeyInformation ki = new KeyInformation(SidePreferences.getKey()); 
		//Retourner le test sur la clef
		return checkValidity(ki,generatorCode);
	}
}
