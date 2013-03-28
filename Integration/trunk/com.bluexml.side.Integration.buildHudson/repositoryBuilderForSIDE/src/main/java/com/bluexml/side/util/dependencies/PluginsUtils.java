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
package com.bluexml.side.util.dependencies;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;

public class PluginsUtils {

	
	/**
	 * search for plugin.xml files
	 * 
	 * @param folder
	 *            where to start search
	 * @return List of plugin.xml files
	 * @throws Exception 
	 */
	public static List<File> getPluginXML(File f) throws Exception {
		List<File> rs = new ArrayList<File>();
		File[] lf = f.listFiles();
		for (File file : lf) {
			if (file.isFile()) {
				if (file.getName().equals("plugin.xml")) {
					rs.add(file);
					break;
				}
			} else if (!file.getName().startsWith(".")) {
				List<File> ll = getPluginXML(file);
				rs.addAll(ll);
			}
		}
		return rs;
	}

	public static Document buildJdomDocument(File xmlFile) throws Exception {
		Document doc;
		org.jdom.input.SAXBuilder builder = new SAXBuilder();
		doc = builder.build(xmlFile);
		return doc;
	}
	
	public static Map<String, Set<String>> add(Map<String, Set<String>> set, String key, String element) {
		if (set.containsKey(key)) {
			set.get(key).add(element);
		} else {
			Set<String> l = new TreeSet<String>();
			l.add(element);
			set.put(key, l);
		}
		return set;
	}
}
