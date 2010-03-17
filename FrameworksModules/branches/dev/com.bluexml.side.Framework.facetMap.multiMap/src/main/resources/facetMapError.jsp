<%@ page import="com.bluexml.side.framework.facetmap.multimap.FacetMapNotAvailableException" %>
<%@ page isErrorPage="true" %>


<%@page import="com.facetmap.DataException"%><html>
<head><title>Data Error</title></head>
<body>
<%
exception = FacetMapNotAvailableException.getInitialCause(exception);
%>



<% if (exception instanceof FacetMapNotAvailableException || exception instanceof DataException) { %>
<table style="background-color: #9999ff; font-family: Arial, Helvetica, sans-serif;" cellspacing=0 cellpadding=5 border=0 width=100%>
<tr><td align="left">
<span>Error while getting FacetMap</span>
</td></tr>
</table>
<p>
<b>There was an error in the data used to create or access this FacetMap:</b>
<p>
<code><%= exception.getMessage() %></code>
<font size=-1><pre>
<% exception.printStackTrace(new java.io.PrintWriter(out)); %>
</pre></font>
<p>
<hr>
<p>
Please be sure that the URL and query string are intact, and try again. If the problem continues, please report it to the website administrator.

<%   } else { %>
<table style="background-color: #9999ff; font-family: Arial, Helvetica, sans-serif;" cellspacing=0 cellpadding=5 border=0 width=100%>
<tr><td align="left">
<span>Unexpected Error</span>
</td></tr>
</table>
<p>
<b>There was an unexpected error:</b>
<p>
<code><%= exception.toString() %></code>
<p>
<hr>
<p>
If this problem continues, please report it to the website administrator.
<p>
<font size=-1><pre>
<% exception.printStackTrace(new java.io.PrintWriter(out)); %>
</pre></font>

<% } // end switch on exception class %>

</body></html>
