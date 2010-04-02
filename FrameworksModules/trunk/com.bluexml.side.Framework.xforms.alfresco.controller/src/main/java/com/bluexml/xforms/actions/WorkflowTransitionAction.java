/**
 * 
 */
package com.bluexml.xforms.actions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.ServletException;

import org.alfresco.repo.workflow.WorkflowModel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.security.PermissionService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
import com.bluexml.xforms.controller.beans.PageInfoBean;
import com.bluexml.xforms.controller.beans.PersistFormResultBean;
import com.bluexml.xforms.controller.beans.RedirectionBean;
import com.bluexml.xforms.controller.beans.WorkflowTaskInfoBean;
import com.bluexml.xforms.controller.binding.GenericAttribute;
import com.bluexml.xforms.controller.binding.GenericClass;
import com.bluexml.xforms.controller.binding.ValueType;
import com.bluexml.xforms.controller.navigation.NavigationSessionListener;
import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * Responds to workflow transition buttons on form.
 * 
 * @author Amenel
 */
public class WorkflowTransitionAction extends AbstractWriteAction {

	/** The separator for the compound string that contains info about a task */
	private static final String TASK_SEPARATOR = "{::}";

	private static final int TASK_SEPARATOR_LENGTH = TASK_SEPARATOR.length();

	/** Name of the transition to take. */
	protected static final String TRANSITION_NAME = "transitionName";

	private Page currentPage;

	private String userName;

	private class TransitionResultBean {
		private boolean success;
		private List<String> tasks;

		public TransitionResultBean() {
			super();
			this.success = false;
			this.tasks = null;
		}

		/**
		 * @return the success
		 */
		public boolean isSuccess() {
			return success;
		}

		/**
		 * @return the tasks
		 */
		public List<String> getTasks() {
			return tasks;
		}

		/**
		 * @param success
		 *            the success to set
		 */
		public void setSuccess(boolean success) {
			this.success = success;
		}

