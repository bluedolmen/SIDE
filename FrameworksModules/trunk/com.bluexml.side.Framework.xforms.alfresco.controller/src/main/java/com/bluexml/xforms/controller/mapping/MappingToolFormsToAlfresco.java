package com.bluexml.xforms.controller.mapping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.bluexml.xforms.controller.binding.AssociationActions;
import com.bluexml.xforms.controller.binding.AssociationType;
import com.bluexml.xforms.controller.binding.ClassType;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoControllerException;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
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
		Element element = null;
		DOMUtil.logXML(formNode, true);
		if (formNode instanceof Document) {
			Element docElt = ((Document) formNode).getDocumentElement();
			element = DOMUtil.getChild(docElt, formName);
			if (element == null) {
				// we may be saving from a workflow form
				element = DOMUtil.getFirstElement(docElt); // behavior when workflows didn't exist
				element = DOMUtil.getChild(element, formName);
			}
		}
		if (formNode instanceof Element) {
			element = (Element) formNode;
		}

		VirtualResolver virtualResolver = new VirtualResolver(this);
		virtualResolver.prepareXFormsToAlfresco(element, formName);

		GenericClass result = persistFormElement(transaction, formName, element);
		return result;
	}

	/**
	 * Persist form element.
	 * 
	 * @param transaction
	 *            the login
	 * @param formName
	 *            the form name
	 * @param element
	 *            the element
	 * 
	 * @return the com.bluexml.xforms.controller.alfresco.binding. class
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 * @throws ServletException
	 */
	private GenericClass persistFormElement(AlfrescoTransaction transaction, String formName,
			Element element) throws AlfrescoControllerException, ServletException {
		GenericClass alfClass = alfrescoObjectFactory.createGenericClass();
		alfClass.setAttributes(alfrescoObjectFactory.createGenericAttributes());
		GenericAssociations createAssociations = alfrescoObjectFactory.createGenericAssociations();
		// createAssociations.setAction("replace");
		alfClass.setAssociations(createAssociations);

		FormType formType = getFormType(formName);
		if (formType != null) {
			ClassType classType = getClassType(formType.getRealClass());

			List<Element> children = DOMUtil.getAllChildren(element);
			String elementId = xformsIdToAlfresco(children);

			alfClass.setQualifiedName(classType.getAlfrescoName());

			if (elementId != null) {
				alfClass.setId(elementId);
				collectAssocsToClear(transaction, element, formType, classType, alfClass);
			}

			collectFields(element, formType.getField(), alfClass);
			collectAssocs(transaction, element, formType.getModelChoice(), formType.getReference(),
					alfClass);
		} else {
			// dealing with a Form Workflow
			WorkflowTaskType taskType = getWorkflowTaskType(formName, false);
			collectFields(element, taskType.getField(), alfClass);
			// change the references list if references become supported in FormWorkflow's
			collectAssocs(transaction, element, taskType.getModelChoice(),
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
	 * @param element
	 *            the element
	 * @param formType
	 *            the form type
	 * @param classType
	 *            the class type
	 * @param alfClass
	 *            the alf class
	 */
	// private void collectFields(Element element, FormType formType,
	// GenericClass alfClass) {
	// List<FormFieldType> fields = formType.getField();
	private void collectFields(Element element, List<FormFieldType> fields, GenericClass alfClass) {
		for (FormFieldType formFieldType : fields) {
			Element fieldElement = DOMUtil.getChild(element, formFieldType.getUniqueName());
			if (fieldElement != null) {

				GenericAttribute attribute = alfrescoObjectFactory.createGenericAttribute();
				attribute.setQualifiedName(formFieldType.getAlfrescoName());
				String type = formFieldType.getType();
				String inputTextContent = fieldElement.getTextContent();
				if (formFieldType.isMultiple()) {
					convertXformsAttributeToAlfresco(attribute, inputTextContent, type,
							formFieldType.getStaticEnumType());
				} else {
					String alfrescoValue = null;
					if (isAmendable(type, formFieldType.isReadOnly())) {
						inputTextContent = getReadOnlyDateOrTimeModifiedValue(type,
								inputTextContent);
					}
					if (type.equals("DateTime")) {
						String date;
						String time;
						if (formFieldType.isReadOnly()) {
							date = extractDateFromDateTimeModified(inputTextContent);
							time = extractTimeFromDateTimeModified(inputTextContent);
						} else {
							date = DOMUtil.getChild(fieldElement, "date").getTextContent();
							time = DOMUtil.getChild(fieldElement, "time").getTextContent();
						}
						alfrescoValue = getDateTimeFromDateAndTime(date, time);
					} else if (formFieldType.isSearchEnum()) {
						alfrescoValue = DOMUtil.getChild(fieldElement, "BXDSID").getTextContent();
					} else {
						String rawFieldValue = inputTextContent;
						alfrescoValue = convertXformsAttributeToAlfresco(rawFieldValue, type,
								formFieldType.getStaticEnumType());
					}
					ValueType valueType = alfrescoObjectFactory.createValueType();
					valueType.setValue(alfrescoValue);
					attribute.getValue().add(valueType);
				}

				alfClass.getAttributes().getAttribute().add(attribute);
			}
		}
	}

}
