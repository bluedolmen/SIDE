package com.bluexml.xforms.controller.mapping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.bluexml.xforms.controller.binding.AssociationActions;
import com.bluexml.xforms.controller.binding.AssociationType;
import com.bluexml.xforms.controller.binding.ClassType;
import com.bluexml.xforms.controller.binding.FileFieldType;
import com.bluexml.xforms.controller.binding.FormFieldType;
import com.bluexml.xforms.controller.binding.FormType;
import com.bluexml.xforms.controller.binding.GenericAssociation;
import com.bluexml.xforms.controller.binding.GenericAssociations;
import com.bluexml.xforms.controller.binding.GenericAttribute;
import com.bluexml.xforms.controller.binding.GenericClass;
import com.bluexml.xforms.controller.binding.GenericClassReference;
import com.bluexml.xforms.controller.binding.Mapping;
import com.bluexml.xforms.controller.binding.ModelChoiceType;
import com.bluexml.xforms.controller.binding.ReferenceType;
import com.bluexml.xforms.controller.binding.ValueType;
import com.bluexml.xforms.controller.binding.WorkflowTaskType;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoControllerException;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.side.form.utils.DOMUtil;

/**
 * The Class MappingToolFormsToAlfresco.
 */
public class MappingToolFormsToAlfresco extends MappingToolCommon {

	/**
	 * Instantiates a new mapping tool forms to alfresco.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param controller
	 *            the controller
	 */
	public MappingToolFormsToAlfresco(Mapping mapping, AlfrescoController controller) {
		super(mapping, controller);
	}

	/**
	 * Transform forms to alfresco.
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
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 * @throws ServletException
	 */
	public GenericClass transformsToAlfresco(AlfrescoTransaction transaction, String formName,
			Node formNode) throws AlfrescoControllerException, ServletException {
		Element rootElt = getRootElement(formName, formNode);

		VirtualResolver virtualResolver = new VirtualResolver(this);
		virtualResolver.prepareXFormsToAlfresco(rootElt, formName);

		GenericClass result = persistFormElement(transaction, formName, rootElt);

		// if applicable, append an attribute that will keep info about the node content
		Element nodeContentElt = DOMUtil.getChild(formNode, MsgId.INT_INSTANCE_SIDE_NODE_CONTENT
				.getText());
		if (nodeContentElt != null) {
			GenericAttribute contentAttr = alfrescoObjectFactory.createGenericAttribute();
			contentAttr.setQualifiedName(MsgId.INT_INSTANCE_SIDE_NODE_CONTENT.getText());
			contentAttr.setSkipMe("true");

			ValueType pathValue = alfrescoObjectFactory.createValueType();
			ValueType nameValue = alfrescoObjectFactory.createValueType();
			ValueType mimeValue = alfrescoObjectFactory.createValueType();

			String path = nodeContentElt.getTextContent();
			pathValue.setValue(path);

			String nameAndExt = nodeContentElt.getAttribute("file");
			nameValue.setValue(nameAndExt);

			String mimetype = nodeContentElt.getAttribute("type");
			mimeValue.setValue(mimetype);

			contentAttr.getValue().add(pathValue);
			contentAttr.getValue().add(nameValue);
			contentAttr.getValue().add(mimeValue);

			// append the attribute for content
			result.getAttributes().getAttribute().add(contentAttr);
		}
		return result;
	}

	/**
	 * @param formName
	 * @param formNode
	 * @return
	 */
	private Element getRootElement(String formName, Node formNode) {
		Element element = null;
		DOMUtil.logXML(formNode, true);
		if (formNode instanceof Document) {
			Element docElt = ((Document) formNode).getDocumentElement();
			element = DOMUtil.getChild(docElt, formName);
			if (element == null) {
				// we may be saving from a workflow form
				// element = DOMUtil.getFirstElement(docElt); // behavior when no workflows existed
				element = DOMUtil.getChild(element, formName);
			}
		}
		if (formNode instanceof Element) {
			element = (Element) formNode;
		}
		return element;
	}

