/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.Framework.alfresco.share.formExtension;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.extensions.config.ConfigBootstrap;
import org.springframework.extensions.config.ConfigDeployment;
import org.springframework.extensions.config.source.UrlConfigSource;
/**
 * SIDE Extension
 * Extends Original Alfresco code
 * This allow to define additional path where to find configuration
 * @author davidabad
 *
 */
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
