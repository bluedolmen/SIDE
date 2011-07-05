package com.bluexml.side.util.libs.eclipse;


public interface RunnableWithState {

	public abstract STATE getState();

	public enum STATE {
		none, started, finished
	}
}