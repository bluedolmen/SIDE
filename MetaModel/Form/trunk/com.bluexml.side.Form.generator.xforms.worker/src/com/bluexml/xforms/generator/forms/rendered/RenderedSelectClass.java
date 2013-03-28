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
package com.bluexml.xforms.generator.forms.rendered;

import java.util.List;

import javax.xml.namespace.QName;

import org.jdom.Element;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.tools.ModelTools;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class RenderedSelectClass.
 */
public class RenderedSelectClass extends Rendered {

	/** The choices. */
	private Element choices;

	/**
	 * Instantiates a new rendered select class.
	 * 
	 * @param path
	 *            the path
	 * @param classesList
	 *            the classes list
	 */
	public RenderedSelectClass(String path, List<Clazz> classesList) {
		super();
		xformsElement = XFormsGenerator.createElementWithLabel("select1",
				XFormsGenerator.NAMESPACE_XFORMS, "Type");
		choices = XFormsGenerator.createElement("choices", XFormsGenerator.NAMESPACE_XFORMS);

		ModelElementBindSimple meb = new ModelElementBindSimple(path
				+ MsgId.INT_INSTANCE_SIDE_DATATYPE.getText());
		meb.setType(new QName("string"));

		meb.addLinkedElement(xformsElement);
		xformsElement.addContent(choices);

		addModelElement(meb);

		addCases(classesList);
	}

	/**
	 * Adds the cases.
	 * 
	 * @param classesList
	 *            the classes list
	 */
	public void addCases(List<Clazz> classesList) {
		for (Clazz classe : classesList) {
			Element classItem = XFormsGenerator.createElement(MsgId.INT_INSTANCE_SELECT_ITEM
					.getText(), XFormsGenerator.NAMESPACE_XFORMS);

			Element label = XFormsGenerator.createElementWithContent("label",
					XFormsGenerator.NAMESPACE_XFORMS, ModelTools.getTitle(classe));
			classItem.addContent(label);

			Element value = XFormsGenerator.createElementWithContent("value",
					XFormsGenerator.NAMESPACE_XFORMS, ModelTools.getCompleteName(classe));
			classItem.addContent(value);

			choices.addContent(classItem);
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

}
