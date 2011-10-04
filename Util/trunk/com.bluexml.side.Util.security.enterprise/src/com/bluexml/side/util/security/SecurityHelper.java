package com.bluexml.side.util.security;


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
	private static Boolean checkValidity(KeyInformation ki, String code){
		boolean validity = ki.getValidity();
		Boolean hascode = ki.hasCode(code);
		if (hascode == null) {
			// code not managed by license
			return null;
		}
		return (hascode && validity);
	}
	
	/**
	 * return true if the generator can be lanched
	 * @param generatorCode
	 * @return true if the key is valid and has the code of the generator
	 */
	public static Boolean check(String generatorCode,String Key) {
		//D�coder la clef
		KeyInformation ki = new KeyInformation(Key); 
		//Retourner le test sur la clef
		Boolean checkValidity = checkValidity(ki,generatorCode);
		if (checkValidity == null) {
			// unable to check, code do not exist
			return true;
		}
		return checkValidity;
		
	}
}
