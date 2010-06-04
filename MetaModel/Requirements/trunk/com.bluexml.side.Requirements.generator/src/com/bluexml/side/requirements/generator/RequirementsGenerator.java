package com.bluexml.side.requirements.generator;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
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
			result = execute(model);
			computeServices();
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

			IPath targetPath = new Path(getTargetPath());
			targetPath = targetPath.append(new Path(getTEMP_FOLDER()));
			for (String key : getOutputModels(keyGenerator).keySet()) {
				String _modelName = key;
				String _metamodelName = getOutputModels(keyGenerator).get(key);
				//By default
				String _metamodelFile = "/com.bluexml.side.Requirements.generator/" + getTargetMetamodel(keyGenerator);
				String _modelFile = targetPath.append(getTargetModelName(keyGenerator)).toString();
				
				t.addOutputModel(_modelName, _metamodelName, _modelFile, _metamodelFile);
			}
			t.setContributor(Activator.getDefault().getBundle().getSymbolicName());

			try {
				t.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Set<String> outModels = t.getOutputModels();

			for (String out : outModels) {
				IPath p = new Path(out.replaceAll("%20", " "));
				IFile omodel = ResourcesPlugin.getWorkspace().getRoot().getFile(p);
				if (omodel.exists())
					result.addAll(super.generate(omodel));
			}
			
			postGeneration();
			
			// Execute ANT scripts
			ExecuteANTScript a = new ExecuteANTScript();
			a.setContributor(PLUGIN_ID);
			for (String script : getANTScripts(keyGenerator)) {
				a.addScript(script);
			}
			IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
			a.execute(myWorkspaceRoot.getFolder(new Path(getTemporaryFolder())));

			monitor.addText("End of model interpretation");
		}
		return result;
	}
	
	@Override
	final protected List<String> getTemplates() {
		return getTemplates(current_keyGenerator);
	}
	
	protected void computeServices() throws CoreException {
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFolder targetFolder = myWorkspaceRoot.getFolder(new Path(getTemporaryFolder()));
		if (targetFolder.exists()) {
			for (File f : targetFolder.getRawLocation().toFile().listFiles()) {
				if (f.getName().contains(".")) {
					String ext = f.getName().substring(f.getName().lastIndexOf('.'));
					if (getExtensionsForServices().contains(ext)) {
						String url = f.getAbsolutePath();
						url = url.replaceAll(" ", "%20");
						monitor.getLog().addServiceLog("Generated document",f.getName(), url);
					}
				}
			}
		}
	}
	
	protected Collection<String> getExtensionsForServices() {
		return Collections.emptySet();
	}
	
	protected void postGeneration() {
		//By default, nothing to do
	}
	
}
