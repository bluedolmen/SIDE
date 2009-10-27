package com.bluexml.side.util.componentmonitor;

import java.util.Map;

import com.bluexml.side.util.documentation.structure.enumeration.LogEntryType;
import com.bluexml.side.util.documentation.structure.enumeration.LogType;

public class NullComponentMonitor extends ComponentMonitor {

	public NullComponentMonitor(final Map<String, String> configurationParameters, LogType logType,String logFileName) {
		super(null, null, -1, null, null, configurationParameters, logType, null, logFileName);
	}

	@Override
	public void logConsole(String txt, LogEntryType type) {
		// nothing to do
	}

	public void beginTask(String name, int totalWork) {

	}

	@Override
	public void beginTask(String name) {

	}

	public void subTask(String name) {
		// acceleo use this for generation part, model access, ...

	}

	public void worked(int work) {
		// TODO Auto-generated method stub

	}

	@Override
	public void taskDone(String text) {

	}

	public void done() {
		// acceleo use this for generation part, model access, ...
	}

	public void addText(String text) {

	}

	public void addErrorText(String text) {

	}

	public void addWarningText(String text) {

	}

	protected void addOneStep() {

	}

	public String toString() {
		String st = "" + "\n";
		return st;
	}

	public void setMaxTaskNb(int nb) {
		
	}

	public void skipTasks(int nb) {
		
	}
}
