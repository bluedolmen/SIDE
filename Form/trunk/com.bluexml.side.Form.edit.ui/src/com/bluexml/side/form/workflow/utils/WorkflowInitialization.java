package com.bluexml.side.form.workflow.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.FormWorkflow;
import com.bluexml.side.form.WorkflowFormCollection;
import com.bluexml.side.form.common.utils.UIUtils;
import com.bluexml.side.workflow.Attribute;
import com.bluexml.side.workflow.Process;
import com.bluexml.side.workflow.StartState;
import com.bluexml.side.workflow.State;
import com.bluexml.side.workflow.TaskNode;

public class WorkflowInitialization {
	
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
					FormWorkflow fw = FormFactory.eINSTANCE.createFormWorkflow();
					fw.setId(s.getName());
					fw.setLabel(s.getName());
					
					// For all attribute we get the field :
					if (s instanceof StartState) {
						StartState ss = (StartState) s;
						for (Attribute a : ss.getAttributes()) {
							Field f = WorkflowDiagramUtils.getFieldForAttribute(a);
							if (f != null) {
								fw.getChildren().add(f);
							}
						}
					} else if (s instanceof TaskNode) {
						TaskNode tn = (TaskNode) s;
						for (Attribute a : tn.getAttributes()) {
							Field f = WorkflowDiagramUtils.getFieldForAttribute(a);
							if (f != null) {
								fw.getChildren().add(f);
							}
						}
					}
					
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
}
