/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
import com.bluexml.side.portal.Page;

public class PageEditDialog extends Dialog implements IDialogConstants{

	/** The ID of the property name */
	public static final String PAGE_ID = "page ID";
	public static final String PAGE_TITLE = "page title";
	public static final String PAGE_DESCRIPTION = "page description";
	public static final String PAGE_POSITION = "page position";
	
	public static final String PAGE_UseLayout ="page layout";
	public static final String PAGE_Portlets ="page portlets";
	public static final String PAGE_IsChildPageOf = "page subpages";
	public static final String PAGE_Visibility = "page visibility";
	public static final String PAGE_Generate = "page generate";
	
	protected Page page;
	
	protected Map<String, Object> data;

	private Text pageId;

	private Text pageTitle;

	private Text pageDescription;
	private Text pagePos;	
	
	private static final int MIN_DIALOG_WIDTH = 500;

	private static final int MIN_DIALOG_HEIGHT = 300;
	
	public PageEditDialog(Page p_page, Shell p_parentShell) {
		super(p_parentShell);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		page = p_page;			
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
		createDescriptionTab(tabFolder);
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
		propertyIdLbl.setText("ID : ");
		pageId = new Text(composite, SWT.BORDER);
		pageId.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label propertyTitleLbl = new Label(composite, SWT.NONE);
		propertyTitleLbl.setText("Title : ");
		pageTitle = new Text(composite, SWT.BORDER);
		pageTitle.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label propertyPosLbl = new Label(composite, SWT.NONE);
		propertyPosLbl.setText("Position : ");
		pagePos = new Text(composite, SWT.BORDER);
		pagePos.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}
	
	
	private void createDescriptionTab(Composite parent) {
		// Create tab item and add it composite that fills it
		TabItem viewItem = new TabItem((TabFolder) parent, SWT.NONE);
		viewItem.setText("Documentation");
		Composite composite = new Composite(parent, SWT.NONE);
		viewItem.setControl(composite);

		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		pageDescription = new Text(composite, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL
				| SWT.BORDER);
		pageDescription.setLayoutData(new GridData(GridData.FILL_BOTH));
	}
	
	/**
	 * Initialize the content of the widgets
	 */
	private void loadData() {
		if (page.getID() != null) {
			pageId.setText(page.getID());
		}
		if (page.getTitle() != null) {
			pageTitle.setText(page.getTitle());
		}
		if (page.getDescription() != null) {
			pageDescription.setText(page.getDescription());
		}
		if (page.getPosition() > 0) {
			pagePos.setText(Integer.toString(page.getPosition()));
		}
	}

	/**
	 * Return a map containing Attribute data that may changed
	 * 
	 * @return a Map
	 */
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
			data.put(PAGE_ID, pageId.getText());
			data.put(PAGE_DESCRIPTION, pageDescription.getText());
			data.put(PAGE_TITLE,pageTitle.getText());
			data.put(PAGE_POSITION,pagePos.getText());
			
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
