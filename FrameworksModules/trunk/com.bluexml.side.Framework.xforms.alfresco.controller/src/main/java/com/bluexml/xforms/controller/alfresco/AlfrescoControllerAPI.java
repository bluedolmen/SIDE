/**
 * 
 */
package com.bluexml.xforms.controller.alfresco;

import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.workflow.WorkflowDefinition;
import org.alfresco.service.cmr.workflow.WorkflowInstance;
import org.alfresco.service.cmr.workflow.WorkflowService;
import org.alfresco.service.cmr.workflow.WorkflowTask;
import org.alfresco.service.cmr.workflow.WorkflowTaskDefinition;
import org.alfresco.service.namespace.QName;
import org.w3c.dom.Document;

import com.bluexml.xforms.controller.beans.RedirectionBean;
import com.bluexml.xforms.controller.beans.WorkflowTaskInfoBean;

/**
 * The public API of the BlueXML XForms component's controller for Alfresco.
 * <p/>
 * NOTES:
 * <ul>
 * <li>User name parameters refer to legitimate registered Alfresco users. There will be no
 * authentication performed so the caller <em>must</em> have verified the authentication credentials
 * before making the call to the function in question as, except for
 * {@link #authenticate(String, String)}, no password parameter is supported.</li>
 * <li>There is no notion of persistence attached to communications with the Alfresco server. All
 * communications are unit-communications. Thus, sessions, timeouts, keep-alives and all such
 * concepts are irrelevant.
 * <li>All ServletException's are thrown by functions whose offered functionality involves a
 * communication with the Alfresco server. An exception being thrown means that the functionality is
 * hindered in some way on the server side (server unreachable, invalid object id, etc.).</li>
 * </ul>
 * 
 * @see also {@link RedirectionBean}, {@link WorkflowTaskInfoBean}
 * @author Amenel
 * 
 */
public interface AlfrescoControllerAPI {

	public AlfrescoControllerAPI getController();

	/**
	 * @return the URL to the Alfresco host
	 */
	public String getAlfrescoUrl();

	/**
	 * Changes the URL to the Alfresco host. The host is initially set to the value defined in the
	 * forms.properties file.
	 * 
	 * @param alfresco_url
	 *            the URL to the Alfresco host to set. Must contain: protocol, hostname, port (if
	 *            relevant), and the Alfresco webapp's context name (which is usually
	 *            <code>alfresco</code>). For instance, "http://localhost:8080/alfresco".
	 */
	public void setAlfrescoUrl(String alfresco_url);

	/**
	 * Patches a node id to make it valid with respect to the usual Alfresco format. Ensures
	 * "workspace://SpacesStore/" is the prefix of the final id.
	 * 
	 * @param dataId
	 *            the node id, with or without protocol and store specified.
	 * 
	 * @return the patched id (may be the given id if that id was already patched).
	 */
	public String patchDataId(String dataId);

	/**
	 * Removes the protocol+store prefix from a full node id.
	 * 
	 * @param dataId
	 * @return the hex code part of the node id
	 */
	public String unpatchDataId(String dataId);

	/**
	 * Gets all custom forms, i.e. forms generated from Form Models.<br/>
	 * In case read-only versions are generated, <em>only</em> the read-write versions are listed:
	 * the list <em>does not</em> contain both the read-write and read-only versions of a form.
	 * 
	 * @returns the list of custom forms
	 */
	public List<String> getAllCustomForms();

	/**
	 * Gets all default forms, i.e. forms generated from Data Models.<br/>
	 * In case read-only versions are generated, <em>only</em> the read-write versions are listed.
	 * 
	 * @returns the list of default forms
	 */
	public List<String> getAllDefaultForms();

	/**
	 * Gets the list of all generated search forms.
	 * 
	 * @return the list of search forms
	 */
	public List<String> getAllSearchForms();

	/**
	 * Gets the list of all generated workflow forms.
	 * 
	 * @return the list of workflow forms
	 */
	public List<String> getAllWorkflowForms();

