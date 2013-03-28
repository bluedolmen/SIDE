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
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NameSpace;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.form.ActionField;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.FormWorkflow;
import com.bluexml.side.form.WorkflowFormCollection;
import com.bluexml.side.form.clazz.utils.ClassInitialization;
import com.bluexml.side.util.libs.ui.UIUtils;
import com.bluexml.side.workflow.Attribute;
import com.bluexml.side.workflow.Process;
import com.bluexml.side.workflow.State;
import com.bluexml.side.workflow.Transition;
import com.bluexml.side.workflow.UserTask;

public class WorkflowInitialization {

	public static void headLessInitialize(WorkflowFormCollection fc) {
		Process p = fc.getLinked_process();
		if (p != null) {
			// fix update fc.name
			if (StringUtils.trimToNull(fc.getName()) == null) {
				fc.setName(p.getName());
			}
			List<State> l = new ArrayList<State>();
			l.add(p.getStartstate());
			l.addAll(p.getTasknode());

			// List of Form
			List<FormWorkflow> lf = new ArrayList<FormWorkflow>();

			// For each task we create a form
			for (State s : l) {
				FormWorkflow fw = createTaskForForm(s);
				fw.setRef(s);
				lf.add(fw);
			}
			fc.getForms().addAll(lf);
		}

	}

	/**
	 * Launch initialization from a Workflow Form Collection
	 * 
	 * @param fc
	 * @param domain
	 * @return
	 */
	public static Command initialize(WorkflowFormCollection fc, EditingDomain domain) {
		CompoundCommand cmd = null;
		Process p = fc.getLinked_process();
		if (p != null) {
			// fix update fc.name
			if (StringUtils.trimToNull(fc.getName()) == null) {
				fc.setName(p.getName());
			}

			boolean doWork = true;
			if (fc.getForms().size() > 0) {
				doWork = UIUtils.showConfirmation("Workflow already set", "This Workflow Form Collection has already been set. Do you want to overwrite it?");
			}

			if (doWork) {
				cmd = new CompoundCommand();
				// Delete all childs:
				if (fc.getForms().size() > 0) {
					cmd.append(RemoveCommand.create(domain, fc.getForms()));
				}
				// Get All Tasks
				List<State> l = new ArrayList<State>();
				l.add(p.getStartstate());
				l.addAll(p.getTasknode());

				// List of Form
				List<FormWorkflow> lf = new ArrayList<FormWorkflow>();

				// For each task we create a form
				for (State s : l) {
					FormWorkflow fw = createTaskForForm(s);

					lf.add(fw);
				}
				cmd.append(AddCommand.create(domain, fc, FormPackage.eINSTANCE.getFormCollection_Forms(), lf));
			}
		} else {
			UIUtils.showError("No Process defined", "No Process has been defined. \n" + "Choose one and run Initialize again.");
		}
		return cmd;
	}

	/**
	 * Return a form a Task
	 * 
	 * @param s
	 * @return
	 */
	public static FormWorkflow createTaskForForm(State s) {
		FormWorkflow fw = FormFactory.eINSTANCE.createFormWorkflow();
		fw.setId(computeFormWorkflowId(s));
		fw.setLabel(s.getName());
		fw.setRef(s);

		if (s instanceof UserTask) {
			UserTask ut = (UserTask) s;

			Clazz advancedTaskDefinition = ut.getAdvancedTaskDefinition();
			if (advancedTaskDefinition != null) {
				// use initialize from the Class definition instead to search attribute task
				Collection<FormElement> createChildsForClass = ClassInitialization.createChildsForClass(advancedTaskDefinition, false);

				// filter bpm element to only keep custom elements mandatory fields are created by Share Form Generator ... very bad
				// TODO : remove share specific need here and let generator do his job
				Collection<FormElement> filtered = filterAttributes(createChildsForClass, "http://www.alfresco.org/model");
				fw.getChildren().addAll(filtered);
			} else {

				// For all attribute we get the field :
				createAttributeFields(fw, ut);

				// For attached class :
				if (((UserTask) s).getClazz().size() > 0) {
					for (Clazz c : ((UserTask) s).getClazz()) {
						Field f = WorkflowDiagramUtils.getFieldForClazzLink(c);
						if (f != null) {
							fw.getChildren().add(f);
						}

						// Commented : add the form class instead of model choice field
						/*
						 * FormClass fc =
						 * FormFactory.eINSTANCE.createFormClass();
						 * fc.setReal_class(c);
						 * fc.getChildren().addAll(ClassInitialization.
						 * getChildForFormClassFromClazz(fc));
						 * fw.getChildren().add(fc);
						 */
					}
				}
			}

			// Same for Transition
			createTransitionsFields(fw, ut);

			// add save button bug #1787
			// TODO the save action is Share Form specific ... need to manage this on Generation part
			createSaveShareAction(fw);

		}
		return fw;
	}

	public static Collection<FormElement> filterAttributes(Collection<FormElement> createChildsForClass, String filterURI) {
		Collection<FormElement> filtered = new ArrayList<FormElement>();
		for (FormElement formElement : createChildsForClass) {
			boolean filterFormElement = filterFormElement(filterURI, formElement);
			if (filterFormElement) {
				filtered.add(formElement);
			}
		}
		return filtered;
	}

	public static boolean filterFormElement(String filterURI, FormElement formElement) {
		ModelElement ref = formElement.getRef();
		String fullName = ((NamedModelElement) ref).getFullName();
		NameSpace namespace = ref.getLogicalNameSpace();

		if (filterURI == null || namespace == null || !namespace.getURI().startsWith(filterURI)) {
			System.out.println("add Field for " + fullName);
			return true;
		} else {
			System.out.println("FormElement not added :" + fullName);
			return false;
		}
	}

	public static String computeFormWorkflowId(State s) {
		Process p = (Process) s.eContainer();
		return p.getName() + "_" + s.getName();
	}

	public static void createSaveShareAction(FormWorkflow fw) {
		ActionField save = FormFactory.eINSTANCE.createActionField();
		save.setId("wrkflw_save");
		save.setLabel("save");
		save.setRef(fw.getRef());
		fw.getChildren().add(save);
	}

	public static void createTransitionsFields(FormWorkflow fw, UserTask ut) {
		for (Transition t : ut.getTransition()) {
			ActionField af = WorkflowDiagramUtils.getOperationForTransition(t);
			if (af != null) {
				fw.getChildren().add(af);
			}
		}
	}

	public static void createAttributeFields(FormWorkflow fw, UserTask ut) {
		for (Attribute a : ut.getAttributes()) {
			//if (a.getAllowedValues().size() == 0) { 
			// @Amenel: ModelChoiceFields are not supported in workflow forms (yet). Fields with
			// an allowed values list must remain a CharField since there's no class associated.
			Field f = WorkflowDiagramUtils.getFieldForAttribute(a);
			if (f != null) {
				fw.getChildren().add(f);
			}
			// } else {
			// ModelChoiceField f = FormFactory.eINSTANCE.createModelChoiceField();
			// f.setId(a.getName());
			// f.setRef(a);
			// if (a.getTitle() != null && a.getTitle().length() > 0) {
			// f.setLabel(a.getTitle());
			// } else {
			// f.setLabel(a.getName());
			// }
			// if (f != null) {
			// fw.getChildren().add(f);
			// }
			// }
		}
	}
}
