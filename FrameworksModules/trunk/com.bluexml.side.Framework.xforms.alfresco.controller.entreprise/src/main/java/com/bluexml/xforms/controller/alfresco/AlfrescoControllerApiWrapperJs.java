/**
 * 
 */
package com.bluexml.xforms.controller.alfresco;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.ServletException;

import org.alfresco.service.cmr.workflow.WorkflowDefinition;
import org.alfresco.service.cmr.workflow.WorkflowTask;
import org.alfresco.service.cmr.workflow.WorkflowTaskDefinition;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;

import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.xforms.controller.beans.RedirectionBean;
import com.bluexml.xforms.controller.beans.WorkflowTaskInfoBean;
import com.google.gson.Gson;

/**
 * Glue for providing Javascript access to the AlfrescoController API.
 * <p>
 * Notes:
 * <ul>
 * <li>When using this Javascript API, <code>null</code> arguments are achieved by not specifying
 * any character. Typically, such an argument, if followed by another argument will be represented
 * by "//". In case there's no next argument, the caller <em>MUST</em> add any string or
 * character after the double slash. For example, <tt>setCssUrl(null)</tt> translates to
 * "setCssUrl//any_character_or_string".</li>
 * <li>All function calls return a string. When mapped to a function whose return type is
 * <em>void</em>; functions in this API return <tt>"ACK"</tt>.</li>
 * <li>The returned string is a JSON string. The key is either <tt>result</tt> or <tt>error</tt>
 * depending on whether the call was successful or not.</li>
 * <li>In function <tt>workflowGetTaskById</tt>: dates returned are formatted as ISO 8601 dates.</li>
 * <li>The {@link #getWebscriptHelp()} function is disabled in this API because multi-line JSON is
 * not a standard yet: any call will cause an exception.</li>
 * <li>The slash ('/') character is used for splitting the call parameter into function name and
 * arguments. Because of this, the character cannot be given as-is if it's supposed to be part of an
 * argument. For instance, if the intention is to create a path "North/Europe" under
 * "Company Home/User Homes" in the Alfresco repository, the call parameter to function
 * <tt>createPathInRepository</tt> would be
 * "createPathInRepository/username/app:company_home/app:user_homes/cm:North/cm:Europe". But the
 * splitting would yield the following interpretation: call function "createPathInRepository" with
 * arguments "app:company_home", "app:user_homes", etc. The same misinterpretation may happen for
 * any other character. In order to avoid it, parameters that contain a slash ('/') character must
 * be URL-encoded in the code that uses this API. Hence, the example path must be:
 * "%2Fapp:company_home%2Fapp:user_homes%2Fcm:North%2Fcm:Europe".</li>
 * <li>Because of the previous point, and to make things simpler, all parameters are URL-decoded,
 * including those that would normally not contain any slashes (e.g. user names). The consequence
 * for the calling JS code is that each parameter value should be URL-encoded.</li>
 * </ul>
 * </p>
 * 
 * @author Amenel
 * 
 */
public class AlfrescoControllerApiWrapperJs {

	private static final String JS_UNDEFINED = "undefined";
	private static final String JS_NULL = "null";

	private static final String JS_ACK = "\"ACK\"";
	private static final String JS_TRUE = "true";
	private static final String JS_FALSE = "false";

	AlfrescoControllerAPI controller;
	SimpleDateFormat formatter = null;
	Gson gSon = null;

	/**
	 * Constructor. Just keep a reference to the controller.
	 */
	public AlfrescoControllerApiWrapperJs(AlfrescoControllerAPI controller) {
		this.controller = controller;
	}

	/**
	 * Encloses the given text with double-quotes, with no verification other than for a
	 * <code>null</code> value.
	 * 
	 * @param text
	 *            the string to enclose within double-quotes
	 * @return the double-quoted string or the non quoted <em>"null"</em>.
	 */
	private static String jsonify(String text) {
		if (text == null) {
			return JS_NULL;
		}
		return "\"" + text + "\"";
	}

	/**
	 * Encloses the items of the given collection with double-quotes into a new collection
	 * instantiated as a vector.
	 * 
	 * @param collection
	 *            a collection of strings
	 * @return the given collection as a vector with each item double-quoted
	 */
	private static List<String> jsonifyCollection(Collection<String> collection) {
		List<String> result = new Vector<String>(collection.size());
		for (String item : collection) {
			result.add(jsonify(item));
		}
		return result;
	}

