package com.bluexml.xforms.controller.navigation;

import java.util.Stack;

import org.apache.commons.lang.StringUtils;

import com.bluexml.xforms.controller.beans.PageInfoBean;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * The Class NavigationPath.
 */
public class NavigationPath {

	private static final int ITERATION_START = 1;

	/** The stack. */
	private Stack<Page> stack = new Stack<Page>();

	/** The session id. */
	private String sessionId;

	/** The page id. */
	private String pageId;

	private String statusMsg = null;

	/** Must always be > ITERATION_START */
	private int statusIteration = ITERATION_START;

	/**
	 * Creates a new page with the info given, and pushes it on top of the page stack.
	 * 
	 * @param bean
	 *            the page information bean
	 */
	public void setCurrentPage(PageInfoBean bean) {
		assert bean.formType != FormTypeEnum.BOTH;
		Page currentPage = new Page(bean);
		pushPage(currentPage);
	}

	/**
	 * Peek current page.
	 * 
	 * @return the page
	 */
	public Page peekCurrentPage() {
		return stack.peek();
	}

	/**
	 * Pop current page.
	 * 
	 * @return the page
	 */
	public Page popCurrentPage() {
		return stack.pop();
	}

	/**
	 * Push page.
	 * 
	 * @param page
	 *            the page
	 */
	public void pushPage(Page page) {
		stack.push(page);
	}

	/**
	 * Checks if is empty.
	 * 
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return (stack.size() == 0);
	}

	/**
	 * Gets the size.
	 * 
	 * @return the size
	 */
	public int getSize() {
		return stack.size();
	}

	/**
	 * Sets the session id.
	 * 
	 * @param sessionId
	 *            the new session id
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * Sets the page id.
	 * 
	 * @param pageId
	 *            the new page id
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	/**
	 * Gets the session id.
	 * 
	 * @return the session id
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * Gets the page id.
	 * 
	 * @return the page id
	 */
	public String getPageId() {
		return pageId;
	}

	/**
	 * @return the statusMsg
	 */
	public String getStatusMsg() {
		return statusMsg;
	}

	/**
	 * Appends a number to the status message if relevant.
	 * 
	 * @return the statusDisplayedMsg
	 */
	public String getStatusDisplayedMsg() {
		if (statusIteration == ITERATION_START) {
			return this.statusMsg;
		}
		return statusMsg + MsgPool.getMsg(MsgId.MSG_STATUS_ITERATION, "" + statusIteration);
	}

	/**
	 * @param statusMsg
	 *            the status message to set
	 */
	public void setStatusMsg(String statusMsg) {
		String emptyMSG = MsgPool.getMsg(MsgId.MSG_STATUS_EMPTY);

		if ((StringUtils.trimToNull(statusMsg) == null) || StringUtils.equals(emptyMSG, statusMsg)) {
			statusIteration = ITERATION_START;
		} else {
			if (StringUtils.equals(this.statusMsg, statusMsg)) {
				statusIteration++;
			} else {
				statusIteration = ITERATION_START;
			}
		}
		this.statusMsg = statusMsg;
	}

	/**
	 * Reset the status message to the default value.
	 */
	public void clearStatusMsg() {
		setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_EMPTY));
	}

}
