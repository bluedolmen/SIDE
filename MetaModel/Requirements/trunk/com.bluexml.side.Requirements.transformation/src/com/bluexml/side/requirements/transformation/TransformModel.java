package com.bluexml.side.requirements.transformation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.m2m.atl.drivers.emf4atl.ASMEMFModel;
import org.eclipse.m2m.atl.drivers.emf4atl.AtlEMFModelHandler;
import org.eclipse.m2m.atl.drivers.emf4atl.EMFModelLoader;
import org.eclipse.m2m.atl.engine.vm.AtlLauncher;
import org.eclipse.m2m.atl.engine.vm.AtlModelHandler;
import org.eclipse.m2m.atl.engine.vm.ModelLoader;
import org.eclipse.m2m.atl.engine.vm.nativelib.ASMModel;

public class TransformModel {

	private Map<Object, Object> loaders;
	private Map<Object, Object> modelCache;
	private Map<Object, Object> in;
	private Map<Object, Object> out;
	private Map<Object, Object> paths;
	private Set<String> outputModels;
	private String asmFile;
	private String contributor;
	private String inputModelName;
	private String inputMetamodelName;
	private String outputModelName;
	private String outputMetamodelName;
	private String targetMetamodel;
	
	public TransformModel() {
		loaders = new HashMap<Object, Object>();
		modelCache = new HashMap<Object, Object>();
		in = new HashMap<Object, Object>();
		out = new HashMap<Object, Object>();
		paths = new HashMap<Object, Object>();
	}

	private ModelLoader getModelLoader(AtlModelHandler amh) {
		ModelLoader ml = (ModelLoader) loaders.get(amh);
		if (ml == null) {
			ml = new EMFModelLoader();
			loaders.put(amh, ml);
		}
		return ml;
	}
	
