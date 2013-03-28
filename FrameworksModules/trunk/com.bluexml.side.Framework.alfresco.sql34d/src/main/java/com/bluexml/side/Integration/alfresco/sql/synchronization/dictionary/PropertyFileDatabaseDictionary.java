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
package com.bluexml.side.Integration.alfresco.sql.synchronization.dictionary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.alfresco.error.AlfrescoRuntimeException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * The Class DatabaseDictionary.
 */
public class PropertyFileDatabaseDictionary implements BidirectionalDatabaseDictionary {
	
	/** The logger. */
	private Logger logger = Logger.getLogger(getClass());
	
	/** The path matching resource pattern resolver */
	private PathMatchingResourcePatternResolver _resolver = new PathMatchingResourcePatternResolver();
	
	private AbstractRenamingStrategy renamingStrategy;

		
	/** The Constant CLASS_PREFIX. */
	private static final String CLASS_PREFIX = "class";
	
	/** The Constant ATTRIBUTE_PREFIX. */
	private static final String ATTRIBUTE_PREFIX = "attribute";
	
	/** The Constant ASSOCIATION_PREFIX. */
	private static final String ASSOCIATION_PREFIX = "association";
	
	/** The Constant SOURCE_PREFIX. */
	private static final String SOURCE_PREFIX = "source";
	
	/** The Constant TARGET_PREFIX. */
	private static final String TARGET_PREFIX = "target";
	
	private static final String ALIAS_PREFIX = "alias";
		
	/** The Constant NAME_PREFIX. */
	private static final String NAME_PREFIX = "name";
	
	/** The Constant KEY_SEPARATOR. */
	private static final String KEY_SEPARATOR = ".";
	
	private static final String CLASS_NAME_PREFIX = CLASS_PREFIX + KEY_SEPARATOR + NAME_PREFIX;
	
	private static final String ASSOCIATION_NAME_PREFIX = ASSOCIATION_PREFIX + KEY_SEPARATOR + NAME_PREFIX;
	
	/** The Dictionary. */
	private Map<String, String> _dictionary = new HashMap<String, String>();
	private Map<String, String> _reverseNameDictionary = new HashMap<String, String>();
		
		
	/* (non-Javadoc)
	 * @see com.bluexml.alfresco.modules.sql.synchronization.dictionary.IDatabaseDictionary#resolveClassAsTableName(java.lang.String)
	 */
	public String resolveClassAsTableName (String class_name) {
		String key = StringUtils.join(new String[] {CLASS_PREFIX, NAME_PREFIX, class_name}, KEY_SEPARATOR);
		return getDictionaryValue(key);
	}
	
	/* (non-Javadoc)
	 * @see com.bluexml.alfresco.modules.sql.synchronization.dictionary.IDatabaseDictionary#resolveAssociationAsTableName(java.lang.String)
	 */
	public String resolveAssociationAsTableName (String association_name) {
		String key = StringUtils.join(new String[] {ASSOCIATION_PREFIX, NAME_PREFIX, association_name}, KEY_SEPARATOR);
		return getDictionaryValue(key);
	}

	/* (non-Javadoc)
	 * @see com.bluexml.alfresco.modules.sql.synchronization.dictionary.IDatabaseDictionary#resolveAttributeAsColumnName(java.lang.String, java.lang.String)
	 */
	public String resolveAttributeAsColumnName (String attribute_name, String class_name) {
		String key = StringUtils.join(new String[] {CLASS_PREFIX, ATTRIBUTE_PREFIX, NAME_PREFIX, class_name, attribute_name}, KEY_SEPARATOR);
		return getDictionaryValue(key);
	}
	
	
	/* (non-Javadoc)
	 * @see com.bluexml.alfresco.modules.sql.synchronization.dictionary.IDatabaseDictionary#getSourceClass(java.lang.String)
	 */
	public String getSourceClass (String association_name) {
		String key = StringUtils.join(new String[] {ASSOCIATION_PREFIX, SOURCE_PREFIX, association_name}, KEY_SEPARATOR);
		return getDictionaryValue(key);
	}
	
