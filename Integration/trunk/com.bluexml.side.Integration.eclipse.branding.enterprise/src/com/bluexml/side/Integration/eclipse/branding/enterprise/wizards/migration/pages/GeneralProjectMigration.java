package com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.migration.pages;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.bluexml.side.util.alfresco.tools.AlfrescoModelBaseVersionChooser;

public class GeneralProjectMigration extends AlfrescoModelBaseVersionChooser {
	public final static String DEFAULT_VALUE_NEWNAME = "Copy-of-";
	protected String projectName = null;

	public GeneralProjectMigration(String projectName) {
		super("importBuildInLibrary");
		this.projectName = projectName;
		this.setTitle("Switch between Model Library");
		this.setDescription("Select the target Model Library");
	}

	public void createFieldsControls(Composite composite) {
		this.values.put(Fields.newName.toString(), DEFAULT_VALUE_NEWNAME + projectName);

		createAlfrescoLibComboBox(composite, Fields.library.toString());

		final Button createBooleanFieldControl = createBooleanFieldControl2(composite, "copy project", Fields.copybefore.toString(), true);

		final Text createTextFieldControl = createTextFieldControl(composite, "new project name", Fields.newName.toString());

		createBooleanFieldControl.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				boolean selection = createBooleanFieldControl.getSelection();
				createTextFieldControl.setEnabled(selection);
				if (!selection) {
					GeneralProjectMigration.this.values.put(Fields.newName.toString(), DEFAULT_VALUE_NEWNAME + projectName);
					checkPageComplite();
				} else {
					GeneralProjectMigration.this.values.put(Fields.newName.toString(), StringUtils.trimToNull(createTextFieldControl.getText()));
					checkPageComplite();
				}
			}
		});
	}

	public enum Fields {
		library,
		copybefore,
		newName;
	}
}
