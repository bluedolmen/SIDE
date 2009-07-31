package com.bluexml.side.integration.standalone;

import java.io.File;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import com.bluexml.side.integration.standalone.utils.Generate;

public class Application implements IApplication {

	@Override
	public Object start(IApplicationContext context) throws Exception {
		System.out.println("Start !!!!!!!!!!");

		System.out.println("Start Generation");

		//File file = new File("workspaceStandAlone/StandAlone/models/My.application");
		File file = new File("workspaceStandAlone/StandAlone/models/My.application");
		
		System.out.println("file.exists(): " + file.exists());
		Generate gen = new Generate(file, "ConfigTest");
		System.out.println("created");

		gen.run();

		System.out.println("End Generation");

		System.out.println("END !!!!!!!!!!");

		return EXIT_OK;
	}

	@Override
	public void stop() {
		// nothing to do

	}

}
