package com.bluexml.side.framework.alfresco.commons.configurations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
/**
 * SIDE Extension
 * 
 * @author davidabad
 */
public abstract class AbstractConfigurationFile<K, V> implements IConfigurationFile<K, V> {
	/** The logger. */
	protected Log logger = LogFactory.getLog(getClass());

	/** The path matching resource pattern resolver */
	protected ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

	/** The Dictionary. */
	protected Map<K, V> dictionary = null;

	Resource[] resources = null;

	List<String> resourcePatterns;

	public AbstractConfigurationFile() {

	}

	public AbstractConfigurationFile(ResourcePatternResolver resolver) {
		this.resolver = resolver;
	}

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
		this.resourcePatterns = new ArrayList<String>();
		this.resourcePatterns.add(resourcePattern);
	}

	public void setResourcePatterns(List<String> resourcePatterns) {
		this.resourcePatterns = resourcePatterns;
	}

	protected List<Resource> getResources() {
		List<Resource> lresources = new ArrayList<Resource>();
		for (String string : resourcePatterns) {
			try {
				Resource[] resources2 = resolver.getResources(string);
				List<Resource> asList = Arrays.asList(resources2);
				lresources.addAll(asList);
			} catch (IOException e) {
				logger.error("error when traying to load configuration", e);
			}
		}
		return lresources;
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
	 * #getValue(java.lang.Object, java.lang.Object)
	 */
	public V getValue(K key, V defaultValue) {
		V value = getValue(key);
		if (hasValue(key)) {
			if (value != null) {
				return value;
			} else {
				logger.debug("value is Null :" + key + "in " + dictionary);
			}
		} else {
			logger.debug("key not found :" + key + "in " + dictionary);
		}
		logger.debug("return default value for :" + key + " dictionary :" + dictionary);
		return defaultValue;
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
		if (dictionary == null) {
			dictionary = new HashMap<K, V>();
			try {
				for (Resource r : getResources()) {
					logger.info("Loading resource " + r.getDescription());
					loadResource(r);
				}
			} catch (Exception e) {
				logger.error("error when traying to reload configuration", e);
			}
		}
		return Collections.unmodifiableMap(dictionary);
	}

}
