/**
 * 
 */
package com.bluexml.xforms.actions;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.workflow.WorkflowPath;
import org.alfresco.service.cmr.workflow.WorkflowTask;
import org.apache.commons.lang.StringUtils;

import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * This gets called from the process selection page with the <CAPTION_BUTTON_WORKFLOW_SELECT>
 * button.
 * <p>
 * 
 * @author Amenel
 * @deprecated
 */
public class WorkflowStartAction extends AbstractWriteAction {

	protected String selDefinitionId;
	protected String selTaskId;

	public WorkflowStartAction() {
		selDefinitionId = null;
		selTaskId = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionCaption()
	 */
	@Override
	public String getActionCaption() {

		return MsgPool.getMsg(MsgId.CAPTION_BUTTON_WORKFLOW_START);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {

		// return MsgId.INT_ACT_CODE_WRKFLW_START.getText();
		return "dummy";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#submit()
	 */
	/**
	 * Called when there's a click on the button. Redirects the user to the appropriate form: the
	 * task's form if a task is selected, or the start task form otherwise.
	 * 
	 * @throws ServletException
	 */
	@Override
	public void submit() throws ServletException {
		DOMUtil.logXML(node, true);
		Map<String, String> initParams = navigationPath.peekCurrentPage().getInitParams();
		String userName = initParams.get(MsgId.PARAM_USER_NAME.getText());
		String location;
		String dataId = null;

		getProcessInfo();
		// build the location where to redirect the client, i.e. the appropriate form.
		if (StringUtils.trimToNull(selTaskId) == null) {
			if (StringUtils.trimToNull(selDefinitionId) == null) {
				throw new ServletException("Please select a process before proceeding.");
			}
			// a process is selected but no task is specified
			// WorkflowDefinition def = controller.workflowGetWorkflowById(selDefinitionId);
			// WorkflowDefinition.getStartTaskDefinition() returns null :-? so we need to start
			// an instance and cancel it afterwards just to get the start task's name... :-o
			WorkflowPath path = controller.workflowStart(transaction, selDefinitionId, null);
			List<WorkflowTask> list = controller.workflowGetCurrentTasks(path.instance.id);
			WorkflowTask startTask = list.get(0);
			controller.workflowCancel(transaction, path.instance.id);

			String formName = AlfrescoController.workflowBuildFormNameFromTask(startTask.name);
			location = buildWorkflowFormURL(formName) + "&" + MsgId.PARAM_USER_NAME + "="
					+ userName;
			dataId = initParams.get(MsgId.PARAM_DATA_ID.getText());
			if (StringUtils.trimToNull(dataId) != null) {
				location += "&" + MsgId.PARAM_DATA_ID + "=" + dataId;
			}
		} else {
			// we got a process and a task
			WorkflowTask task = controller.workflowGetTaskById(selTaskId);
			navigationPath.peekCurrentPage().setWkflwProcessId(selDefinitionId);
			navigationPath.peekCurrentPage().setWkflwInstanceId(task.path.instance.id);
			List<NodeRef> contentList = controller.workflowGetPackageContents(selTaskId);
			if (contentList != null && contentList.size() > 0) {
				// there should be only one item in the package
				dataId = contentList.get(0).getId();
			}
			String formName = AlfrescoController.workflowBuildFormNameFromTask(task.name);
			location = buildWorkflowFormURL(formName) + "&" + MsgId.PARAM_USER_NAME + "="
					+ userName + "&" + MsgId.PARAM_DATA_ID + "=" + dataId + "&"
					+ MsgId.PARAM_WORKFLOW_INSTANCE_ID + "=" + task.path.instance.id;
		}
		redirectClient(location);
	}

	/**
	 * Fills ids for selected process and selected task.
	 * <p>
	 * See WorkflowGetAction for the expected XML structure.
	 * 
	 * @return false if the node structure is not correct
	 */
	private boolean getProcessInfo() {
		if (node != null) {
			// Element root = null;
			// if (node instanceof Document) {
			// root = ((Document) node).getDocumentElement();
			// }
			// if (node instanceof Element) {
			// root = (Element) node;
			// }
			// Element processElt = DOMUtil.getChild(root,
			// MsgId.INT_WKFLW_PROCESS_NODESET.getText());
			// Element processIdElt = DOMUtil
			// .getChild(processElt, MsgId.INT_INSTANCE_SIDEID.getText());
			// if ((processElt == null) || (processIdElt == null)) {
			// return false;
			// }
			// selDefinitionId = processIdElt.getTextContent();
			//
			// Element instanceElt = DOMUtil
			// .getChild(root, MsgId.INT_WKFLW_INSTANCE_NODESET.getText());
			// Element instanceIdElt = DOMUtil.getChild(instanceElt, MsgId.INT_INSTANCE_SIDEID
			// .getText());
			// if (instanceIdElt != null) {
			// selTaskId = instanceIdElt.getTextContent();
			// }
			return true;
		}
		return false;
	}

}
