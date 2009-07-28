package com.bluexml.alfresco.modules.sql.service;

import java.util.List;

public interface BasicController {
	
	public void persist(Object o);
	
	public void merge(Object o);
	
	public void remove(Class clazz, Long id);
	
	public <T> T getById(Class<T> clazz, Long id);
	
	public void addTarget(Class sourceClass, Long sourceId, Class targetClass, Long targetId, String targetAssociationName);
	
	public void removeTarget(Class sourceClass, Long sourceId, Class targetClass, Long targetId, String targetAssociationName);
	
	public abstract List<Object> query(String queryString, Object... params);
	
}