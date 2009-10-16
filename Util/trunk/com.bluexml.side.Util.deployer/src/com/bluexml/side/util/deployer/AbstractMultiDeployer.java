package com.bluexml.side.util.deployer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bluexml.side.util.componentmonitor.ComponentMonitor;

public abstract class AbstractMultiDeployer extends Deployer {

	public AbstractMultiDeployer(String cleanKey, String logChanges) {
		super(cleanKey, logChanges);
	}

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
	public void initialize(Map<String, String> configurationParameters, Map<String, String> generationParameters, List<String> options, ComponentMonitor monitor) {
		super.initialize(configurationParameters, generationParameters, options, monitor);
		for (Deployer wd : deployers) {
			wd.initialize(configurationParameters, generationParameters, options, monitor);
			// propagate options key
			wd.setCleanKey(cleanKey);
			wd.setLogChanges(logChangesKey);
		}
	}
}
