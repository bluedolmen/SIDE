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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ILabelProvider;
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
import org.eclipse.swt.widgets.Text;
import org.topcased.facilities.widgets.SingleObjectChooser;

import com.bluexml.side.Class.modeler.ClazzPlugin;
import com.bluexml.side.Class.modeler.diagram.helper.MetaInfoHelper;
import com.bluexml.side.Class.modeler.diagram.utils.metainfo.OblAttributeMetaInfo;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.clazz.provider.ClazzItemProviderAdapterFactory;
import com.bluexml.side.common.DataType;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.MetaInfoGroup;

public class AttributeEditDialog extends Dialog implements IDialogConstants {
	/** The ID of the property name */
	public static final String PROPERTY_NAME = "property name";

	/** The ID of the property type */
	public static final String PROPERTY_TYPE = "property type";

	/** The ID of the property type */
	public static final String PROPERTY_CONSTRAINTS = "property constraints";

	/** The ID of the property visibility */
	public static final String PROPERTY_VISIBILITY = "property visibility";

	/** The ID of the property documentation */
	public static final String PROPERTY_DOCUMENTATION = "property documentation";

	/** The ID of the property title */
	public static final String PROPERTY_TITLE = "property title";

	/** The ID of the property unique */
	public static final String PROPERTY_UNIQUE = "property unique";

	/** The ID of the property description */
	public static final String PROPERTY_DESCRIPTION = "property description";

	/** The ID of the property value list */
	public static final String PROPERTY_VALUELIST = "property value list";

	private static final int MIN_DIALOG_WIDTH = 500;

	private static final int MIN_DIALOG_HEIGHT = 600;

	/** Current edited property */
	private Attribute Attribute;

	// SWT Objects
	private Text propertyNameTxt;

	private Text propertyTitleTxt;

	private Text propertyDescriptionTxt;

	private Control documentation;

	private SingleObjectChooser typeChooser;

	private SingleObjectChooser valueList;

	private SingleObjectChooser visibilityChooser;

	private Map<String, Object> data;

	private ConstraintsDataStructure dataConstraints;

	private Map<Object, Object> drawConstraints = new HashMap<Object, Object>();

	private Button unique;

	/**
	 * Create the dialog for a given Attribute
	 * 
	 * @param prop
	 *            the Attribute to edit
	 * @param parentShell
	 *            the parent shell
	 */
	public AttributeEditDialog(Attribute prop, Shell parentShell) {
		super(parentShell);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		Attribute = prop;
	}

