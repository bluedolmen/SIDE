package com.bluexml.side.util.libs.eclipse;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public abstract class WorkspaceModifyOperation extends org.eclipse.ui.actions.WorkspaceModifyOperation implements RunnableWithState {
	protected STATE current = STATE.none;

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.side.util.libs.eclipse.RunnableWithState#getState()
	 */
	public STATE getState() {
		return current;
	}

	protected abstract void start(IProgressMonitor monitor) throws CoreException;

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core
	 * .runtime.IProgressMonitor)
	 */
	@Override
	protected final void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		current = STATE.started;
		start(monitor);
		current = STATE.finished;
	}

}
