/**
 * 
 */
package com.bluexml.xforms.generator.forms.rendered;

import com.bluexml.xforms.messages.MsgId;

import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;

/**
 * @author Amenel
 * 
 */
public class RenderedHR extends Rendered {

	public RenderedHR() {
		super();
		xformsElement = XFormsGenerator.createElement("hr", XFormsGenerator.NAMESPACE_XHTML);
		xformsElement.setAttribute("class", MsgId.INT_CSS_HORIZ_LINE.getText());
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
