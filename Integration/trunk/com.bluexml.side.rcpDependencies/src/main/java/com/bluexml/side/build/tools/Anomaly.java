package com.bluexml.side.build.tools;

import java.util.ArrayList;
import java.util.List;

public class Anomaly {

	List<String> notTree = new ArrayList<String>();

	List<String> invalideCheckRef = new ArrayList<String>();

	public void addNotTree(String notTree) {
		this.notTree.add(notTree);
	}

	public void addInvalideCheckRef(String invalideCheckRef) {
		this.invalideCheckRef.add(invalideCheckRef);
	}

}
