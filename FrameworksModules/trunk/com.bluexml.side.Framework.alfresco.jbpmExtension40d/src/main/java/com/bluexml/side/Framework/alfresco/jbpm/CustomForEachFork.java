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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript;
import org.alfresco.repo.workflow.jbpm.JBPMNode;
import org.alfresco.repo.workflow.jbpm.JBPMSpringActionHandler;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.workflow.WorkflowException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.Token;
import org.jbpm.instantiation.FieldInstantiator;
import org.springframework.beans.factory.BeanFactory;


/**
 * For each "item in collection", create a fork.
 * 
 * Update version of Alfresco ForEachFork action class to allow multiple variables in the foreach and var list
 * This allows to pass complementary string variable values on each forked path and not only the assignee variables
 * in the form:
 * &lt;foreach&gt;assigneeList,varList1,varListi,varListN&lt;/foreach&gt;
 * &lt;var&gt;assigneeListVarName,varName1,varNamei,varNameN&lt;/var&gt;
 * 
 * with varListi = "value" or #{variable name}, the variable containing - like for the assigneeList - a collection of JBPMNodes or String, 
 * the size of the collection being either :
 * - the same size of the assigneeList : in this case each value is mapped to the variable of each forked path like the assigneeList
 * - not the same size : in this case all the values are set on the variables of each forked path
 * 
 * For instance, one of this variable may be a list of delegates assignee or a timer value
 * 
 */
public class CustomForEachFork extends JBPMSpringActionHandler
{
    private static final long serialVersionUID = 4643103713602441652L;
    private static Log logger = LogFactory.getLog("com.bluexml.side.Framework.alfresco.jbpm");

    private ServiceRegistry services;
    
    private Element foreach;
    private String var;
    private Element foreachFirstElement;
    private String varFirstElement;
    
    // array which contains the list of complementary variable name of the var element
    // if <var>reviewer,delegate,timeout</var> -> complementaryVarName[0]="delegates", complementaryVarName[1]="timeout"
    private String[] complementaryVarName;
    // array which contains the list of complementary variable value of the foreach element
    // if <foreach>#{reviewers},#{delegates},"1 minutes"</var> and #{reviewers}=[rev1,rev2,...] and #{delegates}=[del1,del2,..]
    // -> foreachComplementaryVar[0]=[del1,del2,..], foreachComplementaryVar[1]=["1 minutes"];
    private Collection<?>[] foreachComplementaryVar;
    

    /* (non-Javadoc)
     * @see org.alfresco.repo.workflow.jbpm.JBPMSpringActionHandler#initialiseHandler(org.springframework.beans.factory.BeanFactory)
     */
    @Override
    protected void initialiseHandler(BeanFactory factory)
    {
        services = (ServiceRegistry)factory.getBean(ServiceRegistry.SERVICE_REGISTRY);
    }
    
