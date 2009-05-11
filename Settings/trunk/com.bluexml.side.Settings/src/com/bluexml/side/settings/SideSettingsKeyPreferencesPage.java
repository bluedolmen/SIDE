package com.bluexml.side.settings;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.bluexml.side.application.security.KeyInformation;

/**
 * The home of the preferences in S-IDE
 */

public class SideSettingsKeyPreferencesPage
	extends PreferencePage
	implements IWorkbenchPreferencePage {
	private Label lbl;
	private Label lblValidity;
	private static final String validationTextOk ="Your key is valid until ";
	private static final String validationTextKo ="Your key is not valid.                       ";
	private static final String dateFormat ="MM/dd/yyyy";
	private Label validationDate;
	private Text key;
	
	/*
	 * @see PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite entryTable = new Composite(parent, SWT.NULL);

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		entryTable.setLayout(layout);

		lblValidity = new Label(entryTable, SWT.RIGHT);
		lblValidity.setAlignment(SWT.RIGHT);
		lblValidity.setFont(new Font(entryTable.getDisplay(), "Courier New", 12, SWT.BOLD));

		lbl = new Label(entryTable,SWT.NONE);
		lbl.setLayoutData(new GridData());
		lbl.setText("Licence Key :");

		key = new Text(entryTable, SWT.BORDER);
		key.setText(SidePreferences.getKey());
		key.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent e) {
				check();
			}
		});
		final GridData gd_key = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		key.setLayoutData(gd_key);

		validationDate = new Label(entryTable, SWT.NONE);
		final GridData gd_validationDate = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		validationDate.setLayoutData(gd_validationDate);
		
		
		check();
		return entryTable;
	}

	/*
	 * @see IWorkbenchPreferencePage#init(IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		//Initialize the preference store we wish to use
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	/**
	 * Performs special processing when this page's Restore Defaults button has 
	 * been pressed.
	 * Sets the contents of the color field to the default value in the preference
	 * store.
	 */
	protected void performDefaults() {
		SidePreferences.setDefaultKey();
		performOk();
	}
	/** 
	 * Method declared on IPreferencePage. Save the
	 * color preference to the preference store.
	 */
	public boolean performOk() {
		SidePreferences.setKey(key.getText());
		return super.performOk();
	}
	
	private void check(){
		KeyInformation ki = new KeyInformation(key.getText());
		DateFormat df = new SimpleDateFormat(dateFormat);
		if (ki.getValidity()){
			lblValidity.setImage(new Image(lblValidity.getDisplay(),SideSettingsKeyPreferencesPage.class.getResourceAsStream("OK.png")));
			validationDate.setText(validationTextOk + df.format(ki.getValidationDate())+". ("+dateFormat+")");
		}
		else{
			lblValidity.setImage(new Image(lblValidity.getDisplay(),SideSettingsKeyPreferencesPage.class.getResourceAsStream("KO.png")));
			validationDate.setText(validationTextKo);
		}
	}
}