package com.bluexml.side.util.feedback.startup;

import java.util.Date;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.PlatformUI;

import com.bluexml.side.util.feedback.Activator;

public class Startup implements IStartup {

	public void earlyStartup() {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				// Get preferences :
				int pref = Activator.getFeedBackPreference();
				if (pref == 0 ||
						pref == Activator.FEEDBACK_PREF_ALWAYS ||
						pref == Activator.FEEDBACK_PREF_NOTNOW ||
							pref == Activator.FEEDBACK_PREF_NOW) {
					// Get last update date
					Long longDate = Activator.getDefault().getPreferenceStore().getLong(Activator.LAST_UPDATE_DATE);
					if (!longDate.equals(0L)) {
						// Does we need to send data?
						Date lastUpdate = new Date(longDate);
						Date now = new Date();
						long delta = now.getTime() - lastUpdate.getTime();
						if (delta / (Activator.MILLISECONDS_PER_DAY) >= Activator.TIME_BETWEEN_SEND) {
							// Show pop up and send data
							PopUpDialogBox popup = new PopUpDialogBox(Display.getDefault().getActiveShell());
							popup.open();
						}
					}
				} else {
					Date nowDate = new Date();
					Activator.getDefault().getPreferenceStore().setValue(Activator.LAST_UPDATE_DATE, nowDate.getTime());
					Activator.getDefault().getPreferenceStore().setValue(Activator.FEEDBACK_PREFERENCE, Activator.FEEDBACK_PREF_NEVERSETTED);
				}
			}
		});
	}
}
