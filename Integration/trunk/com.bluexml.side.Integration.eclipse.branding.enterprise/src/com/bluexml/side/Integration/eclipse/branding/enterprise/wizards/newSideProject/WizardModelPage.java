package com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.newSideProject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.bluexml.side.Integration.eclipse.branding.enterprise.Activator;
import com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.newSideProject.NewSIDEProjectCreator.CreateFields;
import com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.newSideProject.NewSIDEProjectCreator.CreateOptions;
import com.bluexml.side.clazz.alfresco.reverse.action.wizard.AlfrescoModelBaseVersionChooser;
import com.bluexml.side.util.libs.eclipse.RessourcesSelection.RESOURCE_TYPE;
import com.bluexml.side.util.libs.eclipse.StylingUtil;

public class WizardModelPage extends AlfrescoModelBaseVersionChooser {

	private static final String ID = Activator.Messages.getString("Wizard.8");


	public WizardModelPage() {
		super(ID);

	}

	public void createFieldsControls(Composite composite) {

		// set initials values (used to manage mandatory values too
		values.put(CreateFields.TECHNOLOGY.toString(), "");
		values.put(CreateFields.ALFRESCO_HOME.toString(), "");
		values.put(CreateFields.BASE_DATA_MODEL_PACKAGES.toString(), "");

		// technology, alfresco home
		createAlfrescoLibComboBox(composite, CreateFields.TECHNOLOGY.toString());
		
		createResourceControl(composite, "tomcat home", CreateFields.ALFRESCO_HOME.toString(), RESOURCE_TYPE.RESOURCE_TYPE_DIRECTORY);

		// models
		Composite boxComposite = new Composite(composite, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;

		boxComposite.setLayout(layout);
		GridData newLayoutData = StylingUtil.getNewLayoutData();
		newLayoutData.horizontalSpan = 4;

		boxComposite.setLayoutData(newLayoutData);
		final Label chooseInitialModelLabel = new Label(boxComposite, SWT.NONE);
		GridData layoutData = new GridData();
		layoutData.horizontalSpan = 3;

		chooseInitialModelLabel.setLayoutData(layoutData);
		chooseInitialModelLabel.setToolTipText(Activator.Messages.getString("WizardModelOptionsPage.0")); //$NON-NLS-1$
		chooseInitialModelLabel.setText(Activator.Messages.getString("WizardModelOptionsPage.1")); //$NON-NLS-1$

		Composite dataC = StylingUtil.getDefaultComposite(boxComposite);

		createBooleanFieldControl(dataC, "data", CreateFields.DO_CREATE_DATA_MODEL.toString(), true);

		Composite formC = StylingUtil.getDefaultComposite(boxComposite);
		createBooleanFieldControl(formC, "form", CreateFields.DO_CREATE_FORM_MODEL.toString(), false);

		Composite portalC = StylingUtil.getDefaultComposite(boxComposite);
		createBooleanFieldControl(portalC, "portal", CreateFields.DO_CREATE_PORTAL_MODEL.toString(), false);

		Composite requirementC = StylingUtil.getDefaultComposite(boxComposite);
		createBooleanFieldControl(requirementC, "requirement", CreateFields.DO_CREATE_REQUIREMENT_MODEL.toString(), false);

		Composite viewC = StylingUtil.getDefaultComposite(boxComposite);
		createBooleanFieldControl(viewC, "view", CreateFields.DO_CREATE_VIEW_MODEL.toString(), false);

		Composite workflowC = StylingUtil.getDefaultComposite(boxComposite);
		createBooleanFieldControl(workflowC, "workflow", CreateFields.DO_CREATE_WORKFLOW_MODEL.toString(), false);

		// modelName
		createTextFieldControl(composite, "model id", CreateFields.BASE_MODEL_NAME.toString());

		// package
		createTextFieldControl(composite, "package", CreateFields.BASE_DATA_MODEL_PACKAGES.toString());

	}

	public String getStringPath() {
		return getFieldValueString(CreateFields.BASE_DATA_MODEL_PACKAGES.toString());
	}

	public String getModelNameValue() {
		return getFieldValueString(CreateFields.BASE_MODEL_NAME.toString());
	}

	public boolean isCreateDataModel() {
		return Boolean.parseBoolean(getFieldValueString(CreateFields.DO_CREATE_DATA_MODEL.toString()));
	}

	public boolean isCreateFormModel() {
		return getFieldValueBoolean(CreateFields.DO_CREATE_FORM_MODEL.toString());
	}

	public boolean isCreateWorkflowModel() {
		return getFieldValueBoolean(CreateFields.DO_CREATE_WORKFLOW_MODEL.toString());
	}

	public boolean isCreatePortalModel() {
		return getFieldValueBoolean(CreateFields.DO_CREATE_PORTAL_MODEL.toString());
	}

	public boolean isCreateViewModel() {
		return getFieldValueBoolean(CreateFields.DO_CREATE_VIEW_MODEL.toString());
	}

	public boolean isCreateRequirementModel() {
		return getFieldValueBoolean(CreateFields.DO_CREATE_REQUIREMENT_MODEL.toString());
	}
	
	public CreateOptions toOptions() {
		CreateOptions options = new CreateOptions();
		for (CreateFields field : CreateFields.values()) {
			String value = getFieldValueString(field.toString());
			options.setValue(field, value);
		}
		return options;
	}
}
