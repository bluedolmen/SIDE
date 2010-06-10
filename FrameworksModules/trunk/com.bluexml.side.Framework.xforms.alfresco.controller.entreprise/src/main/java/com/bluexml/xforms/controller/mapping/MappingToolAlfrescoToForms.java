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
import com.bluexml.xforms.controller.binding.ValueType;
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
	 * @param initParams
	 * 
	 * @return the form instance
	 * 
	 * @throws ServletException
	 */
	public Document getFormInstance(AlfrescoTransaction transaction, String formName,
			String alfrescoId, Map<String, String> initParams, boolean formIsReadOnly)
			throws ServletException {
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
				alfrescoNodes, formIsReadOnly, initParams);

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
	 * @param initParams
	 * @param formType
	 * @throws ServletException
	 */
	private void getDataFormInstance(Document doc, Element rootElement,
			AlfrescoTransaction transaction, String formName, String alfrescoId,
			Map<String, GenericClass> alfrescoNodes, boolean formIsReadOnly,
			Map<String, String> initParams) throws ServletException {
		FormType formType = getFormType(formName);

		Element formElement = getForm(transaction, formType, alfrescoId, alfrescoNodes, doc,
				initParams, formIsReadOnly);
		VirtualResolver virtualResolver = new VirtualResolver(this);
		virtualResolver.prepareAlfrescoToXForms(formElement, formName);

		rootElement.appendChild(formElement);

		Element typeElement = doc.createElement(MsgId.INT_INSTANCE_SIDE_DATATYPE.getText());
		typeElement.setTextContent(classTypeToString(getClassType(formType.getRealClass())));
		rootElement.appendChild(typeElement);

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
	 * @param transaction
	 * @param formInstance
	 *            the document from which nodes will be created
	 * @param rootElement
	 *            the parent node to be filled in
	 * @param wkFormName
	 *            the name/id of the workflow form
	 * @param nodeName
	 */
	public void collectTaskProperties(AlfrescoTransaction transaction, Document formInstance,
			Element rootElement, String wkFormName, Map<String, GenericClass> alfrescoNodes,
			boolean formIsReadOnly) {
		WorkflowTaskType taskType = getWorkflowTaskType(wkFormName, false);

		Map<String, String> initParams = transaction.getInitParams();
		if (taskType != null) {
			List<FormFieldType> fields = taskType.getField();
			for (FormFieldType field : fields) {
				// we don't filter the parameter map when dealing with fields
				newFormField(formInstance, rootElement, field, transaction, initParams,
						formIsReadOnly);
			}

			List<ModelChoiceType> modelChoices = taskType.getModelChoice();
			for (ModelChoiceType modelChoiceType : modelChoices) {
				// filter the map
				Map<String, String> mcfParams = getSubMap(initParams, modelChoiceType);
				newFormModelChoice(formInstance, rootElement, modelChoiceType, transaction,
						alfrescoNodes, mcfParams, formIsReadOnly);
			}
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
	 */
	public Document newFormInstance(String formName, AlfrescoTransaction at,
			Map<String, String> initParams, boolean formIsReadOnly) {
		FormType formType = getFormType(formName);
		if (loggertrace.isTraceEnabled()) {
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
	 */
	private Element newForm(FormType formType, Document formInstance, AlfrescoTransaction at,
			Map<String, GenericClass> an, Map<String, String> initParams, boolean formIsReadOnly) {
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
		for (ModelChoiceType modelChoiceType : modelChoices) {
			Map<String, String> mcfParams = getSubMap(initParams, modelChoiceType);
			newFormModelChoice(formInstance, formElement, modelChoiceType, at, an, mcfParams,
					formIsReadOnly);
		}
		List<ReferenceType> references = formType.getReference();
		for (ReferenceType referenceType : references) {
			Map<String, String> refParams = getSubMap(initParams, referenceType);
			newFormReference(formInstance, formElement, referenceType, at, an, refParams,
					formIsReadOnly);
		}

		appendFieldForContent(at, formType, formInstance, formElement, null);

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
	 * @param initParams
	 * @param formIsReadOnly
	 */
	private void newFormModelChoice(Document formInstance, Element formElement,
			ModelChoiceType modelChoice, AlfrescoTransaction at, Map<String, GenericClass> an,
			Map<String, String> initParams, boolean formIsReadOnly) {
		Element modelChoiceReference = formInstance.createElement(modelChoice.getUniqueName());
		int maxBound = modelChoice.getMaxBound();
		if (maxBound != 1) {
			modelChoiceReference.setAttribute("bound", "" + maxBound);
		}

		newFormModelChoiceItem(formInstance, modelChoice, modelChoiceReference, at, an, initParams,
				formIsReadOnly);
		formElement.appendChild(modelChoiceReference);
	}

	private void newFormModelChoiceItem(Document formInstance, ModelChoiceType modelChoice,
			Element modelChoiceReference, AlfrescoTransaction at, Map<String, GenericClass> an,
			Map<String, String> initParams, boolean formIsReadOnly) {
		addModelChoiceItem(formInstance, modelChoiceReference, modelChoice, "", "", "", at, an,
				initParams, formIsReadOnly);
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
	 * @param initParams
	 * @param formIsReadOnly
	 *            whether the form whose instance is being provided is read only
	 */
	private void addModelChoiceItem(Document formInstance, Element modelChoiceReference,
			ModelChoiceType modelChoice, String id, String label, String nodeDataType,
			AlfrescoTransaction at, Map<String, GenericClass> an, Map<String, String> initParams,
			boolean formIsReadOnly) {
		Node subNode = formInstance.createElement(MsgId.INT_INSTANCE_ASSOCIATION_ITEM.getText());
		if (isInline(modelChoice)) {
			Node formNode = null;
			if (StringUtils.trimToNull(id) == null) {
				formNode = newForm(getFormType(modelChoice.getTarget().get(0).getName()),
						formInstance, at, an, initParams, formIsReadOnly);
				subNode.appendChild(formNode);
			} else {
				try {
					formNode = getForm(at, getFormType(modelChoice.getTarget().get(0).getName()),
							id, an, formInstance, initParams, formIsReadOnly);
					subNode.appendChild(formNode);
				} catch (ServletException e) {
					logger.error("Error getting the instance section for model choice '"
							+ modelChoice.getDisplayLabel() + "' supporting association '"
							+ modelChoice.getAlfrescoName() + "'", e);
				}
			}
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
	 * @param initParams
	 * @param formIsReadOnly
	 */
	private void newFormReference(Document formInstance, Element formElement,
			ReferenceType referenceType, AlfrescoTransaction at, Map<String, GenericClass> an,
			Map<String, String> initParams, boolean formIsReadOnly) {
		Element formReference = formInstance.createElement(referenceType.getUniqueName());
		List<FormType> targets = referenceType.getTarget();
		int i = 0;
		for (FormType formType : targets) {
			newFormReferenceTarget(formInstance, referenceType, formReference, i, formType, at, an,
					initParams, formIsReadOnly);
			i++;
		}
		formElement.appendChild(formReference);
	}

	private void newFormReferenceTarget(Document formInstance, ReferenceType referenceType,
			Element formReference, int i, FormType formType, AlfrescoTransaction at,
			Map<String, GenericClass> an, Map<String, String> initParams, boolean formIsReadOnly) {
		Element elementTarget = newForm(getFormType(formType.getName()), formInstance, at, an,
				initParams, formIsReadOnly);
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
	 */
	private void newFormField(Document formInstance, Element formElement,
			FormFieldType formFieldType, AlfrescoTransaction transaction,
			Map<String, String> initParams, boolean formIsReadOnly) {
		if (loggertrace.isTraceEnabled()) {
			logger.debug("  setting value for new form field '" + formFieldType.getUniqueName()
					+ "' with alfresco name '" + formFieldType.getAlfrescoName() + "'");
		}
		String finalValue;
		// try to get an initial value
		String value = safeMapGet(initParams, formFieldType.getShortName());
		if (value == null) {
			value = safeMapGet(initParams, formFieldType.getAlfrescoName());
		}
		if (value == null) {
			value = safeMapGet(initParams, formFieldType.getUniqueName());
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
		// add the field value into the instance
		String NoID = null;
		addFormFieldValue(formInstance, formElement, formFieldType, finalValue, transaction, NoID,
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
	 * @param initParams
	 */
	private void addFormFieldValue(Document formInstance, Element formElement,
			FormFieldType formFieldType, String value, AlfrescoTransaction transaction,
			String alfrescoId, boolean formIsReadOnly) {
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
		} else if (isSelectionCapable(formFieldType)) {
			Element selItemElt = formInstance.createElement(MsgId.INT_INSTANCE_ASSOCIATION_ITEM
					.getText());
			Element idElt = formInstance.createElement(MsgId.INT_INSTANCE_SIDEID.getText());
			Element labelElt = formInstance.createElement(MsgId.INT_INSTANCE_SIDELABEL.getText());
			Element typeElt = formInstance.createElement(MsgId.INT_INSTANCE_SIDETYPE.getText());

			// **try** to initialize using the 'value' variable
			if (StringUtils.trimToNull(value) != null) {
				String datatype = getXtensionDataType(formFieldType);
				String identifier = getXtensionIdentifier(formFieldType);
				String format = getXtensionFormat(formFieldType);
				String labelLength = getXtensionLabelLength(formFieldType);
				try {
					String nodeInfo = controller.resolveObjectInfo(transaction, datatype,
							identifier, format, labelLength, value);
					String label = getLabelFromObjectInfo(nodeInfo);
					String qname = getQNameFromObjectInfo(nodeInfo);
					idElt.setTextContent(value);
					labelElt.setTextContent(label);
					typeElt.setTextContent(qname);
					if (logger.isWarnEnabled() && qname.equals(datatype) == false) {
						logger.warn("Got QNmae '" + qname
								+ "' when resolving object info for value '" + value
								+ "' of type '" + datatype + "' with identifier '" + identifier
								+ "'");
					}
				} catch (ServletException e) {
					// nothing to do, the fields just don't get initialized
				}
			}
			selItemElt.appendChild(idElt);
			selItemElt.appendChild(labelElt);
			selItemElt.appendChild(typeElt);

			formField.appendChild(selItemElt);
		} else if (isSearchEnum(formFieldType)) {
			Element idField = formInstance.createElement(MsgId.INT_INSTANCE_SIDEID.getText());
			idField.setTextContent(value);
			formField.appendChild(idField);
			Element labelField = formInstance.createElement(MsgId.INT_INSTANCE_SIDELABEL.getText());
			if (StringUtils.trimToNull(value) != null) {
				String enumCaption;
				try {
					enumCaption = controller.getEnumCaption(transaction, value);
					labelField.setTextContent(enumCaption);
				} catch (ServletException e) {
					logger.error("Error getting an enum caption", e);
				}
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
				if (controller.getParamUploadRepoFormatInfo(transaction.getInitParams())) {
					FileFieldType fileFieldType = (FileFieldType) formFieldType;
					if ((alfrescoId != null) && (isInRepository(fileFieldType))) {
						formField.setTextContent(controller.getNodeContentInfo(transaction, value));
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
	 * @param initParams
	 * 
	 * @return the form
	 * 
	 * @throws ServletException
	 */
	private Element getForm(AlfrescoTransaction transaction, FormType formType, String alfrescoId,
			Map<String, GenericClass> alfrescoNodes, Document formInstance,
			Map<String, String> initParams, boolean formIsReadOnly) throws ServletException {
		GenericClass alfrescoClass = getAlfrescoClass(transaction, alfrescoId, alfrescoNodes);

		Element formElement = formInstance.createElement(formType.getName());

		//
		List<FormFieldType> fields = formType.getField();
		getFormFields(formInstance, formElement, fields, alfrescoClass, transaction, alfrescoId,
				initParams, formIsReadOnly);

		//
		List<ModelChoiceType> modelChoices = formType.getModelChoice();
		getFormModelChoices(transaction, alfrescoNodes, formInstance, formElement, modelChoices,
				alfrescoClass, initParams, formIsReadOnly);

		//
		List<ReferenceType> references = formType.getReference();
		getFormReferences(formInstance, formElement, references, alfrescoClass, transaction,
				alfrescoNodes, initParams, formIsReadOnly);

		Element idField = formInstance.createElement(MsgId.INT_INSTANCE_SIDEID.getText());
		idField.setTextContent(alfrescoId);
		formElement.appendChild(idField);

		appendFieldForContent(transaction, formType, formInstance, formElement, alfrescoId);

		return formElement;
	}

	/**
	 * Adds a DOM node for the implicit file content upload field, in case the form is content
	 * enabled. If the instance refers to a live repository object, the content info is fetched.
	 * 
	 * @param formType
	 * @param formInstance
	 * @param formElement
	 * @param alfrescoId
	 *            the full node id
	 */
	private void appendFieldForContent(AlfrescoTransaction transaction, FormType formType,
			Document formInstance, Element formElement, String alfrescoId) {
		if (isContentEnabled(formType)) {
			Element nodeContentElt = formInstance
					.createElement(MsgId.INT_INSTANCE_SIDE_NODE_CONTENT.getText());
			nodeContentElt.setAttribute("file", "");
			nodeContentElt.setAttribute("type", "");
			if (alfrescoId != null) {
				String contentInfo = controller.getNodeContentInfo(transaction, alfrescoId);
				nodeContentElt.setTextContent(contentInfo);
			}

			formElement.appendChild(nodeContentElt);
			if (loggertrace.isTraceEnabled()) {
				logger.debug("  appended content field '" + nodeContentElt.getNodeName() + "'");
			}
		}
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
	 * @param initParams
	 * @param formIsReadOnly
	 * 
	 * @return the form model choices
	 * @throws ServletException
	 */
	private void getFormModelChoices(AlfrescoTransaction transaction,
			Map<String, GenericClass> alfrescoNodes, Document formInstance, Element formElement,
			List<ModelChoiceType> modelChoices, GenericClass alfrescoClass,
			Map<String, String> initParams, boolean formIsReadOnly) throws ServletException {
		List<GenericAssociation> associations = new ArrayList<GenericAssociation>(alfrescoClass
				.getAssociations().getAssociation());

		for (ModelChoiceType modelChoiceType : modelChoices) {
			Map<String, String> mcfParams = getSubMap(initParams, modelChoiceType);
			getFormModelChoice(transaction, alfrescoNodes, formInstance, formElement, associations,
					modelChoiceType, mcfParams, formIsReadOnly);
		}
	}

	/**
	 * Gets the subset of a map's entries that are relevant for a ModelChoiceField.
	 * 
	 * @param initParams
	 *            the initial map of parameters
	 * @param modelChoiceType
	 *            the model choice field's description in the mapping file
	 * @return the filtered map
	 */
	private Map<String, String> getSubMap(Map<String, String> initParams,
			ModelChoiceType modelChoiceType) {// 1193

		String shortName = modelChoiceType.getShortName();
		String alfName = modelChoiceType.getAlfrescoName();
		Map<String, String> mcfParams = subMap(initParams, shortName);
		Map<String, String> auxiliaryMap = subMap(initParams, alfName);
		try {
			mcfParams.putAll(auxiliaryMap);
		} catch (Exception e) {
			logger.error("Error merging maps (shortName + alfrescoName) for association class '"
					+ shortName + "' of model choice '" + modelChoiceType.getDisplayLabel()
					+ "'. Continuing anyway.");
		}
		return mcfParams;
	}

	/**
	 * Gets DOM section for the form model choice, whether multiple or single, inline or select.
	 * 
	 * @param formInstance
	 *            the form instance
	 * @param formElement
	 *            the form element
	 * @param associations
	 *            the associations
	 * @param modelChoice
	 *            the model choice
	 * @param initParams
	 *            the init parameters, restricted to the model choice
	 * @param formIsReadOnly
	 * 
	 * @return the form model choice
	 * @throws ServletException
	 */
	private void getFormModelChoice(AlfrescoTransaction transaction,
			Map<String, GenericClass> alfrescoNodes, Document formInstance, Element formElement,
			List<GenericAssociation> associations, ModelChoiceType modelChoice,
			Map<String, String> initParams, boolean formIsReadOnly) throws ServletException {
		Element modelChoiceReference = formInstance.createElement(modelChoice.getUniqueName());
		int maxBound = modelChoice.getMaxBound();
		if (maxBound != 1) {
			modelChoiceReference.setAttribute("bound", "" + maxBound);
		}

		// the list of associated items that will appear in the instance
		List<GenericAssociation> matched;

		// #1193: overriding of existing objects' values
		String ids = initParams.get("id"); // <-- multiple targets are allowed, if comma-separated
		if (ids != null) {
			// CAUTION: if overriding, targets from the repository are ignored
			matched = overrideAssociations(transaction, modelChoice, ids);
			initParams.remove("id");
		} else {
			String alfrescoName = modelChoice.getAlfrescoName();
			matched = getAssociationAndRemove(alfrescoName, associations);
		}

		for (GenericAssociation association : matched) {
			GenericClassReference target = association.getTarget();
			GenericClassReference associationClass = association.getAssociationClass();
			getModelChoiceItem(transaction, alfrescoNodes, formInstance, modelChoiceReference,
					modelChoice, target, associationClass, initParams, formIsReadOnly);
		}
		// add one for xforms if : not even one is present or cardinality is multiple
		if (matched.size() == 0 || modelChoice.getMaxBound() != 1) {
			newFormModelChoiceItem(formInstance, modelChoice, modelChoiceReference, transaction,
					alfrescoNodes, initParams, formIsReadOnly);
		}
		formElement.appendChild(modelChoiceReference);
	}

	/**
	 * Provides a list with the information about the given object ids
	 * 
	 * @param transaction
	 * @param modelChoice
	 * @param ids
	 *            a list of comma-separated ids (with or without protocol+store)
	 * @return the list
	 * @throws ServletException
	 */
	private List<GenericAssociation> overrideAssociations(AlfrescoTransaction transaction,
			ModelChoiceType modelChoice, String ids) throws ServletException { // #1193
		List<GenericAssociation> resultList = new ArrayList<GenericAssociation>();

		// we make sure the ids have protocol and store
		String[] splittedIds = StringUtils.split(ids, ',');
		StringBuffer patchedIds = new StringBuffer("");
		boolean first = true;
		for (String splittedId : splittedIds) {
			if (first == false) {
				patchedIds.append(',');
			}
			patchedIds.append(controller.patchDataId(splittedId));
			first = false;
		}
		if (patchedIds.length() == 0) {
			// this should never happen if the ModelChoice is correctly initialized
			return resultList;
		}

		// get the info
		String result = controller.readObjectsInfo(transaction, modelChoice.getFormatPattern(),
				patchedIds.toString());

		// process the info
		String[] splittedInfos = StringUtils.split(result, ',');
		for (String info : splittedInfos) {
			int pos = info.indexOf(WEBSCRIPT_SEPARATOR);
			String id = info.substring(0, pos);

			int posEnd = info.indexOf(WEBSCRIPT_SEPARATOR, pos + WEBSCRIPT_SEPARATOR_LENGTH);
			String label = info.substring(pos + WEBSCRIPT_SEPARATOR_LENGTH, posEnd);

			pos = info.lastIndexOf(WEBSCRIPT_SEPARATOR);
			String qname = info.substring(pos + WEBSCRIPT_SEPARATOR_LENGTH);

			GenericAssociation association = alfrescoObjectFactory.createGenericAssociation();
			GenericClassReference target = alfrescoObjectFactory.createGenericClassReference();
			target.setValue(id);
			target.setLabel(label);
			target.setQualifiedName(qname);
			association.setTarget(target);

			resultList.add(association);
		}
		return resultList;
	}

	private void getModelChoiceItem(AlfrescoTransaction transaction,
			Map<String, GenericClass> alfrescoNodes, Document formInstance,
			Element modelChoiceReference, ModelChoiceType modelChoice,
			GenericClassReference target, GenericClassReference associationClass,
			Map<String, String> initParams, boolean formIsReadOnly) {
		addModelChoiceItem(formInstance, modelChoiceReference, modelChoice, target.getValue(),
				target.getLabel(), target.getQualifiedName(), transaction, alfrescoNodes,
				initParams, formIsReadOnly);
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
	 * @param initParams
	 * @param formIsReadOnly
	 * 
	 * @return the form references
	 * 
	 * @throws ServletException
	 */
	private void getFormReferences(Document formInstance, Element formElement,
			List<ReferenceType> references, GenericClass alfrescoClass,
			AlfrescoTransaction transaction, Map<String, GenericClass> alfrescoNodes,
			Map<String, String> initParams, boolean formIsReadOnly) throws ServletException {

		List<GenericAssociation> associations = new ArrayList<GenericAssociation>(alfrescoClass
				.getAssociations().getAssociation());
		for (ReferenceType reference : references) {
			Map<String, String> refParams = getSubMap(initParams, reference);
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
							targetId, alfrescoNodes, formInstance, refParams, formIsReadOnly);
					referenceElement.appendChild(elementTarget);
				} else {
					newFormReferenceTarget(formInstance, reference, referenceElement, i, target,
							transaction, alfrescoNodes, refParams, formIsReadOnly);
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
	 * @param initParams
	 * @return the form fields
	 */
	private void getFormFields(Document formInstance, Element formElement,
			List<FormFieldType> fields, GenericClass alfrescoClass,
			AlfrescoTransaction transaction, String alfrescoId, Map<String, String> initParams,
			boolean formIsReadOnly) {
		List<GenericAttribute> attributes = alfrescoClass.getAttributes().getAttribute();
		Map<String, GenericAttribute> attributesMap = new HashMap<String, GenericAttribute>();
		for (GenericAttribute attribute : attributes) {
			attributesMap.put(attribute.getQualifiedName(), attribute);
		}
		for (FormFieldType formFieldType : fields) {
			GenericAttribute attribute = attributesMap.get(formFieldType.getAlfrescoName());
			String value = null;

			if (attribute == null || attribute.getValue().size() == 0) {
				// since implicit entries like SIDEID don't get into the mapping file anymore, I
				// don't think this case will ever be reached again
				value = createXFormsInitialValue(formFieldType.getType(),
						getDefault(formFieldType), formFieldType.getStaticEnumType());
			} else {
				// ** #1193
				// get the value from the parameters
				String overrideValue = safeMapGet(initParams, formFieldType.getShortName());
				if (overrideValue == null) {
					overrideValue = safeMapGet(initParams, formFieldType.getAlfrescoName());
				}

				List<ValueType> valuesToUse;
				// use the param value if any
				if (overrideValue != null) {
					valuesToUse = new ArrayList<ValueType>(1);
					ValueType aValue = alfrescoObjectFactory.createValueType();
					aValue.setValue(overrideValue);
					valuesToUse.add(aValue);
				} else {
					valuesToUse = attribute.getValue();
				}
				value = convertAlfrescoAttributeToXforms(valuesToUse, formFieldType.getType(),
						formFieldType.getStaticEnumType());
				// ** #1193
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
