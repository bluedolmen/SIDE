/**
 * 
 */
package com.bluexml.alfresco.modules.sql.searcher;

import java.io.Serializable;

import org.alfresco.repo.search.AbstractResultSetRow;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.Path;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.namespace.QName;

/**
 * @author pajot-b
 *
 */
public class SQLResultSetRow extends AbstractResultSetRow {

	public SQLResultSetRow(ResultSet resultSet, int index) {
		super(resultSet, index);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.alfresco.service.cmr.search.ResultSetRow#getChildAssocRef()
	 */
	public ChildAssociationRef getChildAssocRef() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see org.alfresco.service.cmr.search.ResultSetRow#getQName()
	 */
	public QName getQName() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see org.alfresco.service.cmr.search.ResultSetRow#getValue(org.alfresco.service.cmr.repository.Path)
	 */
	public Serializable getValue(Path path) {
		throw new UnsupportedOperationException();
	}

}
