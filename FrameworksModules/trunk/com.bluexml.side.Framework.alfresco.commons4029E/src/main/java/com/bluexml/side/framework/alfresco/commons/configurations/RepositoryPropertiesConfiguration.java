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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.alfresco.service.ServiceRegistry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
/**
 * SIDE Extension
 * 
 * @author davidabad
 */
public class RepositoryPropertiesConfiguration extends PropertiesConfiguration {
	/** The logger. */
	protected Log logger = LogFactory.getLog(getClass());

	public RepositoryPropertiesConfiguration(ServiceRegistry serviceRegistry) {
		this.resolver = new RepositoryResourcePatternResolver(serviceRegistry);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.bluexml.side.framework.alfresco.commons.configurations.IConfigurationFile
	 * #getDictionary()
	 */
	public Map<String, String> getDictionary() {
		dictionary = new HashMap<String, String>();
		try {
			for (Resource r : getResources()) {
				logger.info("Loading resource " + r.getDescription());
				loadResource(r);
			}
		} catch (Exception e) {
			logger.error("error when traying to reload configuration", e);
		}
		logger.debug("getDictionary() " + dictionary);
		return Collections.unmodifiableMap(dictionary);
	}

	public boolean isValidePropertiesResource(Properties props) {
		return true;
	}
}
