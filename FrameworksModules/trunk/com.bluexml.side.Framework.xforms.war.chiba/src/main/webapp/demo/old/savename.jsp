<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="com.bluexml.xforms.demo.Util" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" type="text/css" href="styles/style.css"/>
	<title>BlueXML Demo for XForms &amp; workflows</title>
</head>
<body>
	<h1><span>Login</span></h1>
	<%
	String name = request.getParameter("username");
	String password = request.getParameter("password");
	String host = (String) session.getAttribute("alfrescohost");
	if (!host.endsWith("/"))
		host += "/";
	
	if (name == null || password == null) {
	%>
		<p class="confirmationerror">All fiels are not filled.</p>
		<jsp:include page="identification_subpart.jsp" />
	<%
	} else {
		// we don't check 'host' cause function authenticate has a default for it.
		if (Util.authenticate(host, name, password)) {
			session.setAttribute("username", name);
			session.setAttribute("password", password);
	%>
		<script type="text/javascript">
			parent.frames[0].location.reload();
		</script>
		<p class="confirmationaccept">Login successfully.</p>
	<%
		} else {
	%>
		<p class="confirmationerror">Error during the authentication.</p>
		<p>
			<jsp:include page="identification_subpart.jsp" />
		</p>
	<%
		}
	}
	%>
</body>
</html>
