package com.bluexml.side.Framework.alfresco.propertiesUpdater346E;

import java.io.File;
import java.io.FileInputStream;

import org.jdom.Document;
import org.jdom.Element;

import com.bluexml.side.Framework.alfresco.propertiesUpdater346E.ConfigurationReader.PropertyPattern;
import com.bluexml.side.framework.alfresco.commons.configurations.MultiValuesConfigurationXMLFile;

public class ConfigurationReader extends MultiValuesConfigurationXMLFile<String, PropertyPattern> {

	private static final String QNAME = "qname";

	public ConfigurationReader() throws Exception {
		super("type", QNAME, "property");
	}


	@Override
	protected PropertyPattern getValueObject(Element value) {
		// TODO Auto-generated method stub
		return new PropertyPattern(value.getAttributeValue(QNAME), value.getTextNormalize());
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

	class PropertyPattern {
		final public String propertyQname;
		final public String pattern;

		public PropertyPattern(String propertyQname, String pattern) {
			this.propertyQname = propertyQname;
			this.pattern = pattern;

		}
	}

}