	/**
	 * Creates or loads the XForms instance document for a default class form.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user. <em>Must</em> be provided if the id of an
	 *            object is provided.
	 * @param formName
	 *            the id of a read-write form
	 * @param dataId
	 *            the node id of an object to load. If <b>null</b>, the form is empty (with the
	 *            exception of default values [from the data model]). If non null, the form is
	 *            filled with values from the object.
	 * @param formIsReadOnly
	 *            whether the instance is for the read only version of the form
	 * @param applyUserFormats
	 *            whether to use user formats (if <code>true</code>) or ISO 8601 (if
	 *            <code>false</code>) for read-only dates and times.
	 * 
	 * @return the default form instance document
	 * 
	 * @throws ServletException
	 */
	public Document getInstanceClass(String userName, String formName, String dataId,
			boolean formIsReadOnly, boolean applyUserFormats) throws ServletException;

	/**
	 * Creates or loads the XForms instance document for a FormClass (a customized form).<br/>
	 * <em>NOTE: user formats always apply.</em>
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user. <em>Must</em> be provided if the id of an
	 *            object is provided.
	 * @param formName
	 *            the form id of a read-write form
	 * @param dataId
	 *            the node id of an object to load. If <b>null</b>, the form is empty (with the
	 *            exception of default values [from the form model]). If non null, the form is
	 *            filled with values from the object.
	 * @param formIsReadOnly
	 *            whether the instance is for the read only version of the form
	 * 
	 * @return the custom form instance document
	 * 
	 * @throws ServletException
	 */
	public Document getInstanceForm(String userName, String formName, String dataId,
			boolean formIsReadOnly) throws ServletException;

	/**
	 * Returns an XForms instance document for a FormSearch.
	 * 
	 * @param formName
	 *            the id of a generated search form
	 * @return the search form instance document. A <code>null</code> value is never returned even
	 *         if the form name does not correspond to the id of a search form actually generated.
	 *         In this case, the document will not be filled.
	 */
	public Document getInstanceSearch(String formName);

	/**
	 * Returns an XForms instance document for a FormWorkflow.
	 * 
	 * @param formName
	 * @return the workflow form instance document. A <code>null</code> value is never returned.
	 * @throws ServletException
	 */
	public Document getInstanceWorkflow(String formName);

	/**
	 * Gets, on behalf of a user, a document filled with the properties of an existing repository
	 * object.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user.
	 * @param dataId
	 *            the node id of an object that exists in the repository
	 * @return a document containing all properties (including system properties) of the object.
	 * @throws ServletException
	 */
	public Document readObjectFromRepository(String userName, String dataId)
			throws ServletException;

	/**
	 * Gets the local part of the node type as returned by Alfresco.
	 * 
	 * @param dataId
	 *            the node id, with or without the protocol and store.
	 * @return the local name, or <code>null</code> if the node does not exist or an exception
	 *         occurred
	 */
	public String getNodeType(String dataId);

	/**
	 * Gets the full node type as returned by Alfresco, i.e. including namespace URI and local part.
	 * 
	 * @param dataId
	 *            the node id, with or without the protocol and store.
	 * @return the node type, or <code>null</code> if the node does not exist or an exception
	 *         occurred
	 */
	public String getNodeTypeFull(String dataId);

	/**
	 * Tells whether a custom form with the given id exists.
	 * 
	 * @param formName
	 *            the form id
	 * @return true if a FormClass exists with that id.
	 */
	public boolean isCustomForm(String formName);

	/**
	 * Tells whether a default form with the given id exists.
	 * 
	 * @param formName
	 *            the form id
	 * @return true if a form (generated from data models) exists with that id.
	 */
	public boolean isDefaultForm(String formName);

	/**
	 * Tells whether a search form with the given id exists.
	 * 
	 * @param formName
	 *            the form id
	 * @return true if a FormSearch exists with that id.
	 */
	public boolean isSearchForm(String formName);

	/**
	 * Tells whether a workflow form with the given id exists.
	 * 
	 * @param formName
	 *            the form id
	 * @return true if a FormWorkflow exists with that id.
	 */
	public boolean isWorkflowForm(String formName);

