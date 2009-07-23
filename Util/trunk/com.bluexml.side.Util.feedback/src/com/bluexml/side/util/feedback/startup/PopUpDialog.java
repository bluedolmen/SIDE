package com.bluexml.side.util.feedback.startup;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class PopUpDialog extends Dialog {

	protected PopUpDialog(Shell parentShell) {
		super(parentShell);

	}

	/**
	 * Create contents of the dialog
	 *
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(null);

		return container;
	}

}
