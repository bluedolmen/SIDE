package com.bluexml.side.Portal.modeler.diagram.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
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
import com.bluexml.side.clazz.provider.ClazzItemProviderAdapterFactory;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.provider.FormItemProviderAdapterFactory;
import com.bluexml.side.portal.InternalPortletType;
import com.bluexml.side.portal.PortletInternal;
import com.bluexml.side.view.ViewPackage;
import com.bluexml.side.view.provider.ViewItemProviderAdapterFactory;
public class PortletInternalEditDialog extends Dialog implements IDialogConstants {

	PortletInternal portletInternal;

	private static final int MIN_DIALOG_WIDTH = 500;

	private static final int MIN_DIALOG_HEIGHT = 300;

	public static final String PORTLETINTERNAL_Form = "portletInternal form";

	public static final String PORTLETINTERNAL_Type = "portletInternal type";

	public static final String PORTLETINTERNAL_View = "portletInternal view";
	
	

	private Map data;

	private SingleObjectChooser typeChooser;

	

	private SingleObjectChooser chooser;

	private Label propertyChooserLbl;
	

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

		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(new ClazzItemProviderAdapterFactory());

		portletTypeChooser(composite, labelProvider);

		
		chooser(composite);
	}

	

	private void chooser(Composite composite) {
		if (propertyChooserLbl == null) {
			propertyChooserLbl = new Label(composite, SWT.NONE);
		}
		
		if (chooser == null) {
			chooser = new SingleObjectChooser(composite, SWT.NONE);
			
			chooser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		}		
		propertyChooserLbl.setVisible(false);
	}

	private void portletTypeChooser(Composite composite, ILabelProvider labelProvider) {
		Label propertyTypeLbl = new Label(composite, SWT.NONE);
		propertyTypeLbl.setText("Type : ");

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
	}

	/**
	 * Initialize the content of the widgets
	 */
	private void loadData() {

		// Portlet Types
		Collection<InternalPortletType> reachableTypes = InternalPortletType.VALUES;
		typeChooser.setChoices(reachableTypes.toArray());
		typeChooser.setSelection(portletInternal.getType());
		
	}

	
	private void loadValuesIn(SingleObjectChooser chooser,List<EClass> listeOf) {
		List<EObject> insntacesL = new ArrayList<EObject>();
		for (EClass type : listeOf) {
			Collection<EObject> instances = ItemPropertyDescriptor.getReachableObjectsOfType(portletInternal, type);
			insntacesL.addAll(instances);
		}
		
		Object[] instancesArray = insntacesL.toArray();	
		Object[] viewsValue = new Object[instancesArray.length + 1];
		for (int i = 0; i < instancesArray.length; i++) {
			viewsValue[i] = instancesArray[i];
		}
		viewsValue[instancesArray.length] = new String();
		chooser.setChoices(viewsValue);		
	}
	
	

	private void refreshViewList() {
		// display chooser
		propertyChooserLbl.setVisible(true);
		chooser.setVisible(true);
		InternalPortletType t = (InternalPortletType) typeChooser.getSelection();
		propertyChooserLbl.setText(t.getLiteral());
		if (t.getValue() == 1) {
			// select types to view
			ArrayList<EClass> viewsType = new ArrayList<EClass>();
			viewsType.add(ViewPackage.eINSTANCE.getDataList());
			viewsType.add(ViewPackage.eINSTANCE.getComposedView());
			viewsType.add(ViewPackage.eINSTANCE.getDataTable());
			viewsType.add(ViewPackage.eINSTANCE.getTree());
			viewsType.add(ViewPackage.eINSTANCE.getFacetMap());
			ILabelProvider labelProvider = new AdapterFactoryLabelProvider(new ViewItemProviderAdapterFactory());
			chooser.setLabelProvider(labelProvider);
			// load instances list
			loadValuesIn(chooser, viewsType);
			// select item
			if (portletInternal.getView() ==null) {
				chooser.setSelection("");
			} else {
				chooser.setSelection(portletInternal.getView());
			}
		} else {
			// select types to view
			ArrayList<EClass> formsType = new ArrayList<EClass>();
			//formsType.add(FormPackage.eINSTANCE.getFormWorkflow());
			//formsType.add(FormPackage.eINSTANCE.getFormClass());
			formsType.add(FormPackage.eINSTANCE.getFormCollection());
			ILabelProvider labelProvider = new AdapterFactoryLabelProvider(new FormItemProviderAdapterFactory());
			chooser.setLabelProvider(labelProvider);
			// load instances list
			loadValuesIn(chooser, formsType);
			// select item
			if (portletInternal.getForm() ==null) {
				chooser.setSelection("");
			} else {
				chooser.setSelection(portletInternal.getForm());
			}
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
			InternalPortletType t = (InternalPortletType) typeChooser.getSelection();			
			data.put(PORTLETINTERNAL_Type, t);
			if (t.equals(InternalPortletType.FORM)) {
				data.put(PORTLETINTERNAL_Form, chooser.getSelection());
			} else {
				data.put(PORTLETINTERNAL_View, chooser.getSelection());
			}
			
			super.okPressed();
		} catch (Exception e) {
			// TODO change this with a validation listener that disable the ok
			// button until the widgets are valid
			PortalPlugin.log("Required fields", IStatus.WARNING);
			MessageDialog.openWarning(getShell(), "Required parameters", "Some parameters are not set.\nPlease, fill those fields before validating.");
		}
	}
}
