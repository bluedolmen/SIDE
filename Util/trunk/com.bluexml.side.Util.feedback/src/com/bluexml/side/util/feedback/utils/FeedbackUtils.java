package com.bluexml.side.util.feedback.utils;

import java.util.Date;

import org.eclipse.core.runtime.IPath;

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
}
