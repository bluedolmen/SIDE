package com.bluexml.side.application.ui.action;

import com.bluexml.side.application.ui.Activator;

public class MustBeStopped extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3405681279357620588L;

	public MustBeStopped() {
		super(Activator.Messages.getString("MustBeStopped_0"));
	}
}
