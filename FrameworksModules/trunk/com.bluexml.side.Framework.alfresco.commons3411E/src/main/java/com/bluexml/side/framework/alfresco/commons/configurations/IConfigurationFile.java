package com.bluexml.side.framework.alfresco.commons.configurations;

import java.util.Map;

public interface IConfigurationFile<K, V> {

	/**
	 * get the value for the given key
	 * 
	 * @param key
	 * @return
	 */
	public abstract V getValue(K key);
	
	/**
	 * get the value for the given key
	 * 
	 * @param key
	 * @param defaultValue, if the key do not exists return this value
	 * @return
	 */
	public abstract V getValue(K key,V defaultValue);

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