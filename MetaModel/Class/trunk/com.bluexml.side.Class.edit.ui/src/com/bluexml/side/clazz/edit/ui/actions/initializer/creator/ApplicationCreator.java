package com.bluexml.side.clazz.edit.ui.actions.initializer.creator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.application.Application;
import com.bluexml.side.application.ApplicationFactory;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.DeployerConfiguration;
import com.bluexml.side.application.GeneratorConfiguration;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.ModelElement;
import com.bluexml.side.application.Option;
import com.bluexml.side.clazz.alfresco.reverse.library.ModelLibrary;
import com.bluexml.side.clazz.edit.ui.actions.initializer.FormModelInitializer;
import com.bluexml.side.clazz.edit.ui.actions.initializer.FormWorkflowModelInitializer;
import com.bluexml.side.clazz.edit.ui.actions.initializer.InitializerRegister;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelCreator;
import com.bluexml.side.clazz.edit.ui.actions.initializer.PortalModelInitializer;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ViewModelInitializer;
import com.bluexml.side.util.libs.IFileHelper;

public class ApplicationCreator extends ModelCreator {
	private static ApplicationFactory FACTORY = ApplicationFactory.eINSTANCE;
	private static final String APPLICATION_EDITOR_ID = "com.bluexml.side.application.presentation.ApplicationEditorID"; //$NON-NLS-1$
	private String alfrescoHome;
	private String alfrescoVersion;
	private List<IFile> externals = new ArrayList<IFile>();

	public ApplicationCreator(IProject project, InitializerRegister register, ASK_USER ask, String formModelFileName, String alfrescoVersion, String alfrescoHome) throws IOException {
		super(project, ModelInitializationUtils.getExtensionForExtensionId(APPLICATION_EDITOR_ID), "application", register, ask, formModelFileName);
		this.alfrescoVersion = alfrescoVersion;
		this.alfrescoHome = alfrescoHome;

		dependencies.add(ViewModelInitializer.class);
		dependencies.add(PortalModelInitializer.class);
		dependencies.add(FormModelInitializer.class);
		dependencies.add(FormWorkflowModelInitializer.class);
	}

	public ApplicationCreator(IProject project, InitializerRegister register, ASK_USER ask, String formModelFileName, String alfrescoVersion, String alfrescoHome, List<IFile> externals) throws IOException {
		this(project, register, ask, formModelFileName, alfrescoVersion, alfrescoHome);
		if (externals != null) {
			this.externals = externals;
		}
	}

	@Override
	protected void createRootObject() {
		newRootObject = FACTORY.createApplication();
	}

	@Override
	protected void headLessInitialize() throws Exception {
		Application app = (Application) newRootObject;
		app.setName(getModelName());

		// create models elements
		updateModelsElements(app);

		if (app.getConfiguration(getConfigurationName()) == null) {
			// create new configuration
			createConfiguration(app);
		}
	}

	@Override
	protected Command initialize(EditingDomain editingDomain) throws Exception {
		headLessInitialize();
		return new CompoundCommand();
	}

	public void createConfiguration(Application app) {
		Configuration conf = FACTORY.createConfiguration();
		// beware, ant build file use project name as default configuration so use project name as configuration here
		conf.setName(getConfigurationName());
		if (alfrescoHome != null) {
			createConfigurationParameter(conf, "Directory", "CATALINA_HOME", alfrescoHome); //$NON-NLS-1$ //$NON-NLS-2$
		}
		String projectName = getConfigurationName();

		createConfigurationParameters(conf, projectName);

		createGeneratorsConfigurations(conf);

		createDeployersConfigurations(conf);

		app.getElements().add(conf);
	}

	public String getConfigurationName() {
		return project.getName();
	}

