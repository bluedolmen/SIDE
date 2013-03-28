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
