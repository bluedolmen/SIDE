package com.bluexml.side.integration.standalone.enterprise;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.internal.Lists;
import com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.newSideProject.NewSIDEProjectCreator;
import com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.newSideProject.NewSIDEProjectCreator.CreateFields;
import com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.newSideProject.NewSIDEProjectCreator.CreateOptions;
import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.Util.ecore.SIDEEditorUtils;
import com.bluexml.side.application.Application;
import com.bluexml.side.application.ApplicationFactory;
import com.bluexml.side.application.ApplicationPackage;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.clazz.Model;
import com.bluexml.side.clazz.alfresco.reverse.library.ModelLibrary;
import com.bluexml.side.clazz.alfresco.reverse.library.ModelLibrary.Libraries;
import com.bluexml.side.clazz.edit.ui.actions.initializer.InitializerRegister;
import com.bluexml.side.integration.standalone.ApplicationModelGenerateGenerationJob;
import com.bluexml.side.integration.standalone.ApplicationModelJob;
import com.bluexml.side.integration.standalone.GenerateModelHelper;
import com.bluexml.side.util.libs.IFileHelper;

/**
 * This class is meant to provide a standalone (headless) generation application
 * using command-line arguments.
 * <p>
 * This headless application accepts two main modes of generation:
 * <ul>
 * <li>either by providing an application model which is generated w.r.t. the
 * given configuration name
 * <li>or by providing a data-model, which is copied in a new project in the
 * current workspace. Some default material is then generated from this
 * data-model
 * </ul>
 * 
 * @author pajot-b
 * 
 */
public class StandaloneGenerateApplication implements IApplication {
	
	public static final String DATA_DIRNAME = ModelInitializationUtils.getDirectoryNameForEditorId(SIDEEditorUtils.DATA_MODEL_EDITOR_ID);
	public static final String DATA_EXTENSION_NAME = ModelInitializationUtils.getExtensionNameForEditorId(SIDEEditorUtils.DATA_MODEL_EDITOR_ID);
	
	private final static Logger LOGGER = Logger.getLogger(StandaloneGenerateApplication.class.getName());
	private final static IProgressMonitor PROGRESS_MONITOR = new NullProgressMonitor();
		
	private long startTime;
	private CommandLineParser commandLineParser;

	public Object start(IApplicationContext context) throws Exception {

		if (!parseArguments(context)) return EXIT_OK;
		
		int exitCode = EXIT_OK;

		initLogging();

		LOGGER.fine("Starting standalone generation process");		
		startTime = System.currentTimeMillis();
		
		try {
			switchProcessOnInputType();
		} catch (ManagedErrorException e) {
			LOGGER.severe(e.getLocalizedMessage());
			exitCode = -1; 
		}
		
		double timeInSec = (System.currentTimeMillis() - startTime) / 1000.;
		LOGGER.fine(String.format("Ending standalone generation process. Processed in %s sec.", new DecimalFormat("#.###").format(timeInSec)));

		return exitCode;
	}
		
	private boolean parseArguments(IApplicationContext context) {
		String[] arguments = (String[]) context.getArguments().get("application.args"); // $NON-NLS-1$

		commandLineParser = new CommandLineParser();
		JCommander jc = new JCommander(commandLineParser);
		
		StringBuilder output = new StringBuilder();

		try {
			jc.parse(arguments);
		} catch (ParameterException pe) {
			output.append(pe.getLocalizedMessage() + '\n');
			outputUsage(jc, output);
			return false;
		}

		if (commandLineParser.help || commandLineParser.parameters.isEmpty()) {
			outputUsage(jc, output);
			return false;
		}
		
		commandLineParser.checkParameters();
		
		return true;
	}
	
	private static void outputUsage(JCommander jc, StringBuilder output) {
		jc.usage(output);
		LOGGER.info(output.toString());		
	}
	
	private void initLogging() {
		Level logLevel = Level.INFO;
		if (commandLineParser.debug) {
			logLevel = Level.FINEST;
		} else if (commandLineParser.verbose){
			logLevel = Level.FINE;
		}
		
		GenerateModelHelper.initLogger(logLevel);	
	}

