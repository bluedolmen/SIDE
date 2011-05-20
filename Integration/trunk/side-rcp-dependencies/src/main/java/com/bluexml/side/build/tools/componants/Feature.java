package com.bluexml.side.build.tools.componants;

import java.util.ArrayList;
import java.util.List;

public class Feature extends Componant {

	String id;
	String name;
	String version;
	List<Feature> includedFeatures = new ArrayList<Feature>();
	List<Plugin> plugins = new ArrayList<Plugin>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<Feature> getIncludedFeatures() {
		return includedFeatures;
	}

	public void setIncludedFeatures(List<Feature> includedFeatures) {
		this.includedFeatures = includedFeatures;
	}

	public List<Plugin> getPlugins() {
		return plugins;
	}

	public void setPlugins(List<Plugin> plugins) {
		this.plugins = plugins;
	}

	
	public String toString() {
		return id + "(" + version + ")";
	}
}
