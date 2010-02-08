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
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.bluexml.side.Class.modeler.ClazzPlugin;
import com.bluexml.side.Class.modeler.diagram.helper.MetaInfoHelper;
import com.bluexml.side.Class.modeler.diagram.utils.metainfo.OblClassMetaInfo;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.MetaInfoGroup;
import com.bluexml.side.common.Comment;
import com.bluexml.side.common.Stereotype;

public class ClazzEditDialog extends Dialog implements IDialogConstants {
	/** The ID of the property name */
	public static final String CLASSE_NAME = "class name";
	public static final String CLASSE_TITLE = "class title";
	public static final String CLASSE_DESCRIPTION = "class description";
	public static final String CLASSE_VIEW = "class view";
	public static final String CLASSE_LAYOUT = "class layout";
	public static final String CLASSE_METAINFO = "class metainfo";
	public static final String CLASSE_ISABSTRACT = "class is abstract";
	public static final String CLASSE_DOCUMENTATION = "class documentation";
	public static final String CLASSE_ISDEPRECATED = "class is deprecated";

	private static final int MIN_DIALOG_WIDTH = 500;

	private static final int MIN_DIALOG_HEIGHT = 300;

	/** Current edited property */
	private Clazz classe;

	// SWT Objects
	private Text classeNameTxt;

	private Text classeTitleTxt;

	private Text classeDescriptionTxt;

	private Map data;

	// private Control view;

	private Control layout;

	private Button checkIsAbstractBt;

	private Button checkIsDeprecatedBt;

	private Map drawConstraints = new HashMap();

	private Text documentation;
	private Table table;

	/**
	 * Create the dialog for a given Attribute
	 * 
	 * @param prop
	 *            the Attribute to edit
	 * @param parentShell
	 *            the parent shell
	 */
	public ClazzEditDialog(Clazz clazz, Shell parentShell) {
		super(parentShell);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		classe = clazz;
	}

	/**
	 * Set the title of the new shell
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		newShell.setText("Class " + classe.getName());
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
//		createViewTab(tabFolder);
//		createLayoutTab(tabFolder);
//		createAspectOrganizationTabItem(tabFolder);
//		createOptionsTab(tabFolder);
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

	private void createOptionsTab(TabFolder parent) {
		// Create tab item and add it composite that fills it
		TabItem generalItem = new TabItem((TabFolder) parent, SWT.NONE);
		generalItem.setText("Options");
		Composite composite = new Composite(parent, SWT.NONE);
		generalItem.setControl(composite);

		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Collection c = (new OblClassMetaInfo()).getAllMetaInfo();

		for (Object obj : c) {
			if (obj instanceof MetaInfo)
				MetaInfoHelper.drawConstraint(composite, (MetaInfo) obj, drawConstraints, classe);
			else if (obj instanceof MetaInfoGroup)
				MetaInfoHelper.drawConstraintGroup(composite, (MetaInfoGroup) obj, drawConstraints, classe);
		}
	}

	private void createViewTab(TabFolder parent) {
		// Create tab item and add it composite that fills it
		TabItem viewItem = new TabItem((TabFolder) parent, SWT.NONE);
		viewItem.setText("View");
		Composite composite = new Composite(parent, SWT.NONE);
		viewItem.setControl(composite);

		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		// view = new Text(composite, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
		// view.setLayoutData(new GridData(GridData.FILL_BOTH));
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
		classeNameTxt = new Text(composite, SWT.BORDER);
		classeNameTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label classTitleLbl = new Label(composite, SWT.NONE);
		classTitleLbl.setText("Title : ");
		classeTitleTxt = new Text(composite, SWT.BORDER);
		classeTitleTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label classDesriptionLbl = new Label(composite, SWT.NONE);
		classDesriptionLbl.setText("Description : ");
		classeDescriptionTxt = new Text(composite, SWT.BORDER);
		classeDescriptionTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label isAbstractLbl = new Label(composite, SWT.NONE);
		isAbstractLbl.setText("Is abstract : ");
		checkIsAbstractBt = new Button(composite, SWT.CHECK);

		Label isDeprecated = new Label(composite, SWT.NONE);
		isDeprecated.setText("Is deprecated : ");
		checkIsDeprecatedBt = new Button(composite, SWT.CHECK);
	}

	private void createAspectOrganizationTabItem(TabFolder parent) {
		// Create tab item and add it composite that fills it
		TabItem tabItem = new TabItem(parent, SWT.NONE);
		tabItem.setText("Aspects Organisation");
		Composite composite = new Composite(parent, SWT.NONE);
		tabItem.setControl(composite);

		// Add layout on composite
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		// Create panel table
		Composite panelTable = new Composite(composite, SWT.NONE);
		panelTable.setLayout(new GridLayout(1, false));
		panelTable.setLayoutData(new GridData(GridData.FILL_BOTH));

		// Create panel button
		Composite panelButton = new Composite(composite, SWT.NONE);
		panelButton.setLayout(new GridLayout(1, false));
		panelButton.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.HORIZONTAL_ALIGN_CENTER));

		// Create Table
		createTableViewer(panelTable);

		// Create Button UP
		Button up = new Button(panelButton, SWT.PUSH | SWT.CENTER);
		up.setText("Up");
		up.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int index = table.getSelectionIndex();
				if (index > 0) {
					TableItem itemMoved = table.getItem(index);
					TableItem otherItem = table.getItem(index - 1);
					Object objectMoved = itemMoved.getData();
					Object otherObject = otherItem.getData();
					itemMoved.setData(otherObject);
					otherItem.setData(objectMoved);
					itemMoved.setText(((Aspect) otherObject).getName());
					otherItem.setText(((Aspect) objectMoved).getName());
				}
			}
		});

		// Create Button DOWN
		Button down = new Button(panelButton, SWT.PUSH | SWT.CENTER);
		down.setText("Down");
		down.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int index = table.getSelectionIndex();
				if (index < table.getItemCount() - 1) {
					TableItem itemMoved = table.getItem(index);
					TableItem otherItem = table.getItem(index + 1);
					Object objectMoved = itemMoved.getData();
					Object otherObject = otherItem.getData();
					itemMoved.setData(otherObject);
					otherItem.setData(objectMoved);
					itemMoved.setText(((Aspect) otherObject).getName());
					otherItem.setText(((Aspect) objectMoved).getName());
				}
			}
		});
	}

	/**
	 * 
	 * @param composite
	 */
	private void createTableViewer(Composite panelTable) {
		int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.HIDE_SELECTION;
		table = new Table(panelTable, style);
		TableColumn aspectColumn = new TableColumn(table, SWT.LEFT);
		aspectColumn.setText("Aspects");
		aspectColumn.setWidth(350);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalSpan = 2;
		table.setLayoutData(gridData);

		TableViewer tableViewer = new TableViewer(table);

		tableViewer.setUseHashlookup(true);
		tableViewer.setColumnProperties(new String[] { "Aspects" });

		tableViewer.setContentProvider(new AspectContentProvider());
		tableViewer.setLabelProvider(new AspectLabelProvider());
		tableViewer.setInput(classe.getAspects());
	}

