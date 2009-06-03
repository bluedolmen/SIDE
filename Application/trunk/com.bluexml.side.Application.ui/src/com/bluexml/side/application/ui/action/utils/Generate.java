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
import com.bluexml.side.application.deployer.Deployer;
import com.bluexml.side.application.documentation.LogSave;
import com.bluexml.side.application.generator.AbstractGenerator;
import com.bluexml.side.application.ui.action.ApplicationDialog;

public class Generate extends Thread {

	private int NB_GENERATION_STEP = 5;
	private int NB_DEPLOY_STEP = 4;
	private ProgressBar progressBar;
	private Label label;
	private StyledText styletext;

	/**
	 * Launch generation on all generator version selected
	 * 
	 * @param configuration
	 * @param staticParameters
	 * @param models
	 * @param progressBar
	 * @param label
	 * @param styletext
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	public void run(Configuration configuration, List<String> staticParameters, List<Model> models, ProgressBar p_progressBar, Label p_label, StyledText p_styletext) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {

		progressBar = p_progressBar;
		label = p_label;
		styletext = p_styletext;

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
		generate(configuration, modelsInfo, configurationParameters, generationParameters);
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
			}

		});

	}

	private AbstractGenerator getGeneratorInstance(GeneratorConfiguration elem) {
		String launchGeneratorClass = elem.getImpl_class();
		String idGenerator = elem.getId();
		Bundle plugin = Platform.getBundle(idGenerator);
		Class<?> gen;
		Object genObj = null;
		try {
			gen = plugin.loadClass(launchGeneratorClass);
			genObj = gen.newInstance();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		if (genObj instanceof AbstractGenerator) {
			AbstractGenerator generator = (AbstractGenerator) genObj;
			return generator;
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

			// We get the option for this generator
			Map<String, Boolean> generatorOptions = new HashMap<String, Boolean>();
			for (Option option : elem.getOptions()) {
				generatorOptions.put(option.getKey(), true);
			}

			AbstractGenerator generator = getGeneratorInstance(elem);

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
						generator.initialize(generationParameters, generatorOptions, configurationParameters, id_techno_version);
					} catch (Exception e) {
						error = true;
						addErrorText(System.getProperty("line.separator") + "ERROR : " + (e.getMessage() != null ? e.getMessage() : ""));
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
								e.printStackTrace();
							}
							addOneStep(progressBar);
						}
					} else {
						addErrorText(System.getProperty("line.separator") + "ERROR : " + "this feature is not activited, please check your plugin licence");
					}
				}
				generator.addInfoLog("Test", "Description de test", null);
				String fileName = generator.getTechVersion() + ".xml";
				String confName = configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral()) + System.getProperty ("file.separator") + configuration.getName();
				LogSave.toXml(generator.getLog(),fileName, confName);
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
					deployer.deploy(id_techno);
				} catch (Exception e) {
					e.printStackTrace();
					error = true;
				}
			}
		}
		return error;
	}
}
