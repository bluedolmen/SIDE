package com.bluexml.side.integration.standalone;

import java.io.File;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import com.bluexml.side.integration.standalone.utils.Generate;

public class Application implements IApplication {

	protected String[] arguments;
	
	public Object start(IApplicationContext context) throws Exception {
		
		arguments = (String[]) context.getArguments().get("application.args");

		System.out.println("Start !!!!!!!!!!");

		  // Start Generation
		System.out.println("Start Generation");
      
		//File file = new File("workspaceStandAlone/StandAlone/models/My.application");
		File file = new File(arguments[0]);
		
		System.out.println("file.exists(): " + file.exists());
		Generate gen = new Generate(file, arguments[1]);
		System.out.println("created");

		gen.run();

		System.out.println("End Generation");

		System.out.println("END !!!!!!!!!!");

		return EXIT_OK;
	}

	public void stop() {
		// nothing to do

	}

}
