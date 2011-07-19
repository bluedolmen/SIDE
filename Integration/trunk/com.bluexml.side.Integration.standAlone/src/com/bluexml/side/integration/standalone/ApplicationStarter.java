package com.bluexml.side.integration.standalone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.application.Application;
import com.bluexml.side.application.ApplicationPackage;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.ModelElement;
import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.application.ui.action.ApplicationDialog;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.application.ui.action.utils.Generate;
import com.bluexml.side.util.componentmonitor.ComponentMonitor;
import com.bluexml.side.util.componentmonitor.Monitor;
import com.bluexml.side.util.componentmonitor.headLessinterface.LabelInterface;
import com.bluexml.side.util.componentmonitor.headLessinterface.ProgressBarInterface;
import com.bluexml.side.util.componentmonitor.headLessinterface.StyledTextInterface;
import com.bluexml.side.util.componentmonitor.headless.LabelHeadLess;
import com.bluexml.side.util.componentmonitor.headless.StyledTextHeadless;
import com.bluexml.side.util.componentmonitor.headless.progressBarHeadLess;
import com.bluexml.side.util.documentation.structure.enumeration.LogType;
import com.bluexml.side.util.libs.IFileHelper;

public class ApplicationStarter implements IApplication {
	public static final String CONFIGURATION_KEY = "configuration";
	public static final String MODELS_KEY = "models";

	protected String[] arguments;

