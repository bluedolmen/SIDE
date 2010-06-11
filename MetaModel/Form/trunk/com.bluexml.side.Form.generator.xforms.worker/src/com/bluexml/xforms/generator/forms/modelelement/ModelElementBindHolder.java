package com.bluexml.xforms.generator.forms.modelelement;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

/**
 * The Class ModelElementBindHolder.
 */
public class ModelElementBindHolder extends ModelElementBindSimple {

	/** The sub binds. */
	private List<ModelElementBindSimple> subBinds = new ArrayList<ModelElementBindSimple>();

	/**
	 * Instantiates a new model element bind holder.
	 * 
	 * @param nodeset
	 *            the nodeset
	 */
	public ModelElementBindHolder(String nodeset) {
		super(nodeset);
	}

	/**
	 * Adds the sub bind.
	 * 
	 * @param bind
	 *            the bind
	 */
	public void addSubBind(ModelElementBindSimple bind) {
		subBinds.add(bind);
	}

	/**
	 * Gets the sub bind.
	 * 
	 * @param i
	 *            the i
	 * 
	 * @return the sub bind
	 */
	public ModelElementBindSimple getSubBind(int i) {
		ModelElementBindSimple result = null;
		if (i < subBinds.size()) {
			result = subBinds.get(i);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple#getModelElement()
	 */
	public Element getModelElement() {
		Element modelElementHolder = super.getModelElement();
		for (ModelElementBindSimple modelElementBindSimple : subBinds) {
			modelElementHolder.addContent(modelElementBindSimple.getModelElement());
		}
		return modelElementHolder;
	}

}