	/**
	 * Central dispatcher to the relevant wrapper function. Each function maps to an entry of
	 * {@link AlfrescoControllerAPI}.<br/>
	 * WARNING: index out-of-range exceptions may happen if the number of parameters provided turns
	 * out to be insufficient for a specific function.
	 * 
	 * @param jsCallStr
	 *            the value of the JS call parameter.
	 * @return the result of the function that has been called, possibly enclosed in double-quotes
	 *         internal to the string.
	 * @throws ServletException
	 * @throws UnsupportedEncodingException
	 */
	public String dispatch(String jsCallStr) throws ServletException, UnsupportedEncodingException {
		String result = null;

		if (jsCallStr == null) {
			throw new IllegalArgumentException(
					"Javascript API to BlueXML XForms Controller: cannot deal with a null request.");
		}

		// get the function name and parameters
		String[] param = jsCallStr.split("/");
		if (param.length > 1) {
			for (int i = 1; i < param.length; i++) {
				if (param[i].equals("")) {
					param[i] = null;
				} else {
					param[i] = URLDecoder.decode(param[i], "UTF-8");
				}
			}
		}
		String function = param[0];

		// dispatch
		if (function.equalsIgnoreCase("authenticate")) {
			result = authenticate(param[1], param[2]);
		} else if (function.equalsIgnoreCase("createPathInRepository")) {
			result = createPathInRepository(param[1], param[2]);
		} else if (function.equalsIgnoreCase("getAlfrescoUrl")) {
			result = getAlfrescoUrl();
		} else if (function.equalsIgnoreCase("getAllCustomForms")) {
			result = getAllCustomForms();
		} else if (function.equalsIgnoreCase("getAllDefaultForms")) {
			result = getAllDefaultForms();
		} else if (function.equalsIgnoreCase("getAllSearchForms")) {
			result = getAllSearchForms();
		} else if (function.equalsIgnoreCase("getAllWorkflowForms")) {
			result = getAllWorkflowForms();
		} else if (function.equalsIgnoreCase("getCssUrl")) {
			result = getCssUrl();
		} else if (function.equalsIgnoreCase("getCustomFormForDatatype")) {
			result = getCustomFormForDatatype(param[1]);
		} else if (function.equalsIgnoreCase("getDefaultFormForDatatype")) {
			result = getDefaultFormForDatatype(param[1]);
		} else if (function.equalsIgnoreCase("getInstanceClass")) {
			boolean readOnly = (param[4].equalsIgnoreCase("true"));
			boolean applyFormat = (param[5].equalsIgnoreCase("true"));
			result = getInstanceClass(param[1], param[2], param[3], readOnly, applyFormat);
		} else if (function.equalsIgnoreCase("getInstanceForm")) {
			boolean readOnly = (param[4].equalsIgnoreCase("true"));
			result = getInstanceForm(param[1], param[2], param[3], readOnly);
		} else if (function.equalsIgnoreCase("getInstanceSearch")) {
			result = getInstanceSearch(param[1]);
		} else if (function.equalsIgnoreCase("getInstanceWorkflow")) {
			result = getInstanceWorkflow(param[1], param[2]);
		} else if (function.equalsIgnoreCase("getNodeContentInfo")) {
			result = getNodeContentInfo(param[1], param[2]);
		} else if (function.equalsIgnoreCase("getNodeType")) {
			result = getNodeType(param[1], param[2]);
		} else if (function.equalsIgnoreCase("getNodeTypeFull")) {
			result = getNodeTypeFull(param[1], param[2]);
		} else if (function.equalsIgnoreCase("getReadOnlyFormsSuffix")) {
			result = getReadOnlyFormsSuffix();
		} else if (function.equalsIgnoreCase("getUnderlyingDataFormForWorkflow")) {
			result = getUnderlyingDataFormForWorkflow(param[1]);
		} else if (function.equalsIgnoreCase("getUnderlyingTypeForForm")) {
			result = getUnderlyingTypeForForm(param[1]);
		} else if (function.equalsIgnoreCase("getUnderlyingTypeForWorkflow")) {
			result = getUnderlyingTypeForWorkflow(param[1]);
		} else if (function.equalsIgnoreCase("getWebscriptHelp")) {
			result = getWebscriptHelp(param[1]);
		} else if (function.equalsIgnoreCase("getWorkflowBlueXMLDefinitionName")) {
			result = getWorkflowBlueXMLDefinitionName(param[1]);
		} else if (function.equalsIgnoreCase("getWorkflowBlueXMLTaskName")) {
			result = getWorkflowBlueXMLTaskName(param[1]);
		} else if (function.equalsIgnoreCase("getWorkflowFormNameByTaskId")) {
			result = getWorkflowFormNameByTaskId(param[1]);
		} else if (function.equalsIgnoreCase("getWorkflowFormNameFromTask")) {
			result = getWorkflowFormNameFromTask(param[1]);
		} else if (function.equalsIgnoreCase("getWorkflowNamespaceURI")) {
			result = getWorkflowNamespaceURI(param[1]);
		} else if (function.equalsIgnoreCase("getWorkflowRedirectionBean")) {
			result = getWorkflowRedirectionBean(param[1]);
		} else if (function.equalsIgnoreCase("getWorkflowStartTaskFormName")) {
			result = getWorkflowStartTaskFormName(param[1]);
		} else if (function.equalsIgnoreCase("getWorkflowTaskInfoBean")) {
			result = getWorkflowTaskInfoBean(param[1]);
		} else if (function.equalsIgnoreCase("getWorkflowTaskInfoBeanByTaskId")) {
			result = getWorkflowTaskInfoBeanByTaskId(param[1]);
		} else if (function.equalsIgnoreCase("isCustomForm")) {
			result = isCustomForm(param[1]);
		} else if (function.equalsIgnoreCase("isDefaultForm")) {
			result = isDefaultForm(param[1]);
		} else if (function.equalsIgnoreCase("isInStandaloneMode")) {
			result = isInStandaloneMode();
		} else if (function.equalsIgnoreCase("isSearchForm")) {
			result = isSearchForm(param[1]);
		} else if (function.equalsIgnoreCase("isStartTaskForm")) {
			result = isStartTaskForm(param[1]);
		} else if (function.equalsIgnoreCase("isWorkflowForm")) {
			result = isWorkflowForm(param[1]);
		} else if (function.equalsIgnoreCase("loadRedirectionTable")) {
			result = loadRedirectionTable(param[1]);
		} else if (function.equalsIgnoreCase("patchDataId")) {
			result = patchDataId(param[1]);
		} else if (function.equalsIgnoreCase("performDynamicReload")) {
			result = performDynamicReload();
		} else if (function.equalsIgnoreCase("readObjectFromRepository")) {
			result = readObjectFromRepository(param[1], param[2]);
		} else if (function.equalsIgnoreCase("setAlfrescoUrl")) {
			result = setAlfrescoUrl(param[1]);
		} else if (function.equalsIgnoreCase("setCssUrl")) {
			result = setCssUrl(param[1]);
		} else if (function.equalsIgnoreCase("setStandaloneMode")) {
			boolean standaloneMode = (param[1].equalsIgnoreCase("true"));
			result = setStandaloneMode(standaloneMode);
		} else if (function.equalsIgnoreCase("systemGetAllAuthoritiesAsGroupsOrUsers")) {
			boolean asGroups = (param[2].equalsIgnoreCase("true"));
			result = systemGetAllAuthoritiesAsGroupsOrUsers(param[1], asGroups);
		} else if (function.equalsIgnoreCase("systemGetContainingGroups")) {
			result = systemGetContainingGroups(param[1], param[2]);
		} else if (function.equalsIgnoreCase("systemGetNodeProperty")) {
			result = systemGetNodeProperty(param[1], param[2], param[3]);
		} else if (function.equalsIgnoreCase("systemGetNodeRefForGroup")) {
			result = systemGetNodeRefForGroup(param[1], param[2]);
		} else if (function.equalsIgnoreCase("systemGetNodeRefForUser")) {
			result = systemGetNodeRefForUser(param[1], param[2]);
		} else if (function.equalsIgnoreCase("unpatchDataId")) {
			result = unpatchDataId(param[1]);
		} else if (function.equalsIgnoreCase("workflowExtractNamespacePrefix")) {
			result = workflowExtractNamespacePrefix(param[1]);
		} else if (function.equalsIgnoreCase("workflowExtractProcessNameFromDefName")) {
			result = workflowExtractProcessNameFromDefName(param[1]);
		} else if (function.equalsIgnoreCase("workflowExtractProcessNameFromFormName")) {
			result = workflowExtractProcessNameFromFormName(param[1]);
		} else if (function.equalsIgnoreCase("workflowExtractTaskNameFromFormName")) {
			result = workflowExtractTaskNameFromFormName(param[1]);
		} else if (function.equalsIgnoreCase("workflowGetCurrentTasks")) {
			result = workflowGetCurrentTasks(param[1], param[2]);
		} else if (function.equalsIgnoreCase("workflowGetPackageContents")) {
			result = workflowGetPackageContents(param[1], param[2]);
		} else if (function.equalsIgnoreCase("workflowGetPooledTasks")) {
			result = workflowGetPooledTasks(param[1], param[2]);
		} else if (function.equalsIgnoreCase("workflowGetTaskById")) {
			result = workflowGetTaskById(param[1], param[2]);
		} else if (function.equalsIgnoreCase("workflowGetTaskDefinition")) {
			result = workflowGetTaskDefinition(param[1], param[2], param[3]);
		} else if (function.equalsIgnoreCase("workflowGetWorkflowById")) {
			result = workflowGetWorkflowById(param[1], param[2]);
		} else if (function.equalsIgnoreCase("workflowGetWorkflowsForContent")) {
			boolean onlyActive = (param[3].equalsIgnoreCase("true"));
			result = workflowGetWorkflowsForContent(param[1], param[2], onlyActive);
		} else if (function.equalsIgnoreCase("workflowIsBlueXMLDefinition")) {
			result = workflowIsBlueXMLDefinition(param[1]);
		} else {
			throw new IllegalArgumentException(
					"Javascript API to BlueXML XForms Controller: unknown method.");
		}

		// some results require being enclosed in double quotes
		if (result == null) {
			return JS_NULL;
		}
		if (result.equals("") == false) {
			char firstChar = result.charAt(0);
			if (firstChar == '[' || firstChar == '{' || firstChar == '"' || result.equals(JS_TRUE)
					|| result.equals(JS_FALSE) || result.equals(JS_ACK) || result.equals(JS_NULL)
					|| result.equals(JS_UNDEFINED)) {
				return result;
			}
		}
		return jsonify(result);
	}

