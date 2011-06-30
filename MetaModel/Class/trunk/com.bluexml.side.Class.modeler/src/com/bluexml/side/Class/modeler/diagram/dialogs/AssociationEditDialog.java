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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.topcased.modeler.ModelerColorConstants;

import com.bluexml.side.Class.modeler.diagram.helper.MetaInfoHelper;
import com.bluexml.side.Class.modeler.diagram.utils.AssociationHelper;
import com.bluexml.side.Class.modeler.diagram.utils.metainfo.OblAssociationMetaInfo;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.AssociationType;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.MetaInfoGroup;

/**
 * Updating association parameters
 * 
 */
public class AssociationEditDialog extends Dialog implements IDialogConstants {
	private static final int MIN_DIALOG_HEIGHT = 300;

	private static final int MIN_DIALOG_WIDTH = 200;

	private Text documentation;
	private String associationDocumentation;

	/** Current edited Association */
	private Association association;

	/** Properties of the Association */
	private String associationName;

	private String associationTitle;

	private String associationDescription;

	private AssociationType associationKind;

	private boolean firstEndIsNavigable;

	private String firstEndLowerBound;

	private String firstEndUpperBound;

	private boolean secondEndIsNavigable;

	private String secondEndLowerBound;

	private String secondEndUpperBound;

	// SWT Objects
	private Composite dialogComposite;

	private Text associationNameTxt;

	private Text associationTitleTxt;

	private Text associationDescriptionTxt;

	private Text roleTargetNameTxt;

	private Text roleSourceNameTxt;

	private Text roleTargetNameTxtTitle;

	private Text roleSourceNameTxtTitle;

	private Button firstEndIsNavigableBt;

	private Text firstEndLowerBoundTxt;

	private Text firstEndUpperBoundTxt;

	private Label firstEndValidationLbl;

	private Label firstEndClassLinked;

	private Button secondEndIsNavigableBt;

	private Text secondEndLowerBoundTxt;

	private Text secondEndUpperBoundTxt;

	private Label secondEndValidationLbl;

	private Label secondEndClassLinked;

	private Button assoTypeDirecBt;

	private Button assoTypeCompositeBt;

	private Button assoTypeAggregateBt;

	private String firstEndClass;

	private String secondEndClass;

	private Map<String, Object> drawConstraints = new HashMap<String, Object>();

	private ConstraintsDataStructure dataConstraints;

	private String associationRoleSrc;

	private String associationRoleTarget;

	private String associationRoleSrcTitle;

	private String associationRoleTargetTitle;

