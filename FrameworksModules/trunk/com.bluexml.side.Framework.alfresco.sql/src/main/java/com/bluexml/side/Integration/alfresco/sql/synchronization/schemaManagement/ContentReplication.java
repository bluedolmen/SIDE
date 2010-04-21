package com.bluexml.side.Integration.alfresco.sql.synchronization.schemaManagement;

import org.alfresco.service.namespace.QName;


public interface ContentReplication {

	public void removeExistingData(QName modelName);

	public void addExistingData(QName modelName);

}