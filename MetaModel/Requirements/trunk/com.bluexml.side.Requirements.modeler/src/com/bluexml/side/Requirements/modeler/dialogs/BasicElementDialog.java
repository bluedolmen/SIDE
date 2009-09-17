package com.bluexml.side.Requirements.modeler.dialogs;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
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

import com.bluexml.side.requirements.BasicElement;

public class BasicElementDialog extends Dialog implements IDialogConstants {

	private static final int MIN_DIALOG_WIDTH = 500;
	private static final int MIN_DIALOG_HEIGHT = 300;
	private Text documentation;
	private Text name;
	protected Map<String,Object> data;
	protected BasicElement element;

	public static final String BASICELEMENT_DOCUMENTATION = "basic element documentation";
	public static final String BASICELEMENT_NAME = "basic element name";

	public BasicElementDialog(Shell parent, BasicElement _element) {
		super(parent);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		element = _element;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogComposite = (Composite) super.createDialogArea(parent);
		GridData dialogLayoutData = new GridData(GridData.FILL_BOTH);
		dialogLayoutData.widthHint = MIN_DIALOG_WIDTH;
		dialogLayoutData.heightHint = MIN_DIALOG_HEIGHT;
		dialogComposite.setLayoutData(dialogLayoutData);

		createGoalGroup(dialogComposite);

		loadData();
		
		return dialogComposite;
	}

	protected void loadData() {
		//Doc
		if (element.getDocumentation() != null)
			documentation.setText(element.getDocumentation());
		//Name
		if (element.getName() != null)
			name.setText(element.getName());
	}

	private void createGoalGroup(Composite parent) {
		TabFolder tabFolder = new TabFolder(parent, SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
		createDocumentationTab(tabFolder);
		createCustomItems(tabFolder);
	}

	private void createDocumentationTab(Composite parent) {
		// Create tab item and add it composite that fills it
		TabItem viewItem = new TabItem((TabFolder) parent, SWT.NONE);
		viewItem.setText("Properties");
		Composite composite = new Composite(parent, SWT.NONE);
		viewItem.setControl(composite);

		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label lbl = new Label(composite, SWT.NONE);
		lbl.setText("Name :");
		name = new Text(composite, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
		name.setLayoutData(new GridData(GridData.FILL_BOTH));

		lbl = new Label(composite, SWT.NONE);
		lbl.setText("Documentation :");
		documentation = new Text(composite, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
		documentation.setLayoutData(new GridData(GridData.FILL_BOTH));

	}
	
	/**
	 * Save the values before the widgets are disposed
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		if (data == null)
			data = new HashMap<String, Object>();
		data.put(BASICELEMENT_DOCUMENTATION, documentation.getText());
		data.put(BASICELEMENT_NAME, name.getText());
		super.okPressed();
	}

	public Map<String, Object> getData() {
		return data;
	}
	
	protected void createCustomItems(Composite parent) {
	}

}
