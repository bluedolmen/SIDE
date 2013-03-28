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
package com.bluexml.side.util.generator.alfresco;

import java.io.File;
import java.util.Collection;
import java.util.Properties;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.generator.acceleo.AbstractAcceleoPackageGenerator;
import com.bluexml.side.util.libs.IFileHelper;

public abstract class AbstractAlfrescoGenerator extends AbstractAcceleoPackageGenerator {

	public static final String CONFIGURATION_PARAMETER_CATALINA_HOME = "CATALINA_HOME";
	public static final String CONFIGURATION_PARAMETER_ALFRESCO_URL = "alfresco.url";
	public static final String CONFIGURATION_PARAMETER_ALFRESCOSHARE_URL = "alfresco.share.url";

	protected Properties moduleProperties;
	public static final String MODULE_DESCRIPTION = "module.description";
	public static final String MODULE_TITLE = "module.title";
	public static final String MODULE_VERSION = "module.version";
	public static final String MODULE_ID = "module.id";

	public String getTEMP_FOLDER(String model) {
		return getTEMP_FOLDER() + File.separator + model;
	}

	abstract public Properties buildModuleProperties(String modelId);

	@Override
	public Collection<IFile> buildPackages(String modelId) throws Exception {
		AlfrescoPackager alfrescoPackager = new AlfrescoPackager(IFileHelper.getIFolder(getTemporaryFolder()), buildModuleProperties(modelId), techVersion);
		Collection<IFile> pkgs = alfrescoPackager.buildPackages().values();
		return pkgs;
	}

}
