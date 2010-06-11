package com.bluexml.xforms.generator.forms.rendered;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;

/**
 * The Class RenderedTabContainer.
 */
public class RenderedTabContainer extends Rendered {

	/** The show tabs. */
	private boolean showTabs;

	/**
	 * Instantiates a new rendered tab container.
	 * 
	 * @param divId
	 *            the div id
	 * @param label
	 *            the label
	 * @param showTabs
	 *            the show tabs
	 */
	public RenderedTabContainer(String divId, String label, boolean showTabs) {
		super();
		this.showTabs = showTabs;
		xformsElement = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		if (showTabs) {
			xformsElement.setAttribute("dojoType", "TabContainer");
			xformsElement.setAttribute("doLayout", "false");
		}
		xformsElement.setAttribute("id", XFormsGenerator.getId(divId)); // #1346
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
		return false;
	}

	/**
	 * Checks if is show tabs.
	 * 
	 * @return true, if is show tabs
	 */
	public boolean isShowTabs() {
		return showTabs;
	}

}
