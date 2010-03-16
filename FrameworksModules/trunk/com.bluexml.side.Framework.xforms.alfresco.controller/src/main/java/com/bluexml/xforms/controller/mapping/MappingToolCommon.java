package com.bluexml.xforms.controller.mapping;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.xforms.actions.EnumAction;
import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
import com.bluexml.xforms.controller.beans.FileUploadInfoBean;
import com.bluexml.xforms.controller.beans.PersistFormResultBean;
import com.bluexml.xforms.controller.binding.ActionFieldType;
import com.bluexml.xforms.controller.binding.AspectType;
import com.bluexml.xforms.controller.binding.AssociationType;
import com.bluexml.xforms.controller.binding.AttributeType;
import com.bluexml.xforms.controller.binding.Batch;
import com.bluexml.xforms.controller.binding.CanisterType;
import com.bluexml.xforms.controller.binding.ClassType;
import com.bluexml.xforms.controller.binding.EnumType;
import com.bluexml.xforms.controller.binding.FieldType;
import com.bluexml.xforms.controller.binding.FileFieldType;
import com.bluexml.xforms.controller.binding.FormFieldType;
import com.bluexml.xforms.controller.binding.FormType;
import com.bluexml.xforms.controller.binding.GenericAttribute;
import com.bluexml.xforms.controller.binding.GenericClass;
import com.bluexml.xforms.controller.binding.Mapping;
import com.bluexml.xforms.controller.binding.ModelChoiceType;
import com.bluexml.xforms.controller.binding.ObjectFactory;
import com.bluexml.xforms.controller.binding.SearchFormType;
import com.bluexml.xforms.controller.binding.ValueType;
import com.bluexml.xforms.controller.binding.WorkflowTaskType;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * The Class MappingToolCommon.
 */
public class MappingToolCommon {

	private static final String TRACE_LOGGER_CATEGORY = "com.bluexml.xforms.controller.mapping.trace";

	/** The document builder. */
	protected static DocumentBuilder documentBuilder;

	/** The document transformer. */
	protected static Transformer documentTransformer;

	/** The alfresco object factory. */
	protected static ObjectFactory alfrescoObjectFactory = new ObjectFactory();

	/** The logger. */
	protected static Log logger = LogFactory.getLog(MappingToolCommon.class);

	/** Trace logging detector. DO NO OUTPUT LOGS USING THIS. */
	protected static Log loggertrace = LogFactory.getLog(TRACE_LOGGER_CATEGORY);

	/** The number types. */
	protected static List<String> numberTypes = Arrays.asList("byte", "double", "float", "int",
			"long", "short");

	/** The mapping. */
	protected Mapping mapping;

	/** The controller. */
	protected AlfrescoController controller;

	/** The alfresco unmarshaller. */
	private static Unmarshaller alfrescoUnmarshaller;

