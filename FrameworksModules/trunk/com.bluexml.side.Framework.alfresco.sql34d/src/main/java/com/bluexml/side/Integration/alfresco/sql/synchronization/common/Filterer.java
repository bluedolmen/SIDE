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
	
}
