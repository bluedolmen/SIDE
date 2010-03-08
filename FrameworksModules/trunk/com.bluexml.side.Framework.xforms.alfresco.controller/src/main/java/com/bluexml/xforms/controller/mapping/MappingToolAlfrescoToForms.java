package com.bluexml.xforms.controller.mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
import com.bluexml.xforms.controller.binding.FileFieldType;
import com.bluexml.xforms.controller.binding.FormFieldType;
import com.bluexml.xforms.controller.binding.FormType;
import com.bluexml.xforms.controller.binding.GenericAssociation;
import com.bluexml.xforms.controller.binding.GenericAttribute;
import com.bluexml.xforms.controller.binding.GenericClass;
import com.bluexml.xforms.controller.binding.GenericClassReference;
import com.bluexml.xforms.controller.binding.Mapping;
import com.bluexml.xforms.controller.binding.ModelChoiceType;
import com.bluexml.xforms.controller.binding.ReferenceType;
import com.bluexml.xforms.controller.binding.WorkflowTaskType;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class MappingToolAlfrescoToForms.
 */
public class MappingToolAlfrescoToForms extends MappingToolCommon {

	/** The logger. */
	protected static Log logger = LogFactory.getLog(MappingToolAlfrescoToForms.class);

	/**
	 * Instantiates a new mapping tool alfresco to forms.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param controller
	 *            the controller
	 */
	public MappingToolAlfrescoToForms(Mapping mapping, AlfrescoController controller) {
		super(mapping, controller);
	}

	/**
	 * Gets the form instance for a specific object whose id is given.
	 * 
	 * @param transaction
	 *            the login
	 * @param formName
	 *            the form name
	 * @param alfrescoId
	 *            the alfresco id
	 * 
	 * @return the form instance
	 * 
	 * @throws ServletException
	 */
	public Document getFormInstance(AlfrescoTransaction transaction, String formName,
			String alfrescoId, boolean formIsReadOnly) throws ServletException {
		Document formInstance = documentBuilder.newDocument();
		Map<String, GenericClass> alfrescoNodes = new HashMap<String, GenericClass>();
		Element rootElement = formInstance.createElement("root");

		String realName = formName;
		// WorkflowTaskType entry = getWorkflowTaskType(formName, false);
		// if (entry != null) {
		// // on a workflow form; we'll need to add the data form
		// realName = entry.getDataForm();
		// getWorkflowInstance(formInstance, rootElement, alfrescoNodes);
		// }
		getDataFormInstance(formInstance, rootElement, transaction, realName, alfrescoId,
				alfrescoNodes, formIsReadOnly);

		formInstance.appendChild(rootElement);
		return formInstance;
	}

	/**
	 * @param transaction
	 * @param formName
	 * @param alfrescoId
	 * @param doc
	 * @param alfrescoNodes
	 * @param rootElement
	 * @param formType
	 * @throws ServletException
	 */
	private void getDataFormInstance(Document doc, Element rootElement,
			AlfrescoTransaction transaction, String formName, String alfrescoId,
			Map<String, GenericClass> alfrescoNodes, boolean formIsReadOnly)
			throws ServletException {
		FormType formType = getFormType(formName);

		Element formElement = getForm(transaction, formType, alfrescoId, alfrescoNodes, doc,
				formIsReadOnly);
		VirtualResolver virtualResolver = new VirtualResolver(this);
		virtualResolver.prepareAlfrescoToXForms(formElement, formName);

		rootElement.appendChild(formElement);

		Element typeElement = doc.createElement(MsgId.INT_INSTANCE_SIDE_DATATYPE.getText());
		typeElement.setTextContent(classTypeToString(getClassType(formType.getRealClass())));
		rootElement.appendChild(typeElement);

		if (isContentEnabled(formType)) {
			Element nodeContentElt = doc.createElement(MsgId.INT_INSTANCE_SIDE_NODE_CONTENT
					.getText());
			nodeContentElt.setAttribute("file", "");
			nodeContentElt.setAttribute("type", "");
			String contentInfo = controller.getWebscriptNodeContentInfo(alfrescoId);
			nodeContentElt.setTextContent(contentInfo);
			rootElement.appendChild(nodeContentElt);
		}
	}

