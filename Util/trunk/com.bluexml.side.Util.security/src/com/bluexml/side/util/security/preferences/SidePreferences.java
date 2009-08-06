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
		//IEclipsePreferences root = Platform.getPreferencesService().getRootNode();
		//root.node(Location.CONFIGURATION_FILTER).node(qualifier).put(keyKey, value);
		Preferences preferences = new ConfigurationScope().getNode(qualifier);
		preferences.put(keyKey, value);
		try {
			preferences.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		//Platform.getConfigurationLocation().CONFIGURATION_FILTER
		//ConfigurationScope.SCOPE
	}

	public static String getKey(){
		//IEclipsePreferences root = Platform.getPreferencesService().getRootNode();
		//return root.node(Location.CONFIGURATION_FILTER).node(qualifier).get(keyKey, Activator.KEY_DEFAULT);
		Preferences preferences = new ConfigurationScope().getNode(qualifier);
		return preferences.get(keyKey, Activator.KEY_DEFAULT);
	}

	public static void setDefaultKey() {
		//IEclipsePreferences root = Platform.getPreferencesService().getRootNode();
		//root.node(ConfigurationScope.SCOPE).node(qualifier).put(keyKey, Activator.KEY_DEFAULT);
	}


}
