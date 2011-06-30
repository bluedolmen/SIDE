package com.bluexml.side.util.libs.eclipse.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.bluexml.side.util.libs.eclipse.RessourcesSelection;
import com.bluexml.side.util.libs.eclipse.StylingUtil;
import com.bluexml.side.util.libs.eclipse.RessourcesSelection.RESOURCE_TYPE;

public class PageControlsHelper {
	CheckablePage page;

	public PageControlsHelper(CheckablePage page) {
		this.page = page;
	}

	/**
	 * @param composite
	 * @param label
	 * @param textField
	 */
	public Text createTextFieldControl(Composite composite, final String label, final String id, final Map<String, Object> values) {
		if (!values.containsKey(id)) {
			values.put(id, null);
		}

		Label artifactIdLabel = new Label(composite, SWT.NONE);
		artifactIdLabel.setText(label);

		final Text textField = StylingUtil.getNewText(composite, null, null);
		Object string = values.get(id);
		if (string != null) {
			textField.setText(string.toString());
		}
		textField.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				// records changed value into map values
				values.put(id, textField.getText());
				page.checkPageComplite();
			}
		});
		return textField;
	}

	public Button createBooleanFieldControl(Composite composite, final String label, final String id, boolean initialValue, final Map<String, Object> values) {
		values.put(id, Boolean.toString(initialValue));
		Label artifactIdLabel = new Label(composite, SWT.NONE);
		artifactIdLabel.setText(label);
		final Button button = new Button(composite, SWT.CHECK);
		button.setSelection(initialValue);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				values.put(id, Boolean.toString(button.getSelection()));
				page.checkPageComplite();
			}
		});
		return button;
	}

	/**
	 * @param composite
	 * @param values
	 */
	public void createComboControl(Composite composite, String label, final String id, List<String> allowedValues, final Map<String, Object> values) {
		if (!values.containsKey(id)) {
			values.put(id, null);
		}
		Label archetypeIdLabel = new Label(composite, SWT.NONE);
		archetypeIdLabel.setText(label);
		final Combo archetypeIdControl = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);

		// convert to String[]
		String[] items = allowedValues.toArray(new String[allowedValues.size()]);

		archetypeIdControl.setItems(items);
		GridData newLayoutData = StylingUtil.getNewLayoutData();
		newLayoutData.horizontalSpan = 3;
		archetypeIdControl.setLayoutData(newLayoutData);
		Object string = values.get(id);
		if (string != null) {
			archetypeIdControl.select(allowedValues.indexOf(string));
		}
		archetypeIdControl.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				values.put(id, archetypeIdControl.getItem(archetypeIdControl.getSelectionIndex()));
				page.checkPageComplite();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}

	
	public void createResourceControl(final Composite composite, final String label, final String id, RESOURCE_TYPE type, final Map<String, Object> values) {
//		final Composite composite_2 = new Composite(composite, SWT.NONE);
//		composite_2.setLayout(StylingUtil.getLayout());
		final Text t = createTextFieldControl(composite, label, id, values);
		GridData gd = StylingUtil.getNewLayoutData();
		gd.horizontalSpan = 2;
		t.setLayoutData(gd);

		// add button and setup events
		Button b = new Button(composite, SWT.PUSH);
		b.setText(StylingUtil.Messages.getString("AbstractFieldsPage.0"));
		SelectionListener listener_file = null;
		if (type.equals(RESOURCE_TYPE.RESOURCE_TYPE_DIRECTORY)) {
			listener_file = new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					try {
						RessourcesSelection.handleFileSystemDirecoryButtonSelected(composite.getShell(), t);
					} catch (Throwable er) {
						er.printStackTrace();
					}
				}

				public void widgetDefaultSelected(SelectionEvent e) {
					System.out.println("AbstractFieldsPage.createRessourceControl(...).new SelectionListener() {...}.widgetDefaultSelected()"); //$NON-NLS-1$
				}
			};
		} else if (type.equals(RESOURCE_TYPE.RESOURCE_TYPE_FILE)) {
			listener_file = new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					RessourcesSelection.handleFileSystemLocationButtonSelected(composite.getShell(), t);
				}

				public void widgetDefaultSelected(SelectionEvent e) {
				}
			};
		} else {

		}
		b.addSelectionListener(listener_file);
//		Label l = new Label(composite, SWT.NONE);
	}
	
	public void createResourcesControl(final Composite composite, final String label, final String id, RESOURCE_TYPE type, final Map<String, Object> values, final Object root, final String fileExtFilter) {
		if (!values.containsKey(id)) {
			values.put(id, null);
		}
		Composite composite_2 = new Composite(composite, SWT.NONE);
		//		composite_2.setSize(771, 65);
		composite_2.setLayout(new GridLayout(4, false));

		final org.eclipse.swt.widgets.List modelList = new org.eclipse.swt.widgets.List(composite_2, SWT.BORDER | SWT.V_SCROLL);
		GridData gd_list = new GridData(SWT.FILL, SWT.TOP, true, false, 1, 2);
		gd_list.heightHint = 100;
		gd_list.minimumWidth = 100;
		modelList.setLayoutData(gd_list);

		Button btnAdd = new Button(composite_2, SWT.NONE);
		btnAdd.setText("add");

		Composite composite_3 = new Composite(composite_2, SWT.NONE);
		composite_3.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2));

		Composite composite_1 = new Composite(composite_2, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 2));

		Button btnRemove = new Button(composite_2, SWT.NONE);
		btnRemove.setText("remove");

		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);

		SelectionListener listener = new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				String title = "";
				String message = "";
				ViewerFilter filter = new ViewerFilter() {
					@Override
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						if (element instanceof org.eclipse.core.resources.IFile) {
							IFile file = (IFile) element;
							String fext = file.getFileExtension();
							if (fext.equals(fileExtFilter)) {
								return true;
							}
						} else {
							return true;
						}
						return false;
					}
				};
				Map<String, IResource> handleMultiIFileSelection = RessourcesSelection.handleWorkspaceMultiIResourceSelection(title, message, filter, root);
				for (Map.Entry<String, IResource> ent : handleMultiIFileSelection.entrySet()) {
					String key = ent.getKey();

					String[] items = modelList.getItems();
					boolean newItem = true;
					for (String string : items) {
						if (string.equals(key)) {
							newItem = false;
							break;
						}
					}
					if (newItem) {
						if (modelList.getItemCount() == 0) {
							values.put(id, new ArrayList<IResource>());
						}
						modelList.add(key);
						List<IResource> object = (List<IResource>) values.get(id);
						object.add(ent.getValue());
						page.checkPageComplite();
					}

				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		};

		SelectionAdapter listener2 = new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				int select = modelList.getSelectionIndex();
				if (select != -1) {
					modelList.remove(select);
				}

			}

		};
		btnAdd.addSelectionListener(listener);
		btnRemove.addSelectionListener(listener2);

	}

	
}
