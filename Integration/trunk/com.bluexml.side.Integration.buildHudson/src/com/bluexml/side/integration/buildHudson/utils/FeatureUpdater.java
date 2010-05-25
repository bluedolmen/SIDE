package com.bluexml.side.integration.buildHudson.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class FeatureUpdater {
	private Logger logger = Logger.getLogger(getClass());
	BuilderUtils bu;
	List<String> feature2update = new ArrayList<String>();
	List<String> featureUpdated = new ArrayList<String>();
	List<String> featuresList = new ArrayList<String>();
	List<String> featuresListReadOnly = new ArrayList<String>();
	Map<String, String> featuresNewsVersion = new HashMap<String, String>();
	Map<String, String> featuresVersions = new HashMap<String, String>();

	public Map<String, String> getFeaturesNewsVersion() {
		return featuresNewsVersion;
	}

	public Map<String, String> getFeaturesVersions() {
		return featuresVersions;
	}

	boolean allmarked = false;

	PluginsUpdater pu = null;

	/**
	 * FeatureUpadter
	 * 
	 * @param featuresList
	 *            list of all feature
	 * @param features2update
	 *            list of features marked for update (by previous process)
	 */
	public FeatureUpdater(List<String> featuresList, List<String> features2update, List<String> featuresListReadOnly, PluginsUpdater pu, BuilderUtils bu) {
		this.bu = bu;
		this.featuresList = featuresList;
		this.feature2update = features2update;
		this.featuresListReadOnly = featuresListReadOnly;
		this.pu = pu;
	}

	private void checkFeatures() throws Exception {
		List<String> features_ = new ArrayList<String>(featuresList);
		features_.removeAll(featuresListReadOnly);
		features_.removeAll(feature2update);
		if (features_.size() > 0) {
			for (String id : features_) {
				System.out.println("FeatureUpdater.checkFeatures() Check feature :" + id);
				boolean marked = false;
				// this feature is not still marked for update
				// get feature file
				File featureXMLFile = new File(bu.getFeatureXMLFile(id));
				Document doc = new SAXBuilder().build(featureXMLFile);
				Element root = doc.getRootElement();
				String version = root.getAttributeValue("version");
				featuresVersions.put(id, version);
				/**
				 * feature can be marked if a plugin is marked
				 */
				List<?> listPlugins = root.getChildren("plugin");

				for (Object object : listPlugins) {
					Element courant = (Element) object;

					// sauvegarde du numéro de version
					String oldVersionNumber = courant.getAttributeValue("version");

					// on regarde si le numéro de version du plugin a changé
					String pluginId = courant.getAttributeValue("id");

					String newVersionNumber = pu.getPluginVersion(pluginId);

					System.out.println("\tFeatureUpdater.checkFeatures() plugin:" + pluginId);
					System.err.println("compare versions old:" + oldVersionNumber + "/" + newVersionNumber);

					if (!oldVersionNumber.equals(newVersionNumber)) {
						System.out.println("\tUtils.updateVersionNumber() feature to update because plugin updated : " + pluginId);
						marked = true;
						// break;
					}
				}

				if (!marked) {
					/**
					 * if a included feature is marked
					 */
					List<?> listIncludedFeatures = root.getChildren("includes");

					for (Object object : listIncludedFeatures) {
						Element currentNode = (Element) object;
						String oldVersionNumber = currentNode.getAttributeValue("version");
						String inculdedFeatureId = currentNode.getAttributeValue("id");
						System.out.println("\tscan included feature :" + inculdedFeatureId + " : " + oldVersionNumber);
						// check version of features

						String newVersionNumber = getFeatureVersion(inculdedFeatureId);

						System.err.println("compare versions old:" + oldVersionNumber + "/" + newVersionNumber);
						if (!oldVersionNumber.equals(newVersionNumber)) {
							System.out.println("\tFeatureUpdater.checkFeatures() feature marked beacause found included marked feature " + inculdedFeatureId);
							marked = true;
							// break;
						}
					}
				}

				if (marked) {
					System.out.println("FeatureUpdater.checkFeatures() MARKED " + id);
					feature2update.add(id);
				}
			}

		}
	}

	private void updateMarkedFeatures() throws Exception {
		for (String feature : feature2update) {
			System.out.println("FeatureUpdater.updateMarkedFeatures() UPDATE " + feature);
			File featureXMLFile = new File(bu.getFeatureXMLFile(feature));
			Document document = new SAXBuilder().build(featureXMLFile);
			Element root = document.getRootElement();

			// update version number
			String[] pattern = bu.getNumVersionPattern();
			String[] number = root.getAttributeValue("version").split("\\.");
			String newVersion = bu.update(number, pattern);
			root.setAttribute("version", newVersion);
			featuresNewsVersion.put(feature, newVersion);
			// update plugins version
			System.out.println("FeatureUpdater.updateMarkedFeatures() Update plugins Refs");
			List<?> listPlugins = root.getChildren("plugin");
			for (Object object : listPlugins) {
				Element courant = (Element) object;
				String pluginId = courant.getAttributeValue("id");
				String newVersionNumber = pu.getPluginVersion(pluginId);
				String oldVersionNumber = courant.getAttributeValue("version");
				if (!oldVersionNumber.equals(newVersionNumber)) {
					courant.setAttribute("version", newVersionNumber);
					// on indique que le numéro de feature doit changer
					System.out.println("\t\tFeatureUpdater.updateMarkedFeatures() update plugin ref " + pluginId + ":" + oldVersionNumber + " -> " + newVersionNumber);
				}
			}

			// update included features version
			System.out.println("FeatureUpdater.updateMarkedFeatures() Update features Refs");
			List<?> listIncludedFeatures = root.getChildren("includes");
			for (Object object : listIncludedFeatures) {
				Element currentNode = (Element) object;
				String oldVersionNumber = currentNode.getAttributeValue("version");
				String inculdedFeatureId = currentNode.getAttributeValue("id");
				String newVersionNumber = getFeatureVersion(inculdedFeatureId);
				if (!oldVersionNumber.equals(newVersionNumber)) {
					currentNode.setAttribute("version", newVersionNumber);
					System.out.println("\t\tFeatureUpdater.updateMarkedFeatures() update feature ref " + inculdedFeatureId + ":" + oldVersionNumber + " -> " + newVersionNumber);
				}
			}

			// Enregistrement du fichier
			try {
				XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
				sortie.output(document, new FileOutputStream(featureXMLFile));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void checkAndUpdateAllFeatures() throws Exception {
		// mark features
		// use fixed point algorithm to check end condition
		// some feature could be forgotten so search one more time
		// if no changes so job's done
		List<String> oldFeature2update;
		System.out.println("#####FeatureUpdater.checkAndUpdateAllFeatures() checkAllFeatures");
		int c = 0;
		do {
			System.out.println("@@FeatureUpdater.checkAndUpdateAllFeatures()");
			oldFeature2update = new ArrayList<String>(feature2update);
			// maybe we could find more feature to mark
			System.err.println("|||| before" + oldFeature2update.size());
			checkFeatures();
			System.err.println("|||| before" + oldFeature2update.size());
			System.err.println("|||| before feature2update" + feature2update.size());
			c++;
		} while (!feature2update.equals(oldFeature2update));
		System.out.println("#####FeatureUpdater.checkAndUpdateAllFeatures() Updates features DONE in " + c);
		updateMarkedFeatures();
	}

	public String getFeatureVersion(String featureId) throws Exception {
		String version = featuresVersions.get(featureId);
		if (allmarked && version != null) {
			return version;
		} else {
			// get Feature.xml
			File featureXMLFile = new File(bu.getFeatureXMLFile(featureId));
			Document document = new SAXBuilder().build(featureXMLFile);
			Element root = document.getRootElement();
			return root.getAttributeValue("version");
		}
	}
}
