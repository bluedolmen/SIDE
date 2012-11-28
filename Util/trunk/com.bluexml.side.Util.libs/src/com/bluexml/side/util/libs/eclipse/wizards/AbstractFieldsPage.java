package com.bluexml.side.util.libs.eclipse.wizards;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.bluexml.side.util.libs.eclipse.StylingUtil;
import com.bluexml.side.util.libs.eclipse.RessourcesSelection.RESOURCE_TYPE;
import com.bluexml.side.util.libs.eclipse.pages.CheckablePage;
import com.bluexml.side.util.libs.eclipse.pages.PageControlsHelper;

public abstract class AbstractFieldsPage extends WizardPage implements CheckablePage {
	PageControlsHelper controlHelper;
	protected Map<String, Object> values = new HashMap<String, Object>();

	protected AbstractFieldsPage(String pageName) {
		super(pageName);
		controlHelper = new PageControlsHelper(this);
	}

	public AbstractFieldsPage(String pageName, String title, ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}

	public Object getFieldValue(String fieldId) {
		return values.get(fieldId);
	}
	

	public String getFieldValueString(String fieldId) {
		Object object = values.get(fieldId);
		if (object != null) {
			return object.toString();
		}
		return null;
	}
	
	public Boolean getFieldValueBoolean(String fieldId) {
		Object object = values.get(fieldId);
		if (object != null) {
			return Boolean.parseBoolean(object.toString());
		}
		return null;
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(StylingUtil.layout);
		
		setControl(composite);

		createFieldsControls(composite);
		setPageComplete(false);
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
	
	protected Button createBooleanFieldControl2(Composite composite, final String label, final String id, boolean initialValue) {
		return controlHelper.createBooleanFieldControl2(composite, label, id, initialValue, values);
	}

	/**
	 * @param composite
	 */
	protected void createComboControl(Composite composite, String label, final String id, Map<String, Object> allowedValues) {
		controlHelper.createComboControl(composite, label, id, allowedValues, values);
	}

	public void checkPageComplite() {
		for (Object s : values.values()) {
			if (s == null) {
				setPageComplete(false);
				return;
			}
		}
		setPageComplete(values.size() > 0);
	}

	protected void createResourceControl(Composite composite, final String label, final String id, RESOURCE_TYPE type) {
		controlHelper.createResourceControl(composite, label, id, type, values);
	}

	protected Composite createResourcesControl(Composite composite, final String label, final String id, RESOURCE_TYPE type, Object root, String fileExtFilter) {
		return controlHelper.createResourcesControl(composite, label, id, type, values, root, fileExtFilter);
	}
}
