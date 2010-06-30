package com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector;

import java.util.Stack;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.rendered.RenderedInput;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * The search input in the selection widgets. Consists of the label, the input and, possibly, the
 * 'launch search' button.
 */
public class RenderableSelectorSearcher extends AbstractRenderableSelectorItem {

	/**
	 * Instantiates a new renderable selector searcher.
	 * 
	 * @param associationBean
	 *            the association bean
	 * @param renderableAssociationSelectionSelector
	 *            the renderable association selection selector
	 */
	public RenderableSelectorSearcher(AssociationBean associationBean,
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
		RenderedInput rendered = new RenderedInput();
		boolean usingAutoSearch = (bean.isNoAutoSearch() == false);

		Element input = XFormsGenerator.createElement("input", XFormsGenerator.NAMESPACE_XFORMS);

		Element label = XFormsGenerator.createElement("label", XFormsGenerator.NAMESPACE_XFORMS);
		if (bean.isInFeatureFilterMode()) {
			label.setText(MsgPool.getMsg(MsgId.MSG_SELECT_LIST_FILTER_LABEL));
		}
		if (bean.isInFeatureSearchMode()) {
			label.setText(MsgPool.getMsg(MsgId.MSG_SELECT_LIST_SEARCH_LABEL));
		}
		input.addContent(label);
		input.setAttribute("id", XFormsGenerator.getId("searchInput"));
		input.setAttribute("incremental", "true");
		input.setAttribute("ref", getInstancePath() + "query/query");
		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);
		action.setAttribute("event", "xforms-value-changed", XFormsGenerator.NAMESPACE_EVENTS);
		Element setvalue = XFormsGenerator.createElement("setvalue",
				XFormsGenerator.NAMESPACE_XFORMS);
		setvalue.setAttribute("ref", getInstancePath() + "query/maxResults");
		setvalue.setAttribute("value", getInstancePath() + "SELECTMAX");
		action.setAttribute("if", "string-length(.) > 2"); // start searching at 3 characters
		action.addContent(setvalue);

		Element send = XFormsGenerator.createElement("send", XFormsGenerator.NAMESPACE_XFORMS);
		getModelElementUpdater().addLinkedElement(send);

		Element trigger = XFormsGenerator.createTriggerWithLabelImage(XFormsGenerator.IMG_SEARCH);

		if (usingAutoSearch) {
			action.addContent(send);
		} else {
			Element actionTrigger = XFormsGenerator.createElement("action",
					XFormsGenerator.NAMESPACE_XFORMS);

			actionTrigger.setAttribute("event", "DOMActivate", XFormsGenerator.NAMESPACE_EVENTS);
			actionTrigger.addContent(send);
			trigger.addContent(actionTrigger);
		}

		input.addContent(action);

		Element div = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		div.setAttribute("class", MsgId.INT_CSS_SELECT_SEARCH_ZONE.getText());

		if (!usingAutoSearch) {
			div.addContent(trigger);

			// set style in order to keep the search button on the same horizontal line as the input
			trigger.setAttribute("class", "xformstdright");
		}
		input.setAttribute("class", "xformstdright");
		div.addContent(input);

		// need this to align the filter on the right side of the containing div
		Element divLineBreaker = XFormsGenerator.createElement("div",
				XFormsGenerator.NAMESPACE_XHTML);
		divLineBreaker.setAttribute("class", "xformstdclear");
		div.addContent(divLineBreaker);

		rendered.setXformsElement(div);
		return rendered;
	}

}
