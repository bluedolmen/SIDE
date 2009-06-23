package com.bluexml.side.Requirements.modeler.goalDiagram.dialogs;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.bluexml.side.requirements.Entity;

public class EntityDialog extends Dialog implements IDialogConstants {

	private static final int MIN_DIALOG_WIDTH = 500;
	private static final int MIN_DIALOG_HEIGHT = 300;
	private Text documentation;
	private Map<String,Object> data;
	private Entity entity;

	public static final String ENTITY_DOCUMENTATION = "entity documentation";

	public EntityDialog(Shell parent, Entity _entity) {
		super(parent);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		entity = _entity;
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

	private void loadData() {
		//Doc
		if (entity.getDocumentation() != null)
			documentation.setText(entity.getDocumentation());
	}

	private void createGoalGroup(Composite parent) {
		TabFolder tabFolder = new TabFolder(parent, SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
		createDocumentationTab(tabFolder);
	}

	private void createDocumentationTab(Composite parent) {
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
	
	/**
	 * Save the values before the widgets are disposed
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		data = new HashMap<String, Object>();
		data.put(ENTITY_DOCUMENTATION, documentation.getText());
		super.okPressed();
	}

	public Map<String, Object> getData() {
		return data;
	}

	
}