	private ModifyListener firstEndValidationListener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			if (getButton(IDialogConstants.OK_ID) != null) {
				getButton(IDialogConstants.OK_ID).setEnabled(validateFirstEndGroup());
			}
		}
	};

	private ModifyListener secondEndValidationListener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			if (getButton(IDialogConstants.OK_ID) != null) {
				getButton(IDialogConstants.OK_ID).setEnabled(validateSecondEndGroup());
			}
		}
	};

	/**
	 * Create the dialog for a given association
	 * 
	 * @param assoc
	 *            the association to edit
	 * @param parentShell
	 *            the owning shell
	 */
	public AssociationEditDialog(Association assoc, Shell parentShell) {
		super(parentShell);

		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);

		this.association = assoc;
		fillFields();
	}

	private void fillFields() {
		associationName = association.getName();
		if (associationName == null)
			associationName = new String();

		associationTitle = association.getTitle();
		if (associationTitle == null)
			associationTitle = new String();

		associationDescription = association.getDescription();
		if (associationDescription == null)
			associationDescription = new String();

		associationDocumentation = association.getDocumentation();
		if (associationDocumentation == null)
			associationDocumentation = new String();

		associationKind = association.getAssociationType();
		associationRoleSrc = association.getFirstEnd().getName();
		associationRoleTarget = association.getSecondEnd().getName();

		associationRoleSrcTitle = association.getFirstEnd().getTitle();
		associationRoleTargetTitle = association.getSecondEnd().getTitle();

		firstEndIsNavigable = association.getFirstEnd().isNavigable();
		firstEndLowerBound = association.getFirstEnd().getCardMin();
		firstEndUpperBound = association.getFirstEnd().getCardMax();
		if (firstEndLowerBound == null)
			firstEndLowerBound = "0";
		if (firstEndUpperBound == null)
			firstEndUpperBound = "0";
		if (firstEndUpperBound.equals("-1"))
			firstEndUpperBound = "*";
		if (association.getFirstEnd().getLinkedClass() instanceof Clazz)
			firstEndClass = ((Clazz) association.getFirstEnd().getLinkedClass()).getName();
		if (association.getFirstEnd().getLinkedClass() instanceof Aspect)
			firstEndClass = ((Aspect) association.getFirstEnd().getLinkedClass()).getName();

		secondEndIsNavigable = association.getSecondEnd().isNavigable();
		secondEndLowerBound = association.getSecondEnd().getCardMin();
		secondEndUpperBound = association.getSecondEnd().getCardMax();
		if (secondEndLowerBound == null)
			secondEndLowerBound = "0";
		if (secondEndUpperBound == null)
			secondEndUpperBound = "0";
		if (secondEndUpperBound.equals("-1"))
			secondEndUpperBound = "*";
		if (association.getSecondEnd().getLinkedClass() instanceof Clazz)
			secondEndClass = ((Clazz) association.getSecondEnd().getLinkedClass()).getName();
		if (association.getSecondEnd().getLinkedClass() instanceof Aspect)
			secondEndClass = ((Aspect) association.getSecondEnd().getLinkedClass()).getName();
	}

	/**
	 * Set the shell title
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		newShell.setText("Association - " + association.getName());
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
		dialogComposite = (Composite) super.createDialogArea(parent);
		createOperationGroup(dialogComposite);

		hookListeners();

		loadData();

		return dialogComposite;
	}

	/**
	 * Creates the group
	 * 
	 * @param parent
	 *            the parent Composite
	 */
	protected void createOperationGroup(Composite parent) {
		TabFolder tabFolder = new TabFolder(parent, SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createGeneralTabItem(tabFolder);
		createFirstEndTabItem(tabFolder);
		createSecondEndTabItem(tabFolder);
		createOptionsTabItem(tabFolder);
		createDocumentationTab(tabFolder);
	}

	private void createOptionsTabItem(TabFolder parent) {
		// Create tab item and add it composite that fills it
		TabItem generalItem = new TabItem((TabFolder) parent, SWT.NONE);
		generalItem.setText("Options");
		Composite composite = new Composite(parent, SWT.NONE);
		generalItem.setControl(composite);

		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Collection<Object> c = (new OblAssociationMetaInfo()).getAllMetaInfo();

		for (Object obj : c) {
			if (obj instanceof MetaInfo)
				MetaInfoHelper.drawConstraint(composite, (MetaInfo) obj, drawConstraints, association);
			else if (obj instanceof MetaInfoGroup)
				MetaInfoHelper.drawConstraintGroup(composite, (MetaInfoGroup) obj, drawConstraints, association);
		}
	}

	/**
	 * Create the Tab for the first end of the association
	 * 
	 * @param parent
	 *            the owning Tab folder
	 */
	private void createFirstEndTabItem(TabFolder parent) {
		Composite composite = createCompositeTab(parent, "Association first end");

		Label lbl = new Label(composite, SWT.NONE);
		lbl.setText("Class linked :");

		firstEndClassLinked = new Label(composite, SWT.NONE);

		Label nameLbl = new Label(composite, SWT.NONE);
		nameLbl.setText("Role :");
		roleSourceNameTxt = new Text(composite, SWT.BORDER);
		roleSourceNameTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label titleLbl = new Label(composite, SWT.NONE);
		titleLbl.setText("Title :");
		roleSourceNameTxtTitle = new Text(composite, SWT.BORDER);
		roleSourceNameTxtTitle.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Group emptyGroup = createEmptyTab(composite);

		firstEndIsNavigableBt = createNavigableTab(emptyGroup);

		Group cardinalityGroup = createCardinalityTab(composite);

		firstEndLowerBoundTxt = createBoundTab(cardinalityGroup, "Lower bound");
		firstEndUpperBoundTxt = createBoundTab(cardinalityGroup, "Upper bound");

		firstEndValidationLbl = createValidationMessage(composite);
	}

	/**
	 * Create the Tab for the second end of the association
	 * 
	 * @param parent
	 *            the owning Tab folder
	 */
	private void createSecondEndTabItem(TabFolder parent) {
		Composite composite = createCompositeTab(parent, "Association second end");

		Label lbl = new Label(composite, SWT.NONE);
		lbl.setText("Class linked :");
		secondEndClassLinked = new Label(composite, SWT.NONE);

		Label nameLbl = new Label(composite, SWT.NONE);
		nameLbl.setText("Role :");
		roleTargetNameTxt = new Text(composite, SWT.BORDER);
		roleTargetNameTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label nameLblTitle = new Label(composite, SWT.NONE);
		nameLblTitle.setText("Title :");
		roleTargetNameTxtTitle = new Text(composite, SWT.BORDER);
		roleTargetNameTxtTitle.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Group emptyGroup = createEmptyTab(composite);

		secondEndIsNavigableBt = createNavigableTab(emptyGroup);

		Group cardinalityGroup = createCardinalityTab(composite);

		secondEndLowerBoundTxt = createBoundTab(cardinalityGroup, "Lower bound");
		secondEndUpperBoundTxt = createBoundTab(cardinalityGroup, "Upper bound");

		secondEndValidationLbl = createValidationMessage(composite);
	}

	/**
	 * Create the tab item that contains main data
	 * 
	 * @param parent
	 *            the tab folder parent
	 */
	private void createGeneralTabItem(TabFolder parent) {
		// Create tab item and add it composite that fills it
		TabItem generalItem = new TabItem(parent, SWT.NONE);
		generalItem.setText("General");
		Composite composite = new Composite(parent, SWT.NONE);
		generalItem.setControl(composite);

		// Add layout on composite
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		// Add widgets
		Label nameLbl = new Label(composite, SWT.NONE);
		nameLbl.setText("Association name");
		associationNameTxt = new Text(composite, SWT.BORDER);
		associationNameTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label titleLbl = new Label(composite, SWT.NONE);
		titleLbl.setText("Title");
		associationTitleTxt = new Text(composite, SWT.BORDER);
		associationTitleTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label descriptionLbl = new Label(composite, SWT.NONE);
		descriptionLbl.setText("Description");
		associationDescriptionTxt = new Text(composite, SWT.BORDER);
		associationDescriptionTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Group typeGroup = createGroupTab(composite, "Association Type");

		assoTypeDirecBt = createItemTab(typeGroup, "Direct");
		assoTypeCompositeBt = createItemTab(typeGroup, "Composition");
		assoTypeAggregateBt = createItemTab(typeGroup, "Aggregation");
	}

	/**
	 * Create a composite tab
	 * 
	 * @param parent
	 *            the parent tab folder
	 * @param tabName
	 *            the name of the table
	 * @return the created composite Tab
	 */
	private Composite createCompositeTab(TabFolder parent, String tabName) {
		// Create tab item and add it composite that fills it
		TabItem item = new TabItem(parent, SWT.NONE);
		item.setText(tabName);
		Composite composite = new Composite(parent, SWT.NONE);
		item.setControl(composite);

		// Add layout on composite
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		return composite;
	}

	/**
	 * Create a group for the Property attributes
	 * 
	 * @param composite
	 *            the parent composite
	 * @return the group for the Property attributes
	 */
	private Group createEmptyTab(Composite composite) {
		Group emptyGroup = new Group(composite, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		emptyGroup.setLayout(new GridLayout(2, false));
		emptyGroup.setLayoutData(gd);
		return emptyGroup;
	}

	/**
	 * Create the navigable button
	 * 
	 * @param composite
	 *            the parent composite
	 * @return the created button
	 */
	private Button createNavigableTab(Composite composite) {
		Button isNavigable = new Button(composite, SWT.CHECK);
		isNavigable.setText("Is navigable");
		GridData isNavGridData = new GridData();
		isNavGridData.horizontalSpan = 2;
		isNavigable.setLayoutData(isNavGridData);
		return isNavigable;
	}

	/**
	 * Create a Group with a given label
	 * 
	 * @param composite
	 *            the parent composite
	 * @param label
	 *            the text of the group
	 * @return the created group
	 */
	private Group createGroupTab(Composite composite, String label) {
		Group group = new Group(composite, SWT.NONE);
		group.setText(label);
		group.setLayout(new GridLayout(2, false));
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		return group;
	}

	/**
	 * Create a RadioButton with a text in the given Group
	 * 
	 * @param theGroup
	 *            the owning group for the item
	 * @param label
	 *            the label of the button
	 * @return the created button
	 */
	private Button createItemTab(Group theGroup, String label) {
		Button itemBt = new Button(theGroup, SWT.RADIO);
		new Label(theGroup, SWT.NONE).setText(label);
		return itemBt;
	}

	/**
	 * Create a group for the cardinality selection
	 * 
	 * @param composite
	 *            the parent composite
	 * @return the group for the cardinality selection
	 */
	private Group createCardinalityTab(Composite composite) {
		Group cardinalityGroup = new Group(composite, SWT.NONE);
		cardinalityGroup.setText("Cardinality");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cardinalityGroup.setLayout(new GridLayout(2, false));
		cardinalityGroup.setLayoutData(gd);
		return cardinalityGroup;
	}

	/**
	 * Create the Text field for the cardinality
	 * 
	 * @param cardinalityGroup
	 *            the ownng group of cardinality selection
	 * @param label
	 *            the name of the button
	 * @return the text fiel
	 */
	private Text createBoundTab(Group cardinalityGroup, String label) {
		new Label(cardinalityGroup, SWT.NONE).setText(label);
		Text bound = new Text(cardinalityGroup, SWT.BORDER);
		GridData layoutData = new GridData();
		layoutData.widthHint = 20;
		bound.setLayoutData(layoutData);
		return bound;
	}

	/**
	 * Create a Label that will display error and warning messages of the
	 * validation step
	 * 
	 * @param composite
	 *            the parent Composite
	 * @return the created Label
	 */
	private Label createValidationMessage(Composite composite) {
		Label validationLbl = new Label(composite, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		validationLbl.setLayoutData(gd);
		validationLbl.setForeground(ModelerColorConstants.COLOR_ERROR);
		return validationLbl;
	}

	/**
	 * Add the listeners to the Buttons and the Text widgets
	 */
	private void hookListeners() {
		firstEndLowerBoundTxt.addModifyListener(firstEndValidationListener);
		firstEndUpperBoundTxt.addModifyListener(firstEndValidationListener);

		secondEndLowerBoundTxt.addModifyListener(secondEndValidationListener);
		secondEndUpperBoundTxt.addModifyListener(secondEndValidationListener);
	}

	/**
	 * Check if the informations contained in the Group are valid
	 * 
	 * @return true if it is OK
	 */
	protected boolean validateFirstEndGroup() {
		try {
			int lowerBound = Integer.parseInt(firstEndLowerBoundTxt.getText());
			if (lowerBound < 0) {
				firstEndValidationLbl.setText("lowerBound should be greater or equal to 0");
				return false;
			}
		} catch (NumberFormatException e) {
			firstEndValidationLbl.setText("lowerBound should be a valid integer value");
			return false;
		}

		try {
			if (!"*".equals(firstEndUpperBoundTxt.getText())) {
				int upperBound = Integer.parseInt(firstEndUpperBoundTxt.getText());
				int lowerBound = Integer.parseInt(firstEndLowerBoundTxt.getText());
				if (upperBound < 1 || upperBound < lowerBound) {
					firstEndValidationLbl.setText("upperBound can not be lower than lowerBound.");
					return false;
				}
			}
		} catch (NumberFormatException e) {
			firstEndValidationLbl.setText("upperBound should be a valid integer value");
			return false;
		}

		firstEndValidationLbl.setText("");
		return true;
	}

	/**
	 * Check if the informations contained in the Group are valid
	 * 
	 * @return true if it is OK
	 */
	protected boolean validateSecondEndGroup() {
		try {
			int lowerBound = Integer.parseInt(secondEndLowerBoundTxt.getText());
			if (lowerBound < 0) {
				secondEndValidationLbl.setText("lowerBound should be greater or equal to 0");
				return false;
			}
		} catch (NumberFormatException e) {
			secondEndValidationLbl.setText("lowerBound should be a valid integer value");
			return false;
		}

		try {
			if (!"*".equals(secondEndUpperBoundTxt.getText())) {
				int upperBound = Integer.parseInt(secondEndUpperBoundTxt.getText());
				int lowerBound = Integer.parseInt(secondEndLowerBoundTxt.getText());
				if (upperBound < 1 || upperBound < lowerBound) {
					secondEndValidationLbl.setText("upperBound can not be lower than lowerBound.");
					return false;
				}
			}
		} catch (NumberFormatException e) {
			secondEndValidationLbl.setText("upperBound should be a valid integer value");
			return false;
		}

		secondEndValidationLbl.setText("");
		return true;
	}

	/**
	 * Fill the fields of the association current datas
	 */
	private void loadData() {
		// Main data
		associationNameTxt.setText(associationName);
		associationTitleTxt.setText(associationTitle);
		associationDescriptionTxt.setText(associationDescription);
		// Doc
		if (association.getDocumentation() != null)
			documentation.setText(association.getDocumentation());
		
		assoTypeDirecBt.setSelection(associationKind.equals(AssociationType.DIRECT));
		assoTypeCompositeBt.setSelection(associationKind.equals(AssociationType.COMPOSITION));
		assoTypeAggregateBt.setSelection(associationKind.equals(AssociationType.AGGREGATION));
		if (associationRoleSrc != null)
			roleSourceNameTxt.setText(associationRoleSrc);
		if (associationRoleTarget != null)
			roleTargetNameTxt.setText(associationRoleTarget);

		if (associationRoleSrcTitle != null)
			roleSourceNameTxtTitle.setText(associationRoleSrcTitle);
		if (associationRoleTargetTitle != null)
			roleTargetNameTxtTitle.setText(associationRoleTargetTitle);

		// First end data
		firstEndIsNavigableBt.setSelection(firstEndIsNavigable);
		firstEndLowerBoundTxt.setText(firstEndLowerBound);
		firstEndUpperBoundTxt.setText(firstEndUpperBound);
		firstEndClassLinked.setText(firstEndClass);

		// Second end data
		secondEndIsNavigableBt.setSelection(secondEndIsNavigable);
		secondEndLowerBoundTxt.setText(secondEndLowerBound);
		secondEndUpperBoundTxt.setText(secondEndUpperBound);
		secondEndClassLinked.setText(secondEndClass);

		// MetaInfo
		for (Object o : association.getMetainfo()) {
			MetaInfo c = (MetaInfo) o;
			for (Object k : drawConstraints.keySet()) {
				String key = ((MetaInfo) k).getKey();
				if (key.equals(c.getKey())) {
					if ((c.getValueType() != null) || (c.getValue() != null)) {
						Object comp = drawConstraints.get(k);
						if (comp instanceof Text) {
							Text t = (Text) comp;
							t.setText(c.getValue().toString());
						} else if (comp instanceof Combo) {
							Combo soc = (Combo) comp;
							soc.select(soc.indexOf("Yes"));
						}

					}
				}
			}
		}
		
	}

	/**
	 * Save the datas before the widgets are disposed
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		// Prepare general data
		associationName = associationNameTxt.getText();
		associationTitle = associationTitleTxt.getText();
		associationDescription = associationDescriptionTxt.getText();
		associationDocumentation = documentation.getText();

		associationRoleSrc = roleSourceNameTxt.getText();
		associationRoleTarget = roleTargetNameTxt.getText();
		associationRoleSrcTitle = roleSourceNameTxtTitle.getText();
		associationRoleTargetTitle = roleTargetNameTxtTitle.getText();

		// Prepare first end data
		firstEndIsNavigable = firstEndIsNavigableBt.getSelection();
		firstEndLowerBound = firstEndLowerBoundTxt.getText();
		String upperBound = firstEndUpperBoundTxt.getText();
		if ("*".equals(upperBound)) {
			upperBound = "-1";
		}
		firstEndUpperBound = upperBound;

		// Prepare second end data
		secondEndIsNavigable = secondEndIsNavigableBt.getSelection();
		secondEndLowerBound = secondEndLowerBoundTxt.getText();
		upperBound = secondEndUpperBoundTxt.getText();
		if ("*".equals(upperBound)) {
			upperBound = "-1";
		}
		secondEndUpperBound = upperBound;

		if (assoTypeDirecBt.getSelection())
			associationKind = AssociationType.DIRECT;
		else if (assoTypeAggregateBt.getSelection())
			associationKind = AssociationType.AGGREGATION;
		else if (assoTypeCompositeBt.getSelection())
			associationKind = AssociationType.COMPOSITION;

		dataConstraints = MetaInfoHelper.getDataStructure(drawConstraints);

		super.okPressed();
	}

	/**
	 * Return all the informations concerning an Association
	 * 
	 * @return a Map
	 */
	public Map<String, Object> getAssociationData() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put(AssociationHelper.ASSOCIATION_NAME, associationName);
		resultMap.put(AssociationHelper.ASSOCIATION_TITLE, associationTitle);
		resultMap.put(AssociationHelper.ASSOCIATION_DESCRIPTION, associationDescription);
		resultMap.put(AssociationHelper.ASSOCIATION_DOCUMENTATION, associationDocumentation);

		resultMap.put(AssociationHelper.ASSOCIATION_ROLE_SRC, associationRoleSrc);
		resultMap.put(AssociationHelper.ASSOCIATION_ROLE_TARGET, associationRoleTarget);

		resultMap.put(AssociationHelper.ASSOCIATION_ROLE_SRC_TITLE, associationRoleSrcTitle);
		resultMap.put(AssociationHelper.ASSOCIATION_ROLE_TARGET_TITLE, associationRoleTargetTitle);

		resultMap.put(AssociationHelper.ASSOCIATION_TYPE, associationKind);

		resultMap.put(AssociationHelper.FIRST_END_IS_NAVIGABLE, new Boolean(firstEndIsNavigable));
		resultMap.put(AssociationHelper.FIRST_END_LOWER_BOUND, firstEndLowerBound);
		resultMap.put(AssociationHelper.FIRST_END_UPPER_BOUND, firstEndUpperBound);

		resultMap.put(AssociationHelper.SECOND_END_IS_NAVIGABLE, new Boolean(secondEndIsNavigable));
		resultMap.put(AssociationHelper.SECOND_END_LOWER_BOUND, secondEndLowerBound);
		resultMap.put(AssociationHelper.SECOND_END_UPPER_BOUND, secondEndUpperBound);

		resultMap.put(AssociationHelper.META_INFO, dataConstraints);

		return resultMap;
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
}
