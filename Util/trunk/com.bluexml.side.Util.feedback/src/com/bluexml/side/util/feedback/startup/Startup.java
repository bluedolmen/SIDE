package com.bluexml.side.util.feedback.startup;

import java.util.Date;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.bluexml.side.util.feedback.Activator;


public class Startup implements IStartup {



	public void earlyStartup() {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchWindow dwindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				IWorkbenchPage page = dwindow.getActivePage();
				// Get last update date
				Long longDate = Activator.getDefault().getPreferenceStore().getLong(Activator.LAST_UPDATE_DATE);
				if (!longDate.equals(0L)) {
					// Does we need to send data?
					Date lastUpdate = new Date(longDate);
					Date now = new Date();
					long delta = now.getTime() - lastUpdate.getTime();
					if (delta / (Activator.MILLISECONDS_PER_DAY) >= 30) {
						// Show pop up and send data
						PopUpDialog popup = new PopUpDialog(Display.getDefault().getActiveShell());
						popup.open();
					}

				} else {
					// Show first time launch dialog box
				}
			}
		});
	}

}
