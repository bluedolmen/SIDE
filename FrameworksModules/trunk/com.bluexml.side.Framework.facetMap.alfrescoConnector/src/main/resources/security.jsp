
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Properties"%>
<%@page
	import="com.bluexml.side.framework.facetmap.alfrescoConnector.Helper"%><%@ page
	import="com.facetmap.Selection,com.facetmap.DataException,com.facetmap.Resource,com.facetmap.servlet.FacetMapServlet,com.facetmap.servlet.ServletUtil,com.facetmap.simple.SimpleFacetmap,javax.xml.transform.Transformer,javax.xml.transform.TransformerException,javax.xml.transform.dom.DOMSource,javax.xml.transform.stream.StreamSource,com.bluexml.side.framework.facetmap.alfrescoConnector.*,com.bluexml.side.framework.facetmap.multimap.*"
	contentType="text/html; charset=ISO-8859-1"%>
<%
	response.setHeader("Cache-control", "max-age=0, no-cache");
	if (request.getSession().getAttribute("alf_ticket") == null) {
		// put in session all Alfresco parameters
		String userName = request.getParameter("userName");
		String userTicket = request.getParameter("userTicket");
		String userpwd = request.getParameter("userpwd");
		request.getSession().setAttribute("userName", userName);
		request.getSession().setAttribute("userTicket", userTicket);
		request.getSession().setAttribute("userpwd", userpwd);
	}

	String userName = (String) request.getSession().getAttribute("userName");
	String userTicket = (String) request.getSession().getAttribute("userTicket");
	String userpwd = (String) request.getSession().getAttribute("userpwd");

	String facetName = null;
	if (request.getParameter("facetName") != null) {
		facetName = request.getParameter("facetName");
		// request.getSession().setAttribute("facetName", facetName);
	} else {
		// facetName = (String) request.getSession().getAttribute("facetName");
		if (facetName == null) {
	throw new FacetMapNotAvailableException("Missing parameter : please to fill facetName");
		}
	}
	String community = null;
	if (request.getParameter("community") != null) {
		community = request.getParameter("community");
		// request.getSession().setAttribute("community", community);
	} else {
		// community = (String) request.getSession().getAttribute("community");
		if (community == null) {
	throw new FacetMapNotAvailableException("Missing parameter : please to fill community");
		}
	}

	Helper h = new Helper();
	Properties props = h.getProperties(facetName);

	AlfrescoCommunicator alfCom = new AlfrescoCommunicator(props);
	Map<String, Object> auth = null;
	if (userpwd != null) {
		auth = alfCom.checkUserAccessForSite(userName, userpwd, community);

	} else {
		auth = alfCom.checkUserAccessForSite(userName, userTicket, community);
	}
	if (auth == null) {
		throw new FacetMapNotAvailableException("Access denied please to logon");
	}
	userTicket = (String) auth.get(AlfrescoCommunicator.TICKET_KEY);

	List<String> auths = (List<String>) auth.get(AlfrescoCommunicator.AUTHORITIES_KEY);
	
	request.getSession().setAttribute("alf_ticket", userTicket);
	request.getSession().setAttribute("userRight", auths);	
	boolean isAdmin = new Helper().isCommunityAdmin(request);
	request.getSession().setAttribute("isAdmin", isAdmin);
%>
