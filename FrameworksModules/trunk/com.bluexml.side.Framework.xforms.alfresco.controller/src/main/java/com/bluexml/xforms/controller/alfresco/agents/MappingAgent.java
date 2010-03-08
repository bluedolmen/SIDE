package com.bluexml.xforms.controller.alfresco.agents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.xforms.actions.GetAction;
import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
import com.bluexml.xforms.controller.beans.FileUploadInfoBean;
import com.bluexml.xforms.controller.beans.PersistFormResultBean;
import com.bluexml.xforms.controller.beans.WorkflowTaskInfoBean;
import com.bluexml.xforms.controller.binding.AspectType;
import com.bluexml.xforms.controller.binding.AssociationType;
import com.bluexml.xforms.controller.binding.CanisterType;
import com.bluexml.xforms.controller.binding.ClassType;
import com.bluexml.xforms.controller.binding.EnumType;
import com.bluexml.xforms.controller.binding.FormType;
import com.bluexml.xforms.controller.binding.GenericAttribute;
import com.bluexml.xforms.controller.binding.GenericClass;
import com.bluexml.xforms.controller.binding.Mapping;
import com.bluexml.xforms.controller.binding.ModelChoiceType;
import com.bluexml.xforms.controller.binding.SearchFormType;
import com.bluexml.xforms.controller.binding.WorkflowTaskType;
import com.bluexml.xforms.controller.mapping.MappingToolAlfrescoToClassForms;
import com.bluexml.xforms.controller.mapping.MappingToolAlfrescoToForms;
import com.bluexml.xforms.controller.mapping.MappingToolClassFormsToAlfresco;
import com.bluexml.xforms.controller.mapping.MappingToolCommon;
import com.bluexml.xforms.controller.mapping.MappingToolFormsToAlfresco;
import com.bluexml.xforms.controller.mapping.MappingToolSearch;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * The Class MappingTool.<br>
 * Delegates operations to instances of MappingToolCommon
 */
public class MappingAgent {

	/** The logger. */
	protected static Log logger = LogFactory.getLog(MappingAgent.class);

	/** The mapping tool impl alfresco to x forms. */
	private MappingToolAlfrescoToClassForms mappingToolImplAlfrescoToXForms;

	/** The mapping tool impl x forms to alfresco. */
	private MappingToolClassFormsToAlfresco mappingToolImplXFormsToAlfresco;

	/** The mapping tool alfresco to forms. */
	private MappingToolAlfrescoToForms mappingToolAlfrescoToForms;

	/** The mapping tool forms to alfresco. */
	private MappingToolFormsToAlfresco mappingToolFormsToAlfresco;

	/** The mapping tool forms to alfresco. */
	private MappingToolSearch mappingToolSearch;

	private Mapping mapping;

	private AlfrescoController controller;

	/** The current upload path, set by getParamUploadPathInFileSystem */
	private File currentUploadDir;

