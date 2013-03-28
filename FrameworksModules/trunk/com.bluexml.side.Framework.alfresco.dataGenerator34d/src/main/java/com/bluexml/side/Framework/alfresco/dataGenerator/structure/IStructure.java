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
package com.bluexml.side.Framework.alfresco.dataGenerator.structure;

import java.util.Collection;
import java.util.Map;

/**
 * @author davidchevrier
 *
 */
public interface IStructure {
	
	/**
	 * allows access to model's defined types
	 * @return types defined in the model
	 */
	public Collection<? extends Object> getTypes();
	/**
	 * allows acces to models'defined properties by type
	 * @return properties by types
	 */
	public Map<? extends Object, ? extends Object> getProperties();
	/**
	 * allows access to model's defined associations
	 * @return associations
	 */
	public Collection<? extends Object> getAssociations();
	/**
	 * allows access to model's defined aspects by type associated to those aspects
	 * @return aspects by associated types
	 */
	public Map<? extends Object, ? extends Object> getAspects();

}
