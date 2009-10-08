package com.bluexml.xforms.generator.forms.rendered;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;

/**
 * The Class RenderedLine.
 */
public class RenderedLine extends Rendered {

	/**
	 * Instantiates a new rendered line.
	 */
	public RenderedLine() {
		xformsElement = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Rendered#isHolder()
	 */
	@Override
	public boolean isHolder() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.xforms.generator.forms.Rendered#addRendered(com.bluexml.xforms.generator.forms
	 * .Rendered, com.bluexml.xforms.generator.forms.Renderable)
	 */
	@Override
	public void addRendered(Rendered rendered, Renderable renderable) {
		super.addRendered(rendered, renderable);
		Element renderedElement = rendered.getXformsElement();
		if (renderedElement != null) {
			Element lineElement = XFormsGenerator.createElement("div",
					XFormsGenerator.NAMESPACE_XHTML);
			if (rendered.isReturnToLine()) {
				lineElement.setAttribute("class", "xformstdclear");
			} else {
				lineElement.setAttribute("class", "xformstdleft");
			}
			lineElement.addContent(renderedElement);
			xformsElement.addContent(lineElement);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.xforms.generator.forms.Rendered#renderEnd(com.bluexml.xforms.generator.forms.
	 * Renderable)
	 */
	@Override
	public void renderEnd(Renderable renderable) {
		super.renderEnd(renderable);
		Element lineElement = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		lineElement.setAttribute("class", "xformstdclear");
		xformsElement.addContent(lineElement);
	}

}
