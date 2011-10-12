package com.bluexml.side.Framework.alfresco.share.formExtension;

import java.util.List;

import org.springframework.extensions.config.ConfigBootstrap;
import org.springframework.extensions.config.ConfigDeployment;
import org.springframework.extensions.config.source.UrlConfigSource;

public class MyConfigBootStrap extends ConfigBootstrap {

	UrlConfigSource urlConfigSource = null;

	public UrlConfigSource getUrlConfigSource() {
		return urlConfigSource;
	}

	public void setUrlConfigSource(UrlConfigSource urlConfigSource) {
		this.urlConfigSource = urlConfigSource;
	}

	public List<ConfigDeployment> initConfig() {
		System.out.println("MyConfigBootStrap.initConfig()");
		return configService.appendConfig(urlConfigSource);
	}
}
