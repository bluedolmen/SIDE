package com.bluexml.side.application.ui.action.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;

import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.application.Application;
import com.bluexml.side.application.ComponantConfiguration;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.DeployerConfiguration;
import com.bluexml.side.application.GeneratorConfiguration;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.ModelElement;
import com.bluexml.side.application.ui.action.ApplicationDialog;
import com.bluexml.side.application.ui.action.tree.Deployer;
import com.bluexml.side.application.ui.action.tree.Generator;
import com.bluexml.side.application.ui.action.tree.ImplNode;

public class ApplicationUtil {
	/**
	 * Return the configuration corresponding to the given key in the current
	 * configuration. Return null if not found.
	 * 
	 * @param key
	 * @return
	 */
	public static ConfigurationParameters getConfigurationParmeterByKey(String key) {
		ConfigurationParameters result = null;
		Configuration config = ApplicationDialog.getCurrentConfiguration();
		int i = 0;
		int size = config.getParameters().size();
		while (i < size && result == null) {
			ConfigurationParameters param = config.getParameters().get(i);
			if (param.getKey().equals(key)) {
				result = param;
			}
			i++;
		}
		return result;
	}

	/**
	 * Return models of the application
	 * 
	 * @param application
	 * @return
	 */
	public static List<Model> getModels(Application application) {
		List<Model> result = new ArrayList<Model>();
		for (ModelElement elem : application.getElements()) {
			if (elem instanceof Model) {
				result.add((Model) elem);
			}
		}
		return result;
	}

	/**
	 * Delete the given generator from the given configuration
	 * @param config
	 * @param in
	 */
	public static void deleteGeneratorFromConf(Configuration config, Generator in) {
		Set<GeneratorConfiguration> eltsGc = new HashSet<GeneratorConfiguration>();
		
		for (GeneratorConfiguration gc : config.getGeneratorConfigurations()) {
			if (gc.getId().equals(in.getId()) 
					&& gc.getId_techno_version().equals(in.getParent().getId()) 
					&& gc.getId_metamodel().equals(in.getParent().getParent().getParent().getId()) ) {
				eltsGc.add(gc);
			}
		}
		config.getGeneratorConfigurations().removeAll(eltsGc);
		
		
	}
	
	/**
	 * Delete the given deployer from the given configuration
	 * @param config
	 * @param in
	 */
	public static void deleteDeployerFromConf(Configuration config,
			Deployer in) {
		Set<DeployerConfiguration> eltsDc = new HashSet<DeployerConfiguration>();
		for (DeployerConfiguration dc : config.getDeployerConfigurations()) {
			if(dc.getId().equals(in.getId()) 
					&& dc.getId_techno_version().equals(in.getParent().getId()) 
					&& dc.getImpl_class().equals(((Deployer)in).getLaunchClass())) {
				eltsDc.add(dc);
			}
		}
		config.getDeployerConfigurations().removeAll(eltsDc);
	}
	
	/**
	 * Return the list of componant configuration for a specific config
	 * @param config
	 * @return
	 */
	public static List<ComponantConfiguration> getComponantConfigurations(Configuration config) {
		List<ComponantConfiguration> l = new ArrayList<ComponantConfiguration>();
		l.addAll(config.getDeployerConfigurations());
		l.addAll(config.getGeneratorConfigurations());
		return l;
	}

	public static ComponantConfiguration getComponantConfiguration(Configuration config, String componantId) {
		List<ComponantConfiguration> l = getComponantConfigurations(config);
		for (ComponantConfiguration c : l) {
			if (c.getId().equals(componantId)) {
				return c;
			}
		}
		return null;
	}

	public static boolean ComponantConfigurationsContains(Configuration config, String componantId) {
		if (getComponantConfiguration(config, componantId) != null) {
			return true;
		}
		return false;
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
	public static Map<String, List<IFile>> getAssociatedMetaModel(List<Model> models) throws IOException {
		Map<String, List<IFile>> result = new HashMap<String, List<IFile>>();
		for (Model model : models) {
			Resource modelResource = null;
			try {
				modelResource = EResourceUtils.createResource(model.getFile());
			} catch (IOException e) {
				throw new IOException(System.getProperty("line.separator") + "Error for file/model " + model.getName());
			}
			ResourceSet rs = modelResource.getResourceSet();

			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(model.getFile()));
			if (!file.exists()) {
				throw new IOException(System.getProperty("line.separator") + "File " + file.getName() + " doesn't exist.");
			}
			String fullPath = file.getRawLocation().toOSString();
			Resource loadedModel;
			try {
				loadedModel = EResourceUtils.openModel(fullPath, null, rs);
			} catch (IOException e) {
				IOException ioe = new IOException(System.getProperty("line.separator") + "Error with file " + file.getName() + " (check that it's a correct model file)");
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
					throw new IOException(System.getProperty("line.separator") + "No model found at " + file.getFullPath());
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
				result = (EPackage) r.getContents().get(0).eClass().getEPackage();
			}
		}
		return result;
	}
	
	/**
	 * Return the root element of a model
	 * 
	 * @param model
	 * @return
	 */
	public static EObject getRootElement(Resource model) {
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
	public static EObject getTopElement(EObject eo) {
		if (eo.eContainer() != null) {
			return getTopElement(eo.eContainer());
		} else {
			return eo;
		}
	}
	
	/**
	 * Launch validation on given EObject
	 * 
	 * @param eo
	 * @return
	 */
	public static boolean validate(EObject eo) {
		Diagnostician diag = new Diagnostician();
		BasicDiagnostic diagnostics = diag.createDefaultDiagnostic(eo);
		return diag.validate(eo, diagnostics);
	}
	
	
	public static boolean validate(IFile modelFile) throws IOException {
		Resource loadedModel = null;
		String fullPath = modelFile.getRawLocation().toOSString();
		Resource modelResource = null;
		try {
			modelResource = EResourceUtils.createResource(modelFile.getFullPath().toOSString());
		} catch (IOException e) {
			throw new IOException(System.getProperty("line.separator") + "Error for file/model " + modelFile.getName());
		}
		ResourceSet rs = modelResource.getResourceSet();
		if (!modelFile.exists()) {
			throw new IOException(System.getProperty("line.separator") + "File " + modelFile.getName() + " doesn't exist.");
		}
		try {
			loadedModel = EResourceUtils.openModel(fullPath, null, rs);
			EObject te = getRootElement(loadedModel);
			if (te != null) {
				return validate(te);
			} else {
				throw new IOException(System.getProperty("line.separator") + "No root element found in " + modelFile.getFullPath() + ". Model empty?");
			}
		} catch (IOException e) {
			IOException ioe = new IOException(System.getProperty("line.separator") + "Error with file " + modelFile.getName() + " (check that it's a correct model file)");
			ioe.setStackTrace(e.getStackTrace());
			throw ioe;
		}
	}

	
}
