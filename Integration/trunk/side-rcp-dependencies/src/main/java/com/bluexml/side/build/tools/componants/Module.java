package com.bluexml.side.build.tools.componants;

import java.util.ArrayList;
import java.util.List;

public class Module extends Componant{

	String moduleID;
	String groupId;
	String artifactId;
	String version;
	String type;
	
	List<Module> dependencies = new ArrayList<Module>();

	public String getModuleID() {
		return moduleID;
	}

	public void setModuleID(String moduleID) {
		this.moduleID = moduleID;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<Module> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<Module> dependencies) {
		this.dependencies = dependencies;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String toString() {
		return moduleID + "(" + version + ")";
	}
	
}
