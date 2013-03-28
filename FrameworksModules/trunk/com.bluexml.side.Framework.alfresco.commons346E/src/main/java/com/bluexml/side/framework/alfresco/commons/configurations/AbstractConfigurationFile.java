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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public abstract class AbstractConfigurationFile<K, V> implements IConfigurationFile<K, V> {
	/** The logger. */
	protected Log logger = LogFactory.getLog(getClass());

	/** The path matching resource pattern resolver */
	protected PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

	/** The Dictionary. */
	protected Map<K, V> dictionary = new HashMap<K, V>();

	Resource[] resources = null;
	
	/*
	 * Helper methods
	 */

	/**
	 * method to load dictionary from resource
	 * 
	 * @param r
	 * @throws Exception
	 */
	protected abstract void loadResource(Resource r) throws Exception;

	/*
	 * Spring IOC/DI material
	 */

	public void setResourcePattern(String resourcePattern) {
		try {
			resources = resolver.getResources(resourcePattern);
			reloadResource();
		} catch (Exception e) {
			logger.error("error when traying to load configuration", e);
		}
	}
	
	protected void reloadResource() throws Exception {
		dictionary.clear();
		for (Resource r : resources) {
			logger.info("Loading resource " + r.getDescription());
			loadResource(r);
		}
	}

	/*
	 * Service Methods
	 */

	/*
	 * (non-Javadoc)
	 * @see
	 * com.bluexml.side.framework.alfresco.commons.configurations.IConfigurationFile
	 * #getValue(K)
	 */
	public V getValue(K key) {
		return getDictionary().get(key);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.bluexml.side.framework.alfresco.commons.configurations.IConfigurationFile
	 * #hasValue(K)
	 */
	public boolean hasValue(K key) {
		return getDictionary().containsKey(key);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.bluexml.side.framework.alfresco.commons.configurations.IConfigurationFile
	 * #getDictionary()
	 */
	public Map<K, V> getDictionary() {
		if (logger.isDebugEnabled()) {
			// dynamic reload so configuration can be reloaded, to work the resource path need to be an URL or local file path
			try {
				reloadResource();
			} catch (Exception e) {
				logger.error("error when traying to reload configuration", e);
			}
		}
		return Collections.unmodifiableMap(dictionary);
	}

}
