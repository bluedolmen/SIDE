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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.form.ActionField;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.FormWorkflow;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.WorkflowFormCollection;
import com.bluexml.side.form.clazz.utils.SynchronizeWithClass;
import com.bluexml.side.form.common.utils.InternalModification;
import com.bluexml.side.util.libs.ecore.EcoreHelper;
import com.bluexml.side.workflow.Attribute;
import com.bluexml.side.workflow.Process;
import com.bluexml.side.workflow.Transition;
import com.bluexml.side.workflow.UserTask;

public class SynchronizeWorkflowFormWithClass {
	protected CompoundCommand cc;
	protected EditingDomain domain;
	SynchronizeWithClass synchroWithClass;
	final boolean headless;
	boolean addNewFormClass = true;
	boolean updateId = true;
	boolean updateLabel = false;

	public SynchronizeWorkflowFormWithClass(EditingDomain domain) {
		cc = new CompoundCommand();
		this.domain = domain;
		headless = false;
		synchroWithClass = new SynchronizeWithClass(domain);

	}

	public SynchronizeWorkflowFormWithClass() {
		headless = true;
		synchroWithClass = new SynchronizeWithClass();
	}

	public void synchronize(FormCollection fc) {
		InternalModification.dontMoveToDisabled();
		removeInvalide(fc);

		Set<ModelElement> existing = new HashSet<ModelElement>();
		EList<FormContainer> forms = fc.getForms();
		for (FormContainer formContainer : forms) {
			synchronizeFormContainer(formContainer);
			existing.add(formContainer.getRef());
		}

		// createMissing FormContainer
		if (addNewFormClass) {
			// create container
			if (fc instanceof WorkflowFormCollection) {
				Set<UserTask> missing = new HashSet<UserTask>();
				Set<UserTask> all = new HashSet<UserTask>();

				WorkflowFormCollection wfc = (WorkflowFormCollection) fc;
				Process linked_process = wfc.getLinked_process();
				all.add(linked_process.getStartstate());
				all.addAll(linked_process.getTasknode());

				missing.addAll(all);
				missing.removeAll(existing);

				for (UserTask userTask : missing) {
					FormWorkflow createTaskForForm = WorkflowInitialization.createTaskForForm(userTask);
					if (headless) {
						fc.getForms().add(createTaskForForm);
					} else {
						cc.append(AddCommand.create(domain, fc, FormPackage.eINSTANCE.getFormCollection_Forms(), createTaskForForm));
					}
				}
			}
		}
		InternalModification.moveToDisabled();
	}

	public void synchronize(FormContainer o) {
		InternalModification.dontMoveToDisabled();
		removeInvalide(o);
		synchronizeFormContainer(o);
		InternalModification.moveToDisabled();
	}

	void synchronizeFormContainer(FormContainer o) {
		if (o instanceof FormWorkflow) {
			FormWorkflow fw = (FormWorkflow) o;
			List<FormElement> allchildren = new ArrayList<FormElement>();
			allchildren.addAll(fw.getFields());
			allchildren.addAll(fw.getSearchFields());
			allchildren.addAll(fw.getDisabled());

			addMissing(fw, allchildren);

			update(fw, allchildren);

		}
	}

	void addMissing(FormWorkflow o, List<FormElement> allchildren) {
		UserTask st = (UserTask) o.getRef();
		Clazz advancedTaskDefinition = st.getAdvancedTaskDefinition();
		if (advancedTaskDefinition != null) {
			// From Clazz
			synchroWithClass.addMissing(o, allchildren, advancedTaskDefinition, "http://www.alfresco.org/model");

		} else {
			// From Task
			addMissingWorkflowAttributes(o, allchildren);
		}

		// manage transitions
		addMissingTransitions(o, allchildren);

	}

	private void addMissingWorkflowAttributes(FormWorkflow fw, List<FormElement> allchildren) {
		UserTask ut = (UserTask) fw.getRef();
		Set<Attribute> missing = new HashSet<Attribute>();
		missing.addAll(ut.getAttributes());

		for (FormElement formElement : allchildren) {
			ModelElement ref = formElement.getRef();
			if (ref != null && ref instanceof Attribute) {
				// linked element is attribute so remove this attribute from the missing list
				missing.remove(ref);
			}
		}

		for (Attribute a : missing) {
			Field f = WorkflowDiagramUtils.getFieldForAttribute(a);
			if (f != null) {
				addChild(fw, f);
			}
		}
	}

	private void addMissingTransitions(FormWorkflow fw, List<FormElement> allchildren) {
		UserTask ut = (UserTask) fw.getRef();

		Set<Transition> missing = new HashSet<Transition>();
		missing.addAll(ut.getTransition());

		for (FormElement formElement : allchildren) {
			ModelElement ref = formElement.getRef();
			if (ref != null && ref instanceof Transition) {
				// linked element is attribute so remove this attribute from the missing list
				missing.remove(ref);
			}
		}

		for (Transition t : missing) {
			ActionField af = WorkflowDiagramUtils.getOperationForTransition(t);
			if (af != null) {
				addChild(fw, af);
			}
		}
	}

