package com.bluexml.side.Framework.alfresco.propertiesUpdater;

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
import org.alfresco.service.cmr.dictionary.ConstraintDefinition;
import org.alfresco.service.cmr.dictionary.ConstraintException;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.security.AuthenticationService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluexml.side.Framework.alfresco.propertiesUpdater.ConfigurationReader.PropertyPattern;

public class PropertiesUpdaterPolicy implements OnCreateNodePolicy, OnUpdatePropertiesPolicy {
	private static final NotificationFrequency EVENT_FREQUENCY = NotificationFrequency.TRANSACTION_COMMIT;

	private static Log logger = LogFactory.getLog(PropertiesUpdaterPolicy.class);

	protected ConfigurationReader nameUpdaterConfig;
	private PolicyComponent policyComponent;

	// Behaviours
	private Behaviour onCreateNode;
	private Behaviour onUpdateProperties;

	public static String alfrescoFileNameToFix = "([\\\"\\*\\\\\\>\\<\\?\\/\\:\\|]+)|([\\.]+$)|([ ]+$)";

	protected NamespaceService namespaceService;

	public NamespaceService getNamespaceService() {
		return namespaceService;
	}

	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

	protected AuthenticationService authenticationService;

	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	protected NodeService nodeService;

	protected DictionaryService dictionaryService;

	public NodeService getNodeService() {
		return nodeService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public ConfigurationReader getNameUpdaterConfig() {
		return nameUpdaterConfig;
	}

	public void setNameUpdaterConfig(ConfigurationReader nameUpdaterConfig) {
		this.nameUpdaterConfig = nameUpdaterConfig;
	}

	public DictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
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

	public void init() {
		logger.info("[init] Initializing NameUpdaterPolicy");
		if (nameUpdaterConfig.getDictionary().size() > 0) {
			// Create behaviours using NotificationFrequency.TRANSACTION_COMMIT
			onCreateNode = new JavaBehaviour(this, "onCreateNode", EVENT_FREQUENCY);
			onUpdateProperties = new JavaBehaviour(this, "onUpdateProperties", EVENT_FREQUENCY);

			// Bind behaviours to node policies
			policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateNode"), this, onCreateNode);
			policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onUpdateProperties"), this, onUpdateProperties);
		} else {
			// no configuration so disable policy
			logger.info("Policy disabled ! (no configuration found)");
		}

	}

	public void onUpdateProperties(NodeRef nodeRef, Map<QName, Serializable> before, Map<QName, Serializable> after) {

		String currentUserName = authenticationService.getCurrentUserName();
		if (logger.isDebugEnabled()) {
			logger.debug("Current User :" + currentUserName);
		}
		// sometime nodeRef that can't be founded by nodeService
		if (currentUserName != null && !currentUserName.equals("null") && nodeService.exists(nodeRef)) {

			QName type = getNodeService().getType(nodeRef);

			if (hasNamePattern(type)) {
				// filter and prepare propertyValues map only String values are managed
				Map<String, String> oldValues = new HashMap<String, String>();
				Map<String, String> newValues = new HashMap<String, String>();
				for (Map.Entry<QName, Serializable> beforeEntry : before.entrySet()) {
					String prefixString = beforeEntry.getKey().toPrefixString(getNamespaceService());
					String value = beforeEntry.getValue() != null ? beforeEntry.getValue().toString() : "";
					oldValues.put(prefixString, value);
				}
				for (Map.Entry<QName, Serializable> afterEntry : after.entrySet()) {
					QName key = afterEntry.getKey();
					String prefixString = key.toPrefixString(getNamespaceService());
					String value = afterEntry.getValue() != null ? afterEntry.getValue().toString() : "";
					newValues.put(prefixString, value);
				}

				PatternPropertiesUpdater updater = new PatternPropertiesUpdater(oldValues, newValues);

				boolean changes = false;
				List<Modification> modifications = getModification(type, updater);
				for (Modification modification : modifications) {
					if (logger.isDebugEnabled()) {
						logger.debug("current Modification :" + modification);
					}
					QName propName = modification.getPropertyToUpdate();

					Serializable oldValue = before.get(propName);
					if (logger.isDebugEnabled()) {
						logger.debug("Old value :" + oldValue);
						logger.debug("configuration exists for " + nodeRef + " type :" + type);
					}
					String newValue = (String) modification.getNewValue();
					if (logger.isDebugEnabled()) {
						logger.debug("new computed Value :" + newValue);
					}
					// validate the newName against constraints			
					String validateNewValue = validateNewValue(newValue, propName);

					if (!newValue.equals(oldValue)) {
						after.put(propName, validateNewValue);
						changes = true;
					}
					if (logger.isDebugEnabled()) {
						logger.debug("New Value :" + after.get(propName));
					}
				}
				if (changes) {
					if (logger.isDebugEnabled()) {
						logger.debug("some changes exists apply new properties values ...");
					}
					getNodeService().setProperties(nodeRef, after);
				}
			} else if (logger.isDebugEnabled()) {
				logger.debug("no configuration for node :" + nodeRef);

			}

		} else if (logger.isTraceEnabled()) {
			logger.trace("This transaction is run without using any user context ...");
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
	public List<PropertyPattern> getNamePatternForType(QName qnameType) {
		return nameUpdaterConfig.getValue(getconfigKey(qnameType));
	}

	public boolean hasNamePattern(QName type) {
		return nameUpdaterConfig.hasValue(getconfigKey(type));
	}

	public List<Modification> getModification(QName type, PatternPropertiesUpdater updater) {
		List<Modification> modifs = new ArrayList<PropertiesUpdaterPolicy.Modification>();
		List<PropertyPattern> patterns = getNamePatternForType(type);
		if (patterns != null) {
			for (PropertyPattern pattern : patterns) {
				if (pattern != null) {
					Modification modif = new Modification(pattern);
					modifs.add(modif);

					String prefixString = modif.getPropertyToUpdate().toPrefixString(getNamespaceService());
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
				logger.debug("new value is not valide try to Fix > " + newName);
				newName = fixNewValue(newName, constraintDefinition);
				logger.debug("Fixed value :" + newName);
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
		String shortName = constraintDefinition.getConstraint().getShortName();
		String prefixString = createQName.toPrefixString(getNamespaceService());
		if (shortName.equals(prefixString)) {
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

		public Modification(PropertyPattern fullPattern) {

			this.propertyToUpdate = QName.createQName(fullPattern.propertyQname, getNamespaceService());
			this.pattern = fullPattern.pattern;
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
	 * TODO
	 * Need to Sort and Order Rules if rules have dependencies to other, need to
	 * detect loops too
	 * see http://en.wikipedia.org/wiki/Topological_sorting for algorithm
	 * 
	 * @return
	 */
	public boolean hasCycleAndTreeSort() {

		boolean cycle = false;
		Set<Entry<String, List<PropertyPattern>>> entrySet = nameUpdaterConfig.getDictionary().entrySet();
		for (Entry<String, List<PropertyPattern>> entry : entrySet) {

			String key = entry.getKey();
			List<PropertyPattern> value = entry.getValue();
			//			Map<String, List<String>> dependencies = new HashMap<String, List<String>>();
			//			CollectionHelper<String, String> h = new CollectionHelper<String, String>(dependencies);

			logger.debug("[hasCycle] key :" + key);

			for (PropertyPattern fullPattern : value) {

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
