package com.bluexml.side.build.tools.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.bluexml.side.build.tools.Anomaly;
import com.bluexml.side.build.tools.componants.Componant;
import com.bluexml.side.build.tools.componants.Feature;
import com.bluexml.side.build.tools.componants.Module;
import com.bluexml.side.build.tools.componants.Option;
import com.bluexml.side.build.tools.componants.Plugin;
import com.thoughtworks.xstream.XStream;

public class ComponantsRegisters {
	static Logger logger = Logger.getLogger(ComponantsRegisters.class);
	List<File> repositoryLocation;
	private File propertiesFile;
	Map<String, Feature> featuresRegister = new TreeMap<String, Feature>();

	Map<String, Plugin> pluginsRegister = new TreeMap<String, Plugin>();

	Map<String, Option> optionRegister = new TreeMap<String, Option>();

	Map<String, Module> modulesRegister = new TreeMap<String, Module>();

	Map<Componant, Set<Componant>> tree = new TreeMap<Componant, Set<Componant>>();

	Anomaly anomaly = new Anomaly();

	public Anomaly getAnomaly() {
		return anomaly;
	}

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
	public File getProjectFolder(String id, String from) throws Exception {
		if (!id.contains("bluexml")) {
			return null;
		}
		File path = null;

		// use buildConfiguration to search features and plugins

		path = Utils.searchProjectForlderFromConf(id, repositoryLocation, propertiesFile);
		// if not found try to search in file system
		if (path == null) {
			logger.warn("Bundle " + id + " not found try to locate from file system");
			if (!id.contains("Framework")) {
				getAnomaly().addBundleNotFoundInConf(new String[] { id, from });
			}
			
			for (File f : repositoryLocation) {
				logger.warn("search in :" + f);
				path = Utils.find(id, f);
				if (path != null) {
					break;
				}
			}
			if (path == null) {
				logger.error("bundle not found in FS");
			} else {
				logger.warn("bundle found in FS");
			}

		} else {
			logger.debug("••• Bundle " + id + " founded from build configuration");
		}

		if (path != null && !path.getParentFile().getName().equals("trunk")) {
			return null;
		}
		return path;
	}

	public void print() {
		logger.debug("Registers");
		logger.debug("Features :" + featuresRegister);
		logger.debug("Plugins :" + pluginsRegister);
		logger.debug("Options :" + optionRegister);
		logger.debug("Modules :" + modulesRegister);

	}

	public Feature getFeature(String id) throws Exception {
		Feature f = featuresRegister.get(id);
		return f;
	}

	public void saveToXML(File savedRegister) throws IOException {
		XStream xs = new XStream();
		if (!savedRegister.exists()) {
			
			File absoluteFile = savedRegister.getAbsoluteFile();
			File parentFile = absoluteFile.getParentFile();
			parentFile.mkdirs();
			savedRegister.createNewFile();
		}
		OutputStream out = new FileOutputStream(savedRegister);
		xs.toXML(this, out);
		out.close();
	}

	public static ComponantsRegisters load(File f) throws IOException {
		XStream xs = new XStream();
		FileInputStream input = new FileInputStream(f);
		ComponantsRegisters fromXML = (ComponantsRegisters) xs.fromXML(input);
		input.close();
		return fromXML;
	}
}
