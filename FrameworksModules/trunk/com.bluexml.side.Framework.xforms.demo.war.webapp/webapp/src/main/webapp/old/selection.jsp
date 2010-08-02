<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="com.bluexml.xforms.demo.Util" %>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" type="text/css" href="styles/style.css"/>
	<title>BlueXML Demo for XForms &amp; workflows</title>
</head>
<body>
	<h1><span>Launch a new instance of workflow</span></h1>
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
	
%>
	<br/>
	<table>
		<tr>
			<th>Workflow id</th>
			<th>Last version</th>
			<th>Name</th>
			<th>Description</th>
			<th/>
		</tr>
<%	
	for (Vector<String> v : Util.getDefinitions(alfrescohost,user)) {
		if (v.elementAt(4).startsWith("jbpm$wfbx")) {
			String url = Util.getStartTaskName(Util.class.getResourceAsStream("/mapping.xml"), v.elementAt(4));
			url = xformshost+"xforms?type="+url+"&formType=wkflw&userName="+user+"&successPage=demo/confirmation.jsp&failurePage=demo/error.jsp";
%>
		<tr>
			<td style="text-align:center"><%= v.elementAt(0)%></td>
			<td style="text-align:center"><%= v.elementAt(1)%></td>
			<td><a href="main.jsp?definition=<%= v.elementAt(4)%>" target="body"><%= v.elementAt(2)%></a></td>
			<td><%= v.elementAt(3)%></td>
			<td style="text-align:center"><a href="<%=url%>"><img style="vertical-align:middle" src="images/check.gif"/>Launch a new instance</a></td>
		</tr>
<%
		}
	}
%>
	</table>
	<br/><br/>
	<h1><span>Pooled tasks</span></h1>
	<br/>
	<table>
		<tr>
			<th>Instance id</th>
			<th>Title</th>
			<th>Description</th>
			<th/>
		</tr>
<%	
	for (Vector<String> v : Util.getPooledTasks(Util.class.getResourceAsStream("/mapping.xml"),alfrescohost,user)) {
		String  url = xformshost+"xforms?type="+v.elementAt(3)+"&formType=wkflw&userName="+user+"&workflowInstanceId="+v.elementAt(5)+"&id="+v.elementAt(4)+"&successPage=demo/confirmation.jsp&failurePage=demo/error.jsp";
%>
		<tr>
			<td style="text-align:center"><%= v.elementAt(0)%></td>
			<td style="text-align:center"><%= v.elementAt(1)%></td>
			<td style="text-align:center"><%= v.elementAt(2)%></td>
			<td style="text-align:center"><a href="<%=url%>"><img style="vertical-align:middle" src="images/check.gif"/>Open this task</a></td>
		</tr>
<%
	}
%>
	</table>
</body>
</html>
