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
package com.bluexml.side.clazz.edit.ui.actions.initializer.creator;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.ClazzFactory;
import com.bluexml.side.clazz.Model;
import com.bluexml.side.clazz.edit.ui.actions.initializer.InitializerRegister;

public class ClassModelCreator extends ModelAndDiagramCreator {
	private static final String ClASS_DIARGAM_ID = "com.bluexml.side.Class.modeler.diagram";
	private static final String Class_EDITOR_ID = ModelInitializationUtils.getExtensionForEditorId("com.bluexml.side.clazz.presentation.ClazzEditorID"); //$NON-NLS-1$
	protected String packages;

	public ClassModelCreator(IProject project, InitializerRegister register, ASK_USER ask, String modelFileName, String packages) throws IOException {
		super(project, Class_EDITOR_ID, "data", register, ask, modelFileName, ClASS_DIARGAM_ID);
		this.packages = packages;
	}

	@Override
	protected void createRootObject() {
		Model createModel = ClazzFactory.eINSTANCE.createModel();
		createModel.setName(getModelName());
		newRootObject = createModel;
	}

	@Override
	protected void headLessInitialize() throws Exception {
		// create packages		
		com.bluexml.side.common.Package parent = (Model) newRootObject;
		if (StringUtils.trimToNull(packages) != null) {
			String[] split = packages.split("/");
			for (String string : split) {
				ClassPackage createClassPackage = ClazzFactory.eINSTANCE.createClassPackage();
				createClassPackage.setName(string);
				parent.getPackageSet().add(createClassPackage);
				parent = createClassPackage;
			}
		}

		rootDiagram = parent;
	}

	@Override
	protected Command initialize(EditingDomain editingDomain) throws Exception {
		CompoundCommand cc = new CompoundCommand();
		headLessInitialize();
		return cc;
	}

}
