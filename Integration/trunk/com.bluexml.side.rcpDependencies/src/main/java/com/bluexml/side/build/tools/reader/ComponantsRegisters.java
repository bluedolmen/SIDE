package com.bluexml.side.build.tools.reader;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.bluexml.side.build.tools.componants.Componant;
import com.bluexml.side.build.tools.componants.Feature;
import com.bluexml.side.build.tools.componants.Module;
import com.bluexml.side.build.tools.componants.Option;
import com.bluexml.side.build.tools.componants.Plugin;

public class ComponantsRegisters {
	Logger logger = Logger.getLogger(this.getClass());
	List<File> repositoryLocation;
	private File propertiesFile;
	Map<String, Feature> featuresRegister = new TreeMap<String, Feature>();

	Map<String, Plugin> pluginsRegister = new TreeMap<String, Plugin>();

	Map<String, Option> optionRegister = new TreeMap<String, Option>();

	Map<String, Module> modulesRegister = new TreeMap<String, Module>();

	Map<Componant, Set<Componant>> tree = new TreeMap<Componant, Set<Componant>>();

	public Logger getLogger() {
		return logger;
	}

	public List<File> getRepositoryLocation() {
		return repositoryLocation;
	}

	public Map<String, Feature> getFeaturesRegister() {
		return featuresRegister;
	}

	public Map<String, Plugin> getPluginsRegister() {
		return pluginsRegister;
	}

	public Map<String, Option> getOptionRegister() {
		return optionRegister;
	}

	public Map<String, Module> getModulesRegister() {
		return modulesRegister;
	}

	public Map<Componant, Set<Componant>> getTree() {
		return tree;
	}

	public ComponantsRegisters(List<File> repo, File propertiesFile) {
		this.repositoryLocation = repo;
		this.propertiesFile = propertiesFile;
	}

	/**
	 * search in repository the project
	 * 
	 * @return
	 * @throws Exception
	 */
	public File getProjectFolder(String id) throws Exception {
		if (!id.contains("bluexml")) {
			return null;
		}
		File path = null;

		// use buildConfiguration to search features and plugins

		path = Utils.searchProjectForlerFromConf(id, repositoryLocation, propertiesFile);
		// if not found try to search in file system
		if (path == null) {
			logger.warn("Bundle " + id + " not found try to locate from file system");
			for (File f : repositoryLocation) {
				path = Utils.find(id, f);
				if (path != null) {
					break;
				}
			}
		} else {
			logger.debug("••• Bundle " + id + " founded from build configuration");
		}

		if (!path.getParentFile().getName().equals("trunk")) {
			return null;
		}
		return path;
	}

	public void print() {
		System.out.println("Registers");
		System.out.println("Features :" + featuresRegister);
		System.out.println("Plugins :" + pluginsRegister);
		System.out.println("Options :" + optionRegister);
		System.out.println("Modules :" + modulesRegister);

	}

	public Feature getFeature(String id) throws Exception {
		Feature f = featuresRegister.get(id);
		return f;
	}
}
