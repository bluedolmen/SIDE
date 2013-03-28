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
package com.bluexml.side.build.tools.reader;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;

import com.bluexml.side.build.tools.componants.Componant;

public class Utils {
	static Logger logger = Logger.getLogger(Utils.class);
	static HashMap<String, String> componants;

	public static Document buildJdomDocument(File xmlFile) throws Exception {
		logger.debug("Load XML document :" + xmlFile);
		Document doc;
		org.jdom.input.SAXBuilder builder = new SAXBuilder();
		doc = builder.build(xmlFile);
		return doc;
	}

	public static File find(String name, File root) {

		File[] children = root.listFiles();

		for (File file2 : children) {
			if (file2.isDirectory()) {
				if (file2.getName().equals(name)) {
					logger.debug("Project " + name + " found in " + root);
					return file2;
				}
			}
		}
		// not found so search in subFolder
		for (File file2 : children) {
			if (file2.isDirectory()) {
				File rt = find(name, file2);
				if (rt != null) {
					return rt;
				}
			}
		}
		return null;
	}

	public static Map<Componant, Set<Componant>> add(Map<Componant, Set<Componant>> set, Componant key, Componant element) {
		if (set.containsKey(key)) {
			set.get(key).add(element);
		} else {
			Set<Componant> l = new TreeSet<Componant>();
			l.add(element);
			set.put(key, l);
		}
		return set;
	}

	public static HashMap<String, String> initMap(File confFile) throws Exception {
		if (componants != null) {
			return componants;
		}
		HashMap<String, String> componants = new HashMap<String, String>();
		Properties p = new Properties();
		FileInputStream fileInputStream = new FileInputStream(confFile);
		p.load(fileInputStream);

		String communityProjects = p.getProperty("project");
		String[] cc = communityProjects.replace("\n", "").split(",");

		for (String string : cc) {
			fillMap(componants, string);
		}
		String enterpriseProjects = p.getProperty("project.enterprise", "");
		if (!enterpriseProjects.equals("")) {
			String[] ce = enterpriseProjects.replace("\n", "").split(",");
			for (String string : ce) {
				fillMap(componants, string);
			}
		}
		fileInputStream.close();
		return componants;
	}

	public static File searchProjectForlderFromConf(String id, List<File> repos, File props) throws Exception {
		File project = null;
		if (props != null && props.exists()) {
			HashMap<String, String> componants = initMap(props);
			String path = componants.get(id);

			if (path == null) {
				path = "FrameworksModules";
			}
			for (File file : repos) {
				File project_ = new File(file, (path + "/trunk/" + id).replace("/", File.separator));
				logger.debug("Project Path :" + project_);
				if (project_.exists()) {
					project = project_;
					break;
				} else {
					logger.debug(project_ + " not found ...");
				}
			}
		}

		return project;
	}

	private static void fillMap(HashMap<String, String> componants, String string) {

		String[] ccc = string.split("&");
		String path = ccc[0];
		String id = ccc[1];
		componants.put(id, path);
	}
}