    /**
     * Create a new child token for each item in list.
     * 
     * @param executionContext
     * @throws Exception
     */
    public void execute(final ExecutionContext executionContext)
        throws Exception
    {
        //
        // process action handler arguments
        //
        
        if (foreach == null)
        {
            throw new WorkflowException("forEach has not been provided");
        }

//        Collection<?> forEachColl = buildForEachCollection(executionContext);
        
        if (var == null || var.length() == 0)
        {
            throw new WorkflowException("forEach variable name has not been provided");
        }
        
        //
        // create forked paths
        //
        
        if (foreachFirstElement == null) foreachFirstElement = foreach.createCopy();
        if (varFirstElement == null) varFirstElement = var;
        buildComplementaryVar(executionContext);
        
        Node node = executionContext.getNode();
        List<ForkedTransition> forkTransitions = new ArrayList<ForkedTransition>();

        Collection<?> forEachColl = buildForEachCollection(executionContext);
        
        // Create a new token and execution context for each node transition and item in list
        List<Transition> nodeTransitions = node.getLeavingTransitions();
        for (Transition noderansition : nodeTransitions)
        {
            int iVar = 0;
            for (Object item: forEachColl)
            {
                // create child token to represent new path
                Token loopToken = buildChildToken(executionContext, noderansition, iVar);

                
                // assign variable within path
                final ExecutionContext newExecutionContext = new ExecutionContext(loopToken);
                newExecutionContext.getContextInstance().createVariable(varFirstElement, item, loopToken);
                
                // create complementary variable within path
                // loop on the list of variable after the assignee List variable
                if (complementaryVarName != null) {
	                for (int i=0; i <complementaryVarName.length; i++) {
	                    if (logger.isDebugEnabled())
	                        logger.debug("Process complementary variable "+complementaryVarName[i]);
	                	if (foreachComplementaryVar[i] != null) {
	                		Object[] var = foreachComplementaryVar[i].toArray();
		                 	if (var.length == forEachColl.size() && !complementaryVarName[i].endsWith(":all")) {
		                		newExecutionContext.getContextInstance().createVariable(complementaryVarName[i], var[iVar], loopToken);
		                        if (logger.isDebugEnabled())
		                            logger.debug("Create path specific Complementary variable "+complementaryVarName[i]+" of value "+var[iVar]);
		                 	} else if (var.length>1) {
		                 		String str = complementaryVarName[i];
		                 		if (complementaryVarName[i].endsWith(":all")) str=complementaryVarName[i].replace(":all", "");
		                		newExecutionContext.getContextInstance().createVariable(str, var, loopToken);	                   	
		                        if (logger.isDebugEnabled())
		                            logger.debug("Create multiple-values common Complementary variable "+str+" of value "+foreachComplementaryVar[i]);
		                	} else if (var.length==1) {		                		
		                 		String str = complementaryVarName[i];
		                 		if (complementaryVarName[i].endsWith(":all")) str=complementaryVarName[i].replace(":all", "");
		                		newExecutionContext.getContextInstance().createVariable(str, var[0], loopToken);	                   	
		                        if (logger.isDebugEnabled())
		                            logger.debug("Create common Complementary variable "+str+" of value "+var[0]);
		                	}
	                	}
	                }
                }
                // record path & transition
                ForkedTransition forkTransition = new ForkedTransition();
                forkTransition.executionContext = newExecutionContext;
                forkTransition.transition = noderansition;
                forkTransitions.add(forkTransition);
                iVar++;
            }
        }

        //
        // let each new token leave the node.
        //
        for (ForkedTransition forkTransition : forkTransitions)
        {
            node.leave(forkTransition.executionContext, forkTransition.transition);
        }
    }

    private Token buildChildToken(final ExecutionContext executionContext, Transition noderansition,
                int iVar)
    {
        Token rootToken = executionContext.getToken();
        String tokenName = getTokenName(rootToken, noderansition.getName(), iVar); 
        Token loopToken = new Token(rootToken, tokenName);
        loopToken.setTerminationImplicit(true);
        executionContext.getJbpmContext().getSession().save(loopToken);
        return loopToken;
    }

    private Collection<?> buildForEachCollection(final ExecutionContext executionContext)
    {
        // build "for each" collection
        String text = foreachFirstElement.getTextTrim();
        if (text != null && text.startsWith("#{"))
        {
            return evaluateForEachExpression(executionContext, text);
        }
        return (Collection<?>) FieldInstantiator.getValue(List.class, foreachFirstElement);
    }
    private Collection<?> buildCollectionText(final ExecutionContext executionContext, String text)
    {
        if (text != null && text.startsWith("#{"))
        {
            return evaluateForEachExpression(executionContext, text);
        } else {
        	String[] result = new String[1];
        	result[0] = text;
            return Arrays.asList(result);        	
        }
    }

    private void buildComplementaryVar(final ExecutionContext executionContext)
    {
        // build "for each" collection
    	String text = foreach.getTextTrim();
        if (text != null && text.contains(",")) {
        	String foreachList[] = text.split(",");
        	String varList[] = var.split(",");
        	if (foreachList.length != varList.length) {
                throw new WorkflowException("forEach element must contain the same number of coma-separated values than var element");
        	}
        	foreachFirstElement.setText(foreachList[0]);
        	varFirstElement = varList[0];
            if (logger.isDebugEnabled())
                logger.debug("Find Main assignee variable "+varFirstElement+" of value "+foreachList[0]);
        	if (foreachList.length > 1) {
	        	foreachComplementaryVar = new Collection[foreachList.length-1];
	        	complementaryVarName = new String[foreachList.length-1];
	        	for (int i = 1; i < foreachList.length; i++) {
	        		String varName = varList[i];
	        		String varValue = foreachList[i];
	        		foreachComplementaryVar[i-1] = buildCollectionText(executionContext,varValue);
	            	complementaryVarName[i-1] = varName;
                    if (logger.isDebugEnabled())
                        logger.debug("Find Complementary variable "+varName+" of value "+foreachComplementaryVar[i-1]);
	        	}
        	}
        }
    }