	public void loadMappingXml() throws Exception {
		URL url = AlfrescoController.class.getResource("/mapping.xml");
		File file = new File(new URI(url.toString()));
		InputStream mappingStream = new FileInputStream(file);
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance("com.bluexml.xforms.controller.binding");
			Unmarshaller mappingUnmarshaller = jaxbContext.createUnmarshaller();
			Mapping mappingInstance = (Mapping) mappingUnmarshaller.unmarshal(mappingStream);
			this.mapping = mappingInstance;
		} catch (JAXBException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e);
			}
			throw new RuntimeException(e);
		} finally {
			mappingStream.close();
		}
	}

	/**
	 * Instantiates a new mapping tool impl.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param controller
	 *            the controller
	 * @throws Exception
	 */
	public MappingAgent(AlfrescoController controller) throws Exception {
		super();
		loadMappingXml();
		this.controller = controller;
		mappingToolImplAlfrescoToXForms = new MappingToolAlfrescoToClassForms(mapping, controller);
		mappingToolImplXFormsToAlfresco = new MappingToolClassFormsToAlfresco(mapping, controller);
		mappingToolAlfrescoToForms = new MappingToolAlfrescoToForms(mapping, controller);
		mappingToolFormsToAlfresco = new MappingToolFormsToAlfresco(mapping, controller);
		mappingToolSearch = new MappingToolSearch(mapping, controller);
	}

	/**
	 * Creates the class forms instance.
	 * 
	 * @param transaction
	 *            the login
	 * @param type
	 *            the type
	 * @param formIsReadOnly
	 * 
	 * @return the document
	 * 
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	public Document createClassFormsInstance(AlfrescoTransaction transaction, String type,
			boolean formIsReadOnly, boolean isServletRequest) throws ServletException {
		return mappingToolImplAlfrescoToXForms.createClassFormsInstance(transaction, type,
				transaction.getInitParams(), new Stack<AssociationType>(), formIsReadOnly,
				isServletRequest);
	}

	/**
	 * Gets the content file name.
	 * 
	 * @param transaction
	 *            the login
	 * @param alfClass
	 *            the alf class
	 * 
	 * @return the content file name
	 */
	public List<FileUploadInfoBean> getUploadBeansFilesystem(AlfrescoTransaction transaction,
			GenericClass alfClass) {
		return mappingToolImplXFormsToAlfresco.getFileUploadBeans(transaction, alfClass,
				MsgId.INT_UPLOAD_DEST_FILE.getText(), MsgId.INT_SUFFIX_UPLOAD_FILE.getText());
	}

	/**
	 * Gets the beans containing info for repository contents.
	 * 
	 * @param transaction
	 *            the login
	 * @param alfClass
	 *            the alf class
	 * 
	 * @return the bean, or null if no repository content file exists in the class
	 */
	public List<FileUploadInfoBean> getUploadBeansRepo(AlfrescoTransaction transaction,
			GenericClass alfClass) {
		return mappingToolImplXFormsToAlfresco.getFileUploadBeans(transaction, alfClass,
				MsgId.INT_UPLOAD_DEST_REPO.getText(), MsgId.INT_SUFFIX_UPLOAD_REPO.getText());
	}

	/**
	 * Gets the repository content info.
	 * 
	 * @param transaction
	 *            the login
	 * @param alfClass
	 *            the alf class
	 * 
	 * @return the bean, or null if no repository content file exists in the class
	 */
	public FileUploadInfoBean getNodeContentInfo(AlfrescoTransaction transaction,
			GenericClass alfClass) {
		return mappingToolFormsToAlfresco.getNodeContentInfo(transaction, alfClass);
	}

	/**
	 * Sets the content file name.
	 * 
	 * @param alfClass
	 *            the alf class
	 * @param fileName
	 *            the file name
	 */
	public void setFileUploadFileName(String fileName, GenericAttribute attr) {
		mappingToolImplXFormsToAlfresco.setFileUploadFileName(fileName, attr);
	}

	/**
	 * Transform alfresco to class forms.
	 * 
	 * @param transaction
	 *            the login
	 * @param alfrescoNode
	 *            the alfresco node
	 * @param stack
	 *            the stack
	 * @param isServletRequest
	 * 
	 * @return the document
	 * 
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	public Document transformAlfrescoToClassForms(AlfrescoTransaction transaction,
			Document alfrescoNode, Stack<AssociationType> stack, boolean formIsReadOnly,
			boolean isServletRequest) throws ServletException {
		return mappingToolImplAlfrescoToXForms.transformAlfrescoToClassForms(transaction,
				alfrescoNode, stack, formIsReadOnly, isServletRequest);
	}

	/**
	 * Transform class forms to alfresco.
	 * 
	 * @param transaction
	 *            the login
	 * @param node
	 *            the node
	 * @param isServletRequest
	 * 
	 * @return the com.bluexml.xforms.controller.alfresco.binding. class
	 */
	public GenericClass transformClassFormsToAlfresco(AlfrescoTransaction transaction, Node node,
			boolean isServletRequest) {
		return mappingToolImplXFormsToAlfresco.transformClassFormsToAlfresco(transaction, node,
				isServletRequest);
	}

	/**
	 * Removes the reference.
	 * 
	 * @param node
	 *            the node
	 * @param elementId
	 *            the element id
	 */
	public void removeReference(Document node, String elementId) {
		mappingToolImplXFormsToAlfresco.removeReference(node, elementId);
	}

	/**
	 * Checks for sub types.
	 * 
	 * @param dataType
	 *            the data type
	 * 
	 * @return true, if successful
	 */
	public boolean hasSubTypes(String dataType) {
		return mappingToolImplXFormsToAlfresco.hasSubTypes(dataType);
	}

	/**
	 * Creates the form instance.
	 * 
	 * @param transaction
	 *            the login
	 * @param formName
	 *            the form name
	 * @param alfrescoId
	 *            the alfresco id
	 * 
	 * @return the document
	 * 
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	public Document createFormInstance(AlfrescoTransaction transaction, String formName,
			boolean formIsReadOnly) throws ServletException {
		return mappingToolAlfrescoToForms.newFormInstance(formName, transaction, transaction
				.getInitParams(), formIsReadOnly);
	}

	/**
	 * Provides an instance of the given form pre-filled with properties stored in the given object.
	 * 
	 * @param transaction
	 * @param type
	 *            the form id
	 * @param id
	 *            the node id of the object that provides the info
	 * @param formIsReadOnly
	 * @return
	 * @throws ServletException
	 */
	private Document loadFormInstance(AlfrescoTransaction transaction, String type, String id,
			boolean formIsReadOnly) throws ServletException {
		return mappingToolAlfrescoToForms.getFormInstance(transaction, type, id, formIsReadOnly);
	}

	public Document getInstanceSearch(String formName) {
		return mappingToolSearch.getInstanceSearch(formName);
	}

	/**
	 * New form instance.
	 * 
	 * @param formName
	 *            the form name
	 * @param transaction
	 * @param formIsReadOnly
	 * 
	 * @return the document
	 * 
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	public Document newFormInstance(String formName, AlfrescoTransaction transaction,
			Map<String, String> initParams, boolean formIsReadOnly) throws ServletException {
		return mappingToolAlfrescoToForms.newFormInstance(formName, transaction, initParams,
				formIsReadOnly);
	}

	/**
	 * Transform forms to alfresco. Builds a GenericClass for a form from the given node. Extracted
	 * from persistForm to provide an access to workflow actions.
	 * 
	 * @param transaction
	 *            the login
	 * @param formName
	 *            the form name
	 * @param formNode
	 *            the instance provided by the XForms engine.
	 * 
	 * @return the com.bluexml.xforms.controller.alfresco.binding. class
	 * 
	 * @throws ServletException
	 */
	public GenericClass transformsToAlfresco(AlfrescoTransaction transaction, String formName,
			Node formNode) throws ServletException {
		return mappingToolFormsToAlfresco.transformsToAlfresco(transaction, formName, formNode);
	}

	/**
	 * Transforms the instance into a JSON string for the given form id.
	 * 
	 * @param transaction
	 * @param alfrescoController
	 * @param formName
	 * @param instance
	 * @return
	 * @throws ServletException
	 */
	public String transformsToJSON(AlfrescoTransaction transaction, String formName, Node instance,
			boolean shortPropertyNames) throws ServletException {
		return mappingToolFormsToAlfresco.transformsToJSON(transaction, formName, instance,
				shortPropertyNames);
	}

	/**
	 * Transforms the instance to the format for searches wrt the given form id.
	 * 
	 * @param formName
	 *            the id of the search form
	 * @param instance
	 * @return
	 * @throws ServletException
	 */
	public String transformSearchForm(String formName, Node instance, boolean shortPropertyNames)
			throws ServletException {
		return mappingToolSearch.transformSearchForm(formName, instance, shortPropertyNames);
	}

	/**
	 * Gets the class type.
	 * 
	 * @param type
	 *            the type
	 * 
	 * @return the class type
	 */
	public ClassType getClassType(String type) {
		return mappingToolImplAlfrescoToXForms.getClassType(type);
	}

	/**
	 * Gets the aspect type.
	 * 
	 * @param aspectType
	 *            the aspect type
	 * 
	 * @return the aspect type
	 */
	public AspectType getAspectType(AspectType aspectType) {
		return mappingToolImplAlfrescoToXForms.getAspectType(aspectType);
	}

	public EnumType getEnumType(String type) {
		return mappingToolImplAlfrescoToXForms.getEnumType(type);
	}

	/**
	 * Gets the value of the field size property for a data type from the mapping.
	 * 
	 * @param attrType
	 *            the complete type name of the items in the model choice field
	 * @param defaultVal
	 *            the value to return if no field size value is found
	 * @param formName
	 *            the id of the form
	 * @return
	 */
	public String getFieldSizeForField(String attrType, String defaultVal, String formName) {
		// we'll test only one form so the form name should be unique in the mapping file
		List<JAXBElement<? extends CanisterType>> elements = mapping.getCanister();
		for (JAXBElement<? extends CanisterType> element : elements) {
			if (element.getValue() instanceof FormType) {
				FormType form = (FormType) element.getValue();
				if (StringUtils.equals(form.getName(), formName)) {
					List<ModelChoiceType> modelChoices = form.getModelChoice();
					for (ModelChoiceType modelChoice : modelChoices) {
						ClassType rc = modelChoice.getRealClass();
						String completeName = rc.getAlfrescoName().replace("_", ".");
						if (completeName.equalsIgnoreCase(attrType)) {
							String value = mappingToolAlfrescoToForms.getFieldSize(modelChoice);
							// the name may exist without having a value for field size
							return ((value == null) ? defaultVal : value);
						}
					}
				}
			}
		}
		return defaultVal;
	}

	/**
	 * Retrieves the label for a model choice field that implements a specific association on a
	 * specific form.
	 * 
	 * @param completeAssoName
	 *            the Alfresco name of the association
	 * @param formId
	 *            the name of the form
	 * @return
	 */
	public String getDisplayLabelFromAssociationName(String completeAssoName, String formId) {
		FormType formType = mappingToolAlfrescoToForms.getFormType(formId);
		if (formType != null) {
			List<ModelChoiceType> modelChoices = formType.getModelChoice();
			for (ModelChoiceType modelChoice : modelChoices) {
				if (modelChoice.getAlfrescoName().equals(completeAssoName)) {
					return modelChoice.getDisplayLabel();
				}
			}
		}
		ClassType classType = getClassType(formId);
		if (classType != null) {
			List<AssociationType> assos = classType.getAssociation();
			for (AssociationType asso : assos) {
				if (asso.getAlfrescoName().equals(completeAssoName)) {
					return asso.getCaption();
				}
			}
		}
		return ""; // normally, we should never get here (unless there are multiple violations)
	}

	public static String marshalBatch(AlfrescoTransaction transaction) throws ServletException {
		return MappingToolCommon.marshal(transaction.getBatch());
	}

	public static synchronized GenericClass unmarshal(Document alfrescoNode) throws JAXBException {
		return MappingToolCommon.unmarshal(alfrescoNode);
	}

	/**
	 * Checks if is an enumeration is dynamic.
	 * 
	 * @param type
	 *            the type
	 * 
	 * @return true, if is dynamic enum
	 */
	public boolean isDynamicEnum(String type) {
		EnumType enumType = getEnumType(type);
		if (enumType != null) {
			return mappingToolFormsToAlfresco.isDynamic(enumType);
		}
		return false; // happens for search operators enums; they don't get into the mapping file
	}

	/**
	 * Gets the suffix (specified at generation time) for read only forms.
	 * 
	 * @return the suffix
	 */
	public String getReadOnlyFormsSuffix() {
		return mapping.getGenInfo().getReadOnlyFormsSuffix();
	}

	public boolean getDebugModeStatus() {
		return mapping.getGenInfo().isDebugMode();
	}

	public FormType getFormType(String refName) {
		return mappingToolAlfrescoToForms.getFormType(refName);
	}

	public SearchFormType getSearchType(String refName) {
		return mappingToolAlfrescoToForms.getSearchFormType(refName);
	}

	public WorkflowTaskType getWorkflowTaskType(String refName, boolean byId) {
		return mappingToolAlfrescoToForms.getWorkflowTaskType(refName, byId);
	}

	public WorkflowTaskType getWorkflowTaskTypeWithField(String fieldName) {
		return mappingToolAlfrescoToForms.getWorkflowTaskTypeWithField(fieldName);
	}

	public String getFormFieldTypeFromCanister(String wkFormName, String fieldName) {
		return mappingToolAlfrescoToForms.getFormFieldTypeFromCanister(wkFormName, fieldName);
	}

	public void collectTaskProperties(Document instance, Element taskElt, String wkFormName,
			Map<String, GenericClass> alfrescoNodes, boolean formIsReadOnly)
			throws ServletException {
		mappingToolAlfrescoToForms.collectTaskProperties(instance, taskElt, wkFormName,
				alfrescoNodes, formIsReadOnly);
	}

	public boolean isStartTaskForm(String wkFormName) {
		return mappingToolFormsToAlfresco.isStartTaskForm(wkFormName);
	}

	public String getFormTypeWithDataType(String dataType) {
		return mappingToolFormsToAlfresco.getFormTypeWithDataType(dataType);
	}

	public String getClassTypeWithDataType(String dataType) {
		return mappingToolFormsToAlfresco.getClassTypeWithDataType(dataType);
	}

	public String getWorkflowStartTaskFormName(String namespacePrefix) {
		return mappingToolFormsToAlfresco.getWorkflowStartTaskFormName(namespacePrefix);
	}

	public String getUnderlyingClassForForm(String formName) {
		FormType formType = getFormType(formName);
		if (formType == null) {
			return null;
		}
		ClassType classType = formType.getRealClass();
		if (classType == null) {
			return null;
		}
		return classType.getAlfrescoName();
	}

	public String getUnderlyingClassForWorkflow(String wkFormName) {
		WorkflowTaskType taskType = getWorkflowTaskType(wkFormName, false);
		if (taskType == null) {
			return null;
		}
		String dataFormName = taskType.getDataForm();
		return getUnderlyingClassForForm(dataFormName);
	}

	public String getUnderlyingDataFormForWorkflow(String wkFormName) {
		WorkflowTaskType taskType = getWorkflowTaskType(wkFormName, false);
		if (taskType == null) {
			return null;
		}
		FormType dataFormType = getFormType(taskType.getDataForm());
		if (dataFormType == null) {
			return null;
		}
		return dataFormType.getName();
	}

	public String getWorkflowTaskPooledActorsById(String taskName) {
		WorkflowTaskType taskType = getWorkflowTaskType(taskName, true);
		if (taskType == null) {
			return null;
		}
		return taskType.getPooledActors();
	}

	public String getWorkflowTaskActorIdById(String taskName) {
		WorkflowTaskType taskType = getWorkflowTaskType(taskName, true);
		if (taskType == null) {
			return null;
		}
		return taskType.getActorId();
	}

	public String getTaskNameFromFormName(String wkFormName) {
		WorkflowTaskType taskType = getWorkflowTaskType(wkFormName, false);
		if (taskType == null) {
			return null;
		}
		return taskType.getName();
	}

	public WorkflowTaskInfoBean getWorkflowTaskInfoBean(String wkFormName) {
		WorkflowTaskType taskType = getWorkflowTaskType(wkFormName, false);
		if (taskType == null) {
			return null;
		}
		return new WorkflowTaskInfoBean(taskType.getTaskId(), taskType.getName(), taskType
				.getActorId(), taskType.getPooledActors(), taskType.getTitle());
	}

	public String getWorkflowFormNameByTaskId(String taskId) {
		WorkflowTaskType taskType = getWorkflowTaskType(taskId, true);
		if (taskType == null) {
			if (logger.isErrorEnabled()) {
				logger.error("No task definition in the mapping for task '" + taskId + "'");
			}
			return "";
		}
		return taskType.getName();
	}

	/**
	 * Persists a FormClass: transforms the XForms instance into a GenericClass, and either saves or
	 * updates a node.
	 * 
	 * @param type
	 *            the type
	 * @param instance
	 *            the instance
	 * @param transaction
	 *            the transaction
	 * 
	 * @return the temporary id assigned to the class
	 * 
	 * @throws ServletException
	 */
	public PersistFormResultBean persistForm(AlfrescoTransaction transaction, String type,
			Node instance) throws ServletException {
		GenericClass alfClass = this.transformsToAlfresco(transaction, type, instance);
		if (alfClass.getId() == null) {
			alfClass.setId(saveObject(transaction, alfClass));
		} else {
			updateObject(transaction, alfClass);
		}
		PersistFormResultBean resultBean = new PersistFormResultBean();
		resultBean.setResultStr(alfClass.getId());

		return resultBean;
	}

	/**
	 * Builds a GenericClass object from the instance of a workflow and processes the possible
	 * upload fields.
	 * 
	 * @param transaction
	 * @param taskTypeName
	 *            the id of the workflow form
	 * @param taskElt
	 *            the root element containing the workflow properties
	 * @return
	 * @throws ServletException
	 */
	public PersistFormResultBean persistWorkflow(AlfrescoTransaction transaction,
			String taskTypeName, Node taskElt) throws ServletException {
		GenericClass transformed = transformsToAlfresco(transaction, taskTypeName, taskElt);

		// #1209: we must support FileFields on workflow forms so we also simulate a queuing
		saveObject(transaction, transformed);

		PersistFormResultBean resultBean = new PersistFormResultBean();
		resultBean.setResultClass(transformed);

		return resultBean;
	}

	/**
	 * Persists a class form: transforms the XForms instance into a GenericClass, and either saves
	 * or updates a node.
	 * 
	 * @param instance
	 *            the instance
	 * @param transaction
	 *            the transaction
	 * @param isServletRequest
	 * 
	 * @return the object id as given by the transaction manager
	 * 
	 * @throws ServletException
	 */
	public PersistFormResultBean persistClass(AlfrescoTransaction transaction, Node instance,
			boolean isServletRequest) throws ServletException {
		GenericClass alfClass = transformClassFormsToAlfresco(transaction, instance,
				isServletRequest);
		if (alfClass.getId() == null) {
			alfClass.setId(saveObject(transaction, alfClass));
		} else {
			uploadProcessOnUpdate(transaction, alfClass);
		}
		PersistFormResultBean resultBean = new PersistFormResultBean();
		resultBean.setResultStr(alfClass.getId());

		return resultBean;
	}

	/**
	 * Creates or loads the instance for a default class form.
	 * 
	 * @param transaction
	 *            the transaction
	 * @param type
	 *            the content type
	 * @param id
	 *            the id
	 * @param initParams
	 *            the init params
	 * @param idAsServlet
	 *            whether the request comes from a servlet
	 * 
	 * @return the class
	 * 
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	public Document getInstanceClass(AlfrescoTransaction transaction, String type, String id,
			boolean formIsReadOnly, boolean isServletRequest) throws ServletException {
		Document instance = null;
		try {
			if (id == null) {
				instance = createClassFormsInstance(transaction, type, formIsReadOnly,
						isServletRequest);
			} else {
				instance = controller.getObjectInstance(transaction, id,
						new Stack<AssociationType>(), formIsReadOnly, isServletRequest);
			}
		} catch (ServletException se) {
			throw se; // just propagate
		} catch (Exception e) {
			throw new ServletException(e);
		}
		return instance;
	}

	/**
	 * Creates or loads the instance for a FormClass.
	 * 
	 * @param transaction
	 *            the transaction
	 * @param type
	 *            the content type
	 * @param id
	 *            the id of the object to load. If <b>null</b>, the form is empty (with the
	 *            exception of default values [from in the model] and initial values [from URL
	 *            parameters]. If non null, the form is filled with values from the object.
	 * 
	 * @return the form
	 * 
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	public Document getInstanceForm(AlfrescoTransaction transaction, String type, String id,
			boolean formIsReadOnly) throws ServletException {
		Document instance = null;
		try {
			if (id == null) {
				instance = createFormInstance(transaction, type, formIsReadOnly);
			} else {
				instance = loadFormInstance(transaction, type, id, formIsReadOnly);
			}

		} catch (Exception e) {
			throw new ServletException(e);
		}
		return instance;
	}

	/**
	 * Returns an instance for workflow forms so that they can be displayed.
	 * 
	 * @see {@link GetAction}
	 * @param formName
	 * @return
	 * @throws ServletException
	 */
	public Document getInstanceWorkflow(String formName) throws ServletException {
		Document instance = AlfrescoController.getDocBuilder().newDocument();

		Map<String, GenericClass> alfrescoNodes = new HashMap<String, GenericClass>();

		Element taskElt = instance.createElement(formName);

		collectTaskProperties(instance, taskElt, formName, alfrescoNodes, false);

		Element rootElement = instance.createElement(MsgId.INT_INSTANCE_WKFLW_NODESET.getText());
		rootElement.appendChild(taskElt);

		instance.appendChild(rootElement);
		return instance;
	}

	/**
	 * Saves a node. Moves uploaded files to the file system/repository and enqueues the 'save'
	 * operation in the transaction, returning the id given by the transaction manager. <br/>
	 * NOTE: The values from the instance <b>must</b> have already been collected.
	 * 
	 * @param alfClass
	 *            the alf class
	 * @param transaction
	 *            the transaction
	 * 
	 * @return the string
	 * 
	 * @throws ServletException
	 */
	private String saveObject(AlfrescoTransaction transaction, GenericClass alfClass)
			throws ServletException {
		// enqueue the operation
		transaction.queueSave(alfClass);

		// process file upload fields if any
		uploadProcessOnSave(transaction, alfClass);

		return alfClass.getId();
	}

	/**
	 * Updates a node: enqueues the update operation in the transaction and processes file uploads.
	 * 
	 * @param transaction
	 * @param alfClass
	 * @return
	 * @throws ServletException
	 */
	private String updateObject(AlfrescoTransaction transaction, GenericClass alfClass)
			throws ServletException {
		// enqueue the operation
		transaction.queueUpdate(alfClass);

		// process file upload fields if any
		uploadProcessOnUpdate(transaction, alfClass);

		return alfClass.getId();
	}

	/**
	 * Attaches the referenced content to a transaction. The attachment is not done if the file name
	 * is not a valid file URI (which happens when 1-there's no uploaded file and 2-a content has
	 * been uploaded previously).
	 * 
	 * @param transaction
	 * @param alfClass
	 * @param fileName
	 * @param filePath
	 * @param mimeType
	 * @param shouldAppendSuffix
	 * @throws ServletException
	 *             if the file doesn't exist
	 */
	private void uploadAttachContent(AlfrescoTransaction transaction, GenericClass alfClass,
			String fileName, String filePath, String mimeType, boolean shouldAppendSuffix)
			throws ServletException {
		if (filePath != null && filePath.startsWith("file:")) {
			File file;
			URI fileURI = URI.create(filePath);
			String fullFileName = fileURI.getPath();
			file = new File(fullFileName);

			if (!file.exists()) {
				if (logger.isErrorEnabled()) {
					logger.error("The file '" + fullFileName + "' to be uploaded does not exist.");
				}
				throw new ServletException(
						"The file to upload does not exist. Your session may have expired. Please load and submit the form again.");
			}

			if (file.length() > 0) {
				transaction.queueAttachContent(alfClass.getId(), fileName, fullFileName, mimeType,
						shouldAppendSuffix, alfClass.getQualifiedName());
			}
		}
	}

	/**
	 * Processes all upload fields on initial submission. Moves filesystem uploads to the directory,
	 * stores repo uploads into the repository and attaches the (possible) node content to the
	 * transaction. If the transaction fails subsequently, all uploads should be removed.
	 * 
	 * @param transaction
	 * @param alfClass
	 * @throws ServletException
	 * @throws ServletException
	 */
	private void uploadProcessOnSave(AlfrescoTransaction transaction, GenericClass alfClass)
			throws ServletException {
		String fileName = null;
		String filePath = null;
		String mimeType = null;

		// content file(s); these will be saved to the server's filesystem
		List<FileUploadInfoBean> fileBeans = getUploadBeansFilesystem(transaction, alfClass);
		for (FileUploadInfoBean infoBean : fileBeans) {
			fileName = infoBean.getPath();
			if (fileName.startsWith("file:")) {
				String type = alfClass.getQualifiedName();
				fileName = uploadMoveFileToDir(type, fileName, transaction);
			}
			setFileUploadFileName(fileName, infoBean.getAttribute());
		}

		// repository content file(s); these will be directly uploaded to the repository
		List<FileUploadInfoBean> repoBeans = getUploadBeansRepo(transaction, alfClass);
		for (FileUploadInfoBean infoBean : repoBeans) {
			fileName = null;
			fileName = infoBean.getName();
			filePath = infoBean.getPath();
			mimeType = infoBean.getMimeType();
			GenericAttribute attribute = infoBean.getAttribute();
			if (filePath.startsWith("file:")) {
				String location = controller.getParamUploadPathInRepository();
				fileName = uploadMoveFileToRepo(transaction, fileName, filePath, location,
						mimeType, infoBean.isShouldAppendSuffix());
				if (StringUtils.trimToNull(fileName) == null) {
					throw new ServletException(MsgPool.getMsg(MsgId.MSG_UPLOAD_FAILED));
				}
			}
			setFileUploadFileName(fileName, attribute);
		}

		// node content file; there's at most one instance of this.
		FileUploadInfoBean nodeInfoBean = getNodeContentInfo(transaction, alfClass);
		if (nodeInfoBean != null) {
			fileName = nodeInfoBean.getName();
			filePath = nodeInfoBean.getPath();
			mimeType = nodeInfoBean.getMimeType();

			uploadAttachContent(transaction, alfClass, fileName, filePath, mimeType, nodeInfoBean
					.isShouldAppendSuffix());
		}
	}

	/**
	 * Processes the upload files on update: deletes (if relevant) the old files and uploads the new
	 * ones. The deletions are not actually performed here: files/nodes to be deleted are put in
	 * queues.<br/>
	 * <p>
	 * <b>The file providing the node content is not deleted!</b> Don't know whether we should.
	 * </p>
	 * 
	 * @param alfClass
	 *            the alf class
	 * @param transaction
	 *            the transaction
	 * 
	 * @throws ServletException
	 *             the alfresco controller exception
	 * @throws ServletException
	 */
	private void uploadProcessOnUpdate(AlfrescoTransaction transaction, GenericClass alfClass)
			throws ServletException {
		List<FileUploadInfoBean> previousFileContentInfo;
		List<FileUploadInfoBean> previousRepoContentInfo;
		List<FileUploadInfoBean> newFileContentInfo;
		List<FileUploadInfoBean> newRepoContentInfo;

		// read the old class
		GenericClass oldClass = null;
		try {
			oldClass = MappingAgent.unmarshal(controller.readObjectFromRepository(transaction,
					alfClass.getId()));
		} catch (JAXBException e) {
			throw new ServletException(e);
		}
		previousFileContentInfo = getUploadBeansFilesystem(transaction, oldClass);
		previousRepoContentInfo = getUploadBeansRepo(transaction, oldClass);
		newFileContentInfo = getUploadBeansFilesystem(transaction, alfClass);
		newRepoContentInfo = getUploadBeansRepo(transaction, alfClass);

		//
		// enqueue files/nodes to be deleted
		String fileName;
		FileUploadInfoBean oldBean;
		for (FileUploadInfoBean newBean : newFileContentInfo) {
			fileName = newBean.getPath();
			if (fileName != null && fileName.startsWith("file:")) { // value is being replaced
				String qualifiedName = newBean.getAttribute().getQualifiedName();
				oldBean = uploadFindBean(previousFileContentInfo, qualifiedName);
				if (oldBean != null) {
					// we need to register as a temp file for deletion in case of success
					transaction.registerTempFileName(controller.getParamUploadPathInFileSystem()
							+ File.separator + oldBean.getPath());
				}
			}
		}
		for (FileUploadInfoBean newBean : newRepoContentInfo) {
			fileName = newBean.getPath();
			if (fileName != null && fileName.startsWith("file:")) {
				String qualifiedName = newBean.getAttribute().getQualifiedName();
				oldBean = uploadFindBean(previousRepoContentInfo, qualifiedName);
				if (oldBean != null) {
					String oldNodeId = oldBean.getPath();
					transaction.registerTempNodeId(oldNodeId);
				}
			}
		}

		//
		// set the new version of the object
		uploadProcessOnSave(transaction, alfClass);
	}

	/**
	 * Finds the upload bean with the given attribute qname.
	 * 
	 * @param previousFileContentInfo
	 * @param qname
	 * @return the bean or null if not found
	 */
	private FileUploadInfoBean uploadFindBean(List<FileUploadInfoBean> list, String qname) {
		if (list == null) {
			return null;
		}
		for (FileUploadInfoBean bean : list) {
			GenericAttribute attribute = bean.getAttribute();
			if (attribute != null) {
				if (attribute.getQualifiedName().equals(qname)) {
					return bean;
				}
			}
		}
		return null;
	}

	/**
	 * Moves the uploaded file to a randomly chosen folder under the file system upload directory.
	 * 
	 * @param type
	 *            the type
	 * @param fileName
	 *            the file name
	 * 
	 * @return the string
	 * 
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	private String uploadMoveFileToDir(String type, String fileName, AlfrescoTransaction transaction)
			throws ServletException {
		URI fileURI = URI.create(fileName);
		File sourceFile = null;
		try { // #1160
			sourceFile = new File(fileURI);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				String message = "XForms Controller: error when processing the file to upload. Check the path: "
						+ fileURI;
				logger.error(message, e);
			}
			return null;
		}

		currentUploadDir = controller.getParamUploadPathInFileSystem();
		int depth = controller.getParamUploadPathDepth();
		File targetFile = findNewName(depth, type, sourceFile.getName());

		copyFile(sourceFile, targetFile);
		String relativePath = targetFile.getAbsolutePath().replace(
				currentUploadDir.getAbsolutePath(), "");

		String outputFileName = relativePath.replace('\\', '/');
		transaction.registerUploadedFileName(outputFileName);
		transaction.registerTempFileName(sourceFile.getAbsolutePath());

		return outputFileName;
	}

	/**
	 * Process repository content. Moves the uploaded file to the given location into the content
	 * manager.
	 * 
	 * @param transaction
	 * @param fileName
	 *            the file name, name and extension
	 * @param filePath
	 *            the file system complete path to the file to be uploaded. The name and extension
	 *            may be different from parameter 'fileName'.
	 * @param location
	 *            path to a folder in the content management system
	 * @param shouldAppendSuffix
	 *            if set to true, an index [e.g. '(1)'] is appended to the filename if the original
	 *            filename is not available
	 * @return the string
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	private String uploadMoveFileToRepo(AlfrescoTransaction transaction, String fileName,
			String filePath, String location, String mimetype, boolean shouldAppendSuffix)
			throws ServletException {

		// collect parameters
		Map<String, String> parameters = new TreeMap<String, String>();
		URI fileURI = URI.create(filePath);
		String fullFileName = fileURI.getPath();

		parameters.put("filename", fileName);
		parameters.put("filepath", fullFileName);
		parameters.put("location", location);
		parameters.put("mimetype", mimetype);
		parameters.put("suffixappend", "" + shouldAppendSuffix);
		// call the webscript
		String resultId = controller.requestString(transaction, parameters,
				MsgId.INT_WEBSCRIPT_OPCODE_UPLOAD);

		return resultId;
	}

	/**
	 * Copies the content of a source file to a target file.
	 * 
	 * @param sourceFile
	 *            the source file
	 * @param targetFile
	 *            the target file
	 * 
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	private void copyFile(File sourceFile, File targetFile) throws ServletException {
		FileChannel srcChannel = null;
		FileChannel dstChannel = null;
		try {
			try {
				srcChannel = new FileInputStream(sourceFile).getChannel();
				dstChannel = new FileOutputStream(targetFile).getChannel();
				dstChannel.transferFrom(srcChannel, 0, srcChannel.size());
			} catch (IOException e) {
				throw new ServletException(e);
			} finally {
				if (srcChannel != null)
					srcChannel.close();
				if (dstChannel != null)
					dstChannel.close();
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * Finds a new name for an uploaded content. The name includes the content type, the random path
	 * and the original file name.
	 * <p/>
	 * NOTE: uploadDir must have been set.
	 * 
	 * @param type
	 *            the type
	 * @param fileName
	 *            the file name
	 * 
	 * @return the file
	 */
	private File findNewName(int depth, String type, String fileName) {
		String lFileName = fileName;
		if (lFileName.contains("\\")) {
			int lastIndexOf = StringUtils.lastIndexOf(lFileName, '\\');
			lFileName = StringUtils.substring(lFileName, lastIndexOf + 1);
		}
		String rootPath = currentUploadDir.getAbsolutePath() + File.separator + type;

		String randomPath = RandomStringUtils.randomNumeric(depth);
		for (int i = 0; i < depth; i++) {
			rootPath = rootPath + File.separator + randomPath.charAt(i);
		}

		File root = new File(rootPath);

		File result = new File(root, lFileName);
		if (result.exists()) {
			int dotPos = lFileName.lastIndexOf(".");

			String fileNameWihoutExtension = null;
			String fileNameExtension = null;

			if (dotPos == -1) {
				fileNameWihoutExtension = lFileName;
			} else {
				fileNameWihoutExtension = lFileName.substring(0, dotPos);
				fileNameExtension = lFileName.substring(dotPos + 1);
			}
			int i = 0;
			do {
				String newFileName = fileNameWihoutExtension + "-" + i;
				if (fileNameExtension != null) {
					newFileName = newFileName + "." + fileNameExtension;
				}
				result = new File(root, newFileName);
				i++;
			} while (result.exists());
		}
		result.getParentFile().mkdirs();
		return result;
	}

}
