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
package com.bluexml.side.ant;

import org.apache.tools.ant.BuildException;

import com.bluexml.side.integration.standalone.ApplicationModelGenerateJob;


public abstract class SideTask extends SideApplicationTask {	

	private String configurationName = "";


	public void setConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}

	public String getConfigurationName() {
		return configurationName;
	}
	
	protected abstract class ApplicationModelGenerateJobWrapper extends ApplicationModelGenerateJob {
		
		protected ApplicationModelGenerateJobWrapper() {
			super(getInitApplicationModel());
			setConfigurationName(getConfigurationName());
		}

		public void performJob() throws BuildException {
			try {
				run();
			} catch (Exception e) {
				throw new BuildException(e);
			}
		}
	}

}