	//
	//
	//
	// The rest of this file is made of wrappers to the matching method in the Java API.
	//
	//
	//

	private String authenticate(String username, String password) {
		boolean result = controller.authenticate(username, password);
		return (result ? JS_TRUE : JS_FALSE);
	}

	private String createPathInRepository(String userName, String folderPath)
			throws ServletException {
		return controller.createPathInRepository(userName, folderPath);
	}

	private String getAlfrescoUrl() {
		return controller.getAlfrescoUrl();
	}

	private String getAllCustomForms() {
		List<String> result = controller.getAllCustomForms();
		return "[" + StringUtils.join(jsonifyCollection(result), ',') + "]";
	}

	private String getAllDefaultForms() {
		List<String> result = controller.getAllDefaultForms();
		return "[" + StringUtils.join(jsonifyCollection(result), ',') + "]";
	}

	private String getAllSearchForms() {
		List<String> result = controller.getAllSearchForms();
		return "[" + StringUtils.join(jsonifyCollection(result), ',') + "]";
	}

	private String getAllWorkflowForms() {
		List<String> result = controller.getAllWorkflowForms();
		return "[" + StringUtils.join(jsonifyCollection(result), ',') + "]";
	}

	private String getCssUrl() {
		return controller.getCssUrl();
	}

