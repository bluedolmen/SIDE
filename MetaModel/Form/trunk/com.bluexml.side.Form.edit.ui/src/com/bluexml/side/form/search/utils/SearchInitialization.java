/**
 * 
 */
package com.bluexml.side.form.search.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.common.DataType;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.FormSearch;
import com.bluexml.side.form.SearchField;
import com.bluexml.side.form.clazz.utils.ClassDiagramUtils;
import com.bluexml.side.util.libs.ui.UIUtils;

/**
 * @author Amenel
 * @author davidabad
 */
public class SearchInitialization {

	private static final String SUFFIX_NAME = "_Search";
	private static final String SUFFIX_LABEL = " (search)";

	public static void initializeFormSearch(FormSearch form) {
		if (form.getReal_class() != null) {
			// 
			initializeFormProperties(form);

			// get the new children. Old children are deleted.
			Collection<FormElement> children = getSearchChildrenFromClazz(form);
			// add the children
			if (children.size() > 0) {
				form.getChildren().addAll(children);
			}

		}
	}

	public static Command initializeFormSearch(FormSearch form, EditingDomain domain) {
		CompoundCommand cmd = new CompoundCommand();
		if (form.getReal_class() != null) {
			boolean doWork = true;
			if (form.getChildren().size() > 0) {
				doWork = UIUtils.showConfirmation("Form already set", "This search form has already been set. Do you really want to overwrite?");
			}

			if (doWork) {
				// 
				Command initCmd = initializeFormProperties(form, domain);
				if (initCmd.canExecute()) {
					cmd.append(initCmd);
				}

				// get the new children. Old children are deleted.
				Collection<FormElement> children = getSearchChildrenFromClazz(form);
				// add the children
				if (children.size() > 0) {
					Command addCmd = AddCommand.create(domain, form, FormPackage.eINSTANCE.getFormGroup_Children(), children);
					cmd.append(addCmd);
				}
			}
		} else {
			UIUtils.showError("No Data Form defined", "No data form has been defined. \n" + "Please choose one and run Initialize again.");
		}
		return cmd;
	}

	private static void initializeFormProperties(FormSearch form) {
		// get the attached model element
		AbstractClass realClass = form.getReal_class();
		if (realClass != null) {
			String className = realClass.getName();
			String classLabel = realClass.getLabel();
			String label = (classLabel.length() > 0 ? classLabel : className);

			if (form.getLabel() == null || form.getLabel().length() == 0) {

				String value = label + SUFFIX_LABEL;
				form.setLabel(value);

			}
			if (form.getId() == null || form.getId().length() == 0) {
				String value = className + SUFFIX_NAME;
				form.setId(value);
			}
		}
	}

