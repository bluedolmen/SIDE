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

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.clazz.edit.ui.actions.initializer.InitializerRegister;
import com.bluexml.side.workflow.EndState;
import com.bluexml.side.workflow.Process;
import com.bluexml.side.workflow.StartState;
import com.bluexml.side.workflow.Swimlane;
import com.bluexml.side.workflow.WorkflowFactory;

public class WorkflowModelCreator extends ModelAndDiagramCreator {
	private static final String WORKFLOW_DIAGRAM_ID = "com.bluexml.side.Workflow.modeler.diagram";
	private static final WorkflowFactory FACTORY = WorkflowFactory.eINSTANCE;
	private static final String WORKFLOW_EDITOR_ID = ModelInitializationUtils.getExtensionForEditorId("com.bluexml.side.workflow.presentation.WorkflowEditorID"); //$NON-NLS-1$

	public WorkflowModelCreator(IProject project, InitializerRegister register, ASK_USER ask, String formModelFileName) throws IOException {		
		super(project, WORKFLOW_EDITOR_ID, "workflow", register, ask, formModelFileName,WORKFLOW_DIAGRAM_ID);
	}

	@Override
	protected void createRootObject() {
		Process createProcess = FACTORY.createProcess();
		createProcess.setName(getModelName());
		newRootObject = createProcess;
	}

	@Override
	protected void headLessInitialize() throws Exception {
		Process root = (Process) newRootObject;

		Swimlane createSwimlane = FACTORY.createSwimlane();
		createSwimlane.setName("Initiator");
		createSwimlane.setActorid("initiator");
		root.getSwimlane().add(createSwimlane);

		StartState createStartState = FACTORY.createStartState();
		createStartState.setName("start");
		createStartState.setInitiator(createSwimlane);
		root.setStartstate(createStartState);

		EndState createEndState = FACTORY.createEndState();
		createEndState.setName("end");
		root.getEndstate().add(createEndState);

	}

	@Override
	protected Command initialize(EditingDomain editingDomain) throws Exception {
		headLessInitialize();
		return new CompoundCommand();
	}

}
