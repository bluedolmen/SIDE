package com.bluexml.side.util.settings;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import com.swtdesigner.SWTResourceManager;

public class SideSettingsFeedbackPreferencesPage extends PreferencePage
		implements IWorkbenchPreferencePage {

	private Button uploadNow;
	private Button uploadAlways;
	private Button dontUploadNowButton;
	private Button turnSideFeedbackButton;

	@Override
	protected Control createContents(Composite parent) {
		Composite entryTable = new Composite(parent, SWT.NULL);
		entryTable.setForeground(SWTResourceManager.getColor(255, 255, 255));

		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		entryTable.setLayout(layout);

		final Label theSideFeedbackLabel = new Label(entryTable, SWT.WRAP);
		theSideFeedbackLabel.setLayoutData(new GridData(494, 63));
		theSideFeedbackLabel.setText("The S-IDE Feedback Data Collector has been collecting data on how you have been using the S-IDE tools. It would like to upload the data on a server at the S-IDE Bluexml.\n\nYou can preview the date before it is upload on the Preview Page.");

		uploadNow = new Button(entryTable, SWT.RADIO);
		uploadNow.setText("Upload Now");
		final Label lbluploadNow = new Label(entryTable, SWT.RIGHT);
		final GridData gd_lbluploadNow = new GridData(SWT.FILL, SWT.CENTER, false, false);
		gd_lbluploadNow.horizontalIndent = 30;
		lbluploadNow.setLayoutData(gd_lbluploadNow);
		lbluploadNow.setAlignment(SWT.LEFT);
		lbluploadNow
				.setText("Upload the data now. Ask before uploading again.");

		uploadAlways = new Button(entryTable, SWT.RADIO);
		uploadAlways.setText("Upload Always");
		final Label lbluploadAlways = new Label(entryTable, SWT.WRAP);
		final GridData gd_lbluploadAlways = new GridData(456, 48);
		gd_lbluploadAlways.horizontalIndent = 30;
		lbluploadAlways.setLayoutData(gd_lbluploadAlways);
		lbluploadAlways
				.setText("Upload the usage data now. Don't ask the next time; just do the upload in the background. \nNote that you can change this setting in the preferences.");

		dontUploadNowButton = new Button(entryTable, SWT.RADIO);
		dontUploadNowButton.setText("Don't upload now.");

		final Label doNotUploadLabel = new Label(entryTable, SWT.NONE);
		final GridData gd_doNotUploadLabel = new GridData(SWT.FILL, SWT.CENTER, false, false);
		gd_doNotUploadLabel.horizontalIndent = 30;
		gd_doNotUploadLabel.heightHint = 18;
		doNotUploadLabel.setLayoutData(gd_doNotUploadLabel);
		doNotUploadLabel.setText("Do not upload usage data at this time. You will be asked to do the upload later.");

		turnSideFeedbackButton = new Button(entryTable, SWT.RADIO);
		turnSideFeedbackButton.setText("Turn S-IDE Feedback Off");

		final Label stopCollectingDataLabel = new Label(entryTable, SWT.WRAP);
		final GridData gd_stopCollectingDataLabel = new GridData(SWT.LEFT, SWT.TOP, true, false);
		gd_stopCollectingDataLabel.horizontalIndent = 30;
		gd_stopCollectingDataLabel.heightHint = 37;
		gd_stopCollectingDataLabel.widthHint = 451;
		stopCollectingDataLabel.setLayoutData(gd_stopCollectingDataLabel);
		stopCollectingDataLabel.setText("Stop collecting data. The S-IDE Feedback will be turned off and data will never be uploaded.");

		return entryTable;
	}

	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	public void applyData(Object data) {
		super.applyData(data);
		setPreferences();
	}

	@Override
	public boolean performOk() {
		setPreferences();
		return super.performOk();
	}

	private void setPreferences() {

	}
}