	/**
	 * Returns a JSON string reflecting the content of the GenericClass object built from the
	 * instance. The example used as a specification is:
	 * <p>
	 * 
	 * <pre>
	 * {type:"{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Personne",
	 *  properties:{"{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Personne_nom":"abad", 
	 *              "{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Personne_prenom":"david"
	 *             }
	 * }
	 * 
	 * <pre>
	 * @param transaction
	 * @param formName
	 * @param shortPropertyNames
	 * @param instance
	 * @return
	 * @throws ServletException
	 * @throws AlfrescoControllerException
	 */
	public String transformsToJSON(AlfrescoTransaction transaction, String formName, Node formNode,
			boolean shortPropertyNames) throws AlfrescoControllerException, ServletException {

		Element root = getRootElement(formName, formNode);

		FormType formType = getFormType(formName);
		GenericClass alfClass = persistFormElement(transaction, formName, root);

		StringBuffer buf = new StringBuffer(256);
		String propName = "";
		String typeName = "";

		ClassType realClass = formType.getRealClass();
		String packageName = realClass.getPackage();
		String rootPackage = packageName.substring(0, packageName.indexOf('.'));
		String namespace = MsgId.INT_NAMESPACE_BLUEXML_CLASS + "/" + rootPackage + "/1.0";

		buf.append("{"); // open JSON string

		// the title
		buf.append("type:\"");
		typeName = "{" + namespace + "}" + realClass.getAlfrescoName();
		buf.append(typeName);
		buf.append("\"");

		// the properties
		buf.append(",properties:{");
		boolean first = true;
		for (GenericAttribute attribute : alfClass.getAttributes().getAttribute()) {
			if (first == false) {
				buf.append(",");
			}
			first = false;
			propName = "{";
			propName += namespace;
			propName += "}";
			propName += attribute.getQualifiedName();
			if (shortPropertyNames) {
				propName = StringUtils.replace(propName, typeName, "");
			}
			buf.append("\"");
			buf.append(propName);
			buf.append("\":\"");
			buf.append(attribute.getValue().get(0).getValue());
			buf.append("\"");
		}
		buf.append("}"); // close properties

		buf.append("}"); // close the JSON string

		return buf.toString();
	}

	/**
	 * Builds a GenericClass from fields and associations defined on the form only: the special
	 * attribute for data node content is added afterwards.
	 * 
	 * @param transaction
	 *            the login
	 * @param formName
	 *            the form name
	 * @param rootElt
	 *            the element
	 * 
	 * @return the com.bluexml.xforms.controller.alfresco.binding. class
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 * @throws ServletException
	 */
	private GenericClass persistFormElement(AlfrescoTransaction transaction, String formName,
			Element rootElt) throws AlfrescoControllerException, ServletException {

		GenericClass alfClass = alfrescoObjectFactory.createGenericClass();
		alfClass.setAttributes(alfrescoObjectFactory.createGenericAttributes());
		GenericAssociations createAssociations = alfrescoObjectFactory.createGenericAssociations();
		alfClass.setAssociations(createAssociations);

		FormType formType = getFormType(formName);
		if (formType != null) {
			ClassType classType = getClassType(formType.getRealClass());

			List<Element> children = DOMUtil.getAllChildren(rootElt);
			String elementId = xformsIdToAlfresco(children);

			alfClass.setQualifiedName(classType.getAlfrescoName());

			if (elementId != null) {
				alfClass.setId(elementId);
				collectAssocsToClear(transaction, rootElt, formType, classType, alfClass);
			}

			collectFields(formName, rootElt, formType.getField(), alfClass);
			collectAssocs(transaction, rootElt, formType.getModelChoice(), formType.getReference(),
					alfClass);
		} else {
			// dealing with a Form Workflow
			WorkflowTaskType taskType = getWorkflowTaskType(formName, false);
			collectFields(formName, rootElt, taskType.getField(), alfClass);
			// change the references list if references become supported in FormWorkflow's
			collectAssocs(transaction, rootElt, taskType.getModelChoice(),
					new ArrayList<ReferenceType>(), alfClass);
		}
		return alfClass;
	}