	/**
	 * Analyze the provided input location to determine further processing.
	 * 
	 * @throws Exception
	 */
	private void switchProcessOnInputType() throws Exception {
		String modelFilePath = commandLineParser.parameters.get(0);
		File modelFile = new File(modelFilePath);
		
		if (!modelFile.exists()) {
			throw new FileNotFoundException(String.format("The file '%s' cannot be found", modelFilePath));
		}
		
		if (modelFile.isDirectory()) {
			modelFile = getModelFromDirectory();
		}

		IFile modelIFile = IFileHelper.getIFile(modelFile.getAbsoluteFile());
		
		if (modelIFile == null) {
			// The file is outside the workspace.
			// This file has to be a data-model file

			processClazzModelFile(modelFile);
			return;

		} else {
		
			EObject eObject = ModelInitializationUtils.getCheckedEObject(modelIFile, EObject.class);
					
			if (eObject != null) {
				EClass eClass = eObject.eClass();
				
				// Switch on file content
				if (ApplicationPackage.Literals.APPLICATION.equals(eClass)) {
					processApplicationModelIFile(modelIFile);
					return;
				} else if (ClazzPackage.Literals.MODEL.equals(eClass)) {
					// the file is already in the workspace
					final String message = "Do not know what to do with a data-model file already registered in the workspace";
					throw new ManagedErrorException(message);
				}
			}
			
		}
			
		throw new ManagedErrorException(String.format("Do not know what to do with file location '%s'", modelFilePath));

	}
	
	/**
	 * If we get a directory as input, then try to find either application files
	 * or data files. If one of both can be found, then launch the associated
	 * project. If both kind of files can be found, then return an error.
	 * <p>
	 * Further processing could use a command line parameter to switch on the
	 * desired process.
	 * 
	 * @return the first application or data model
	 * @throws Exception
	 */
	private File getModelFromDirectory() throws Exception {
		
		String directoryPath = commandLineParser.parameters.get(0);
		LOGGER.finer(String.format("Processing path '%s' as a directory", directoryPath));
		
		try {
			List<File> applicationFiles = GenerateModelHelper.retrieveModelFiles(directoryPath,GenerateModelHelper.APPLICATION_EXTENSION_NAME);
			List<File> dataFiles = GenerateModelHelper.retrieveModelFiles(directoryPath, DATA_EXTENSION_NAME);
			
			if (!applicationFiles.isEmpty() && ! dataFiles.isEmpty()) {
				String message = String.format("The provided directory '%s' contains both application and data models, please provide the full location of the file", directoryPath);
				throw new ManagedErrorException(message);
			}
			
			if (!applicationFiles.isEmpty()) {
				return applicationFiles.get(0);
			} else if (!applicationFiles.isEmpty()) {
				return dataFiles.get(0);
			}
			
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e); // this is an unexpected and unlikely exception at this stage 
		}
		
