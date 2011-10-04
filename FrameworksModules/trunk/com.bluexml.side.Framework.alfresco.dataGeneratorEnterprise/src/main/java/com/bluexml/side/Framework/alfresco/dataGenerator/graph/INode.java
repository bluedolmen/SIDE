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
