package com.bluexml.side.util.feedback.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class TermOfUsePopUp extends Dialog {


	protected TermOfUsePopUp(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog
	 *
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);
		Browser browser = new Browser(composite, SWT.BORDER);
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
		layoutData.heightHint = 307;
		layoutData.widthHint = 351;
		browser.setLayoutData(layoutData);
		browser.setUrl(SideSettingFeedbackTermOfUsePage.getTermsOfUseUrl());
		return composite;
	}
}
