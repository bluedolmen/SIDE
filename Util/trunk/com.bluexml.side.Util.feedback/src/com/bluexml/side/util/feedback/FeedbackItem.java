package com.bluexml.side.util.feedback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedbackItem {
	protected String id;
	protected String metaModel;
	protected String version;
	protected Date date;
	protected List<Option> options = new ArrayList<Option>();

	public FeedbackItem(String id, String metaModel, String version) {
		this.id = id;
		this.metaModel = metaModel;
		this.version = version;
	}


}
