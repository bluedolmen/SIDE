package com.bluexml.side.clazz.edit.ui.actions.initializer;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.core.resources.IFile;

import com.bluexml.side.clazz.alfresco.reverse.library.ModelLibrary;
import com.bluexml.side.util.libs.IFileHelper;

public class AntInitializerTask extends Task {
	File tomcatHome;
	String model;

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	ModelLibrary.Libraries alfrescoVersion;

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
	 * @return the alfrescoVersion
	 */
	public ModelLibrary.Libraries getAlfrescoVersion() {
		return alfrescoVersion;
	}

	/**
	 * @param alfrescoVersion
	 *            the alfrescoVersion to set
	 */
	public void setAlfrescoVersion(ModelLibrary.Libraries alfrescoVersion) {
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
			IFile classModelIfile = IFileHelper.getIFile(model);
			InitializerRegister initializerRegister = InitializerRegister.getInitializerRegister(classModelIfile, tomcatHome.getAbsolutePath(), alfrescoVersion.toString());
			initializerRegister.initialize();
		} catch (Exception e) {
			throw new BuildException(e);
		}
	}

}
