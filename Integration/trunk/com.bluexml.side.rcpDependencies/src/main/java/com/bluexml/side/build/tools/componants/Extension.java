/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
