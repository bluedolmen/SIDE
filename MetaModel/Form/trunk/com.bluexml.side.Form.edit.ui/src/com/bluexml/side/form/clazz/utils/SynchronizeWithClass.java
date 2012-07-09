package com.bluexml.side.form.clazz.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.Model;
import com.bluexml.side.clazz.TitledNamedClassModelElement;
import com.bluexml.side.clazz.service.alfresco.CommonServices;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.form.ClassFormCollection;
import com.bluexml.side.form.ClassReference;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.FormSearch;
import com.bluexml.side.form.FormWorkflow;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.SearchFormCollection;
import com.bluexml.side.form.WorkflowFormCollection;
import com.bluexml.side.form.common.utils.InternalModification;
import com.bluexml.side.form.search.utils.SearchInitialization;
import com.bluexml.side.form.workflow.utils.WorkflowInitialization;
import com.bluexml.side.util.libs.ecore.EcoreHelper;

public class SynchronizeWithClass {
	protected CompoundCommand cc;
	protected EditingDomain domain;

	boolean createMissingGroup = true;

	boolean updateId = true;
	boolean updateLabel = false;
	boolean addNewFormClass = true;
	boolean includeAlfrescoNativeClass = false;
	boolean includeAbstract = false;

	final boolean headless;

	public SynchronizeWithClass(EditingDomain domain) {
		cc = new CompoundCommand();
		this.domain = domain;
		headless = false;
	}

	public SynchronizeWithClass() {
		headless = true;
	}

	public void synchronize(FormCollection fc) throws Exception {
		InternalModification.dontMoveToDisabled();
		removeInvalide(fc);

		EList<FormContainer> forms = fc.getForms();
		for (FormContainer formContainer : forms) {
			synchronizeFormContainer(formContainer);
		}

		// add Missing FormClass (new Class in dt model)

		if (addNewFormClass) {
			// get All Class
			Collection<AbstractClass> missing = getAllClassesFromReferedModels(fc);
			for (AbstractClass abstractClass : missing) {

				if (!(abstractClass instanceof Clazz) ^ !((Clazz) abstractClass).isAbstract()) {
					FormContainer formContainer = null;
					
					if (!CommonServices.isNativeModel(abstractClass) || includeAlfrescoNativeClass) {

						if (fc instanceof ClassFormCollection) {
							formContainer = FormFactory.eINSTANCE.createFormClass();
							setFormContainer(abstractClass, formContainer);
							formContainer.setId(abstractClass.getName());
							formContainer.setLabel(abstractClass.getLabel());
						} else if (fc instanceof SearchFormCollection) {
							formContainer = FormFactory.eINSTANCE.createFormSearch();
							setFormContainer(abstractClass, formContainer);
							SearchInitialization.initializeFormProperties((FormSearch) formContainer);
						} else if (fc instanceof WorkflowFormCollection) {
							formContainer = FormFactory.eINSTANCE.createFormWorkflow();
						}

						if (headless) {
							fc.getForms().add(formContainer);
						} else {
							cc.append(AddCommand.create(domain, fc, FormPackage.eINSTANCE.getFormCollection_Forms(), formContainer));
						}
						if (formContainer instanceof ClassReference) {
							synchronizeFormContainer(formContainer);
						}
					}
				}
			}
		}
		InternalModification.moveToDisabled();
	}

	protected void setFormContainer(AbstractClass abstractClass, FormContainer formContainer) {
		ClassReference cref = (ClassReference) formContainer;
		cref.setReal_class(abstractClass);
	}

	public void synchronize(FormContainer o) {
		InternalModification.dontMoveToDisabled();
		removeInvalide(o);
		synchronizeFormContainer(o);
		InternalModification.moveToDisabled();
	}

	void synchronizeFormContainer(FormContainer o) {
		if (o instanceof FormClass || o instanceof FormSearch) {
			synchronize_formClass(o);
		}
	}

