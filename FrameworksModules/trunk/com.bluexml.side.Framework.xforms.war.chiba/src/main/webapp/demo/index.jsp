<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="com.bluexml.xforms.demo.Util" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>BlueXML Demo for XForms &amp; workflows</title>
	<link rel="stylesheet" type="text/css" href="styles/style.css" />
</head>
<body class="body">
	<p>
    	<a href="http://validator.w3.org/check?uri=referer"><img
        	src="http://www.w3.org/Icons/valid-xhtml11-blue"
	        alt="Valid XHTML 1.1" height="31" width="88" /></a>
  	</p>

	<div class="centered_div">

<%
	String name = request.getParameter("username");
	String password = request.getParameter("password");
	String host = request.getParameter("alfrescohost");
	if ((name != null) || (password != null)) {
		if ((host == null) || (name == null) || (password == null) || (host.length() == 0) || (name.length() == 0) || (password.length() == 0)) {
		%>
			<p class="confirmationerror">All fields must be filled.</p>
		<%	
		} else {
			if (!host.endsWith("/"))
				host += "/";
			// we don't check 'host' cause function authenticate has a default for it.
			if (Util.authenticate(host, name, password)) {
				session.setAttribute("username", name);
				session.setAttribute("password", password);
				session.setAttribute("alfrescohost", host);
		%>
			<p class="confirmationaccept">Login successfully.</p>
		<%
				response.sendRedirect("main.jsp");
			} else {
		%>
			<p class="confirmationerror">Error during authentication.</p>
		<%
			}
		}
	}
	
	if (name == null)
		name = "";
	if (password == null)
		password= "";
	if (host == null || host.length() == 0)
	    host = "http://localhost:8080/alfresco/";
	%>
	    	<h1>Welcome</h1>
	    	<form method="post" action="index.jsp">
	    	<table width="100%">
	    		<tr>
	    			<td class="form_field">Username :</td><td class="form_input"><input type="text" name="username" value="<%=name%>"/></td>
	    		</tr>
	    		<tr>
	    			<td class="form_field">Password :</td><td class="form_input"><input type="text" name="password"/></td>
	    		</tr>
	    		<tr>
	    			<td class="form_field">Alfresco URL :</td><td class="form_input">
	    				<input type="text" value="<%=host%>" name="alfrescohost" size="40"/></td>
	    		</tr>
	    	</table>
	    	<div>
	    	<br/><br/><br/>
	    	<input type="submit" value="Login"/>
	    	</div>
	    	</form>
	</div>
</body>
</html>
