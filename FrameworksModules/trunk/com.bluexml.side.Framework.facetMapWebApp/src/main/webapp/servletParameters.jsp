<%@
page import="
    java.util.Enumeration,
    java.util.Iterator,
    com.facetmap.Map,
    com.facetmap.InternalException,
    com.facetmap.servlet.FacetMapServlet,
    com.facetmap.simple.TransformerPersister"
contentType="text/html; charset=ISO-8859-1" 
errorPage="error.jsp"
%><%
FacetMapServlet fms = (FacetMapServlet)getServletContext().getAttribute("com.facetmap.servlet");
%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="servletParameters.css">
<script type="text/javascript" src="xsl/display/browseSimpleForFrames.js">
</script>
</head>

<body>

<p class="top-context-nav">
    <a class="top-context-item" href="browseSimple_facets.jsp">Retour &agrave; la facetmap</a>
</p>

<div>
<h4>Configuration de la facetmap</h4>
<form action="setServletParameters.jsp" id="configServletFormFacet" name="configServletFormFacet">
<table class="servletParameters">
    
    <tr>
        <td class="code">Map</td>
        <td>
            <input name="map" type="text" size="60"
             value="<%= fms.getParameter("map") %>"
        </td>
    </tr>
    <tr class="description">
        <td></td>
        <td>
            <p>Nom de la facetmap &agrave; utiliser (lien absolu, relatif (point de d&eacute;part = WEB-INF) ou http)</p>
        </td>
    </tr>
    
    <tr>
        <td class="code">Affichage</td>
        <td>
            <select name="view">
<%
    for (Iterator i = fms.getViewPersister().getTransformerNames().iterator(); i.hasNext(); ) {
        String view = i.next().toString();
        String selected = view.equals(fms.getParameter("view")) ? "selected" : "";
%>
                <option value="<%= view %>" <%= selected %>><%= view %></option>
<%
    }
%>
            </select>
        </td>
    </tr>
    <tr class="description">
        <td></td>
        <td>
            <p>Choix du mode d'affichage &agrave; utiliser</p>
        </td>
    </tr>

    <tr>
        <td class="code">R&eacute;sultats affich&eacute;s</td>
        <td>
            <input name="resultLimit" type="text" size="10"
             value="<%= fms.getParameter("resultLimit") %>"
        </td>
    </tr>
    <tr class="description">
        <td></td>
        <td>
            <p>Limite maximale des r&eacute;sultats affich&eacute;s sur la page</p>
        </td>
    </tr>

    <tr>
        <td class="code">Facettes vides</td>
        <td>
<%          boolean bVal = fms.getBooleanParameter("showEmptySelections"); %>
            <input type="radio" name="showEmptySelections" value="true"
             <%= bVal ? "checked=true" : ""%>>&nbsp;Yes</input><br />
            <input type="radio" name="showEmptySelections" value="false"
             <%= bVal ? "" : "checked=true"%>>&nbsp;No</input>
        </td>
    </tr>
    <tr class="description">
        <td></td>
        <td>
            <p>Permet de choisir si on affiche les facettes qui n'ont pas de repr&eacute;sentant.</p>
        </td>
    </tr>

    <tr>
        <td class="code"> Fichier log4j</td>
        <td>
            <input name="log4jConfigFile" type="text" size="60"
             value="<%= fms.getParameter("log4jConfigFile") %>"
        </td>
    </tr>
    <tr>
        <td class="code">R&eacute;pertoire utilis&eacute;</td>
        <td>
            <input name="workDirectory" type="text" size="60"
             value="<%= fms.getParameter("workDirectory") %>"
        </td>
    </tr>
    <tr class="description">
        <td></td>
        <td>
            <p>Facetmap a besoin d'un dossier o&ugrave; elle peut &eacute;crire, par d&eacute;faut ce dossier doir &ecirc;tre "." mais si le r&eacute;pertoire courant est prot&eacute;g&eacute; en &eacute;criture veuillez en pr&eacute;ciser un autre.</p>
        </td>
    </tr>

    <tr><td colspan="2">
        <%-- <input type="submit" value="Valider les changements">--%>
        <input type="submit" value="Valider les changements" onclick="transfer_config()">
    </td></tr>
</table>
</form>
</div>
</body></html>