	/**
	 * Expands an XForms instance with the properties of a workflow task. the structure of this
	 * instance is:
	 * 
	 * <pre>
	 * &lt;workflow&gt;
	 *   &lt;processId&gt;&lt;/processId&gt;
	 *   &lt;instanceId&gt;&lt;/instanceId&gt;
	 *   &lt;NAME OF THE TASK&gt;
	 *     OTHER PROPERTIES COME HERE
	 *   &lt;/NAME OF THE TASK&gt;
	 * &lt;/workflow&gt;
	 * </pre>
	 * 
	 * <b>NOTE</b> if instanceId is set, the processId will be overridden
	 * 
	 * @param formInstance
	 *            the document from which nodes will be created
	 * @param rootElement
	 *            the node to be expanded by the workflow instance
	 * @param bean
	 *            the bean that contains the process id and/or instance id
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private void getWorkflowInstance(Document formInstance, Element rootElement,
			Map<String, GenericClass> alfrescoNodes) {

		// if (bean.isAlive() == false) {
		// return;
		// }
		// Element workflowElement = formInstance.createElement(MsgId.INT_WKFLW_INSTANCE_TAG_WKFLW
		// .getText());
		// Element processIdElement = formInstance
		// .createElement(MsgId.INT_WKFLW_INSTANCE_TAG_PROCESS_ID.getText());
		// Element instanceIdElement = formInstance
		// .createElement(MsgId.INT_WKFLW_INSTANCE_TAG_INSTANCE_ID.getText());
		//
		// workflowElement.appendChild(processIdElement);
		// workflowElement.appendChild(instanceIdElement);
		// // TODO: signaler les erreurs en message de statut
		// if (bean.instanceId != null) {
		// // retrieve and set the process id
		// controller.fillInProcessInfo(bean);
		// // include props of current task
		// addTaskProperties(formInstance, workflowElement, bean, false, alfrescoNodes);
		// }
		// // start task properties are included for all tasks
		// addTaskProperties(formInstance, workflowElement, bean, true, alfrescoNodes);
		//
		// rootElement.appendChild(workflowElement);
		throw new RuntimeException("Deprecated function");
	}

	/**
	 * Appends elements for each of the task's properties into the root element of the instance:
	 * gets the content type associated with the start task and adds the properties for that type.
	 * Start task properties are standard but properties on any other task are a BlueXML addition <br/>
	 * <b>REQUIRED</b>: process id
	 * 
	 * @param formInstance
	 *            the document from which nodes will be created
	 * @param workflowElement
	 *            the root element for the properties to append
	 * @param taskDef
	 *            the task definition from which properties are read
	 * @param isStartTask
	 *            true if dealing with the initial task
	 */
	// @Deprecated
	// private void addTaskProperties(Document formInstance, Element workflowElement,
	// boolean isStartTask, Map<String, GenericClass> alfrescoNodes) {
	// TypeDefinition contentType;
	// String nodeName;
	//
	// if (isStartTask) {
	// nodeName = controller.getStartTaskName(bean);
	// } else {
	// nodeName = controller.workflowGetCurrentTaskName(bean);
	// }
	//
	// contentType = controller.systemGetTaskContentType(bean.processName, nodeName);
	// if (contentType == null) {
	// logger.warn("Content type for " + nodeName + " does not exist.");
	// }
	// // FIXME: check that the formName is correctly taken i.e. the node name
	// // does not have the process name in it
	// String formName = bean.processName + "_" + nodeName;
	// WorkflowTaskType taskType = getWorkflowTaskType(formName, false);
	// Element taskElt = formInstance.createElement(formName);
	// workflowElement.appendChild(taskElt);
	// try {
	// collectTaskProperties(formInstance, taskElt, taskType, alfrescoNodes);
	// } catch (ServletException e) {
	// e.printStackTrace();
	// }
	// throw new RuntimeException("Deprecated function");
	// }

