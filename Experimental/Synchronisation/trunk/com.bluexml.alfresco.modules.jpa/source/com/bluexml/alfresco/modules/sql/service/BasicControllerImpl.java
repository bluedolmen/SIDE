package com.bluexml.alfresco.modules.sql.service;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.util.WebUtils;

import com.bluexml.alfresco.modules.sql.dao.BasicDAO;

public class BasicControllerImpl implements BasicController {

	private Logger _logger = Logger.getLogger(getClass());
	
	public void persist(Object o) {
		basicDAO.persist(o);
	}
	
	public void merge(Object o) {
		basicDAO.merge(o);
	}

	public List<Object> query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}

	public void remove(Class clazz, Long id) {
		Object instance = basicDAO.getById(clazz, id); // Needs to attach the instance before removing !
		basicDAO.remove(instance);
	}

	public <T> T getById(Class<T> clazz, Long id) {
		return basicDAO.getById(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public void addTarget(Class sourceClass, Long sourceId, Class targetClass, Long targetId, String targetAssociationName) {
		String methodName = "get" + targetAssociationName.substring(0, 1).toUpperCase() + targetAssociationName.substring(1, targetAssociationName.length());

		Object sourceInstance = getById(sourceClass, sourceId);
		Object targetInstance = getById(targetClass, targetId);
		
		Class[] attributes = new Class[0];
		Object methodReturn = null;
		
		try {
			Method method = sourceClass.getMethod(methodName, attributes);
			methodReturn = method.invoke(sourceInstance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (methodReturn instanceof List) {
			// Multiple cardinality of the association, we just add the target instance to the list
			List<Object> list = (List<Object>) methodReturn;
			list.add(targetInstance);
		} else {
			// Simple cardinality (OneToOne or ManyToOne)
			// methodReturn should be null, or there is a problem (previous validation should ensure that it cannot happen)
			if (methodReturn != null) {
				_logger.error(
						"Error during adding instance target (" + sourceClass.getCanonicalName() + "," + sourceId.toString() + ")" + 
						" to association with name \"" + targetAssociationName + "\"" +
						" of source instance (" + targetClass.getCanonicalName() + "," + targetId.toString() + "):" +
						" A previous association is existing!");
			}
			
			// We call the set method
			methodName = "set" + targetAssociationName.substring(0, 1).toUpperCase() + targetAssociationName.substring(1, targetAssociationName.length());
			attributes = new Class[1];
			attributes[0] = targetClass;
			try {
				Method method = sourceClass.getMethod(methodName, attributes);
				method.invoke(sourceInstance, targetInstance); // no return on setters
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		basicDAO.merge(sourceInstance);
		
	}

	public void removeTarget(Class sourceClass, Long sourceId, Class targetClass, Long targetId, String targetAssociationName) {
		String methodName = "get" + targetAssociationName.substring(0, 1).toUpperCase() + targetAssociationName.substring(1, targetAssociationName.length());

		Object sourceInstance = getById(sourceClass, sourceId);
		Object targetInstance = getById(targetClass, targetId);
		
		Class[] attributes = new Class[0];
		Object methodReturn = null;
		
		try {
			Method method = sourceClass.getMethod(methodName, attributes);
			methodReturn = method.invoke(sourceInstance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (methodReturn instanceof List) {
			// Multiple cardinality of the association, we just add the target instance to the list
			List<Object> list = (List<Object>) methodReturn;
			list.remove(targetInstance);
		} else {
			// Simple cardinality (OneToOne or ManyToOne)
			
			// TODO : Check wether setting a value to null is necessary
			// On xToOne associations, Alfresco seems to call update-properties
			
			// We call the set method
			methodName = "set" + targetAssociationName.substring(0, 1).toUpperCase() + targetAssociationName.substring(1, targetAssociationName.length());
			attributes = new Class[1];
			attributes[0] = targetClass;
			try {
				Method method = sourceClass.getMethod(methodName, attributes);
				targetInstance = null;
				method.invoke(sourceInstance, targetInstance); // no return on setters
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		basicDAO.merge(sourceInstance);
	}

	
	// Spring IOC material

	private BasicDAO basicDAO;

	public void setBasicDAO(BasicDAO basicDAO) {
		this.basicDAO = basicDAO;
	}





}