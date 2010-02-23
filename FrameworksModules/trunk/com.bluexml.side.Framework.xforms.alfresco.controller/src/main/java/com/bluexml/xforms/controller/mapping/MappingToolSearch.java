/**
 * 
 */
package com.bluexml.xforms.controller.mapping;

import java.util.Map;

import javax.servlet.ServletException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
	public String transformSearchForm(String formName, Node instance) throws ServletException {
		SearchFormType sfType = getSearchFormType(formName);
		if (sfType == null) {
			throw new ServletException("The search form '" + formName
					+ "' was not found in the mapping.xml file.");
		}
		// TODO:
		return null;
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

		String realClassName = sfType.getRealClass().getPackage() + "."
				+ sfType.getRealClass().getName();
		ClassType realClassType = getClassType(realClassName);
		for (SearchFieldType fieldType : sfType.getField()) {
			Element topElt = getSearchFieldNode(formInstance, realClassType, fieldType);
			formElt.appendChild(topElt);

		}

		formInstance.appendChild(rootElement);
		return formInstance;
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

		boolean doubleValue = false;
		for (AttributeType attribute : classType.getAttribute()) {
			if (attribute.getName().equals(fieldName)) {
				String attributeType = attribute.getType();
				if (attributeType.equals("date") || numberTypes.indexOf(attributeType) != -1) {
					doubleValue = true;
				}
				break;
			}
		}

		if (doubleValue == false) {
			Element valueElt = doc.createElement(MsgId.INT_INSTANCE_SEARCH_VALUE.getText());
			fieldElt.appendChild(valueElt);
		} else {
			Element valueLoElt = doc.createElement(MsgId.INT_INSTANCE_SEARCH_VALUE_LO.getText());
			fieldElt.appendChild(valueLoElt);
			Element valueHiElt = doc.createElement(MsgId.INT_INSTANCE_SEARCH_VALUE_HI.getText());
			fieldElt.appendChild(valueHiElt);
		}

		return fieldElt;
	}
}
