package com.bluexml.xforms.generator.forms.renderable.common.association.selection.unique;

import java.util.Stack;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.RenderableSDisplay;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.RenderableSEdit;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector.RenderableSelector;
import com.bluexml.xforms.generator.forms.rendered.RenderedLine;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class RenderableSSingle.
 */
public class RenderableSSingle extends AbstractRenderable {

	private ModelElementBindSimple selectorBindId; // #1156
	//** #1310
	private ModelElementBindSimple selectedBindId; 
	private ModelElementBindSimple selectedBindLabel;
	private ModelElementBindSimple selectedBindEdit;
	//** #1310

	/**
	 * Instantiates a new renderable s single.
	 * 
	 * @param associationBean
	 *            the association bean
	 * @param selector
	 *            the selector
	 * @param associationClassBean
	 *            the association class bean
	 */
	public RenderableSSingle(AssociationBean associationBean, RenderableSelector selector) {
		super(associationBean);

		add(selector);
		selectorBindId = selector.getBindId(); // #1156

		if (associationBean.isDisabled() == false) {
			add(new RenderableSSingleActions(associationBean, selector.getBindId(), selector
					.getBindLabel(), selector));
		}
		add(new RenderableSDisplay(associationBean));
		if (associationBean.isShowingActions()) {
			add(new RenderableSEdit(associationBean, false));
		}

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
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents, boolean isInIMultRepeater) {
		Rendered rendered = new RenderedLine();
		Element div = rendered.getXformsElement();
		div.setAttribute("class", MsgId.INT_CSS_SELECT_WIDGET.getText());

		ModelElementBindSimple bindLabel = getSelectedBindLabel();
		ModelElementBindSimple bindId = getSelectedBindId();
		ModelElementBindSimple bindEdit = getSelectedBindEdit();

		bindLabel.setNodeset(path + MsgId.INT_INSTANCE_SIDELABEL);
		bindId.setNodeset(path + MsgId.INT_INSTANCE_SIDEID);
		bindEdit.setNodeset(path + MsgId.INT_INSTANCE_SIDEEDIT);

		if (bean.isMandatory()) { // #978
			// no visual cues here, but useful for causing XForms validation to
			// fail if nothing is selected
			bindId.setRequired(true);
			bindLabel.setRequired(true);
			//** #1156
			String constraint = "instance('minstance')/" + bindId.getNodeset() + " ne ''";
			selectorBindId.setConstraint(constraint); 
			//** #1156
		}

		rendered.addModelElement(bindId);
		rendered.addModelElement(bindLabel);
		rendered.addModelElement(bindEdit);

		return rendered;
	}
	/**
	 * @return the selectedBindId
	 */
	public ModelElementBindSimple getSelectedBindId() {
		if (selectedBindId == null) {
			selectedBindId = new ModelElementBindSimple("");
		}
		return selectedBindId;
	}

	/**
	 * @return the selectedBindLabel
	 */
	public ModelElementBindSimple getSelectedBindLabel() {
		if (selectedBindLabel == null) {
			selectedBindLabel = new ModelElementBindSimple("");
		}
		return selectedBindLabel;
	}

	/**
	 * @return the selectedBindEdit
	 */
	public ModelElementBindSimple getSelectedBindEdit() {
		if (selectedBindEdit == null) {
			selectedBindEdit = new ModelElementBindSimple("");
		}
		return selectedBindEdit;
	}

	/**
	 * @param selectedBindEdit the selectedBindEdit to set
	 */
	public void setSelectedBindEdit(ModelElementBindSimple selectedBindEdit) {
		this.selectedBindEdit = selectedBindEdit;
	}

	/* (non-Javadoc)
	 * @see org.blueXML.xforms.generator.forms.Renderable#renderEnd(org.blueXML.xforms.generator.forms.Rendered)
	 */
	@Override
	public void renderEnd(Rendered rendered) {
		super.renderEnd(rendered);
		selectedBindEdit = null;
		selectedBindId = null;
		selectedBindLabel = null;
	}

}
