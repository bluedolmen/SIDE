/**
 * 
 */
package com.bluexml.xforms.controller.beans;

/**
 * Bean for providing a set of information on a workflow form.
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
	 * 
	 */
	public WorkflowTaskInfoBean(String taskId, String name, String dataForm, String actorId,
			String pooledActors, String title) {
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
	public String getTaskId() {
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
