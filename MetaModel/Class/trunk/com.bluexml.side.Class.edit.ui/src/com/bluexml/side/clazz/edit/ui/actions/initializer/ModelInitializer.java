/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
