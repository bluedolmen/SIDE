package com.bluexml.xforms.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

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
import com.bluexml.side.common.CommonFactory;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.Package;
import com.bluexml.side.common.Stereotype;
import com.bluexml.side.common.impl.CommonFactoryImpl;
import com.bluexml.side.form.BooleanFieldSearchOperators;
import com.bluexml.side.form.BooleanSearchField;
import com.bluexml.side.form.CharFieldSearchOperators;
import com.bluexml.side.form.CharSearchField;
import com.bluexml.side.form.ChoiceFieldSearchOperators;
import com.bluexml.side.form.ChoiceSearchField;
import com.bluexml.side.form.DateFieldSearchOperators;
import com.bluexml.side.form.DateSearchField;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FileFieldSearchOperators;
import com.bluexml.side.form.FileSearchField;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.NumericalFieldSearchOperators;
import com.bluexml.side.form.NumericalSearchField;
import com.bluexml.side.form.SearchField;
import com.bluexml.side.util.componentmonitor.indy.CoreInterface;
import com.bluexml.side.workflow.WorkflowPackage;
import com.bluexml.xforms.generator.GeneratorInterface.AssociationCardinality;
import com.bluexml.xforms.generator.GeneratorInterface.AssociationKind;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.tools.ClasseComparator;
import com.bluexml.xforms.generator.tools.ModelTools;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class FormGenerator.
 */
public class FormGeneratorsManager {

