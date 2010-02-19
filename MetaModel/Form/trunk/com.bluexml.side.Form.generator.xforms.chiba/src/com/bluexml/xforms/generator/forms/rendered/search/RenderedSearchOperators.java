/**
 * 
 */
package com.bluexml.xforms.generator.forms.rendered.search;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementEnumeration;
import com.bluexml.xforms.generator.forms.renderable.common.SelectBean;
import com.bluexml.xforms.messages.MsgId;

/**
 * @author Amenel
 * 
 */
public class RenderedSearchOperators extends RenderedSearchField {

	/**
	 * @param opBind
	 *            the bind where this input will put the chosen operation
	 * @param opId
	 *            the id of the select list in the XHTML template
	 * @param opFileId
	 *            the id of a specific set of operators, for the URI that fetches enum items
	 * @param fieldLabel
	 *            the label of the search field as specified in the form model
	 * @param string
	 */
	public RenderedSearchOperators(ModelElementBindSimple opBind, String opId, String opFileId,
			String opInstanceId, String fieldLabel) {

		xformsElement = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		xformsElement.setAttribute("class", MsgId.INT_CSS_SEARCH_OPERATOR.getText());

		// Top element and its attributes
		Element selElt = XFormsGenerator.createElement("select1", XFormsGenerator.NAMESPACE_XFORMS);
		xformsElement.addContent(selElt);
		selElt.setAttribute("id", opId);
		opBind.addLinkedElement(selElt);
		selElt.setAttribute("appearance", "minimal");
		Element label = XFormsGenerator.createElement("label", XFormsGenerator.NAMESPACE_XFORMS);
		String labelText = fieldLabel;
		label.setText(labelText);
		selElt.addContent(label);

		// deal with choice options
		ModelElementEnumeration modelElementEnum = getModelElementEnumeration(opFileId,
				opInstanceId);
		addModelElement(modelElementEnum);

		String enumInstance = modelElementEnum.getEnumInstanceName();
		Element itemset = XFormsGenerator
				.createElement("itemset", XFormsGenerator.NAMESPACE_XFORMS);
		itemset.setAttribute("nodeset", "instance('" + enumInstance + "')/item");
		Element itemLabel = XFormsGenerator
				.createElement("label", XFormsGenerator.NAMESPACE_XFORMS);
		itemLabel.setAttribute("ref", "value");
		itemset.addContent(itemLabel);
		Element itemValue = XFormsGenerator
				.createElement("value", XFormsGenerator.NAMESPACE_XFORMS);
		itemValue.setAttribute("ref", "id");
		itemset.addContent(itemValue);
		selElt.addContent(itemset);
	}

	/**
	 * Provides the model element for fetching the list of operators.
	 * 
	 * @param opFileId
	 *            the id for the enum file that will provide the items in the combobox
	 * @param opInstanceId
	 *            a prefix for the final instance id
	 * 
	 * @return
	 */
	private ModelElementEnumeration getModelElementEnumeration(String opFileId, String opInstanceId) {
		SelectBean selectBean = new SelectBean();

		selectBean.setWorkflowEnum(false);
		selectBean.setEnumeration(null);
		selectBean.setEnumContext(null);
		selectBean.setEnumParent(null);
		selectBean.setLimited(false);
		selectBean.setOperatorType(opFileId);

		return new ModelElementEnumeration(selectBean, opInstanceId + "Instance");
	}
}
