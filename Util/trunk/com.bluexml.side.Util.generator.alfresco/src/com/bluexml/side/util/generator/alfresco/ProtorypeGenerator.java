package com.bluexml.side.util.generator.alfresco;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.generator.acceleo.MergeUtil;
import com.bluexml.side.util.generator.alfresco.AlfrescoProjectUtil.ModuleType;
import com.bluexml.side.util.libs.IFileHelper;

public abstract class ProtorypeGenerator extends AbstractAlfrescoGenerator {

	File ampAlfresco = null;
	File zipShare = null;
	File currentProject = null;

	private File getCurrentProject() {
		if (currentProject == null) {
			currentProject = new File(IFileHelper.getSystemFolderPath(getTemporaryFolder()));
		}
		return currentProject;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.bluexml.side.util.generator.acceleo.AbstractAcceleoGenerator#generate
	 * (org.eclipse.core.resources.IFile)
	 */
	@Override
	public Collection<IFile> generate(IFile model) throws Exception {
		// get projectProperties to generate maven projects

		File currentProject = getCurrentProject();
		AlfrescoProjectUtil alfPUtil = new AlfrescoProjectUtil(currentProject);

		Properties props = buildModuleProperties(MergeUtil.getRootNameForModel(model, this.groupedModels));

		String artifactId = props.getProperty(MODULE_ID);
		String version = getVersioNumber();

		String groupId = "side.generator.alfresco";
		String projectName_ = "test project";
		String artifactIdAmp = artifactId + "Alf";
		alfPUtil.createAMPProject(artifactIdAmp, groupId, version, projectName_);
		ampAlfresco = new File(currentProject, artifactIdAmp);

		String groupId2 = "side.generator.alfresco.share";
		String projectName_2 = "project 2";
		String artifactIdShare = artifactId + "Share";
		alfPUtil.createSHAREProject(artifactIdShare, groupId2, version, projectName_2);
		zipShare = new File(currentProject, artifactIdShare);

		return super.generate(model);
	}

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.util.generator.alfresco.AbstractAlfrescoGenerator#
	 * buildPackages(java.lang.String)
	 */
	@Override
	public Collection<IFile> buildPackages(String modelId) throws Exception {
		File currentProject = getCurrentProject();
		AlfrescoProjectUtil alfPUtil = new AlfrescoProjectUtil(currentProject);

		Properties props = buildModuleProperties(modelId);

		ArrayList<IFile> packages = new ArrayList<IFile>();
		String artifactId = props.getProperty(MODULE_ID);
		String version = getVersioNumber();

		String artifactIdAmp = artifactId + "Alf";
		File file = alfPUtil.mavenPackage(ampAlfresco, artifactIdAmp, version, ModuleType.AMP);

		packages.add(IFileHelper.getIFile(file));

		String artifactIdShare = artifactId + "Share";
		File fileShare = alfPUtil.mavenPackage(zipShare, artifactIdShare, version, ModuleType.SHARE);

		packages.add(IFileHelper.getIFile(fileShare));

		return packages;
	}
}
