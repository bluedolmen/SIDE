package com.bluexml.xforms.generator.forms.rendered;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.ModelElement;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindHolder;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;

/**
 * The Class RenderedRepeater.
 */
public class RenderedRepeater extends Rendered {

	/** The parent bind. */
	private ModelElementBindHolder parentBind;

	/** The repeat id. */
	private String repeatId;

	/**
	 * Instantiates a new rendered repeater.
	 * 
	 * @param parentBind
	 *            the parent bind
	 * @param repeatId
	 *            the repeat id
	 */
	public RenderedRepeater(ModelElementBindHolder parentBind, String repeatId) {
		Element repElt = XFormsGenerator.createElement("repeat", XFormsGenerator.NAMESPACE_XFORMS);
		repElt.setAttribute("id", repeatId);
		parentBind.addLinkedElement(repElt);
		repElt.setAttribute("appearance", "full");

		xformsElement = repElt;
		this.parentBind = parentBind;
		this.repeatId = repeatId;
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
		Element renderedElement = rendered.getXformsElement();
		if (renderedElement != null) {
			xformsElement.addContent(renderedElement);
		}

		for (ModelElement me : rendered.getModelElements()) {
			if (me instanceof ModelElementBindSimple) {
				ModelElementBindSimple meb = (ModelElementBindSimple) me;
				if (!meb.getNodeset().startsWith("instance(")) {
					parentBind.addSubBind(meb);
				} else {
					addModelElement(me);
				}
			} else {
				addModelElement(me);
			}
		}
		getChildren().add(rendered);
	}

	/**
	 * Gets the node set.
	 * 
	 * @return the node set
	 */
	public String getNodeSet() {
		return parentBind.getNodeset();
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

	/**
	 * Gets the repeat id.
	 * 
	 * @return the repeat id
	 */
	public String getRepeatId() {
		return repeatId;
	}

}
