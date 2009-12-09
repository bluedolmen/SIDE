<%@ page import="com.facetmap.DataException, com.facetmap.InternalException" %>
<%@ page isErrorPage="true" %>

<html>
<head><title>Data Error</title></head>
<body>

<% if (exception instanceof DataException) { %>
<table style="background-color: #9999ff; font-family: Arial, Helvetica, sans-serif;" cellspacing=0 cellpadding=5 border=0 width=100%>
<tr><td align="left">
<span>Data Error</span>
</td></tr>
</table>
<p>
<b>There was an error in the data used to create or access this FacetMap:</b>
<p>
<code><%= exception.getMessage() %></code>
<p>
<hr>
<p>
Please be sure that the URL and query string are intact, and try again. If the problem continues, please report it to the website administrator.

<% } else if (exception instanceof InternalException) { %>
<table style="background-color: #9999ff; font-family: Arial, Helvetica, sans-serif;" cellspacing=0 cellpadding=5 border=0 width=100%>
<tr><td align="left">
<span>Internal Error</span>
</td></tr>
</table>
<p>
<b>There was an error in the FacetMap system or a system that FacetMap needs:</b>
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
