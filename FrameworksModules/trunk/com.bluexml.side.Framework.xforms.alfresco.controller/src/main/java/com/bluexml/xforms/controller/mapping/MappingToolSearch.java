/**
 * 
 */
package com.bluexml.xforms.controller.mapping;

import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.binding.AttributeType;
import com.bluexml.xforms.controller.binding.ClassType;
import com.bluexml.xforms.controller.binding.Mapping;
import com.bluexml.xforms.controller.binding.SearchFieldType;
import com.bluexml.xforms.controller.binding.SearchFormType;
import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.messages.MsgId;

/**
 * @author Amenel
 * 
 */
public class MappingToolSearch extends MappingToolCommon {

	/**
	 * @param mapping
	 * @param controller
	 */
	public MappingToolSearch(Mapping mapping, AlfrescoController controller) {
		super(mapping, controller);
	}

	/**
	 * Builds a JSON string for the search options in the node.
	 * 
	 * @param formName
	 * @param instance
	 * @return
	 * @throws ServletException
	 */
	public String transformSearchForm(String formName, Node instance, boolean shortPropertyNames)
			throws ServletException {
		SearchFormType sfType = getSearchFormType(formName);
		if (sfType == null) {
			throw new ServletException("The search form '" + formName
					+ "' was not found in the mapping.xml file.");
		}
		Element rootElt = getRootElement(formName, instance);

		StringBuffer buf = new StringBuffer(1024);
		String propName = "";
		String typeName = "";

		ClassType classType = resolveClassType(sfType);
		String packageName = classType.getPackage();
		String rootPackage = packageName.substring(0, packageName.indexOf('.'));
		String namespace = MsgId.INT_NAMESPACE_BLUEXML_CLASS + "/" + rootPackage + "/1.0";

		buf.append("{"); // open JSON string

		// the title
		buf.append("type:\"");
		typeName = "{" + namespace + "}" + classType.getAlfrescoName();
		buf.append(typeName);
		buf.append("\"");

		// the combination operator
		buf.append(",operator:\"");
		buf.append(sfType.getOperator());
		buf.append("\"");

		// the properties
		buf.append(",fields:{"); // open fields
		boolean first = true;
		for (SearchFieldType fieldType : sfType.getField()) {
			if (first == false) {
				buf.append(",");
			}

			// find the corresponding attribute
			AttributeType refAttribute = null;
			String fieldName = fieldType.getName();
			for (AttributeType attribute : classType.getAttribute()) {
				if (attribute.getName().equals(fieldName)) {
					refAttribute = attribute;
					break;
				}
			}
			if (refAttribute == null) {
				logger
						.error("The attribute for search field '"
								+ fieldName
								+ "' was not found! This should never happen. The mapping file is not correct.");
				throw new ServletException("The processing of this search form could not complete.");
			}
			first = false;
			propName = "{";
			propName += namespace;
			propName += "}";
			propName += refAttribute.getAlfrescoName();
			if (shortPropertyNames) {
				propName = StringUtils.replace(propName, typeName, "");
			}
			buf.append("\"");
			buf.append(propName);
			buf.append("\":");
			buf.append("{"); // open field

			Element fieldElt = DOMUtil.getChild(rootElt, fieldName);
			buf.append("type:\"");
			buf.append(refAttribute.getType());
			buf.append("\"");

			buf.append(",operator:\"");
			Element opElt = DOMUtil.getChild(fieldElt, MsgId.INT_INSTANCE_SEARCH_OPCODE.getText());
			String operator = opElt.getTextContent();
			buf.append(operator);
			buf.append("\"");

			buf.append(",values:{"); // open values
			if (isSingleValued(fieldType)) {
				Element valElt = DOMUtil.getChild(fieldElt, MsgId.INT_INSTANCE_SEARCH_VALUE
						.getText());
				String value = valElt.getTextContent();
				buf.append("\"");
				buf.append(value);
				buf.append("\"");
			} else {
				Element valEltLo = DOMUtil.getChild(fieldElt, MsgId.INT_INSTANCE_SEARCH_VALUE_LO
						.getText());
				String valueLo = valEltLo.getTextContent();
				buf.append("\"");
				buf.append(valueLo);
				buf.append("\"");
				Element valEltHi = DOMUtil.getChild(fieldElt, MsgId.INT_INSTANCE_SEARCH_VALUE_HI
						.getText());
				String valueHi = valEltHi.getTextContent();
				buf.append(",\"");
				buf.append(valueHi);
				buf.append("\"");
			}
			buf.append("}"); // close values
			buf.append("}"); // close field
		}
		buf.append("}"); // close fields

		buf.append("}"); // close the JSON string

		return buf.toString();
	}

