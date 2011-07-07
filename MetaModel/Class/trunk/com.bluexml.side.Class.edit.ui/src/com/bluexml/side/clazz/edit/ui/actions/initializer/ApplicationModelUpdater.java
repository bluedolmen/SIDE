package com.bluexml.side.clazz.edit.ui.actions.initializer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.application.Application;
import com.bluexml.side.application.ApplicationFactory;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.DeployerConfiguration;
import com.bluexml.side.application.GeneratorConfiguration;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.Option;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.edit.ui.actions.wizards.initializefromclass.pages.InitializerPageWelcome.AlfrescoVersions;
import com.bluexml.side.util.libs.IFileHelper;

public class ApplicationModelUpdater extends ModelInitializer {
	private static ApplicationFactory FACTORY = ApplicationFactory.eINSTANCE;
	private static final String APPLICATION_EDITOR_ID = "com.bluexml.side.application.presentation.ApplicationEditorID"; //$NON-NLS-1$
	private String alfrescoHome;
	private String alfrescoVersion;

	public ApplicationModelUpdater(IFile classModel, ClassPackage root, InitializerRegister register, ASK_USER ask, String fileName, String alfrescoVersion, String alfrescoHome) throws IOException {
		super(classModel, root, ModelInitializationUtils.getExtensionForExtensionId(APPLICATION_EDITOR_ID), "application", register, ask, fileName); //$NON-NLS-1$
		this.alfrescoVersion = alfrescoVersion;
		this.alfrescoHome = alfrescoHome;
	}

	@Override
	protected void headLessInitialize() throws Exception {
		Application app = (Application) newRootObject;
		app.setName(getModelName());

		// create models elements
		createModelsElements(app);

		// create new configuration
		createConfiguration(app);

	}

	@Override
	protected Command initialize(EditingDomain editingDomain) throws Exception {
		CompoundCommand cc = new CompoundCommand();
		headLessInitialize();
		return cc;
	}

	private void createConfiguration(Application app) {
		Configuration conf = FACTORY.createConfiguration();
		conf.setName(getModelName());
		createConfigurationParameter(conf, "Directory", "CATALINA_HOME", alfrescoHome); //$NON-NLS-1$ //$NON-NLS-2$
		String projectName = classModel.getProject().getName();

		createConfigurationParameters(conf, projectName);

		createGeneratorsConfigurations(conf);

		createDeployersConfigurations(conf);

		app.getElements().add(conf);
	}

	private void createConfigurationParameters(Configuration conf, String projectName) {
		createConfigurationParameter(conf, "", "generation.options.logPath", "/" + projectName + "/build/logs"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		createConfigurationParameter(conf, "", "generation.options.destinationPath", "/" + projectName + "/build/generated"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		createConfigurationParameter(conf, "", "generation.options.clean", "true"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	private void createDeployersConfigurations(Configuration conf) {
		List<String> options = new ArrayList<String>();
		options.add("com.bluexml.side.Deployer.alfrescoDirectCopy34d.clean"); //$NON-NLS-1$
		createDeployerConfiguration(conf, "com.bluexml.side.Deployer.alfrescoDirectCopy34d", options); //$NON-NLS-1$
	}

	private void createGeneratorsConfigurations(Configuration conf) {
		if (alfrescoVersion.equals(AlfrescoVersions.COMMUNITY_34D.toString())) {
			List<String> options_class = new ArrayList<String>();
			options_class.add("alfresco.share.extension"); //$NON-NLS-1$
			createGeneratorConfiguration(conf, "com.bluexml.side.Class.generator.alfresco34d", options_class); //$NON-NLS-1$
			List<String> options_portal = new ArrayList<String>();
			options_portal.add("com.bluexml.side.Portal.generator.alfresco.forms"); //$NON-NLS-1$
			options_portal.add("com.bluexml.side.Portal.generator.alfresco.doclist"); //$NON-NLS-1$
			createGeneratorConfiguration(conf, "com.bluexml.side.Portal.generator.alfresco3.4d", options_portal); //$NON-NLS-1$
			createGeneratorConfiguration(conf, "com.bluexml.side.View.generator.alfresco 34d", null); //$NON-NLS-1$
			createGeneratorConfiguration(conf, "com.bluexml.side.Form.generator.alfresco34d", null); //$NON-NLS-1$
		} else if (alfrescoVersion.equals(AlfrescoVersions.COMMUNITY_32R2.toString())) {
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

	private void createModelsElements(Application app) {
		List<ModelInitializer> init = new ArrayList<ModelInitializer>();
		init.addAll(register.getFormInitializer().values());
		init.addAll(register.getPortalInitializer().values());
		init.addAll(register.getViewInitializer().values());

		for (ModelInitializer modelInitializer : init) {
			IFile modelF = IFileHelper.getIFile(new File(modelInitializer.newModelPath.toOSString()));
			Model model = FACTORY.createModel();
			model.setFile(modelF.getFullPath().toString());
			model.setName(modelInitializer.getNewFileName());
			app.getElements().add(model);
		}

		// add classModel
		IFile modelF = classModel;
		Model model = FACTORY.createModel();
		model.setFile(modelF.getFullPath().toString());
		model.setName(classModel.getName());
		app.getElements().add(model);
	}

	@Override
	protected void createRootObject() {
		// create new application
		newRootObject = FACTORY.createApplication();
	}

}
