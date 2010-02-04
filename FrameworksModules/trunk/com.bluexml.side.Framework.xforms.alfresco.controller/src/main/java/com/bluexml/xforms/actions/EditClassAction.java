package com.bluexml.xforms.actions;

import javax.servlet.ServletException;

import org.w3c.dom.Node;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.navigation.FormTypeEnum;
import com.bluexml.xforms.controller.navigation.PageInfoBean;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;
import com.bluexml.side.form.utils.DOMUtil;

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
	 * @throws ServletException 
	 * 
	 * @throws Exception
	 *             the exception
	 */
	protected void edit() throws ServletException {
		// retrieve id and datatype
		String dataType = requestParameters.get(DATA_TYPE);
		AlfrescoController alfController = AlfrescoController.getInstance();
		String dataId = alfController.getAndResetEditNode(node);

		String realDataType = findRealDataType(controller, dataType, dataId);
		PageInfoBean bean = new PageInfoBean();
		bean.formType = FormTypeEnum.CLASS;
		bean.formName = realDataType;
		bean.dataType = AlfrescoController.getInstance().getDataTypeFromFormName(bean.formName);
		bean.dataId = dataId;
		bean.language = navigationPath.peekCurrentPage().getLanguage();
		navigationPath.setCurrentPage(bean);
	}

	/**
	 * Find real data type.
	 * 
	 * @param login
	 *            the login
	 * @param controller
	 *            the controller
	 * @param dataType
	 *            the data type
	 * @param dataId
	 *            the data id
	 * 
	 * @return the string
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private String findRealDataType(AlfrescoController controller, String dataType, String dataId)
			throws ServletException {
		// FIXME do not get all class with all associations...
		Node node = controller.getClass(transaction, dataType, dataId, null, false, false);
		String realDataType = DOMUtil.getNodeValueByTagName(node, MsgId.INT_INSTANCE_SIDE_DATATYPE
				.getText(), false);
		if (realDataType == null) {
			realDataType = dataType;
		}
		return realDataType;
	}

}
