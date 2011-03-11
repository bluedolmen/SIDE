package org.demo.workflow.integration;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.json.JSONException;
import org.json.JSONObject;

public class Helper {
	public String host = "";
	public static String[] allowedValues = { "vehicle", "mail", "quotation" };
	public static String[] states = { "IN_PROGRESS", "COMPLETED" };

	public static String[] ALF_URL_SERV_FORM = { "POST", "{host}/service/api/{item_kind}/{item_id}/formprocessor" }; //POST

	public static String[] ALF_URL_SERV_WORK_GET_TASKS = { "GET", "{host}/service/api/workflow-instances/{workflow_instance_id}/task-instances?authority={authority}&state={state}&maxItems={maxItems}&skipCount={skipCount}" };
	//	public static String ALF_URL_SERV_WORK_PUT_TASKS = "{host}/service/api/task-instances/{task_instance_id}";

	public static String[] ALF_URL_SERV_WORK_ASSIGN = { "PUT", "{host}/service/api/task-instances/{task_instance_id}" }; //PUT {"cm_owner":"admin"}, to pool -> {"cm_owner":null}

	public static String[] ALF_URL_SERV_WORK_DELETE = { "DELETE", "{host}/service/api/workflow-instances/{workflow_instance_id}" }; //DELETE

	public static String[] ALF_URL_SERV_WORK_EndTask = { "POST", "http://localhost:8080/alfresco/service/api/workflow/task/end/{taskId}/{transitionId}" };

	HttpClient client = new HttpClient();

	public Helper(String userName, String userPassWord, String host) {
		this.host = host;
		client.getState().setCredentials(new AuthScope(null, -1, null), new UsernamePasswordCredentials(userName, userPassWord));

		//		OptionsMethod options = new OptionsMethod(host);
		//		// execute method and handle any error responses.
		//		Enumeration allowedMethods = options.getAllowedMethods();
		//		options.releaseConnection();

	}

	public static void launchWorkflow(String nodRef) {

	}

	public static void transition() {

	}

	public String requestFormProcessor(String item_kind, String item_id, Map<String, String> properties) throws HttpException, IOException {
		return requestFormProcessor(item_kind, item_id, serializeMap(properties));
	}

	private String serializeMap(Map<String, String> properties) {
		return new JSONObject(properties).toString();
	}

	@SuppressWarnings("deprecation")
	private String requestFormProcessor(String item_kind, String item_id, String body) throws HttpException, IOException {
		// compute alfresco url for contact formprocessor webscript
		String connectionUrl = replaceTokensInURL(ALF_URL_SERV_FORM[1], "host", host);
		connectionUrl = replaceTokensInURL(connectionUrl, "item_kind", item_kind);
		connectionUrl = replaceTokensInURL(connectionUrl, "item_id", item_id);

		// instance POST method
		PostMethod method = new PostMethod(connectionUrl);
		method.setDoAuthentication(true);
		method.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
		method.setRequestBody(body);

		System.out.println("execute post method");
		System.out.println("url :" + connectionUrl);
		System.out.println("POST :" + body);

		client.executeMethod(method);

		String response = method.getResponseBodyAsString();

		return response;
	}

	public String endTask(String taskId, String transitionId) throws HttpException, IOException {
		// compute alfresco url for contact formprocessor webscript
		String connectionUrl = replaceTokensInURL(ALF_URL_SERV_WORK_EndTask[1], "host", host);
		connectionUrl = replaceTokensInURL(connectionUrl, "taskId", taskId);
		connectionUrl = replaceTokensInURL(connectionUrl, "transitionId", transitionId);

		// instance POST method
		PostMethod method = new PostMethod(connectionUrl);
		method.setDoAuthentication(true);
		method.setRequestHeader("Content-Type", "application/json; charset=UTF-8");

		System.out.println("execute post method");
		System.out.println("url :" + connectionUrl);

		client.executeMethod(method);

		String response = method.getResponseBodyAsString();

		return response;
	}

	public String getTasks(String workflow_instance_id, String authority, String state, String maxItems, String skipCount) throws HttpException, IOException {
		String rt = "";
		String connectionUrl = replaceTokensInURL(ALF_URL_SERV_WORK_GET_TASKS[1], "host", host);
		connectionUrl = replaceTokensInURL(connectionUrl, "workflow_instance_id", workflow_instance_id);
		connectionUrl = replaceTokensInURL(connectionUrl, "state", state);
		connectionUrl = replaceTokensInURL(connectionUrl, "authority", authority);
		connectionUrl = replaceTokensInURL(connectionUrl, "maxItems", maxItems);
		connectionUrl = replaceTokensInURL(connectionUrl, "skipCount", skipCount);

		GetMethod method = new GetMethod(connectionUrl);
		method.setDoAuthentication(true);
		method.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
		System.out.println("execute post method");
		System.out.println("url :" + connectionUrl);

		client.executeMethod(method);

		rt = method.getResponseBodyAsString();
		return rt;
	}

	@SuppressWarnings("deprecation")
	public String putTaskProperties(String task_instance_id, String body) throws HttpException, IOException {
		String connectionUrl = replaceTokensInURL(ALF_URL_SERV_WORK_ASSIGN[1], "host", host);
		connectionUrl = replaceTokensInURL(connectionUrl, "task_instance_id", task_instance_id);

		PutMethod method = new PutMethod(connectionUrl);
		method.setDoAuthentication(true);
		method.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
		method.setRequestBody(body);

		client.executeMethod(method);

		String rt = method.getResponseBodyAsString();

		return rt;
	}

	public static String getWokInstanceIdFromResponse(String res) throws JSONException {
		return new JSONObject(res).getString("persistedObject").replaceFirst("WorkflowInstance\\[id=([^,]*),.*", "$1");
	}

	public static String replaceTokensInURL(String url, String token, String value) {
		return url.replaceFirst("\\{" + token + "\\}", Matcher.quoteReplacement(value));
	}

	public String putTaskProperties(String task_instance_id, Map<String, String> properties) throws HttpException, IOException {
		return putTaskProperties(task_instance_id, serializeMap(properties));
	}

	public String deleteProcess(String workflow_instance_id) throws HttpException, IOException {

		String connectionUrl = replaceTokensInURL(ALF_URL_SERV_WORK_DELETE[1], "host", host);
		connectionUrl = replaceTokensInURL(connectionUrl, "workflow_instance_id", workflow_instance_id);

		DeleteMethod method = new DeleteMethod(connectionUrl);

		client.executeMethod(method);

		String rt = method.getResponseBodyAsString();

		return rt;

	}

}
