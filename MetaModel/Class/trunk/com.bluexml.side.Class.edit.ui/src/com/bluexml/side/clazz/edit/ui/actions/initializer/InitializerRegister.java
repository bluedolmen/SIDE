package com.bluexml.side.clazz.edit.ui.actions.initializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.application.Application;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.ModelElement;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelCreator.ASK_USER;
import com.bluexml.side.util.libs.IFileHelper;

public class InitializerRegister {

	Map<Class<? extends ModelCreator>, Map<String, ModelCreator>> registermap;

	public void recordInitializer(String key, ModelCreator init) {
		Class<? extends ModelCreator> class1 = init.getClass();
		Map<String, ModelCreator> map;
		if (registermap.containsKey(class1)) {
			map = registermap.get(class1);
		} else {
			map = new HashMap<String, ModelCreator>();
			registermap.put(class1, map);
		}
		map.put(key, init);
	}

	public Set<ModelCreator> getAllInitializer() {
		Set<ModelCreator> x = new HashSet<ModelCreator>();
		Collection<Map<String, ModelCreator>> values = registermap.values();
		for (Map<String, ModelCreator> map : values) {
			x.addAll(map.values());
		}
		return x;
	}

	public Map<String, ModelCreator> getInitializers(Class<? extends ModelCreator> c) {
		return registermap.get(c);
	}

	public InitializerRegister() {
		this.registermap = new HashMap<Class<? extends ModelCreator>, Map<String, ModelCreator>>();
	}

	public static InitializerRegister getDefaultInitializerRegister(IFile classModel, ClassPackage root, ASK_USER ask) throws Exception {
		InitializerRegister register = new InitializerRegister();
		getInitializerForClassModel(register, classModel, root, ask);
		return register;
	}

	public static InitializerRegister getInitializerForClassModel(InitializerRegister register, IFile classModel, ClassPackage root, ASK_USER ask) throws Exception {

		register.recordInitializer("", new ViewModelInitializer(classModel, root, register, ask, null)); //$NON-NLS-1$

		register.recordInitializer("", new FormModelInitializer(classModel, root, register, ask, "default.form")); //$NON-NLS-1$ //$NON-NLS-2$

		register.recordInitializer("anotherFormCollection.form", new FormModelInitializer(classModel, root, register, ask, "anotherFormCollection.form")); //$NON-NLS-1$ //$NON-NLS-2$

		register.recordInitializer("", new PortalModelInitializer(classModel, root, register, ASK_USER.SKIP)); //$NON-NLS-1$

		return register;
	}

	public static InitializerRegister getFormWorkFlowInitializerRegister(IFile classModel, ASK_USER ask) throws Exception {
		InitializerRegister register = new InitializerRegister();

		register.recordInitializer("", new FormWorkflowModelInitializer(classModel, register, ask, null));

		return register;
	}

	public static InitializerRegister getInitializerRegister(IFile classModel, String alf_home, String alf_ver) throws Exception {
		InitializerRegister initilizerRegister = null;
		ClassPackage cp = (ClassPackage) openModel(classModel);

		initilizerRegister = InitializerRegister.getDefaultInitializerRegister(classModel, cp, ASK_USER.OVERRIDE);

		ModelInitializer applicationModelInit = new ApplicationModelInitializer(classModel, cp, initilizerRegister, ASK_USER.OVERRIDE, null, alf_ver, alf_home);

		// add ApplicationInitializer to register
		initilizerRegister.recordInitializer("", applicationModelInit); //$NON-NLS-1$

		return initilizerRegister;
	}

	public static InitializerRegister getInitializerRegister(IFile applicationModel) throws Exception {
		Application app = (Application) openModel(applicationModel);

		// search for  dt models and workflow models
		List<Model> dts = new ArrayList<Model>();
		List<Model> wks = new ArrayList<Model>();

		EList<ModelElement> elements = app.getElements();
		for (ModelElement modelElement : elements) {
			if (modelElement instanceof Model) {
				Model model = (Model) modelElement;
				String replaceFirst = model.getFile().replaceFirst("/.*(\\..*)", "$1");
				if (replaceFirst.equals(".dt")) {
					dts.add(model);
				} else if (replaceFirst.equals(".workflow")) {
					wks.add(model);
				}
			}
		}
		return InitializerRegister.getInitializerRegister(dts, wks);
	}

	public static InitializerRegister getInitializerRegister(List<Model> dts, List<Model> wks) throws Exception {
		InitializerRegister initilizerRegister = new InitializerRegister();
		// create initializers

		for (Model modelElement : wks) {
			String file = modelElement.getFile();
			IFile iFile = IFileHelper.getIFile(file);
			initilizerRegister.recordInitializer("", new FormWorkflowModelInitializer(iFile, initilizerRegister, ASK_USER.UPDATE, null));
		}

		for (Model modelElement : dts) {
			String file = modelElement.getFile();
			IFile iFile = IFileHelper.getIFile(file);
			System.out.println("converted to IFile :" + iFile);
			ClassPackage cp = (ClassPackage) openModel(iFile);
			getInitializerForClassModel(initilizerRegister, iFile, cp, ASK_USER.OVERRIDE);
			ModelInitializer applicationModelInit = new ApplicationModelInitializer(iFile, cp, initilizerRegister, ASK_USER.UPDATE, null, null, null);
			initilizerRegister.recordInitializer("", applicationModelInit); //$NON-NLS-1$
		}
		return initilizerRegister;
	}

	public void initialize() throws Exception, CoreException {
		// ModelCreator use a cascading initialization that use this register
		// so we execute each ModelCreator
		Set<ModelCreator> allInitializer = getAllInitializer();
		for (ModelCreator modelInitializer : allInitializer) {
			modelInitializer.initialize();
		}
	}

	private static EObject openModel(IFile classModel) throws IOException {
		EList<EObject> l = ModelInitializationUtils.openModel(classModel);
		return l.get(0);
	}
}
