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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.clazz.ClassPackage;

public abstract class ModelAndDiagramInitializer extends ModelInitializer {
	protected String diagramTypeId;
	protected EObject rootDiagram;
	protected boolean diagramInitialized = false;

	public ModelAndDiagramInitializer(IFile classModel, String newModelExt, String modelTypeSegment, InitializerRegister register, ASK_USER ask, String formModelFileName) throws IOException {
		super(classModel, newModelExt, modelTypeSegment, register, ask, formModelFileName);
	}

	public ModelAndDiagramInitializer(IFile classModel, ClassPackage root, String newModelExt, String modelTypeSegment, String diagramTypeId, InitializerRegister register, ASK_USER ask, String formModelFileName) throws IOException {
		super(classModel, root, newModelExt, modelTypeSegment, register, ask, formModelFileName);
		this.diagramTypeId = diagramTypeId;
	}

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.clazz.edit.ui.actions.initializer.ModelInitializer#
	 * initialize()
	 */
	@Override
	public void initialize() throws Exception {
		super.initialize();
		if (!headless && !diagramInitialized) {
			if (rootDiagram == null) {
				rootDiagram = newRootObject;
			}
			ModelInitializationUtils.createDiagramFromExistingModel(rootDiagram, diagramTypeId);
			diagramInitialized = true;
		}
	}	

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.clazz.edit.ui.actions.initializer.ModelInitializer#
	 * postInitialization(org.eclipse.core.resources.IFile,
	 * org.eclipse.ui.IWorkbenchPage, org.eclipse.ui.IEditorDescriptor,
	 * org.eclipse.ui.IEditorPart)
	 */
	@Override
	protected void postInitialization(IFile newModelFile, IWorkbenchPage page, IEditorDescriptor desc, IEditorPart editorPart) throws Exception {
		page.closeEditor(editorPart, false);
		// save initialized resource
		saveNewModel();
	}

}
