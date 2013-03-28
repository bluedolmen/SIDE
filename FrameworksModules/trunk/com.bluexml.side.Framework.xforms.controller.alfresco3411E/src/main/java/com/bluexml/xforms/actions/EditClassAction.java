/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.xforms.actions;

import javax.servlet.ServletException;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.beans.EditNodeBean;
import com.bluexml.xforms.controller.beans.PageInfoBean;
import com.bluexml.xforms.controller.navigation.FormTypeEnum;
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
	@Override
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
		bean.setFormType(FormTypeEnum.CLASS);
		bean.setFormName(realDataType);
		bean.setDataType(controller.getDataTypeFromFormName(bean.getFormName()));
		bean.setDataId(dataId);
		bean.setLanguage(navigationPath.peekCurrentPage().getLanguage());
		navigationPath.setCurrentPage(bean);
	}

}
