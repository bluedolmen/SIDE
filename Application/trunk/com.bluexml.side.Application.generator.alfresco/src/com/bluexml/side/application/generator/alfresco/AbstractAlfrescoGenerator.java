package com.bluexml.side.application.generator.alfresco;

import java.util.Properties;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.application.generator.acceleo.AbstractAcceleoGenerator;
import com.bluexml.side.deployer.alfresco.Packager;
import com.bluexml.side.util.libs.IFileHelper;

public abstract class AbstractAlfrescoGenerator extends AbstractAcceleoGenerator {

	public static String CONFIGURATION_PARAMETER_CATALINA_HOME = "CATALINA_HOME";
	protected Properties moduleProperties;

	public Properties getModuleProperties() throws Exception {
		if (moduleProperties == null) {
			moduleProperties = buildModuleProperties();
		}
		return moduleProperties;
	}

	abstract public Properties buildModuleProperties();

	public IFile buildAMPPackage() throws Exception {
		Packager alfrescoPakager = new Packager(IFileHelper.getIFolder(getTemporaryFolder()), getModuleProperties());
		IFile ampIFile = alfrescoPakager.buildAMP(generatedFiles);
		return ampIFile;
	}
}
