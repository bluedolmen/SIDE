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
/**
 * 
 */
package com.bluexml.xforms.generator.forms.renderable.forms.search;

import java.util.Stack;

import javax.xml.namespace.QName;

import com.bluexml.side.form.SearchField;
import com.bluexml.xforms.generator.FormGeneratorsManager;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableSearchField;
import com.bluexml.xforms.generator.forms.rendered.search.RenderedSearchOperators;
import com.bluexml.xforms.messages.MsgId;

/**
 * A generic element (rendered as a combobox) for the list of operators of a search field.<br/>
 * The contents of the list of operators is an enumeration shared by all instances of this class
 * that have the same set of operators. The responsibility of managing these enumerations is
 * delegated to {@link FormGeneratorsManager}.
 * 
 * @author Amenel
 * 
 */
public class RenderableSearchOperators extends Renderable {

	RenderableSearchField<? extends SearchField> refField;

	/**
	 * 
	 */
	public RenderableSearchOperators(RenderableSearchField<? extends SearchField> field) {
		refField = field;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#getPath(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return ROOT_RELATIVE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack, boolean)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		String nodeset = MsgId.INT_INSTANCE_SEARCH_OPCODE.getText();

		// NOTE: the path for the bind MUST not be an absolute path: apparently, Chiba does not
		// support xf:select elements that bind to sub-binds. Don't know whether this is part of
		// the XForms specs.
		ModelElementBindSimple opBind = new ModelElementBindSimple(path + nodeset);
		opBind.setType(new QName(MsgId.INT_TYPE_XSD_STRING.getText()));

		String opSetId = getFormGenerator().getSearchOperatorsListId(refField.getFormElement());
		String opFileId = MsgId.INT_PREFIX_FILENAME_OPERATORS + opSetId;
		String opInstanceId = MsgId.INT_PREFIX_INSTANCE_OPERATORS + opSetId;
		RenderedSearchOperators rendered = new RenderedSearchOperators(opBind, refField
				.getFormElement().getId()
				+ "_" + nodeset, opFileId, opInstanceId, refField.getFormElement().getLabel());
		rendered.addModelElement(opBind);
		return rendered;
	}

}