	/**
	 * Tells whether the form supports a start task.
	 * 
	 * @param wkFormName
	 *            the form id
	 * @return true if the form exists as a workflow form and the form supports a start task.
	 */
	public boolean isStartTaskForm(String wkFormName);

	/**
	 * Returns the redirection bean for a specific workflow form.
	 * 
	 * @param formName
	 *            the name of the form (e.g. Evaluation_Demarrage)
	 * @see {@link RedirectionBean}
	 */
	public RedirectionBean workflowGetRedirectionBean(String formName);

	/**
	 * Loads the redirection configuration from the file path. If the path is invalid, the previous
	 * configuration remains active.
	 * 
	 * @param filePath
	 *            the complete path to the redirection file, with path, name and extension
	 * @return <code>true</code> if the file was loaded.
	 */
	public boolean loadRedirectionTable(String filePath);

	/**
	 * Provides the id of a FormClass that supports the given data type.
	 * 
	 * @param dataType
	 *            the local part of a node type as returned by {@link #getNodeType(String)} or by
	 *            the Alfresco API.
	 * @return a custom form id, or <code>null</code> if none is found.
	 */
	public String getCustomFormForDatatype(String dataType);

	/**
	 * Provides the id of the first default form that supports the given data type.
	 * 
	 * @param dataType
	 *            the local part of a node type as returned by {@link #getNodeType(String)} or by
	 *            the Alfresco API.
	 * @return a default form id, or <code>null</code> if none is found.
	 */
	public String getDefaultFormForDatatype(String dataType);

	/**
	 * Provides the name of the data type supported by the FormClass with the given id.
	 * 
	 * @param formName
	 *            the valid id of a FormClass that has been generated.
	 * @return the data type as defined in the class model, which is also what would be returned by
	 *         {@link #getNodeType(String)} for a node created used the given form. Returns
	 *         <code>null</code> if the form name is unknown.
	 */
	public String getUnderlyingTypeForForm(String formName);

	/**
	 * Provides the name of the data type supported by the data form of the FormWorkflow with the
	 * given id.
	 * 
	 * @param formName
	 *            the valid id of a FormWorkflow that has been generated.
	 * @return the data type of the workflow form's data form, as defined in the class model, and as
	 *         would be returned by {@link #getUnderlyingTypeForForm(String)} for the data form.
	 *         Returns <code>null</code> if the form name is unknown.
	 */
	public String getUnderlyingTypeForWorkflow(String formName);

	/**
	 * Provides the id of the data form linked to the given workflow form.
	 * 
	 * 
	 * @param formName
	 *            the valid id of a FormWorkflow that has been generated.
	 * @return the id of the data form.
	 */
	public String getUnderlyingDataFormForWorkflow(String formName);

	/**
	 * Provides a pool of information about the form whose name is given.
	 * 
	 * @param wkFormName
	 *            the id of a valid workflow form.
	 * @return the information bean.
	 * @see {@link WorkflowTaskInfoBean}
	 */
	public WorkflowTaskInfoBean getWorkflowTaskInfoBean(String wkFormName);

	/**
	 * Provides a pool of information about the form that supports the given task id.
	 * 
	 * @param taskId
	 *            the id of a task.
	 * @return the information bean.
	 * @see {@link WorkflowTaskInfoBean}
	 */
	public WorkflowTaskInfoBean getWorkflowTaskInfoBeanByTaskId(String taskId);

	/**
	 * Dynamically reloads all reloadable info (such as the mapping.xml file) except properties
	 * files.
	 * 
	 * @return <code>false</code> if an error occurred, <code>true</code> otherwise.
	 */
	public boolean performDynamicReload();

	//
	// WEBSCRIPT
	//