	/*
	 * Returns an alias of the class if any exist
	 * This method is mainly useful on reflexive associations where the both columns cannot have the same name
	 */
	/* (non-Javadoc)
	 * @see com.bluexml.alfresco.modules.sql.synchronization.dictionary.IDatabaseDictionary#getSourceAlias(java.lang.String)
	 */
	public String getSourceAlias (String association_name) {
		String key = StringUtils.join(new String[] {ASSOCIATION_PREFIX, SOURCE_PREFIX, ALIAS_PREFIX, association_name}, KEY_SEPARATOR);
		if (! _dictionary.containsKey(key)) {
			key = StringUtils.join(new String[] {ASSOCIATION_PREFIX, SOURCE_PREFIX, association_name}, KEY_SEPARATOR);
		}
		
		return getDictionaryValue(key);
	}
	
	/* (non-Javadoc)
	 * @see com.bluexml.alfresco.modules.sql.synchronization.dictionary.IDatabaseDictionary#getTargetClass(java.lang.String)
	 */
	public String getTargetClass (String association_name) {
		String key = StringUtils.join(new String[] {ASSOCIATION_PREFIX, TARGET_PREFIX, association_name}, KEY_SEPARATOR);
		return getDictionaryValue(key);
	}

	/* (non-Javadoc)
	 * @see com.bluexml.alfresco.modules.sql.synchronization.dictionary.IDatabaseDictionary#getTargetAlias(java.lang.String)
	 */
	public String getTargetAlias (String association_name) {
		String key = StringUtils.join(new String[] {ASSOCIATION_PREFIX, TARGET_PREFIX, ALIAS_PREFIX, association_name}, KEY_SEPARATOR);
		if (! _dictionary.containsKey(key)) {
			key = StringUtils.join(new String[] {ASSOCIATION_PREFIX, TARGET_PREFIX, association_name}, KEY_SEPARATOR);
		}
		
		return getDictionaryValue(key);
	}

	public String resolveTableAsAssociationName(String tableName) {
		return getDictionaryKey(tableName);
	}

	public String resolveTableAsClassName(String tableName) {
		return getDictionaryKey(tableName);
	}

	
	/**
	 * Gets the dictionary value.
	 * 
	 * @param key the key
	 * 
	 * @return the dictionary value
	 */
	protected String getDictionaryValue(String key) {
		String result = (String) _dictionary.get(key);
		
		if (result == null) {
			if (logger.isInfoEnabled())
				logger.info("Cannot find any translation for the key \"" + key + "\"");
		}
		return result;
	}
	

	/**
	 * Gets the dictionary key associated to the value.
	 * 
	 * @param key the value
	 * 
	 * @return the dictionary key
	 */
	protected String getDictionaryKey(String value) {
		String result =  _reverseNameDictionary.get(value);
		
		if (result == null) {
			if (logger.isInfoEnabled())
				logger.info("Cannot find any reverse translation for the value \"" + value + "\"");
		}
		return result;
	}

	
	private void _loadResource(Resource r) {
		Properties properties = new Properties();
		try {
			properties.load(r.getInputStream());
		} catch (IOException e) {
			logger.error(e);
		}
		
		for (Object property : properties.keySet()) {
			String key = (String) property;
			String value = (String) properties.getProperty(key);
			_dictionary.put(key, value);
		}
		
	}

	public void init() {
		if (_dictionary.isEmpty()) {
			logger.error("The database has no entry, which means that no \"synchronization-database-mapping.properties\" have been found");
			throw new AlfrescoRuntimeException("Unititialized database dictionary");
		}
		for (String key : _dictionary.keySet()) {
			if (key.startsWith(CLASS_NAME_PREFIX) || key.startsWith(ASSOCIATION_NAME_PREFIX)) {
				// If table or association name, then applies the renaming strategy
				String value = renamingStrategy.renameTable(_dictionary.get(key));
				
				if (_reverseNameDictionary.containsKey(value)) {
					//logger.error("Table name conflict detected on value \"" + value + "\"");
					throw new TableNameConflict(value);
				}
				_reverseNameDictionary.put(value, key);
				_dictionary.put(key, value);
			}			
		}
	}
	
	/*
	 * Spring IOC/DI material
	 */
		
	public void setResourcePattern(String resourcePattern) {
		try {
			Resource[] resources = _resolver.getResources(resourcePattern);
			for (Resource r : resources) {
				if (logger.isDebugEnabled())
					logger.debug("Loading resource " + r.getDescription());
				_loadResource(r);
			}
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	public void setRenamingStrategy(AbstractRenamingStrategy renamingStrategy_) {
		renamingStrategy = renamingStrategy_;
	}


}
