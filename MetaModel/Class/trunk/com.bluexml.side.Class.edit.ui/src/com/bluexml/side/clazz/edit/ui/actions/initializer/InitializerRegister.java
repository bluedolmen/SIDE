package com.bluexml.side.clazz.edit.ui.actions.initializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
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

		register.getViewInitializer().put("", new ViewModelInitializer(classModel, root, register, ask, null)); //$NON-NLS-1$

		register.getFormInitializer().put("", new FormModelInitializer(classModel, root, register, ask, "default.form")); //$NON-NLS-1$ //$NON-NLS-2$

		register.getFormInitializer().put("anotherFormCollection.form", new FormModelInitializer(classModel, root, register, ask, "anotherFormCollection.form")); //$NON-NLS-1$ //$NON-NLS-2$

		register.getPortalInitializer().put("", new PortalModelInitializer(classModel, root, register, ask)); //$NON-NLS-1$

		return register;
	}
	
	public static InitializerRegister getInitializerRegister(IFile classModel, String alf_home, String alf_ver) throws Exception {
		InitializerRegister initilizerRegister = null;
		ClassPackage cp = openModel(classModel);

		initilizerRegister = InitializerRegister.getDefaultInitializerRegister(classModel, cp, ASK_USER.OVERRIDE);

		ApplicationModelInitializer applicationModelInit = new ApplicationModelInitializer(classModel, cp, initilizerRegister, ASK_USER.OVERRIDE, null, alf_ver, alf_home);
		// add ApplicationInitializer to register
		initilizerRegister.getApplicationInitializer().put("", applicationModelInit); //$NON-NLS-1$

		return initilizerRegister;
	}

	public void initialize() throws Exception, CoreException {
		for (ModelInitializer initializer : this.getViewInitializer().values()) {
			initializer.initialize();
		}
		for (ModelInitializer initializer : this.getFormInitializer().values()) {
			initializer.initialize();
		}
		for (ModelInitializer initializer : this.getPortalInitializer().values()) {
			initializer.initialize();
		}
		for (ModelInitializer initializer : this.getApplicationInitializer().values()) {
			initializer.initialize();
		}

	}

	private static ClassPackage openModel(IFile classModel) throws IOException {
		EList<?> l = ModelInitializationUtils.openModel(classModel);
		return (ClassPackage) l.get(0);
	}
}