	/**
	 * Appends instance elements for each of the task's properties below the root element of the
	 * instance depending on the definition of the task when designing the form.
	 * 
	 * @param formInstance
	 *            the document from which nodes will be created
	 * @param rootElement
	 *            the parent node to expand
	 * @param taskType
	 *            the definition of the task from the mapping
	 * @param nodeName
	 * @throws ServletException
	 */
	public void collectTaskProperties(Document formInstance, Element rootElement,
			String wkFormName, Map<String, GenericClass> alfrescoNodes,
			boolean formIsReadOnly) throws ServletException {
		AlfrescoTransaction transaction = new AlfrescoTransaction(controller);
		WorkflowTaskType taskType = getWorkflowTaskType(wkFormName, false);

		List<FormFieldType> fields = taskType.getField();
		for (FormFieldType field : fields) {
			newFormField(formInstance, rootElement, field, transaction, null, formIsReadOnly);
		}

		List<ModelChoiceType> modelChoices = taskType.getModelChoice();
		for (ModelChoiceType modelChoice : modelChoices) {
			newFormModelChoice(formInstance, rootElement, modelChoice, transaction, alfrescoNodes,
					formIsReadOnly);
		}

	}

	/**
	 * Initialize a totally empty form instance. No object id is given. //$$ TRACE LOG
	 * 
	 * @param formName
	 *            the form name
	 * @param at
	 *            the alfresco transaction
	 * 
	 * @return the document
	 * 
	 * @throws ServletException
	 */
	public Document newFormInstance(String formName, AlfrescoTransaction at,
			Map<String, String> initParams, boolean formIsReadOnly) throws ServletException {
		FormType formType = getFormType(formName);
		if (logger.isTraceEnabled()) {
			logger.debug("Creating new form instance document for the controller; form: "
					+ formName + ", read-only status: " + formIsReadOnly + ", formType found: "
					+ formType.getName());
		}
		if (formType == null) {
			throw new RuntimeException("Form '" + formName + "' not found in the mapping file.");
		}
		Document formInstance = documentBuilder.newDocument();
		Element rootElement = formInstance.createElement("root");

		// get the actual form structure
		Element formElement = newForm(formType, formInstance, at,
				new HashMap<String, GenericClass>(), initParams, formIsReadOnly);

		VirtualResolver virtualResolver = new VirtualResolver(this);
		virtualResolver.prepareAlfrescoToXForms(formElement, formName);

		rootElement.appendChild(formElement);

		Element typeElement = formInstance
				.createElement(MsgId.INT_INSTANCE_SIDE_DATATYPE.getText());
		typeElement.setTextContent(classTypeToString(getClassType(formType.getRealClass())));
		rootElement.appendChild(typeElement);

		if (isContentEnabled(formType)) {
			Element nodeContentElt = formInstance
					.createElement(MsgId.INT_INSTANCE_SIDE_NODE_CONTENT.getText());
			nodeContentElt.setAttribute("file", "");
			nodeContentElt.setAttribute("type", "");
			rootElement.appendChild(nodeContentElt);
		}

		formInstance.appendChild(rootElement);
		return formInstance;
	}

	/**
	 * New form.
	 * 
	 * @param formType
	 *            the form type
	 * @param formInstance
	 *            the form instance
	 * @param at
	 * @param an
	 * @param transaction
	 * 
	 * @return the element
	 * @throws ServletException
	 */
	private Element newForm(FormType formType, Document formInstance, AlfrescoTransaction at,
			Map<String, GenericClass> an, Map<String, String> initParams, boolean formIsReadOnly)
			throws ServletException {
		Element formElement = formInstance.createElement(formType.getName());

		/*
		 * List<VirtualFieldType> virtuals = formType.getVirtual(); for (VirtualFieldType
		 * virtualFieldType : virtuals) { FormFieldType formFieldType =
		 * getFormFieldType(virtualFieldType .getFormName(), virtualFieldType.getFieldName());
		 * newFormField(formInstance, formElement, formFieldType); }
		 */

		List<FormFieldType> fields = formType.getField();
		for (FormFieldType formFieldType : fields) {
			newFormField(formInstance, formElement, formFieldType, at, initParams, formIsReadOnly);
		}
		List<ModelChoiceType> modelChoices = formType.getModelChoice();
		for (ModelChoiceType modelChoice : modelChoices) {
			newFormModelChoice(formInstance, formElement, modelChoice, at, an, formIsReadOnly);
		}
		List<ReferenceType> references = formType.getReference();
		for (ReferenceType referenceType : references) {
			newFormReference(formInstance, formElement, referenceType, at, an, formIsReadOnly);
		}
		return formElement;
	}

