/**
 * 
 */
package com.bluexml.xforms.generator.forms.rendered.search;

import org.jdom.Element;

import com.bluexml.side.clazz.Enumeration;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementEnumeration;
import com.bluexml.xforms.generator.forms.renderable.common.SelectBean;
import com.bluexml.xforms.generator.tools.ModelTools;
import com.bluexml.xforms.messages.MsgId;

/**
 * @author Amenel
 * 
 */
public class RenderedSearchSelectInput extends Rendered {

	/**
	 * @param selId
	 *            the id of the select list in the XHTML template
	 * 
	 */
	public RenderedSearchSelectInput(ModelElementBindSimple inputBind, String selId,
			Enumeration valueList) {
		xformsElement = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		xformsElement.setAttribute("class", MsgId.INT_CSS_SEARCH_OPERATOR.getText());

		// Top element and its attributes
		Element selElt = XFormsGenerator.createElement("select", XFormsGenerator.NAMESPACE_XFORMS);
		xformsElement.addContent(selElt);
		selElt.setAttribute("id", selId);
		inputBind.addLinkedElement(selElt);
		selElt.setAttribute("appearance", "compact");

		// deal with the item set (choice options)
		ModelElementEnumeration modelElementEnum = getModelElementEnumeration(valueList);
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
	 * Provides the model element for fetching the list of enumeration literals.
	 * 
	 * @param valueList
	 *            should always be non-null. The modeler should have checked the presence of a value
	 *            list before adding a ChoiceSearchField.
	 * @return
	 */
	private ModelElementEnumeration getModelElementEnumeration(Enumeration valueList) {
		SelectBean selectBean = new SelectBean();

		selectBean.setWorkflowEnum(false);
		selectBean.setEnumeration(null);
		selectBean.setEnumContext(null);
		selectBean.setEnumParent(null);
		selectBean.setLimited(false);
		selectBean.setEnumeration(valueList);

		String enumInstance = ModelTools.toJAXB(ModelTools.getCompleteName(valueList)) + "Instance";

		return new ModelElementEnumeration(selectBean, enumInstance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Rendered#isHolder()
	 */
	@Override
	public boolean isHolder() {
		return false;
	}

}