		/**
		 * @param tasks
		 *            the tasks to set
		 */
		public void setTasks(List<String> tasks) {
			this.tasks = tasks;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {

		return MsgId.INT_ACT_CODE_WRKFLW_TRANSITION.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getParamNames()
	 */
	@Override
	protected String[] getParamNames() {

		return new String[] { TRANSITION_NAME };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#submit()
	 */
	@Override
	public void submit() throws ServletException {

		if (controller.isInStandaloneMode()) {
			String msg = "The Alfresco Controller is in standalone mode. Some actions are unavailable";
			navigationPath.setStatusMsg(msg);
			throw new ServletException(msg);
		}

		Page currentPage = navigationPath.peekCurrentPage();

		// do the work
		TransitionResultBean resultBean = submitWork();

		// redirect the client to the appropriate next page
		String msg = navigationPath.getStatusMsg();
		String URLsuffix = MsgId.PARAM_STATUS_MSG + "=" + StringUtils.trimToEmpty(msg);
		if (resultBean.isSuccess()) {
			redirectSuccess(currentPage, resultBean, URLsuffix);
		} else {
			redirectFailure(currentPage, URLsuffix);
		}
	}

	/**
	 * Deals with redirecting the client in case the transition succeeded.
	 * 
	 * @param currentPage
	 * @param nextPage
	 * @param resultBean
	 * @param URLsuffix
	 */
	private void redirectSuccess(Page currentPage, TransitionResultBean resultBean, String URLsuffix) {
		Map<String, String> initParams = currentPage.getInitParams();
		String nextPage = null;

		String suffix = URLsuffix;
		boolean noAddInfo = (StringUtils.equals(initParams.get(MsgId.PARAM_SKIP_ADDITIONAL_INFO
				.getText()), "true"));
		if (noAddInfo == false) {
			// normally, the instance and process ids are available
			suffix = suffix + "&" + MsgId.PARAM_WORKFLOW_PROCESS_ID + "="
					+ currentPage.getWkflwProcessId();
			suffix = suffix + "&" + MsgId.PARAM_WORKFLOW_INSTANCE_ID + "="
					+ currentPage.getWkflwInstanceId();
		}
		nextPage = initParams.get(MsgId.PARAM_PAGE_SUCCESS.getText());

		if (nextPage != null) {
			// go to any url that was specified
			redirectToClientURL(currentPage.getFormName(), nextPage, suffix, noAddInfo);
		} else {
			// we get to decide where to redirect
			redirectToWorkflowForm(currentPage, resultBean, suffix);
		}
	}

	/**
	 * Deals with redirecting the client in case the transition failed.
	 * 
	 * @param currentPage
	 * @param nextPage
	 * @param URLsuffix
	 */
	private void redirectFailure(Page currentPage, String URLsuffix) {
		Map<String, String> initParams = currentPage.getInitParams();
		String nextPage = null;

		if (initParams != null) {
			nextPage = initParams.get(MsgId.PARAM_PAGE_FAILURE.getText());
			if (nextPage != null) {
				boolean noAddInfo = (StringUtils.equals(initParams
						.get(MsgId.PARAM_SKIP_ADDITIONAL_INFO.getText()), "true"));
				redirectToClientURL(currentPage.getFormName(), nextPage, URLsuffix, noAddInfo);
				return;
			}
		}

		PageInfoBean bean = new PageInfoBean(currentPage);
		navigationPath.setCurrentPage(bean);
		setSubmissionDefaultLocation(getServletURL(), result);
	}

	/**
	 * Redirects the XForms engine to a user-chosen URL, providing (unless told otherwise) some info
	 * about the workflow task that has just been completed.
	 * 
	 * @param formName
	 *            the name of the form being left
	 * @param nextPage
	 *            the target URL given as a URL parameter
	 * @param URLsuffix
	 * @param noAddInfo
	 *            whether info parameters should be (if set to false) added to the URL.
	 */
	private void redirectToClientURL(String formName, String nextPage, String URLsuffix,
			boolean noAddInfo) {
		String infixe = (nextPage.indexOf('?') == -1) ? "?" : "&";
		String location = nextPage;
		if (noAddInfo == false) {
			location += infixe + URLsuffix + "&" + MsgId.PARAM_LEAVING_FORM + "=" + formName;
		}
		super.redirectClient(location);
	}

	/**
	 * @param currentPage
	 * @param resultBean
	 * @param URLsuffix
	 */
	private void redirectToWorkflowForm(Page currentPage, TransitionResultBean resultBean,
			String URLsuffix) {
		String nextFormName;
		String location;
		Map<String, String> initParams = currentPage.getInitParams();
		RedirectionBean bean = controller.getWorkflowRedirectionBean(currentPage.getFormName());
		if (bean != null) {
			if (bean.isAutoAdvance()) {
				// if autoAdvancing, get the next form from the tasks
				if (resultBean.getTasks() != null && (resultBean.getTasks().size() > 0)) {
					String taskInfoString = resultBean.getTasks().get(0);
					String nextTaskName = getNameFromTaskIdNameTitle(taskInfoString);
					nextFormName = controller.getWorkflowFormNameFromTask(nextTaskName);
					location = buildNextFormUrl(nextFormName, initParams, URLsuffix, true);
					super.redirectClient(location);
					return;
				}
			} else {
				// go to the specified address if one is provided
				if (StringUtils.trimToNull(bean.getTargetUrl()) != null) {
					location = addRedirectionParameters(bean.getTargetUrl(), bean.isAutoAdvance(),
							bean.isAddParams(), initParams, URLsuffix);
					super.redirectClient(location);
					return;
				}
			}
		}
		// default case: reload the same page
		setSubmissionDefaultLocation(getServletURL(), result);
	}

	/**
	 * Appends parameters that may be useful to the given target URL.
	 * 
	 * @param targetUrl
	 * @return
	 */
	private String addRedirectionParameters(String targetUrl, boolean autoAdvance,
			boolean addParams, Map<String, String> initParams, String suffix) {
		if (addParams == false) {
			return targetUrl;
		}

		boolean first = true;
		StringBuffer outParamsStr = new StringBuffer(suffix);

		for (String param : initParams.keySet()) {
			if (isParameterToSkip(param)) {
				break;
			}
			first = addParam(outParamsStr, first, param, initParams.get(param));
		}
		if (autoAdvance) {
			first = addParam(outParamsStr, first, MsgId.PARAM_AUTO_ADVANCE.getText(), "true");
		}
		// redirect to specified URL
		String infixe = (outParamsStr.indexOf("?") == -1) ? "?" : "&";
		String location = targetUrl + infixe + outParamsStr;

		return location;
	}

	/**
	 * Tells whether a parameter should not be reported to the redirection address.
	 * 
	 * @param paramName
	 * @return
	 */
	private boolean isParameterToSkip(String paramName) {
		String[] paramsToSkip = { MsgId.PARAM_LEAVING_FORM.getText() };

		for (String param : paramsToSkip) {
			if (param.equals(paramName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param buffer
	 * @param first
	 * @param param
	 */
	private boolean addParam(StringBuffer buffer, boolean first, String paramName, String paramValue) {
		if (first == false) {
			buffer.append('&');
		}
		buffer.append(paramName + "=" + paramValue);
		return false;
	}

	/**
	 * Builds the appropriate target URL to the given form. Essentially, sets the target on the same
	 * context.
	 * 
	 * @param nextFormName
	 * @param initParams
	 * @param lsuffix
	 * @return
	 */
	private String buildNextFormUrl(String nextFormName, Map<String, String> initParams,
			String lsuffix, boolean autoAdvance) {

		// StringBuffer outParamsStr = new StringBuffer(256);
		// outParamsStr.append(MsgId.PARAM_DATA_TYPE.getText());
		// outParamsStr.append('=');
		// outParamsStr.append(nextFormName);
		// outParamsStr.append('&');
		// outParamsStr.append(MsgId.PARAM_FORM_TYPE.getText());
		// outParamsStr.append('=');
		// outParamsStr.append(MsgId.INT_FORMTYPE_WKFLW);

		// String location = getServletURL() + "?" + outParamsStr;
		String location = buildWorkflowFormURL(nextFormName);
		return addRedirectionParameters(location, autoAdvance, true, initParams, lsuffix);
	}

	/**
	 * Worker function.
	 * 
	 * @return false if exception or can't do the transition. Otherwise true.
	 * @throws ServletException
	 */
	private TransitionResultBean submitWork() throws ServletException {
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

		// check that the user is authorized. Already done for the initial task so don't redo.
		if ((controller.isStartTaskForm(wkFormName) == false)
				&& (validateCurrentUser(taskBean) == false)) {
			navigationPath.setStatusMsg("Transition not followed. User '" + userName
					+ "' is not a valid actor for this task.");
			return resultBean;
		}

		// get process id; try url params first.
		String candidateId = currentPage.getInitParams().get(
				MsgId.PARAM_WORKFLOW_PROCESS_ID.getText());
		String processId = findProcessId(candidateId, wkFormName);
		if (processId == null) {
			navigationPath.setStatusMsg("Could not find the process Id. Giving up.");
			return resultBean;
		}

		// add properties from the form fields
		String formTaskName = controller.getWorkflowBlueXMLTaskName(wkFormName);
		collectTaskProperties(properties, node, taskBean, processId);

		// no need to continue if in standalone mode
		if (controller.isInStandaloneMode()) {
			navigationPath
					.setStatusMsg("The Alfresco Controller is in standalone mode. Workflow actions are not available.");
			return resultBean;
		}

		// check that there's some repository content to associate with the workflow package
		// # 1299: try to save the data form. In case of initial task, this will provide a data id.
		// In all cases, currentPage.getDataId() returns a valid id. Certified :-)
		String dataForm = controller.getUnderlyingDataFormForWorkflow(wkFormName);
		if (dataForm != null) { // #1284
			SubmitAction action = new SubmitAction();
			action.setProperties(controller, uri);
			action.setSubmitProperties(this.result, submission, node, NavigationSessionListener
					.getServletURL(action.getSessionId()));
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
			navigationPath
					.setStatusMsg("Transition not followed. No workflow instance id is available.");
			return resultBean;
		}

		// check that an active task for the workflow instance is consistent with the current form
		logger.debug("Getting the current tasks for workflow instance with id: " + wkflwInstanceId);
		List<String> tasks = controller.workflowGetCurrentTasksInfo(transaction, wkflwInstanceId);
		if (tasks.size() == 0) {
			navigationPath
					.setStatusMsg("Transition not followed. No tasks were found for instance id '"
							+ wkflwInstanceId + "'.");
			return resultBean;
		}

		logger.debug("Finding the relevant tasks for form " + formTaskName + " amongst tasks '"
				+ tasks + "'");
		String taskInfoString = findRelevantTaskForForm(formTaskName, tasks);
		if (taskInfoString == null) {
			navigationPath.setStatusMsg("Transition not followed. The form '" + wkFormName
					+ "' is not consistent with the current task(s) on the workflow instance.");
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
	 * Finds which of the given tasks matches the given task name.
	 * 
	 * @param formTaskName
	 *            the task name, inferred from a form name
	 * @param taskNames
	 *            a list of active tasks
	 * @return the relevant task for the form
	 */
	private String findRelevantTaskForForm(String formTaskName, List<String> taskNames) {
		if (taskNames == null) {
			return null;
		}
		for (String taskInfoString : taskNames) {
			logger.debug(">>Testing match against task: " + taskInfoString);
			String taskName = getNameFromTaskIdNameTitle(taskInfoString);
			if (StringUtils.equals(formTaskName, taskName)) {
				logger.debug("  >> Match found for task: " + taskInfoString);
				return taskInfoString;
			}
		}
		return null;
	}

	/**
	 * Sets the current user name from the URL parameters.
	 * 
	 * @return
	 */
	private String getCurrentUserName() {
		userName = currentPage.getInitParams().get(MsgId.PARAM_USER_NAME.getText());
		return (userName != null) ? userName : controller.getParamUserName(transaction
				.getInitParams());
	}

	/**
	 * Tells whether the current user is a legitimate actor for this task.
	 * 
	 * @param taskBean
	 * 
	 * @return true if the user is the actorId or belongs to the pooled actors
	 */
	private boolean validateCurrentUser(WorkflowTaskInfoBean taskBean) {
		String actorIds = taskBean.getActorId();
		if (actorIds != null) {
			// #1514: support for multiple groups/users via comma-separated list
			String[] actors = StringUtils.split(actorIds, ",");
			for (String anActor : actors) {
				anActor = StringUtils.trim(anActor);
				if (anActor.equals("initiator")) { // #1531
					return true;
				}
				if (StringUtils.equals(anActor, userName)) {
					return true;
				}
			}
		}

		String pooledActors = taskBean.getPooledActors();
		if (pooledActors == null) {
			return false;
		}

		String[] actors = StringUtils.split(pooledActors, ",");
		for (String anActor : actors) {
			anActor = StringUtils.trim(anActor);
			// search the registered task groups amongst the user's groups
			Set<String> userGroups = controller.systemGetContainingGroups(transaction, userName);
			if (userGroups != null) { // <-- can this ever not be so ?
				String authorizedGroup = PermissionService.GROUP_PREFIX + anActor;
				for (String aUserGroup : userGroups) {
					if (StringUtils.equals(aUserGroup, authorizedGroup)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Collects information and performs checks before starting the workflow.<br/>
	 * 
	 * @param formName
	 * @param properties
	 *            the properties to set on the task. Must be non-null.
	 * @param dataId
	 *            the complete (including protocol & namespace) ref to the linked data
	 * @param workflowTitle
	 * @param isStartTask
	 * @return false if any exception or error happens. Otherwise true.
	 * @throws ServletException
	 */
	private boolean initializeTask(String formName, WorkflowTaskInfoBean taskBean,
			HashMap<QName, Serializable> properties, String processId) throws ServletException {
		if (controller.isStartTaskForm(formName)) {
			// check that the user is authorized to start the workflow
			if (validateCurrentUser(taskBean) == false) {
				navigationPath.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_WKFLW_FAIL_INITIATOR));
				return false;
			}

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
					navigationPath
							.setStatusMsg(MsgPool.getMsg(MsgId.MSG_ERROR_WKFLW_START_FAILURE));
					return false;
				}

				// create a workflow package and link the data to the package
				NodeRef wkPackage = controller.workflowCreatePackage(transaction, currentPage
						.getDataId());
				if (wkPackage == null) {
					navigationPath
							.setStatusMsg(MsgPool.getMsg(MsgId.MSG_STATUS_WKFLW_FAIL_PACKAGE));
					return false;
				}
				properties.put(WorkflowModel.ASSOC_PACKAGE, wkPackage);
				properties.put(WorkflowModel.ASSOC_ASSIGNEE, assignee);

				String instanceDescription = taskBean.getProcessTitle();
				if (StringUtils.trimToNull(instanceDescription) == null) {
					instanceDescription = taskBean.getTitle();
				}
				if (StringUtils.trimToNull(instanceDescription) == null) {
					instanceDescription = controller
							.workflowExtractProcessNameFromFormName(taskBean.getFormName());
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
	 * Updates the next task(s) so that the workflow instance appears as assigned to a user and/or
	 * group. <br/>
	 * This updating is COMPULSORY: Alfresco will not do it automatically as one could expect.
	 * 
	 * @param transaction
	 * @param taskProperties
	 */
	private TransitionResultBean reassignWorkflow(AlfrescoTransaction transaction,
			HashMap<QName, Serializable> taskProperties) {
		TransitionResultBean result = new TransitionResultBean();
		HashMap<QName, Serializable> properties;
		List<String> tasks;
		tasks = controller.workflowGetCurrentTasksInfo(transaction, currentPage
				.getWkflwInstanceId());
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
		for (String taskInfoString : tasks) {
			String taskId = getIdFromTaskIdNameTitle(taskInfoString);
			String taskName = getNameFromTaskIdNameTitle(taskInfoString);
			WorkflowTaskInfoBean taskBean = controller.getWorkflowTaskInfoBeanByTaskId(taskName);
			String pooledActors = taskBean.getPooledActors();
			String actorIds = taskBean.getActorId();

			if ((StringUtils.trimToNull(pooledActors) != null)
					|| (StringUtils.trimToNull(actorIds) != null)) {
				// we got some user(s)/group(s) to assign the task to

				// the list of users/groups allowed to manage the task
				List<NodeRef> refToActors = new Vector<NodeRef>();
				properties = new HashMap<QName, Serializable>();
				if (StringUtils.trimToNull(actorIds) == null
						&& StringUtils.trimToNull(pooledActors) == null) {
					navigationPath.setStatusMsg(MsgPool
							.getMsg(MsgId.MSG_STATUS_WKFLW_FAIL_NO_ACTOR));
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
				//
				properties.put(WorkflowModel.ASSOC_POOLED_ACTORS, (Serializable) refToActors);
				controller.workflowUpdateTask(transaction, taskId, properties);
			}
		}
		String nextTasksTitles = buildNextTasksTitles(tasks);
		String msg = MsgPool.getMsg(MsgId.MSG_STATUS_WKFLW_SUCCESS, nextTasksTitles, currentPage
				.getWkflwInstanceId());
		navigationPath.setStatusMsg(msg);
		result.setTasks(tasks);
		result.setSuccess(true);
		logger.debug("Workflow reassignment of task(s) '" + nextTasksTitles + "' is successful.");
		return result;
	}

	/**
	 * Gets the id from a task information string as formatted by
	 * {@link XFormsWork.wfGetCurrentTasks}.
	 * 
	 * @param taskInfoString
	 * @return the id of the task, e.g. "jbpm$64".
	 */
	private String getIdFromTaskIdNameTitle(String taskInfoString) { // #1534
		int pos = taskInfoString.indexOf(TASK_SEPARATOR);
		String result = taskInfoString.substring(0, pos);
		return result;
	}

	/**
	 * Gets the name from a task information string as formatted by
	 * {@link XFormsWork.wfGetCurrentTasks}.
	 * 
	 * @param taskInfoString
	 * @return the name of the task, e.g. "wfbxDigitizationProcess:Debut"
	 */
	private String getNameFromTaskIdNameTitle(String taskInfoString) { // #1534
		int pos = taskInfoString.indexOf(TASK_SEPARATOR);
		int posEnd = taskInfoString.indexOf(TASK_SEPARATOR, pos + TASK_SEPARATOR_LENGTH);
		String result = taskInfoString.substring(pos + TASK_SEPARATOR_LENGTH, posEnd);
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
	 * Resolves the actorId using the indirection format supported by Alfresco in workflow
	 * definitions.
	 * 
	 * @param actorId
	 *            e.g. "#{wfbxifremer_user1}"
	 * @param properties
	 * @return
	 */
	private String resolveActorId(String actorId, HashMap<QName, Serializable> properties) {
		// #1532: support for expressions in actorId/pooledActors
		// 
		int pos = actorId.indexOf("#{");
		if (pos != 0) {
			return actorId;
		}
		pos += 2;
		int posEnd = actorId.indexOf("}", pos);
		if (posEnd == -1) {
			return actorId;
		}

		String expr = actorId.substring(pos, posEnd);
		pos = expr.indexOf('_');
		if (pos == -1) {
			return actorId;
		}
		String property = expr.substring(pos + 1);
		for (QName qname : properties.keySet()) {
			if (qname.getLocalName().endsWith(property)) {
				Serializable serializable = properties.get(qname);
				return serializable.toString();
			}
		}
		return actorId;
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

	/**
	 * Transforms form fields values into properties of the current task.
	 * 
	 * @param properties
	 * @param node
	 * @param taskBean
	 * @param processId
	 * @return null in case of exception
	 * @throws ServletException
	 */
	private GenericClass collectTaskProperties(HashMap<QName, Serializable> properties, Node node,
			WorkflowTaskInfoBean taskBean, String processId) throws ServletException {
		String taskTypeName = taskBean.getFormName();
		String taskTypeId = taskBean.getTaskId();

		Element root;
		if (node instanceof Document) {
			root = ((Document) node).getDocumentElement();
		} else {
			root = (Element) node;
		}
		// Element processElt = DOMUtil.getChild(root, processName);
		Element taskElt = DOMUtil.getChild(root, taskTypeName);
		GenericClass toCreate;
		// Assemble a BlueXML Class.xsd object from the instance, dealing with FileFields (#1209)
		PersistFormResultBean resultBean = controller.persistWorkflow(transaction, taskTypeName,
				taskElt);
		toCreate = resultBean.getResultClass();

		// add task properties built from the attributes derived from form fields
		String processName = controller.workflowExtractProcessNameFromFormName(taskTypeName);
		String namespaceURI = controller.getWorkflowNamespaceURI(processName);
		List<GenericAttribute> attributes = toCreate.getAttributes().getAttribute();
		for (GenericAttribute attribute : attributes) {
			String localName = attribute.getQualifiedName();
			QName qname = QName.createQName(namespaceURI, localName);
			List<String> propDefs = controller.workflowGetTaskPropertiesQNames(transaction,
					processId, taskTypeId);

			// we need to filter out properties that do not belong with this task
			if (propDefs.contains(qname.toString())) {
				Serializable value;
				List<ValueType> allValues = attribute.getValue();
				if (allValues.size() > 0) {
					if (allValues.size() == 1) {
						value = attribute.getValue().get(0).getValue();
					} else {
						value = (Serializable) allValues;
					}
					properties.put(qname, value);
				}
			}
		}

		return toCreate;
	}

	/**
	 * Finds the workflow definition id for a form.
	 * 
	 * @param candidateProcessId
	 *            the url param the form was called with (if relevant). null if no URL param was
	 *            given.
	 * @param formName
	 *            the name of the workflow form, e.g. "DigitizationProcess_Debut"
	 * @return candidateProcessId if given. Otherwise, the id for the latest deployed version of the
	 *         process definition
	 */
	private String findProcessId(String candidateProcessId, String formName) {
		if (StringUtils.trimToNull(candidateProcessId) != null) {
			return candidateProcessId;
		}

		String processName = controller.workflowExtractProcessNameFromFormName(formName);

		String definitionName = controller.getWorkflowBlueXMLDefinitionName(processName);
		return controller.workflowGetIdForProcessDefinition(transaction, definitionName); // $$
	}

}
