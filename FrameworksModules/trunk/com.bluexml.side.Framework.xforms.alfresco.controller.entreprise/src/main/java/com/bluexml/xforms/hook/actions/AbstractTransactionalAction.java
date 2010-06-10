package com.bluexml.xforms.hook.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.bluexml.xforms.actions.AbstractWriteAction;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

public abstract class AbstractTransactionalAction extends AbstractWriteAction {

	boolean isSearching = false; // #1465

	protected abstract void prepareSubmit() throws ServletException;

	protected abstract void afterSubmit() throws ServletException;

	@Override
	public void submit() throws ServletException {

		if (controller.isInStandaloneMode()) {
			String msg = "The Alfresco Controller is in standalone mode. Some actions are unavailable";
			navigationPath.setStatusMsg(msg);
			throw new ServletException(msg);
		}

		Page curPage = navigationPath.peekCurrentPage();
		try {
			prepareSubmit();
			if (!isSearching) {
				transaction.executeBatch();

				// all went OK, we may delete the temporary files if any
				deleteUploadedFiles(transaction.getTempFileNames(), true);

				// update the status message
				if (getActionName() == MsgId.INT_ACT_CODE_SUBMIT.getText()) {
					if (curPage.getDataId() == null) { // CREATE
						navigationPath
								.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_CREATE_SUCCESS));
					} else { // UPDATE
						navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_EDIT_SUCCESS));
						deleteUploadedRepo(transaction.getTempNodeIds());
					}
				} else {
					navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_DELETE_SUCCESS));
				}
			}
		} catch (ServletException e) {
			if (getActionName() == MsgId.INT_ACT_CODE_SUBMIT.getText()) {
				deleteUploadedFiles(transaction.getUploadedFileNames(), false);
				if (curPage.getDataId() == null) {
					navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_CREATE_FAILURE));
				} else {
					navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_EDIT_FAILURE));
					deleteUploadedRepo(transaction.getUploadedNodes());
				}
			} else {
				navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_DELETE_FAILURE) + " "
						+ e.toString());
			}
			if (logger.isErrorEnabled()) {
				logger.error(e);
			}
			throw e;
		}
		// redirect the client to the appropriate page
		afterSubmit();
	}

	/**
	 * Deletes the uploaded file(s) that have been stored in the repository.
	 */
	private void deleteUploadedRepo(ArrayList<String> list) {
		if (list == null) {
			return;
		}
		// enqueue all ids in a new transaction
		AlfrescoTransaction delTrans = new AlfrescoTransaction(controller, transaction.getLogin());
		for (String nodeId : list) {
			delTrans.queueDelete(nodeId);
		}

		// run the transaction
		try {
			delTrans.executeBatch();
		} catch (ServletException e) {
			if (logger.isErrorEnabled()) {
				logger.error("Deletion of files uploaded to repository failed.", e);
			}
		}
	}

	/**
	 * Deletes the uploaded or temporary file(s).
	 */
	private void deleteUploadedFiles(List<String> list, boolean isTemporary) { // #1278
		if (list == null) {
			return;
		}
		for (String fileName : list) {
			try {
				File sourceFile = new File(fileName);
				sourceFile.delete();
			} catch (SecurityException io) {
				if (logger.isErrorEnabled()) {
					logger.error(io);
				}
			}
		}
	}

}