	/**
	 * Collect assocs to clear.
	 * 
	 * @param transaction
	 *            the login
	 * @param element
	 *            the element
	 * @param formType
	 *            the form type
	 * @param classType
	 *            the class type
	 * @param alfClass
	 *            the alf class
	 */
	private void collectAssocsToClear(AlfrescoTransaction transaction, Element element,
			FormType formType, ClassType classType, GenericClass alfClass) {
		List<ModelChoiceType> modelChoices = new ArrayList<ModelChoiceType>(formType
				.getModelChoice());
		modelChoices.addAll(formType.getReference());

		List<ModelChoiceType> presentModelChoices = new ArrayList<ModelChoiceType>();

		List<AssociationType> associations = new ArrayList<AssociationType>();

		ClassType type = classType;
		do {
			type = getClassType(type);
			associations.addAll(type.getAssociation());
			type = type.getParentClass();
		} while (type != null);

		for (AssociationType associationType : associations) {
			String alfrescoName = associationType.getAlfrescoName();
			ModelChoiceType modelChoiceType = getModelChoice(modelChoices, alfrescoName);
			if (modelChoiceType != null) {
				presentModelChoices.add(modelChoiceType);
			}
		}

		for (ModelChoiceType modelChoiceType : presentModelChoices) {

			String associationAlfrescoName = modelChoiceType.getAlfrescoName();
			// String targetAlfrescoName = getClassType(
			// modelChoiceType.getRealClass()).getAlfrescoName();

			GenericAssociation association = alfrescoObjectFactory.createGenericAssociation();
			association.setQualifiedName(associationAlfrescoName);
			association.setAction(AssociationActions.DELETE_ALL);
			// Target alfTarget = alfrescoObjectFactory.createTarget();
			// alfTarget.setQualifiedName(targetAlfrescoName);
			// alfTarget.setValue("notused");
			// association.getAssociationClassOrTarget().add(alfTarget);
			alfClass.getAssociations().getAssociation().add(association);
		}
	}

	/**
	 * Gets the model choice.
	 * 
	 * @param modelChoices
	 *            the model choices
	 * @param alfrescoName
	 *            the alfresco name
	 * 
	 * @return the model choice
	 */
	private ModelChoiceType getModelChoice(List<ModelChoiceType> modelChoices, String alfrescoName) {
		for (ModelChoiceType modelChoiceType : modelChoices) {
			if (modelChoiceType.getAlfrescoName().equals(alfrescoName)) {
				return modelChoiceType;
			}
		}
		return null;
	}

	/**
	 * Collect assocs.
	 * 
	 * @param transaction
	 *            the login
	 * @param element
	 *            the element
	 * @param formType
	 *            the form type
	 * @param classType
	 *            the class type
	 * @param alfClass
	 *            the alf class
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 * @throws ServletException
	 */
	// private void collectAssocs(AlfrescoTransaction transaction, Element
	// element, FormType formType,
	// GenericClass alfClass) throws AlfrescoControllerException {
	private void collectAssocs(AlfrescoTransaction transaction, Element element,
			List<ModelChoiceType> modelChoices, List<ReferenceType> references,
			GenericClass alfClass) throws AlfrescoControllerException, ServletException {
		for (ModelChoiceType modelChoiceType : modelChoices) {
			Element modelChoiceElement = DOMUtil.getChild(element, modelChoiceType.getUniqueName());
			collectModelChoices(transaction, alfClass, modelChoiceType, modelChoiceElement);
		}
		for (ReferenceType referenceType : references) {
			Element referenceElement = DOMUtil.getChild(element, referenceType.getUniqueName());
			List<FormType> targets = referenceType.getTarget();
			collectTargets(transaction, alfClass, referenceType, referenceElement, targets);
		}
	}

	/**
	 * Collect model choices.
	 * 
	 * @param transaction
	 *            the login
	 * @param alfClass
	 *            the alf class
	 * @param modelChoiceType
	 *            the model choice type
	 * @param modelChoiceElement
	 *            the model choice element
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 * @throws ServletException
	 */
	private void collectModelChoices(AlfrescoTransaction transaction, GenericClass alfClass,
			ModelChoiceType modelChoiceType, Element modelChoiceElement)
			throws AlfrescoControllerException, ServletException {
		List<Element> values = new ArrayList<Element>(DOMUtil.getAllChildren(modelChoiceElement));
		if (modelChoiceType.getMaxBound() != 1) {
			values.remove(values.size() - 1);
		}
		for (Element value : values) {
			collectModelChoice(alfClass, modelChoiceType, value, transaction);
		}
	}

