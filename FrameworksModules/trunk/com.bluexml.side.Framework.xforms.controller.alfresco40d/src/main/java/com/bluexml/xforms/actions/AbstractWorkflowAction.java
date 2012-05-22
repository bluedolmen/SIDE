package com.bluexml.xforms.actions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;

import org.alfresco.service.cmr.security.PermissionService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.xforms.controller.beans.PageInfoBean;
import com.bluexml.xforms.controller.beans.PersistFormResultBean;
import com.bluexml.xforms.controller.beans.RedirectionBean;
import com.bluexml.xforms.controller.beans.WorkflowTaskInfoBean;
import com.bluexml.xforms.controller.binding.GenericAttribute;
import com.bluexml.xforms.controller.binding.GenericClass;
import com.bluexml.xforms.controller.binding.ValueType;
import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.messages.MsgId;

/**
 * @author davidabad
 */
public abstract class AbstractWorkflowAction extends AbstractWriteAction {
	/** The separator for the compound string that contains info about a task */
	protected static final String TASK_SEPARATOR = "{::}";

	protected static final int TASK_SEPARATOR_LENGTH = TASK_SEPARATOR.length();

	protected static final String TRANSITION_NAME = MsgId.INT_ACT_PARAM_EXEC_ACTION.getText();

	protected Page currentPage;

	protected String userName;

