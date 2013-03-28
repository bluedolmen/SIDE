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
package com.bluexml.xforms.generator.forms.renderable.common;

import java.util.Stack;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.renderable.common.association.inline.RenderableIMultiple;
import com.bluexml.xforms.generator.forms.renderable.common.association.inline.RenderableIUnique;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.multiple.RenderableSMultiple;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector.RenderableSelector;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.unique.RenderableSSingle;
import com.bluexml.xforms.generator.forms.rendered.RenderedGroup;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class CommonRenderableAssociation. Représente un aiguilleur vers le type adéquat de widget,
 * en fonction des propriétés 'inline' et 'multiple'.
 */
public class CommonRenderableAssociation extends Renderable {
	/** The title. */
	private String title;

	/** The association bean. */
	private AssociationBean associationBean;

	/** The name. */
	private String name;

	/**
	 * Instantiates a new common renderable association.
	 * 
	 * @param properties
	 *            the properties
	 */
	public CommonRenderableAssociation(AssociationProperties properties) {
		super();
		this.name = properties.getUniqueName();
		this.title = properties.getAssocTitle();

		associationBean = new AssociationBean();
		associationBean.setDestinationClass(properties.getDestination());
		associationBean.setName(name);
		associationBean.setTitle(title);
		associationBean.setHint(properties.getHint());
		associationBean.setStyle(properties.getStyle());
		associationBean.setDestinationRenderable(properties.getDestinationRenderable());
		associationBean.setCreateEditFormType(properties.getCreateEditFormType());
		associationBean.setCreateEditForms(properties.getCreateEditFormName());
		associationBean.setCreateEditDefaultFormName(properties.getCreateEditDefaultFormName());
		associationBean.setHiBound(properties.getHiBound());

		// hide or display action buttons
		associationBean.setShowingActions(properties.isShowingActions());
		associationBean.setDisabled(properties.isDisabled());
		associationBean.setMandatory(properties.isMandatory());

		// set maximum number of items to display
		associationBean.setFieldSize(properties.getFieldSize());

		associationBean.setFormatPattern(properties.getFormatPattern());
		associationBean.setLabelLength(properties.getLabelLength());
		associationBean.setForField(properties.isForField());
		associationBean.setOverridingType(properties.getOverridingType());
		associationBean.setIdentifierPropName(properties.getIdentifierPropName());
		associationBean.setFilterAssoc(properties.getFilterAssoc());
		associationBean.setComposition(properties.isComposition());

		associationBean.setWidgetType(properties.getWidgetType());

		// custom configuration parameters
		associationBean.setDataSourceUri(properties.getDataSourceUri());
		associationBean.setFeatureMode(properties.getFeatureMode());
		associationBean.setLuceneQuery(properties.getLuceneQuery());
		associationBean.setNoAutoSearch(properties.isNoAutoSearch());
		associationBean.setNoStatsOutput(properties.isNoStatsOutput());

		if (properties.isInline()) {
			if (properties.isMultiple()) {
				add(new RenderableIMultiple(associationBean));
			} else {
				add(new RenderableIUnique(associationBean, false, false));
			}
		} else {
			RenderableSelector selector = new RenderableSelector(associationBean);
			if (properties.isMultiple()) {
				add(new RenderableSMultiple(associationBean, selector));
			} else {
				add(new RenderableSSingle(associationBean, selector));
			}
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

		String path = name + "/" + MsgId.INT_INSTANCE_ASSOCIATION_ITEM + "/";
		return new Path(PathType.relativePath, path);
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
		String style = associationBean.getStyle(); // #1600
		return new RenderedGroup(null, name, style);
	}

}
