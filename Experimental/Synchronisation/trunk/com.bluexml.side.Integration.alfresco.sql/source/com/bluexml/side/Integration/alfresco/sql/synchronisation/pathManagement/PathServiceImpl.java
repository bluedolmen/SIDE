package com.bluexml.side.Integration.alfresco.sql.synchronisation.pathManagement;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.transaction.TransactionListener;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.Path;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

import com.bluexml.side.Integration.alfresco.sql.synchronisation.common.JdbcTransactionListener;
import com.bluexml.side.Integration.alfresco.sql.synchronisation.common.NodeHelper;
import com.bluexml.side.Integration.alfresco.sql.synchronisation.dialects.SynchronisationDialect;
import com.bluexml.side.Integration.alfresco.sql.synchronisation.dictionary.DatabaseDictionary;

public class PathServiceImpl implements PathService {

	private Logger logger = Logger.getLogger(getClass());

	public void updatePath(NodeRef nodeRef) {
		Path path = nodeService.getPath(nodeRef);
		List<String> sqlQueries = new ArrayList<String>();
		String escapedPathString = synchronisationDialect.quoteString(synchronisationDialect.escape(path.toPrefixString(namespaceService)));
		
		List<QName> parentNames = nodeHelper.getParentQNames(nodeRef);

		for (QName type_qname : parentNames) {
			String type_name = type_qname.getLocalName();

			String simplified_type_name = databaseDictionary.resolveClassAsTableName(type_name);
			Serializable dbid = nodeService.getProperty(nodeRef, ContentModel.PROP_NODE_DBID);
	
			String sqlQuery = String.format("UPDATE %1$s SET %2$s = %3$s WHERE id = %4$s", 
					simplified_type_name, 
					PathManagementCommon.PATH_COLUMN_NAME, 
					escapedPathString, 
					dbid
			);
			sqlQueries.add(sqlQuery);

		}

		executeSQLQuery(sqlQueries);

	}

	
	/*
	 * Helper methods
	 */
	private void executeSQLQuery(List<String> sqlQueries) {
		try {
			transactionListener.executeSQLQuery(sqlQueries);
		} catch (SQLException e) {
			throw new PathServiceFailureException(e);
		}
	}
	
	/*
	 * Spring IoC/DI material
	 */
	
	private JdbcTransactionListener transactionListener;
	private NodeService nodeService;
	private NamespaceService namespaceService;
	private DatabaseDictionary databaseDictionary;
	private NodeHelper nodeHelper;
	private SynchronisationDialect synchronisationDialect;


	public void setNodeService(NodeService nodeService_) {
		nodeService = nodeService_;
	}
	
	public void setNamespaceService(NamespaceService namespaceService_) {
		namespaceService = namespaceService_;
	}

	public void setTransactionListener(TransactionListener transactionListener_) {
		if (! (transactionListener_ instanceof JdbcTransactionListener) ) {
			logger.error("NodeServiceImpl needs a JdbcTransactionListener since implementation is relative to sql synchronisation");
		}
		transactionListener = (JdbcTransactionListener) transactionListener_;
	}
	
	public void setDatabaseDictionary(DatabaseDictionary dbd_) {
		databaseDictionary = dbd_;
	}
	
	public void setNodeHelper(NodeHelper nodeHelper_) {
		nodeHelper = nodeHelper_;
	}
	
	public void setSynchronisationDialect(SynchronisationDialect synchronisationDialect_) {
		synchronisationDialect = synchronisationDialect_;
	}


}