	/**
	 * update Fields, Fix minor changes since invalid ref and other Validation
	 * Fail force Fields to be re-initialized
	 * 
	 * @param fw
	 * @param allchildren
	 */
	void update(FormWorkflow fw, List<FormElement> allchildren) {
		UserTask ref = (UserTask) fw.getRef();
		// check wf id/label
		if (updateId) {
			String computeFormWorkflowId = WorkflowInitialization.computeFormWorkflowId(ref);
			if (!computeFormWorkflowId.equals(fw.getId())) {
				updateId(fw, computeFormWorkflowId);

			}
		}

		String name = ref.getName();
		if (updateLabel && name.equals(fw.getLabel())) {
			updateLabel(fw, name);
		}

		// check attributes
		EList<Field> fields = fw.getFields();
		for (Field field : fields) {
			ModelElement ref2 = field.getRef();
			if (field instanceof ActionField && ref2 instanceof Transition) {
				// check transitions
				Transition t = (Transition) ref2;
				ActionField af = WorkflowDiagramUtils.getOperationForTransition(t);
				if (updateId) {
					String id = af.getId();
					if (!field.getId().equals(id)) {
						updateId(field, id);
					}
				}
				if (updateLabel) {
					String label = af.getLabel();
					if (!field.getLabel().equals(label)) {
						updateLabel(field, label);
					}
				}
			} else if (field instanceof ModelChoiceField) {
				//check associations
				synchroWithClass.updateModelChoiceField(ref.getAdvancedTaskDefinition(), (Association) ref2, (ModelChoiceField) field);
			} else {
				// other Fields linked to Attributes (from Workflow or Clazz Model)
				if (ref2 instanceof Attribute) {
					Attribute a = (Attribute) ref2;
					Field f = WorkflowDiagramUtils.getFieldForAttribute(a);
					if (updateId) {
						String id = f.getId();
						if (!field.getId().equals(id)) {
							updateId(field, id);
						}
					}
					if (updateLabel) {
						String label = f.getLabel();
						if (!field.getLabel().equals(label)) {
							updateLabel(field, label);
						}
					}
				} else if (ref2 instanceof com.bluexml.side.clazz.Attribute) {
					com.bluexml.side.clazz.Attribute att = (com.bluexml.side.clazz.Attribute) ref2;
					synchroWithClass.updateAttributeFields(fw, field, att);
				}
			}
		}

	}

	public void updateId(FormElement fw, String computeFormWorkflowId) {
		if (headless) {
			fw.setId(computeFormWorkflowId);
		} else {
			cc.append(SetCommand.create(domain, fw, FormPackage.eINSTANCE.getFormElement_Id(), computeFormWorkflowId));
		}
	}

	public void updateLabel(FormElement fw, String name) {
		if (headless) {
			fw.setLabel(name);
		} else {
			cc.append(SetCommand.create(domain, fw, FormPackage.eINSTANCE.getFormElement_Label(), name));
		}
	}

	void removeInvalide(EObject o) {
		CompoundCommand removeCommands = new CompoundCommand();
		List<EObject> validationErrors = EcoreHelper.getValidationErrors(o);
		for (EObject eObject : validationErrors) {
			if (eObject instanceof FormElement) {

				System.out.println("SynchronizeWithClass.removeInvalide() remove :" + eObject);
				if (headless) {
					EList<?> children = null;
					EObject eContainer = eObject.eContainer();
					if (eContainer instanceof FormGroup) {
						FormGroup fg = (FormGroup) eContainer;

						if (fg.getChildren().contains(eObject)) {
							children = fg.getChildren();
						} else if (fg.getDisabled().contains(eObject)) {
							children = fg.getDisabled();
						}
					} else if (eContainer instanceof FormCollection) {
						children = ((FormCollection) eContainer).getForms();
					}
					boolean remove = children.remove(eObject);
					System.out.println("SynchronizeWithClass.removeInvalide() removed? " + remove);
				} else {
					Command delCmd = RemoveCommand.create(domain, eObject);
					removeCommands.append(delCmd);
				}
			}
		}
		if (!headless) {
			domain.getCommandStack().execute(removeCommands);
		}
	}

	public void addChild(FormWorkflow fw, FormElement f) {
		if (headless) {
			fw.getChildren().add(f);
		} else {
			Command create = AddCommand.create(domain, fw, FormPackage.eINSTANCE.getFormGroup_Children(), f);
			boolean canExecute = create.canExecute();
			System.out.println("SynchronizeWorkflowFormWithClass.addChild() canExecute :" + fw.getId() + "->" + f + canExecute);
			cc.append(create);
		}
	}

	public CompoundCommand getCc() {
		CompoundCommand cc2 = synchroWithClass.getCc();
		if (cc2.getCommandList().size() > 0) {
			cc.append(cc2);
		}

		return cc;
	}
}
