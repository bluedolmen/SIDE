package com.bluexml.xforms.actions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;

import org.alfresco.service.namespace.QName;
import org.apache.commons.lang.StringUtils;

import com.bluexml.xforms.controller.beans.WorkflowTaskInfoBean;
import com.bluexml.xforms.controller.navigation.NavigationSessionListener;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * @author davidabad
 */
public class WorkflowSaveAction extends AbstractWorkflowAction {

	@Override
	public String getActionName() {
		return MsgId.INT_ACT_CODE_WRKFLW_SAVE.getText();
	}

	public TransitionResultBean submitWork() throws ServletException {
		TransitionResultBean resultBean = new TransitionResultBean();
		if (logger.isDebugEnabled()) {
			logger.debug("WorkflowSaveAction.submit()");
		}

		HashMap<QName, Serializable> properties = new HashMap<QName, Serializable>();
		currentPage = navigationPath.peekCurrentPage();
		String wkFormName = currentPage.getFormName();
		WorkflowTaskInfoBean taskBean = controller.getWorkflowTaskInfoBean(wkFormName);
		userName = getCurrentUserName();
		transaction.setLogin(userName);

		// get process id; try url params first.
		String candidateId = currentPage.getInitParams().get(MsgId.PARAM_WORKFLOW_PROCESS_ID.getText());
		String processId = findProcessId(candidateId, wkFormName);
		if (processId == null) {

		}

		collectTaskProperties(properties, node, taskBean, processId);

		// check that the user is authorized. Already done for the initial task so don't redo.
		if (/*
			 * (controller.isStartTaskForm(wkFormName) == false)
			 * &&
			 */(validateCurrentUser(taskBean, properties) == false)) {
			navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_WKFLW_FAIL_INITIATOR));
			if (logger.isErrorEnabled()) {
				logger.error(MsgPool.getMsg(MsgId.MSG_STATUS_WKFLW_FAIL_INITIATOR));
			}
			return resultBean;
		}

		// add properties from the form fields
		String formTaskName = controller.getWorkflowBlueXMLTaskName(wkFormName);

		// no need to continue if in standalone mode
		if (controller.isInStandaloneMode()) {
			navigationPath.setStatusMsg("The Alfresco Controller is in standalone mode. Workflow actions are not available.");
			return resultBean;
		}

		// check that there's some repository content to associate with the workflow package
		// # 1299: try to save the data form. In case of initial task, this will provide a data id.
		// In all cases, currentPage.getDataId() returns a valid id. Certified :-)
		String dataForm = controller.getUnderlyingDataFormForWorkflow(wkFormName);
		if (dataForm != null) { // #1284
			SubmitAction action = new SubmitAction();
			action.setProperties(controller, uri);
			action.setSubmitProperties(this.result, submission, node, NavigationSessionListener.getServletURL(action.getSessionId()));
			try {
				action.submit();
			} catch (Exception e) {
				navigationPath.setStatusMsg("Could not save the data form.");
				if (logger.isErrorEnabled()) {
					logger.error("Auto-save at workflow: error when saving the data form.", e);
				}
				return resultBean;
			}
		}

		currentPage.setWkflwProcessId(processId);

		// there's no point in continuing without a workflow instance Id
		String wkflwInstanceId = currentPage.getWkflwInstanceId();
		if (StringUtils.trimToNull(wkflwInstanceId) == null) {
			navigationPath.setStatusMsg("Transition not followed. No workflow instance id is available.");
			return resultBean;
		}

		// check that an active task for the workflow instance is consistent with the current form
		logger.debug("Getting the current tasks for workflow instance with id: " + wkflwInstanceId);
		List<String> tasks = controller.workflowGetCurrentTasksInfo(transaction, wkflwInstanceId);
		if (tasks.size() == 0) {
			navigationPath.setStatusMsg("Transition not followed. No tasks were found for instance id '" + wkflwInstanceId + "'.");
			return resultBean;
		}

		logger.debug("Finding the relevant tasks for form " + formTaskName + " amongst tasks '" + tasks + "'");
		String taskInfoString = findRelevantTaskForForm(formTaskName, tasks);
		if (taskInfoString == null) {
			navigationPath.setStatusMsg("Transition not followed. The form '" + wkFormName + "' is not consistent with the current task(s) on the workflow instance.");
			return resultBean;
		}

		// save the task's current state
		logger.debug("Updating task " + taskInfoString);
		String taskId = getIdFromTaskIdNameTitle(taskInfoString);
		if (controller.workflowUpdateTask(transaction, taskId, properties) == false) {
			navigationPath.setStatusMsg("Transition not followed. Failed while updating the task.");
			return resultBean;
		}

		// NO transition triggered
		// seem to be successful
		resultBean.setSuccess(true);
		return resultBean;
	}

}
