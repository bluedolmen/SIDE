package com.bluexml.side.framework.alfresco.commons.configurations;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.alfresco.service.ServiceRegistry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;

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
