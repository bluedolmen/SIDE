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
		pageBean.dataId = dataId;
		pageBean.language = navigationPath.peekCurrentPage().getLanguage();

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
		String classFormName = controller.getDefaultFormForDataType(editBean.getDataType());
		if (classFormName != null) {
			pageBean.formType = FormTypeEnum.CLASS;
			pageBean.formName = classFormName;
			pageBean.dataType = pageBean.dataType;
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
			String formClassName = controller.getUnderlyingClassForForm(formName);
			if (formClassName != null) {
				if (editDataType.equals(formClassName)) {
					pageBean.formType = FormTypeEnum.FORM;
					pageBean.formName = formName;
					pageBean.dataType = formName;
					return true;
				}
			}
		}

		// look for a customized form that supports it and return its name
		String formName = controller.getCustomFormForDataType(editDataType);
		if (formName != null) {
			pageBean.formType = FormTypeEnum.FORM;
			pageBean.formName = formName;
			pageBean.dataType = pageBean.dataType;
			return true;
		}

		return resolvePageInfoClass(pageBean, editBean);
	}
}
