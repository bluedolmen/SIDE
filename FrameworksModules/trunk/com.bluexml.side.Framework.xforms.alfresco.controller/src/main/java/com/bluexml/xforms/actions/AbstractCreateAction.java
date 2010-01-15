package com.bluexml.xforms.actions;

import org.w3c.dom.Document;

public abstract class AbstractCreateAction extends AbstractWriteAction {

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
		// save status
		navigationPath.peekCurrentPage().setDataId(null);
		navigationPath.peekCurrentPage().setNode((Document) node);
		navigationPath.clearStatusMsg();
		// create new page
		create();
		setSubmissionDefaultLocation(getServletURL(), result);
	}

	protected abstract void create();

}
