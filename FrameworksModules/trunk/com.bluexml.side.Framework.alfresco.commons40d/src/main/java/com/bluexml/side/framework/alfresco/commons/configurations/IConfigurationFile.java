package com.bluexml.side.framework.alfresco.commons.configurations;

import java.util.Map;
/**
 * SIDE Extension
 * 
 * @author davidabad
 */
public interface IConfigurationFile<K, V> {

	/**
	 * get the value for the given key
	 * 
	 * @param key
	 * @return
	 */
	public abstract V getValue(K key);

	/**
	 * true if value exists for this key
	 * 
	 * @param key
	 * @return
	 */
	public abstract boolean hasValue(K key);

	/**
	 * get read only access to dictionary Map
	 * 
	 * @return
	 */
	public abstract Map<K, V> getDictionary();

}