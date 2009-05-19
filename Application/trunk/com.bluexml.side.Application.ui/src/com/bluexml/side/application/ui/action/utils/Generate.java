package com.bluexml.side.application.ui.action.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.osgi.framework.Bundle;

import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.DeployerConfiguration;
import com.bluexml.side.application.GeneratorConfiguration;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.deployer.Deployer;
import com.bluexml.side.application.generator.AbstractGenerator;
import com.bluexml.side.application.ui.action.ApplicationDialog;

public class Generate extends Thread {

	private int NB_GENERATION_STEP = 6;
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
			modelsInfo = (HashMap<String, List<IFile>>) getAssociatedMetaModel(models, skipValidation);
		} catch (IOException e) {
			addErrorText("Error with model : " + e.getMessage());
			e.printStackTrace();
		}

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
					generator.initialize(generationParameters, generatorOptions, configurationParameters, id_techno_version);
					addOneStep(progressBar);

					label.setText("Merging models for " + name);
					
					addOneStep(progressBar);

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
				}
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
				deployer.setConfigurationParameters(configurationParameters);
				deployer.setGenerationParameters(generationParameters);

				try {
					deployer.deploy(id_techno, options);
				} catch (Exception e) {
					e.printStackTrace();
					error = true;
				}
			}
		}
		return error;
	}

	/**
	 * Return a map with association model <> metaModel name
	 * 
	 * @param models
	 * @param doValidation
	 * @return
	 * @throws IOException
	 * @throws IOException
	 */
	private Map<String, List<IFile>> getAssociatedMetaModel(List<Model> models, boolean skipValidation) throws IOException {
		Map<String, List<IFile>> result = new HashMap<String, List<IFile>>();
		for (Model model : models) {
			Resource modelResource = null;
			try {
				modelResource = EResourceUtils.createResource(model.getFile());
			} catch (IOException e) {
				throw new IOException("Error for file/model " + model.getName());
			}
			ResourceSet rs = modelResource.getResourceSet();

			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(model.getFile()));
			if (!file.exists()) {
				throw new IOException("File " + file.getName() + " doesn't exist.");
			}
			String fullPath = file.getRawLocation().toOSString();
			Resource loadedModel;
			try {
				loadedModel = EResourceUtils.openModel(fullPath, null, rs);
			} catch (IOException e) {
				IOException ioe = new IOException("Error with file " + file.getName() + " (check that it's a correct model file)");
				ioe.setStackTrace(e.getStackTrace());
				throw ioe;
			}

			EPackage metaModel = getMetaModelEpackage(loadedModel);

			if (metaModel != null) {
				if (!result.containsKey(metaModel.getNsURI())) {
					result.put(metaModel.getNsURI(), new ArrayList<IFile>());
				}

				if (file.exists()) {
					result.get(metaModel.getNsURI()).add(file);
				} else {
					throw new IOException("No model found at " + file.getFullPath());
				}
			}
			if (!skipValidation) {
				EObject te = getRootElement(loadedModel);
				if (te != null) {
					label.setText("Validating model " + loadedModel.getURI());
					if (!validate(te)) {
						throw new IOException("You have error in your model (" + file.getFullPath() + "), please run validate on first element of your model and correct error(s).");
					} else {
						addText(System.getProperty("line.separator") + "Model validated : " + file.getFullPath() + " with success.");
					}
					addOneStep(progressBar);
				} else {
					throw new IOException("No root element found in " + file.getFullPath() + ". Model empty?");
				}
			} else {
				label.setText("Validatiion skipped.");
				addOneStep(progressBar);
			}
		}
		return result;
	}

	/**
	 * Launch validation on given EObject
	 * 
	 * @param eo
	 * @return
	 */
	private boolean validate(EObject eo) {
		Diagnostician diag = new Diagnostician();
		BasicDiagnostic diagnostics = diag.createDefaultDiagnostic(eo);
		return diag.validate(eo, diagnostics);
	}

	/**
	 * Return the root element of a model
	 * 
	 * @param model
	 * @return
	 */
	private EObject getRootElement(Resource model) {
		EObject te = null;
		if (model.getContents() != null && model.getContents().size() > 0) {
			EObject eo = model.getContents().get(0);
			te = getTopElement(eo);

		}
		return te;
	}

	/**
	 * Take a EObject and will return the top container
	 * 
	 * @param eo
	 * @return
	 */
	private EObject getTopElement(EObject eo) {
		if (eo.eContainer() != null) {
			return getTopElement(eo.eContainer());
		} else {
			return eo;
		}
	}

	/**
	 * Return the meta model EPackage
	 * 
	 * @param r
	 * @return
	 */
	public EPackage getMetaModelEpackage(Resource r) {
		EPackage result = null;
		if (r != null) {
			if (r.getContents() != null && r.getContents().size() > 0) {
				result = (EPackage) r.getContents().get(0).eClass().getEPackage();
			}
		}
		return result;
	}
}
