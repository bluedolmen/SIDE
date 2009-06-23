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
import com.bluexml.side.Class.modeler.diagram.dialogs.EnumerationLiteralDataStructure.EnumerationLiteralObject;
import com.bluexml.side.clazz.Enumeration;

public class EnumerationEditDialog extends Dialog implements IDialogConstants {

	public static final String ENUMERATION_NAME = "Enumeration name";
	public static final String ENUMERATION_LITERALS = "Enumeration Literals";
	public static final String ENUMERATION_ISDYNAMIC = "Enumeration isDynamic";
	
	private static final int MIN_DIALOG_WIDTH = 500;

	private static final int MIN_DIALOG_HEIGHT = 300;
	
	private EnumerationLiteralViewer inputParameters;
	
	private Button checkIsDynamicBt;

	/** Current edited property */
	private Enumeration object;

	// SWT Objects
	private Text EnumerationNameTxt;

	private Map data;

	/**
	 * Create the dialog for a given Attribute
	 * 
	 * @param prop
	 *            the Attribute to edit
	 * @param parentShell
	 *            the parent shell
	 */
	public EnumerationEditDialog(Enumeration object, Shell parentShell) {
		super(parentShell);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		this.object = object;
	}

	/**
	 * Set the title of the new shell
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		newShell.setText("Enumeration " + object.getName());
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

		createEnumerationGroup(dialogComposite);

		loadData();

		return dialogComposite;
	}

	/**
	 * Creates the group
	 * 
	 * @param parent
	 *            the parent Composite
	 */
	private void createEnumerationGroup(Composite parent) {
		TabFolder tabFolder = new TabFolder(parent, SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createGeneralTab(tabFolder);
		createLiteralsTab(tabFolder);
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
		EnumerationNameTxt = new Text(composite, SWT.BORDER);
		EnumerationNameTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label propertyIsDynamicLbl = new Label(composite, SWT.NONE);
		propertyIsDynamicLbl.setText("Is Dynamic : ");		
		checkIsDynamicBt = new Button(composite, SWT.CHECK);				
		checkIsDynamicBt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}
	
	private void createLiteralsTab(TabFolder parent) {
		// Create tab item and add it composite that fills it
		TabItem secondItem = new TabItem(parent, SWT.NONE);
		secondItem.setText("Value");
		Composite composite = new Composite(parent, SWT.NONE);
		secondItem.setControl(composite);

		// Add layout on composite
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

		new Label(panelTable, SWT.NONE).setText("Value");

		inputParameters = new EnumerationLiteralViewer(panelTable,
				new EnumerationLiteralDataStructure(object));

		Button add = new Button(panelButton, SWT.PUSH | SWT.CENTER);
		add.setText("Add");

		GridData gd = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_END);
		gd.widthHint = 80;
		add.setLayoutData(gd);
		add.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputParameters.addParameter();
			}
		});

		Button delete = new Button(panelButton, SWT.PUSH | SWT.CENTER);
		delete.setText("Delete");
		gd = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.widthHint = 80;
		delete.setLayoutData(gd);

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
				int index = inputParameters.getTable().getSelectionIndex();
				if (index > 0) {
					TableItem itemMoved = inputParameters.getTable().getItem(index);
					TableItem otherItem = inputParameters.getTable().getItem(index-1);
					inputParameters.getData().replace(index,index-1);
					Object objectMoved = itemMoved.getData();
					Object otherObject = otherItem.getData();
					itemMoved.setData(otherObject);
					otherItem.setData(objectMoved);					
					itemMoved.setText(((EnumerationLiteralObject)otherObject).getName());
					otherItem.setText(((EnumerationLiteralObject)objectMoved).getName());
					inputParameters.getTable().select(index-1);
				}
			}
		});
		
		//Create Button DOWN
		Button down = new Button(panelButton, SWT.PUSH | SWT.CENTER);
		down.setText("Down");
		down.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int index = inputParameters.getTable().getSelectionIndex();
				if (index < inputParameters.getTable().getItemCount()-1) {
					TableItem itemMoved = inputParameters.getTable().getItem(index);
					TableItem otherItem = inputParameters.getTable().getItem(index+1);
					inputParameters.getData().replace(index,index+1);
					Object objectMoved = itemMoved.getData();
					Object otherObject = otherItem.getData();
					itemMoved.setData(otherObject);
					otherItem.setData(objectMoved);
					itemMoved.setText(((EnumerationLiteralObject)otherObject).getName());
					otherItem.setText(((EnumerationLiteralObject)objectMoved).getName());
					inputParameters.getTable().select(index+1);
				}
			}
		});

	}

	/**
	 * Initialize the content of the widgets
	 */
	private void loadData() {
		if (object != null) {
			// Name
			EnumerationNameTxt.setText(object.getName());
			checkIsDynamicBt.setSelection(object.getIsDynamic());
		}				
	}

	/**
	 * Save the values before the widgets are disposed
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		data = new HashMap();
		try {
			data.put(ENUMERATION_NAME, EnumerationNameTxt.getText());
			data.put(ENUMERATION_LITERALS, inputParameters.getData());
			data.put(ENUMERATION_ISDYNAMIC, checkIsDynamicBt.getSelection());
			super.okPressed();
		} catch (Exception e) {
			ClazzPlugin.log("Required fields", IStatus.WARNING);
			MessageDialog
					.openWarning(getShell(), "Required parameters",
							"Some parameters are not set.\nPlease, fill those fields before validating.");
		}
	}

	/**
	 * Return a map containing Attribute data that may changed
	 * 
	 * @return a Map
	 */
	public Map getData() {
		return data;
	}

}
