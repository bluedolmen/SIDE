<html>
<body>
<%
	String uuid = request.getParameter("uuid");
%>
<embed width="100%" height="670" flashvars="paging=true&amp;url=http://localhost:8080/alfresco/service/api/node/workspace/SpacesStore/<%=uuid%>/content/thumbnails/imgpreview?c=force&amp;noCacheToken=1258125963289" wmode="transparent" allowfullscreen="true" allowscriptaccess="sameDomain" quality="high" name="WebPreviewer" id="WebPreviewer" style="" src="components/WebPreviewer.swf" type="application/x-shockwave-flash"/>
</body>
</html>