package com.bluexml.side.framework.alfresco.webscriptExtension;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
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
 * siderule.addScriptRule(scriptFileNode, targetedSpace) to add a
 * javascript-based script rule to a targeted space
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
	 * @param script
	 *            the javascript script file containing the code of the rule to
	 *            apply on the targeted space
	 * @param targetSpace
	 *            the targeted space
	 * @param ruleType
	 *            the rule type: either "inbound", "outbound" or "update"
	 */
	public void addScriptRule(ScriptNode script, ScriptNode targetSpace, String ruleType) {
		addScriptRuleWithTypeCondition(script, targetSpace, ruleType, null, null);
	}

	/**
	 * Method to add a script-based rule on a space to apply on children space
	 * 
	 * @param script
	 *            the javascript script file containing the code of the rule to
	 *            apply on the targeted space
	 * @param targetSpace
	 *            the targeted space
	 * @param ruleType
	 *            the rule type: either "inbound", "outbound" or "update"
	 * @param applyToChildren
	 * @param setExecuteAsynchronously
	 * @param setRuleDisabled
	 */
	public void addScriptRuleWithTypeCondition(ScriptNode script, ScriptNode targetSpace, String ruleType, String contentType, String uri) {
		String title = "rule_script_execution";
		String description = "Run a script when an event occurs on the targetSpace";
		String scriptName = (String) serviceRegistry.getNodeService().getProperty(script.getNodeRef(), ContentModel.PROP_NAME);
		String targetSpaceName = (String) serviceRegistry.getNodeService().getProperty(targetSpace.getNodeRef(), ContentModel.PROP_NAME);
		title = scriptName + "_" + ruleType + "_" + targetSpaceName;
		if (serviceRegistry.getNodeService().getProperty(script.getNodeRef(), ContentModel.PROP_TITLE) != null) {
			if (contentType != null)
				description = serviceRegistry.getNodeService().getProperty(script.getNodeRef(), ContentModel.PROP_TITLE) + " for type " + contentType + " on space " + targetSpaceName;
			else
				description = (String) serviceRegistry.getNodeService().getProperty(script.getNodeRef(), ContentModel.PROP_TITLE) + " for all types on space " + targetSpaceName;
		}
		boolean setExecuteAsynchronously = true;
		boolean applyToChildren = false;
		boolean setRuleDisabled = false;
		addScriptRuleWithTypeCondition(script, targetSpace, ruleType, contentType, uri, applyToChildren, setExecuteAsynchronously, setRuleDisabled, title, description);
	}

	/**
	 * Method to add a script-based rule with full setting
	 * 
	 * @param script
	 * @param targetSpace
	 * @param ruleType
	 * @param contentType
	 * @param uri
	 * @param applyToChildren
	 * @param setExecuteAsynchronously
	 * @param setRuleDisabled
	 * @param title
	 * @param description
	 */
	public void addScriptRuleWithTypeCondition(ScriptNode script, ScriptNode targetSpace, String ruleType, String contentType, String uri, boolean applyToChildren, boolean setExecuteAsynchronously, boolean setRuleDisabled, String title, String description) {
		if (logger.isDebugEnabled())
			logger.debug("Add the script rule " + script + " on the space " + targetSpace);
		Rule rule = new Rule();

		rule.setTitle(title);
		rule.setDescription(description);
		rule.applyToChildren(applyToChildren);
		rule.setExecuteAsynchronously(setExecuteAsynchronously);
		rule.setRuleDisabled(setRuleDisabled);
		if (ruleType.equals("update")) {
			rule.setRuleType(RuleType.UPDATE);
		} else if (ruleType.equals("outbound")) {
			rule.setRuleType(RuleType.OUTBOUND);
		} else {
			rule.setRuleType(RuleType.INBOUND);
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

		// save the rule on the node
		List<Rule> myRules = this.getRuleService().getRules(targetSpace.getNodeRef(), false);
		for (Rule ruleTest : myRules) {
			if (ruleTest.getTitle().equals(title)) {
				this.getRuleService().removeRule(targetSpace.getNodeRef(), ruleTest);
			}
		}
		this.getRuleService().saveRule(targetSpace.getNodeRef(), rule);
	}

	/**
	 * 
	 * @param script
	 * @param title (optional return all if null)
	 * @param ruleTypeName (optional return all if null)
	 * @param includeInhertiedRuleType
	 */
	public void disableRules(ScriptNode script, String title, String ruleTypeName, boolean includeInhertiedRuleType) {
		List<Rule> rules = serviceRegistry.getRuleService().getRules(script.getNodeRef(), includeInhertiedRuleType, ruleTypeName);
		for (Rule rule : rules) {
			if (title == null || rule.getTitle().equals(title)) {
				serviceRegistry.getRuleService().disableRule(rule);
			}

		}
	}

	/**
	 * 
	 * @param script
	 * @param title (optional return all if null)
	 * @param ruleTypeName (optional return all if null)
	 * @param includeInhertiedRuleType
	 */
	public void enableRules(ScriptNode script, String title, String ruleTypeName, boolean includeInhertiedRuleType) {
		List<Rule> rules = serviceRegistry.getRuleService().getRules(script.getNodeRef(), includeInhertiedRuleType, ruleTypeName);
		for (Rule rule : rules) {
			if (title == null || rule.getTitle().equals(title)) {
				serviceRegistry.getRuleService().enableRule(rule);
			}
		}
	}

	/**
	 * Disable all rule in the current thread
	 */
	public void disableAllRules() {
		serviceRegistry.getRuleService().disableRules();
	}

	/**
	 * Enable all rule in the current thread
	 */
	public void enableAllRules() {
		serviceRegistry.getRuleService().enableRules();
	}
}