	private String getCustomFormForDatatype(String dataType) {
		return controller.getCustomFormForDatatype(dataType);
	}

	private String getDefaultFormForDatatype(String dataType) {
		return controller.getDefaultFormForDatatype(dataType);
	}

	private String getInstanceClass(String userName, String formName, String dataId,
			boolean formIsReadOnly, boolean applyUserFormats) throws ServletException {
		Document resultDoc = controller.getInstanceClass(userName, formName, dataId,
				formIsReadOnly, applyUserFormats);
		String result = DOMUtil.convertDocument2String(resultDoc, false);
		return result;
	}

	private String getInstanceForm(String userName, String formName, String dataId,
			boolean formIsReadOnly) throws ServletException {
		Document resultDoc = controller.getInstanceForm(userName, formName, dataId, formIsReadOnly);
		String result = DOMUtil.convertDocument2String(resultDoc, false);
		return result;
	}

	private String getInstanceSearch(String formName) {
		Document resultDoc = controller.getInstanceSearch(formName);
		String result = DOMUtil.convertDocument2String(resultDoc, false);
		return result;
	}

	private String getInstanceWorkflow(String userName, String formName) {
		Document resultDoc = controller.getInstanceWorkflow(userName, formName);
		String result = DOMUtil.convertDocument2String(resultDoc, false);
		return result;
	}

