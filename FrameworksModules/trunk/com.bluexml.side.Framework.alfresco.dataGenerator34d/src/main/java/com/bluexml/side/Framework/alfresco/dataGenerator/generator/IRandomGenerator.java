/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.generator;

import com.bluexml.side.Framework.alfresco.dataGenerator.structure.IStructure;

/**
 * @author davidchevrier
 *
 */
public interface IRandomGenerator {
	
	public boolean generateNodesInstances(IStructure structure) throws Exception;
	public boolean generateArcsInstances(IStructure structure) throws Exception;

}
