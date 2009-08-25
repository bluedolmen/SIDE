package com.bluexml.side.application.ui.action.utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormText;
import org.osgi.framework.Bundle;

import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.DeployerConfiguration;
import com.bluexml.side.application.GeneratorConfiguration;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.application.ui.Activator;
import com.bluexml.side.application.ui.action.ApplicationDialog;
import com.bluexml.side.application.ui.action.Messages;
import com.bluexml.side.util.dependencies.DependencesManager;
import com.bluexml.side.util.dependencies.ModuleConstraint;
import com.bluexml.side.util.deployer.Deployer;
import com.bluexml.side.util.documentation.LogSave;
import com.bluexml.side.util.feedback.FeedbackActivator;
import com.bluexml.side.util.feedback.management.FeedbackManager;
import com.bluexml.side.util.generator.AbstractGenerator;
import com.bluexml.side.util.libs.IFileHelper;

public class Generate extends Thread {

	private int NB_GENERATION_STEP = 5;
	private int NB_DEPLOY_STEP = 4;
	private ProgressBar progressBar;
	private Label label;
	private StyledText styletext;
	private String logPath;
	private FormText logLink;
	private String genPath;
	private FeedbackManager feedbackManager;
	protected String lineSeparator = System.getProperty("line.separator"); //$NON-NLS-1$
	protected String fileSeparator = System.getProperty("file.separator"); //$NON-NLS-1$
	private boolean doDocumentation;
	private boolean skipValidation;

	/**
	 * Launch generation on all generator version selected
	 * 
	 * @param configuration
	 * @param staticParameters
	 * @param models
	 * @param logLink2
	 * @param progressBar
	 * @param label
	 * @param styletext
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	public void run(Configuration configuration, List<String> staticParameters, List<Model> models, ProgressBar p_progressBar, Label p_label, StyledText p_styletext, FormText p_logLink) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {

		progressBar = p_progressBar;
		label = p_label;
		styletext = p_styletext;
		logLink = p_logLink;
		feedbackManager = new FeedbackManager();

		// First we seek the generator parameters, and separate fields
		// of dynamic fields
		Map<String, String> configurationParameters = new HashMap<String, String>();
		Map<String, String> generationParameters = new HashMap<String, String>();
		for (ConfigurationParameters param : configuration.getParameters()) {
			if (staticParameters.contains(param.getKey())) {
				configurationParameters.put(param.getKey(), param.getValue());
			} else {
				generationParameters.put(param.getKey(), param.getValue());
				// Check to know if option have been set, no error but warning
				// message
				if (param.getValue() == null || param.getValue().length() == 0) {
					addWarningText(Messages.getString("Generate.2", param.getKey())); //$NON-NLS-1$
				}
			}
		}
		initOptions(configuration, configurationParameters);

		// Secondly we get the meta-model associated to a model
		HashMap<String, List<IFile>> modelsInfo = null;
		boolean modelWithError = false;

		try {
			modelsInfo = (HashMap<String, List<IFile>>) ApplicationUtil.getAssociatedMetaModel(models);
		} catch (IOException e) {
			modelWithError = true;
			addErrorText(Messages.getString("Generate.4") + e.getMessage()); //$NON-NLS-1$
			e.printStackTrace();
		}

		// Validation :
		label.setText(Messages.getString("Generate.5")); //$NON-NLS-1$
		if (!skipValidation) {
			Iterator<List<IFile>> it = modelsInfo.values().iterator();
			List<IFile> listModel;
			while (it.hasNext()) {
				listModel = it.next();
				for (IFile m : listModel) {
					try {
						if (ApplicationUtil.validate(m)) {
							addText(m.getName() + Messages.getString("Generate.6")); //$NON-NLS-1$
						} else {
							addErrorText(Messages.getString("Generate.7") + m.getName() + " isn't validated. Please launch 'Validate' on top model element of " + m.getName() + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
							modelWithError = true;
						}

					} catch (IOException e) {
						addErrorText(Messages.getString("Generate.10") + m.getName() + " : " + e.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
						modelWithError = true;
						e.printStackTrace();
					}
				}
			}
		}
		if (!modelWithError) {
			addOneStep(progressBar);

			IFolder logFolder = IFileHelper.getIFolder(logPath);
			if (!logFolder.exists()) {
				addErrorText(Messages.getString("Generate.12") + logPath + " doesn't exist"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			IFolder genFolder = IFileHelper.getIFolder(genPath);
			if (!genFolder.exists()) {
				addErrorText(Messages.getString("Generate.14") + genPath + " doesn't exist"); //$NON-NLS-1$ //$NON-NLS-2$
			}

			generate(configuration, modelsInfo, configurationParameters, generationParameters);
			// All behind this line will be execute before the generation (async
			// method).
		}
	}

	protected void initOptions(Configuration configuration, Map<String, String> configurationParameters) {
		logPath = getLogPath(configuration, configurationParameters);
		genPath = getGenerationPath(configuration, configurationParameters);
		doDocumentation = getDoDocumentation(configuration, configurationParameters);
		skipValidation = getSkipValidation(configuration, configurationParameters);
	}

	/**
	 * Refresh log and generation paths
	 */
	private void refreshFolders() {
		try {
			IFileHelper.refreshFolder(logPath);
			IFileHelper.refreshFolder(genPath);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Return the log path (folder path + configuration name)
	 * 
	 * @param configuration
	 * @param configurationParameters
	 * @return
	 */
	protected String getLogPath(Configuration configuration, Map<String, String> configurationParameters) {
		return configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral()) + fileSeparator + configuration.getName();
	}

	protected String getGenerationPath(Configuration configuration, Map<String, String> configurationParameters) {
		return configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSDESTINATION_PATH.getLiteral()) + fileSeparator;
	}

