package com.bluexml.xforms.controller.mapping;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBElement;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
import com.bluexml.xforms.controller.binding.AspectType;
import com.bluexml.xforms.controller.binding.AssociationType;
import com.bluexml.xforms.controller.binding.CanisterType;
import com.bluexml.xforms.controller.binding.ClassType;
import com.bluexml.xforms.controller.binding.EnumType;
import com.bluexml.xforms.controller.binding.FormFieldType;
import com.bluexml.xforms.controller.binding.FormType;
import com.bluexml.xforms.controller.binding.GenericAttribute;
import com.bluexml.xforms.controller.binding.GenericClass;
import com.bluexml.xforms.controller.binding.Mapping;
import com.bluexml.xforms.controller.binding.ModelChoiceType;
import com.bluexml.xforms.controller.binding.SearchFormType;
import com.bluexml.xforms.controller.binding.WorkflowTaskType;
import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class MappingTool.<br>
 * Delegates operations to instances of MappingToolCommon
 */
public class MappingTool {

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

	/**
	 * Instantiates a new mapping tool impl.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param controller
	 *            the controller
	 */
	public MappingTool(Mapping mapping, AlfrescoController controller) {
		super();
		this.mapping = mapping;
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
	 * @param initParams
	 *            the init params
	 * @param stack
	 *            the stack
	 * @param formIsReadOnly
	 * 
	 * @return the document
	 * 
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	public Document createClassFormsInstance(AlfrescoTransaction transaction, String type,
			Map<String, String> initParams, Stack<AssociationType> stack, boolean formIsReadOnly,
			boolean isServletRequest) throws ServletException {
		return mappingToolImplAlfrescoToXForms.createClassFormsInstance(transaction, type,
				initParams, stack, formIsReadOnly, isServletRequest);
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
	public List<RepoContentInfoBean> getUploadBeansFilesystem(AlfrescoTransaction transaction,
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
	public List<RepoContentInfoBean> getUploadBeansRepo(AlfrescoTransaction transaction,
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
	public RepoContentInfoBean getNodeContentInfo(AlfrescoTransaction transaction,
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
			String alfrescoId, boolean formIsReadOnly) throws ServletException {
		return mappingToolAlfrescoToForms.getFormInstance(transaction, formName, alfrescoId,
				formIsReadOnly);
	}

	public Document getInstanceSearch(Page currentPage, String formName,
			Map<String, String> initParams) {
		return mappingToolSearch.getInstanceSearch(currentPage, formName, initParams);
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
	 * Transform forms to alfresco. Builds a GenericClass for a form from the given node.
	 * 
	 * @param transaction
	 *            the login
	 * @param formName
	 *            the form name
	 * @param formNode
	 *            the form node
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

	public FormFieldType getFormFieldTypeFromCanister(CanisterType formType, String fieldName) {
		return mappingToolAlfrescoToForms.getFormFieldTypeFromCanister(formType, fieldName);
	}

	public void collectTaskProperties(Document instance, Element taskElt,
			WorkflowTaskType taskType, Map<String, GenericClass> alfrescoNodes,
			boolean formIsReadOnly) throws ServletException {
		mappingToolAlfrescoToForms.collectTaskProperties(instance, taskElt, taskType,
				alfrescoNodes, formIsReadOnly);
	}

	public boolean isStartTask(WorkflowTaskType taskType) {
		return mappingToolFormsToAlfresco.isStartTask(taskType);
	}

	public FormType getFormTypeWithDataType(String dataType) {
		return mappingToolFormsToAlfresco.getFormTypeWithDataType(dataType);
	}

	public ClassType getClassTypeWithDataType(String dataType) {
		return mappingToolFormsToAlfresco.getClassTypeWithDataType(dataType);
	}

	public boolean isDynamic(EnumType enumType) {
		return mappingToolFormsToAlfresco.isDynamic(enumType);
	}
	
}
