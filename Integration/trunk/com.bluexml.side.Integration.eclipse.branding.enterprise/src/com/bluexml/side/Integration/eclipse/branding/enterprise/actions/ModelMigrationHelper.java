package com.bluexml.side.Integration.eclipse.branding.enterprise.actions;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectEList;

import com.bluexml.side.common.NameSpace;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.integration.eclipse.builder.settings.SIDEBuilderConfiguration;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.libs.ecore.EResourceUtils;

public class ModelMigrationHelper {

	public static IFile getApplication(IProject project) {

		SIDEBuilderConfiguration sideconf = new SIDEBuilderConfiguration(project);
		if (sideconf.load()) {
			return sideconf.getApplicationFile();
		}
		return null;
	}

	public static void updateProject(IProject source, IProject target, boolean preview) throws Exception {
		List<File> models = getModels(source);
		List<File> modelsTarget = getModels(target);

		for (File file : models) {
			List<File> modelsTarget_filtred = new ArrayList<File>();
			for (File fileT : modelsTarget) {
				if (FileHelper.getFileExt(fileT).equals(FileHelper.getFileExt(file))) {
					modelsTarget_filtred.add(fileT);
				}
			}
			updateModel(file, modelsTarget_filtred, preview);
		}

	}

	protected static List<File> getModels(IProject source) {
		File projectHome = IFileHelper.convertIRessourceToFile(source);
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File file, String name) {
				boolean ok = false;
				String[] accepted = { ".dt", ".portal" };
				for (String string : accepted) {
					if (name.endsWith(string)) {
						ok = true;
						break;
					}
				}
				return file.isFile() && ok;
			}
		};
		List<File> files = FileHelper.listAll(projectHome);
		List<File> models = new ArrayList<File>();
		for (File file : files) {
			if (filter.accept(file, file.getName())) {
				models.add(file);
			}
		}
		return models;
	}

	public static void updateModel(File file, List<File> modelsTarget, boolean preview) throws Exception {
		System.out.println("ModelMigrationHelper.updateModel() on " + file);
		// load ECore resource
		IFile model = IFileHelper.getIFile(file);
		EList<EObject> openModel = EResourceUtils.openModel(model);
		EObject eObject = openModel.get(0);

		//		System.out.println("ModelMigrationHelper.updateModel() model root :" + eObject);
		TreeIterator<EObject> eAllContents = eObject.eAllContents();
		while (eAllContents.hasNext()) {
			EObject eObject2 = (EObject) eAllContents.next();
			//			System.out.println("ModelMigrationHelper.updateModel() " + eObject2);
			for (EStructuralFeature esf : eObject2.eClass().getEAllStructuralFeatures()) {
				//				System.out.println("ModelMigrationHelper.updateModel() search on feature " + esf.getName());
				Object o = eObject2.eGet(esf, false);
				Object o_resolved = eObject2.eGet(esf, true);
				if (o instanceof List<?>) {
					if (o instanceof EObjectEList) {

						// System.out.println("ModelMigrationHelper.updateModel() le truc est une list de truc");
						EObjectEList<EObject> l = (EObjectEList<EObject>) o;
						EObjectEList<EObject> l_r = (EObjectEList<EObject>) o_resolved;
						Map<Integer, Object> toReplace = new HashMap<Integer, Object>();

						for (EObject object : l.basicList()) {
							int indexOf = l.indexOf(object);
							EObject updateElement = updateElement(esf, object, l_r.get(indexOf), modelsTarget);
							if (updateElement != null) {

								toReplace.put(indexOf, updateElement);
							}
						}
						// replace
						Set<Entry<Integer, Object>> entrySet = toReplace.entrySet();
						for (Entry<Integer, Object> entry : entrySet) {

							Integer index = entry.getKey();
							EObject updateElement = (EObject) entry.getValue();
							System.out.println("ModelMigrationHelper.updateModel() REPLACE " + o + " by " + updateElement);
							l.remove(index);

							l.add(index, updateElement);
						}
					} else {
						System.out.println("ModelMigrationHelper.updateModel() o: " + eObject2 + " for :" + esf.getName());
					}
				} else {
					if (o instanceof EObject) {
						EObject updateElement = updateElement(esf, (EObject) o, (EObject) o_resolved, modelsTarget);
						if (updateElement != null) {
							System.out.println("ModelMigrationHelper.updateModel() REPLACE " + o + " by " + updateElement);
							eObject2.eSet(esf, updateElement);
						}
					}
				}
			}
		}
		if (preview) {
			IContainer parent = model.getParent();
			IFile createFile = IFileHelper.createFile(parent, "preview_" + model.getName());
			EResourceUtils.saveModel(createFile, eObject);
		} else {
			EResourceUtils.saveModel(model, eObject);
		}

	}

	protected static EObject updateElement(EStructuralFeature esf, EObject eo2, EObject resolved, List<File> modelsTarget) throws IOException {
		if (eo2.eIsProxy()) {
			//			System.out.println("ModelMigrationHelper.updateModel() search on feature " + esf.getName());
			if (eo2 instanceof InternalEObject) {
				//				System.out.println("ModelMigrationHelper.updateModel() le truc est un proxy interne de machin " + eo2);
				// search in target for the same object
				return searchForSameObjectIn(resolved, modelsTarget);

			} else {
				//				System.out.println("ModelMigrationHelper.updateModel() le truc est un proxy externe de machin " + eo2);
			}
		} else {
			//							System.out.println("ModelMigrationHelper.updateModel() le truc est un machin" + eo2);
		}
		return null;
	}

	protected static EObject searchForSameObjectIn(EObject object, List<File> modelsTarget) throws IOException {
		EObject target = null;
		for (File file : modelsTarget) {
			target = searchForSameObjectIn(object, file);
			if (target != null) {
				break;
			}
		}
		return target;
	}

	protected static EObject searchForSameObjectIn(EObject object, File modelsTarget) throws IOException {
		IFile model = IFileHelper.getIFile(modelsTarget);
		EList<EObject> openModel = EResourceUtils.openModel(model);
		EObject eObject = openModel.get(0);
		System.out.println("ModelMigrationHelper.updateModel() model root :" + eObject);
		TreeIterator<EObject> eAllContents = eObject.eAllContents();
		while (eAllContents.hasNext()) {
			EObject eObject2 = (EObject) eAllContents.next();
			if (equals(object, eObject2)) {
				return eObject2;
			}
		}
		return null;
	}

	public static boolean equals(EObject a, EObject b) {
		EClass aeClass = a.eClass();
		EClass beClass = b.eClass();
		//		System.out.println("ModelMigrationHelper.equals() a :" + a);
		//		System.out.println("ModelMigrationHelper.equals() b :" + b);
		boolean equals = aeClass.equals(beClass);

		if (equals) {
			if (a instanceof NamedModelElement && b instanceof NamedModelElement) {
				NamedModelElement namedModelElementA = (NamedModelElement) a;
				NamedModelElement namedModelElementB = (NamedModelElement) b;

				String fullNameA = namedModelElementA.getFullName();
				String fullNameB = namedModelElementB.getFullName();

				equals &= isInSameNS(namedModelElementA, namedModelElementB);
				equals &= fullNameA.equals(fullNameB);

				if (equals) {
					System.out.println("ModelMigrationHelper.equals() is NamedModelElement fullName a: " + fullNameA);
					System.out.println("ModelMigrationHelper.equals() is NamedModelElement fullName b: " + fullNameB);
				}
			} else {
				System.out.println("ModelMigrationHelper.equals() not NamedElement" + a);
				equals = false;
			}
		}
		return equals;
	}

	public static boolean isInSameNS(NamedModelElement a, NamedModelElement b) {
		return getCompliteNS(a).equals(getCompliteNS(b));
	}

	public static String getCompliteNS(NamedModelElement a) {
		NameSpace logicalNameSpace = a.getLogicalNameSpace();
		return "{" + logicalNameSpace.getURI() + "}" + logicalNameSpace.getPrefix();
	}
}
