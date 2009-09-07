package com.bluexml.side.util.deployer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractMultiDeployer extends Deployer {

	protected List<Deployer> deployers = new ArrayList<Deployer>();

	public void addDeployer(Deployer dep) {
		deployers.add(dep);
	}

	@Override
	protected void deployProcess(File fileToDeploy) throws Exception {
		
		for (Deployer wd : deployers) {
			wd.deploy();
		}
		
	}

	@Override
	public void initialize(Map<String, String> configurationParameters, Map<String, String> generationParameters, List<String> options) {
		super.initialize(configurationParameters, generationParameters, options);
		for (Deployer wd : deployers) {
			wd.initialize(configurationParameters, generationParameters, options);
			wd.setLog(getLog());
			// propagate options key
			wd.setCleanKey(cleanKey);
			wd.setLogChanges(logChanges);
		}
	}
}
