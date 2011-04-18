package com.bluexml.side.Portal.modeler.diagram.dialogs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.PortletTypeAttributeDataStructure;
import com.bluexml.side.Portal.modeler.diagram.dialogs.viewer.PortletTypeAttributeViewer;
import com.bluexml.side.portal.PortletType;
import com.bluexml.side.portal.PortletTypeAttributeType;

public class PortletTypeEditDialog  extends Dialog implements IDialogConstants{
	
	private PortletType portletType;
	
	private static final int MIN_DIALOG_WIDTH = 500;

	private static final int MIN_DIALOG_HEIGHT = 400;
	
	public static final String PORTLETYPE_Attributes = "portletType attributes";
	
	public static final String PORTLETYPE_Groups = "portletType groups";
	
	public static final String PORTLETYPE_ID = "portletType id";
	
	public static final String PORTLETYPE_Instanceable = "portletType instanceable";
	
	public static final String PORTLETYPE_Name = "portletType Name";
	
	private Map<String, Object> data;

	private PortletTypeAttributeViewer inputAttributes;	

	private Text portletId;
	
	private Text portletName;
	
	private Button portletInstanceable;
	
	public PortletTypeEditDialog(PortletType p_portletType, Shell p_parentShell) {
		super(p_parentShell);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		portletType = p_portletType;			
	}
	
	public Map<String, Object> getData() {
		return data;		
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite dialogComposite = (Composite) super.createDialogArea(parent);
		GridData dialogLayoutData = new GridData(GridData.FILL_BOTH);
		dialogLayoutData.widthHint = MIN_DIALOG_WIDTH;
		dialogLayoutData.heightHint = MIN_DIALOG_HEIGHT;
		dialogComposite.setLayoutData(dialogLayoutData);

		createPageGroup(dialogComposite);
		loadData();
		
		return dialogComposite;
	}
	
	protected void createPageGroup(Composite parent) {
		TabFolder tabFolder = new TabFolder(parent, SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createGeneralTab(tabFolder);
		createAttributeTab(tabFolder);
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
		propertyIdLbl.setText("ID : ");
		portletId = new Text(composite, SWT.BORDER);
		portletId.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label propertyTitleLbl = new Label(composite, SWT.NONE);
		propertyTitleLbl.setText("Title : ");
		portletName = new Text(composite, SWT.BORDER);
		portletName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label instanceableTitleLbl = new Label(composite, SWT.NONE);
		instanceableTitleLbl.setText("Instanceable : ");
		portletInstanceable = new Button(composite,SWT.CHECK);
		portletInstanceable.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}
	
	private void createAttributeTab(Composite parent) {
		TabItem generalItem = new TabItem((TabFolder) parent, SWT.NONE);
		generalItem.setText("Attribute");
		Composite composite = new Composite(parent, SWT.NONE);
		generalItem.setControl(composite);

		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		// Attributes
		Label propertyAttrLbl = new Label(composite, SWT.NONE);
		propertyAttrLbl.setText("Attributes : ");	
		
		String[] attrType = getAttributeType();		
						
		PortletTypeAttributeDataStructure dataStruct = new PortletTypeAttributeDataStructure(portletType.getHaveAttribute(), attrType);
		inputAttributes = new PortletTypeAttributeViewer(composite,dataStruct, attrType);
		Button add = new Button(composite, SWT.PUSH | SWT.CENTER);
		add.setText("Add");

		GridData gd = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END);
		gd.widthHint = 80;
		add.setLayoutData(gd);
		add.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputAttributes.addAttribute();
			}
		});

		Button delete = new Button(composite, SWT.PUSH | SWT.CENTER);
		delete.setText("Delete");
		gd = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.widthHint = 80;
		delete.setLayoutData(gd);

		delete.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputAttributes.removeAttribute();
			}
		});				
	}		

	private String[] getAttributeType() {
		Collection<PortletTypeAttributeType> reachableTypes = PortletTypeAttributeType.VALUES;
		String[] arrType = new String[reachableTypes.size()];
		Iterator<PortletTypeAttributeType> itAttr = reachableTypes.iterator();
		int i = 0;
		while (itAttr.hasNext()) {
			PortletTypeAttributeType attr = itAttr.next();			
			arrType[i] = attr.getName();
			i++;
		}
		return arrType;
	}
	
	/**
	 * Initialize the content of the widgets
	 */
	private void loadData() {	
		if (portletType.getId() != null) {
			portletId.setText(portletType.getId());
		}
		if (portletType.getName() != null) {
			portletName.setText(portletType.getName());
		}	
		portletInstanceable.setSelection(portletType.isInstanceable());
	}
	
	/**
	 * Save the values before the widgets are disposed
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		data = new HashMap<String, Object>();
		try {
			data.put(PORTLETYPE_ID, portletId.getText());
			data.put(PORTLETYPE_Name, portletName.getText());
			data.put(PORTLETYPE_Attributes,inputAttributes.getData());	
			data.put(PORTLETYPE_Instanceable, portletInstanceable.getSelection());
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
