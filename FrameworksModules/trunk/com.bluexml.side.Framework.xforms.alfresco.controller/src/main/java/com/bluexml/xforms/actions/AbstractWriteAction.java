package com.bluexml.xforms.actions;

import org.w3c.dom.Node;

/**
 * The Class AbstractWriteAction.<br>
 * Inner action extends this class, as those actions replace all page. Superclass for actions that
 * are called via writer://... URIs.
 */
public abstract class AbstractWriteAction extends AbstractAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#resolve()
	 */
	@Override
	public Node resolve() {
		// This does not get called for write actions.
		return null;
	}

}
