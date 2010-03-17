package com.bluexml.xforms.actions;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * The Class CancelAction.<br>
 * Cancel form and return to previous
 */
public class CancelAction extends AbstractWriteAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {
		return MsgId.INT_ACT_CODE_CANCEL.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionCaption()
	 */
	@Override
	public String getActionCaption() {
		return MsgPool.getMsg(MsgId.CAPTION_BUTTON_CANCEL.getText());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#executeSubmit()
	 */
	@Override
	public void submit() {
		// pop page from stack
		Page currentPage = navigationPath.popCurrentPage();
		Map<String, String> initParams = currentPage.getInitParams();

		String url = StringUtils.trimToNull(initParams.get(MsgId.PARAM_PAGE_CANCEL.getText()));
		if (StringUtils.trimToNull(url) != null) {
			super.redirectClient(url);
		} else {
			navigationPath.clearStatusMsg();
			boolean empty = navigationPath.isEmpty();
			if (empty) {
				navigationPath.pushPage(currentPage);
			}
			setSubmissionDefaultLocation(getServletURL(), result);
		}
	}

}
