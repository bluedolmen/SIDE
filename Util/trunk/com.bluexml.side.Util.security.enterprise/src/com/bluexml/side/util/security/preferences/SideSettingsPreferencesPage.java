package com.bluexml.side.util.security.preferences;

import java.net.URL;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormText;

import com.bluexml.side.util.libs.SystemInfoGetter;
import com.bluexml.side.util.security.Activator;

/**
 * The BadWordsColorPreferencePage is a preference page that
 * handles setting the colors used by the editors.
 */

public class SideSettingsPreferencesPage
	extends PreferencePage
	implements IWorkbenchPreferencePage {
	private static final String linkText =
		"<form><p>To register go to the <a href='http://www.bluexml.com/v3/membership/'> Membership page</a> on our website.</p></form>";
	private static final String iDText ="Unique ID for Registering : (Copy/paste enable)";
	/*
	 * @see PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {

		Composite entryTable = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		entryTable.setLayout(layout);
		//label info for machine name
		Label lblName = new Label(entryTable,SWT.NONE);
		lblName.setText(iDText);
		//Machine name
		Text textNomMachine = new Text(entryTable, SWT.BORDER | SWT.SINGLE);
		textNomMachine.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		textNomMachine.setEditable(true);
		textNomMachine.setText(SystemInfoGetter.getHostWithHash());
		//link to SIDE website
		FormText link = new FormText(entryTable, SWT.NONE);
		link.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		link.setBounds(137, 269, 223, 44);
		link.setText(linkText, true, true);
		//Litener for link
		link.addHyperlinkListener(new HyperlinkAdapter() {
			@Override
			public void linkActivated(HyperlinkEvent event) {
				try {
					PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(new URL(event.getHref().toString()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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