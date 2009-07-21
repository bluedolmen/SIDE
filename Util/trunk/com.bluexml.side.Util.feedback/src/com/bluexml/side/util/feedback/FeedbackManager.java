package com.bluexml.side.util.feedback;

import org.eclipse.core.runtime.IPath;

public class FeedbackManager {

	Feedback feedback = new Feedback();

	public void addFeedBackItem(String id, String metaModel, String version) {
		feedback.addItem(id, metaModel, version);
	}

	public void save() {
		IPath pluginSaveFolder = Activator.getDefault().getStateLocation();
	}
}
