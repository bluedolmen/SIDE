/**
 * This class enables access to the Spring context in order to access instanciated beans 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author davidchevrier
 *
 */
public class SpringContext implements ApplicationContextAware {
	
	private static ApplicationContext ctx;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ctx = applicationContext;
	}
	
	/**
	 * 
	 * @return the Spring context application
	 */
	public static ApplicationContext getContext(){ 
		return ctx; 
	}

}
