package com.bluexml.side.form.clazz.utils;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.common.OperationComponent;
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
			if(fc.getChildren().size() > 0) {
				doWork = UIUtils.showConfirmation("Class already set","Class have already been set. Do you want to overwrite it?");
			}

			if (doWork) {
				Command initCmd = initializeAttributeFormClass(fc,domain);
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
			UIUtils.showError("No Class defined","No class have been defined. \n"
					+ "Choose one and run Initiliaze again.");
		}
		return cc;
	}

	/**
	 * Will rerturn a compoundCommand with setcommand if needed.
	 * @param fc
	 * @param domain
	 * @return
	 */
	private static Command initializeAttributeFormClass(FormClass fc, EditingDomain domain) {
		CompoundCommand cc = new CompoundCommand();
		if (fc.getReal_class() != null) {
			Clazz c = fc.getReal_class();
			String name = c.getName();
			String label = (c.getLabel().length() > 0 ? c.getLabel() : c.getName());

			if (fc.getLabel() == null || fc.getLabel().length() == 0) {
				cc.append(SetCommand.create(domain, fc, FormPackage.eINSTANCE.getFormElement_Label(), label));
			}
			if (fc.getId() == null || fc.getId().length() == 0) {
				cc.append(SetCommand.create(domain, fc, FormPackage.eINSTANCE.getFormElement_Id(), name));
			}
		}
		return cc;
	}

	public static Collection<FormElement> getChildForFormClassFromClazz(
			FormClass fc) {
		fc.getDisabled().clear();
		fc.getChildren().removeAll(fc.getChildren());
		Clazz cl = fc.getReal_class();
		Collection<FormElement> c = new ArrayList<FormElement>();

		if (cl != null) {
			Clazz clazz = cl;
				// Attributes

				for (Attribute att : clazz.getAllAttributesWithoutAspectsAttributes()) {
					Field field = null;
					field = ClassDiagramUtils.getFieldForAttribute(att);
					if (field != null) {
						c.add(field);
					}
				}

				// Aspects
				for (Aspect asp : clazz.getAllAspects()) {
					FormAspect fa = FormFactory.eINSTANCE.createFormAspect();
					fa.setRef(asp);
					fa.setId(asp.getName());
					fa.setLabel(asp.getLabel());
					Collection<Field> cf = new ArrayList<Field>();
					for (Attribute att : asp.getAttributes()) {
						Field field = ClassDiagramUtils.getFieldForAttribute(att);
						if (field != null) {
							cf.add( field);
						}
					}
					if (cf.size() > 0 || fa.getChildren() != null) {
						fa.getChildren().addAll(cf);
					}
					c.add(fa);
				}

				// Associations :
				for (Association ass : clazz.getAllSourceAssociations()) {
					c.add(ClassDiagramUtils.transformAssociationIntoModelChoiceField(ass,clazz));

				}

				// Operations :
				for (OperationComponent op : clazz.getOperations()) {
					Field field = ClassDiagramUtils.getFieldForOperation(op);
					c.add(field);
				}
			}

		return c;
	}
}
