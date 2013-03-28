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
package com.bluexml.side.util.libs.eclipse.pages;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;

import com.bluexml.side.util.libs.eclipse.RessourcesSelection.RESOURCE_TYPE;
import com.bluexml.side.util.libs.eclipse.StylingUtil;
import com.bluexml.side.util.libs.eclipse.wizards.ValuesMap;

public abstract class AbstractFieldsPropertyPage extends PropertyPage implements CheckablePage {
	PageControlsHelper controlHelper = new PageControlsHelper(this);
	protected ValuesMap<String, Object> values = new ValuesMap<String, Object>();

	@Override
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(StylingUtil.layout);

		createFieldsControls(composite);
		return composite;
	}

	/**
	 * @param composite
	 * @param label
	 * @param textField
	 */
	protected Text createTextFieldControl(Composite composite, final String label, final String id) {
		return controlHelper.createTextFieldControl(composite, label, id, values);
	}

	protected Button createBooleanFieldControl(Composite composite, final String label, final String id, boolean initialValue) {
		return controlHelper.createBooleanFieldControl(composite, label, id, initialValue, values);
	}

	/**
	 * @param composite
	 * @return
	 */
	protected Object[] createComboControl(Composite composite, String label, final String id, Map<String, Object> allowedValues) {
		return controlHelper.createComboControl(composite, label, id, allowedValues, values);
	}

	public void checkPageComplite() {

	}

	protected void createResourceControl(Composite composite, final String label, final String id, RESOURCE_TYPE type) {
		controlHelper.createResourceControl(composite, label, id, type, values);
	}
}
