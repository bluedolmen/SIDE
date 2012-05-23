package com.bluexml.side.build.tools.componants;

import java.util.ArrayList;
import java.util.List;

public class Plugin extends Componant {

	String id;
	String version;
	String name;
	List<Plugin> dependecies = new ArrayList<Plugin>();

	List<LinkedWithModule> extensions = new ArrayList<LinkedWithModule>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Plugin> getDependecies() {
		return dependecies;
	}

	public void setDependecies(List<Plugin> dependecies) {
		this.dependecies = dependecies;
	}

	public List<LinkedWithModule> getExtensions() {
		return extensions;
	}

	public void setExtensions(List<LinkedWithModule> extensions) {
		this.extensions = extensions;
	}

	public String toString() {
		return id + "(" + version + ")";
	}

}
