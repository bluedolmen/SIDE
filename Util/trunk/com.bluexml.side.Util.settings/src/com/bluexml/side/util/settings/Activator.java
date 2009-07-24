package com.bluexml.side.util.settings;

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

	public static final String FEEDBACK_PREFERENCE = "DoFeedback";

	public static final int FEEDBACK_PREF_NEVERSETTED = 0;
	public static final int FEEDBACK_PREF_ALWAYS = 1;
	public static final int FEEDBACK_PREF_NOW = 2;
	public static final int FEEDBACK_PREF_NOTNOW = 3;
	public static final int FEEDBACK_PREF_NEVER = 4;

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
}
