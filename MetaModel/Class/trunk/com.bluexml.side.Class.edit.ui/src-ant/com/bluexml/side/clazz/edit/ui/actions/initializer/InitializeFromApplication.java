package com.bluexml.side.clazz.edit.ui.actions.initializer;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.bluexml.side.util.libs.IFileHelper;

public class InitializeFromApplication extends Task {
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

	/*
	 * (non-Javadoc)
	 * @see org.apache.tools.ant.Task#execute()
	 */
	@Override
	public void execute() throws BuildException {
		IFile applicationModel = IFileHelper.getIFile(model);
		try {
			InitializerRegister initializerRegister = InitializerRegister.getInitializerRegister(applicationModel);
			initializerRegister.initialize();
			applicationModel.getProject().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BuildException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.tools.ant.Task#init()
	 */
	@Override
	public void init() throws BuildException {
		super.init();
		log("init is never called !!!");
	}

}
