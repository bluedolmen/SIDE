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
