package com.bluexml.side.Framework.alfresco.hotdeployer;

import java.util.List;

import org.alfresco.error.AlfrescoRuntimeException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.extensions.config.ConfigDeployer;
import org.springframework.extensions.config.ConfigDeployment;
import org.springframework.extensions.config.ConfigService;
import org.springframework.extensions.config.source.UrlConfigSource;
/**
 * SIDE Extension
 * Extends Original Alfresco Surf code
 * @author davidabad
 *
 */
public class WebClientRepoBootstrap implements ApplicationContextAware, BeanNameAware, ConfigDeployer {

	/** The bean name. */
	protected String beanName;

	/** The application context */
	protected ApplicationContext applicationContext;

	/** Dependency */
	protected ConfigService configService;

	protected UrlConfigSource configSource;

	public WebClientRepoBootstrap(UrlConfigSource configSource) {
		this.configSource = configSource;
	}

	/**
	 * @deprecated
	 */
	public void init() {
		// TODO - see JIRA Task AR-1715 - refactor calling modules to inject webClientConfigService, and use init-method="register" directly 
		// (instead of init-method="init"). Can then remove applicationContext and no longer implement ApplicationContextAware

		if (this.applicationContext.containsBean("webClientConfigService") == true) {
			ConfigService configService = (ConfigService) this.applicationContext.getBean("webClientConfigService");
			if (configService != null) {
				setConfigService(configService);
				register();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.beans.factory.BeanNameAware#setBeanName(java.lang
	 * .String)
	 */
	public void setBeanName(String name) {
		this.beanName = name;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

	/*
	 * (non-Javadoc)
	 * @see org.alfresco.config.ConfigDeployer#getSortKey()
	 */
	public String getSortKey() {
		return this.beanName;
	}

	public void register() {
		if (configService == null) {
			throw new AlfrescoRuntimeException("Config service must be provided");
		}

		configService.addDeployer(this);
	}

	/**
	 * Initialisation method
	 */
	public List<ConfigDeployment> initConfig() {
		if (configService != null) {
			return configService.appendConfig(configSource);
		}

		return null;
	}

}