	/**
	 * Builds the XForms instance document for a search form.
	 * 
	 * @param currentPage
	 * @param formName
	 * @param initParams
	 * @return
	 */
	public Document getInstanceSearch(Page currentPage, String formName,
			Map<String, String> initParams) {
		Document formInstance = documentBuilder.newDocument();
		Element rootElement = formInstance.createElement("root");
		SearchFormType sfType = getSearchFormType(formName);

		Element formElt = formInstance.createElement(sfType.getName());
		rootElement.appendChild(formElt);

		ClassType realClassType = resolveClassType(sfType);
		for (SearchFieldType fieldType : sfType.getField()) {
			Element topElt = getSearchFieldNode(formInstance, realClassType, fieldType);
			formElt.appendChild(topElt);

		}

		formInstance.appendChild(rootElement);
		return formInstance;
	}

	/**
	 * Returns the full class definition (with attributes) for the given search form's real class.
	 * 
	 * @param sfType
	 * @return
	 */
	private ClassType resolveClassType(SearchFormType sfType) {
		String realClassName = sfType.getRealClass().getPackage() + "."
				+ sfType.getRealClass().getName();
		ClassType realClassType = getClassType(realClassName);
		return realClassType;
	}

	/**
	 * Creates and initializes the DOM section for a field.
	 * 
	 * @param doc
	 * @param classType
	 * @param fieldType
	 * @return
	 */
	private Element getSearchFieldNode(Document doc, ClassType classType, SearchFieldType fieldType) {
		String fieldName = fieldType.getName();
		Element fieldElt = doc.createElement(fieldName);

		Element opElt = doc.createElement(MsgId.INT_INSTANCE_SEARCH_OPCODE.getText());
		fieldElt.appendChild(opElt);
		opElt.setTextContent(fieldType.getPick()); // set the default operator

		String dataType = fieldType.getType();
		boolean isTyped = (StringUtils.trimToNull(dataType) != null);

		if (isSingleValued(fieldType) == true) {
			Element valueElt = doc.createElement(MsgId.INT_INSTANCE_SEARCH_VALUE.getText());
			if (isTyped) {
				valueElt.setTextContent(createXFormsInitialValue(dataType, null, null));
			}
			fieldElt.appendChild(valueElt);
		} else {
			// when more than two inputs become supported, test for the number of inputs
			Element valueLoElt = doc.createElement(MsgId.INT_INSTANCE_SEARCH_VALUE_LO.getText());
			if (isTyped) {
				valueLoElt.setTextContent(createXFormsInitialValue(dataType, null, null));
			}
			fieldElt.appendChild(valueLoElt);
			Element valueHiElt = doc.createElement(MsgId.INT_INSTANCE_SEARCH_VALUE_HI.getText());
			if (isTyped) {
				valueHiElt.setTextContent(createXFormsInitialValue(dataType, null, null));
			}
			fieldElt.appendChild(valueHiElt);
		}

		return fieldElt;
	}

	/**
	 * Tells whether the search field has a single UI input control.
	 * 
	 * @param fieldType
	 * @return
	 */
	private boolean isSingleValued(SearchFieldType fieldType) {
		String inputs = fieldType.getInputs();
		boolean singleValue = (StringUtils.trimToNull(inputs) == null);
		return singleValue;
	}

}