	void synchronize_formClass(FormContainer o) {
		List<FormElement> allchildren = new ArrayList<FormElement>();
		allchildren.addAll(o.getFields());
		allchildren.addAll(o.getSearchFields());
		allchildren.addAll(o.getDisabled());

		addMissing(o, allchildren);

		update(o, allchildren);

	}

	@SuppressWarnings("unchecked")
	Collection<AbstractClass> getAllClassesFromReferedModels(FormCollection fc) {
		Set<AbstractClass> existing = new HashSet<AbstractClass>();
		Set<AbstractClass> all = new HashSet<AbstractClass>();

		EList<FormContainer> forms = fc.getForms();
		for (FormContainer formContainer : forms) {
			if (formContainer instanceof ClassReference) {
				ClassReference cr = (ClassReference) formContainer;
				AbstractClass real_class = cr.getReal_class();
				if (real_class instanceof Clazz) {
					existing.add(real_class);
				}
			}
		}
		List<Model> lm = new ArrayList<Model>();

		for (AbstractClass abstractClass : existing) {
			EObject rootContainer2 = CommonServices.getRootContainer(abstractClass);
			if (rootContainer2 instanceof Model) {
				Model rootContainer = (Model) rootContainer2;
				lm.add(rootContainer);
			}
		}

		for (Model package1 : lm) {
			EList<Clazz> allClasses = package1.getAllClasses();
			all.addAll(allClasses);
		}

		return CollectionUtils.subtract(all, existing);
	}

	void update(FormContainer formContainer, List<FormElement> allchildren) {
		// update id
		if (updateId) {
			updateFormContainerId(formContainer);
		}

		if (updateLabel) {
			updateLabel(formContainer, ((TitledNamedClassModelElement) formContainer.getRef()).getLabel());
		}

		for (FormElement formElement : allchildren) {
			ModelElement ref = formElement.getRef();
			if (ref instanceof NamedModelElement) {
				NamedModelElement nme = (NamedModelElement) ref;
				String name = nme.getName();

				if (updateId && !name.equals(formElement.getId())) {
					updateId(formElement, name);
				}

				if (updateLabel && ref instanceof TitledNamedClassModelElement) {
					updateLabel(formContainer, ((TitledNamedClassModelElement) ref).getLabel());
				}
			}

			// check Field subType
			if (ref instanceof Attribute) {
				Attribute att = (Attribute) ref;

				updateAttributeFields(formContainer, formElement, att);
			} else if (ref instanceof Association) {

				Association ass = (Association) ref;
				if (formElement instanceof ModelChoiceField) {
					ModelChoiceField mcf = (ModelChoiceField) formElement;
					ClassReference cref = (ClassReference) formContainer;
					AbstractClass srcClazz = cref.getReal_class();
					updateModelChoiceField(srcClazz, ass, mcf);
				}
			}
		}
	}

	protected void updateFormContainerId(FormContainer formContainer) {
		if (formContainer instanceof FormSearch) {
			SearchInitialization.initializeFormProperties((FormSearch) formContainer);
			updateId(formContainer, formContainer.getId());
		} else if (formContainer instanceof ClassReference) {
			ClassReference cref = (ClassReference) formContainer;
			AbstractClass abstractClass = cref.getReal_class();
			if (!abstractClass.getName().equals(formContainer.getId())) {
				updateId(formContainer, abstractClass.getName());
			}
		}
	}

	public void updateModelChoiceField(AbstractClass srcClazz, Association ass, ModelChoiceField mcf) {

		ModelChoiceField modelChoiceFieldForAssociation = ClassDiagramUtils.getModelChoiceFieldForAssociation(ass, srcClazz);

		int parseInt = modelChoiceFieldForAssociation.getMax_bound();
		// at least bounds must be compatible with constraints in the dt model
		boolean b = (((ModelChoiceField) mcf).getMax_bound() > parseInt) && (parseInt != -1);
		if (headless) {
			if (b) {
				mcf.setMax_bound(parseInt);
			}
		} else {
			if (b) {
				cc.append(SetCommand.create(domain, mcf, FormPackage.eINSTANCE.getModelChoiceField_Max_bound(), parseInt));
			}
		}
		// maybe the name or Label have been changed must be based on target name/Label if exists
		String id = modelChoiceFieldForAssociation.getId();
		if (updateId && !id.equals(mcf.getId())) {
			updateId(mcf, id);

		}
		String label = modelChoiceFieldForAssociation.getLabel();
		if (updateLabel && !label.equals(mcf.getLabel())) {
			updateLabel(mcf, label);
		}
	}

