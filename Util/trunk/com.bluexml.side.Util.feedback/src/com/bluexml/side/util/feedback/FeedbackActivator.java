package com.bluexml.side.util.feedback;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;



/**
 * The activator class controls the plug-in life cycle
 */
public class FeedbackActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.bluexml.side.Util.feedback";

	// URL to send file
	public static String SERVICE_URL = "http://www.bluexml.com/static/test/upload.php";
	// URL for image stats to show
	public static String STATS_URL = "http://www.statssheet.com/images/web_counter_stats.jpg";
	// URL for link on image :
	public static String STATS_LINK_URL = "http://www.side-labs.org";

	// Zipe file name
	public static String ZIP_FILE_NAME = "sideLog.zip";

	// Attribute name for the logfile (used by SERVICE_URL with POST method)
	public static final String LOGFILE_ATTRIBUTE_NAME = "log";

	public static final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;

	// Key
	public static final String FEEDBACK_PREFERENCE = "DoFeedback";
	public static final String FEEDBACK_PERIOD_PREFERENCE = "feedbackPeriod";
	public static final String LAST_UPDATE_DATE = "lastUpdateDate";
	public static final String TERM_OF_USE = "termOfUse";

	// Default Value
	public static final int FEEDBACK_DEFAULT_PERIOD_IN_DAY = 7;
	public static final int FEEDBACK_PREF_NEVERSETTED = 0;

	// Possible value Value
	public static final int FEEDBACK_PREF_ALWAYS = 1;
	public static final int FEEDBACK_PREF_NOW = 2;
	public static final int FEEDBACK_PREF_NOTNOW = 3;
	public static final int FEEDBACK_PREF_NEVER = 4;

	// The shared instance
	private static FeedbackActivator plugin;

	/**
	 * The constructor
	 */
	public FeedbackActivator() {
		//FeedbackActivator.getDefault().getPreferenceStore().setDefault(FeedbackActivator.FEEDBACK_PERIOD_PREFERENCE, FEEDBACK_DEFAULT_PERIOD_IN_DAY);
		//FeedbackActivator.getDefault().getPreferenceStore().setDefault(FeedbackActivator.FEEDBACK_PREFERENCE, FEEDBACK_PREF_NEVERSETTED);
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
	public static FeedbackActivator getDefault() {
		return plugin;
	}

	public static void setFeedBackPreference(int feedBackPreference){
		FeedbackActivator.getDefault().getPreferenceStore().setValue(FeedbackActivator.FEEDBACK_PREFERENCE, feedBackPreference);
	}

	public static int getFeedBackPreference(){
		return FeedbackActivator.getDefault().getPreferenceStore().getInt(FeedbackActivator.FEEDBACK_PREFERENCE);
	}

	public static int getDefaultFeedbackUploadPeriod() {
		return FeedbackActivator.FEEDBACK_DEFAULT_PERIOD_IN_DAY;
	}


	public static int getFeedbackUploadPeriodPreference() {
		return FeedbackActivator.getDefault().getPreferenceStore().getInt(FeedbackActivator.FEEDBACK_PERIOD_PREFERENCE);
	}

	public static void setFeedbackUploadPeriod(int period) {
		FeedbackActivator.getDefault().getPreferenceStore().setValue(FeedbackActivator.FEEDBACK_PERIOD_PREFERENCE, period);
	}

	public static boolean doFeedback() {
		return getFeedBackPreference() != FEEDBACK_PREF_NEVER;
	}

	public static boolean getFeedbackTermOfUseAccepted() {
		return FeedbackActivator.getDefault().getPreferenceStore().getBoolean(FeedbackActivator.TERM_OF_USE);
	}

	public static void setFeedbackTermOfUseAccepted(boolean accepted) {
		FeedbackActivator.getDefault().getPreferenceStore().setValue(FeedbackActivator.TERM_OF_USE, accepted);
	}
}
