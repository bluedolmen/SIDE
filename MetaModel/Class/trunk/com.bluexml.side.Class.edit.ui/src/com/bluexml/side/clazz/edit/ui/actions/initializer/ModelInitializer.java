package com.bluexml.side.clazz.edit.ui.actions.initializer;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.util.libs.eclipse.ProjectNatureHelper;

public abstract class ModelInitializer extends ModelCreator {

	protected final IFile classModel;
	protected final EObject root;

	protected String inputModelExt;

	protected ModelInitializer(IFile classModel, String newModelExt, String modelTypeSegment, InitializerRegister register, ASK_USER ask, String formModelFileName) throws IOException {
		this(classModel, openModel(classModel), newModelExt, modelTypeSegment, register, ask, formModelFileName);
	}

	protected ModelInitializer(IFile classModel, EObject root, String newModelExt, String modelTypeSegment, InitializerRegister register, ASK_USER ask, String formModelFileName) throws IOException {
		super(classModel.getProject(), newModelExt, modelTypeSegment, register, ask);
		this.classModel = classModel;
		this.inputModelExt = "." + classModel.getFullPath().getFileExtension();
		this.root = root;
		this.ask = ask;
		this.newModelPath = getNewModelPath(formModelFileName);
	}

	@Override
	protected IPath getNewModelPath(String formModelFileName) {
		// get folder where to create new model
		IPath newModelParentPath;
		if (ProjectNatureHelper.hasNature(project, NATURE_ID) || ProjectNatureHelper.hasNature(project, NATURE_WithBuilder_ID)) {
			// side layout
			// get models path			
			newModelParentPath = project.getFullPath().append("src").append("models").append(modelTypeSegment);
		} else {
			newModelParentPath = classModel.getRawLocation().removeLastSegments(1);
		}

		// get new Model path
		if (formModelFileName == null) {
			formModelFileName = classModel.getName();
			formModelFileName = formModelFileName.replaceAll(inputModelExt, newModelExt);
		}
		return newModelParentPath.append(formModelFileName);
	}

}
