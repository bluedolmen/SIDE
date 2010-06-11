package com.bluexml.xforms.generator.forms.renderable.classes;

import java.util.Stack;


import com.bluexml.side.clazz.Association;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationProperties;
import com.bluexml.xforms.generator.forms.renderable.common.CommonRenderableAssociation;

/**
 * The Class RenderableAssociation.
 */
public class RenderableAssociation extends CommonRenderableAssociation {

	/** The association. */
	private Association association;

	/**
	 * Instantiates a new renderable association.
	 * 
	 * @param properties
	 *            the properties
	 * @param association
	 *            the association
	 */
	public RenderableAssociation(AssociationProperties properties, Association association) {
		super(properties);
		this.association = association;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#shouldRender(java.util.Stack)
	 */
	@Override
	protected boolean shouldRender(Stack<Renderable> parents) {
		boolean render = true;
		for (Renderable renderable : parents) {
			if (renderable instanceof RenderableAssociation) {
				RenderableAssociation renderableAssociation = (RenderableAssociation) renderable;
				if (renderableAssociation.association == this.association) {
					return false;
				}
			}
		}
		return render;
	}

}
