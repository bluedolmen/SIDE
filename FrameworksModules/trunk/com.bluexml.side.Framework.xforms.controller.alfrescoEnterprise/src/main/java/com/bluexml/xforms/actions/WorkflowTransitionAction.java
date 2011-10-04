/**
 * 
 */
package com.bluexml.xforms.actions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;

import org.alfresco.repo.workflow.WorkflowModel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;
import org.apache.commons.lang.StringUtils;

import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
import com.bluexml.xforms.controller.beans.PageInfoBean;
import com.bluexml.xforms.controller.beans.WorkflowTaskInfoBean;
import com.bluexml.xforms.controller.navigation.NavigationSessionListener;
import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * Responds to workflow transition buttons on form.
 * 
 * @author Amenel
 * @author davidabad
 */
public class WorkflowTransitionAction extends AbstractWorkflowAction {

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {

		return MsgId.INT_ACT_CODE_WRKFLW_TRANSITION.getText();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.actions.AbstractAction#getParamNames()
	 */
	@Override
	protected String[] getParamNames() {

		return new String[] { TRANSITION_NAME };
	}

	/**
	 * Deals with redirecting the client in case the transition succeeded.
	 * 
	 * @param currentPage
	 * @param resultBean
	 * @param URLsuffix
	 */
	protected void redirectSuccess(Page currentPage, TransitionResultBean resultBean, String URLsuffix) {
		Map<String, String> initParams = currentPage.getInitParams();
		String nextPage = null;

		// 
		// decide whether to append parameters to the URL
		boolean foundInParams = initParams.containsKey(MsgId.PARAM_SKIP_ADDITIONAL_INFO.getText());
		boolean skipInfo = false;
		String formName = currentPage.getFormName();
		if (foundInParams) {
			skipInfo = (StringUtils.equals(initParams.get(MsgId.PARAM_SKIP_ADDITIONAL_INFO.getText()), "true"));
		} else { // #1656
			// there may be something specified in the Xtension property of this form
			skipInfo = controller.getXtensionSkipAdditionalInfo(formName, currentPage.getFormType());
		}

		//
		// compute the suffix (parameters string), if any.
		String suffix = "";
		if (skipInfo == false) {
			suffix += URLsuffix;
			// normally, the instance and process ids are available
			suffix = suffix + "&" + MsgId.PARAM_WORKFLOW_PROCESS_ID + "=" + currentPage.getWkflwProcessId();
			suffix = suffix + "&" + MsgId.PARAM_WORKFLOW_INSTANCE_ID + "=" + currentPage.getWkflwInstanceId();
		}

		//
		// determine the redirection URL if any
		nextPage = initParams.get(MsgId.PARAM_SUCCESS_PAGE.getText());
		if (nextPage == null) { // #1656
			// there may be something specified in the Xtension property of this form
			nextPage = controller.getXtensionSuccessPage(formName);
		}

		//
		// do the redirection
		if (nextPage != null) {
			// go to any url that was specified
			redirectToClientURL(formName, nextPage, suffix, skipInfo);
		} else {
			// we get to decide where to redirect
			redirectToWorkflowForm(currentPage, resultBean, suffix);
		}
	}

	/**
	 * Deals with redirecting the client in case the transition failed.
	 * 
	 * @param currentPage
	 * @param URLsuffix
	 */
	protected void redirectFailure(Page currentPage, String URLsuffix) {
		Map<String, String> initParams = currentPage.getInitParams();
		String nextPage = null;

		//
		// determine the redirection page's URL
		nextPage = initParams.get(MsgId.PARAM_FAILURE_PAGE.getText());
		if (nextPage == null) { // #1656
			// there may be something specified in the Xtension property of this form
			nextPage = controller.getXtensionFailurePage(currentPage.getFormName());
		}

		if (nextPage != null) {
			//
			// determine whether to append the suffix
			boolean foundInParams = initParams.containsKey(MsgId.PARAM_SKIP_ADDITIONAL_INFO.getText());
			boolean skipInfo = false;
			if (foundInParams) {
				skipInfo = (StringUtils.equals(initParams.get(MsgId.PARAM_SKIP_ADDITIONAL_INFO.getText()), "true"));
			} else { // #1656
				// there may be something specified in the Xtension property of this form
				skipInfo = controller.getXtensionSkipAdditionalInfo(currentPage.getFormName(), currentPage.getFormType());
			}

			//
			//
			redirectToClientURL(currentPage.getFormName(), nextPage, URLsuffix, skipInfo);
			return;
		}

		PageInfoBean bean = new PageInfoBean(currentPage);
		navigationPath.setCurrentPage(bean);
		setSubmissionDefaultLocation(getServletURL(), result);
	}

	/**
	 * Worker function.
	 * 
	 * @return false if exception or can't do the transition. Otherwise true.
	 * @throws ServletException
	 */
	protected TransitionResultBean submitWork() throws ServletException {
		TransitionResultBean resultBean = new TransitionResultBean();
		HashMap<QName, Serializable> properties = new HashMap<QName, Serializable>();
		currentPage = navigationPath.peekCurrentPage();

		// check the transition although should never throw up... normally.
		String transitionToTake = requestParameters.get(TRANSITION_NAME);
		if (StringUtils.trimToEmpty(transitionToTake).equals(StringUtils.EMPTY)) {
			throw new RuntimeException("XForms WorkflowTransitionAction: no transition name given.");
		}

		// collect info for use later
		String wkFormName = currentPage.getFormName();
		WorkflowTaskInfoBean taskBean = controller.getWorkflowTaskInfoBean(wkFormName);
		userName = getCurrentUserName();
		transaction.setLogin(userName);

		// get process id; try url params first.
		String candidateId = currentPage.getInitParams().get(MsgId.PARAM_WORKFLOW_PROCESS_ID.getText());
		String processId = findProcessId(candidateId, wkFormName);
		if (processId == null) {
			navigationPath.setStatusMsg("Could not find the process Id. Giving up.");
			return resultBean;
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

		// launch a workflow if on a start task form - also set the instance id
		if (initializeTask(wkFormName, taskBean, properties, processId) == false) {
			return resultBean;
		}

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

		// trigger the transition whose button was clicked
		logger.debug("Ending task " + taskId + " with transition " + transitionToTake);
		if (controller.workflowEndTask(transaction, taskId, transitionToTake) == false) {
			navigationPath.setStatusMsg("Transition not followed. Failed while ending the task.");
			return resultBean;
		}

		// set assignment for next task(s) if any
		logger.debug("Reassigning workflow " + wkflwInstanceId);
		return reassignWorkflow(transaction, properties);
	}

	/**
	 * Collects information and performs checks before starting the workflow.<br/>
	 * 
	 * @param formName
	 * @param properties
	 *            the properties to set on the task. Must be non-null.
	 * @param dataId
	 *            the complete (including protocol & namespace) ref to the
	 *            linked data
	 * @param workflowTitle
	 * @param isStartTask
	 * @return false if any exception or error happens. Otherwise true.
	 * @throws ServletException
	 */
	private boolean initializeTask(String formName, WorkflowTaskInfoBean taskBean, HashMap<QName, Serializable> properties, String processId) throws ServletException {
		if (controller.isStartTaskForm(formName)) {
			// check that the user is authorized to start the workflow
			// if (validateCurrentUser(taskBean) == false) {
			// navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_WKFLW_FAIL_INITIATOR));
			// return false;
			// }

			if (currentPage.getWkflwInstanceId() == null) {
				// navigationPath
				// .setStatusMsg("Cannot start workflow when an instance id already exists.");
				// return false;

				NodeRef assignee = controller.systemGetNodeRefForUser(transaction, userName);
				if (assignee == null) {
					navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_WKFLW_FAIL_USER));
					return false;
				}

				// start a workflow instance and set some useful info
				String instanceId = controller.workflowStart(transaction, processId, null);
				if (instanceId == null) {
					navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_ERROR_WKFLW_START_FAILURE));
					return false;
				}

				// create a workflow package and link the data to the package. There's always an id.
				NodeRef wkPackage = controller.workflowCreatePackage(transaction, currentPage.getDataId());
				if (wkPackage == null) {
					navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_WKFLW_FAIL_PACKAGE));
					return false;
				}
				properties.put(WorkflowModel.ASSOC_PACKAGE, wkPackage);
				properties.put(WorkflowModel.ASSOC_ASSIGNEE, assignee);

				String instanceDescription = taskBean.getProcessTitle();
				if (StringUtils.trimToNull(instanceDescription) == null) {
					instanceDescription = taskBean.getTitle();
				}
				if (StringUtils.trimToNull(instanceDescription) == null) {
					instanceDescription = controller.workflowExtractProcessNameFromFormName(taskBean.getFormName());
				}
				properties.put(WorkflowModel.PROP_WORKFLOW_DESCRIPTION, instanceDescription);

				currentPage.setWkflwInstanceId(instanceId);
			}
			currentPage.setWkflwProcessId(processId);
		}
		logger.debug("Started a workflow instance with id :" + currentPage.getWkflwInstanceId());
		return true;
	}

	/**
	 * Updates the next task(s) so that the workflow instance appears as
	 * assigned to a user and/or
	 * group. <br/>
	 * This updating is COMPULSORY: Alfresco will not do it automatically as one
	 * could expect.
	 * 
	 * @param transaction
	 * @param taskProperties
	 */
	private TransitionResultBean reassignWorkflow(AlfrescoTransaction transaction, HashMap<QName, Serializable> taskProperties) {
		TransitionResultBean result = new TransitionResultBean();
		HashMap<QName, Serializable> properties;
		List<String> tasks;
		try {
			tasks = controller.workflowGetCurrentTasksInfo(transaction, currentPage.getWkflwInstanceId());
		} catch (Exception e) {
			logger.debug("Error getting the current tasks", e);
			tasks = null;
		}
		if (tasks == null) {
			navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_WKFLW_FAIL_SERVER));
			return result;
		}
		if (tasks.size() == 0) {
			// the workflow is completed, no more tasks are available
			navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_WKFLW_SUCCESS_END));
			result.setSuccess(true);
			return result;
		}

		List<String> failedAssignmentTasks = new Vector<String>();

		for (String taskInfoString : tasks) {
			String taskId = getIdFromTaskIdNameTitle(taskInfoString);
			String taskName = getNameFromTaskIdNameTitle(taskInfoString);
			WorkflowTaskInfoBean taskBean = controller.getWorkflowTaskInfoBeanByTaskId(taskName);
			if (taskBean == null) {
				// oups sorry try to get it 
				taskBean = controller.getWorkflowTaskInfoBean(taskName);
			}
			String pooledActors = taskBean.getPooledActors();
			String actorIds = taskBean.getActorId();

			if ((StringUtils.trimToNull(pooledActors) != null) || (StringUtils.trimToNull(actorIds) != null)) {
				// we got some user(s)/group(s) to assign the task to

				// the list of users/groups allowed to manage the task
				List<NodeRef> refToActors = new Vector<NodeRef>();
				properties = new HashMap<QName, Serializable>();
				if (StringUtils.trimToNull(actorIds) == null && StringUtils.trimToNull(pooledActors) == null) {
					navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_WKFLW_FAIL_NO_ACTOR));
					return result;
				}
				if (StringUtils.trimToNull(pooledActors) != null) {
					// #1514: support for multiple groups/users via comma-separated list
					String[] actors = StringUtils.split(pooledActors, ",");
					for (String anActor : actors) {
						anActor = StringUtils.trim(anActor);
						anActor = resolveActorId(anActor, taskProperties);
						NodeRef nodeRef = controller.systemGetNodeRefForGroup(transaction, anActor);
						addActor(refToActors, nodeRef);
					}
				}
				if (StringUtils.trimToNull(actorIds) != null) {
					String[] actors = StringUtils.split(actorIds, ",");
					for (String anActor : actors) {
						anActor = StringUtils.trim(anActor);
						anActor = resolveActorId(anActor, taskProperties);
						NodeRef nodeRef = controller.systemGetNodeRefForUser(transaction, anActor);
						addActor(refToActors, nodeRef);
					}
				}
				if (refToActors.size() == 0) {
					navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_WKFLW_FAIL_ASSIGN));
					return result;
				}

				// perform the task assignment to the user(s)/group(s)
				properties.put(WorkflowModel.ASSOC_POOLED_ACTORS, (Serializable) refToActors);
				try {
					controller.workflowUpdateTask(transaction, taskId, properties);
				} catch (Exception e) {
					logger.debug("Error affecting the next tasks (id=" + taskId + ", name=" + taskName + ")", e);
					failedAssignmentTasks.add(taskInfoString);
				}
			}
		}

		// all workflow tasks except the end task should lead to at least one successful rea
		int nbFailedAssignments = failedAssignmentTasks.size();
		if (nbFailedAssignments > 0) {
			String names = StringUtils.join(failedAssignmentTasks, ", ");
			navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_WKFLW_FAIL_ASSIGN_TASKS, "" + nbFailedAssignments, "" + tasks.size(), names));
			return result;
		}
		String nextTasksTitles = buildNextTasksTitles(tasks);
		String msg = MsgPool.getMsg(MsgId.MSG_STATUS_WKFLW_SUCCESS, nextTasksTitles, currentPage.getWkflwInstanceId());
		navigationPath.setStatusMsg(msg);
		result.setTasks(tasks);
		result.setSuccess(true);
		logger.debug("Workflow reassignment of task(s) '" + nextTasksTitles + "' is successful.");
		return result;
	}

	/**
	 * Gets the title from a task information string as formatted by
	 * {@link XFormsWork.wfGetCurrentTasks}.
	 * 
	 * @param taskInfoString
	 * @return the title of the task, e.g. "Demarrage de la dematerialisation"
	 */
	private String getTitleFromTaskIdNameTitle(String taskInfoString) { // #1534
		int pos = taskInfoString.lastIndexOf(TASK_SEPARATOR);
		String result = taskInfoString.substring(pos + TASK_SEPARATOR_LENGTH);
		return result;
	}

	/**
	 * Adds a node ref pointing to an authority into a list of actors.
	 * 
	 * @param refToActors
	 *            the list, possibly modified on return
	 * @param assignee
	 *            a reference to a user or group
	 */
	private void addActor(List<NodeRef> refToActors, NodeRef assignee) {
		if (assignee != null) {
			refToActors.add(assignee);
		}
	}

	/**
	 * Builds a comma separated string with the titles of the tasks.
	 * 
	 * @param tasks
	 * @return a non empty string
	 */
	private String buildNextTasksTitles(List<String> tasks) {
		String result = "";
		boolean first = true;
		for (String task : tasks) {
			if (!first) {
				result += ", ";
			}
			result += getTitleFromTaskIdNameTitle(task);
			first = false;
		}
		return result;
	}

}
