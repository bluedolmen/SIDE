package com.bluexml.side.util.feedback;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "com.bluexml.side.util.feedback.messages";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	public static String SideFeedbackPreferencesPage_0;
	public static String SideFeedbackPreferencesPage_1;
	public static String SideFeedbackPreferencesPage_2;
	public static String SideFeedbackPreferencesPage_3;
	public static String SideFeedbackPreferencesPage_4;
	public static String SideFeedbackPreferencesPage_5;
	public static String SideFeedbackPreferencesPage_6;
	public static String SideFeedbackPreferencesPage_7;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
