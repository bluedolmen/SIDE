package com.bluexml.side.application.ui.action;

public class MustBeStopped extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3405681279357620588L;

	public MustBeStopped() {
		super("Process Stopped user request cancel");
	}
}
