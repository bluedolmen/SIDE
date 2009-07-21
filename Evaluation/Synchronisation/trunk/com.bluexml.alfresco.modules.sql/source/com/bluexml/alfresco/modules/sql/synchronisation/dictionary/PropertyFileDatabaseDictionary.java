package com.bluexml.alfresco.modules.sql.synchronisation.dictionary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * The Class DatabaseDictionary.
 */
public class PropertyFileDatabaseDictionary implements DatabaseDictionary {
	
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
	
	/** The Dictionary. */
	private Map<String, String> _dictionary = new HashMap<String, String>();
		
		
	/* (non-Javadoc)
	 * @see com.bluexml.alfresco.modules.sql.synchronisation.dictionary.IDatabaseDictionary#resolveClassAsTableName(java.lang.String)
	 */
	public String resolveClassAsTableName (String class_name) {
		String key = StringUtils.join(new String[] {CLASS_PREFIX, NAME_PREFIX, class_name}, KEY_SEPARATOR);
		String tableName = getDictionaryValue(key);
		String tableRename = (tableName != null ? renamingStrategy.renameTable(tableName) : null); 
		return tableRename;
	}
	
	/* (non-Javadoc)
	 * @see com.bluexml.alfresco.modules.sql.synchronisation.dictionary.IDatabaseDictionary#resolveAssociationAsTableName(java.lang.String)
	 */
	public String resolveAssociationAsTableName (String association_name) {
		String key = StringUtils.join(new String[] {ASSOCIATION_PREFIX, NAME_PREFIX, association_name}, KEY_SEPARATOR);
		String tableName = getDictionaryValue(key);
		String tableRename = (tableName != null ? renamingStrategy.renameTable(tableName) : null); 
		return tableRename;
	}

	/* (non-Javadoc)
	 * @see com.bluexml.alfresco.modules.sql.synchronisation.dictionary.IDatabaseDictionary#resolveAttributeAsColumnName(java.lang.String, java.lang.String)
	 */
	public String resolveAttributeAsColumnName (String attribute_name, String class_name) {
		String key = StringUtils.join(new String[] {CLASS_PREFIX, ATTRIBUTE_PREFIX, NAME_PREFIX, class_name, attribute_name}, KEY_SEPARATOR);
		return getDictionaryValue(key);
	}
	
	
	/* (non-Javadoc)
	 * @see com.bluexml.alfresco.modules.sql.synchronisation.dictionary.IDatabaseDictionary#getSourceClass(java.lang.String)
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
	 * @see com.bluexml.alfresco.modules.sql.synchronisation.dictionary.IDatabaseDictionary#getSourceAlias(java.lang.String)
	 */
	public String getSourceAlias (String association_name) {
		String key = StringUtils.join(new String[] {ASSOCIATION_PREFIX, SOURCE_PREFIX, ALIAS_PREFIX, association_name}, KEY_SEPARATOR);
		if (! _dictionary.containsKey(key)) {
			key = StringUtils.join(new String[] {ASSOCIATION_PREFIX, SOURCE_PREFIX, association_name}, KEY_SEPARATOR);
		}
		
		return getDictionaryValue(key);
	}
	
	/* (non-Javadoc)
	 * @see com.bluexml.alfresco.modules.sql.synchronisation.dictionary.IDatabaseDictionary#getTargetClass(java.lang.String)
	 */
	public String getTargetClass (String association_name) {
		String key = StringUtils.join(new String[] {ASSOCIATION_PREFIX, TARGET_PREFIX, association_name}, KEY_SEPARATOR);
		return getDictionaryValue(key);
	}

	/* (non-Javadoc)
	 * @see com.bluexml.alfresco.modules.sql.synchronisation.dictionary.IDatabaseDictionary#getTargetAlias(java.lang.String)
	 */
	public String getTargetAlias (String association_name) {
		String key = StringUtils.join(new String[] {ASSOCIATION_PREFIX, TARGET_PREFIX, ALIAS_PREFIX, association_name}, KEY_SEPARATOR);
		if (! _dictionary.containsKey(key)) {
			key = StringUtils.join(new String[] {ASSOCIATION_PREFIX, TARGET_PREFIX, association_name}, KEY_SEPARATOR);
		}
		
		return getDictionaryValue(key);
	}

	
	/**
	 * Gets the dictionary value.
	 * 
	 * @param key the key
	 * 
	 * @return the dictionary value
	 */
	protected String getDictionaryValue(String key) {
		String result = _dictionary.get(key);
		
		if (result == null) {
			logger.debug("Cannot find any translation for the key \"" + key + "\"");
		}
		return result;
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
	
	public void setRenamingStrategy(AbstractRenamingStrategy renamingStrategy_) {
		renamingStrategy = renamingStrategy_;
	}

}
