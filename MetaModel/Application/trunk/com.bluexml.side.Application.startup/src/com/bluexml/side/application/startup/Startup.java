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
package com.bluexml.side.application.startup;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class Startup implements IStartup {

	public void earlyStartup() {

		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchWindow dwindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				IWorkbenchPage page = dwindow.getActivePage();
				List<String> commandLineArgs = Arrays.asList(Platform.getCommandLineArgs());
				if (page != null && !commandLineArgs.contains("-nostartupbrowser")) {
					try {
						URL url;
						if (commandLineArgs.contains("-side.url")) {
							int index = commandLineArgs.indexOf("-side.url");
							index++;
							url = new URL(commandLineArgs.get(index));
						} else
							url = new URL("http://www.bluexml.com/v/getting-started");
						PlatformUI.getWorkbench().getBrowserSupport().createBrowser("com.bluexml.side.application.startup").openURL(url);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

	}

}
