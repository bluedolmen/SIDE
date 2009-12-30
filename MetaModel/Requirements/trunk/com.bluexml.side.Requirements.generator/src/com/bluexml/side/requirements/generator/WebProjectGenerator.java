package com.bluexml.side.requirements.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jface.dialogs.MessageDialog;

import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.requirements.AnnotableElement;
import com.bluexml.side.requirements.Annotation;
import com.bluexml.side.requirements.RequirementsDefinition;

public class WebProjectGenerator extends RequirementsGenerator {

	private static String MM_URI = "http://www.kerblue.org/requirements/1.0";
	
	@Override
	protected String getMetamodelURI() {
		return MM_URI;
	}

	@Override
	protected List<String> getTemplates(String keyGenerator) {
		List<String> l = new ArrayList<String>();
		l.add("/"+PLUGIN_ID+"/src/interpretation/webproject/generation/data.mt");	
		l.add("/"+PLUGIN_ID+"/src/interpretation/webproject/generation/main_edit.mt");	
		l.add("/"+PLUGIN_ID+"/src/interpretation/webproject/generation/main.mt");	
		return l;
	}

	@Override
	public Collection<IFile> complete() throws Exception {
		//Nothing to do
		return null;
	}
	
	@Override
	protected Map<String, String> getInputModels(String keyGenerator) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("IN", "RWM");
		return result;
	}

	@Override
	protected Map<String, String> getOutputModels(String keyGenerator) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("OUT", "WebProject");
		return result;
	}

	@Override
	protected List<String> getANTScripts(String keyGenerator) {
		return Collections.singletonList("src/interpretation/webproject/generation/build-default/");
	}

	@Override
	protected String getASMFile(String keyGenerator) {
		return "src/interpretation/webproject/transformation/RWM2WebProject.asm";
	}

	@Override
	protected String getTargetMetamodel(String keyGenerator) {
		return "src/interpretation/webproject/webproject.ecore";
	}

	@Override
	protected Set<String> getTransformation() {
		return Collections.singleton("webProject generator");
	}

	@Override
	protected String getTargetModelName(String keyGenerator) {
		return "webproject.ecore";
	}
	
	@Override
	public Collection<IFile> generate(IFile model) throws Exception {
		Collection<IFile> result = super.generate(model);

		//Export annotations
		RequirementsDefinition def = null;
		try {
			Map<?,?> m = new HashMap<Object, Object>();
			Resource r  = EResourceUtils.openModel(URI.createFileURI(model.getLocation().toString()), m);
			XMIResource xmi = (XMIResource) r;
			Set<Annotation> annotations = new HashSet<Annotation>();
			if (xmi.getContents().get(0) instanceof RequirementsDefinition) {
				def = (RequirementsDefinition) xmi.getContents().get(0);
				for (Iterator<EObject> iterator = def.eAllContents(); iterator.hasNext();) {
					EObject obj = (EObject) iterator.next();
					if (obj instanceof Annotation) {
						Annotation a = (Annotation) obj;
						annotations.add(a);
					}
				}
			}
			
			IFile sqlSchema = null;
			for (IFile file : result)
				if (file.getName().endsWith(".sql"))
					sqlSchema = file;
			
			if (sqlSchema != null) {
				FileWriter fstream = new FileWriter(sqlSchema.getRawLocation().toString(),true);
	        	BufferedWriter out = new BufferedWriter(fstream);
				for (Annotation a : annotations) {
					String sql = "";
					sql = "INSERT IGNORE INTO `annotation` (" +
							"`id` ," +
							"`elementId` ," +
							"`author` ," +
							"`annotation` ," +
							"`comment` ," +
							"`date`" +
							")VALUES (" +
							"	'"+a.getId()+"', " +
							"	'"+((AnnotableElement)a.eContainer()).getId()+"', " +
							"	'"+a.getAuthor()+"', " +
							"	'"+a.getAnnotation().replaceAll("'","\\\\'")+"', " +
							"	'"+a.getComment().replaceAll("'", "\\\\'")+"', " +
							"	'"+a.getDate()+"');\n";
					out.write(sql);
				}
				out.close();
			}
		} catch (Exception e1) {
			MessageDialog.openError(null, "Problem during reading of input model !", e1.getMessage());
		}
		
		return result;
	}

	
	protected void computeServices() throws CoreException {
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFolder targetFolder = myWorkspaceRoot.getFolder(new Path(getTemporaryFolder()));
		if (targetFolder.exists()) {
			for (File f : targetFolder.getRawLocation().toFile().listFiles()) {
				if (f.getName().equalsIgnoreCase("My Web Project")) {
					String url = f.getAbsolutePath();
					url = url.replaceAll(" ", "%20");
					monitor.getLog().addServiceLog("Generated prototype",f.getName(), url);
				}
			}
		}
	}
}
