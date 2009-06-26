package com.bluexml.side.util.deployer.war;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bluexml.side.util.deployer.Deployer;

public abstract class MultiWarDeployer extends Deployer {

	/**
	 * this map records deployer to use : String : webapp name WarDeployer :
	 * instance of the WarDeployer to use
	 */
	protected Map<String, WarDeployer> warDeployers = new HashMap<String, WarDeployer>();

	@Override
	protected void clean(File fileToDeploy) throws Exception {
		for (Map.Entry<String, WarDeployer> wd : warDeployers.entrySet()) {
			wd.getValue().clean(fileToDeploy);
		}
	}

	@Override
	protected void deployProcess(File fileToDeploy) throws Exception {
		for (Map.Entry<String, WarDeployer> wd : warDeployers.entrySet()) {
			wd.getValue().deploy();
		}
	}

	@Override
	protected void preProcess(File fileToDeploy) throws Exception {
		// check precondition
		boolean available = true;
		for (Map.Entry<String, WarDeployer> wd : warDeployers.entrySet()) {
			available &= (wd.getValue().webappName != null);
		}
		if (available == false) {
			throw new Exception("check WarDeployer initialization");
		}
	}

	public void addWarDeployer(String webappName, WarDeployer dep) {
		warDeployers.put(webappName, dep);
	}

	public void initialize(Map<String, String> configurationParameters, Map<String, String> generationParameters, List<String> options) {
		super.initialize(configurationParameters, generationParameters, options);

		for (Map.Entry<String, WarDeployer> wd : warDeployers.entrySet()) {
			wd.getValue().initialize(wd.getKey(), cleanKey, logChanges, configurationParameters, generationParameters, options);
		}
	}
}
