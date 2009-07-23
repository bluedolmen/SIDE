package com.bluexml.side.util.settings;



import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class SideSettingsFeedbackPreferencesPage extends PreferencePage
implements IWorkbenchPreferencePage {

	@Override
	protected Control createContents(Composite parent) {
		Composite entryTable = new Composite(parent, SWT.NULL);

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		entryTable.setLayout(layout);

		final Label lbl = new Label(entryTable, SWT.RIGHT);
		lbl.setAlignment(SWT.RIGHT);
		lbl.setText("Authorize Feedback");

		final Button checkbox = new Button(entryTable, SWT.CHECK);
		checkbox.setSelection(SidePreferences.getFeedBackPreference());
		checkbox.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				checkbox.setSelection(SidePreferences.getDefaultFeedBackPreference());
			}

			public void widgetSelected(SelectionEvent e) {
				SidePreferences.setFeedBackPreference(checkbox.getSelection());
			}

		});

		return entryTable;
	}

	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

}
