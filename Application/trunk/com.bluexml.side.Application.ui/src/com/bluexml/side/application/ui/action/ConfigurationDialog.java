package com.bluexml.side.application.ui.action;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ConfigurationDialog extends Dialog {

	private Text descriptionText;
	private Text nameText;
	
	private String _name;
	private String _description;
	
	/**
	 * Create the dialog
	 * @param parentShell
	 */
	public ConfigurationDialog(Shell parentShell, String name, String description) {
		super(parentShell);
		_name = "";
		_description = "";
		if (name != null)
			_name = name;
		if (description != null)
			_description = description;
	}

	/**
	 * Create contents of the dialog
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout());

		final Label nameLabel = new Label(container, SWT.NONE);
		nameLabel.setText("Name");

		nameText = new Text(container, SWT.BORDER);
		final GridData gd_nameText = new GridData(SWT.FILL, SWT.CENTER, true, false);
		nameText.setLayoutData(gd_nameText);
		nameText.setText(_name);

		final Label descriptionLabel = new Label(container, SWT.NONE);
		descriptionLabel.setText("Description");

		descriptionText = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP);
		final GridData gd_descriptionText = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_descriptionText.heightHint = 123;
		descriptionText.setLayoutData(gd_descriptionText);
		descriptionText.setText(_description);
		return container;
	}

	/**
	 * Create contents of the button bar
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(500, 300);
	}
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			_name = nameText.getText();
			_description = descriptionText.getText();
		}
		super.buttonPressed(buttonId);
	}
	
	public String getName() {
		return _name;
	}
	
	public String getDescription() {
		return _description;
	}

}
