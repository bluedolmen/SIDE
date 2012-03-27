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
			Resource[] resources = resolver.getResources(resourcePattern);
			for (Resource r : resources) {
				logger.info("Loading resource " + r.getDescription());
				loadResource(r);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return dictionary.get(key);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.bluexml.side.framework.alfresco.commons.configurations.IConfigurationFile
	 * #hasValue(K)
	 */
	public boolean hasValue(K key) {
		return dictionary.containsKey(key);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.bluexml.side.framework.alfresco.commons.configurations.IConfigurationFile
	 * #getDictionary()
	 */
	public Map<K, V> getDictionary() {
		return Collections.unmodifiableMap(dictionary);
	}

}
