/**
 * 
 */
package com.bluexml.xforms.controller.beans;

/**
 * @author Amenel
 * 
 */
public class WorkflowTaskInfoBean {

	private String id;
	private String name;
	private String actorId;
	private String pooledActors;
	private String title;

	/**
	 * @param id
	 * @param name
	 * @param actorId
	 * @param pooledActors
	 */
	public WorkflowTaskInfoBean(String id, String name, String actorId, String pooledActors,
			String title) {
		this.id = id;
		this.name = name;
		this.actorId = actorId;
		this.pooledActors = pooledActors;
		this.title = title;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
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

}
