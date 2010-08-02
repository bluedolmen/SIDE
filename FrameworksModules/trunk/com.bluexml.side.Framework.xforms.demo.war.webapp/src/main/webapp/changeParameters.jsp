<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" type="text/css" href="styles/style.css"/>
	<title>BlueXML Demo for XForms &amp; workflows</title>
</head>
<body>
	<%!
		public String getParamValue(HttpSession session, String param) {
			Object value = session.getAttribute(param);
			if (value != null)
				return value.toString();
			else
				return "";
		}
	%>
	<h1>Configure your application</h1>
	<div class="box">
		<form method="post" action="saveParameters.jsp"  class="nyroModal">
			Alfresco URL :<br/>
			<input type="text" name="alfrescohost" size="40" value="<%= getParamValue(session, "alfrescohost") %>"/><br/><br/>

			Chiba URL :<br/>
			<input type="text" name="xformshost" size="40" value="<%= getParamValue(session, "xformshost") %>"/><br/><br/>
			<br/>
			<input type="submit" value="Submit"/>
		</form>
	</div>
</body>
</html>