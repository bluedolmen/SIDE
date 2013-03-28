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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.application.Application;
import com.bluexml.side.application.ApplicationFactory;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.clazz.edit.ui.actions.initializer.creator.ApplicationCreator;
import com.bluexml.side.util.libs.ecore.EResourceUtils;

public class ApplicationModelInitializer extends ModelUpdater {
	private static ApplicationFactory FACTORY = ApplicationFactory.eINSTANCE;
	private static final String APPLICATION_EDITOR_ID = "com.bluexml.side.application.presentation.ApplicationEditorID"; //$NON-NLS-1$
	private ApplicationCreator creator;

	public ApplicationModelInitializer(IFile classModel, EObject root, InitializerRegister register, ASK_USER ask, String fileName, String alfrescoVersion, String alfrescoHome) throws IOException {
		super(classModel, root, ModelInitializationUtils.getExtensionForEditorId(APPLICATION_EDITOR_ID), "application", register, ask, fileName); //$NON-NLS-1$
		List<IFile> initializedModels = new ArrayList<IFile>();
		// add classModel
		initializedModels.add(classModel);
		this.creator = new ApplicationCreator(project, register, ask, fileName, alfrescoVersion, alfrescoHome, initializedModels);
		dependencies.add(ViewModelInitializer.class);
		dependencies.add(PortalModelInitializer.class);
		dependencies.add(FormModelInitializer.class);
		dependencies.add(FormWorkflowModelInitializer.class);
	}

	public ApplicationModelInitializer(IFile classModel, InitializerRegister register, ASK_USER ask, String formModelFileName, String alfrescoVersion, String alfrescoHome) throws IOException {
		this(classModel, EResourceUtils.openModel(classModel).get(0), register, ask, formModelFileName, alfrescoVersion, alfrescoHome);
	}

	@Override
	protected void headLessInitialize() throws Exception {
		Application app = (Application) newRootObject;
		app.setName(getModelName());

		// create models elements
		updateModelsElements(app);

		if (app.getConfiguration(creator.getConfigurationName()) == null) {
			// create new configuration
			creator.createConfiguration(app);
		}

	}

	@Override
	protected Command initialize(EditingDomain editingDomain) throws Exception {
		CompoundCommand cc = new CompoundCommand();
		headLessInitialize();
		return cc;
	}

	@Override
	protected void createRootObject() {
		// create new application
		newRootObject = FACTORY.createApplication();
	}

	@Override
	protected void headLessUpdate() throws Exception {
		// root element must be loaded before
		Application app = (Application) newRootObject;

		// search for default configuration, if not found we create it
		Configuration configuration = app.getConfiguration(creator.getConfigurationName());
		if (configuration == null) {
			creator.createConfiguration(app);
		}

		// update models elements
		updateModelsElements(app);
	}

	@Override
	protected Command update(EditingDomain editingDomain) throws Exception {
		CompoundCommand cc = new CompoundCommand();
		headLessUpdate();
		return cc;
	}

	private void updateModelsElements(Application app) {
		creator.updateModelsElements(app);
	}

}
