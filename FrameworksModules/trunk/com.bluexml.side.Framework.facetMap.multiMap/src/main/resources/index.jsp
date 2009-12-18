<%@ page contentType="text/html; charset=ISO-8859-1" %>
<html>
<head>
<title>FacetMaps</title>

</head>
<body>
<iframe src="/facetmap/browseSimple_facets.jsp?<%=request.getQueryString()%>" id="facetsContainer"
	style="width: 600px; height: 600px"></iframe>
<iframe src="/facetmap/browseSimple_content.jsp?<%=request.getQueryString()%>" id="resultsContainer"
	style="width: 600px; height: 600px"></iframe>
</body>
</html>