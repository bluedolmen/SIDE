package com.bluexml.side.Portal.modeler.diagram.dialogs;

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
import org.eclipse.swt.widgets.Text;

import com.bluexml.side.Portal.modeler.PortalPlugin;
import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.ColumnDataStructure;
import com.bluexml.side.Portal.modeler.diagram.dialogs.viewer.ColumnViewer;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.widthUnit;

public class PortalLayoutEditDialog extends Dialog implements IDialogConstants{

	/** The ID of the property name */
	public static final String PAGELAYOUT_Name = "pagelayout name";	
	
	public static final String PAGELAYOUT_Columns = "pagelayout columns";
	
	public static final String PAGELAYOUT_ColumnsKind = "pagelayout columnKind";
	
	protected PortalLayout portalLayout;
	
	protected Map<String, Object> data;

	
	
	private Text portalLayoutName;	
	
	private ColumnViewer inputColumns;
	
	private String[] widthType;

	private Button portalLayoutColumnKind;

	private TabItem tabTitleLabel;

	private Label tableTitle;
	
	private static final int MIN_DIALOG_WIDTH = 500;

	private static final int MIN_DIALOG_HEIGHT = 300;
	
	public PortalLayoutEditDialog(PortalLayout p_portalLayout, Shell p_parentShell) {
		super(p_parentShell);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		portalLayout = p_portalLayout;
		initializeTypes();
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite dialogComposite = (Composite) super.createDialogArea(parent);
		GridData dialogLayoutData = new GridData(GridData.FILL_BOTH);
		dialogLayoutData.widthHint = MIN_DIALOG_WIDTH;
		dialogLayoutData.heightHint = MIN_DIALOG_HEIGHT;
		dialogComposite.setLayoutData(dialogLayoutData);

		createPageLayoutGroup(dialogComposite);

		loadData();
		
		return dialogComposite;
	}
	
	protected void createPageLayoutGroup(Composite parent) {
		TabFolder tabFolder = new TabFolder(parent, SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createGeneralTab(tabFolder);
		createColumnTab(tabFolder);
	}
	
	private void createGeneralTab(Composite parent) {
		// Create tab item and add it composite that fills it
		TabItem generalItem = new TabItem((TabFolder) parent, SWT.NONE);
		generalItem.setText("General");
		Composite composite = new Composite(parent, SWT.NONE);
		generalItem.setControl(composite);

		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label propertyIdLbl = new Label(composite, SWT.NONE);
		propertyIdLbl.setText("Name : ");
		portalLayoutName = new Text(composite, SWT.BORDER);
		portalLayoutName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));				
	}
	
	
	private void createColumnTab(Composite parent) {
		// Create tab item and add it composite that fills it
		TabItem viewItem = new TabItem((TabFolder) parent, SWT.NONE);		
		tabTitleLabel = viewItem;
		Composite composite = new Composite(parent, SWT.NONE);
		viewItem.setControl(composite);
		
		Label propertyColumnKindLbl = new Label(composite, SWT.NONE);
		propertyColumnKindLbl.setText("Column layout : ");
		portalLayoutColumnKind = new Button(composite,SWT.CHECK);
		portalLayoutColumnKind.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));	
		portalLayoutColumnKind.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				if (portalLayoutColumnKind.getSelection()) {
					useColumns();					
				} else {
					useArea();					
				}				
			}

			public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
			}
		});
		
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		tableTitle = new Label(composite, SWT.NONE);		
								
		inputColumns = new ColumnViewer(composite, new ColumnDataStructure(portalLayout.getColumns()), widthType);
		Button add = new Button(composite, SWT.PUSH | SWT.CENTER);
		add.setText("Add");

		GridData gd = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END);
		gd.widthHint = 80;
		add.setLayoutData(gd);
		add.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputColumns.addColumn();
			}
		});

		Button delete = new Button(composite, SWT.PUSH | SWT.CENTER);
		delete.setText("Delete");
		gd = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.widthHint = 80;
		delete.setLayoutData(gd);

		delete.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputColumns.removeColumn();
			}
		});
		
		if (portalLayout.isColumnMode()) {
			useColumns();	
		} else {
			useArea();
		}
	}
	
	private void useArea() {
		inputColumns.useAreaName();
		tabTitleLabel.setText("Area");
		tableTitle.setText("Areas");		
	}
	
	private void useColumns() {
		inputColumns.useColumns();
		tabTitleLabel.setText("Column");
		tableTitle.setText("Columns");
	}
	
	/**
	 * Initialize the the widgets content
	 */
	private void loadData() {
		if (portalLayout.getName() != null) {
			portalLayoutName.setText(portalLayout.getName());			
		}
		portalLayoutColumnKind.setSelection(portalLayout.isColumnMode());
	}
	
	/**
	 * Initialize attributes about column size type that model currently contains
	 * 
	 */
	private void initializeTypes() {
		widthType = new String[widthUnit.VALUES.size()];
		int i = 0;
		for (Object o : widthUnit.VALUES) {
			widthType[i] = o.toString();
			i++;
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
	
	/**
	 * Save the values before the widgets are disposed
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		data = new HashMap<String, Object>();
		try {
			data.put(PAGELAYOUT_Name, portalLayoutName.getText());
			data.put(PAGELAYOUT_Columns, inputColumns.getData());		
			data.put(PAGELAYOUT_ColumnsKind, portalLayoutColumnKind.getSelection());
			super.okPressed();
		} catch (Exception e) {
			// TODO change this with a validation listener that disable the ok
			// button until the widgets are valid
			PortalPlugin.log("Required fields", IStatus.WARNING);
			MessageDialog.openWarning(getShell(), "Required parameters",
							"Some parameters are not set.\nPlease, fill those fields before validating.");
		}
	}
}
