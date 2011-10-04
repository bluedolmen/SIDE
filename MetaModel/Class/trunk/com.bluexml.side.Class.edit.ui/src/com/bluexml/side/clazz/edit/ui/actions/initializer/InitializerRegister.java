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
import com.bluexml.side.Util.ecore.SIDEEditorUtils;
import com.bluexml.side.application.Application;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.ModelElement;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelCreator.ASK_USER;
import com.bluexml.side.util.libs.IFileHelper;

public class InitializerRegister {
	
	public static final String DEFAULT_INITIALIZER_KEY = ""; // $NON-NLS-1$
	public static final String DEFAULT_ANOTHER_FORM_COLLECTION = "anotherFormCollection.form"; // $NON-NLS-1$
	private static final String DATA_MODEL_EXTENSION = ModelInitializationUtils.getExtensionForEditorId(SIDEEditorUtils.DATA_MODEL_EDITOR_ID);
	private static final String WORKFLOW_MODEL_EXTENSION = ModelInitializationUtils.getExtensionForEditorId(SIDEEditorUtils.WORKFLOW_MODEL_EDITOR_ID);

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
		fillInitializerForClassModel(register, classModel, root, ask);
		return register;
	}

	private static void fillInitializerForClassModel(InitializerRegister register, IFile classModel, ClassPackage root, ASK_USER ask) throws Exception {

		register.recordInitializer(DEFAULT_INITIALIZER_KEY, new ViewModelInitializer(classModel, root, register, ask, null));

		register.recordInitializer(DEFAULT_INITIALIZER_KEY, new FormModelInitializer(classModel, root, register, ask, "default.form")); //$NON-NLS-1$
		
		register.recordInitializer(DEFAULT_INITIALIZER_KEY, new FormSearchModelInitializer(classModel, root, register, ask, "search.form")); //$NON-NLS-1$

		register.recordInitializer(DEFAULT_ANOTHER_FORM_COLLECTION, new FormModelInitializer(classModel, root, register, ask, DEFAULT_ANOTHER_FORM_COLLECTION));

		register.recordInitializer(DEFAULT_INITIALIZER_KEY, new PortalModelInitializer(classModel, root, register, ASK_USER.OVERRIDE));

	}

	public static InitializerRegister getFormWorkFlowInitializerRegister(IFile classModel, ASK_USER ask) throws Exception {
		InitializerRegister register = new InitializerRegister();

		register.recordInitializer(DEFAULT_INITIALIZER_KEY, new FormWorkflowModelInitializer(classModel, register, ask, null));

		return register;
	}

	/**
	 * Retrieves an {@link InitializerRegister} from a given class-model passed
	 * as an {@link IFile}.
	 * <p>
	 * The path to Alfresco HOME should be provided as well as the Alfresco
	 * version (standardized form) in order to provide additional information
	 * for deployer configuration.
	 * <p>
	 * This method is used to initialize default models from a given class-model
	 * 
	 * @param classModel The class-model as an {@link IFile}
	 * @param alf_home the path to the home of Alfresco 
	 * @param alf_ver the version of Alfresco alf_home is targeting
	 * @return the {@link InitializerRegister}
	 * @throws Exception
	 */
	public static InitializerRegister getInitializerRegisterFromClassModel(IFile classModel, String alf_home, String alf_ver) throws Exception {
		InitializerRegister initilizerRegister = null;
		ClassPackage cp = (ClassPackage) openModel(classModel);

		initilizerRegister = InitializerRegister.getDefaultInitializerRegister(classModel, cp, ASK_USER.OVERRIDE);

		ModelInitializer applicationModelInit = new ApplicationModelInitializer(classModel, cp, initilizerRegister, ASK_USER.OVERRIDE, null, alf_ver, alf_home);

		// add ApplicationInitializer to register
		initilizerRegister.recordInitializer(DEFAULT_INITIALIZER_KEY, applicationModelInit);

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
				String replaceFirst = model.getFile().replaceFirst("/.*(\\..*)", "$1"); // $NON-NLS-1$ // $NON-NLS-2$
				if (replaceFirst.equals(DATA_MODEL_EXTENSION)) {
					dts.add(model);
				} else if (replaceFirst.equals(WORKFLOW_MODEL_EXTENSION)) {
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
			initilizerRegister.recordInitializer(DEFAULT_INITIALIZER_KEY, new FormWorkflowModelInitializer(iFile, initilizerRegister, ASK_USER.UPDATE, null));
		}

		for (Model modelElement : dts) {
			String file = modelElement.getFile();
			IFile iFile = IFileHelper.getIFile(file);
			System.out.println("converted to IFile :" + iFile);
			ClassPackage cp = (ClassPackage) openModel(iFile);
			fillInitializerForClassModel(initilizerRegister, iFile, cp, ASK_USER.OVERRIDE);
			ModelInitializer applicationModelInit = new ApplicationModelInitializer(iFile, cp, initilizerRegister, ASK_USER.UPDATE, null, null, null);
			initilizerRegister.recordInitializer(DEFAULT_INITIALIZER_KEY, applicationModelInit);
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
	
	public boolean isInitialized() {
		Set<ModelCreator> allInitializer = getAllInitializer();
		
		for (ModelCreator modelCreator : allInitializer) {
			if (!modelCreator.isInitialized()) {
				return false;
			}
		}
		
		return true;
	}
}
