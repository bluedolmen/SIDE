package com.bluexml.side.util.feedback.ui;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Date;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.bluexml.side.util.feedback.FeedbackActivator;
import com.bluexml.side.util.feedback.utils.FeedbackUtils;

public class SideSettingsFeedbackPreferencesPage extends PreferencePage
		implements IWorkbenchPreferencePage {

	protected static int MAXIMUM_PERIOD_IN_DAYS = 90;

	protected static int MINIMUM_PERIOD_IN_DAYS = 7;

	private Label label;
	private Text lastUploadText;
	private Text uploadPeriodText;


	@Override
	protected Control createContents(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite.setLayout(new GridLayout());

		Group group = new Group(composite, SWT.NONE);
		group.setText(Messages.SideFeedbackPreferencesPage_2);
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		group.setLayout(new GridLayout(3, false));

		// Create the layout that will be used by all the fields.
		GridData fieldLayoutData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		fieldLayoutData.horizontalIndent = FieldDecorationRegistry.getDefault().getMaximumDecorationWidth();

		createUploadPeriodField(group);
		createLastUploadField(group);

		final Button setUploadPreferencesButton = new Button(composite, SWT.NONE);
		setUploadPreferencesButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				PopUpDialogBox popup = new PopUpDialogBox(Display.getDefault().getActiveShell());
				popup.open();
			}
		});
		setUploadPreferencesButton.setText(Messages.SideFeedbackPreferencesPage_7);
		setUploadPreferencesButton.setBounds(10, 126, 156, 25);

		Image imageStats = null;
		final Label lbl = new Label(composite, SWT.NONE);
		lbl.setText(Messages.SideFeedbackPreferencesPage_8);

		final Label l = new Label(composite, SWT.CENTER);
		l.setLayoutData(new GridData());
		try {
			URL url = new URL(FeedbackActivator.STATS_URL);
			InputStream is = url.openStream();
			imageStats = new Image(composite.getDisplay(), is);

			is.close();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		l.setImage(imageStats);
		final Cursor cursor = new Cursor(l.getDisplay(), SWT.CURSOR_HAND);
		l.addMouseTrackListener(new MouseTrackAdapter() {
			public void mouseHover(final MouseEvent e) {
				l.setCursor(cursor);
			}
		});
		l.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event e) {
		        l.setCursor(cursor);
		      }
		    });

		l.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				FeedbackUtils.browseTo(FeedbackActivator.STATS_LINK_URL);
			}
		});
		return composite;
	}

	/*
	 * Note that this method expects to be run in the UI Thread.
	 */
	private void createUploadPeriodField(Group composite) {
		Label label = new Label(composite, SWT.NONE);
		label.setText(Messages.SideFeedbackPreferencesPage_3);

		uploadPeriodText = new Text(composite, SWT.SINGLE | SWT.BORDER | SWT.RIGHT);
		uploadPeriodText.setTextLimit(2);
		GridData gridData = new GridData(SWT.FILL, SWT.CENTER, false, false);
		gridData.horizontalIndent = FieldDecorationRegistry.getDefault().getMaximumDecorationWidth();
		gridData.horizontalSpan = 1;
		GC gc = new GC(uploadPeriodText.getDisplay());
		gc.setFont(uploadPeriodText.getFont());
		gridData.widthHint = gc.stringExtent(String.valueOf(MAXIMUM_PERIOD_IN_DAYS)).x;
		gc.dispose();
		uploadPeriodText.setLayoutData(gridData);

		new Label(composite, SWT.NONE).setText(Messages.SideFeedbackPreferencesPage_4);

		final ControlDecoration rangeErrorDecoration = new ControlDecoration(uploadPeriodText, SWT.LEFT | SWT.TOP);
		rangeErrorDecoration.setDescriptionText(MessageFormat.format(Messages.SideFeedbackPreferencesPage_5, new Object[] {MINIMUM_PERIOD_IN_DAYS, MAXIMUM_PERIOD_IN_DAYS}));
		rangeErrorDecoration.setImage(getErrorImage());
		rangeErrorDecoration.hide();

		uploadPeriodText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String contents = uploadPeriodText.getText();
				if (isValidUploadPeriod(contents))
					rangeErrorDecoration.hide();
				else {
					rangeErrorDecoration.show();
				}
				updateApplyButton();
				getContainer().updateButtons();
			}
		});
	}


	/*
	 * Note that this method expects to be run in the UI Thread.
	 */
	private void createLastUploadField(Group composite) {
		label = new Label(composite, SWT.NONE);
		label.setText(Messages.SideFeedbackPreferencesPage_6);

		lastUploadText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		lastUploadText.setEnabled(false);
		GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gridData.horizontalIndent = FieldDecorationRegistry.getDefault().getMaximumDecorationWidth();
		gridData.horizontalSpan = 2;
		lastUploadText.setLayoutData(gridData);
		lastUploadText.setText(getLastUploadDateAsString());
		updateUploadPeriodText();
	}

	private Image getErrorImage() {
		return FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR).getImage();
	}

	public void init(IWorkbench workbench) {
		setDescription(Messages.SideFeedbackPreferencesPage_0);
		setPreferenceStore(FeedbackActivator.getDefault().getPreferenceStore());

	}

	private void updateUploadPeriodText() {
		uploadPeriodText.setText(Integer.toString(FeedbackActivator.getFeedbackUploadPeriodPreference()));
	}

	@Override
	public void applyData(Object data) {
		super.applyData(data);
		setPreferences();
	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
		uploadPeriodText.setText(Integer.toString(FeedbackActivator.FEEDBACK_DEFAULT_PERIOD_IN_DAY));
	}

	@Override
	public boolean performOk() {
		setPreferences();
		return super.performOk();
	}

	private void setPreferences() {
		FeedbackActivator.setFeedbackUploadPeriod(Integer.parseInt(uploadPeriodText.getText()));
	}

	private boolean isValidUploadPeriod(String text) {
		try {
			long value = Long.parseLong(text);
			if (value < MINIMUM_PERIOD_IN_DAYS)
				return false;
			if (value > MAXIMUM_PERIOD_IN_DAYS)
				return false;
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private String getLastUploadDateAsString() {
		long time = com.bluexml.side.util.feedback.FeedbackActivator.getDefault().getPreferenceStore().getLong(com.bluexml.side.util.feedback.FeedbackActivator.LAST_UPDATE_DATE);
		Date date = new Date(time);
		return date.toString();
	}

}
