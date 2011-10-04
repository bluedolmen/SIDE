
<%@page
	import="com.bluexml.side.framework.facetmap.multimap.FacetMapAlfrescoServlet"%>
<%@page
	import="com.bluexml.side.framework.facetmap.multimap.FacetMapInstance,com.bluexml.side.framework.facetmap.alfrescoConnector.Helper"%>
<%@
page
	import="java.util.Enumeration,java.util.Iterator,com.facetmap.Map,com.facetmap.InternalException,com.facetmap.servlet.FacetMapServlet,com.facetmap.simple.TransformerPersister"
	contentType="text/html; charset=ISO-8859-1" errorPage="error.jsp"%>
<%
	FacetMapAlfrescoServlet fms = (FacetMapAlfrescoServlet) getServletContext()
			.getAttribute("com.facetmap.servlet");

	
%>


<%@page import="java.util.Set"%><html>
<head>
<link rel="stylesheet" type="text/css" href="servletParameters.css">
<script type="text/javascript"
	src="xsl/display/browseSimpleForFrames.js">
</script>
</head>

<body>

<p class="top-context-nav"><a class="top-context-item"
	href="browseSimple_facets.jsp?<%=request.getQueryString()%>">Retour
&agrave; la facetmap</a></p>

<div>
<h4>Configuration de la facetmap</h4>
<form action="setServletParameters.jsp" id="configServletFormFacet"
	name="configServletFormFacet"><input id="community"
	name="community" type="hidden"
	value="<%=request.getParameter("community")%>" /> <input
	id="facetName" name="facetName" type="hidden"
	value="<%=request.getParameter("facetName")%>" />
<table class="servletParameters">
	<tr>
		<td class="code">Mise &aacute; jour</td>
		<td>
		 <input id="update_facets" type="button"
			value="Mise &aacute; jour"
			onclick="window.update_facets('&facetName=<%=request.getParameter("facetName")%>','&community=<%=request.getParameter("community")%>')"
			name="update_facets" />
			
		</td>
	</tr>
	<tr class="description">
		<td></td>
		<td>
		<p>mise &aacute; jour de l'ensemble des facetmap ou uniquement une
		au choix</p>
		</td>
	</tr>


	<tr>
		<td class="code">R&eacute;sultats affich&eacute;s</td>
		<td><input name="resultLimit" type="text" size="10"
			value="<%=fms.getParameter("resultLimit", request)%>" /></td>
	</tr>
	<tr class="description">
		<td></td>
		<td>
		<p>Limite maximale des r&eacute;sultats affich&eacute;s sur la
		page</p>
		</td>
	</tr>

	<tr>
		<td class="code">Facettes vides</td>
		<td>
		<%
			boolean bVal = fms.getBooleanParameter("showEmptySelections",
					request);
		%> <input type="radio" name="showEmptySelections" value="true"
			<%=bVal ? "checked=true" : ""%>>&nbsp;Yes</input><br />
		<input type="radio" name="showEmptySelections" value="false"
			<%=bVal ? "" : "checked=true"%>>&nbsp;No</input></td>
	</tr>
	<tr class="description">
		<td></td>
		<td>
		<p>Permet de choisir si on affiche les facettes qui n'ont pas de
		repr&eacute;sentant.</p>
		</td>
	</tr>





	<tr>
		<td colspan="2"><%-- <input type="submit" value="Valider les changements">--%>
		<input type="submit" value="Valider les changements"
			onclick="transfer_config()"></td>
	</tr>
</table>
</form>
</div>
</body>
</html>

