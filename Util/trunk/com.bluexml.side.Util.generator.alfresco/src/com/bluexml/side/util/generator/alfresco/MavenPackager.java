package com.bluexml.side.util.generator.alfresco;

import java.io.File;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import com.bluexml.side.util.generator.alfresco.AlfrescoProjectUtil.ModuleType;
import com.bluexml.side.util.generator.packager.AbstractPackager;
import com.bluexml.side.util.libs.IFileHelper;

public class MavenPackager extends AbstractPackager {

	private Properties moduleProperties;
	File currentProject = null;

	public MavenPackager(IFolder folder, IFolder ItechnoVPath, Properties moduleProperties) {
		super(folder, ItechnoVPath);
		this.moduleProperties = moduleProperties;
	}

	@Override
	public IFile buildPackage() throws Exception {
		AlfrescoProjectUtil alfPUtil = new AlfrescoProjectUtil(currentProject);
		String artifactId = moduleProperties.getProperty(AbstractAlfrescoGenerator.MODULE_ID);
		String version = moduleProperties.getProperty(AbstractAlfrescoGenerator.MODULE_VERSION);

		String artifactIdAmp = artifactId + "Alf";
		// run mvn package
		File file = alfPUtil.mavenPackage(new File(workingdir), artifactIdAmp, version, ModuleType.AMP);

		// convert to IFile		
		
		return IFileHelper.getIFile(file);
	}

	@Override
	protected File getFolderToPackage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPackageFileName() {
		// TODO Auto-generated method stub
		return null;
	}

}
