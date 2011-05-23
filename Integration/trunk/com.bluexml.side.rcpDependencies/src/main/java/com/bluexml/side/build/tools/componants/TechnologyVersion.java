package com.bluexml.side.build.tools.componants;

public class TechnologyVersion extends Componant {
	String version;
	String id;
	String description;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return id + "(" + version + ")";
	}
}
