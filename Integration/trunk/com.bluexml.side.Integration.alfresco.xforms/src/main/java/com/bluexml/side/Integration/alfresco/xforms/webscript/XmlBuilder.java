/**
 * 
 */
package com.bluexml.side.Integration.alfresco.xforms.webscript;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.alfresco.service.namespace.QName;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.bluexml.side.Integration.alfresco.xforms.webscript.AssociationBean.AssoType;

/**
 * Builds an XML representation of an object from its properties and associations.<br/>
 * Taken from BxDS dataLayer module. Stripped down to the necessary.
 * 
 * @author Amenel
 * 
 */
public class XmlBuilder {
	static final String ENTRY_ASSOCIATION_NODE = "association";
	static final String ENTRY_ASSOCIATION_TARGET_NODE = "target";
	static final String ENTRY_ASSOCIATION_TYPE = "type";
	static final String ENTRY_ASSOCIATION_TYPE_SIMPLE = "Simple";
	static final String ENTRY_ASSOCIATION_TYPE_COMPOSITION = "Composition";

	static final String ENTRY_ASSOCIATIONS_NODE = "associations";
	static final String ENTRY_ATTRIBUTE_NODE = "attribute";
	static final String ENTRY_ATTRIBUTE_VALUE = "value";
	static final String ENTRY_ATTRIBUTES_NODE = "attributes";
	static final String ENTRY_ID = "id";
	static final String ENTRY_QUALIFIEDNAME = "qualifiedName";
	static final String ENTRY_ROOTNODE = "Class";

	public static Document buildEntry(QName type, String objectId,
			Map<QName, Serializable> properties, List<AssociationBean> list) {
		// Document entry = new Document();
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			throw new RuntimeException("Couldn't create a document builder.");
		}
		Document doc = documentBuilder.newDocument();

		Element root = doc.createElement(ENTRY_ROOTNODE);
		root.setAttribute(ENTRY_QUALIFIEDNAME, type.getLocalName());
		root.setAttribute(ENTRY_ID, objectId);
		Element attributesE = buildAttributes(doc, properties);
		Element associationsE = buildAssociations(doc, list);
		root.appendChild(attributesE);
		root.appendChild(associationsE);

		doc.appendChild(root);
		return doc;
	}

	@SuppressWarnings("unchecked")
	private static Element buildAttributes(Document doc, Map<QName, Serializable> properties) {
		Element attributesE = doc.createElement(ENTRY_ATTRIBUTES_NODE);
		for (Entry<QName, Serializable> entry : properties.entrySet()) {
			Serializable value = entry.getValue();
			if (value != null) {
				Element att = null;
				if (value instanceof Collection) {
					att = buildAttributeCollection(doc, entry.getKey().getLocalName(),
							(Collection<?>) value);
				} else {
					att = buildAttribute(doc, entry.getKey().getLocalName(), value.toString());
				}
				attributesE.appendChild(att);
			}
		}
		return attributesE;
	}

	private static Element buildAttributeCollection(Document doc, String localName,
			Collection<?> values) {
		Element attribute = doc.createElement(ENTRY_ATTRIBUTE_NODE);
		attribute.setAttribute(ENTRY_QUALIFIEDNAME, localName);
		for (Object value : values) {
			Element valueElement = doc.createElement(ENTRY_ATTRIBUTE_VALUE);
			valueElement.setTextContent(value.toString());
			attribute.appendChild(valueElement);
		}
		return attribute;
	}

	private static Element buildAttribute(Document doc, String attributeName, String attributeValue) {
		Element attribute = doc.createElement(ENTRY_ATTRIBUTE_NODE);
		attribute.setAttribute(ENTRY_QUALIFIEDNAME, attributeName);
		Element value = doc.createElement(ENTRY_ATTRIBUTE_VALUE);
		value.setTextContent(attributeValue);
		attribute.appendChild(value);
		return attribute;
	}

	private static Element buildAssociations(Document doc, List<AssociationBean> list) {
		Element associationsE = doc.createElement(ENTRY_ASSOCIATIONS_NODE);
		for (AssociationBean associationBean : list) {
			Element asso = buildAssociation(doc, associationBean);
			associationsE.appendChild(asso);
		}
		return associationsE;
	}

	private static Element buildAssociation(Document doc, AssociationBean associationBean) {
		// association
		Element association = doc.createElement(ENTRY_ASSOCIATION_NODE);
		association.setAttribute(ENTRY_QUALIFIEDNAME, associationBean.getAssociationName());
		if (associationBean.getAssoType().equals(AssoType.Simple)) {
			association.setAttribute(ENTRY_ASSOCIATION_TYPE, ENTRY_ASSOCIATION_TYPE_SIMPLE);
		} else {
			association.setAttribute(ENTRY_ASSOCIATION_TYPE, ENTRY_ASSOCIATION_TYPE_COMPOSITION);
		}
		// target
		String targetValue = associationBean.getTargetId();
		String targetLabel = associationBean.getTargetLabel();

		String targetNodeType = associationBean.getTargetQualifiedName();
		Element target = doc.createElement(ENTRY_ASSOCIATION_TARGET_NODE);
		target.setAttribute(ENTRY_QUALIFIEDNAME, targetNodeType);
		target.setTextContent(targetValue);
		if (StringUtils.trimToNull(targetLabel) != null) {
			target.setAttribute("label", targetLabel);
		}
		association.appendChild(target);

		return association;
	}

}
