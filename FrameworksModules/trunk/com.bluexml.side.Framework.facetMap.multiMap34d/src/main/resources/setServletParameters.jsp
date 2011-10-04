<%@
page
	import="
    java.io.IOException,
    java.util.Collection,
    java.util.Enumeration,
    java.util.Iterator,
    java.util.Vector,
    com.facetmap.Map,
    com.facetmap.InternalException,
    com.facetmap.servlet.FacetMapServlet"
	errorPage="error.jsp" contentType="text/html; charset=ISO-8859-1"%>
<%
FacetMapAlfrescoServlet fms = (FacetMapAlfrescoServlet) getServletContext().getAttribute("com.facetmap.servlet");
Collection exceptions = new Vector();
Enumeration names = request.getParameterNames();
while (names.hasMoreElements()) {
    try {
        String name = names.nextElement().toString();
        fms.setParameter(name, request.getParameter(name),request);
    } catch (Exception exc) {
        exceptions.add("Unexpected error: " + exc.toString());
    }
}
/*
try {
    fms.configure();
} catch (Exception exc) {
    exceptions.add("Unexpected error: " + exc.toString());
    exceptions.add("Your changes were not applied.");
    try {
        fms.readProps();
        fms.configure();
    } catch (Exception exc2) {
        exceptions.add("Error reverting to previous configuration: " + exc.toString());
        exceptions.add("FacetMap may be unstable.");
    }
}
*/

if (exceptions.size() == 0) {
    try {    	
        fms.writeProps("Modified by setServletParameters.jsp",request);
    } catch (IOException exc) {
        exceptions.add("Couldn't write changes to disk. File permissions may be incorrect.");
    }
}

if (exceptions.size() == 0) {
    response.sendRedirect("browseSimple_facets.jsp?"+request.getQueryString());
}
// else print page of exceptions
%>

<%@page import="com.bluexml.side.framework.facetmap.multimap.FacetMapAlfrescoServlet"%><html>
<head>
<title>Configuration Problems</title>
<link rel="stylesheet" type="text/css" href="facetmap_manual.css">
</head>

<body class="manual">
<p>FacetMap encountered these warnings/errors:
<p>
<table>
	<%
Iterator excIter = exceptions.iterator();
while (excIter.hasNext()) {
%>
	<tr>
		<td
			style="font-size: 80%; border-top: 1px solid black; padding: 0.5em"><%= excIter.next().toString() %></td>
	</tr>
	<%
}
%>
</table>

<form action="browseSimple_facets.jsp"><input type="submit"
	value="Continue"></form>
</body>
</html>

