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
