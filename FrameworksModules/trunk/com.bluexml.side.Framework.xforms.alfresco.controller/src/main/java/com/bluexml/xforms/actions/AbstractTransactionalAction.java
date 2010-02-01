package com.bluexml.xforms.actions;

import java.io.File;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.io.FileUtils;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

public abstract class AbstractTransactionalAction extends AbstractWriteAction {

	boolean isSearching = false; // #1465

	protected abstract void prepareSubmit() throws Exception;

	protected abstract void afterSubmit() throws Exception;

	@Override
	public void submit() throws Exception {

		if (AlfrescoController.isStandaloneMode()) {
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
					if (curPage.getDataId() == null) {
						navigationPath
								.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_CREATE_SUCCESS));
					} else {
						navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_EDIT_SUCCESS));
					}
				} else {
					navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_DELETE_SUCCESS));
				}
			}
		} catch (Exception e) {
			if (getActionName() == MsgId.INT_ACT_CODE_SUBMIT.getText()) {
				deleteUploadedFiles(transaction.getUploadedFileNames(), false);
				if (curPage.getDataId() == null) {
					navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_CREATE_FAILURE));
				} else {
					navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_EDIT_FAILURE));
				}
			} else {
				navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_DELETE_FAILURE) + " "
						+ e.toString());
			}
			logger.error(e);
			throw e;
		}
		// redirect the client to the appropriate page
		afterSubmit();
	}

	/**
	 * Deletes the uploaded or temporary file.
	 */
	private void deleteUploadedFiles(List<String> list, boolean isTemporary) { // #1278
		if (list == null) {
			return;
		}
		for (String fileName : list) {
			try {
				File sourceFile = new File(fileName);
				File parent = sourceFile.getParentFile();
				if (isTemporary) {
					FileUtils.deleteDirectory(parent);
					return;
				}
				sourceFile.delete();
				// we need as many parent.delete()'s as the number of characters in randomPath
				// in
				// AlfrescoController.findNewName
				parent.delete();
				parent = parent.getParentFile();
				parent.delete();
				parent = parent.getParentFile();
				parent.delete();
			} catch (Exception io) {
				logger.error(io);
			}
		}
	}

}
