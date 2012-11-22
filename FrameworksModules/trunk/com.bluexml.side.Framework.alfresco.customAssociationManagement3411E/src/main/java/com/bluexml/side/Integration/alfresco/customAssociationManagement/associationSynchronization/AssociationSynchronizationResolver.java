package com.bluexml.side.Integration.alfresco.customAssociationManagement.associationSynchronization;

import org.alfresco.service.namespace.QName;

public interface AssociationSynchronizationResolver {
	
	/*
	 * Returns true if the association is both-navigable
	 */
	public boolean isBothNavigable(QName associationQName);
	
	/*
	 * Returns the QName of the association when read in the opposite direction
	 * Returns null if the name cannot be resolved
	 */
	public QName resolve(QName associationQName);
}
