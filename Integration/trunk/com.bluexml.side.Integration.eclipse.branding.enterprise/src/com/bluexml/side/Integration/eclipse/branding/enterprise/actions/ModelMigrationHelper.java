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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.bluexml.side.application.Application;
import com.bluexml.side.application.ComponantConfiguration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.GeneratorConfiguration;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.ModuleConstraint;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.clazz.EnumerationLiteral;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NameSpace;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.integration.eclipse.builder.settings.SIDEBuilderConfiguration;
import com.bluexml.side.portal.Column;
import com.bluexml.side.portal.HavePortlet;
import com.bluexml.side.portal.InstanciatePortletType;
import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.PortletAttribute;
import com.bluexml.side.portal.PortletType;
import com.bluexml.side.util.alfresco.tools.ModelLibrary;
import com.bluexml.side.util.antrunner.AntFileGeneratorAction;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.libs.ecore.EResourceUtils;

public class ModelMigrationHelper {

	List<File> loadedFiles = new ArrayList<File>();

	List<ComparableEObject> index = new ArrayList<ModelMigrationHelper.ComparableEObject>();

	Map<File, List<ComparableEObject>> map = new HashMap<File, List<ComparableEObject>>();

	public IFile getApplication(IProject project) {

		SIDEBuilderConfiguration sideconf = new SIDEBuilderConfiguration(project);
		if (sideconf.load()) {
			return sideconf.getApplicationFile();
		}
		return null;
	}

	public void updateProject(final IProject source, IProject target, String libraryId, boolean makeCopy, IProgressMonitor monitor2) throws Exception {
		System.out.println("ModelMigrationHelper.updateProject() source :"+source.getName());
		System.out.println("ModelMigrationHelper.updateProject() target :"+target.getName());
		List<File> models = getModels(source);
		List<File> diagrams = getDiagrams(source);
		List<File> applicationModels = getApplicationModels(source);

		List<File> mavenModules = getMavenModules(source);

		List<File> modelsTarget = getModels(target);

		//		System.out.println("ModelMigrationHelper.updateProject() models :" + models.size());
		//		System.out.println("ModelMigrationHelper.updateProject() diagrams :" + diagrams.size());
		//		System.out.println("ModelMigrationHelper.updateProject() applicationModels :" + applicationModels.size());
		//		System.out.println("ModelMigrationHelper.updateProject() mavenModules :" + mavenModules.size());
		//		System.out.println("ModelMigrationHelper.updateProject() targetModels :" + modelsTarget.size());

		monitor2.beginTask("updating project", models.size() + applicationModels.size() + mavenModules.size() + diagrams.size());
		// System.out.println("ModelMigrationHelper.updateProject() models to update :" + models.size());
		monitor2.subTask("models references");
		for (File file : models) {
			if (monitor2.isCanceled()) {
				return;
			}
			updateModel(file, modelsTarget, monitor2);
			monitor2.worked(1);
		}

		monitor2.subTask("diagrams references");
		for (File file : diagrams) {
			if (monitor2.isCanceled()) {
				return;
			}
			updateModel(file, modelsTarget, monitor2);
			monitor2.worked(1);
		}

		monitor2.subTask("application model");
		for (File file : applicationModels) {
			if (monitor2.isCanceled()) {
				return;
			}
			if (makeCopy) {
				updateApplicationModel(file, libraryId, source);
			} else {
				updateApplicationModel(file, libraryId, null);
			}
			AntFileGeneratorAction.generate(IFileHelper.getIFile(file));
			monitor2.worked(1);
		}

		if (makeCopy) {
			// check update project configuration
			SIDEBuilderConfiguration conf = new SIDEBuilderConfiguration(source);
			conf.load();
			String applicationRessourcePath = conf.getApplicationRessourcePath();
			String match = "(" + Pattern.quote("${workspace_loc:/") + ")" + "([^/]*)" + "(" + Pattern.quote("/") + ".*" + Pattern.quote("}") + ")";
			if (applicationRessourcePath.matches(match)) {
				Pattern p = Pattern.compile(match);
				Matcher matcher = p.matcher(applicationRessourcePath);
				matcher.find();
				String group1 = matcher.group(1);
				String group3 = matcher.group(3);
				String replace = group1 + source.getName() + group3;
				conf.setApplicationRessourcePath(replace);
				conf.reload();
				conf.persist();
			} else {
				
			}
		}

		monitor2.subTask("maven modules");
		ModelLibrary modellib = new ModelLibrary(libraryId);
		for (File file : mavenModules) {
			if (!monitor2.isCanceled()) {
				return;
			}
			String newVersion = modellib.getMavenFrameworkVersion();
			String newClassifier = modellib.getMavenFrameworkClassifier();

			String[] groupIds = modellib.getMavenFrameworkGroup().split(",");
			if (StringUtils.trimToNull(newClassifier) != null && StringUtils.trimToNull(newVersion) != null && StringUtils.trimToNull(libraryId) != null) {
				PomMigrationHelper.updateMavenPom(file, groupIds, newVersion, newClassifier);
			}
		}
	}

