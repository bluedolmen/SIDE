/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.generator;

import com.bluexml.side.Framework.alfresco.dataGenerator.graph.IArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.INode;

/**
 * @author davidchevrier
 *
 */
public interface IInstance {
	
	/**
	 * 
	 * @param type
	 * @return the instance node of the given type
	 * @throws Exception
	 */
	public INode instanciation(Object type) throws Exception;
	
	/**
	 * 
	 * @param source
	 * @param target
	 * @param associationDefinition
	 * @return arc instance of the association 
	 */
	public IArc instanciation(INode source, INode target, Object associationDefinition);

}