	protected void updateId(FormElement fw, String computeFormWorkflowId) {
		if (headless) {
			fw.setId(computeFormWorkflowId);
		} else {
			cc.append(SetCommand.create(domain, fw, FormPackage.eINSTANCE.getFormElement_Id(), computeFormWorkflowId));
		}
	}

	protected void updateLabel(FormElement fw, String name) {
		if (headless) {
			fw.setLabel(name);
		} else {
			cc.append(SetCommand.create(domain, fw, FormPackage.eINSTANCE.getFormElement_Label(), name));
		}
	}

	public void updateAttributeFields(FormContainer formContainer, FormElement formElement, Attribute att) {
		FormElement fieldForAttribute = null;
		if (formContainer instanceof FormSearch) {
			fieldForAttribute = SearchInitialization.getSearchFieldForAttribute(att);
		} else if (formContainer instanceof FormClass || formContainer instanceof FormWorkflow) {
			fieldForAttribute = ClassDiagramUtils.getFieldForAttribute(att);
		}

		if (!fieldForAttribute.getClass().isInstance(formElement)) {
			// mismatch so we replace the Field
			FormGroup eContainer = (FormGroup) formElement.eContainer();
			boolean contains = eContainer.getChildren().contains(formElement);

			if (headless) {
				EList<FormElement> children = null;
				if (contains) {
					children = eContainer.getChildren();
				} else {
					children = eContainer.getDisabled();
				}
				// add the new Field
				children.add(fieldForAttribute);
				// remove
				children.remove(formElement);
			} else {
				if (contains) {
					cc.append(AddCommand.create(domain, eContainer, FormPackage.eINSTANCE.getFormGroup_Children(), fieldForAttribute));
				} else {
					cc.append(AddCommand.create(domain, eContainer, FormPackage.eINSTANCE.getFormGroup_Disabled(), fieldForAttribute));
				}
				Command rmCmd = RemoveCommand.create(domain, formElement);
				cc.append(rmCmd);
			}
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

	void addMissing(FormContainer o, List<FormElement> allchildren) {

		if (o instanceof ClassReference) {
			// get the attached class
			ClassReference cr = (ClassReference) o;
			AbstractClass real_class = cr.getReal_class();

			addMissing(o, allchildren, real_class, null);
		}

	}

	public void addMissing(FormContainer o, List<FormElement> allchildren, AbstractClass real_class, String filterNS) {
		Set<FormGroup> groups = new HashSet<FormGroup>();
		synchronizeMissingAttributes(o, allchildren, real_class, groups, filterNS);
		synchronizeMissingAssociations(o, allchildren, real_class, groups, filterNS);
	}

	protected void synchronizeMissingAssociations(FormContainer o, List<FormElement> children, AbstractClass real_class, Set<FormGroup> groups, String filterNS) {

		List<Association> allAssociations = real_class.getAllSourceAssociations();

		// get FormElement that miss
		ArrayList<Association> missAtt = new ArrayList<Association>();

		if (allAssociations != null) {
			missAtt.addAll(allAssociations);
		}

		for (FormElement formElement : children) {
			ModelElement ref = formElement.getRef();
			if (ref != null && ref instanceof Association) {
				// linked element is attribute so remove this attribute from the missing list
				missAtt.remove(ref);
			}
		}

		EList<FormGroup> allSubGroups = o.getAllSubGroups();
		groups.addAll(allSubGroups);

		// now we have the attribute missing list
		// initialize missing Field
		for (Association ass : missAtt) {

			FormElement fieldForAssociation = null;
			if (o instanceof FormClass || o instanceof FormWorkflow) {
				fieldForAssociation = ClassDiagramUtils.getModelChoiceFieldForAssociation(ass, real_class);
			} else if (o instanceof FormSearch) {
				fieldForAssociation = ClassDiagramUtils.transformAssociationIntoModelChoiceSearchField(ass, real_class);
			}

			if (WorkflowInitialization.filterFormElement(filterNS, fieldForAssociation)) {

				// get where to add the field
				FormGroup parent = null;
				// mybe a group exist with ref to the attribute container

				// search for matching group

				for (FormGroup formGroup : groups) {
					ModelElement ref = formGroup.getRef();
					if (ref instanceof AbstractClass && ((AbstractClass) ref).getSourceAssociations().contains(ass)) {
						// matching group founded
						parent = formGroup;
						break;
					}
				}

				if (createMissingGroup && parent == null) {
					AbstractClass source = null;
					EList<AbstractClass> sources = ass.getSource();
					if (sources.size() == 1) {
						source = sources.get(0);
					} else {
						EList<AbstractClass> allLinkedAbstractClass = real_class.getAllLinkedAbstractClass();
						boolean contains = allLinkedAbstractClass.contains(sources.get(0));
						if (contains) {
							source = sources.get(0);
						} else {
							source = sources.get(1);
						}
					}
					parent = ClassInitialization.createGroup(source);
					addChild(o, parent);
				}

				// add new Field to the parent
				if (parent == null) {
					parent = o;
				}
				addChild(parent, fieldForAssociation);

			}
		}

	}

	protected void synchronizeMissingAttributes(FormContainer o, List<FormElement> children, AbstractClass real_class, Set<FormGroup> groups, String filterNS) {

		List<Attribute> allAttributes = real_class.getAllAttributes();

		// get FormElement that miss
		ArrayList<Attribute> missAtt = new ArrayList<Attribute>();

		if (allAttributes != null) {
			missAtt.addAll(allAttributes);
		}

		for (FormElement formElement : children) {
			ModelElement ref = formElement.getRef();
			if (ref != null && ref instanceof Attribute) {
				// linked element is attribute so remove this attribute from the missing list
				missAtt.remove(ref);
			}
		}

		EList<FormGroup> allSubGroups = o.getAllSubGroups();
		groups.addAll(allSubGroups);

		// now we have the attribute missing list
		// initialize missing Field
		for (Attribute attribute : missAtt) {
			// Field or searchField
			FormElement fieldForAttribute = null;
			if (o instanceof FormSearch) {
				fieldForAttribute = SearchInitialization.getSearchFieldForAttribute(attribute);
			} else if (o instanceof FormClass || o instanceof FormWorkflow) {
				fieldForAttribute = ClassDiagramUtils.getFieldForAttribute(attribute);
			}
			if (WorkflowInitialization.filterFormElement(filterNS, fieldForAttribute)) {

				// get where to add the field
				FormGroup parent = null;
				// mybe a group exist with ref to the attribute container
				AbstractClass eContainer = (AbstractClass) attribute.eContainer();
				// search for matching group

				for (FormGroup formGroup : groups) {
					ModelElement ref = formGroup.getRef();
					if (ref != null && ref.equals(eContainer)) {
						// matching group founded
						parent = formGroup;
						break;
					}
				}
				if (createMissingGroup && parent == null) {
					parent = ClassInitialization.createGroup(eContainer);
					groups.add(parent);
					addChild(o, parent);
				}
				if (parent == null) {
					parent = o;
				}

				// add new Field to the parent
				addChild(parent, fieldForAttribute);

			}
		}

	}

	public CompoundCommand getCc() {
		return cc;
	}

	protected void addChild(FormGroup fw, FormElement f) {
		if (headless) {
			fw.getChildren().add(f);
		} else {
			Command create = AddCommand.create(domain, fw, FormPackage.eINSTANCE.getFormGroup_Children(), f);
			boolean canExecute = create.canExecute();
			System.out.println("SynchronizeWorkflowFormWithClass.addChild() canExecute :" + fw.getId() + "->" + f + canExecute);
			cc.append(create);
		}
	}
}
