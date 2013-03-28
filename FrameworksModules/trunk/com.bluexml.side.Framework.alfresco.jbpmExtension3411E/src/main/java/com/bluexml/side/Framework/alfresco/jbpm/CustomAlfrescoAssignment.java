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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.jscript.ScriptNode;
import org.alfresco.repo.security.authority.AuthorityDAO;
import org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript;
import org.alfresco.repo.workflow.jbpm.JBPMNode;
import org.alfresco.repo.workflow.jbpm.JBPMSpringAssignmentHandler;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.workflow.WorkflowException;
import org.alfresco.service.namespace.QName;
import org.dom4j.Element;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.exe.Assignable;
import org.springframework.beans.factory.BeanFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Assignment Handler for assigning Alfresco People and Groups to Tasks
 * and Swimlanes
 * 
 * @author davidc
 */
public class CustomAlfrescoAssignment extends JBPMSpringAssignmentHandler {
    private static final long serialVersionUID = 1025667849552265719L;
    private static Log logger = LogFactory.getLog("com.bluexml.side.Framework.alfresco.jbpm");
    private ServiceRegistry services;
    private DictionaryService dictionaryService;
    private AuthorityDAO authorityDAO;

    private Element actor;
    private Element pooledactors;


    /* (non-Javadoc)
     * @see org.alfresco.repo.workflow.jbpm.JBPMSpringActionHandler#initialiseHandler(org.springframework.beans.factory.BeanFactory)
     */
    @Override
    protected void initialiseHandler(BeanFactory factory)
    {
        services = (ServiceRegistry)factory.getBean(ServiceRegistry.SERVICE_REGISTRY);
        dictionaryService = services.getDictionaryService();
        authorityDAO = (AuthorityDAO)factory.getBean("authorityDAO");
    }

    
    /* (non-Javadoc)
     * @see org.jbpm.taskmgmt.def.AssignmentHandler#assign(org.jbpm.taskmgmt.exe.Assignable, org.jbpm.graph.exe.ExecutionContext)
     * 
     * SIDE: if actor and pooledactors are both present with expression like #{var}, the actor expression is evaluated first and if null, no exception but 
     * the pooledactors expression is evaluated: if both of them are null, exception.
     */
    public void assign(Assignable assignable, ExecutionContext executionContext) throws Exception
    {
        if (actor == null && pooledactors == null)
        {
            throw new WorkflowException("no actor or pooled actors has been specified");
        }
        if (logger.isDebugEnabled())
            logger.debug("assign assignable : "+assignable);

        //
        // extract actor
        //
        boolean actorEvaluated = false;
        String assignedActor = null;
        if (actor != null)
        {
            String actorValStr = actor.getTextTrim();
            if (logger.isDebugEnabled()) logger.debug("actor "+actorValStr);
            if (actorValStr != null && actorValStr.length() > 0)
            {
                if (actorValStr.startsWith("#{"))
                {
                    String expression = actorValStr.substring(2, actorValStr.length() -1);
                    Object eval = AlfrescoJavaScript.executeScript(executionContext, services, expression, null, null);
                    if (eval == null)
                    {
                    	actorEvaluated = true;
                    	//throw new WorkflowException("actor expression '" + actorValStr + "' evaluates to null");
                    } else {
	                    String actor = null;
	                    if (eval instanceof String)
	                    {
	                        actor = (String)eval;
	                    }
	                    else if (eval instanceof JBPMNode)
	                    {
	                        actor = mapAuthorityToName((JBPMNode)eval, false);
	                    }
	                    if (actor == null)
	                    {
	                        throw new WorkflowException("actor expression must evaluate to a person");
	                    }
	                    assignedActor = actor;
                    }
                }
                else
                {
                    assignedActor = actorValStr;
                }
            }
        }

        //
        // extract pooled actors
        //
        
        boolean pooledactorsEvaluated = false;
        String[] assignedPooledActors = null;
        if (pooledactors != null)
        {
            String pooledactorValStr = pooledactors.getTextTrim();
            if (logger.isDebugEnabled()) logger.debug("Pooled Actor "+pooledactorValStr);
            if (pooledactorValStr != null && pooledactorValStr.length() > 0)
            {
                if (pooledactorValStr.startsWith("#{"))
                {
                    String expression = pooledactorValStr.substring(2, pooledactorValStr.length() -1);
                    Object eval = AlfrescoJavaScript.executeScript(executionContext, services, expression, null, null);
                    if (logger.isDebugEnabled())
                        logger.debug("eval Pooled Actor "+eval);
                    if (eval == null)
                    {
                    	pooledactorsEvaluated = true;
                        //throw new WorkflowException("pooledactors expression '" + pooledactorValStr + "' evaluates to null");
                    } else {    
	                    if (eval instanceof ScriptNode[])
	                    {
	                        if (logger.isDebugEnabled())
	                            logger.debug("ScriptNode[]");
	                        ScriptNode[] nodes = (ScriptNode[])eval;
	                        assignedPooledActors = new String[nodes.length];
	                        
	                        int i = 0;
	                        for (ScriptNode node : (ScriptNode[])nodes)
	                        {
	                            String actor = mapAuthorityToName(node, true);
	                            if (logger.isDebugEnabled())
	                                logger.debug("ScriptNode[] : "+actor);
	                            if (actor == null)
	                            {
	                                throw new WorkflowException("pooledactors expression does not evaluate to a collection of authorities");
	                            }
	                            assignedPooledActors[i++] = actor;
	                        }
	                    }
	                    else if (eval instanceof Collection)
	                    {
	                        if (logger.isDebugEnabled())
	                            logger.debug("Collection");
	                        List<String> actors = new ArrayList<String>();
	                        Collection<Object> nodes = (Collection<Object>)eval;
	                        for (Object node : nodes)
	                        {
	                            if (node instanceof ScriptNode)
	                            {
	                                String actor = mapAuthorityToName((ScriptNode)node, true);
	                                if (logger.isDebugEnabled())
	                                    logger.debug("Collection : "+actor);
	                                if (actor == null)
	                                {
	                                    throw new WorkflowException("pooledactors expression does not evaluate to a collection of authorities");
	                                }
	                                actors.add(actor);
	                            } else if (node instanceof String) {                            	
	                                actors.add((String)node);
	                            }
	                        }
	                        assignedPooledActors = new String[actors.size()];
	                        actors.toArray(assignedPooledActors);
	                    }
	                    else if (eval instanceof ScriptNode)
	                    {
	                        ScriptNode node = (ScriptNode)eval;
	                        String actor = mapAuthorityToName(node, true);
	                        if (logger.isDebugEnabled())
	                            logger.debug("ScriptNode : "+actor);
	                        if (actor == null)
	                        {
	                            throw new WorkflowException("pooledactors expression does not evaluate to a collection of authorities");
	                        }
	                        assignedPooledActors = new String[] {actor};
	                    }
	                    else if (eval instanceof String)
	                    {
	                        if (logger.isDebugEnabled())
	                            logger.debug("String : "+eval);
	                        assignedPooledActors = new String[] {(String)eval};
	                    }
	                    else if (eval instanceof org.jbpm.bytes.ByteArray)
	                    {
	                    	if (logger.isDebugEnabled())
	                            logger.debug("org.jbpm.bytes.ByteArray : "+(org.jbpm.bytes.ByteArray)eval);
	                    	byte[] bArray = ((org.jbpm.bytes.ByteArray)eval).getBytes();
	                    	StringBuffer buffer = new StringBuffer();
	                    	 
	                    	for(byte b : bArray) {
	                          buffer.append(Integer.toHexString(b));
	                          buffer.append(" ");
	                        }
	                    	String pool = buffer.toString();
	                    	if (logger.isDebugEnabled())
	                            logger.debug("Byte String : "+pool);
	                     
	                        assignedPooledActors = new String[] {(String)pool};
	                    }
	                    else
	                    {
	                        throw new WorkflowException("pooledactors expression does not evaluate to a collection of authorities");
	                    }
                    }
                }
                else
                {
                    assignedPooledActors = new String[] {pooledactorValStr};
                }
            }
        }
        
        if (actorEvaluated && (pooledactors == null || assignedPooledActors == null)) {
        	throw new WorkflowException("actor expression '" + actor.getTextTrim() + "' evaluates to null");        	
        }
        if (pooledactorsEvaluated && (actor == null || assignedActor == null)) {
        	throw new WorkflowException("pooledactors expression '" + pooledactors.getTextTrim() + "' evaluates to null");        	
        }
        //
        // make the assignment
        //
        if (assignedActor != null)
        {
        	if (logger.isDebugEnabled()) logger.debug("Assign actor : "+assignedActor);
            assignable.setActorId(assignedActor);
        }
        if (assignedPooledActors != null)
        {
        	if (logger.isDebugEnabled()) logger.debug("Assign pooled actor : "+assignedPooledActors.toString());
            assignable.setPooledActors(assignedPooledActors);
        }        
    }

    
    /**
     * Convert Alfresco authority to actor id
     *  
     * @param authority
     * @return  actor id
     */
    private String mapAuthorityToName(ScriptNode authority, boolean allowGroup)
    {
        String name = null;
        QName type = authority.getQNameType();

        if (dictionaryService.isSubClass(type, ContentModel.TYPE_PERSON))
        {
            name = (String)authority.getProperties().get(ContentModel.PROP_USERNAME);
        }
        else if (allowGroup && dictionaryService.isSubClass(type, ContentModel.TYPE_AUTHORITY_CONTAINER))
        {
            name = authorityDAO.getAuthorityName(authority.getNodeRef());
        }
        else if (type.equals(ContentModel.TYPE_AUTHORITY))
        {
            name = authorityDAO.getAuthorityName(authority.getNodeRef());
        }
        return name;
    }
}
