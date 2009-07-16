package com.bluexml.alfresco.modules.sql.synchronisation;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.alfresco.repo.node.NodeServicePolicies.BeforeDeleteNodePolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnCreateAssociationPolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnCreateChildAssociationPolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnCreateNodePolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnDeleteAssociationPolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnDeleteChildAssociationPolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnUpdatePropertiesPolicy;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.repo.policy.Behaviour.NotificationFrequency;
import org.alfresco.repo.transaction.AlfrescoTransactionSupport;
import org.alfresco.repo.transaction.TransactionListener;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.bluexml.alfresco.modules.sql.synchronisation.dictionary.DatabaseDictionary;

public class SQLSynchronisationPolicy implements OnCreateNodePolicy, OnUpdatePropertiesPolicy, OnCreateAssociationPolicy, OnDeleteAssociationPolicy, OnCreateChildAssociationPolicy, OnDeleteChildAssociationPolicy, BeforeDeleteNodePolicy {

	// Behaviours
	private Behaviour onCreateNode;
	private Behaviour onUpdateProperties;
	private Behaviour onCreateAssociation;
	private Behaviour onCreateChildAssociation;
	private Behaviour onDeleteChildAssociation;
	private Behaviour beforeDeleteNode;
	private Behaviour onDeleteAssociation;

	private Logger logger = Logger.getLogger(getClass());

	private static final String BLUEXML_MODEL_URI = "http://www.bluexml.com/model/content/";

	private static ThreadLocal<TransactionListenerForAssociationClasses> listener = new ThreadLocal<TransactionListenerForAssociationClasses>();

