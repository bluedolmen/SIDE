/**
 * 
 */
package com.bluexml.xforms.controller.mapping;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.bluexml.xforms.messages.MsgId;

/**
 * Provides the XForms instance document for a search form and deals with the instance once it is
 * filled by the XForms engine.
 * 
 * @author Amenel
 * 
 */
public class MappingToolSearch extends MappingToolCommon {

	/** The logger. */
	protected static Log logger = LogFactory.getLog(MappingToolSearch.class);

	/**
	 * @param mapping
	 * @param controller
	 */
	public MappingToolSearch(Mapping mapping, AlfrescoController controller) {
		super(mapping, controller);
	}

	/**
	 * Builds the XForms instance document for a search form.
	 * 
	 * @param formName
	 * @return
	 */
	public Document getInstanceSearch(String formName) {
		Document formInstance = documentBuilder.newDocument();
		Element rootElement = formInstance.createElement("root");
		SearchFormType sfType = getSearchFormType(formName);

		Element formElt = formInstance.createElement(sfType.getName());
		rootElement.appendChild(formElt);

		for (SearchFieldType fieldType : sfType.getField()) {
			Element topElt = getSearchFieldNode(formInstance, fieldType);
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
	 * @param fieldType
	 * @return
	 */
	private Element getSearchFieldNode(Document doc, SearchFieldType fieldType) {
		String fieldName = fieldType.getName();
		Element fieldElt = doc.createElement(fieldName);

		Element opElt = doc.createElement(MsgId.INT_INSTANCE_SEARCH_OPCODE.getText());
		fieldElt.appendChild(opElt);
		opElt.setTextContent(fieldType.getPick()); // set the default operator

		String dataType = fieldType.getType();
		boolean isTyped = (StringUtils.trimToNull(dataType) != null);
		if (isTyped && dataType.equalsIgnoreCase("datetime")) {
			dataType = "Date";
		}

		if (hasSingleInput(fieldType) == true) {
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
	 * Builds a JSON string for the search options provided by the XForms engine in the node.
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
			if (logger.isErrorEnabled()) {
				logger.error("No search form found with id '" + formName + "'");
			}
			throw new ServletException("The search form '" + formName
					+ "' was not found in the mapping.xml file.");
		}
		Element rootElt = getRootElement(formName, instance);

		StringBuffer buf = new StringBuffer(1024);
		String propName = "";
		String typeName = "";

		ClassType classType = resolveClassType(sfType);
		String packageName = classType.getPackage();
		String rootPackage = packageName;
		int pos = packageName.indexOf('.');
		if (pos != -1) {
			rootPackage = packageName.substring(0, pos);
		}
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
								+ "' was not found! Since this should never happen, the mapping file is most obviously not correct.");
				throw new ServletException("The processing of this search form could not complete.");
			}

			Element fieldElt = DOMUtil.getChild(rootElt, fieldName);
			Element opElt = DOMUtil.getChild(fieldElt, MsgId.INT_INSTANCE_SEARCH_OPCODE.getText());
			String operator = opElt.getTextContent();

			if (StringUtils.trimToNull(operator) == null) {
				continue;
			}
			if (operator.equals(MsgId.INT_SEARCH_OPERATOR_IGNORE.getText()) == false) {
				if (first == false) {
					buf.append(",");
				}

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

				buf.append("type:\"");
				String type;
				if (isEnumerated(fieldType)) {
					type = MsgId.INT_SEARCH_JSON_TYPE_ENUMS.getText();
				} else {
					type = refAttribute.getType();
				}
				buf.append(type);
				buf.append("\"");

				buf.append(",operator:\"");
				buf.append(operator);
				buf.append("\"");

				buf.append(",values:["); // open values
				if (hasSingleInput(fieldType)) {
					Element valElt = DOMUtil.getChild(fieldElt, MsgId.INT_INSTANCE_SEARCH_VALUE
							.getText());
					String value = valElt.getTextContent();
					if (isEnumerated(fieldType)) {
						value = convertSelectInputValuesToSearchFormat(value, fieldType.getEnum());
					}
					buf.append("\"");
					buf.append(value);
					buf.append("\"");
				} else {
					Element valEltLo = DOMUtil.getChild(fieldElt,
							MsgId.INT_INSTANCE_SEARCH_VALUE_LO.getText());
					String valueLo = valEltLo.getTextContent();
					buf.append("\"");
					buf.append(valueLo);
					buf.append("\"");
					Element valEltHi = DOMUtil.getChild(fieldElt,
							MsgId.INT_INSTANCE_SEARCH_VALUE_HI.getText());
					String valueHi = valEltHi.getTextContent();
					buf.append(",\"");
					buf.append(valueHi);
					buf.append("\"");
				}
				buf.append("]"); // close values
				buf.append("}"); // close field
				first = false;
			}
		}
		buf.append("}"); // close fields

