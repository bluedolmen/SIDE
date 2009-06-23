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
package com.bluexml.side.Workflow.modeler.diagram.dialogs;

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

import com.bluexml.side.Workflow.modeler.WorkflowPlugin;
import com.bluexml.side.workflow.ProcessState;

/**
 * Updating task parameters
 */
public class ProcessStateEditDialog extends Dialog implements IDialogConstants {
	
	public static final String ACTION_VARIABLE = "action variables";

	private static final int MIN_DIALOG_WIDTH = 540;

	private static final int MIN_DIALOG_HEIGHT = 300;


	private VariableViewer inputParameters;
	
	/** Current edited property */
	private ProcessState pState;

	protected HashMap<String, Object> data;

	/**
	 * Create the dialog for a given task
	 */
	public ProcessStateEditDialog(ProcessState state, Shell parentShell) {
		super(parentShell);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		this.pState = state;
	}

	/**
	 * Set the title of the new shell
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		newShell.setText("Process State");
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

		createTaskGroup(dialogComposite);

		loadData();

		return dialogComposite;
	}

	/**
	 * Creates the group
	 * 
	 * @param parent
	 *            the parent Composite
	 */
	protected void createTaskGroup(Composite parent) {
		TabFolder tabFolder = new TabFolder(parent, SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createAttributesTabItem(tabFolder);
	}
	
	private void createAttributesTabItem(TabFolder parent) {
		TabItem secondItem = new TabItem(parent, SWT.NONE);
		secondItem.setText("Variables");
		Composite composite = new Composite(parent, SWT.NONE);
		secondItem.setControl(composite);

		// Add layout on composite
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		new Label(composite, SWT.NONE).setText("Input parameters");

		inputParameters = new VariableViewer(composite,
				new VariableDataStructure(pState.getVariable()),true);

		Button add = new Button(composite, SWT.PUSH | SWT.CENTER);
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

		Button delete = new Button(composite, SWT.PUSH | SWT.CENTER);
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
	}

	/**
	 * Initialize the content of the widgets
	 */
	protected void loadData() {
		//Nothing
	}

	/**
	 * Save the values before the widgets are disposed
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		data = new HashMap<String,Object>();
		try {
			data.put(ACTION_VARIABLE, inputParameters.getData());
			super.okPressed();
		} catch (Exception e) {
			WorkflowPlugin.log("Required fields", IStatus.WARNING);
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
	public Map<String,Object> getData() {
		return data;
	}

}
