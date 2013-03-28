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

import java.util.Arrays;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.beans.EditNodeBean;
import com.bluexml.xforms.controller.navigation.FormTypeEnum;
import com.bluexml.xforms.controller.beans.PageInfoBean;
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
	 * Edits the item that's active among the items selected on a selection widget.
	 * 
	 * @throws ServletException
	 */
	@Override
	protected void edit() throws ServletException {
		// retrieve id
		AlfrescoController alfController = AlfrescoController.getInstance();
		EditNodeBean editBean = alfController.getEditNodeAndReset(node);
		if (editBean == null) {
			throw new ServletException("No edit id found in the form instance.");
		}
		String dataId = editBean.getDataId();

		PageInfoBean pageBean = new PageInfoBean();
		String targetForms = requestParameters.get(MsgId.INT_ACT_PARAM_ANY_DATATYPE.getText());
		// String formTypeHint = requestParameters.get(MsgId.INT_ACT_PARAM_ANY_HINT.getText()); 
		// FIXME: do we need formTypeHint ?
		pageBean.setDataId(dataId);
		pageBean.setLanguage(navigationPath.peekCurrentPage().getLanguage());

		// set the form type, form name and data type
		resolvePageInfo(pageBean, editBean, targetForms);

		navigationPath.setCurrentPage(pageBean);
	}

	/**
	 * Finds the appropriate form for the object being edited and sets some page info.
	 * 
	 * @param pageBean
	 * @param editBean
	 * @param targetForms
	 *            the comma separated list of target forms, initially added in the modeler and
	 *            written in the XHTML template
	 * @throws ServletException
	 */
	private void resolvePageInfo(PageInfoBean pageBean, EditNodeBean editBean, String targetForms)
			throws ServletException {
		String[] forms = StringUtils.split(targetForms, ',');
		boolean found = false;
		if (forms == null) {
			found = resolvePageInfoClass(pageBean, editBean);
		} else {
			found = resolvePageInfoForm(pageBean, editBean, forms);
		}
		if (found == false) {
			throw new ServletException("No generated form able to edit this object was found.");
		}
	}

	/**
	 * 
	 * @param pageBean
	 * @param editBean
	 * @return true if a default form was found
	 */
	private boolean resolvePageInfoClass(PageInfoBean pageBean, EditNodeBean editBean) {
		// look for a default form that supports the data type and return its name
		String classFormName = controller.getDefaultFormForDatatype(editBean.getDataType());
		if (classFormName != null) {
			pageBean.setFormType(FormTypeEnum.CLASS);
			pageBean.setFormName(classFormName);
			pageBean.setDataType(pageBean.getFormName());
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param pageBean
	 * @param editBean
	 * @param forms
	 * @return true if a customized form was found
	 */
	private boolean resolvePageInfoForm(PageInfoBean pageBean, EditNodeBean editBean, String[] forms) {
		String editDataType = editBean.getDataType();

		// look for the form in the list of target forms that supports the edit node's data type
		for (String formName : Arrays.asList(forms)) {
			String formClassName = controller.getUnderlyingTypeForForm(formName);
			if (formClassName != null) {
				if (editDataType.equals(formClassName)) {
					pageBean.setFormType(FormTypeEnum.FORM);
					pageBean.setFormName(formName);
					pageBean.setDataType(formName);
					return true;
				}
			}
		}

		// look for a customized form that supports it and return its name
		String formName = controller.getCustomFormForDatatype(editDataType);
		if (formName != null) {
			pageBean.setFormType(FormTypeEnum.FORM);
			pageBean.setFormName(formName);
			pageBean.setDataType(pageBean.getFormName());
			return true;
		}

		return resolvePageInfoClass(pageBean, editBean);
	}
}
