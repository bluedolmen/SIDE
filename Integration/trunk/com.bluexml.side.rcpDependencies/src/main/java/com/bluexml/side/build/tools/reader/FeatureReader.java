package com.bluexml.side.build.tools.reader;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.bluexml.side.build.tools.componants.Feature;
import com.bluexml.side.build.tools.componants.Plugin;

public class FeatureReader extends Reader {
	Logger logger = Logger.getLogger(this.getClass());

	boolean addAll = false;

	private boolean readPlugins = true;
	private boolean readIncludedFeatures = true;

	public FeatureReader(ComponantsRegisters registries, Properties props) {
		super(registries, props);
		addAll = getBooleanPropertyValueFor("addAll", addAll);
		readPlugins = getBooleanPropertyValueFor("readPlugins", readPlugins);
		readIncludedFeatures = getBooleanPropertyValueFor("readIncludedFeatures", readIncludedFeatures);

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

		if (readPlugins) {
			/**
			 * read plugins in this feature
			 */
			List<?> listPlugins = root.getChildren("plugin");

			for (Object object : listPlugins) {
				Element courant = (Element) object;
				String pluginId = courant.getAttributeValue("id");
				String pluginVersion = courant.getAttributeValue("version");
				Plugin p;
				boolean side = true;

				if (registries.pluginsRegister.containsKey(pluginId)) {
					logger.debug("get plugin from regitry :" + pluginId);
					// search if this plugin have been previously read (from another feature)
					p = registries.pluginsRegister.get(pluginId);
				} else {
					File pluginFolder = registries.getProjectFolder(pluginId, id);
					if (pluginFolder != null) {
						logger.debug("Read plugin from file " + pluginId);
						PluginReader pr = new PluginReader(registries, props);
						p = pr.read(pluginFolder);
					} else {
						side = false;
						logger.warn(id + " : plugin project not found, create plugin object with reference");
						p = new Plugin();
						p.setId(pluginId);
						p.setVersion(pluginVersion);
					}
					logger.debug("Reccord Plugin :" + p.getId());
					registries.pluginsRegister.put(pluginId, p);
				}
				if (side || addAll) {
					logger.debug("add :" + feature + " ->" + p);
					Utils.add(registries.tree, feature, p);
					feature.getPlugins().add(p);
				}
			}

		}

		if (readIncludedFeatures) {
			List<?> listIncludedFeatures = root.getChildren("includes");

			for (Object object : listIncludedFeatures) {
				Element currentNode = (Element) object;
				String inculdedFeatureId = currentNode.getAttributeValue("id");
				boolean side = true;
				Feature f = registries.featuresRegister.get(inculdedFeatureId);

				if (f == null) {
					File featureFolder = registries.getProjectFolder(inculdedFeatureId, id);
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
					registries.featuresRegister.put(inculdedFeatureId, f);
					Utils.add(registries.tree, feature, f);
					feature.getIncludedFeatures().add(f);
				}
			}
		}

		return feature;
	}
}
