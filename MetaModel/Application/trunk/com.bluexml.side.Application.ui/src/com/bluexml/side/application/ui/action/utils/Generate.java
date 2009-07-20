package com.bluexml.side.application.ui.action.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.osgi.framework.Bundle;

import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.DeployerConfiguration;
import com.bluexml.side.application.GeneratorConfiguration;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.application.ui.action.ApplicationDialog;
import com.bluexml.side.util.deployer.Deployer;
import com.bluexml.side.util.documentation.LogSave;
import com.bluexml.side.util.generator.AbstractGenerator;
import com.bluexml.side.util.generator.dependency.DependencesManager;
import com.bluexml.side.util.generator.dependency.ModuleConstraint;
import com.bluexml.side.util.libs.IFileHelper;

public class Generate extends Thread {

	private int NB_GENERATION_STEP = 5;
	private int NB_DEPLOY_STEP = 4;
	private ProgressBar progressBar;
	private Label label;
	private StyledText styletext;
	private String logPath;
	private Browser logLink;
	private String genPath;

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
	public void run(Configuration configuration, List<String> staticParameters, List<Model> models, ProgressBar p_progressBar, Label p_label, StyledText p_styletext, Browser p_logLink) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {

		progressBar = p_progressBar;
		label = p_label;
		styletext = p_styletext;
		logLink = p_logLink;

		// First we seek the generator parameters, and separate fields
		// of dynamic fields
		Map<String, String> configurationParameters = new HashMap<String, String>();
		Map<String, String> generationParameters = new HashMap<String, String>();
		for (ConfigurationParameters param : configuration.getParameters()) {
			if (staticParameters.contains(param.getKey())) {
				configurationParameters.put(param.getKey(), param.getValue());
				// TODO : Check to know if Log Path and Generation Path are set
				// :

			} else {
				generationParameters.put(param.getKey(), param.getValue());
				// Check to know if option have been set, no error but warning
				// message
				if (param.getValue() == null || param.getValue().length() == 0) {
					addWarningText(System.getProperty("line.separator") + "WARNING : Parameter " + param.getKey() + " isn't set.");
				}
			}
		}

		// Secondly we get the meta-model associated to a model
		HashMap<String, List<IFile>> modelsInfo = null;
		boolean skipValidation = true;
		if (configurationParameters.containsKey(ApplicationDialog.KEY_SKIPVALIDATION)) {
			skipValidation = Boolean.valueOf(configurationParameters.get(ApplicationDialog.KEY_SKIPVALIDATION));
		}

		try {
			modelsInfo = (HashMap<String, List<IFile>>) ApplicationUtil.getAssociatedMetaModel(models);
		} catch (IOException e) {
			addErrorText(System.getProperty("line.separator") + "Error with model : " + e.getMessage());
			e.printStackTrace();
		}

		// Validation :
		label.setText("Validating models");
		boolean modelWithError = false;
		if (!skipValidation) {
			Iterator<List<IFile>> it = modelsInfo.values().iterator();
			List<IFile> listModel;
			while (it.hasNext()) {
				listModel = it.next();
				for (IFile m : listModel) {
					try {
						if (ApplicationUtil.validate(m)) {
							addText(System.getProperty("line.separator") + m.getName() + " validated");
						} else {
							addErrorText(System.getProperty("line.separator") + "Model " + m.getName() + " isn't validated. Please launch 'Validate' on top model element of " + m.getName() + ".");
							modelWithError = true;
						}

					} catch (IOException e) {
						addErrorText(System.getProperty("line.separator") + "Error with model " + m.getName() + " : " + e.getMessage());
						modelWithError = true;
						e.printStackTrace();
					}
				}
			}
		}
		if (!modelWithError) {
			addOneStep(progressBar);
			logPath = getLogPath(configuration, configurationParameters);
			genPath = getGenerationPath(configuration, configurationParameters);
			generate(configuration, modelsInfo, configurationParameters, generationParameters);
			// Refresh log and generation folder
			refreshFolders();
		}
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
	private String getLogPath(Configuration configuration, Map<String, String> configurationParameters) {
		return configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral()) + System.getProperty("file.separator") + configuration.getName();
	}