	/**
	 * Sets the properties of the search form that may be inferred from the
	 * attachment link.
	 * 
	 * @param form
	 * @param domain
	 * @return
	 */
	private static Command initializeFormProperties(FormSearch form, EditingDomain domain) {
		CompoundCommand cc = new CompoundCommand();

		// get the attached model element
		AbstractClass realClass = form.getReal_class();
		if (realClass != null) {
			String className = realClass.getName();
			String classLabel = realClass.getLabel();
			String label = (classLabel.length() > 0 ? classLabel : className);

			if (form.getLabel() == null || form.getLabel().length() == 0) {
				cc.append(SetCommand.create(domain, form, FormPackage.eINSTANCE.getFormElement_Label(), label + SUFFIX_LABEL));
			}
			if (form.getId() == null || form.getId().length() == 0) {
				cc.append(SetCommand.create(domain, form, FormPackage.eINSTANCE.getFormElement_Id(), className + SUFFIX_NAME));
			}
		}
		return cc;
	}
	public static Collection<FormElement> createChildsForClass(AbstractClass abc, boolean groupRoot) {
		Collection<FormElement> c = new ArrayList<FormElement>();
		try {
			if (abc != null) {
				Map<String, AbstractClass> linkedL = new TreeMap<String, AbstractClass>();
				EList<AbstractClass> allLinkedAbstractClass = abc.getAllLinkedAbstractClass();
				for (AbstractClass abstractClass : allLinkedAbstractClass) {
					linkedL.put(abstractClass.getName(), abstractClass);
				}

				if (!groupRoot) {
					createChildren(abc, c);
					linkedL.remove(abc.getName());
				}
				// create a group for the classes items			

				for (AbstractClass linked : linkedL.values()) {
					FormGroup createFormGroup2 = initializeClassGroup(linked);
					c.add(createFormGroup2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	private static FormGroup initializeClassGroup(AbstractClass abc) {
		FormGroup createFormGroup = FormFactory.eINSTANCE.createFormGroup();
		createFormGroup.setRef(abc);
		createFormGroup.setId(abc.getName());
		createFormGroup.setLabel(abc.getLabel());
		EList<FormElement> children = createFormGroup.getChildren();

		createChildren(abc, children);
		return createFormGroup;
	}
	private static void createChildren(AbstractClass abc, Collection<FormElement> children) {
		// Attributes
		for (Attribute att : abc.getAttributes()) {
			SearchField field = null;
			field = getSearchFieldForAttribute(att);
			if (field != null) {
				children.add(field);
			}
		}
	}
	
	private static Collection<FormElement> getSearchChildrenFromClazz(FormSearch form) {
		form.getDisabled().clear();
		form.getChildren().removeAll(form.getChildren()); // <-- this is MANDATORY !
		AbstractClass cl = form.getReal_class();
		Collection<FormElement> c = createChildsForClass(cl, false);
		return c;
	}
	
	

	/**
	 * Provides a search field of the type that corresponds to the type of the
	 * given attribute.
	 * <p/>
	 * Types <em>NOT</em> supported: Time, Boolean
	 * <p/>
	 * Downgraded types: DateTime (to Date)
	 * 
	 * @param att
	 * @return
	 */
	private static SearchField getSearchFieldForAttribute(Attribute att) {
		SearchField field = null;
		if (att != null) {
			Map<String, String> metaInfoMap = ClassDiagramUtils.InitializeMetaInfo(att.getMetainfo());
			if (att.getValueList() != null) {
				// Choice Field
				field = FormFactory.eINSTANCE.createChoiceSearchField();
			} else if (att.getTyp().equals(DataType.STRING)) {
				field = FormFactory.eINSTANCE.createCharSearchField();
			} else if (att.getTyp().equals(DataType.DATE_TIME)) {
				// Date Time Field
				field = FormFactory.eINSTANCE.createDateSearchField();
			} else if (att.getTyp().equals(DataType.DATE)) {
				// Date Field
				field = FormFactory.eINSTANCE.createDateSearchField();
			} else if (att.getTyp().equals(DataType.TIME)) {
				// Time Field
				// TODO
			} else if (att.getTyp().equals(DataType.BOOLEAN)) {
				// Boolean Field
				field = FormFactory.eINSTANCE.createBooleanSearchField();
			} else if (att.getTyp().equals(DataType.INT)) {
				// Integer Field
				field = FormFactory.eINSTANCE.createNumericalSearchField();
			} else if (att.getTyp().equals(DataType.LONG)) {
				// Long Field
				field = FormFactory.eINSTANCE.createNumericalSearchField();
			} else if (att.getTyp().equals(DataType.FLOAT)) {
				// Float Field
				field = FormFactory.eINSTANCE.createNumericalSearchField();
			} else if (att.getTyp().equals(DataType.DOUBLE)) {
				// Decimal Field
				field = FormFactory.eINSTANCE.createNumericalSearchField();
			} else if (att.getTyp().equals(DataType.SHORT)) {
				// Short Field
				field = FormFactory.eINSTANCE.createNumericalSearchField();
			} else if (att.getTyp().equals(DataType.BYTE)) {
				// Byte Field
				field = FormFactory.eINSTANCE.createNumericalSearchField();
			} else if (att.getTyp().equals(DataType.OBJECT)) {
				field = FormFactory.eINSTANCE.createCharSearchField();
			} else {
				EcorePlugin.INSTANCE.log("No field available for " + att.getTyp());
			}

			if (field == null) {
				// field = formFactory.eINSTANCE.createField();
			} else {
				field.setRef(att);
				field.setId(att.getName());
				if (att.getTitle() != null && att.getTitle().length() > 0) {
					field.setLabel(att.getTitle());
				} else {
					field.setLabel(att.getName());
				}
				field.setHidden(Boolean.parseBoolean(metaInfoMap.get("hidden")));
				field.setHelp_text(att.getDescription());
				field.setId(att.getName());
			}
		}
		return field;
	}

}
