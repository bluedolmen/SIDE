package com.bluexml.side.requirements.generator;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;

import com.bluexml.side.Util.ecore.DiagramImageExporter;
import com.bluexml.side.Util.ecore.export.JPEGExporter;
import com.bluexml.side.requirements.generator.services.DocumentationServices;

public class DocumentationGenerator extends RequirementsGenerator {

	private static String MM_URI = "http://www.kerblue.org/requirements/1.0";
	
	@Override
	protected String getMetamodelURI() {
		return MM_URI;
	}

	@Override
	protected List<String> getTemplates(String keyGenerator) {
		List<String> l = new ArrayList<String>();
		l.add("/"+PLUGIN_ID+"/src/interpretation/documentation/generation/opendocument/content_chapter.mt");	
		l.add("/"+PLUGIN_ID+"/src/interpretation/documentation/generation/opendocument/content.mt");	
		l.add("/"+PLUGIN_ID+"/src/interpretation/documentation/generation/opendocument/manifest.mt");	
		l.add("/"+PLUGIN_ID+"/src/interpretation/documentation/generation/opendocument/meta.mt");	
		l.add("/"+PLUGIN_ID+"/src/interpretation/documentation/generation/opendocument/mimetype.mt");	
		l.add("/"+PLUGIN_ID+"/src/interpretation/documentation/generation/opendocument/styles.mt");	
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
		result.put("OUT", "Documentation");
		return result;
	}

	@Override
	protected List<String> getANTScripts(String keyGenerator) {
		return Collections.singletonList("src/interpretation/documentation/generation/opendocument/build-default/");
	}

	@Override
	protected String getASMFile(String keyGenerator) {
		return "src/interpretation/documentation/transformation/RWM2CdCF.asm";
	}

	@Override
	protected String getTargetMetamodel(String keyGenerator) {
		return "src/interpretation/documentation/documentation.ecore";
	}

	@Override
	protected Set<String> getTransformation() {
		return Collections.singleton("CdCF generator");
	}

	@Override
	protected String getTargetModelName(String keyGenerator) {
		return "cdcf.ecore";
	}
	
	protected Collection<String> getExtensionsForServices() {
		return Collections.singleton(".odt");
	}

	@Override
	protected void postGeneration() {
		try {
			if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
				IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
				IFolder targetFolder = myWorkspaceRoot.getFolder(new Path(getTemporaryFolder()));
				targetFolder = targetFolder.getFolder("cdc");
				IFile myFile = targetFolder.getFile("content.xml");

				//Modify the header of content.xml
				List<String> contents = new ArrayList<String>();
				FileInputStream fis = new FileInputStream(myFile.getRawLocation().toFile());
				InputStreamReader inr = new InputStreamReader(fis, "ISO-8859-1"); 
				BufferedReader in = new BufferedReader(inr);
				String line = null;
				while (( line = in.readLine()) != null) {
					contents.add(line.concat(System.getProperty("line.separator")));
				}
				in.close();

				OutputStream fout= new FileOutputStream(myFile.getRawLocation().toFile());
			    OutputStream bout= new BufferedOutputStream(fout);
			    OutputStreamWriter out = new OutputStreamWriter(bout, "8859_1");
				for (String l : contents)
					if (l.contains("encoding=\"UTF-8\""))
						out.write(l.replace("encoding=\"UTF-8\"", "encoding=\"ISO-8859-1\""));
					else
						out.write(l);
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public Collection<IFile> generate(IFile model) throws Exception {
		DocumentationServices.clearAll();
		DocumentationServices.setModelName(model.getName());

		// Check if there is a diagram file :
		IFile diag = model.getParent().getFile(new Path(model.getName()+"di"));
		if (diag.exists()) {
			// If one generate image for each diagram :
			DiagramImageExporter diagExporter = new JPEGExporter();
			// TODO : add to result[]
			String pathName = getTemporarySystemFolder() + File.separator + "cdc" + File.separator + "Pictures" + File.separator;
			File f = new File(pathName);
			f.mkdirs();
			diagExporter.exportFile(diag, pathName);
			// Add fileName to list (to include it in generation)
			List<String> diagImgFileName = diagExporter.getFileNames();
			for (String name : diagImgFileName) {
				DocumentationServices.addDiagImgPath(name);
			}
		}
		
		model.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
		Collection<IFile> result = super.generate(model);
		return result;
	}
}
