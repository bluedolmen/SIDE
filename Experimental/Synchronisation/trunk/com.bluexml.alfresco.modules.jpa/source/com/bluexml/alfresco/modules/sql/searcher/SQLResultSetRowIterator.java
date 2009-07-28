/**
 * 
 */
package com.bluexml.alfresco.modules.sql.searcher;

import org.alfresco.repo.search.AbstractResultSetRowIterator;
import org.alfresco.service.cmr.search.ResultSetRow;

/**
 * @author pajot-b
 *
 */
public class SQLResultSetRowIterator extends AbstractResultSetRowIterator {

	public SQLResultSetRowIterator(SQLResultSet resultSet) {
		super(resultSet);
	}

	/* (non-Javadoc)
	 * @see org.alfresco.repo.search.AbstractResultSetRowIterator#next()
	 */
	@Override
	public ResultSetRow next() {
		ResultSetRow result = null;
		if (hasNext()) {
			int nextPosition = moveToNextPosition();
			result = ((SQLResultSet) getResultSet()).getRow(nextPosition);
		}
		return result; 
	}

	/* (non-Javadoc)
	 * @see org.alfresco.repo.search.AbstractResultSetRowIterator#previous()
	 */
	@Override
	public ResultSetRow previous() {
		ResultSetRow result = null;
		if (hasPrevious()) {
			int previousPosition = moveToPreviousPosition();
			result = ((SQLResultSet) getResultSet()).getRow(previousPosition);
		}
		return result; 
	}

}
