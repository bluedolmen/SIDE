package com.bluexml.xforms.generator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.bluexml.xforms.messages.MsgId;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.AssociationEnd;
import com.bluexml.side.clazz.AssociationType;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.clazz.impl.ClazzFactoryImpl;
import com.bluexml.side.common.CommonFactory;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.Package;
import com.bluexml.side.common.Stereotype;
import com.bluexml.side.common.impl.CommonFactoryImpl;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.impl.FormFactoryImpl;
import com.bluexml.side.util.componentmonitor.indy.CoreInterface;
import com.bluexml.side.workflow.impl.WorkflowFactoryImpl;
import com.bluexml.side.workflow.WorkflowPackage;
import com.bluexml.xforms.generator.DataGenerator.AssociationCardinality;
import com.bluexml.xforms.generator.DataGenerator.AssociationKind;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.tools.ClasseComparator;
import com.bluexml.xforms.generator.tools.ModelTools;

/**
 * The Class FormGenerator.
 */
public class FormGenerator {

	static {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("library",
				new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("form",
				new FormFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("clazz",
				new ClazzFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("workflow",
				new WorkflowFactoryImpl());

		EPackage.Registry.INSTANCE.put(org.eclipse.emf.ecore.EcorePackage.eNS_URI,
				org.eclipse.emf.ecore.EcorePackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(org.eclipse.ocl.ecore.EcorePackage.eNS_URI,
				org.eclipse.ocl.ecore.EcorePackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(ClazzPackage.eNS_URI, ClazzPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(FormPackage.eNS_URI, FormPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(WorkflowPackage.eNS_URI, WorkflowPackage.eINSTANCE);

		ClazzPackage.eINSTANCE.eClass();
		FormPackage.eINSTANCE.eClass();
		WorkflowPackage.eINSTANCE.eClass();
		org.eclipse.emf.ecore.EcorePackage.eINSTANCE.eClass();
		org.eclipse.ocl.ecore.EcorePackage.eINSTANCE.eClass();
	}

	private static final CommonFactory commonFactory = CommonFactoryImpl.init();

	/** The Constant ALFRESCO_NAME_ASSOCIATION. */
	public static final String ALFRESCO_NAME_ASSOCIATION = "alfrescoNameAssociation";

	/** The logger. */
	protected static Log logger = LogFactory.getLog(FormGenerator.class);

	/** The classes. */
	private Map<URI, EObject> eobjects = new HashMap<URI, EObject>();

	/** The resources. */
	private Map<URI, Resource> resources = new HashMap<URI, Resource>();

	private static Map<FormElement, String> uniqueNames = new HashMap<FormElement, String>();

	/** The class models. */
	private Map<URI, PackageInfo> classModels;

	/** The all enums. */
	private List<Enumeration> allEnums;

	/** The all aspects. */
	private List<Aspect> allAspects;

	/** The all classes. */
	private List<AbstractClass> allClasses;

	/** The all classes to render. */
	private List<AbstractClass> allClassesToRender;

	/** The all associations. */
	private List<Association> allAssociations;

	/** The all forms. */
	private List<FormContainer> allForms;

	/** The form collections. */
	private List<FormCollection> formCollections;

	/** The current generator. */
	private DataGenerator currentGenerator;

	/** The sub classes. */
	private Map<Clazz, Set<Clazz>> subClasses;

	/** The parent classes. */
	private Map<Clazz, Clazz> parentClasses;

	/** The alfresco name stereotype. */
	private Stereotype alfrescoNameStereotype;

	/** The real name stereotype. */
	private Stereotype realNameStereotype;

	private Log genLogger;

	private boolean simplifyClasses;

	private boolean renderDataBeforeWorkflow;

	private boolean autoSwitchToStandaloneMode;

	private String readOnlySuffix;

	private boolean generateReadOnlyForms;

	private boolean inReadOnlyMode;

	/**
	 * The Class PackageInfo.
	 */
	private class PackageInfo {

		/** The paquage. */
		Package paquage;

		/** The explicit. */
		boolean explicit;

		/**
		 * Instantiates a new package info.
		 * 
		 * @param paquage
		 *            the paquage
		 * @param explicit
		 *            the explicit
		 */
		public PackageInfo(Package paquage, boolean explicit) {
			super();
			this.paquage = paquage;
			this.explicit = explicit;
		}

	}

	/**
	 * The Class AssociationInfo.
	 */
	public class AssociationInfo {

		/** The real association. */
		public Association realAssociation;

		/** The source. */
		public Clazz source;

		/** The destination. */
		public Clazz destination;

		/** The source role. */
		public String sourceRole;

		/** The destination role. */
		public String destinationRole;

		/** Whether it's an auto association. */
		public boolean reverse;

		/**
		 * Instantiates a new association info.
		 * 
		 * @param realAssociation
		 *            the real association
		 * @param source
		 *            the source
		 * @param destination
		 *            the destination
		 * @param sourceRole
		 *            the source role
		 * @param destinationRole
		 *            the destination role
		 * @param reverse
		 *            the reverse
		 */
		public AssociationInfo(Association realAssociation, Clazz source, Clazz destination,
				String sourceRole, String destinationRole, boolean reverse) {
			super();
			this.realAssociation = realAssociation;
			this.source = source;
			this.destination = destination;
			this.sourceRole = sourceRole;
			this.destinationRole = destinationRole;
			this.reverse = reverse;
		}

	}

	public static String getClassQualifiedName(AbstractClass aClass) {
		String res = aClass.getFullName().replaceAll("\\.", "_");
		return res;
	}

	/**
	 * Builds the association name in the same way as the generator when producing the model.xml
	 * file.<br/>
	 * NOTE: this is prone to discrepancies and obsolescence.
	 * 
	 * @param asso
	 * @return
	 */
	public String getAssoQualifiedName(Association asso) {
		StringBuffer res = new StringBuffer(128);
		//** #979, #1273
		AssociationEnd srcEnd = (AssociationEnd) getRealObject(asso.getFirstEnd());
		AssociationEnd targetEnd = (AssociationEnd) getRealObject(srcEnd.getOpposite());
		// @Amenel: not sure whether linked classes may be proxies but getRealObject won't hurt
		AbstractClass srcClass = (AbstractClass) getRealObject(srcEnd.getLinkedClass());
		AbstractClass targetClass = (AbstractClass) getRealObject(targetEnd.getLinkedClass());
		//** #979, #1273
		// definition in Acceleo template used for writing this function
		// <%args(0).linkedClass.getQualifiedName()%>_<%name%><%if
		// (args(0).getOpposite().name !=
		// ""){%>_<%args(0).getOpposite().name%><%}%>_<%args(0).getOpposite().linkedClass.getQualifiedName()%>
		res.append(getClassQualifiedName(srcClass));
		res.append("_");
		res.append(asso.getName());
		if (targetEnd.getName() != "") {
			res.append("_");
			res.append(targetEnd.getName());
		}
		res.append("_");
		res.append(getClassQualifiedName(targetClass));

		return res.toString();
	}

	/**
	 * Gets the all packages.
	 * 
	 * @return the all packages
	 */
	private List<Package> getAllPackages() {
		List<Package> result = new ArrayList<Package>();
		Collection<PackageInfo> values = classModels.values();
		for (PackageInfo packageInfo : values) {
			result.add(packageInfo.paquage);
		}
		return result;
	}

	/**
	 * Gets the all packages to render.
	 * 
	 * @return the all packages to render
	 */
	private List<Package> getAllPackagesToRender() {
		List<Package> result = new ArrayList<Package>();
		Collection<PackageInfo> values = classModels.values();
		for (PackageInfo packageInfo : values) {
			if (packageInfo.explicit) {
				result.add(packageInfo.paquage);
			}
		}
		return result;
	}

	/**
	 * Instantiates a new form generator.
	 * 
	 * @param obls
	 *            the obls
	 * @param kerblueforms
	 *            the kerblueforms
	 * @param simplifyClasses
	 */
	public FormGenerator(File[] obls, File[] kerblueforms, Log genLogger, boolean simplifyClasses,
			boolean renderDataBeforeWorkflow) {
		super();
		if (genLogger != null) {
			this.genLogger = genLogger;
		} else {
			this.genLogger = LogFactory.getLog(FormGenerator.class);
		}
		this.simplifyClasses = simplifyClasses;
		this.setRenderDataBeforeWorkflow(renderDataBeforeWorkflow);
		XFormsGenerator.resetKeys();
		// be courteous to other classes
		// ** #1222: generation of read only forms
		com.bluexml.xforms.generator.forms.ModelElement.setFormGenerator(this);
		Renderable.setFormGenerator(this);
		// ** #1222
		try {
			classModels = new HashMap<URI, PackageInfo>();
			formCollections = new ArrayList<FormCollection>();

			if (obls != null && obls.length > 0) {
				for (File oblFile : obls) {
					// Load the model
					Resource resource = EResourceUtils.openModel(oblFile.getAbsolutePath(), null);
					Package model = ModelTools.getModel(resource);
					classModels.put(EcoreUtil.getURI(model).trimFragment(), new PackageInfo(model,
							true));
				}
			}

			if (kerblueforms != null) {
				for (File kerblueFormsFile : kerblueforms) {
					Resource resource = EResourceUtils.openModel(
							kerblueFormsFile.getAbsolutePath(), null);
					addClassModels(resource);
					FormCollection formCollection = ModelTools.getFormCollection(resource);
					formCollections.add(formCollection);
				}
			}
		} catch (Exception e) {
			logger.error("Please check file paths.");
			logger.error(e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds the class models.
	 * 
	 * @param resource
	 *            the resource
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws Exception
	 *             the exception
	 */
	private void addClassModels(Resource resource) throws IOException, Exception {
		Map<EObject, Collection<Setting>> references = EcoreUtil.ExternalCrossReferencer
				.find(resource);
		Set<Entry<EObject, Collection<Setting>>> referencesEntrySet = references.entrySet();
		for (Entry<EObject, Collection<Setting>> reference : referencesEntrySet) {
			EObject referencedObject = reference.getKey();
			URI uri = EcoreUtil.getURI(referencedObject).trimFragment();
			if (classModels.get(uri) == null) {
				Resource referencedResource = EResourceUtils.openModel(uri, null);
				Package model = ModelTools.getModel(referencedResource);
				if (model != null) {
					classModels.put(uri, new PackageInfo(model, false));
				}
			}
		}
	}

	/**
	 * Generate.
	 * 
	 * @param generators
	 *            the generators
	 */
	public void generate(List<DataGenerator> generators, CoreInterface monitor) {
		if (monitor != null) {
			monitor.setTaskName("Collecting data");
		}
		alfrescoNameStereotype = commonFactory.createStereotype();
		alfrescoNameStereotype.setName(ALFRESCO_NAME_ASSOCIATION);

		realNameStereotype = commonFactory.createStereotype();
		realNameStereotype.setName("realName");

		List<Package> allPackages = getAllPackages();
		allEnums = ModelTools.getAllEnums(allPackages);
		allAspects = ModelTools.getAllAspects(allPackages);

		List<Clazz> allClassesReal = ModelTools.getAllClasses(allPackages);
		allClasses = new ArrayList<AbstractClass>(allClassesReal);
		ModelTools.sortClasses(allClasses);

		List<Package> allPackagesToRender = getAllPackagesToRender();

		List<Clazz> allClassesToRenderReal = ModelTools.getAllClasses(allPackagesToRender);
		allClassesToRender = new ArrayList<AbstractClass>(allClassesToRenderReal);
		ModelTools.sortClasses(allClassesToRender);

		computeSubClasses(allClassesReal);
		allAssociations = ModelTools.getAllAssociations(allPackages);

		allForms = ModelTools.getAllForms(formCollections);

		// first pass: normal generation
		setInReadOnlyMode(false);
		for (DataGenerator dataGenerator : generators) {
			currentGenerator = dataGenerator;
			currentGenerator.setLogger(genLogger);
			currentGenerator.setMonitor(monitor);
			processGenerator();
		}

		if (isGenerateReadOnlyForms()) {
			// second pass: generation in readOnly mode
			setInReadOnlyMode(true);
			for (DataGenerator dataGenerator : generators) {
				if (dataGenerator.supportsReadOnlyMode()) {
					currentGenerator = dataGenerator;
					currentGenerator.setLogger(genLogger);
					currentGenerator.setReadOnlyMode(true);
					processGenerator();
				}
			}
		}
		logger.info("End of generation.");
		if (monitor != null) {
			monitor.addText("Xforms Generation completed successfully");
		}
	}

	/**
	 * Compute sub classes.
	 * 
	 * @param allClassesReal
	 *            the all classes real
	 */
	private void computeSubClasses(List<Clazz> allClassesReal) {
		subClasses = new TreeMap<Clazz, Set<Clazz>>(ClasseComparator.INSTANCE);
		parentClasses = new TreeMap<Clazz, Clazz>(ClasseComparator.INSTANCE);
		for (Clazz classe : allClassesReal) {
			EList<Clazz> generalizations = classe.getGeneralizations();
			for (Clazz generalization : generalizations) {
				if (generalization != null) {
					Clazz parentClasse = generalization;
					addSubClass(classe, parentClasse);
					parentClasses.put(classe, parentClasse);
				}
			}
		}
	}

	/**
	 * Adds the sub class.
	 * 
	 * @param classe
	 *            the classe
	 * @param parentClasse
	 *            the parent classe
	 */
	private void addSubClass(Clazz classe, Clazz parentClasse) {
		Set<Clazz> subSet = subClasses.get(parentClasse);
		if (subSet == null) {
			subSet = new TreeSet<Clazz>(ClasseComparator.INSTANCE);
			subClasses.put(parentClasse, subSet);
		}
		subSet.add(classe);
	}

	/**
	 * Gets the association type.
	 * 
	 * @param association
	 *            the association
	 * 
	 * @return the association type
	 */
	private AssociationCardinality getAssociationType(Association association) {
		AssociationCardinality result = AssociationCardinality.oneToOne;
		if (!isMany(association.getFirstEnd().getCardMax())) {
			if (!isMany(association.getSecondEnd().getCardMax())) {
				result = AssociationCardinality.oneToOne;
			} else {
				result = AssociationCardinality.oneToMany;
			}
		} else {
			if (!isMany(association.getSecondEnd().getCardMax())) {
				result = AssociationCardinality.manyToOne;
			} else {
				result = AssociationCardinality.manyToMany;
			}
		}
		return result;
	}

	/**
	 * Checks if is many.
	 * 
	 * @param max
	 *            the max
	 * 
	 * @return true, if is many
	 */
	private boolean isMany(String max) {
		if (max.equals("0") || max.equals("1")) {
			return false;
		}
		return true;
	}

	/**
	 * Process aspect.
	 * 
	 * @param aspect
	 *            the aspect
	 */
	private void processAspect(Aspect aspect) {
		logger.info("Processing Aspect " + ModelTools.getCompleteName(aspect));

		currentGenerator.beginAspect(aspect);
		List<Attribute> aspectAttributes = aspect.getAttributes();
		for (Attribute attribute : aspectAttributes) {
			currentGenerator.addAttributeForAspect(aspect, attribute);
		}
		currentGenerator.endAspect(aspect);
	}

	/**
	 * Process association.
	 * 
	 * @param association
	 *            the association
	 */
	private void processAssociation(Association association) {
		logger.info("Processing Association " + ModelTools.getCompleteName(association));

		//** #979, #1273
		AssociationEnd fEnd = (AssociationEnd) getRealObject(association.getFirstEnd());
		Clazz fEndLinkedClass = null;
		if (fEnd.getLinkedClass() != null) {
			fEndLinkedClass = (Clazz) getRealObject(fEnd.getLinkedClass());
		}
		AssociationEnd sEnd = (AssociationEnd) getRealObject(association.getSecondEnd());
		Clazz sEndLinkedClass = null;
		if (sEnd.getLinkedClass() != null) {
			sEndLinkedClass = (Clazz) getRealObject(sEnd.getLinkedClass());
		}
		//** #979, #1273
		if ((fEndLinkedClass != null) && (sEndLinkedClass != null)) {
			boolean doublenav = sEnd.isNavigable() && fEnd.isNavigable();
			AssociationCardinality associationType = getAssociationType(association);
			// boolean isSubAssociation = isSubAssociation(association);
			String subAssociationNameTo = null;
			String subAssociationNameFrom = null;

			if (sEnd.isNavigable()) {
				boolean isInlineDest = isInline(sEndLinkedClass, associationType, association
						.getAssociationType());
				int hiBound = Integer.parseInt(sEnd.getCardMax());
				int loBound = Integer.parseInt(sEnd.getCardMin());
				AssociationKind associationKindDest = new AssociationKind(associationType,
						isInlineDest, subAssociationNameTo, simplifyClasses, hiBound, loBound);
				addAssociations(associationKindDest, association.getName(), association.getTitle(),
						fEndLinkedClass, sEndLinkedClass, sEnd.getName(), sEnd.getTitle(),
						doublenav, association);
			}
			if (fEnd.isNavigable()) {
				boolean isInlineSrc = isInline(fEndLinkedClass, associationType.getInverse(),
						association.getAssociationType());
				int hiBound = Integer.parseInt(fEnd.getCardMax());
				int loBound = Integer.parseInt(sEnd.getCardMin());
				AssociationKind associationKindSrc = new AssociationKind(associationType
						.getInverse(), isInlineSrc, subAssociationNameFrom, simplifyClasses,
						hiBound, loBound);
				addAssociations(associationKindSrc, association.getName(), association.getTitle(),
						sEndLinkedClass, fEndLinkedClass, fEnd.getName(), fEnd.getTitle(),
						doublenav, association);
			}
		} else {
			throw new RuntimeException(MsgId.INT_EXC_ASSOCIATION_ENDS.getText());
		}
	}

	/**
	 * Checks if is inline.
	 * 
	 * @param target
	 *            the target
	 * @param associationType
	 *            the association type
	 * @param associationType2
	 *            the association type2
	 * 
	 * @return true, if is inline
	 */
	private boolean isInline(Clazz target, AssociationCardinality associationType,
			AssociationType associationType2) {
		boolean isInline = true;
		if (associationType == AssociationCardinality.manyToMany
				|| associationType == AssociationCardinality.manyToOne) {
			isInline = false;
		}
		if (associationType2 == AssociationType.AGGREGATION) {
			isInline = false;
		}
		Set<Clazz> subClassesDest = subClasses.get(target);
		if (subClassesDest != null && subClassesDest.size() > 0) {
			isInline = false;
		}
		return isInline;
	}

	/**
	 * Adds the associations.
	 * 
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param title
	 *            the title
	 * @param source
	 *            the source
	 * @param destination
	 *            the destination
	 * @param role
	 *            the role
	 * @param roleTitle
	 *            the role title
	 * @param doublenav
	 *            the doublenav
	 * @param association
	 *            the association
	 */
	private void addAssociations(AssociationKind type, String name, String title, Clazz source,
			Clazz destination, String role, String roleTitle, boolean doublenav,
			Association association) {
		addAssociationForClasse(type, name, title, source, destination, role, roleTitle, doublenav,
				association, source);
		for (AbstractClass abstractClass : allClasses) {
			if (abstractClass instanceof Clazz) {
				Clazz classe = (Clazz) abstractClass;
				if (ModelTools.isGeneralizationOf(classe, source)) {
					addAssociationForClasse(type, name, title, source, destination, role,
							roleTitle, doublenav, association, classe);
				}
			}
		}
	}

	/**
	 * Adds the association for classe.
	 * 
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param title
	 *            the title
	 * @param source
	 *            the source
	 * @param destination
	 *            the destination
	 * @param role
	 *            the role
	 * @param roleTitle
	 *            the role title
	 * @param doublenav
	 *            the doublenav
	 * @param association
	 *            the association
	 * @param subClasse
	 *            the sub classe
	 */
	private void addAssociationForClasse(AssociationKind type, String name, String title,
			Clazz source, Clazz destination, String role, String roleTitle, boolean doublenav,
			Association association, Clazz subClasse) {
		String finalName = name;
		if (StringUtils.trimToNull(role) != null) {
			finalName = finalName + StringUtils.trimToNull(role);
		}
		String finalTitle = title;
		if (StringUtils.trimToNull(finalTitle) == null) {
			finalTitle = name;
		}
		if (StringUtils.trimToNull(roleTitle) != null) {
			finalTitle = finalTitle + " (" + roleTitle + ")";
		} else if (StringUtils.trimToNull(role) != null) {
			finalTitle = finalTitle + " (" + role + ")";
		}
		currentGenerator.addAssociation(type, finalName, finalTitle, subClasse, destination, role,
				doublenav, association, source);
	}

	/**
	 * Process classe.
	 * 
	 * @param classe
	 *            the classe
	 * @param rendered
	 *            the rendered
	 */
	private void processClasse(Clazz classe, boolean rendered) {
		currentGenerator.beginClasse(classe, rendered);

		Clazz parent = ModelTools.getParent(classe);
		boolean hasParent = (parent != null);
		currentGenerator.addIdForClass(classe, "BXDSID", hasParent);

		Map<Aspect, Clazz> allClassAspects = ModelTools.getClassAspects(classe);
		Set<Entry<Aspect, Clazz>> aspectEntrySet = allClassAspects.entrySet();
		for (Entry<Aspect, Clazz> entry : aspectEntrySet) {
			currentGenerator.addAspectForClass(classe, entry.getKey(), entry.getValue());
		}

		Map<Attribute, Clazz> allClassAttributes = ModelTools.getClassAttributes(classe);
		Set<Entry<Attribute, Clazz>> attributesEntrySet = allClassAttributes.entrySet();
		for (Entry<Attribute, Clazz> entry : attributesEntrySet) {
			currentGenerator.addAttributeForClass(classe, entry.getKey(), entry.getValue());
		}

		currentGenerator.endClasse(classe);
	}

	/**
	 * Process form.
	 * 
	 * @param form
	 *            the form
	 */
	private void processForm(FormContainer form) {
		currentGenerator.beginForm(form);
		currentGenerator.endForm(form);
	}

	/**
	 * Process generator.
	 */
	private void processGenerator() {
		// logger = LogFactory.getLog(currentGenerator.getClass());
		currentGenerator.setFormGenerator(this);

		currentGenerator.beginGeneration();

		currentGenerator.beginListEnums();
		for (Enumeration enumeration : allEnums) {
			currentGenerator.processEnum(enumeration);
		}
		currentGenerator.endListEnums();

		currentGenerator.beginListAspects();
		for (Aspect aspect : allAspects) {
			processAspect(aspect);
		}
		currentGenerator.endListAspects();

		currentGenerator.beginListClasses();
		for (AbstractClass classe : allClasses) {
			boolean rendered = allClassesToRender.contains(classe);
			processClasse((Clazz) classe, rendered);
		}
		currentGenerator.endListClasses();

		currentGenerator.beginListAssociations();
		for (Association association : allAssociations) {
			processAssociation(association);
		}
		currentGenerator.endListAssociations();

		currentGenerator.beginListForms();
		for (FormContainer form : allForms) {
			processForm(form);
		}
		currentGenerator.endListForms();

		currentGenerator.endGeneration();
	}

	/**
	 * Finds the association that points to a class for a given model element. For subclasses,
	 * retrieves the association that points to/from the provided source including if linked to a
	 * parent class. If the source is not relevant for the model element, returns null.
	 * 
	 * @param source
	 *            the source
	 * @param modelElement
	 *            the model element, typically a ModelChoiceField
	 * 
	 * @return the association info
	 */
	public AssociationInfo findAssocation(Clazz source, ModelElement modelElement) {
		AssociationInfo result = null;
		String sourceName = ModelTools.getCompleteName(source);
		for (Association association : allAssociations) {
			//** #979, #1273
			AssociationEnd firstEnd = (AssociationEnd) getRealObject(association.getFirstEnd());
			AssociationEnd secondEnd = (AssociationEnd) getRealObject(association.getSecondEnd());
			AbstractClass firstEndClass = (AbstractClass) getRealObject(firstEnd.getLinkedClass());
			AbstractClass secondEndClass = (AbstractClass) getRealObject(secondEnd.getLinkedClass());
			//** #979, #1273
			if (firstEndClass != null && secondEndClass != null) {
				Clazz assoSource = null;
				if (firstEndClass instanceof Clazz) {
					assoSource = (Clazz) firstEndClass;
				}
				String assoSourceName = ModelTools.getCompleteName(assoSource);
				Clazz assoTarget = null;
				if (secondEndClass instanceof Clazz) {
					assoTarget = (Clazz) secondEndClass;
				}
				String assoTargetName = ModelTools.getCompleteName(assoTarget);
				String roleSrc = firstEnd.getName();
				String roleTarget = secondEnd.getName();
				if (assoSourceName.equals(sourceName) && elementEquals(association, modelElement)) {
					return new AssociationInfo(association, assoSource, assoTarget, roleSrc,
							roleTarget, false);
				}
				if (assoTargetName.equals(sourceName) && elementEquals(association, modelElement)) {
					return new AssociationInfo(association, assoSource, assoTarget, roleSrc,
							roleTarget, true); // #980
				}
			} else {
				throw new RuntimeException(MsgId.INT_EXC_ASSOCIATION_ENDS.getText());
			}
		}
		return result;
	}

	private boolean elementEquals(ModelElement association, ModelElement modelElement) {
		return StringUtils.equals(ModelTools.getCompleteName(association), ModelTools
				.getCompleteName(modelElement));
	}

	/**
	 * Gets the real classe.
	 * 
	 * @param classe
	 *            the classe
	 * 
	 * @return the real classe
	 */
	public EObject getRealObject(EObject object) {
		EObject realObject = object;
		if (object.eIsProxy()) {
			URI uri = EcoreUtil.getURI(object);
			realObject = eobjects.get(uri);
			if (realObject == null) {
				URI trimURI = uri.trimFragment();
				Resource resource = resources.get(trimURI);
				if (resource == null) {

					try {
						resource = EResourceUtils.openModel(trimURI, null);
					} catch (IOException e) {
						throw new RuntimeException(e);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					if (!resource.isLoaded()) {
						Map<String, Object> map = new TreeMap<String, Object>();
						try {
							resource.load(map);
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
					}
					resources.put(trimURI, resource);
				}
				String fragment = uri.fragment();
				realObject = resource.getEObject(fragment);
				eobjects.put(uri, realObject);
			}
		}
		return realObject;
	}

	/**
	 * Gets the field's alfresco name.
	 * 
	 * @param refClass
	 *            the referenced class, for getting the real_class
	 * @param modelElement
	 *            the model element
	 * 
	 * @return the field alfresco name
	 */
	public String getAlfrescoName(Clazz refClass, ModelElement refModelElement) {
		Clazz real_class = (Clazz) getRealObject(refClass);
		// FIXME: ds les wkflw forms, le choicefield n'a pas un nom
		// d'association
		if (refModelElement == null) {
			// this happens for choice fields in wkflw forms
			return getClassQualifiedName(real_class);
		}
		ModelElement modelElement = (ModelElement) getRealObject(refModelElement);

		String result = null;

		AssociationInfo associationInfo = findAssocation(real_class, modelElement);
		if (associationInfo != null) {
			result = getAssoQualifiedName(associationInfo.realAssociation);
		} else {
			AbstractClass classe = null;
			for (AbstractClass abstractClass : allClasses) {
				if (elementEquals(real_class, abstractClass)) {
					classe = abstractClass;
					break; // $$
				}
			}
			if (classe != null) {
				while ((classe != null) && (result == null)) {
					EList<Attribute> attributes = classe.getAttributes();
					for (Attribute attribute : attributes) {
						if (elementEquals(attribute, modelElement)) {
							result = getClassQualifiedName(classe) + "_" + attribute.getName();
							return result; // $$
						}
					}
					if (classe instanceof Clazz) {
						Clazz clazz = (Clazz) classe;
						EList<Aspect> aspects = clazz.getAspects();
						for (Aspect aspect : aspects) {
							attributes = aspect.getAttributes();
							for (Attribute attribute : attributes) {
								if (elementEquals(attribute, modelElement)) {
									result = getClassQualifiedName(aspect) + "_"
											+ attribute.getName();
									return result; // $$
								}
							}
						}
					}
					classe = parentClasses.get(classe);
				}
			}
		}
		return result;
	}

	public static String getUniqueName(FormElement field) {
		String uniqueName = uniqueNames.get(field);
		if (uniqueName == null) {
			uniqueName = "field_" + uniqueNames.size();
			uniqueNames.put(field, uniqueName);
		}
		return uniqueName;
	}

	/**
	 * @param renderDataBeforeWorkflow
	 *            the renderDataBeforeWorkflow feature to set
	 */
	public void setRenderDataBeforeWorkflow(boolean renderDataBeforeWorkflow) {
		this.renderDataBeforeWorkflow = renderDataBeforeWorkflow;
	}

	/**
	 * @return the renderDataBeforeWorkflow feature
	 */
	public boolean isRenderDataBeforeWorkflow() {
		return renderDataBeforeWorkflow;
	}
	/**
	 * @return the inReadOnlyMode
	 */
	public boolean isInReadOnlyMode() {
		return inReadOnlyMode;
	}

	/**
	 * @param inReadOnlyMode
	 *            the inReadOnlyMode to set
	 */
	public void setInReadOnlyMode(boolean inReadOnlyMode) {
		this.inReadOnlyMode = inReadOnlyMode;
	}

	/**
	 * @return the generateReadOnlyForms
	 */
	public boolean isGenerateReadOnlyForms() {
		return generateReadOnlyForms;
	}

	/**
	 * @param generateReadOnlyForms
	 *            the generateReadOnlyForms to set
	 */
	public void setGenerateReadOnlyForms(boolean generateReadOnlyForms) {
		this.generateReadOnlyForms = generateReadOnlyForms;
	}

	/**
	 * @return the readOnlySuffix
	 */
	public String getReadOnlySuffix() {
		return readOnlySuffix;
	}

	/**
	 * @param readOnlySuffix
	 *            the readOnlySuffix to set
	 */
	public void setReadOnlySuffix(String readOnlySuffix) {
		this.readOnlySuffix = readOnlySuffix;
	}

	/**
	 * @return the autoSwitchToStandaloneMode
	 */
	public boolean isAutoSwitchToStandaloneMode() {
		return autoSwitchToStandaloneMode;
	}

	/**
	 * @param autoSwitchToStandaloneMode
	 *            the autoSwitchToStandaloneMode to set
	 */
	public void setAutoSwitchToStandaloneMode(boolean autoSwitchToStandaloneMode) {
		this.autoSwitchToStandaloneMode = autoSwitchToStandaloneMode;
	}

}
