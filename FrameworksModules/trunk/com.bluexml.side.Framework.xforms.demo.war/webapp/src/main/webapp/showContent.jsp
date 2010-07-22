<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="com.bluexml.xforms.demo.Util" %>
<%@page import="java.util.*" %>
<%@page import="org.apache.commons.httpclient.*" %>
<%@page import="org.apache.commons.httpclient.methods.*" %>
<%@page import="javax.xml.parsers.*" %>
<%@page import="org.w3c.dom.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>BlueXML Demo for XForms &amp; workflows</title>
	<link rel="stylesheet" type="text/css" href="styles/style.css"/>
	<link rel="stylesheet" type="text/css" href="styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
	<script src="js/jquery-1.3.2.min.js" type="text/javascript"></script>
	<script src="js/jquery-ui-1.7.2.custom.min.js" type="text/javascript"></script>
</head>
<body>
<script type="text/javascript">
	$(function() {
		$("#accordion").accordion();
	});
</script>
<div id="accordion">
	  <%
	  	String password = (String) session.getAttribute("password");
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

		for (Vector<String> v : Util.showAvailableContent(alfrescohost,user,password,request.getParameter("taskId"))) {
			String uuid = v.elementAt(1);
			uuid = uuid.substring(0,uuid.lastIndexOf("/"));
			uuid = uuid.substring(uuid.lastIndexOf("/")+1);
			if (v.elementAt(0).endsWith(".pdf")) {
				%>
				<h3><a href="#"><%=v.elementAt(0)%></a></h3>
				<div><p>
					<embed width="100%" height="460" flashvars="paging=true&amp;url=http://localhost:8080/alfresco/service/api/node/workspace/SpacesStore/<%=uuid%>/content/thumbnails/imgpreview?c=force" wmode="transparent" allowfullscreen="true" allowscriptaccess="sameDomain" quality="high" name="WebPreviewer" id="WebPreviewer" style="" src="components/WebPreviewer.swf" type="application/x-shockwave-flash"/>
					<a href="<%=v.elementAt(1)%>">Download</a>
				</p></div>
				<%
			} else {
				PostMethod post = new PostMethod(alfrescohost+"service/xforms/read");
				post.setParameter("objectId", "workspace://SpacesStore/"+uuid);
				HttpClient client = new HttpClient();
				client.executeMethod(post);
				%>
				<h3><a href="#"><%=v.elementAt(0)%></a></h3>
				<div><p>
						<%
							DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
							DocumentBuilder builder = factory.newDocumentBuilder();
							Document document = builder.parse(post.getResponseBodyAsStream());
							Node root = document.getDocumentElement();
							root = root.getFirstChild();
							for (int i = 0; i < root.getChildNodes().getLength(); ++i) {
								Node n = root.getChildNodes().item(i);
								if (n.getNodeType() == Element.ELEMENT_NODE) {
									%>
										<b><%=n.getAttributes().getNamedItem("qualifiedName").getNodeValue()%> :</b><br/>
										<%
											Node value = n.getFirstChild().getFirstChild();
											if (value != null)
												out.println(value.getNodeValue() + "<br/>");
											else
												out.println("<br/>");
								}
							}
						%>
				</p></div>
				<%
			}
		}
		%>
</div>
</body>
</html>
