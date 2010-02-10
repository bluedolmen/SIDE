package com.bluexml.xforms.controller.alfresco;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.ServletException;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.workflow.WorkflowDefinition;
import org.alfresco.service.cmr.workflow.WorkflowInstance;
import org.alfresco.service.cmr.workflow.WorkflowNode;
import org.alfresco.service.cmr.workflow.WorkflowPath;
import org.alfresco.service.cmr.workflow.WorkflowTask;
import org.alfresco.service.cmr.workflow.WorkflowTaskDefinition;
import org.alfresco.service.cmr.workflow.WorkflowTaskState;
import org.alfresco.service.namespace.QName;
import org.apache.commons.lang.StringUtils;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoControllerException;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;

@SuppressWarnings("unused")
public class CallWorkflow {
	static AlfrescoController controller;
	static AlfrescoTransaction transaction;

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		controller = AlfrescoController.getInstance();
		transaction = new AlfrescoTransaction(controller);

		//
		// list all available workflow definitions
		// listAllDefinitions();
		// listBlueXMLDefinitions();

		// String processId = showLatestVersion();
		//
		// System.out.println("Getting the task def for 'Annotation'");
		// String taskId = AlfrescoController.workflowBuildBlueXMLTaskName("Evaluation_Annotation");
		// WorkflowTaskDefinition taskdef = controller.workflowGetTaskDefinition(processId, taskId);
		// if (taskdef == null) {
		// System.out.println("A problem occured");
		// } else {
		// System.out.println("the task def is: " + taskdef);
		// }
		// System.out.println("--------");

