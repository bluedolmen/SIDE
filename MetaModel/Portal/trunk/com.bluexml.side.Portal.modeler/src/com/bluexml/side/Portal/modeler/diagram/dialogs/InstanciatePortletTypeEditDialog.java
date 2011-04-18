package com.bluexml.side.Portal.modeler.diagram.dialogs;

import java.util.ArrayList;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import com.bluexml.side.Portal.modeler.PortalPlugin;
import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.InstancesDataStructure;
import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.InstancesDataStructure.InstancesObject;
import com.bluexml.side.Portal.modeler.diagram.dialogs.viewer.InstancesViewer;
import com.bluexml.side.portal.InstanciatePortletType;
import com.bluexml.side.portal.PortletAttribute;

public class InstanciatePortletTypeEditDialog extends Dialog implements IDialogConstants{

	private InstanciatePortletType instances;
	
	private Map<String, Object> data;

	private InstancesViewer inputInstances;
	
	private static final int MIN_DIALOG_WIDTH = 500;

	private static final int MIN_DIALOG_HEIGHT = 300;
	
	public static final String INSTANCIATEPORTLETTYPE_instances = "InstanciatePortletType instances";
	
	protected ArrayList<String> dynAttributesNames = new ArrayList<String>();
	
	protected Combo dynamAttribute;
	
	protected InstancesDataStructure dataStruct;
	
	public InstanciatePortletTypeEditDialog(InstanciatePortletType p_instances, Shell p_parentShell) {
		super(p_parentShell);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		instances = p_instances;
		updateDynAttrNames();
	}
	
	private void updateDynAttrNames() {
		Iterator<PortletAttribute> itAttr = instances.getPortletType().getHaveAttribute().iterator();
		while (itAttr.hasNext()) {
			PortletAttribute attr = itAttr.next();
			if (attr.isMultiValued()) {
				dynAttributesNames.add(attr.getName());
			}
		}
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

		createDialogGroup(dialogComposite);		
		
		return dialogComposite;
	}
	
	protected void createDialogGroup(Composite parent) {
		TabFolder tabFolder = new TabFolder(parent, SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createGeneralTab(tabFolder);		
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
		propertyIdLbl.setText("Instances : ");
		
		dataStruct = new InstancesDataStructure(instances.getInstances(),instances.getPortletType().getHaveAttribute());		
		inputInstances = new InstancesViewer(composite,  dataStruct);
		
		// If there is dynamic attributes we add the possibility to add instances
		if (dynAttributesNames.size() > 0) {		
			
			Label addInstanceIdLbl = new Label(composite, SWT.NONE);
			addInstanceIdLbl.setText("Add instance of : ");
			
			dynamAttribute = new Combo(composite,SWT.DROP_DOWN | SWT.READ_ONLY);
			if (dynAttributesNames != null) {
				dynamAttribute.setItems((String[]) dynAttributesNames.toArray(new String[dynAttributesNames.size()]));
			}		
	
			Button add = new Button(composite, SWT.PUSH | SWT.CENTER);
			add.setText("Add");				
			GridData gd = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END);
			gd.widthHint = 80;
			add.setLayoutData(gd);
			dynamAttribute.setLayoutData(gd);
			add.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					addInstance();
				}
			});
	
			Button delete = new Button(composite, SWT.PUSH | SWT.CENTER);
			delete.setText("Delete");
			GridData gdDel = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_BEGINNING);
			gdDel.widthHint = 80;
			delete.setLayoutData(gdDel);
	
			delete.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					removeInstance();
				}
			});
		}
	}
	
	/**
	 * Add an instance of the selected attribute choosen.
	 */
	protected void addInstance() {
		if (dynamAttribute.getSelectionIndex() > -1) {
			dataStruct.add(dynamAttribute.getText());
			inputInstances.refresh();
		}
	}
	
	/**
	 * Remove an instance (only available for multivalued attribute)
	 */
	protected void removeInstance() {
		// We can remove only multivalued field
		if (dynAttributesNames.contains(((InstancesObject) inputInstances.getSelection()).getAttributeName())) {
			inputInstances.remove();
			inputInstances.refresh();
		}
	}
	
	/**
	 * Save the values before the widgets are disposed
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		data = new HashMap<String, Object>();
		try {
			data.put(INSTANCIATEPORTLETTYPE_instances, inputInstances.getData());			
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
