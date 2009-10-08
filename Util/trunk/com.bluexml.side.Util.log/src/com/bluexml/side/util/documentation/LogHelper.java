package com.bluexml.side.util.documentation;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.util.documentation.structure.LogEntry;
import com.bluexml.side.util.documentation.structure.SIDELog;
import com.bluexml.side.util.documentation.structure.enumeration.LogEntryType;
import com.bluexml.side.util.documentation.structure.enumeration.LogType;

public class LogHelper {
	SIDELog log;
	String logDirectory;
	protected String fileSeparator = System.getProperty("file.separator"); //$NON-NLS-1$

	public LogHelper(final Map<String, String> configurationParameters, LogType logType) {
		this.logDirectory = configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral()) + File.separator + configurationParameters.get("configurationName");
		SIDELog log_ = null;
		if (logType.equals(LogType.GENERATION)) {
			log_ = new SIDELog(configurationParameters.get("generatorName"), configurationParameters.get("generatorId"), configurationParameters.get("technologyVersionName"), configurationParameters.get("technologyName"), configurationParameters //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					.get("metaModelName"), new Date(), logType); //$NON-NLS-1$
		} else if (logType.equals(LogType.DEPLOYMENT)) {
			log_ = new SIDELog(configurationParameters.get("deployerName"), configurationParameters.get("deployerId"), configurationParameters.get("technologyVersionName"), configurationParameters.get("technologyName"), configurationParameters.get("metaModelName"), new Date(),
					logType);
		} else if (logType.equals(LogType.CONSOLE)) {
			log_ = new SIDELog("name", "deployerId", "technologyVersionName", "technologyName", "metaModelName", new Date(), logType);
		}
		this.log = log_;
	}

	/**
	 * Add log to know on each model have been launch generation
	 * 
	 * @param name
	 */
	public void addModelLog(String name) {
		log.addModel(name);
	}

	public void addModelLog(List<String> names) {
		for (String name : names) {
			addModelLog(name);
		}
	}

	public void addModelsLog(List<IFile> models) {
		for (IFile model : models) {
			addModelLog(model.getName());
		}
	}

	/**
	 * Add a Log
	 * 
	 * @param title
	 * @param description
	 * @param uri
	 * @param logEntryType
	 */
	public void addLog(String title, String description, URI uri, LogEntryType logEntryType) {
		List<String> lDesc = new ArrayList<String>();
		String[] ld = description.split("\n");
		for (int i = 0; i < ld.length; i++) {
			lDesc.add(ld[i]);
		}
		log.addLogEntry(new LogEntry(title, lDesc, uri, logEntryType));
	}

	public void addLog(String title, String description, String uri, LogEntryType logEntryType) {
		List<String> lDesc = new ArrayList<String>();
		String[] ld = description.split("\n");
		for (int i = 0; i < ld.length; i++) {
			lDesc.add(ld[i]);
		}
		log.addLogEntry(new LogEntry(title, lDesc, uri, logEntryType));
	}

	/**
	 * Add an Error Log
	 * 
	 * @param title
	 * @param description
	 * @param uri
	 */
	public void addErrorLog(String title, String description, String uri) {
		addLog(title, description, uri, LogEntryType.ERROR);
	}

	/**
	 * Add an error log using a stracktrace instead of a string description
	 * 
	 * @param title
	 * @param stackTrace
	 * @param uri
	 */
	public void addErrorLog(String title, Throwable error, String uri) {
		String description = "";
		if (error != null) {
			description = error.getMessage();
			StackTraceElement[] stackTrace = error.getStackTrace();
			if (stackTrace != null && stackTrace.length > 0) {
				for (StackTraceElement se : stackTrace) {
					description += System.getProperty("line.separator") + se.toString();
				}
			}
		}
		addErrorLog(title, description, uri);
	}

	/**
	 * Add a warning log
	 * 
	 * @param title
	 * @param description
	 * @param uri
	 *            : null if no uri
	 */
	public void addWarningLog(String title, String description, String uri) {
		addLog(title, description, uri, LogEntryType.WARNING);
	}

	public void addWarningLog(String title, String description, URI uri) {
		addLog(title, description, uri, LogEntryType.WARNING);
	}

	/**
	 * Add information log
	 * 
	 * @param title
	 * @param description
	 * @param uri
	 */
	public void addInfoLog(String title, String description, URI uri) {
		addLog(title, description, uri, LogEntryType.GENERATION_INFORMATION);
	}

	public void addInfoLog(String title, String description, String uri) {
		addLog(title, description, uri, LogEntryType.GENERATION_INFORMATION);
	}

	/**
	 * Add a service log (service : a webpage or a file that can be acceeded by
	 * user to test application).
	 * 
	 * @param title
	 * @param description
	 * @param uri
	 */
	public void addServiceLog(String title, String description, URI uri) {
		addLog(title, description, uri, LogEntryType.SERVICE);
	}

	public void addServiceLog(String title, String description, String uri) {
		addLog(title, description, uri, LogEntryType.SERVICE);
	}

	/**
	 * Use to log generated file
	 * 
	 * @param path
	 * @param description
	 * @param uri
	 */
	public void addFileGeneratedLog(String path, String description, String uri) {
		addLog(path, description, uri, LogEntryType.GENERATED_FILE);
	}

	public void addFileGeneratedLog(String path, String description, URI uri) {
		addLog(path, description, uri, LogEntryType.GENERATED_FILE);
	}

	public SIDELog getLog() {
		return log;
	}

	public void setLog(SIDELog log) {
		this.log = log;
	}

	/**
	 * Return the log target file (only generator)
	 * 
	 * @return
	 */
	public String getLogFile() {
		return logDirectory + File.separator + getClass().getName() + ".txt"; //$NON-NLS-1$

	}

	public void saveLog(String fileName, String folderName) throws Exception {
		LogSave.toXml(log, fileName, folderName + fileSeparator + "work" + fileSeparator);
	}
	
	
}
