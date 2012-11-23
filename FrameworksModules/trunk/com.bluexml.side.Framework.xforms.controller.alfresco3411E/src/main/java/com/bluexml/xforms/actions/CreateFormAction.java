package com.bluexml.xforms.actions;

import org.apache.commons.lang.StringUtils;

import com.bluexml.xforms.controller.beans.PageInfoBean;
import com.bluexml.xforms.controller.navigation.FormTypeEnum;
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
	@Override
	protected void create() {
		// retrieve data type
		String inputDataType = requestParameters.get(MsgId.INT_ACT_PARAM_ANY_DATATYPE.getText());
		String formTypeHint = requestParameters.get(MsgId.INT_ACT_PARAM_ANY_HINT.getText());
		// push new page
		PageInfoBean bean = new PageInfoBean();
		if (StringUtils.equals(formTypeHint, MsgId.INT_ACT_SUFFIX_GET_FORM_CLASS.getText())) {
			bean.setFormType(FormTypeEnum.CLASS);
		} else {
			bean.setFormType(FormTypeEnum.FORM);
		}
		bean.setFormName(inputDataType);
		bean.setDataType(controller.getDataTypeFromFormName(bean.getFormName()));
		bean.setLanguage(navigationPath.peekCurrentPage().getLanguage());
		navigationPath.setCurrentPage(bean);
	}

}