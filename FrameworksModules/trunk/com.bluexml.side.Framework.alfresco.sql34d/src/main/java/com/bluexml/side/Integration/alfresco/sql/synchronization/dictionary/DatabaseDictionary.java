package com.bluexml.side.Integration.alfresco.sql.synchronization.dictionary;

public interface DatabaseDictionary {

	/**
	 * Resolve class name.
	 * 
	 * @param class_name the class_name
	 * 
	 * @return the resolved table name or null if cannot be resolved
	 */
	public abstract String resolveClassAsTableName(String class_name);

	/**
	 * Resolve association name.
	 * 
	 * @param association_name the association_name
	 * 
	 * @return the resolved association name or null if cannot be resolved
	 */
	public abstract String resolveAssociationAsTableName(String association_name);

	/**
	 * Resolve attribute name.
	 * 
	 * @param attribute_name the attribute_name
	 * @param class_name the class_name
	 * 
	 * @return the resolved attribute name or null if cannot be resolved
	 */
	public abstract String resolveAttributeAsColumnName(String attribute_name,
			String class_name);

	/**
	 * Gets the source class.
	 * 
	 * @param association_name the association_name
	 * 
	 * @return the source class
	 */
	public abstract String getSourceClass(String association_name);

	/*
	 * Returns an alias of the class if any exist
	 * This method is mainly useful on reflexive associations where the both columns cannot have the same name
	 */
	public abstract String getSourceAlias(String association_name);

	/**
	 * Gets the target class.
	 * 
	 * @param association_name the association_name
	 * 
	 * @return the target class
	 */
	public abstract String getTargetClass(String association_name);

	public abstract String getTargetAlias(String association_name);

}