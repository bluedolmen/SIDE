package com.bluexml.side.form.utils;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.Operation;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.Form;
import com.bluexml.side.form.FormAspect;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.formFactory;
import com.bluexml.side.form.formPackage;

public class ClassInitialization {
	
	public static Command initializeClass(FormClass fc, EditingDomain domain) {
		Command cmd = null;
		if (fc.getReal_class() != null) {
			boolean doWork = true;
			if(fc.getChildren().size() > 0) {
				doWork = UIUtils.showConfirmation("Class already set","Class have already been set. Do you want to overwrite it?");
			}
			
			if (doWork) {
				fc.getDisabled().clear();
				fc.getChildren().removeAll(fc.getChildren());
				Clazz cl = fc.getReal_class();
				if (fc.getLabel() == null || fc.getLabel().length() == 0) {
					fc.setLabel(cl.getTitle());
				}
				
				if (fc.getId() == null || fc.getId().length() == 0) {
					fc.setId(cl.getName());
				}
				
				if (((Form)fc.eContainer()).getName() == null || ((Form)fc.eContainer()).getName().length() == 0) {
					((Form)fc.eContainer()).setName(cl.getName());
				}
				
				Collection<FormElement> c = new ArrayList<FormElement>();
				Collection<Clazz> listClazz = new ArrayList<Clazz>();
				listClazz = ClassDiagramUtils.getInheritedClazzs(cl);
				for (Clazz Clazz : listClazz) {
					// Attributes
					for (Attribute att : Clazz.getAttributes()) {
						Field field = null;
						field = ClassDiagramUtils.getFieldForAttribute(att);
						c.add(field);
					}
					
					// Aspects
					for (Aspect asp : Clazz.getAspects()) {
						FormAspect fa = formFactory.eINSTANCE.createFormAspect();
						fa.setRef(asp);
						fa.setId(asp.getName());
						fa.setLabel(ClassDiagramUtils.getLabel(asp));
						Collection<Field> cf = new ArrayList<Field>();
						for (Attribute att : asp.getAttributes()) {
							Field field = ClassDiagramUtils.getFieldForAttribute(att);
							cf.add( field);
						}
						fa.getChildren().addAll(cf);
						c.add(fa);
					}
					
					// Associations :
					for (Association ass : Clazz.getAssociations()) {
						
						if (ass.getSource().equals(Clazz) && ass.isIsNavigableTARGET()) {
							c.add(ClassDiagramUtils.transformAssociationIntoModelChoiceField(ass,false));
						}
						if (ass.getDestination().equals(Clazz) && ass.isIsNavigableSRC()) {
							c.add(ClassDiagramUtils.transformAssociationIntoModelChoiceField(ass,true));
						}
					}
					
					// Operations :
					for (Operation op : Clazz.getOperations()) {
						Field field = ClassDiagramUtils.getFieldForOperation(op);
						c.add(field);
					}
				}
				cmd = AddCommand.create(domain, fc, formPackage.eINSTANCE.getFormGroup_Children(), c);
			}
		} else {
			UIUtils.showError("No Class defined","No class have been defined. \n"
					+ "Choose one and run Initiliaze again.");
		}
		return cmd;
	}
}
