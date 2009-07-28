package com.bluexml.alfresco.modules.sql.dao;

import java.util.List;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class BasicDAO extends JpaDaoSupport {
	
    public void persist(Object o) {
    	try {
    		getJpaTemplate().persist(o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void merge(Object o) {
    	try {
    		getJpaTemplate().merge(o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void refresh(Object o) {
    	try {
    		getJpaTemplate().refresh(o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
    public void remove(Object o) {
    	try {
    		getJpaTemplate().remove(o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public <T> T getById(Class<T> clazz, Long id) {
    	return getJpaTemplate().find(clazz, id);
    }
    
    @SuppressWarnings("unchecked")
	public List<Object> query(String queryString, final Object... params) {
	    return getJpaTemplate().find(queryString,params);
	}	

    
}