<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.portal.generator.alfresco.PortalAlfrescoGenerator
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%-- Templates creation --%>
<%script type="Portal" name="createTemplates"%>
<%getProperty("alf.share.paths.core.site-webscripts")%>/com/bluexml/components/facetMap/facetMap.get.html.ftl
<%script type="Portal" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
<iframe src="<%getFacetMapURL()%>/browseSimple_facets.jsp?facetName=${facetName}&community=${siteID}&userName=${userName}&userTicket=${ticket}" id="facetsContainer" style="width: 600px; height: 600px;border:none"></iframe>
<iframe src="<%getFacetMapURL()%>/browseSimple_content.jsp?facetName=${facetName}&community=${siteID}&userName=${userName}&userTicket=${ticket}" id="resultsContainer" style="width: 600px; height: 600px;border:none"></iframe>