	/**
	 * New form model choice.
	 * 
	 * @param formInstance
	 *            the form instance
	 * @param formElement
	 *            the form element
	 * @param modelChoice
	 *            the model choice
	 * @param at
	 * @param an
	 * @param formIsReadOnly
	 * @throws ServletException
	 */
	private void newFormModelChoice(Document formInstance, Element formElement,
			ModelChoiceType modelChoice, AlfrescoTransaction at, Map<String, GenericClass> an,
			boolean formIsReadOnly) throws ServletException {
		Element modelChoiceReference = formInstance.createElement(modelChoice.getUniqueName());
		int maxBound = modelChoice.getMaxBound();
		if (maxBound != 1) {
			modelChoiceReference.setAttribute("bound", "" + maxBound);
		}

		newFormModelChoiceItem(formInstance, modelChoice, modelChoiceReference, at, an,
				formIsReadOnly);
		formElement.appendChild(modelChoiceReference);
	}

	private void newFormModelChoiceItem(Document formInstance, ModelChoiceType modelChoice,
			Element modelChoiceReference, AlfrescoTransaction at, Map<String, GenericClass> an,
			boolean formIsReadOnly) throws ServletException {
		addModelChoiceItem(formInstance, modelChoiceReference, modelChoice, "", "", "", at, an,
				formIsReadOnly);
	}

	/**
	 * Adds the section corresponding to a model choice into an XForms instance document.
	 * 
	 * @param formInstance
	 *            the XForms instance document being filled
	 * @param modelChoiceReference
	 *            the parent node (its tag name is field id) that will receive the section created
	 *            here; the parent may end up having several sections (in which case the model
	 *            choice is multiple).
	 * @param modelChoice
	 *            the description for the model choice in the mapping file
	 * @param id
	 *            the id
	 * @param label
	 *            the label
	 * @param nodeDataType
	 *            the qualified name of the node as returned by Alfresco
	 * @param at
	 * @param an
	 * @param formIsReadOnly
	 *            whether the form whose instance is being provided is read only
	 * @throws ServletException
	 */
	private void addModelChoiceItem(Document formInstance, Element modelChoiceReference,
			ModelChoiceType modelChoice, String id, String label, String nodeDataType,
			AlfrescoTransaction at, Map<String, GenericClass> an, boolean formIsReadOnly)
			throws ServletException {
		Node subNode = formInstance.createElement(MsgId.INT_INSTANCE_ASSOCIATION_ITEM.getText());
		if (isInline(modelChoice)) {
			Node formNode = null;
			if (StringUtils.trimToNull(id) == null) {
				formNode = newForm(getFormType(modelChoice.getTarget().get(0).getName()),
						formInstance, at, an, null, formIsReadOnly);
			} else {
				formNode = getForm(at, getFormType(modelChoice.getTarget().get(0).getName()), id,
						an, formInstance, formIsReadOnly);
			}
			subNode.appendChild(formNode);
		} else {
			Element eltId = formInstance.createElement(MsgId.INT_INSTANCE_SIDEID.getText());
			eltId.setTextContent(id);
			subNode.appendChild(eltId);

			Element eltLabel = formInstance.createElement(MsgId.INT_INSTANCE_SIDELABEL.getText());
			eltLabel.setTextContent(StringUtils.trimToEmpty(label));
			subNode.appendChild(eltLabel);

			Element eltEdit = formInstance.createElement(MsgId.INT_INSTANCE_SIDEEDIT.getText());
			eltEdit.setTextContent("");
			subNode.appendChild(eltEdit);

			// ** #1485
			Element eltType = formInstance.createElement(MsgId.INT_INSTANCE_SIDETYPE.getText());
			eltType.setTextContent(nodeDataType);
			subNode.appendChild(eltType);
			// ** #1485
		}
		modelChoiceReference.appendChild(subNode);
	}

