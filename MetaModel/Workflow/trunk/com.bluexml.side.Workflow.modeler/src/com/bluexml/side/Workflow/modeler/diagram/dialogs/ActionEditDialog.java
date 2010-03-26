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
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.osgi.framework.Bundle;

import com.bluexml.side.Workflow.modeler.WorkflowPlugin;
import com.bluexml.side.workflow.Action;
import com.bluexml.side.workflow.Script;

/**
 * Updating task parameters
 */
public class ActionEditDialog extends Dialog implements IDialogConstants {
	
	public static final String ACTION_SCRIPT = "action script";
	
	public static final String ACTION_JAVA_CLASS = "action java class";

	public static final String ACTION_VARIABLE = "action variables";

	private static final int MIN_DIALOG_WIDTH = 600;

	private static final int MIN_DIALOG_HEIGHT = 300;


	private VariableViewer inputParameters;
	
	/** Current edited property */
	private Action action;

	protected HashMap<String, Object> data;

	private Text scriptTxt;
	
	private Combo javaClass;

	/**
	 * Create the dialog for a given task
	 */
	public ActionEditDialog(Action action, Shell parentShell) {
		super(parentShell);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		this.action = action;
	}

	/**
	 * Set the title of the new shell
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		newShell.setText("Action");
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

		createScriptTab(tabFolder);
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

		Script s = null;
		if (action.getScript().size() > 0)
			s = action.getScript().get(0);
		
		if (s != null)
			inputParameters = new VariableViewer(composite,
				new VariableDataStructure(s.getVariable()), false);
		else
			inputParameters = new VariableViewer(composite,
					new VariableDataStructure(), false);

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

	protected TabItem createScriptTab(TabFolder parent) {
		// Create tab item and add it composite that fills it
		TabItem scriptItem = new TabItem((TabFolder) parent, SWT.NONE);
		scriptItem.setText("Script");
		Composite composite = new Composite(parent, SWT.NONE);
		scriptItem.setControl(composite);

		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		if (inEnterpriseVersion()) {
			javaClass = new Combo(composite, SWT.NULL);
			javaClass.setItems(new String[] { "org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript" });
		}

		scriptTxt = new Text(composite, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL
				| SWT.BORDER);
		scriptTxt.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		return scriptItem;
	}
	
	private boolean inEnterpriseVersion() {
		Bundle b = Platform.getBundle("com.bluexml.side.Integration.eclipse.builder");
		return (b != null);
	}

	/**
	 * Initialize the content of the widgets
	 */
	protected void loadData() {
		Script s = null;
		if (action.getScript().size() > 0)
			s = action.getScript().get(0);
		if (s != null && s.getExpression() != null)
			scriptTxt.setText(s.getExpression());
		if (inEnterpriseVersion()) {
			javaClass.setText("org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript");
			if (action.getJavaClass() != null && action.getJavaClass().length() > 0)
				javaClass.setText(action.getJavaClass().replaceAll("\"", ""));
		}
	}

	/**
	 * Save the values before the widgets are disposed
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		data = new HashMap<String,Object>();
		try {
			data.put(ACTION_SCRIPT, scriptTxt.getText());
			if (inEnterpriseVersion())
				data.put(ACTION_JAVA_CLASS, javaClass.getText());
			else {
				data.put(ACTION_JAVA_CLASS, "org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript");
				if (action.getJavaClass() != null && action.getJavaClass().length() > 0)
					data.put(ACTION_JAVA_CLASS, action.getJavaClass().replaceAll("\"", ""));
			}
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
