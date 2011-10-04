package com.bluexml.side.Integration.eclipse.branding.wizard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.bluexml.side.Integration.eclipse.branding.Activator;
import com.bluexml.side.Integration.eclipse.branding.Wizard;

public class WizardModelOptionsPage extends WizardPage {

	boolean createDataModel, createFormModel, createWorkflowModel, createPortalModel, createViewModel, createRequirementModel;

	public String getStringPath() {
		return stringPath;
	}

	public void setStringPath(String stringPath) {
		this.stringPath = stringPath;
	}

	public boolean isCreateDataModel() {
		return createDataModel;
	}

	public boolean isCreateFormModel() {
		return createFormModel;
	}

	public boolean isCreateWorkflowModel() {
		return createWorkflowModel;
	}

	public boolean isCreatePortalModel() {
		return createPortalModel;
	}

	public boolean isCreateViewModel() {
		return createViewModel;
	}

	public boolean isCreateRequirementModel() {
		return createRequirementModel;
	}

	protected String stringPath;
	protected String modelName;
	private Text pathText;
	private Text modelNameText;

	public WizardModelOptionsPage(String pageName) {
		super(pageName);
	    setPageComplete(true);
	}

	public void createControl(Composite parent) {
		 Composite composite = new Composite(parent, SWT.NULL);
		 composite.setLayout(new GridLayout());
	     composite.setLayoutData(new GridData(GridData.FILL_BOTH));

	     // Show description on opening
	     setErrorMessage(null);
	     setMessage(null);
	     setControl(composite);
	     Dialog.applyDialogFont(composite);

		 final Label chooseInitialModelLabel = new Label(composite, SWT.NONE);
		 chooseInitialModelLabel.setLayoutData(new GridData(204, SWT.DEFAULT));
		 chooseInitialModelLabel.setToolTipText(Activator.Messages.getString("WizardModelOptionsPage.0")); //$NON-NLS-1$
		 chooseInitialModelLabel.setText(Activator.Messages.getString("WizardModelOptionsPage.1")); //$NON-NLS-1$

		 final Button dataModelButton = new Button(composite, SWT.CHECK);
		 dataModelButton.addSelectionListener(new SelectionAdapter() {
		 	public void widgetSelected(final SelectionEvent e) {
		 		createDataModel = dataModelButton.getSelection();
		 	}
		 });
		 dataModelButton.setText(Activator.Messages.getString("WizardModelOptionsPage.2")); //$NON-NLS-1$

		 final Button formModelButton = new Button(composite, SWT.CHECK);
		 formModelButton.addSelectionListener(new SelectionAdapter() {
			 	public void widgetSelected(final SelectionEvent e) {
			 		createFormModel = formModelButton.getSelection();
			 	}
			 });
		 formModelButton.setText(Activator.Messages.getString("WizardModelOptionsPage.3")); //$NON-NLS-1$

		 final Button workflowModelButton = new Button(composite, SWT.CHECK);
		 workflowModelButton.addSelectionListener(new SelectionAdapter() {
			 	public void widgetSelected(final SelectionEvent e) {
			 		createWorkflowModel = workflowModelButton.getSelection();
			 	}
			 });
		 workflowModelButton.setText(Activator.Messages.getString("WizardModelOptionsPage.4")); //$NON-NLS-1$

		 final Button portalModelButton = new Button(composite, SWT.CHECK);
		 portalModelButton.addSelectionListener(new SelectionAdapter() {
			 	public void widgetSelected(final SelectionEvent e) {
			 		createPortalModel = portalModelButton.getSelection();
			 	}
			 });
		 portalModelButton.setText(Activator.Messages.getString("WizardModelOptionsPage.5")); //$NON-NLS-1$

		 final Button viewModelButton = new Button(composite, SWT.CHECK);
		 viewModelButton.addSelectionListener(new SelectionAdapter() {
			 	public void widgetSelected(final SelectionEvent e) {
			 		createViewModel = viewModelButton.getSelection();
			 	}
			 });
		 viewModelButton.setText(Activator.Messages.getString("WizardModelOptionsPage.6")); //$NON-NLS-1$

		 final Button requirementModelButton = new Button(composite, SWT.CHECK);
		 requirementModelButton.addSelectionListener(new SelectionAdapter() {
			 	public void widgetSelected(final SelectionEvent e) {
			 		createRequirementModel = requirementModelButton.getSelection();
			 	}
			 });
		 requirementModelButton.setText(Activator.Messages.getString("WizardModelOptionsPage.7")); //$NON-NLS-1$

		 final Label modelNameLabel = new Label(composite, SWT.NONE);
		 modelNameLabel.setText(Activator.Messages.getString("WizardModelOptionsPage.10")); //$NON-NLS-1$

		 modelNameText = new Text(composite, SWT.BORDER);
		 final GridData gd_modelNameText = new GridData(SWT.FILL, SWT.CENTER, true, false);
		 modelNameText.setLayoutData(gd_modelNameText);
		 modelNameText.addFocusListener(new FocusAdapter() {
		 	public void focusLost(final FocusEvent e) {
		 		modelName = getModelNameValue();
		 	}
		 });

		 modelNameText.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					validatePage();
				}
			});
		 modelNameText.setText(Wizard.DEFAULT_MODEL_NAME);

		 final Label forClassModelLabel = new Label(composite, SWT.NONE);
		 forClassModelLabel.setText(Activator.Messages.getString("WizardModelOptionsPage.8")); //$NON-NLS-1$

		 pathText = new Text(composite, SWT.BORDER);
		 pathText.addFocusListener(new FocusAdapter() {
		 	public void focusLost(final FocusEvent e) {
		 		stringPath = getPackagePathValue();
		 	}
		 });

		 pathText.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					validatePage();
				}
			});
		 pathText.setTextLimit(300);

		 final GridData gd_pathText = new GridData(SWT.FILL, SWT.CENTER, true, false);
		 gd_pathText.widthHint = 248;
		 pathText.setLayoutData(gd_pathText);

		 setPageComplete(validatePage());
	}

	protected boolean validatePage() {
		String name = getModelNameValue();
        Pattern p = Pattern.compile("\\w+"); //$NON-NLS-1$
		Matcher m = p.matcher(name);
		boolean matchFound = m.matches();
		if (!matchFound) {
			setErrorMessage(Activator.Messages.getString("WizardModelOptionsPage.12")); //$NON-NLS-1$
			setPageComplete(false);
			return false;
		}


        String path = getPackagePathValue();
        Pattern p2 = Pattern.compile("((\\w+/)*\\w+)?"); //$NON-NLS-1$
		Matcher m2 = p2.matcher(path);
		boolean matchFound2 = m2.matches();
		if (!matchFound2) {
			setErrorMessage(Activator.Messages.getString("WizardModelOptionsPage.9")); //$NON-NLS-1$
			setPageComplete(false);
			return false;
		}

        setErrorMessage(null);
        setMessage(null);
        setPageComplete(true);
        return true;
    }

	public String getModelNameValue() {
		if (modelNameText == null) {
			return ""; //$NON-NLS-1$
		}
		return modelNameText.getText();
	}

	public String getPackagePathValue() {
		if (pathText == null) {
			return ""; //$NON-NLS-1$
		}
		return pathText.getText();
	}

}
