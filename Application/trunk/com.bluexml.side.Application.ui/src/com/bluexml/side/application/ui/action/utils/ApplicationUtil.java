package com.bluexml.side.application.ui.action.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bluexml.side.application.Application;
import com.bluexml.side.application.ComponantConfiguration;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.GeneratorConfiguration;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.ModelElement;
import com.bluexml.side.application.ui.action.ApplicationDialog;
import com.bluexml.side.application.ui.action.tree.Generator;
import com.bluexml.side.application.ui.action.tree.ImplNode;

public class ApplicationUtil {
	/**
	 * Return the configuration corresponding to the given key in the current
	 * configuration. Return null if not found.
	 * 
	 * @param key
	 * @return
	 */
	public static ConfigurationParameters getConfigurationParmeterByKey(String key) {
		ConfigurationParameters result = null;
		Configuration config = ApplicationDialog.getCurrentConfiguration();
		int i = 0;
		int size = config.getParameters().size();
		while (i < size && result == null) {
			ConfigurationParameters param = config.getParameters().get(i);
			if (param.getKey().equals(key)) {
				result = param;
			}
			i++;
		}
		return result;
	}

	/**
	 * Return models of the application
	 * 
	 * @param application
	 * @return
	 */
	public static List<Model> getModels(Application application) {
		List<Model> result = new ArrayList<Model>();
		for (ModelElement elem : application.getElements()) {
			if (elem instanceof Model) {
				result.add((Model) elem);
			}
		}
		return result;
	}

	/**
	 * Delete the given generator from the given configuration
	 * @param config
	 */
	public static void deleteImplNodeFromConf(Configuration config, ImplNode gen) {
		Set<ComponantConfiguration> elts = new HashSet<ComponantConfiguration>();
		for (GeneratorConfiguration elt : config.getGeneratorConfigurations()) {
			if (elt.getId().equals(gen.getId()) 
					&& elt.getId_techno_version().equals(gen.getParent().getId()) 
					&& elt.getId_metamodel().equals(gen.getParent().getParent().getParent().getId()) ) {
				elts.add(elt);
			}
		}
		config.getGeneratorConfigurations().removeAll(elts);
	}
	/**
	 * Return the list of componant configuration for a specific config
	 * @param config
	 * @return
	 */
	public static List<ComponantConfiguration> getComponantConfigurations(Configuration config) {
		List<ComponantConfiguration> l = new ArrayList<ComponantConfiguration>();
		l.addAll(config.getDeployerConfigurations());
		l.addAll(config.getGeneratorConfigurations());
		return l;
	}

	public static ComponantConfiguration getComponantConfiguration(Configuration config, String componantId) {
		List<ComponantConfiguration> l = getComponantConfigurations(config);
		for (ComponantConfiguration c : l) {
			if (c.getId().equals(componantId)) {
				return c;
			}
		}
		return null;
	}

	public static boolean ComponantConfigurationsContains(Configuration config, String componantId) {
		if (getComponantConfiguration(config, componantId) != null) {
			return true;
		}
		return false;
	}
}
