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
	 * Uploads the content of a file to a node, possibly leaving the node name unchanged.
	 * 
	 * @param receiver
	 *            complete id (protocol, store and id) of the receiver node
	 * @param filename
	 *            the name under which the node is seen in the repository. If empty or null, the
	 *            node name property is not changed.
	 * @param filepath
	 *            complete filesystem path to the file that provides the content
	 * @param mimeType
	 *            the MIME type, usually provided by web clients
	 * @param contentType
	 *            the content type as seen by the class generator
	 * @param shouldAppendSuffix
	 *            if set to true, requests an index [e.g. '(1)'] to be appended to the filename if
	 *            the original filename is not available. <b>DEFAULTS to true</b>. Needs to be
	 *            explicitly set to false to prevent the renaming.
	 * @return true if the content of the has been successfully reassigned. False in case the
	 *         provider has no content.
	 * @throws Exception
	 */
	public boolean attachContent(String receiver, String filename, String filepath,
			String mimeType, String contentType, boolean shouldAppendSuffix) throws Exception;

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
	 * Create (or get if already created) the specified path. Non-existent folders are created.
	 * 
	 * @param where
	 *            the path to create, e.g. /app:company_home/app:dictionary/cm:NORTH/cm:Europe
	 * @return the noderef to the deepest folder
	 * @throws Exception
	 */
	public NodeRef createPath(String where) throws Exception;

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
	 * Read an object from the repository.
	 * 
	 * @param objectId
	 *            the full node reference, including protocol and store
	 * @return an XML representation of the object (Class.xsd-compliant) serialized into a String
	 */
	public String read(String objectId);

	/**
	 * Return the list of nodes that correspond to the query string.
	 * 
	 * @param xpath
	 *            the Xpath expression to query
	 * @return the list of nodes
	 */
	public List<NodeRef> request(String xpath) throws Exception;

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

}
