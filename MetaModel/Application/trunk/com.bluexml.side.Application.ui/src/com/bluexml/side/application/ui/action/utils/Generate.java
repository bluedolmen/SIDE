package com.bluexml.side.application.ui.action.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Platform;
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
import com.bluexml.side.util.libs.IFileHelper;

public class Generate extends Thread {

	private int NB_GENERATION_STEP = 5;
	private int NB_DEPLOY_STEP = 4;
	private ProgressBar progressBar;
	private Label label;
	private StyledText styletext;
	private String logPath;
	private Browser logLink;

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
					addWarningText("WARNING : Parameter " + param.getKey() + " isn't set.");
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
			addErrorText("Error with model : " + e.getMessage());
			e.printStackTrace();
		}
		
		
		// Validation :
		label.setText("Validating models");
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
							addErrorText("Model " + m.getName() + " isn't validated. Please launch 'Validate' on top model element of " + m.getName() + ".");
						}
						
					} catch (IOException e) {
						addErrorText("Error with model " + m.getName() + " : " +e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
		addOneStep(progressBar);
		logPath = getLogPath(configuration, configurationParameters);
		generate(configuration, modelsInfo, configurationParameters, generationParameters);
	}

	/**
	 * Return the log path (folder path + configuration name)
	 * @param configuration
	 * @param configurationParameters
	 * @return
	 */
	private String getLogPath(Configuration configuration,
			Map<String, String> configurationParameters) {
		return configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral()) + System.getProperty ("file.separator") + configuration.getName();
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
					logLink.setText("<html><body style=\"overflow:auto; background-color:#f0f0f0;\"><div style=\"font-family: Verdana; "
				+ "color: #444;" + "text-decoration: none;"
				+ "word-spacing: normal;" + "text-align: justify;"
				+ "letter-spacing: 0;" + "line-height: 1.2em;"
				+ "font-size: 11px; width:100%; text-align:center;\">Log File can be found <a href=\"file:///" + IFileHelper.createFolder(logPath).getLocation().toOSString() + System.getProperty("file.separator") + LogSave.LOG_FILE_NAME + "\" target=\"_blank\">here</a></div></body></html>");
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
						generator.initialize(generationParameters, generatorOptions, configurationParameters);
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
							addOneStep(progressBar);
						}
					} else {
						addErrorText(System.getProperty("line.separator") + "ERROR : " + "this feature is not activited, please check your plugin licence");
						generator.addErrorLog("Feature not available", "Feature is not activited, please check your plugin licence", null);
					}
				}
				String fileName = "gen_" + generator.getTechVersion() + ".xml";
				LogSave.toXml(generator.getLog(),fileName, logPath);
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
			configurationParameters.put("metaModelName", depConf.getMetaModelName());
			configurationParameters.put("technologyName", depConf.getTechnologyName());
			configurationParameters.put("technologyVersionName", depConf.getTechnologyVersionName());
			
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
			} catch (InstantiationException e) {
				error = true;
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				error = true;
				e.printStackTrace();
			}

			if (genObj instanceof Deployer) {
				Deployer deployer = (Deployer) genObj;				
				deployer.initialize(configurationParameters, generationParameters, deployerOptions);
				
				try {
					deployer.deploy();
				} catch (Exception e) {
					e.printStackTrace();
					error = true;
				}
				String fileName = "dep_" + deployer.getTechVersion() + ".xml";
				LogSave.toXml(deployer.getLog(),fileName, logPath);
			}
		}
		return error;
	}
}