	private String getNodeContentInfo(String userName, String nodeId) {
		return controller.getNodeContentInfo(userName, nodeId);
	}

	private String getNodeType(String userName, String dataId) {

		return controller.getNodeType(userName, dataId);
	}

	private String getNodeTypeFull(String userName, String dataId) {
		String result = controller.getNodeTypeFull(userName, dataId);
		return jsonify(result);
	}

	private String getReadOnlyFormsSuffix() {
		return controller.getReadOnlyFormsSuffix();
	}

	private String getUnderlyingDataFormForWorkflow(String formName) {

		return controller.getUnderlyingDataFormForWorkflow(formName);
	}

	private String getUnderlyingTypeForForm(String formName) {
		return controller.getUnderlyingTypeForForm(formName);
	}

	private String getUnderlyingTypeForWorkflow(String formName) {
		return controller.getUnderlyingTypeForWorkflow(formName);
	}

	private String getWebscriptHelp(String userName) {
		// remove the exception when multi-line JSON becomes standard
		throw new IllegalArgumentException(
				"Javascript wrapper to BlueXML XForms Controller API: this function (getWebscriptHelp) is disabled in this Javascript wrapper.");
		// return controller.getWebscriptHelp(userName);
	}

	private String getWorkflowBlueXMLDefinitionName(String name) {
		return controller.getWorkflowBlueXMLDefinitionName(name);
	}

	private String getWorkflowBlueXMLTaskName(String formName) {
		return controller.getWorkflowBlueXMLTaskName(formName);
	}

	private String getWorkflowFormNameByTaskId(String fullTaskId) {
		return controller.getWorkflowFormNameByTaskId(fullTaskId);
	}

	private String getWorkflowFormNameFromTask(String taskName) {
		return controller.getWorkflowFormNameFromTask(taskName);
	}

	private String getWorkflowNamespaceURI(String processName) {
		return controller.getWorkflowNamespaceURI(processName);
	}

	private String getWorkflowRedirectionBean(String formName) {
		RedirectionBean resultBean = controller.getWorkflowRedirectionBean(formName);
		if (resultBean == null) {
			return JS_UNDEFINED;
		}
		StringBuffer result = new StringBuffer();
		result.append("{");
		result.append("\"targetUrl\": ").append(jsonify(resultBean.getTargetUrl()));
		result.append(",\"autoAdvance\": ").append(resultBean.isAutoAdvance());
		result.append(",\"addParams\": ").append(resultBean.isAddParams());
		result.append("}");
		return result.toString();
	}

	private String getWorkflowStartTaskFormName(String workflowDefName) {
		return controller.getWorkflowStartTaskFormName(workflowDefName);
	}

	private String getWorkflowTaskInfoBean(String wkFormName) {
		WorkflowTaskInfoBean resultBean = controller.getWorkflowTaskInfoBean(wkFormName);
		if (resultBean == null) {
			return JS_UNDEFINED;
		}
		StringBuffer result = new StringBuffer();
		result.append("{");
		result.append("\"actorId\": ").append(jsonify(resultBean.getActorId()));
		result.append(",\"dataFormName\": ").append(jsonify(resultBean.getDataFormName()));
		result.append(",\"formName\": ").append(jsonify(resultBean.getFormName()));
		result.append(",\"pooledActors\": ").append(jsonify(resultBean.getPooledActors()));
		result.append(",\"processTitle\": ").append(jsonify(resultBean.getProcessTitle()));
		result.append(",\"taskId\": ").append(jsonify(resultBean.getTaskId()));
		result.append(",\"title\": ").append(jsonify(resultBean.getTitle()));
		result.append("}");
		return result.toString();
	}

