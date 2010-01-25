<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="com.bluexml.xforms.demo.Util" %>
<%@page import="java.util.*" %>
<%@page import="org.apache.commons.httpclient.*" %>
<%@page import="org.apache.commons.httpclient.methods.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" type="text/css" href="styles/style.css"/>
	<link rel="stylesheet" type="text/css" href="styles/nyroModal.full.css"/>
	<title>BlueXML Demo for XForms &amp; workflows</title>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
	<script src="js/jquery.nyroModal-1.5.5.js" type="text/javascript"></script>
</head>
<body class="body">
	<div style="position: absolute;">
	<p>
    	<a href="http://validator.w3.org/check?uri=referer"><img
        	src="http://www.w3.org/Icons/valid-xhtml11-blue"
	        alt="Valid XHTML 1.1" height="31" width="88" /></a>
  	</p>
  	</div>
	<%
		String xforms = (String) session.getAttribute("xformshost");
		if (xforms == null || xforms.length() == 0) {
			xforms = "";
		%><p class="confirmationerror">You must complete Chiba host.</p><%	
		} else {
			if (!xforms.endsWith("/"))
				xforms += "/";
		}			
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
	<table width="100%">
		<tr>
		<td style="50%">
	<div class="box">
		<div class="box">
			<h1><img src="images/configuration.png" alt="configuration"/>  Configuration</h1>
		</div>
		<br/>
		<table>
			<tr>
				<td class="field">Login :</td><td><%=session.getAttribute("username")%></td>
			</tr>
			<tr>
				<td class="field">Alfresco URL :</td><td><%=session.getAttribute("alfrescohost")%></td>
			</tr>
			<tr>
				<td class="field">Chiba URL :</td><td><%=xforms%></td>
			</tr>
		</table>
	</div>
	</td>
	<td>
	<div class="box">
		<div class="box">
			<h1><img src="images/actions.png" alt="action"/>  Actions</h1>
		</div>
		<br/>
		<table>
			<tr>
				<td><img src="images/logout.png" alt="logout"/></td><td class="field"><a href="logout.jsp">Logout</a></td>
			</tr>
			<tr>
				<td><img src="images/edit.gif" alt="change parameters"/></td><td class="field"><a href="changeParameters.jsp" class="nyroModal" id="changeParameters">Change parameters</a></td>
			</tr>
		</table>
	</div>

	</td>
	</tr>
	<tr>
		<td colspan="2">
		<div class="box">
			<div class="box"><h1><img src="images/start.png" alt="action"/>  Launch a new instance of workflow</h1></div>
	<br/>
	<table class="list-table" id="launchWorkflow">
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
			url = xforms+"xforms?type="+url+"&amp;formType=wkflw&amp;userName="+user+"&amp;successPage=demo/confirmation.jsp&amp;failurePage=demo/error.jsp";
%>
		<tr>
			<td style="text-align:center"><%= v.elementAt(0)%></td>
			<td style="text-align:center"><%= v.elementAt(1)%></td>
			<td><%= v.elementAt(2)%></td>
			<td><%= v.elementAt(3)%></td>
			<td style="text-align:center"><a href="<%=url%>" class="nyroModal" target="_blank"><img style="vertical-align:middle" src="images/check.gif" alt="launch a new instance"/>Launch a new instance</a></td>
		</tr>
<%
		}
	}
%>
	</table>
	</div>
	</td></tr>

<%
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

	<tr>
		<td colspan="2">
		<div class="box"><div class="box"><h1><img src="images/pooled_task.png" alt="action"/>  Pooled tasks</h1></div>
	<br/>
	<table class="list-table" id="launchTask">
		<tr>
			<th>Instance id</th>
			<th>Title</th>
			<th>Description</th>
			<th/>
		</tr>
<%	
	for (Vector<String> v : Util.getPooledTasks(Util.class.getResourceAsStream("/mapping.xml"),alfrescohost,user)) {
		String  url = xforms+"xforms?type="+v.elementAt(3)+"&amp;taskId="+v.elementAt(0)+"&amp;formType=wkflw&amp;userName="+user+"&amp;workflowInstanceId="+v.elementAt(5)+"&amp;id="+v.elementAt(4)+"&amp;successPage=demo/confirmation.jsp&amp;failurePage=demo/error.jsp";
%>
		<tr>
			<td style="text-align:center"><%= v.elementAt(0)%></td>
			<td style="text-align:center"><%= v.elementAt(1)%></td>
			<td style="text-align:center"><%= v.elementAt(2)%></td>
			<td style="text-align:center"><a href="showTask.jsp?formUrl=<%=url%>" class="nyroModal" target="_blank"><img style="vertical-align:middle" src="images/check.gif" alt=""/>Open this task</a></td>
		</tr>
<%
	}
%>
	</table>
	</div>
		</td>
	</tr>
	
	<tr>
	<td colspan="2">
		<div class="box">
			<div class="box"><h1><img src="images/back-office.png" alt="action"/>  Back office</h1></div>
			<br/>
	<table class="list-table">
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
			<td><%= v.elementAt(0)%></td>
			<td style="text-align:center"><%= v.elementAt(1)%></td>
			<td><a href="main.jsp?definition=<%= v.elementAt(4)%>"><%= v.elementAt(2)%></a></td>
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
	<h3 style="text-align:center">Workflow definition id : <%=definitionId%></h3>
	<table class="list-table">
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
		<tr>
			<td><%= v.elementAt(0)%></td>
			<td><%= v.elementAt(3)%></td>
			<td><%= v.elementAt(1)%></td>
			<td><%= v.elementAt(2)%></td>
			<td><a href="main.jsp?definition=<%=definitionId%>&deleteInstanceId=<%=v.elementAt(0)%>"><img src="images/x.gif"/></a></td>
		</tr>
<%			
		}
%>
	</table>
<%
	}
%> 
	</div>
	</td>
	</tr>
	</table>
	
	<script type="text/javascript">
		$(function() {
			$('#launchWorkflow a').nyroModal({minHeight: 400, minWidth: 600});
			$('#launchTask a').nyroModal({minHeight: 768, minWidth: 1024});
		})

	</script>
	
</body>
</html>
