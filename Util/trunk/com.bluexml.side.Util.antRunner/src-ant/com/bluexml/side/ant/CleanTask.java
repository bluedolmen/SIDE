package com.bluexml.side.ant;

import org.apache.tools.ant.BuildException;

import com.bluexml.side.application.ui.action.utils.Generate;

public class CleanTask extends SideTask {

	@Override
	public void executeImp() throws BuildException {
		new ApplicationModelGenerateJobWrapper() {
			
			@Override
			public void run() throws Exception {
				Generate gen = getHeadlessGenerate();
				gen.clean();
				gen.refreshFolders();
			}
			
		}.performJob();
	}
}