	/**
	 * Initialize the content of the widgets
	 */
	private void loadData() {
		// Name
		classeNameTxt.setText(classe.getName());
		if (classe.getTitle() != null)
			classeTitleTxt.setText(classe.getTitle());
		if (classe.getDescription() != null)
			classeDescriptionTxt.setText(classe.getDescription());
		checkIsAbstractBt.setSelection(classe.isAbstract());
		checkIsDeprecatedBt.setSelection(classe.isDeprecated());

		// Layout & View
		
		/*
		for (Object o : classe.getComments()) {
			Comment c = (Comment) o;
			if (isStereotyped(c, "view")) {
				((Text) view).setText(c.getValue());
			}
			if (isStereotyped(c, "layout")) {
				((Text) layout).setText(c.getValue());
			}
		}
		 */
		
		// MetaInfo
		MetaInfoHelper.loadData(classe, drawConstraints);

		// Doc
		if (classe.getDocumentation() != null)
			documentation.setText(classe.getDocumentation());
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
			data.put(CLASSE_NAME, classeNameTxt.getText());
			data.put(CLASSE_TITLE, classeTitleTxt.getText());
			data.put(CLASSE_DESCRIPTION, classeDescriptionTxt.getText());
//			data.put(CLASSE_VIEW, ((Text) view).getText());
			
//			data.put(CLASSE_LAYOUT, ((Text) layout).getText());
//			ConstraintsDataStructure dataConstraints = MetaInfoHelper.getDataStructure(drawConstraints);
//			data.put(CLASSE_METAINFO, dataConstraints);
			data.put(CLASSE_ISABSTRACT, checkIsAbstractBt.getSelection());
			data.put(CLASSE_DOCUMENTATION, documentation.getText());
			data.put(CLASSE_ISDEPRECATED, checkIsDeprecatedBt.getSelection());

			// Refresh order aspects
//			classe.getAspects().clear();
//			for (TableItem ti : table.getItems()) {
//				classe.getAspects().add((Aspect) ti.getData());
//			}
			super.okPressed();
		} catch (Exception e) {
			// TODO change this with a validation listener that disable the ok
			// button until the widgets are valid
			ClazzPlugin.log("Required fields", IStatus.WARNING);
			MessageDialog.openWarning(getShell(), "Required parameters", "Some parameters are not set.\nPlease, fill those fields before validating.");
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

	class AspectContentProvider implements IStructuredContentProvider {
		/**
		 * 
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		public Object[] getElements(Object inputElement) {
			return classe.getAspects().toArray();
		}

		/**
		 * 
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose() {
			// nothing to do
		}

		/**
		 * 
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
		 *      java.lang.Object, java.lang.Object)
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// nothing to do
		}
	}

	class AspectLabelProvider extends LabelProvider implements ITableLabelProvider {
		/**
		 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object,
		 *      int)
		 */
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		/**
		 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
		 *      int)
		 */
		public String getColumnText(Object element, int columnIndex) {
			String result = "";
			switch (columnIndex) {
			case 0:
				if (element instanceof Aspect) {
					Aspect aspect = (Aspect) element;
					result = aspect.getName();
				}
				break;
			default:
				break;
			}
			return result;
		}
	}
}
