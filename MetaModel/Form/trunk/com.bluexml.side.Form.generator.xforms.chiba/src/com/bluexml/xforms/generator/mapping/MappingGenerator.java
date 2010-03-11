package com.bluexml.xforms.generator.mapping;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.common.DataType;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.Tag;
import com.bluexml.side.form.ActionField;
import com.bluexml.side.form.ChoiceField;
import com.bluexml.side.form.ChoiceWidgetType;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FileField;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormSearch;
import com.bluexml.side.form.FormWorkflow;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.ModelChoiceWidgetType;
import com.bluexml.side.form.Reference;
import com.bluexml.side.form.SearchField;
import com.bluexml.side.form.VirtualField;
import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.side.workflow.StartState;
import com.bluexml.side.workflow.Swimlane;
import com.bluexml.side.workflow.TaskNode;
import com.bluexml.side.workflow.impl.StartStateImpl;
import com.bluexml.xforms.controller.binding.ActionFieldType;
import com.bluexml.xforms.controller.binding.AspectType;
import com.bluexml.xforms.controller.binding.AssociationType;
import com.bluexml.xforms.controller.binding.AttributeType;
import com.bluexml.xforms.controller.binding.CanisterType;
import com.bluexml.xforms.controller.binding.ClassType;
import com.bluexml.xforms.controller.binding.EnumType;
import com.bluexml.xforms.controller.binding.FileFieldType;
import com.bluexml.xforms.controller.binding.FormFieldType;
import com.bluexml.xforms.controller.binding.FormType;
import com.bluexml.xforms.controller.binding.GenInfoType;
import com.bluexml.xforms.controller.binding.Mapping;
import com.bluexml.xforms.controller.binding.ModelChoiceType;
import com.bluexml.xforms.controller.binding.ObjectFactory;
import com.bluexml.xforms.controller.binding.ReferenceType;
import com.bluexml.xforms.controller.binding.SearchFieldType;
import com.bluexml.xforms.controller.binding.SearchFormType;
import com.bluexml.xforms.controller.binding.VirtualFieldType;
import com.bluexml.xforms.controller.binding.WorkflowTaskType;
import com.bluexml.xforms.generator.AbstractGenerator;
import com.bluexml.xforms.generator.FormGeneratorsManager;
import com.bluexml.xforms.generator.tools.AspectComparator;
import com.bluexml.xforms.generator.tools.ClasseComparator;
import com.bluexml.xforms.generator.tools.ModelTools;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class MappingGenerator.
 */
public class MappingGenerator extends AbstractGenerator {

	private static final String BLUEXML_WORKFLOW_PREFIX = "wfbx";
	/** The mapping marshaller. */
	private static Marshaller mappingMarshaller;
	protected static ObjectFactory objectFactory = new ObjectFactory();

