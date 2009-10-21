package com.bluexml.side.integration.standalone.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.osgi.framework.Bundle;

import com.bluexml.side.application.Application;
import com.bluexml.side.application.ApplicationPackage;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.DeployerConfiguration;
import com.bluexml.side.application.GeneratorConfiguration;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.application.ui.action.ApplicationDialog;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.util.componentmonitor.NullComponentMonitor;
import com.bluexml.side.util.dependencies.DependencesManager;
import com.bluexml.side.util.dependencies.ModuleConstraint;
import com.bluexml.side.util.deployer.Deployer;
import com.bluexml.side.util.documentation.LogSave;
import com.bluexml.side.util.documentation.structure.enumeration.LogType;
import com.bluexml.side.util.generator.AbstractGenerator;
import com.bluexml.side.util.libs.IFileHelper;

public class Generate extends Thread {

	private String logPath;
	private String genPath;
	protected String lineSeparator = System.getProperty("line.separator"); //$NON-NLS-1$
	protected String fileSeparator = System.getProperty("file.separator"); //$NON-NLS-1$
	/* ############### GeneratePopUp.java method ################ */
	public Configuration configuration;
	public List<String> staticParameters;
	public List<Model> models;
	public Application application;

	/**
	 * Analyse the model.application file and extract the configuration
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public Generate(File filePath, String name) {

		System.out.println("Start Generate with filePath= " + filePath + " & Name: " + name);

		// Create the IFile
		IFile file = null;
		try {
			// IWorkspace ws = ResourcesPlugin.getWorkspace();
			// IProject project= ws.getRoot().getProject("StandAlone");
			// if (!project.exists())
			// project.create(null);
			// if (!project.isOpen())
			// project.open(null);
			// IPath location = new Path(filePath.getAbsolutePath());

			// file = project.getFile(location.lastSegment());

			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IPath location = Path.fromOSString(filePath.getAbsolutePath());
			file = workspace.getRoot().getFileForLocation(location);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		System.out.println("getWorkspace: " + ResourcesPlugin.getWorkspace());
		System.out.println("getRoot: " + ResourcesPlugin.getWorkspace().getRoot().exists() + " -> "
		+ ResourcesPlugin.getWorkspace().getRoot());
		System.out.println("getPath: "
		 + ResourcesPlugin.getWorkspace().getRoot().getFile(
		 new Path(filePath.getAbsolutePath())));

		System.out.println("\tfile.exists(): " + file.exists());

		URI uri = null;
		System.out.println("\tURI");
		try {

			System.out.println("\tgetRawLocation: " + file.getRawLocation());
			System.out.println("\ttoFile: " +
			file.getRawLocation().toFile());
			System.out.println("\tpath: "
			+ file.getRawLocation().toFile().getPath());

			String absolutePath = file.getRawLocation().toFile().getAbsolutePath();
			System.out.println("\tabsolutePath: " + absolutePath);

			uri = URI.createFileURI(new File(absolutePath).getAbsolutePath());

			System.out.println("URI: " + uri);

		} catch (Exception e) {
			System.out.println("Exception : " + e.getClass());
		}

		// System.out.println("\tXMI");
		XMIResource resource = new XMIResourceImpl(uri);
		// System.out.println("\tFILE INPUT");

		FileInputStream fi = null;
		try {
			fi = new FileInputStream(file.getRawLocation().toFile());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\tMAP");
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put(ApplicationPackage.eINSTANCE.getNsURI(), ApplicationPackage.eINSTANCE);
		map.put(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION, Boolean.TRUE);

		System.out.println("\tLOAD");
		try {
			resource.load(fi, map);
		} catch (IOException e) {
			System.out.println("Exception  "+e.getMessage());
			e.printStackTrace();
		} catch (Exception e1) {
			System.out.println("Exception  "+e1.getMessage());
			e1.printStackTrace();
		}

		try {
			application = (Application) resource.getContents().get(0);
			System.out.println("\tapplication: " + application);
			staticParameters = ApplicationDialog.staticFieldsName;
			System.out.println("\tstaticParameters: " + staticParameters);
			configuration = application.getConfiguration(name);
			System.out.println("\tconfiguration: " + configuration);
			models = ApplicationUtil.getModels(application);
			System.out.println("\tmodels: " + models);
		} catch (java.lang.NullPointerException e) {
			System.out.println("NullPointerException  "+e.getMessage());
			e.printStackTrace();
		} catch (Exception e1) {
			System.out.println("Exception  "+e.getMessage());
			e1.printStackTrace();
		}
		System.out.println("### Generate Done");
	}

	/* ############################### */
	/**
	 * Launch generation on all generator version selected
	 * 
	 * @param configuration
	 * @param staticParameters
	 * @param models
	 * @param logLink2
	 * @param progressBar
	 * @param label
	 * @param styletext
	 */
	// public void run(Configuration configuration, List<String>
	// staticParameters, List<Model> models) throws ClassNotFoundException,
	// InstantiationException, IllegalAccessException, IOException {
	public void run() {
		System.out.println("### Run Start");

		// First we seek the generator parameters, and separate fields
		// of dynamic fields
		Map<String, String> configurationParameters = new HashMap<String, String>();
		Map<String, String> generationParameters = new HashMap<String, String>();
		System.out.println("Get Application configuration parameters");
		for (ConfigurationParameters param : configuration.getParameters()) {
			if (staticParameters.contains(param.getKey())) {
				configurationParameters.put(param.getKey(), param.getValue());
				// TODO : Check to know if Log Path and Generation Path are set
				// :

			} else {
				generationParameters.put(param.getKey(), param.getValue());
			}
		}

		// System.out.println("log1");
		// Secondly we get the meta-model associated to a model
		System.out.println("Check if validation necessary");
		HashMap<String, List<IFile>> modelsInfo = null;
		boolean skipValidation = true;
		if (configurationParameters.containsKey(ApplicationDialog.KEY_SKIPVALIDATION)) {
			skipValidation = Boolean.valueOf(configurationParameters.get(ApplicationDialog.KEY_SKIPVALIDATION));
		}
		System.out.println("skipValidation is "+skipValidation);
		// System.out.println("log2");
		System.out.println("Get Meta-models");
		try {
			modelsInfo = (HashMap<String, List<IFile>>) ApplicationUtil.getAssociatedMetaModel(models);
		} catch (Exception e) {
			System.out.println("Exception  "+e.getMessage());
			e.printStackTrace();
		}
		// System.out.println("log3");
		// Validation :
		System.out.println("Validate models if no skip validation: skipValidation="+skipValidation);
		boolean modelWithError = false;
		if (!skipValidation) {
			Iterator<List<IFile>> it = modelsInfo.values().iterator();
			List<IFile> listModel;
			while (it.hasNext()) {
				listModel = it.next();
				for (IFile m : listModel) {
					try {
						if (!ApplicationUtil.validate(m)) {
							modelWithError = true;
						}

					} catch (Exception e) {
						System.out.println("Exception - model with error - "+e.getMessage());
						modelWithError = true;
						e.printStackTrace();
					}
				}
			}
		}
		System.out.println("Is there model with Error? : " + modelWithError);
		// System.out.println("log4");
		if (!modelWithError) {
			logPath = getLogPath(configuration, configurationParameters);
			genPath = getGenerationPath(configuration, configurationParameters);

			System.out.println("logPath: " + logPath);
			System.out.println("genPath: " + genPath);

			try {
				System.out.println("configuration: " + configuration);
				System.out.println("modelsInfo: " + modelsInfo);
				System.out.println("configurationParameters: "
				 + configurationParameters);
				 System.out.println("generationParameters: "
				 + generationParameters);
				generate(configuration, modelsInfo, configurationParameters, generationParameters);
				System.out.println("return from generate");
			} catch (ClassNotFoundException e) {
				System.out.println("Exception  "+e.getMessage());
				e.printStackTrace();
			} catch (InstantiationException e) {
				System.out.println("Exception  "+e.getMessage());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				System.out.println("Exception  "+e.getMessage());
				e.printStackTrace();
			}
			// Refresh log and generation folder
			refreshFolders();
		}
		System.out.println("### Run Done");
	}

