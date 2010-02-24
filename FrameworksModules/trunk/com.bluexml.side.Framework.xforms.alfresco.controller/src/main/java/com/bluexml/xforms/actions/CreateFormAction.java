package com.bluexml.xforms.actions;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.navigation.FormTypeEnum;
import com.bluexml.xforms.controller.navigation.PageInfoBean;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

public class CreateFormAction extends AbstractCreateAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {
		return MsgId.INT_ACT_CODE_CREATE_FORM.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractCreateAction#getActionCaption()
	 */
	@Override
	public String getActionCaption() {
		String caption = MsgPool.testMsg(MsgId.CAPTION_BUTTON_CREATE);
		if (caption == null) {
			return super.getActionCaption();
		}
		return caption;
	}

	/**
	 * Creates a new create page.
	 */
	protected void create() {
		// retrieve data type
		String inputDataType = requestParameters.get(MsgId.INT_ACT_PARAM_ANY_DATATYPE.getText());
		// push new page
		PageInfoBean bean = new PageInfoBean();
		bean.formType = FormTypeEnum.FORM;
		bean.formName = inputDataType;
		bean.dataType = AlfrescoController.getInstance().getDataTypeFromFormName(bean.formName);
		bean.language = navigationPath.peekCurrentPage().getLanguage();
		navigationPath.setCurrentPage(bean);
	}

}
