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

import com.bluexml.side.clazz.Clazz;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindHolder;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementInstanceList;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementUpdaterList;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.rendered.RenderedGroup;
import com.bluexml.xforms.generator.tools.ModelTools;
import com.bluexml.xforms.messages.MsgId;

public class RenderableClassList extends Renderable {

	private Clazz classe;
	private ModelElementUpdaterList modelElementListUpdater;
	private ModelElementBindHolder modelElementBindHolder;
	private ModelElementBindSimple bindLabel;
	private AssociationBean bean;

	public RenderableClassList(Clazz classe) {
		super();
		this.classe = classe;
		bean = new AssociationBean();
		
		modelElementListUpdater = new ModelElementUpdaterList(classe, "instanceList", bean);
		modelElementBindHolder = new ModelElementBindHolder("instance('instanceList')/"
				+ MsgId.INT_INSTANCE_SELECT_ITEM);
		bindLabel = new ModelElementBindSimple(MsgId.INT_INSTANCE_SELECT_LABEL.getText());
		modelElementBindHolder.addSubBind(bindLabel);

		add(new RenderableListSearcher(this));
		add(new RenderableList(this));
		add(new RenderableListCreate(this));

		bean.setDestinationClass(classe);
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return ROOT_RELATIVE;
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		RenderedGroup renderedGroup = new RenderedGroup("Liste des " + ModelTools.getTitle(classe),
				"list", null);
		renderedGroup.addModelElement(modelElementListUpdater);
		renderedGroup.addModelElement(modelElementBindHolder);

		renderedGroup.addModelElement(new ModelElementInstanceList(bean, "instanceList"));
		return renderedGroup;
	}

	public Clazz getClasse() {
		return classe;
	}

	public ModelElementUpdaterList getModelElementListUpdater() {
		return modelElementListUpdater;
	}

	public ModelElementBindHolder getModelElementBindHolder() {
		return modelElementBindHolder;
	}

	public ModelElementBindSimple getBindLabel() {
		return bindLabel;
	}

}