	private String getGenerationPath(Configuration configuration, Map<String, String> configurationParameters) {
		return configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSDESTINATION_PATH.getLiteral()) + System.getProperty("file.separator") + configuration.getName();
	}

	private void addText(String text) {
		addText(text, SWT.COLOR_BLACK);
	}

	private void addErrorText(String text) {
		addText(text, SWT.COLOR_RED);
	}

	private void addWarningText(String text) {
		addText(text, SWT.COLOR_DARK_YELLOW);
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
				boolean error = generate_(configuration, modelsInfo, configurationParameters, generationParameters);

				error &= deploy_(configuration, modelsInfo, configurationParameters, generationParameters);

				progressBar.setSelection(progressBar.getMaximum());
				if (!error) {
					label.setText("Generation Completed");
				} else {
					label.setText("Generation completed with errors.");
				}
				try {
					LogSave.buildGeneraLogFile(logPath);
					IFileHelper.refreshFolder(logPath);
					logLink.setText("<html><body style=\"overflow:auto; background-color:#f0f0f0;\"><div style=\"font-family: Verdana; " + "color: #444;" + "text-decoration: none;" + "word-spacing: normal;" + "text-align: justify;" + "letter-spacing: 0;" + "line-height: 1.2em;"
							+ "font-size: 11px; width:100%; text-align:center;\">Log File can be found <a href=\"file:///" + IFileHelper.createFolder(logPath).getLocation().toOSString() + System.getProperty("file.separator") + LogSave.LOG_FILE_NAME
							+ "\" target=\"_blank\">here</a><br\\>(" + IFileHelper.createFolder(logPath).getLocation().toOSString() + System.getProperty("file.separator") + LogSave.LOG_FILE_NAME + ")</div></body></html>");
					logLink.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

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
			addErrorText(System.getProperty("line.separator") + "Plugin " + idGenerator + " haven't been found.");
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
			configurationParameters.put("technologyVersion", id_techno_version);
			configurationParameters.put("generatorName", elem.getGeneratorName());
			configurationParameters.put("generatorId", elem.getId());
			configurationParameters.put("metaModelName", elem.getMetaModelName());
			configurationParameters.put("technologyName", elem.getTechnologyName());
			configurationParameters.put("technologyVersionName", elem.getTechnologyVersionName());

			// We get the option for this generator
			Map<String, Boolean> generatorOptions = new HashMap<String, Boolean>();
			for (Option option : elem.getOptions()) {
				generatorOptions.put(option.getKey(), true);
			}

			AbstractGenerator generator = null;
			try {
				generator = getGeneratorInstance(elem);
			} catch (ClassNotFoundException e1) {
				addErrorText(System.getProperty("line.separator") + "Error while getting generator (" + elem.getId() + ").");
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				addErrorText(System.getProperty("line.separator") + "Error while instanciating generator (" + elem.getId() + ").");
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				addErrorText(System.getProperty("line.separator") + "Error while accessing generator (" + elem.getId() + ").");
				e1.printStackTrace();
			}

			// We initialize the generator with all data collected in
			// application model
			if (generator != null) {
				// We generate only if there is meta-model available for
				// the generator
				if (generator.shouldGenerate(modelsInfo, elem.getId_metamodel())) {
					String name = elem.getId().substring(elem.getId().lastIndexOf(".") + 1);
					label.setText("Initialize " + name);
					addText(System.getProperty("line.separator") + "Generation for " + name);

					try {
						List<ModuleConstraint> lmc = new ArrayList<ModuleConstraint>();
						EList<com.bluexml.side.application.ModuleConstraint> l =elem.getModuleContraints();
						for (int c=0;c<l.size();c++) {
							com.bluexml.side.application.ModuleConstraint current = l.get(c);
							lmc.add(new ModuleConstraint(current.getModuleId(),current.getModuleType(),current.getVersionMin(),current.getVersionMax()));
						}
						DependencesManager dm = new DependencesManager(lmc);

						generator.initialize(generationParameters, generatorOptions, configurationParameters, dm);
					} catch (Exception e) {
						error = true;
						addErrorText(System.getProperty("line.separator") + "ERROR : " + (e.getMessage() != null ? e.getMessage() : ""));
						generator.addErrorLog("Initialization error : " + e.getMessage(), e.getStackTrace(), null);
						e.printStackTrace();
					}

					addOneStep(progressBar);
					if (generator.check()) {
						// The first one
						if (modelsInfo.size() > 0) {
							try {
								label.setText("Generate for " + name);
								generator.generate(modelsInfo, elem.getId_metamodel());

								label.setText(System.getProperty("line.separator") + "Completing generation for " + name);
								Collection<IFile> generatedFiles = new ArrayList<IFile>();
								generatedFiles = generator.complete();

								addText(System.getProperty("line.separator") + "Files generated by " + name + " :");
								if (generatedFiles != null) {
									for (IFile filePath : generatedFiles) {
										addText(System.getProperty("line.separator") + filePath.getRawLocation().makeAbsolute().toOSString());
									}
								}
								addOneStep(progressBar);

							} catch (Exception e) {
								error = true;
								addErrorText(System.getProperty("line.separator") + "ERROR : " + (e.getMessage() != null ? e.getMessage() : ""));
								generator.addErrorLog("Generation error : " + e.getMessage(), e.getStackTrace(), null);
								e.printStackTrace();
							}

							try {
								generator.createStampFile();
							} catch (Exception e) {
								generator.addErrorLog("Generation error : Stamp file error. " + e.getMessage(), e.getStackTrace(), null);
								addErrorText(System.getProperty("line.separator") + "ERROR :  Stamp file error.");
								e.printStackTrace();
							}

							addOneStep(progressBar);
						}
					} else {
						addErrorText(System.getProperty("line.separator") + "ERROR : " + "this feature is not activited, please check your plugin licence");
						generator.addErrorLog("Feature not available", "Feature is not activited, please check your plugin licence", null);
					}
				}
				String fileName = "gen_" + generator.getTechVersion() + ".xml";
				LogSave.toXml(generator.getLog(), fileName, logPath + System.getProperty("file.separator") + "work" + System.getProperty("file.separator"));
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
			configurationParameters.put("technologyVersion", id_techno);
			configurationParameters.put("deployerName", depConf.getDeployerName());
			configurationParameters.put("deployerId", id_deployer);
			configurationParameters.put("metaModelName", depConf.getMetaModelName());
			configurationParameters.put("technologyName", depConf.getTechnologyName());
			configurationParameters.put("technologyVersionName", depConf.getTechnologyVersionName());
			configurationParameters.put("configurationName", configuration.getName());

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
				addErrorText(System.getProperty("line.separator") + "Depolyer " + id_deployer + " haven't been found.");
			} catch (InstantiationException e) {
				error = true;
				e.printStackTrace();
				addErrorText(System.getProperty("line.separator") + "Depolyer " + id_deployer + " can't be instanciate.");
			} catch (IllegalAccessException e) {
				error = true;
				e.printStackTrace();
				addErrorText(System.getProperty("line.separator") + "Depolyer " + id_deployer + " access error.");
			} catch (Exception e) {
				error = true;
				e.printStackTrace();
				addErrorText(System.getProperty("line.separator") + "Error getting depolyer " + id_deployer + ".");
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
				} catch (Exception e) {
					e.printStackTrace();
					error = true;
					addErrorText(System.getProperty("line.separator") + "Error during deployment. " + e.getMessage());
				}

				try {
					deployer.moveStampFile(logPath);
				} catch (Exception e) {
					e.printStackTrace();
					addWarningText(System.getProperty("line.separator") + "Error during logging. " + e.getMessage());
				}

				String fileName = "dep_" + deployer.getTechVersion() + ".xml";
				LogSave.toXml(deployer.getLog(), fileName, logPath + System.getProperty("file.separator") + "work" + System.getProperty("file.separator"));
			}
		}
		return error;
	}
}
