package com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector;

import com.bluexml.xforms.generator.forms.modelelement.AbstractModelElementUpdater;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;

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
	 * Gets the model element list updater.
	 * 
	 * @return the model element list updater
	 */
	public AbstractModelElementUpdater getModelElementUpdater() {
		return renderableAssociationSelectionSelector.getModelElementUpdater();
	}
}
