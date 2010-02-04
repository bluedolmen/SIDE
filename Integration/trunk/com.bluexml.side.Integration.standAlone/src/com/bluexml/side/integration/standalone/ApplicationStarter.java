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
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import com.bluexml.side.application.Application;
import com.bluexml.side.application.ApplicationPackage;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.Model;
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
import com.bluexml.side.util.libs.SystemInfoGetter;
import com.bluexml.side.util.security.preferences.SidePreferences;

public class ApplicationStarter implements IApplication {
	static final String CONFIGURATION_KEY = "configuration";
	static final String MODELS_KEY = "models";

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

		if (securityServices() == 0) {
			return EXIT_OK;
		}

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

	private void generate(File fileAP) {
		Map<String, Object> conf = loadConfiguration(fileAP, arguments[1]);
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
		try {
			Generate gen = new Generate(configuration, (List<Model>) conf.get(MODELS_KEY), generalMonitor, componentMonitor);
			System.out.println("created, let's run");
			gen.setHeadless(true);
			gen.setUser(false);
			gen.setSystem(true);
			// don't use job scheduler, but invoke the main method
			gen.run_(new NullProgressMonitor());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("### Generate Done");

	}

	private int securityServices() {
		//System.out.println("args[0] :" + arguments[0]);
		if (arguments[0].toString().contains("getHostID")) {
			//System.out.println("hostID :" + SystemInfoGetter.getHostWithHash());
			System.out.println(SystemInfoGetter.getHostWithHash());
		} else if (arguments[0].toString().contains("setLicense")) {
			System.out.println("previous license :" + SidePreferences.getKey());
			SidePreferences.setKey(arguments[1].toString());
			System.out.println("license recorded :" + SidePreferences.getKey());
		} else if (arguments[0].toString().contains("getLicense")) {
			System.out.println("recorded license :" + SidePreferences.getKey());
		} else {
			return -1;
		}
		return 0;
	}

	protected Map<String, Object> loadConfiguration(File filePath, String name) {
		Map<String, Object> extractedConfiguration = new HashMap<String, Object>();

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
			System.out.println("workspace: " + workspace);
			IPath location = Path.fromOSString(filePath.getAbsolutePath());
			System.out.println("location: " + location);
			file = workspace.getRoot().getFileForLocation(location);
			if (file == null)
				file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(filePath.getAbsolutePath()));
			System.out.println("file: " + file);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		System.out.println("getWorkspace: " + ResourcesPlugin.getWorkspace());
		System.out.println("getRoot: " + ResourcesPlugin.getWorkspace().getRoot().exists() + " -> " + ResourcesPlugin.getWorkspace().getRoot());
		System.out.println("getPath: " + ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(filePath.getAbsolutePath())));
		if (file == null)
			file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(filePath.getAbsolutePath()));
		if (file == null)
			System.out.println("\tfile is null ");

		System.out.println("\tfile.exists(): " + file.exists());

		URI uri = null;
		System.out.println("\tURI");
		try {

			System.out.println("\tgetRawLocation: " + file.getRawLocation());
			System.out.println("\ttoFile: " + file.getRawLocation().toFile());
			System.out.println("\tpath: " + file.getRawLocation().toFile().getPath());

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
			System.out.println("Exception  " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e1) {
			System.out.println("Exception  " + e1.getMessage());
			e1.printStackTrace();
		}

		try {
			Application application = (Application) resource.getContents().get(0);
			System.out.println("\tupdateApplicationFile : ");
			ApplicationUtil.updateApplicationFromExtensionPoint(application, file);
			System.out.println("\tapplication: " + application);
			
			

			System.out.println("\tstaticParameters: " + ApplicationDialog.staticFieldsName);
			Configuration configuration = application.getConfiguration(name);
			System.out.println("\tconfiguration: " + configuration);
			List<Model> models = ApplicationUtil.getModels(application);
			System.out.println("\tmodels: " + models);
			extractedConfiguration.put(CONFIGURATION_KEY, configuration);
			extractedConfiguration.put(MODELS_KEY, models);
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
