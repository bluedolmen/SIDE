package com.bluexml.side.build.tools.reader;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.bluexml.side.build.tools.componants.Feature;
import com.bluexml.side.build.tools.componants.Plugin;

public class FeatureReader {
	Logger logger = Logger.getLogger(this.getClass());
	ComponantsRegisters util;
	boolean addAll = false;

	public FeatureReader(ComponantsRegisters util) {
		this.util = util;
	}

	public Feature read(File project) throws Exception {
		logger.debug("Read Feature :" + project);
		// get properties

		File featureXMLFile = new File(project, "feature.xml");

		Feature feature = new Feature();

		Document doc = new SAXBuilder().build(featureXMLFile);
		Element root = doc.getRootElement();

		String id = root.getAttributeValue("id");
		feature.setId(id);

		String version = root.getAttributeValue("version");
		feature.setVersion(version);

		/**
		 * feature can be marked if a plugin is marked
		 */
		List<?> listPlugins = root.getChildren("plugin");

		for (Object object : listPlugins) {
			Element courant = (Element) object;
			String pluginId = courant.getAttributeValue("id");
			String pluginVersion = courant.getAttributeValue("version");
			Plugin p;
			boolean side = false;

			if (util.pluginsRegister.containsKey(pluginId)) {
				logger.debug("get plugin from regitry :" + pluginId);
				// search if this plugin have been previously read (from another feature)
				p = util.pluginsRegister.get(pluginId);
			} else {
				File pluginFolder = util.getProjectFolder(pluginId);
				if (pluginFolder != null) {
					logger.debug("Read plugin from file "+pluginId);
					PluginReader pr = new PluginReader(util);
					p = pr.read(pluginFolder);
					side = true;
				} else {
					logger.debug("plugin project not found, create plugin object with reference");
					p = new Plugin();
					p.setId(pluginId);
					p.setVersion(pluginVersion);
				}
				logger.debug("Reccord Plugin :" + p.getId());
				util.pluginsRegister.put(pluginId, p);
			}
			if (side || addAll) {
				logger.debug("add :" + feature + " ->" + p);
				Utils.add(util.tree, feature, p);
				feature.getPlugins().add(p);
			}
		}

		List<?> listIncludedFeatures = root.getChildren("includes");

		for (Object object : listIncludedFeatures) {
			Element currentNode = (Element) object;
			String inculdedFeatureId = currentNode.getAttributeValue("id");
			boolean side = true;
			Feature f = util.featuresRegister.get(inculdedFeatureId);

			if (f == null) {
				File featureFolder = util.getProjectFolder(inculdedFeatureId);
				if (featureFolder != null) {
					f = read(featureFolder);
				} else {
					// not found in repository, not SIDE
					side = false;
					f = new Feature();
					f.setId(inculdedFeatureId);
					f.setVersion(currentNode.getAttributeValue("version"));

				}
			}
			if (side || addAll) {
				util.featuresRegister.put(inculdedFeatureId, f);
				Utils.add(util.tree, feature, f);
				feature.getIncludedFeatures().add(f);
			}
		}

		return feature;
	}
}
