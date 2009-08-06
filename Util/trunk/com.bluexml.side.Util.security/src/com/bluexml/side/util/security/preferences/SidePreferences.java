package com.bluexml.side.util.security.preferences;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
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
		IEclipsePreferences root = Platform.getPreferencesService().getRootNode();
		root.node(ConfigurationScope.SCOPE).node(qualifier).put(keyKey, value);
	}

	public static String getKey(){
		Preferences node = Platform.getPreferencesService().getRootNode().node(ConfigurationScope.SCOPE).node(qualifier);
		return node.get(keyKey, Activator.KEY_DEFAULT);
	}

	public static void setDefaultKey() {
		IEclipsePreferences root = Platform.getPreferencesService().getRootNode();
		root.node(ConfigurationScope.SCOPE).node(qualifier).put(keyKey, Activator.KEY_DEFAULT);
	}


}
