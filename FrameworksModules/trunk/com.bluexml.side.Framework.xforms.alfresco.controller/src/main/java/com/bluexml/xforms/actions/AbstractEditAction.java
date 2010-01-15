package com.bluexml.xforms.actions;

import org.w3c.dom.Document;

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
	public void submit() throws Exception {
		// save form state
		navigationPath.peekCurrentPage().setDataId(null);
		navigationPath.peekCurrentPage().setNode((Document) node);
		navigationPath.clearStatusMsg();
		edit();
		setSubmissionDefaultLocation(getServletURL(), result);
	}

	protected abstract void edit() throws Exception;

}
