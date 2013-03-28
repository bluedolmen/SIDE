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
