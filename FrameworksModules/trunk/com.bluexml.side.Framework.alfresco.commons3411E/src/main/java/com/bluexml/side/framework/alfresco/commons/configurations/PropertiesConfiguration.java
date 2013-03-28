/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.framework.alfresco.commons.configurations;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;

public abstract class PropertiesConfiguration extends AbstractConfigurationFile<String, String> {
	/** The logger. */
	protected Log logger = LogFactory.getLog(getClass());

	/*
	 * Helper methods
	 */

	protected void loadResource(Resource r) {
		if (r.exists()) {
			Properties properties = new Properties();
			try {
				properties.load(r.getInputStream());
				logger.info("Load Properties form Resource " + r);
			} catch (IOException e) {
				logger.error("Unexpected error loading property file \"" + r.getFilename() + "\" ", e);
			}
			if (isValidePropertiesResource(properties)) {
				for (Object property : properties.keySet()) {
					String key = (String) property;
					String value = (String) properties.getProperty(key);
					dictionary.put(key, value);
				}
			}
		} else {
			logger.info("Resource do not exists :" + r.getDescription());
		}
	}

	public boolean getAsBooleanValue(String key) {
		return Boolean.parseBoolean(getValue(key).trim());
	}

	public boolean getAsBooleanValue(String key, Boolean defaultValue) {
		String defaultString = Boolean.toString(defaultValue);
		String value = getValue(key, defaultString);
		return Boolean.parseBoolean(value.trim());
	}

	public abstract boolean isValidePropertiesResource(Properties props);
}
