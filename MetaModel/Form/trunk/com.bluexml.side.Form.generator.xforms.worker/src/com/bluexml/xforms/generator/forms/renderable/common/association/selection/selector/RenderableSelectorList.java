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
package com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector;

import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.rendered.RenderedInput;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

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

		// compute the right type of UI control
		String xfControl = "select1";
		if ((bean.isItemSelector() == false) && bean.isMultiple()) {
			xfControl = "select";
		}
		Element select = XFormsGenerator.createElement(xfControl, XFormsGenerator.NAMESPACE_XFORMS);

		// attributes
		String selectId = XFormsGenerator.getId("select");
		select.setAttribute("id", selectId);
		if (bean.isItemSelector()) {
			getBindId().addLinkedElement(select);
		} else {
			// direct connection to the field's node in the form's XML instance
			try {
				ModelElementBindSimple fieldBind = getFieldBind();
				fieldBind.addLinkedElement(select);
				// we need to set the mandatory status and the constraint
				if (bean.isMandatory()) { // #978
					fieldBind.setRequired(true);
				}
			} catch (Exception e) {
				System.out.println("Something");
			}
		}
		select.setAttribute("appearance", "compact");

		// field label
		Element label = XFormsGenerator.createElement("label", XFormsGenerator.NAMESPACE_XFORMS);
		label.setText(MsgPool.getMsg(MsgId.MSG_FIELD_LABEL_FORMAT, bean.getTitle()));
		select.addContent(label);

		// the action, not applicable for the simple select widget
		if (bean.isItemSelector()) {
			Element action = XFormsGenerator.createElement("action",
					XFormsGenerator.NAMESPACE_XFORMS);
			action.setAttribute("event", "xforms-value-changed", XFormsGenerator.NAMESPACE_EVENTS);

			// label
			Element setvaluelabel = XFormsGenerator.createElement("setvalue",
					XFormsGenerator.NAMESPACE_XFORMS);
			// Example target text: "instance('ComBluexmlDemoRhContratDeTravailList')/item[id =
			// instance('ComBluexmlDemoRhContratDeTravailList')/SELECTEDID]/value"
			setvaluelabel.setAttribute("value", getInstancePath() + MsgId.INT_INSTANCE_SELECT_ITEM
					+ "[" + MsgId.INT_INSTANCE_SELECT_ID + " = " + getInstancePath()
					+ MsgId.INT_INSTANCE_SELECTEDID + "]/" + MsgId.INT_INSTANCE_SELECT_LABEL);
			getBindLabel().addLinkedElement(setvaluelabel);
			action.addContent(setvaluelabel);

			// data type
			Element setvaluetype = XFormsGenerator.createElement("setvalue",
					XFormsGenerator.NAMESPACE_XFORMS);
			// Example target text: "instance('ComBluexmlDemoRhContratDeTravailList')/item[id =
			// instance('ComBluexmlDemoRhContratDeTravailList')/SELECTEDID]/qname"
			setvaluetype.setAttribute("value", getInstancePath() + MsgId.INT_INSTANCE_SELECT_ITEM
					+ "[" + MsgId.INT_INSTANCE_SELECT_ID + " = " + getInstancePath()
					+ MsgId.INT_INSTANCE_SELECTEDID + "]/" + MsgId.INT_INSTANCE_SELECT_TYPE);
			getBindType().addLinkedElement(setvaluetype);
			action.addContent(setvaluetype);

			select.addContent(action);
		}

		// the itemset
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

			// we set hint/help only when a hint message is specified
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
