package com.bluexml.side.util.settings;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.bluexml.side.util.libs.SystemInfoGetter;

/**
 * The BadWordsColorPreferencePage is a preference page that
 * handles setting the colors used by the editors.
 */

public class SideSettingsPreferencesPage 
	extends PreferencePage
	implements IWorkbenchPreferencePage {
	private static final String linkMembership = "http://www.bluexml.com/v3/membership/";
	private static final String linkText =
		"To register go to the <a href='"+linkMembership+"' target='blank'>Membership page</A> on our website.<br />"+
		"  <a href='"+linkMembership+"' target='blank'>"+linkMembership+"</A>";
	private static final String iDText ="Unique ID for Registering : (Copy/paste enable)";
	/*
	 * @see PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {

		Composite entryTable = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		entryTable.setLayout(layout);

		String nomMachine = SystemInfoGetter.getHostWithHash();

		Label lblName = new Label(entryTable,SWT.NONE);
		lblName.setText(iDText);
		Text textNomMachine = new Text(entryTable,SWT.NONE);
		textNomMachine.setEditable(false);
		textNomMachine.setText(nomMachine);
		Browser link = new Browser(entryTable, SWT.NONE);
		link.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		link.setText(linkText);
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