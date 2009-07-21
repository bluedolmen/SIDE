package com.bluexml.alfresco.modules.sql.synchronisation.schemaManagement;

public interface ContentReplication {

	public abstract void removeExistingData();

	public abstract void addExistingData();

}