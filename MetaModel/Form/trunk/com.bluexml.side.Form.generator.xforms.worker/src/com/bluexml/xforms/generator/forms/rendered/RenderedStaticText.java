/**
 * 
 */
package com.bluexml.xforms.generator.forms.rendered;

import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.messages.MsgId;

/**
 * @author Amenel
 *
 */
public class RenderedStaticText extends Rendered {

	/**
	 * 
	 */
	public RenderedStaticText(String text) {
		xformsElement = XFormsGenerator.createElement("span", XFormsGenerator.NAMESPACE_XHTML);
		xformsElement.setAttribute("class", MsgId.INT_CSS_STATIC_TEXT.getText());
		xformsElement.setText(text);
	}

	/* (non-Javadoc)
	 * @see com.bluexml.xforms.generator.forms.Rendered#isHolder()
	 */
	@Override
	public boolean isHolder() {
		return false;
	}

}
