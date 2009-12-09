<%@page
	import="com.bluexml.side.framework.facetmap.alfrescoConnector.Updater"%>
<%@ page
	import="java.io.File,org.apache.tools.ant.Project,org.apache.tools.ant.ProjectHelper;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;"
	contentType="text/html; charset=ISO-8859-1"%>
<%
	String mapId = (String) request.getSession().getAttribute("current_map");

	Updater.update(request.getHeader("host"), mapId);
%>

<%
	response.sendRedirect("servletParameters.jsp");
%>