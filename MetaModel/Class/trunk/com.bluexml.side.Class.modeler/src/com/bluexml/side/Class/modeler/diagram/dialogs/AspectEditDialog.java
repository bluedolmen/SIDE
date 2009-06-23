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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.bluexml.side.Class.modeler.ClazzPlugin;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.common.Comment;
import com.bluexml.side.common.Stereotype;


public class AspectEditDialog extends Dialog implements IDialogConstants {
	/** The ID of the property name */
	public static final String ASPECT_NAME = "aspect name";
	public static final String ASPECT_TITLE = "aspect title";
	public static final String ASPECT_DESCRIPTION = "aspect description";
	public static final String ASPECT_LAYOUT = "aspect layout";

	private static final int MIN_DIALOG_WIDTH = 400;

	private static final int MIN_DIALOG_HEIGHT = 300;

	/** Current edited property */
	private Aspect aspect;

	// SWT Objects
	private Text aspectNameTxt;
	
	private Text aspectTitleTxt;
	
	private Text aspectDescriptionTxt;

	private Map data;
	
	private Control layout;

	/**
	 * Create the dialog for a given Attribute
	 * 
	 * @param prop
	 *            the Attribute to edit
	 * @param parentShell
	 *            the parent shell
	 */
	public AspectEditDialog(Aspect aspect, Shell parentShell) {
		super(parentShell);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		this.aspect = aspect;
	}

	/**
	 * Set the title of the new shell
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		newShell.setText("Aspect " + aspect.getName());
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
		createLayoutTab(tabFolder);
	}
	
	private void createGeneralTab(Composite parent) {
		// Create tab item and add it composite that fills it
		TabItem generalItem = new TabItem((TabFolder) parent, SWT.NONE);
		generalItem.setText("General");
		Composite composite = new Composite(parent, SWT.NONE);
		generalItem.setControl(composite);

		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label lbl = new Label(composite, SWT.NONE);
		lbl.setText("Name : ");
		aspectNameTxt = new Text(composite, SWT.BORDER);
		aspectNameTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		lbl = new Label(composite, SWT.NONE);
		lbl.setText("Title : ");
		aspectTitleTxt = new Text(composite, SWT.BORDER);
		aspectTitleTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		lbl = new Label(composite, SWT.NONE);
		lbl.setText("Description : ");
		aspectDescriptionTxt = new Text(composite, SWT.BORDER);
		aspectDescriptionTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}
	
	private void createLayoutTab(TabFolder parent) {
		// Create tab item and add it composite that fills it
		TabItem layoutItem = new TabItem((TabFolder) parent, SWT.NONE);
		layoutItem.setText("Layout");
		Composite composite = new Composite(parent, SWT.NONE);
		layoutItem.setControl(composite);

		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		layout = new Text(composite, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
		layout.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	/**
	 * Initialize the content of the widgets
	 */
	private void loadData() {
		// Name
		aspectNameTxt.setText(aspect.getName());
		if (aspect.getTitle() != null)
			aspectTitleTxt.setText(aspect.getTitle());
		if (aspect.getDescription() != null)
			aspectDescriptionTxt.setText(aspect.getDescription());
		
		//Layout
		for (Object o : aspect.getComments()) {
			Comment c = (Comment) o;
			if (isStereotyped(c,"layout")) {
				((Text) layout).setText(c.getValue());
			}
		}
	}
	
	private boolean isStereotyped(Comment c, String stereotype) {
		boolean result = false;
		
		for (Object obj : c.getStereotypes()) {
			if (obj instanceof Stereotype) {
				Stereotype s = (Stereotype) obj;
				if (s.getName().equalsIgnoreCase(stereotype)) {
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * Save the values before the widgets are disposed
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		data = new HashMap();
		try {
			data.put(ASPECT_NAME, aspectNameTxt.getText());
			data.put(ASPECT_TITLE, aspectTitleTxt.getText());
			data.put(ASPECT_DESCRIPTION, aspectDescriptionTxt.getText());
			data.put(ASPECT_LAYOUT, ((Text) layout).getText());
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
