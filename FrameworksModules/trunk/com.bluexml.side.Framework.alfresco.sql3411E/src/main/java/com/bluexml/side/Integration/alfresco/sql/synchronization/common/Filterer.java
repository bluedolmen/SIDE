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
	
	public boolean acceptPropertyQName(String className, QName propertyQname);

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
