<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" type="text/css" href="styles/style.css"/>
	<title>BlueXML Demo for XForms &amp; workflows</title>
</head>
<body>
<%
	session.removeAttribute("username");
%>
	<h1><span>Logout</span></h1>
	<p class="confirmationaccept">
		Logout successfully !
	</p>
	<%
		response.sendRedirect("index.jsp");
	%>
</body>
</html>