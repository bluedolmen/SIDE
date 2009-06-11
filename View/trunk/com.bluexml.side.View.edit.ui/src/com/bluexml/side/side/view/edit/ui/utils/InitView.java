package com.bluexml.side.side.view.edit.ui.utils;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.util.libs.ui.UIUtils;
import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.FieldElement;
import com.bluexml.side.view.ViewPackage;

public class InitView {

	public static Command init(AbstractView av, EditingDomain domain) {
		CompoundCommand cmd = new CompoundCommand();;
		if (av.getViewOf() != null) {
			boolean doWork = true;
			if (av.getChildren().size() > 0) {
				doWork = UIUtils.showConfirmation("View already set","View have already been set. Do you want to overwrite it?");
			}
			if (doWork) {
				if (av.getChildren().size() > 0) {
					cmd.append(RemoveCommand.create(domain, av.getChildren()));
				}
				if (av.getDisabled().size() > 0) {
					cmd.append(RemoveCommand.create(domain, av.getDisabled()));
				}
				
				if (av.getViewOf() instanceof Clazz) {
					Collection<FieldElement> c = getViewElementForClass((Clazz) av.getViewOf());
					cmd.append(AddCommand.create(domain, av, ViewPackage.eINSTANCE.getFieldGroup_Children(), c));
				}
			}
		} else {
			UIUtils.showError("No Class defined","No class have been defined. \n"
					+ "Choose one and run Initiliaze again.");
		}
		return null;
	}

	private static Collection<FieldElement> getViewElementForClass(Clazz c) {
		Collection<FieldElement> list = new ArrayList<FieldElement>();
		if (c != null) {
			// Attributes :
			for (Attribute a : c.getAllAttributes()) {
				
			}
		}
		return list;
	}

}
