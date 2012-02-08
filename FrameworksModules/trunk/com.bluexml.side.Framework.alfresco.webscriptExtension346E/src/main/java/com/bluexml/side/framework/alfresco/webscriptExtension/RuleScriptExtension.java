package com.bluexml.side.framework.alfresco.webscriptExtension;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.action.evaluator.IsSubTypeEvaluator;
import org.alfresco.repo.jscript.BaseScopableProcessorExtension;
import org.alfresco.repo.jscript.ScriptNode;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ActionCondition;
import org.alfresco.service.cmr.action.ActionService;
import org.alfresco.service.cmr.action.CompositeAction;
import org.alfresco.service.cmr.rule.Rule;
import org.alfresco.service.cmr.rule.RuleService;
import org.alfresco.service.cmr.rule.RuleType;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

/**
 * Extend Alfresco Javascript API with rule service
 * siderule.addScriptRule(scriptFileNode, targetedSpace) to add a javascript-based script rule to a targeted space
 *
 */
public class RuleScriptExtension extends BaseScopableProcessorExtension {

	private Logger logger = Logger.getLogger(getClass());
	ServiceRegistry serviceRegistry;
	RuleService ruleService;
	ActionService actionService;

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
	public RuleService getRuleService() {
		return ruleService;
	}

	/**
	 * @param ruleService
	 *            the Rule Service to set
	 */
	public void setRuleService(RuleService ruleService) {
		this.ruleService = ruleService;
	}

		/**
	 * @return the actionService
	 */
	public ActionService getActionService() {
		return actionService;
	}

	/**
	 * @param actionService
	 *            the Action Service to set
	 */
	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}


	/**
	 * Method to add a script-based rule on a space to apply on children space
	 * 
	 * @param script the javascript script file containing the code of the rule to apply on the targeted space
	 * @param targetSpace the targeted space
	 * @param ruleType the rule type: either "inbound", "outbound" or "update"
	 */
	public void addScriptRule(ScriptNode script, ScriptNode targetSpace, String ruleType) {
		addScriptRuleWithTypeCondition(script, targetSpace, ruleType, null, null);
	}

	/**
	 * Method to add a script-based rule on a space to apply on children space
	 * 
	 * @param script the javascript script file containing the code of the rule to apply on the targeted space
	 * @param targetSpace the targeted space
	 * @param ruleType the rule type: either "inbound", "outbound" or "update"
	 */
	public void addScriptRuleWithTypeCondition(ScriptNode script, ScriptNode targetSpace, String ruleType, String contentType, String uri) {
	    if (logger.isDebugEnabled())
	            logger.debug("Add the script rule " + script + " on the space " +targetSpace);
		Rule rule = new Rule();
		rule.setTitle("rule_script_execution");
		rule.setDescription("Run a script when an event occurs on the targetSpace");
		rule.applyToChildren(true);
		rule.setExecuteAsynchronously(false);
		rule.setRuleDisabled(false);
		if (ruleType.equals("update")){
			rule.setRuleType(RuleType.UPDATE);
		} else { 
			if (ruleType.equals("outbound")){
				rule.setRuleType(RuleType.OUTBOUND);
			} else { 
				rule.setRuleType(RuleType.INBOUND);
			}
		}
		
		CompositeAction compositeAction = actionService.createCompositeAction();
		rule.setAction(compositeAction);
		
		ActionCondition condition = actionService.createActionCondition(IsSubTypeEvaluator.NAME);
		Map<String, Serializable> conditionParameters = new HashMap<String, Serializable>(1);
		if (contentType != null) {
			conditionParameters.put(IsSubTypeEvaluator.PARAM_TYPE, QName.createQName(uri, contentType));
		} else {
			conditionParameters.put(IsSubTypeEvaluator.PARAM_TYPE, ContentModel.TYPE_FOLDER);
		}
		condition.setParameterValues(conditionParameters);
		compositeAction.addActionCondition(condition);
		
		Action action = actionService.createAction("script");
		action.setParameterValue("script-ref", script.getNodeRef());
		compositeAction.addAction(action);
		
		// save the rule on the node only if the node has no rule
		if (!this.getRuleService().hasRules(targetSpace.getNodeRef())) {
			this.getRuleService().saveRule(targetSpace.getNodeRef(), rule);			
		}
	}

}
