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
package com.bluexml.side.framework.alfresco.commons.services;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.action.evaluator.IsSubTypeEvaluator;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ActionCondition;
import org.alfresco.service.cmr.action.CompositeAction;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.rule.Rule;
import org.alfresco.service.cmr.rule.RuleType;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * SIDE Extension
 * 
 * @author davidabad
 */
public class SIDERulesService {
	static Log logger = LogFactory.getLog(SIDERulesService.class);
	private ServiceRegistry serviceRegistry;

	public static final String RULETYPE_INBOUND = "inbound";
	public static final String RULETYPE_OUTBOUND = "outbound";
	public static final String RULETYPE_UPDATE = "update";

	private String scriptsPath;

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	/**
	 * Method to add a -based rule on a space to apply on children space
	 * 
	 * @param the
	 *            java file containing the code of the rule to
	 *            apply on the targeted space
	 * @param targetSpace
	 *            the targeted space
	 * @param ruleType
	 *            the rule type: either "inbound", "outbound" or "update"
	 */
	public void addRule(NodeRef node, NodeRef targetSpace, String ruleType) {
		addRuleWithTypeCondition(node, targetSpace, ruleType, null);
	}

	/**
	 * Method to add a -based rule on a space to apply on children space
	 * 
	 * @param the
	 *            java file containing the code of the rule to
	 *            apply on the targeted space
	 * @param targetSpace
	 *            the targeted space
	 * @param ruleType
	 *            the rule type: either "inbound", "outbound" or "update"
	 * @param contentType
	 * @param uri
	 */
	public void addRuleWithTypeCondition(NodeRef node, NodeRef targetSpace, String ruleType, QName nodeType) {
		String title = "rule__execution";
		String description = "Run a  when an event occurs on the targetSpace";
		String Name = (String) serviceRegistry.getNodeService().getProperty(node, ContentModel.PROP_NAME);
		String targetSpaceName = (String) serviceRegistry.getNodeService().getProperty(targetSpace, ContentModel.PROP_NAME);
		title = Name + "_" + ruleType + "_" + targetSpaceName;
		if (serviceRegistry.getNodeService().getProperty(node, ContentModel.PROP_TITLE) != null) {
			if (nodeType != null) description = serviceRegistry.getNodeService().getProperty(node, ContentModel.PROP_TITLE) + " for type " + nodeType + " on space " + targetSpaceName;
			else description = (String) serviceRegistry.getNodeService().getProperty(node, ContentModel.PROP_TITLE) + " for all types on space " + targetSpaceName;
		}
		boolean setExecuteAsynchronously = true;
		boolean applyToChildren = false;
		boolean setRuleDisabled = false;
		addRuleWithTypeCondition(node, targetSpace, ruleType, nodeType, applyToChildren, setExecuteAsynchronously, setRuleDisabled, title, description);
	}

	/**
	 * Method to add a -based rule with full setting
	 * 
	 * @param
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
	public void addRuleWithTypeCondition(NodeRef node, NodeRef targetSpace, String ruleType, QName nodeType, boolean applyToChildren, boolean setExecuteAsynchronously, boolean setRuleDisabled, String title, String description) {
		if (logger.isDebugEnabled()) logger.debug("Add the  rule " + node + " on the space " + targetSpace);
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

		CompositeAction compositeAction = serviceRegistry.getActionService().createCompositeAction();
		rule.setAction(compositeAction);

		ActionCondition condition = serviceRegistry.getActionService().createActionCondition(IsSubTypeEvaluator.NAME);
		Map<String, Serializable> conditionParameters = new HashMap<String, Serializable>(1);
		if (nodeType != null) {
			conditionParameters.put(IsSubTypeEvaluator.PARAM_TYPE, nodeType);
		} else {
			conditionParameters.put(IsSubTypeEvaluator.PARAM_TYPE, ContentModel.TYPE_FOLDER);
		}
		condition.setParameterValues(conditionParameters);
		compositeAction.addActionCondition(condition);

		Action action = serviceRegistry.getActionService().createAction("script");
		action.setParameterValue("script-ref", node);
		compositeAction.addAction(action);

		// save the rule on the node
		List<Rule> myRules = serviceRegistry.getRuleService().getRules(targetSpace, false);
		for (Rule ruleTest : myRules) {
			if (ruleTest.getTitle().equals(title)) {
				serviceRegistry.getRuleService().removeRule(targetSpace, ruleTest);
			}
		}
		serviceRegistry.getRuleService().saveRule(targetSpace, rule);
	}

	/**
	 * @param
	 * @param title
	 *            (optional return all if null)
	 * @param ruleTypeName
	 *            (optional return all if null)
	 * @param includeInhertiedRuleType
	 */
	public void disableRules(NodeRef node, String title, String ruleTypeName, boolean includeInhertiedRuleType) {
		List<Rule> rules = serviceRegistry.getRuleService().getRules(node, includeInhertiedRuleType, ruleTypeName);
		for (Rule rule : rules) {
			if (title == null || rule.getTitle().equals(title)) {
				serviceRegistry.getRuleService().disableRule(rule);
			}

		}
	}

	/**
	 * @param
	 * @param title
	 *            (optional return all if null)
	 * @param ruleTypeName
	 *            (optional return all if null)
	 * @param includeInhertiedRuleType
	 */
	public void enableRules(NodeRef node, String title, String ruleTypeName, boolean includeInhertiedRuleType) {
		List<Rule> rules = serviceRegistry.getRuleService().getRules(node, includeInhertiedRuleType, ruleTypeName);
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

	public NodeRef saveRuleScript(InputStream in, String name) {
		String query = "PATH:\"" + scriptsPath + "\"";
		NodeRef scripts = serviceRegistry.getSearchService().query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, SearchService.LANGUAGE_LUCENE, query).getNodeRef(0);
		NodeRef script = serviceRegistry.getNodeService().getChildByName(scripts, ContentModel.ASSOC_CONTAINS, name);
		if (script == null) {
			script = serviceRegistry.getFileFolderService().create(scripts, name, ContentModel.TYPE_CONTENT).getNodeRef();
		}

		ContentWriter writerHTML = serviceRegistry.getContentService().getWriter(script, ContentModel.PROP_CONTENT, true);
		String mimetype = serviceRegistry.getMimetypeService().getMimetype("js");
		writerHTML.setMimetype(mimetype);
		writerHTML.putContent(in);
		return script;
	}

	public String getScriptsPath() {
		return scriptsPath;
	}

	public void setScriptsPath(String scriptsPath) {
		this.scriptsPath = scriptsPath;
	}
}