	/**
	 * Retrieves some information about the content of an existing node and provides a user-readable
	 * version of the info collected if the node has a defined content. <br/>
	 * <p>
	 * <em>NOTE: a K is 1024</em>
	 * </p>
	 * For now, gets:
	 * <ul>
	 * <li>node name as displayed via a web client</li>
	 * <li>the localized content size, as number of bytes and readable file size. e.g. "7 614 525
	 * bytes (7.26 MB)"</li>
	 * </ul>
	 * 
	 * @param nodeId
	 *            the full node Id (including protocol and store)
	 * @return the info about a content formatted as indicated by the relevant format in
	 *         messages.properties, or <code>null</code> if an exception occurred, or empty if no
	 *         content is associated with the node.
	 */
	public String getNodeContentInfo(String nodeId);

	/**
	 * Gets the string that the webscript's help operation provides.
	 * 
	 * @return the string, as provided by the web script
	 */
	public String getWebscriptHelp();

	/**
	 * Tests authentication credentials with the current Alfresco host (defined in the properties
	 * file or via {@link #setAlfrescoUrl(String)}).
	 * 
	 * @param username
	 *            the user name, which must be known to the Alfresco host for the authentication to
	 *            succeed
	 * @param password
	 *            the plain password
	 * @return true if the authentication succeeded, false otherwise or in case of exception.
	 */
	public boolean authenticate(String username, String password);

	/**
	 * Creates a folder in the repository, creating the missing parents as needed.
	 * 
	 * @param folderPath
	 *            a standard XPath expression of a path in the repository, e.g.
	 *            "/app:company_home/app:dictionary/cm:Countries/cm:Europe/cm:Finland"
	 * @param userName
	 *            the user on behalf of whom the operation is conducted
	 * @return the full node id of the terminal folder ('Finland' in the example above).
	 * @throws ServletException
	 */
	public String createPathInRepository(String folderPath, String userName)
			throws ServletException;

	//
	//
	// Bridges to Alfresco API functions via the webscript.
	//
	//

	/**
	 * Gets the set of authorities that exist in the current Alfresco host.
	 * 
	 * @param asGroups
	 *            if true, specifies that only groups, including standard Alfresco groups, are
	 *            returned. If false, only users. To have the full set of users and groups, this
	 *            function must be called twice.
	 * @return the set of registered authorities. Returns <b>null</b> if an exception occurred or if
	 *         the value is <b>null</b> by itself.
	 */
	public Set<String> systemGetAllAuthoritiesAsGroupsOrUsers(boolean asGroups);

	/**
	 * Returns all groups a specific user belongs to, including non immediate parent groups.
	 * 
	 * @param userName
	 *            the name of the user.
	 * @return the set of groups the user is part of. Returns <b>null</b> if an exception occurred
	 *         or if the value is <b>null</b> by itself.
	 */
	public Set<String> systemGetContainingGroups(String userName);

	/**
	 * Gets the value of a property for a node.
	 * 
	 * @param node
	 *            a node reference
	 * @param propertyName
	 *            the qualified name of the property
	 * @return the value of the property for the node. Returns <b>null</b> if an exception occurred
	 *         or if the value is <b>null</b>.
	 */
	public String systemGetNodeProperty(NodeRef node, QName propertyName);

	/**
	 * Returns the node ref for a user identified by name. If no user with that name exists, the
	 * corresponding authority will not be created.
	 * 
	 * @param userName
	 *            the name of the user.
	 * @return the noderef for the user name. Returns <b>null</b> if an exception occurred or if the
	 *         value returned by Alfresco is <b>null</b>.
	 */
	public NodeRef systemGetNodeRefForUser(String userName);

	/**
	 * Returns the node ref for a user group identified by name.
	 * 
	 * @param groupName
	 *            the group name as can be seen in Alfresco's web client. The system prefix for
	 *            groups will be automatically prepended before calling the web script.
	 * @return the noderef for the user group. Returns <b>null</b> if an exception occurred or if
	 *         the group does not exist.
	 */
	public NodeRef systemGetNodeRefForGroup(String groupName);

	/**
	 * Gets the type of a node as a QName. See the companion {@link #getNodeTypeFull(String)} for a
	 * string version of that QName.
	 * 
	 * @param dataId
	 *            the node's id, with or without the protocol and store.
	 * @return the QName that corresponds to the node's content type. Returns <b>null</b> if an
	 *         exception occurred or if the value is <b>null</b>, which is a hint that either the
	 *         Alfresco server is not available or the object does not exist.
	 */
	public QName systemGetNodeType(String dataId);

