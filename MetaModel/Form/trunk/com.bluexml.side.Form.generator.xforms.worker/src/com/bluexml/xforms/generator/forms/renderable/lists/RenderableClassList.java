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
