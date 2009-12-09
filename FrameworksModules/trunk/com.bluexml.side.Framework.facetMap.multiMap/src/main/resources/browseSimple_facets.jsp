<%@ page import="
	java.util.Enumeration,
	com.facetmap.Map,
	com.facetmap.Selection,
	com.facetmap.DataException,
	com.facetmap.Resource,
	com.facetmap.servlet.FacetMapServlet,
	com.facetmap.servlet.ServletUtil,
	com.facetmap.simple.SimpleFacetmap,
	javax.xml.transform.Transformer,
	javax.xml.transform.TransformerException,
	javax.xml.transform.dom.DOMSource,
	javax.xml.transform.stream.StreamSource,
	javax.xml.transform.stream.StreamResult,
	javax.xml.transform.dom.DOMResult,
	com.bluexml.side.framework.facetmap.multimap.*"
	contentType="text/html; charset=ISO-8859-1" 
 %>
<%
	FacetMapAlfrescoServlet fms = (FacetMapAlfrescoServlet)getServletContext().getAttribute("com.facetmap.servlet");
	String groupId ="admin";
	String contentType="cm:content";
	contentType="com:com_bluexml_demo_rh_Personne";
	if (request.getParameter("contentType") !=null) {
		contentType=request.getParameter("contentType");
	}
	
	String mapId=contentType+"_"+groupId;
	
	Map facetmap = (Map)fms.getMap(request, mapId);
	System.out.println("facetMap loaded");
	Selection selection = ServletUtil.processRequest(request, facetmap);
	String sRef = selection.getRef();
	request.setAttribute("_selection", selection);
	String view = fms.getParameter("view_facets");
	
	response.setHeader("Cache-control", "max-age=0, no-cache");
%>
<jsp:include page="renderSelection.jsp" flush="true">
  <jsp:param name="transform" value="<%= view %>" />
  <jsp:param name="browsePage" value="browseSimple_facets.jsp?" />
</jsp:include>
<%
    DOMResult selectionHtmlDomResult = (DOMResult)request.getAttribute("selectionHtmlDomResult");
    Transformer headTransformer = (Transformer)request.getAttribute("headTransformer");
    Transformer bodyTransformer = (Transformer)request.getAttribute("bodyTransformer");
%>
<html>
  <head>
    <title>Facetmap-content</title>
    <%
    if (headTransformer == null || selectionHtmlDomResult == null) {
        out.println("<!-- headTransformer or selectionHtmlDomResult missing -->");
    } else {
        headTransformer.transform(
            new DOMSource(selectionHtmlDomResult.getNode()),
            new StreamResult(out)
        );
    }
%>
	  <script type="text/javascript" src="xsl/display/browseSimpleForFrames.js">
	  </script>
  </head>
<body onload="{check_selected_criteria();check_pagination()}">
<%
    if (bodyTransformer == null || selectionHtmlDomResult == null) {
        out.println("<!-- bodyTransformer or selectionHtmlDomResult missing -->");
    } else {
        bodyTransformer.transform(
            new DOMSource(selectionHtmlDomResult.getNode()),
            new StreamResult(out)
        );
    }
%>
<body>
</html>