	//
	//
	// Workflow-related
	//
	//

	/**
	 * Gets the list of task definitions associated with a process.
	 * 
	 * @param processDefId
	 *            the id of a process
	 * @return the list of task definitions
	 */
	public List<WorkflowTaskDefinition> workflowGetTaskDefinitions(String processDefId);

	/**
	 * Gets the list of workflow instances that apply to a node and meet the required status.
	 * 
	 * @param refStr
	 *            the full node id (including protocol and store) of a repository content item
	 * @param onlyActive
	 *            the status that returned workflows must be in. <code>true</code> means "active",
	 *            <code>false</code> means "completed"
	 * @return the list of workflow instances
	 */
	public List<WorkflowInstance> workflowGetWorkflowsForContent(String refStr, boolean onlyActive);

	/**
	 * Gets the workflow definition for the given workflow id
	 * 
	 * @param defId
	 *            the workflow definition id
	 * @return the definition, as returned by the Alfresco API
	 */
	public WorkflowDefinition workflowGetWorkflowById(String defId);

	/**
	 * Returns the form name for a task, based on the full task id.
	 * 
	 * @param fullTaskId
	 *            the task id as generated by the BlueXML workflow generator, e.g. "wfbxwfTest:T1"
	 * @return the id of the workflow form that can be used for the task, or <code>null</code> if no
	 *         such form exists
	 */
	public String getWorkflowFormNameByTaskId(String fullTaskId);

	/**
	 * Gets the task name from the form name.
	 * 
	 * @param formName
	 *            the id of a workflow form
	 * @return the name of the task, which cannot be <code>null</code> or empty.
	 */
	public String workflowExtractTaskNameFromFormName(String formName);

	/**
	 * Gets the process name from the form name.<br/>
	 * NOTE: a duplicate of this function is also defined in MappingGenerator
	 * 
	 * @param formName
	 *            the id of a workflow form
	 * @return the name of the process. Cannot be <code>null</code> or empty.
	 */
	public String workflowExtractProcessNameFromFormName(String formName);

	/**
	 * Returns the namespace prefix part of a generated workflow from a WorkflowDefinition.name. In
	 * case the name does not have the expected format, the parameter is returned as is.
	 * 
	 * @param name
	 *            (e.g. "jbpm$wfbxEvaluation:Evaluation")
	 * @return the local name of the process (e.g. "wfbxEvaluation")
	 */
	public String workflowExtractNamespacePrefix(String name);

	/**
	 * Tells whether a workflow definition fits the definition name of processes generated via the
	 * BlueXML workflow modeler and generator.
	 * 
	 * @param name
	 *            a process definition name
	 * @return true if the definition name is a candidate.
	 */
	public boolean workflowIsBlueXMLDefinition(String name);

	/**
	 * Gets the process name from WorkflowDefinition name.
	 * 
	 * @param name
	 *            a process definition name
	 * @return the local name of the process (e.g. "jbpm$wf:review" -> "review")
	 */
	public String workflowExtractProcessNameFromDefName(String name);

	/**
	 * Gives the name under which a process is known by the workflow engine under Alfresco. <br/>
	 * This matches the name construction rules that are used by the BlueXML workflow generator.
	 * 
	 * @param name
	 *            a process definition name
	 * @return the process definition as should be found in WorkflowDefinition.name.
	 */
	public String workflowBuildBlueXMLDefinitionName(String name);

	/**
	 * Gives the name under which a task is defined in the process definition model. <br/>
	 * This matches the name construction rules that are used by the BlueXML workflow generator.
	 * 
	 * @param formName
	 *            the id of a workflow form
	 * @return the task name as should be found in the generated model.xml or processDefintion.xml.
	 */
	public String workflowBuildBlueXMLTaskName(String formName);

