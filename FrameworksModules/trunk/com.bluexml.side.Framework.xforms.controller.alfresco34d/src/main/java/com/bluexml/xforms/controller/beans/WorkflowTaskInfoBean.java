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
/**
 * 
 */
package com.bluexml.xforms.controller.beans;

import com.bluexml.xforms.messages.MsgPool;

/**
 * Bean for providing some information about a workflow form.<br/>
 * NOTE: If the title is a reference to a key in messages.properties, the reference is resolved when
 * creating the bean. Subsequent calls to {@link #getTitle()} are guaranteed to return the resolved
 * message. Of course, the key SHOULD exist in the file.
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
	private String processTitle; // #1535 // meaningful only for start task forms

	/**
	 * 
	 */
	public WorkflowTaskInfoBean(String taskId, String name, String dataForm, String actorId,
			String pooledActors, String title, String processTitle) {
		this.taskId = taskId;
		this.formName = name;
		this.dataFormName = dataForm;
		this.actorId = actorId;
		this.pooledActors = pooledActors;
		this.title = title;
		if ((title != null) && (title.charAt(0) == '#') && (title.indexOf("##") != 0)) {
			this.title = MsgPool.getMsg(title.substring(1));
		}
		this.processTitle = processTitle;
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

	/**
	 * @return the processTitle
	 */
	public String getProcessTitle() {
		return processTitle;
	}

}
