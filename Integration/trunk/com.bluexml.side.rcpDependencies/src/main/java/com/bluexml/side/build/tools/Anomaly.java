package com.bluexml.side.build.tools;

import java.util.ArrayList;
import java.util.List;

public class Anomaly {

	List<String> notTree = new ArrayList<String>();
	
	List<String> missingPluginsInFeatures = new ArrayList<String>();

	List<String> invalideCheckRef = new ArrayList<String>();
	
	List<String[]> bundleNotFoundInConf = new ArrayList<String[]>();
	
	List<String> invalideEntryInConf = new ArrayList<String>();
	
	List<String> moduleNotFound = new ArrayList<String>();

	public void addNotTree(String notTree) {
		this.notTree.add(notTree);
	}

	public void addInvalideCheckRef(String invalideCheckRef) {
		this.invalideCheckRef.add(invalideCheckRef);
	}

	public void addBundleNotFoundInConf(String[] strings) {
		this.bundleNotFoundInConf.add(strings);
	}
	
	public void addInvalideEntryInConf(String notTree) {
		this.invalideEntryInConf.add(notTree);
	}
	
	public void addModuleNotFound(String moduleId) {
		this.moduleNotFound.add(moduleId);
	}
	
	public void addMissingPluginsInFeatures(String notTree) {
		this.missingPluginsInFeatures.add(notTree);
	}
	
}
