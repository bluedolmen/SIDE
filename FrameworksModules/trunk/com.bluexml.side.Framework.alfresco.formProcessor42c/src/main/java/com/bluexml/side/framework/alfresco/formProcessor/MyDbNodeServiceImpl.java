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

	/*
	 * (non-Javadoc)
	 * @see org.alfresco.repo.node.AbstractNodeServiceImpl#init()
	 */
	@Override
	public void init() {
		super.init();
		logger.info("[init] MyDBNodeServiceImpl initialization (enable createAssociation to invoke OnUpdateNode)");

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.alfresco.repo.node.db.DbNodeServiceImpl#createAssociation(org.alfresco
	 * .service.cmr.repository.NodeRef,
	 * org.alfresco.service.cmr.repository.NodeRef,
	 * org.alfresco.service.namespace.QName)
	 */
	/**
	 * override createAssociation to call invokeOnUpdateNode,
	 * this allow to define NodeServicePolicies aware of global node
	 * modification
	 * some actions need to have a view on properties AND associations at the
	 * same time
	 */
	@Override
	public AssociationRef createAssociation(NodeRef sourceRef, NodeRef targetRef, QName assocTypeQName) throws InvalidNodeRefException, AssociationExistsException {
		AssociationRef createAssociation = super.createAssociation(sourceRef, targetRef, assocTypeQName);
		logger.debug("MyDbNodeServiceImpl.createAssociation() invoke OnUpdateNode policies on " + sourceRef);
		this.invokeOnUpdateNode(sourceRef);
		return createAssociation;
	}

}
