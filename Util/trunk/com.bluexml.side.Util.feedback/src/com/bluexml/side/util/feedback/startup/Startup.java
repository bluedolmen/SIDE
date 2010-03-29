package com.bluexml.side.util.feedback.startup;

import java.util.Date;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.PlatformUI;

import com.bluexml.side.util.feedback.FeedbackActivator;
import com.bluexml.side.util.feedback.management.FeedbackSender;
import com.bluexml.side.util.feedback.ui.PopUpDialogBox;

public class Startup implements IStartup {

	public void earlyStartup() {
		/*PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				// Get preferences :
				int pref = FeedbackActivator.getFeedBackPreference();
				if (pref == 0 ||
						pref == FeedbackActivator.FEEDBACK_PREF_NOTNOW ||
							pref == FeedbackActivator.FEEDBACK_PREF_NOW) {
					// Get last update date
					Long longDate = FeedbackActivator.getDefault().getPreferenceStore().getLong(FeedbackActivator.LAST_UPDATE_DATE);
					if (longDate.equals(0L)) {
						// Does we need to send data?
						Date lastUpdate = new Date(longDate);
						Date now = new Date();
						long delta = now.getTime() - lastUpdate.getTime();
						if (delta / (FeedbackActivator.MILLISECONDS_PER_DAY) >= FeedbackActivator.getFeedbackUploadPeriodPreference()) {
							// Show pop up and send data
							PopUpDialogBox popup = new PopUpDialogBox(Display.getDefault().getActiveShell());
							popup.open();
						}
					}
				} else if (pref == FeedbackActivator.FEEDBACK_PREF_ALWAYS) {
					FeedbackSender.doSend();
				} else if (pref != FeedbackActivator.FEEDBACK_PREF_NEVERSETTED) {
					Date nowDate = new Date();
					FeedbackActivator.setFeedBackPreference(FeedbackActivator.FEEDBACK_PREF_NEVERSETTED);
					FeedbackActivator.getDefault().getPreferenceStore().setValue(FeedbackActivator.LAST_UPDATE_DATE, nowDate.getTime());
				}
			}
		});*/
	}
}
