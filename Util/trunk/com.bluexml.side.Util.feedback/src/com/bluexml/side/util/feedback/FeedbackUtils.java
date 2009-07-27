package com.bluexml.side.util.feedback;

import java.util.Date;

import org.eclipse.core.runtime.IPath;

public class FeedbackUtils {

	public static String END_FILE_NAME = "-log.xml";

	public static IPath getFeedbackSaveFolder() {
		return Activator.getDefault().getStateLocation();
	}

	public static void initPreferences() {
		// First date :
		Date nowDate = new Date();
		Activator.getDefault().getPreferenceStore().setValue(Activator.LAST_UPDATE_DATE, nowDate.getTime());
		// Set the status
		// TODO :
		//Activator.getDefault().getPreferenceStore().setValue(com.bluexml.side.util.settings.Activator.FEEDBACK_PREFERENCE, com.bluexml.side.util.settings.Activator.FEEDBACK_PREF_NEVERSETTED);
		// Set the period :
	}
}