		return null;
	}
	
	/**
	 * When a data model (class model) is provided, the processing consist in
	 * generating the application model (file) with the default behavior and to
	 * launch the generation of the application model.
	 * <p>
	 * If a project name is provided then create the given project in the
	 * current workspace, else deduce the project name from the data model name.
	 * <p>
	 * The default behavior should be configured to not deploy the files in the
	 * target directory
	 * 
	 * @param dataModelFile
	 *            a non-null {@link File} containing a {@link Model} as a root
	 *            element
	 * @throws Exception
	 */
	private void processClazzModelFile(File dataModelFile) throws Exception {

		IProject newProject = createNewProject(dataModelFile);
		IFile dataModelIFile = copyDataModelFileIntoProject(dataModelFile, newProject);
		initializeDefaultModels(dataModelIFile);
		
		IFile applicationModelFile = GenerateModelHelper.findApplicationIFile(newProject);
		
		if (applicationModelFile == null) {
			throw new ManagedErrorException("Problem while generating the new project. The application-model file cannot be generated.");
		}

		if (!commandLineParser.keepDeployment) {
			removeDeploymentPhasesInApplicationModelIFile(applicationModelFile);
		}
		
		if (commandLineParser.doGenerate) {
			processApplicationModelIFile(applicationModelFile);
		}
	}


	/**
	 * Create a new {@link IProject} given a data-model File
	 * <p>
	 * The name of the project is either provided on the command line or by
	 * deducing it from the data-model file (name).
	 * 
	 * @param dataModelFile
	 * @return 
	 * @throws ManagedErrorException
	 */
	private IProject createNewProject(File dataModelFile) throws ManagedErrorException {
		
		String projectName = getNewProjectName(dataModelFile);		
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		
		if (project.exists()) {
			if (commandLineParser.force) {
				try {
					LOGGER.finer(String.format("Removing existing project '%s'", projectName));
					project.delete(true, true, new NullProgressMonitor());
				} catch (CoreException e) {
					final String message = String.format("Cannot delete the existing project '%s'. Do it by yourself before launching again this command", projectName);
					throw new ManagedErrorException(message);
				}
			} else {
				final String message = String.format("The new project '%s' cannot be created since it already exist in the workspace. Use '--force' option to override and remove the existing project.", projectName);
				throw new ManagedErrorException(message);
			}
		}
		
		CreateOptions createOptions = new CreateOptions();
		// TODO : Remove or change the BASE_MODEL_NAME value which is artificial
		// when the SIDE Project creation will be managed normally w.r.t. this
		// value
		createOptions.setValue(CreateFields.BASE_MODEL_NAME, projectName); 
		createOptions.setValue(CreateFields.BASE_DATA_MODEL_PACKAGES, null);
		createOptions.setValue(CreateFields.ALFRESCO_HOME, commandLineParser.alfrescoHome);
		createOptions.setValue(CreateFields.TECHNOLOGY, commandLineParser.alfrescoVersion);
		
		NewSIDEProjectCreator sideProjectCreator = new NewSIDEProjectCreator(project, createOptions, null);
		IWorkspaceRunnable runnable = sideProjectCreator.createWorkspaceRunnable();

		try {
			ResourcesPlugin.getWorkspace().run(runnable, PROGRESS_MONITOR);
		} catch (CoreException e) {
			final String message = String.format("Cannot create the project '%s' in the current workspace due to an unepected core exception '%s'", projectName, e.getLocalizedMessage());
			throw new ManagedErrorException(message);
		}
		
		return project;
	}

	/**
	 * Define a project-name based on the data-model file.
	 * 
	 * @param dataModelFile
	 * @return a project name
	 * @throws ManagedErrorException 
	 */
	private String getNewProjectName(File dataModelFile) throws ManagedErrorException {
		
		String projectName = commandLineParser.projectName;
		
		if (projectName == null) {
			// Deduce a project name from the data-model filename
			final String dataModelFilename = dataModelFile.getName();
			final IPath dataModelFilenameAsPath = new Path(dataModelFilename);
			final String fileExtension = dataModelFilenameAsPath.getFileExtension();
			
			if (!DATA_EXTENSION_NAME.equals(fileExtension)) {
				final String message = String.format("The extension '%s' was expected, but the provided data-model file has extension '%s'.", DATA_EXTENSION_NAME, fileExtension);
				throw new ManagedErrorException(message);
			}
			
			projectName = dataModelFilenameAsPath.removeFileExtension().lastSegment();
		}
		
		if (projectName == null) {
			final String message = "Cannot get a valid project name. Please provide one on the command line with the appropriate option.";
			throw new ManagedErrorException(message);
		}

		return projectName;
	}
	
	/**
	 * Copy the given data-model {@link File} into the project as a new
	 * {@link IFile}.
	 * 
	 * @param dataModelFile
	 * @param project
	 * @return the new created (copied) data-model {@link IFile}
	 * @throws ManagedErrorException
	 */
	private static IFile copyDataModelFileIntoProject(File dataModelFile, IProject project) throws ManagedErrorException{
		
		LOGGER.finer(String.format("Copying data-model file '%s' into project '%s'", dataModelFile.getPath(), project.getLocation()));
		
		IFolder dataModelsFolder = ModelInitializationUtils.getIFolderForEditorId(project, SIDEEditorUtils.DATA_MODEL_EDITOR_ID);
		
		IFile newIFile = null;
		
		if (dataModelsFolder != null) {
			final String fileName = dataModelFile.getName();
			newIFile = dataModelsFolder.getFile(fileName);
			
			InputStream inputFileStream = null;
			try {
				inputFileStream = new BufferedInputStream(new FileInputStream(dataModelFile));
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e); // This is a major problem at this stage
			}
			
			try {
				newIFile.create(inputFileStream, true, PROGRESS_MONITOR);
			} catch (CoreException e) {
				final String message = String.format("Cannot copy the input data-model file '%s' in the project due to a core Eclipse exception (%s)", dataModelFile.getPath(), e.getLocalizedMessage());
				throw new ManagedErrorException(message);
			} finally {
				if (inputFileStream != null) {
					try {
						inputFileStream.close();
					} catch (IOException e) {
						LOGGER.warning("Cannot close the opened input stream");
					}
				}

			}
			
			
		} else {
			final String message = "Cannot find the expected data-models folder in the project " + project.getName();
			throw new ManagedErrorException(message);
		}

		return newIFile;

	}
		
	/**
	 * Initialize other models using the now in-workspace dataModelIFile.
	 * <p>
	 * This method also use alfresco-home and alfresco-version information
	 * provided on the command-line arguments in order to define a default
	 * deployer configuration.
	 * 
	 * @param dataModelIFile
	 *            the data-model {@link IFile} defined in a SIDE project of the
	 *            current workspace.
	 * @throws Exception
	 */
	private void initializeDefaultModels(IFile dataModelIFile) throws Exception {
		
		LOGGER.finer(String.format("Initializing default model files from data-model '%s'", dataModelIFile.getName()));		
		
		// WARNING! alfrescoHome and alfrescoVersion seems to be not used correctly here
		String alfrescoHome = commandLineParser.alfrescoHome;
		String alfrescoVersion = commandLineParser.alfrescoVersion.name();
		InitializerRegister initializerRegister = InitializerRegister.getInitializerRegisterFromClassModel(dataModelIFile, alfrescoHome, alfrescoVersion);
		initializerRegister.initialize();
		
	}
	
	/**
	 * Processing on application model files consist in generating the material
	 * w.r.t. to the provided configuration.
	 * <p>
	 * The Application Model has to be in the workspace (IFile is thus the
	 * preferred localization type)
	 * 
	 * @param applicationModelIFile
	 * @throws Exception
	 */
	private void processApplicationModelIFile(IFile applicationModelIFile) throws Exception {
				
		Application applicationModel = GenerateModelHelper.getApplication(applicationModelIFile, true);
		if (applicationModel == null) {
			LOGGER.severe(String.format("Ignoring the application model file '%s' which is not a valid application model.", applicationModelIFile.getLocation()));
		}

		String configurationName = commandLineParser.configuration;
		Configuration configuration = applicationModel.getConfiguration(configurationName);
		
		if (configuration == null) {
			configuration = ApplicationUtil.getFirstConfiguration(applicationModel);
		}

		modifyRuntimeConfigurationParameters(configuration);
		
		ApplicationModelGenerateGenerationJob job = new ApplicationModelGenerateGenerationJob(applicationModel);
		if (configuration != null) job.setConfigurationProvider(new ApplicationModelJob.StaticConfigurationProvider(configuration));
		job.generate();
		
	}
	
	private void modifyRuntimeConfigurationParameters(Configuration configuration) {
		if (configuration == null) return;

		setConfigurationParameter(configuration, StaticConfigurationParameters.GENERATIONOPTION_SKIP_VALIDATION, Boolean.toString(commandLineParser.skipValidation));
		setConfigurationParameter(configuration, StaticConfigurationParameters.GENERATIONOPTIONSCLEAN, Boolean.toString(!commandLineParser.doNotClean));
		setConfigurationParameter(configuration, StaticConfigurationParameters.GENERATION_OPTION_COMPLETE, Boolean.toString(!commandLineParser.doNotPackage));
		
	}
	
	private static void setConfigurationParameter(Configuration configuration, StaticConfigurationParameters configurationParameter, String value) {
		
		boolean found = false;
		
		for (ConfigurationParameters configuredParameter : configuration.getParameters()) {
			if (configurationParameter.equals(configuredParameter)) {
				String configuredValue = configuredParameter.getValue(); 
				if (configuredValue != null && !configuredValue.equals(value)) {
					configuredParameter.setValue(value);
				}
				found = true;
				break;
			}
		}
		
		if (!found) {
			ConfigurationParameters newConfigurationParameter = ApplicationFactory.eINSTANCE.createConfigurationParameters();
			newConfigurationParameter.setDataType(configurationParameter.getLiteral());
			newConfigurationParameter.setValue(value);
			configuration.getParameters().add(newConfigurationParameter);
		}
		
	}
	
	/**
	 * Remove all the deployment configurations from the target
	 * application-model {@link IFile}.
	 * <p>
	 * Also save the new modified application-model
	 * 
	 * @param applicationModelFile
	 */
	private static void removeDeploymentPhasesInApplicationModelIFile(IFile applicationModelFile) {

		Application applicationModel = GenerateModelHelper.getApplication(applicationModelFile, false);
		
		for (Configuration configuration : ApplicationUtil.getConfigurations(applicationModel)) {
			removeDeploymentPhasesInConfiguration(configuration);
		}
		
		ApplicationUtil.saveData(applicationModelFile, applicationModel);
	}
	
	private static void removeDeploymentPhasesInConfiguration(Configuration configuration) {
		LOGGER.finer(String.format("Removing deployment phase from configuration", configuration.getName()));
		configuration.getDeployerConfigurations().clear();
	}
		
	public void stop() {
	}
	
	private static class ManagedErrorException extends Exception {
		private static final long serialVersionUID = 1L;
		
		private ManagedErrorException(String message) {
			super(message);
		}
	}
	
	public static class CommandLineParser {
		@Parameter
		public List<String> parameters = Lists.newArrayList();

		@Parameter(names = { "-h", "--help" }, description = "Display this help")
		public boolean help = false;

		@Parameter(names = { "-v", "--verbose" }, description = "Verbose logging")
		public boolean verbose = false;

		@Parameter(names = { "-d", "-debug"}, description = "Debug mode")
		public boolean debug = false;

		@Parameter(names = { "-f", "--force" }, description = "Force operation")
		public boolean force = false;

		@Parameter(names = { "-pn", "--project-name" }, description = "The new generation project name (default is deduced from model name)", validateWith = NonEmptyStringValidator.class)
		public String projectName = null;

		@Parameter(names = { "-conf", "--configuration" }, description = "The name of the launched configuration (default is first)")
		public String configuration = null;

		@Parameter(names = { "-ah", "--alfresco-home" }, description = "The path to the alfresco installation that will be updated", validateWith = PathExists.class)
		public String alfrescoHome = null;

		@Parameter(names = { "-av", "--alfresco-version" }, description = "The version of the alfresco installation which is targeted (default is ALFRESCO_34D_CE)", converter = LibrariesStringConverter.class)
		public Libraries alfrescoVersion = Libraries.ALFRESCO_34D_CE;

		@Parameter(names = { "-gen", "--generate" }, description = "Generate the Alfresco material (for data-model files)")
		public boolean doGenerate = false;
		
		@Parameter(names = { "-kdep", "--keep-deployment" }, description = "Do not remove deployment phase when generating the application file from the data-model file")
		public boolean keepDeployment = false;
		
		@Parameter(names = { "-sv", "--skip-validation" }, description = "Skip validation of model(s)")
		public boolean skipValidation = false;

		@Parameter(names = { "-dnc", "--do-not-clean" }, description = "Do not clean the previously generated material")
		public boolean doNotClean = false;

		@Parameter(names = { "-dnp", "--do-not-package" }, description = "Do not package the generated material")
		public boolean doNotPackage = false;
		
		private void checkParameters() {
			
			if (alfrescoHome != null && alfrescoVersion == null) {
				throw new ParameterException("Both or neither of the alfresco parameters should be provided.");
			}
			
		}
		
		public static class NonEmptyStringValidator implements IParameterValidator {
	
			public void validate(String name, String value) throws ParameterException {
				if (value.isEmpty()) {
					final String message = String.format("The parameter '%s' has to be a non-empty", name);
					throw new ParameterException(message);
				}
				
			}
			
		}
		
		public static class PathExists implements IParameterValidator {
	
			public void validate(String name, String value) throws ParameterException {
				File target = new File(value);
				if (!target.exists() || !target.isDirectory()) {
					final String message = String.format("The alfresco targeted directory must exist and has to be a valid directory. The path '%s' does not respect these conditions.", value);
					throw new ParameterException(message);
				}
			}
			
		}
		
		public static class LibrariesStringConverter implements IStringConverter<Libraries> {
	
			public Libraries convert(String value) {
				List<Libraries> acceptedValues = Arrays.asList(ModelLibrary.Libraries.values());
				
				for (Libraries library : acceptedValues) {
					if (library.name().equals(value)) return library;
				}
	
				// NOT FOUND
				StringBuilder acceptedValuesString = new StringBuilder();
				Iterator<Libraries> it = acceptedValues.iterator();
				while (it.hasNext()) {
					Libraries library = it.next();
					acceptedValuesString.append(library.name());
					
					if (it.hasNext()) {
						acceptedValuesString.append(',');
					}
				}
				
				final String message = String.format("The alfresco version only supports values in [%s]", acceptedValuesString.toString());
				throw new ParameterException(message);				
			}
			
		}		

	}
	
	

}
