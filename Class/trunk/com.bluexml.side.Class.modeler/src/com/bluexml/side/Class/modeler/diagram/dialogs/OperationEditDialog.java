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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
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
import org.topcased.facilities.widgets.SingleObjectChooser;

import com.bluexml.side.Class.modeler.diagram.helper.MetaInfoHelper;
import com.bluexml.side.Class.modeler.diagram.utils.metainfo.OblOperationMetaInfo;
import com.bluexml.side.common.DataType;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.MetaInfoGroup;
import com.bluexml.side.common.Operation;
import com.bluexml.side.common.Visibility;


/**
 * Updating operation parameters
 */
public class OperationEditDialog extends Dialog implements IDialogConstants {
	/** The ID of the operation name */
	public static final String OPERATION_NAME = "operation name";

	/** The ID of the operation visibility */
	public static final String OPERATION_VISIBILITY = "operation visibility";
	
	/** The ID of the operation visibility */
	public static final String OPERATION_STATIC = "operation static";

	/** The ID of the operation return type */
	public static final String OPERATION_RETURN_TYPE = "operation return type";

	/** The ID of the operation inputs */
	public static final String OPERATION_INPUTS = "operation inputs";

	/** The ID of the operation constraints */
	public static final String OPERATION_CONSTRAINTS = "operation constraints";

	public static final String OPERATION_DOCUMENTATION = "operation documentation";
	
	public static final String OPERATION_BODY = "operation body";

	private OperationParameterViewer inputParameters;

	/** Current edited operation */
	private Operation operation;

	// SWT Objects
	private Combo returnTypeChooser;

	private SingleObjectChooser visibilityChooser;

	private Text operationNameTxt;
	
	private Button checkIsStatic;

	private String[] typeNames;

	private String[] types;

	private Map data;

	private Map drawConstraints = new HashMap();

	private Text documentation;
	
	private Text body;

	/**
	 * Create the dialog for a given operation
	 * 
	 * @param op
	 *            the operation to edit
	 * @param parentShell
	 *            the owning shell
	 */
	public OperationEditDialog(Operation op, Shell parentShell) {
		super(parentShell);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		operation = op;
		initializeTypes();
	}

	/**
	 * Set the title of the shell
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		newShell.setText("Operation " + operation.getName());
		newShell.setMinimumSize(100, 200);

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
		dialogLayoutData.widthHint = 100;
		dialogLayoutData.heightHint = 200;

		createOperationGroup(dialogComposite);
		loadData();

		return dialogComposite;
	}

	/**
	 * Initialize attributes about types that model currently contains
	 * 
	 */
	private void initializeTypes() {
		types = new String[DataType.VALUES.size()];
		int i = 0;
		for (Object o : DataType.VALUES) {
			types[i] = o.toString();
			i++;
		}
		typeNames = types;
	}

