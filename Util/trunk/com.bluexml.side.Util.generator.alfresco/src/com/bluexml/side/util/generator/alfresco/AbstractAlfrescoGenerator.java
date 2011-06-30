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
