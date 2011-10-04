package com.bluexml.side.ant;

import org.apache.tools.ant.BuildException;

import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.DeployerConfiguration;
import com.bluexml.side.application.ui.action.MustBeStopped;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.application.ui.action.utils.Generate;

public class DeployTask extends SideComponentTask {

	@Override
	public void executeImp() throws BuildException {

		new ApplicationModelGenerateJobWrapper() {
			
			@Override
			public void run() throws Exception {
				Generate gen = getHeadlessGenerate();
				Configuration configuration = getConfiguration();
				
				DeployerConfiguration depConf = (DeployerConfiguration) ApplicationUtil.getComponantConfiguration(configuration, getComponentid());
				try {
					gen.launchDeployer(depConf, configuration);
					gen.refreshFolders();
				} catch (MustBeStopped e) {
					// Do nothing
				}
			}
			
		}.performJob();
		
	}
}
