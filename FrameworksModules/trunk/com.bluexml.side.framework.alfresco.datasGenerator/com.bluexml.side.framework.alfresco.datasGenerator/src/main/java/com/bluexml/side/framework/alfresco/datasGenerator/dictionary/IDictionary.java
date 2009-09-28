/**
 * 
 */
package com.bluexml.side.framework.alfresco.datasGenerator.dictionary;

import com.bluexml.side.framework.alfresco.datasGenerator.structure.IStructure;

/**
 * @author davidchevrier
 *
 */
public interface IDictionary {
	
	public IStructure getStructure(String modelName);

}
