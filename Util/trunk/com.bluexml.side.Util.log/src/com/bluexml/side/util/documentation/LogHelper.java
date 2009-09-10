package com.bluexml.side.util.documentation;

import java.io.File;
import java.net.URI;
import java.util.List;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.documentation.structure.LogEntry;
import com.bluexml.side.util.documentation.structure.SIDELog;
import com.bluexml.side.util.documentation.structure.enumeration.LogEntryType;

public class LogHelper {
	SIDELog log;
	String logDirectory;

	public LogHelper(SIDELog log, String logDirectory) {
		this.logDirectory = logDirectory;
		this.log = log;
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
		log.addLogEntry(new LogEntry(title, description, uri, logEntryType));
	}

	public void addLog(String title, String description, String uri, LogEntryType logEntryType) {
		log.addLogEntry(new LogEntry(title, description, uri, logEntryType));
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
	public void addErrorLog(String title, StackTraceElement[] stackTrace, String uri) {
		String description = "";
		if (stackTrace != null && stackTrace.length > 0) {
			for (StackTraceElement se : stackTrace) {
				description += System.getProperty("line.separator") + se.toString();
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
		LogSave.toXml(log, fileName, folderName);
	}
}
