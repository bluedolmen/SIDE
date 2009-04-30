package com.bluexml.side.settings;

public class SidePreferences {

	public static void setKey(String key){
		Activator.getDefault().getPreferenceStore().setValue(Activator.KEY_PREFERENCE, key);
	}

	public static String getKey(){
		return Activator.getDefault().getPreferenceStore().getString(Activator.KEY_PREFERENCE);
	}
}
