/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.dictionary;

import com.bluexml.side.Framework.alfresco.dataGenerator.structure.IStructure;

/**
 * @author davidchevrier
 *
 */
public interface IDictionary {
	
	public IStructure getStructure(String modelName);

}
