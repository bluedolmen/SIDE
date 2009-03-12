/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2008
 *
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package com.bluexml.side.Class.modeler.diagram.dialogs;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.bluexml.side.Class.modeler.ClazzPlugin;
import com.bluexml.side.Class.modeler.diagram.dialogs.view.ViewAttribute;
import com.bluexml.side.clazz.View;

public class ViewEditDialog extends Dialog implements IDialogConstants {

	/** The ID of the property name */
	public static final String VIEW_NAME = "View name";
	public static final String VIEW_DESCRIPTION = "View description";
	public static final String VIEW_ATTRIBUTES = "View attributes";

	private static final int MIN_DIALOG_WIDTH = 500;

	private static final int MIN_DIALOG_HEIGHT = 300;

	/** Current edited property */
	private View view;

	// SWT Objects
	private Text viewNameTxt;

	private Text viewDescriptionTxt;

	private Map<String, Object> data;

	private ViewItemsViewer inputParameters;

	/**
	 * Create the dialog for a given Attribute
	 * 
	 * @param prop
	 *            the Attribute to edit
	 * @param parentShell
	 *            the parent shell
	 */
	public ViewEditDialog(View view, Shell parentShell) {
		super(parentShell);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		this.view = view;
	}

	/**
	 * Set the title of the new shell
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		newShell.setText("View " + view.getName());
		newShell.setMinimumSize(MIN_DIALOG_WIDTH, MIN_DIALOG_HEIGHT);

		super.configureShell(newShell);
	}

	/**
	 * Create the Dialog area
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		// Dialog
		Composite dialogComposite = (Composite) super.createDialogArea(parent);
		GridData dialogLayoutData = new GridData(GridData.FILL_BOTH);
		dialogLayoutData.widthHint = MIN_DIALOG_WIDTH;
		dialogLayoutData.heightHint = MIN_DIALOG_HEIGHT;
		dialogComposite.setLayoutData(dialogLayoutData);

		createClassGroup(dialogComposite);

		loadData();

		return dialogComposite;
	}

	/**
	 * Creates the group
	 * 
	 * @param parent
	 *            the parent Composite
	 */
	private void createClassGroup(Composite parent) {
		TabFolder tabFolder = new TabFolder(parent, SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createGeneralTab(tabFolder);
		createAttributesTabItem(tabFolder);
	}

	private void createGeneralTab(Composite parent) {
		// Create tab item and add it composite that fills it
		TabItem generalItem = new TabItem((TabFolder) parent, SWT.NONE);
		generalItem.setText("General");
		Composite composite = new Composite(parent, SWT.NONE);
		generalItem.setControl(composite);

		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label propertyNameLbl = new Label(composite, SWT.NONE);
		propertyNameLbl.setText("Name : ");
		viewNameTxt = new Text(composite, SWT.BORDER);
		viewNameTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label propertyDescriptionLbl = new Label(composite, SWT.NONE);
		propertyDescriptionLbl.setText("Description : ");
		viewDescriptionTxt = new Text(composite, SWT.BORDER);
		viewDescriptionTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	private void createAttributesTabItem(TabFolder parent) {
		// Create tab item and add it composite that fills it
		TabItem secondItem = new TabItem(parent, SWT.NONE);
		secondItem.setText("Attributes");
		Composite composite = new Composite(parent, SWT.NONE);
		secondItem.setControl(composite);

		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		//Create panel table
		Composite panelTable = new Composite(composite, SWT.NONE);
		panelTable.setLayout(new GridLayout(1,false));
		panelTable.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		//Create panel button
		Composite panelButton = new Composite(composite, SWT.NONE);
		panelButton.setLayout(new GridLayout(1,false));
		panelButton.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.HORIZONTAL_ALIGN_CENTER));
		
		new Label(panelTable, SWT.NONE).setText("Attributes");

		ViewDataStructure viewDataStructure = new ViewDataStructure(view);
		inputParameters = new ViewItemsViewer(panelTable, viewDataStructure);

		Button add = new Button(panelButton, SWT.PUSH | SWT.CENTER);
		add.setText("Add");

		GridData gd = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END);
		gd.widthHint = 80;
		add.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputParameters.addParameter();
			}
		});

		Button delete = new Button(panelButton, SWT.PUSH | SWT.CENTER);
		delete.setText("Delete");
		gd = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.widthHint = 80;

		delete.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputParameters.removeParameter();
			}
		});
		
		//Create Button UP
		Button up = new Button(panelButton, SWT.PUSH | SWT.CENTER);
		up.setText("Up");
		up.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int index = inputParameters.table.getSelectionIndex();
				
				if (index > 0) {
					TableItem itemMoved = inputParameters.table.getItem(index);
					TableItem otherItem = inputParameters.table.getItem(index-1);
					Object objectMoved = itemMoved.getData();
					Object otherObject = otherItem.getData();
					inputParameters.dataStructure.invert((ViewAttribute) objectMoved,(ViewAttribute)  otherObject);
					inputParameters.tableViewer.refresh();
				}
			}
		});
		
		//Create Button DOWN
		Button down = new Button(panelButton, SWT.PUSH | SWT.CENTER);
		down.setText("Down");
		down.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int index = inputParameters.table.getSelectionIndex();
				
				if (index < inputParameters.table.getItemCount()-1) {
					TableItem itemMoved = inputParameters.table.getItem(index);
					TableItem otherItem = inputParameters.table.getItem(index+1);
					Object objectMoved = itemMoved.getData();
					Object otherObject = otherItem.getData();
					inputParameters.dataStructure.invert((ViewAttribute) objectMoved,(ViewAttribute)  otherObject);
					inputParameters.tableViewer.refresh();
				}
			}
		});
	}

	/**
	 * Initialize the content of the widgets
	 */
	private void loadData() {
		// Name
		if (view.getName() != null)
			viewNameTxt.setText(view.getName());
		if (view.getDescription() != null)
			viewDescriptionTxt.setText(view.getDescription());
	}

	/**
	 * Save the values before the widgets are disposed
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		data = new HashMap<String, Object>();
		try {
			data.put(VIEW_NAME, viewNameTxt.getText());
			data.put(VIEW_DESCRIPTION, viewDescriptionTxt.getText());
			data.put(VIEW_ATTRIBUTES, inputParameters.getData());
			super.okPressed();
		} catch (Exception e) {
			// TODO change this with a validation listener that disable the ok
			// button until the widgets are valid
			ClazzPlugin.log("Required fields", IStatus.WARNING);
			MessageDialog.openWarning(getShell(), "Required parameters",
					"Some parameters are not set.\nPlease, fill those fields before validating.");
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

}
