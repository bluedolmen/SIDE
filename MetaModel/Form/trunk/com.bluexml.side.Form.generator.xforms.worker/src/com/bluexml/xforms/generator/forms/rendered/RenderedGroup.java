package com.bluexml.xforms.generator.forms.rendered;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;

/**
 * The Class RenderedGroup.
 */
public class RenderedGroup extends Rendered {

	/**
	 * Instantiates a new rendered group.
	 * 
	 * @param label
	 *            the label
	 * @param id
	 *            the id
	 * @param style TODO
	 */
	public RenderedGroup(String label, String id, String style) {
		super();
		xformsElement = XFormsGenerator.createXFormsGroup(label, style);
		xformsElement.setAttribute("id", XFormsGenerator.getId(id));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Rendered#isHolder()
	 */
	@Override
	public boolean isHolder() {
		return true;
	}

}
