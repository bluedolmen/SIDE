/**
 * 
 */
package com.bluexml.xforms.actions;

/**
 * Superclass for actions that are called via reader://... URIs.
 * 
 * @author Amenel
 * 
 */
public abstract class AbstractReadAction extends AbstractAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#submit()
	 */
	@Override
	public void submit() throws Exception {
		// nothing to do here in read only actions
	}

}
