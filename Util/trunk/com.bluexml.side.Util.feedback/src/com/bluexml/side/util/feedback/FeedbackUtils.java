package com.bluexml.side.util.feedback;

import org.eclipse.core.runtime.IPath;

public class FeedbackUtils {

	public static String END_FILE_NAME = "-log.xml";

	public static IPath getFeedbackSaveFolder() {
		return Activator.getDefault().getStateLocation();
	}
}
