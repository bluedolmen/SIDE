package com.bluexml.xforms.actions;

import org.w3c.dom.Node;

/**
 * Superclass for actions that are called via <tt>writerscheme://...</tt> URIs. Write actions may
 * replace one instance or all. In practice, at least one instance is replaced. Examples: the action
 * for the Submit button, or the action for editing an item from selection widgets.
 * 
 * @author Amenel
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
