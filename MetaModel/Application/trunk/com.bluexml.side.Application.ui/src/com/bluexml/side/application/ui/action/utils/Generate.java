package com.bluexml.side.application.ui.action.utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import com.bluexml.side.application.ui.Activator;
import com.bluexml.side.application.ui.action.ApplicationDialog;
import com.bluexml.side.util.componentmonitor.ComponentMonitor;
import com.bluexml.side.util.componentmonitor.Monitor;
import com.bluexml.side.util.dependencies.DependencesManager;
import com.bluexml.side.util.dependencies.ModuleConstraint;
import com.bluexml.side.util.deployer.Deployer;
import com.bluexml.side.util.documentation.LogSave;
import com.bluexml.side.util.documentation.structure.enumeration.LogType;
import com.bluexml.side.util.feedback.FeedbackActivator;
import com.bluexml.side.util.feedback.management.FeedbackManager;
import com.bluexml.side.util.generator.AbstractGenerator;
import com.bluexml.side.util.libs.IFileHelper;

public class Generate extends Thread {

	private static int NB_GENERATION_STEP = 3;
	private static int NB_DEPLOY_STEP = 4;
	private static int NB_GENERAL_STEP = 2;

	private ProgressBar progressBar;
	private Label label;
	private ProgressBar progressBar2;
	private Label label2;
	private StyledText styletext;
	private Monitor generalMonitor;
	private String logPath;
	private FormText logLink;
	private String genPath;
	private FeedbackManager feedbackManager;
	protected String lineSeparator = System.getProperty("line.separator"); //$NON-NLS-1$
	protected String fileSeparator = System.getProperty("file.separator"); //$NON-NLS-1$
	private boolean doDocumentation;
	private boolean skipValidation;
	private boolean doClean;

