package com.bluexml.alfresco.modules.sql.synchronisation;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * The Class DatabaseDictionary.
 */
public class DatabaseDictionary {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(DatabaseDictionary.class);
	
	/** The Constant FILE_PATH_SQLMAPPING_PROPERTIES. */
	private static final String FILE_PATH_SQLMAPPING_PROPERTIES = "bluexml/sql_mapping.properties";
	
	/** The Constant CLASS_PREFIX. */
	private static final String CLASS_PREFIX = "class";
	
	/** The Constant ATTRIBUTE_PREFIX. */
	private static final String ATTRIBUTE_PREFIX = "attribute";
	
	/** The Constant ASSOCIATION_PREFIX. */
	private static final String ASSOCIATION_PREFIX = "association";
	
	/** The Constant LEFT_PREFIX. */
	private static final String LEFT_PREFIX = "left";
	
	/** The Constant RIGHT_PREFIX. */
	private static final String RIGHT_PREFIX = "right";
	
	/** The Constant MIDDLE_PREFIX. */
	private static final String MIDDLE_PREFIX = "middle";
	
	/** The Constant NAME_PREFIX. */
	private static final String NAME_PREFIX = "name";
	
	/** The Constant KEY_SEPARATOR. */
	private static final String KEY_SEPARATOR = ".";
	
	/** The Dictionary. */
	private Map<String, Object> Dictionary;
	
	/** The database_dictionary_. */
	private static DatabaseDictionary database_dictionary_ = null;
	
	/** The association classes cache. */
	private static Map<String, List<AssociationClassDefinitionBean>> associationClassesCache = new HashMap<String, List<AssociationClassDefinitionBean>>();
	
	/**
	 * Instantiates a new database dictionary.
	 */
	private DatabaseDictionary() {
		Dictionary = new HashMap<String, Object>();
		loadPropertiesFile(Dictionary);
	}
	
	/**
	 * Gets the database dictionary.
	 * 
	 * @return the database dictionary
	 */
	public static DatabaseDictionary getDatabaseDictionary() {
		if (database_dictionary_ == null) {
			database_dictionary_ = new DatabaseDictionary();
		}
		return database_dictionary_;
	}
	
	/**
	 * Resolve class name.
	 * 
	 * @param class_name the class_name
	 * 
	 * @return the string
	 */
	public String resolveClassName (String class_name) {
		Map<String, Object> classNameDictionary =
			getSubDictionary(getSubDictionary(Dictionary, CLASS_PREFIX), NAME_PREFIX);
		return getDictionaryValue(classNameDictionary, class_name);
	}
	
	/**
	 * Resolve attribute name.
	 * 
	 * @param attribute_name the attribute_name
	 * @param class_name the class_name
	 * 
	 * @return the string
	 */
	public String resolveAttributeName (String attribute_name, String class_name) {
		Map<String, Object> attributeNameDictionary = 
			getSubDictionary(getSubDictionary(getSubDictionary(Dictionary, CLASS_PREFIX), ATTRIBUTE_PREFIX),NAME_PREFIX);
		return getDictionaryValue(getSubDictionary(attributeNameDictionary, class_name), attribute_name);
	}
	
	/**
	 * Resolve association name.
	 * 
	 * @param association_name the association_name
	 * 
	 * @return the string
	 */
	public String resolveAssociationName (String association_name) {
		Map<String, Object> associationNameDictionary = 
			getSubDictionary(getSubDictionary(Dictionary, ASSOCIATION_PREFIX), NAME_PREFIX);
		return getDictionaryValue(associationNameDictionary, association_name);
	}
	
	/**
	 * Gets the left class.
	 * 
	 * @param association_name the association_name
	 * 
	 * @return the left class
	 */
	public String getLeftClass (String association_name) {
		Map<String, Object> leftDictionary = 
			getSubDictionary(getSubDictionary(Dictionary, ASSOCIATION_PREFIX), LEFT_PREFIX);
		return getDictionaryValue(leftDictionary, association_name);
	}
	
	/**
	 * Gets the right class.
	 * 
	 * @param association_name the association_name
	 * 
	 * @return the right class
	 */
	public String getRightClass (String association_name) {
		Map<String, Object> rightDictionary = 
			getSubDictionary(getSubDictionary(Dictionary, ASSOCIATION_PREFIX), RIGHT_PREFIX);
		return getDictionaryValue(rightDictionary, association_name);
	}
	
