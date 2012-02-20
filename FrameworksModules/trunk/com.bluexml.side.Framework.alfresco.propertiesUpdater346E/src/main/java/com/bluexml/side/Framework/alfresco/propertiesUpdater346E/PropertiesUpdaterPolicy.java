package com.bluexml.side.Framework.alfresco.propertiesUpdater346E;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.alfresco.repo.node.NodeServicePolicies.OnCreateNodePolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnUpdatePropertiesPolicy;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.Behaviour.NotificationFrequency;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.dictionary.ConstraintDefinition;
import org.alfresco.service.cmr.dictionary.ConstraintException;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertiesUpdaterPolicy implements OnCreateNodePolicy, OnUpdatePropertiesPolicy {
	private static Log logger = LogFactory.getLog(PropertiesUpdaterPolicy.class);

	protected ConfigurationReader nameUpdaterConfig;
	private PolicyComponent policyComponent;

	// Behaviours
	private Behaviour onCreateNode;
	private Behaviour onUpdateProperties;

	protected String alfrescoFileNameToFix = "([\\\"\\*\\\\\\>\\<\\?\\/\\:\\|]+)|([\\.]?.*[\\.]+$)|([ ]+$)";

	protected ServiceRegistry serviceRegistry;

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	public ConfigurationReader getNameUpdaterConfig() {
		return nameUpdaterConfig;
	}

	public void setNameUpdaterConfig(ConfigurationReader nameUpdaterConfig) {
		this.nameUpdaterConfig = nameUpdaterConfig;
	}

	/**
	 * @return the policyComponent
	 */
	public PolicyComponent getPolicyComponent() {
		return policyComponent;
	}

	/**
	 * @param policyComponent
	 *            the policyComponent to set
	 */
	public void setPolicyComponent(PolicyComponent policyComponent) {
		this.policyComponent = policyComponent;
	}

	public DictionaryService getDictionaryService() {
		return serviceRegistry.getDictionaryService();
	}

	public void init() {
		logger.debug("[init] Initializing NameUpdaterPolicy");

		// Create behaviours using NotificationFrequency.TRANSACTION_COMMIT cause to have nodeRef that can't be founded by nodeService
		onCreateNode = new JavaBehaviour(this, "onCreateNode", NotificationFrequency.FIRST_EVENT);
		onUpdateProperties = new JavaBehaviour(this, "onUpdateProperties", NotificationFrequency.TRANSACTION_COMMIT);

		// Bind behaviours to node policies
		policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateNode"), this, onCreateNode);
		policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onUpdateProperties"), this, onUpdateProperties);

	}

	/**
	 * @return the nodeService
	 */
	public NodeService getNodeService() {
		return serviceRegistry.getNodeService();
	}

	public void onUpdateProperties(NodeRef nodeRef, Map<QName, Serializable> before, Map<QName, Serializable> after) {
		QName type = getNodeService().getType(nodeRef);

		// filter and prepare propertyValues map only String values are managed
		Map<String, String> oldValues = new HashMap<String, String>();
		Map<String, String> newValues = new HashMap<String, String>();
		for (Map.Entry<QName, Serializable> beforeEntry : before.entrySet()) {
			String prefixString = beforeEntry.getKey().toPrefixString(serviceRegistry.getNamespaceService());
			String value = beforeEntry.getValue() != null ? beforeEntry.getValue().toString() : "";
			oldValues.put(prefixString, value);
		}
		for (Map.Entry<QName, Serializable> afterEntry : after.entrySet()) {
			QName key = afterEntry.getKey();
			String prefixString = key.toPrefixString(serviceRegistry.getNamespaceService());
			String value = afterEntry.getValue() != null ? afterEntry.getValue().toString() : "";
			newValues.put(prefixString, value);
		}

		PatternPropertiesUpdater updater = new PatternPropertiesUpdater(oldValues, newValues);
		if (hasNamePattern(type)) {
			boolean changes = false;
			List<Modification> modifications = getModification(type, updater);
			for (Modification modification : modifications) {
				logger.debug("current Modification :" + modification);
				QName propName = modification.getPropertyToUpdate();

				Serializable oldValue = before.get(propName);
				logger.debug("Old value :" + oldValue);
				logger.debug("configuration exists for " + nodeRef + " type :" + type);

				String newValue = (String) modification.getNewValue();

				logger.debug("new computed Value :" + newValue);

				// validate the newName against constraints			
				String validateNewValue = validateNewValue(newValue, propName);

				if (!newValue.equals(oldValue)) {
					after.put(propName, validateNewValue);
					changes = true;
				}
				logger.debug("New Value :" + after.get(propName));
			}
			if (changes) {
				logger.debug("some changes exists apply new properties values ...");
				getNodeService().setProperties(nodeRef, after);
			}
		} else {
			logger.trace("no configuration for node :" + nodeRef);
		}
	}

	public void onCreateNode(ChildAssociationRef childAssocRef) {
		// TODO Auto-generated method stub

	}

	/**
	 * get pattern for cm:name
	 * 
	 * @param qnameType
	 * @return
	 */
	public List<String> getNamePatternForType(QName qnameType) {
		return nameUpdaterConfig.getValue(getconfigKey(qnameType));
	}

	public boolean hasNamePattern(QName type) {
		return nameUpdaterConfig.hasValue(getconfigKey(type));
	}

	public List<Modification> getModification(QName type, PatternPropertiesUpdater updater) {
		List<Modification> modifs = new ArrayList<PropertiesUpdaterPolicy.Modification>();
		List<String> patterns = getNamePatternForType(type);
		if (patterns != null) {
			for (String pattern : patterns) {
				if (pattern != null) {
					Modification modif = new Modification(pattern);
					modifs.add(modif);

					String prefixString = modif.getPropertyToUpdate().toPrefixString(serviceRegistry.getNamespaceService());
					String newValue = updater.getNewValue(prefixString, modif.getPattern());

					modif.setNewValue(newValue);
				}
			}
		}
		return modifs;
	}

	/**
	 * Test newValue against property constraints, if fail try to fix the
	 * newValue
	 * 
	 * @param newName
	 * @param propName
	 * @return
	 */
	private String validateNewValue(String newName, QName propName) {
		List<ConstraintDefinition> constraints = getPropertyConstraints(propName);

		for (ConstraintDefinition constraintDefinition : constraints) {
			try {
				constraintDefinition.getConstraint().evaluate(newName);
			} catch (ConstraintException e) {
				newName = fixNewValue(newName, constraintDefinition);
			}
		}
		return newName;
	}

	/**
	 * Try to Fix the new computed value according to constraints
	 * 
	 * @param newName
	 * @param propName
	 * @return
	 */
	private String fixNewValue(String newName, ConstraintDefinition constraintDefinition) {
		QName createQName = QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, "filename");
		if (constraintDefinition.getName().toPrefixString().equals(createQName)) {
			return newName.replaceAll(alfrescoFileNameToFix, "_");
		}
		return null;
	}

	private List<ConstraintDefinition> getPropertyConstraints(QName propName) {
		return getDictionaryService().getProperty(propName).getConstraints();
	}

	private String getconfigKey(QName type) {
		return type.toString();
	}

	class Modification {
		Serializable oldValue;
		Serializable newValue;
		String pattern;
		QName propertyToUpdate;

		public Modification(String fullPattern) {
			String[] split = fullPattern.split("=");
			this.propertyToUpdate = QName.createQName(split[0], serviceRegistry.getNamespaceService());
			this.pattern = split[1];
		}

		public Serializable getOldValue() {
			return oldValue;
		}

		public void setOldValue(Serializable oldValue) {
			this.oldValue = oldValue;
		}

		public Serializable getNewValue() {
			return newValue;
		}

		public void setNewValue(Serializable newValue) {
			this.newValue = newValue;
		}

		public String getPattern() {
			return pattern;
		}

		public void setPattern(String pattern) {
			this.pattern = pattern;
		}

		public QName getPropertyToUpdate() {
			return propertyToUpdate;
		}

		public void setPropertyToUpdate(QName propertyToUpdate) {
			this.propertyToUpdate = propertyToUpdate;
		}

		public String toString() {
			String s = "{";
			s += "oldValue:" + oldValue;
			s += ", newValue:" + newValue;
			s += ", pattern:" + pattern;
			s += ", propertyToUpdate:" + propertyToUpdate;
			s += "}";

			return s;
		}
	}

	/**
	 * Need to Sort and Order Rules if rules have dependencies to other, need to
	 * detect loops too
	 * see http://en.wikipedia.org/wiki/Topological_sorting for algorithm
	 * 
	 * @return
	 */
	public boolean hasCycleAndTreeSort() {

		boolean cycle = false;
		Set<Entry<String, List<String>>> entrySet = nameUpdaterConfig.getDictionary().entrySet();
		for (Entry<String, List<String>> entry : entrySet) {

			String key = entry.getKey();
			List<String> value = entry.getValue();
			//			Map<String, List<String>> dependencies = new HashMap<String, List<String>>();
			//			CollectionHelper<String, String> h = new CollectionHelper<String, String>(dependencies);

			logger.debug("[hasCycle] key :" + key);

			for (String fullPattern : value) {

				Modification mod = new Modification(fullPattern);
				//				QName propertyToUpdate = mod.getPropertyToUpdate();
				//				String propertyKey = propertyToUpdate.toPrefixString();
				String pattern = mod.getPattern();

				Pattern p = Pattern.compile(PatternPropertiesUpdater.EXPRESSION_PATTERN);
				Matcher matcher = p.matcher(pattern);
				logger.debug("[hasCycle] tokenizePattern :" + pattern);

				while (matcher.find()) {

					String token = matcher.group(1);
					logger.debug("[hasCycle] QName :" + token);

					//					h.addToMap(token, propertyKey, true);

				}
			}
		}

		//TODO : search for cycle in the dependencies Graph

		return cycle;
	}
}
