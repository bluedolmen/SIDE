package com.bluexml.side.util.feedback.utils;

import java.net.URL;
import java.util.Date;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.PlatformUI;

import com.bluexml.side.util.feedback.FeedbackActivator;

public class FeedbackUtils {

	public static String END_FILE_NAME = "-log.xml";

	public static IPath getFeedbackSaveFolder() {
		return FeedbackActivator.getDefault().getStateLocation();
	}

	public static void initPreferences() {
		// First date :
		Date nowDate = new Date();
		FeedbackActivator.getDefault().getPreferenceStore().setValue(FeedbackActivator.LAST_UPDATE_DATE, nowDate.getTime());
		// Set the status
		// TODO :
		FeedbackActivator.getDefault().getPreferenceStore().setValue(FeedbackActivator.FEEDBACK_PREFERENCE, FeedbackActivator.FEEDBACK_PREF_NEVERSETTED);
		// Set the period :
	}

	/**
	 * Open the OS browser to the given URL
	 * @param url
	 */
	public static void browseTo(String url) {
		try {
			PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(new URL(url));
		} catch (Exception e) {
			FeedbackActivator.getDefault().getLog().log(new Status(Status.ERROR, FeedbackActivator.PLUGIN_ID, "Error opening browser", e)); //$NON-NLS-1$
		}
	}
}