	/**
	 * Collect model choice.
	 * 
	 * @param alfClass
	 *            the alf class
	 * @param modelChoiceType
	 *            the model choice type
	 * @param value
	 *            the value
	 * @param at
	 * @throws AlfrescoControllerException
	 * @throws ServletException
	 */
	private void collectModelChoice(GenericClass alfClass, ModelChoiceType modelChoiceType,
			Element value, AlfrescoTransaction at) throws AlfrescoControllerException,
			ServletException {
		String id = getModelChoiceId(value, modelChoiceType, at);
		if (id != null) {
			collectAddAssociation(alfClass, id, modelChoiceType);
		}
	}

	private String getModelChoiceId(Element value, ModelChoiceType modelChoiceType,
			AlfrescoTransaction at) throws AlfrescoControllerException, ServletException {
		String id = null;
		if (modelChoiceType.isInline()) {
			id = controller.persistForm(at, modelChoiceType.getTarget().get(0).getName(), DOMUtil
					.getFirstElement(value));
		} else {
			id = getId(value);
		}
		return id;
	}

	/**
	 * Collect targets.
	 * 
	 * @param transaction
	 *            the login
	 * @param alfClass
	 *            the alf class
	 * @param referenceType
	 *            the reference type
	 * @param referenceElement
	 *            the reference element
	 * @param targets
	 *            the targets
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 * @throws ServletException
	 */
	private void collectTargets(AlfrescoTransaction transaction, GenericClass alfClass,
			ReferenceType referenceType, Element referenceElement, List<FormType> targets)
			throws AlfrescoControllerException, ServletException {

		int i = 0;
		for (FormType target : targets) {
			String targetId = null;
			Element targetElement = null;

			targetElement = DOMUtil.getChild(referenceElement, target.getName());

			targetId = controller.persistForm(transaction, target.getName(), targetElement);
			collectAddAssociation(alfClass, targetId, referenceType);
			i++;
		}
	}

	/**
	 * Collect add association.
	 * 
	 * @param alfClass
	 *            the alf class
	 * @param targetId
	 *            the target id
	 * @param modelChoiceType
	 *            the model choice type
	 * @param associationClassId
	 *            the association class id
	 */
	private void collectAddAssociation(GenericClass alfClass, String targetId,
			ModelChoiceType modelChoiceType) {

		String associationAlfrescoName = modelChoiceType.getAlfrescoName();
		String targetAlfrescoName = getClassType(modelChoiceType.getRealClass()).getAlfrescoName();

		GenericAssociation association = alfrescoObjectFactory.createGenericAssociation();
		association.setQualifiedName(associationAlfrescoName);
		association.setAction(AssociationActions.ADD);

		GenericClassReference alfTarget = alfrescoObjectFactory.createGenericClassReference();
		alfTarget.setQualifiedName(targetAlfrescoName);
		alfTarget.setValue(targetId);
		association.setTarget(alfTarget);

		alfClass.getAssociations().getAssociation().add(association);
	}

