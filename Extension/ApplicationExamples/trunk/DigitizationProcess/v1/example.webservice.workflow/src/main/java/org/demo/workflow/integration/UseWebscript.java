package org.demo.workflow.integration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;

public class UseWebscript extends Common {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			/* create workflow instance */
			String nodeRef = createNewDoc();
			String res = UseFormProcessor.createWorkFlow(nodeRef);
			System.out.println(res);
			String wkInstanceId = Helper.getWokInstanceIdFromResponse(res);
			System.out.println(wkInstanceId);

			/* Update Task */

			// get TaskId
			String taskId = getFirstTaskId(wkInstanceId, "");
			System.out.println("taskId :" + taskId);

			// update owner

			// return to pool
			Map<String, String> p2 = new HashMap<String, String>();
			p2.put("cm_owner", "null");
			updateTask(taskId, p2);

			// task ownership
			Map<String, String> p = new HashMap<String, String>();
			p.put("cm_owner", "admin");
			updateTask(taskId, p);

			// update task
			// update task using formprocessor POST and do transition
			Map<String, String> p3 = new HashMap<String, String>();
			p3.put("wfbxDigitizationProcess_DocumentType", Helper.allowedValues[1]);
			p3.put("bpm_workflowDescription", "try transition");
			updateTask(taskId, p3);

			/* Transition */
			// end task
			res = endTask(taskId, "testDocumentType");
			System.out.println(res);

			// get Task id for next Task
			taskId = getFirstTaskId(wkInstanceId, "");

			// end task
			res = endTask(taskId, "ApprovalRequest");
			System.out.println(res);

			// update Task (add comment)
			// get Task id for next Task
			taskId = getFirstTaskId(wkInstanceId, "");
			Map<String, String> p4 = new HashMap<String, String>();
			p4.put("wfbxDigitizationProcess_Comment", "ok continue");
			updateTask(taskId, p4);

			// end task
			res = endTask(taskId, "integrateMailDocument");
			System.out.println(res);

			// delete workflow instance DELETE

			//			delete(wkInstanceId);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void updateTask(String taskId, Map<String, String> properties) throws HttpException, IOException {
		Helper h = new Helper("admin", "admin", "http://localhost:8080/alfresco");
		h.putTaskProperties(taskId, properties);
	}

	private static String endTask(String taskId, String transitionId) throws HttpException, IOException {
		Helper h = new Helper("admin", "admin", "http://localhost:8080/alfresco");
		String res = h.endTask(taskId, transitionId);
		return res;
	}

}
