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
	<h1><span>Confirmation</span></h1>
	<%
	String message = request.getParameter("statusMsg");
	%>
	<p class="confirmationaccept"><%=message%></p>
	<br/>
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
	
	String password = (String) session.getAttribute("password");
	%>
	<table>
		<tr>
			<th style="width:30px;"/>
			<th>Name</th>
			<th/>
		</tr>	
		<%	
		for (Vector<String> v : Util.showAvailableContent(alfrescohost,user,password,request.getParameter("workflowInstanceId"))) {
		%>
		<tr>
			<td style="text-align:center"><img src="<%=v.elementAt(2)%>"/></td>
			<td style="text-align:center"><%=v.elementAt(0)%></td>
			<td style="text-align:center"><a href="<%=v.elementAt(1)%>">Download</a></td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>
