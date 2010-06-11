package com.bluexml.xforms.generator.forms.rendered;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;

/**
 * The Class RenderedTab.
 */
public class RenderedTab extends Rendered {

	/**
	 * Instantiates a new rendered tab.
	 * 
	 * @param title
	 *            the title
	 * @param showTab
	 *            the show tab
	 */
	public RenderedTab(String title, boolean showTab) {
		if (showTab) {
			xformsElement = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
			xformsElement.setAttribute("dojoType", "ContentPane");
			xformsElement.setAttribute("label", title);
		} else {
			xformsElement = XFormsGenerator.createElementWithLabel("group",
					XFormsGenerator.NAMESPACE_XFORMS, title);
		}
		xformsElement.setAttribute("style", "height: 100%");
		xformsElement.setAttribute("id", XFormsGenerator.getId("contentPaneTab"));
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
			xformsElement.addContent(renderedElement);
		}
	}

}