	/**
	 * Gives the name of the form that corresponds to a task name.
	 * 
	 * @param taskName
	 *            the task id, with or without the "jbpm$" prefix, e.g. "wfbxEvaluation:Annotation"
	 *            or "jbpm$wfbxEvaluation:Annotation"
	 * @return the id of a workflow form
	 */
	public String workflowBuildFormNameFromTask(String taskName);

	/**
	 * Builds the namespace URI for workflow models generated with SIDE.<br/>
	 * This matches the name construction rules that are used by the BlueXML workflow generator.
	 * 
	 * @param processName
	 *            the token that is used as process name in the BlueXML workflow modeler.
	 * @return the namespace URI
	 */
	public String getWorkflowNamespaceURI(String processName);

	/**
	 * Retrieves the name/id of a form that implements the start task for a workflow definition
	 * name.<br/>
	 * 
	 * @param workflowDefName
	 *            the definition name as provided by {@link WorkflowService#getDefinitions()}, e.g.
	 *            "jbpm$wfbxwfTest:wfTest"
	 * @return the id of a form, e.g. "wfTest_Start"
	 */
	public String getWorkflowStartTaskFormName(String workflowDefName);

	/**
	 * Gets a list of in-progress workflow tasks for a user.
	 * 
	 * @param userName
	 *            the login name of the user
	 * @return the list
	 */
	public List<WorkflowTask> workflowGetPooledTasks(String userName);

	/**
	 * Gets the WorkflowTask object for the given task id.
	 * 
	 * @param taskId
	 *            the task id
	 * @return the requested task object, or <b>null</b> if not found
	 */
	public WorkflowTask workflowGetTaskById(String taskId);

	/**
	 * Gets the contents of the workflow package for the given task id.
	 * 
	 * @param taskId
	 *            the task id
	 * @return the list of items associated with the task's workflow package
	 */
	public List<NodeRef> workflowGetPackageContents(String taskId);

	/**
	 * Retrieves a specific task definition for a given process Id.
	 * 
	 * @param processDefId
	 *            the id of a process, e.g. "jbpm$6"
	 * @param task
	 *            the id of a task to search for in the definition, e.g. "wfbxdemande:Accepter"
	 * @return
	 */
	public WorkflowTaskDefinition workflowGetTaskDefinition(String processDefId, String task);

	/**
	 * Retrieves all in-progress tasks found for the instance. Since several paths may be associated
	 * with the instance, each active path may provide tasks.
	 * 
	 * @param instanceId
	 *            the id of a live workflow instance
	 * @return the list (possibly empty if an exception occurred) of in-progress tasks.
	 * @throws ServletException
	 */
	public List<WorkflowTask> workflowGetCurrentTasks(String instanceId);

	//
	// General
	//
	/**
	 * Tells whether the controller is in debug mode.
	 * 
	 * @return the debug mode status. See the documentation for the implications of this mode.
	 */
	public boolean isInDebugMode();

	/**
	 * Gets the suffix appended to read only forms.
	 * 
	 * @return the suffix appended to names of read-write forms to produce the names of the
	 *         read-only versions of the same forms.
	 */
	public String getReadOnlyFormsSuffix();

	/**
	 * Gets the URL for the CSS file that was previously set.
	 * 
	 * @return the cssUrl
	 */
	public String getCssUrl();

	/**
	 * Sets a CSS file URL that will be added in the head section of all forms at serving time. The
	 * URL applies from the moment it is set: setting it for one form sets it for all subsequent
	 * forms until the URL is changed.
	 * 
	 * @param cssUrl
	 *            the cssUrl to set. If <code>null</code>, the adding of the CSS URL to forms is
	 *            disabled.
	 */
	public void setCssUrl(String cssUrl);

	/**
	 * Sets the controller in or out of the stand alone mode.
	 * 
	 * @param standaloneMode
	 *            the standalone status to set: true=ON and false=OFF.
	 */
	public void setStandaloneMode(boolean standaloneMode);

	/**
	 * Tells whether the controller is in stand alone mode. If so, the Alfresco server is considered
	 * unavailable and all reads and writes are either simulated or disabled.
	 * 
	 * @return the standalone status
	 */
	public boolean isInStandaloneMode();

}
