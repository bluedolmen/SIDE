package com.bluexml.side.clazz.edit.ui.actions.initializer;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

public abstract class ModelUpdater extends ModelInitializer {

	

	protected ModelUpdater(IFile classModel, EObject root, String newModelExt, String modelTypeSegment, InitializerRegister register, ASK_USER ask, String formModelFileName) throws IOException {
		super(classModel, root, newModelExt, modelTypeSegment, register, ask, formModelFileName);
		// TODO Auto-generated constructor stub
	}

	
	public ModelUpdater(IFile classModel, String newModelExt, String modelTypeSegment, InitializerRegister register, ASK_USER ask, String formModelFileName) throws IOException {
		super(classModel, newModelExt, modelTypeSegment, register, ask, formModelFileName);
		// TODO Auto-generated constructor stub
	}


	protected abstract Command update(EditingDomain editingDomain) throws Exception;

	protected abstract void headLessUpdate() throws Exception;

	protected void loadTargetModel() throws Exception {
		newRootObject = openModel(getNewModelIFile());
	}

}