	/**
	 * Creates the group
	 * 
	 * @param parent
	 *            the parent Composite
	 */
	private void createOperationGroup(Composite parent) {
		TabFolder tabFolder = new TabFolder(parent, SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createOperationTabItem(tabFolder);
		createParametersTabItem(tabFolder);
		createConstraintTab(tabFolder);
		createBodyTab(tabFolder);
		createDocumentationTab(tabFolder);
	}

	private void createDocumentationTab(TabFolder parent) {
		// Create tab item and add it composite that fills it
		TabItem viewItem = new TabItem((TabFolder) parent, SWT.NONE);
		viewItem.setText("Documentation");
		Composite composite = new Composite(parent, SWT.NONE);
		viewItem.setControl(composite);

		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		documentation = new Text(composite, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL
				| SWT.BORDER);
		documentation.setLayoutData(new GridData(GridData.FILL_BOTH));
	}
	
	private void createBodyTab(TabFolder parent) {
		// Create tab item and add it composite that fills it
		TabItem bodyItem = new TabItem((TabFolder) parent, SWT.NONE);
		bodyItem.setText("Body");
		Composite composite = new Composite(parent, SWT.NONE);
		bodyItem.setControl(composite);

		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		body = new Text(composite, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL
				| SWT.BORDER);
		body.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	private void createConstraintTab(TabFolder parent) {
		// Create tab item and add it composite that fills it
		TabItem generalItem = new TabItem((TabFolder) parent, SWT.NONE);
		generalItem.setText("Options");
		Composite composite = new Composite(parent, SWT.NONE);
		generalItem.setControl(composite);

		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Collection c = (new OblOperationMetaInfo()).getAllMetaInfo();

		for (Object obj : c) {
			if (obj instanceof MetaInfo)
				MetaInfoHelper.drawConstraint(composite, (MetaInfo) obj,
						drawConstraints, operation);
			else if (obj instanceof MetaInfoGroup)
				MetaInfoHelper.drawConstraintGroup(composite,
						(MetaInfoGroup) obj, drawConstraints, operation);
		}
	}

	/**
	 * Create the first tab
	 * 
	 * @param parent
	 *            the owning tab folder
	 */
	private void createOperationTabItem(TabFolder parent) {

		// Create tab item and add it composite that fills it
		TabItem firstItem = new TabItem(parent, SWT.NONE);
		firstItem.setText("General Informations");
		Composite composite = new Composite(parent, SWT.NONE);
		firstItem.setControl(composite);

		// Add layout on composite
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label operationNameLbl = new Label(composite, SWT.NONE);
		operationNameLbl.setText("Name : ");
		operationNameTxt = new Text(composite, SWT.BORDER);
		operationNameTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label returnTypenNameLbl = new Label(composite, SWT.NONE);
		returnTypenNameLbl.setText("Return type : ");
		returnTypenNameLbl
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		returnTypeChooser = new Combo(composite, SWT.READ_ONLY);
		returnTypeChooser.setItems(types);
		returnTypeChooser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		returnTypeChooser.setEnabled(true);

		Label lbl = new Label(composite, SWT.NONE);
		lbl.setText("Visibility : ");
		lbl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		visibilityChooser = new SingleObjectChooser(composite, SWT.READ_ONLY);
		visibilityChooser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		visibilityChooser.setEnabled(true);
		
		Label isStatic = new Label(composite, SWT.NONE);
		isStatic.setText("Is static : ");
		checkIsStatic = new Button(composite, SWT.CHECK);
	}

	/**
	 * Create the secong tab
	 * 
	 * @param parent
	 *            the owning tab folder
	 */
	private void createParametersTabItem(TabFolder parent) {
		// Create tab item and add it composite that fills it
		TabItem secondItem = new TabItem(parent, SWT.NONE);
		secondItem.setText("Parameters");
		Composite composite = new Composite(parent, SWT.NONE);
		secondItem.setControl(composite);

		// Add layout on composite
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		new Label(composite, SWT.NONE).setText("Input parameters");

		inputParameters = new OperationParameterViewer(composite,
				new OperationDataStructure(operation), types, typeNames);

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
	 * Fill fields with current operation values
	 */
	private void loadData() {
		// Name
		operationNameTxt.setText(operation.getName());
		for (int i = 0; i < returnTypeChooser.getItemCount(); ++i)
			if (returnTypeChooser.getItem(i).toString().equals(
					operation.getReturnType().toString())) {
				returnTypeChooser.select(i);
				break;
			}

		// MetaInfo
		MetaInfoHelper.loadData(operation, drawConstraints);

		// Visibility
		Collection reachableVisibility = Visibility.VALUES;
		visibilityChooser.setChoices(reachableVisibility.toArray());
		visibilityChooser.setSelection(operation.getVisibility());

		// Documentation
		if (operation.getDocumentation() != null)
			documentation.setText(operation.getDocumentation());
		
		checkIsStatic.setSelection(operation.isStatic());
		
		// Body
		if (operation.getBody() != null)
			body.setText(operation.getBody());
	}

	/**
	 * Save the datas before the widgets are disposed
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		data = new HashMap();
		data.put(OPERATION_NAME, operationNameTxt.getText());
		data.put(OPERATION_RETURN_TYPE, returnTypeChooser.getText());
		data.put(OPERATION_VISIBILITY, visibilityChooser.getSelection());
		data.put(OPERATION_INPUTS, inputParameters.getData());
		ConstraintsDataStructure dataConstraints = MetaInfoHelper
				.getDataStructure(drawConstraints);
		data.put(OPERATION_CONSTRAINTS, dataConstraints);
		data.put(OPERATION_DOCUMENTATION, documentation.getText());
		data.put(OPERATION_BODY, body.getText());
		data.put(OPERATION_STATIC, checkIsStatic.getSelection());
		super.okPressed();
	}

	/**
	 * Return a map containing property data that may changed
	 * 
	 * @return a Map
	 */
	public Map getData() {
		return data;
	}

}
