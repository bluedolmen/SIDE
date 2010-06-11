package com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector;

import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;
import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.rendered.RenderedInput;

/**
 * The Class RenderableSelectorList.
 */
public class RenderableSelectorList extends AbstractRenderableSelectorItem {

	/**
	 * Instantiates a new renderable selector list.
	 * 
	 * @param associationBean
	 *            the association bean
	 * @param renderableAssociationSelectionSelector
	 *            the renderable association selection selector
	 */
	public RenderableSelectorList(AssociationBean associationBean,
			RenderableSelector renderableAssociationSelectionSelector) {
		super(associationBean, renderableAssociationSelectionSelector);
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
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		RenderedInput renderedInput = new RenderedInput();

		Element select = XFormsGenerator.createElement("select1", XFormsGenerator.NAMESPACE_XFORMS);
		String selectId = XFormsGenerator.getId("select");
		select.setAttribute("id", selectId);
		getBindId().addLinkedElement(select);

		Element label = XFormsGenerator.createElement("label", XFormsGenerator.NAMESPACE_XFORMS);
		label.setText(MsgPool.getMsg(MsgId.MSG_FIELD_LABEL_FORMAT, bean.getTitle()));
		select.addContent(label);

		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);
		action.setAttribute("event", "xforms-value-changed", XFormsGenerator.NAMESPACE_EVENTS);

		// label
		Element setvaluelabel = XFormsGenerator.createElement("setvalue",
				XFormsGenerator.NAMESPACE_XFORMS);
		// Target text: "instance('ComBluexmlDemoRhContratDeTravailList')/item[id =
		// instance('ComBluexmlDemoRhContratDeTravailList')/SELECTEDID]/value"
		setvaluelabel.setAttribute("value", getInstancePath() + MsgId.INT_INSTANCE_SELECT_ITEM
				+ "[" + MsgId.INT_INSTANCE_SELECT_ID + " = " + getInstancePath()
				+ MsgId.INT_INSTANCE_SELECTEDID + "]/" + MsgId.INT_INSTANCE_SELECT_LABEL);
		getBindLabel().addLinkedElement(setvaluelabel);
		action.addContent(setvaluelabel);

		// data type
		Element setvaluetype = XFormsGenerator.createElement("setvalue",
				XFormsGenerator.NAMESPACE_XFORMS);
		// Target text: "instance('ComBluexmlDemoRhContratDeTravailList')/item[id =
		// instance('ComBluexmlDemoRhContratDeTravailList')/SELECTEDID]/qname"
		setvaluetype.setAttribute("value", getInstancePath() + MsgId.INT_INSTANCE_SELECT_ITEM + "["
				+ MsgId.INT_INSTANCE_SELECT_ID + " = " + getInstancePath()
				+ MsgId.INT_INSTANCE_SELECTEDID + "]/" + MsgId.INT_INSTANCE_SELECT_TYPE);
		getBindType().addLinkedElement(setvaluetype);
		action.addContent(setvaluetype);

		select.addContent(action);

		select.setAttribute("appearance", "compact");

		Element itemset = XFormsGenerator
				.createElement("itemset", XFormsGenerator.NAMESPACE_XFORMS);
		itemset.setAttribute("nodeset", getInstanceNodePath());
		Element labelItem = XFormsGenerator
				.createElement("label", XFormsGenerator.NAMESPACE_XFORMS);
		labelItem.setAttribute("ref", MsgId.INT_INSTANCE_SELECT_LABEL.getText());
		itemset.addContent(labelItem);
		Element value = XFormsGenerator.createElement("value", XFormsGenerator.NAMESPACE_XFORMS);
		value.setAttribute("ref", MsgId.INT_INSTANCE_SELECT_ID.getText());
		itemset.addContent(value);
		select.addContent(itemset);

		if (bean.isMandatory()) {
			// set the hint/help message
			String hintMsg = bean.getHint();
			// we set hint/help only when a hint message is specified and when
			// the related messages in the properties file are not both empty
			if (StringUtils.trimToNull(hintMsg) != null) {
				Element hintElt = XFormsGenerator.createElement("hint",
						XFormsGenerator.NAMESPACE_XFORMS);
				hintElt.setText(hintMsg);
				select.addContent(hintElt);
				// FIXME: as long as a specific property for help doesn't exist, we
				// duplicate the hint message into help
				Element helpElt = XFormsGenerator.createElement("help",
						XFormsGenerator.NAMESPACE_XFORMS);
				helpElt.setText(hintMsg);
				select.addContent(helpElt);
			}
			String msg = MsgPool.getMsg(MsgId.MSG_ASSOC_MANDATORY);
			if (StringUtils.trimToNull(msg) != null) {
				String errMsg = "";
				errMsg = MsgPool.getMsg(MsgId.MSG_ASSOC_MANDATORY, bean.getTitle());
				Element alertElement = XFormsGenerator.createElement("alert",
						XFormsGenerator.NAMESPACE_XFORMS);
				alertElement.setText(errMsg);
				select.addContent(alertElement);
			}
		}

		renderedInput.setXformsElement(select);

		return renderedInput;
	}
}
