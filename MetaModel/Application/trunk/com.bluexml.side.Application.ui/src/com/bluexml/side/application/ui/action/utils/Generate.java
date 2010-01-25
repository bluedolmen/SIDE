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
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.progress.IProgressConstants;
import org.osgi.framework.Bundle;

import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.DeployerConfiguration;
import com.bluexml.side.application.GeneratorConfiguration;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.ui.Activator;
import com.bluexml.side.application.ui.action.ApplicationDialog;
import com.bluexml.side.application.ui.action.MustBeStopped;
import com.bluexml.side.util.componentmonitor.ComponentMonitor;
import com.bluexml.side.util.componentmonitor.Monitor;
import com.bluexml.side.util.componentmonitor.MonitorListener;
import com.bluexml.side.util.componentmonitor.guiAdapter.IProgressMonitorAdapter;
import com.bluexml.side.util.dependencies.DependencesManager;
import com.bluexml.side.util.dependencies.DependenciesDeployer;
import com.bluexml.side.util.dependencies.ModuleConstraint;
import com.bluexml.side.util.deployer.Deployer;
import com.bluexml.side.util.documentation.LogSave;
import com.bluexml.side.util.documentation.structure.enumeration.LogEntryType;
import com.bluexml.side.util.documentation.structure.enumeration.LogType;
import com.bluexml.side.util.feedback.FeedbackActivator;
import com.bluexml.side.util.feedback.management.FeedbackManager;
import com.bluexml.side.util.generator.AbstractGenerator;
import com.bluexml.side.util.libs.IFileHelper;

public class Generate extends WorkspaceJob {
	final ComponentMonitor componentMonitor;
	Configuration configuration;
	List<Model> models;
	boolean headless = false;
	private static int NB_GENERATION_STEP = 3;
	private static int NB_DEPLOY_STEP = 4;
	private static int NB_GENERAL_STEP = 2;

	final private Monitor generalMonitor;
	private String logPath;

	private String genPath;
	private FeedbackManager feedbackManager;
	protected String lineSeparator = System.getProperty("line.separator"); //$NON-NLS-1$
	protected String fileSeparator = System.getProperty("file.separator"); //$NON-NLS-1$
	private boolean doDocumentation;
	private boolean skipValidation;
	private boolean doClean;
	public List<Throwable> errors = new ArrayList<Throwable>();
	public List<String> warns = new ArrayList<String>();

	public void setHeadless(boolean headless) {
		this.headless = headless;
	}

	public Generate(Configuration configuration, List<Model> models, Monitor generalMonitor, ComponentMonitor componentMonitor) {
		super("SIDE Generation Process : " + configuration.getName());
		this.generalMonitor = generalMonitor;
		this.componentMonitor = componentMonitor;
		this.configuration = configuration;
		this.models = models;
		// setProperty(IProgressConstants.NO_IMMEDIATE_ERROR_PROMPT_PROPERTY, Boolean.TRUE);
		setProperty(IProgressConstants.KEEPONE_PROPERTY, Boolean.TRUE);
		setProperty(IProgressConstants.ICON_PROPERTY, getImage());

	}

	private ImageDescriptor getImage() {
		Bundle plugin = Platform.getBundle(Activator.PLUGIN_ID);
		URL imgPath = plugin.getResource("/icon/side_16.gif");
		ImageDescriptor imgDesc = ImageDescriptor.createFromURL(imgPath);
		return imgDesc;
	}

	protected Action getReservationCompletedAction() {
		return new Action("Open Report") {
			public void run() {
				ApplicationUtil.browseTo("file://" + IFileHelper.getIFolder(logPath).getRawLocation().toFile().getAbsolutePath() + fileSeparator + LogSave.LOG_HTML_FILE_NAME); //$NON-NLS-1$
			}
		};
	}

