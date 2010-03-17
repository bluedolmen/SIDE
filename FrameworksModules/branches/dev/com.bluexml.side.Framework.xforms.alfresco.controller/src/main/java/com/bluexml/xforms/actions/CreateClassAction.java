package com.bluexml.xforms.actions;

import com.bluexml.xforms.controller.beans.PageInfoBean;
import com.bluexml.xforms.controller.navigation.FormTypeEnum;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * The Class CreateClassAction.<br>
 * Open a form to create a new instance
 */
public class CreateClassAction extends AbstractCreateAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {
		return MsgId.INT_ACT_CODE_CREATE_CLASS.getText();
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
		String dataType = requestParameters.get(MsgId.INT_ACT_PARAM_ANY_DATATYPE.getText());
		// push new page
		PageInfoBean bean = new PageInfoBean();
		bean.setFormType(FormTypeEnum.CLASS);
		bean.setFormName(dataType);
		bean.setDataType(controller.getDataTypeFromFormName(bean.getFormName()));
		bean.setLanguage(navigationPath.peekCurrentPage().getLanguage());
		navigationPath.setCurrentPage(bean);
	}

}
