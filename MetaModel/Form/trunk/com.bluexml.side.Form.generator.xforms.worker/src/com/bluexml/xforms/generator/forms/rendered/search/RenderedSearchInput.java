/**
 * 
 */
package com.bluexml.xforms.generator.forms.rendered.search;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.messages.MsgId;

/**
 * @author Amenel
 *
 */
public class RenderedSearchInput extends Rendered {

	/**
	 * 
	 */
	public RenderedSearchInput(ModelElementBindSimple inputBind, String inputId) {
		xformsElement = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		xformsElement.setAttribute("class", MsgId.INT_CSS_SEARCH_VALUE.getText());

		Element inputElt = XFormsGenerator.createElement("input", XFormsGenerator.NAMESPACE_XFORMS);
		inputElt.setAttribute("id", XFormsGenerator.getId(inputId));

		inputBind.addLinkedElement(inputElt);
		xformsElement.addContent(inputElt);
	}

	/* (non-Javadoc)
	 * @see com.bluexml.xforms.generator.forms.Rendered#isHolder()
	 */
	@Override
	public boolean isHolder() {
		return false;
	}

}
