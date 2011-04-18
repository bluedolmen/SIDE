package com.bluexml.side.Portal.modeler.diagram.dialogs;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.bluexml.side.Portal.modeler.PortalPlugin;
import com.bluexml.side.portal.Portlet;

public class PortletEditDialog extends Dialog implements IDialogConstants {

	protected Portlet portlet;

	private static final int MIN_DIALOG_WIDTH = 500;

	private static final int MIN_DIALOG_HEIGHT = 300;

	private Text pageName;

	protected HashMap<String, Object> data;

	public static final String PORTLET_Name = "portlet name";

	public PortletEditDialog(Portlet p_portlet, Shell p_parentShell) {
		super(p_parentShell);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		portlet = p_portlet;
	}

	protected Control createDialogArea(Composite parent) {
		Composite dialogComposite = (Composite) super.createDialogArea(parent);
		GridData dialogLayoutData = new GridData(GridData.FILL_BOTH);
		dialogLayoutData.widthHint = MIN_DIALOG_WIDTH;
		dialogLayoutData.heightHint = MIN_DIALOG_HEIGHT;
		dialogComposite.setLayoutData(dialogLayoutData);

		createPageGroup(dialogComposite);

		loadData();

		return dialogComposite;
	}

	protected void createPageGroup(Composite parent) {
		TabFolder tabFolder = new TabFolder(parent, SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createGeneralTab(tabFolder);
	}

	private void createGeneralTab(Composite parent) {
		// Create tab item and add it composite that fills it
		TabItem generalItem = new TabItem((TabFolder) parent, SWT.NONE);
		generalItem.setText("General");
		Composite composite = new Composite(parent, SWT.NONE);
		generalItem.setControl(composite);

		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label propertyIdLbl = new Label(composite, SWT.NONE);
		propertyIdLbl.setText("Name : ");
		pageName = new Text(composite, SWT.BORDER);
		pageName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	/**
	 * Initialize the content of the widgets
	 */
	private void loadData() {
		if (portlet.getName() != null) {
			pageName.setText(portlet.getName());
		}
	}

	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * Save the values before the widgets are disposed
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		data = new HashMap<String, Object>();
		try {
			data.put(PORTLET_Name, pageName.getText());
			super.okPressed();
		} catch (Exception e) {
			// TODO change this with a validation listener that disable the ok
			// button until the widgets are valid
			PortalPlugin.log("Required fields", IStatus.WARNING);
			MessageDialog.openWarning(getShell(), "Required parameters", "Some parameters are not set.\nPlease, fill those fields before validating.");
		}
	}
}
