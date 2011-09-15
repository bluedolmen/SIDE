package com.bluexml.side.integration.standalone;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import com.bluexml.side.application.Application;

public class ApplicationStarter implements IApplication {
	
	public static final String APPLICATION_EXTENSION_NAME = "application"; // $NON-NLS-1$
	public static final String APPLICATION_EXTENSION = '.' + APPLICATION_EXTENSION_NAME;

	private final static Logger LOGGER = Logger.getLogger(ApplicationStarter.class.getName());
		
	private long startTime;

	public Object start(IApplicationContext context) throws Exception {

		GenerateModelHelper.initLogger(Level.FINE);
		String[] arguments = (String[]) context.getArguments().get("application.args"); // $NON-NLS-1$

		if (arguments.length < 1) {
			LOGGER.info("... <model.application> [<configuration-name>]");
			return EXIT_OK;
		}

		LOGGER.fine("Starting standalone generation process");
		
		startTime = System.currentTimeMillis();
		
		
		String configurationName = null;
		if (arguments.length > 1) configurationName = arguments[1];
		String applicationFilename = arguments[0];
		List<File> applicationModelFiles = retrieveApplicationModelFiles(applicationFilename);
		
		for (File applicationModelFile : applicationModelFiles) {
			
			Application applicationModel = GenerateModelHelper.findApplication(applicationModelFile, true);
			if (applicationModel == null) {
				LOGGER.warning(String.format("Ignoring the application model file '%s' which is not a valid application model.", applicationModelFile.getPath()));
			}

			ApplicationModelGenerateGenerationJob job = new ApplicationModelGenerateGenerationJob(applicationModel, configurationName);
			job.generate();
		}		
		
		double timeInSec = (System.currentTimeMillis() - startTime) / 1000.;
		LOGGER.fine(String.format("Ending standalone generation process. Processed in '%s' sec.", new DecimalFormat("#.###").format(timeInSec)));
		
		return EXIT_OK;
	}
	

	private static List<File> retrieveApplicationModelFiles(String applicationFilename) throws FileNotFoundException {
		File applicationFile = new File(applicationFilename);
		
		if (!applicationFile.exists()) {
			final String message = String.format("The file or directory '%s' does not exist. A valid application model or containing directory has to be provided.", applicationFilename);
			LOGGER.severe(message);
			throw new FileNotFoundException(message);
		}
		
		List<File> applicationModelFiles = new ArrayList<File>();
		
		if (applicationFile.isDirectory()) {
			// The provided argument is a directory, look for a containing application model
			Collection<?> files = FileUtils.listFiles(applicationFile, new String[] {APPLICATION_EXTENSION_NAME}, true);
			for (Iterator<?> iterator = files.iterator(); iterator.hasNext();) {
				applicationModelFiles.add((File) iterator.next());
			}
		} else if (applicationFilename.endsWith(APPLICATION_EXTENSION)) {
			applicationModelFiles.add(applicationFile);
		}

		return applicationModelFiles;
	}

	public void stop() {
	}
}
