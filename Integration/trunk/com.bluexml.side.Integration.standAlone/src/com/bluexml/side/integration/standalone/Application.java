package com.bluexml.side.integration.standalone;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;


import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import com.bluexml.side.integration.standalone.utils.Generate;

public class Application implements IApplication {

	protected String[] arguments;
	
	public Object start(IApplicationContext context) throws Exception {
		
		arguments = (String[]) context.getArguments().get("application.args");

		System.out.println("Start !!!!!!!!!!");
		
		if(!arguments[0].toString().contains(".application")){
			File root = new File(arguments[0]);
			String[] extensions = {"application"};
			boolean recursive = true;
			try{
				Collection files = FileUtils.listFiles(root, extensions, recursive);
			
			        for (Iterator iterator = files.iterator(); iterator.hasNext();) {
			        	File file = (File) iterator.next();
			        	System.out.println("File = " + file.getAbsolutePath());
			        	File fileAP = new File(file.getAbsolutePath());
			        	System.out.println("file.exists(): " + fileAP.exists());
			        	Generate gen = new Generate(fileAP, arguments[1]);
			        	System.out.println("created");
			        	gen.run();
			        }          
			}
			catch(Exception e){
				e.printStackTrace();
			}
			// Start Generation
		}else{
			System.out.println("Start Generation");
      
			//File file = new File("workspaceStandAlone/StandAlone/models/My.application");
		
			File file = new File(arguments[0]);
			System.out.println("file.exists(): " + file.exists());
			Generate gen = new Generate(file, arguments[1]);
			System.out.println("created");

			gen.run();
		}

		System.out.println("End Generation");

		System.out.println("END !!!!!!!!!!");

		return EXIT_OK;
	}

	public void stop() {
		// nothing to do

	}

}
