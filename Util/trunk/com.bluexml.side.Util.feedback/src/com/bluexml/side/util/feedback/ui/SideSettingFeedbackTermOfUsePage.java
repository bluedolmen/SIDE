/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.util.feedback.ui;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.bluexml.side.util.feedback.FeedbackActivator;

public class SideSettingFeedbackTermOfUsePage extends PreferencePage
	implements IWorkbenchPreferencePage {

	private Button acceptTermsButton;

	public SideSettingFeedbackTermOfUsePage() {
		noDefaultAndApplyButton();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		Browser browser = new Browser(composite, SWT.BORDER);
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
		browser.setLayoutData(layoutData);
		browser.setUrl(getTermsOfUseUrl());

		acceptTermsButton = new Button(composite, SWT.CHECK);
		acceptTermsButton.setText(Messages.SideTermsOfUsePage_0);
		GridData gridData = new GridData(SWT.BEGINNING, SWT.FILL, true, false);
		acceptTermsButton.setLayoutData(gridData);

		acceptTermsButton.setSelection(FeedbackActivator.getFeedbackTermOfUseAccepted());

		return composite;
	}

	@Override
	public boolean performOk() {
		FeedbackActivator.setFeedbackTermOfUseAccepted(acceptTermsButton.getSelection());
		return super.performOk();
	}

	public static String getTermsOfUseUrl() {
		URL terms = FileLocator.find(FeedbackActivator.getDefault().getBundle(), new Path("terms.html"), null); //$NON-NLS-1$
		try {
			return FileLocator.toFileURL(terms).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
