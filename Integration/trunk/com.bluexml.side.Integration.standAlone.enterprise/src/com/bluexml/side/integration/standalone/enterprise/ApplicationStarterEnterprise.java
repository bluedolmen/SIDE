package com.bluexml.side.integration.standalone.enterprise;

import org.eclipse.equinox.app.IApplicationContext;

import com.bluexml.side.integration.standalone.ApplicationStarter;
import com.bluexml.side.util.libs.SystemInfoGetter;
import com.bluexml.side.util.security.preferences.SidePreferences;

public class ApplicationStarterEnterprise extends ApplicationStarter {	

	protected String[] arguments;

	/**
	 * application.args[0] : getHostID : return the hostID getLicense : return
	 * the recorded license setLicense : record a new license (must be generated
	 * using the HostID) $FilePath : launch generation process from .application
	 * model (or many application files if $FilePath is a directory)
	 * application.args[1] : the configuration name to use for launch generation
	 * process
	 */
	public Object start(IApplicationContext context) throws Exception {

		arguments = (String[]) context.getArguments().get("application.args");

		if (securityServices() == 0) {
			return EXIT_OK;
		}

		return super.start(context);
	}

	

	private int securityServices() {
		//System.out.println("args[0] :" + arguments[0]);
		if (arguments[0].toString().contains("getHostID")) {
			//System.out.println("hostID :" + SystemInfoGetter.getHostWithHash());
			System.out.println(SystemInfoGetter.getHostWithHash());
		} else if (arguments[0].toString().contains("setLicense")) {
			System.out.println("previous license :" + SidePreferences.getKey());
			SidePreferences.setKey(arguments[1].toString());
			System.out.println("license recorded :" + SidePreferences.getKey());
		} else if (arguments[0].toString().contains("getLicense")) {
			System.out.println("recorded license :" + SidePreferences.getKey());
		} else {
			return -1;
		}
		return 0;
	}

	

}