	/**
	 * New form reference.
	 * 
	 * @param formInstance
	 *            the form instance
	 * @param formElement
	 *            the form element
	 * @param referenceType
	 *            the reference type
	 * @param at
	 * @param an
	 * @param formIsReadOnly
	 * @throws ServletException
	 */
	private void newFormReference(Document formInstance, Element formElement,
			ReferenceType referenceType, AlfrescoTransaction at, Map<String, GenericClass> an,
			boolean formIsReadOnly) throws ServletException {
		Element formReference = formInstance.createElement(referenceType.getUniqueName());
		List<FormType> targets = referenceType.getTarget();
		int i = 0;
		for (FormType formType : targets) {
			newFormReferenceTarget(formInstance, referenceType, formReference, i, formType, at, an,
					formIsReadOnly);
			i++;
		}
		formElement.appendChild(formReference);
	}

	private void newFormReferenceTarget(Document formInstance, ReferenceType referenceType,
			Element formReference, int i, FormType formType, AlfrescoTransaction at,
			Map<String, GenericClass> an, boolean formIsReadOnly) throws ServletException {
		Element elementTarget = newForm(getFormType(formType.getName()), formInstance, at, an,
				null, formIsReadOnly);
		formReference.appendChild(elementTarget);
	}

	/**
	 * Creates a new field with, possibly, an initial value. The priority order is first, an init
	 * param from the uri, tied to the field's unique name, then the init param for its reference
	 * Alfresco attribute, and finally the init value from the form model. //$$ TRACE LOG
	 * 
	 * @param formInstance
	 *            the form instance
	 * @param formElement
	 *            the form element
	 * @param formFieldType
	 *            the form field type
	 * @param transaction
	 * @throws ServletException
	 */
	private void newFormField(Document formInstance, Element formElement,
			FormFieldType formFieldType, AlfrescoTransaction transaction,
			Map<String, String> initParams, boolean formIsReadOnly) throws ServletException {
		if (logger.isTraceEnabled()) {
			logger.debug("  setting value for new form field '" + formFieldType.getUniqueName()
					+ "' with alfresco name '" + formFieldType.getAlfrescoName() + "'");
		}
		String finalValue;
		// try to get an initial value
		String value = safeMapGet(initParams, formFieldType.getUniqueName());
		if (value == null) {
			value = safeMapGet(initParams, formFieldType.getAlfrescoName());
		}
		if (value == null) { // if no value in uri, have we got a default ?
			value = getDefault(formFieldType);
		}
		// if applicable, map the value to its type
		if (StringUtils.trimToNull(value) != null) {
			finalValue = convertAlfrescoAttributeToXforms(value, formFieldType.getType(),
					formFieldType.getStaticEnumType());
		} else {
			finalValue = createXFormsInitialValue(formFieldType.getType(), value, formFieldType
					.getStaticEnumType());
		}
		// add the field into the instance
		addFormFieldValue(formInstance, formElement, formFieldType, finalValue, transaction, null,
				formIsReadOnly);
	}