	public void init() {

		logger.debug("[init] Initializing relational synchronisation");

		// Create behaviours
		this.onCreateNode = new JavaBehaviour(this, "onCreateNode", NotificationFrequency.TRANSACTION_COMMIT);
		this.onUpdateProperties = new JavaBehaviour(this, "onUpdateProperties", NotificationFrequency.TRANSACTION_COMMIT);
		this.onCreateAssociation = new JavaBehaviour(this, "onCreateAssociation", NotificationFrequency.TRANSACTION_COMMIT);
		this.onCreateChildAssociation = new JavaBehaviour(this, "onCreateChildAssociation", NotificationFrequency.TRANSACTION_COMMIT);
		
		this.beforeDeleteNode = new JavaBehaviour(this, "beforeDeleteNode", NotificationFrequency.FIRST_EVENT); 
		this.onDeleteAssociation = new JavaBehaviour(this, "onDeleteAssociation", NotificationFrequency.TRANSACTION_COMMIT); 
		this.onDeleteChildAssociation = new JavaBehaviour(this, "onDeleteChildAssociation", NotificationFrequency.EVERY_EVENT);

		// Bind behaviours to node policies
		policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateNode"), this,this.onCreateNode);
		policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onUpdateProperties"), this,this.onUpdateProperties);
		policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "beforeDeleteNode"), this,this.beforeDeleteNode);
		policyComponent.bindAssociationBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateAssociation"), this,this.onCreateAssociation);
		policyComponent.bindAssociationBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateChildAssociation"), this,this.onCreateChildAssociation);
		policyComponent.bindAssociationBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onDeleteChildAssociation"), this,this.onDeleteChildAssociation);
		policyComponent.bindAssociationBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onDeleteAssociation"), this,this.onDeleteAssociation);
	}

	/**
	 * Bind a listener for the transaction of this thread.
	 */
	private synchronized void bindListener() {
		if (listener.get() == null) {
			listener.set(new TransactionListenerForAssociationClasses(dataSource));
		}
		try {
			AlfrescoTransactionSupport.bindListener(listener.get());
		} catch (Exception e) {
			listener.set(null);
			logger.error("[bindListener] Failed to register listener", e);
		}
	}

	private void executeSQLQuery(String sql_query) {
		bindListener();
		if (listener.get() != null) {
			listener.get().executeSQLQuery(sql_query);
		}
	}

	private class TransactionListenerForAssociationClasses implements TransactionListener {

		private DataSource dataSource;
		private Connection currentAlfRelationalConnection;
		private String alfrescoTransactionId = null;

		public TransactionListenerForAssociationClasses(DataSource dataSource_) {
			dataSource = dataSource_;
		}
		
		private Connection getConnection() {
			String currentTransactionId = AlfrescoTransactionSupport.getTransactionId();
			logger.debug("[getConnection] alfresco transaction id : " + currentTransactionId);
			if (!StringUtils.equals(currentTransactionId, alfrescoTransactionId)) {
				if (currentAlfRelationalConnection != null) {
					try {
						if (!currentAlfRelationalConnection.isClosed()) {
							currentAlfRelationalConnection.close();
						}
					} catch (SQLException e) {
						logger.error("[getConnection]", e);
					}
				}
				alfrescoTransactionId = currentTransactionId;
				if (dataSource != null) {
					try {
						currentAlfRelationalConnection = dataSource.getConnection();
						logger.debug("[getConnection] opened : " + currentAlfRelationalConnection);
					} catch (SQLException e) {
						logger.error("[getConnection]", e);
					}
				}
			}
			logger.debug("[getConnection] connection : " + currentAlfRelationalConnection);
			return currentAlfRelationalConnection;
		}

		/**
		 * Execute an SQL query by performing all checking operation and
		 * opening/closure of related sql artefacts
		 * 
		 * @param query
		 */
		public void executeSQLQuery(String sql_query) {
			Connection connection = getConnection();
			if (connection != null) {
				Statement st = null;
				try {
					logger.debug("[executeSQLQuery] " + sql_query);
					st = connection.createStatement();
					int rowCount = st.executeUpdate(sql_query);
					logger.debug("[executeSQLQuery] Row count : " + rowCount);
				} catch (SQLException e) {
					logger.error("[executeSQLQuery]", e);
				} finally {
					if (st != null) {
						try {
							st.close();
						} catch (SQLException e) {
							logger.error("[executeSQLQuery]", e);
						}
						st = null;
					}
				}
			}
		}

		private void tryCommit() {
			Connection connection = getConnection();
			if (connection != null) {
				logger.debug("[tryCommit] on " + connection);
				try {
					connection.commit();
				} catch (SQLException e) {
					logger.error("[tryCommit]", e);
				} finally {
					tryClose(connection);
				}
			}
		}

		private void tryRollback() {
			Connection connection = getConnection();
			if (connection != null) {
				logger.debug("[tryRollback] on " + connection);
				try {
					connection.rollback();
				} catch (SQLException e) {
					logger.error("[tryRollback]", e);
				} finally {
					tryClose(connection);
				}
			}
		}

		private void tryClose(Connection connection) {
			if (connection != null) {
				logger.debug("[tryClose] on " + connection);
				try {
					connection.close();
				} catch (SQLException e) {
					logger.error("[tryClose]", e);
				}
			}
		}

		public void afterCommit() {
			logger.debug("[afterCommit]" + AlfrescoTransactionSupport.getTransactionId());
			tryCommit();
		}

		public void afterRollback() {
			tryRollback();
		}

		public void beforeCommit(boolean readOnly) {
			if (!readOnly) {
				logger.debug("[beforeCommit] " + AlfrescoTransactionSupport.getTransactionId());
			}
		}

		public void beforeCompletion() {
			// nothing
		}

		public void flush() {
			// nothing
		}

	}


	public void onCreateNode(ChildAssociationRef childAssociationRef) {
		NodeRef nodeRef = childAssociationRef.getChildRef();

		// Only process bluexml nodes
		if (hasToBeProcessed(nodeRef)) {
			logger.debug("[onCreateNode] CUSTOM CREATE");
		}
	}

	public void beforeDeleteNode(NodeRef nodeRef) {
		deleteNode(nodeRef);
	}

	private void deleteNode(NodeRef nodeRef) {
		if (hasToBeProcessed(nodeRef)) {
			String type_name = nodeService.getType(nodeRef).getLocalName();
			logger.debug("[deleteNode] CUSTOM DELETE");
		}
	}

	public void onUpdateProperties(NodeRef nodeRef, Map<QName, Serializable> before_properties, Map<QName, Serializable> after_properties) {
		if (hasToBeProcessed(nodeRef)) {
			Map<QName, Serializable> changes = new HashMap<QName, Serializable>();
			for (QName key : after_properties.keySet()) {
				if (key.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {
					if (!before_properties.containsKey(key)
							|| before_properties.get(key) == null
							|| !before_properties.get(key).equals(
									after_properties.get(key))) {
						// Hack : if before_properties.get(key) == null, we
						// accepted the value for a change
						// to avoid nullpointerexception with the equals
						// statement
						if (!(before_properties.get(key) == null && after_properties
								.get(key) == null))
							changes.put(key, after_properties.get(key));
					}
				}
			}

			if (changes.size() > 0) {
				logger.debug("[onUpdateProperties] CUSTOM UPDATE PROPERTIES");

			} else {
				logger.debug("[onUpdateProperties] update skiped because one or more invalide properties");
			}
		}

	}



	public void onCreateAssociation(AssociationRef associationref) {
		QName association_type = associationref.getTypeQName();
		if (association_type.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {
			logger.debug("[onCreateAssociation] CUSTOM CREATE ASSOCIATION");

		}
	}
	
	public void onDeleteAssociation(AssociationRef associationref) {
		QName association_type = associationref.getTypeQName();
		if (association_type.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {
		}
	}

	public void onCreateChildAssociation(ChildAssociationRef associationref,
			boolean isNewNode) {
		QName association_type = associationref.getTypeQName();
		if (association_type.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {
			logger.debug("[onCreateChildAssociation] CUSTOM CREATE CHILD ASSOCIATION");
		}
	}

	public void onDeleteChildAssociation(ChildAssociationRef associationref) {
		QName association_type = associationref.getTypeQName();
		if (association_type.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {

			logger.debug("[onDeleteChildAssociation] CUSTOM DELETE CHILD ASSOCIATION");

		}
	}


	private boolean hasToBeProcessed(NodeRef nodeRef) {
		boolean result = false;
		QName nodeType = nodeService.getType(nodeRef);
		String uri = nodeType.getNamespaceURI();
		if (uri.startsWith(BLUEXML_MODEL_URI)) {
			Serializable store_protocol = nodeService.getProperty(nodeRef, QName.createQName(NamespaceService.SYSTEM_MODEL_1_0_URI, "store-protocol"));
			// Only process nodes which are not in the archive space (the node
			// was deleted)
			if (!store_protocol.equals("archive")) {
				String type_name = nodeService.getType(nodeRef).getLocalName();
				if (!type_name.matches(".*_search$")) {
					result = true;
				}
			}
		}
		return result;
	}


	//
	// IoC/DI Spring
	//

	// Dependencies
	private NodeService nodeService;
	private PolicyComponent policyComponent;
	private DataSource dataSource;

	public void setNodeService(NodeService nodeService_) {
		nodeService = nodeService_;
	}

	public void setPolicyComponent(PolicyComponent policyComponent_) {
		policyComponent = policyComponent_;
	}

	public void setDataSource(DataSource dataSource_) {
		dataSource = dataSource_;
	}
}
