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
