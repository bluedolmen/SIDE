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
 * Arc is equivalent to Association
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.graph;

import org.alfresco.service.cmr.dictionary.AssociationDefinition;

/**
 * @author davidchevrier
 *
 */
public class AlfrescoArc implements IArc {
	
	private INode source;
	private INode target;
	private AssociationDefinition typeAssociation;
	/**
	 * @return the source
	 */
	public INode getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(INode source) {
		this.source = source;
	}
	/**
	 * @return the target
	 */
	public INode getTarget() {
		return target;
	}
	/**
	 * @param target the target to set
	 */
	public void setTarget(INode target) {
		this.target = target;
	}
	/**
	 * @return the typeAssociation
	 */
	public AssociationDefinition getTypeAssociation() {
		return typeAssociation;
	}
	/**
	 * @param typeAssociation the typeAssociation to set
	 */
	public void setTypeAssociation(AssociationDefinition typeAssociation) {
		this.typeAssociation = typeAssociation;
	}
		
}
