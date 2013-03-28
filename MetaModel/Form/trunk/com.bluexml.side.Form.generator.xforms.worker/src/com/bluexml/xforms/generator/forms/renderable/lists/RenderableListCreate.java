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
package com.bluexml.xforms.generator.forms.renderable.lists;

import java.util.Stack;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementSubmission;
import com.bluexml.xforms.generator.forms.renderable.common.RenderableSubmit;
import com.bluexml.xforms.generator.forms.rendered.RenderedParentGroup;
import com.bluexml.xforms.generator.tools.ModelTools;
import com.bluexml.xforms.messages.MsgId;

public class RenderableListCreate extends Renderable {

	/** The submission create. */
	private ModelElementSubmission submissionCreate;
	private RenderableClassList renderableClassList;

	public RenderableListCreate(RenderableClassList renderableClassList) {
		super();
		this.renderableClassList = renderableClassList;
		submissionCreate = new ModelElementSubmission("", "", true, false);
		RenderableSubmit createButton = new RenderableSubmit(submissionCreate, "Créer", true);
		add(createButton);
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return ROOT_RELATIVE;
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		RenderedParentGroup renderedParentGroup = new RenderedParentGroup(renderedParents);
		// String action = MsgId.INT_URI_SCHEME_WRITER + "create//"
		// + ModelTools.getCompleteName(renderableClassList.getClasse());
		String action = MsgId.INT_URI_SCHEME_WRITER + "create?" + MsgId.INT_ACT_PARAM_ANY_ASSOC
				+ "=" + "&" + MsgId.INT_ACT_PARAM_ANY_DATATYPE + "="
				+ ModelTools.getCompleteName(renderableClassList.getClasse());
		submissionCreate.setAction(action);
		renderedParentGroup.getParent().addModelElement(submissionCreate);
		return renderedParentGroup;
	}

}
