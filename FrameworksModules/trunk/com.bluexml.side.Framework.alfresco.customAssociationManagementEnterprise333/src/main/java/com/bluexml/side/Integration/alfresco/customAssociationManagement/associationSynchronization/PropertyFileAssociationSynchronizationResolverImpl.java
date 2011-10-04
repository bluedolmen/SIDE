package com.bluexml.side.Integration.alfresco.customAssociationManagement.associationSynchronization;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class PropertyFileAssociationSynchronizationResolverImpl implements
		AssociationSynchronizationResolver {
	
	/** The logger. */
	private Logger logger = Logger.getLogger(getClass());
	
	/** The path matching resource pattern resolver */
	private PathMatchingResourcePatternResolver _resolver = new PathMatchingResourcePatternResolver();

	/** The Dictionary. */
	private Map<String, String> _dictionary = new HashMap<String, String>();


	public QName resolve(QName associationQName) {
		if (_dictionary.containsKey(associationQName.getLocalName())) {
			return QName.createQName(associationQName.getNamespaceURI(), _dictionary.get(associationQName.getLocalName()) );
		} else {
			return null;
		}
	}

	/*
	 * This implements relies on the fact that if a key can be found in the dictionary, then it is a both navigable association
	 * @see com.bluexml.side.Integration.alfresco.associationSynchronisation.AssociationSynchronisationResolver#isBothNavigable(org.alfresco.service.namespace.QName)
	 */
	public boolean isBothNavigable(QName associationQName) {
		return _dictionary.containsKey(associationQName.getLocalName());
	}

	/*
	 * Helper methods
	 */
				
	private void _loadResource(Resource r) {
		Properties properties = new Properties();
		try {
			properties.load(r.getInputStream());
		} catch (IOException e) {
			logger.error("Unexpected error loading property file \"" + r.getFilename() + "\" ",e);
		}
		
		for (Object property : properties.keySet()) {
			String key = (String) property;
			String value = (String) properties.getProperty(key);
			_dictionary.put(key, value);
			_dictionary.put(value, key);
		}
		
	}

	
	/*
	 * Spring IOC/DI material
	 */
		
	public void setResourcePattern(String resourcePattern) {
		try {
			Resource[] resources = _resolver.getResources(resourcePattern);
			for (Resource r : resources) {
				logger.debug("Loading resource " + r.getDescription());
				_loadResource(r);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
