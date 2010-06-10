package com.bluexml.xforms.actions;

import javax.servlet.ServletException;

public abstract class AbstractTransactionalAction extends AbstractWriteAction {

	boolean isSearching = false; // #1465

	protected abstract void prepareSubmit() throws ServletException;

	protected abstract void afterSubmit() throws ServletException;

	@Override
	public void submit() throws ServletException {

		if (controller.isInStandaloneMode()) {
			String msg = "The Alfresco Controller has no write capability.";
			navigationPath.setStatusMsg(msg);
			throw new ServletException(msg);
		}

	}

}