	/** The alfresco marshaller. */
	private static Marshaller alfrescoMarshaller;
	static {
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance("com.bluexml.xforms.controller.binding");
			alfrescoMarshaller = jaxbContext.createMarshaller();
			alfrescoMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			alfrescoMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			alfrescoUnmarshaller = jaxbContext.createUnmarshaller();

			documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			documentTransformer = TransformerFactory.newInstance().newTransformer();
			documentTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Marshal.
	 * 
	 * @param alfrescoClass
	 *            the alfresco class
	 * @param os
	 *            the os
	 * 
	 * @throws JAXBException
	 *             the JAXB exception
	 */
	protected static synchronized void marshal(GenericClass alfrescoClass, OutputStream os)
			throws JAXBException {
		alfrescoMarshaller.marshal(alfrescoObjectFactory.createClass(alfrescoClass), os);
	}

	private static synchronized void marshal(Batch batch, ByteArrayOutputStream os)
			throws JAXBException {
		alfrescoMarshaller.marshal(batch, os);
	}

	/**
	 * Marshal.
	 * 
	 * @param alfrescoClass
	 *            the alfresco class
	 * 
	 * @return the string
	 * 
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private static String marshal(GenericClass alfrescoClass) throws ServletException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			marshal(alfrescoClass, os);
		} catch (JAXBException e) {
			throw new ServletException(e);
		}
		String datas = "";
		try {
			datas = os.toString("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new ServletException(e);
		}
		return datas;
	}

	/**
	 * Marshalls the batch operations into a string.
	 * 
	 * @param batch
	 * @return
	 * @throws ServletException
	 */
	public static String marshal(Batch batch) throws ServletException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			marshal(batch, os);
		} catch (JAXBException e) {
			throw new ServletException(e);
		}
		String datas = "";
		try {
			datas = os.toString("UTF-8"); // #1295
		} catch (UnsupportedEncodingException e) {
			String message = "UTF-8 encoding is unsupported.";
			logger.error(message, e);
			throw new ServletException(message);
		}
		return datas;
	}

	/**
	 * Unmarshal.
	 * 
	 * @param alfrescoNode
	 *            the alfresco node
	 * 
	 * @return the com.bluexml.xforms.controller.alfresco.binding. class
	 * 
	 * @throws JAXBException
	 *             the JAXB exception
	 */
	public static synchronized GenericClass unmarshal(Document alfrescoNode) throws JAXBException {
		JAXBElement<GenericClass> alfrescoClass = alfrescoUnmarshaller.unmarshal(alfrescoNode,
				GenericClass.class);
		return alfrescoClass.getValue();
	}

	public static String packageConcate(String parent, String name) {
		if (parent.equals("")) {
			return name;
		}
		return parent + "." + name;
	}

	class TransformInfoBean {
		public String result;
		public int positionWhereStopped; // set only in the Date transform. -1 otherwise

		public TransformInfoBean(String value, int position) {
			this.result = value;
			this.positionWhereStopped = position;
		}
	}

	/**
	 * Instantiates a new mapping tool common.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param controller
	 *            the controller
	 */
	public MappingToolCommon(Mapping mapping, AlfrescoController controller) {
		super();
		this.mapping = mapping;
		this.controller = controller;
	}

	/**
	 * Class type to string.
	 * 
	 * @param classType
	 *            the class type
	 * 
	 * @return the string
	 */
	protected String classTypeToString(ClassType classType) {
		return packageConcate(classType.getPackage(), classType.getName());
	}

	/**
	 * Gets the aspect type.
	 * 
	 * @param aspectDecl
	 *            the aspect decl
	 * 
	 * @return the aspect type
	 */
	public AspectType getAspectType(AspectType aspectDecl) {
		List<AspectType> aspects = mapping.getAspect();
		for (AspectType aspectType : aspects) {
			if (aspectDecl.getPackage().equals(aspectType.getPackage())
					&& aspectDecl.getName().equals(aspectType.getName())) {
				return aspectType;
			}
		}
		return null;
	}

	public EnumType getEnumType(String type) {
		List<EnumType> enums = mapping.getEnum();
		for (EnumType enumType : enums) {
			if (type.equals(enumTypeToString(enumType))) {
				return enumType;
			}
		}
		return null;
	}

	private String enumTypeToString(EnumType enumType) {
		return packageConcate(enumType.getPackage(), enumType.getName());
	}

	/**
	 * Gets the class type.
	 * 
	 * @param type
	 *            the type
	 * 
	 * @return the class type
	 */
	protected ClassType getClassType(ClassType type) {
		List<ClassType> clazz = mapping.getClazz();
		for (ClassType classType : clazz) {
			if (type.getPackage().equals(classType.getPackage())
					&& type.getName().equals(classType.getName())) {
				return classType;
			}
		}
		return null;
	}

	/**
	 * Gets the class type.
	 * 
	 * @param type
	 *            the type, under the form package + "." + name
	 * 
	 * @return the class type
	 */
	public ClassType getClassType(String type) {
		List<ClassType> clazz = mapping.getClazz();
		for (ClassType classType : clazz) {
			String refType = classTypeToString(classType);
			if (type.equals(refType)) {
				return classType;
			}
		}
		return null;
	}

	/**
	 * Gets the complete name of a class type.
	 * 
	 * @param classType
	 *            the type
	 * 
	 * @return the complete name of the type, under the form package + "." + name
	 */
	public String getClassTypeCompleteName(ClassType classType) {
		return classTypeToString(classType);
	}

	/**
	 * Gets the id of a form that supports the given data type.
	 * 
	 * @param dataType
	 *            a node type as returned by Alfresco
	 * 
	 * @return the class type, under the form [package].[class name]
	 */
	public String getDefaultFormForDatatype(String dataType) {
		List<ClassType> clazz = mapping.getClazz();
		for (ClassType classType : clazz) {
			if (classType.getAlfrescoName().equals(dataType)) {
				return classType.getPackage() + "." + classType.getName();
			}
		}
		return null;
	}

	/**
	 * Gets the form type that matches the given name.
	 * 
	 * @param formName
	 *            the form name
	 * 
	 * @return the form type
	 */
	public FormType getFormType(String formName) {
		List<JAXBElement<? extends CanisterType>> elements = mapping.getCanister();

		for (JAXBElement<? extends CanisterType> element : elements) {
			if (element.getValue() instanceof FormType) {
				FormType formType = (FormType) element.getValue();
				if (formType.getName().equals(formName)) {
					return formType;
				}
			}
		}
		return null;
	}

	/**
	 * Gets the name of the form type that supports the given data type (i.e. whose real class's
	 * name matches the data type).
	 * 
	 * @param formName
	 *            the form name
	 * 
	 * @return the form type
	 */
	public String getCustomFormForDatatype(String dataType) {
		List<JAXBElement<? extends CanisterType>> elements = mapping.getCanister();

		for (JAXBElement<? extends CanisterType> element : elements) {
			if (element.getValue() instanceof FormType) {
				FormType formType = (FormType) element.getValue();
				if (formType.getRealClass().getAlfrescoName().equals(dataType)) {
					return formType.getName();
				}
			}
		}
		return null;
	}

	/**
	 * Gets the search form type that matches the given name.
	 * 
	 * @param formName
	 *            the form name
	 * 
	 * @return the search form type
	 */
	public SearchFormType getSearchFormType(String formName) {
		List<JAXBElement<? extends CanisterType>> elements = mapping.getCanister();

		for (JAXBElement<? extends CanisterType> element : elements) {
			if (element.getValue() instanceof SearchFormType) {
				SearchFormType formType = (SearchFormType) element.getValue();
				if (formType.getName().equals(formName)) {
					return formType;
				}
			}
		}
		return null;
	}

	/**
	 * Gets (from the mapping) the WorkflowTaskType object that matches the name.
	 * 
	 * @param refName
	 *            the name to test against, either a task id (e.g. "wfbxwfTest:Start") or a form
	 *            name (e.g. "wfTest_Start")
	 * @param byId
	 *            if true, the task id from mapping.xml is tested. Otherwise, the form name is
	 *            tested.
	 * @return the form type that matches
	 */
	public WorkflowTaskType getWorkflowTaskType(String refName, boolean byId) {
		List<JAXBElement<? extends CanisterType>> elements = mapping.getCanister();

		String testValue;
		for (JAXBElement<? extends CanisterType> element : elements) {
			if (element.getValue() instanceof WorkflowTaskType) {
				WorkflowTaskType taskType = (WorkflowTaskType) element.getValue();
				if (byId) {
					testValue = taskType.getTaskId();
				} else {
					testValue = taskType.getName();
				}
				if (testValue.equals(refName)) {
					return taskType;
				}
			}
		}
		return null;
	}

	/**
	 * Gets (from the mapping) the WorkflowTaskType object that contains the field whose
	 * 'uniqueName' matches the given field name.
	 * 
	 * @param fieldName
	 * @return
	 */
	public WorkflowTaskType getWorkflowTaskTypeWithField(String fieldName) {
		List<JAXBElement<? extends CanisterType>> elements = mapping.getCanister();

		for (JAXBElement<? extends CanisterType> element : elements) {
			if (element.getValue() instanceof WorkflowTaskType) {
				WorkflowTaskType taskType = (WorkflowTaskType) element.getValue();
				List<FormFieldType> fields = taskType.getField();
				for (FormFieldType field : fields) {
					if (StringUtils.equals(field.getUniqueName(), fieldName)) {
						return taskType;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Gets (from the mapping) the name of the form that is the start task for the given definition
	 * name.<br/>
	 * e.g. "wfTest" returns "wfTest_Start" knowing the start task is named "Start".
	 * 
	 * @param namespacePrefix
	 *            the name to test against
	 * @return the name of the start task
	 */
	public String getWorkflowStartTaskFormName(String namespacePrefix) {
		List<JAXBElement<? extends CanisterType>> elements = mapping.getCanister();

		for (JAXBElement<? extends CanisterType> element : elements) {
			if (element.getValue() instanceof WorkflowTaskType) {
				WorkflowTaskType taskType = (WorkflowTaskType) element.getValue();
				if (isStartTask(taskType)) {
					String formName = taskType.getName();
					if (formName.startsWith(namespacePrefix + "_")) {
						return formName;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Gets the Alfresco name of the form field whose 'uniqueName' matches the given field name in
	 * the task form.
	 * 
	 * @param formName
	 *            the id of the workflow form
	 * @param fieldName
	 *            the field name
	 * 
	 * @return the Alfresco name of the form field
	 */
	public String getWorkflowFieldAlfrescoName(String formName, String fieldName) {
		List<FormFieldType> fields = null;
		WorkflowTaskType taskType = getWorkflowTaskType(formName, false);
		fields = taskType.getField();

		if (fields != null) {
			for (FormFieldType formFieldType : fields) {
				if (formFieldType.getUniqueName().equals(fieldName)) {
					return formFieldType.getAlfrescoName();
				}
			}
		}
		return null;
	}

	/**
	 * Gets the class type q name.
	 * 
	 * @param qNameType
	 *            the q name type
	 * 
	 * @return the class type q name
	 */
	protected ClassType getClassTypeAlfrescoName(String qNameType) {
		List<ClassType> clazz = mapping.getClazz();
		for (ClassType classType : clazz) {
			if (qNameType.equals(classType.getAlfrescoName())) {
				return classType;
			}
		}
		return null;
	}

	/**
	 * Gets the parent class types.
	 * 
	 * @param classType
	 *            the class type
	 * 
	 * @return the parent class types
	 */
	protected List<ClassType> getParentClassTypes(ClassType classType) {
		List<ClassType> result = new ArrayList<ClassType>();
		recursiveGetParentClassTypes(classType, result);
		return result;
	}

	/**
	 * Gets the id.
	 * 
	 * @param node
	 *            the node
	 * 
	 * @return the id
	 */
	public String getId(Node node) {
		Element idElement = DOMUtil.getChild(node, MsgId.INT_INSTANCE_SIDEID.getText());
		String id = null;
		if (idElement != null) {
			id = StringUtils.trimToNull(idElement.getTextContent());

		}
		return id;
	}

	protected void addId(Document xformsDocument, Node element, String alfrescoId) {
		if (alfrescoId != null) {
			Element idElement = xformsDocument.createElement(MsgId.INT_INSTANCE_SIDEID.getText());
			idElement.setTextContent(alfrescoId);
			element.appendChild(idElement);
		}
	}

	/**
	 * Gets the label.
	 * 
	 * @param node
	 *            the node
	 * 
	 * @return the label
	 */
	public String getLabel(Node node) {
		Element idElement = DOMUtil.getChild(node, MsgId.INT_INSTANCE_SIDELABEL.getText());
		String id = null;
		if (idElement != null) {
			id = StringUtils.trimToNull(idElement.getTextContent());

		}
		return id;
	}

	/**
	 * Log xml.
	 * 
	 * @param node
	 *            the node
	 * @param messages
	 *            the messages
	 */
	public void logXML(Node node, String... messages) {
		if (logger.isDebugEnabled()) {
			for (String message : messages) {
				logger.debug(message);
			}
			if (node != null) {
				Source xmlSource = new DOMSource(node);
				StringWriter sw = new StringWriter();
				Result outputTarget = new StreamResult(sw);
				try {
					documentTransformer.transform(xmlSource, outputTarget);
				} catch (TransformerException e) {
					logger.error(e);
				}
				logger.debug(sw.toString());
			}
		}
	}

	/**
	 * Recursive get parent class types.
	 * 
	 * @param classType
	 *            the class type
	 * @param result
	 *            the result
	 */
	protected void recursiveGetParentClassTypes(ClassType classType, List<ClassType> result) {
		ClassType realClassType = getClassType(classType);
		if (!result.contains(realClassType)) {
			result.add(realClassType);
			ClassType parentClass = realClassType.getParentClass();
			if (parentClass != null) {
				recursiveGetParentClassTypes(parentClass, result);
			}
		}
	}

	/**
	 * Safe map get.
	 * 
	 * @param map
	 *            the map
	 * @param key
	 *            the key
	 * 
	 * @return the string
	 */
	protected String safeMapGet(Map<String, String> map, String key) {
		if (map != null) {
			return map.get(key);
		}
		return null;
	}

	/**
	 * Sub map.
	 * 
	 * @param map
	 *            the map
	 * @param start
	 *            the start
	 * 
	 * @return the map< string, string>
	 */
	protected Map<String, String> subMap(Map<String, String> map, String start) {
		Map<String, String> result = new TreeMap<String, String>();
		if (map != null) {
			Set<Entry<String, String>> entrySet = map.entrySet();
			for (Entry<String, String> entry : entrySet) {
				String key = entry.getKey();
				if (key.startsWith(start + ".")) {
					result.put(key.substring(start.length() + 1), entry.getValue());
				}
			}
		}
		return result;
	}

	/**
	 * Convert attribute values returned by Alfresco to their XForms equivalent. NOTE:
	 * <b>enumerations have a specific processing.</b> Used when building a form from an existing
	 * repository object.
	 * 
	 * @param textContent
	 *            the text content
	 * @param xformsAttribute
	 *            the xforms attribute
	 * 
	 * @return the string
	 */
	protected String convertAlfrescoAttributeToXforms(String textContent, String xformsAttribute,
			String staticEnumType) {
		if (xformsAttribute.equalsIgnoreCase("DateTime")) {
			return DateTimeConverter.convert_AlfrescoToXForms_DateTime(textContent);
		}
		if (xformsAttribute.equalsIgnoreCase("Date")) {
			String localTimeZone = createXFormsInitialValue("Time", null, null).substring(12);
			return DateTimeConverter.convert_AlfrescoToXForms_Date(textContent, localTimeZone);
		}
		if (xformsAttribute.equalsIgnoreCase("Time")) {
			return DateTimeConverter.convert_AlfrescoToXForms_Time(textContent);
		}
		if (xformsAttribute.equalsIgnoreCase("double") || xformsAttribute.equalsIgnoreCase("float")) {
			return textContent.replace(',', '.');
		}

		String realTextContent = textContent;
		if (StringUtils.trimToNull(staticEnumType) != null) {
			realTextContent = StringUtils.trimToEmpty(EnumAction.getEnumKey(staticEnumType,
					textContent));
		}
		return realTextContent;
	}

	protected String convertAlfrescoAttributeToXforms(List<ValueType> value, String type,
			String staticEnumType) {
		StringBuffer result = new StringBuffer();
		boolean first = true;
		for (ValueType valueType : value) {
			if (!first) {
				result.append(" ");
			}
			result.append(convertAlfrescoAttributeToXforms(valueType.getValue(), type,
					staticEnumType));
			first = false;
		}
		return result.toString();
	}

	/**
	 * Convert xforms attribute to alfresco.
	 * 
	 * @param textContent
	 *            the text content
	 * @param type
	 *            the type
	 * 
	 * @return the string
	 */
	protected String convertXformsAttributeToAlfresco(String textContent, String type,
			String staticEnumType) {
		if (type.equalsIgnoreCase("DateTime")) {
			return DateTimeConverter.convert_XFormsToAlfresco_DateTime(textContent);
		}
		if (type.equalsIgnoreCase("Date")) {
			return DateTimeConverter.convert_XFormsToAlfresco_Date(textContent);
		}
		if (type.equalsIgnoreCase("Time")) {
			return DateTimeConverter.convert_XFormsToAlfresco_Time(textContent);
		}
		if (type.equalsIgnoreCase("double") || type.equalsIgnoreCase("float")) {
			return textContent.replace(',', '.');
		}
		if (StringUtils.trimToNull(staticEnumType) != null) {
			String res = StringUtils.trimToEmpty(EnumAction.getEnumValue(staticEnumType,
					textContent));
			if (res == null) {
				if (logger.isErrorEnabled()) {
					logger.error("The value '" + textContent
							+ "' is not a valid for enumeration type '" + staticEnumType
							+ "'; will be overriden");
				}
				res = "1"; // inspired by #941: always initialize enums
			}
			return res;
		}
		return textContent;
	}

	protected void convertXformsAttributeToAlfresco(GenericAttribute attribute, String textContent,
			String type, String staticEnumType) {
		String[] values = textContent.split(" ");
		for (String rawValue : values) {
			String value = convertXformsAttributeToAlfresco(rawValue, type, staticEnumType);
			if (StringUtils.trimToNull(value) != null) {
				ValueType valueType = alfrescoObjectFactory.createValueType();
				valueType.setValue(value);
				attribute.getValue().add(valueType);
			}
		}
	}

	/**
	 * Return an initial value for a form field, depending on the data type.<br/>
	 * For instance, <b>boolean</b> is initialized to <b>false</b>, <b>Date</b> is initialized to
	 * the system date, etc. If the field is a static enumeration, returns the key that corresponds
	 * to the initial value.
	 * 
	 * @param attributeType
	 *            the attribute type
	 * @param candidateValue
	 *            the default value, if any, that will be returned. May be <b>null</b>
	 * @param enumType
	 *            the name of the static enumeration the candidate value comes from
	 * 
	 * @return the value, as a string
	 */
	protected String createXFormsInitialValue(String attributeType, String candidateValue,
			String enumType) {
		if (attributeType.equalsIgnoreCase("DateTime")) {
			return DateTimeConverter.convert_AlfrescoToXForms_DateTime(new Date().getTime());
		}
		if (attributeType.equalsIgnoreCase("Date")) {
			return DateTimeConverter.convert_AlfrescoToXForms_Date(new Date().getTime());
		}
		if (attributeType.equalsIgnoreCase("Time")) {
			return DateTimeConverter.convert_AlfrescoToXForms_Time(new Date().getTime());
		}

		if (attributeType.equalsIgnoreCase("boolean")) {
			return "false";
		}
		if (numberTypes.indexOf(attributeType) != -1) {
			return "0";
		}
		String resValue = candidateValue;
		if (StringUtils.trimToNull(enumType) != null) {
			resValue = StringUtils.trimToNull(EnumAction.getEnumKey(enumType, candidateValue));
			if (resValue == null) { // fix for #941: always initialize enums
				resValue = "1";
			}
		}
		return (resValue == null) ? "" : resValue;
	}

	/*
	 * HELPER FUNCTIONS DUE TO MOVING SOME INFO FROM ELEMENTS TO ATTRIBUTES
	 */

	/*
	 * ActionFieldType
	 */
	protected boolean isInWorkflow(ActionFieldType actionType) {
		if (actionType.isInWorkflow() == null) {
			return false;
		}
		return actionType.isInWorkflow();
	}

	/*
	 * AssociationType
	 */
	protected boolean isInline(AssociationType associationType) {
		if (associationType.isInline() == null) {
			return false;
		}
		return associationType.isInline();
	}

	protected boolean isMultiple(AssociationType associationType) {
		if (associationType.isMultiple() == null) {
			return false;
		}
		return associationType.isMultiple();
	}

	/*
	 * AttributeType
	 */
	protected String getDefault(AttributeType attributeType) {
		try {
			return attributeType.getDefault();
		} catch (Exception e) {
			return null;
		}
	}

	protected String getFieldSize(AttributeType attributeType) {
		try {
			return attributeType.getFieldSize();
		} catch (Exception e) {
			return null;
		}
	}

	protected boolean isDynamicEnum(AttributeType attributeType) {
		if (attributeType.isDynamicEnum() == null) {
			return false;
		}
		return attributeType.isDynamicEnum();
	}

	protected boolean isMultiple(AttributeType attributeType) {
		if (attributeType.isMultiple() == null) {
			return false;
		}
		return attributeType.isMultiple();
	}

	protected boolean isReadOnly(AttributeType attributeType) {
		if (attributeType.isReadOnly() == null) {
			return false;
		}
		return attributeType.isReadOnly();
	}

	/*
	 * EnumType
	 */
	public boolean isDynamic(EnumType enumType) { // public for MappingAgent
		if (enumType.isDynamic() == null) {
			return false;
		}
		return enumType.isDynamic();
	}

	/*
	 * FieldType
	 */
	protected boolean isMandatory(FieldType fieldType) {
		if (fieldType.isMandatory() == null) {
			return false;
		}
		return fieldType.isMandatory();
	}

	/*
	 * FileFieldType
	 */
	protected boolean isInRepository(FileFieldType fieldType) {
		if (fieldType.isInRepository() == null) {
			return false;
		}
		return fieldType.isInRepository();
	}

	/*
	 * FormFieldType
	 */
	protected String getDefault(FormFieldType formFieldType) {
		return formFieldType.getDefault();
	}

	protected boolean isMultiple(FormFieldType formFieldType) {
		if (formFieldType.isMultiple() == null) {
			return false;
		}
		return formFieldType.isMultiple();
	}

	protected boolean isReadOnly(FormFieldType formFieldType) {
		if (formFieldType.isReadOnly() == null) {
			return false;
		}
		return formFieldType.isReadOnly();
	}

	protected boolean isSearchEnum(FormFieldType formFieldType) {
		if (formFieldType.isSearchEnum() == null) {
			return false;
		}
		return formFieldType.isSearchEnum();
	}

	/*
	 * FormType
	 */
	protected boolean isContentEnabled(FormType formType) {
		if (formType.isContentEnabled() == null) {
			return false;
		}
		return formType.isContentEnabled();
	}

	/*
	 * ModelChoiceType
	 */
	public String getFieldSize(ModelChoiceType modelChoiceType) { // public for MappingAgent
		try {
			return modelChoiceType.getFieldSize();
		} catch (Exception e) {
			return null;
		}
	}

	protected boolean isInline(ModelChoiceType modelChoiceType) {
		if (modelChoiceType.isInline() == null) {
			return false;
		}
		return modelChoiceType.isInline();
	}

	protected boolean isOrdered(ModelChoiceType modelChoiceType) {
		if (modelChoiceType.isOrdered() == null) {
			return false;
		}
		return modelChoiceType.isOrdered();
	}

	/*
	 * WorkflowTaskType
	 */
	protected boolean isStartTask(WorkflowTaskType workflowTaskType) {
		if (workflowTaskType.isStartTask() == null) {
			return false;
		}
		return workflowTaskType.isStartTask();
	}

	/*
	 * 
	 */

	/**
	 * Tells whether an AttributeType refers to a file field with upload to file system.
	 * 
	 * @param xformsAttribute
	 *            the object to test
	 * @return the status
	 */
	public boolean isFileContent(AttributeType xformsAttribute) {
		return (xformsAttribute.getName().endsWith("content"));
	}

	/**
	 * Tells whether an AttributeType refers to a file field with upload to the repository.
	 * 
	 * @param xformsAttribute
	 *            the object to test
	 * @return the status
	 */
	public boolean isRepositoryContent(AttributeType xformsAttribute) {
		return (xformsAttribute.getName().endsWith("repositoryContent"));
	}

	/**
	 * Tells whether an AttributeType refers to a file upload field.
	 * 
	 * @param xformsAttribute
	 *            the object to test
	 * @return the status
	 */
	public boolean isFileField(AttributeType xformsAttribute) {
		return (isRepositoryContent(xformsAttribute) || isFileContent(xformsAttribute));
	}

	/**
	 * Tells whether the workflow form whose name is given supports a start task.
	 * 
	 * @param wkFormName
	 * @return false if either the workflow form does not exist or it is not a start task.
	 */
	public boolean isStartTaskForm(String wkFormName) {
		WorkflowTaskType workflowTaskType = getWorkflowTaskType(wkFormName, false);
		if (workflowTaskType == null) {
			return false;
		}
		return isStartTask(workflowTaskType);
	}

	/*
	 * SUPPORT FOR READ ONLY DATES AND TIMES
	 */

	/**
	 * Gets the time from date time.
	 * 
	 * @param value
	 *            the value
	 * 
	 * @return the time from date time
	 */
	protected String getTimeFromDateTime(String value) {
		Calendar date = DatatypeConverter.parseDateTime(value);
		return DateTimeConverter.convert_AlfrescoToXForms_Time(date.getTimeInMillis());
	}

	/**
	 * Gets the date from date time.
	 * 
	 * @param value
	 *            the value
	 * 
	 * @return the date from date time
	 */
	protected String getDateFromDateTime(String value) {
		Calendar date = DatatypeConverter.parseDateTime(value);
		return DateTimeConverter.convert_AlfrescoToXForms_Date(date.getTimeInMillis());
	}

	/**
	 * Gets the date time from date and time.
	 * 
	 * @param date
	 *            the date
	 * @param time
	 *            the time
	 * 
	 * @return the date time from date and time
	 */
	protected String getDateTimeFromDateAndTime(String date, String time) {
		DateTime rdate = new DateTime(DatatypeConverter.parseDate(date));
		DateTime rtime = new DateTime(DatatypeConverter.parseTime(time));
		long millis = rdate.getMillis() - rdate.getMillisOfDay() + rtime.getMillisOfDay();
		return DateTimeConverter.convert_XFormsToAlfresco_DateTime(millis);
	}

	/**
	 * Gets, from the given instance, the element whose tag name that matches the given form name.
	 * 
	 * @param formName
	 *            the id of the form
	 * @param formNode
	 *            the XForms instance
	 * @return
	 */
	protected Element getRootElement(String formName, Node formNode) {
		Element element = null;
		DOMUtil.logXML(formNode, true);
		if (formNode instanceof Document) {
			Element docElt = ((Document) formNode).getDocumentElement();
			element = DOMUtil.getChild(docElt, formName);
			if (element == null) {
				// we may be dealing with a workflow form
				element = DOMUtil.getFirstElement(docElt); // behavior when no workflows existed
				element = DOMUtil.getChild(element, formName);
			}
		}
		if (formNode instanceof Element) {
			element = (Element) formNode;
		}
		return element;
	}

	/**
	 * Process save.
	 * 
	 * @param login
	 *            the login
	 * @param targetNode
	 *            the target node
	 * 
	 * @return the string
	 */
	protected String processSave(AlfrescoTransaction transaction, Element targetNode) {
		// this item has to be updated or saved
		String targetId = null;
		try {
			PersistFormResultBean result = controller.persistClass(transaction, targetNode, false);
			targetId = result.getResultStr();
		} catch (ServletException e) {
			throw new RuntimeException(e);
		}
		return targetId;
	}

	/**
	 * Xforms id to alfresco.
	 * 
	 * @param children
	 *            the children
	 * 
	 * @return the string
	 */
	protected String xformsIdToAlfresco(List<Element> children) {
		String result = null;
		Element idElt = DOMUtil.getOneElementByTagName(children, MsgId.INT_INSTANCE_SIDEID
				.getText());
		if (idElt != null) {
			result = controller.patchDataId(StringUtils.trimToNull(idElt.getTextContent()));
		}
		return result;
	}

	/**
	 * Gets the list of content beans for the specified destination.
	 * 
	 * @param transaction
	 *            the login
	 * @param alfClass
	 *            the alf class
	 * @param uploadDestination
	 *            the identification of the upload store
	 * @param suffix
	 *            the suffix that, when found in attribute names, denotes an upload field/attribute
	 * @return null if no repository content file name was detected
	 */
	public List<FileUploadInfoBean> getFileUploadBeans(AlfrescoTransaction transaction,
			GenericClass alfClass, String uploadDestination, String suffix) {
		List<FileUploadInfoBean> list = new ArrayList<FileUploadInfoBean>();
		List<GenericAttribute> attributes = alfClass.getAttributes().getAttribute();

		for (GenericAttribute attribute : attributes) {
			if (attribute.getQualifiedName().endsWith(suffix)
					|| StringUtils.equals(attribute.getUploadTo(), uploadDestination)) {
				String path = attribute.getValue().get(0).getValue();
				String name = attribute.getValue().get(1).getValue();
				String type = attribute.getValue().get(2).getValue();

				list.add(new FileUploadInfoBean(path, name, type, attribute, controller
						.getParamUploadRepoAppendSuffix(transaction.getInitParams())));
			}
		}

		return list;
	}

	/**
	 * Sets the value of an attribute to the reference, which should be a file name for a filesystem
	 * upload, or a reference in the format "workspace://SpacesStore/..." in case of repository
	 * content.<br/>
	 * Clearing is needed before setting the value! Or the attr will be taken as multiple-valued
	 * since the GenericAttribute has file name, path, MIME...
	 * 
	 * @param attr
	 * @param fileName
	 *            the file name
	 */
	public void setFileUploadFileName(String fileRef, GenericAttribute attr) {
		GenericAttribute contentAttribute = attr;
		if (contentAttribute != null) {
			contentAttribute.getValue().clear();

			ValueType valueName = alfrescoObjectFactory.createValueType();
			valueName.setValue(fileRef);
			contentAttribute.getValue().add(valueName);
		}
	}

	/**
	 * Tells whether the data type and read only status provided indicate a special processing.
	 * 
	 * @param type
	 * @param isReadOnly
	 * @param isServletRequest
	 * @return
	 */
	protected static boolean isAmendable(String type, boolean isReadOnly, boolean isServletRequest) {
		return isReadOnly
				&& (isServletRequest == false)
				&& (type.equalsIgnoreCase(MsgId.INT_TYPE_XSD_DATE.getText())
						|| type.equalsIgnoreCase(MsgId.INT_TYPE_XSD_DATETIME.getText()) || type
						.equalsIgnoreCase(MsgId.INT_TYPE_XSD_TIME.getText()));
	}

	/**
	 * Formats the time sent to the XForms instances for "time" or "dateTime" values.
	 * 
	 * @param timeValue
	 *            , e.g. 12:34:56.789+01:00
	 * @return the time, formatted as specified in the messages properties, or (if nothing is
	 *         specified) the time in 24-hour format (e.g. 12:34:56)
	 */
	protected String transformTimeValueForDisplay(String timeValue) {
		String defaultFormat = timeValue.substring(0, 8);
		String hh = timeValue.substring(0, 2);
		String mm = timeValue.substring(3, 5);
		String ss = timeValue.substring(6, 8);
		String milli = timeValue.substring(9, 12);

		String formatted = MsgPool.testMsg(MsgId.MSG_FORMAT_TIME_OUTPUT, hh, mm, ss, milli);
		if (StringUtils.trimToNull(formatted) == null) {
			return defaultFormat;
		}

		return formatted;
	}

	/**
	 * 
	 * @param dateValue
	 *            in YYYY-MM-DD format
	 * @return the date, formatted as specified.
	 */
	protected String transformDateValueForDisplay(String dateValue) {
		String defaultFormat = dateValue.substring(0, 10);
		String yyyy = dateValue.substring(0, 4);
		String yy = dateValue.substring(2, 4);
		String mm = dateValue.substring(5, 7);
		String dd = dateValue.substring(8, 10);

		String formatted = MsgPool.testMsg(MsgId.MSG_FORMAT_DATE_OUTPUT, yyyy, yy, mm, dd);
		if (StringUtils.trimToNull(formatted) == null) {
			return defaultFormat;
		}

		return formatted;
	}

	protected String extractTimeFromDateTimeModified(String inputTextContent) {
		return inputTextContent.substring(inputTextContent.indexOf('T') + 1);
	}

	protected String extractDateFromDateTimeModified(String inputTextContent) {
		return inputTextContent.substring(0, inputTextContent.indexOf('T'));
	}

	protected String getReadOnlyDateOrTimeModifiedValue(String type, String initialValue) {
		String modifiedValue = null;

		if (type.equalsIgnoreCase("date")) {
			modifiedValue = transformDateValueForSaving(initialValue);
		} else if (type.equalsIgnoreCase("time")) {
			modifiedValue = transformTimeValueForSaving(initialValue);
		} else {
			modifiedValue = transformDateTimeValueForSaving(initialValue);
		}

		return modifiedValue;
	}

	/**
	 * Interprets the user format for times and maps the value to the usual format (ISO 8601).<br/>
	 * Mainly copied from transformDateValueForSaving.
	 * 
	 * @param initialValue
	 * @return the ISO 8601 DateTime value, e.g. 2009-12-09T12:34:56.789+01:00. If any exception
	 *         occurs or the format/input value is not well formed, returns the current date and
	 *         time.
	 */
	private String transformDateTimeValueForSaving(String initialValue) {

		TransformInfoBean bean = transformDateValueHelper(initialValue);
		String dateValue = bean.result;
		String timeInitialValue = initialValue.substring(bean.positionWhereStopped + 1);
		String timeValue = transformTimeValueHelper(timeInitialValue).result;
		return dateValue + 'T' + timeValue;
	}

	/**
	 * Interprets the user format for times and maps the value to the usual format (ISO 8601).<br/>
	 * Mainly copied from transformDateValueForSaving.
	 * 
	 * @param timeValue
	 * @return the time in HH:MM:SS.mmm+TimeZone, e.g. 12:34:56.789+01:00. If any exception occurs
	 *         or the format/input value is not well formed, returns the current date.
	 */
	protected String transformTimeValueForSaving(String timeValue) {

		return transformTimeValueHelper(timeValue).result;
	}

	/**
	 * @param timeValue
	 * @param format
	 * @return
	 */
	private TransformInfoBean transformTimeValueHelper(String timeValue) {
		String format = MsgPool.getMsg(MsgId.MSG_FORMAT_TIME_OUTPUT);

		StringBuffer result = new StringBuffer("");
		// # 0: hour, 1: min, 2: sec, 3: millis
		final int HH = 0, MM = 1, SS = 2, MILLIS = 3;
		String[] values = { "", "00", "00", "000" };
		int[] lengths = { 2, 2, 2, 3 };
		int posStart;
		int posEnd;
		String idxStr;
		int idx;
		String finalResult;
		boolean error = false;
		String localValue = timeValue;

		posStart = format.indexOf('{');

		// collect the values
		while ((posStart != -1) && (error == false)) {
			posEnd = format.indexOf('}', posStart);
			if (posEnd == -1) {
				error = true;
			} else {
				idxStr = format.substring(posStart + 1, posEnd);
				if (idxStr.length() == 1) {
					if (posStart != 0) {
						// get rid of supplementary characters
						localValue = localValue.substring(posStart);
					}
					try {
						idx = Integer.parseInt(idxStr);
						if ((idx >= 0) && (idx <= 3)) {
							values[idx] = localValue.substring(0, lengths[idx]);
							// get rid of what's been consumed
							localValue = localValue.substring(lengths[idx]);
							format = format.substring(posEnd + 1);
							posStart = format.indexOf('{');
						} else {
							error = true;
						}
					} catch (Exception e) {
						error = true;
					}
				} else {
					// we don't support 2-digit placeholders
					error = true;
				}
			}
		}

		// if incomplete format, return the default
		if (error || (values[HH].length() != 2) || (values[SS].length() != 2)) {
			finalResult = createXFormsInitialValue("Time", null, null);
		} else {
			result.append(values[HH]);
			result.append(":");
			result.append(values[MM]);
			result.append(":");
			result.append(values[SS]);
			result.append(".");
			result.append(values[MILLIS]);

			String localTimeZone = createXFormsInitialValue("Time", null, null).substring(12);
			result.append(localTimeZone);
			finalResult = result.toString();
		}

		return new TransformInfoBean(finalResult, -1);
	}

	/**
	 * Interprets the user format for dates and maps the value to the usual format (ISO 8601).
	 * 
	 * @param dateValue
	 * @return the date in YYYY-MM-DD, e.g. 2009-12-09. If any exception occurs or the format/input
	 *         value is not well formed, returns the current date.
	 */
	protected String transformDateValueForSaving(String dateValue) {
		return transformDateValueHelper(dateValue).result;
	}

	/**
	 * @param dateValue
	 * @return
	 */
	private TransformInfoBean transformDateValueHelper(String dateValue) {
		StringBuffer result = new StringBuffer("");
		// # 0: yyyy; 1: yy; 2: mm; 3: dd
		final int YYYY = 0, YY = 1, MM = 2, DD = 3;
		String[] values = { "", "", "", "" };
		int[] lengths = { 4, 2, 2, 2 };
		int posStart;
		int posEnd;
		String idxStr;
		int idx;
		boolean error = false;
		String finalResult;
		String format = MsgPool.getMsg(MsgId.MSG_FORMAT_DATE_OUTPUT);
		String localValue = dateValue;
		int skipped = 0;

		posStart = format.indexOf('{');

		// collect the values for YYYY, YY, MM and DD
		while ((posStart != -1) && (error == false)) {
			posEnd = format.indexOf('}', posStart);
			if (posEnd == -1) {
				error = true;
			} else {
				idxStr = format.substring(posStart + 1, posEnd);
				if (idxStr.length() == 1) {
					if (posStart != 0) {
						// get rid of supplementary characters
						localValue = localValue.substring(posStart);
						skipped += posStart;
					}
					try {
						idx = Integer.parseInt(idxStr);
						if ((idx >= 0) && (idx <= 3)) {
							values[idx] = localValue.substring(0, lengths[idx]);
							// get rid of what's been consumed
							localValue = localValue.substring(lengths[idx]);
							skipped += lengths[idx];
							format = format.substring(posEnd + 1);
							posStart = format.indexOf('{');
						} else {
							error = true;
						}
					} catch (Exception e) {
						error = true;
					}
				} else {
					// we don't support 2-digit placeholders
					error = true;
				}
			}
		}

		// if incomplete format, return the default
		if (error || ((values[YYYY].length() != 4) && (values[YY].length() != 2))
				|| (values[MM].length() != 2) || (values[DD].length() != 2)) {
			finalResult = createXFormsInitialValue("Date", null, null);
		} else {
			result.append((values[YYYY].length() == 4) ? values[YYYY] : "20" + values[YY]);
			result.append("-");
			result.append(values[MM]);
			result.append("-");
			result.append(values[DD]);

			finalResult = result.toString();
		}

		return new TransformInfoBean(finalResult, skipped);
	}

}
