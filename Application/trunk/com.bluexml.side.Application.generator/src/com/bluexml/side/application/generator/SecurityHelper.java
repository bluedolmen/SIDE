package com.bluexml.side.application.generator;

import com.bluexml.side.settings.Activator;
import com.bluexml.side.application.security.KeyInformation;

public class SecurityHelper {

	private static String getKey(){
		return Activator.getDefault().getPreferenceStore().getString(Activator.KEY_PREFERENCE);
		//return "2q8575bwohia4rrdauo1us3e26f0g2f";
	}
	
	private static boolean checkValidity(KeyInformation ki, String code){
		boolean validity = ki.getValidity();
		boolean hascode = ki.hasCode(code);
		return (hascode && validity);
	}
	
	public static boolean check(String generatorCode) {
		//Récupérer la clef
		String key = getKey();
		//Décoder la clef
		KeyInformation ki = new KeyInformation(key); 
		//Retourner le test sur la clef
		return checkValidity(ki,generatorCode);
	}
}
