package com.bluexml.side.integration.buildHudson.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URI;
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
import org.jdom.xpath.XPath;

public class PluginsUpdater {
	private Logger logger = Logger.getLogger(getClass());
	BuilderUtils bu;
	List<String> plugins2update = new ArrayList<String>();
	List<String> pluginsUpdated = new ArrayList<String>();
	List<String> pluginsList = new ArrayList<String>();
	// project used to update plugin dependencies but do not need to be updated
	// (Community/Enterprise)
	List<String> pluginsListReadOnly = new ArrayList<String>();
	Map<String, String> pluginsNewVersion = new HashMap<String, String>();
	Map<String, String> pluginsVersions = new HashMap<String, String>();

	public Map<String, String> getPluginsNewVersion() {
		return pluginsNewVersion;
	}

	boolean allFixed = false;

	MavenProjectUpdater mpu = null;

	public PluginsUpdater(List<String> pluginsList, List<String> plugins2update, List<String> pluginsListReadOnly, MavenProjectUpdater mpu, BuilderUtils bu) {
		this.bu = bu;
		this.plugins2update = plugins2update;
		this.pluginsList = pluginsList;
		if (pluginsListReadOnly != null) {
			this.pluginsListReadOnly = pluginsListReadOnly;
		}
		this.mpu = mpu;
	}

	public void checkAndUpdate() throws Exception {
		List<String> oldPlugins2update;

		int c = 0;
		do {
			logger.debug("PluginsUpdater.checkAndUpdate() occurance #" + c);
			oldPlugins2update = new ArrayList<String>(plugins2update);
			markPlugins();
			c++;
		} while (!plugins2update.equals(oldPlugins2update));
		logger.debug("PluginsUpdater.checkAndUpdate() Updates DONE in " + c + " iteration");

		fixPluginsVersion();
		allFixed = true;

	}

	private void markPlugins() throws Exception {
		logger.debug("PluginsUpdater.markPlugins()");
		List<String> plugins = new ArrayList<String>(pluginsList);
		plugins.removeAll(pluginsListReadOnly);
		plugins.removeAll(plugins2update);
		for (String pluginId : plugins) {
			logger.debug("\tcheck plugin " + pluginId);
			// mark if have module dependency updated (maven project)
			Document pluginDoc = getPluginDoc(pluginId);
			if (pluginDoc != null) {
				boolean changes = updatePluginModuleDependencies(pluginDoc, false);
				if (changes) {
					logger.debug("PluginsUpdater.markPlugins() mark " + pluginId);
					plugins2update.add(pluginId);
				} else {
					// up to date
					// record in list
					String version = getPluginVersion(pluginId);
					pluginsVersions.put(pluginId, version);
				}
			}
			// TODO option update plugins dependencies to fix plugins version

		}
	}

	private void fixPluginsVersion() throws Exception {
		logger.debug("PluginsUpdater.fixPluginsVersion()");
		for (String plugin : plugins2update) {
			// fix plugin verion
			updateVersion(plugin);
			// fix module dependencies
			Document pluginDoc = getPluginDoc(plugin);
			if (pluginDoc != null) {
				updatePluginModuleDependencies(pluginDoc, true);
			}
			// TODO fix plugins depenencies

			// get dependencies

			// check if marked
			// check if in pluginUpdeted
			// so update ref

			pluginsUpdated.add(plugin);

		}

	}

