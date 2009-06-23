package com.bluexml.side.util.settings;

public class SidePreferences {
	/**
	 * Utility classes don't need to (and shouldn't) be instantiated.
	 */
	private SidePreferences() {
		// prevents instantiation
	}

	public static void setKey(String key){
		Activator.getDefault().getPreferenceStore().setValue(Activator.KEY_PREFERENCE, key);
	}

	public static String getKey(){
		return Activator.getDefault().getPreferenceStore().getString(Activator.KEY_PREFERENCE);
	}

	public static void setDefaultKey() {
		Activator.getDefault().getPreferenceStore().setValue(Activator.KEY_PREFERENCE, Activator.KEY_DEFAULT);
	}
}