	private String getWorkflowTaskInfoBeanByTaskId(String taskId) {
		WorkflowTaskInfoBean resultBean = controller.getWorkflowTaskInfoBeanByTaskId(taskId);
		if (resultBean == null) {
			return JS_UNDEFINED;
		}
		StringBuffer result = new StringBuffer();
		result.append("{");
		result.append("\"actorId\": ").append(jsonify(resultBean.getActorId()));
		result.append(",\"dataFormName\": ").append(jsonify(resultBean.getDataFormName()));
		result.append(",\"formName\": ").append(jsonify(resultBean.getFormName()));
		result.append(",\"pooledActors\": ").append(jsonify(resultBean.getPooledActors()));
		result.append(",\"processTitle\": ").append(jsonify(resultBean.getProcessTitle()));
		result.append(",\"taskId\": ").append(jsonify(resultBean.getTaskId()));
		result.append(",\"title\": ").append(jsonify(resultBean.getTitle()));
		result.append("}");
		return result.toString();
	}

	private String isCustomForm(String formName) {
		return "" + controller.isCustomForm(formName);
	}

	private String isDefaultForm(String formName) {
		return "" + controller.isDefaultForm(formName);
	}

	private String isInStandaloneMode() {
		return "" + controller.isInStandaloneMode();
	}

	private String isSearchForm(String formName) {
		return "" + controller.isSearchForm(formName);
	}

	private String isStartTaskForm(String wkFormName) {
		return "" + controller.isStartTaskForm(wkFormName);
	}

	private String isWorkflowForm(String formName) {
		return "" + controller.isWorkflowForm(formName);
	}

	private String loadRedirectionTable(String filePath) {
		return "" + controller.loadRedirectionTable(filePath);
	}

	private String patchDataId(String dataId) {
		return controller.patchDataId(dataId);
	}

	private String performDynamicReload() {
		return "" + controller.performDynamicReload();
	}

	private String readObjectFromRepository(String userName, String dataId) throws ServletException {
		Document resultDoc = controller.readObjectFromRepository(userName, dataId);
		String result = DOMUtil.convertDocument2String(resultDoc, false);
		return result.replaceAll("\"", "\\\\\"");
	}

	private String setAlfrescoUrl(String alfrescoUrl) {
		controller.setAlfrescoUrl(alfrescoUrl);
		return JS_ACK;
	}

	private String setCssUrl(String cssUrl) {
		controller.setCssUrl(cssUrl);
		return JS_ACK;
	}

	private String setStandaloneMode(boolean standaloneMode) {
		controller.setStandaloneMode(standaloneMode);
		return JS_ACK;
	}

	private String systemGetAllAuthoritiesAsGroupsOrUsers(String userName, boolean asGroups) {
		Set<String> result = controller.systemGetAllAuthoritiesAsGroupsOrUsers(userName, asGroups);
		return "[" + StringUtils.join(jsonifyCollection(result), ',') + "]";
	}

	private String systemGetContainingGroups(String userName, String specificUserName) {
		Set<String> result = controller.systemGetContainingGroups(userName, specificUserName);
		return "[" + StringUtils.join(jsonifyCollection(result), ',') + "]";
	}

	private String systemGetNodeProperty(String userName, String propertyName, String nodeId) {
		return controller.systemGetNodeProperty(userName, propertyName, nodeId);
	}

	private String systemGetNodeRefForGroup(String userName, String groupName) {
		return controller.systemGetNodeRefForGroup(userName, groupName);
	}

	private String systemGetNodeRefForUser(String userName, String specificUserName) {
		return controller.systemGetNodeRefForUser(userName, specificUserName);
	}

	private String unpatchDataId(String dataId) {
		return controller.unpatchDataId(dataId);
	}

	private String workflowExtractNamespacePrefix(String name) {
		return controller.workflowExtractNamespacePrefix(name);
	}

	private String workflowExtractProcessNameFromDefName(String name) {
		return controller.workflowExtractProcessNameFromDefName(name);
	}

	private String workflowExtractProcessNameFromFormName(String formName) {
		return controller.workflowExtractProcessNameFromFormName(formName);
	}

	private String workflowExtractTaskNameFromFormName(String formName) {
		return controller.workflowExtractTaskNameFromFormName(formName);
	}

	private String workflowGetCurrentTasks(String userName, String instanceId) {
		List<String> result = controller.workflowGetCurrentTasks(userName, instanceId);
		return "[" + StringUtils.join(jsonifyCollection(result), ',') + "]";
	}

