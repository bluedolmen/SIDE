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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import com.bluexml.side.Portal.modeler.PortalPlugin;
import com.bluexml.side.portal.isChildPage;

public class IsChildPageEditDialog  extends Dialog implements IDialogConstants {

	private isChildPage isChildPage;
	
	private Map<String, Object> data;
	
	private static final int MIN_DIALOG_WIDTH = 500;

	private static final int MIN_DIALOG_HEIGHT = 300;
	
	public static final String ISCHILDPAGE_Inherit = "isChildPage inherit";
	
	private Button inherit;
	
	public IsChildPageEditDialog(isChildPage p_isChildPage, Shell p_parentShell) {
		super(p_parentShell);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		isChildPage = p_isChildPage;
	}
	
	public Map<String, Object> getData() {
		return data;
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite dialogComposite = (Composite) super.createDialogArea(parent);
		GridData dialogLayoutData = new GridData(GridData.FILL_BOTH);
		dialogLayoutData.widthHint = MIN_DIALOG_WIDTH;
		dialogLayoutData.heightHint = MIN_DIALOG_HEIGHT;
		dialogComposite.setLayoutData(dialogLayoutData);

		createIsChildPageGroup(dialogComposite);		
		loadData();
		return dialogComposite;
	}
	
	protected void createIsChildPageGroup(Composite parent) {
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
		propertyIdLbl.setText("Inherit : ");
		inherit = new Button(composite,SWT.CHECK);
		inherit.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}
	
	/**
	 * Initialize the content of the widgets
	 */
	private void loadData() {	
		inherit.setSelection(isChildPage.isInherit());
	}
	
	
	/**
	 * Save the values before the widgets are disposed
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		data = new HashMap<String, Object>();
		try {
			data.put(ISCHILDPAGE_Inherit, inherit.getSelection());
			super.okPressed();
		} catch (Exception e) {
			// TODO change this with a validation listener that disable the ok
			// button until the widgets are valid
			PortalPlugin.log("Required fields", IStatus.WARNING);
			MessageDialog.openWarning(getShell(), "Required parameters",
							"Some parameters are not set.\nPlease, fill those fields before validating.");
		}
	}
}
