package com.bluexml.xforms.controller.mapping;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBElement;

import org.apache.commons.lang.StringUtils;
import com.bluexml.xforms.controller.binding.AspectType;
import com.bluexml.xforms.controller.binding.AssociationType;
import com.bluexml.xforms.controller.binding.CanisterType;
import com.bluexml.xforms.controller.binding.ClassType;
import com.bluexml.xforms.controller.binding.EnumType;
import com.bluexml.xforms.controller.binding.FormFieldType;
import com.bluexml.xforms.controller.binding.FormType;
import com.bluexml.xforms.controller.binding.GenericClass;
import com.bluexml.xforms.controller.binding.Mapping;
import com.bluexml.xforms.controller.binding.ModelChoiceType;
import com.bluexml.xforms.controller.binding.WorkflowTaskType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoControllerException;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;

/**
 * The Class MappingTool.<br>
 * Delegates operations to 4 instances of MappingToolCommon
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
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	public Document createClassFormsInstance(AlfrescoTransaction transaction, String type,
			Map<String, String> initParams, Stack<AssociationType> stack, boolean formIsReadOnly)
			throws AlfrescoControllerException {
		return mappingToolImplAlfrescoToXForms.createClassFormsInstance(transaction, type,
				initParams, stack, formIsReadOnly);
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
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	public String getFileContentFileName(AlfrescoTransaction transaction, GenericClass alfClass)
			throws AlfrescoControllerException {
		return mappingToolImplXFormsToAlfresco.getFileContentFileName(transaction, alfClass);
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
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	public RepoContentInfoBean getNodeContentInfo(AlfrescoTransaction transaction,
			GenericClass alfClass) throws AlfrescoControllerException {
		return mappingToolFormsToAlfresco.getNodeContentInfo(transaction, alfClass);
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
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	public RepoContentInfoBean getRepoContentInfo(AlfrescoTransaction transaction,
			GenericClass alfClass) throws AlfrescoControllerException {
		return mappingToolImplXFormsToAlfresco.getRepoContentInfo(transaction, alfClass);
	}
	
	/**
	 * Sets the content file name.
	 * 
	 * @param alfClass
	 *            the alf class
	 * @param fileName
	 *            the file name
	 */
	public void setFileContentFileName(GenericClass alfClass, String fileName) {
		mappingToolImplXFormsToAlfresco.setFileContentFileName(alfClass, fileName);
	}

	/**
	 * Sets the repository content file name.
	 * 
	 * @param alfClass
	 *            the alf class
	 * @param fileName
	 *            the file name
	 */
	public void setRepoContentFileName(GenericClass alfClass, String fileName) {
		mappingToolImplXFormsToAlfresco.setRepoContentFileName(alfClass, fileName);
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
	 * 
	 * @return the document
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	public Document transformAlfrescoToClassForms(AlfrescoTransaction transaction,
			Document alfrescoNode, Stack<AssociationType> stack, boolean formIsReadOnly)
			throws AlfrescoControllerException {
		return mappingToolImplAlfrescoToXForms.transformAlfrescoToClassForms(transaction,
				alfrescoNode, stack, formIsReadOnly);
	}

	/**
	 * Transform class forms to alfresco.
	 * 
	 * @param transaction
	 *            the login
	 * @param node
	 *            the node
	 * 
	 * @return the com.bluexml.xforms.controller.alfresco.binding. class
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 * @throws ServletException
	 */
	public GenericClass transformClassFormsToAlfresco(AlfrescoTransaction transaction, Node node)
			throws AlfrescoControllerException, ServletException {
		return mappingToolImplXFormsToAlfresco.transformClassFormsToAlfresco(transaction, node);
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
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	public Document createFormInstance(AlfrescoTransaction transaction, String formName,
			String alfrescoId, boolean formIsReadOnly) throws AlfrescoControllerException {
		return mappingToolAlfrescoToForms.getFormInstance(transaction, formName, alfrescoId,
				formIsReadOnly);
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
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	public Document newFormInstance(String formName, AlfrescoTransaction transaction,
			Map<String, String> initParams, boolean formIsReadOnly)
			throws AlfrescoControllerException {
		return mappingToolAlfrescoToForms.newFormInstance(formName, transaction, initParams,
				formIsReadOnly);
	}

	/**
	 * Transform forms to alfresco.
	 * 
	 * @param transaction
	 *            the login
	 * @param controller
	 *            the controller
	 * @param formName
	 *            the form name
	 * @param formNode
	 *            the form node
	 * 
	 * @return the com.bluexml.xforms.controller.alfresco.binding. class
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 * @throws ServletException
	 */
	public GenericClass transformsToAlfresco(AlfrescoTransaction transaction,
			AlfrescoController controller, String formName, Node formNode)
			throws AlfrescoControllerException, ServletException {
		return mappingToolFormsToAlfresco.transformsToAlfresco(transaction, formName, formNode);
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
	 * Retourne la valeur de fieldSize pour un type de données. On récupère cette valeur dans la
	 * liste des ModelChoiceField du mapping.
	 * 
	 * @param type
	 *            le type de données concerné
	 * @param defaultVal
	 *            la valeur à renvoyer si aucune valeur n'existe pour ce type.
	 * @param formType
	 *            le type du formulaire concerné
	 * @return
	 */
	public String getFieldSizeForField(String type, String defaultVal, String formName) {
		// on ne teste qu'un formulaire
		List<JAXBElement<? extends CanisterType>> elements = mapping.getCanister();
		for (JAXBElement<? extends CanisterType> element : elements) {
			if (element.getValue() instanceof FormType) {
				FormType form = (FormType) element.getValue();
				if (StringUtils.equals(form.getName(), formName)) {
					List<ModelChoiceType> modelChoices = form.getModelChoice();
					for (ModelChoiceType modelChoice : modelChoices) {
						ClassType rc = modelChoice.getRealClass();
						String completeName = rc.getAlfrescoName().replace("_", ".");
						if (completeName.equalsIgnoreCase(type)) {
							String value = modelChoice.getFieldSize();
							// le nom peut exister sans avoir de field size
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

	//
	// Bridge functions.
	//
	public FormType getFormType(String refName) {
		return mappingToolAlfrescoToForms.getFormType(refName);
	}

	public WorkflowTaskType getWorkflowTaskType(String refName, boolean byId) {
		return mappingToolAlfrescoToForms.getWorkflowTaskType(refName, byId);
	}

	public WorkflowTaskType getWorkflowTaskTypeWithField(String fieldName) {
		return mappingToolAlfrescoToForms.getWorkflowTaskTypeWithField(fieldName);
	}

	public FormFieldType getFormFieldTypeFromCanister(CanisterType taskType, String fieldName) {
		return mappingToolAlfrescoToForms.getFormFieldTypeFromCanister(taskType, fieldName);
	}

	public void collectTaskProperties(Document instance, Element taskElt,
			WorkflowTaskType taskType, Map<String, GenericClass> alfrescoNodes,
			boolean formIsReadOnly) throws AlfrescoControllerException {
		mappingToolAlfrescoToForms.collectTaskProperties(instance, taskElt, taskType,
				alfrescoNodes, formIsReadOnly);
	}

}
