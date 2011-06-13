package com.bluexml.side.clazz.edit.ui.actions.initializer;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelInitializer.ASK_USER;

public class InitializerRegister {
	Map<String, FormModelInitializer> formInitializer;
	Map<String, PortalModelInitializer> portalInitializer;
	Map<String, ViewModelInitializer> viewInitializer;
	Map<String, ApplicationModelInitializer> applicationInitializer;
	public InitializerRegister(IFile classModel, ClassPackage root, ASK_USER ask) throws Exception {
		this.formInitializer = new HashMap<String, FormModelInitializer>();
		this.portalInitializer = new HashMap<String, PortalModelInitializer>();
		this.viewInitializer = new HashMap<String, ViewModelInitializer>();
		this.applicationInitializer = new HashMap<String, ApplicationModelInitializer>();
	}

	/**
	 * @return the formInitializer
	 */
	public Map<String, FormModelInitializer> getFormInitializer() {
		return formInitializer;
	}

	/**
	 * @return the portalInitializer
	 */
	public Map<String, PortalModelInitializer> getPortalInitializer() {
		return portalInitializer;
	}

	/**
	 * @return the viewInitializer
	 */
	public Map<String, ViewModelInitializer> getViewInitializer() {
		return viewInitializer;
	}

	
	/**
	 * @return the applicationInitializer
	 */
	public Map<String, ApplicationModelInitializer> getApplicationInitializer() {
		return applicationInitializer;
	}

	public static InitializerRegister getDefaultInitializerRegister(IFile classModel, ClassPackage root, ASK_USER ask) throws Exception {
		InitializerRegister register = new InitializerRegister(classModel, root, ask);

		register.getViewInitializer().put("", new ViewModelInitializer(classModel, root, register, ask, null));

		register.getFormInitializer().put("", new FormModelInitializer(classModel, root, register, ask, "default.form"));

		register.getFormInitializer().put("anotherFormCollection.form", new FormModelInitializer(classModel, root, register, ask, "anotherFormCollection.form"));

		register.getPortalInitializer().put("", new PortalModelInitializer(classModel, root, register, ask));

		return register;
	}
}
