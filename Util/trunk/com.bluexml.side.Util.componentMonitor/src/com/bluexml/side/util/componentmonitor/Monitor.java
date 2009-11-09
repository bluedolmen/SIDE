package com.bluexml.side.util.componentmonitor;

import java.util.HashMap;
import java.util.Map;

import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.util.componentmonitor.headLessinterface.LabelInterface;
import com.bluexml.side.util.componentmonitor.headLessinterface.ProgressBarInterface;
import com.bluexml.side.util.componentmonitor.headLessinterface.StyledTextInterface;
import com.bluexml.side.util.documentation.LogHelper;
import com.bluexml.side.util.documentation.structure.enumeration.LogType;

public class Monitor extends AbstractMonitor {
	public Monitor(StyledTextInterface styletext, ProgressBarInterface progressBar, LabelInterface progressBarlabel, String logPath, String configurationName, String logFileName) {
		super(styletext, progressBar, progressBarlabel, null);
		if (progressBar != null) {
			progressBar.setSelection(0);
		}
		Map<String, String> configurationParameters = new HashMap<String, String>();
		configurationParameters.put(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral(), logPath);
		configurationParameters.put("configurationName", configurationName);
		LogType logType = LogType.CONSOLE;
		this.consoleLog = new LogHelper(configurationParameters, logType, logFileName);
	}

	public void beginTask(String name, int totalWork) {
		beginTask(name);
		progressBar.setMaximum(totalWork);
	}

	public void subTask(String name) {
		if (name != null) {
			addText(name);
			progressBarlabel.setText(name);
		}
		currentOpenTask++;
	}

	public void worked(int work) {

	}

	public void internalWorked(double work) {
		// TODO Auto-generated method stub

	}

	public boolean isCanceled() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setCanceled(boolean value) {
		// TODO Auto-generated method stub

	}

	public void setTaskName(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beginTask(String name) {
		if (name != null) {
			addText(name);
			progressBarlabel.setText(name);
		}
		currentOpenTask++;

	}

	public void taskDone(String text) {
		done();
		if (text != null) {
			addText(text);
			progressBarlabel.setText(text);
		}

	}

	public void done() {
		currentOpenTask--;
		addOneStep();
	}

}
