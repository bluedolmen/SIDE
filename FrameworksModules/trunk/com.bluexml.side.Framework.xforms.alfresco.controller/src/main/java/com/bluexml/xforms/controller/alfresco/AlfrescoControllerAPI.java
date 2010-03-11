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
 * 
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
	 *            the URL to the Alfresco host to set
	 */
	public void setAlfrescoUrl(String alfresco_url);

	/**
	 * Patches a node id to make a valid {@link NodeRef} format. Adds "workspace://SpacesStore/" as
	 * a prefix to dataId.
	 * 
	 * @param dataId
	 *            the data id, with or without protocol and store specified.
	 * 
	 * @return the patched id (may be the given id if that id was already patched).
	 */
	public String patchDataId(String dataId);

	/**
	 * Removes the protocol+store prefix from a full node id.
	 * 
	 * @param dataId
	 * @return the hex code part of the id
	 */
	public String unpatchDataId(String dataId);

	/**
	 * Gets all custom forms, i.e. forms generated from Form Models. <br/>
	 * In case read-only versions are generated, <em>only</em> the read-write versions are listed:
	 * the list <em>does not</em> contain both the read-write and read-only versions of a form.
	 * 
	 * @returns the list of custom forms
	 */
	public List<String> getAllCustomForms();

	public List<String> getAllDefaultForms();

	public List<String> getAllSearchForms();

	public List<String> getAllWorkflowForms();

	/**
	 * Creates or loads the XForms instance document for a default class form.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user. There will be no authentication performed
	 *            so the caller must have verified the authentication credentials. <em>Must</em> be
	 *            provided if the id of an object is provided.
	 * @param formName
	 *            the content type (also the id of the read-write form)
	 * @param dataId
	 *            the node id
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
	public Document getInstanceClass(AlfrescoTransaction transaction, String formName,
			String dataId, boolean formIsReadOnly, boolean applyUserFormats)
			throws ServletException;

	/**
	 * Creates or loads the XForms instance document for a FormClass (a customized form).<br/>
	 * NOTE: user formats always apply.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user. There will be no authentication performed
	 *            so the caller must have verified the authentication credentials. <em>Must</em> be
	 *            provided if the id of an object is provided.
	 * @param formName
	 *            the form id (also the id of the read-write form)
	 * @param id
	 *            the id of the object to load. If <b>null</b>, the form is empty (with the
	 *            exception of default values [from the data model]. If non null, the form is filled
	 *            with values from the object.
	 * @param formIsReadOnly
	 *            whether the instance is for the read only version of the form
	 * 
	 * @return the custom form instance document
	 * 
	 * @throws ServletException
	 */
	public Document getInstanceForm(String userName, String formName, String id,
			boolean formIsReadOnly) throws ServletException;

	/**
	 * Returns an XForms instance document for a FormSearch.
	 * 
	 * @param formName
	 *            the form Id
	 * @return the search form instance document
	 */
	public Document getInstanceSearch(String formName);

	/**
	 * Returns an XForms instance document for a FormWorkflow.
	 * 
	 * @param formName
	 * @return the workflow form instance document
	 * @throws ServletException
	 */
	public Document getInstanceWorkflow(String formName) throws ServletException;

	public Document readObjectFromRepository(AlfrescoTransaction transaction, String id)
			throws ServletException;

	/**
	 * Gets the local part of the node type as returned by Alfresco.
	 * 
	 * @param dataId
	 *            the node's id, with or without the protocol and store.
	 * @return the local name, or <code>null</code> if the node does not exist or an exception
	 *         occurred
	 */
	public String getNodeType(String dataId);

	/**
	 * Gets the full node type as returned by Alfresco, i.e. including namespace URI and local part.
	 * 
	 * @param dataId
	 *            the node's id, with or without the protocol and store.
	 * @return the node typeor <code>null</code> if the node does not exist or an exception occurred
	 */
	public String getNodeTypeFull(String nodeId);

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
	 * @return true if a search form exists with that id.
	 */
	public boolean isSearchForm(String formName);

	/**
	 * Tells whether a workflow form with the given id exists.
	 * 
	 * @param formName
	 *            the form id
	 * @return true if a workflow form exists with that id.
	 */
	public boolean isWorkflowForm(String formName);

	/**
	 * Tells whether the form supports a start task.
	 * 
	 * @param wkFormName
	 * @return true if the form exists as a workflow form and the form supports a start task.
	 */
	public boolean isStartTaskForm(String wkFormName);

	/**
	 * Returns the redirection bean for a specific workflow form.
	 * 
	 * @param formName
	 *            the name of the form (e.g. Evaluation_Demarrage)
	 */
	public RedirectionBean workflowGetRedirectionBean(String formName);

	/**
	 * Loads the redirection configuration from the file path. If the path is invalid, the previous
	 * configuration remains active.
	 * 
	 * @param filePath
	 * @return
	 */
	public boolean loadRedirectionTable(String filePath);

	/**
	 * Provides the id of a FormClass that supports the given data type.
	 * 
	 * @param dataType
	 * @return
	 */
	public String getCustomFormForDatatype(String dataType);

	/**
	 * Provides the id of the first default form that supports the given data type.
	 * 
	 * @param dataType
	 * @return
	 */
	public String getDefaultFormForDatatype(String dataType);

	/**
	 * Provides the name of the data type supported by the FormClass with the given id.
	 * 
	 * @param formName
	 *            the valid id of a FormClass that has been generated.
	 * @return the data type as defined in the class model, or <code>null</code> if the form name is
	 *         unknown.
	 */
	public String getUnderlyingClassForForm(String formName);

	/**
	 * Provides the name of the data type supported by the data form of the FormWorkflow with the
	 * given id.
	 * 
	 * @param formName
	 *            the valid id of a FormWorkflow that has been generated.
	 * @return the data type of the workflow form's data form, as defined in the class model, or
	 *         <code>null</code> if the form name is unknown.
	 */
	public String getUnderlyingClassForWorkflow(String formName);

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
	 * @see WorkflowTaskInfoBean
	 */
	public WorkflowTaskInfoBean getWorkflowTaskInfoBean(String wkFormName);

	/**
	 * Provides a pool of information about the form that supports the given task id.
	 * 
	 * @param taskId
	 *            the id of a task.
	 * @return the information bean.
	 */
	public WorkflowTaskInfoBean getWorkflowTaskInfoBeanByTaskId(String taskId);

	/**
	 * Retrieves some information about the content of an existing node and provides a user-readable
	 * version of the collected info if the node has a defined content. <br/>
	 * For now, gets:
	 * <ul>
	 * <li>node name as displayed via a web client</li>
	 * <li>the localized content size, as number of bytes and readable file size. e.g. "7 614 525
	 * bytes (7.26 MB)"</li>
	 * </ul>
	 * 
	 * @param nodeId
	 *            the full node Id (including protocol and workspace)
	 * @return the info about a content formatted as indicated by the relevant format in
	 *         messages.properties, or <code>null</code> if an exception occurred, or empty if no
	 *         content is associated with the node.
	 */
	public String getWebscriptNodeContentInfo(String nodeId);

	/**
	 * Dynamically reloads all (class-related) info (such as the mapping.xml file).
	 * 
	 * @return false if an exception occurred
	 */
	public boolean performDynamicReload();

	//
	// WEBSCRIPT
	//
	/**
	 * Gets the string that the webscript's help operation provides.
	 * 
	 * @return the string, as returned by the web script
	 */
	public String getWebscriptHelp();

	/**
	 * Tests authentication credentials with the current Alfresco host (defined in the properties
	 * file or via {@link #setAlfrescoUrl(String)}).
	 * 
	 * @param username
	 *            the user name, which must be known to the Alfresco host for this function to
	 *            return <code>true</code>
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
	 *             if the operation could not be performed
	 */
	public String createPathInRepository(String folderPath, String userName)
			throws ServletException;

	//
	//
	// Bridges to Alfresco API functions via the webscript.
	//
	//

	/**
	 * Returns all known groups, including standard Alfresco groups.
	 * 
	 * @param asGroups
	 *            if true, specifies that only groups are returned. If false, only users. To have
	 *            the full set of users and groups, this function must be called twice.
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
	 * @return the value of the property for the node. Returns <b>null</b> if an exception occurred
	 *         or if the value is <b>null</b>.
	 */
	public String systemGetNodeProperty(NodeRef node, QName propertyName);

	/**
	 * Returns the node ref for a user identified by name. If no user with that name exists, the
	 * authority will not be created.
	 * 
	 * @param userName
	 * @return the noderef for the user name. Returns <b>null</b> if an exception occurred or if the
	 *         value is <b>null</b>.
	 */
	public NodeRef systemGetNodeRefForUser(String userName);

	/**
	 * Returns the node ref for a user group identified by name.
	 * 
	 * @param groupName
	 *            the group name as can be seen in Alfresco's web client. The system prefix for
	 *            groups will be prepended before calling the web script.
	 * @return the noderef for the user group. Returns <b>null</b> if an exception occurred or if
	 *         the group does not exist.
	 */
	public NodeRef systemGetNodeRefForGroup(String groupName);

	/**
	 * Gets the type of a node.
	 * 
	 * @param dataId
	 *            the node's id, with or without the protocol and store.
	 * @return the QName that corresponds to the content type. Returns <b>null</b> if an exception
	 *         occurred or if the value is <b>null</b>, which is a hint that either the webscript is
	 *         not available or the object does not exist.
	 */
	public QName systemGetNodeType(String dataId);

	//
	//
	// Workflow-related
	//
	//

	public List<WorkflowTaskDefinition> workflowGetTaskDefinitions(String processDefId);

	public List<WorkflowInstance> workflowGetWorkflowsForContent(String refStr, boolean onlyActive);

	public WorkflowDefinition workflowGetWorkflowById(String defId);

	/**
	 * Returns the form name for a task based on the full task id.
	 * 
	 * @param fullTaskId
	 *            e.g. "wfbxwfTest:T1"
	 * @return the id of the workflow form that can be used for the task, or <code>null</code> if no
	 *         such form exists
	 */
	public String getWorkflowFormNameByTaskId(String fullTaskId);

	/**
	 * Gets the task name from the form name.
	 * 
	 * @param formName
	 * @return the name of the process (e.g. "Evaluation_Demarrage" -> "Demarrage")
	 */
	public String workflowExtractTaskNameFromFormName(String formName);

	/**
	 * Gets the process name from the form name.<br/>
	 * NOTE: a duplicate of this function is also defined in MappingGenerator
	 * 
	 * @param formName
	 * @return the name of the process (e.g. "Evaluation_Demarrage" -> "Evaluation")
	 */
	public String workflowExtractProcessNameFromFormName(String formName);

	/**
	 * Returns the namespace part of a generated workflow from a WorkflowDefinition.name. In case
	 * the process was not defined through the workflow modeler, the parameter is returned as is.
	 * 
	 * @param name
	 *            (e.g. "jbpm$wfbxEvaluation:Evaluation")
	 * @return the local name of the process (e.g. "wfbxEvaluation")
	 */
	public String workflowExtractNamespacePrefix(String name);

	/**
	 * Tells whether a workflow definition fits the definition name of processes generated via the
	 * modeler.
	 * 
	 * @param name
	 * @return true if the definition name is a candidate.
	 */
	public boolean workflowIsBlueXMLDefinition(String name);

	/**
	 * Gets the process name from WorkflowDefinition name.
	 * 
	 * @param name
	 * @return the local name of the process (e.g. "jbpm$wf:review" -> "review")
	 */
	public String workflowExtractProcessNameFromDefName(String name);

	/**
	 * Gives the name under which a process is known by the workflow engine under Alfresco. <br/>
	 * Used when reacting to a workflow transition action. <br/>
	 * e.g. "Evaluation" --> "jbpm$wfbxEvaluation:Evaluation"
	 * 
	 * @param processName
	 * @return the process definition as should be found in WorkflowDefinition.name.
	 */
	public String workflowBuildBlueXMLDefinitionName(String processName);

	/**
	 * Gives the name under which a task is defined in the process definition model. <br/>
	 * e.g. "Evaluation_Annotation" --> "wfbxEvaluation:Annotation"<br/>
	 * NOTE: a duplicate of this function is also defined in MappingGenerator
	 * 
	 * @param formName
	 * @return the task name as should be found in the generated model.xml or processDefintion.xml.
	 */
	public String workflowBuildBlueXMLTaskName(String formName);

	/**
	 * Gives the name of the form that corresponds to a task name.<br/>
	 * e.g. "wfbxEvaluation:Annotation" --> "Evaluation_Annotation"<br/>
	 * e.g. "jbpm$wfbxEvaluation:Annotation" --> "Evaluation_Annotation"<br/>
	 * 
	 * @param taskName
	 * @return the form name.
	 */
	public String workflowBuildFormNameFromTask(String taskName);

	/**
	 * Builds the namespace URI for workflow models generated with SIDE.
	 * 
	 * @param processName
	 * @return
	 */
	public String workflowBuildNamespaceURI(String processName);

	/**
	 * Retrieves the name/id of a form that implements the start task for a workflow definition
	 * name.<br/>
	 * 
	 * @param workflowDefName
	 *            the definition name as provided by {@link WorkflowService#getDefinitions()}, e.g.
	 *            jbpm$wfbxwfTest:wfTest
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
	public List<WorkflowTask> workflowGetPooledTasks(String userName);// FIXME: add connectionUser ?

	/**
	 * Gets the WorkflowTask object for the given task id.
	 * 
	 * @param taskId
	 * @return the requested task object, or <b>null</b> if not found
	 */
	public WorkflowTask workflowGetTaskById(String taskId);

	/**
	 * Gets the contents of the workflow package for the given task id.
	 * 
	 * @param taskId
	 * @return the list of items associated with the task's workflow package
	 */
	public List<NodeRef> workflowGetPackageContents(String taskId);

	/**
	 * Retrieves a specific task definition for a given process Id.
	 * 
	 * @param processDefId
	 * @param task
	 *            the task to search for in the definition
	 * @return
	 */
	public WorkflowTaskDefinition workflowGetTaskDefinition(String processDefId, String task);

	/**
	 * Retrieves all in-progress tasks found for the instance. Since several paths may be associated
	 * with the instance, each active path may provide tasks.
	 * 
	 * @param path
	 * @return the list (possibly empty if exception) of in-progress tasks.
	 * @throws ServletException
	 */
	public List<WorkflowTask> workflowGetCurrentTasks(String instanceId);

	//
	// General
	//
	/**
	 * Tells whether the controller is in debug mode.
	 * 
	 * @return the debug mode status. See the documentation for the implications.
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
	 * URL applies from the moment it is set. In other words, setting it for one form sets it for
	 * all subsequent forms until the URL is changed.
	 * 
	 * @param cssUrl
	 *            the cssUrl to set. If <code>null</code>, adding the CSS URL is disabled.
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