	/**
	 * application.args[0] : getHostID : return the hostID getLicense : return
	 * the recorded license setLicense : record a new license (must be generated
	 * using the HostID) $FilePath : launch generation process from .application
	 * model (or many application files if $FilePath is a directory)
	 * application.args[1] : the configuration name to use for launch generation
	 * process
	 */
	public Object start(IApplicationContext context) throws Exception {

		arguments = (String[]) context.getArguments().get("application.args");

		System.out.println("Start !!!!!!!!!!");
		long time1 = System.currentTimeMillis();
		if (!arguments[0].toString().contains(".application")) {
			File root = new File(arguments[0]);
			String[] extensions = { "application" };
			boolean recursive = true;
			try {
				Collection files = FileUtils.listFiles(root, extensions, recursive);

				for (Iterator iterator = files.iterator(); iterator.hasNext();) {
					File file = (File) iterator.next();
					System.out.println("File = " + file.getAbsolutePath());
					File fileAP = new File(file.getAbsolutePath());
					System.out.println("file.exists(): " + fileAP.exists());

					generate(fileAP);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Start Generation
		} else {
			// System.out.println("file.exists(): " + file.exists());

			File file = new File(arguments[0]);
			System.out.println("File = " + arguments[0]);
			generate(file);
		}

		System.out.println("End Generation");
		long time2 = System.currentTimeMillis() - time1;
		System.out.println("Time " + Long.toString(time2 / 1000));
		// System.out.println("END !!!!!!!!!!");

		return EXIT_OK;
	}

	public static Generate getGenerate(IFile application, String confName) {
		Map<String, Object> conf = loadConfiguration(application, confName);
		return getGenerate(conf);
	}

	public static Generate getGenerate(File application, String confName) {
		Map<String, Object> conf = loadConfiguration(application, confName);
		return getGenerate(conf);

	}

	private static Generate getGenerate(Map<String, Object> conf) {
		Configuration configuration = (Configuration) conf.get(CONFIGURATION_KEY);

		// instantiate general monitor
		ProgressBarInterface progressBar = new progressBarHeadLess();
		LabelInterface label = new LabelHeadLess();
		ProgressBarInterface progressBar2 = new progressBarHeadLess();
		LabelInterface label2 = new LabelHeadLess();
		StyledTextInterface styletext = new StyledTextHeadless();

		String fileName = "general_" + Generate.class.getName() + ".xml"; //$NON-NLS-1$ //$NON-NLS-2$
		String otherLogPath = ""; //$NON-NLS-1$
		for (ConfigurationParameters p : configuration.getParameters()) {
			if (p.getKey().equals(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral())) {
				otherLogPath = p.getValue();
				break;
			}
		}

		Monitor generalMonitor = new Monitor(styletext, progressBar, label, otherLogPath, configuration.getName(), fileName);

		ComponentMonitor componentMonitor = new ComponentMonitor(styletext, progressBar2, null, label2, generalMonitor, null, LogType.GENERATION, generalMonitor.getConsoleLog(), fileName);

		Generate gen = new Generate(configuration, (List<Model>) conf.get(MODELS_KEY), generalMonitor, componentMonitor);
		gen.setHeadless(true);
		gen.setUser(false);
		gen.setSystem(true);
		return gen;
	}

	public static Monitor getHeadlessMonitor(String generatorLog, String configurationName, String fileName) {
		ProgressBarInterface progressBar = new progressBarHeadLess();
		LabelInterface label = new LabelHeadLess();
		StyledTextInterface styletext = new StyledTextHeadless();
		Monitor generalMonitor = new Monitor(styletext, progressBar, label, generatorLog, configurationName, fileName);
		return generalMonitor;
	}

	public static ComponentMonitor getHeadlessComponantMonitor(Monitor generalMonitor, String generatorLog, String configurationName, String fileName) {
		ProgressBarInterface progressBar2 = new progressBarHeadLess();
		LabelInterface label2 = new LabelHeadLess();
		StyledTextInterface styletext = new StyledTextHeadless();
		if (generalMonitor == null) {
			ProgressBarInterface progressBar = new progressBarHeadLess();
			LabelInterface label = new LabelHeadLess();
			generalMonitor = new Monitor(styletext, progressBar, label, generatorLog, configurationName, fileName);
		}
		ComponentMonitor componentMonitor = new ComponentMonitor(styletext, progressBar2, null, label2, generalMonitor, null, LogType.GENERATION, generalMonitor.getConsoleLog(), fileName);
		return componentMonitor;
	}

	protected void generate(File fileAP) {
		Generate gen = getGenerate(fileAP, arguments[1]);
		try {
			System.out.println("created, let's run");
			// don't use job scheduler, but invoke the main method
			gen.run_(new NullProgressMonitor());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("### Generate Done");

	}

	public static Map<String, Object> loadConfiguration(File filePath, String name) {
		// Create the IFile
		IFile file = IFileHelper.getIFile(filePath);
		return loadConfiguration(file, name);

	}

	/**
	 * @param file
	 * @param name
	 *            , if null return the first configuration
	 * @return
	 */
	public static Map<String, Object> loadConfiguration(IFile file, String name) {
		Map<String, Object> extractedConfiguration = new HashMap<String, Object>();

		System.out.println("\tfile.exists(): " + file.exists());

		try {
			Application application = (Application) ModelInitializationUtils.openModel(file).get(0);
			System.out.println("\tupdateApplicationFile : ");
			ApplicationUtil.updateApplicationFromExtensionPoint(application, file);
			System.out.println("\tapplication: " + application);

			System.out.println("\tstaticParameters: " + ApplicationDialog.staticFieldsName);
			List<Model> models = ApplicationUtil.getModels(application);
			System.out.println("\tmodels: " + models);
			extractedConfiguration.put(MODELS_KEY, models);
			Configuration configuration = null;
			if (name != null) {
				configuration = application.getConfiguration(name);
			} else {
				// get the first configuration if no configuration name provided
				EList<ModelElement> elements = application.getElements();
				for (ModelElement modelElement : elements) {
					if (modelElement instanceof Configuration) {
						configuration = (Configuration) modelElement;
						break;
					}
				}
			}

			System.out.println("\tconfiguration: " + configuration);
			extractedConfiguration.put(CONFIGURATION_KEY, configuration);

		} catch (java.lang.NullPointerException e) {
			System.out.println("NullPointerException  " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e1) {
			System.out.println("Exception  " + e1.getMessage());
			e1.printStackTrace();
		}
		return extractedConfiguration;
	}

	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("Application Stop.");
	}
}
