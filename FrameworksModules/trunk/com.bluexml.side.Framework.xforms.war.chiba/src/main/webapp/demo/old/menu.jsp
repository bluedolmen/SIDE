<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>BlueXML Demo for XForms &amp; workflows</title>
	<link rel="stylesheet" type="text/css" href="styles/style.css"/>
	<link rel="stylesheet" type="text/css"   href="styles/menu_style.css"/>
</head>
<body class="menuBar">
	<h1><span>SIDE Demo WebApp</span></h1>
	<div id="menu">
		<ul>
			<li><a href="initialisation.jsp" class="link" target="body">Configure</a></li>
			<li><a href="main.jsp" class="link" target="body">Back office</a></li>
		<%
			String user = (String) session.getAttribute("username");
			if (user != null) {
		%>
			<li><a href="deconnexion.jsp" class="link" target="body">Logout</a></li>
			<li><a href="selection.jsp" class="link" target="body">Launch a workflow</a></li>
		<%
			} else {
		 %>
			<li><a href="identification.jsp" class="link" target="body">Login</a></li>
		 <%
			}
		 %>
		</ul>
	</div>
	<br/><br/>
	<%
		if (user != null) {
	%>
		<div id="menu">
	<%	out.println("<br/><span class=\"username\">User logged: <br/><br/>" + user + "</span>");%>
		</div>
	<%
		}
	%>
</body>
</html>