	/**
	 * we make the assumption that componentId are somethings like
	 * &lt;id&gt;&lt;version&gt;, and version start by a digit
	 * 
	 * @param componentsId
	 * @param currentId
	 * @return
	 */
	protected String getTargetComponentId(List<String> componentsId, String currentId) {
		String root = currentId.replaceFirst("[0-9]+.*", "");
		for (String string : componentsId) {
			String prefix = string.replaceFirst("[0-9]+.*", "");
			if (root.equals(prefix)) {
				//				System.out.println("ModelMigrationHelper.getTargetComponentId() matchs :" + currentId + " -> " + string);
				return string;
			}
		}

		return null;
	}

	/**
	 * 
	 * @param file
	 * @param libraryId
	 * @param source, can be null if project copy is disabled
	 * @throws IOException
	 * @throws CoreException
	 * @throws Exception
	 */
	protected void updateApplicationModel(File file, String libraryId, IProject source) throws IOException, CoreException, Exception {

		IFile model = IFileHelper.getIFile(file);
		EList<EObject> openModel = EResourceUtils.openModel(model);
		EObject eObject = openModel.get(0);
		TreeIterator<EObject> eAllContents = eObject.eAllContents();
		while (eAllContents.hasNext()) {
			EObject eObject2 = (EObject) eAllContents.next();

			if (eObject2 instanceof ComponantConfiguration) {
				List<String> availableComponents = ApplicationUtil.getAvailableComponents(libraryId);
				ComponantConfiguration generatorConfiguration = (ComponantConfiguration) eObject2;
				String id = generatorConfiguration.getId();
				String targetComponentId = getTargetComponentId(availableComponents, id);
				if (targetComponentId != null) {
					generatorConfiguration.setId(targetComponentId);
				}
				if (eObject2 instanceof GeneratorConfiguration && id.equals("side.customModules")) {
					// need to update the techVersion
					GeneratorConfiguration gen = (GeneratorConfiguration) eObject2;
					EList<ModuleConstraint> moduleContraints = gen.getModuleContraints();
					for (ModuleConstraint moduleConstraint : moduleContraints) {
						moduleConstraint.setTechnologyVersion(libraryId);
					}
				}
			} else if (eObject2 instanceof Model && source != null) {
				// check models list 
				// disabled models are not renamed
				Model modelItem = (Model) eObject2;
				String file2 = modelItem.getFile();
				String[] split = file2.split("/");
				String toreplace = split[1];

				modelItem.setFile(file2.replaceFirst(toreplace, source.getName()));
			} else if (eObject2 instanceof ConfigurationParameters && source != null) {
				ConfigurationParameters conf = (ConfigurationParameters) eObject2;
				if ("generation.options.logPath".equals(conf.getKey()) || "generation.options.destinationPath".equals(conf.getKey())) {
					String val = conf.getValue().split("/")[1];
					conf.setValue(conf.getValue().replaceFirst(val, source.getName()));
				}
			}
		}

		EResourceUtils.saveModel(model, eObject);
		// apply autoUpdate that check and fix options and modules according to componentId
		ApplicationUtil.updateApplicationFromExtensionPoint((Application) eObject, model);

	}

