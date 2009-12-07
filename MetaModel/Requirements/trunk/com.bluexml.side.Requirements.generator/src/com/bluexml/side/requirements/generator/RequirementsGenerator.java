package com.bluexml.side.requirements.generator;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;

import com.bluexml.side.util.generator.acceleo.AbstractAcceleoGenerator;

abstract public class RequirementsGenerator extends AbstractAcceleoGenerator {

	protected static String PLUGIN_ID = "com.bluexml.side.Requirements.generator";
	
	abstract protected Map<String,String> getInputModels(String keyGenerator);
	abstract protected Map<String,String> getOutputModels(String keyGenerator);
	abstract protected String getASMFile(String keyGenerator);
	abstract protected String getTargetMetamodel(String keyGenerator);
	abstract protected List<String> getANTScripts(String keyGenerator);
	abstract protected Set<String> getTransformation();
	abstract protected List<String> getTemplates(String keyGenerator);
	abstract protected String getTargetModelName(String keyGenerator);
	
	static private String current_keyGenerator;
	
	@Override
	public Collection<IFile> generate(IFile model) throws Exception {
		Collection<IFile> result = null;
		try {
			IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
			// Temporary project
			IProject tmpProject = myWorkspaceRoot.getProject(".requirements");

			// create and open if necessary
			if (!tmpProject.exists()) {
				tmpProject.create(null);
			}
			if (!tmpProject.isOpen()) {
				tmpProject.open(null);
			}
			
			/*tmpProject.setDefaultCharset("UTF-8", null);

			IFolder outputFolder = tmpProject.getFolder("output");
			if (!outputFolder.exists())
				outputFolder.create(true, true, null);
			IFolder modelFolder = tmpProject.getFolder("models");
			if (!modelFolder.exists())
				modelFolder.create(true, true, null);*/

			// Create sub-directory
			IPath path = model.getFullPath().removeLastSegments(1).removeFirstSegments(1);
			IFolder newFolder = model.getProject().getFolder(path.toString() + File.separator + "Generated files for " + model.getName());
			if (newFolder.exists())
				newFolder.delete(true, null);

			result = execute(model);

			//Delete the temporary project
			tmpProject.delete(true, new NullProgressMonitor());

		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private Collection<IFile> execute(IFile model) throws Exception {
		Collection<IFile> result = new HashSet<IFile>();
		for (String keyGenerator : getTransformation()) {
			monitor.addText("Start of model interpretation");
			current_keyGenerator = keyGenerator;

			// Execute transformation
			TransformModel t = new TransformModel();
			t.setASMFile(getASMFile(keyGenerator));

			for (String key : getInputModels(keyGenerator).keySet()) {
				String _modelName = key;
				String _metamodelName = getInputModels(keyGenerator).get(key);
				//By default
				String _metamodelFile = "/com.bluexml.side.Requirements/model/requirements.ecore";
				String _modelFile = model.getRawLocation().toString();
				
				t.addInputModel(_modelName, _metamodelName, _modelFile, _metamodelFile);
			}
			for (String key : getOutputModels(keyGenerator).keySet()) {
				String _modelName = key;
				String _metamodelName = getOutputModels(keyGenerator).get(key);
				//By default
				String _metamodelFile = "/com.bluexml.side.Requirements.generator/" + getTargetMetamodel(keyGenerator);
				String _modelFile = getTargetPath() + File.separator + getTargetModelName(keyGenerator);
				
				t.addOutputModel(_modelName, _metamodelName, _modelFile, _metamodelFile);
			}
			t.setContributor(Activator.getDefault().getBundle().getSymbolicName());

			/*if (elt.getAttribute("new_name") == null || elt.getAttribute("new_name").length() == 0)
			outputModelName = model.getName() + ".tmp";
		else
			outputModelName = elt.getAttribute("new_name");*/

			//Serialize input model file with xmi:id
			/*String newInputModelName = modelFolder.getLocation().toFile() + File.separator + model.getName();
		IFile newInputModelFile = SIDE_XMIResource.export(model, newInputModelName);*/

			try {
				t.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Set<String> outModels = t.getOutputModels();

			for (String out : outModels) {
				IPath p = new Path(out);
				IFile omodel = ResourcesPlugin.getWorkspace().getRoot().getFile(p);
				if (omodel.exists())
					result.addAll(super.generate(omodel));
			}

			// Execute ANT scripts
			ExecuteANTScript a = new ExecuteANTScript();
			a.setContributor(PLUGIN_ID);
			for (String script : getANTScripts(keyGenerator)) {
				a.addScript(script);
			}
			IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
			a.execute(myWorkspaceRoot.getFolder(new Path(getTemporaryFolder())));

			//Delete models
			for (String out : outModels) {
				IPath p = new Path(out);
				IFile omodel = ResourcesPlugin.getWorkspace().getRoot().getFile(p);
				if (omodel.exists())
					omodel.delete(true, null);
			}	
			monitor.addText("End of model interpretation");
		}
		return result;
	}
	
	@Override
	final protected List<String> getTemplates() {
		return getTemplates(current_keyGenerator);
	}
}
