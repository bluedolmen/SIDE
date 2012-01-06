package com.bluexml.side.Framework.alfresco.share.formExtension;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.extensions.config.ConfigBootstrap;
import org.springframework.extensions.config.ConfigDeployment;
import org.springframework.extensions.config.source.UrlConfigSource;

public class MyConfigBootStrap extends ConfigBootstrap {
	static Log logger = LogFactory.getLog(MyConfigBootStrap.class);
	UrlConfigSource urlConfigSource = null;

	public UrlConfigSource getUrlConfigSource() {
		return urlConfigSource;
	}

	public void setUrlConfigSource(UrlConfigSource urlConfigSource) {
		this.urlConfigSource = urlConfigSource;
	}

	public List<ConfigDeployment> initConfig() {
		if (logger.isDebugEnabled()) {
			logger.debug("MyConfigBootStrap.initConfig()");
		}
		return configService.appendConfig(urlConfigSource);
	}
}
