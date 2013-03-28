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
/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.data;

import java.util.Collection;

import com.bluexml.side.Framework.alfresco.dataGenerator.graph.IArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.INode;

/**
 * @author davidchevrier
 *
 */
public interface IData {
	
	/**
	 * to access the generated types instances
	 * @return the generated types instances
	 */
	public Collection<INode> getGeneratedTypesInstances();
	/**
	 * to access the generated associations instances
	 * @return the generated associations instances
	 */
	public Collection<IArc> getGeneratedAssociationsInstances();
	
}
