package com.bluexml.side.integration.standalone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.Util.ecore.SIDEEditorUtils;
import com.bluexml.side.application.Application;
import com.bluexml.side.application.ui.action.ApplicationDialog;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.util.libs.IFileHelper;

public final class GenerateModelHelper {

	public static final String APPLICATION_DIRNAME = ModelInitializationUtils.getDirectoryNameForEditorId(SIDEEditorUtils.APPLICATION_MODEL_EDITOR_ID);
	public static final String APPLICATION_EXTENSION_NAME = ModelInitializationUtils.getExtensionNameForEditorId(SIDEEditorUtils.APPLICATION_MODEL_EDITOR_ID);
	
	private final static Logger LOGGER = Logger.getLogger(GenerateModelHelper.class.getName());
	
	/**
	 * Gets an {@link Application} model object from a given {@link File} that has
	 * to target an Eclipse workspace file (a.k.a. {@link IFile})
	 *  
	 * @param applicationFile
	 * @param updateApplication
	 * @return
	 */
	public static Application findApplication(File applicationFile, boolean updateApplication) {
		if (applicationFile == null) {
			throw new IllegalArgumentException("The provided application file cannot be null");
		}
		
		IFile applicationModelIFile = IFileHelper.getIFile(applicationFile);
		
		if (applicationModelIFile != null) {
			return findApplication(applicationModelIFile, updateApplication);
		} else {
			LOGGER.fine(String.format("Cannot get the application-model file '%s' since it does not locate in the current workspace.", applicationFile.getAbsolutePath()));
		}
		
		return null;
	}	
	
	/**
	 * Gets an {@link Application} from a given {@link IFile} that has to target a valid
	 * {@link Application} model file.
	 * 
	 * @param applicationIFile
	 * @param updateApplication
	 * @return
	 */
	public static Application findApplication(IFile applicationIFile, boolean updateApplication) {
		
		Application application = ModelInitializationUtils.getCheckedEObject(applicationIFile, Application.class);
		
		// Try to update application file if possible
		if (updateApplication) {
			LOGGER.fine("Trying to update application file");
			try {
				ApplicationUtil.updateApplicationFromExtensionPoint(application, applicationIFile);
			} catch (Exception e) {
				LOGGER.log(Level.WARNING, String.format("Cannot update Application from file '%s' because of an unexpected Exception", applicationIFile.getName()), e);
			}
		}
		
		LOGGER.finer("Application static-parameters: " + ApplicationDialog.staticFieldsName);
		return application;
	}

	/**
	 * Gets the first (unique ?) application model IFile from a given
	 * {@link IProject}.
	 * <p>
	 * This method only use file extension and a potential containing
	 * (sub-)directory name. More advanced methods on a SIDE Project exist
	 * 
	 * @param project
	 * @param updateApplication
	 * @return the {@link Application} object if it can be found, null otherwise
	 */
	public static IFile findApplicationIFile(IProject project) {
		if (project == null) {
			throw new IllegalArgumentException("The provided project has to be non-null");
		}
		
		String projectPath = project.getLocation().toFile().getPath();
		try {
			List<File> applicationFiles = retrieveModelFiles(projectPath, APPLICATION_EXTENSION_NAME);
			if (!applicationFiles.isEmpty()) {
				
				if (applicationFiles.size() > 1) {
					LOGGER.warning(String.format("Project '%s' contains more than one application model-file. Taking first."));
				}
				
				File applicationModelFile = applicationFiles.get(0);
				IFile applicationModelIFile = IFileHelper.getIFile(applicationModelFile);
				assert(applicationModelIFile != null);
				
				return applicationModelIFile;
			}
		} catch (FileNotFoundException e) {
			// DO NOTHING
			LOGGER.finest("Cannot find any application file in project " + project.getLocation());
		}
		
		return null;
	}
	
	/**
	 * Gets the first (unique ?) application model-file from a given
	 * {@link IProject}.
	 * 
	 * @param project
	 * @param updateApplication
	 * @return the {@link Application} object if it can be found, null otherwise
	 */
	public static Application getApplication(IProject project, boolean updateApplication) {
		IFile applicationModelIFile = findApplicationIFile(project);
		
		if (applicationModelIFile == null) return null;
		else return findApplication(applicationModelIFile, updateApplication);		
	}

	
	/**
	 * Retrieve the model files in a directory, given a file-extension name
	 * 
	 * @param directoryPath the base directory path
	 * @param subDirectoryName the sub-directory name to restrict to
	 * @param extensionName the extension name
	 * @return a list of corresponding {@link File}s
	 * @throws FileNotFoundException if the input directory path cannot be found
	 */
	public static List<File> retrieveModelFiles(String directoryPath, String extensionName) throws FileNotFoundException {
		File projectDirectory = new File(directoryPath);

		if (!projectDirectory.exists()) {
			final String message = String.format("The directory '%s' does not exist. A valid project path has to be provided.", projectDirectory);
			LOGGER.severe(message);
			throw new FileNotFoundException(message);
		}
		
		if (!projectDirectory.isDirectory()) {
			return Collections.emptyList();
		}
		
		
		List<File> modelFiles = new ArrayList<File>();
		
		Collection<?> files = FileUtils.listFiles(projectDirectory, FileFilterUtils.suffixFileFilter(extensionName), FileFilterUtils.trueFileFilter());
		for (Iterator<?> iterator = files.iterator(); iterator.hasNext();) {
			modelFiles.add((File) iterator.next());
		}

		return modelFiles;
	}

	
	/**
	 * Init {@link Logger}s associated to package
	 * com.bluexml.side.Integration.standAlone
	 * 
	 * @param baseLevel
	 *            the level above which logs will be displayed on the console
	 * @return the base {@link Logger} managing the package
	 */
	public static Logger initLogger(Level baseLevel) {
		if (baseLevel == null) {
			baseLevel = Level.INFO;
		}
		
		if (baseLogger == null) {
			Logger logger = Logger.getLogger("com.bluexml.side"); // $NON-NLS-1$
			
			ConsoleHandler handler = new ConsoleHandler();
			handler.setFormatter(new ConsoleSimpleFormatter());
			
			logger.setUseParentHandlers(false);
			logger.addHandler(handler);
			
			baseLogger = logger;
			consoleHandler = handler;
		}
		
		baseLogger.setLevel(baseLevel);
		consoleHandler.setLevel(baseLevel);
		
		return baseLogger;
	}
	
	/**
	 * The very simple {@link Formatter} used to display logs on the console
	 * 
	 * @author pajot-b
	 *
	 */
	private static class ConsoleSimpleFormatter extends Formatter {

	    /**
	     * Format the given LogRecord.
	     * @param record the log record to be formatted.
	     * @return a formatted log record
	     */
	    public synchronized String format(LogRecord record) {
			StringBuffer sb = new StringBuffer();
			String message = formatMessage(record);
			sb.append('[').append(record.getLevel().getLocalizedName()).append(']').append(' ');
			sb.append(message);
			sb.append('\n');
			if (record.getThrown() != null) {
			    try {
			        StringWriter sw = new StringWriter();
			        PrintWriter pw = new PrintWriter(sw);
			        record.getThrown().printStackTrace(pw);
			        pw.close();
			        sb.append(sw.toString());
			    } catch (Exception ex) {
			    }
			}
			return sb.toString();
	    }
	}

	private static Logger baseLogger = null;
	private static ConsoleHandler consoleHandler = null;
	
	private GenerateModelHelper(){} // Utility class
}
