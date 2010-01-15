package com.bluexml.xforms.actions;

import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.bluexml.xforms.controller.alfresco.AlfrescoControllerException;
import com.bluexml.xforms.controller.navigation.FormTypeEnum;
import com.bluexml.xforms.controller.navigation.NavigationPath;
import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * The Class SubmitAction.<br>
 * Saves user input
 */
public class SubmitAction extends AbstractTransactionalAction {

	private String transactionId = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionCaption()
	 */
	@Override
	public String getActionCaption() {
		return MsgPool.getMsg(MsgId.CAPTION_BUTTON_SUBMIT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {
		return MsgId.INT_ACT_CODE_SUBMIT.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#isValidateFirst()
	 */
	@Override
	public boolean isValidateFirst() {
		return true;
	}

	@Override
	protected void prepareSubmit() throws Exception {
		transactionId = submitNode();
	}

	@Override
	protected void afterSubmit() throws Exception {
		String elementId = transaction.getIds().get(transactionId);
		String extActionResultURL = null;

		// persist data id
		Page currentPage = navigationPath.peekCurrentPage();
		currentPage.setDataId(elementId);
		currentPage.setNode(null);

		// call external action if any
		Map<String, String> initParams = currentPage.getInitParams();
		if (initParams != null) {
			String className = StringUtils.trimToNull(initParams.get(MsgId.PARAM_ACTION_NAME
					.getText()));
			if ((className != null) && !(className.equals("null"))) {
				extActionResultURL = callExternalAction(className);
			}
		}
		if (StringUtils.trimToNull(extActionResultURL) != null) {
			super.redirectClient(extActionResultURL);
		} else {
			String submitURL = StringUtils.trimToNull(initParams.get(MsgId.PARAM_PAGE_SUBMIT
					.getText()));
			if (StringUtils.trimToNull(submitURL) != null) {
				super.redirectClient(submitURL);
			} else {
				// go to previous page
				restorePrevPage(navigationPath, elementId);
				setSubmissionDefaultLocation(getServletURL(), result);
			}
		}
	}

	/**
	 * Submit.
	 * 
	 * @param navigationPath
	 *            the navigation path
	 * @param elementId
	 *            the element id
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private void restorePrevPage(NavigationPath navigationPath, String elementId) throws Exception {
		Page currentPage = navigationPath.popCurrentPage();
		// previous page by default
		boolean empty = navigationPath.isEmpty();
		if (empty) {
			currentPage.setDataId(elementId);
			navigationPath.pushPage(currentPage);
		}
	}

	/**
	 * Submit node.
	 * 
	 * @return the string
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 * @throws ServletException
	 */
	private String submitNode() throws AlfrescoControllerException, ServletException {
		FormTypeEnum type = navigationPath.peekCurrentPage().getFormType();
		String id = null;
		// persist instance
		if (type == FormTypeEnum.CLASS) {
			id = controller.persistClass(transaction, node);
		} else {
			String formName = navigationPath.peekCurrentPage().getFormName();
			String datatype = controller.getUnderlyingForm(formName);
			id = controller.persistForm(transaction, datatype, node);
		}
		return id;
	}
}