	/**
	 * Adds the form field value. Sets the value for the given field in the given instance. A
	 * candidate value may be provided, in which case either a default field value had been
	 * specified (via uri or mapping file) or an alfrescoId is known.
	 * <p/>
	 * If the field is an enumeration, the value must be a key instead of an enumeration literal.
	 * 
	 * @param formInstance
	 *            the form instance
	 * @param formElement
	 *            the form element
	 * @param formFieldType
	 *            the form field type
	 * @param value
	 *            the value
	 * @param transaction
	 * @param alfrescoId
	 * @throws ServletException
	 */
	private void addFormFieldValue(Document formInstance, Element formElement,
			FormFieldType formFieldType, String value, AlfrescoTransaction transaction,
			String alfrescoId, boolean formIsReadOnly) throws ServletException {
		Element formField = formInstance.createElement(formFieldType.getUniqueName());

		boolean applyUserFormat = formIsReadOnly || isReadOnly(formFieldType);
		if (formFieldType.getType().equals("DateTime")) {
			String dateValue = getDateFromDateTime(value);
			String timeValue = getTimeFromDateTime(value);
			if (applyUserFormat) {
				dateValue = transformDateValueForDisplay(dateValue);
				timeValue = transformTimeValueForDisplay(timeValue);
				formField.setTextContent(dateValue + " " + timeValue);
			} else {
				Element dateField = formInstance.createElement("date");
				dateField.setTextContent(dateValue);
				formField.appendChild(dateField);
				Element timeField = formInstance.createElement("time");
				timeField.setTextContent(timeValue);
				formField.appendChild(timeField);
			}
		} else if (formFieldType.getType().equals("Date")) {
			String dateValue = value;
			if (applyUserFormat) {
				dateValue = transformDateValueForDisplay(value);
			}
			formField.setTextContent(dateValue);
		} else if (formFieldType.getType().equals("Time")) {
			String timeValue = value;
			if (applyUserFormat) {
				transformTimeValueForDisplay(value);
			}
			formField.setTextContent(timeValue);
		} else if (isSearchEnum(formFieldType)) {
			Element idField = formInstance.createElement(MsgId.INT_INSTANCE_SIDEID.getText());
			idField.setTextContent(value);
			formField.appendChild(idField);
			Element labelField = formInstance.createElement(MsgId.INT_INSTANCE_SIDELABEL.getText());
			if (StringUtils.trimToNull(value) != null) {
				String enumCaption = controller.getEnumCaption(transaction, value);
				labelField.setTextContent(enumCaption);
			} else {
				labelField.setTextContent("");
			}
			formField.appendChild(labelField);
		} else {
			//
			// String enumType = formFieldType.getStaticEnumType();
			// if (StringUtils.trimToNull(enumType) != null) {
			// String enumvalue = StringUtils.trimToNull(EnumAction
			// .getEnumKey(enumType, value));
			// // if alfrescoId is given, variable "value" is already a key
			// value = (alfrescoId == null) ? enumvalue : value;
			// }
			formField.setTextContent(value);

			// file fields need additional attributes
			if (formFieldType instanceof FileFieldType) {
				formField.setAttribute("file", "");
				formField.setAttribute("type", "");
				if (controller.getParamUploadRepoFormatInfo()) {
					FileFieldType fileFieldType = (FileFieldType) formFieldType;
					if ((alfrescoId != null) && (isInRepository(fileFieldType))) {
						formField.setTextContent(controller.getWebscriptNodeContentInfo(value));
					}
				}
			}
		}
		formElement.appendChild(formField);
	}

	/**
	 * Gets the form.
	 * 
	 * @param transaction
	 *            the login
	 * @param formType
	 *            the form type
	 * @param alfrescoId
	 *            the alfresco id
	 * @param alfrescoNodes
	 *            the alfresco nodes
	 * @param formInstance
	 *            the form instance
	 * 
	 * @return the form
	 * 
	 * @throws ServletException
	 */
	private Element getForm(AlfrescoTransaction transaction, FormType formType, String alfrescoId,
			Map<String, GenericClass> alfrescoNodes, Document formInstance, boolean formIsReadOnly)
			throws ServletException {
		GenericClass alfrescoClass = getAlfrescoClass(transaction, alfrescoId, alfrescoNodes);

		Element formElement = formInstance.createElement(formType.getName());

		List<FormFieldType> fields = formType.getField();
		getFormFields(formInstance, formElement, fields, alfrescoClass, transaction, alfrescoId,
				formIsReadOnly);

		List<ModelChoiceType> modelChoices = formType.getModelChoice();
		getFormModelChoices(transaction, alfrescoNodes, formInstance, formElement, modelChoices,
				alfrescoClass, formIsReadOnly);

		List<ReferenceType> references = formType.getReference();
		getFormReferences(formInstance, formElement, references, alfrescoClass, transaction,
				alfrescoNodes, formIsReadOnly);

		Element idField = formInstance.createElement(MsgId.INT_INSTANCE_SIDEID.getText());
		idField.setTextContent(alfrescoId);
		formElement.appendChild(idField);

		return formElement;
	}

