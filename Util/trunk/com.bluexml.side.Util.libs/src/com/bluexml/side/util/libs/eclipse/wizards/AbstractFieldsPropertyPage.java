package com.bluexml.side.util.libs.eclipse.wizards;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;

import com.bluexml.side.util.libs.eclipse.StylingUtil;
import com.bluexml.side.util.libs.eclipse.RessourcesSelection.RESOURCE_TYPE;
import com.bluexml.side.util.libs.eclipse.pages.CheckablePage;
import com.bluexml.side.util.libs.eclipse.pages.PageControlsHelper;

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
	protected Object[] createComboControl(Composite composite, String label, final String id, List<String> allowedValues) {
		return controlHelper.createComboControl(composite, label, id, allowedValues, values);
	}

	public void checkPageComplite() {

	}

	protected void createResourceControl(Composite composite, final String label, final String id, RESOURCE_TYPE type) {
		controlHelper.createResourceControl(composite, label, id, type, values);
	}
}
