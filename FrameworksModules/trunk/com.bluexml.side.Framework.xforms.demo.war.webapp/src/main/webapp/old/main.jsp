<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="com.bluexml.xforms.demo.Util" %>
<%@page import="java.util.*" %>
<%@page import="org.apache.commons.httpclient.*" %>
<%@page import="org.apache.commons.httpclient.methods.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" type="text/css" href="styles/style.css"/>
	<title>BlueXML Demo for XForms &amp; workflows</title>
</head>
<body>
	<h1><span>Back office</span></h1>
<%
	String xformshost = (String) session.getAttribute("xformshost");
	if (xformshost == null || xformshost.length() == 0) {
		out.println("<p class=\"confirmationerror\">XForm webapp undefined.<br/> You must configure your application before.</p>");
		return;
	} 
	if (!xformshost.endsWith("/"))
		xformshost += "/";
	
	String alfrescohost = (String) session.getAttribute("alfrescohost");
	if (alfrescohost == null || alfrescohost.length() == 0) {
		out.println("<p class=\"confirmationerror\">Alfresco undefined.<br/> You must configure your application before.</p>");
		return;
	} 
	if (!alfrescohost.endsWith("/"))
		alfrescohost += "/";
		
	String user = (String) session.getAttribute("username");
	if (user == null || user.length() == 0) {
		out.println("<p class=\"confirmationerror\">You are not logged.</p>");
		return;
	}
	
	String deleteInstanceId = request.getParameter("deleteInstanceId");
	if (deleteInstanceId != null && deleteInstanceId.length() > 0) {
		PostMethod post = new PostMethod(alfrescohost+"service/xforms/workflow");
		post.setParameter("username", user);
		post.setParameter("method", "deleteWorkflow");
		post.setParameter("arg0", deleteInstanceId);
		HttpClient client = new HttpClient();
		client.executeMethod(post);
	}
%>
	<table>
		<tr>
			<th>Workflow id</th>
			<th>Last version</th>
			<th>Name</th>
			<th>Description</th>
		</tr>
<%	
	for (Vector<String> v : Util.getDefinitions(alfrescohost,user)) {
%>
		<tr>
			<td style="text-align:center"><%= v.elementAt(0)%></td>
			<td style="text-align:center"><%= v.elementAt(1)%></td>
			<td><a href="main.jsp?definition=<%= v.elementAt(4)%>" target="body"><%= v.elementAt(2)%></a></td>
			<td><%= v.elementAt(3)%></td>
		</tr>
<%
	}
%>
	</table>
	<br/>
<% 
	String definitionId = request.getParameter("definition");
	if (definitionId != null && definitionId.length() > 0) {
%>
	<table>
		<tr>
			<th>Instance id</th>
			<th>Workflow version</th>
			<th>Start date</th>
			<th>Launched by</th>
			<th/>
		</tr>
<%
		for (Vector<String> v : Util.getInstances(alfrescohost,user,definitionId)) {
%>
		<tr style="text-align:center">
			<td><%= v.elementAt(0)%></td>
			<td><%= v.elementAt(3)%></td>
			<td><%= v.elementAt(1)%></td>
			<td><%= v.elementAt(2)%></td>
			<td><a href="main.jsp?definition=<%=definitionId%>&deleteInstanceId=<%=v.elementAt(0)%>" target="body"><img src="images/x.gif"/></a></td>
		</tr>
<%			
		}
%>
	</table>
<%
	}
%> 
	
</body>
</html>
