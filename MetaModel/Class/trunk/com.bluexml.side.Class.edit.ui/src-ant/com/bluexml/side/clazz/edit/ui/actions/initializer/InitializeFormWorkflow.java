package com.bluexml.side.clazz.edit.ui.actions.initializer;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.libs.IFileHelper;

public class InitializeFormWorkflow extends Task {
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
		IFile ifile = IFileHelper.getIFile(model);
		try {
			InitializerRegister formWorkFlowInitializerRegister = InitializerRegister.getFormWorkFlowInitializerRegister(ifile, ModelInitializer.ASK_USER.OVERRIDE);
			formWorkFlowInitializerRegister.initialize();
		} catch (Exception e) {
			throw new BuildException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.apache.tools.ant.Task#init()
	 */
	@Override
	public void init() throws BuildException {
		super.init();
		log("init is never called !!!");
	}
	
	

}
