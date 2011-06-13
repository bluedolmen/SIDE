package com.bluexml.side.util.libs.eclipse.wizards;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.bluexml.side.util.libs.eclipse.RessourcesSelection;
import com.bluexml.side.util.libs.eclipse.StylingUtil;
import com.bluexml.side.util.libs.eclipse.RessourcesSelection.RESOURCE_TYPE;

public abstract class AbstractFieldsPage extends WizardPage {

	protected AbstractFieldsPage(String pageName) {
		super(pageName);
	}

	public AbstractFieldsPage(String pageName, String title, ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}

	protected Map<String, String> values = new HashMap<String, String>();

	public String getFieldValue(String fieldId) {
		return values.get(fieldId);
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(StylingUtil.layout);
		setControl(composite);

		createFieldsControls(composite);
		setPageComplete(false);
	}

	protected abstract void createFieldsControls(Composite composite);

	/**
	 * @param composite
	 * @param label
	 * @param textField
	 */
	protected Text createTextFieldControl(Composite composite, final String label, final String id) {
		values.put(id, null);
		Label artifactIdLabel = new Label(composite, SWT.NONE);
		artifactIdLabel.setText(label);

		final Text textField = StylingUtil.getNewText(composite, null, null);
		textField.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				// records changed value into map values
				values.put(id, textField.getText());
				checkPageComplite();
			}
		});
		return textField;
	}

	protected Button createBooleanFieldControl(Composite composite, final String label, final String id, boolean initialValue) {
		values.put(id, Boolean.toString(initialValue));
		Label artifactIdLabel = new Label(composite, SWT.NONE);
		artifactIdLabel.setText(label);
		final Button button = new Button(composite, SWT.CHECK);
		button.setSelection(initialValue);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				values.put(id, Boolean.toString(button.getSelection()));
				checkPageComplite();
			}
		});
		return button;
	}

	/**
	 * @param composite
	 */
	protected void createComboControl(Composite composite, String label, final String id, List<String> allowedValues) {
		values.put(id, null);
		Label archetypeIdLabel = new Label(composite, SWT.NONE);
		archetypeIdLabel.setText(label);
		final Combo archetypeIdControl = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);

		// convert to String[]
		String[] items = allowedValues.toArray(new String[allowedValues.size()]);

		archetypeIdControl.setItems(items);
		GridData newLayoutData = StylingUtil.getNewLayoutData();
		newLayoutData.horizontalSpan = 3;
		archetypeIdControl.setLayoutData(newLayoutData);
		archetypeIdControl.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				values.put(id, archetypeIdControl.getItem(archetypeIdControl.getSelectionIndex()));
				checkPageComplite();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}

	private void checkPageComplite() {
		for (String s : values.values()) {
			if (s == null) {
				setPageComplete(false);
				return;
			}
		}
		setPageComplete(values.size() > 0);
	}

	protected void createResourceControl(Composite composite, final String label, final String id, RESOURCE_TYPE type) {
		final Text t = createTextFieldControl(composite, label, id);
		GridData gd = StylingUtil.getNewLayoutData();
		gd.horizontalSpan = StylingUtil.layout.numColumns - 2;
		t.setLayoutData(gd);

		// add button and setup events
		Button b = new Button(composite, SWT.PUSH);
		b.setText(StylingUtil.Messages.getString("AbstractFieldsPage.0"));
		SelectionListener listener_file;
		if (type.equals(RESOURCE_TYPE.RESOURCE_TYPE_DIRECTORY)) {
			listener_file = new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					try {
						RessourcesSelection.handleFileSystemDirecoryButtonSelected(getShell(), t);
					} catch (Throwable er) {
						er.printStackTrace();
					}
				}

				public void widgetDefaultSelected(SelectionEvent e) {
					System.out.println("AbstractFieldsPage.createRessourceControl(...).new SelectionListener() {...}.widgetDefaultSelected()"); //$NON-NLS-1$
				}
			};
		} else {
			listener_file = new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					RessourcesSelection.handleFileSystemLocationButtonSelected(getShell(), t);
				}

				public void widgetDefaultSelected(SelectionEvent e) {
				}
			};
		}
		b.addSelectionListener(listener_file);

	}
}
