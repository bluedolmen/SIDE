package com.bluexml.side.util.libs.eclipse.wizards;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.bluexml.side.util.libs.eclipse.StylingUtil;
import com.bluexml.side.util.libs.eclipse.RessourcesSelection.RESOURCE_TYPE;
import com.bluexml.side.util.libs.eclipse.pages.CheckablePage;
import com.bluexml.side.util.libs.eclipse.pages.PageControlsHelper;

public abstract class AbstractFieldsPreferencePage extends PreferencePage implements CheckablePage {
	PageControlsHelper controlHelper = new PageControlsHelper(this);
	protected Map<String, Object> values = new HashMap<String, Object>();

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
	 */
	protected void createComboControl(Composite composite, String label, final String id, List<String> allowedValues) {
		controlHelper.createComboControl(composite, label, id, allowedValues, values);
	}

	public void checkPageComplite() {

	}

	protected void createResourceControl(Composite composite, final String label, final String id, RESOURCE_TYPE type) {
		controlHelper.createResourceControl(composite, label, id, type, values);
	}
}
