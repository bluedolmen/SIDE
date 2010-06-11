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
