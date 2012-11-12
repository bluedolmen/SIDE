package com.bluexml.side.framework.alfresco.webscriptExtension;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.repo.jscript.BaseScopableProcessorExtension;
import org.alfresco.repo.jscript.ScriptNode;
import org.alfresco.repo.workflow.jscript.JscriptWorkflowTask;
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
	ServiceRegistry serviceRegistry;
	WorkflowService workflowService;

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
	 * @return the ruleService
	 */
	public WorkflowService getWorkflowService() {
		return workflowService;
	}

	/**
	 * @param ruleService
	 *            the Rule Service to set
	 */
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}


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
}
