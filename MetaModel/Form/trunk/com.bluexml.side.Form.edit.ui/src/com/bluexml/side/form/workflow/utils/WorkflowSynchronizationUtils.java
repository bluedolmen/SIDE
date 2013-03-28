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
package com.bluexml.side.form.workflow.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.FormWorkflow;
import com.bluexml.side.form.WorkflowFormCollection;
import com.bluexml.side.form.common.utils.FormDiagramUtils;
import com.bluexml.side.form.common.utils.InternalModification;
import com.bluexml.side.workflow.Attribute;
import com.bluexml.side.workflow.Process;
import com.bluexml.side.workflow.StartState;
import com.bluexml.side.workflow.State;
import com.bluexml.side.workflow.TaskNode;
import com.bluexml.side.workflow.Transition;
import com.bluexml.side.workflow.UserTask;

public class WorkflowSynchronizationUtils {

	protected static CompoundCommand cc;
	private static WorkflowFormCollection fc;
	private static Map<String, FormWorkflow> formList;
	private static EditingDomain domain;
	private static HashMap<FormWorkflow, Map<String, FormElement>> attributeList;

	/**
	 * Synchronize a process with a Workflow Form Collection
	 * 
	 * @param fw
	 * @param domain
	 * @return
	 */
	public static Command synchronizeProcess(WorkflowFormCollection p_fc, EditingDomain p_domain) {
		cc = new CompoundCommand();
		fc = p_fc;
		domain = p_domain;
		formList = null;
		attributeList = new HashMap<FormWorkflow, Map<String, FormElement>>();
		ArrayList<String> taskList = new ArrayList<String>();

		InternalModification.dontMoveToDisabled();

		Process p = fc.getLinked_process();
		StartState st = p.getStartstate();
		Command c = null;
		taskList.add(st.getName());

		// We manage all add and update
		c = synchronizeTask(p, st);
		if (c != null && c.canExecute()) {
			cc.append(c);
		}

		for (TaskNode t : p.getTasknode()) {
			c = null;
			c = synchronizeTask(p, t);
			taskList.add(t.getName());
			if (c != null && c.canExecute()) {
				cc.append(c);
			}
		}

		// We manage all task delete
		for (FormContainer f : fc.getForms()) {
			String key = "";
			if (f.getId() != null && f.getId().length() > p.getName().length() + 1) {
				key = f.getId().substring(p.getName().length() + 1);
			}
			if (!taskList.contains(key)) {
				Command cmd = RemoveCommand.create(domain, f);
				if (cmd.canExecute()) {
					cc.append(cmd);
				}
			}
		}

		InternalModification.moveToDisabled();
		return cc;
	}

	/**
	 * Will synchronize the given task with the form
	 * 
	 * @param p
	 * @param st
	 * @return
	 */
	private static Command synchronizeTask(Process p, UserTask st) {
		Command c = null;
		if (formList == null) {
			buildFormsList();
		}
		// Does the form exists for this task?
		String key = p.getName() + "_" + st.getName();
		if (formList.containsKey(key)) {
			FormWorkflow f = formList.get(key);
			c = new CompoundCommand();
			// If yes, we check for modification
			Clazz advancedTaskDefinition = st.getAdvancedTaskDefinition();
			if (advancedTaskDefinition != null) {
				// get Attributes from Class
				EList<com.bluexml.side.clazz.Attribute> allAttributes = advancedTaskDefinition.getAllAttributes();

			} else {

				ArrayList<String> attList = new ArrayList<String>();
				for (Attribute a : st.getAttributes()) {
					Command attCmd = synchronizeAttribute(a, f);
					if (attCmd != null && attCmd.canExecute()) {
						cc.append(attCmd);
					}
					attList.add(a.getName());
				}
				// Same with transition :
				for (Transition t : st.getTransition()) {
					Command tranCmd = synchronizeTransition(t, f);
					if (tranCmd != null && tranCmd.canExecute()) {
						cc.append(tranCmd);
					}
					attList.add(t.getName());
				}

				// Same with class link :
				for (Clazz cl : st.getClazz()) {
					Command clCmd = synchronizeClazzLink(cl, f);
					if (clCmd != null && clCmd.canExecute()) {
						cc.append(clCmd);
					}
					attList.add(cl.getName());
				}

				// We decide to keep all attributes contained by user task
				for (FormElement fe : f.getFields()) {
					boolean deleteIfnoRefExceptIfSave = (fe.getRef() == null) && (fe instanceof Field) && (!fe.getId().equals("wrkflw_save"));

					if (deleteIfnoRefExceptIfSave || ((fe.getRef() instanceof Attribute) && !(((State) ((Attribute) fe.getRef()).eContainer()).eContainer().equals(p)))) {

						Command cmd = DeleteCommand.create(domain, fe);
						if (cmd.canExecute()) {
							cc.append(cmd);
						}
					}
				}
			}
		} else {
			// If no, we add it
			FormWorkflow fw = WorkflowInitialization.createTaskForForm(st);
			c = AddCommand.create(domain, fc, FormPackage.eINSTANCE.getFormCollection_Forms(), fw);
		}
		return c;
	}

