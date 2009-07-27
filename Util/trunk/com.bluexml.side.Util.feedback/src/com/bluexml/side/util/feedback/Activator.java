package com.bluexml.side.util.feedback;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;



/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.bluexml.side.Util.feedback";

	// Variable used in plugin :

	public static final String LAST_UPDATE_DATE = "lastUpdateDate";

	// URL to send file
	public static String SERVICE_URL = "http://www.bluexml.com/ws/upload";

	// Attribute name for the logfile (used by SERVICE_URL with POST method)
	public static final String LOGFILE_ATTRIBUTE_NAME = "log";

	// URL for stats to show in popup.
	public static String STATS_URL = "http://www.bluexml.com";

	// Zipe file name
	public static String ZIP_FILE_NAME = "sideLog.zip";

	public static final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;

	// Period between ech send (in day)
	public static final int TIME_BETWEEN_SEND = 0;

	public static final String FEEDBACK_PREFERENCE = "DoFeedback";

	public static final String FEEDBACK_PERIOD_PREFERENCE = "feedbackPeriod";

	public static final int FEEDBACK_DEFAULT_PERIOD_IN_DAY = 5;

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

	public static void setFeedBackPreference(int feedBackPreference){
		Activator.getDefault().getPreferenceStore().setValue(Activator.FEEDBACK_PREFERENCE, feedBackPreference);
	}

	public static int getFeedBackPreference(){
		return Activator.getDefault().getPreferenceStore().getInt(Activator.FEEDBACK_PREFERENCE);
	}

	public static int getDefaultFeedbackUploadPeriod() {
		return Activator.FEEDBACK_DEFAULT_PERIOD_IN_DAY;
	}


	public static int getFeedbackUploadPeriodPreference() {
		return Activator.getDefault().getPreferenceStore().getInt(Activator.FEEDBACK_PERIOD_PREFERENCE);
	}
}
