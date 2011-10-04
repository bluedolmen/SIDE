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
		this(classModel, ModelInitializationUtils.openModel(classModel).get(0), register, ask, formModelFileName, alfrescoVersion, alfrescoHome);
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
