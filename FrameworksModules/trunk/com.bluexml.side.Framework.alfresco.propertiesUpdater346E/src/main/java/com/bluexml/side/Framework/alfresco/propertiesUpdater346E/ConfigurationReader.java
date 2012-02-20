package com.bluexml.side.Framework.alfresco.propertiesUpdater346E;

import java.io.File;
import java.io.FileInputStream;

import org.jdom.Document;

import com.bluexml.side.framework.alfresco.commons.configurations.MultiValuesConfigurationXMLFile;

public class ConfigurationReader extends MultiValuesConfigurationXMLFile<String, String> {

	public ConfigurationReader() throws Exception {
		super("type", "qname", "property", ContentType.TEXTNODE, null);
	}

	@Override
	protected String getValueObject(String valueString) {
		return valueString;
	}

	@Override
	protected String getKeyObject(String keyString) {
		return keyString;
	}

	public static void main(String[] args) {
		File f = new File("/Users/davidabad/servers/alfresco-enterprise-3.4.6/tomcat/webapps/alfresco/WEB-INF/classes/alfresco/module/SIDE_ModelExtension_ifremer/properties-synchronization.xml");
		try {
			ConfigurationReader conf = new ConfigurationReader();
			Document document = conf.getDocument(new FileInputStream(f));
			conf.loadXML(document);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