	private static ASMModel loadModel(ModelLoader ml, String modelId, ASMModel metaModel, InputStream metamodel_stream) throws FileNotFoundException {
		ASMModel model = null;
		if (ml instanceof EMFModelLoader) {
			try {
				model = ((EMFModelLoader) ml).loadModel(modelId, metaModel, metamodel_stream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return model;
	}
	
	private void addInputModel(String model_name, InputStream model_stream, String metamodel_name, InputStream metamodel_stream) throws Exception {
		AtlModelHandler amh = AtlEMFModelHandler.getDefault("EMF");
		ModelLoader ml = getModelLoader(amh);

		ASMModel metaModel;
		ASMModel inputModel;

		metaModel = (ASMModel) in.get(metamodel_name);
		if (metaModel == null) {
			System.out.println("Input metamodel " + metamodel_name + " @ " + amh + " not yet loaded - loading from " + metamodel_stream);
			metaModel = loadModel(ml, metamodel_name, ml.getMOF(), metamodel_stream);
			in.put(metamodel_name, metaModel);
		}
		System.out.println("Using input metamodel " + metaModel);

		inputModel = (ASMModel) modelCache.get(model_name);
		if (inputModel == null) {
			System.out.println("Loading input model " + model_name + " from " + model_stream);
			inputModel = loadModel(ml, model_name, metaModel, model_stream);
			if (inputModel instanceof ASMEMFModel)
				((ASMEMFModel) inputModel).setCheckSameModel(false);
			modelCache.put(model_name, inputModel);
		}
		System.out.println("Using input model " + inputModel);
		in.put(model_name, inputModel);

		paths.put(model_name, model_stream);
		paths.put(metamodel_name, metamodel_stream);
	}
	
	private void addOutputModel(String model_name, URI model_uri, String metamodel_name, InputStream metamodel_stream) throws Exception {
		AtlModelHandler amh = AtlEMFModelHandler.getDefault("EMF");
		ModelLoader ml = getModelLoader(amh);

		ASMModel metaModel;
		ASMModel outputModel;

		metaModel = (ASMModel) out.get(metamodel_name);
		if (metaModel == null) {
			System.out.println("Loading output metamodel " + metamodel_name + " @ " + amh + " from " + metamodel_stream);
			metaModel = loadModel(ml, metamodel_name, ml.getMOF(), metamodel_stream);
			in.put(metamodel_name, metaModel);
		}
		System.out.println("Using output metamodel " + metaModel);
		System.out.println("Creating new model " + model_name + " for output");
		outputModel = ml.newModel(model_name, model_uri.toString(), metaModel);

		if (outputModel instanceof ASMEMFModel)
			((ASMEMFModel) outputModel).setCheckSameModel(false);
		out.put(model_name, outputModel);

		paths.put(model_name, model_uri);
		paths.put(metamodel_name, metamodel_stream);
	}
	
	public void execute(IFile inputModel, String outputModel) throws Exception {
		Map<Object, Object> models = new HashMap<Object, Object>();
		outputModels = new HashSet<String>();
		
		URL trans = null;
		String asmPath = asmFile;

		trans = Platform.getBundle(contributor).getEntry(asmPath);

		InputStream in_model_stream = new FileInputStream(inputModel.getFullPath().toFile());
		InputStream in_metamodel_stream = Platform.getBundle("com.bluexml.side.Requirements").getEntry("/model/requirements.ecore").openStream();
		addInputModel(inputModelName, in_model_stream, inputMetamodelName, in_metamodel_stream);

		URI out_model_uri = URI.create(outputModel);
		InputStream out_metamodel_stream = Platform.getBundle(contributor).getEntry(targetMetamodel).openStream();
		
		ResourceSet resourceSet = new ResourceSetImpl();
		org.eclipse.emf.common.util.URI emfUri = org.eclipse.emf.common.util.URI.createURI(Platform.getBundle(contributor).getEntry(targetMetamodel).toString());
		resourceSet.getResource(emfUri, true);
		
		addOutputModel(outputModelName, out_model_uri, outputMetamodelName, out_metamodel_stream);

		// add input models
		for (Iterator<Object> i = in.keySet().iterator(); i.hasNext();) {
			String mName = (String) i.next();
			models.put(mName, in.get(mName));
		}
		// add output models
		for (Iterator<Object> i = out.keySet().iterator(); i.hasNext();) {
			String mName = (String) i.next();
			models.put(mName, out.get(mName));
		}

		Map<?, ?> params = Collections.EMPTY_MAP;
		Map<?, ?> options = Collections.EMPTY_MAP;
		Map<?, ?> libs = Collections.EMPTY_MAP;
		List<?> superimpose = Collections.EMPTY_LIST;

		AtlLauncher myLauncher = AtlLauncher.getDefault();
		myLauncher.launch(trans, libs, models, params, superimpose, options);
		
		for (Iterator<Object> i = out.keySet().iterator(); i.hasNext();) {
			String mName = (String) i.next();
			ASMModel currentOutModel = (ASMModel) out.get(mName);
			String outModel = paths.get(mName).toString().substring(Platform.getLocation().toString().length());
			outputModels.add(outModel);
			currentOutModel.getModelLoader().save(currentOutModel, outModel);
			System.out.println("Wrote " + outModel);
			modelCache.put(mName, currentOutModel);
			i.remove();
		}
		
		System.out.println("Model transformation done");
	}

	public void setASMFile(String _asmFile) {
		asmFile = _asmFile;
	}

	public void setContributor(String _contributor) {
		contributor = _contributor;
	}

	public void setInputModelName(String _inputModelName) {
		inputModelName = _inputModelName;
	}

	public void setInputMetamodelName(String _inputMetamodelName) {
		inputMetamodelName = _inputMetamodelName;
	}

	public void setOutputModelName(String _outputModelName) {
		outputModelName = _outputModelName;
	}

	public void setOutputMetamodelName(String _outputMetamodelName) {
		outputMetamodelName = _outputMetamodelName;
	}

	public void setTargetMetamodel(String _targetMetamodel) {
		targetMetamodel = _targetMetamodel;
	}

	public Set<String> getOutputModels() {
		return outputModels;
	}
}
