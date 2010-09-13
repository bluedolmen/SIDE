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
	String componentId;
	String componentName;
	String componentTechnoVersionName;
	String componentTechnoName;
	Date logSate;
	LogType logType;
	String metaModel;
	String logFileName;

	protected String fileSeparator = System.getProperty("file.separator"); //$NON-NLS-1$

	public LogHelper(final Map<String, String> configurationParameters, LogType logType, String logFileName) {
		this.logDirectory = configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral()) + File.separator + configurationParameters.get("configurationName");
		this.logFileName = logFileName;
		logSate = new Date();
		this.logType = logType;
		if (logType.equals(LogType.CONSOLE)) {
			this.log = new SIDELog("name", "deployerId", "technologyVersionName", "technologyName", "metaModelName", new Date(), logType);
			componentId = "namelessComponant";
		} else {
			componentTechnoVersionName = configurationParameters.get("technologyVersionName");
			componentTechnoName = configurationParameters.get("technologyName");
			metaModel = configurationParameters.get("metaModelName");
			if (logType.equals(LogType.GENERATION)) {
				componentName = configurationParameters.get("generatorName");
				componentId = configurationParameters.get("generatorId");
			} else if (logType.equals(LogType.DEPLOYMENT)) {
				componentId = configurationParameters.get("deployerId");
				componentName = configurationParameters.get("deployerName");
			}
			this.log = new SIDELog(componentName, componentId, componentTechnoVersionName, componentTechnoName, metaModel, logSate, logType);
		}

		
	}

	/**
	 * @return the logDirectory
	 */
	public String getLogDirectory() {
		return logDirectory;
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
	public String getGeneratorLogFile() {
		return logDirectory + File.separator + componentId + ".txt"; //$NON-NLS-1$

	}

	public void saveLog() throws Exception {
		LogSave.toXml(log, logFileName, logDirectory + fileSeparator + "work" + fileSeparator);
	}

}