	/**
	 * compute the max number of task for this configuration.
	 * 
	 * @param configuration
	 * @return
	 */
	private int computetotalTaskNb(Configuration configuration) {
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

	public IStatus run_(final IProgressMonitor monitor) {
		try {
			// bind generalMonitor with given monitor from ProgressManager
			generalMonitor.setParent(new IProgressMonitorAdapter(monitor));
			componentMonitor.addMonitorListener(new MonitorListener() {

				public void skipTasks(int skippedTask) {
				}

				public void isCanceled() {
				}

				public void addText(String txt, LogEntryType type) {
					String subTask = "" + generalMonitor.getCurrentTask() + " :" + componentMonitor.getCurrentTask();
					generalMonitor.getParent().subTask(subTask);
				}
			});
			feedbackManager = new FeedbackManager();

			// First we seek the generator parameters, and separate fields
			// of dynamic fields
			Map<String, String> configurationParameters = new HashMap<String, String>();
			Map<String, String> generationParameters = new HashMap<String, String>();
			for (ConfigurationParameters param : configuration.getParameters()) {
				if (ApplicationDialog.staticFieldsName.contains(param.getKey())) {
					configurationParameters.put(param.getKey(), param.getValue());
				} else {
					generationParameters.put(param.getKey(), param.getValue());
					// Check to know if option have been set, no error but
					// warning
					// message
					if (param.getValue() == null || param.getValue().length() == 0) {
						generalMonitor.addWarningText(Activator.Messages.getString("Generate.2", param.getKey())); //$NON-NLS-1$
					}
				}
			}
			initOptions(configuration, configurationParameters);
			// compute total of general step
			int nbTask = computetotalTaskNb(configuration);
			generalMonitor.setMaxTaskNb(nbTask);
			monitor.beginTask("", nbTask);
			// Secondly we get the meta-model associated to a model
			HashMap<String, List<IFile>> modelsInfo = null;

			try {
				modelsInfo = (HashMap<String, List<IFile>>) ApplicationUtil.getAssociatedMetaModel(models);
			} catch (Exception e) {

				generalMonitor.addErrorText(Activator.Messages.getString("Generate.4") + e.getMessage()); //$NON-NLS-1$
				e.printStackTrace();
			}

			// Validation :
			generalMonitor.addText(Activator.Messages.getString("Generate.5")); //$NON-NLS-1$
			if (!skipValidation) {

				checkUserRequest();

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
								throw new Exception(Activator.Messages.getString("Generate.7", m.getName()));
							}
						} catch (Exception e) {
							generalMonitor.addErrorText(Activator.Messages.getString("Generate.10") + m.getName() + " : " + e.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$

						}
					}
				}
				generalMonitor.taskDone(Activator.Messages.getString("Generate.1")); //$NON-NLS-1$
				checkUserRequest();
			}