	private static void initEcoreFactories() {
		// Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("library",
		// new XMIResourceFactoryImpl());
		// Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("form",
		// new FormFactoryImpl());
		// Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("clazz",
		// new ClazzFactoryImpl());
		// Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("workflow",
		// new WorkflowFactoryImpl());

		EPackage.Registry.INSTANCE.put(org.eclipse.emf.ecore.EcorePackage.eNS_URI,
				org.eclipse.emf.ecore.EcorePackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(org.eclipse.ocl.ecore.EcorePackage.eNS_URI,
				org.eclipse.ocl.ecore.EcorePackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(ClazzPackage.eNS_URI, ClazzPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(FormPackage.eNS_URI, FormPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(WorkflowPackage.eNS_URI, WorkflowPackage.eINSTANCE);

		// ClazzPackage.eINSTANCE.eClass();
		// FormPackage.eINSTANCE.eClass();
		// WorkflowPackage.eINSTANCE.eClass();
		// org.eclipse.emf.ecore.EcorePackage.eINSTANCE.eClass();
		// org.eclipse.ocl.ecore.EcorePackage.eINSTANCE.eClass();
	}

	public class SearchFieldDataBean {
		public final String defaultOp;
		public final List<SearchOperator> listOp;

		public SearchFieldDataBean(String defaultOp, List<SearchOperator> listOp) {
			super();
			this.defaultOp = defaultOp;
			this.listOp = listOp;
		}

	}

	private static final CommonFactory commonFactory = CommonFactoryImpl.init();

	/** The Constant ALFRESCO_NAME_ASSOCIATION. */
	public static final String ALFRESCO_NAME_ASSOCIATION = "alfrescoNameAssociation";

	private static Map<FormElement, String> uniqueNames = new HashMap<FormElement, String>();

	private static Comparator<SearchOperator> operatorComparatorLabel = new Comparator<SearchOperator>() {
		public int compare(SearchOperator o1, SearchOperator o2) {
			return o1.getLabel().compareTo(o2.getLabel());
		}
	};

	/** The set of all operators that are available. */
	private ArrayList<SearchOperator> operatorPool;

	/** The register of <b>sorted</b> lists of operators that were specified in search forms. */
	private Map<String, List<SearchOperator>> operatorsEnumsMap = new HashMap<String, List<SearchOperator>>();

	/** The classes. */
	private Map<URI, EObject> eobjects = new HashMap<URI, EObject>();

	/** The resources. */
	private Map<URI, Resource> resources = new HashMap<URI, Resource>();

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
	private GeneratorInterface currentGenerator;

	/** The sub classes. */
	private Map<Clazz, Set<Clazz>> subClasses;

	/** The parent classes. */
	private Map<Clazz, Clazz> parentClasses;

	/** The alfresco name stereotype. */
	private Stereotype alfrescoNameStereotype;

	/** The real name stereotype. */
	private Stereotype realNameStereotype;

	/** The monitor through which messages will be output. */
	private CoreInterface genLogger;

	/** if true, associations are rendered as model choice fields instead of inline forms */
	private boolean simplifyClasses;

	/** Layout of workflow forms */
	private boolean renderDataBeforeWorkflow;

	private String readOnlySuffix;

	private boolean generateReadOnlyForms;

	private boolean generateLogListForms;

	/** whether the generation is in read only mode. Normally true during the second pass. */
	private boolean inReadOnlyMode;

	/**
	 * true if at least one workflow form was generated. Used at post-generation time, notably for
	 * outputting a URL for the demo webapp.
	 */
	private boolean workflowCapable;

	/**
	 * true if at least one search form was generated. Used at generation time for determining
	 * whether to write search operators enumerations.
	 */
	private boolean searchCapable;

	/** Name of the form being processed. For error messages. */
	private String currentForm;

	/** If true, some messages that are usually not displayed are sent to the monitor. */
	private boolean debugMode = false;

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

	public String getClassQualifiedName(AbstractClass aClass) {
		String res = aClass.getFullName().replaceAll("\\.", "_");
		return res;
	}

	/**
	 * Builds the association name in the same way as the generator when producing the model.xml
	 * file.<br/>
	 * NOTE: this is prone to discrepancies and obsolescence w.r.t the generator's way of building
	 * the names.
	 * 
	 * @param asso
	 * @param classAsSource the class being used as the source for the association
	 * @return
	 */
	public String getAssoQualifiedName(Association asso, Clazz classAsSource) {
		StringBuffer res = new StringBuffer(128);
		// ** #979, #1273
		AssociationEnd srcEnd = (AssociationEnd) getRealObject(asso.getFirstEnd());
		AssociationEnd targetEnd = (AssociationEnd) getRealObject(srcEnd.getOpposite());
		// @Amenel: not sure whether linked classes may be proxies but getRealObject won't hurt
		AbstractClass srcClass = (AbstractClass) getRealObject(srcEnd.getLinkedClass());
		AbstractClass targetClass = (AbstractClass) getRealObject(targetEnd.getLinkedClass());
		// ** #979, #1273

		// #1549: we need to exchange the association ends so that the final name is correct
		boolean doublenav = srcEnd.isNavigable() && targetEnd.isNavigable();

		if (doublenav && (classAsSource.equals(targetClass))) {
			AbstractClass tempClass = targetClass;
			targetClass = srcClass;
			srcClass = tempClass;
		}
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
	public FormGeneratorsManager(File[] obls, File[] kerblueforms, CoreInterface monitor,
			boolean simplifyClasses, boolean renderDataBeforeWorkflow, boolean asMavenPlugin) {
		super();
		if (asMavenPlugin) {
			initEcoreFactories();
		}
		setWorkflowCapable(false);
		setSearchCapable(false);
		this.debugMode = false;
		this.genLogger = monitor;
		this.simplifyClasses = simplifyClasses;
		this.setRenderDataBeforeWorkflow(renderDataBeforeWorkflow);
		XFormsGenerator.resetKeys();
		// be courteous to other classes
		// ** #1222: generation of read only forms
		com.bluexml.xforms.generator.forms.ModelElement.setFormGenerator(this);
		Renderable.setFormGenerator(this);
		// ** #1222
		String currentFile = null;
		try {
			classModels = new HashMap<URI, PackageInfo>();
			formCollections = new ArrayList<FormCollection>();

			if (obls != null && obls.length > 0) {
				for (File oblFile : obls) {
					// Load the model
					currentFile = oblFile.getAbsolutePath();
					if (isDebugMode()) {
						genLogger.addText("Opening file '" + currentFile);
					}
					Resource resource = EResourceUtils.openModel(currentFile, null);
					Package model = ModelTools.getModel(resource);
					classModels.put(EcoreUtil.getURI(model).trimFragment(), new PackageInfo(model,
							true));
				}
			}

			if (kerblueforms != null) {
				for (File kerblueFormsFile : kerblueforms) {
					currentFile = kerblueFormsFile.getAbsolutePath();
					if (isDebugMode()) {
						genLogger.addText("Opening file '" + currentFile);
					}
					Resource resource = EResourceUtils.openModel(
							kerblueFormsFile.getAbsolutePath(), null);
					addClassModels(resource);
					FormCollection formCollection = ModelTools.getFormCollection(resource);
					formCollections.add(formCollection);
				}
			}
		} catch (FileNotFoundException e) {
			genLogger.addErrorTextAndLog("Please check file paths.", e, null);
			throw new RuntimeException(e);
		} catch (Exception e) {
			genLogger.addErrorTextAndLog("Error opening " + currentFile, e, null);
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
	public void generate(List<GeneratorInterface> generators) {
		genLogger.setTaskName("Collecting data");
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

		buildOperatorsPool();

		// first pass: normal generation
		setInReadOnlyMode(false);
		for (GeneratorInterface dataGenerator : generators) {
			currentGenerator = dataGenerator;
			currentGenerator.setMonitor(genLogger);
			processGenerator();
		}

		if (isGenerateReadOnlyForms()) {
			// second pass: generation in readOnly mode
			setInReadOnlyMode(true);
			for (GeneratorInterface dataGenerator : generators) {
				if (dataGenerator.supportsReadOnlyMode()) {
					currentGenerator = dataGenerator;
					currentGenerator.setReadOnlyMode(true);
					processGenerator();
				}
			}
		}
		genLogger.addText("XForms Generation completed successfully");
		genLogger
				.addServiceLog(
						"List of generated forms",
						"This page provides an easy access to the forms, with default parameters. The server, port and webapp context should be adapted as needed.",
						"http://localhost:8080/xforms/resources/jsp/forms.jsp");
		if (isWorkflowCapable()) {
			genLogger
					.addServiceLog(
							"Demo webapp",
							"This page links to a demo webapp we provide to you so that you may easily test the integration of workflows with forms. The server, port and webapp context should be adapted as needed.",
							"http://localhost:8080/xforms/demo/index.jsp");
		}
		genLogger
				.addServiceLog(
						"Default generated stylesheet",
						"This file contains the definitions for the default appearance of the generated forms. The file is provided as a reference; in case you need to adapt the styles, a better approach is to override/redefine the relevant styles in the custom file.",
						"http://localhost:8080/xforms/resources/styles/xforms.generated.css");
		genLogger
				.addServiceLog(
						"Custom CSS definitions",
						"Modify this file in case the stylesheets have to to be customized or changed. The modifications will be used since this file is already loaded by all generated forms.",
						"http://localhost:8080/xforms/resources/styles/custom.css");
		genLogger.addText("End of generation.");
	}

	// 
	private void buildOperatorsPool() {
		operatorPool = new ArrayList<SearchOperator>(Arrays.asList(SearchOperator.values()));
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
		String completeName = ModelTools.getCompleteName(aspect);
		genLogger.addText("Processing Aspect '" + completeName + "'");

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
		String assoCompleteName = ModelTools.getCompleteName(association);
		if (isDebugMode()) {
			genLogger.addText("Processing Association " + assoCompleteName);
		}

		// ** #979, #1273
		AssociationEnd fEnd = (AssociationEnd) getRealObject(association.getFirstEnd());
		Clazz fEndLinkedClass = null;
		if (fEnd.getLinkedClass() != null) {
			EObject realObject = getRealObject(fEnd.getLinkedClass());
			if (realObject instanceof Clazz) {
				fEndLinkedClass = (Clazz) getRealObject(fEnd.getLinkedClass());
			}
		}
		AssociationEnd sEnd = (AssociationEnd) getRealObject(association.getSecondEnd());
		Clazz sEndLinkedClass = null;
		if (sEnd.getLinkedClass() != null) {
			EObject realObject = getRealObject(sEnd.getLinkedClass());
			if (realObject instanceof Clazz) {
				sEndLinkedClass = (Clazz) realObject;
			}
		}
		// ** #979, #1273
		if ((fEndLinkedClass != null) && (sEndLinkedClass != null)) {
			boolean doublenav = sEnd.isNavigable() && fEnd.isNavigable();
			AssociationCardinality associationType = getAssociationType(association);
			// boolean isSubAssociation = isSubAssociation(association);

			if (sEnd.isNavigable()) {
				boolean isInlineDest = isInline(sEndLinkedClass, associationType, association
						.getAssociationType());
				int hiBound = Integer.parseInt(sEnd.getCardMax());
				int loBound = Integer.parseInt(sEnd.getCardMin());
				boolean filtered = isAssociationFilterable(sEndLinkedClass, association);
				AssociationKind associationKindDest = new AssociationKind(associationType,
						isInlineDest, simplifyClasses, hiBound, loBound, filtered);
				addAssociations(associationKindDest, association.getName(), association.getTitle(),
						fEndLinkedClass, sEndLinkedClass, sEnd.getName(), sEnd.getTitle(),
						doublenav, association);
			}
			if (fEnd.isNavigable()) {
				boolean isInlineSrc = isInline(fEndLinkedClass, associationType.getInverse(),
						association.getAssociationType());
				int hiBound = Integer.parseInt(fEnd.getCardMax());
				int loBound = Integer.parseInt(fEnd.getCardMin());
				boolean filtered = isAssociationFilterable(fEndLinkedClass, association);
				AssociationKind associationKindSrc = new AssociationKind(associationType
						.getInverse(), isInlineSrc, simplifyClasses, hiBound, loBound, filtered);
				addAssociations(associationKindSrc, association.getName(), association.getTitle(),
						sEndLinkedClass, fEndLinkedClass, fEnd.getName(), fEnd.getTitle(),
						doublenav, association);
			}
		} else {
			genLogger.addText("Ignoring association '" + assoCompleteName
					+ "' both ends must be classes to be supported.");
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
		if (isDebugMode()) {
			String className = getClassQualifiedName(classe);
			genLogger.addText("Processing " + className);
		}
		currentGenerator.beginClasse(classe, rendered);

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
		currentForm = ModelTools.getCompleteName(form);
		if (isDebugMode()) {
			genLogger.addText("Processing " + currentForm);
		}
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
			// ** #979, #1273
			AssociationEnd firstEnd = (AssociationEnd) getRealObject(association.getFirstEnd());
			AssociationEnd secondEnd = (AssociationEnd) getRealObject(association.getSecondEnd());
			AbstractClass firstEndClass = (AbstractClass) getRealObject(firstEnd.getLinkedClass());
			AbstractClass secondEndClass = (AbstractClass) getRealObject(secondEnd.getLinkedClass());
			// ** #979, #1273
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
	 * Gets the real object pointed by the given object in case this one is a proxy (i.e. it
	 * references a real object that is located in another resource file). If it's not a proxy, the
	 * given object is returned as is.
	 * 
	 * @param object
	 *            a model element
	 * 
	 * @return the resolved object
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
		// FIXME: ds les wkflw forms, le choicefield n'a pas un nom d'association
		if (refModelElement == null) {
			// this happens for choice fields in wkflw forms
			return getClassQualifiedName(real_class);
		}
		ModelElement modelElement = (ModelElement) getRealObject(refModelElement);

		String result = null;

		AssociationInfo associationInfo = findAssocation(real_class, modelElement);
		if (associationInfo != null) {
			result = getAssoQualifiedName(associationInfo.realAssociation, real_class);
		} else {
			AbstractClass classe = null;
			for (AbstractClass abstractClass : allClasses) {
				if (elementEquals(real_class, abstractClass)) {
					classe = abstractClass;
					break; // $$
				}
			}
			if (classe != null) {
				while (classe != null) {
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
						// #1257: search the attribute in the appropriate aspect
						AbstractClass refAspect = (AbstractClass) modelElement.eContainer();
						String refAspectName = getClassQualifiedName(refAspect);
						for (Aspect aspect : aspects) {
							String aspectName = getClassQualifiedName(aspect);
							if (refAspectName.equals(aspectName)) {
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
	 * @return the generateLogListForms
	 */
	public boolean isGenerateLogListForms() {
		return generateLogListForms;
	}

	/**
	 * @param generateLogListForms
	 *            the generateLogListForms to set
	 */
	public void setGenerateLogListForms(boolean generateLogListForms) {
		this.generateLogListForms = generateLogListForms;
	}

	/**
	 * @param workflowCapable
	 *            the workflowCapable to set
	 */
	public void setWorkflowCapable(boolean workflowCapable) {
		this.workflowCapable = workflowCapable;
	}

	/**
	 * @return the workflowCapable status
	 */
	public boolean isWorkflowCapable() {
		return workflowCapable;
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}

	/**
	 * @param searchCapable
	 *            the searchCapable status to set
	 */
	public void setSearchCapable(boolean searchCapable) {
		this.searchCapable = searchCapable;
	}

	/**
	 * @return the searchCapable status
	 */
	public boolean isSearchCapable() {
		return searchCapable;
	}

	/**
	 * @return the operatorsEnumsMap
	 */
	public Map<String, List<SearchOperator>> getOperatorsEnumsMap() {
		return operatorsEnumsMap;
	}

	/**
	 * Provides a unique id for the set of search operators defined in a search field. If an
	 * equivalent set already exists, its id is returned. Otherwise, the new set is registered with
	 * a new id that's returned.
	 * 
	 * @param searchField
	 * @return the id, a zero padded sequence number starting from 1
	 */
	public String getSearchOperatorsListId(SearchField searchField) {
		String resultId;

		// get list of operators and default operator
		SearchFieldDataBean sfDataBean = getSearchFieldDataBean(searchField);

		// test whether the list already exists
		resultId = testOperatorList(sfDataBean.listOp);

		// if so, return the id
		if (resultId != null) {
			return resultId;
		}
		// if not, register the list and return its id
		Formatter formatter = new Formatter();
		resultId = formatter.format("%06d", operatorsEnumsMap.keySet().size() + 1).toString();
		operatorsEnumsMap.put(resultId, sfDataBean.listOp);
		return resultId;
	}

	/**
	 * Gets the default operator for a search field. The operator is either specified in the model
	 * or forced upon the field.
	 * 
	 * @param searchField
	 * @return
	 */
	public String getSearchFieldDefaultOperator(SearchField searchField) {
		SearchFieldDataBean sfDataBean = getSearchFieldDataBean(searchField);
		return sfDataBean.defaultOp;
	}

	/**
	 * Computes the list of operators for a search field and defines a default operator.
	 * 
	 * @param searchField
	 * @return the bean that is computed. Default operator is always filled. List may be empty.
	 */
	private SearchFieldDataBean getSearchFieldDataBean(SearchField searchField) {
		//
		// the values must be kept in sync with names in the metamodel. The value indicated here as
		// default for a type should also be the same default in the controller.

		// the id/name of the default op specified in the search field
		String fieldOp = null;
		// the default op we consider as the most suitable default for the field type
		String defaultTypeOp = null;

		//
		// build the list of operators for this field
		List<SearchOperator> listOp = new ArrayList<SearchOperator>();
		if (searchField instanceof CharSearchField) {
			defaultTypeOp = "is";
			CharSearchField localField = (CharSearchField) searchField;
			CharFieldSearchOperators defaultOperator = localField.getDefaultOperator();
			if (defaultOperator != null) {
				fieldOp = defaultOperator.getName();
			}
			EList<CharFieldSearchOperators> fieldOps = localField.getOperators();
			for (CharFieldSearchOperators op : fieldOps) {
				listOp.add(getOperatorFromPool(op.getName()));
			}
		} else if (searchField instanceof DateSearchField) {
			defaultTypeOp = "exactly";
			DateSearchField localField = (DateSearchField) searchField;
			DateFieldSearchOperators defaultOperator = localField.getDefaultOperator();
			if (defaultOperator != null) {
				fieldOp = defaultOperator.getName();
			}
			EList<DateFieldSearchOperators> fieldOps = localField.getOperators();
			for (DateFieldSearchOperators op : fieldOps) {
				listOp.add(getOperatorFromPool(op.getName()));
			}
		} else if (searchField instanceof NumericalSearchField) {
			defaultTypeOp = "exactly";
			NumericalSearchField localField = (NumericalSearchField) searchField;
			NumericalFieldSearchOperators defaultOperator = localField.getDefaultOperator();
			if (defaultOperator != null) {
				fieldOp = defaultOperator.getName();
			}
			EList<NumericalFieldSearchOperators> fieldOps = localField.getOperators();
			for (NumericalFieldSearchOperators op : fieldOps) {
				listOp.add(getOperatorFromPool(op.getName()));
			}
		} else if (searchField instanceof ChoiceSearchField) {
			defaultTypeOp = "oneOf";
			ChoiceSearchField localField = (ChoiceSearchField) searchField;
			ChoiceFieldSearchOperators defaultOperator = localField.getDefaultOperator();
			if (defaultOperator != null) {
				fieldOp = defaultOperator.getName();
			}
			EList<ChoiceFieldSearchOperators> fieldOps = localField.getOperators();
			for (ChoiceFieldSearchOperators op : fieldOps) {
				listOp.add(getOperatorFromPool(op.getName()));
			}
		} else if (searchField instanceof FileSearchField) {
			defaultTypeOp = "fileType";
			FileSearchField localField = (FileSearchField) searchField;
			EList<FileFieldSearchOperators> fieldOps = localField.getOperators();
			FileFieldSearchOperators defaultOperator = localField.getDefaultOperator();
			if (defaultOperator != null) {
				fieldOp = defaultOperator.getName();
			}
			for (FileFieldSearchOperators op : fieldOps) {
				listOp.add(getOperatorFromPool(op.getName()));
			}
		} else if (searchField instanceof BooleanSearchField) {
			defaultTypeOp = "is";
			BooleanSearchField localField = (BooleanSearchField) searchField;
			EList<BooleanFieldSearchOperators> fieldOps = localField.getOperators();
			BooleanFieldSearchOperators defaultOperator = localField.getDefaultOperator();
			if (defaultOperator != null) {
				fieldOp = defaultOperator.getName();
			}
			for (BooleanFieldSearchOperators op : fieldOps) {
				listOp.add(getOperatorFromPool(op.getName()));
			}
		}
		// if empty list, add the default op from either the field or that type
		if (listOp.size() == 0) {
			if (StringUtils.trimToNull(fieldOp) == null) {
				fieldOp = defaultTypeOp;
			}
			listOp.add(getOperatorFromPool(fieldOp));
		} else {
			if (fieldOp == null) {
				fieldOp = listOp.get(0).getId();
			}
		}
		// sort the list <-- this is MANDATORY for the reusing of operator sets to happen
		Collections.sort(listOp, operatorComparatorLabel);

		return new SearchFieldDataBean(fieldOp, listOp);
	}

	/**
	 * Finds the operator whose id is the same as the given id
	 * 
	 * @param refId
	 * @return the matching operator
	 */
	private SearchOperator getOperatorFromPool(String refId) {
		for (SearchOperator op : operatorPool) {
			if (op.getId().equals(refId)) {
				return op;
			}
		}
		throw new RuntimeException("No operator found with id '" + refId + "'");
	}

	/**
	 * Tests for the presence of the given list amongst the registered lists. We just test whether a
	 * registered list 'equals' this one.
	 * 
	 * @param opList
	 *            the list to check
	 * @return the registration key, or null if the list is not registered.
	 * @see {@link #getSearchOperatorsListId(SearchField)} for details about the key (or id).
	 */
	private String testOperatorList(List<SearchOperator> opList) {
		for (String key : operatorsEnumsMap.keySet()) {
			if (opList.equals(operatorsEnumsMap.get(key))) {
				return key;
			}
		}
		return null;
	}

	/**
	 * Tells whether the field will be rendered as a selection widget instead of a text input.
	 * 
	 * @param field
	 * @return true if required parameters are found in the appropriate property
	 */
	public boolean isFieldSelectionCapable(Field field) {
		EList<String> xtension = getXtension(field);
		if (xtension != null) {
			String format = getXtensionParameter(xtension, MsgId.MODEL_XTENSION_FORMAT.getText());
			String type = getXtensionParameter(xtension, MsgId.MODEL_XTENSION_DATATYPE.getText());
			String id = getXtensionParameter(xtension, MsgId.MODEL_XTENSION_IDENTIFIER.getText());
			if ((format != null) && (type != null) && (id != null)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the given parameter from the list that carries extension definition parameters. These
	 * must follow the [parameter name]=[parameter value] format, with no whitespace before the "=".<br/>
	 * The list is searched for a correctly formatted parameter until completely visited. If a
	 * parameter is defined several times, only the first occurrence will be seen and used.
	 * 
	 * @param xtension
	 * @param parameter
	 * @return the value of the parameter, or <code>null</code> if no correctly formated definition
	 *         is found.
	 */
	private String getXtensionParameter(EList<String> xtension, String parameter) {
		int pos;
		for (String configItem : xtension) {
			pos = configItem.indexOf(parameter);
			if (pos != -1) {
				pos = pos + parameter.length();
				if (configItem.charAt(pos) == '=') {
					return configItem.substring(pos + 1);
				}
			}
		}
		return null;
	}

	//
	//
	//
	private EList<String> getXtension(FormElement formElt) {
		return formElt.getXtension();
	}

	/**
	 * Gets the content of the extension field as a comma-separated string.
	 * 
	 * @param formElt
	 * @return the string
	 */
	public String getXtensionAsString(FormElement formElt) {
		EList<String> list = formElt.getXtension();
		StringBuffer result = new StringBuffer("");
		boolean first = true;
		for (String item : list) {
			if (first == false) {
				result.append(',');
			}
			result.append(item);
			first = false;
		}
		return result.toString();
	}

	public String getXtensionFormat(FormElement formElt) {
		EList<String> xtension = getXtension(formElt);

		return getXtensionParameter(xtension, MsgId.MODEL_XTENSION_FORMAT.getText());
	}

	public String getXtensionDatatype(Field field) {
		EList<String> xtension = getXtension(field);

		return getXtensionParameter(xtension, MsgId.MODEL_XTENSION_DATATYPE.getText());
	}

	public String getXtensionIdentifier(Field field) {
		EList<String> xtension = getXtension(field);

		return getXtensionParameter(xtension, MsgId.MODEL_XTENSION_IDENTIFIER.getText());
	}

	public String getXtensionLabelLength(Field field) {
		EList<String> xtension = getXtension(field);

		return getXtensionParameter(xtension, MsgId.MODEL_XTENSION_LABEL_LENGTH.getText());
	}

	/**
	 * Tells whether an association is to be filtered on the side of the given class. If so, objects
	 * of that class, when listed on a selection widget as available items, are filtered out if they
	 * already bear an association (i.e. if they are already pointed to using that association). <br/>
	 * NOTE: not sure this will work correctly for reflexive associations.
	 * <p/>
	 * Example association: Person (0..*) <---> (0.. 1) Company.
	 * <p/>
	 * On the form for 'Company', the 'real class' property for the ModelChoiceField is 'Person':
	 * several Person objects can be associated. But because the association reads 'a Person can be
	 * associated with at most one Company', any Person object already associated should not be
	 * associated again. So that object must be filtered out: this function returns
	 * <code>true</code>.
	 * <p/>
	 * On the form for 'Person', the 'real class' property for the ModelChoiceField is 'Company':
	 * only one Company object can be associated. But this time, the association reads 'a Company
	 * can be associated with several Person', so having a Company object already associated does
	 * not require that the Company object be filtered out. So, return <code>false</code>.
	 * 
	 * @param formEltClass
	 *            the class for the target items (also the 'real class' property of
	 *            ModelChoiceField's)
	 * @param asso
	 * @return true if the maximum multiplicity on the <b>opposite end</b> of the given class is 1.
	 */
	public boolean isAssociationFilterable(Clazz formEltClass, Association asso) { // #1536
		int maxBound = -1;
		boolean filtered;

		AssociationEnd srcEnd = (AssociationEnd) getRealObject(asso.getFirstEnd());
		AssociationEnd targetEnd = (AssociationEnd) getRealObject(srcEnd.getOpposite());
		AbstractClass srcClass = (AbstractClass) getRealObject(srcEnd.getLinkedClass());
		AbstractClass targetClass = (AbstractClass) getRealObject(targetEnd.getLinkedClass());

		EObject realClass = getRealObject(formEltClass);
		// we get the max bound from the end opposite to the one where the class is found
		if (srcClass.equals(realClass)) {
			maxBound = Integer.parseInt(targetEnd.getCardMax());
		} else if (targetClass.equals(realClass)) {
			maxBound = Integer.parseInt(srcEnd.getCardMax());
		} else {
			throw new IllegalArgumentException("Uncomparable classes.");
		}
		filtered = (maxBound == 1);
		return filtered;
	}

}
