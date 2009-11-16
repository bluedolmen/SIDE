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
		if (parent !=null) {
			parent.beginTask(name, totalWork);
		}
	}

	public void subTask(String name) {
		if (name != null) {
			addText(name);
			progressBarlabel.setText(name);
		}
		currentOpenTask++;
		if (parent !=null) {
			parent.subTask(name);
		}
	}

	public void worked(int work) {
		if (parent !=null) {
			parent.worked(work);
		}
	}

	public void internalWorked(double work) {
		if (parent !=null) {
			parent.internalWorked(work);
		}

	}

	public void setTaskName(String name) {
		if (parent !=null) {
			parent.setTaskName(name);
		}

	}

	@Override
	public void beginTask(String name) {
		if (name != null) {
			addText(name);
			progressBarlabel.setText(name);
		}
		currentOpenTask++;
		if (parent !=null) {
			parent.beginTask(name);
		}
	}

	public void taskDone(String text) {
		done();
		if (text != null) {
			addText(text);
			progressBarlabel.setText(text);
		}
		if (parent !=null) {
			parent.taskDone(text);
		}
	}

	public void done() {
		currentOpenTask--;
		addOneStep();
		if (parent !=null) {
			parent.done();
		}
	}

}