	private String workflowGetPackageContents(String userName, String taskId) {
		List<String> result = controller.workflowGetPackageContents(userName, taskId);
		return "[" + StringUtils.join(jsonifyCollection(result), ',') + "]";
	}

	private String workflowGetPooledTasks(String userName, String managerUserName) {
		List<String> result = controller.workflowGetPooledTasks(userName, managerUserName);
		return "[" + StringUtils.join(jsonifyCollection(result), ',') + "]";
	}

	//
	// leaves out: task.path.node and task.path.instance.definition
	//
	private String workflowGetTaskById(String userName, String taskId) {
		WorkflowTask task = controller.workflowGetTaskById(userName, taskId);
		if (task == null) {
			return JS_NULL;
		}
		StringBuffer res = new StringBuffer();
		Object propertyValue;
		if (formatter == null) {
			formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		}
		res.append("\"id\":").append(jsonify(task.id));
		res.append(",\"name\":").append(jsonify(task.name));
		res.append(",\"title\":").append(jsonify(task.title));
		res.append(",\"description\":").append(jsonify(task.description));
		res.append(",\"state\":").append(jsonify(task.state.toString()));
		res.append(",\"properties\":");
		if (task.properties == null) {
			res.append("null");
		} else {
			// begin object
			res.append("{");
			Map<?, ?> properties = task.properties;
			boolean first = true;
			for (Object property : properties.keySet()) {
				if (first == false) {
					res.append(",");
				}
				res.append("\"");
				res.append(property.toString());
				res.append("\":");
				propertyValue = properties.get(property);
				res.append(jsonify(propertyValue == null ? null : propertyValue.toString()));
				first = false;
			}
			res.append("}");
			// end object
		}
		// begin object
		res.append(",\"path\":{");
		res.append("\"id\":").append(jsonify(task.path.id));
		res.append(",\"active\":").append(task.path.active);
		// // begin object
		res.append(",\"instance\":{");
		res.append("\"id\":").append(jsonify(task.path.instance.id));
		res.append(",\"description\":").append(jsonify(task.path.instance.description));
		res.append(",\"active\":").append(task.path.instance.active);

		res.append(",\"context\":");
		propertyValue = task.path.instance.context;
		res.append(jsonify(propertyValue == null ? null : propertyValue.toString()));

		res.append(",\"initiator\":");
		propertyValue = task.path.instance.initiator;
		res.append(jsonify(propertyValue == null ? null : propertyValue.toString()));

		res.append(",\"startDate\":").append(
				jsonify(formatter.format(task.path.instance.startDate)));

		res.append(",\"endDate\":");
		propertyValue = task.path.instance.endDate;
		res.append(jsonify(propertyValue == null ? null : formatter.format(propertyValue)));

		res.append(",\"workflowPackage\":");
		propertyValue = task.path.instance.workflowPackage;
		res.append(jsonify(propertyValue == null ? null : propertyValue.toString()));

		res.append("}");
		// // end object
		res.append("}");
		// end object
		return res.toString();
	}

	//
	// CAUTION: the result is a fully jsonified object. The resulting string may be huge, possibly
	// larger than what the javascript engines in browsers can handle. It is NOT RECOMMENDED to use
	// this function via javascript.
	//
	private String workflowGetTaskDefinition(String userName, String processDefId, String task) {
		WorkflowTaskDefinition def = controller.workflowGetTaskDefinition(userName, processDefId,
				task);
		if (def == null) {
			return JS_NULL;
		}
		if (gSon == null) {
			gSon = new Gson();
		}

		return gSon.toJson(def);
	}

	private String workflowGetWorkflowById(String userName, String defId) {
		WorkflowDefinition def = controller.workflowGetWorkflowById(userName, defId);
		if (def == null) {
			return JS_NULL;
		}
		if (gSon == null) {
			gSon = new Gson();
		}

		return gSon.toJson(def);
	}

	private String workflowGetWorkflowsForContent(String userName, String nodeId, boolean onlyActive) {
		List<String> result = controller.workflowGetWorkflowsForContent(userName, nodeId,
				onlyActive);
		return "[" + StringUtils.join(jsonifyCollection(result), ',') + "]";
	}

	private String workflowIsBlueXMLDefinition(String name) {
		boolean result = controller.workflowIsBlueXMLDefinition(name);
		return "" + result;
	}

}
