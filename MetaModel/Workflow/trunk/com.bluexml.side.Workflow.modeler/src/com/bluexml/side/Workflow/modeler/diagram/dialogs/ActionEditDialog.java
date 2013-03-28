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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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

	public static final String ACTION_SCRIPT_TXT = "action script txt";

	public static final String ACTION_JAVA_CLASS = "action java class";

	public static final String ACTION_VARIABLE = "action variables";

	private static final int MIN_DIALOG_WIDTH = 600;

	private static final int MIN_DIALOG_HEIGHT = 300;

	private VariableViewer inputParameters;

	/** Current edited property */
	private Action action;

	protected HashMap<String, Object> data;

	TabFolder tabFolder;

	TabItem secondItem;

	private Text scriptTxt;

	private Combo javaClass;

	private List<String> defaultsJavaClasses = new ArrayList<String>();

	private Button scriptButton;

	boolean script = true;

	String expression = null;

	String javaClassTxt = null;

	/**
	 * Create the dialog for a given task
	 */
	public ActionEditDialog(Action action, Shell parentShell) {
		super(parentShell);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		this.action = action;

		defaultsJavaClasses.add("org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript");
		defaultsJavaClasses.add("org.jboss.beanshell");

		loadData();
	}

	/**
	 * Set the title of the new shell
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	@Override
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
	@Override
	protected Control createDialogArea(Composite parent) {
		// Dialog
		Composite dialogComposite = (Composite) super.createDialogArea(parent);
		GridData dialogLayoutData = new GridData(GridData.FILL_BOTH);
		dialogLayoutData.widthHint = MIN_DIALOG_WIDTH;
		dialogLayoutData.heightHint = MIN_DIALOG_HEIGHT;
		dialogComposite.setLayoutData(dialogLayoutData);

		createTaskGroup(dialogComposite);

		return dialogComposite;
	}

	/**
	 * Creates the group
	 * 
	 * @param parent
	 *            the parent Composite
	 */
	protected void createTaskGroup(Composite parent) {
		tabFolder = new TabFolder(parent, SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createScriptTab(tabFolder);
		if (script) {
			createAttributesTabItem(tabFolder);
		}
	}

	private void createAttributesTabItem(TabFolder parent) {
		secondItem = new TabItem(parent, SWT.NONE);
		secondItem.setText("Script Variables");
		Composite composite = new Composite(parent, SWT.NONE);
		secondItem.setControl(composite);

		// Add layout on composite
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		new Label(composite, SWT.NONE).setText("Input parameters");

		Script s = null;
		if (action.getScript().size() > 0) {
			s = action.getScript().get(0);
		}
		if (s != null) {
			inputParameters = new VariableViewer(composite, new VariableDataStructure(s.getVariable()), false);
		} else {
			inputParameters = new VariableViewer(composite, new VariableDataStructure(), false);
		}

		Button add = new Button(composite, SWT.PUSH | SWT.CENTER);
		add.setText("Add");

		GridData gd = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END);
		gd.widthHint = 80;
		add.setLayoutData(gd);
		add.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputParameters.addParameter();
			}
		});

		Button delete = new Button(composite, SWT.PUSH | SWT.CENTER);
		delete.setText("Delete");
		gd = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.widthHint = 80;
		delete.setLayoutData(gd);

		delete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputParameters.removeParameter();
			}
		});
	}

	protected TabItem createScriptTab(TabFolder parent) {
		// Create tab item and add it composite that fills it
		final TabItem scriptItem = new TabItem(parent, SWT.NONE);
		scriptItem.setText("General");
		Composite composite = new Composite(parent, SWT.NONE);
		scriptItem.setControl(composite);

		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		if (inEnterpriseVersion()) {
			javaClass = new Combo(composite, SWT.NULL);

			javaClass.setItems(defaultsJavaClasses.toArray(new String[] {}));
			javaClass.setText(javaClassTxt);

		}

		scriptButton = new Button(composite, SWT.CHECK);
		scriptButton.setText("script ?");
		scriptButton.setSelection(script);
		scriptButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (scriptButton.getSelection()) {
					script = true;
					createAttributesTabItem(tabFolder);
				} else {
					script = false;
					if (secondItem != null) {
						secondItem.dispose();
						secondItem = null;
					}
				}

			}

			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		scriptTxt = new Text(composite, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
		scriptTxt.setLayoutData(new GridData(GridData.FILL_BOTH));
		if (StringUtils.trimToNull(expression) != null) {
			scriptTxt.setText(expression);
		}
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

		String expression = action.getExpression();
		if (StringUtils.trimToNull(expression) == null) {
			Script s = null;
			if (action.getScript().size() > 0) {
				s = action.getScript().get(0);
			} else {
				script = false;
			}
			if (s != null && s.getExpression() != null) {
				this.expression = s.getExpression();
			}

		} else {
			this.expression = expression;
			script = false;
		}
		if (inEnterpriseVersion()) {
			javaClassTxt = defaultsJavaClasses.get(0);
			if (action.getJavaClass() != null && action.getJavaClass().length() > 0) {
				javaClassTxt = action.getJavaClass().replaceAll("\"", "");
			}
		}

	}

	/**
	 * Save the values before the widgets are disposed
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	@Override
	protected void okPressed() {
		data = new HashMap<String, Object>();
		try {

			data.put(ACTION_SCRIPT_TXT, scriptTxt.getText());
			if (inEnterpriseVersion()) {
				data.put(ACTION_JAVA_CLASS, javaClass.getText());
			} else {
				data.put(ACTION_JAVA_CLASS, defaultsJavaClasses.get(0));
			}
			data.put(ACTION_SCRIPT, script);
			if (script) {
				data.put(ACTION_VARIABLE, inputParameters.getData());
			}
			super.okPressed();
		} catch (Exception e) {
			WorkflowPlugin.log("Required fields", IStatus.WARNING);
			MessageDialog.openWarning(getShell(), "Required parameters", "Some parameters are not set.\nPlease, fill those fields before validating.");
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