    private Collection<?> evaluateForEachExpression(final ExecutionContext executionContext, String forEachText)
    {
        String expression = forEachText.substring(2, forEachText.length() -1);
        Object result = AlfrescoJavaScript.executeScript(executionContext, services, expression, null, null);
        if (result == null)
        {
            throw new WorkflowException("forEach expression '" + forEachText + "' evaluates to null");
        }
        if (logger.isDebugEnabled()) logger.debug("evaluate foreach "+expression+" evaluate to  "+result+ " - class="+result.getClass());
        // expression evaluates to string
        if (result instanceof String)
        {
            if (logger.isDebugEnabled()) logger.debug("foreach evaluate to String "+result);
            return buildStrings((String)result);
        }
        else if (result instanceof Integer)
        {
            if (logger.isDebugEnabled()) logger.debug("foreach evaluate to Integer "+result);
            return buildIntegers((Integer)result);
        }
        // expression evaluates to Node array
        else if (result instanceof Serializable[])
        {
            if (logger.isDebugEnabled()) logger.debug("foreach evaluate to Serializable "+result);
            return buildJbpmNodes((Serializable[]) result);
        }
        // expression evaluates to collection
        else if (result instanceof Collection<?>)
        {
            if (logger.isDebugEnabled()) logger.debug("foreach evaluate to Collection "+result);
            return (Collection<?>)result;
        }
        else return null;
    }

    private List<?> buildStrings(String result)
    {
        String[] results = result.trim().split(",");
        return Arrays.asList(results);
    }
    private List<?> buildIntegers(Integer result)
    {
        Integer[] results = { result };
        return Arrays.asList(results);
    }

    private List<?> buildJbpmNodes(Serializable[] nodes)
    {
        List<JBPMNode> jbpmNodes = new ArrayList<JBPMNode>(nodes.length);
        for (Serializable node : nodes)
        {
            if (node instanceof NodeRef)
            {
                JBPMNode jbpmNode = new JBPMNode((NodeRef)node, services);
                jbpmNodes.add(jbpmNode);
            }
        }
        return jbpmNodes;
    }

    /**
     * Create a token name
     * 
     * @param parent
     * @param transitionName
     * @return
     */
    protected String getTokenName(Token parent, String transitionName, int loopIndex)
    {
        String suffix = "." + loopIndex;
        if (transitionName == null || transitionName.isEmpty())
        {
            // No transition name
            int size = (parent.getChildren() != null) ? parent.getChildren().size() + 1 : 1;
            return buildTokenName("FOREACHFORK", suffix, size);
        }
        return findFirstAvailableTokenName(parent, transitionName, suffix);
    }

    private String findFirstAvailableTokenName(Token parent, String transitionName, String suffix)
    {
        int i = 1;
        while (true)
        {
            String tokenName = buildTokenName(transitionName, suffix, i);
            if(!parent.hasChild(tokenName))
            {
                return tokenName;
            }
            i++;
        }
    }
    
    private String buildTokenName(String prefix, String suffix, int count)
    {
        String countStr = count<2 ? "": Integer.toString(count);
        return prefix + countStr + suffix;
    }

    /**
     * Sets the list of objects to be iterated over.
     * @param foreach the list of objects to set
     */
    public void setForeach(Element foreach)
    {
        this.foreach = foreach;
    }
    
    /**
     * Set the name of the variable to which the eleements of <code>foreach</code> are assigned.
     * @param var the variable name to set
     */
    public void setVar(String var)
    {
        this.var = var;
    }

    /**
     * Fork Transition
     */
    private class ForkedTransition
    {
        private ExecutionContext executionContext;
        private Transition transition;
    }

}
