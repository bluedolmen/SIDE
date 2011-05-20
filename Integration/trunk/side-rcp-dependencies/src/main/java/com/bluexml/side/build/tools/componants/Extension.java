package com.bluexml.side.build.tools.componants;

import java.util.ArrayList;
import java.util.List;

public abstract class Extension extends Configuration {

	Technology techno;
	TechnologyVersion techno_version;
	
	List<Option> options= new ArrayList<Option>();
	
	
	String id;
	String version;
	String classId;
	String hidden;
	String description;
	

	public Technology getTechno() {
		return techno;
	}
	public void setTechno(Technology techno) {
		this.techno = techno;
	}
	public TechnologyVersion getTechno_version() {
		return techno_version;
	}
	public void setTechno_version(TechnologyVersion technoVersion) {
		techno_version = technoVersion;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getHidden() {
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String toString() {
		return id + "(" + version + ")";
	}
	
}
