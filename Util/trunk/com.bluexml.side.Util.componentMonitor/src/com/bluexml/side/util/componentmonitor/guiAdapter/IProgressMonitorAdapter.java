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
package com.bluexml.side.util.componentmonitor.guiAdapter;

import org.eclipse.core.runtime.IProgressMonitor;

import com.bluexml.side.util.componentmonitor.Monitor;
import com.bluexml.side.util.componentmonitor.headless.LabelHeadLess;
import com.bluexml.side.util.componentmonitor.headless.StyledTextHeadless;
import com.bluexml.side.util.componentmonitor.headless.progressBarHeadLess;
import com.bluexml.side.util.documentation.structure.enumeration.LogEntryType;

public class IProgressMonitorAdapter extends Monitor {
	IProgressMonitor adapted = null;

	public IProgressMonitorAdapter(IProgressMonitor monitor) {
		super(new StyledTextHeadless(), new progressBarHeadLess(), new LabelHeadLess(), null, null, null);
		adapted = monitor;
	}

	public void beginTask(String name, int totalWork) {
		adapted.beginTask(name, totalWork);
	}

	public void subTask(String name) {
		adapted.subTask(name);
	}

	public void worked(int work) {
		adapted.worked(work);
	}

	public void internalWorked(double work) {
		adapted.internalWorked(work);
	}

	public boolean isCanceled() {
		return adapted.isCanceled();
	}

	public void setCanceled(boolean value) {
		adapted.setCanceled(value);
	}

	public void setTaskName(String name) {
		adapted.setTaskName(name);
	}

	@Override
	public void beginTask(String name) {
		adapted.subTask(name);
	}

	public void taskDone(String text) {
		super.taskDone(text);
		adapted.subTask(text);
	}

	public void done() {
		addOneStep();
	}

	public void addText(String text) {

	}

	public void addErrorText(String text) {

	}

	public void addWarningText(String text) {

	}

	protected void addOneStep() {
		adapted.worked(1);
	}

	public void setMaxTaskNb(int nb) {

	}

	public void skipTasks(int nb) {
		adapted.worked(nb);
	}

	protected void logConsole(String txt, LogEntryType type) {

	}

}
