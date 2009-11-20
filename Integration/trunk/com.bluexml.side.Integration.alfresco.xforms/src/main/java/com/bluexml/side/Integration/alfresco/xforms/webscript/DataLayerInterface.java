/**
 * 
 */
package com.bluexml.side.Integration.alfresco.xforms.webscript;

import java.util.List;

import org.alfresco.service.cmr.repository.NodeRef;
import org.w3c.dom.Element;

/**
 * @author Amenel
 * 
 */
public interface DataLayerInterface {

	/**
	 * Create a node with the specified properties.
	 * 
	 * @param where
	 *            the parent folder
	 * @param what
	 *            the DOM tree describing the node properties, compliant with Class.xsd
	 * @param nodeName
	 *            the future name of the node
	 * @return the noderef to the new object
	 */
	public NodeRef create(String where, Element what, String id) throws Exception;

	/**
	 * Return the list of nodes that correspond to the query string.
	 * 
	 * @param xpath
	 *            the Xpath expression to query
	 * @return the list of nodes
	 */
	public List<NodeRef> request(String xpath) throws Exception;

	/**
	 * Delete the node whose id is given.
	 * 
	 * @param objectId
	 *            the full node id including store and protocol, e.g.
	 *            "workspace://SpacesStore/286a1ddc-8aff-11de-9631-c5360495d0b8"
	 * 
	 */
	public void delete(String objectId);

	/**
	 * Update a node and set the given properties.
	 * 
	 * @param nodeId
	 *            the full id of the object to update, including protocol and store.
	 * @param what
	 *            the new properties
	 * @return the noderef to the updated object
	 * @throws Exception
	 */
	public NodeRef update(String nodeId, Element what) throws Exception;

	/**
	 * Read an object from the repository.
	 * 
	 * @param objectId
	 *            the full node reference, including protocol and store
	 * @return an XML representation of the object (Class.xsd-compliant) serialized into a String
	 */
	public String read(String objectId);

	/**
	 * Create (or get if already created) the specified path. Non-existent folders are created.
	 * 
	 * @param where
	 *            the path to create, e.g. /app:company_home/app:dictionary/cm:NORTH/cm:Europe
	 * @return the noderef to the deepest folder
	 * @throws Exception
	 */
	public NodeRef createPath(String where) throws Exception;
}
