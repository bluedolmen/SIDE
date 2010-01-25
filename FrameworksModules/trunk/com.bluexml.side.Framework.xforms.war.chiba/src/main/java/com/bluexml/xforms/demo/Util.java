package com.bluexml.xforms.demo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Utility class for the XForms/Workflow integration demo webapp.
 * 
 * @author Amenel
 * 
 */
public class Util {

	/**
	 * Authenticates a user with an Alfresco instance.
	 * 
	 * @param host
	 *            the address (protocol, hostname, port number) of the host where the BlueXML XForms
	 *            webscript is deployed, with NO trailing slash. If NULL, defaults to localhost:8080
	 * @param userName
	 *            the user name to test, which should be known to Alfresco
	 * @param password
	 *            the plain text password to test
	 * @return true if the authentication succeeded, false if the authentication failed or if an
	 *         exception occurred
	 */
	public static boolean authenticate(String host, String userName, String password) {
		PostMethod post = new PostMethod(host + "service/xforms/auth");

		post.setParameter("username", userName);
		post.setParameter("password", password);

		HttpClient client = new HttpClient();
		try {
			client.executeMethod(post);
		} catch (HttpException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		String result;
		try {
			result = post.getResponseBodyAsString();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		if (result == null) {
			return false;
		}
		result = result.trim();
		return result.equals("success");
	}

	/**
	 * Calls the XForms webapp with initialization values.
	 * 
	 * @param alfrescohost
	 *            the address (protocol, host name, port number) to the Alfresco instance, with NO
	 *            trailing slash. e.g. http://www.bluexml.com/alfresco
	 * @param xformshost
	 *            the address (including context) of the xforms webapp host, with NO trailing slash.
	 *            e.g: http://localhost:8081/myforms
	 * @param formsproperties
	 *            the path to the forms.properties file
	 * @param redirectxml
	 *            the path to the redirect.xml file
	 * @return
	 */
	public static boolean initWebApp(String alfrescohost, String xformshost,
			String formsproperties, String redirectxml) {
		if (StringUtils.trimToNull(xformshost) == null) {
			return false;
		}
		String serviceURL = xformshost + "/xforms?init=true";

		serviceURL += "&alfrescoHost="+alfrescohost;
		serviceURL += "&redirectXmlFile="+ redirectxml;
		serviceURL += "&formsPropertiesFile="+ formsproperties;

		GetMethod get = new GetMethod(serviceURL);
		HttpClient client = new HttpClient();
		try {
			client.executeMethod(get);
		} catch (HttpException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		String result;
		try {
			result = get.getResponseBodyAsString();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		if (result == null) {
			return false;
		}
		result = result.trim();
		return result.equals("success");
	}
	
	public static String getStartTaskName(InputStream stream, String workflowName) {
		String result = "";
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(stream);
			
			Node root = document.getDocumentElement();
			for (int i = 0; i < root.getChildNodes().getLength(); ++i) {
				Node n = root.getChildNodes().item(i);
				if (n.getNodeName().equals("task")) {
					if (findNode(n.getChildNodes(), "startTask").getTextContent().equals("true")) {
						String taskId = findNode(n.getChildNodes(), "taskId").getTextContent();
						taskId = taskId.substring(0, taskId.indexOf(':'));
						
						String wfName = workflowName;
						wfName = wfName.substring(0,wfName.lastIndexOf(':'));
						wfName = wfName.substring(wfName.indexOf('$')+1);
						if (wfName.equals(taskId))
							return findNode(n.getChildNodes(), "name").getTextContent(); 
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static Set<Vector<String>> getDefinitions(String alfrescohost, String user) {
		Set<Vector<String>> result = new HashSet<Vector<String>>();
		try {
			PostMethod post = new PostMethod(alfrescohost+"service/xforms/workflow");
			post.setParameter("username", user);
			post.setParameter("method", "getDefinitions");
			HttpClient client = new HttpClient();
			client.executeMethod(post);
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(post.getResponseBodyAsStream());
			
			Node root = document.getDocumentElement();
			root = findNode(root.getChildNodes(),"list");
			
			for (int i = 0; i < root.getChildNodes().getLength(); i++) {
				Node n = root.getChildNodes().item(i);
				if (n.getNodeType() == Element.ELEMENT_NODE) {
					Vector<String> v = new Vector<String>();
					v.add(findNode(n.getChildNodes(), "id").getTextContent());
					v.add(findNode(n.getChildNodes(), "version").getTextContent());
					v.add(findNode(n.getChildNodes(), "title").getTextContent());
					v.add(findNode(n.getChildNodes(), "description").getTextContent());
					v.add(findNode(n.getChildNodes(), "name").getTextContent());
					result.add(v);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Set<Vector<String>> getInstances(String alfrescohost, String user, String definitionName) {
		Set<Vector<String>> result = new HashSet<Vector<String>>();
		try {
			List<String> ids = getIdentifiers(alfrescohost, user, definitionName);
			
			for (String id : ids)
				result.addAll(getInstancesById(alfrescohost, user, id));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private static Collection<? extends Vector<String>> getInstancesById(
			String alfrescohost, String user, String id) throws Exception {
		Set<Vector<String>> result = new HashSet<Vector<String>>();
		
		PostMethod post = new PostMethod(alfrescohost+"service/xforms/workflow");
		post.setParameter("username", user);
		post.setParameter("method", "getActiveWorkflows");
		post.setParameter("arg0", id);
		HttpClient client = new HttpClient();
		client.executeMethod(post);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(post.getResponseBodyAsStream());
		Node root = document.getDocumentElement();
		
		for (int i = 0; i < root.getChildNodes().getLength(); i++) {
			Node n = root.getChildNodes().item(i);
			if (n.getNodeType() == Element.ELEMENT_NODE) {
				Vector<String> v = new Vector<String>();
				v.add(findNode(n.getChildNodes(), "id").getTextContent());
				v.add(findNode(n.getChildNodes(), "startDate").getTextContent());
				
				String initiator = findNode(findNode(n.getChildNodes(), "initiator").getChildNodes(),"id").getTextContent();
				String protocol = findNode(findNode(findNode(n.getChildNodes(), "initiator").getChildNodes(),"storeRef").getChildNodes(),"protocol").getTextContent();
				String identifier = findNode(findNode(findNode(n.getChildNodes(), "initiator").getChildNodes(),"storeRef").getChildNodes(),"identifier").getTextContent();
				
				String username = getUserName(alfrescohost,user,protocol,identifier,initiator);
				v.add(username);
				v.add(findNode(findNode(n.getChildNodes(), "definition").getChildNodes(),"version").getTextContent());
				
				result.add(v);
			}
		}
		
		return result;
	}

	private static List<String> getIdentifiers(String alfrescohost,
			String user, String definitionName) throws Exception {
		List<String> result = new ArrayList<String>();
		
		PostMethod post = new PostMethod(alfrescohost+"service/xforms/workflow");
		post.setParameter("username", user);
		post.setParameter("method", "getAllDefinitionsByName");
		post.setParameter("arg0", definitionName);
		HttpClient client = new HttpClient();
		client.executeMethod(post);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(post.getResponseBodyAsStream());
		Node root = document.getDocumentElement();
		
		for (int i = 0; i < root.getChildNodes().getLength(); i++) {
			Node n = root.getChildNodes().item(i);
			if (n.getNodeType() == Element.ELEMENT_NODE)
				result.add(findNode(n.getChildNodes(), "id").getTextContent());
		}
		
		return result;
	}

	private static String getUserName(String alfrescohost, String user, String protocol, String identifier, String initiator) throws Exception {
		String firstname = "";
		String lastname = "";
		
		PostMethod post = new PostMethod(alfrescohost+"service/xforms/read");
		post.setParameter("username", user);
		post.setParameter("objectId", protocol+"://"+identifier+"/"+initiator);
		HttpClient client = new HttpClient();
		client.executeMethod(post);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(post.getResponseBodyAsStream());
		Node root = document.getDocumentElement();
		
		NodeList nodes = findNode(root.getChildNodes(), "attributes").getChildNodes();
		for (int i = 0; i < nodes.getLength(); ++i) {
			Node n = nodes.item(i); 
			if (n.getNodeName().equals("attribute")) {
				Node attr = n.getAttributes().getNamedItem("qualifiedName");
				if (attr != null)
					if (attr.getNodeValue().equals("firstName"))
						 	firstname = findNode(n.getChildNodes(), "value").getTextContent();
					else
						if (attr.getNodeValue().equals("lastName"))
							 	lastname = findNode(n.getChildNodes(), "value").getTextContent();
			}
		}
		return firstname+" "+lastname;
	}
	
	public static Set<Vector<String>> getPooledTasks(InputStream mapping, String alfrescohost, String user) {
		Set<Vector<String>> result = new HashSet<Vector<String>>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document mappingDocument = builder.parse(mapping);
			
			PostMethod post = new PostMethod(alfrescohost+"service/xforms/workflow");
			post.setParameter("username", user);
			post.setParameter("method", "getPooledTasks");
			post.setParameter("arg0", user);
			HttpClient client = new HttpClient();
			client.executeMethod(post);

			Document document = builder.parse(new ByteArrayInputStream(("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"+post.getResponseBodyAsString()).getBytes()));
			
			Node root = document.getDocumentElement();
			root = findNode(root.getChildNodes(),"list");
			
			for (int i = 0; i < root.getChildNodes().getLength(); i++) {
				Node n = root.getChildNodes().item(i);
				if (n.getNodeType() == Element.ELEMENT_NODE) {
					Vector<String> v = new Vector<String>();
					String instanceId = findNode(findNode(findNode(n.getChildNodes(), "path").getChildNodes(),"instance").getChildNodes(),"id").getTextContent(); 
					String taskId = findNode(n.getChildNodes(),"id").getTextContent(); 
					v.add(taskId);
					v.add(findNode(n.getChildNodes(), "title").getTextContent());
					v.add(findNode(n.getChildNodes(), "description").getTextContent());
					String name = findNode(n.getChildNodes(), "name").getTextContent();
					v.add(getFormName(mappingDocument, name));
					v.add(getContentId(alfrescohost, user, taskId));
					v.add(instanceId);
					
					result.add(v);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static String getContentId(String alfrescohost, String userName, String taskId) throws Exception {
		String result = "";
		PostMethod post = new PostMethod(alfrescohost+"service/xforms/workflow");
		post.setParameter("username", userName);
		post.setParameter("method", "getPackageContents");
		post.setParameter("arg0", taskId);
		HttpClient client = new HttpClient();
		client.executeMethod(post);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(post.getResponseBodyAsStream());
		
		Node searchedNode = null;
		Node root = document.getDocumentElement();
		for (int i = 0; i < root.getChildNodes().getLength(); i++) {
			Node n = root.getChildNodes().item(i);
			if (n.getNodeType() == Element.ELEMENT_NODE && searchedNode == null) {
				searchedNode = n;
			}
		}
		if (searchedNode != null)
			result = findNode(searchedNode.getChildNodes(),"id").getTextContent();
		
		return result;
	}

	private static String getFormName(Document document, String name) {
		String result = "";
		
		try {
			Node root = document.getDocumentElement();
			for (int i = 0; i < root.getChildNodes().getLength(); ++i) {
				Node n = root.getChildNodes().item(i);
				if (n.getNodeName().equals("task")) {
					if (findNode(n.getChildNodes(), "taskId").getTextContent().equals(name)) {
						return findNode(n.getChildNodes(), "name").getTextContent(); 
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	private static Node findNode(NodeList nodes, String nodeName) {
		Node result = null;
		for (int i = 0; i < nodes.getLength(); ++i)
			if (nodes.item(i).getNodeName().equals(nodeName))
				result = nodes.item(i);
		return result;
	}
	
	private static List<Node> findNodes(NodeList nodes, String nodeName) {
		List<Node> result = new ArrayList<Node>();
		for (int i = 0; i < nodes.getLength(); ++i)
			if (nodes.item(i).getNodeName().equals(nodeName))
				result.add(nodes.item(i));
		return result;
	}
	
	public static Collection<? extends Vector<String>> showAvailableContent(
			String alfrescohost, String user, String password, String taskId) throws Exception {
		Set<Vector<String>> result = new HashSet<Vector<String>>();
		
		PostMethod post = new PostMethod(alfrescohost+"service/xforms/workflow");
		post.setParameter("username", user);
		post.setParameter("method", "getPackageContents");
		post.setParameter("arg0", taskId);
		HttpClient client = new HttpClient();
		client.executeMethod(post);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(post.getResponseBodyAsStream());
		Node root = document.getDocumentElement();
		
		for (int i = 0; i < root.getChildNodes().getLength(); i++) {
			Node n = root.getChildNodes().item(i);
			if (n.getNodeType() == Element.ELEMENT_NODE) {
				String protocol = findNode(findNode(n.getChildNodes(), "storeRef").getChildNodes(),"protocol").getTextContent();
				String workspace = findNode(findNode(n.getChildNodes(), "storeRef").getChildNodes(),"identifier").getTextContent();
				String id = findNode(n.getChildNodes(), "id").getTextContent();
				
				String url = alfrescohost+"service/api/node/"+protocol+"/"+workspace+"/"+id;
				GetMethod get = new GetMethod(url);
				client = new HttpClient();
				UsernamePasswordCredentials upc = new UsernamePasswordCredentials(user, password);
				client.getState().setCredentials(AuthScope.ANY, upc);
				get.setDoAuthentication(true); 
				client.executeMethod(get);
				document = builder.parse(get.getResponseBodyAsStream());
				Node rootContent = document.getDocumentElement();

				Vector<String> v = new Vector<String>();
				String downloadUrl = findNode(rootContent.getChildNodes(), "content").getAttributes().getNamedItem("src").getNodeValue();
				String title = findNode(rootContent.getChildNodes(), "title").getTextContent();
				String icon = findNode(rootContent.getChildNodes(), "alf:icon").getTextContent();
				v.add(title);
				v.add(downloadUrl);
				v.add(icon);
				result.add(v);
			}
		}
		
		return result;
	}
	
	public static Collection<? extends Vector<String>> showAvailableContentWithWorklowId(
			String alfrescohost, String user, String password, String workflowId) throws Exception {
		Set<Vector<String>> result = new HashSet<Vector<String>>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		PostMethod post = new PostMethod(alfrescohost+"service/xforms/workflow");
		post.setParameter("username", user);
		post.setParameter("method", "getWorkflowPaths");
		post.setParameter("arg0", workflowId);
		HttpClient client = new HttpClient();
		client.executeMethod(post);
		
		Document document = builder.parse(new ByteArrayInputStream(("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"+post.getResponseBodyAsString()).getBytes()));
		Node root = document.getDocumentElement();
		//in the case of the workflow is ended
		if (findNodes(root.getChildNodes(), "org.alfresco.service.cmr.workflow.WorkflowPath").size() > 0) {
			String pathId = findNode(findNode(root.getChildNodes(), "org.alfresco.service.cmr.workflow.WorkflowPath").getChildNodes(),"id").getTextContent();
			
			post = new PostMethod(alfrescohost+"service/xforms/workflow");
			post.setParameter("username", user);
			post.setParameter("method", "getTasksForWorkflowPath");
			post.setParameter("arg0", pathId);
			client = new HttpClient();
			client.executeMethod(post);
			
			document = builder.parse(new ByteArrayInputStream(("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"+post.getResponseBodyAsString()).getBytes()));
			root = document.getDocumentElement();
			String taskId = findNode(findNode(root.getChildNodes(), "org.alfresco.service.cmr.workflow.WorkflowTask").getChildNodes(),"id").getTextContent();
			
			post = new PostMethod(alfrescohost+"service/xforms/workflow");
			post.setParameter("username", user);
			post.setParameter("method", "getPackageContents");
			post.setParameter("arg0", taskId);
			client = new HttpClient();
			client.executeMethod(post);
	
			document = builder.parse(new ByteArrayInputStream(("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"+post.getResponseBodyAsString()).getBytes()));
			root = document.getDocumentElement();
			
			for (int i = 0; i < root.getChildNodes().getLength(); i++) {
				Node n = root.getChildNodes().item(i);
				if (n.getNodeType() == Element.ELEMENT_NODE) {
					String protocol = findNode(findNode(n.getChildNodes(), "storeRef").getChildNodes(),"protocol").getTextContent();
					String workspace = findNode(findNode(n.getChildNodes(), "storeRef").getChildNodes(),"identifier").getTextContent();
					String id = findNode(n.getChildNodes(), "id").getTextContent();
					
					String url = alfrescohost+"service/api/node/"+protocol+"/"+workspace+"/"+id;
					GetMethod get = new GetMethod(url);
					client = new HttpClient();
					UsernamePasswordCredentials upc = new UsernamePasswordCredentials(user, password);
					client.getState().setCredentials(AuthScope.ANY, upc);
					get.setDoAuthentication(true); 
					client.executeMethod(get);
					document = builder.parse(get.getResponseBodyAsStream());
					Node rootContent = document.getDocumentElement();
	
					Vector<String> v = new Vector<String>();
					String downloadUrl = findNode(rootContent.getChildNodes(), "content").getAttributes().getNamedItem("src").getNodeValue();
					String title = findNode(rootContent.getChildNodes(), "title").getTextContent();
					String icon = findNode(rootContent.getChildNodes(), "alf:icon").getTextContent();
					v.add(title);
					v.add(downloadUrl);
					v.add(icon);
					result.add(v);
				}
			}
		}
		return result;
	}
	
}
