package com.bluexml.side.util.security.preferences;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.bluexml.side.util.security.Activator;
import com.bluexml.side.util.security.CodeHelper;
import com.bluexml.side.util.security.KeyInformation;

/**
 * The home of the preferences in SIDE
 */

public class SideSettingsKeyPreferencesPage extends PreferencePage implements IWorkbenchPreferencePage {
	public SideSettingsKeyPreferencesPage() {
	}

	private Label lbl;
	private Label lblValidity;
	private static final String validationTextOk = "Your key is valid until ";
	private static final String validationTextKo = "Your key is not valid.                                               ";
	private static final String listComponentText = "These componentes are available:";
	private static final String dateFormat = "MM/dd/yyyy";
	private Label validationDate;
	private Text key;
	// Variables for the list of components
	private Table codeTable;
	private static HashMap<String, String> codeToIcon = CodeHelper.getCodeToIcon();
	private static HashMap<String, String> codeToName = CodeHelper.getCodeToName();

	/*
	 * @see PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite entryTable = new Composite(parent, SWT.NULL);

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		entryTable.setLayout(layout);

		// Label for title
		lbl = new Label(entryTable, SWT.NONE);
		lbl.setText("Licence Key :");
		new Label(entryTable, SWT.NONE);

		// Input for the key
		key = new Text(entryTable, SWT.BORDER);
		{
			GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, true, false);
			// gridData.widthHint = 359;
			key.setLayoutData(gridData);
		}
		key.setText(SidePreferences.getKey());
		key.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent e) {
				update();
			}
		});

		// Label for image validity
		lblValidity = new Label(entryTable, SWT.NONE);

		// Label for validation Date
		validationDate = new Label(entryTable, SWT.NONE);
		validationDate.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));

		// Grid for components
		Label label = new Label(entryTable, SWT.NONE);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		label.setText(listComponentText);
		createTable(entryTable);
		new Label(entryTable, SWT.NONE);

		update();
		return entryTable;
	}

	/*
	 * @see IWorkbenchPreferencePage#init(IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		// Initialize the preference store we wish to use
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	/**
	 * Performs special processing when this page's Restore Defaults button has
	 * been pressed. Sets the contents of the color field to the default value
	 * in the preference store.
	 */
	protected void performDefaults() {
		SidePreferences.setDefaultKey();
		performOk();
	}

	/**
	 * Method declared on IPreferencePage. Save the color preference to the
	 * preference store.
	 */
	public boolean performOk() {
		SidePreferences.setKey(getTrimedKey());
		return super.performOk();
	}

	private String getTrimedKey() {
		return key.getText().trim();
	}

	/**
	 * This function update the display when the key change
	 */
	private void update() {
		tableUpdate();
		check();
	}

	/**
	 * This function check the validity of the key and update the different
	 * displays of the page
	 */
	private void check() {
		KeyInformation ki = new KeyInformation(getTrimedKey());
		DateFormat df = new SimpleDateFormat(dateFormat);
		if (ki.getValidity()) {
			lblValidity.setImage(new Image(lblValidity.getDisplay(), SideSettingsKeyPreferencesPage.class.getResourceAsStream("icons/OK.png")));
			validationDate.setText(validationTextOk + df.format(ki.getValidationDate()) + ". (" + dateFormat + ")");
		} else {
			lblValidity.setImage(new Image(lblValidity.getDisplay(), SideSettingsKeyPreferencesPage.class.getResourceAsStream("icons/KO.png")));
			validationDate.setText(validationTextKo);
		}
	}

	/**
	 * This function update the table
	 */
	private void tableUpdate() {
		initTable();
		KeyInformation ki = new KeyInformation(getTrimedKey());
		if (ki.getValidity()) {
			int i = 0;
			// sort by names
			TreeMap<String, String> tm = new TreeMap<String, String>();
			for (String code : codeToName.keySet()) {
				tm.put(codeToName.get(code), code);
			}

			for (Map.Entry<String, String> entry : tm.entrySet()) {
				TableItem item = codeTable.getItem(i);
				String code = entry.getValue();
				if (ki.hasCode(code)) {
					setItem(item, code, null);
				}
				i++;
			}
		}
	}

	private void setItem(TableItem item, String code, String icon) {
		int index = 0;
		String label = codeToName.get(code);
		if (label.contains("/")) {
			label = label.substring(label.lastIndexOf("/") + 1);
			index = 1;
			item.setImage(0, null);
		}
		if (icon == null) {
			icon = codeToIcon.get(code);
		}
		item.setImage(index, new Image(item.getDisplay(), SideSettingsKeyPreferencesPage.class.getResourceAsStream("icons/" + icon)));
		item.setText(1, label);
	}

	/**
	 * Create the table in the SWT page
	 * 
	 * @param parent
	 *            the component in which is displayed the table
	 */
	private void createTable(Composite parent) {
		codeTable = new Table(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		TableColumn column1 = new TableColumn(codeTable, SWT.NONE);
		TableColumn column2 = new TableColumn(codeTable, SWT.NONE);
		for (int i = 0; i < codeToIcon.keySet().size(); i++) {
			new TableItem(codeTable, 0);
		}
		initTable();
		column1.pack();
		column2.pack();
	}

	/**
	 * initialize the table with void value and specific icon
	 */
	private void initTable() {
		String icon = "no_value.png";
		int i = 0;
		// sort by names
		TreeMap<String, String> tm = new TreeMap<String, String>();
		for (String code : codeToName.keySet()) {
			tm.put(codeToName.get(code), code);
		}
		for (Map.Entry<String, String> entry : tm.entrySet()) {
			String code = entry.getValue();
			TableItem item = codeTable.getItem(i);
			setItem(item, code, icon);
			i++;
		}
	}
}