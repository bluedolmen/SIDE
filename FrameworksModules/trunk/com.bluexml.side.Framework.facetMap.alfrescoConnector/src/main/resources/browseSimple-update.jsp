<%@page
	import="com.bluexml.side.framework.facetmap.multimap.FacetMapAlfrescoServlet"%><%@page
	import="com.bluexml.side.framework.facetmap.alfrescoConnector.Updater"%>
<%@ page
	import="java.io.File,org.apache.tools.ant.Project,org.apache.tools.ant.ProjectHelper;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;"
	contentType="text/html; charset=ISO-8859-1"%>
<jsp:include page="security.jsp" />
<%
	FacetMapAlfrescoServlet fms = (FacetMapAlfrescoServlet) getServletContext().getAttribute("com.facetmap.servlet");
	
	
	String current = Updater.update(request);
	System.out.println(current);

	fms.updateFacets();
%>

<%
	response.sendRedirect("browseSimple_facets.jsp?"+request.getQueryString());
%>