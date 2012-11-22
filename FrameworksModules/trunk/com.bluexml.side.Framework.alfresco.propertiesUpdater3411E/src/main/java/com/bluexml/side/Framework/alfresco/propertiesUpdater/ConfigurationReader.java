package com.bluexml.side.Framework.alfresco.propertiesUpdater;

import org.jdom.Element;

import com.bluexml.side.Framework.alfresco.propertiesUpdater.ConfigurationReader.PropertyPattern;
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

	class PropertyPattern {
		final public String propertyQname;
		final public String pattern;

		public PropertyPattern(String propertyQname, String pattern) {
			this.propertyQname = propertyQname;
			this.pattern = pattern;

		}
	}

}
