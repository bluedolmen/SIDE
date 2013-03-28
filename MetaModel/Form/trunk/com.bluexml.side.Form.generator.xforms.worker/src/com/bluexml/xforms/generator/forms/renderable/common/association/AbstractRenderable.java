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
