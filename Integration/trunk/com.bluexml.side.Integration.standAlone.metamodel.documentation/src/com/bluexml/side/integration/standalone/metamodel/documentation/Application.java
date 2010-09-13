package com.bluexml.side.integration.standalone.metamodel.documentation;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.deployer.documentation.DocumentationDeployer;
import com.bluexml.side.util.componentmonitor.ComponentMonitor;
import com.bluexml.side.util.componentmonitor.Monitor;
import com.bluexml.side.util.componentmonitor.headLessinterface.LabelInterface;
import com.bluexml.side.util.componentmonitor.headLessinterface.ProgressBarInterface;
import com.bluexml.side.util.componentmonitor.headLessinterface.StyledTextInterface;
import com.bluexml.side.util.componentmonitor.headless.LabelHeadLess;
import com.bluexml.side.util.componentmonitor.headless.StyledTextHeadless;
import com.bluexml.side.util.componentmonitor.headless.progressBarHeadLess;
import com.bluexml.side.util.documentation.structure.enumeration.LogType;
import com.bluexml.side.util.generator.documentation.DocumentationGenerator;
import com.bluexml.side.util.libs.IFileHelper;

/**
 * @author Constantin Madola
 * Standalone  meta model documentation aims to generate an opendoc documentation for each
 * meta model available in directory passed in arguments.
 * 
 */

/**
 * @author Constantin Madola
 * 
 */
public class Application implements IApplication {

	protected String[] arguments;

	private static final String TECH_NAME = "test";
	private static final String ECORE_FILE_EXTENSION = "ecore";
	private static final int MIN_ARGS_NUM = 2;
	private static final String ARGS_LIST = " Arguments are : \n " + " 0 -the path where application can find metamodels\n" + " 1 -the target folder\n";
	private static final String MSG_ERR_ARGS_MISS = " Missing arguments. \n";
	private static final String MSG_ERR_ARGS_FEW = " Too few arguments \n" + " Applications requires at least " + MIN_ARGS_NUM + " argument(s) \n";

	/*
	 * Arguments are : 0 -the path where application can find metamodels 1 -the
	 * target folder
	 */
	public Object start(IApplicationContext context) throws Exception {

		arguments = (String[]) context.getArguments().get("application.args");
		// arguments checking
		if (arguments == null) {
			throw new IllegalArgumentException(MSG_ERR_ARGS_MISS + ARGS_LIST);
		} else {
			if (arguments.length < MIN_ARGS_NUM) {
				throw new IllegalArgumentException(MSG_ERR_ARGS_FEW + MSG_ERR_ARGS_MISS);
			}
		}

		String metaModelDirPath = arguments[0];
		String targetPath = arguments[1];

		System.out.println("Starting metaModel documentation generation...");
		System.out.println("Meta Model Directory = " + metaModelDirPath + " .");
		System.out.println("Target path          = " + targetPath + " .");

		// initialize Generate Objects
		ProgressBarInterface progressBar = new progressBarHeadLess();
		LabelInterface label = new LabelHeadLess();
		ProgressBarInterface progressBar2 = new progressBarHeadLess();
		LabelInterface label2 = new LabelHeadLess();
		StyledTextInterface styletext = new StyledTextHeadless();
		

		String confName = "test";
		HashMap<String, String> configurationParameters_ = getConfigurationParameter(targetPath, TECH_NAME);
		String logPath = configurationParameters_.get(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral());
		System.out.println("logPath :" + logPath);
		System.out.println();

		// clean folders
		IFileHelper.deleteFolderContent(IFileHelper.getIFolder(logPath));
		IFileHelper.deleteFolderContent(IFileHelper.getIFolder(targetPath));
		
		// general monitor
		String fileName = "general_" + Application.class.getName() + ".xml"; //$NON-NLS-1$ //$NON-NLS-2$
		Monitor generalMonitor = new Monitor(styletext, progressBar, label, logPath, confName, fileName);

		final DocumentationGenerator gen = new MetaModelDocumentationGenerator();
		String fileName2 = "gen_" + gen.getClass().getName() + ".xml"; //$NON-NLS-1$ //$NON-NLS-2$

		// generator monitor
		ComponentMonitor generationMonitor = new ComponentMonitor(styletext, progressBar2, 100, label2, generalMonitor, configurationParameters_, LogType.GENERATION, generalMonitor.getConsoleLog(),
				fileName2);

		Map<String, String> generationParameters_ = new HashMap<String, String>();
		Map<String, Boolean> generatorOptions_ = new HashMap<String, Boolean>();
		gen.initialize(generationParameters_, generatorOptions_, configurationParameters_, null, generationMonitor);

		System.out.println(gen.getClass().getName() + " Initalized.");
		
		
		final DocumentationDeployer deployer = new DocumentationDeployer();
		String fileName3 = "dep_" + deployer.getClass().getName() + ".xml"; //$NON-NLS-1$ //$NON-NLS-2$
		ComponentMonitor deployerMonitor = new ComponentMonitor(styletext, progressBar2, 100, label2, generalMonitor, configurationParameters_, LogType.GENERATION, generalMonitor.getConsoleLog(),
				fileName3);
		
		deployer.initialize(configurationParameters_, generationParameters_, null, deployerMonitor);
		System.out.println(deployer.getClass().getName() + " Initalized.");

		File dir = new File(metaModelDirPath);
		if (dir == null || dir.exists() == false) {
			System.out.println("File " + metaModelDirPath + " does not exists");
			throw new FileNotFoundException("File " + metaModelDirPath + " does not exists");
		}

		File[] metaModelFileList = getModelFileList(dir);
		// EXIT
		if (metaModelFileList.length < 1) {
			System.out.println("Cannot find any meta model file (extension .ecore) in the specified directory.");
			System.out.println("Ensure that files exists in " + metaModelDirPath + " prior to launch");
			return EXIT_OK;
		}

		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		final IWorkspaceRoot workspaceRoot = workspace.getRoot();
		System.out.println("getWorkspace: " + workspace);
		System.out.println("getRoot: " + workspaceRoot);

		int c = 0;
		while (c < metaModelFileList.length) {
			File file = metaModelFileList[c];
			System.out.println("file: " + file.getAbsolutePath());
			IPath location = Path.fromOSString(file.getAbsolutePath());
			System.out.println("location: " + location);
			// IFile model = getMetaModelIFile(file, workspaceRoot);
			IFile model = workspaceRoot.getFileForLocation(location);
			if (model == null) {
				model = workspaceRoot.getFile(new Path(file.getAbsolutePath()));
			}
			System.out.println("model: " + model);
			String modelName = model.getName();
			System.out.println("modelName: " + modelName);

			// EXCEPTION
			// if (!(model.exists() && model.isAccessible())) {
			// throw new Exception("Requested meta model file [" + modelName +
			// "] is not accesible. \n" +
			// "This may happen when the metamodel directory path refers to a directory outside of any eclipse project");
			// } else {
			System.out.println("| generating : " + modelName);
			gen.generate(model);
			System.out.println(" -> completing : " + modelName);
			gen.complete(null);
			System.out.print(" --> deploying  : " + modelName);
			deployer.deploy();
			System.out.println(" ---> deployed  !");
			// }
			c++;
		}
		generalMonitor.getConsoleLog().saveLog();
		generationMonitor.getLog().saveLog();
		deployerMonitor.getLog().saveLog();
		System.out.println("------------");
		System.out.println("Finished  !");
		System.out.println("------------");
		return EXIT_OK;
	}