	/**
	 * Gets the form model choices.
	 * 
	 * @param formInstance
	 *            the form instance
	 * @param formElement
	 *            the form element
	 * @param modelChoices
	 *            the model choices
	 * @param alfrescoClass
	 *            the alfresco class
	 * @param transaction
	 *            the login
	 * @param alfrescoNodes
	 *            the alfresco nodes
	 * @param formIsReadOnly
	 * 
	 * @return the form model choices
	 * @throws ServletException
	 */
	private void getFormModelChoices(AlfrescoTransaction transaction,
			Map<String, GenericClass> alfrescoNodes, Document formInstance, Element formElement,
			List<ModelChoiceType> modelChoices, GenericClass alfrescoClass, boolean formIsReadOnly)
			throws ServletException {
		List<GenericAssociation> associations = new ArrayList<GenericAssociation>(alfrescoClass
				.getAssociations().getAssociation());
		for (ModelChoiceType modelChoice : modelChoices) {
			getFormModelChoice(transaction, alfrescoNodes, formInstance, formElement, associations,
					modelChoice, formIsReadOnly);
		}
	}

	/**
	 * Gets the form model choice.
	 * 
	 * @param formInstance
	 *            the form instance
	 * @param formElement
	 *            the form element
	 * @param associations
	 *            the associations
	 * @param modelChoice
	 *            the model choice
	 * @param formIsReadOnly
	 * 
	 * @return the form model choice
	 * @throws ServletException
	 */
	private void getFormModelChoice(AlfrescoTransaction transaction,
			Map<String, GenericClass> alfrescoNodes, Document formInstance, Element formElement,
			List<GenericAssociation> associations, ModelChoiceType modelChoice,
			boolean formIsReadOnly) throws ServletException {
		Element modelChoiceReference = formInstance.createElement(modelChoice.getUniqueName());
		int maxBound = modelChoice.getMaxBound();
		if (maxBound != 1) {
			modelChoiceReference.setAttribute("bound", "" + maxBound);
		}

		String alfrescoName = modelChoice.getAlfrescoName();
		List<GenericAssociation> matched = getAssociationAndRemove(alfrescoName, associations);
		for (GenericAssociation association : matched) {
			GenericClassReference target = association.getTarget();
			GenericClassReference associationClass = association.getAssociationClass();
			getModelChoiceItem(transaction, alfrescoNodes, formInstance, modelChoiceReference,
					modelChoice, target, associationClass, formIsReadOnly);
		}
		// add one for xforms if :
		// not even one is present
		// or cardinality is multiple
		if (matched.size() == 0 || modelChoice.getMaxBound() != 1) {
			newFormModelChoiceItem(formInstance, modelChoice, modelChoiceReference, transaction,
					alfrescoNodes, formIsReadOnly);
		}
		formElement.appendChild(modelChoiceReference);
	}

	private void getModelChoiceItem(AlfrescoTransaction transaction,
			Map<String, GenericClass> alfrescoNodes, Document formInstance,
			Element modelChoiceReference, ModelChoiceType modelChoice,
			GenericClassReference target, GenericClassReference associationClass,
			boolean formIsReadOnly) throws ServletException {
		addModelChoiceItem(formInstance, modelChoiceReference, modelChoice, target.getValue(),
				target.getLabel(), target.getQualifiedName(), transaction, alfrescoNodes,
				formIsReadOnly);
	}

