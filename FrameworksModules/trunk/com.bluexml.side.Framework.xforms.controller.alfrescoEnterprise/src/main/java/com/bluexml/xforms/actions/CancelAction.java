package com.bluexml.xforms.actions;

import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;

import com.bluexml.xforms.controller.beans.GetInstanceFormBean;
import com.bluexml.xforms.controller.navigation.FormTypeEnum;
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
	public void submit() throws ServletException {
		// pop page from stack
		Page currentPage = navigationPath.popCurrentPage();
		Map<String, String> initParams = currentPage.getInitParams();

		String url = StringUtils.trimToNull(initParams.get(MsgId.PARAM_NEXT_PAGE_CANCEL.getText()));

		if (url == null) { // #1656
			// there may be something specified in the Xtension property of this form
			url = controller.getXtensionNextPageCancel(currentPage.getFormName(), currentPage
					.getFormType());
		}

		if (StringUtils.trimToNull(url) != null) {
			String nextPageUrl = buildRedirectionUrlWithParams(url, currentPage);
			super.redirectClient(nextPageUrl); 
		} else {
			navigationPath.clearStatusMsg();
			boolean empty = navigationPath.isEmpty();
			if (empty) {
				// ** #1367; we redo what is normally done in GetAction
				boolean formIsReadOnly = (currentPage.getDataType() != currentPage.getFormName());
				String dataType = currentPage.getDataType();
				String dataId = currentPage.getDataId();
				Document node;
				if (currentPage.getFormType() == FormTypeEnum.FORM) {
					GetInstanceFormBean bean = new GetInstanceFormBean(dataType, dataId,
							formIsReadOnly, false, null);
					node = controller.getInstanceForm(transaction, bean);
				} else {
					node = controller.getInstanceClass(transaction, dataType, dataId,
							formIsReadOnly, false);
				}
				currentPage.setNode(node);
				// ** #1367
				navigationPath.pushPage(currentPage);
			}
			setSubmissionDefaultLocation(getServletURL(), result);
		}
	}

}
