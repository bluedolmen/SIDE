
<%@page import="java.util.Map"%>
<%@page
	import="com.bluexml.side.framework.facetmap.alfrescoConnector.Helper"%>
<%@page
	import="com.bluexml.side.framework.facetmap.multimap.FacetMapNotAvailableException"%>
<%@page
	import="com.bluexml.side.framework.facetmap.alfrescoConnector.AlfrescoCommunicator"%><%@page
	import="com.bluexml.side.framework.facetmap.multimap.FacetMapAlfrescoServlet"%><%@page
	import="com.bluexml.side.framework.facetmap.alfrescoConnector.Updater"%>
<%@ page
	import="java.io.File,org.apache.tools.ant.Project,org.apache.tools.ant.ProjectHelper,java.util.Properties,java.io.FileInputStream,java.io.FileOutputStream"
	contentType="text/html; charset=ISO-8859-1"%>

<%@page import="java.util.List"%>
<%
	FacetMapAlfrescoServlet fms = (FacetMapAlfrescoServlet) getServletContext().getAttribute("com.facetmap.servlet");
	String userName = request.getParameter("userName");
	String userpwd = request.getParameter("userpwd");
	Helper h = new Helper();
	Properties props = h.getProperties(null);

	AlfrescoCommunicator alfCom = new AlfrescoCommunicator(props);
	Map<String, Object> auth = null;

	auth = alfCom.checkUserAccess(userName, userpwd);

	if (auth == null) {
		throw new FacetMapNotAvailableException("Access denied please to logon");
	}
	String userTicket = (String) auth.get(AlfrescoCommunicator.TICKET_KEY);

	List<String> auths = (List<String>) auth.get(AlfrescoCommunicator.AUTHORITIES_KEY);
	boolean isAdmin=h.isAdmin(auths);
	if (!isAdmin) {
		throw new FacetMapNotAvailableException("Access denied (you must login as admin)");
	}
	List<String> sites = Updater.updateAll(fms, request);
%>

<%--
	response.sendRedirect("browseSimple_facets.jsp?" + request.getQueryString());
--%>
<html>
<head>
<title>Facet Map initialized</title>
</head>
<body>
<p>all facet have been generated</p>
</body>
</html>