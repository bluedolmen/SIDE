package com.bluexml.xforms.controller.alfresco.agents;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.ServletException;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.security.AuthorityType;
import org.alfresco.service.cmr.security.PermissionService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
import com.bluexml.xforms.messages.MsgId;
import com.thoughtworks.xstream.XStream;

/**
 * The agent for calling methods of various "Services" in the Alfresco API.<br/>
 * The Javadoc with specifications and all details is in {@link AlfrescoController}.
 * 
 * @author Amenel
 * 
 */
public class SystemAgent {
	private AlfrescoController controller;
	private final XStream xstream;

	protected static Log logger = AlfrescoController.loggertrace;

	/**
	 * 
	 */
	public SystemAgent(AlfrescoController controller, XStream xstream) {
		this.controller = controller;
		this.xstream = xstream;
	}

	/**
	 * 
	 * @param asGroups
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Set<String> getAllAuthoritiesAsGroupsOrUsers(AlfrescoTransaction transaction,
			boolean asGroups) {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("serviceName", "AuthorityDAO");
		parameters.put("methodName", "getAllAuthorities");
		Vector<Object> paramList = new Vector<Object>();
		// add parameters to the method in paramList
		if (asGroups) {
			paramList.add(AuthorityType.GROUP);
		} else {
			paramList.add(AuthorityType.USER);
		}
		parameters.put("methodParams", xstream.toXML(paramList));
		Set<String> result;
		try {
			String resultStr = controller.requestString(transaction, parameters,
					MsgId.INT_WEBSCRIPT_OPCODE_SERVICE);
			result = (Set<String>) xstream.fromXML(resultStr);
		} catch (ServletException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * 
	 * @param userName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Set<String> getContainingGroups(AlfrescoTransaction transaction, String userName) {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("serviceName", "AuthorityDAO");
		parameters.put("methodName", "getContainingAuthorities");
		Vector<Object> paramList = new Vector<Object>();
		// add parameters to the method in paramList
		paramList.add(AuthorityType.GROUP);
		paramList.add(userName);
		paramList.add(false);
		parameters.put("methodParams", xstream.toXML(paramList));
		Set<String> result;
		try {
			String resultStr = controller.requestString(transaction, parameters,
					MsgId.INT_WEBSCRIPT_OPCODE_SERVICE);
			result = (Set<String>) xstream.fromXML(resultStr);
		} catch (ServletException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * 
	 * @param node
	 * @param propertyName
	 * @return
	 */
	public String getNodeProperty(AlfrescoTransaction transaction, NodeRef node, QName propertyName) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("serviceName", "NodeService");
		parameters.put("methodName", "getProperty");
		Vector<Object> paramList = new Vector<Object>();
		// add parameters to the method in paramList
		paramList.add(node);
		paramList.add(propertyName);
		parameters.put("methodParams", xstream.toXML(paramList));
		String result;
		try {
			result = (String) xstream.fromXML(controller.requestString(transaction, parameters,
					MsgId.INT_WEBSCRIPT_OPCODE_SERVICE));
		} catch (ServletException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * 
	 * @param groupName
	 * @return
	 */
	public NodeRef getNodeRefForGroup(AlfrescoTransaction transaction, String groupName) {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("serviceName", "AuthorityDAO");
		parameters.put("methodName", "getAuthorityNodeRefOrNull");
		Vector<Object> paramList = new Vector<Object>();
		// add parameters to the method in paramList
		String patchedGroupName = PermissionService.GROUP_PREFIX + groupName;
		if (groupName.startsWith(PermissionService.GROUP_PREFIX)) {
			patchedGroupName = groupName;
		}
		paramList.add(patchedGroupName);
		parameters.put("methodParams", xstream.toXML(paramList));
		NodeRef result;
		try {
			String resultStr = controller.requestString(transaction, parameters,
					MsgId.INT_WEBSCRIPT_OPCODE_SERVICE);
			result = (NodeRef) xstream.fromXML(resultStr);
		} catch (ServletException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * 
	 * @param userName
	 * @return
	 */
	public NodeRef getNodeRefForUser(AlfrescoTransaction transaction, String userName) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("serviceName", "AuthorityDAO");
		parameters.put("methodName", "getAuthorityNodeRefOrNull");
		Vector<Object> paramList = new Vector<Object>();
		// add parameters to the method in paramList
		paramList.add(userName);
		parameters.put("methodParams", xstream.toXML(paramList));
		NodeRef result;
		try {
			String resultStr = controller.requestString(transaction, parameters,
					MsgId.INT_WEBSCRIPT_OPCODE_SERVICE);
			result = (NodeRef) xstream.fromXML(resultStr);
		} catch (ServletException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * 
	 * @param dataId
	 *            a full node Id (with protocol and store)
	 * @return
	 */
	public QName getNodeType(AlfrescoTransaction transaction, String dataId) {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("serviceName", "NodeService");
		parameters.put("methodName", "getType");
		Vector<Object> paramList = new Vector<Object>();

		// add parameters to the method in paramList
		logger.debug(">>getNodeType: received request for dataId: " + dataId);
		String patchedDataId = controller.patchDataId(dataId);
		logger.debug(">>>>>>>>>>>>>> dataId: " + dataId + " patched to " + patchedDataId);
		NodeRef noderef = new NodeRef(patchedDataId);
		paramList.add(noderef);
		parameters.put("methodParams", xstream.toXML(paramList));

		QName result;
		try {
			String requestString = controller.requestString(transaction, parameters,
					MsgId.INT_WEBSCRIPT_OPCODE_SERVICE);
			result = (QName) xstream.fromXML(requestString);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
}
