package com.bluexml.side.Integration.alfresco.sql.synchronization.common;

import java.util.ArrayList;
import java.util.HashMap;

import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

public interface Filterer {

	public boolean acceptQName(QName qname);
	
	public boolean acceptModelQName(QName qname);
	
	public boolean acceptTypeQName(QName qname);
	
	public boolean acceptAssociationQName(QName qname);
	
	public boolean acceptPropertyQName(QName qname);
	
	public boolean accept(NodeRef nodeRef);
	
	public boolean accept(AssociationRef associationRef);
	
	public boolean accept(ChildAssociationRef childAssociationRef);
	
	public void setExternalTypesMapping(String externalTypesMapping_);

	/**
	 * This method gets the list of external class (usually defined in alfresco models) which need to be defined in the SQL physical model
	 * Externalm in the sense that they are not in the filtered namespace given by the parameter 'synchrodb.namespacePrefix'
	 * @return an hashmap of the QName corresponding ot the list of external class to map as key and their attributes to map as values, 
	 * 			attributes are given in the synchronization-database-mapping.properties configuration file
	 * 
	 */
	public HashMap<QName, ArrayList<QName>> getExternalTypesMappingArray();
	
}
