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
package com.bluexml.side.Framework.alfresco.jbpm;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.TaskInstanceFactory;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * jBPM factory for creating Alfresco derived Task Instances
 * 
 * @author davidc
 */
public class CustomWorkflowTaskInstanceFactory implements TaskInstanceFactory
{
    private static final long serialVersionUID = -8097108150047415711L;

    private String jbpmEngineName;


    /**
     * @param jbpmEngine the jbpmEngine to set
     */
    public void setJbpmEngine(String jbpmEngine)
    {
        this.jbpmEngineName = jbpmEngine;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.jbpm.taskmgmt.TaskInstanceFactory#createTaskInstance(org.jbpm.graph
     * .exe.ExecutionContext)
     */
    public TaskInstance createTaskInstance(ExecutionContext executionContext)
    {
        CustomWorkflowTaskInstance taskInstance = new CustomWorkflowTaskInstance();
        taskInstance.setJbpmEngineName(jbpmEngineName);
        return taskInstance;
    }
}