	protected boolean getDoDocumentation(Configuration configuration, Map<String, String> configurationParameters) {
		return Boolean.parseBoolean(configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSDOCUMENTATION.getLiteral()));
	}

	protected boolean getSkipValidation(Configuration configuration, Map<String, String> configurationParameters) {
		return Boolean.valueOf(configurationParameters.get(ApplicationDialog.KEY_SKIPVALIDATION));
	}

	private void addText(String text) {
		addText(lineSeparator + text, SWT.COLOR_BLACK);
	}

	private void addErrorText(String text) {
		addText(lineSeparator + text, SWT.COLOR_RED);
	}

	private void addWarningText(String text) {
		addText(lineSeparator + text, SWT.COLOR_DARK_YELLOW);
	}

	private void addText(String text, int color) {
		StyleRange style2 = new StyleRange();
		style2.start = styletext.getText().length();
		style2.length = text.length();
		style2.foreground = Display.getDefault().getSystemColor(color);
		styletext.append(text);
		styletext.setStyleRange(style2);
		styletext.setTopIndex(styletext.getLineCount());
	}

	private void generate(final Configuration configuration, final HashMap<String, List<IFile>> modelsInfo, final Map<String, String> configurationParameters, final Map<String, String> generationParameters) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {

		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				// Clean if needed :
				boolean doClean = Boolean.parseBoolean(configurationParameters.get(ApplicationDialog.KEY_DOCLEAN));
				if (doClean) {
					try {
						label.setText(Messages.getString("Generate.16")); //$NON-NLS-1$
						addText(Messages.getString("Generate.17")); //$NON-NLS-1$
						clean();
						addText(Messages.getString("Generate.18")); //$NON-NLS-1$
						label.setText(Messages.getString("Generate.19")); //$NON-NLS-1$
					} catch (CoreException e) {
						addErrorText(Messages.getString("Generate.20")); //$NON-NLS-1$
						e.printStackTrace();
					}
				}

				boolean error = generate_(configuration, modelsInfo, configurationParameters, generationParameters);
				error &= deploy_(configuration, modelsInfo, configurationParameters, generationParameters);

				progressBar.setSelection(progressBar.getMaximum());
				if (!error) {
					label.setText(Messages.getString("Generate.21")); //$NON-NLS-1$
				} else {
					label.setText(Messages.getString("Generate.22")); //$NON-NLS-1$
				}

				// Log
				try {
					LogSave.buildGeneraLogFile(logPath);
					IFileHelper.refreshFolder(logPath);
					logLink.setVisible(true);
					logLink.addHyperlinkListener(new HyperlinkAdapter() {
						@Override
						public void linkActivated(HyperlinkEvent event) {
							browseTo("file://" + IFileHelper.getIFolder(logPath).getRawLocation().toFile().getAbsolutePath() + fileSeparator + LogSave.LOG_FILE_NAME); //$NON-NLS-1$
						}
					});

				} catch (Exception e) {
					e.printStackTrace();
				}
				if (FeedbackActivator.doFeedback()) {
					// Feedback
					try {
						feedbackManager.save();
						// FeedbackSender.send();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				// Refresh log and generation folder
				refreshFolders();
			}
		});

	}

	private void browseTo(String url) {
		try {
			PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(new URL(url));
		} catch (Exception e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Error opening browser", e)); //$NON-NLS-1$
		}
	}

	private void clean() throws CoreException {
		IFileHelper.deleteFolderContent(IFileHelper.getIFolder(logPath));
		IFileHelper.deleteFolderContent(IFileHelper.getIFolder(genPath));
	}

	private AbstractGenerator getGeneratorInstance(GeneratorConfiguration elem) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String launchGeneratorClass = elem.getImpl_class();
		String idGenerator = elem.getId();
		Bundle plugin = Platform.getBundle(idGenerator);
		Class<?> gen;
		Object genObj = null;
		if (plugin != null) {
			gen = plugin.loadClass(launchGeneratorClass);
			genObj = gen.newInstance();
			if (genObj instanceof AbstractGenerator) {
				AbstractGenerator generator = (AbstractGenerator) genObj;
				return generator;
			}
		} else {
			addErrorText(Messages.getString("Generate.24", idGenerator)); //$NON-NLS-1$
		}
		return null;
	}

	private boolean generate_(final Configuration configuration, final HashMap<String, List<IFile>> modelsInfo, final Map<String, String> configurationParameters, final Map<String, String> generationParameters) {
		// For all generator version we will call generation method
		int nbTask = configuration.getGeneratorConfigurations().size() * NB_GENERATION_STEP;
		progressBar.setMaximum(nbTask);
		boolean error = false;

		for (GeneratorConfiguration elem : configuration.getGeneratorConfigurations()) {
			String id_techno_version = elem.getId_techno_version();
			configurationParameters.put("technologyVersion", id_techno_version); //$NON-NLS-1$
			configurationParameters.put("generatorName", elem.getGeneratorName()); //$NON-NLS-1$
			configurationParameters.put("generatorId", elem.getId()); //$NON-NLS-1$
			configurationParameters.put("metaModelName", elem.getMetaModelName()); //$NON-NLS-1$
			configurationParameters.put("technologyName", elem.getTechnologyName()); //$NON-NLS-1$
			configurationParameters.put("technologyVersionName", elem.getTechnologyVersionName()); //$NON-NLS-1$

			// We get the option for this generator
			Map<String, Boolean> generatorOptions = new HashMap<String, Boolean>();
			for (Option option : elem.getOptions()) {
				generatorOptions.put(option.getKey(), true);
			}
			if (FeedbackActivator.doFeedback()) {
				feedbackManager.addFeedBackItem(elem.getId(), elem.getMetaModelName(), id_techno_version, generatorOptions);
			}

			AbstractGenerator generator = null;
			try {
				generator = getGeneratorInstance(elem);
			} catch (ClassNotFoundException e1) {
				addErrorText("Error while getting generator (" + elem.getId() + ")."); //$NON-NLS-1$ //$NON-NLS-2$
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				addErrorText("Error while instanciating generator (" + elem.getId() + ")."); //$NON-NLS-1$ //$NON-NLS-2$
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				addErrorText("Error while accessing generator (" + elem.getId() + ")."); //$NON-NLS-1$ //$NON-NLS-2$
				e1.printStackTrace();
			}

			// We initialize the generator with all data collected in
			// application model
			if (generator != null && ((generator.isDocumentationGenerator() && doDocumentation) || !generator.isDocumentationGenerator())) {
				// We generate only if there is meta-model available for
				// the generator
				if (generator.shouldGenerate(modelsInfo, elem.getId_metamodel())) {
					String name = elem.getId().substring(elem.getId().lastIndexOf(".") + 1); //$NON-NLS-1$
					label.setText(Messages.getString("Generate.30", name)); //$NON-NLS-1$
					addText(Messages.getString("Generate.59", name)); //$NON-NLS-1$

					try {
						List<ModuleConstraint> lmc = new ArrayList<ModuleConstraint>();
						EList<com.bluexml.side.application.ModuleConstraint> l = elem.getModuleContraints();
						for (int c = 0; c < l.size(); c++) {
							com.bluexml.side.application.ModuleConstraint current = l.get(c);
							lmc.add(new ModuleConstraint(current.getModuleId(), current.getTechnologyVersion(), current.getModuleType(), current.getVersionMin(), current.getVersionMax()));
						}
						DependencesManager dm = new DependencesManager(lmc);

						generator.initialize(generationParameters, generatorOptions, configurationParameters, dm);
					} catch (Exception e) {
						error = true;
						addErrorText(Messages.getString("Generate.60", e.getMessage())); //$NON-NLS-1$
						generator.addErrorLog(Messages.getString("Generate.32") + e.getMessage(), e.getStackTrace(), null); //$NON-NLS-1$
						Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, Messages.getString("Generate.32"), e)); //$NON-NLS-1$
					}

					addOneStep(progressBar);
					if (generator.check()) {
						// The first one
						if (modelsInfo.size() > 0) {
							// Generate
							try {
								label.setText(Messages.getString("Generate.33", name)); //$NON-NLS-1$
								generator.generate(modelsInfo, elem.getId_metamodel());
								addText(Messages.getString("Generate.34")); //$NON-NLS-1$
							} catch (Exception e) {
								error = true;
								addErrorText(Messages.getString("Generate.39", e.getMessage())); //$NON-NLS-1$
								generator.addErrorLog(Messages.getString("Generate.41") + e.getMessage(), e.getStackTrace(), null); //$NON-NLS-1$
								Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, Messages.getString("Generate.39", e.getMessage()))); //$NON-NLS-1$
							}

							label.setText(Messages.getString("Generate.35", name)); //$NON-NLS-1$
							Collection<IFile> generatedFiles = new ArrayList<IFile>();

							// Complete
							try {
								generatedFiles = generator.complete();
							} catch (Exception e) {
								addErrorText(Messages.getString("Generate.61", e.getMessage())); //$NON-NLS-1$
								Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, Messages.getString("Generate.61", e.getMessage()))); //$NON-NLS-1$
								e.printStackTrace();
							}
							addText(Messages.getString("Generate.36")); //$NON-NLS-1$

							addText(Messages.getString("Generate.37", name)); //$NON-NLS-1$ //$NON-NLS-2$
							if (generatedFiles != null) {
								for (IFile filePath : generatedFiles) {
									addText(filePath.getRawLocation().makeAbsolute().toOSString());
								}
							}
							addOneStep(progressBar);

							try {
								generator.createStampFile();
							} catch (Exception e) {
								generator.addErrorLog(Messages.getString("Generate.42") + e.getMessage(), e.getStackTrace(), null); //$NON-NLS-1$
								addErrorText(Messages.getString("Generate.43")); //$NON-NLS-1$
								Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, Messages.getString("Generate.42"), e)); //$NON-NLS-1$
							}

							addOneStep(progressBar);
						}
					} else {
						addErrorText(Messages.getString("Generate.44") + elem.getId() + " is not activated, please check your plugin licence"); //$NON-NLS-1$ //$NON-NLS-2$
						generator.addErrorLog(Messages.getString("Generate.46"), Messages.getString("Generate.47"), null); //$NON-NLS-1$ //$NON-NLS-2$
					}
				} else {
					if (!generator.isDocumentationGenerator()) {
						addWarningText(Messages.getString("Generate.58", elem.getId())); //$NON-NLS-1$
					}
				}
				String fileName = "gen_" + generator.getTechVersion() + ".xml"; //$NON-NLS-1$ //$NON-NLS-2$
				LogSave.toXml(generator.getLog(), fileName, logPath + fileSeparator + "work" + fileSeparator); //$NON-NLS-1$
			} else {
				error = true;
			}
		}
		return error;
	}

	private void addOneStep(ProgressBar progressBar) {
		progressBar.setSelection(progressBar.getSelection() + 1);
	}

	private boolean deploy_(final Configuration configuration, final HashMap<String, List<IFile>> modelsInfo, final Map<String, String> configurationParameters, final Map<String, String> generationParameters) {
		progressBar.setSelection(0);
		int nbTask = configuration.getDeployerConfigurations().size() * NB_DEPLOY_STEP;
		progressBar.setMaximum(nbTask);
		boolean error = false;
		List<DeployerConfiguration> ldeployers = configuration.getDeployerConfigurations();
		for (DeployerConfiguration depConf : ldeployers) {
			String deployerClassName = depConf.getImpl_class();
			String id_deployer = depConf.getId();
			String id_techno = depConf.getId_techno_version();
			configurationParameters.put("technologyVersion", id_techno); //$NON-NLS-1$
			configurationParameters.put("deployerName", depConf.getDeployerName()); //$NON-NLS-1$
			configurationParameters.put("deployerId", id_deployer); //$NON-NLS-1$
			configurationParameters.put("metaModelName", depConf.getMetaModelName()); //$NON-NLS-1$
			configurationParameters.put("technologyName", depConf.getTechnologyName()); //$NON-NLS-1$
			configurationParameters.put("technologyVersionName", depConf.getTechnologyVersionName()); //$NON-NLS-1$
			configurationParameters.put("configurationName", configuration.getName()); //$NON-NLS-1$

			List<Option> options = depConf.getOptions();
			// We get the option for this generator
			List<String> deployerOptions = new ArrayList<String>();
			for (Option option : options) {
				deployerOptions.add(option.getKey());
			}

			Bundle plugin = Platform.getBundle(id_deployer);
			Class<?> gen;
			Object genObj = null;
			try {
				gen = plugin.loadClass(deployerClassName);
				genObj = gen.newInstance();
			} catch (ClassNotFoundException e1) {
				error = true;
				e1.printStackTrace();
				addErrorText(Messages.getString("Generate.48") + id_deployer + " haven't been found."); //$NON-NLS-1$ //$NON-NLS-2$
			} catch (InstantiationException e) {
				error = true;
				e.printStackTrace();
				addErrorText(Messages.getString("Generate.50") + id_deployer + " can't be instanciate."); //$NON-NLS-1$ //$NON-NLS-2$
			} catch (IllegalAccessException e) {
				error = true;
				e.printStackTrace();
				addErrorText(Messages.getString("Generate.52") + id_deployer + " access error."); //$NON-NLS-1$ //$NON-NLS-2$
			} catch (Exception e) {
				error = true;
				e.printStackTrace();
				addErrorText(Messages.getString("Generate.54") + id_deployer + "."); //$NON-NLS-1$ //$NON-NLS-2$
			}
			try {
				IFileHelper.refreshFolder(logPath);
			} catch (CoreException e1) {
				e1.printStackTrace();
			}
			if (genObj instanceof Deployer) {
				Deployer deployer = (Deployer) genObj;
				deployer.initialize(configurationParameters, generationParameters, deployerOptions);

				try {
					deployer.deploy();
					// We get the option for this generator
					if (FeedbackActivator.doFeedback()) {
						Map<String, Boolean> optionsDep = new HashMap<String, Boolean>();
						for (Option option : depConf.getOptions()) {
							optionsDep.put(option.getKey(), true);
						}
						feedbackManager.addFeedBackItem(depConf.getId(), null, id_techno, optionsDep);
					}
				} catch (Exception e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, Messages.getString("Generate.56"), e)); //$NON-NLS-1$
					e.printStackTrace();
					error = true;
					addErrorText(Messages.getString("Generate.56") + e.getMessage()); //$NON-NLS-1$
					deployer.addErrorLog(Messages.getString("Generate.56"), e.getStackTrace(), null); //$NON-NLS-1$
				}

				try {
					deployer.moveStampFile(logPath);
				} catch (Exception e) {
					e.printStackTrace();
					addWarningText(Messages.getString("Generate.57") + e.getMessage()); //$NON-NLS-1$
				}

				String fileName = "dep_" + deployer.getTechVersion() + ".xml"; //$NON-NLS-1$ //$NON-NLS-2$
				LogSave.toXml(deployer.getLog(), fileName, logPath + fileSeparator + "work" + fileSeparator); //$NON-NLS-1$
			}
		}
		return error;
	}
}
