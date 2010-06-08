package com.bluexml.side.util.feedback.ui;

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
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormText;

import com.bluexml.side.util.feedback.FeedbackActivator;
import com.bluexml.side.util.feedback.management.FeedbackSender;

public class PopUpDialogBox extends Dialog {

	protected Button uploadNow;
	protected Button uploadAlways;
	protected Button dontUploadNowButton;
	protected Button noFeedbackButton;
	protected int choice;
	private Button acceptTermsButton;
	protected Shell parentShell;

	public PopUpDialogBox(Shell parentShell) {
		super(parentShell);
		choice = FeedbackActivator.getFeedBackPreference();
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

		final FormText theSideFeedbackLabel = new FormText(entryTable, SWT.WRAP);
		theSideFeedbackLabel.setLayoutData(new GridData(494, 63));
		theSideFeedbackLabel
				.setText(Messages.SidePopUp_0, true, true);
		theSideFeedbackLabel.addHyperlinkListener(new HyperlinkAdapter() {
			@Override
			public void linkActivated(HyperlinkEvent event) {
				TermOfUsePopUp termOfUse = new TermOfUsePopUp(getShell());
				termOfUse.open();
			}
		});
		uploadNow = new Button(entryTable, SWT.RADIO);
		uploadNow.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				choice = FeedbackActivator.FEEDBACK_PREF_NOW;
			}
		});
		uploadNow.setText(Messages.SidePopUp_1);
		checkIfSelect(uploadNow,FeedbackActivator.FEEDBACK_PREF_NOW);

		final Label lbluploadNow = new Label(entryTable, SWT.RIGHT);
		final GridData gd_lbluploadNow = new GridData(SWT.FILL, SWT.CENTER,
				false, false);
		gd_lbluploadNow.horizontalIndent = 30;
		lbluploadNow.setLayoutData(gd_lbluploadNow);
		lbluploadNow.setAlignment(SWT.LEFT);
		lbluploadNow
				.setText(Messages.SidePopUp_2);

		uploadAlways = new Button(entryTable, SWT.RADIO);
		uploadAlways.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				choice = FeedbackActivator.FEEDBACK_PREF_ALWAYS;
			}
		});
		uploadAlways.setText(Messages.SidePopUp_3);
		checkIfSelect(uploadAlways,FeedbackActivator.FEEDBACK_PREF_ALWAYS);

		final Label lbluploadAlways = new Label(entryTable, SWT.WRAP);
		final GridData gd_lbluploadAlways = new GridData(456, 48);
		gd_lbluploadAlways.horizontalIndent = 30;
		lbluploadAlways.setLayoutData(gd_lbluploadAlways);
		lbluploadAlways
				.setText(Messages.SidePopUp_4);

		dontUploadNowButton = new Button(entryTable, SWT.RADIO);
		dontUploadNowButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				choice = FeedbackActivator.FEEDBACK_PREF_NOTNOW;
			}
		});
		dontUploadNowButton.setText(Messages.SidePopUp_5);
		checkIfSelect(dontUploadNowButton,FeedbackActivator.FEEDBACK_PREF_NOTNOW);

		final Label doNotUploadLabel = new Label(entryTable, SWT.NONE);
		final GridData gd_doNotUploadLabel = new GridData(SWT.FILL, SWT.CENTER,
				false, false);
		gd_doNotUploadLabel.horizontalIndent = 30;
		gd_doNotUploadLabel.heightHint = 18;
		doNotUploadLabel.setLayoutData(gd_doNotUploadLabel);
		doNotUploadLabel
				.setText(Messages.SidePopUp_6);

		noFeedbackButton = new Button(entryTable, SWT.RADIO);
		noFeedbackButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				choice = FeedbackActivator.FEEDBACK_PREF_NEVER;
			}
		});
		noFeedbackButton.setText(Messages.SidePopUp_7);
		checkIfSelect(noFeedbackButton,FeedbackActivator.FEEDBACK_PREF_NEVER);

		final Label stopCollectingDataLabel = new Label(entryTable, SWT.WRAP);
		final GridData gd_stopCollectingDataLabel = new GridData(SWT.LEFT,
				SWT.TOP, true, false);
		gd_stopCollectingDataLabel.horizontalIndent = 30;
		gd_stopCollectingDataLabel.heightHint = 37;
		gd_stopCollectingDataLabel.widthHint = 451;
		stopCollectingDataLabel.setLayoutData(gd_stopCollectingDataLabel);
		stopCollectingDataLabel
				.setText(Messages.SidePopUp_8);

		acceptTermsButton = new Button(entryTable, SWT.CHECK);
		acceptTermsButton.setText(Messages.SideTermsOfUsePage_0);
		GridData gridData = new GridData(SWT.BEGINNING, SWT.FILL, true, false);
		acceptTermsButton.setLayoutData(gridData);
		acceptTermsButton.setSelection(FeedbackActivator.getFeedbackTermOfUseAccepted());
		return entryTable;
	}

	private void checkIfSelect(Button radio, int correspondingPref) {
		if (correspondingPref == FeedbackActivator.getFeedBackPreference()) {
			radio.setSelection(true);
		}
	}

	@Override
	protected void okPressed() {
		updatePreferences();
		super.okPressed();
	}

	private void updatePreferences() {
		switch (choice) {
		case FeedbackActivator.FEEDBACK_PREF_ALWAYS:
			// Change preference setting (made after) and send data
			doSend();
			break;
		case FeedbackActivator.FEEDBACK_PREF_NOW:
			// Send now and change preference setting and send data
			doSend();
			break;
		case FeedbackActivator.FEEDBACK_PREF_NEVER:
			// change preference setting (made after)
			break;
		case FeedbackActivator.FEEDBACK_PREF_NOTNOW:
			// change preference setting (made after)
			break;
		default:
			break;
		}
		FeedbackActivator.setFeedBackPreference(choice);
		FeedbackActivator.setFeedbackTermOfUseAccepted(acceptTermsButton.getSelection());
	}

	private void doSend() {
		FeedbackSender.doSend();
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("SIDE Feedback");
	}

}
