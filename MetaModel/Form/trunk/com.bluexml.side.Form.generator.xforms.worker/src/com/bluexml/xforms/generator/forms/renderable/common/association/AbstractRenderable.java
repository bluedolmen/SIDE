package com.bluexml.xforms.generator.forms.renderable.common.association;

import org.apache.commons.lang.StringUtils;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class AbstractRenderable.
 */
public abstract class AbstractRenderable extends Renderable {

	/** The bean. */
	protected AssociationBean bean;

	/**
	 * Instantiates a new abstract renderable.
	 * 
	 * @param bean
	 *            the bean
	 */
	public AbstractRenderable(AssociationBean bean) {
		super();
		this.bean = bean;
	}

	/**
	 * Compute node set actions.
	 * 
	 * @param path
	 *            the path
	 * 
	 * @return the string
	 */
	protected String computeNodeSetActions(String path) {
		return StringUtils.removeEnd(path, "/");
	}

	/**
	 * Compute node set items.
	 * 
	 * @param nodeSetActions 
	 * 
	 * @return the string
	 */
	protected String computeNodeSetItems(String nodeSetActions) {
		String nodeSetItems = nodeSetActions + "[position()!=last()]";
		return nodeSetItems;
	}

	/**
	 * Returns the appropriate CSS class for the selection widget.
	 */
	protected String getWidgetStyle() {
		String style = MsgId.INT_CSS_SELECT_WIDGET.getText();
		if (bean.isInFeatureFilterMode()) {
			style += " " + MsgId.INT_CSS_SELECT_FEATURE_FILTER;
		}
		if (bean.isInFeatureSearchMode()) {
			style += " " + MsgId.INT_CSS_SELECT_FEATURE_SEARCH;
		}
		return style;
	}


}
