/**
 * 
 */
package com.bluexml.xforms.generator.forms.rendered.search;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;

/**
 * @author Amenel
 *
 */
public class RenderedSearchInput extends Rendered {

	/**
	 * 
	 */
	public RenderedSearchInput(ModelElementBindSimple inputBind, String inputId) {
		Element inputElt = XFormsGenerator.createElement("input", XFormsGenerator.NAMESPACE_XFORMS);
		inputElt.setAttribute("id", XFormsGenerator.getId(inputId));

		inputBind.addLinkedElement(inputElt);
		setXformsElement(inputElt);
	}

	/* (non-Javadoc)
	 * @see com.bluexml.xforms.generator.forms.Rendered#isHolder()
	 */
	@Override
	public boolean isHolder() {
		return false;
	}

}
