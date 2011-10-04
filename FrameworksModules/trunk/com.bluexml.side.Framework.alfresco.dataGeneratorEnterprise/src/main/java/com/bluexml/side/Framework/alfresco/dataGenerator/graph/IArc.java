/**
 * This interface defines access to associations's instances
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.graph;


/**
 * @author davidchevrier
 *
 */
public interface IArc {
	
	/**
	 * allows access to source instance of arc
	 * @return source node
	 */
	public INode getSource();
	/**
	 * allows access to target instance of arc
	 * @return target node
	 */
	public INode getTarget();
	/**
	 * allows access to association type associated with arc instance
	 * @return type of association associated with arc instance
	 */
	public Object getTypeAssociation();

}
