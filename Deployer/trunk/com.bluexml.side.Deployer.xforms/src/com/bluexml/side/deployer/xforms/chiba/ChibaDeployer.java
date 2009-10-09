package com.bluexml.side.deployer.xforms.chiba;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import com.bluexml.side.util.deployer.war.WarDeployer;

public class ChibaDeployer extends WarDeployer {

	private final String WEBAPP_NAME_KEY = "com.bluexml.side.Deployer.xforms.webappName";

	public ChibaDeployer() {
		super("xforms");
		cleanKey = "com.bluexml.side.Deployer.xforms.clean";
	}

	public boolean check() {
		return true;
	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// nothing to do
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.side.util.deployer.war.WarDeployer#preProcess(java.io.File)
	 */
	@Override
	protected void preProcess(File fileToDeploy) throws Exception {
		super.preProcess(fileToDeploy);

		String webappName = getConfigurationParameters().get(WEBAPP_NAME_KEY);
		if (StringUtils.trimToNull(webappName) != null) {
			setWebappName(webappName);
		}
	}

}
