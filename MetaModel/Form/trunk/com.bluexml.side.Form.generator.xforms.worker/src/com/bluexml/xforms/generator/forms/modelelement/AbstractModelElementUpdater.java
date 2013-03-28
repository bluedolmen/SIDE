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

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.xforms.generator.forms.ModelElement;
import com.bluexml.xforms.generator.tools.ModelTools;

public abstract class AbstractModelElementUpdater extends ModelElement {

	/** The type complete name. */
	protected String typeCompleteName;

	/** The linked elements. */
	protected List<Element> linkedElements = new ArrayList<Element>();

	/** The instance name. */
	protected String instanceName;

	/**
	 * Instantiates a new model element list updater.
	 * 
	 * @param typeCompleteName
	 *            the type complete name
	 * @param instanceName
	 *            the instance name
	 */
	public AbstractModelElementUpdater(String typeCompleteName, String instanceName) {
		this.typeCompleteName = typeCompleteName;
		this.instanceName = instanceName;
	}

	public AbstractModelElementUpdater(com.bluexml.side.common.ModelElement target,
			String instanceName) {
		this(ModelTools.getCompleteName(target), instanceName);
		if (target instanceof AbstractClass) {
			this.typeCompleteName = ModelTools.getNamespacePrefix(target) + ":"
					+ getFormGenerator().getClassQualifiedName((AbstractClass) target);
		}
	}

	/**
	 * Adds the linked element.
	 * 
	 * @param linkedElement
	 *            the linked element
	 */
	public void addLinkedElement(Element linkedElement) {
		linkedElements.add(linkedElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.ModelElement#hasClone(java.util.List)
	 */
	@Override
	public boolean hasClone(List<ModelElement> allModelElementsClean) {
		return false;
	}

}
