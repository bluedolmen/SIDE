package com.bluexml.xforms.actions;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.navigation.FormTypeEnum;
import com.bluexml.xforms.controller.navigation.PageInfoBean;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * The Class EditFormAction.
 */
public class EditFormAction extends AbstractEditAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {
		return MsgId.INT_ACT_CODE_EDIT_FORM.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractCreateAction#getActionCaption()
	 */
	@Override
	public String getActionCaption() {
		String caption = MsgPool.testMsg(MsgId.CAPTION_BUTTON_EDIT);
		if (caption == null) {
			return super.getActionCaption();
		}
		return caption;
	}

	/**
	 * Edits the.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	protected void edit() throws Exception {
		// retrieve id
		AlfrescoController alfController = AlfrescoController.getInstance();
		String dataId = alfController.getAndResetEditNode(node);

		PageInfoBean bean = new PageInfoBean();
		bean.formType = FormTypeEnum.FORM;
		bean.formName = requestParameters.get(DATA_TYPE);
		bean.dataType = AlfrescoController.getInstance().getDataTypeFromFormName(bean.formName);
		bean.dataId = dataId;
		bean.language = navigationPath.peekCurrentPage().getLanguage();
		navigationPath.setCurrentPage(bean);
	}
}
