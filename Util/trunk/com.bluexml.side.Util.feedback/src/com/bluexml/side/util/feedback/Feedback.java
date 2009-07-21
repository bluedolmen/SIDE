package com.bluexml.side.util.feedback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Feedback {
	protected Date date;
	protected List<FeedbackItem> feedbacks = new ArrayList<FeedbackItem>();

	public void addItem(String id, String metaModel, String version) {
		FeedbackItem feedbackItem = new FeedbackItem(id, metaModel, version);
		feedbacks.add(feedbackItem);
	}
}
