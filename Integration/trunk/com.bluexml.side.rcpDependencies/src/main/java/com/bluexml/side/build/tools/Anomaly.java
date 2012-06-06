package com.bluexml.side.build.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Anomaly {

	private List<String> notTree = new ArrayList<String>();
	
	private List<String> missingPluginsInFeatures = new ArrayList<String>();

	private List<String> invalideCheckRef = new ArrayList<String>();
	
	private List<String[]> bundleNotFoundInConf = new ArrayList<String[]>();
	
	private List<String> invalideEntryInConf = new ArrayList<String>();
	
	private List<String> moduleNotFound = new ArrayList<String>();
	
	
	private List<String> featureNoDescription = new ArrayList<String>();
	
	private List<String> featureNoCopyright = new ArrayList<String>();
	
	private List<String> featureNoLicence = new ArrayList<String>();
	
	
	private List<String> featureBadProvider = new ArrayList<String>();
	
	
	

	public List<String> getFeatureNoDescription() {
		return Collections.unmodifiableList(featureNoDescription);
	}

	public void addFeatureNoDescription(String featureNoDescription) {
		this.featureNoDescription.add(featureNoDescription);
	}

	public List<String> getFeatureNoCopyright() {
		return Collections.unmodifiableList(featureNoCopyright);
	}

	public void addFeatureNoCopyright(String featureNoCopyright) {
		this.featureNoCopyright.add(featureNoCopyright);
	}

	public List<String> getFeatureNoLicence() {
		return Collections.unmodifiableList(featureNoLicence);
	}

	public void addFeatureNoLicence(String featureNoLicence) {
		this.featureNoLicence.add(featureNoLicence);
	}

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

	public List<String> getNotTree() {
		return Collections.unmodifiableList(notTree);
	}

	public List<String> getMissingPluginsInFeatures() {
		return Collections.unmodifiableList(missingPluginsInFeatures);
	}

	public List<String> getInvalideCheckRef() {
		return Collections.unmodifiableList(invalideCheckRef);
	}

	public List<String[]> getBundleNotFoundInConf() {
		return Collections.unmodifiableList(bundleNotFoundInConf);
	}

	public List<String> getInvalideEntryInConf() {
		return Collections.unmodifiableList(invalideEntryInConf);
	}

	public List<String> getModuleNotFound() {
		return Collections.unmodifiableList(moduleNotFound);
	}

	public List<String> getFeatureBadProvider() {
		return Collections.unmodifiableList(featureBadProvider);
	}

	public void addFeatureBadProvider(String featureBadProvider) {
		this.featureBadProvider.add(featureBadProvider);
	}
	
}