	private void createConfigurationParameters(Configuration conf, String projectName) {
		createConfigurationParameter(conf, "", "generation.options.logPath", "/" + projectName + "/build/logs"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		createConfigurationParameter(conf, "", "generation.options.destinationPath", "/" + projectName + "/build/generated"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		createConfigurationParameter(conf, "", "generation.options.clean", "true"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	private void createDeployersConfigurations(Configuration conf) {
		if (alfrescoVersion == null) {
			System.out.println("no deployer configuration because of unknown alfresco version");
		} else if (alfrescoVersion.equals(ModelLibrary.Libraries.ALFRESCO_34D_CE.toString())) {
			List<String> options = new ArrayList<String>();
			options.add("com.bluexml.side.Deployer.alfrescoDirectCopy34d.clean"); //$NON-NLS-1$
			createDeployerConfiguration(conf, "com.bluexml.side.Deployer.alfrescoDirectCopy34d", options); //$NON-NLS-1$

		} else if (alfrescoVersion.equals(ModelLibrary.Libraries.ALFRESCO_33R2_CE.toString())) {
			//TODO add side component for 3.2R2 and make a fork to create 3.2R2 Portal model
			System.err.println("3.2R2 NOT YET IMPLEMENTED"); //$NON-NLS-1$
		}
	}

	private void createGeneratorsConfigurations(Configuration conf) {
		if (alfrescoVersion == null) {
			System.out.println("no generator configuration because of unknown alfresco version");
		} else if (alfrescoVersion.equals(ModelLibrary.Libraries.ALFRESCO_34D_CE.toString())) {
			List<String> options_class = new ArrayList<String>();
			options_class.add("alfresco.share.extension"); //$NON-NLS-1$
			createGeneratorConfiguration(conf, "com.bluexml.side.Class.generator.alfresco34d", options_class); //$NON-NLS-1$
			List<String> options_portal = new ArrayList<String>();
			options_portal.add("com.bluexml.side.Portal.generator.alfresco.forms"); //$NON-NLS-1$
			options_portal.add("com.bluexml.side.Portal.generator.alfresco.doclist"); //$NON-NLS-1$
			createGeneratorConfiguration(conf, "com.bluexml.side.Portal.generator.alfresco3.4d", options_portal); //$NON-NLS-1$
			createGeneratorConfiguration(conf, "com.bluexml.side.View.generator.alfresco 34d", null); //$NON-NLS-1$
			createGeneratorConfiguration(conf, "com.bluexml.side.Form.generator.alfresco34d", null); //$NON-NLS-1$
		} else if (alfrescoVersion.equals(ModelLibrary.Libraries.ALFRESCO_33R2_CE.toString())) {
			//TODO add side component for 3.2R2 and make a fork to create 3.2R2 Portal model
			System.err.println("3.2R2 NOT YET IMPLEMENTED"); //$NON-NLS-1$
		}
	}

	private void createGeneratorConfiguration(Configuration conf, String sideComponentId, List<String> options) {
		GeneratorConfiguration componentConf = FACTORY.createGeneratorConfiguration();
		// only set id because we let com.bluexml.side.application.ui.action.utils.ApplicationUtil.updateConfigurationFromExtensionPoint(Configuration) update the rest
		componentConf.setId(sideComponentId);

		if (options != null) {
			for (String string : options) {
				Option op = FACTORY.createOption();
				op.setKey(string);
				componentConf.getOptions().add(op);
			}
		}
		conf.getGeneratorConfigurations().add(componentConf);
	}

	private void createDeployerConfiguration(Configuration conf, String sideComponentId, List<String> options) {
		DeployerConfiguration componentConf = FACTORY.createDeployerConfiguration();
		// only set id because we let com.bluexml.side.application.ui.action.utils.ApplicationUtil.updateConfigurationFromExtensionPoint(Configuration) update the rest
		componentConf.setId(sideComponentId);

		if (options != null) {
			for (String string : options) {
				Option op = FACTORY.createOption();
				op.setKey(string);
				componentConf.getOptions().add(op);
			}
		}
		conf.getDeployerConfigurations().add(componentConf);
	}

	private void createConfigurationParameter(Configuration conf, String datatype, String key, String value) {
		ConfigurationParameters param = FACTORY.createConfigurationParameters();
		param.setDataType(datatype);
		param.setKey(key);
		param.setValue(value);
		conf.getParameters().add(param);
	}

	public void updateModelsElements(Application app) {
		List<IFile> initializedModels = new ArrayList<IFile>();
		initializedModels.addAll(externals);

		Set<ModelCreator> allInitializer = register.getAllInitializer();
		// we do not want to include .application ... in application model
		allInitializer.remove(this);

		for (ModelCreator modelInitializer : allInitializer) {
			IFile iFile = IFileHelper.getIFile(modelInitializer.getNewModelPath());
			initializedModels.add(iFile);
		}

		// only create new Models entry
		EList<ModelElement> elements = app.getElements();
		List<String> modelsPaths = new ArrayList<String>();
		for (ModelElement modelElement : elements) {
			if (modelElement instanceof Model) {
				Model modelElementM = (Model) modelElement;
				String file = modelElementM.getFile();
				modelsPaths.add(file);
			}
		}
		for (IFile iFile : initializedModels) {
			String fullpath = iFile.getFullPath().toString();
			if (!modelsPaths.contains(fullpath)) {
				Model model = FACTORY.createModel();
				model.setFile(fullpath);
				model.setName(iFile.getName());
				elements.add(model);
			}
		}
	}
}