	/**
	 * Set the title of the new shell
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		newShell.setText("Attribute " + Attribute.getName());
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

		createPropertyGroup(dialogComposite);

		loadData();

		return dialogComposite;
	}

	/**
	 * Creates the group
	 * 
	 * @param parent
	 *            the parent Composite
	 */
	private void createPropertyGroup(Composite parent) {
		TabFolder tabFolder = new TabFolder(parent, SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createGeneralTab(tabFolder);
		createConstraintTab(tabFolder);
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

		documentation = new Text(composite, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
		documentation.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	private void createConstraintTab(TabFolder parent) {
		// Create tab item and add it composite that fills it
		TabItem generalItem = new TabItem((TabFolder) parent, SWT.NONE);
		generalItem.setText("Options");
		Composite composite = new Composite(parent, SWT.NONE);
		generalItem.setControl(composite);

		composite.setLayout(new GridLayout(4, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Collection<?> c = (new OblAttributeMetaInfo()).getMetaInfo(Attribute.getTyp());

		Composite panelMetaInfo = new Composite(composite, SWT.NONE);
		panelMetaInfo.setLayout(new GridLayout(2, false));
		panelMetaInfo.setLayoutData(new GridData(GridData.FILL_BOTH));

		Composite panelMetaInfoGroup = new Composite(composite, SWT.NONE);
		panelMetaInfoGroup.setLayout(new GridLayout(2, false));
		panelMetaInfoGroup.setLayoutData(new GridData(GridData.FILL_BOTH));

		for (Object obj : c) {
			if (obj instanceof MetaInfo)
				MetaInfoHelper.drawConstraint(panelMetaInfo, (MetaInfo) obj, drawConstraints, Attribute);
		}

		for (Object obj : c) {
			if (obj instanceof MetaInfoGroup)
				MetaInfoHelper.drawConstraintGroup(panelMetaInfoGroup, (MetaInfoGroup) obj, drawConstraints, Attribute);
		}
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
		propertyNameTxt = new Text(composite, SWT.BORDER);
		propertyNameTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label typeLbl = new Label(composite, SWT.NONE);
		typeLbl.setText("Type : ");

		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(new ClazzItemProviderAdapterFactory());

		typeChooser = new SingleObjectChooser(composite, SWT.NONE);
		typeChooser.setLabelProvider(labelProvider);
		typeChooser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

//		Label lbl = new Label(composite, SWT.NONE);
//		lbl.setText("Visibility : ");

//		ILabelProvider labelProviderV = new AdapterFactoryLabelProvider(new ClazzItemProviderAdapterFactory());

//		visibilityChooser = new SingleObjectChooser(composite, SWT.NONE);
//		visibilityChooser.setLabelProvider(labelProviderV);
//		visibilityChooser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label propertyTitleLbl = new Label(composite, SWT.NONE);
		propertyTitleLbl.setText("Title : ");
		propertyTitleTxt = new Text(composite, SWT.BORDER);
		propertyTitleTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label propertyDescriptionLbl = new Label(composite, SWT.NONE);
		propertyDescriptionLbl.setText("Short description : ");
		propertyDescriptionTxt = new Text(composite, SWT.BORDER);
		propertyDescriptionTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label valueListLbl = new Label(composite, SWT.NONE);
		valueListLbl.setText("Value List : ");

		labelProvider = new AdapterFactoryLabelProvider(new ClazzItemProviderAdapterFactory());

		valueList = new SingleObjectChooser(composite, SWT.NONE);
		valueList.setLabelProvider(labelProvider);
		valueList.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label propertyIdLbl = new Label(composite, SWT.NONE);
		propertyIdLbl.setText("Unique : ");
		unique = new Button(composite, SWT.CHECK);
		unique.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	/**
	 * Initialize the content of the widgets
	 */
	@SuppressWarnings("unchecked")
	private void loadData() {
		// Name
		propertyNameTxt.setText(Attribute.getName());

		// Types
		Collection<DataType> reachableTypes = DataType.VALUES;
		typeChooser.setChoices(reachableTypes.toArray());
		typeChooser.setSelection(Attribute.getTyp());

		// Value list
		Collection<EObject> reachableEnumeration = ItemPropertyDescriptor.getReachableObjectsOfType(Attribute, ClazzPackage.eINSTANCE.getEnumeration());
		Object[] enumArray = reachableEnumeration.toArray();
		Object[] enumArray2 = new Object[enumArray.length + 1];
		for (int i = 0; i < enumArray.length; ++i) {
			enumArray2[i] = enumArray[i];
		}
		enumArray2[enumArray.length] = new String();
		valueList.setChoices(enumArray2);
		if (Attribute.getValueList() == null)
			valueList.setSelection("");
		else
			valueList.setSelection(Attribute.getValueList());

		// Unicity
		unique.setSelection(Attribute.isUnique());

		// Visibility
		// bug http://bugs.bluexml.net/show_bug.cgi?id=1281
		// Collection<Visibility> reachableVisibility = Visibility.VALUES;
		// visibilityChooser.setChoices(reachableVisibility.toArray());
		// visibilityChooser.setSelection(Attribute.getVisibility());

		// MetaInfo
		MetaInfoHelper.loadData(Attribute, drawConstraints);

		// Documentation
		if (Attribute.getDocumentation() != null)
			((Text) documentation).setText(Attribute.getDocumentation());

		// Title
		if (Attribute.getTitle() != null)
			((Text) propertyTitleTxt).setText(Attribute.getTitle());

		// Description
		if (Attribute.getDescription() != null)
			((Text) propertyDescriptionTxt).setText(Attribute.getDescription());
	}

	/**
	 * Save the values before the widgets are disposed
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		data = new HashMap<String, Object>();
		try {
			data.put(PROPERTY_NAME, propertyNameTxt.getText());
			data.put(PROPERTY_VALUELIST, valueList.getSelection());
			data.put(PROPERTY_TYPE, typeChooser.getSelection());
			// data.put(PROPERTY_VISIBILITY, visibilityChooser.getSelection());
			dataConstraints = MetaInfoHelper.getDataStructure(drawConstraints);
			data.put(PROPERTY_CONSTRAINTS, dataConstraints);
			data.put(PROPERTY_DOCUMENTATION, ((Text) documentation).getText());
			data.put(PROPERTY_TITLE, ((Text) propertyTitleTxt).getText());
			data.put(PROPERTY_DESCRIPTION, ((Text) propertyDescriptionTxt).getText());
			data.put(PROPERTY_UNIQUE, unique.getSelection());
			super.okPressed();
		} catch (Exception e) {
			ClazzPlugin.log("Required fields", IStatus.WARNING);
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