		buf.append("}"); // close the JSON string

		return buf.toString();
	}

	/**
	 * Converts the text content of a node bound to a multiple select into the appropriate format.<br/>
	 * The XForms separator when multiple values are selected is a space. Since we use sequential
	 * numbers as 'values' (XForms terminology) there is no risk that spaces inside the labels of
	 * items interfere with the correct handling of the user's selections.
	 * <p/>
	 * Sample list items:<br/>
	 * <table>
	 * <th>
	 * <tr>
	 * <td>id (enum file terminology) / value (XForms term.)|</td>
	 * <td>|value (enum file) / label (XForms)</td>
	 * </tr>
	 * </th>
	 * <tr>
	 * <td>1</td>
	 * <td>Canada</td>
	 * </tr>
	 * <tr>
	 * <td>2</td>
	 * <td>England</td>
	 * </tr>
	 * <tr>
	 * <td>3</td>
	 * <td>France</td>
	 * </tr>
	 * <tr>
	 * <td>4</td>
	 * <td>New Zealand</td>
	 * </tr>
	 * <tr>
	 * <td>5</td>
	 * <td>United States of America</td>
	 * </tr>
	 * </table>
	 * 
	 * @param value
	 *            the text content of the node, as provided by the XForms engine. A sample content
	 *            is "1 3 4" to indicate that the first, third and fourth items were selected.
	 * @param enumName
	 *            the enumeration as saved in the mapping file
	 * @return the comma-separated list of selected values or empty string if none selected. e.g.
	 *         "Canada,France,New&nbsp;Zealand"
	 */
	private String convertSelectInputValuesToSearchFormat(String value, String enumName) {
		String convertedValue = "";
		boolean first = true;
		final String XFORMS_MULTIPLE_SELECT_SEPARATOR = " ";

		// we split the input value into individual tokens and process each
		String[] tokens = value.split(XFORMS_MULTIPLE_SELECT_SEPARATOR);
		for (String token : tokens) {
			String aValue = convertXformsAttributeToAlfresco(token, "string", enumName);
			if (first == false) {
				convertedValue += "\",\"";
			}
			convertedValue += aValue;
			first = false;
		}
		return convertedValue;
	}

	/**
	 * Tells whether the search field has a single UI input control.
	 * 
	 * @param fieldType
	 * @return
	 */
	private boolean hasSingleInput(SearchFieldType fieldType) {
		String inputs = fieldType.getInputs();
		boolean singleInput = ((StringUtils.trimToNull(inputs) == null) || (StringUtils.equals(
				inputs, "1")));
		return singleInput;
	}

	/**
	 * Tells whether the search field is mapped to a multiple select input control, which happens
	 * for string attributes that have an enumeration.
	 * 
	 * @param fieldType
	 * @return
	 */
	private boolean isEnumerated(SearchFieldType fieldType) {
		String enumName = fieldType.getEnum();
		boolean isEnum = (StringUtils.trimToNull(enumName) != null);
		return isEnum;
	}

}
