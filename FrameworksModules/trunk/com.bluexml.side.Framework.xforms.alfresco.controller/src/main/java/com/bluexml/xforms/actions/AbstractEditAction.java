package com.bluexml.xforms.actions;

import javax.servlet.ServletException;

import org.w3c.dom.Document;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;

public abstract class AbstractEditAction extends AbstractWriteAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getParamNames()
	 */
	@Override
	protected String[] getParamNames() {
		return new String[] { DATA_ASSOC, DATA_TYPE };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#executeSubmit()
	 */
	@Override
	public void submit() throws ServletException {
		if (AlfrescoController.isStandaloneMode()) {
			String msg = "The Alfresco Controller is in standalone mode. Some actions are unavailable";
			navigationPath.setStatusMsg(msg);
			throw new ServletException(msg);
		}

		// save form state
		navigationPath.peekCurrentPage().setDataId(null);
		navigationPath.peekCurrentPage().setNode((Document) node);
		navigationPath.clearStatusMsg();
		edit();
		setSubmissionDefaultLocation(getServletURL(), result);
	}

	protected abstract void edit() throws ServletException;

}
