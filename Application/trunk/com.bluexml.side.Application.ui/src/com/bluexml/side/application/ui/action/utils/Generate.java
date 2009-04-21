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
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
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
		// Check to know if Log Path and Generation Path are set :
		
		
		progressBar = p_progressBar;
		label = p_label;
		styletext = p_styletext;
		// First we get the meta-model associated to a model
		HashMap<String, List<IFile>> modelsInfo = null;
		try {
			modelsInfo = (HashMap<String, List<IFile>>) getAssociatedMetaModel(models);
		} catch (IOException e) {
			addText("Error with model : " + e.getMessage(), true);
			e.printStackTrace();
		}

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
		
		generate(configuration, modelsInfo, configurationParameters,
				generationParameters);
	}
	
	static private void  addText(String text) {
		addText(text, false);
	}

	static private void addText(String text, boolean error) {
		if (!error) {
			styletext.append(text);
		} else {
			StyleRange style2 = new StyleRange();
			style2.start = styletext.getText().length();
			style2.length = text.length();
			style2.foreground = Display.getDefault().getSystemColor(
					SWT.COLOR_RED);
			styletext.append(text);
			styletext.setStyleRange(style2);
		}
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
						// gen = Class.forName(launchGeneratorClass);
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
						AbstractGenerator generator = (AbstractGenerator) genObj;
						// We generate only if there is meta-model available for
						// the generator
						if (generator.shouldGenerate(modelsInfo, elem
								.getId_metamodel())) {
							String name = elem.getId_generator()
									.substring(
											elem.getId_generator().lastIndexOf(
													".") + 1);
							label.setText("Initialize " + name);
							addText(System.getProperty("line.separator")
									+ "Generation for " + name);
							generator.initialize(generationParameters,
									generatorOptions, configurationParameters);
							addOneStep(progressBar);

							label.setText("Merging models for " + name);
							// TODO : CALL MERGE METHOD!!!
							addOneStep(progressBar);

							// The first one
							if (modelsInfo.size() > 0) {
								try {
									label.setText("Generate for " + name);
									generator.generate(modelsInfo, elem
											.getId_metamodel());

									label.setText(System
											.getProperty("line.separator")
											+ "Completing generation for "
											+ name);
									Collection<IFile> generatedFiles = new ArrayList<IFile>();
									generatedFiles = generator.complete();

									addText(System
											.getProperty("line.separator")
											+ "Files generated by" + name);
									if (generatedFiles != null) {
										for (IFile filePath : generatedFiles) {
											addText(System
													.getProperty("line.separator")
													+ filePath.getRawLocation()
															.makeAbsolute()
															.toOSString());
										}
									}
									addOneStep(progressBar);
									if (generator.doDeploy()) {
										label.setText("Deploy " + name);
										generator.deploy();
										addOneStep(progressBar);
									}

								} catch (Exception e) {
									error = true;
									addText(System
											.getProperty("line.separator")
											+ "ERROR : "
											+ (e.getMessage() != null ? e
													.getMessage() : "")
											+ " see log file.", true);
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
	 * @throws IOException
	 */
	private static Map<String, List<IFile>> getAssociatedMetaModel(
			List<Model> models) throws IOException {
		Map<String, List<IFile>> result = new HashMap<String, List<IFile>>();
		for (Model model : models) {
			Resource modelResource = null;
			try {
				modelResource = EResourceUtils.createResource(model.getFile());
			} catch (IOException e) {
				throw new IOException("Error for file/model " + model.getName());
			}
			ResourceSet rs = modelResource.getResourceSet();

			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(
					new Path(model.getFile()));
			if (!file.exists()) {
				throw  new IOException("File " + file.getName()
						+ " doesn't exist.");
			}
			String fullPath = file.getRawLocation().toOSString();
			Resource loadedModel;
			try {
				loadedModel = EResourceUtils.openModel(fullPath, null, rs);
			} catch (IOException e) {
				IOException ioe = new IOException("Error with file " + file.getName() + " (check that it's a correct model file)" );
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
					throw new IOException("No model found at "
							+ file.getFullPath());
				}
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