			IFolder logFolder = IFileHelper.getIFolder(configurationParameters.get(ApplicationDialog.KEY_LOGPATH));
			if (!logFolder.exists()) {
				generalMonitor.addWarningText(Activator.Messages.getString("Generate.12") + logPath + " doesn't exist"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			IFolder genFolder = IFileHelper.getIFolder(genPath);
			if (!genFolder.exists()) {
				generalMonitor.addWarningText(Activator.Messages.getString("Generate.14") + genPath + " doesn't exist"); //$NON-NLS-1$ //$NON-NLS-2$
			}

			generate(configuration, modelsInfo, configurationParameters, generationParameters);

		} catch (MustBeStopped e1) {
			generalMonitor.addErrorText(e1.getMessage());
			monitor.subTask(e1.getMessage());
			setProperty(IProgressConstants.ACTION_PROPERTY, getReservationCompletedAction());
			return new Status(Status.CANCEL, Activator.PLUGIN_ID, e1.getMessage());
		} catch (Exception e2) {
			fatalError(e2);
			setProperty(IProgressConstants.ACTION_PROPERTY, getReservationCompletedAction());
			generalMonitor.addErrorText(Activator.Messages.getString("Generate_0")); //$NON-NLS-1$
			return new Status(Status.ERROR, Activator.PLUGIN_ID, e2.getMessage(), e2);
		}
		monitor.done();
		setProperty(IProgressConstants.ACTION_PROPERTY, getReservationCompletedAction());
		return new Status(Status.OK, Activator.PLUGIN_ID, Activator.Messages.getString("Generate_1"));
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

	private void generate(final Configuration configuration, final HashMap<String, List<IFile>> modelsInfo, final Map<String, String> configurationParameters,
			final Map<String, String> generationParameters) throws MustBeStopped, Exception {

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
		checkUserRequest();
		// if work online do a mvn go-offline to prepare maven to work
		// offline if asked
//		if (!isOfflineMode(configurationParameters)) {
//			// get all Integration modules for offline mode
//			try {
//				generalMonitor.subTask(Activator.Messages.getString("Generate_101")); //$NON-NLS-1$
//				ApplicationUtil.prepareForOffline();
//				generalMonitor.taskDone(Activator.Messages.getString("Generate_102")); //$NON-NLS-1$
//			} catch (Exception e1) {
//				e1.printStackTrace();
//				generalMonitor.addErrorText(Activator.Messages.getString("Generate.15")); //$NON-NLS-1$
//			}
//		}
		
		// update local repository from embedded archive
		generalMonitor.subTask(Activator.Messages.getString("Generate_101")); //$NON-NLS-1$
		DependenciesDeployer.deploy();
		generalMonitor.taskDone(Activator.Messages.getString("Generate_102")); //$NON-NLS-1$
		
		
		checkUserRequest();
		boolean error = false;
		// generate
		generalMonitor.subTask(Activator.Messages.getString("Generate.48")); //$NON-NLS-1$
		error &= generate_(configuration, modelsInfo, configurationParameters, generationParameters);
		generalMonitor.taskDone(null); //$NON-NLS-1$
		checkUserRequest();
		// deploy
		generalMonitor.subTask(Activator.Messages.getString("Generate.49")); //$NON-NLS-1$
		error &= deploy_(configuration, modelsInfo, configurationParameters, generationParameters);
		generalMonitor.taskDone(null); //$NON-NLS-1$
		checkUserRequest();
		if (!error) {
			generalMonitor.addText(Activator.Messages.getString("Generate.21")); //$NON-NLS-1$
		} else {
			generalMonitor.addErrorText(Activator.Messages.getString("Generate.22")); //$NON-NLS-1$
		}

		// write general log file
		try {
			if (generalMonitor != null && generalMonitor.getConsoleLog() != null) {
				generalMonitor.getConsoleLog().saveLog();
			}
		} catch (Exception e1) {
			generalMonitor.addErrorText(Activator.Messages.getString("Generate_103", e1.getMessage())); //$NON-NLS-1$
			e1.printStackTrace();
		}
		generalMonitor.beginTask(Activator.Messages.getString("Generate_1"));
		// Log and feedback
		saveSideReportAndFeedBack();

		// Refresh log and generation folder
		refreshFolders();
	}

	private void clean() throws CoreException {
		IFileHelper.deleteFolderContent(IFileHelper.getIFolder(logPath));
		IFileHelper.deleteFolderContent(IFileHelper.getIFolder(genPath));
	}

	private AbstractGenerator getGeneratorInstance(GeneratorConfiguration elem) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String launchGeneratorClass = elem.getImpl_class();
		String idGenerator = elem.getId();
		Bundle plugin = Platform.getBundle(elem.getContributorId());
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

	private boolean generate_(final Configuration configuration, final HashMap<String, List<IFile>> modelsInfo, final Map<String, String> configurationParameters,
			final Map<String, String> generationParameters) throws MustBeStopped, Exception {

		// For all generator version we will call generation method
		boolean error = false;
		for (GeneratorConfiguration elem : configuration.getGeneratorConfigurations()) {
			checkUserRequest();
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
					String fileName = "gen_" + generator.getClass().getName() + ".xml"; //$NON-NLS-1$ //$NON-NLS-2$
					componentMonitor.initialize(nbTask, configurationParameters, LogType.GENERATION, fileName);

					String name = elem.getGeneratorName();
					componentMonitor.beginTask(Activator.Messages.getString("Generate.30", name)); //$NON-NLS-1$

					try {
						List<ModuleConstraint> lmc = new ArrayList<ModuleConstraint>();
						EList<com.bluexml.side.application.ModuleConstraint> l = elem.getModuleContraints();
						for (int c = 0; c < l.size(); c++) {
							com.bluexml.side.application.ModuleConstraint current = l.get(c);
							lmc.add(new ModuleConstraint(current.getModuleId(), current.getTechnologyVersion(), current.getModuleType(), current.getVersionMin(), current.getVersionMax()));
						}

						DependencesManager dm = new DependencesManager(lmc, isOfflineMode(configurationParameters));

						generator.initialize(generationParameters, generatorOptions, configurationParameters, dm, componentMonitor);
					} catch (Exception e) {
						error = true;
						throw new Exception(Activator.Messages.getString("Generate.32", e.getMessage()), e);
					}

					this.componentMonitor.taskDone(Activator.Messages.getString("Generate.8")); //$NON-NLS-1$
					if (generator.check()) {
						// The first one
						if (modelsInfo.size() > 0) {
							// Generate
							try {
								this.componentMonitor.beginTask(Activator.Messages.getString("Generate.33", name)); //$NON-NLS-1$
								generator.generate(modelsInfo, elem.getId_metamodel());

								this.componentMonitor.taskDone(Activator.Messages.getString("Generate.34")); //$NON-NLS-1$
							} catch (Exception e) {
								error = true;
								throw new Exception(Activator.Messages.getString("Generate.39", e.getMessage()), e);
								// fatalError("Generate.39", e, this.componentMonitor); //$NON-NLS-1$
							}
							this.componentMonitor.beginTask(Activator.Messages.getString("Generate.35", name)); //$NON-NLS-1$

							// Complete
							try {
								generator.complete();
							} catch (Exception e) {
								error = true;
								throw new Exception(Activator.Messages.getString("Generate.61", e.getMessage()), e);
								//								fatalError("Generate.61", e, this.componentMonitor); //$NON-NLS-1$
							}
							this.componentMonitor.taskDone(Activator.Messages.getString("Generate.36")); //$NON-NLS-1$

							try {
								generator.createStampFile();
							} catch (Exception e) {
								error = true;
								throw new Exception(Activator.Messages.getString("Generate.42", e.getMessage()), e);
								//								fatalError("Generate.42", e, this.componentMonitor); //$NON-NLS-1$
							}
						}
					} else {
						this.componentMonitor.addErrorTextAndLog(Activator.Messages.getString("Generate.44", elem.getId()), null, null); //$NON-NLS-1$ //$NON-NLS-2$
						error = true;
						this.componentMonitor.skipTasks(NB_GENERATION_STEP);
					}
				} else {
					if (!generator.isDocumentationGenerator()) {
						generalMonitor.addWarningText(Activator.Messages.getString("Generate.58", elem.getId())); //$NON-NLS-1$
					}
					generalMonitor.skipTasks(NB_GENERATION_STEP);
				}

				try {
					if (generator.getMonitor() != null) {
						generator.getMonitor().getLog().saveLog(); //$NON-NLS-1$
					}

				} catch (Exception e) {
					error = true;
					throw new Exception(Activator.Messages.getString("Generate.62", e.getMessage()), e);
					//					fatalError("Generate.62", e, generalMonitor); //$NON-NLS-1$
				}
			} else {
				generalMonitor.skipTasks(NB_GENERATION_STEP);
			}
		}
		return error;
	}

	private boolean deploy_(final Configuration configuration, final HashMap<String, List<IFile>> modelsInfo, final Map<String, String> configurationParameters,
			final Map<String, String> generationParameters) throws MustBeStopped, Exception {
		boolean error = false;

		List<DeployerConfiguration> ldeployers = configuration.getDeployerConfigurations();
		List<DeployerConfiguration> sharedDeployers = new ArrayList<DeployerConfiguration>();

		for (DeployerConfiguration depConf : ldeployers) {
			if (depConf.isShared()) {
				sharedDeployers.add(depConf);
			}
		}
		ldeployers.removeAll(sharedDeployers);

		// Call shared deployers
		for (DeployerConfiguration depConf : sharedDeployers) {
			error &= launchDeployer(depConf, configuration, configurationParameters, generationParameters);
		}
		// Call others deployers
		for (DeployerConfiguration depConf : ldeployers) {
			error &= launchDeployer(depConf, configuration, configurationParameters, generationParameters);
		}
		// Re-call shared deployers
		for (DeployerConfiguration depConf : sharedDeployers) {
			error &= launchDeployer(depConf, configuration, configurationParameters, generationParameters);
		}

		return error;
	}

	private boolean launchDeployer(DeployerConfiguration depConf, Configuration configuration, Map<String, String> configurationParameters, Map<String, String> generationParameters)
			throws MustBeStopped, Exception {

		checkUserRequest();
		boolean error = false;
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

		Bundle plugin = Platform.getBundle(depConf.getContributorId());
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

			String fileName = "dep_" + deployer.getClass().getName() + ".xml"; //$NON-NLS-1$ //$NON-NLS-2$
			this.componentMonitor.initialize(nbTask, configurationParameters, LogType.DEPLOYMENT, fileName);
			// deployer initialization
			deployer.initialize(configurationParameters, generationParameters, deployerOptions, this.componentMonitor);
			boolean showEndMessage = false;
			if ((deployer.isDocumentationDeployer() && doDocumentation) || !deployer.isDocumentationDeployer()) {

				try {
					this.componentMonitor.beginTask(Activator.Messages.getString("Generate.51", depConf.getDeployerName())); //$NON-NLS-1$
					showEndMessage = true;
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
					error = true;
					throw new Exception(Activator.Messages.getString("Generate.56", e.getMessage()), e);
					// fatalError("Generate.56", e, this.componentMonitor); //$NON-NLS-1$
				}

				try {
					deployer.moveStampFile(logPath);
				} catch (Exception e) {
					e.printStackTrace();
					this.componentMonitor.addWarningTextAndLog(Activator.Messages.getString("Generate.57") + e.getMessage(), null); //$NON-NLS-1$
				}

				try {
					this.componentMonitor.getLog().saveLog(); //$NON-NLS-1$
				} catch (Exception e) {
					error = true;
					throw new Exception(Activator.Messages.getString("Generate.62", e.getMessage()), e);
					//					fatalError("Generate.62", e, generalMonitor); //$NON-NLS-1$
				}
			} else {
				this.componentMonitor.skipTasks(NB_DEPLOY_STEP);
			}
			if (showEndMessage) {
				this.componentMonitor.taskDone(Activator.Messages.getString("Generate.52")); //$NON-NLS-1$
			} else {
				this.componentMonitor.taskDone(null); //$NON-NLS-1$
			}

		}
		return error;
	}

	protected void fatalError(Throwable e) {
		String message = e.getMessage();
		if (componentMonitor.isInitialized()) {
			// so we can log in current componentMonitor
			componentMonitor.addErrorTextAndLog(message, e, null);
			// save component log file
			try {
				componentMonitor.getLog().saveLog();
			} catch (Exception e1) {
				componentMonitor.addErrorText(e1.getMessage());
				e1.printStackTrace();
			}
			// set labels
			componentMonitor.setLabel(message);
		}
		// manage generalMonitor
		generalMonitor.setLabel(message);
		try {
			generalMonitor.getConsoleLog().saveLog();
		} catch (Exception e1) {
			generalMonitor.addErrorText(e1.getMessage());
			e1.printStackTrace();
		}
		saveSideReportAndFeedBack();
		generalMonitor.skipAllTasks(true);
		e.printStackTrace();
		// if (Activator.getDefault() != null && Activator.getDefault().getLog()
		// != null) {
		// Activator.getDefault().getLog().log(new Status(Status.ERROR,
		// Activator.PLUGIN_ID, message, e));
		// } else {
		// new Activator().getLog().log(new Status(Status.ERROR,
		// Activator.PLUGIN_ID, message, e));
		// }
	}

	private void saveSideReportAndFeedBack() {
		try {
			LogSave.buildGeneraLogFile(logPath);
			IFileHelper.refreshFolder(logPath);

		} catch (Exception e) {
			generalMonitor.addErrorText(Activator.Messages.getString("Generate_104", e.getMessage())); //$NON-NLS-1$
			e.printStackTrace();
		}
		saveFeedBack();
	}

	private void saveFeedBack() {
		if (FeedbackActivator.doFeedback()) {
			// Feedback
			try {
				feedbackManager.save();
				// FeedbackSender.send();
			} catch (IOException e) {
				generalMonitor.addErrorText(Activator.Messages.getString("Generate_105", e.getMessage())); //$NON-NLS-1$
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.jobs.Job#belongsTo(java.lang.Object)
	 */
	@Override
	public boolean belongsTo(Object family) {
		return (family instanceof Generate);
	}

	/**
	 * method to check user request (cancel), if requested this method throw a
	 * MustBeStopped, this throwable can be catch at the top level of this job
	 * 
	 * @throws MustBeStopped
	 */
	private void checkUserRequest() throws MustBeStopped {
		if (generalMonitor.isCanceled()) {
			throw new MustBeStopped();
		}
	}

	// @Override
	// protected IStatus run(IProgressMonitor monitor) {
	// return run_(monitor);
	// }

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
		return run_(monitor);
	}

}