	/**
	 * Gets the form references.
	 * 
	 * @param formInstance
	 *            the form instance
	 * @param formElement
	 *            the form element
	 * @param references
	 *            the references
	 * @param alfrescoClass
	 *            the alfresco class
	 * @param transaction
	 *            the login
	 * @param alfrescoNodes
	 *            the alfresco nodes
	 * @param formIsReadOnly
	 * 
	 * @return the form references
	 * 
	 * @throws ServletException
	 */
	private void getFormReferences(Document formInstance, Element formElement,
			List<ReferenceType> references, GenericClass alfrescoClass,
			AlfrescoTransaction transaction, Map<String, GenericClass> alfrescoNodes,
			boolean formIsReadOnly) throws ServletException {

		List<GenericAssociation> associations = new ArrayList<GenericAssociation>(alfrescoClass
				.getAssociations().getAssociation());
		for (ReferenceType reference : references) {

			Element referenceElement = formInstance.createElement(reference.getUniqueName());

			List<FormType> targets = reference.getTarget();
			List<GenericAssociation> matchedAssociations = getAssociationAndRemove(reference
					.getAlfrescoName(), associations);

			Iterator<GenericAssociation> matchedAssociationsIterator = matchedAssociations
					.iterator();

			int i = 0;
			for (FormType target : targets) {
				GenericAssociation association = null;
				if (matchedAssociationsIterator.hasNext()) {
					association = matchedAssociationsIterator.next();
				}
				if (association != null) {

					GenericClassReference associationTarget = association.getTarget();
					String targetId = associationTarget.getValue();
					Element elementTarget = getForm(transaction, getFormType(target.getName()),
							targetId, alfrescoNodes, formInstance, formIsReadOnly);
					referenceElement.appendChild(elementTarget);
				} else {
					newFormReferenceTarget(formInstance, reference, referenceElement, i, target,
							transaction, alfrescoNodes, formIsReadOnly);
				}

				i++;
			}

			formElement.appendChild(referenceElement);

		}
	}

	/**
	 * Gets the list of associations that match the name and removes the matching associations from
	 * the input list.
	 * 
	 * @param alfrescoName
	 *            the alfresco name
	 * @param associations
	 *            the associations
	 * 
	 * @return the list of matching associations
	 */
	private List<GenericAssociation> getAssociationAndRemove(String alfrescoName,
			List<GenericAssociation> associations) {
		List<GenericAssociation> result = new ArrayList<GenericAssociation>();
		for (GenericAssociation association : associations) {
			if (association.getQualifiedName().equals(alfrescoName)) {
				result.add(association);
			}
		}
		associations.removeAll(result);
		return result;
	}

	/**
	 * Gets the form fields.
	 * 
	 * @param formInstance
	 *            the form instance
	 * @param formElement
	 *            the form element
	 * @param fields
	 *            the fields
	 * @param alfrescoClass
	 *            the alfresco class
	 * @param transaction
	 * 
	 * @return the form fields
	 * @throws ServletException
	 */
	private void getFormFields(Document formInstance, Element formElement,
			List<FormFieldType> fields, GenericClass alfrescoClass,
			AlfrescoTransaction transaction, String alfrescoId, boolean formIsReadOnly)
			throws ServletException {
		List<GenericAttribute> attributes = alfrescoClass.getAttributes().getAttribute();
		Map<String, GenericAttribute> attributesMap = new HashMap<String, GenericAttribute>();
		for (GenericAttribute attribute : attributes) {
			attributesMap.put(attribute.getQualifiedName(), attribute);
		}
		for (FormFieldType formFieldType : fields) {
			// TODO: switch entre workflow et classe
			GenericAttribute attribute = attributesMap.get(formFieldType.getAlfrescoName());
			String value = null;

			if (attribute == null || attribute.getValue().size() == 0) {
				value = createXFormsInitialValue(formFieldType.getType(),
						getDefault(formFieldType), formFieldType.getStaticEnumType());
			} else {
				value = convertAlfrescoAttributeToXforms(attribute.getValue(), formFieldType
						.getType(), formFieldType.getStaticEnumType());
			}
			addFormFieldValue(formInstance, formElement, formFieldType, value, transaction,
					alfrescoId, formIsReadOnly);
		}
	}

	/**
	 * Gets the alfresco class.
	 * 
	 * @param transaction
	 *            the login
	 * @param alfrescoId
	 *            the alfresco id
	 * @param alfrescoNodes
	 *            the alfresco nodes
	 * 
	 * @return the alfresco class
	 * 
	 * @throws ServletException
	 */
	private GenericClass getAlfrescoClass(AlfrescoTransaction transaction, String alfrescoId,
			Map<String, GenericClass> alfrescoNodes) throws ServletException {
		GenericClass result = alfrescoNodes.get(alfrescoId);
		if (result == null) {
			try {
				result = unmarshal(controller.readObjectFromRepository(transaction, alfrescoId));
			} catch (Exception e) {
				throw new ServletException(e);
			}
			alfrescoNodes.put(alfrescoId, result);
		}
		return result;
	}

}
