package com.bluexml.side.ant;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;

public class SetPropertyFromConfiguration extends SideTask {
	public static final String CONFIGURATION_KEY = "configuration";

	private String parameterId = "";

	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}

	public String getParameterId() {
		return parameterId;
	}

	private String antProperty = "";

	public void setAntProperty(String antProperty) {
		this.antProperty = antProperty;
	}

	public String getAntProperty() {
		return antProperty;
	}

	@Override
	void executeImp() {
		
		new ApplicationModelGenerateJobWrapper() {
			
			@Override
			public void run() throws Exception {
				Configuration configuration = getConfiguration();
				
				EList<ConfigurationParameters> confParam = configuration.getParameters();
				for (ConfigurationParameters configurationParameters : confParam) {
					if (getParameterId().equals(configurationParameters.getKey())) {
						LOGGER.fine("\tParameter found: " + getParameterId());
						String paramValue = configurationParameters.getValue();
						LOGGER.fine("\tValue is:" + paramValue);
						// try to evaluate against Eclipse variables
						paramValue = ApplicationUtil.eclipseVariableSubstitution(paramValue);
						getProject().setProperty(getAntProperty(), paramValue);
					}
				}
			}
			
		}.performJob();
		
	}

}
