/**
 * 
 */
package com.bluexml.alfresco.modules.sql.searcher;

import java.sql.SQLException;
import java.util.Iterator;

import org.alfresco.repo.search.AbstractResultSet;
import org.alfresco.repo.tenant.TenantService;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.Path;
import org.alfresco.service.cmr.search.ResultSetMetaData;
import org.alfresco.service.cmr.search.ResultSetRow;
import org.alfresco.service.cmr.search.SearchParameters;

/**
 * Implementation of the Alfresco ResultSet for SQL requests
 * This class encapsulates java.sql ResultSet by providing NodeRef flavors 
 * @author pajot-b
 *
 */
public class SQLResultSet extends AbstractResultSet {

	private static String UUID = "uuid";
	
	private java.sql.ResultSet resultSet;
	private int length;
	
    private NodeService nodeService;
    private TenantService tenantService;
    private SearchParameters searchParameters;
	
    public SQLResultSet(
    		java.sql.ResultSet dataResultSet,
    		NodeService nodeService, 
    		TenantService tenantService, 
    		Path[] propertyPaths, 
    		SearchParameters searchParameters)
    {
        super(propertyPaths);
        this.resultSet = dataResultSet;
        this.nodeService = nodeService;
        this.tenantService = tenantService;
        this.searchParameters = searchParameters;
        
        // TODO : improve (by using count ?) since this method will certainly lack of performance
		try {
			resultSet.last();
		    this.length = resultSet.getRow();
		    resultSet.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }

	/* (non-Javadoc)
	 * @see org.alfresco.service.cmr.search.ResultSet#getChildAssocRef(int)
	 */
	public ChildAssociationRef getChildAssocRef(int n) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see org.alfresco.service.cmr.search.ResultSet#getNodeRef(int)
	 */
	public NodeRef getNodeRef(int n) {
		String id = null;
		try {
			resultSet.absolute(n);
	        id = resultSet.getString(UUID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return tenantService.getBaseName(new NodeRef(id));
	}

	/* (non-Javadoc)
	 * @see org.alfresco.service.cmr.search.ResultSet#getResultSetMetaData()
	 */
	public ResultSetMetaData getResultSetMetaData() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see org.alfresco.service.cmr.search.ResultSet#getRow(int)
	 */
	public ResultSetRow getRow(int i) {
		return new SQLResultSetRow(this, i);
	}

	/* (non-Javadoc)
	 * @see org.alfresco.service.cmr.search.ResultSet#length()
	 */
	public int length() {
		return length;
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<ResultSetRow> iterator() {
		// TODO Auto-generated method stub
		return new SQLResultSetRowIterator(this);
	}

}
