package com.bluexml.xforms.actions;

import org.w3c.dom.Document;

import com.bluexml.xforms.messages.MsgId;

public abstract class AbstractCreateAction extends AbstractWriteAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getParamNames()
	 */
	@Override
	protected String[] getParamNames() {
		return new String[] {
				MsgId.INT_ACT_PARAM_ANY_ASSOC.getText(),
				MsgId.INT_ACT_PARAM_ANY_DATATYPE.getText(),
				MsgId.INT_ACT_PARAM_ANY_HINT.getText() };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#executeSubmit()
	 */
	@Override
	public void submit() {
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
