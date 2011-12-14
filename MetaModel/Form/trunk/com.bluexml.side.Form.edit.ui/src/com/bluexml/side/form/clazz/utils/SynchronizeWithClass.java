package com.bluexml.side.form.clazz.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.FormSearch;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.ModelChoiceSearchField;
import com.bluexml.side.form.SearchFormCollection;
import com.bluexml.side.form.WorkflowFormCollection;
import com.bluexml.side.form.common.utils.InternalModification;
import com.bluexml.side.form.search.utils.SearchInitialization;
import com.bluexml.side.util.libs.ecore.EcoreHelper;

public class SynchronizeWithClass {
	protected CompoundCommand cc;
	protected EditingDomain domain;

	boolean createMissingGroup = true;
	boolean updateId = true;
	boolean updateLabel = false;
	boolean addNewFormClass = true;
	final boolean headless;

	public SynchronizeWithClass(EditingDomain domain) {
		cc = new CompoundCommand();
		this.domain = domain;
		headless = false;
	}

	public SynchronizeWithClass() {
		headless = true;
	}

	public void synchronize(FormCollection fc) {
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
				FormContainer formContainer = null;
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
		InternalModification.moveToDisabled();
	}

	public void setFormContainer(AbstractClass abstractClass, FormContainer formContainer) {
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
			if (formContainer instanceof FormSearch) {
				SearchInitialization.initializeFormProperties((FormSearch) formContainer);
			} else if (formContainer instanceof ClassReference) {
				ClassReference cref = (ClassReference) formContainer;
				AbstractClass abstractClass = cref.getReal_class();
				if (!abstractClass.getName().equals(formContainer.getId())) {
					formContainer.setId(abstractClass.getName());
				}
			}
		}

		for (FormElement formElement : allchildren) {
			ModelElement ref = formElement.getRef();
			if (ref instanceof NamedModelElement) {
				NamedModelElement nme = (NamedModelElement) ref;
				String name = nme.getName();

				if (updateId && !name.equals(formElement.getId())) {
					if (headless) {
						formElement.setId(name);
					} else {
						cc.append(SetCommand.create(domain, formElement, FormPackage.eINSTANCE.getFormElement_Id(), name));
					}
				}

				if (updateLabel && ref instanceof TitledNamedClassModelElement) {
					TitledNamedClassModelElement tme = (TitledNamedClassModelElement) ref;
					String title = tme.getTitle();
					if (StringUtils.trimToNull(title) == null) {
						title = name;
					}
					if (headless) {
						formElement.setLabel(title);
					} else {
						cc.append(SetCommand.create(domain, formElement, FormPackage.eINSTANCE.getFormElement_Label(), title));
					}
				}
			}

			// check Field subType
			if (ref instanceof Attribute) {
				Attribute att = (Attribute) ref;

				FormElement fieldForAttribute = null;
				if (formContainer instanceof FormSearch) {
					fieldForAttribute = SearchInitialization.getSearchFieldForAttribute(att);
				} else if (formContainer instanceof FormClass) {
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
			} else if (ref instanceof Association) {
				Association ass = (Association) ref;
				if (formElement instanceof ModelChoiceField) {
					ModelChoiceField mcf = (ModelChoiceField) formElement;
					int parseInt = Integer.parseInt(ass.getSecondEnd().getCardMax());
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
				}
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
		domain.getCommandStack().execute(removeCommands);
	}

	void addMissing(FormContainer o, List<FormElement> allchildren) {

		addMissingAttributes(o, allchildren);

		addMissingAssociations(o, allchildren);

	}

	void addMissingAssociations(FormContainer o, List<FormElement> children) {
		List<Association> allAssociations = null;
		if (o instanceof ClassReference) {
			// get the attached class
			ClassReference cr = (ClassReference) o;
			AbstractClass real_class = cr.getReal_class();
			allAssociations = real_class.getAllSourceAssociations();

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
			// now we have the attribute missing list
			// initialize missing Field
			for (Association ass : missAtt) {

				FormElement fieldForAssociation = null;
				if (o instanceof FormClass) {
					fieldForAssociation = ClassDiagramUtils.getModelChoiceFieldForAssociation(ass, real_class);
				} else if (o instanceof FormSearch) {
					fieldForAssociation = ClassDiagramUtils.transformAssociationIntoModelChoiceSearchField(ass, real_class);
				}
				// get where to add the field
				FormGroup parent = o;
				// mybe a group exist with ref to the attribute container
				EObject eContainer = ass.eContainer();
				// search for matching group
				EList<FormGroup> allSubGroups = o.getAllSubGroups();
				for (FormGroup formGroup : allSubGroups) {
					ModelElement ref = formGroup.getRef();
					if (ref != null && ref.equals(eContainer)) {
						// matching group founded
						parent = formGroup;
						break;
					}
				}
				// add new Field to the parent
				if (headless) {
					parent.getChildren().add(fieldForAssociation);
				} else {
					cc.append(AddCommand.create(domain, parent, FormPackage.eINSTANCE.getFormGroup_Children(), fieldForAssociation));
				}

			}
		}
	}

	void addMissingAttributes(FormContainer o, List<FormElement> children) {
		List<Attribute> allAttributes = null;
		if (o instanceof ClassReference) {
			// get the attached class
			ClassReference cr = (ClassReference) o;
			AbstractClass real_class = cr.getReal_class();
			allAttributes = real_class.getAllAttributes();
		}

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
		// now we have the attribute missing list
		// initialize missing Field
		for (Attribute attribute : missAtt) {
			// Field or searchField
			FormElement fieldForAttribute = null;
			if (o instanceof FormSearch) {
				fieldForAttribute = SearchInitialization.getSearchFieldForAttribute(attribute);
			} else if (o instanceof FormClass) {
				fieldForAttribute = ClassDiagramUtils.getFieldForAttribute(attribute);
			}

			// get where to add the field
			FormGroup parent = null;
			// mybe a group exist with ref to the attribute container
			AbstractClass eContainer = (AbstractClass) attribute.eContainer();
			// search for matching group
			EList<FormGroup> allSubGroups = o.getAllSubGroups();
			for (FormGroup formGroup : allSubGroups) {
				ModelElement ref = formGroup.getRef();
				if (ref != null && ref.equals(eContainer)) {
					// matching group founded
					parent = formGroup;
					break;
				}
			}
			if (createMissingGroup && parent == null) {
				parent = ClassInitialization.createGroup(eContainer);
				o.getChildren().add(parent);
			}
			if (parent == null) {
				parent = o;
			}

			// add new Field to the parent
			if (headless) {
				parent.getChildren().add(fieldForAttribute);
			} else {
				cc.append(AddCommand.create(domain, parent, FormPackage.eINSTANCE.getFormGroup_Children(), fieldForAttribute));
			}
		}
	}

	public CompoundCommand getCc() {
		return cc;
	}

}
