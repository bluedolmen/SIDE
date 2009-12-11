/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.bluexml.side.Requirements.modeler.wizards;

import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


public class ImportAnnotationWizardPage extends WizardPage {

	public Text host;
	public Text user;
	public Text password;
	public Text database;
	protected FileFieldEditor editor;
	
	public ImportAnnotationWizardPage(String pageName) {
		super(pageName, pageName, null);
		setDescription("Import annotations from database."); //NON-NLS-1
	}

	public void createControl(Composite parent) {
		Composite selectionArea = new Composite(parent, SWT.NONE);
		GridData selectionData = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.FILL_HORIZONTAL);
		selectionArea.setLayoutData(selectionData);

		GridLayout selectionLayout = new GridLayout();
		selectionLayout.numColumns = 2;
		selectionLayout.makeColumnsEqualWidth = false;
		selectionLayout.marginWidth = 0;
		selectionLayout.marginHeight = 0;
		selectionArea.setLayout(selectionLayout);
		
		//Mysql host
		Label lbl = new Label(selectionArea, SWT.NONE);
		lbl.setText("Mysql host : ");
		
		host = new Text(selectionArea,SWT.FILL);
		host.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));
		host.setText("localhost");
		
		//User
		lbl = new Label(selectionArea, SWT.NONE);
		lbl.setText("Username : ");
		
		user = new Text(selectionArea,SWT.FILL);
		user.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));
		user.setText("root");
		
		//Password
		lbl = new Label(selectionArea, SWT.NONE);
		lbl.setText("Password : ");
		
		password = new Text(selectionArea,SWT.FILL);
		password.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));
		
		//Database
		lbl = new Label(selectionArea, SWT.NONE);
		lbl.setText("Database : ");
		
		database = new Text(selectionArea,SWT.FILL);
		database.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));
		database.setText("reqs_prototype");
		
		setControl(host);
	}
}
