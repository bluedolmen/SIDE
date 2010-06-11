package com.bluexml.xforms.hook.actions;

import javax.servlet.ServletException;

import com.bluexml.xforms.actions.AbstractWriteAction;

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

	/**
	 * @return the isSearching
	 */
	public boolean isSearching() {
		return isSearching;
	}

	/**
	 * @param isSearching the isSearching to set
	 */
	public void setSearching(boolean isSearching) {
		this.isSearching = isSearching;
	}

}