		// undeployBlueXMLDefinitions();
		//

//		listCurrentTasks("jbpm$2");
//		listPooledTasks("ggray");
//		listPooledTasks("admin");
//		System.out.println("Noderef for ggray: " + controller.systemGetNodeRefForUser("ggray"));
//		System.out.println("Noderef : " + controller.systemGetNodeRefForUser("collaborateurs"));
//		System.out.println("\n The help message:");
//		System.out.println(controller.getWebscriptHelp());
//		System.out.println("Test service");
//		System.out.println(controller.systemGetNodeRefForPerson("admin"));
		System.setProperty("javax.xml.parsers.SAXParserFactory","org.apache.xerces.jaxp.SAXParserFactoryImpl");		
	}

	private static void listAuthorities(String userName) {
		Set<String> auths = controller.systemGetContainingGroups(userName);
		System.out.println("Groups for user " + userName + " --------");
		if (auths == null) {
			System.out.println("no containing groups");
		} else {
			for (String group : auths) {
				System.out.println(group);
			}
		}
		System.out.println("-------------");
	}

	/**
	 * 
	 */
	private static void listAllGroupsAndUsers() {
		Set<String> allGroups = controller.systemGetAllAuthoritiesAsGroupsOrUsers(true);
		System.out.println("List of all groups:");
		for (String aGroup : allGroups) {
			System.out.println(aGroup);
		}
		System.out.println();

		Set<String> allUsers = controller.systemGetAllAuthoritiesAsGroupsOrUsers(false);
		System.out.println("List of all users:");
		for (String aUser : allUsers) {
			System.out.println(aUser);
		}
		System.out.println();
	}

	/**
	 * @throws AlfrescoControllerException
	 */
	private static String showLatestVersion() throws AlfrescoControllerException {
		System.out.println("Calling getDefinitionByName for the latest version: ");
		String fullName = AlfrescoController.workflowBuildBlueXMLDefinitionName("Evaluation");
		WorkflowDefinition def = callGetDefinitionByName(fullName);
		if (def == null) {
			System.out.println("Nothing found");
			return null;
		}
		System.out.println("found id: " + def.id);
		return def.id;
	}

	private static void listBlueXMLDefinitions() throws AlfrescoControllerException {
		List<WorkflowDefinition> listDefs = callGetDefinitions();
		int number = 0;
		System.out.println("Listing BlueXML definitions");
		for (WorkflowDefinition def : listDefs) {
			if (AlfrescoController.workflowIsBlueXMLDefinition(def.name)) {
				number++;
				System.out.println("Definition -->" + def.id + "<-- version -->" + def.version
						+ "<--");
			}
		}
		System.out.println("Found " + number + " out of " + listDefs.size());
	}

	private static void undeployBlueXMLDefinitions() throws AlfrescoControllerException {
		List<WorkflowDefinition> listDefs = callGetDefinitions();
		int number = 0;
		for (WorkflowDefinition def : listDefs) {
			if (isBlueXMLDefinition(def.name)) {
				number++;
				System.out.println("Undeploying -->" + def.id + "<-- version -->" + def.version
						+ "<--");
				callUndeployDefinition(def.id);
			}
		}
		System.out.println("Undeployed " + number + " out of " + listDefs.size());
	}

	private static boolean isBlueXMLDefinition(String name) {
		int start = name.indexOf("wfbx");
		int end = name.indexOf(':');
		if (start == -1 || end == -1 || (end < start)) {
			return false;
		}
		String prefix = name.substring(start, end);
		String processName = name.substring(end + 1);
		return StringUtils.endsWith(prefix, processName);
	}

	/**
	 * @param contentRefStr
	 * @throws AlfrescoControllerException
	 */
	private static void listWorkflowsForContent(String contentRefStr)
			throws AlfrescoControllerException {
		List<WorkflowInstance> listInstance = callGetWorkflowsForContent(contentRefStr, true);
		System.out.println();
		System.out.println("Workflows for content: " + contentRefStr);
		if (listInstance == null) {
			System.out.println("no instances alive for this content!");
		} else {
			if (listInstance.size() == 0) {
				System.out.println("no workflow instance found for this content");
				return;
			}
			for (WorkflowInstance instance : listInstance) {
				System.out.println("Id -->" + instance.id + "<-- started on: " + instance.startDate
						+ " from definition: " + instance.definition.id + "("
						+ instance.definition.name + ")");
				// WorkflowDefinition res = callGetDefinition(controller, transaction, def.id);
			}
		}
	}

	/**
	 * @throws AlfrescoControllerException
	 */
	private static void listAllDefinitions() throws AlfrescoControllerException {
		List<WorkflowDefinition> listDefs = callGetDefinitions();
		System.out.println("Listing all definitions");
		for (WorkflowDefinition def : listDefs) {
			System.out.println("Definition -->" + def.id + "<-- version -->" + def.version + "<--");
			WorkflowDefinition res = callGetDefinition(def.id);
			System.out.println(res);
		}
		System.out.println("-----------");
	}

	/**
	 * @param userName
	 * @throws AlfrescoControllerException
	 */
	private static void listAssignedTasks(String userName) throws AlfrescoControllerException {
		System.out.println("Assigned tasks for user: " + userName);
		List<WorkflowTask> listAssignedTasks = callGetAssignedTasks(userName);
		if (listAssignedTasks == null) {
			System.out.println("no tasks found");
		} else {
			if (listAssignedTasks.size() == 0) {
				System.out.println("empty task list");
			} else {
				for (WorkflowTask task : listAssignedTasks) {
					System.out.println("Task: " + task);
				}
			}
		}
	}

	/**
	 * @param userName
	 * @throws AlfrescoControllerException
	 */
	private static void listPooledTasks(String userName) throws AlfrescoControllerException {
		System.out.println("Pooled tasks for user: " + userName);
		List<WorkflowTask> listPooledTasks = callGetPooledTasks(userName);
		if (listPooledTasks == null) {
			System.out.println("no tasks found");
		} else {
			if (listPooledTasks.size() == 0) {
				System.out.println("empty task list");
			} else {
				for (WorkflowTask task : listPooledTasks) {
					System.out.println("Task: " + task);
				}
			}
		}
	}

	/**
	 * @param defId
	 * @throws AlfrescoControllerException
	 */
	private static void listTaskDefinitions(String defId) throws AlfrescoControllerException {
		System.out.println("Task definitions for definition: " + defId);
		List<WorkflowTaskDefinition> list = callGetTaskDefinitions(defId);
		if (list == null) {
			System.out.println("no tasks found");
		} else {
			if (list.size() == 0) {
				System.out.println("empty task list");
			} else {
				for (WorkflowTaskDefinition task : list) {
					System.out.println("Task: " + task);
				}
			}
		}
	}

	private static void listCurrentTasks(String instanceId) {
		List<WorkflowTask> tasks = controller.workflowGetCurrentTasks(instanceId);
		for (WorkflowTask task : tasks) {
			System.out.println("Task: " + task);
			System.out.println("  -- properties");
			Map<QName, Serializable> properties = task.properties;
			for (QName prop : properties.keySet()) {
				System.out.println("\t" + prop.toString() + " : " + properties.get(prop));
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static List<WorkflowTask> callGetPooledTasks(String userName)
			throws AlfrescoControllerException {
		String methodName = "getPooledTasks";
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add(userName);
		List<WorkflowTask> workflowRequestWrapper = (List<WorkflowTask>) controller.workflowRequestWrapper(
				transaction, methodName, methodParameters);
		return workflowRequestWrapper;
	}

	@SuppressWarnings("unchecked")
	private static List<WorkflowTask> callGetAssignedTasks(String userName)
			throws AlfrescoControllerException {
		String methodName = "getAssignedTasks";
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add(userName);
		methodParameters.add(WorkflowTaskState.IN_PROGRESS);
		List<WorkflowTask> workflowRequestWrapper = (List<WorkflowTask>) controller.workflowRequestWrapper(
				transaction, methodName, methodParameters);
		return workflowRequestWrapper;
	}

	private static void callUndeployDefinition(String defId) throws AlfrescoControllerException {
		String methodName = "undeployDefinition";
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add(defId);
		controller.workflowRequestWrapper(transaction, methodName, methodParameters);
	}

	@SuppressWarnings("unchecked")
	private static List<WorkflowInstance> callGetWorkflowsForContent(String refStr,
			boolean onlyActive) throws AlfrescoControllerException {
		NodeRef nodeRef = new NodeRef(refStr);
		String methodName = "getWorkflowsForContent";
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add(nodeRef);
		methodParameters.add(onlyActive);
		List<WorkflowInstance> workflowRequestWrapper = (List<WorkflowInstance>) controller
				.workflowRequestWrapper(transaction, methodName, methodParameters);
		return workflowRequestWrapper;
	}

	@SuppressWarnings("unchecked")
	private static List<WorkflowTaskDefinition> callGetTaskDefinitions(String defId)
			throws AlfrescoControllerException {
		String methodName = "getTaskDefinitions";
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add(defId);
		List<WorkflowTaskDefinition> workflowRequestWrapper = (List<WorkflowTaskDefinition>) controller
				.workflowRequestWrapper(transaction, methodName, methodParameters);
		return workflowRequestWrapper;
	}

	private static WorkflowInstance callGetWorkflowById(String refStr)
			throws AlfrescoControllerException {
		String methodName = "getWorkflowById";
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add(refStr);
		WorkflowInstance workflowRequestWrapper = (WorkflowInstance) controller.workflowRequestWrapper(
				transaction, methodName, methodParameters);
		return workflowRequestWrapper;
	}

	private static boolean callDeleteWorkflow(String instanceId) throws Exception {
		String methodName = "deleteWorkflow";
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add(instanceId);
		if (controller.workflowRequestWrapper(transaction, methodName, methodParameters) == null) {
			return false;
		}
		return true;
	}

	private static String callGetPath(NodeRef noderef) throws AlfrescoControllerException {
		return controller.systemNodeGetPath(noderef);
	}

	private static NodeRef callCreatePackage() throws AlfrescoControllerException {
		String methodName = "createPackage";
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add(null);
		NodeRef workflowRequestWrapper = (NodeRef) controller.workflowRequestWrapper(transaction, methodName,
				methodParameters);
		return workflowRequestWrapper;
	}

	@SuppressWarnings("unchecked")
	private static List<WorkflowDefinition> callGetDefinitions() throws AlfrescoControllerException {
		String methodName = "getAllDefinitions";
		List<Object> methodParameters = new ArrayList<Object>();
		List<WorkflowDefinition> workflowRequestWrapper = (List<WorkflowDefinition>) controller
				.workflowRequestWrapper(transaction, methodName, methodParameters);
		return workflowRequestWrapper;
	}

	private static WorkflowDefinition callGetDefinition(String processId)
			throws AlfrescoControllerException {
		String methodName = "getDefinitionById";
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add(processId);
		WorkflowDefinition workflowRequestWrapper = (WorkflowDefinition) controller.workflowRequestWrapper(
				transaction, methodName, methodParameters);
		return workflowRequestWrapper;
	}

	private static WorkflowDefinition callGetDefinitionByName(String processId)
			throws AlfrescoControllerException {
		String methodName = "getDefinitionByName";
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add(processId);
		WorkflowDefinition workflowRequestWrapper = (WorkflowDefinition) controller.workflowRequestWrapper(
				transaction, methodName, methodParameters);
		return workflowRequestWrapper;
	}

	private static void callStartWorkflow() throws AlfrescoControllerException {
		String methodName = "startWorkflow";
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add("jbpm$1");
		methodParameters.add(new HashMap<QName, Serializable>());
		WorkflowPath workflowRequestWrapper = (WorkflowPath) controller.workflowRequestWrapper(transaction,
				methodName, methodParameters);
		System.out.println(workflowRequestWrapper);
	}

	@SuppressWarnings("unchecked")
	private static void getActivePath() throws ServletException {
		// start a workflow and get a path
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add("jbpm$1");
		methodParameters.add(new HashMap<QName, Serializable>());
		WorkflowPath workflowRequestWrapper = (WorkflowPath) controller.workflowRequestWrapper(transaction,
				"startWorkflow", methodParameters);

		// get the paths from the instance we know does exist
		List<WorkflowPath> paths = null;
		Vector<Object> paramList = new Vector<Object>();
		paramList.add(workflowRequestWrapper.instance.id);
		paths = (List<WorkflowPath>) controller.workflowRequestWrapper(null, "getWorkflowPaths",
				paramList);
		if (paths == null) {
			System.out.println("paths is empty");
			return;
		}
		// get the active path
		WorkflowPath path = null;
		for (WorkflowPath aPath : paths) {
			if (aPath.active) {
				path = aPath;
				break;
			}
		}
		if (path == null) {
			System.out.println("no active path found");
			return;
		}
		WorkflowNode node = path.node;
		System.out.println("the path is at node:" + node.name);
		// get the tasks for the path
		List<WorkflowTask> tasks = null;
		Vector<Object> paramTaskList = new Vector<Object>();
		paramTaskList.add(path.id);
		tasks = (List<WorkflowTask>) controller.workflowRequestWrapper(null,
				"getTasksForWorkflowPath", paramTaskList);
		if (tasks == null) {
			System.out.println("No tasks found");
			return;
		}
		for (WorkflowTask task : tasks) {
			System.out.println("Found task with id:" + task.id + " name:" + task.name + " title:"
					+ task.title + "status: "
					+ (task.state == WorkflowTaskState.IN_PROGRESS ? "in progress" : "completed"));
		}
		// à faire: mettre un contenu dans un package; update de la task et
		// passage des transitions.
		// passer le chargement des properties en UTF-8.
	}
}
