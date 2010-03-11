/**
 * 
 */
package com.bluexml.xforms.controller.beans;

import com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI;

/**
 * Bean for providing a set of information on a workflow form. Use this to combine several calls to
 * {@link AlfrescoControllerAPI}.
 * 
 * @author Amenel
 * 
 */
public class WorkflowTaskInfoBean {

	private String taskId;
	private String formName;
	private String dataFormName;
	private String actorId;
	private String pooledActors;
	private String title;

	/**
	 * @param id
	 * @param name
	 * @param actorId
	 * @param pooledActors
	 */
	public WorkflowTaskInfoBean(String taskId, String name, String dataForm, String actorId, String pooledActors,
			String title) {
		this.taskId = taskId;
		this.formName = name;
		this.dataFormName = dataForm;
		this.actorId = actorId;
		this.pooledActors = pooledActors;
		this.title = title;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return taskId;
	}

	/**
	 * @return the name
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @return the actorId
	 */
	public String getActorId() {
		return actorId;
	}

	/**
	 * @return the pooledActors
	 */
	public String getPooledActors() {
		return pooledActors;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the dataFormName
	 */
	public String getDataFormName() {
		return dataFormName;
	}

}
