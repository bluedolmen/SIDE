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
package com.bluexml.side.framework.alfresco.webscriptExtension;

import org.alfresco.repo.jscript.BaseScopableProcessorExtension;
import org.alfresco.repo.jscript.ScriptNode;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluexml.side.framework.alfresco.commons.services.SIDERulesService;

/**
 * Extend Alfresco Javascript API with rule service
 * siderule.addScriptRule(scriptFileNode, targetedSpace) to add a
 * javascript-based script rule to a targeted space
 */
public class RuleScriptExtension extends BaseScopableProcessorExtension {

	static Log logger = LogFactory.getLog(RuleScriptExtension.class);

	private SIDERulesService rulesService;

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
		rulesService.addRule(script.getNodeRef(), targetSpace.getNodeRef(), ruleType);
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
	 * @param contentType
	 * @param uri
	 */
	public void addScriptRuleWithTypeCondition(ScriptNode script, ScriptNode targetSpace, String ruleType, String contentType, String uri) {
		rulesService.addRuleWithTypeCondition(script.getNodeRef(), targetSpace.getNodeRef(), ruleType, QName.createQName(uri, contentType));
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
		rulesService.addRuleWithTypeCondition(script.getNodeRef(), targetSpace.getNodeRef(), ruleType, QName.createQName(uri, contentType), applyToChildren, setExecuteAsynchronously, setRuleDisabled, title, description);
	}

	/**
	 * @param script
	 * @param title
	 *            (optional return all if null)
	 * @param ruleTypeName
	 *            (optional return all if null)
	 * @param includeInhertiedRuleType
	 */
	public void disableRules(ScriptNode script, String title, String ruleTypeName, boolean includeInhertiedRuleType) {
		rulesService.disableRules(script.getNodeRef(), title, ruleTypeName, includeInhertiedRuleType);
	}

	/**
	 * @param script
	 * @param title
	 *            (optional return all if null)
	 * @param ruleTypeName
	 *            (optional return all if null)
	 * @param includeInhertiedRuleType
	 */
	public void enableRules(ScriptNode script, String title, String ruleTypeName, boolean includeInhertiedRuleType) {
		rulesService.enableRules(script.getNodeRef(), title, ruleTypeName, includeInhertiedRuleType);
	}

	/**
	 * Disable all rule in the current thread
	 */
	public void disableAllRules() {
		rulesService.disableAllRules();
	}

	/**
	 * Enable all rule in the current thread
	 */
	public void enableAllRules() {
		rulesService.enableAllRules();
	}

	public SIDERulesService getRulesService() {
		return rulesService;
	}

	public void setRulesService(SIDERulesService rulesService) {
		this.rulesService = rulesService;
	}
}
