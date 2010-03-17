<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="com.bluexml.xforms.demo.Util" %>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>BlueXML Demo for XForms &amp; workflows</title>
	<link rel="stylesheet" type="text/css" href="styles/style.css"/>
</head>
	<frameset cols="50,50">
	  <frame src="showContent.jsp?taskId=<%=request.getParameter("taskId")%>&amp;workflowInstanceId=<%=request.getParameter("workflowInstanceId")%>" name="menu" marginwidth="0"/>
		<%		
		String formUrl = request.getParameter("formUrl");
		formUrl += "&amp;workflowInstanceId="+ request.getParameter("workflowInstanceId");
		formUrl += "&amp;id="+ request.getParameter("id");
		formUrl += "&amp;successPage="+ request.getParameter("successPage");
		formUrl += "&amp;failurePage="+ request.getParameter("failurePage");
		formUrl += "&amp;formType="+ request.getParameter("formType");
		%>
	  <frame src="<%=formUrl%>" name="body" marginwidth="0"/>
	</frameset>
</html>
