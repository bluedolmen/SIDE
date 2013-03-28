/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
		xformsElement.setAttribute("class", MsgId.INT_CSS_SEARCH_VALUE.getText());

		// Top element and its attributes
		Element selElt = XFormsGenerator.createElement("select", XFormsGenerator.NAMESPACE_XFORMS);
		xformsElement.addContent(selElt);
		selElt.setAttribute("id", selId);
		inputBind.addLinkedElement(selElt);
		selElt.setAttribute("appearance", "compact"); // despite "compact", this is an enumeration!

		// deal with the item set (choice options)
		ModelElementEnumeration modelElementEnum = getModelElementEnumeration(valueList);
		addModelElement(modelElementEnum);

		String enumInstance = modelElementEnum.getEnumInstanceName();
		Element itemset = XFormsGenerator
				.createElement("itemset", XFormsGenerator.NAMESPACE_XFORMS);
		itemset.setAttribute("nodeset", "instance('" + enumInstance + "')/item");
		Element itemLabel = XFormsGenerator
				.createElement("label", XFormsGenerator.NAMESPACE_XFORMS);
		itemLabel.setAttribute("ref", MsgId.INT_INSTANCE_ENUM_VALUE.getText());
		itemset.addContent(itemLabel);
		Element itemValue = XFormsGenerator
				.createElement("value", XFormsGenerator.NAMESPACE_XFORMS);
		itemValue.setAttribute("ref", MsgId.INT_INSTANCE_ENUM_ID.getText());
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
