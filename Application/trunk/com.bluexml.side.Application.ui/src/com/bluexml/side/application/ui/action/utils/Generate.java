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
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.osgi.framework.Bundle;

import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationElement;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.generator.AbstractGenerator;

public class Generate extends Thread {

	private static int NB_GENERATION_STEP = 5;
	private static ProgressBar progressBar;
	private static Label label;
	private static StyledText styletext;

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
	public static void run(Configuration configuration,
			List<String> staticParameters, List<Model> models,
			ProgressBar p_progressBar, Label p_label, StyledText p_styletext)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, IOException {
		// First we get the meta-model associated to a model
		HashMap<String, List<IFile>> modelsInfo = (HashMap<String, List<IFile>>) getAssociatedMetaModel(models);

		// Secondly we seek the generator parameters, and separate static fields
		// of dynamic fields
		Map<String, String> configurationParameters = new HashMap<String, String>();
		Map<String, String> generationParameters = new HashMap<String, String>();
		for (ConfigurationParameters param : configuration.getParameters()) {
			if (staticParameters.contains(param.getKey())) {
				configurationParameters.put(param.getKey(), param.getValue());
			} else {
				generationParameters.put(param.getKey(), param.getValue());
			}
		}
		progressBar = p_progressBar;
		label = p_label;
		styletext = p_styletext;
		generate(configuration, modelsInfo, configurationParameters,
				generationParameters);
	}

	private static void generate(final Configuration configuration,
			final HashMap<String, List<IFile>> modelsInfo,
			final Map<String, String> configurationParameters,
			final Map<String, String> generationParameters)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {

		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				// For all generator version we will call generation method
				int nbTask = configuration.getElements().size()
						* NB_GENERATION_STEP;
				progressBar.setMaximum(nbTask);
				boolean error = false;

				for (ConfigurationElement elem : configuration.getElements()) {
					String launchGeneratorClass = elem.getClass_generator();
					String idGenerator = elem.getId_generator();
					Bundle plugin = Platform.getBundle(idGenerator);
					
					
					Class<?> gen;
					Object genObj = null;
					try {
						gen = plugin.loadClass(launchGeneratorClass);
						//gen = Class.forName(launchGeneratorClass);
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

					// We get the option for this generator
					Map<String, Boolean> generatorOptions = new HashMap<String, Boolean>();
					for (Option option : elem.getOptions()) {
						generatorOptions.put(option.getKey(), true);
					}

					// We initialize the generator with all data collected in
					// application model
					if (genObj instanceof AbstractGenerator) {
						// We generate only if there is meta-model available for
						// the generator
						if (modelsInfo.containsKey(elem.getId_metamodel())) {
							AbstractGenerator generator = (AbstractGenerator) genObj;
							String name = elem.getId_generator()
									.substring(
											elem.getId_generator().lastIndexOf(
													".") + 1);
							label.setText("Initialize " + name);
							addText("\nGeneration for " + name);
							generator.initialize(generationParameters,
									generatorOptions, configurationParameters);
							addOneStep(progressBar);

							label.setText("Merging models for " + name);
							// TODO : CALL MERGE METHOD!!! For now, we only give
							addOneStep(progressBar);

							// The first one
							if (modelsInfo.size() > 0) {
								try {
									label.setText("Generate for " + name);
									generator.generate(modelsInfo.get(
											elem.getId_metamodel()).get(0));

									label.setText("\nCompleting generation for "
													+ name);
									Collection<String> generatedFiles = new ArrayList<String>(); 
									generatedFiles = generator.complete();

									addText("\nFiles generated by" + name);
									if (generatedFiles != null) {
										for (String filePath : generatedFiles) {
											styletext.setText(styletext.getText()
													+ "\n" + filePath);
										}
									}
									addOneStep(progressBar);

									label.setText("Deploy " + name);
									// TODO : CALL DEPLOY METHOD
									addOneStep(progressBar);

								} catch (Exception e) {
									error = true;
									addText("\nERROR : "
											+ (e.getMessage() != null ? e
													.getMessage() : "")
											+ " see log file.");
									e.printStackTrace();
								}
								addOneStep(progressBar);
							}
						}
					}
				}
				progressBar.setSelection(progressBar.getMaximum());
				if (!error) {
					label.setText("Generation Completed");
				} else {
					label.setText("Generation completed with errors.");
				}
			}

			private void addText(String text) {
				styletext.setText(styletext.getText() + text);
			}

			private void addOneStep(ProgressBar progressBar) {
				progressBar.setSelection(progressBar.getSelection() + 1);
			}
		});

	}

	/**
	 * Return a map with association model <> metaModel name
	 * 
	 * @param models
	 * @return
	 * @throws IOException
	 */
	private static Map<String, List<IFile>> getAssociatedMetaModel(
			List<Model> models) {
		Map<String, List<IFile>> result = new HashMap<String, List<IFile>>();
		for (Model model : models) {
			Resource modelResource = null;
			try {
				modelResource = EResourceUtils.createResource(model.getFile());
				ResourceSet rs = modelResource.getResourceSet();

				IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(
						new Path(model.getFile()));

				String fullPath = file.getRawLocation().toOSString();

				Resource loadedModel = EResourceUtils.openModel(fullPath, null,
						rs);

				EPackage metaModel = getMetaModelEpackage(loadedModel);

				if (metaModel != null) {
					if (!result.containsKey(metaModel.getNsURI())) {
						result
								.put(metaModel.getNsURI(),
										new ArrayList<IFile>());
					}

					if (file.exists()) {
						result.get(metaModel.getNsURI()).add(file);
					} else {
						// TODO
						throw new IOException("No model found at "
								+ file.getFullPath());
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return result;
	}

	/**
	 * Return the meta model EPackage
	 * 
	 * @param r
	 * @return
	 */
	public static EPackage getMetaModelEpackage(Resource r) {
		EPackage result = null;
		if (r != null) {
			if (r.getContents() != null && r.getContents().size() > 0) {
				result = (EPackage) r.getContents().get(0).eClass()
						.getEPackage();
			}
		}
		return result;
	}
}
