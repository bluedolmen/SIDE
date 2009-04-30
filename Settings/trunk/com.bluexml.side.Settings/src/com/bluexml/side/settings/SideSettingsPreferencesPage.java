package com.bluexml.side.settings;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * The BadWordsColorPreferencePage is a preference page that
 * handles setting the colors used by the editors.
 */

public class SideSettingsPreferencesPage 
	extends PreferencePage
	implements IWorkbenchPreferencePage {


	/*
	 * @see PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {

		Composite entryTable = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		entryTable.setLayout(layout);
		Label lbl = new Label(entryTable,SWT.NONE);
		lbl.setText("General settings for S-IDE");
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
	protected void performDefaults() {}
	
	/** 
	 * Method declared on IPreferencePage. Save the
	 * color preference to the preference store.
	 */
	public boolean performOk() {
		return super.performOk();
	}

}