package com.bluexml.side.form.clazz.utils;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.common.OperationComponent;
import com.bluexml.side.form.ClassFormCollection;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormAspect;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormFactory;
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
				Command initCmd = initializeAttributeFormClass(fc, domain);
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

	/**
	 * Will rerturn a compoundCommand with setcommand if needed.
	 * 
	 * @param fc
	 * @param domain
	 * @return
	 */
	private static Command initializeAttributeFormClass(FormClass fc, EditingDomain domain) {

		CompoundCommand cc = new CompoundCommand();
		try {
			if (fc.getReal_class() != null) {
				AbstractClass c = fc.getReal_class();
				String name = c.getName();
				String label = (c.getLabel().length() > 0 ? c.getLabel() : c.getName());

				// need to search if exist another form with default value, if yes must increments label and id, to avoid validation fault

				ClassFormCollection parent = (ClassFormCollection) fc.eContainer();

				int index_g = getIndexOfForm(name, parent);
				int i = 0;
				while (index_g != -1) {
					i++;
					name += i;
					label += " " + i;
					index_g = getIndexOfForm(name, parent);
				}

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

	private static int getIndexOfForm(String id, ClassFormCollection parent) {
		for (FormElement item : parent.getForms()) {
			if (item.getId() != null && item.getId().equals(id)) {
				return parent.getForms().indexOf(item);
			}
		}
		return -1;
	}

	public static Collection<FormElement> getChildForFormClassFromClazz(FormClass fc) {
		fc.getDisabled().clear();
		fc.getChildren().removeAll(fc.getChildren());
		AbstractClass cl = fc.getReal_class();
		Collection<FormElement> c = new ArrayList<FormElement>();

		if (cl != null) {

			// Attributes

			for (Attribute att : cl.getAllAttributesWithoutAspectsAttributes()) {
				Field field = null;
				field = ClassDiagramUtils.getFieldForAttribute(att);
				if (field != null) {
					c.add(field);
				}
			}
			if (cl instanceof Clazz) {
				Clazz clazz = (Clazz) cl;

				try {
					// Aspects
					for (Aspect asp : clazz.getAllAspects()) {
						FormAspect fa = FormFactory.eINSTANCE.createFormAspect();
						fa.setRef(asp);
						fa.setId(asp.getName());
						fa.setLabel(asp.getLabel());
						Collection<Field> cf = new ArrayList<Field>();
						for (Attribute att : asp.getAllAttributesWithoutAspectsAttributes()) {
							Field field = ClassDiagramUtils.getFieldForAttribute(att);
							if (field != null) {
								cf.add(field);
							}
						}
						// manage associations defined for aspects
						for (Association ass : asp.getAllSourceAssociations()) {
							cf.add(ClassDiagramUtils.transformAssociationIntoModelChoiceField(ass, asp));
						}

						if (cf.size() > 0 || fa.getChildren() != null) {
							fa.getChildren().addAll(cf);
						}
						c.add(fa);
					}
					// Associations :
					for (Association ass : clazz.getAllSourceAssociations()) {
						c.add(ClassDiagramUtils.transformAssociationIntoModelChoiceField(ass, clazz));

					}

					// Operations :
					for (OperationComponent op : clazz.getOperations()) {
						Field field = ClassDiagramUtils.getFieldForOperation(op);
						c.add(field);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return c;
	}
}
