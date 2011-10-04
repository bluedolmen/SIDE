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
