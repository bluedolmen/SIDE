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
package com.bluexml.side.framework.alfresco.formProcessor;

import org.alfresco.repo.node.db.DbNodeServiceImpl;
import org.alfresco.service.cmr.repository.AssociationExistsException;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.InvalidNodeRefException;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyDbNodeServiceImpl extends DbNodeServiceImpl {

	private static Log logger = LogFactory.getLog(MyDbNodeServiceImpl.class);
	
	
	
	
	
	
	
	/* (non-Javadoc)
	 * @see org.alfresco.repo.node.AbstractNodeServiceImpl#init()
	 */
	@Override
	public void init() {
		logger.info("[init] MyDBNodeServiceImpl initialization");
		super.init();
	}







	/* (non-Javadoc)
	 * @see org.alfresco.repo.node.db.DbNodeServiceImpl#createAssociation(org.alfresco.service.cmr.repository.NodeRef, org.alfresco.service.cmr.repository.NodeRef, org.alfresco.service.namespace.QName)
	 * 
	 */
	/**
	 * override createAssociation to call invokeOnUpdateNode,
	 * this allow to define NodeServicePolicies aware of global node modification
	 * some actions need to have a view on properties AND associations at the same time 
	 */
	@Override
	public AssociationRef createAssociation(NodeRef sourceRef, NodeRef targetRef, QName assocTypeQName) throws InvalidNodeRefException, AssociationExistsException {
		AssociationRef createAssociation = super.createAssociation(sourceRef, targetRef, assocTypeQName);
		this.invokeOnUpdateNode(sourceRef);
		return createAssociation; 
	}

	

}
