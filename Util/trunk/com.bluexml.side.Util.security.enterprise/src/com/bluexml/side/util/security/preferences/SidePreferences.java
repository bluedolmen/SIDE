package com.bluexml.side.util.security.preferences;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import com.bluexml.side.util.security.Activator;

public class SidePreferences {
	static String qualifier = Activator.PLUGIN_ID;
	static String keyKey = Activator.KEY_PREFERENCE;
	
	/**
	 * Utility classes don't need to (and shouldn't) be instantiated.
	 */
	private SidePreferences() {
		// prevents instantiation
	}

	public static void setKey(String value){
		Preferences preferences = new ConfigurationScope().getNode(qualifier);
		preferences.put(keyKey, value);
		try {
			preferences.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	public static String getKey(){
		Preferences preferences = new ConfigurationScope().getNode(qualifier);
		return preferences.get(keyKey, Activator.KEY_DEFAULT);
	}

	public static void setDefaultKey() {
		Preferences preferences = new ConfigurationScope().getNode(qualifier);
		preferences.put(keyKey, Activator.KEY_DEFAULT);
		try {
			preferences.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}


}
