package com.bluexml.side.build.tools.reader;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.jar.Manifest;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.log4j.Logger;

import com.bluexml.side.build.tools.componants.LinkedWithModule;
import com.bluexml.side.build.tools.componants.Plugin;

public class PluginReader extends Reader {
	Logger logger = Logger.getLogger(this.getClass());

	boolean addAll = false;
	boolean addExtensions = false;
	boolean addRequiredBundle = false;

	public PluginReader(ComponantsRegisters registries, Properties props) {
		super(registries, props);

		addAll = getBooleanPropertyValueFor("addAll", addAll);

		addExtensions = getBooleanPropertyValueFor("addExtensions", addExtensions);

		addRequiredBundle = getBooleanPropertyValueFor("addRequiredBundle", addRequiredBundle);

	}

	public Plugin read(File project) throws Exception {
		logger.debug("Read plugin :" + project.getName());
		// chemin vers le MANIFEST.MF

		File filePluginPath = new File(project.getAbsoluteFile() + File.separator + "META-INF" + File.separator + "MANIFEST.MF");

		FileInputStream fileInputStream;
		fileInputStream = new FileInputStream(filePluginPath);
		Manifest m = new Manifest(fileInputStream);

		String id = "";
		String version = "";
		String name = "";
		List<String> requiredBundle = new ArrayList<String>();

		for (Entry<Object, Object> ent : m.getMainAttributes().entrySet()) {
			String key = ent.getKey().toString();
			logger.debug("key :" + key);
			Object value = ent.getValue();
			logger.debug("* value :" + value);
			String string = value.toString();
			if (key.equals("Bundle-Version")) {
				version = string;
			} else if (key.equals("Bundle-SymbolicName")) {
				if (string.indexOf(";") == -1) {
					id = string;
				} else {
					id = string.substring(0, string.indexOf(";"));
				}
			} else if (key.equals("Bundle-Name")) {
				name = string;
			} else if (key.equals("Require-Bundle")) {
				String[] split = string.split(",");
				CollectionUtils.addAll(requiredBundle, split);

			}
		}

		logger.debug("Plugin ID :" + id);
		if (name.equals("%pluginName")) {
			// must read plugin.properties
			logger.debug("plugin name must be read from properties file");
		} else {
			logger.debug("Plugin Name :" + name);
		}
		logger.debug("Plugin Version :" + version);
		logger.debug("Required Bundles :" + requiredBundle);

		fileInputStream.close();

		Plugin p = new Plugin();
		p.setId(id);
		p.setVersion(version);
		p.setName(name);

		if (registries.pluginsRegister.containsKey(p.getId())) {
			logger.debug("this plugin exist in registry so we stop reading now");
			// stop to prevent loops between PluginReader -> extPReader -> constraints -> plugin
			return registries.pluginsRegister.get(p.getId());
		}

		if (addExtensions) {

			// extension
			// search plugin.xml if any
			File filePluginXMLPath = new File(project, "plugin.xml");

			if (filePluginXMLPath.exists()) {
				logger.debug("extension found :" + filePluginXMLPath);
				BlxExtensionPointReader extR = new BlxExtensionPointReader(registries, props);
				List<LinkedWithModule> lext = extR.read(filePluginXMLPath, p.getId());
				p.setExtensions(lext);
				for (LinkedWithModule extension : lext) {
					Utils.add(registries.tree, p, extension);
				}
			} else {
				logger.debug("Plugin do not have extension :" + filePluginXMLPath);
			}
		}
		if (addRequiredBundle) {
			// add dependencies
			for (String reqIdString : requiredBundle) {
				String reqId = reqIdString;
				String reqVersion = null;
				int indexOfSep = reqIdString.indexOf(";");
				if (indexOfSep != -1) {
					reqId = reqIdString.substring(0, indexOfSep);
					reqVersion = reqIdString.substring(indexOfSep);
				}

				// get the Object
				Plugin reqP = null;
				boolean side = true;

				if (registries.pluginsRegister.containsKey(p.getId())) {
					logger.debug("this plugin exist in registry so we stop reading now");
					reqP = registries.pluginsRegister.get(p.getId());
				} else {
					// need to read from plugin definition if source are available
					logger.debug("requeried Bundle " + reqId + " is not in register, try to read from FS");
					File featureFolder = registries.getProjectFolder(reqId, id);
					if (featureFolder != null) {
						reqP = read(featureFolder);
					} else {
						// not found in repository, not SIDE
						side = false;
						reqP = new Plugin();
						reqP.setId(reqId);
						if (reqVersion != null) {
							reqP.setVersion(reqVersion);
						}
					}
				}

				if (side || addAll) {
					registries.pluginsRegister.put(reqId, reqP);
					Utils.add(registries.tree, p, reqP);
					p.getDependecies().add(reqP);
				}
			}
		}
		return p;
	}
}
