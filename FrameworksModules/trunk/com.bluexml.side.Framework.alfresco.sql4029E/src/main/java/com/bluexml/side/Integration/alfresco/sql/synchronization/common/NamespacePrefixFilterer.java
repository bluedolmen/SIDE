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
import java.util.Iterator;
import java.util.List;

import org.alfresco.model.ContentModel;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.service.transaction.TransactionService;
import org.apache.log4j.Logger;

import com.bluexml.side.Integration.alfresco.sql.synchronization.dictionary.DatabaseDictionary;
/**
 * A filter mode to limit the number of class to synchronize in the SQL Database
 * This filter limits using :
 * - the parameter "synchrodb.namespacePrefix" of the synchronisation.properties file:
 * 		it gives the namespace prefix of the classes to authorize
 * 		Ex: synchrodb.namespacePrefix=http://www.bluexml.com/model/content/MyModel/ to authorize the types of 'MyModel'
 *
 */
public class NamespacePrefixFilterer extends AbstractFilterer {

	private Logger logger = Logger.getLogger(getClass());

	public boolean acceptQName(QName qname) {
		return qname.getNamespaceURI().startsWith(namespacePrefix);
	}

	public boolean acceptPropertyQName(QName qname) {
		return super.acceptPropertyQName(qname) || ContentModel.PROP_NODE_DBID.equals(qname) || ContentModel.PROP_NODE_UUID.equals(qname);
	}
	
	//
	// IoC/DI Spring
	//
	
	// Dependencies
	private String namespacePrefix;

	public void setNamespacePrefix(String namespacePrefix_) {
		namespacePrefix = namespacePrefix_;
	}
	
	NamespaceService namespaceService;
	public void setNamespaceService(NamespaceService namespaceService_) {
		namespaceService = namespaceService_;
	}
	
	private DatabaseDictionary databaseDictionary;
	public void setDatabaseDictionary(DatabaseDictionary databaseDictionary_) {
		databaseDictionary = databaseDictionary_;
	}
	
	

}
