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
