package com.bluexml.side.clazz.edit.ui.actions.initializer;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.core.resources.IFile;

import com.bluexml.side.clazz.edit.ui.actions.wizards.initializefromclass.pages.InitializerPageWelcome.AlfrescoVersions;
import com.bluexml.side.util.libs.IFileHelper;

public class AntInitializerTask extends Task {
	File tomcatHome;
	String classModelPath;
	AlfrescoVersions alfrescoVersion;

	/**
	 * @return the tomcatHome
	 */
	public File getTomcatHome() {
		return tomcatHome;
	}

	/**
	 * @param tomcatHome
	 *            the tomcatHome to set
	 */
	public void setTomcatHome(File tomcatHome) {
		this.tomcatHome = tomcatHome;
	}

	/**
	 * @return the classModelPath
	 */
	public String getClassModelPath() {
		return classModelPath;
	}

	/**
	 * @param classModelPath
	 *            the classModelPath to set
	 */
	public void setClassModelPath(String classModelPath) {
		this.classModelPath = classModelPath;
	}

	/**
	 * @return the alfrescoVersion
	 */
	public AlfrescoVersions getAlfrescoVersion() {
		return alfrescoVersion;
	}

	/**
	 * @param alfrescoVersion
	 *            the alfrescoVersion to set
	 */
	public void setAlfrescoVersion(AlfrescoVersions alfrescoVersion) {
		this.alfrescoVersion = alfrescoVersion;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.tools.ant.Task#execute()
	 */
	@Override
	public void execute() throws BuildException {
		if (!tomcatHome.exists() || tomcatHome.isFile()) {
			throw new BuildException("Bad tomcat home, please to give the absolute path");
		}
		try {
			IFile classModelIfile = IFileHelper.getIFile(classModelPath);
			InitializerRegister initializerRegister = InitializerRegister.getInitializerRegister(classModelIfile, tomcatHome.getAbsolutePath(), alfrescoVersion.toString());
			initializerRegister.initialize();
		} catch (Exception e) {
			throw new BuildException(e);
		}
	}
	
}
