package com.bluexml.side.application.generator.alfresco;

import java.io.File;
import java.util.Properties;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.application.deployer.alfresco.AMPDeployer;
import com.bluexml.side.application.deployer.alfresco.Packager;
import com.bluexml.side.application.generator.acceleo.AbstractAcceleoGenerator;
import com.bluexml.side.util.libs.IFileHelper;

public abstract class AbstractAlfrescoGenerator extends AbstractAcceleoGenerator {

	public static String CONFIGURATION_PARAMETER_CATALINA_HOME = "CATALINA_HOME";
	protected Properties moduleProperties;
	protected IFile ampIFile = null;

	public Properties getModuleProperties() throws Exception {
		if (moduleProperties == null) {
			moduleProperties = buildModuleProperties();
		}
		return moduleProperties;
	}

	abstract public Properties buildModuleProperties();

	public IFile buildAMPPackage() throws Exception {
		Packager alfrescoPakager = new Packager(IFileHelper.getIFolder(getTemporaryFolder()), getModuleProperties());
		ampIFile = alfrescoPakager.buildAMP(generatedFiles);
		return ampIFile;
	}

	public void deploy() throws Exception {
		if (ampIFile == null) {
			throw new Exception("ampFile not found");
		}
		String cataHome = generationParameters.get(CONFIGURATION_PARAMETER_CATALINA_HOME);
		AMPDeployer.deploy(IFileHelper.getFile(ampIFile), new File(cataHome));
	}
}