	static {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(
					"com.bluexml.xforms.controller.binding", MappingGenerator.class
							.getClassLoader());
			mappingMarshaller = jaxbContext.createMarshaller();
			mappingMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** The output mapping. */
	private File outputMapping;

	/** The mapping. */
	private Mapping mapping;

	/** The aspect types. */
	private Map<Aspect, AspectType> aspectTypes = new TreeMap<Aspect, AspectType>(
			AspectComparator.INSTANCE);

	/** The class types. */
	private Map<Clazz, ClassType> classTypes = new TreeMap<Clazz, ClassType>(
			ClasseComparator.INSTANCE);

	/** The file for outputting the list of CSS classes defined in the models. */
	private File CSSFile;

	/** The output file for the redirection table of workflow forms. */
	private File RedirectFile;

	/** The style collector specific to CSS */
	private List<String> CSSCollector = new ArrayList<String>();

	public static String workflowGetProcessNameFromFormName(String formName) {
		return formName.substring(0, formName.indexOf("_"));
	}

	public static String workflowBuildBlueXMLTaskName(String formName) {
		return BLUEXML_WORKFLOW_PREFIX + formName.replace('_', ':');
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#addAspectForClass(com.bluexml
	 * .side.clazz.Clazz, com.bluexml.side.clazz.Aspect, com.bluexml.side.clazz.Clazz)
	 */
	public void addAspectForClass(Clazz classe, Aspect aspect, Clazz owner) {
		AspectType aspectType = objectFactory.createAspectType();
		aspectType.setName(aspect.getName());
		aspectType.setPackage(ModelTools.getPackage(aspect));
		aspectType.setAlfrescoName(FormGeneratorsManager.getClassQualifiedName(aspect));
		getClassType(classe).getAspect().add(aspectType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#addAttributeForAspect(com.
	 * bluexml.side.clazz.Aspect, com.bluexml.side.clazz.Attribute)
	 */
	public void addAttributeForAspect(Aspect aspect, Attribute attribute) {
		AttributeType asAttributeType = processAttribute(aspect, attribute);
		getAspectType(aspect).getAttribute().add(asAttributeType);
	}

	/**
	 * Gets the aspect type.
	 * 
	 * @param aspect
	 *            the aspect
	 * 
	 * @return the aspect type
	 */
	private AspectType getAspectType(Aspect aspect) {
		return aspectTypes.get(aspect);
	}

	/**
	 * Gets the class type.
	 * 
	 * @param classe
	 *            the classe
	 * 
	 * @return the class type
	 */
	private ClassType getClassType(Clazz classe) {
		Clazz realClasse = (Clazz) formGenerator.getRealObject(classe);
		return classTypes.get(realClasse);
	}

	/**
	 * As attribute type.
	 * 
	 * @param classe
	 *            the classe
	 * @param attribute
	 *            the attribute
	 * 
	 * @return the attribute type
	 */
	private AttributeType processAttribute(AbstractClass classe, Attribute attribute) {
		boolean isMultiple = false;
		String result;

		AttributeType attributeType = objectFactory.createAttributeType();
		attributeType.setName(attribute.getName());
		attributeType.setAlfrescoName(getAlfrescoNameForAttribute(classe, attribute));
		attributeType.setType(attribute.getTyp().getLiteral());
		if (attribute.getValueList() != null) {
			attributeType.setEnumQName(ModelTools.getCompleteName(attribute.getValueList()));
		}

		//
		// properties stored as attributes if different from the default
		result = ModelTools.getMetaInfoValue(attribute, "multiple");
		if (result != null) {
			if (StringUtils.equalsIgnoreCase(result, "true")) {
				attributeType.setMultiple(isMultiple);
			}
		}

		boolean isDisabled = false;
		result = ModelTools.getMetaInfoValue(attribute, "read-only");
		if (result != null) {
			isDisabled = (StringUtils.equalsIgnoreCase(result, "true"));
			attributeType.setReadOnly(isDisabled);
		}

		String initialValue = attribute.getInitialValue();
		if (initialValue != null) {
			attributeType.setDefault(initialValue);
		}

		String fieldSize = ModelTools.getMetaInfoValue(attribute, "size");
		if ((fieldSize != null) && (!fieldSize.equals("0"))) {
			attributeType.setFieldSize(fieldSize);
		}

		return attributeType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#addAttributeForClass(com.bluexml
	 * .side.clazz.Clazz, com.bluexml.side.clazz.Attribute, com.bluexml.side.clazz.Clazz)
	 */
	public void addAttributeForClass(Clazz classe, Attribute attribute, Clazz owner) {
		AttributeType asAttributeType = processAttribute(owner, attribute);
		getClassType(classe).getAttribute().add(asAttributeType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#beginAspect(com.bluexml.side
	 * .clazz.Aspect)
	 */
	public void beginAspect(Aspect aspect) {
		AspectType aspectType = objectFactory.createAspectType();
		aspectType.setName(aspect.getName());
		aspectType.setPackage(ModelTools.getPackage(aspect));
		aspectType.setAlfrescoName(FormGeneratorsManager.getClassQualifiedName(aspect));
		aspectTypes.put(aspect, aspectType);
		mapping.getAspect().add(aspectType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#beginClasse(com.bluexml.side
	 * .clazz.Clazz, boolean)
	 */
	public void beginClasse(Clazz classe, boolean rendered) {
		ClassType classType = objectFactory.createClassType();
		classType.setName(classe.getName());
		classType.setPackage(ModelTools.getPackage(classe));
		classType.setAlfrescoName(FormGeneratorsManager.getClassQualifiedName(classe));
		classTypes.put(classe, classType);
		mapping.getClazz().add(classType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#beginGeneration()
	 */
	public void beginGeneration() {
		monitor.setTaskName("Generating resources.");
		mapping = new Mapping();

		GenInfoType genInfoType = objectFactory.createGenInfoType();
		genInfoType.setReadOnlyFormsSuffix(formGenerator.getReadOnlySuffix());
		genInfoType.setDebugMode(formGenerator.isDebugMode());

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String dateString = formatter.format(date);
		genInfoType.setTimeStamp(dateString);

		mapping.setGenInfo(genInfoType);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#beginListAspects()
	 */
	public void beginListAspects() {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#beginListAssociations()
	 */
	public void beginListAssociations() {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#beginListClasses()
	 */
	public void beginListClasses() {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#beginListEnums()
	 */
	public void beginListEnums() {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#addAssociation(org.blueXML
	 * .xforms.generator.GeneratorInterface.AssociationKind, java.lang.String, java.lang.String,
	 * com.bluexml.side.clazz.Clazz, com.bluexml.side.clazz.Clazz, java.lang.String, boolean,
	 * com.bluexml.side.clazz.Association, com.bluexml.side.clazz.Clazz)
	 */
	public void addAssociation(AssociationKind type, String name, String title, Clazz source,
			Clazz destination, String role, boolean doublenav, Association association, Clazz owner) {
		if (owner == source) {
			ClassType sourceType = getClassType(source);

			AssociationType associationType = objectFactory.createAssociationType();
			associationType.setPackage(ModelTools.getPackage(association));
			associationType.setCaption(title);
			associationType.setName(name);
			ClassType classType = getClassType(destination);
			if (classType == null) {
				monitor.addErrorTextAndLog("No classType found for class '"
						+ destination.getLabel()
						+ "'. Please add the containing model to the generation project.", null,
						null);
			} else {
				associationType.setType(copyClassType(classType));
			}
			String associationName = formGenerator.getAssoQualifiedName(association);
			associationType.setAlfrescoName(associationName);

			if (type.getAssociationCardinality().isMultiple()) {
				associationType.setMultiple(true); // optional attribute
			}
			if (type.isInline()) {
				associationType.setInline(true); // optional attribute
			}

			sourceType.getAssociation().add(associationType);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#endAspect(com.bluexml.side
	 * .clazz.Aspect)
	 */
	public void endAspect(Aspect aspect) {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#endClasse(com.bluexml.side .clazz.Clazz)
	 */
	public void endClasse(Clazz classe) {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#endGeneration()
	 */
	/**
	 * Writes all generated files. <br/>
	 * NOTE: the enumeration files for search operators are written by the template generator.
	 */
	public void endGeneration() {
		// mapping file
		try {
			mappingMarshaller.marshal(mapping, outputMapping);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// CSS
		try {
			writeSkeletonCSS();
		} catch (IOException e) {
			monitor.addErrorTextAndLog("File error while trying to write the CSS file.", e, null);
		}
		// redirector configuration file
		try {
			writeSkeletonRedirector();
		} catch (IOException e) {
			monitor.addErrorTextAndLog("File error while trying to write the redirection file.", e,
					null);
		}
		monitor.addText("Finished generating resources.");
	}

	/**
	 * Writes a dictionary of all user-given styles in a file. If no styles were indicated, the file
	 * is not created.
	 * 
	 * @throws IOException
	 */
	private void writeSkeletonCSS() throws IOException {
		if (CSSCollector.size() > 0) {
			FileWriter fw = new FileWriter(CSSFile);
			PrintWriter pw = new PrintWriter(fw);
			int nbClasses = 0;

			pw.println("/* ************************ */");
			pw.println("/* BlueXML XForms Generator */");
			pw.println("/* Generated CSS Template   */");
			pw.println("/* ************************ */");
			pw.println("/* Modify this template as suits your needs.*/");
			pw.println("/* If this file is available as 'resources/styles/custom.css' under */");
			pw.println("/* your webapp's folder, it will be used by the client browsers.*/");
			pw.println("/* Otherwise, indicating its location via a URL parameter will be */");
			pw.println("/* necessary. See the SIDE documentation for further details.*/");
			pw.println("\n\n");

			pw.println("/* CLASSES */");
			for (String elt : CSSCollector) {
				if (StringUtils.trimToNull(elt) != null) {
					if (!(elt.charAt(0) == '#')) {
						nbClasses++;
						pw.println("." + elt + " {");
						pw.println("}");
						pw.println();
					}
				}
			}

			pw.println();
			pw.println("/* ID's */");
			for (String elt : CSSCollector) {
				if (StringUtils.trimToNull(elt) != null) {
					if ((elt.charAt(0) == '#')) {
						pw.println(elt + " {");
						pw.println("}");
						pw.println();
					}
				}
			}

			pw.close();
		}
	}

	/**
	 * Writes a skeleton of the redirection file filled with default values. The file is not created
	 * if no workflow forms were generated.
	 * 
	 * @throws IOException
	 */
	private void writeSkeletonRedirector() throws IOException {
		// redirector configuration
		Document doc = DOMUtil.getNewDocument();
		if (doc != null) {
			List<JAXBElement<? extends CanisterType>> elements = mapping.getCanister();

			Element entriesElt = doc.createElement("entries");
			doc.appendChild(entriesElt);
			int numberOfEntries = 0;
			for (JAXBElement<? extends CanisterType> element : elements) {
				if (element.getValue() instanceof WorkflowTaskType) {
					WorkflowTaskType taskType = (WorkflowTaskType) element.getValue();

					Element nameElt = doc.createElement(MsgId.INT_REDIRECTION_NAME.getText());
					nameElt.setTextContent(taskType.getName());
					Element urlElt = doc.createElement(MsgId.INT_REDIRECTION_URL.getText());
					Element autoElt = doc.createElement(MsgId.INT_REDIRECTION_AUTO_ADVANCE
							.getText());
					autoElt.setTextContent("false");
					Element addElt = doc.createElement(MsgId.INT_REDIRECTION_ADD_PARAMS.getText());
					addElt.setTextContent("true");

					Element entryElt = doc.createElement(MsgId.INT_REDIRECTION_ENTRY.getText());
					entryElt.appendChild(nameElt);
					entryElt.appendChild(urlElt);
					entryElt.appendChild(autoElt);
					entryElt.appendChild(addElt);

					entriesElt.appendChild(entryElt);
					numberOfEntries++;
				}
			}

			if (numberOfEntries > 0) {
				String docStr = DOMUtil.convertDocument2String(doc);

				FileWriter fw;
				fw = new FileWriter(RedirectFile);
				PrintWriter pw = new PrintWriter(fw);
				pw.print(docStr);
				pw.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#endListAspects()
	 */
	public void endListAspects() {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#endListAssociations()
	 */
	public void endListAssociations() {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#endListClasses()
	 */
	public void endListClasses() {
		Set<Entry<Clazz, ClassType>> entrySet = classTypes.entrySet();
		for (Entry<Clazz, ClassType> entry : entrySet) {
			Clazz classe = entry.getKey();
			if (formGenerator.isDebugMode()) {
				monitor.addText("Adding Generalizations to '" + classe.getLabel() + "'");
			}
			addGeneralizations(classe, classe, 0);
		}
	}

	/**
	 * Adds the generalizations.
	 * 
	 * @param leafClasse
	 *            the leaf classe
	 * @param classe
	 *            the classe
	 * @param level
	 *            the level
	 */
	private void addGeneralizations(Clazz leafClasse, Clazz classe, int level) {
		EList<Clazz> generalizations = leafClasse.getGeneralizations();
		for (Clazz generalization : generalizations) {
			if (generalization != null) {
				Clazz parentClasse = generalization;
				ClassType classType = getClassType(classe);
				ClassType parentClassType = getClassType(parentClasse);
				if (parentClassType != null) {
					if (!classe.isAbstract()) {
						parentClassType.getSubClass().add(copyClassType(classType));
					}
					if (level == 0) {
						classType.setParentClass(copyClassType(parentClassType));
					}
				}
				addGeneralizations(parentClasse, classe, level + 1);
			}
		}
	}

	/**
	 * Copy class type.
	 * 
	 * @param classType
	 *            the class type
	 * 
	 * @return the class type
	 */
	private ClassType copyClassType(ClassType classType) {
		ClassType result = objectFactory.createClassType();
		result.setName(classType.getName());
		result.setPackage(classType.getPackage());
		result.setAlfrescoName(classType.getAlfrescoName());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#endListEnums()
	 */
	public void endListEnums() {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#processEnum(com.bluexml.side
	 * .clazz.Enumeration)
	 */
	public void processEnum(Enumeration enumeration) {
		EnumType enumType = objectFactory.createEnumType();
		enumType.setPackage(ModelTools.getPackage(enumeration));
		enumType.setName(enumeration.getName());
		String alfrescoName = ModelTools.getCompleteName(enumeration).replace('.', '_');
		enumType.setAlfrescoName(alfrescoName);
		if (enumeration.getDynamic()) {
			enumType.setDynamic(true);
		}
		mapping.getEnum().add(enumType);
	}

	/**
	 * Sets the mapping output file.
	 * 
	 * @param mappingOutput
	 *            the new output file
	 */
	public void setOutputMappingFile(String mappingOutput) {
		outputMapping = new File(mappingOutput);
		outputMapping.getParentFile().mkdirs();
	}

	/**
	 * Sets the CSS output file and creates path.
	 * 
	 * @param name
	 *            the path to the CSS file
	 */
	public void setOutputCSSFile(String name) {
		CSSFile = new File(name);
		CSSFile.getParentFile().mkdirs();
	}

	/**
	 * Sets the redirector's configuration output file and creates path.
	 * 
	 * @param name
	 *            the path to the redirector's configuration file
	 */
	public void setOutputRedirectFile(String name) {
		RedirectFile = new File(name);
		RedirectFile.getParentFile().mkdirs();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#beginForm(com.bluexml.side .form.Form)
	 */
	public void beginForm(FormContainer form) {
		FormContainer realContainer = form;

		realContainer = (FormContainer) formGenerator.getRealObject(form); // #1225

		if (realContainer instanceof FormClass) {
			FormType formType = newFormType(realContainer);

			FormClass formClass = (FormClass) realContainer;
			if (formClass.isContent_enabled()) {
				formType.setContentEnabled(true);
			}
			formType.setRealClass(copyClassType(getClassType(formClass.getReal_class())));
			processFormElement(formType, realContainer, realContainer, realContainer);
			mapping.getCanister().add(objectFactory.createForm(formType));
		} else if (realContainer instanceof FormSearch) {
			SearchFormType sfType = objectFactory.createSearchFormType();
			sfType.setName(realContainer.getId());
			FormSearch formSearch = (FormSearch) realContainer;
			String operator = formSearch.getCombinationOperator().getName();
			sfType.setOperator(operator);
			sfType.setRealClass(copyClassType(getClassType(formSearch.getReal_class())));
			processFormElement(sfType, realContainer, realContainer, realContainer);
			mapping.getCanister().add(objectFactory.createSearch(sfType));
		} else if (realContainer instanceof FormWorkflow) {
			FormWorkflow formWorkflow = ((FormWorkflow) realContainer);

			WorkflowTaskType taskType = objectFactory.createWorkflowTaskType();
			String formName = formWorkflow.getId();
			String taskId = workflowBuildBlueXMLTaskName(formName);

			taskType.setName(formName);
			taskType.setTaskId(taskId);
			FormClass attached = formWorkflow.getDataForm();
			taskType.setDataForm(attached.getId());
			taskType.setTitle(formWorkflow.getLabel());

			// set the assignment.
			// we won't check that the reference is indeed a state or node
			ModelElement mel = formWorkflow.getRef();
			Swimlane swimlane;
			if (mel instanceof StartStateImpl) {
				taskType.setStartTask(true);
				StartState start = ((StartStateImpl) mel);
				swimlane = start.getInitiator();
			} else {
				// taskType.setStartTask(false); // optional attribute so we don't set it
				TaskNode aTask = (TaskNode) mel;
				swimlane = aTask.getSwimlane();
			}
			if (StringUtils.trimToNull(swimlane.getActorid() + swimlane.getPooledactors()) == null) {
				throw new RuntimeException("Wrong assignment in form '" + formName
						+ "'. Needs either 'actorId' or 'pooledActors'");
			}
			taskType.setActorId(swimlane.getActorid());
			taskType.setPooledActors(swimlane.getPooledactors());

			processFormElement(taskType, realContainer, realContainer, realContainer);
			mapping.getCanister().add(objectFactory.createTask(taskType));
		}
	}

	/**
	 * 
	 * @param formType
	 *            placeholder for a form (should be of the appropriate mapping type for search
	 *            forms, i.e. if the modeler did his job of restraining search fields to search
	 *            forms)
	 * @param formContainer
	 *            SHOULD be a FormSearch
	 * @param searchField
	 */
	private void processSearchField(CanisterType formType, FormContainer formContainer,
			SearchField searchField) {
		if (!(formContainer instanceof FormSearch)) {
			throw new RuntimeException("Search fields are allowed only on FormSearch objects. '"
					+ formContainer.getLabel() + "' is not a FormSearch.");
		}
		SearchFieldType fieldType = objectFactory.createSearchFieldType();
		// name
		fieldType.setName(searchField.getId());

		// default operator
		String defaultOp = formGenerator.getSearchFieldDefaultOperator(searchField);
		fieldType.setPick(defaultOp);

		// number of UI input controls
		Attribute attribute = (Attribute) formGenerator.getRealObject(searchField.getRef());
		DataType typ = attribute.getTyp();
		if (isDateType(typ)) {
			fieldType.setInputs("2");
		} else if (isNumericType(typ)) {
			fieldType.setInputs("2");
		}

		// we set the 'type' attribute for data types whose inputs will be initialized
		if (isDateType(typ)) {
			fieldType.setType(typ.getName());
		}
		// enums need special processing by the controller at runtime so let's be courteous
		if (attribute.getValueList() != null) {
			fieldType.setEnum(ModelTools.getCompleteName(attribute.getValueList()));
		}

		// style
		String style = searchField.getStyle();
		if (style != null) {
			CSSCollector.add(style);
		}

		((SearchFormType) formType).getField().add(fieldType);
	}

	private boolean isDateType(DataType typ) {
		return typ.equals(DataType.DATE) || typ.equals(DataType.DATE_TIME);
	}

	private boolean isNumericType(DataType typ) {
		return typ.equals(DataType.BYTE) || typ.equals(DataType.LONG) || typ.equals(DataType.INT)
				|| typ.equals(DataType.SHORT) || typ.equals(DataType.DOUBLE)
				|| typ.equals(DataType.FLOAT);
	}

	/**
	 * Process form element.
	 * 
	 * @param canister
	 *            placeholder for a form (either a workflow or class)
	 * @param parent
	 *            the parent
	 * @param formElement
	 *            the form element
	 * @param formContainer
	 *            either a FormClass or FormWorkflow object
	 */
	private void processFormElement(CanisterType canister, FormContainer formContainer,
			FormElement parent, FormElement formElement) {
		if (formElement instanceof FormGroup) {
			FormGroup formGroup = (FormGroup) formElement;
			EList<FormElement> children = formGroup.getChildren();
			for (FormElement child : children) {
				processFormElement(canister, formContainer, formGroup, child);
			}
		} else {
			if (formElement instanceof Reference) {
				processReference(canister, formContainer, (Reference) formElement);
			} else if (formElement instanceof ModelChoiceField) {
				processChoiceField(canister, formContainer, (ModelChoiceField) formElement);
			} else if (formElement instanceof VirtualField) {
				processVirtual(canister, (VirtualField) formElement);
			} else if (formElement instanceof ActionField) {
				processActionField(canister, formContainer, (Field) formElement);
			} else if (formElement instanceof Field) {
				processField(canister, formContainer, (Field) formElement);
			} else if (formElement instanceof SearchField) {
				processSearchField(canister, formContainer, (SearchField) formElement);
			}
		}
	}

	/**
	 * Process action field.
	 * 
	 * @param canister
	 *            the FormType or workflowTaskType
	 * @param formContainer
	 *            the form container: FormClass or FormWorkflow
	 * @param field
	 *            the field
	 * @deprecated I don't see any motive in this function since the info kept here (except for the
	 *             style) is never used anywhere (@Amenel).
	 */
	private void processActionField(CanisterType canister, FormContainer formContainer, Field field) {
		ActionFieldType actionFieldType = objectFactory.createActionFieldType();

		// inherited properties
		actionFieldType.setUniqueName(FormGeneratorsManager.getUniqueName(field));
		String style = field.getStyle();
		if (style != null) {
			CSSCollector.add(style);
		}
		// own properties
		if (formContainer instanceof FormWorkflow) {
			actionFieldType.setInWorkflow(true);
		}
		actionFieldType.setLabel(field.getLabel());
		// enlist
		canister.getAction().add(actionFieldType);
	}

	/**
	 * Process field.
	 * 
	 * @param canister
	 *            the FormType or workflowTaskType
	 * @param field
	 *            the field
	 * @param formClass
	 *            the form class
	 */
	private void processField(CanisterType canister, FormContainer formContainer, Field field) {
		String result;
		boolean isMultiple = false;
		FormFieldType formFieldType = objectFactory.createFormFieldType();
		List<FormFieldType> fieldTypesList;

		if (canister instanceof WorkflowTaskType) {
			fieldTypesList = ((WorkflowTaskType) canister).getField();
		} else {
			fieldTypesList = ((FormType) canister).getField();
		}

		formFieldType.setUniqueName(FormGeneratorsManager.getUniqueName(field));
		String style = field.getStyle();
		if (style != null) {
			CSSCollector.add(style);
		}

		ModelElement ref = (ModelElement) formGenerator.getRealObject(field.getRef());
		if (formContainer instanceof FormClass) {
			FormClass formClass = (FormClass) formContainer;
			Clazz realClass = null;
			try {
				realClass = (Clazz) formGenerator.getRealObject(formClass.getReal_class());
			} catch (ClassCastException cce) {
				throw new RuntimeException("The model element in property <Real class> of form '"
						+ formClass.getId() + "' is not a class.");
			}
			//
			// check that the ref'ed attribute is legitimate for this FormClass
			if (checkClassAttributeInclusion(realClass, ref) == false) {
				throw new RuntimeException(
						"The field '"
								+ field.getLabel()
								+ "' on form '"
								+ formClass.getId()
								+ "' has a bad <Ref> property: either the model element is not an attribute or the attribute belongs to another class.");
			}
			String alfrescoName = getAlfrescoNameForAttribute(realClass, ref);
			formFieldType.setAlfrescoName(alfrescoName);
		} else {
			com.bluexml.side.workflow.Attribute attribute = (com.bluexml.side.workflow.Attribute) ref;
			formFieldType.setAlfrescoName(attribute.getName());
		}

		String initialValue = field.getInitial();
		if (initialValue != null) {
			formFieldType.setDefault(initialValue); // optional attribute
		}
		if (field.isMandatory()) {
			formFieldType.setMandatory(true); // optional attribute
		}

		if (field instanceof ChoiceField) {
			ChoiceField choiceField = (ChoiceField) field;
			if (choiceField.getWidget() == ChoiceWidgetType.LIST_ALL) {
				// FIXME: why would the widget determine where the captions of the enum literals are
				// fetched from ?
				formFieldType.setSearchEnum(true); // optional attribute
			}
		}
		if (ref instanceof Attribute) {
			Attribute attribute = (Attribute) ref;
			formFieldType.setType(attribute.getTyp().getLiteral());
			if (attribute.getValueList() != null) {
				if (!attribute.getValueList().getDynamic()) {
					formFieldType.setStaticEnumType(ModelTools.getCompleteName(attribute
							.getValueList()));
				}
			}

			result = ModelTools.getMetaInfoValue(attribute, "multiple");
			if (result != null) {
				if (StringUtils.equalsIgnoreCase(result, "true")) {
					formFieldType.setMultiple(isMultiple); // optional attribute
				}
			}
		} else if (ref instanceof com.bluexml.side.workflow.Attribute) {
			com.bluexml.side.workflow.Attribute attribute = ((com.bluexml.side.workflow.Attribute) ref);
			formFieldType.setType(attribute.getTyp().getLiteral());
		} else {
			formFieldType.setType("String");
		}

		// boolean attributes
		boolean disabled = field.isDisabled();
		if (disabled) {
			formFieldType.setReadOnly(disabled); // optional attribute
		}

		// TODO: remove
		// formFieldType.setDummyValue(pickDummyValue(formFieldType.getType())); // optional
		if (field instanceof FileField) {
			FileFieldType fileFieldType = initFileFieldFromFormField(formFieldType);

			if (((FileField) field).isInRepository()) {
				fileFieldType.setInRepository(true);
			}
			fieldTypesList.add(fileFieldType);
		} else {
			// check that if mandatory, the field has an initial value
			if (field.isMandatory() && (StringUtils.trimToNull(field.getInitial()) == null)) {
				monitor.addErrorTextAndLog("Attribute '" + formFieldType.getAlfrescoName()
						+ "' is mandatory: it should have an initial value!", null, null);
			}
			fieldTypesList.add(formFieldType);
		}

	}

	/**
	 * Computes the name of an attribute as specified in the Alfresco models.
	 * 
	 * @param classe
	 * @param ref
	 * @return
	 */
	private String getAlfrescoNameForAttribute(AbstractClass classe, ModelElement ref) {
		boolean noPrefix = false;

		Attribute attribute = (Attribute) ref;
		for (Tag tag : attribute.getTags()) {
			if (tag.getKey().equals(MsgId.INT_GEN_REVERSED_TAG_KEY.getText())) {
				if (tag.getValue().contains(MsgId.INT_GEN_REVERSED_TAG_VAL_ALFRESCO.getText())) {
					noPrefix = true;
					break;
				}
			}
		}
		if (noPrefix) {
			return attribute.getName();
		}
		String result = FormGeneratorsManager.getClassQualifiedName(classe) + "_"
				+ attribute.getName();
		return result;
	}

	/**
	 * Checks that the field reference belongs to the class reference, taking into account
	 * generalizations. If the attribute is directly included in the class or comes from a parent
	 * class, then the return value if <em>true</em>.
	 * 
	 * @param classRef
	 * @param fieldRef
	 * @return false if the field reference is not a class diagram attribute or does not belong to
	 *         the given class. True otherwise.
	 */
	private boolean checkClassAttributeInclusion(Clazz classRef, ModelElement fieldRef) {
		try {
			@SuppressWarnings("unused")
			Attribute attr = (Attribute) fieldRef;
		} catch (ClassCastException cce) {
			return false;
		}
		EObject container = ((EObject) fieldRef).eContainer();
		ModelElement realContainer = (ModelElement) formGenerator.getRealObject(container);
		if (classRef.equals(realContainer) == true) {
			return true;
		}

		// the field ref's container may be a superclass of the class ref
		for (Clazz parentClass : classRef.getGeneralizations()) {
			Clazz realParentClass = (Clazz) formGenerator.getRealObject(parentClass);
			if (checkClassAttributeInclusion(realParentClass, fieldRef)) {
				return true;
			}
		}

		// the field ref's container may be one of the class' aspects
		for (Aspect aspect : classRef.getAspects()) {
			Aspect realAspect = (Aspect) formGenerator.getRealObject(aspect);
			if (realAspect.equals(realContainer)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Provides a dummy value for a specific type.
	 * 
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unused")
	private String pickDummyValue(String type) {
		String[] strings = {
				"alpha",
				"beta",
				"gamma",
				"delta",
				"kappa",
				"sigma",
				"zeta",
				"omega",
				"everything",
				"something" };
		if (type.equalsIgnoreCase("Date")) {
			return null;
		}
		Random alea = new Random();
		if (type.equalsIgnoreCase("String")) {
			return strings[alea.nextInt(strings.length)];
		}
		if (type.equalsIgnoreCase("boolean")) {
			if (alea.nextInt() % 2 == 0) {
				return "true";
			}
			return "false";
		}
		if (type.equalsIgnoreCase("int") || type.equalsIgnoreCase("integer")) {
			return "" + alea.nextInt();
		}
		if (type.equalsIgnoreCase("float")) {
			return "" + alea.nextFloat();
		}
		return null;
	}

	/**
	 * Initializes a FileFieldType with info from a FormFieldType
	 * 
	 * @param formFieldType
	 * @return a FileFieldType object that has the same info as the parameter
	 */
	private FileFieldType initFileFieldFromFormField(FormFieldType formFieldType) {

		FileFieldType res = objectFactory.createFileFieldType();
		res.setUniqueName(formFieldType.getUniqueName());

		res.setAlfrescoName(formFieldType.getAlfrescoName());
		res.setType(formFieldType.getType());
		res.setStaticEnumType(formFieldType.getStaticEnumType());

		//
		// optional attributes
		if (StringUtils.trimToNull(formFieldType.getDefault()) != null) {
			res.setDefault(formFieldType.getDefault());
		}
		try {
			res.setMultiple(formFieldType.isMultiple());
			res.setSearchEnum(formFieldType.isSearchEnum());
		} catch (Exception e) {
			// nothing to do, these are optional attributes
		}
		return res;
	}

	/**
	 * Process virtual.
	 * 
	 * @param canister
	 *            the FormType or workflowTaskType
	 * @param virtualField
	 *            the virtual field
	 */
	private void processVirtual(CanisterType canister, VirtualField virtualField) {
		Field linkedField = virtualField.getLink();
		if (linkedField instanceof VirtualField) {
			processVirtual(canister, (VirtualField) linkedField);
		}

		FormClass formClass = getFormClass(linkedField);
		VirtualFieldType virtualFieldType = objectFactory.createVirtualFieldType();
		// inherited properties
		virtualFieldType.setUniqueName(FormGeneratorsManager.getUniqueName(virtualField));
		String style = virtualField.getStyle();
		if (style != null) {
			CSSCollector.add(style);
		}
		// own properties
		virtualFieldType.setFieldName(FormGeneratorsManager.getUniqueName(linkedField));
		virtualFieldType.setFormName(formClass.getId());
		if (canister instanceof FormType) {
			FormType formType = (FormType) canister;
			formType.getVirtual().add(virtualFieldType);
		}
	}

	/**
	 * Gets the form class.
	 * 
	 * @param field
	 *            the field
	 * 
	 * @return the form class
	 */
	private FormClass getFormClass(Field field) {
		EObject container = field.eContainer();
		while (!(container instanceof FormClass)) {
			container = container.eContainer();
		}
		FormClass formClass = (FormClass) container;
		return formClass;
	}

	/**
	 * Process choice field.
	 * 
	 * @param canister
	 *            the FormType or workflowTaskType
	 * @param formElement
	 *            the form element
	 * @param formClass
	 *            the form class
	 */
	private void processChoiceField(CanisterType canister, FormContainer formContainer,
			ModelChoiceField formElement) {
		List<ModelChoiceType> list = null;
		if (canister instanceof WorkflowTaskType) {
			list = ((WorkflowTaskType) canister).getModelChoice();
		} else {
			list = ((FormType) canister).getModelChoice();
		}
		ModelChoiceType modelChoiceType = objectFactory.createModelChoiceType();
		processChoiceFieldCommon(formElement, formContainer, modelChoiceType);
		list.add(modelChoiceType);
	}

	/**
	 * Process reference.
	 * 
	 * @param canister
	 *            the FormType or workflowTaskType
	 * @param reference
	 *            the reference
	 * @param formClass
	 *            the form class
	 */
	private void processReference(CanisterType canister, FormContainer formContainer,
			Reference reference) {
		// TODO: update the condition when references become available on FormWorkflow
		if (canister instanceof FormType) {
			ReferenceType referenceType = objectFactory.createReferenceType();
			processChoiceFieldCommon(reference, formContainer, referenceType);
			FormType formType = (FormType) canister;
			formType.getReference().add(referenceType);
		}
	}

	/**
	 * New form type.
	 * 
	 * @param formContainer
	 *            the form container object
	 * 
	 * @return the form type
	 */
	private FormType newFormType(FormContainer form) {
		FormContainer realContainer = (FormContainer) formGenerator.getRealObject(form); // #1225

		FormType childFormType = objectFactory.createFormType();
		childFormType.setName(realContainer.getId());
		return childFormType;
	}

	/**
	 * Process choice field common.
	 * 
	 * @param modelChoiceField
	 *            the model choice field
	 * @param modelChoiceType
	 *            the model choice type
	 * @param formClass
	 *            the form class
	 */
	private void processChoiceFieldCommon(ModelChoiceField modelChoiceField,
			FormContainer formContainer, ModelChoiceType modelChoiceType) {
		String style = modelChoiceField.getStyle();
		if (style != null) {
			CSSCollector.add(style);
		}

		modelChoiceType.setDisplayLabel(modelChoiceField.getLabel()); // #1212

		if (modelChoiceField.getWidget() == ModelChoiceWidgetType.INLINE) {
			modelChoiceType.setInline(true); // optional attribute
		}
		modelChoiceType.setMaxBound(modelChoiceField.getMax_bound());
		modelChoiceType.setMinBound(modelChoiceField.getMin_bound());
		modelChoiceType.setUniqueName(FormGeneratorsManager.getUniqueName(modelChoiceField));
		String alfrescoName = formGenerator.getAlfrescoName(modelChoiceField.getReal_class(),
				modelChoiceField.getRef()); // #980
		modelChoiceType.setAlfrescoName(alfrescoName);
		modelChoiceType.setRealClass(copyClassType(getClassType(modelChoiceField.getReal_class())));

		EList<FormContainer> targets = modelChoiceField.getTarget();
		for (FormContainer target : targets) {
			if (target instanceof FormClass) {
				FormType childFormType = newFormType(target);
				modelChoiceType.getTarget().add(childFormType);
			}
		}

		// field size
		if (modelChoiceField.getField_size() > 0) {
			String lsize = "" + modelChoiceField.getField_size();
			modelChoiceType.setFieldSize(StringUtils.trim(lsize));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#beginListForms()
	 */
	public void beginListForms() {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#endForm(com.bluexml.side.form .Form)
	 */
	public void endForm(FormContainer form) {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.GeneratorInterface#endListForms()
	 */
	public void endListForms() {
		// nothing
	}

}
