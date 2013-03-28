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
