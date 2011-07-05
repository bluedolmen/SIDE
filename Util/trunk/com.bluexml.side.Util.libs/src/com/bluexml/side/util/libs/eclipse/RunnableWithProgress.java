package com.bluexml.side.util.libs.eclipse;

import org.eclipse.jface.operation.IRunnableWithProgress;

public abstract class RunnableWithProgress implements IRunnableWithProgress, RunnableWithState {
	protected STATE current = STATE.none;

	/* (non-Javadoc)
	 * @see com.bluexml.side.util.libs.eclipse.RunnableWithState#getState()
	 */
	public STATE getState() {
		return current;
	}
	
}
