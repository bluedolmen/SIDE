package com.bluexml.side.integration.standalone.metamodel.documentation;


import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.deployer.documentation.DocumentationDeployer;
import com.bluexml.side.util.generator.documentation.DocumentationGenerator;



/**
 * @author Constantin Madola
 * Standalone  meta model documentation aims to generate an opendoc documentation for each
 * meta model available in the repository.
 * 
 */

public class Application implements IApplication {

	protected String[] arguments;

	public Object start(IApplicationContext context) throws Exception {
		System.out.println("DAMN !!!!!");
		String filePathC ="C:/Wahork/WorkspaceRun/MetaModelDocumentationGenerator";
		String targetPathC = "/com.bluexml.side.Class/model/target";
		/*
		 * Args are :
		 * 0 -the path where application can find metamodels
		 * 1 -the target folder
		 * 4-the log folder (note that if this argument is missing args[1] will be used for log path
		 * 
		 */
		arguments = (String[]) context.getArguments().get("application.args");
		String metaModelDirPath = arguments[0];
		if(metaModelDirPath==null || metaModelDirPath.equals("")){
			metaModelDirPath = filePathC;
		}
		String targetPath = arguments[1];
		if(targetPath==null || targetPath.equals("")){
			metaModelDirPath = targetPathC;
		}

		DocumentationGenerator gen = new MetaModelDocumentationGenerator();
		HashMap<String,String>  configurationParameters_ = getConfigurationParameter(targetPath, "test");
		gen.initialize(null, null, configurationParameters_, null);
		DocumentationDeployer deployer = new DocumentationDeployer ();
		deployer.initialize(configurationParameters_, null, null);
		
		File[] metaModelFileList = getModelFileList(new File(metaModelDirPath));
		IWorkspace workspace= ResourcesPlugin.getWorkspace();
		IWorkspaceRoot workspaceRoot =workspace.getRoot();
		
		//proceed
		int c =0;
		while(c<metaModelFileList.length){
			
			File file = metaModelFileList[c];
			IFile model = getMetaModelIFile(file,workspaceRoot);
			if(!(model.exists() && model.isAccessible())){
				throw new Exception("fichier model non accessible");
			}else{
				System.out.println("generating"+model.getName());
				gen.generate(model);
				System.out.println("completing");
				gen.complete();
				System.out.println("deploying"+model.getName());
				deployer.deploy();
			}
			c++;
		}
		

		return EXIT_OK;
	}

	private IFile getMetaModelIFile(File file,IWorkspaceRoot wsr){
		IFile result = wsr.getFile(new Path(file.getAbsolutePath()));
		if(!result.exists()){
			result = wsr.getFileForLocation((new Path(file.getAbsolutePath())));
		}
		return result;
	}
	
	private HashMap<String,String> getConfigurationParameter(String targetPath,String techName){
		HashMap<String,String>  configurationParameters_ = new HashMap<String,String>();
		configurationParameters_.put(StaticConfigurationParameters.GENERATIONOPTIONSDESTINATION_PATH.getLiteral(),targetPath);
		configurationParameters_.put(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral(),targetPath);
		configurationParameters_.put("technologyVersion",techName);
		return configurationParameters_;
	}
	
	
	private File[] getModelFileList(File file){
		File[]  result =null;
		class EcoreFileFilter implements FileFilter{
			String extension = ".ecore";
			public boolean accept(File f) {	
				return f!=null&&f.getName()!=null&&f.getName().endsWith(extension);
			}
		}
		if(file.isDirectory()){
			result =file.listFiles(new EcoreFileFilter());
		}
		return result;
	}

	public void stop() {
		// nothing to do

	}

}