	/**
	 * Update the version number of the modules in the files feature.xml
	 * 
	 * 
	 * @param projectName
	 * @throws Exception
	 * @throws
	 */
	private boolean updatePluginModuleDependencies(Document plugin, boolean applyChanges) throws Exception {
		logger.debug("PluginsUpdater.updatePluginModuleDependencies()");
		boolean modifie = false;
		Document document = plugin;
		URI path = new URI(document.getBaseURI());
		File fileFeaturePath = new File(path.getPath());
		Element racine = document.getRootElement();

		XPath xpa = XPath.newInstance("/plugin//moduleDependence");
		List<?> plugins = xpa.selectNodes(racine);

		for (Object moduleEl : plugins) {
			Element courantmoduleDependence1 = (Element) moduleEl;
			String moduleId = courantmoduleDependence1.getAttributeValue("moduleId");
			String oldVersionMin = courantmoduleDependence1.getAttributeValue("versionMin");
			String oldVersionMax = courantmoduleDependence1.getAttributeValue("versionMax");
			logger.debug("\t\tcheck module :" + moduleId);
			String version = mpu.getMavenProjectVersion(moduleId);
			if (!oldVersionMin.equals(version) || !oldVersionMax.equals(version)) {
				courantmoduleDependence1.setAttribute("versionMax", version);
				courantmoduleDependence1.setAttribute("versionMin", version);
				logger.debug("\t\tPluginsUpdater.updatePluginModuleDependencies() " + moduleId + ":" + oldVersionMax + " -> " + version);
				modifie = true;
			}
		}

		if (applyChanges && modifie) {
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream(fileFeaturePath));
		}

		return modifie;
	}

	private Document getPluginDoc(String plugin) throws Exception {
		// chemin vers le plugin.xml
		String fileFeaturePath = bu.getPathToLocalCopy(plugin) + File.separator + "plugin.xml";
		File pluginFile = new File(fileFeaturePath);

		// On crée une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();
		Document document = null;
		try {
			document = sxb.build(pluginFile);
		} catch (FileNotFoundException e) {
			// plugin.xml could not exist for most plugins
			logger.debug("warn " + pluginFile + " not found");
		}
		return document;
	}

	private void updateVersion(String pluginId) throws Exception {
		String[] pattern = bu.getNumVersionPattern();

		// chemin vers le MANIFEST.MF
		String filePluginPath = bu.getPathToLocalCopy(pluginId) + File.separator + "META-INF" + File.separator + "MANIFEST.MF";

		// on récupére dans un tableau les 3 numéros de version du
		// projet
		String[] number = BuilderUtils.openProperties(filePluginPath).getProperty("Bundle-Version").split("\\.");

		String ligne = "";

		BufferedReader reader = new BufferedReader(new FileReader(filePluginPath));
		PrintWriter writer = new PrintWriter(new FileWriter(filePluginPath + ".txt"));
		while ((ligne = reader.readLine()) != null) {
			// si la ligne contient "Bundle-Version:"
			if (ligne.indexOf("Bundle-Version:") != -1) {
				// on supprime tout ce qui se trouve aprés
				// "Bundle-Version:"
				ligne = ligne.substring(0, "Bundle-Version:".length());
				// on ajoute a la ligne le nouveau numéro de
				// version
				// si on ne force pas la mise a jour du numéro de
				// version
				String newVersion = "";
				if ("".equals(bu.getForceNumberVersion())) {
					newVersion = bu.update(number, pattern);
				} else {
					newVersion = bu.update(number, bu.getForceNumberVersion().split("\\."));
				}
				ligne += " " + newVersion;
				pluginsNewVersion.put(pluginId, newVersion);
				pluginsVersions.put(pluginId, newVersion);
			}

			// ecriture de la ligne dans le nouveau fichier
			writer.println(ligne);
		}
		// fermeture des flux
		reader.close();
		writer.close();

		// Suppression de l'ancien fichier MANIFEST
		new File(filePluginPath).delete();
		// Renomage du nouveau MANIFEST
		new File(filePluginPath + ".txt").renameTo(new File(filePluginPath));
	}

	public String getPluginVersion(String pluginId) {
		String version = pluginsVersions.get(pluginId);
		if (allFixed && version != null) {
			return version;
		} else {
			// chemin vers le MANIFEST.MF
			String filePluginPath = bu.getPathToLocalCopy(pluginId) + File.separator + "META-INF" + File.separator + "MANIFEST.MF";
			String number = BuilderUtils.openProperties(filePluginPath).getProperty("Bundle-Version").trim();
			return number;
		}
	}
}
