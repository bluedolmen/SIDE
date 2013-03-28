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
 * This interface defines access to instance's characteristics of some type defined in the model
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.graph;

import java.util.Map;

/**
 * @author davidchevrier
 *
 */
public interface INode {
	
	/**
	 * allows access to type associated to instance
	 * @return the type
	 */
	public Object getTypeDefinition();
	/**
	 * allows access to filled properties of instance
	 * @return data by property
	 */
	public Map<? extends Object, Object> getDatasProperties();
	/**
	 * allows access to filled aspects
	 * @return filled properties defined in aspects by aspect
	 */
	public Map<? extends Object, ? extends Object> getDataAspects();

}
