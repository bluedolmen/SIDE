package com.bluexml.side.util.feedback.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FeedbackItem {
	protected String id;
	protected String metaModel;
	protected String technoVersion;
	protected List<Option> options = new ArrayList<Option>();

	public FeedbackItem(String id, String metaModel, String technoVersion, Map<String, Boolean> p_options) {
		this.id = id;
		this.metaModel = metaModel;
		this.technoVersion = technoVersion;
		for (String key : p_options.keySet()) {
			options.add(new Option(key, p_options.get(key)));
		}
	}


}
