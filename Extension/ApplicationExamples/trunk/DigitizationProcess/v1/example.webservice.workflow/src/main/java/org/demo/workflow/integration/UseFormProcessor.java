package org.demo.workflow.integration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;

public class UseFormProcessor extends Common {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			/* create workflow instance */
			String nodeRef = createNewDoc();
			String res = createWorkFlow(nodeRef);
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
//			updateTask(taskId, p2);

			// task ownership
			Map<String, String> p = new HashMap<String, String>();
			p.put("cm_owner", "admin");
//			updateTask(taskId, p);

			// update task
			// update task using formprocessor POST and do transition
			Map<String, String> p3 = new HashMap<String, String>();
			p3.put("wfbxDigitizationProcess_DocumentType", Helper.allowedValues[1]);
			p3.put("prop_bpm_workflowDescription", "try transition");
			// transition
			p3.put("prop_transitions", "testDocumentType");
			updateTask(taskId, p3);

			// delete workflow instance DELETE

			//			delete(wkInstanceId);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String createWorkFlow(String documentNodeRef) throws Exception {
		
		String id = "jbpm$wfbxDigitizationProcess_DigitizationProcess";
		String item_kind = "workflow";

		Map<String, String> properties = new HashMap<String, String>();
		properties.put("prop_bpm_workflowDescription", "ploufe");
		properties.put("prop_bpm_workflowDueDate", "");
		properties.put("prop_bpm_workflowPriority", "2");
		properties.put("assoc_bpm_groupAssignee_added", "workspace://SpacesStore/GROUP_Digitization");
		properties.put("assoc_bpm_groupAssignee_removed", "");
		properties.put("assoc_packageItems_added", documentNodeRef);
		properties.put("assoc_packageItems_removed", "");

		// get Helper to post to alfresco
		Helper h = new Helper("admin", "admin", "http://localhost:8080/alfresco");
		// send request to alfresco formprocessor webscript
		String res = h.requestFormProcessor(item_kind, id, properties);
		return res;

	}

	/**
	 * use formprocessor to update task and do transition
	 * 
	 * @param taskId
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	private static String updateTask(String taskId, Map<String, String> properties) throws HttpException, IOException {

		String item_kind = "task";

		// get Helper to post to alfresco
		Helper h = new Helper("admin", "admin", "http://localhost:8080/alfresco");
		// send request to alfresco formprocessor webscript
		String res = h.requestFormProcessor(item_kind, taskId, properties);
		// get response
		return res;
	}

}
