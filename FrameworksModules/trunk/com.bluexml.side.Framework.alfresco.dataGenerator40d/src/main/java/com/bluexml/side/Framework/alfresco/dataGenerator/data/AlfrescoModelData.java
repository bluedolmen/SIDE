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
 * This class represents the result of generation, i.e. types and associations instances (filled with data) 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.data;

import java.util.Collection;

import com.bluexml.side.Framework.alfresco.dataGenerator.graph.IArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.INode;

/**
 * @author davidchevrier
 *
 */
public class AlfrescoModelData implements IData {
	
	private Collection<INode> generatedTypesInstances;
	private Collection<IArc> generatedAssociationsInstances;
	
	/**
	 * @return the generatedTypesInstances
	 */
	public Collection<INode> getGeneratedTypesInstances() {
		return generatedTypesInstances;
	}
	/**
	 * @param generatedTypesInstances the generatedTypesInstances to set
	 */
	public void setGeneratedTypesInstances(Collection<INode> generatedTypesInstances) {
		this.generatedTypesInstances = generatedTypesInstances;
	}
	/**
	 * @return the generatedAssociations
	 */
	public Collection<IArc> getGeneratedAssociationsInstances() {
		return generatedAssociationsInstances;
	}
	/**
	 * @param generatedAssociations the generatedAssociations to set
	 */
	public void setGeneratedAssociationsInstances(Collection<IArc> generatedAssociations) {
		this.generatedAssociationsInstances = generatedAssociations;
	}
		
}
