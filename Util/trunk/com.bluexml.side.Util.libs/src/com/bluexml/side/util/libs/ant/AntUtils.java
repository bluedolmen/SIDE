package com.bluexml.side.util.libs.ant;

import java.util.Map;
import java.util.Properties;

import org.apache.tools.ant.Project;

public class AntUtils {
	public static void setProperties(Project ant, Properties p) {
		for (Map.Entry<Object, Object> it : p.entrySet()) {
			ant.setProperty(it.getKey().toString(), it.getValue().toString());
		}
	}

	public static void setProperties(Project ant, Map<String, String> properties) {
		for (Map.Entry<String, String> item : properties.entrySet()) {
			ant.setProperty(item.getKey(), item.getValue());
		}		
	}
	
}
