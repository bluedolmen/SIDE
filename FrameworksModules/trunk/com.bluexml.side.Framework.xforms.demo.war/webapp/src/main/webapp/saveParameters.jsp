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
	<%
	String alfrescohost = request.getParameter("alfrescohost");
	String xformshost = request.getParameter("xformshost");
	String formsproperties = request.getParameter("formsproperties");
	String redirectxml = request.getParameter("redirectxml");

	if (alfrescohost != null) {
		if (!alfrescohost.endsWith("/"))
			alfrescohost += "/";
		session.setAttribute("alfrescohost", alfrescohost);
		out.println("<p class=\"confirmationaccept\">Alfresco host saved.</p>");
	}
	if (xformshost != null) {
		if (!xformshost.endsWith("/"))
			xformshost += "/";
		session.setAttribute("xformshost", xformshost);
		out.println("<p class=\"confirmationaccept\">XForms webapp host saved.</p>");
	}
	
	if (Util.initWebApp(alfrescohost, xformshost, formsproperties, redirectxml)) {
		out.println("<p class=\"confirmationaccept\">All configuration elements saved.</p>");
	} else {
		out.println("<p class=\"confirmationerror\">Webapp initialisation failed.</p>");
	}
%>
	<script type="text/javascript">
		parent.location.reload();
	</script>
</body>
</html>