	/**
	 * This method return an IFile from a Java.io.File using 2 different method
	 * (getFile,and getFileLocatation) if one of those returns null the second
	 * one is called. This is to ensure that the IFile can be retrieved no mater
	 * absolute or relative path is used
	 * 
	 * @param file
	 * @param wsr
	 * @return
	 */
	private IFile getMetaModelIFile(File file, IWorkspaceRoot wsr) {
		IFile result = wsr.getFile(new Path(file.getAbsolutePath()));
		if (!result.exists()) {
			result = wsr.getFileForLocation((new Path(file.getAbsolutePath())));
		}
		return result;
	}

	/**
	 * Returns the configurationParameters for the meta model documentation
	 * generation
	 * 
	 * @param targetPath
	 * @param techName
	 * @return
	 */
	private HashMap<String, String> getConfigurationParameter(String targetPath, String techName) {
		HashMap<String, String> configurationParameters_ = new HashMap<String, String>();
		configurationParameters_.put(StaticConfigurationParameters.GENERATIONOPTIONSDESTINATION_PATH.getLiteral(), targetPath);
		configurationParameters_.put(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral(), targetPath + File.separator + ".." + File.separator + "logs");
		configurationParameters_.put("technologyVersion", techName);
		// configurationName, est utilis� par le depoyer pour la tache ant de
		// creation d'ODT
		// il doit etre = � techName
		configurationParameters_.put("configurationName", techName);
		return configurationParameters_;
	}

	/**
	 * This method returns a list of ecore files contained in the specified
	 * directory
	 * 
	 * @param file
	 *            Le repertoire dans lequel trouver les m�taModel
	 * @return
	 */
	private File[] getModelFileList(File file) {
		File[] result = new File[0];

		class EcoreFileFilter implements FileFilter {
			public boolean accept(File f) {
				return f != null && f.getName() != null && f.getName().endsWith("." + ECORE_FILE_EXTENSION);
			}
		}
		if (file.isDirectory()) {
			result = file.listFiles(new EcoreFileFilter());
		}
		return result;
	}

	public void stop() {
		// nothing to do

	}

}
