package com.bluexml.side.application.ui.action.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationElement;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.generator.AbstractGenerator;

public class Generate {

	/**
	 * Launch generation on all generator version selected
	 * 
	 * @param configuration
	 * @param staticParameters
	 * @param models
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	public static void launch(Configuration configuration,
			List<String> staticParameters, List<Model> models)
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

		// Second for all generator version we will call generation method
		for (ConfigurationElement elem : configuration.getElements()) {
			String idGenerator = elem.getId_generator();

			Class<?> gen = Class.forName(idGenerator);
			Object genObj = gen.newInstance();

			// We get the option for this generator
			Map<String, Boolean> generatorOptions = new HashMap<String, Boolean>();
			for (Option option : elem.getOptions()) {
				generatorOptions.put(option.getKey(), true);
			}

			// We initialize the generator with all data collected in
			// application model
			if (genObj instanceof AbstractGenerator) {
				// We generate only of there is meta-model available for the
				// generator
				if (modelsInfo.containsKey(elem.getId_metamodel())) {
					AbstractGenerator generator = (AbstractGenerator) genObj;
					generator.initialize(generationParameters,
							generatorOptions, configurationParameters);
					//TODO : CALL MERGE METHOD!!! For now, we only give the first one
					if (modelsInfo.size() > 0) {
						try {
							generator.generate(modelsInfo.get(elem.getId_metamodel()).get(0));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
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
				Resource loadedModel = EResourceUtils.openModel(
						model.getFile(), null, rs);

				EPackage metaModel = getMetaModelEpackage(loadedModel);

				if (metaModel != null) {
					if (!result.containsKey(metaModel.getNsURI())) {
						result
								.put(metaModel.getNsURI(),
										new ArrayList<IFile>());
					}
					IFile file = ResourcesPlugin.getWorkspace().getRoot()
							.getFile(new Path(model.getFile()));
					result.get(metaModel.getNsURI()).add(file);
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