	/**
	 * Refresh log and generation paths
	 */
	private void refreshFolders() {
		System.out.println("### refreshFolders Start");
		try {
			IFileHelper.refreshFolder(logPath);
			IFileHelper.refreshFolder(genPath);
		} catch (CoreException e) {
			System.out.println("Exception  "+e.getMessage());
			e.printStackTrace();
		}
		System.out.println("### refreshFolders Done");
	}

	/**
	 * Return the log path (folder path + configuration name)
	 * 
	 * @param configuration
	 * @param configurationParameters
	 * @return
	 */
	private String getLogPath(Configuration configuration, Map<String, String> configurationParameters) {
		return configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral()) + System.getProperty("file.separator") + configuration.getName();
	}

	private String getGenerationPath(Configuration configuration, Map<String, String> configurationParameters) {
		return configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSDESTINATION_PATH.getLiteral()) + System.getProperty("file.separator");
	}

	private void generate(final Configuration configuration, final HashMap<String, List<IFile>> modelsInfo, final Map<String, String> configurationParameters, final Map<String, String> generationParameters) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {

		System.out.println("### generate Start");
		// System.out.println("Generate: ");

		// System.out.println("\tRun");
		// Clean if needed :
		boolean doClean = Boolean.parseBoolean(configurationParameters.get(ApplicationDialog.KEY_DOCLEAN));
		System.out.println("Do clean ? : "+doClean);
		if (doClean) {
			try {
				clean();
			} catch (CoreException e) {
				System.out.println("Exception  "+e.getMessage());
				e.printStackTrace();
			}
		}

		// System.out.println("\tconfiguration: " + configuration);
		// System.out.println("\tmodelsInfo: " + modelsInfo);
		// System.out.println("\tconfigurationParameters: "
		// + configurationParameters);
		// System.out.println("\tgenerationParameters: " +
		// generationParameters);

		System.out.println("call generate_  ");
		boolean error = generate_(configuration, modelsInfo, configurationParameters, generationParameters);
		System.out.println("return generateç  error="+error);

		// System.out.println("\tError: " + error);

		System.out.println("call deploy_  ");
		error &= deploy_(configuration, modelsInfo, configurationParameters, generationParameters);
		System.out.println("return deploy_  error="+error);

		try {
			LogSave.buildGeneraLogFile(logPath);
			IFileHelper.refreshFolder(logPath);
		} catch (Exception e) {
			System.out.println("Exception  "+e.getMessage());
			e.printStackTrace();
		}

		System.out.println("### generate Done");
	}

	private void clean() throws CoreException {
		System.out.println("### clean Start");
		IFileHelper.deleteFolderContent(IFileHelper.getIFolder(logPath));
		IFileHelper.deleteFolderContent(IFileHelper.getIFolder(genPath));
		System.out.println("### clean Done");
	}

	private AbstractGenerator getGeneratorInstance(GeneratorConfiguration elem) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String launchGeneratorClass = elem.getImpl_class();
		String idGenerator = elem.getId();
		Bundle plugin = Platform.getBundle(idGenerator);
		Class<?> gen;
		Object genObj = null;
		if (plugin != null) {
			gen = plugin.loadClass(launchGeneratorClass);
			genObj = gen.newInstance();
			if (genObj instanceof AbstractGenerator) {
				AbstractGenerator generator = (AbstractGenerator) genObj;
				System.out.println("getGeneratorInstance initialized  ");
				return generator;
			}
		}
		System.out.println("getGeneratorInstance is null  ");
		return null;
	}

	private boolean generate_(final Configuration configuration, final HashMap<String, List<IFile>> modelsInfo, final Map<String, String> configurationParameters, final Map<String, String> generationParameters) {
		System.out.println("### generate_ Start");
		// For all generator version we will call generation method
		boolean error = false;

		// System.out.println("Generate_");

		for (GeneratorConfiguration elem : configuration.getGeneratorConfigurations()) {
			System.out.println("Work on generator "+elem.getGeneratorName());
			System.out.println("Generation structure is :  technologyVersion ="+elem.getId_techno_version());
			System.out.println("                           generatorId ="+elem.getId());
			System.out.println("                           metaModelName ="+elem.getMetaModelName());
			System.out.println("                           technologyName ="+elem.getTechnologyName());
			System.out.println("                           technologyVersionName ="+elem.getTechnologyVersionName());
			String id_techno_version = elem.getId_techno_version();
			configurationParameters.put("technologyVersion", id_techno_version);
			configurationParameters.put("generatorName", elem.getGeneratorName());
			configurationParameters.put("generatorId", elem.getId());
			configurationParameters.put("metaModelName", elem.getMetaModelName());
			configurationParameters.put("technologyName", elem.getTechnologyName());
			configurationParameters.put("technologyVersionName", elem.getTechnologyVersionName());

			// We get the option for this generator
			Map<String, Boolean> generatorOptions = new HashMap<String, Boolean>();
			for (Option option : elem.getOptions()) {
				generatorOptions.put(option.getKey(), true);
			}

			AbstractGenerator generator = null;
			try {
				generator = getGeneratorInstance(elem);
			} catch (ClassNotFoundException e1) {
				System.out.println("Exception  "+e1.getMessage());
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				System.out.println("Exception  "+e1.getMessage());
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				System.out.println("Exception  "+e1.getMessage());
				e1.printStackTrace();
			}

			// We initialize the generator with all data collected in
			// application model
			if (generator != null) {

				System.out.println("Initialize generator "+elem.getGeneratorName() + "on MM " + elem.getId_metamodel());
				// We generate only if there is meta-model available for
				// the generator
				if (generator.shouldGenerate(modelsInfo, elem.getId_metamodel())) {

					NullComponentMonitor generationMonitor = new NullComponentMonitor(configurationParameters, LogType.GENERATION);

					try {
						List<ModuleConstraint> lmc = new ArrayList<ModuleConstraint>();
						System.out.println("check module constraints "+elem.getModuleContraints());
						EList<com.bluexml.side.application.ModuleConstraint> l = elem.getModuleContraints();
						for (int c = 0; c < l.size(); c++) {
							com.bluexml.side.application.ModuleConstraint current = l.get(c);
							lmc.add(new ModuleConstraint(current.getModuleId(), current.getTechnologyVersion(), current.getModuleType(), current.getVersionMin(), current.getVersionMax()));
						}
						System.out.println("initialize dependence Manager ");
						DependencesManager dm = new DependencesManager(lmc, false);

						System.out.println("initialize generation ");
						generator.initialize(generationParameters, generatorOptions, configurationParameters, dm, generationMonitor);
					} catch (Exception e) {
						error = true;
						System.out.println("Exception  "+e.getMessage());
						generationMonitor.getLog().addErrorLog("Initialization error : " + e.getMessage(), e, null);
						e.printStackTrace();
					}

					System.out.println("start generation if  modelsInfo.size() > 0: " +modelsInfo.size());
					// The first one
					if (modelsInfo.size() > 0) {
						try {
							System.out.println("generate");
							generator.generate(modelsInfo, elem.getId_metamodel());
							
							// System.out.println("\tlog92");
							System.out.println("finalize");
							generator.complete();
							// System.out.println("\tlog93");

							// System.out.println("\tlog10");
							// TODO : add feedback

						} catch (Exception e) {
							error = true;
							System.out.println("Exception  "+e.getMessage());
							generationMonitor.getLog().addErrorLog("Generation error : " + e.getMessage(), e, null);
							e.printStackTrace();
						}

						// System.out.println("\tlog11");
						try {
							System.out.println("Create Stamp File");
							generator.createStampFile();
						} catch (Exception e) {
							System.out.println("Exception  "+e.getMessage());
							generationMonitor.getLog().addErrorLog("Generation error : Stamp file error. " + e.getMessage(), e, null);
							e.printStackTrace();
						}
						// System.out.println("\tlog12");
					}
				}
				// System.out.println("\tlog13");
				String fileName = "gen_" + generator.getTechVersion() + ".xml";
				System.out.println("Log info in "+ fileName);
				try {
					if (generator.getMonitor() != null) {
						generator.getMonitor().getLog().saveLog(fileName, logPath + fileSeparator + "work" + fileSeparator);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Exception  "+e.getMessage());
					e.printStackTrace();
				}
			} else {
				error = true;
			}
		}
		System.out.println("### generate_ Done error="+error);
		return error;
	}

	private boolean deploy_(final Configuration configuration, final HashMap<String, List<IFile>> modelsInfo, final Map<String, String> configurationParameters, final Map<String, String> generationParameters) {
		System.out.println("### deploy_ Start");
		boolean error = false;
		List<DeployerConfiguration> ldeployers = configuration.getDeployerConfigurations();
		for (DeployerConfiguration depConf : ldeployers) {
			String deployerClassName = depConf.getImpl_class();
			String id_deployer = depConf.getId();
			String id_techno = depConf.getId_techno_version();
			System.out.println("Work on deployer "+depConf.getDeployerName());
			System.out.println("Deployement structure is :  technologyVersion ="+id_techno);
			System.out.println("                           deployerId ="+id_deployer);
			System.out.println("                           deployerClassName ="+depConf.getImpl_class());
			System.out.println("                           technologyName ="+depConf.getTechnologyName());
			System.out.println("                           technologyVersionName ="+depConf.getTechnologyVersionName());
			System.out.println("                           configurationName ="+configuration.getName());
			configurationParameters.put("technologyVersion", id_techno);
			configurationParameters.put("deployerName", depConf.getDeployerName());
			configurationParameters.put("deployerId", id_deployer);
			//configurationParameters.put("metaModelName", depConf.getMetaModelName());
			configurationParameters.put("technologyName", depConf.getTechnologyName());
			configurationParameters.put("technologyVersionName", depConf.getTechnologyVersionName());
			configurationParameters.put("configurationName", configuration.getName());

			List<Option> options = depConf.getOptions();
			// We get the option for this generator
			List<String> deployerOptions = new ArrayList<String>();
			for (Option option : options) {
				deployerOptions.add(option.getKey());
			}
			Bundle plugin = Platform.getBundle(id_deployer);

			Class<?> gen;
			Object genObj = null;
			try {
				System.out.println("Load deployer class "+deployerClassName);
				gen = plugin.loadClass(deployerClassName);
				genObj = gen.newInstance();
			} catch (ClassNotFoundException e1) {
				System.out.println("Exception  "+e1.getMessage());
				error = true;
				e1.printStackTrace();
			} catch (InstantiationException e) {
				System.out.println("Exception  "+e.getMessage());
				error = true;
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				System.out.println("Exception  "+e.getMessage());
				error = true;
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("Exception  "+e.getMessage());
				error = true;
				e.printStackTrace();
			}
			try {
				IFileHelper.refreshFolder(logPath);
			} catch (CoreException e1) {
				System.out.println("Exception  "+e1.getMessage());
				e1.printStackTrace();
			}
			if (genObj instanceof Deployer) {
				Deployer deployer = (Deployer) genObj;
				
				NullComponentMonitor deployerMonitor = new NullComponentMonitor(configurationParameters, LogType.DEPLOYMENT);
				deployer.initialize(configurationParameters, generationParameters, deployerOptions, deployerMonitor);
				try {
					System.out.println("Deploy ");
					deployer.deploy();
				} catch (Exception e) {
					System.out.println("Exception  "+e.getMessage());
					e.printStackTrace();
					error = true;
				}

				try {
					System.out.println("Move Stampe File to "+logPath);
					deployer.moveStampFile(logPath);
				} catch (Exception e) {
					System.out.println("Exception  "+e.getMessage());
					e.printStackTrace();
				}

				String fileName = "dep_" + deployer.getTechVersion() + ".xml";
				try {
					deployerMonitor.getLog().saveLog(fileName, logPath + System.getProperty("file.separator") + "work" + System.getProperty("file.separator"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Exception  "+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		System.out.println("### deploy_ Done error="+error);
		return error;
	}
}
