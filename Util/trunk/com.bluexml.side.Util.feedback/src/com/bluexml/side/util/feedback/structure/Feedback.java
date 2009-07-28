package com.bluexml.side.util.feedback.structure;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Feedback {
	protected Date date;
	protected List<FeedbackItem> feedbacks = new ArrayList<FeedbackItem>();

	public void addItem(String id, String metaModel, String version, Map<String, Boolean> options) {
		FeedbackItem feedbackItem = new FeedbackItem(id, metaModel, version, options);
		date = new Date();
		feedbacks.add(feedbackItem);
	}
}
