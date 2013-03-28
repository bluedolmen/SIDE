/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
	@Override
	public Element getModelElement() {
		Element modelElementHolder = super.getModelElement();
		for (ModelElementBindSimple modelElementBindSimple : subBinds) {
			modelElementHolder.addContent(modelElementBindSimple.getModelElement());
		}
		return modelElementHolder;
	}

}
