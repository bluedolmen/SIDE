package com.bluexml.side.util.generator.alfresco;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.generator.acceleo.AbstractAcceleoGenerator;
import com.bluexml.side.util.libs.IFileHelper;

public abstract class AbstractAlfrescoGenerator extends AbstractAcceleoGenerator {

	public static final String CONFIGURATION_PARAMETER_CATALINA_HOME = "CATALINA_HOME";
	protected Properties moduleProperties;
	protected IFile ampIFile = null;

	

	public String getTEMP_FOLDER(String model) {
		return getTEMP_FOLDER() + File.separator + model;
	}

	abstract public Properties buildModuleProperties(String modelId);

	public IFile buildPackage(String modelId) throws Exception {
		Packager alfrescoPakager = new Packager(IFileHelper.getIFolder(getTemporaryFolder()), buildModuleProperties(modelId), techVersion);
		ampIFile = alfrescoPakager.buildAMP(null, doClean());
		return ampIFile;
	}

	@Override
	protected String getMetamodelURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<String> getTemplates() {
		// TODO Auto-generated method stub
		return null;
	}	

}
