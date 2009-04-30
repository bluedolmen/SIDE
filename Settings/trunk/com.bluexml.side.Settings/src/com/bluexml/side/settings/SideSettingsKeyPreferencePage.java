package com.bluexml.side.settings;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.bluexml.side.application.security.KeyInformation;

/**
 * The home of the preferences in S-IDE
 */

public class SideSettingsKeyPreferencePage
	extends PreferencePage
	implements IWorkbenchPreferencePage {
	private Label lblValidity;
	private Text key;

	/*
	 * @see PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite entryTable = new Composite(parent, SWT.NULL);

		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		entryTable.setLayout(layout);

		Label lbl = new Label(entryTable,SWT.NONE);
		lbl.setText("Licence Key");
		
		key = new Text(entryTable,SWT.NONE);
		key.setText(Activator.getKey());
		key.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		key.addListener(SWT.Modify, new Listener() {
			public void handleEvent(Event event) {
				check();
			}
        });

		
		lblValidity = new Label(entryTable,SWT.NONE);
		lblValidity.setFont(new Font(lblValidity.getDisplay(),"Arial",12,SWT.BOLD));
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
		key.setText(Activator.KEY_DEFAULT);
		performOk();
	}
	/** 
	 * Method declared on IPreferencePage. Save the
	 * color preference to the preference store.
	 */
	public boolean performOk() {
		Activator.setKey(key.getText());
		return super.performOk();
	}
	
	private void check(){
		KeyInformation ki = new KeyInformation(key.getText());
		if (ki.getValidity()){
			lblValidity.setForeground(new Color(lblValidity.getDisplay(),120,255,120));
			lblValidity.setText("OK");
		}
		else{
			lblValidity.setForeground(new Color(lblValidity.getDisplay(),255,33,33));
			lblValidity.setText("KO");
		}
	}
}