	/**
	 * Will synchronize the given attribute with the field
	 * 
	 * @param a
	 * @param f
	 * @return
	 */
	private static Command synchronizeAttribute(Attribute a, FormWorkflow f) {
		Command c = null;
		if (!attributeList.containsKey(f)) {
			buildAttributesList(f);
		}
		// Does the attribute exists in form?
		FormElement formElement = null;
		for (FormElement fe : f.getFields()) {
			if (fe.getRef() != null && fe.getRef().equals(a)) {
				formElement = fe;
			}
		}
		if (formElement != null) {
			//Change id
			if (formElement.getId() != null && !(formElement.getId().equals(a.getName()))) {
				c = SetCommand.create(domain, formElement, FormPackage.eINSTANCE.getFormElement_Id(), a.getName());
			}
		} else {
			Field fi = WorkflowDiagramUtils.getFieldForAttribute(a);
			c = AddCommand.create(domain, f, FormPackage.eINSTANCE.getFormGroup_Children(), fi);
		}
		return c;
	}

	/**
	 * Will synchronize the given transition with the field
	 * 
	 * @param t
	 * @param f
	 * @return
	 */
	private static Command synchronizeTransition(Transition t, FormWorkflow f) {
		Command c = null;
		if (!attributeList.containsKey(f)) {
			buildAttributesList(f);
		}
		Map<String, FormElement> attList = attributeList.get(f);
		// Does the attribute exists in form?
		if (!attList.containsKey(t.getName())) {
			Field fi = WorkflowDiagramUtils.getOperationForTransition(t);
			c = AddCommand.create(domain, f, FormPackage.eINSTANCE.getFormGroup_Children(), fi);
		}
		return c;
	}

	/**
	 * Will synchronize the given clazz link with the model choice field
	 * 
	 * @param cl
	 * @param f
	 * @return
	 */
	private static Command synchronizeClazzLink(Clazz cl, FormWorkflow f) {
		Command c = null;
		if (!attributeList.containsKey(f)) {
			buildAttributesList(f);
		}
		Map<String, FormElement> attList = attributeList.get(f);
		// Does the attribute exists in form?
		if (!attList.containsKey(cl.getName())) {
			Field fi = WorkflowDiagramUtils.getFieldForClazzLink(cl);
			c = AddCommand.create(domain, f, FormPackage.eINSTANCE.getFormGroup_Children(), fi);
		}
		return c;
	}

	/**
	 * Build the list of form with Form Id -> FormWorkflow
	 */
	private static void buildFormsList() {
		formList = new HashMap<String, FormWorkflow>();
		for (FormContainer f : fc.getForms()) {
			formList.put(f.getId(), (FormWorkflow) f);
		}
	}

	/**
	 * Add the list of attributes of the workflow to the list
	 * 
	 * @param f
	 */
	private static void buildAttributesList(FormWorkflow f) {
		Map<String, FormElement> listAtt = new HashMap<String, FormElement>();
		listAtt.putAll(FormDiagramUtils.getFormChild(f));
		attributeList.put(f, listAtt);
	}
}
