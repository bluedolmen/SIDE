package com.bluexml.xforms.actions;

import javax.servlet.ServletException;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.EditNodeBean;
import com.bluexml.xforms.controller.navigation.FormTypeEnum;
import com.bluexml.xforms.controller.navigation.PageInfoBean;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * The Class EditAction.<br>
 * Edit a node by its id
 */
public class EditClassAction extends AbstractEditAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {
		return MsgId.INT_ACT_CODE_EDIT_CLASS.getText();
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
	 * @throws ServletException
	 * 
	 * @throws Exception
	 *             the exception
	 */
	protected void edit() throws ServletException {
		// retrieve id and datatype
		String dataType = requestParameters.get(MsgId.INT_ACT_PARAM_ANY_DATATYPE.getText());
		AlfrescoController alfController = AlfrescoController.getInstance();
		EditNodeBean editBean = alfController.getEditNodeAndReset(node);
		String dataId = editBean.getDataId();

		String realDataType = findRealDataType(controller, dataType, dataId);
		if (realDataType == null) {
			throw new ServletException("");
		}
		PageInfoBean bean = new PageInfoBean();
		bean.formType = FormTypeEnum.CLASS;
		bean.formName = realDataType;
		bean.dataType = AlfrescoController.getInstance().getDataTypeFromFormName(bean.formName);
		bean.dataId = dataId;
		bean.language = navigationPath.peekCurrentPage().getLanguage();
		navigationPath.setCurrentPage(bean);
	}

}
