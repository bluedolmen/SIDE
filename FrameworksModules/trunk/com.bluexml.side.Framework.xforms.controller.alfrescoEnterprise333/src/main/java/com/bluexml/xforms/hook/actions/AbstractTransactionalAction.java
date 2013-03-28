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

	private boolean isSearching = false; // #1465

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
			if (!isSearching()) {
				transaction.executeBatch();

				// all went OK, we may delete the temporary files if any
				deleteUploadedFiles(transaction.getTempFileNames());

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
				deleteUploadedFiles(transaction.getUploadedFileNames());
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
	 * Deletes the uploaded or temporary file(s) depending on the list of file names.
	 * 
	 * @param list
	 *            the list of file names
	 */
	private void deleteUploadedFiles(List<String> list) { // #1278
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

	/**
	 * @param isSearching
	 *            the isSearching to set
	 */
	public void setSearching(boolean isSearching) {
		this.isSearching = isSearching;
	}

	/**
	 * @return the isSearching
	 */
	public boolean isSearching() {
		return isSearching;
	}

}