	public static List<File> getModels(IProject source) {
		String[] accepted = { ".dt", ".portal", ".form", ".view", ".workflow" };
		return getModels(source, accepted);
	}

	public static List<File> getDiagrams(IProject source) {
		String[] accepted = { ".dtdi", ".portaldi", ".workflowdi" };
		return getModels(source, accepted);
	}

	public static List<File> getApplicationModels(IProject source) {
		String[] accepted = { ".application" };
		return getModels(source, accepted);
	}

	public static List<File> getMavenModules(IProject source) {
		String[] accepted = { "pom.xml" };
		return getModels(source, accepted);
	}
	
	public static List<File> getModels(IProject source, final String[] fileExt) {
		File projectHome = IFileHelper.convertIRessourceToFile(source);
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File file, String name) {
				boolean ok = false;

				for (String string : fileExt) {
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

	public void updateModel(File file, List<File> modelsTarget, IProgressMonitor monitor2) throws Exception {
		// System.out.println("ModelMigrationHelper.updateModel() on " + file);
		// load ECore resource
		IFile model = IFileHelper.getIFile(file);
		EList<EObject> openModel = EResourceUtils.openModel(model);
		EObject eObject = openModel.get(0);

		// System.out.println("ModelMigrationHelper.updateModel() model root :" + eObject);
		TreeIterator<EObject> eAllContents = eObject.eAllContents();

		while (eAllContents.hasNext()) {
			EObject eObject2 = (EObject) eAllContents.next();
			// System.out.println("ModelMigrationHelper.updateModel() " + eObject2);
			for (EStructuralFeature esf : eObject2.eClass().getEAllStructuralFeatures()) {

				// System.out.println("ModelMigrationHelper.updateModel() search on feature " + esf.getName());
				Object o = eObject2.eGet(esf, false);
				Object o_resolved = eObject2.eGet(esf, true);
				try {
					if (o != null || o_resolved != null) {
						if (o instanceof List<?>) {
							if (o instanceof EObjectEList) {

								//								System.out.println("ModelMigrationHelper.updateModel() le truc est une list de truc");
								EObjectEList<EObject> l = (EObjectEList<EObject>) o;
								EObjectEList<EObject> l_r = (EObjectEList<EObject>) o_resolved;
								Map<Object, Object[]> toReplace = new HashMap<Object, Object[]>();

								for (EObject object : l.basicList()) {
									int indexOf = l.indexOf(object);
									EObject updateElement = updateElement(esf, object, l_r.get(indexOf), modelsTarget);
									if (updateElement != null) {
										//										System.out.println("ModelMigrationHelper.updateModel() indexof :" + indexOf);
										toReplace.put(l_r.get(indexOf), new Object[] { indexOf, updateElement });
									}
								}
								// replace
								Set<Entry<Object, Object[]>> entrySet = toReplace.entrySet();
								for (Entry<Object, Object[]> entry : entrySet) {

									Object object = entry.getKey();
									EObject updateElement = (EObject) entry.getValue()[1];
									//									System.out.println("ModelMigrationHelper.updateModel() REPLACE " + object + " by " + updateElement);

									// System.out.println("ModelMigrationHelper.updateModel() BEFORE size :" + l.size());
									int indexOf = (Integer) entry.getValue()[0];
									l.add(indexOf, updateElement);
									// System.out.println("ModelMigrationHelper.updateModel() ADD size :" + l.size());
									l.remove(object);
									// System.out.println("ModelMigrationHelper.updateModel() REMOVE " + remove + " size :" + l.size());

								}
							} else {
								//								System.err.println("ModelMigrationHelper.updateModel() NOT Managed o: " + o + " for :" + esf.getName());
							}
						} else {
							if (o instanceof EObject) {
								EObject updateElement = updateElement(esf, (EObject) o, (EObject) o_resolved, modelsTarget);
								if (updateElement != null) {
									//									System.out.println("ModelMigrationHelper.updateModel() REPLACE " + o + " by " + updateElement);
									eObject2.eSet(esf, updateElement);
								}
							}
						}
					} else {
						//						System.out.println("ModelMigrationHelper.updateModel() null value in " + model + " on " + eObject2 + " ref :" + esf.getName());
					}
				} catch (EqualsException e) {
					throw new Exception("Please Check your models for missing references in " + model + " on " + eObject2 + " ref :" + esf.getName());
				}
			}
		}

		EResourceUtils.saveModel(model, eObject);

	}

	protected EObject updateElement(EStructuralFeature esf, EObject eo2, EObject resolved, List<File> modelsTarget) throws Exception {
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
			//			System.out.println("ModelMigrationHelper.updateModel() le truc est un machin" + eo2);
		}
		return null;
	}

	protected EObject searchForSameObjectIn(EObject object, List<File> modelsTarget) throws Exception {
		loadIndexes(modelsTarget);
		EObject target = null;
		for (File file : modelsTarget) {
			target = searchForSameObjectIn(object, file);
			if (target != null) {
				break;
			}
		}
		return target;
	}

	protected void loadIndexes(List<File> modelsTarget) throws IOException {
		for (File file : modelsTarget) {
			if (!map.containsKey(file)) {
				List<ComparableEObject> value = new ArrayList<ModelMigrationHelper.ComparableEObject>();
				IFile model = IFileHelper.getIFile(file);
				EList<EObject> openModel = EResourceUtils.openModel(model);
				EObject eObject = openModel.get(0);
				// System.out.println("ModelMigrationHelper.searchForSameObjectIn() model root :" + eObject);
				// System.out.println("ModelMigrationHelper.searchForSameObjectIn() Object to find :" + object);
				TreeIterator<EObject> eAllContents = eObject.eAllContents();
				while (eAllContents.hasNext()) {
					EObject eObject2 = (EObject) eAllContents.next();
					value.add(new ComparableEObject(eObject2));
				}
				map.put(file, value);
			}
		}
	}

	protected EObject searchForSameObjectIn(EObject object, File modelsTarget) throws Exception {

		List<ComparableEObject> list = map.get(modelsTarget);
		for (ComparableEObject comparableEObject : list) {
			if (new ComparableEObject(object).equals(comparableEObject)) {
				//				System.out.println("ModelMigrationHelper.searchForSameObjectIn() object matchs :" + eObject2);
				return comparableEObject.eObject;
			}
		}

		return null;
	}

	public static boolean equals(EObject a, EObject b) throws RootElementException {
		EClass aeClass = a.eClass();
		EClass beClass = b.eClass();
//		 System.out.println("ModelMigrationHelper.equals() a :" + a);
//		 System.out.println("ModelMigrationHelper.equals() b :" + b);
		boolean equals = aeClass.equals(beClass);

		if (equals) {
			if (a instanceof NamedModelElement && b instanceof NamedModelElement) {
				NamedModelElement namedModelElementA = (NamedModelElement) a;
				NamedModelElement namedModelElementB = (NamedModelElement) b;

				String fullNameA = namedModelElementA.getFullName();
				String fullNameB = namedModelElementB.getFullName();

				equals &= isInSameNS(namedModelElementA, namedModelElementB);
				
				if (a instanceof Column) {
					equals &= avoid40_42_missmatchs(fullNameA).equals(avoid40_42_missmatchs(fullNameB));
				} else {
					equals &= fullNameA.equals(fullNameB);
				}
				
				

			} else {

//				 System.out.println("ModelMigrationHelper.equals() not NamedElement " + a);

				if (a instanceof Portlet && b instanceof Portlet) {
					equals &= getRootName(a).equals(getRootName(b));
					// System.out.println("ModelMigrationHelper.equals() is Portlet");
					equals &= ((Portlet) a).getName().equals(((Portlet) b).getName());
				} else if (a instanceof Page && b instanceof Page) {
					equals &= getRootName(a).equals(getRootName(b));
					equals &= ((Page) a).getID().equals(((Page) b).getID());
				} else if (a instanceof PortletType && b instanceof PortletType) {
					equals &= getRootName(a).equals(getRootName(b));
					equals &= ((PortletType) a).getId().equals(((PortletType) b).getId());
					String name = StringUtils.trimToEmpty(((PortletType) a).getName());
					String name2 = StringUtils.trimToEmpty(((PortletType) b).getName());
					equals &= name.equals(name2);

				} else if (a instanceof PortletAttribute) {
					PortletAttribute portA = (PortletAttribute) a;
					PortletAttribute portB = (PortletAttribute) b;

					equals &= isInSameNS(portA, portB);
					equals &= portA.getName().equals(portB.getName());
					equals &= portA.getType().getName().equals(portB.getType().getName());

				} else if (a instanceof HavePortlet) {
					equals &= getRootName(a).equals(getRootName(b));

					HavePortlet hpA = (HavePortlet) a;

					Page associationPage = hpA.getAssociationPage();
					Portlet associationPortlet = hpA.getAssociationPortlet();

					HavePortlet hpB = (HavePortlet) b;
					Page associationPage2 = hpB.getAssociationPage();
					Portlet associationPortlet2 = hpB.getAssociationPortlet();

					equals &= equals(associationPage, associationPage2);
					equals &= equals(associationPortlet, associationPortlet2);
				} else if (a instanceof InstanciatePortletType) {
					InstanciatePortletType iptA = (InstanciatePortletType) a;
					InstanciatePortletType iptB = (InstanciatePortletType) b;

					equals &= equals(iptA.getPortletType(), iptB.getPortletType());
				} else if (a instanceof EnumerationLiteral) {
					Enumeration eContainerA = (Enumeration) a.eContainer();
					Enumeration eContainerB = (Enumeration) b.eContainer();
					equals &= equals(eContainerA, eContainerB);

					equals &= ((EnumerationLiteral) a).getName().equals(((EnumerationLiteral) b).getName());
					equals &= ((EnumerationLiteral) a).getValue().equals(((EnumerationLiteral) b).getValue());
				} else {
//					System.out.println("ModelMigrationHelper.equals() not managed to be compared NEED TO BE ADDED TO CONTROLER :" + a.getClass());
					throw new UnsupportedOperationException("equals on " + a.getClass() + " is not supported this need to be implemented");
				}
			}
		}
		return equals;
	}

	protected static String avoid40_42_missmatchs(String fullNameA) {
		String replaceAll = fullNameA.replaceAll("\\.@markup", "");
		return replaceAll;
	}

	public static boolean isInSameNS(ModelElement a, ModelElement b) throws RootElementException {
		return getCompliteNS(a).equals(getCompliteNS(b));
	}

	public static String getCompliteNS(ModelElement a) throws RootElementException {
		NameSpace logicalNameSpace = a.getLogicalNameSpace();
		if (logicalNameSpace != null) {
			return "{" + logicalNameSpace.getURI() + "}" + logicalNameSpace.getPrefix();
		} else {
			String name = getRootName(a);
			if (name == null) {
				throw new RootElementException("Root element is Null, please to check and Fix your models");
			}
			// System.out.println("ModelMigrationHelper.getCompliteNS() no NS object so use rootElement name as NS :" + name);
			return name;
		}

	}

	protected static String getRootName(EObject a) {
		return ((NamedModelElement) EcoreUtil.getRootContainer(a, true)).getName();
	}

	class ComparableEObject {
		EObject eObject;

		public ComparableEObject(EObject eObject) {
			this.eObject = eObject;
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object arg0) {
			if (arg0 instanceof ComparableEObject) {
				try {
					return ModelMigrationHelper.equals(eObject, ((ComparableEObject) arg0).eObject);
				} catch (RootElementException e) {
					throw new EqualsException(e.getMessage(), e.getCause());
				}
			} else {
				return false;
			}
		}
	}
}
