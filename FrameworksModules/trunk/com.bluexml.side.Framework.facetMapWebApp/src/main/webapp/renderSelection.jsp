<%@ page import="
    com.facetmap.*,
    com.facetmap.servlet.FacetMapServlet,
    com.facetmap.servlet.ServletUtil,
    com.facetmap.simple.TransformerPersister,
    com.facetmap.simple.XmlGenerator,
    com.facetmap.simple.logging.LogUtil,
 java.io.File,
 javax.xml.transform.Templates,
 javax.xml.transform.TransformerFactory,
 javax.xml.transform.Transformer,
 javax.xml.transform.TransformerException,
 javax.xml.transform.TransformerConfigurationException,
 javax.xml.transform.Source,
 javax.xml.transform.dom.DOMSource,
 javax.xml.transform.stream.StreamSource,
 javax.xml.transform.stream.StreamResult,
 javax.xml.transform.dom.DOMResult" 
contentType="text/html; charset=ISO-8859-1" %>
<%
    try {
        FacetMapServlet fms = (FacetMapServlet)getServletContext().getAttribute("com.facetmap.servlet");
		if (! fms.isConfigured()) {
          %><jsp:forward page="servletParameters.jsp" /><%
        }
        
        XmlGenerator xml = new XmlGenerator();
        Selection selection = (Selection)request.getAttribute("_selection");
        DOMSource selectionSource = new DOMSource(xml.documentOf(selection));
        DOMResult result = new DOMResult();

        String transformName = request.getParameter("transform");
        TransformerPersister transformerPersister = fms.getViewPersister();
        Transformer transformer = transformerPersister.get(transformName);

        transformer.setParameter("pre_reference_url", request.getParameter("browsePage") + "&s=");
        transformer.transform(selectionSource, result);

        Transformer headReader = transformerPersister.get("html_head.xsl");

        Transformer bodyReader = transformerPersister.get("html_body.xsl");

        request.setAttribute("selectionHtmlDomResult", result);
        request.setAttribute("headTransformer", headReader);
        request.setAttribute("bodyTransformer", bodyReader);

    } catch (DataException exc) {
        throw new ServletException(exc);
    } catch (InternalException exc) {
        throw new ServletException(exc);
    } catch (TransformerException exc) {
        throw new ServletException(exc);
    }
%>

