/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
		currentTask= name;
		progressBar.setMaximum(totalWork);
		if (parent !=null) {
			parent.beginTask(name, totalWork);
		}
	}

	public void subTask(String name) {
		if (name != null) {
			addText(name);
			progressBarlabel.setText(name);
			currentTask = name;
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