	/**
	 * Gets the middle class.
	 * 
	 * @param association_name the association_name
	 * 
	 * @return the middle class
	 */
	public String getMiddleClass (String association_name) {
		Map<String, Object> middleDictionary = 
			getSubDictionary(getSubDictionary(Dictionary, ASSOCIATION_PREFIX), MIDDLE_PREFIX);
		return getDictionaryValue(middleDictionary, association_name);
	}
		
	/**
	 * Gets the dictionary value.
	 * 
	 * @param map_ the map_
	 * @param key the key
	 * 
	 * @return the dictionary value
	 */
	protected static String getDictionaryValue(Map<String, Object> map_, String key) {
		String result = null;
		Object o = map_.get(key);
		if (!(o instanceof String)) {
			logger.error("[getDictionaryValue] Problem getting the value (key = " + key + "), the pointed key is not a String !");
		} else {
			result = (String) o;
		}
		return result;
	}
	
	/**
	 * Gets the sub dictionary.
	 * 
	 * @param map_ the map_
	 * @param key the key
	 * 
	 * @return the sub dictionary
	 */
	@SuppressWarnings("unchecked")
	protected static Map<String, Object> getSubDictionary(Map<String, Object> map_, String key) {
		Map<String, Object> result = null;
		if (map_.containsKey(key)) {
			Object o = map_.get(key);
			if (o instanceof String) {
				logger.error("[DatabaseDictionary.getSubDictionary] Problem getting the subdictionary (key = " + key +"), the pointed key is a String!");
			} else {
				result = (Map<String, Object>) o;
			}
		} else {
			logger.warn("[DatabaseDictionary.getSubDictionary] Problem getting the subdictionary (key = " + key +"), the key does not exist!");
			// The key cannot be found, return an empty map
			//result = new HashMap<String, Object>();
		}
		return result;
	}
	
	
	/**
	 * Load properties file.
	 * 
	 * @param dict_ the dict_
	 */
	protected static void loadPropertiesFile(Map<String, Object> dict_) {
		Properties properties = null;
		InputStream myStream = null;
		
		myStream = SQLSynchronisationPolicy.class.getClassLoader().getResourceAsStream(FILE_PATH_SQLMAPPING_PROPERTIES);
		if (myStream != null) {
			properties = new Properties();
			try {
				properties.load(myStream);
				myStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			parseProperties(properties, dict_);
		}
	}
	
	/**
	 * Parses the properties.
	 * 
	 * @param properties the properties
	 * @param dict_ the dict_
	 */
	protected static void parseProperties(Properties properties, Map<String, Object> dict_) {
		for (Object key : properties.keySet() ) {
			String property_name = (String) key;
			parseProperty(property_name, properties.getProperty(property_name), dict_);
		}
	}
	
	
	/**
	 * Parses the property.
	 * 
	 * @param property_name the property_name
	 * @param value the value
	 * @param dict_ the dict_
	 */
	private static void parseProperty(String property_name, String value, Map<String, Object> dict_) {
		int indexOfSeparator = property_name.indexOf(KEY_SEPARATOR);
		if (indexOfSeparator == -1) {
			dict_.put(property_name, value);
		} else {
			String key = property_name.substring(0,indexOfSeparator);
			String remaining = property_name.substring(indexOfSeparator+1,property_name.length());
			
			if (! dict_.containsKey(key)) {
				dict_.put(key, new HashMap<Object, Object>());
			}
			parseProperty(remaining, value, getSubDictionary(dict_, key));
		}
	}



	/**
	 * The Enum Position.
	 */
	protected static enum Position {

		/** The left. */
		left,
		/** The middle. */
		middle,
		/** The right. */
		right;
	}

	/**
	 * The Class AssociationClassDefinitionBean.
	 */
	protected static class AssociationClassDefinitionBean {

		/** The position. */
		protected Position position;
		
		/** The association name. */
		protected String associationName;

		/**
		 * Instantiates a new association class definition bean.
		 * 
		 * @param position the position
		 * @param associationName the association name
		 */
		public AssociationClassDefinitionBean(Position position,
				String associationName) {
			super();
			this.position = position;
			this.associationName = associationName;
		}

	}
	
}
