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