	/*
	 * (non-Javadoc)
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

	protected abstract TransitionResultBean submitWork() throws ServletException;

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
	 * @param currentPage
	 * @param resultBean
	 * @param URLsuffix
	 */
	protected void redirectToWorkflowForm(Page currentPage, TransitionResultBean resultBean, String URLsuffix) {
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
					location = addRedirectionParameters(bean.getTargetUrl(), bean.isAutoAdvance(), bean.isAddParams(), initParams, URLsuffix);
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
	protected String addRedirectionParameters(String targetUrl, boolean autoAdvance, boolean addParams, Map<String, String> initParams, String suffix) {
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
	 * Tells whether a parameter should not be reported to the redirection
	 * address.
	 * 
	 * @param paramName
	 * @return
	 */
	protected boolean isParameterToSkip(String paramName) {
		String[] paramsToSkip = { MsgId.PARAM_LEAVING_FORM.getText() };

		for (String param : paramsToSkip) {
			if (param.equals(paramName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param buffer
	 * @param first
	 * @param param
	 */
	protected boolean addParam(StringBuffer buffer, boolean first, String paramName, String paramValue) {
		if (first == false) {
			buffer.append('&');
		}
		buffer.append(paramName + "=" + paramValue);
		return false;
	}
	
	/**
	 * Builds the appropriate target URL to the given form. Essentially, sets
	 * the target on the same
	 * context.
	 * 
	 * @param nextFormName
	 * @param initParams
	 * @param lsuffix
	 * @return
	 */
	protected String buildNextFormUrl(String nextFormName, Map<String, String> initParams, String lsuffix, boolean autoAdvance) {

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
	 * Redirects the XForms engine to a user-chosen URL, providing (unless told
	 * otherwise) some info
	 * about the workflow task that has just been completed.
	 * 
	 * @param formName
	 *            the name of the form being left
	 * @param nextPage
	 *            the target URL given as a URL parameter
	 * @param URLsuffix
	 * @param noAddInfo
	 *            whether info parameters should be (if set to false) added to
	 *            the URL.
	 */
	protected void redirectToClientURL(String formName, String nextPage, String URLsuffix, boolean noAddInfo) {
		String location = nextPage;
		String infixe = (nextPage.indexOf('?') == -1) ? "?" : "&";
		if (noAddInfo == false) {
			location += infixe + URLsuffix + "&" + MsgId.PARAM_LEAVING_FORM + "=" + formName;
		}
		super.redirectClient(location);
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
	 * Sets the current user name from the URL parameters.
	 * 
	 * @return
	 */
	protected String getCurrentUserName() {
		userName = currentPage.getInitParams().get(MsgId.PARAM_USER_NAME.getText());
		return (userName != null) ? userName : controller.getParamUserName(transaction.getInitParams());
	}

	/**
	 * Finds the workflow definition id for a form.
	 * 
	 * @param candidateProcessId
	 *            the url param the form was called with (if relevant). null if
	 *            no URL param was
	 *            given.
	 * @param formName
	 *            the name of the workflow form, e.g.
	 *            "DigitizationProcess_Debut"
	 * @return candidateProcessId if given. Otherwise, the id for the latest
	 *         deployed version of the
	 *         process definition
	 */
	protected String findProcessId(String candidateProcessId, String formName) {
		if (StringUtils.trimToNull(candidateProcessId) != null) {
			return candidateProcessId;
		}
		logger.debug("Entering findProcessId");
		String processName = controller.workflowExtractProcessNameFromFormName(formName);
		logger.debug(" got processName: " + processName);

		String definitionName = controller.getWorkflowBlueXMLDefinitionName(processName);
		logger.debug(" got definitionName: " + definitionName);
		return controller.workflowGetIdForProcessDefinition(transaction, definitionName); // $$
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
	protected GenericClass collectTaskProperties(HashMap<QName, Serializable> properties, Node node, WorkflowTaskInfoBean taskBean, String processId) throws ServletException {
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
		PersistFormResultBean resultBean = controller.persistWorkflow(transaction, taskTypeName, taskElt, currentPage.getInitParams());
		toCreate = resultBean.getResultClass();

		// add task properties built from the attributes derived from form fields
		String processName = controller.workflowExtractProcessNameFromFormName(taskTypeName);
		String namespaceURI = controller.getWorkflowNamespaceURI(processName);
		List<GenericAttribute> attributes = toCreate.getAttributes().getAttribute();
		for (GenericAttribute attribute : attributes) {
			String localName = attribute.getQualifiedName();
			QName qname = QName.createQName(namespaceURI, localName);
			List<String> propDefs = controller.workflowGetTaskPropertiesQNames(transaction, processId, taskTypeId);

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
	 * Tells whether the current user is a legitimate actor for this task.
	 * 
	 * @param taskBean
	 * @param properties
	 * @return true if the user is the actorId or belongs to the pooled actors
	 */
	protected boolean validateCurrentUser(WorkflowTaskInfoBean taskBean, HashMap<QName, Serializable> properties) {
		String actorIds = taskBean.getActorId();
		if (actorIds != null) {
			// #1514: support for multiple groups/users via comma-separated list
			String[] actors = StringUtils.split(actorIds, ",");
			for (String anActor : actors) {
				anActor = resolveActorId(StringUtils.trim(anActor), properties);
				if (anActor.equals("initiator")) { // #1531
					return true;
				}
				if (anActor.startsWith("#{")) {
					// oups Xform can't interpret this variable we hope that current user can execute this transition
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
	 * Resolves the actorId using the indirection format supported by Alfresco
	 * in workflow
	 * definitions.
	 * 
	 * @param actorId
	 *            e.g. "#{wfbxifremer_user1}"
	 * @param properties
	 * @return
	 */
	protected String resolveActorId(String actorId, HashMap<QName, Serializable> properties) {
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
	 * Finds which of the given tasks matches the given task name.
	 * 
	 * @param formTaskName
	 *            the task name, inferred from a form name
	 * @param taskNames
	 *            a list of active tasks
	 * @return the relevant task for the form
	 */
	protected String findRelevantTaskForForm(String formTaskName, List<String> taskNames) {
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
	 * Gets the name from a task information string as formatted by
	 * {@link XFormsWork.wfGetCurrentTasks}.
	 * 
	 * @param taskInfoString
	 * @return the name of the task, e.g. "wfbxDigitizationProcess:Debut"
	 */
	protected String getNameFromTaskIdNameTitle(String taskInfoString) { // #1534
		int pos = taskInfoString.indexOf(TASK_SEPARATOR);
		int posEnd = taskInfoString.indexOf(TASK_SEPARATOR, pos + TASK_SEPARATOR_LENGTH);
		String result = taskInfoString.substring(pos + TASK_SEPARATOR_LENGTH, posEnd);
		return result;
	}

	/**
	 * Gets the id from a task information string as formatted by
	 * {@link XFormsWork.wfGetCurrentTasks}.
	 * 
	 * @param taskInfoString
	 * @return the id of the task, e.g. "jbpm$64".
	 */
	protected String getIdFromTaskIdNameTitle(String taskInfoString) { // #1534
		int pos = taskInfoString.indexOf(TASK_SEPARATOR);
		String result = taskInfoString.substring(0, pos);
		return result;
	}

	protected class TransitionResultBean {
		protected boolean success;
		protected List<String> tasks;

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
}
