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

import com.bluexml.xforms.generator.forms.modelelement.AbstractModelElementUpdater;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.multiple.RenderableSMultiple;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.unique.RenderableSSingle;

/**
 * The Class AbstractRenderableSelectorItem.
 */
public abstract class AbstractRenderableSelectorItem extends AbstractRenderable {

	/** The renderable association selection selector. */
	private RenderableSelector renderableAssociationSelectionSelector;

	/**
	 * Instantiates a new abstract renderable selector item.
	 * 
	 * @param associationBean
	 *            the association bean
	 * @param renderableAssociationSelectionSelector
	 *            the renderable association selection selector
	 */
	public AbstractRenderableSelectorItem(AssociationBean associationBean,
			RenderableSelector renderableAssociationSelectionSelector) {
		super(associationBean);
		this.renderableAssociationSelectionSelector = renderableAssociationSelectionSelector;
	}

	/**
	 * Gets the instance name.
	 * 
	 * @return the instance name
	 */
	public String getInstanceName() {
		return renderableAssociationSelectionSelector.getInstanceName();
	}

	/**
	 * Gets the instance path.
	 * 
	 * @return the instance path
	 */
	public String getInstancePath() {
		return renderableAssociationSelectionSelector.getInstancePath();
	}

	/**
	 * Gets the instance node path.
	 * 
	 * @return the instance node path
	 */
	public String getInstanceNodePath() {
		return renderableAssociationSelectionSelector.getInstanceNodePath();
	}

	/**
	 * Gets the bind id.
	 * 
	 * @return the bind id
	 */
	public ModelElementBindSimple getBindId() {
		return renderableAssociationSelectionSelector.getBindId();
	}

	/**
	 * Gets the bind label.
	 * 
	 * @return the bind label
	 */
	public ModelElementBindSimple getBindLabel() {
		return renderableAssociationSelectionSelector.getBindLabel();
	}

	/**
	 * Gets the bind for data type.
	 * 
	 * @return the bind
	 */
	public ModelElementBindSimple getBindType() {
		return renderableAssociationSelectionSelector.getBindType();
	}

	/**
	 * Gets the bind for data type.
	 * 
	 * @return the bind
	 */
	public ModelElementBindSimple getFieldBind() {
		AbstractRenderable parent = renderableAssociationSelectionSelector.getParent();
		if (bean.isMultiple()) {
			return ((RenderableSMultiple) parent).getBindActions();
		}
		if (parent instanceof RenderableSSingle) {
		return ((RenderableSSingle) parent).getSelectedBindId();
		}
		System.out.println("");
		return null;
	}

	/**
	 * Gets the model element list updater.
	 * 
	 * @return the model element list updater
	 */
	public AbstractModelElementUpdater getModelElementUpdater() {
		return renderableAssociationSelectionSelector.getModelElementUpdater();
	}
}
