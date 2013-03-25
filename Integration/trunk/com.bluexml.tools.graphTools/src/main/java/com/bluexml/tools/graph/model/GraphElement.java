package com.bluexml.tools.graph.model;

import java.util.UUID;

public class GraphElement extends Component {

	String uid = UUID.randomUUID().toString();

	String label;

	public GraphElement(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return label + " (" + uid + ")";
	}

	public String getUid() {
		return uid;
	}
	
}
