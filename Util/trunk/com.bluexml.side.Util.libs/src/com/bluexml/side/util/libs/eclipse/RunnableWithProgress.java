package com.bluexml.side.util.libs.eclipse;

import org.eclipse.jface.operation.IRunnableWithProgress;

public abstract class RunnableWithProgress implements IRunnableWithProgress {
	protected STATE current = STATE.none;

	public STATE getState() {
		return current;
	}

	public enum STATE {
		none, started, finished
	}
}
