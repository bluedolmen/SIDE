package com.bluexml.side.settings;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.bluexml.Settings";
	public static final String KEY_PREFERENCE = "Key";
	public static final String KEY_DEFAULT = "";

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/** 
	 * Initializes a preference store with default preference values 
	 * for this plug-in.
	 */
	public static void initializeDefaultPreferences() {
		Activator.getDefault().getPreferenceStore().setDefault(KEY_PREFERENCE, KEY_DEFAULT);
	}
	
	public static void setKey(String key){
		Activator.getDefault().getPreferenceStore().setValue(KEY_PREFERENCE, key);
	}
	
	public static String getKey(){
		return Activator.getDefault().getPreferenceStore().getString(Activator.KEY_PREFERENCE);
	}
}
