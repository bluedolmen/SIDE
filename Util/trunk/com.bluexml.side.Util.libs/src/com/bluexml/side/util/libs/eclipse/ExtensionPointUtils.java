package com.bluexml.side.util.libs.eclipse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class ExtensionPointUtils {

	/**
	 * search in extension fragment that match with given name and a set of
	 * attributes
	 * 
	 * @param parent
	 * @param nodeName
	 * @param parametersMatchs
	 * @return
	 */
	public static List<IConfigurationElement> getIConfigurationElementBy(IConfigurationElement parent, String nodeName, Map<String, String> parametersMatchs) {
		List<IConfigurationElement> l = getIConfigurationElementsByName(parent, nodeName);
		ArrayList<IConfigurationElement> result = new ArrayList<IConfigurationElement>();
		for (IConfigurationElement configurationElement : l) {
			if (parametersMatchs(configurationElement, parametersMatchs)) {
				result.add(configurationElement);
			}
		}
		return result;
	}

	/**
	 * return a list of extension fragment that match with the given name
	 * 
	 * @param parent
	 * @param name
	 * @return
	 */
	public static List<IConfigurationElement> getIConfigurationElementsByName(IConfigurationElement parent, String name) {
		ArrayList<IConfigurationElement> l = new ArrayList<IConfigurationElement>();
		if (parent.getName().equals(name)) {
			l.add(parent);
		}
		for (IConfigurationElement configurationElement : parent.getChildren()) {
			List<IConfigurationElement> ll = getIConfigurationElementsByName(configurationElement, name);
			l.addAll(ll);
		}
		return l;
	}

	/**
	 * test if the given extension fragment match with all attributes values
	 * 
	 * @param node
	 * @param parametersMatchs
	 * @return
	 */
	public static boolean parametersMatchs(IConfigurationElement node, Map<String, String> parametersMatchs) {
		Set<String> g = new HashSet<String>();
		String[] attrs = node.getAttributeNames();
		for (String string : attrs) {
			g.add(string);
		}
		boolean okSubSet = g.containsAll(parametersMatchs.keySet());
		if (!okSubSet) {
			return false;
		}
		for (Map.Entry<String, String> match : parametersMatchs.entrySet()) {
			if (!node.getAttribute(match.getKey()).equals(match.getValue())) {
				return false;
			}
		}
		return true;
	}

	public static Class<?> getGeneratorInstance(String bundle, String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Bundle plugin = Platform.getBundle(bundle);
		Class<?> gen = null;
		if (plugin != null) {
			gen = plugin.loadClass(className);

		}
		return gen;
	}
}
