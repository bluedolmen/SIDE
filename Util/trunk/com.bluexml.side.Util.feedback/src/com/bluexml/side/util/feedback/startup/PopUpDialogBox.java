package com.bluexml.side.util.feedback.startup;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.bluexml.side.util.settings.SidePreferences;

public class PopUpDialogBox extends Dialog {

	protected Button uploadNow;
	protected Button uploadAlways;
	protected Button dontUploadNowButton;
	protected Button noFeedbackButton;
	protected int choice;

	protected PopUpDialogBox(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog
	 *
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite entryTable = new Composite(parent, SWT.NULL);
		entryTable.setForeground(new Color(null, 255, 255, 255));

		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		entryTable.setLayout(layout);

		final Label theSideFeedbackLabel = new Label(entryTable, SWT.WRAP);
		theSideFeedbackLabel.setLayoutData(new GridData(494, 63));
		theSideFeedbackLabel
				.setText("The S-IDE Feedback Data Collector has been collecting data on how you have been using the S-IDE tools. It would like to upload the data on a server at the S-IDE Bluexml.\n\nYou can preview the date before it is upload on the Preview Page.");

		uploadNow = new Button(entryTable, SWT.RADIO);
		uploadNow.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				choice = com.bluexml.side.util.settings.Activator.FEEDBACK_PREF_NOW;
			}
		});
		uploadNow.setText("Upload Now");
		final Label lbluploadNow = new Label(entryTable, SWT.RIGHT);
		final GridData gd_lbluploadNow = new GridData(SWT.FILL, SWT.CENTER,
				false, false);
		gd_lbluploadNow.horizontalIndent = 30;
		lbluploadNow.setLayoutData(gd_lbluploadNow);
		lbluploadNow.setAlignment(SWT.LEFT);
		lbluploadNow
				.setText("Upload the data now. Ask before uploading again.");

		uploadAlways = new Button(entryTable, SWT.RADIO);
		uploadAlways.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				choice = com.bluexml.side.util.settings.Activator.FEEDBACK_PREF_ALWAYS;
			}
		});
		uploadAlways.setText("Upload Always");
		final Label lbluploadAlways = new Label(entryTable, SWT.WRAP);
		final GridData gd_lbluploadAlways = new GridData(456, 48);
		gd_lbluploadAlways.horizontalIndent = 30;
		lbluploadAlways.setLayoutData(gd_lbluploadAlways);
		lbluploadAlways
				.setText("Upload the usage data now. Don't ask the next time; just do the upload in the background. \nNote that you can change this setting in the preferences.");

		dontUploadNowButton = new Button(entryTable, SWT.RADIO);
		dontUploadNowButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				choice = com.bluexml.side.util.settings.Activator.FEEDBACK_PREF_NOTNOW;
			}
		});
		dontUploadNowButton.setText("Don't upload now.");

		final Label doNotUploadLabel = new Label(entryTable, SWT.NONE);
		final GridData gd_doNotUploadLabel = new GridData(SWT.FILL, SWT.CENTER,
				false, false);
		gd_doNotUploadLabel.horizontalIndent = 30;
		gd_doNotUploadLabel.heightHint = 18;
		doNotUploadLabel.setLayoutData(gd_doNotUploadLabel);
		doNotUploadLabel
				.setText("Do not upload usage data at this time. You will be asked to do the upload later.");

		noFeedbackButton = new Button(entryTable, SWT.RADIO);
		noFeedbackButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				choice = com.bluexml.side.util.settings.Activator.FEEDBACK_PREF_NEVER;
			}
		});
		noFeedbackButton.setText("Turn S-IDE Feedback Off");

		final Label stopCollectingDataLabel = new Label(entryTable, SWT.WRAP);
		final GridData gd_stopCollectingDataLabel = new GridData(SWT.LEFT,
				SWT.TOP, true, false);
		gd_stopCollectingDataLabel.horizontalIndent = 30;
		gd_stopCollectingDataLabel.heightHint = 37;
		gd_stopCollectingDataLabel.widthHint = 451;
		stopCollectingDataLabel.setLayoutData(gd_stopCollectingDataLabel);
		stopCollectingDataLabel
				.setText("Stop collecting data. The S-IDE Feedback will be turned off and data will never be uploaded.");
		return entryTable;
	}

	@Override
	protected void okPressed() {
		updatePreferences();
		super.okPressed();
	}

	private void updatePreferences() {
		switch (choice) {
		case com.bluexml.side.util.settings.Activator.FEEDBACK_PREF_ALWAYS:
			// Change preference setting (made after) and send data
			doSend();
			break;
		case com.bluexml.side.util.settings.Activator.FEEDBACK_PREF_NOW:
			// Send now and change preference setting and send data
			doSend();
			break;
		case com.bluexml.side.util.settings.Activator.FEEDBACK_PREF_NEVER:
			// change preference setting (made after)
			break;
		case com.bluexml.side.util.settings.Activator.FEEDBACK_PREF_NOTNOW:
			// change preference setting (made after)

			break;
		default:
			break;
		}
		SidePreferences.setFeedBackPreference(choice);
	}

	private void doSend() {
		// TODO Auto-generated method stub

	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("S-IDE Feedback");
	}

}
