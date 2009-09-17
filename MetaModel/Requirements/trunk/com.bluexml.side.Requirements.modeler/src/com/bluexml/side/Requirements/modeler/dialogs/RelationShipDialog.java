package com.bluexml.side.Requirements.modeler.dialogs;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import com.bluexml.side.Requirements.modeler.RequirementsPlugin;
import com.bluexml.side.requirements.RelationShip;

public class RelationShipDialog extends BasicElementDialog {

	private Button sourceMin; 
	private Button sourceMax; 
	private Button targetMin; 
	private Button targetMax;
	
	public static final String RELATIONSHIP_SOURCE_MIN = "relationship source min"; //$NON-NLS-1$
	public static final String RELATIONSHIP_SOURCE_MAX = "relationship source max"; //$NON-NLS-1$
	public static final String RELATIONSHIP_TARGET_MIN = "relationship target min"; //$NON-NLS-1$
	public static final String RELATIONSHIP_TARGET_MAX = "relationship target max"; //$NON-NLS-1$
	
	public RelationShipDialog(Shell parent, RelationShip element) {
		super(parent, element);
	}
	
	@Override
	protected void createCustomItems(Composite parent) {
		super.createCustomItems(parent);
		
		RelationShip r = (RelationShip) element;

		// Create tab item and add it composite that fills it
		TabItem item = new TabItem((TabFolder) parent, SWT.NONE);
		item.setText("Options"); //$NON-NLS-1$
		Composite composite = new Composite(parent, SWT.NONE);
		item.setControl(composite);

		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label lbl = new Label(composite, SWT.WRAP);
		lbl.setText(RequirementsPlugin.Messages.getString("RelationShipDialog_5")+r.getTarget().getName()+RequirementsPlugin.Messages.getString("RelationShipDialog_6")+r.getSource().getName()+" ?"); //$NON-NLS-3$
		targetMax = createRadioButton(composite);
		
		lbl = new Label(composite, SWT.WRAP);
		lbl.setText(RequirementsPlugin.Messages.getString("RelationShipDialog_7")+r.getTarget().getName()+RequirementsPlugin.Messages.getString("RelationShipDialog_6")+r.getSource().getName()+" ?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		targetMin = createRadioButton(composite);

		lbl = new Label(composite, SWT.WRAP);
		lbl.setText(RequirementsPlugin.Messages.getString("RelationShipDialog_5")+r.getSource().getName()+RequirementsPlugin.Messages.getString("RelationShipDialog_6")+r.getTarget().getName()+" ?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		sourceMax = createRadioButton(composite);
		
		lbl = new Label(composite, SWT.WRAP);
		lbl.setText(RequirementsPlugin.Messages.getString("RelationShipDialog_7")+r.getSource().getName()+RequirementsPlugin.Messages.getString("RelationShipDialog_6")+r.getTarget().getName()+" ?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		sourceMin = createRadioButton(composite);
	}
	
	private Button createRadioButton(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
	    composite.setLayout(new RowLayout());
	    
	    Button yes = new Button(composite, SWT.RADIO);
	    yes.setText(RequirementsPlugin.Messages.getString("RelationShipDialog_17"));
	    Button no = new Button(composite, SWT.RADIO);
	    no.setText(RequirementsPlugin.Messages.getString("RelationShipDialog_18"));
	    
	    return yes;
	}
	
	@Override
	protected void okPressed() {
		if (data == null)
			data = new HashMap<String, Object>();
		data.put(RELATIONSHIP_SOURCE_MAX, sourceMax.getSelection());
		data.put(RELATIONSHIP_SOURCE_MIN, sourceMin.getSelection());
		data.put(RELATIONSHIP_TARGET_MAX, targetMax.getSelection());
		data.put(RELATIONSHIP_TARGET_MIN, targetMin.getSelection());
		super.okPressed();
	}
	
	@Override
	protected void loadData() {
		super.loadData();
		RelationShip r = (RelationShip) element;
		
		sourceMin.setSelection(r.getSourceMin() == 0);
		((Button) sourceMin.getParent().getChildren()[1]).setSelection(!sourceMin.getSelection()); 
		sourceMax.setSelection(r.getSourceMax() == -1);
		((Button) sourceMax.getParent().getChildren()[1]).setSelection(!sourceMax.getSelection()); 
		targetMin.setSelection(r.getTargetMin() == 0);
		((Button) targetMin.getParent().getChildren()[1]).setSelection(!targetMin.getSelection()); 
		targetMax.setSelection(r.getTargetMax() == -1);
		((Button) targetMax.getParent().getChildren()[1]).setSelection(!targetMax.getSelection()); 
	}

}
