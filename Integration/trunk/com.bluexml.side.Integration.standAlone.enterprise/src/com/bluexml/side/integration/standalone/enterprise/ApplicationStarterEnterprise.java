/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.integration.standalone.enterprise;

import org.eclipse.equinox.app.IApplicationContext;

import com.bluexml.side.integration.standalone.ApplicationStarter;
import com.bluexml.side.util.libs.SystemInfoGetter;

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
		} else {
			return -1;
		}
		return 0;
	}

	

}
