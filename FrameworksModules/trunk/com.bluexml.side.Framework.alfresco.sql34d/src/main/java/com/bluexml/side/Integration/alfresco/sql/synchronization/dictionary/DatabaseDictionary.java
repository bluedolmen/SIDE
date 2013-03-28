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