	/**
	 * compute the max number of task for this configuration.
	 * 
	 * @param configuration
	 * @return
	 */
	public int computetotalTaskNb(Configuration configuration) {
		// general steps
		int generalSteps = NB_GENERAL_STEP;
		// optional general steps
		if (!skipValidation) {
			generalSteps += 1;
		}
		if (doClean) {
			generalSteps += 1;
		}
		int generators = configuration.getGeneratorConfigurations().size() * NB_GENERATION_STEP;
		int deployers = configuration.getDeployerConfigurations().size() * NB_DEPLOY_STEP;

		return generalSteps + generators + deployers;

	}

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
	public void run(Configuration configuration, List<String> staticParameters, List<Model> models, ProgressBar p_progressBar, Label p_label, ProgressBar p_progressBar2, Label p_label2, StyledText p_styletext, FormText p_logLink) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, IOException {

		progressBar = p_progressBar;
		label = p_label;
		progressBar2 = p_progressBar2;
		label2 = p_label2;
		styletext = p_styletext;
		logLink = p_logLink;
		feedbackManager = new FeedbackManager();
		// compute total of general step

		generalMonitor = new Monitor(styletext, progressBar, label, null);

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
					generalMonitor.addWarningText(Activator.Messages.getString("Generate.2", param.getKey())); //$NON-NLS-1$
				}
			}
		}
		initOptions(configuration, configurationParameters);
		int nbTask = computetotalTaskNb(configuration);
		generalMonitor.setMaxTaskNb(nbTask);

		// Secondly we get the meta-model associated to a model
		HashMap<String, List<IFile>> modelsInfo = null;
		boolean modelWithError = false;

		try {
			modelsInfo = (HashMap<String, List<IFile>>) ApplicationUtil.getAssociatedMetaModel(models);
		} catch (Exception e) {
			modelWithError = true;
			generalMonitor.addErrorText(Activator.Messages.getString("Generate.4") + e.getMessage()); //$NON-NLS-1$
			e.printStackTrace();
		}

		// Validation :
		label.setText(Activator.Messages.getString("Generate.5")); //$NON-NLS-1$
		if (!skipValidation) {
			generalMonitor.beginTask(Activator.Messages.getString("Generate.5")); //$NON-NLS-1$
			Iterator<List<IFile>> it = modelsInfo.values().iterator();
			List<IFile> listModel;
			while (it.hasNext()) {
				listModel = it.next();
				for (IFile m : listModel) {
					try {
						if (ApplicationUtil.validate(m)) {
							generalMonitor.addText(m.getName() + Activator.Messages.getString("Generate.6")); //$NON-NLS-1$
						} else {
							generalMonitor.addErrorText(Activator.Messages.getString("Generate.7") + m.getName() + " isn't validated. Please launch 'Validate' on top model element of " + m.getName() + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
							modelWithError = true;
						}

					} catch (Exception e) {
						generalMonitor.addErrorText(Activator.Messages.getString("Generate.10") + m.getName() + " : " + e.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
						modelWithError = true;
						e.printStackTrace();
					}
				}
			}
			generalMonitor.taskDone(Activator.Messages.getString("Generate.1")); //$NON-NLS-1$
		}
		if (!modelWithError) {

			IFolder logFolder = IFileHelper.getIFolder(configurationParameters.get(ApplicationDialog.KEY_LOGPATH));
			if (!logFolder.exists()) {
				generalMonitor.addWarningText(Activator.Messages.getString("Generate.12") + logPath + " doesn't exist"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			IFolder genFolder = IFileHelper.getIFolder(genPath);
			if (!genFolder.exists()) {
				generalMonitor.addWarningText(Activator.Messages.getString("Generate.14") + genPath + " doesn't exist"); //$NON-NLS-1$ //$NON-NLS-2$
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
		doClean = getCleanOption(configuration, configurationParameters);
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
		return configurationParameters.get(ApplicationDialog.KEY_LOGPATH) + fileSeparator + configuration.getName();
	}

	protected String getGenerationPath(Configuration configuration, Map<String, String> configurationParameters) {
		return configurationParameters.get(ApplicationDialog.KEY_GENPATH) + fileSeparator;
	}

	protected boolean getDoDocumentation(Configuration configuration, Map<String, String> configurationParameters) {
		return Boolean.parseBoolean(configurationParameters.get(ApplicationDialog.KEY_DOCUMENTATION));
	}

	protected boolean getSkipValidation(Configuration configuration, Map<String, String> configurationParameters) {
		return Boolean.valueOf(configurationParameters.get(ApplicationDialog.KEY_SKIPVALIDATION));
	}

	protected boolean getCleanOption(Configuration configuration, Map<String, String> configurationParameters) {
		return Boolean.valueOf(configurationParameters.get(ApplicationDialog.KEY_DOCLEAN));
	}

	protected boolean isOfflineMode(Map<String, String> configurationParameters) {
		return Boolean.valueOf(configurationParameters.get(ApplicationDialog.KEY_OFFLINE));
	}

	private void generate(final Configuration configuration, final HashMap<String, List<IFile>> modelsInfo, final Map<String, String> configurationParameters, final Map<String, String> generationParameters) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {

		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				// Clean if needed :
				if (doClean) {
					try {
						generalMonitor.subTask(Activator.Messages.getString("Generate.16")); //$NON-NLS-1$
						clean();
						generalMonitor.taskDone(Activator.Messages.getString("Generate.18")); //$NON-NLS-1$
					} catch (CoreException e) {
						e.printStackTrace();
						generalMonitor.addErrorText(Activator.Messages.getString("Generate.20")); //$NON-NLS-1$

					}
				}
				// if work online do a mvn go-offline to prepare maven to work
				// offline if asked
				if (!isOfflineMode(configurationParameters)) {
					// get all Integration modules for offline mode
					try {
						ApplicationUtil.prepareForOffline();
					} catch (Exception e1) {
						e1.printStackTrace();
						generalMonitor.addErrorText(Activator.Messages.getString("Generate.15")); //$NON-NLS-1$
					}
				}
				boolean error = false;
				// generate
				generalMonitor.subTask(Activator.Messages.getString("Generate.48")); //$NON-NLS-1$
				error &= generate_(configuration, modelsInfo, configurationParameters, generationParameters);
				generalMonitor.taskDone(null); //$NON-NLS-1$

				// deploy
				generalMonitor.subTask(Activator.Messages.getString("Generate.49")); //$NON-NLS-1$
				error &= deploy_(configuration, modelsInfo, configurationParameters, generationParameters);
				generalMonitor.taskDone(null); //$NON-NLS-1$

				if (!error) {
					generalMonitor.addText(Activator.Messages.getString("Generate.21")); //$NON-NLS-1$
				} else {
					generalMonitor.addErrorText(Activator.Messages.getString("Generate.22")); //$NON-NLS-1$
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
			generalMonitor.addErrorText(Activator.Messages.getString("Generate.24", idGenerator)); //$NON-NLS-1$
		}
		return null;
	}

	private boolean generate_(final Configuration configuration, final HashMap<String, List<IFile>> modelsInfo, final Map<String, String> configurationParameters, final Map<String, String> generationParameters) {

		// For all generator version we will call generation method

		// progressBar.setMaximum(nbTask);
		boolean error = false;

		for (GeneratorConfiguration elem : configuration.getGeneratorConfigurations()) {

			String id_techno_version = elem.getId_techno_version();
			configurationParameters.put("technologyVersion", id_techno_version); //$NON-NLS-1$
			configurationParameters.put("generatorName", elem.getGeneratorName()); //$NON-NLS-1$
			configurationParameters.put("generatorId", elem.getId()); //$NON-NLS-1$
			configurationParameters.put("metaModelName", elem.getMetaModelName()); //$NON-NLS-1$
			configurationParameters.put("technologyName", elem.getTechnologyName()); //$NON-NLS-1$
			configurationParameters.put("technologyVersionName", elem.getTechnologyVersionName()); //$NON-NLS-1$
			configurationParameters.put("configurationName", configuration.getName()); //$NON-NLS-1$

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
				generalMonitor.addErrorText("Error while getting generator (" + elem.getId() + ")."); //$NON-NLS-1$ //$NON-NLS-2$
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				generalMonitor.addErrorText("Error while instanciating generator (" + elem.getId() + ")."); //$NON-NLS-1$ //$NON-NLS-2$
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				generalMonitor.addErrorText("Error while accessing generator (" + elem.getId() + ")."); //$NON-NLS-1$ //$NON-NLS-2$
				e1.printStackTrace();
			}

			// We initialize the generator with all data collected in
			// application model
			if (generator != null && ((generator.isDocumentationGenerator() && doDocumentation) || !generator.isDocumentationGenerator())) {
				// We generate only if there is meta-model available for
				// the generator
				if (generator.shouldGenerate(modelsInfo, elem.getId_metamodel())) {
					// create monitor
					int nbTask = NB_GENERATION_STEP;

					ComponentMonitor generationMonitor = new ComponentMonitor(styletext, progressBar2, nbTask, label2, generalMonitor, configurationParameters, LogType.GENERATION);

					String name = elem.getGeneratorName();
					generationMonitor.beginTask(Activator.Messages.getString("Generate.30", name)); //$NON-NLS-1$
					generationMonitor.beginTask(Activator.Messages.getString("Generate.59", name)); //$NON-NLS-1$

					try {
						List<ModuleConstraint> lmc = new ArrayList<ModuleConstraint>();
						EList<com.bluexml.side.application.ModuleConstraint> l = elem.getModuleContraints();
						for (int c = 0; c < l.size(); c++) {
							com.bluexml.side.application.ModuleConstraint current = l.get(c);
							lmc.add(new ModuleConstraint(current.getModuleId(), current.getTechnologyVersion(), current.getModuleType(), current.getVersionMin(), current.getVersionMax()));
						}

						DependencesManager dm = new DependencesManager(lmc, isOfflineMode(configurationParameters));

						generator.initialize(generationParameters, generatorOptions, configurationParameters, dm, generationMonitor);
					} catch (Exception e) {
						error = true;
						generationMonitor.addErrorTextAndLog(Activator.Messages.getString("Generate.32", e.getMessage()), e, null); //$NON-NLS-1$
						Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, Activator.Messages.getString("Generate.32"), e)); //$NON-NLS-1$
					}

					generationMonitor.taskDone(Activator.Messages.getString("Generate.8")); //$NON-NLS-1$
					if (generator.check()) {
						// The first one
						if (modelsInfo.size() > 0) {
							// Generate
							try {
								generationMonitor.beginTask(Activator.Messages.getString("Generate.33", name)); //$NON-NLS-1$
								//label.setText(Activator.Messages.getString("Generate.33", name)); //$NON-NLS-1$
								generator.generate(modelsInfo, elem.getId_metamodel());
								generationMonitor.taskDone(Activator.Messages.getString("Generate.34")); //$NON-NLS-1$
								//addText(Activator.Messages.getString("Generate.34")); //$NON-NLS-1$
							} catch (Exception e) {
								error = true;
								generationMonitor.addErrorTextAndLog(Activator.Messages.getString("Generate.39", e.getMessage()), e, null); //$NON-NLS-1$
								Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, Activator.Messages.getString("Generate.39", e.getMessage()))); //$NON-NLS-1$
							}
							generationMonitor.beginTask(Activator.Messages.getString("Generate.35", name)); //$NON-NLS-1$
							//label.setText(Activator.Messages.getString("Generate.35", name)); //$NON-NLS-1$

							// Complete
							try {
								generator.complete();
							} catch (Exception e) {
								generationMonitor.addErrorTextAndLog(Activator.Messages.getString("Generate.61", e.getMessage()), e, null); //$NON-NLS-1$
								e.printStackTrace();
								Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, Activator.Messages.getString("Generate.61", e.getMessage()))); //$NON-NLS-1$
							}
							generationMonitor.taskDone(Activator.Messages.getString("Generate.36")); //$NON-NLS-1$

							try {
								generator.createStampFile();
							} catch (Exception e) {
								generationMonitor.addErrorTextAndLog(Activator.Messages.getString("Generate.42") + e.getMessage(), e, null); //$NON-NLS-1$
								Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, Activator.Messages.getString("Generate.42"), e)); //$NON-NLS-1$
							}
						}
					} else {
						generationMonitor.addErrorTextAndLog(Activator.Messages.getString("Generate.44", elem.getId()), null, null); //$NON-NLS-1$ //$NON-NLS-2$
						generationMonitor.skipTasks(NB_GENERATION_STEP);
					}
				} else {
					if (!generator.isDocumentationGenerator()) {
						generalMonitor.addWarningText(Activator.Messages.getString("Generate.58", elem.getId())); //$NON-NLS-1$
					}
					generalMonitor.skipTasks(NB_GENERATION_STEP);
				}
				String fileName = "gen_" + generator.getClass().getName() + ".xml"; //$NON-NLS-1$ //$NON-NLS-2$
				try {
					if (generator.getMonitor() != null) {
						generator.getMonitor().getLog().saveLog(fileName, logPath + fileSeparator + "work" + fileSeparator); //$NON-NLS-1$
					}

				} catch (Exception e) {
					generalMonitor.addErrorText(Activator.Messages.getString("Generate.62", e.getMessage())); //$NON-NLS-1$
					e.printStackTrace();
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, Activator.Messages.getString("Generate.62"), e)); //$NON-NLS-1$
				}
			} else {
				generalMonitor.skipTasks(NB_GENERATION_STEP);
			}
		}
		return error;
	}

	private boolean deploy_(final Configuration configuration, final HashMap<String, List<IFile>> modelsInfo, final Map<String, String> configurationParameters, final Map<String, String> generationParameters) {
		boolean error = false;
		List<DeployerConfiguration> ldeployers = configuration.getDeployerConfigurations();
		//label.setText(Activator.Messages.getString("Generate.48")); //$NON-NLS-1$
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
				generalMonitor.addErrorText(Activator.Messages.getString("Generate.50") + id_deployer + " haven't been found."); //$NON-NLS-1$ //$NON-NLS-2$
			} catch (InstantiationException e) {
				error = true;
				e.printStackTrace();
				generalMonitor.addErrorText(Activator.Messages.getString("Generate.50") + id_deployer + " can't be instanciate."); //$NON-NLS-1$ //$NON-NLS-2$
			} catch (IllegalAccessException e) {
				error = true;
				e.printStackTrace();
				generalMonitor.addErrorText(Activator.Messages.getString("Generate.50") + id_deployer + " access error."); //$NON-NLS-1$ //$NON-NLS-2$
			} catch (Exception e) {
				error = true;
				e.printStackTrace();
				generalMonitor.addErrorText(Activator.Messages.getString("Generate.54") + id_deployer + "."); //$NON-NLS-1$ //$NON-NLS-2$
			}
			try {
				IFileHelper.refreshFolder(logPath);
			} catch (CoreException e1) {
				e1.printStackTrace();
			}
			if (genObj instanceof Deployer) {
				Deployer deployer = (Deployer) genObj;
				int nbTask = NB_DEPLOY_STEP;
				ComponentMonitor deployerMonitor = new ComponentMonitor(styletext, progressBar2, nbTask, label2, generalMonitor, configurationParameters, LogType.DEPLOYMENT);
				// deployer initialization
				deployer.initialize(configurationParameters, generationParameters, deployerOptions, deployerMonitor);
				if ((deployer.isDocumentationDeployer() && doDocumentation) || !deployer.isDocumentationDeployer()) {

					try {
						deployerMonitor.beginTask(Activator.Messages.getString("Generate.51", depConf.getDeployerName())); //$NON-NLS-1$
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
						e.printStackTrace();
						error = true;
						deployerMonitor.addErrorTextAndLog(Activator.Messages.getString("Generate.56") + e.getMessage(), e, null); //$NON-NLS-1$
						// must be the last action because this cause a break in
						// the
						// execution stack
						// so no deployer after this can be executed !
						Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, Activator.Messages.getString("Generate.56"), e)); //$NON-NLS-1$
					}

					try {
						deployer.moveStampFile(logPath);
					} catch (Exception e) {
						e.printStackTrace();
						deployerMonitor.addWarningTextAndLog(Activator.Messages.getString("Generate.57") + e.getMessage(), null); //$NON-NLS-1$
					}

					String fileName = "dep_" + deployer.getClass().getName() + ".xml"; //$NON-NLS-1$ //$NON-NLS-2$
					try {
						deployerMonitor.getLog().saveLog(fileName, logPath + fileSeparator + "work" + fileSeparator); //$NON-NLS-1$
					} catch (Exception e) {
						deployerMonitor.addErrorText(Activator.Messages.getString("Generate.62", e.getMessage())); //$NON-NLS-1$
						e.printStackTrace();
						Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, Activator.Messages.getString("Generate.62"), e)); //$NON-NLS-1$
					}
				} else {
					deployerMonitor.skipTasks(NB_DEPLOY_STEP);
				}
				deployerMonitor.taskDone(Activator.Messages.getString("Generate.52")); //$NON-NLS-1$
			}
		}
		return error;
	}
}
