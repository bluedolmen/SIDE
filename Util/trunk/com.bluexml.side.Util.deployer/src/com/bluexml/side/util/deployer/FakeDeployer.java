package com.bluexml.side.util.deployer;

import java.io.File;

public class FakeDeployer extends Deployer {

	public FakeDeployer() {
		super("fakeClean", "fakeLog");
	}

	@Override
	protected void clean(File fileToDeploy) throws Exception {
		// nothing to do
	}

	@Override
	protected void deployProcess(File fileToDeploy) throws Exception {
		// nothing to do
	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// nothing to do
	}

	@Override
	protected void preProcess(File fileToDeploy) throws Exception {
		// nothing to do
	}

	public boolean check() {
		return true;
	}

	public boolean checkOption(String optionID) {
		return true;
	}

}
