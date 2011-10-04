package com.bluexml.xforms.actions;

import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluexml.xforms.controller.beans.PersistFormResultBean;
import com.bluexml.xforms.controller.navigation.FormTypeEnum;
import com.bluexml.xforms.controller.navigation.NavigationPath;
import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.hook.actions.AbstractTransactionalAction;
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

	Map<String, String> initParams = null;

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
	protected void prepareSubmit() throws ServletException {
		transactionId = submitNode();
	}

	@Override
	protected void afterSubmit() throws ServletException {
		String url = StringUtils.trimToNull(initParams.get(MsgId.PARAM_NEXT_PAGE_SUBMIT.getText()));

		if (url == null) { // #1656
			// there may be something specified in the Xtension property of this form
			url = controller.getXtensionNextPageSubmit(currentPage.getFormName(), currentPage
					.getFormType());
		}

		// if in search mode, a specific processing applies
		if (isSearching()) {
			if (logger.isDebugEnabled()) {
				logger.debug("Redirecting after search mode or search form");
				logger.debug(" --> targetURL:'" + url + "'");
				logger.debug(" --> search string:'" + transactionId + "'");
			}
			if (StringUtils.trimToNull(url) == null) {
				throw new ServletException("No next page was provided for this search.");
			}
			String nextPageURL = url;
			nextPageURL += (url.indexOf('?') == -1) ? "?" : "&";
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
			if (StringUtils.trimToNull(url) != null) {
				url = buildRedirectionUrlWithParams(url, currentPage);
				super.redirectClient(url);
			} else {
				// go to previous page
				restorePrevPage(navigationPath, elementId);
				setSubmissionDefaultLocation(getServletURL(), result);
			}
		}
	}

	/**
	 * Restores the page that launched the current page from which the submission was performed. If
	 * there was no previous page, this corresponds to the reloading of the page in the web client.
	 * Otherwise, the current form is closed and the previous form is brought back in the web client
	 * with its content intact.
	 * 
	 * @param navigationPath
	 *            the navigation path
	 * @param elementId
	 *            the element id
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private void restorePrevPage(NavigationPath navigationPath, String elementId) {
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
	 * @throws ServletException
	 *             the alfresco controller exception
	 * @throws ServletException
	 */
	private String submitNode() throws ServletException {
		currentPage = navigationPath.peekCurrentPage();
		FormTypeEnum type = currentPage.getFormType();
		String formName = currentPage.getFormName();
		initParams = currentPage.getInitParams();
		String result = null;
		String propStr = StringUtils.trimToNull(initParams.get(MsgId.PARAM_SEARCH_USE_SHORT_NAMES
				.getText()));
		boolean shortNames = StringUtils.equals(propStr, "true");

		// persist instance
		if (type == FormTypeEnum.CLASS) {
			PersistFormResultBean resultBean = controller.persistClass(transaction, node, false,
					initParams);
			result = resultBean.getResultStr();
		} else if (type == FormTypeEnum.SEARCH) {
			result = controller.persistSearch(formName, node, shortNames, initParams);
			setSearching(true);
		} else if (type == FormTypeEnum.FORM) {
			PersistFormResultBean resultBean;
			boolean isMassTagging = currentPage.isMassTagging(); // #1421
			resultBean = controller.persistForm(transaction, formName, node, initParams,
					isMassTagging);
			result = resultBean.getResultStr();
		} else {
			String datatype = controller.getUnderlyingDataFormForWorkflow(formName);
			String searchStr = StringUtils.trimToNull(initParams.get(MsgId.PARAM_SEARCH_MODE
					.getText()));
			setSearching(StringUtils.equals(searchStr, "true"));

			if (isSearching()) {
				result = controller.persistFormJSON(transaction, datatype, node, shortNames,
						initParams);
			} else {
				PersistFormResultBean resultBean = controller.persistForm(transaction, datatype,
						node, initParams, false);
				result = resultBean.getResultStr();
			}
		}
		return result;
	}

}
