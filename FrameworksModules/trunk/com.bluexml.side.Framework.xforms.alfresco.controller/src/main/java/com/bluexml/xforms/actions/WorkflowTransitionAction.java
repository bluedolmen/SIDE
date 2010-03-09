/**
 * 
 */
package com.bluexml.xforms.actions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.ServletException;

import org.alfresco.repo.workflow.WorkflowModel;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.security.PermissionService;
import org.alfresco.service.cmr.workflow.WorkflowDefinition;
import org.alfresco.service.cmr.workflow.WorkflowInstance;
import org.alfresco.service.cmr.workflow.WorkflowPath;
import org.alfresco.service.cmr.workflow.WorkflowTask;
import org.alfresco.service.cmr.workflow.WorkflowTaskDefinition;
import org.alfresco.service.namespace.QName;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
import com.bluexml.xforms.controller.beans.PersistFormResultBean;
import com.bluexml.xforms.controller.beans.RedirectionBean;
import com.bluexml.xforms.controller.beans.WorkflowTaskInfoBean;
import com.bluexml.xforms.controller.binding.GenericAttribute;
import com.bluexml.xforms.controller.binding.GenericClass;
import com.bluexml.xforms.controller.binding.ValueType;
import com.bluexml.xforms.controller.navigation.NavigationSessionListener;
import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.controller.beans.PageInfoBean;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * Responds to workflow transition buttons on form.
 * 
 * @author Amenel
 */
public class WorkflowTransitionAction extends AbstractWriteAction {

	/** Name of the transition to take. */
	protected static final String TRANSITION_NAME = "transitionName";

	private Page currentPage;

	private String userName;

	private String suffixMsg = "";

