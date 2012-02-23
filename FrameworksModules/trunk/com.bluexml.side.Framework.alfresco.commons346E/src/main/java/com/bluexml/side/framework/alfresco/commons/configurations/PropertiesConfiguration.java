package com.bluexml.side.framework.alfresco.commons.configurations;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;

public class PropertiesConfiguration extends AbstractConfigurationFile<String, String> {
	/** The logger. */
	protected Log logger = LogFactory.getLog(getClass());

	/*
	 * Helper methods
	 */

	protected void loadResource(Resource r) {
		Properties properties = new Properties();
		try {
			properties.load(r.getInputStream());
		} catch (IOException e) {
			logger.error("Unexpected error loading property file \"" + r.getFilename() + "\" ", e);
		}

		for (Object property : properties.keySet()) {
			String key = (String) property;
			String value = (String) properties.getProperty(key);
			dictionary.put(key, value);			
		}
	}

	public boolean getAsBooleanValue(String key) {
		return Boolean.parseBoolean(getValue(key).trim());
	}
}
