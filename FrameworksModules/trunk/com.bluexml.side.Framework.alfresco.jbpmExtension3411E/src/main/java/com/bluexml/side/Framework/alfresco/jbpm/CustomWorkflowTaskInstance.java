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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.repo.workflow.WorkflowModel;
import org.alfresco.service.cmr.workflow.WorkflowException;
import org.alfresco.service.namespace.QName;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springmodules.workflow.jbpm31.JbpmFactoryLocator;

/**
 * Alfresco specific implementation of a jBPM task instance
 * extended to get the bluxeml CustomJBPMEngine
 * 
 * @author davidc
 */
public class CustomWorkflowTaskInstance extends TaskInstance
{
    private static final long serialVersionUID = 6824116036569411964L;

    /**
     * Used to look up the Alfresco JBPM Engine.
     */
    private String jbpmEngineName = null;

    /** Alfresco JBPM Engine */
    private static CustomJBPMEngine jbpmEngine = null;

    /**
     * Construct
     */
    public CustomWorkflowTaskInstance()
    {
        super();
    }

    /**
     * Construct
     * 
     * @param taskName
     * @param actorId
     */
    public CustomWorkflowTaskInstance(String taskName, String actorId)
    {
        super(taskName, actorId);
    }

    /**
     * Sets jbpmEngineName which is used to get the JBPMEngine instance from a
     * BeanFactory
     * 
     * @param jbpmEngineName the jbpmEngineName to set
     */
    public void setJbpmEngineName(String jbpmEngineName)
    {
        this.jbpmEngineName = jbpmEngineName;
    }

    /**
     * Gets the JBPM Engine instance
     * 
     * @return JBPM Engine
     */
    private CustomJBPMEngine getJBPMEngine()
    {
        if (jbpmEngine == null)
        {
            BeanFactoryLocator factoryLocator = new JbpmFactoryLocator();
            BeanFactoryReference factory = factoryLocator.useBeanFactory(null);
            if (jbpmEngineName == null) jbpmEngineName = "jbpm_engine";
            jbpmEngine = (CustomJBPMEngine) factory.getFactory().getBean(jbpmEngineName);
            if (jbpmEngine == null) { throw new WorkflowException(
                        "Failed to retrieve JBPMEngine component"); }
        }
        return jbpmEngine;
    }

    @Override
    public void create(ExecutionContext executionContext)
    {
        super.create(executionContext);
        getJBPMEngine().setDefaultTaskProperties(this);
    }

    @Override
    public void end(Transition transition)
    {
        // Force assignment of task if transition is taken, but no owner has yet
        // been assigned
        if (actorId == null)
        {
            actorId = AuthenticationUtil.getFullyAuthenticatedUser();
        }

        // Set task properties on completion of task
        // NOTE: Set properties first, so they're available during the
        // submission of
        // task variables to the process context
        Map<QName, Serializable> taskProperties = new HashMap<QName, Serializable>();
        Transition outcome = (transition == null) ? token.getNode().getDefaultLeavingTransition()
                    : transition;
        if (outcome != null)
        {
            taskProperties.put(WorkflowModel.PROP_OUTCOME, outcome.getName());
        }
        taskProperties.put(WorkflowModel.PROP_STATUS, "Completed");
        getJBPMEngine().setTaskProperties(this, taskProperties);

        // perform transition
        super.end(transition);

        if (getTask().getStartState() != null)
        {
            // if ending a start task, push start task properties to process
            // context, if not
            // already done
            getJBPMEngine().setDefaultWorkflowProperties(this);

            // set task description
            getJBPMEngine().setDefaultStartTaskDescription(this);
        }
    }

}
