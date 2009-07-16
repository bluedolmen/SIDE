package com.bluexml.alfresco.modules.sql.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class JPAAssociationDictionary {

	private Logger _logger = Logger.getLogger(getClass());
	private Map<String, String> _dictionary = new HashMap<String, String>();
	private PathMatchingResourcePatternResolver _resolver = new PathMatchingResourcePatternResolver(); 
	
	public String translate(String key) {
		return _dictionary.get(key);
	}
	
	public void clear() {
		_dictionary.clear();
	}
	
	private void _loadResource(Resource r) {
		Properties properties = new Properties();
		try {
			properties.load(r.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Object property : properties.keySet()) {
			String key = (String) property;
			String value = (String) properties.getProperty(key);
			_dictionary.put(key, value);
		}
		
	}
	
	/*
	 * Spring IoC material
	 */
	
	public void setResourcePattern(String resourcePattern) {
		try {
			Resource[] resources = _resolver.getResources(resourcePattern);
			for (Resource r : resources) {
				_logger.debug("Loading resource " + r.getDescription());
				_loadResource(r);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
