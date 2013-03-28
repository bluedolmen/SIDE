/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.framework.alfresco.webscriptExtension;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.repo.jscript.BaseScopableProcessorExtension;
import org.alfresco.repo.jscript.ScriptNode;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.workflow.WorkflowDefinition;
import org.alfresco.service.cmr.workflow.WorkflowInstance;
import org.alfresco.service.cmr.workflow.WorkflowPath;
import org.alfresco.service.cmr.workflow.WorkflowService;
import org.alfresco.service.cmr.workflow.WorkflowTask;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

/**
 * Extend Alfresco Javascript API with rule service
 * siderule.getTasksDescriptionOfDocument(document) to get the workflow task on a document, including subprocess
 */
public class WorkflowScriptExtension extends BaseScopableProcessorExtension {

	private Logger logger = Logger.getLogger(getClass());


	/**
	 * Method to get the associated tasks of a document, including those in a subprocess
	 * 
	 * @param document
	 *            the document to get all the active tasks
	 * @param namespace 
	 * 			  to reduce the list of workflows of this namespace
	 *            
	 * @return list of string of the form "<definition_id>/<instance_id>/[<task_id>]", task may be not present if subprocess
	 */
	public List<String> getTasksDescriptionOfDocument(ScriptNode document, String namespace) {
        if (logger.isDebugEnabled()) logger.debug("Process document "+document.getNodeRef());
		ArrayList<String> concernedTasks = new ArrayList<String>();
		ArrayList<String> concernedInstances = new ArrayList<String>();
		for (WorkflowDefinition def : workflowService.getAllDefinitions()) {
			if (namespace == null || def.getName().startsWith(namespace)) {
				List<WorkflowInstance> instances = workflowService.getActiveWorkflows(def.getId());
				for (WorkflowInstance instance : instances) {
			         if (logger.isDebugEnabled()) logger.debug("Process instance "+instance.toString()+" of workflow defintion " + def.getName());
					 NodeRef pkg = instance.getWorkflowPackage();
					 if (pkg != null) {
				         if (logger.isDebugEnabled()) logger.debug("Process package " + pkg);
						 List<ChildAssociationRef> docs = serviceRegistry.getNodeService().getChildAssocs(pkg);
						 if (docs != null && !docs.isEmpty()) {
							 NodeRef pkg0 = docs.get(0).getChildRef();
					         if (logger.isDebugEnabled()) logger.debug("Process package child " + pkg0);
							 if (pkg0.equals(document.getNodeRef())) {
								 // this instance is based on the document; we must get its tasks if any
								 List<WorkflowPath> paths = workflowService.getWorkflowPaths(instance.getId());
								 for (WorkflowPath path : paths) {
							         if (logger.isDebugEnabled()) logger.debug("Process path " + path.getId());
									 List<WorkflowTask> tasks = workflowService.getTasksForWorkflowPath(path.getId());
									 for (WorkflowTask task : tasks) {
								         if (logger.isDebugEnabled()) logger.debug("Return tasks " + task.getName()+" - "+task.getId());
										 String st = def.getId()+"/"+instance.getId()+"/"+task.getId();									 
								         if (!concernedTasks.contains(st)) concernedTasks.add(st);
										 if (!concernedInstances.contains(instance.getId())) concernedInstances.add(instance.getId());
									 }
								 }
							 }
						 }
					 }
				}		
			}
		}
		// also store the instance without task
		for (WorkflowInstance instance :  workflowService.getWorkflowsForContent(document.getNodeRef(), true)) {
			if (!concernedInstances.contains(instance.getId())) {
		         if (logger.isDebugEnabled()) logger.debug("Store instance  " + instance.getId()+" without task");
				concernedTasks.add(instance.getDefinition().getId()+"/"+instance.getId());
			}
		}
		return concernedTasks;
	}
	
	
	
	/**
	 * Method to reassign a task
	 * 
	 * @param taskId
	 *            the id of the task to reassign
	 * @param actor 
	 * 			  the current actor of the task; if present, check that is the current actor of the task and if not, do not perform the reassign
	 *            
	 * @param newActor 
	 * 			  the newActor to assign the task
	 * 
	 * @return boolean true if the task has been reassigned
	 */
	public boolean reassign(String currentUser, String taskId, String actor, String newActor) {
        boolean reassigned = false;
		WorkflowTask workflowTask = workflowService.getTaskById(taskId);
        if (logger.isDebugEnabled()) logger.debug("reassign task " + taskId+" from "+actor+" to "+newActor);
		if (this.workflowService.isTaskEditable(workflowTask, currentUser) || serviceRegistry.getAuthorityService().isAdminAuthority(currentUser)) {
			Map<QName, Serializable> props = new HashMap<QName, Serializable>();
            QName key = QName.createQName("cm:owner", serviceRegistry.getNamespaceService());
            props.put(key, newActor);
			// update task properties
			workflowTask = workflowService.updateTask(taskId, props, null, null);
			reassigned = true;
	        if (logger.isDebugEnabled()) logger.debug("Task has been reassigned ");
		} else {
			logger.error("Task is not editable by user "+currentUser+" - An admin user must be used !!!");
		}
		return reassigned;
	}

	// IOC
	private ServiceRegistry serviceRegistry;
	private WorkflowService workflowService;

	/**
	 * @return the serviceRegistry
	 */
	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	/**
	 * @param serviceRegistry
	 *            the serviceRegistry to set
	 */
	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	/**
	 * @return the workflowService
	 */
	public WorkflowService getWorkflowService() {
		return workflowService;
	}
	/**
	 * @param workflowService
	 *            the Workflow Service to set
	 */
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}


}


