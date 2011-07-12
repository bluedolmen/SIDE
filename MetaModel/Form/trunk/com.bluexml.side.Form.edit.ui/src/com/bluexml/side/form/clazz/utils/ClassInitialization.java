package com.bluexml.side.form.clazz.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.common.OperationComponent;
import com.bluexml.side.form.ClassFormCollection;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.util.libs.ui.UIUtils;

public class ClassInitialization {

	public static Command initializeClass(FormClass fc, EditingDomain domain) {

		CompoundCommand cc = new CompoundCommand();
		if (fc.getReal_class() != null) {
			boolean doWork = true;
			if (fc.getChildren().size() > 0) {
				doWork = UIUtils.showConfirmation("Class already set", "Class have already been set. Do you want to overwrite it?");
			}

			if (doWork) {
				Command initCmd = initializeFormClass(fc, domain);
				if (initCmd.canExecute()) {
					cc.append(initCmd);
				}
				Collection<FormElement> c = getChildForFormClassFromClazz(fc);
				if (c.size() > 0) {
					Command addCmd = AddCommand.create(domain, fc, FormPackage.eINSTANCE.getFormGroup_Children(), c);
					cc.append(addCmd);
				}
			}
		} else {
			UIUtils.showError("No Class defined", "No class have been defined. \n" + "Choose one and run Initiliaze again.");
		}
		return cc;
	}

	public static void headLessInitializeClass(FormClass fc) {
		// update FormClass properties
		String[] updateLabelAndName = updateLabelAndName(fc);
		String label = updateLabelAndName[0];
		String name = updateLabelAndName[1];
		fc.setId(name);
		fc.setLabel(label);
		// add all children
		Collection<FormElement> c = getChildForFormClassFromClazz(fc);
		fc.getChildren().addAll(c);
	}

	/**
	 * Will rerturn a compoundCommand with setcommand if needed.
	 * 
	 * @param fc
	 * @param domain
	 * @return
	 */
	private static Command initializeFormClass(FormClass fc, EditingDomain domain) {

		CompoundCommand cc = new CompoundCommand();
		try {
			if (fc.getReal_class() != null) {
				String[] updateLabelAndName = updateLabelAndName(fc);
				String label = updateLabelAndName[0];
				String name = updateLabelAndName[1];

				if (fc.getLabel() == null || fc.getLabel().length() == 0) {
					cc.append(SetCommand.create(domain, fc, FormPackage.eINSTANCE.getFormElement_Label(), label));
				}
				if (fc.getId() == null || fc.getId().length() == 0) {
					cc.append(SetCommand.create(domain, fc, FormPackage.eINSTANCE.getFormElement_Id(), name));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cc;
	}

	private static String[] updateLabelAndName(FormClass fc) {
		String name, label;
		// need to search if exist another form with default value, if yes must increments label and id, to avoid validation fault
		AbstractClass c = fc.getReal_class();
		name = c.getName();
		label = c.getLabel();
		ClassFormCollection parent = (ClassFormCollection) fc.eContainer();

		int index_g = getIndexOfForm(name, parent);
		int i = 0;
		while (index_g != -1) {
			i++;
			name += i;
			label += " " + i;
			index_g = getIndexOfForm(name, parent);
		}
		return new String[] { name, label };
	}

	private static int getIndexOfForm(String id, ClassFormCollection parent) {
		for (FormElement item : parent.getForms()) {
			if (item.getId() != null && item.getId().equals(id)) {
				return parent.getForms().indexOf(item);
			}
		}
		return -1;
	}

	private static Collection<FormElement> getChildForFormClassFromClazz(FormClass fc) {
		fc.getDisabled().clear();
		fc.getChildren().removeAll(fc.getChildren());
		AbstractClass cl = fc.getReal_class();
		Collection<FormElement> c = createChildsForClass(cl, false);

		return c;
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
			Field field = null;
			field = ClassDiagramUtils.getFieldForAttribute(att);
			if (field != null) {

				children.add(field);
			}
		}

		// Associations :
		for (Association ass : abc.getSourceAssociations()) {
			children.add(ClassDiagramUtils.transformAssociationIntoModelChoiceField(ass, abc));
		}
		if (abc instanceof Clazz) {
			Clazz cl = (Clazz) abc;
			// Operations :
			for (OperationComponent op : cl.getOperations()) {
				Field field = ClassDiagramUtils.getFieldForOperation(op);
				children.add(field);
			}
		}
	}
}
