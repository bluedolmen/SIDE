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

import java.util.Calendar;
import java.util.Date;

import org.alfresco.repo.workflow.jbpm.AlfrescoTimer;
import org.alfresco.service.cmr.workflow.WorkflowException;
import org.jbpm.calendar.BusinessCalendar;
import org.jbpm.calendar.Duration;
import org.jbpm.graph.def.GraphElement;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.job.Timer;
import org.jbpm.jpdl.el.impl.JbpmExpressionEvaluator;
import org.jbpm.scheduler.def.CreateTimerAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Extended Create Timer action for supporting Alfresco implemented timers.
 * 
 * Alfresco timer supports ability to provide due date expression that can
 * evaluate to a date. 
 * 
 * @author davidc
 */
public class CustomCreateTimerAction extends CreateTimerAction
{
    private static final long serialVersionUID = -7427571820130058416L;
    private static Log logger = LogFactory.getLog("com.bluexml.side.Framework.alfresco.jbpm");
    protected static BusinessCalendar businessCalendar = new BusinessCalendar(); 
    
    
    /* (non-Javadoc)
     * @see org.jbpm.scheduler.def.CreateTimerAction#createTimer(org.jbpm.graph.exe.ExecutionContext)
     */
    @Override
    protected Timer createTimer(ExecutionContext executionContext)
    {
        String name = null;
        String nameExpression = getTimerName();
        if (nameExpression.startsWith("#{"))
        {
            String nameExpressionIC = nameExpression.toLowerCase();
        	if (nameExpressionIC.substring(2, nameExpression.length()-1).equals("taskinstance.id")) {
            	String last = nameExpression.replaceFirst("#\\{.+\\}", "");
        		name = "Timer_"+executionContext.getTaskInstance().getId() + last;
            } else {
	        	Object result = JbpmExpressionEvaluator.evaluate(nameExpression, executionContext);
	            if (result instanceof String)
	            {
	                name = (String)result;
	            }
	            else
	            {
	                throw new WorkflowException("name expression must evaluate to a String");
	            }
            }
        } else {
        	name = getTimerName(); 
        }
        if (logger.isDebugEnabled())
            logger.debug("Timer name is "+name);
 
        Date dueDate = null;
        String dueDateExpression = getDueDate();
        if (dueDateExpression.startsWith("#{"))
        {
            Object result = JbpmExpressionEvaluator.evaluate(dueDateExpression, executionContext);
            if (logger.isDebugEnabled())
                logger.debug("Timer variable duedate is "+result);

            if (result instanceof Date)
            {
                dueDate = (Date)result;
            }
            else if(result instanceof Calendar)
            {
                dueDate = ((Calendar)result).getTime();
            }
            else if(result instanceof String)
            {
            	Duration duration = new Duration((String)result);   
            	dueDate = businessCalendar.add(new Date(), duration);       
            }
            else
            {
                throw new WorkflowException("duedate expression must evaluate to a date");
            }
        }
        else
        {
            Duration duration = new Duration(getDueDate());
            dueDate = businessCalendar.add(new Date(), duration);            
        }
        
        Timer timer = new AlfrescoTimer(executionContext.getToken());
        timer.setName(name);
        timer.setRepeat(getRepeat());
        timer.setDueDate(dueDate);
        timer.setAction(getTimerAction());
        timer.setTransitionName(getTransitionName());
        timer.setGraphElement(executionContext.getEventSource());
        timer.setTaskInstance(executionContext.getTaskInstance());

        // if this action was executed for a graph element
        if ((getEvent() != null) && (getEvent().getGraphElement() != null))
        {
            GraphElement graphElement = getEvent().getGraphElement();
            try
            {
                executionContext.setTimer(timer);
                // fire the create timer event on the same graph element
                graphElement.fireEvent("timer-create", executionContext);
            } 
            finally
            {
                executionContext.setTimer(null);
            }
        }

        return timer;
    }

}
