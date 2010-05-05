package com.bluexml.xforms.generator.forms.renderable.common.association.selection.multiple;

import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.jdom.Element;

import com.bluexml.xforms.messages.MsgId;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindHolder;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector.RenderableSelector;
import com.bluexml.xforms.generator.forms.rendered.RenderedLine;

/**
 * The Class RenderableSMultiple.
 */
public class RenderableSMultiple extends AbstractRenderable {

	/** The renderable association selection multiple actions add remove. */
	private RenderableSMultipleActionsAddRemove renderableAssoSelMultActionsAddRemove;

	/** The renderable association selection multiple display. */
	private RenderableSMultipleDisplay renderableAssoSelMultDisplay;

	private RenderableSelector selector; // #1156

	private ModelElementBindHolder bindRepeater = null; // #1310
	private ModelElementBindSimple bindActions = null;
	private ModelElementBindSimple bindEdit = null;
	private ModelElementBindSimple bindType = null; // #1510

	/**
	 * Instantiates a new renderable s multiple.
	 * 
	 * @param associationBean
	 *            the association bean
	 * @param selector
	 *            the selector
	 * @param associationClassBean
	 *            the association class bean
	 */
	public RenderableSMultiple(AssociationBean associationBean, RenderableSelector selector) {
		super(associationBean);

		add(selector);
		this.selector = selector;
		if (associationBean.isDisabled() == false) {
			renderableAssoSelMultActionsAddRemove = new RenderableSMultipleActionsAddRemove(
					associationBean, selector);
			add(renderableAssoSelMultActionsAddRemove);
		}
		renderableAssoSelMultDisplay = new RenderableSMultipleDisplay(associationBean);
		add(renderableAssoSelMultDisplay);
		if (associationBean.isDisabled() == false) {
			add(new RenderableSMultipleActionsOrder(associationBean));
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
		ModelElementBindHolder bindRepeater = getBindRepeater();
		ModelElementBindSimple bindActions = getBindActions();

		ModelElementBindSimple bindLabel = new ModelElementBindSimple(MsgId.INT_INSTANCE_SIDELABEL.getText());
		ModelElementBindSimple bindId = new ModelElementBindSimple(MsgId.INT_INSTANCE_SIDEID.getText());
		ModelElementBindSimple bindEdit = getBindEdit();
		bindEdit.setNodeset(MsgId.INT_INSTANCE_SIDEEDIT.getText());
		ModelElementBindSimple bindType = getBindType();
		bindType.setNodeset(MsgId.INT_INSTANCE_SIDETYPE.getText());
		

		bindRepeater.addSubBind(bindLabel);
		bindRepeater.addSubBind(bindId);
		bindRepeater.addSubBind(bindEdit);
		setBindEdit(bindEdit);
		bindRepeater.addSubBind(bindType);
		setBindType(bindType);

		String nodeSetActions = computeNodeSetActions(path);
		String nodeSetItems = computeNodeSetItems(path, nodeSetActions);
		Rendered rendered = new RenderedLine();
		rendered.setOptionalData(XFormsGenerator.getId(bean.getName() + "Repeater"));
		
		Element div = rendered.getXformsElement();
		div.setAttribute("class", MsgId.INT_CSS_SELECT_WIDGET.getText());
		
		// e.g.: Paragraphe1310/field_12/modelcyvel.Video (usual suffix is [position()!=last()])
		bindActions.setNodeset(nodeSetActions);
		bindActions.setRepeaterRootBind(true); // #1241

		bindRepeater.setNodeset(nodeSetItems);
		rendered.addModelElement(bindRepeater);
		rendered.addModelElement(bindActions);

		if (getFormGenerator().isInReadOnlyMode() == false) {
			if (bean.isMandatory()) { // #978
				bindId.setRequired(true);
				bindLabel.setRequired(true);

				int pos = path.length() - 1;
				String pathShortened = path.charAt(pos) == '/' ? path.substring(0, pos) : path;
				String constraint = "instance('minstance')/" + pathShortened + "[1]/"
						+ bindId.getNodeset() + " ne ''";
				if (StringUtils.contains(selector.getBindId().getConstraint(), constraint) == false) {
					selector.getBindId().setConstraint(constraint);
				}
			}
		}
		
		return rendered;
	}
	/**
	 * 
	 * @return the bind holder for the repeated items
	 */
	public ModelElementBindHolder getBindRepeater() {
		if (bindRepeater == null) {
			bindRepeater = new ModelElementBindHolder("");
		}
		return bindRepeater;
	}

	/**
	 * @return the bindActions
	 */
	public ModelElementBindSimple getBindActions() {
		if (bindActions == null) {
			bindActions = new ModelElementBindSimple("");
		}
		return bindActions;
	}

	/**
	 * @return the bindEdit
	 */
	public ModelElementBindSimple getBindEdit() {
		if (bindEdit == null) {
			bindEdit = new ModelElementBindSimple("");
		}
		return bindEdit;
	}

	/**
	 * @param bindEdit the bindEdit to set
	 */
	public void setBindEdit(ModelElementBindSimple bindEdit) {
		this.bindEdit = bindEdit;
	}
	
	/**
	 * @return the bindType
	 */
	public ModelElementBindSimple getBindType() {
		if (bindType == null) {
			bindType = new ModelElementBindSimple("");
		}
		return bindType;
	}
	
	/**
	 * @param bindType the bindType to set
	 */
	public void setBindType(ModelElementBindSimple bindType) {
		this.bindType = bindType;
	}
	
	/* (non-Javadoc)
	 * @see org.blueXML.xforms.generator.forms.Renderable#renderEnd(org.blueXML.xforms.generator.forms.Rendered)
	 */
	@Override
	public void renderEnd(Rendered rendered) {
		super.renderEnd(rendered);
		bindRepeater = null;
		bindActions = null;
		bindEdit = null;
	}

}
