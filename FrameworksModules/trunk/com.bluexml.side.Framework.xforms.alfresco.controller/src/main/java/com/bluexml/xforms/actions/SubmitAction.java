package com.bluexml.xforms.actions;

import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

	/** The logger. */
	protected static Log logger = LogFactory.getLog(SubmitAction.class);

	private String transactionId = null;

	Map<String, String> pageInitParams = null;

	Page currentPage = null;

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
		String submitURL = StringUtils.trimToNull(pageInitParams.get(MsgId.PARAM_PAGE_SUBMIT
				.getText()));

		// if in search mode, a specific processing applies
		if (isSearching) {
			logger.debug("Redirecting after search mode");
			logger.debug(" --> targetURL:'"+ submitURL + "'");
			logger.debug(" --> search string:'"+ transactionId + "'");
			String nextPageURL = submitURL;
			nextPageURL += (submitURL.indexOf('?') == -1) ? "?" : "&";
			nextPageURL += "search=" + transactionId;
			super.redirectClient(nextPageURL);
			return;
		}

		String elementId = transaction.getIds().get(transactionId);
		String extActionResultURL = null;

		// persist data id
		Page currentPage = navigationPath.peekCurrentPage();
		currentPage.setDataId(elementId);
		currentPage.setNode(null);

		// call external action if any
		if (pageInitParams != null) {
			String className = StringUtils.trimToNull(pageInitParams.get(MsgId.PARAM_ACTION_NAME
					.getText()));
			if ((className != null) && !(className.equals("null"))) {
				extActionResultURL = callExternalAction(className);
			}
		}
		if (StringUtils.trimToNull(extActionResultURL) != null) {
			super.redirectClient(extActionResultURL);
		} else {
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
		currentPage = navigationPath.peekCurrentPage();
		FormTypeEnum type = currentPage.getFormType();
		String result = null;
		// persist instance
		if (type == FormTypeEnum.CLASS) {
			result = controller.persistClass(transaction, node, false);
		} else {
			String formName = currentPage.getFormName();
			String datatype = controller.getUnderlyingForm(formName);
			pageInitParams = currentPage.getInitParams();
			String searchStr = StringUtils.trimToNull(pageInitParams.get(MsgId.PARAM_SEARCH_MODE
					.getText()));
			isSearching = StringUtils.equals(searchStr, "true");

			String propStr = StringUtils.trimToNull(pageInitParams
					.get(MsgId.PARAM_SEARCH_USE_SHORT_NAMES.getText()));
			boolean useShortPropertyNames = StringUtils.equals(propStr, "true");

			if (isSearching) {
				result = controller.persistFormJSON(transaction, datatype, node,
						useShortPropertyNames);
			} else {
				result = controller.persistForm(transaction, datatype, node);
			}
		}
		return result;
	}

}
