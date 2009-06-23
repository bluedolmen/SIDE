package com.bluexml.side.application.security;


/**
 * 
 * @author Pierre BERTRAND
 * @author Benjamin CHEVALLEREAU
 */
public class SecurityHelper {
	
	/**
	 * Utility classes don't need to (and shouldn't) be instantiated.
	 */
	private SecurityHelper() {
		// prevents instantiation
	}

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
	public static boolean check(String generatorCode,String Key) {
		//Décoder la clef
		KeyInformation ki = new KeyInformation(Key); 
		//Retourner le test sur la clef
		return checkValidity(ki,generatorCode);
	}
}
