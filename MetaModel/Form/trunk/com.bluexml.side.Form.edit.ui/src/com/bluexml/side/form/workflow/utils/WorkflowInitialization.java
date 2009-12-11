package com.bluexml.side.form.workflow.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.form.ActionField;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.FormWorkflow;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.WorkflowFormCollection;
import com.bluexml.side.util.libs.ui.UIUtils;
import com.bluexml.side.workflow.Attribute;
import com.bluexml.side.workflow.Process;
import com.bluexml.side.workflow.State;
import com.bluexml.side.workflow.Transition;
import com.bluexml.side.workflow.UserTask;

public class WorkflowInitialization {

	/**
	 * Launch initialization from a Workflow Form Collection
	 * @param fc
	 * @param domain
	 * @return
	 */
	public static Command initialize(WorkflowFormCollection fc, EditingDomain domain) {
		CompoundCommand cmd = null;
		Process p = fc.getLinked_process();
		if (p != null) {
			boolean doWork = true;
			if(fc.getForms().size() > 0) {
				doWork = UIUtils.showConfirmation("Workflow already set","Workflow have already been set. Do you want to overwrite it?");
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
					FormWorkflow fw = createTaskForForm(p,s);
					fw.setRef(s);
					lf.add(fw);
				}
				cmd.append(AddCommand.create(domain, fc, FormPackage.eINSTANCE.getFormCollection_Forms(), lf));
			}
		} else {
			UIUtils.showError("No Process defined","No Process have been defined. \n"
					+ "Choose one and run Initiliaze again.");
		}
		return cmd;
	}

	/**
	 * Return a form a Task
	 * @param p
	 * @param s
	 * @return
	 */
	public static FormWorkflow createTaskForForm(Process p, State s) {
		FormWorkflow fw = FormFactory.eINSTANCE.createFormWorkflow();
		fw.setId(p.getName() + "_" + s.getName());
		fw.setLabel(s.getName());
		fw.setRef(s);

		if (s instanceof UserTask) {
			UserTask ut = (UserTask) s;
			// For all attribute we get the field :
			for (Attribute a : ut.getAttributes()) {
				if (a.getAllowedValues().size() == 0) {
					Field f = WorkflowDiagramUtils.getFieldForAttribute(a);
					if (f != null) {
						fw.getChildren().add(f);
					}
				} else {
					ModelChoiceField f = FormFactory.eINSTANCE.createModelChoiceField();
					f.setId(a.getName());
					f.setRef(a);
					if (a.getTitle() != null && a.getTitle().length() > 0) {
						f.setLabel(a.getTitle());
					} else {
						f.setLabel(a.getName());
					}
					if (f != null) {
						fw.getChildren().add(f);
					}
				}
			}

			// For attached class :
			if (((UserTask) s).getClazz().size() > 0) {
				for (Clazz c : ((UserTask) s).getClazz()) {
					Field f = WorkflowDiagramUtils.getFieldForClazzLink(c);
					if (f != null) {
						fw.getChildren().add(f);
					}

					// Commented : add the form class instead of model choice field
					/*FormClass fc = FormFactory.eINSTANCE.createFormClass();
					fc.setReal_class(c);
					fc.getChildren().addAll(ClassInitialization.getChildForFormClassFromClazz(fc));
					fw.getChildren().add(fc);*/
				}
			}

			// Same for Transition
			for (Transition t : ut.getTransition()) {
				 ActionField af = WorkflowDiagramUtils.getOperationForTransition(t);
				 if (af != null) {
					 fw.getChildren().add(af);
				 }
			}


		}
		return fw;
	}
}
