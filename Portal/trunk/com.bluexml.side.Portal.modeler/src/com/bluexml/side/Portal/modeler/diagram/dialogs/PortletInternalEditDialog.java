package com.bluexml.side.Portal.modeler.diagram.dialogs;

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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.topcased.facilities.widgets.SingleObjectChooser;

import com.bluexml.side.Portal.modeler.PortalPlugin;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.clazz.provider.ClazzItemProviderAdapterFactory;
import com.bluexml.side.portal.InternalPortletType;
import com.bluexml.side.portal.PortletInternal;



public class PortletInternalEditDialog extends Dialog implements IDialogConstants{

	PortletInternal portletInternal;
	
	private static final int MIN_DIALOG_WIDTH = 500;

	private static final int MIN_DIALOG_HEIGHT = 300;
	
	public static final String PORTLETINTERNAL_Class = "portletInternal class";
	
	public static final String PORTLETINTERNAL_Type = "portletInternal type";
	
	public static final String PORTLETINTERNAL_View = "portletInternal view";
			
	private Map data;		

	private SingleObjectChooser typeChooser;

	private SingleObjectChooser classChooser;

	private Collection<EObject> reachableEnumeration;

	private SingleObjectChooser viewChooser;

	private Label propertyViewLbl;
	
	public PortletInternalEditDialog(PortletInternal p_portletInternal, Shell p_parentShell) {
		super(p_parentShell);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		portletInternal = p_portletInternal;		
	}
	
	public Map getData() {
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
	}
	
	private void createGeneralTab(Composite parent) {
		// Create tab item and add it composite that fills it
		TabItem generalItem = new TabItem((TabFolder) parent, SWT.NONE);
		generalItem.setText("General");
		Composite composite = new Composite(parent, SWT.NONE);
		generalItem.setControl(composite);

		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label propertyClassLbl = new Label(composite, SWT.NONE);
		propertyClassLbl.setText("Class : ");		
		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
				new ClazzItemProviderAdapterFactory());
		
		classChooser = new SingleObjectChooser(composite, SWT.NONE);
		classChooser.setLabelProvider(labelProvider);
		classChooser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		classChooser.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				refreshViewList();			
			}
			public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
			}
		});
		
		Label propertyTypeLbl = new Label(composite, SWT.NONE);
		propertyTypeLbl.setText("Type : ");		
		
		//ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
			//	new OblItemProviderAdapterFactory());

		typeChooser = new SingleObjectChooser(composite, SWT.NONE);
		typeChooser.setLabelProvider(labelProvider);
		typeChooser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		typeChooser.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				refreshViewList();			
			}

			public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
			}
		});
		
		propertyViewLbl = new Label(composite, SWT.NONE);
		propertyViewLbl.setText("View : ");	
		propertyViewLbl.setVisible(false);
		
		viewChooser = new SingleObjectChooser(composite, SWT.NONE);
		viewChooser.setLabelProvider(labelProvider);
		viewChooser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}
	
	/**
	 * Initialize the content of the widgets
	 */
	private void loadData() {	
		
		// Types
		Collection<InternalPortletType> reachableTypes = InternalPortletType.VALUES;
		typeChooser.setChoices(reachableTypes.toArray());
		typeChooser.setSelection(portletInternal.getType());						
		
		// Value list
		reachableEnumeration = ItemPropertyDescriptor
				.getReachableObjectsOfType(portletInternal, ClazzPackage.eINSTANCE.getClazz());
		Object[] enumArray = reachableEnumeration.toArray();
		Object[] enumArray2 = new Object[enumArray.length + 1];
		for (int i = 0; i < enumArray.length; ++i) {
			enumArray2[i] = enumArray[i];
		}
		
		enumArray2[enumArray.length] = new String();
		classChooser.setChoices(enumArray2);
		if (portletInternal.getClass_() == null) {
			classChooser.setSelection("");			
		}
		else {
			classChooser.setSelection(portletInternal.getClass_());
			// View, only if Array
			if (portletInternal.getType().getValue() == 1) {
				refreshViewList();
			}
			viewChooser.setSelection(portletInternal.getView());
		}					
	}
	
	
	private void refreshViewList() {		
		if(((InternalPortletType) typeChooser.getSelection()).getValue() == 1) {
			propertyViewLbl.setVisible(true);				
			viewChooser.setVisible(true);	
			if (classChooser.getSelection() != null) {				
				if (classChooser.getSelection() instanceof Clazz) {
					Clazz c = (Clazz) classChooser.getSelection();
					Object[] viewArray = new Object[c.getHasView().size() + 1]; 
					Object[] classviewArray = c.getHasView().toArray();					
					for (int i = 0; i < classviewArray.length; ++i) {
						viewArray[i] = classviewArray[i];
					}
					viewArray[viewArray.length - 1] = new String();
					viewChooser.setChoices(viewArray);												
				} 
			}
		} else {
			propertyViewLbl.setVisible(false);				
			viewChooser.setVisible(false);
			viewChooser.setSelection("");
		}
	}

	/**
	 * Save the values before the widgets are disposed
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		data = new HashMap();
		try {
			data.put(PORTLETINTERNAL_Class, classChooser.getSelection());
			data.put(PORTLETINTERNAL_Type, typeChooser.getSelection());
			data.put(PORTLETINTERNAL_View, viewChooser.getSelection());
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