	private class TransitionResultBean {
		private boolean success;
		private List<WorkflowTask> tasks;

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
		public List<WorkflowTask> getTasks() {
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
		public void setTasks(List<WorkflowTask> tasks) {
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

		if (AlfrescoController.isStandaloneMode()) {
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
		RedirectionBean bean = controller.workflowGetRedirectionBean(currentPage.getFormName());
		if (bean != null) {
			if (bean.isAutoAdvance()) {
				// if autoAdvancing, get the next form from the tasks
				if (resultBean.getTasks() != null && (resultBean.getTasks().size() > 0)) {
					WorkflowTask nextTask = resultBean.getTasks().get(0);
					nextFormName = AlfrescoController.workflowBuildFormNameFromTask(nextTask.name);
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
		String formTaskName = AlfrescoController.workflowBuildBlueXMLTaskName(wkFormName);
		collectTaskProperties(properties, node, taskBean, processId);

		// no need to continue if in standalone mode
		if (AlfrescoController.isStandaloneMode()) {
			navigationPath
					.setStatusMsg("The Alfresco Controller is in standalone mode. Workflow actions are not available.");
			return resultBean;
		}

		// check that there's some repository content to associate with the workflow package
		// # 1299: try to save the data form. In case of initial task, this will provide a data id.
		// in all cases, currentPage.getDataId() returns a valid id. Certified :-)
		SubmitAction action = new SubmitAction();
		action.setProperties(controller, uri);
		action.setSubmitProperties(this.result, submission, node, NavigationSessionListener
				.getServletURL(action.getSessionId()));
		try {
			action.submit();
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("Auto-save at workflow: error when saving the data form.", e);
			}
			return resultBean;
		}

		// launch a workflow if on a start task form - also set the instance id
		if (initializeTask(wkFormName, taskBean, properties, processId) == false) {
			return resultBean;
		}

		// there's no point in continuing without a workflow instance Id
		if (StringUtils.trimToNull(currentPage.getWkflwInstanceId()) == null) {
			navigationPath
					.setStatusMsg("Transition not followed. No workflow instance id is available.");

			return resultBean;
		}

		// check that an active task for the workflow instance is consistent with the current form
		List<WorkflowTask> tasks = controller.workflowGetCurrentTasks(currentPage
				.getWkflwInstanceId());
		WorkflowTask task = findRelevantTaskForForm(formTaskName, tasks);
		if (task == null) {
			navigationPath.setStatusMsg("Transition not followed. The form '" + wkFormName
					+ "' is not consistent with the current task(s) on the workflow instance.");
			return resultBean;
		}

		// save the task's current state
		if (controller.workflowUpdateTask(transaction, task.id, properties) == null) {
			navigationPath.setStatusMsg("Transition not followed. Failed while updating the task.");
			return resultBean;
		}

		// trigger the transition whose button was clicked
		if (controller.workflowEndTask(transaction, task.id, transitionToTake) == null) {
			navigationPath.setStatusMsg("Transition not followed. Failed while ending the task.");
			return resultBean;
		}

		// set assignment for next task(s) if any
		return reassignWorkflow(transaction);
	}

	/**
	 * Finds which of the given tasks matches the given task name.
	 * 
	 * @param formTaskName
	 *            the task name, inferred from a form name
	 * @param tasks
	 *            a list of active tasks
	 * @return the relevant task for the form
	 */
	private WorkflowTask findRelevantTaskForForm(String formTaskName, List<WorkflowTask> tasks) {
		if (tasks == null) {
			return null;
		}
		for (WorkflowTask task : tasks) {
			if (StringUtils.equals(formTaskName, task.name)) {
				return task;
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
		return (userName != null) ? userName : controller.getParamLoginUserName(transaction
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
		if (taskBean.getActorId() != null) {
			if (StringUtils.equals(taskBean.getActorId(), userName)) {
				return true;
			}
		}
		// search the registered task actors amongst the user's groups
		String refPool = taskBean.getPooledActors();
		if (refPool == null) {
			return false;
		}
		Set<String> auths = controller.systemGetContainingGroups(userName);
		String refGroup = PermissionService.GROUP_PREFIX + refPool;
		if (auths != null) {
			for (String group : auths) {
				if (StringUtils.equals(group, refGroup)) {
					return true;
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
				navigationPath
						.setStatusMsg("Workflow not started. The user is not allowed as initiator of this workflow definition.");
				return false;
			}

			if (currentPage.getWkflwInstanceId() == null) {
				// navigationPath
				// .setStatusMsg("Cannot start workflow when an instance id already exists.");
				// return false;

				NodeRef assignee = controller.systemGetNodeRefForUser(userName);
				if (assignee == null) {
					navigationPath.setStatusMsg("Workflow not started. User unknown in Alfresco.");
					return false;
				}

				// create a workflow package and link the data to the package
				NodeRef wkPackage = controller.workflowCreatePackage(transaction, currentPage
						.getDataId(), userName);
				if (wkPackage == null) {
					navigationPath
							.setStatusMsg("Workflow not started: could not create the package.");
					return false;
				}
				properties.put(WorkflowModel.ASSOC_PACKAGE, wkPackage);
				properties.put(WorkflowModel.ASSOC_ASSIGNEE, assignee);
				properties.put(WorkflowModel.PROP_WORKFLOW_DESCRIPTION, taskBean.getTitle());

				// start a workflow instance and set some useful info
				WorkflowPath path = controller.workflowStart(transaction, processId, null);
				if (path == null) {
					navigationPath
							.setStatusMsg(MsgPool.getMsg(MsgId.MSG_WKFLW_ERROR_START_FAILURE));
					return false;
				}
				WorkflowInstance instance = path.instance;
				if (instance == null) {
					navigationPath
							.setStatusMsg(MsgPool.getMsg(MsgId.MSG_WKFLW_ERROR_START_FAILURE));
					return false;
				}
				currentPage.setWkflwInstanceId(instance.id);
			}
			currentPage.setWkflwProcessId(processId);
			suffixMsg = "Instance id: " + currentPage.getWkflwInstanceId();
		}
		return true;
	}

	/**
	 * Updates the next task(s) so that the workflow instance appears as assigned to a user and/or
	 * group. <br/>
	 * This updating is COMPULSORY: Alfresco will not do it automatically as one could expect.
	 * 
	 * @param transaction
	 */
	private TransitionResultBean reassignWorkflow(AlfrescoTransaction transaction) {
		TransitionResultBean result = new TransitionResultBean();
		HashMap<QName, Serializable> properties;
		List<WorkflowTask> tasks;
		tasks = controller.workflowGetCurrentTasks(currentPage.getWkflwInstanceId());
		if (tasks == null) {
			navigationPath
					.setStatusMsg("Transition followed but couldn't reassign next tasks due to errors on the Alfresco server.");
			return result;
		}
		if (tasks.size() == 0) {
			// the workflow is completed, no more tasks are available
			navigationPath.setStatusMsg("Transition completed: the workflow is ended.");
			result.setSuccess(true);
			return result;
		}
		for (WorkflowTask task : tasks) {
			String pooledActors = controller.getTaskPooledActorsByTaskId(task.name);
			String actorIds = controller.getTaskActorIdByTaskId(task.name);

			if ((StringUtils.trimToNull(pooledActors) != null)
					|| (StringUtils.trimToNull(actorIds) != null)) {
				// we got some user(s)/group(s) to assign the task to

				// the list of users/groups allowed to manage the task
				List<NodeRef> refToActors = new Vector<NodeRef>();
				properties = new HashMap<QName, Serializable>();
				if (StringUtils.trimToNull(actorIds) == null
						&& StringUtils.trimToNull(pooledActors) == null) {
					navigationPath
							.setStatusMsg("Transition followed but an invalid task assignment information prevents reassigning the workflow. All tasks must have either 'actorId' or 'pooledActors' specified.");
					return result;
				}
				if (StringUtils.trimToNull(pooledActors) != null) {
					// #1514: support for multiple groups/users via comma-separated list
					String[] actors = StringUtils.split(pooledActors, ",");
					for (String anActor : actors) {
						addActor(refToActors, controller.systemGetNodeRefForGroup(anActor));
					}
				}
				if (StringUtils.trimToNull(actorIds) != null) {
					String[] actors = StringUtils.split(actorIds, ",");
					for (String anActor : actors) {
						addActor(refToActors, controller.systemGetNodeRefForUser(anActor));
					}
				}
				if (refToActors.size() == 0) {
					navigationPath
							.setStatusMsg("Transition followed but no user/group (actorId/pooledActors) for the next task is known to Alfresco.");
					return result;
				}
				//
				properties.put(WorkflowModel.ASSOC_POOLED_ACTORS, (Serializable) refToActors);
				controller.workflowUpdateTask(transaction, task.id, properties);
			}
		}
		String nextTasksTitles = buildNextTasksTitles(tasks);
		String infixe = "tasks are";
		if (tasks.size() == 1) {
			infixe = "task is";
		}
		result.setTasks(tasks);
		navigationPath.setStatusMsg("Transition successfully followed: next " + infixe + " '"
				+ nextTasksTitles + "'. " + suffixMsg);
		result.setSuccess(true);
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
	private String buildNextTasksTitles(List<WorkflowTask> tasks) {
		String result = "";
		boolean first = true;
		for (WorkflowTask task : tasks) {
			if (!first) {
				result += ", ";
			}
			result += task.title;
			first = false;
		}
		return result;
	}

	/**
	 * Transforms form fields values into properties of the current task.
	 * 
	 * @param properties
	 * @param node
	 * @param taskTypeName
	 * @return null in case of exception
	 * @throws ServletException
	 */
	private GenericClass collectTaskProperties(HashMap<QName, Serializable> properties, Node node,
			WorkflowTaskInfoBean taskBean, String processId) throws ServletException {
		String taskTypeName = taskBean.getName();
		String taskTypeId = taskBean.getId();

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
		String processName = AlfrescoController
				.workflowExtractProcessNameFromFormName(taskTypeName);
		String namespaceURI = AlfrescoController.workflowBuildNamespaceURI(processName);
		WorkflowTaskDefinition taskDef;
		taskDef = controller.workflowGetTaskDefinition(processId, taskTypeId);
		if (taskDef == null) {
			return null;
		}
		Map<QName, PropertyDefinition> propDefs = taskDef.metadata.getProperties();
		List<GenericAttribute> attributes = toCreate.getAttributes().getAttribute();
		for (GenericAttribute attribute : attributes) {
			String localName = attribute.getQualifiedName();
			QName qname = QName.createQName(namespaceURI, localName);
			// we need to filter out properties that do not belong with this task
			if (propDefs.containsKey(qname)) {
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
	 *            the name of the workflow form
	 * @return candidateProcessId if given, the Id for the latest version
	 */
	private String findProcessId(String candidateProcessId, String formName) {
		if (StringUtils.trimToNull(candidateProcessId) != null) {
			return candidateProcessId;
		}

		String processName = AlfrescoController.workflowExtractProcessNameFromFormName(formName);

		String methodName = "getDefinitionByName";
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add(AlfrescoController.workflowBuildBlueXMLDefinitionName(processName));
		WorkflowDefinition def = (WorkflowDefinition) controller.workflowRequestWrapper(
				transaction, methodName, methodParameters);
		return (def != null) ? def.id : null;
	}

}
