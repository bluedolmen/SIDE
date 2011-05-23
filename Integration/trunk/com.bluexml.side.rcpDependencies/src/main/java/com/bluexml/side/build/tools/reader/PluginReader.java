package com.bluexml.side.build.tools.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.bluexml.side.build.tools.componants.Extension;
import com.bluexml.side.build.tools.componants.Plugin;

public class PluginReader {
	Logger logger = Logger.getLogger(this.getClass());
	private ComponantsRegisters util;

	public PluginReader(ComponantsRegisters util) {
		this.util = util;
	}

	public Plugin read(File project) throws Exception {
		logger.debug("Read plugin :" + project.getName());
		// chemin vers le MANIFEST.MF

		File filePluginPath = new File(project.getAbsoluteFile() + File.separator + "META-INF" + File.separator + "MANIFEST.MF");
		Properties props = new Properties();

		props.load(new FileInputStream(filePluginPath));

		String id = "";
		String version = "";
		String name = "";
		String ligne = "";

		BufferedReader reader = new BufferedReader(new FileReader(filePluginPath));
		while ((ligne = reader.readLine()) != null) {
			// si la ligne contient "Bundle-Version:"
			if (ligne.indexOf("Bundle-Version:") != -1) {
				// on supprime tout ce qui se trouve aprés
				// "Bundle-Version:"
				version = ligne.substring(0, "Bundle-Version:".length());
				version = ligne.replaceFirst("^Bundle-Version:([^;]*).*", "$1").trim();
				logger.debug("Plugin Version :" + version);
			}

			if (ligne.indexOf("Bundle-SymbolicName: ") != -1) {
				id = ligne.substring(0, "Bundle-SymbolicName:".length());
				id = ligne.replaceFirst("^Bundle-SymbolicName:([^;]*).*", "$1").trim();
				logger.debug("Plugin ID :" + id);
			}
			if (ligne.indexOf("Bundle-Name:") != -1) {
				name = ligne.substring(0, "Bundle-Name:".length());
				name = ligne.replaceFirst("^Bundle-Name:([^;]*).*", "$1").trim();
				if (name.equals("%pluginName")) {
					// must read plugin.properties
					logger.debug("plugin name must be read from properties file");
				} else {
					logger.debug("Plugin Name :" + name);
				}
			}

		}
		Plugin p = new Plugin();
		p.setId(id);
		p.setVersion(version);
		p.setName(name);

		if (util.pluginsRegister.containsKey(p.getId())) {
			logger.debug("this plugin exist in registry so we stop reading now");
			// stop to prevent loops between PluginReader -> extPReader -> constraints -> plugin
			return util.pluginsRegister.get(p.getId());
		}
		// extension
		// search plugin.xml if any
		File filePluginXMLPath = new File(project, "plugin.xml");

		if (filePluginXMLPath.exists()) {
			logger.debug("extension found :" + filePluginXMLPath);
			BlxExtensionPointReader extR = new BlxExtensionPointReader(util);
			List<Extension> lext = extR.read(filePluginXMLPath, p.getId());
			p.setExtensions(lext);
			for (Extension extension : lext) {
				Utils.add(util.tree, p, extension);
			}
		} else {
			logger.debug("Plugin do not have extension :" + filePluginXMLPath);
		}

		return p;
	}

}