	/**
	 * Collect fields.
	 * 
	 * @param formName
	 *            the name of the current form, used for messages.
	 * @param rootElt
	 *            the root element of the instance
	 * @param formType
	 *            the form type
	 * @param classType
	 *            the class type
	 * @param alfClass
	 *            the GenericClass to be filled
	 */
	private void collectFields(String formName, Element rootElt, List<FormFieldType> fields,
			GenericClass alfClass) {
		for (FormFieldType fieldType : fields) {
			String uniqueName = fieldType.getUniqueName();
			String alfrescoName = fieldType.getAlfrescoName();
			Element fieldElement = DOMUtil.getChild(rootElt, uniqueName);
			if (fieldElement == null) {
				throw new RuntimeException("No DOM element was found in the instance for field: "
						+ uniqueName + "' (" + alfrescoName
						+ "). Probably another form has the same id as this one ('" + formName
						+ "') or there's a bug in the XForms engine.");
			}

			//
			GenericAttribute attribute = alfrescoObjectFactory.createGenericAttribute();
			attribute.setQualifiedName(alfrescoName);
			String type = fieldType.getType();
			String inputTextContent = fieldElement.getTextContent();
			boolean readOnly = fieldType.isReadOnly();
			if (logger.isTraceEnabled()) {
				logger.debug("Received value '" + inputTextContent + "' for attribute '"
						+ alfrescoName + "' with type '" + type + "'. Read-only status '"
						+ readOnly + "'. isFileField: " + (fieldType instanceof FileFieldType)
						+ " . isServletRequest: N/A");
			}

			//
			// mark FileField's with their destination. Useful for the webscript.
			if (fieldType instanceof FileFieldType) {
				FileFieldType fileField = (FileFieldType) fieldType;
				String destination = fileField.isInRepository() ? MsgId.INT_UPLOAD_DEST_REPO
						.getText() : MsgId.INT_UPLOAD_DEST_FILE.getText();
				attribute.setUploadTo(destination);
			}

			//
			// convert the XForms field value to an attribute (possibly multiple) value
			if (fieldType.isMultiple()) {
				convertXformsAttributeToAlfresco(attribute, inputTextContent, type, fieldType
						.getStaticEnumType());
			} else {
				String alfrescoValue = null;
				// if applicable, take the user format into account
				if (isAmendable(type, fieldType.isReadOnly(), false)) {
					inputTextContent = getReadOnlyDateOrTimeModifiedValue(type, inputTextContent);
				}
				if (type.equals("DateTime")) {
					String date;
					String time;
					if (readOnly) {
						date = extractDateFromDateTimeModified(inputTextContent);
						time = extractTimeFromDateTimeModified(inputTextContent);
					} else {
						date = DOMUtil.getChild(fieldElement, "date").getTextContent();
						time = DOMUtil.getChild(fieldElement, "time").getTextContent();
					}
					alfrescoValue = getDateTimeFromDateAndTime(date, time);
				} else if (fieldType.isSearchEnum()) {
					alfrescoValue = DOMUtil.getChild(fieldElement,
							MsgId.INT_INSTANCE_SIDEID.getText()).getTextContent();
				} else {
					alfrescoValue = convertXformsAttributeToAlfresco(inputTextContent, type,
							fieldType.getStaticEnumType());
				}
				ValueType valueType = alfrescoObjectFactory.createValueType();
				valueType.setValue(alfrescoValue);
				attribute.getValue().add(valueType);
			}

			alfClass.getAttributes().getAttribute().add(attribute);
		}
	}

	/**
	 * Gets the (repository) content attribute.
	 * 
	 * @param alfClass
	 *            the alf class
	 * 
	 * @return the repository content attribute
	 */
	private GenericAttribute getNodeContentAttribute(GenericClass alfClass) {

		List<GenericAttribute> attributes = alfClass.getAttributes().getAttribute();
		for (GenericAttribute attribute : attributes) {
			if (attribute.getQualifiedName().equals(MsgId.INT_INSTANCE_SIDE_NODE_CONTENT.getText())) {
				return attribute;
			}
		}
		return null;
	}

	/**
	 * Gets the repository content file name.
	 * 
	 * @param transaction
	 *            the login
	 * @param alfClass
	 *            the alf class
	 * 
	 * @return null if no repository content file name was detected
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	public RepoContentInfoBean getNodeContentInfo(AlfrescoTransaction transaction,
			GenericClass alfClass) throws AlfrescoControllerException {
		GenericAttribute contentAttribute = getNodeContentAttribute(alfClass);
		if (contentAttribute != null) {
			String path = contentAttribute.getValue().get(0).getValue();
			String name = contentAttribute.getValue().get(1).getValue();
			String type = contentAttribute.getValue().get(2).getValue();

			return new RepoContentInfoBean(path, name, type, contentAttribute, controller
					.getUploadRepoAppendSuffix());
		}
		return null;
	}

}
