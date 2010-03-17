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
