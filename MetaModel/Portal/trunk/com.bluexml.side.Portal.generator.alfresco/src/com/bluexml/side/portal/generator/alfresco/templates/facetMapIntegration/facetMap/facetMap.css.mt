<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%-- Templates creation --%>
<%script type="Portal" name="createTemplates"%>
<%getProperty("alf.share.paths.core.webapp.app-components-root")%>/facetMap/facetMap.css
<%script type="Portal" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
#facetsContainer {
	width: 300px;
	height: 1000px;
	border:none;
}

#resultsContainer {
	width: 900px;
	height: 600px;
	border:none;
	float:right;
}