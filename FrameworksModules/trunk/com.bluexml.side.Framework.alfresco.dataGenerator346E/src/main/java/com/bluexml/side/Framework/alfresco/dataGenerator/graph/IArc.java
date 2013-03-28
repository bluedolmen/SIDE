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
 * This interface defines access to associations's instances
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.graph;


/**
 * @author davidchevrier
 *
 */
public interface IArc {
	
	/**
	 * allows access to source instance of arc
	 * @return source node
	 */
	public INode getSource();
	/**
	 * allows access to target instance of arc
	 * @return target node
	 */
	public INode getTarget();
	/**
	 * allows access to association type associated with arc instance
	 * @return type of association associated with arc instance
	 */
	public Object getTypeAssociation();

}
