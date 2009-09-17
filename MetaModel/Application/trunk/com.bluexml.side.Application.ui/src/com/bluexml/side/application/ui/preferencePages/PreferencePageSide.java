package com.bluexml.side.application.ui.preferencePages;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.bluexml.side.application.ui.Activator;

public class PreferencePageSide extends PreferencePage implements
		IWorkbenchPreferencePage {

	public PreferencePageSide() {
		noDefaultAndApplyButton();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());

		final Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(488, 368));
		label.setText(Activator.Messages.getString("PreferencePageSide.1")); //$NON-NLS-1$

		return composite;
	}
}