<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%-- Templates creation --%>
<%script type="Portal" name="createTemplates"%>
<%getProperty("alf.share.paths.core.webapp.app-components-root")%>/Xform/Xform.css
<%script type="Portal" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
#xformEdit {
	width: 900px;
	height: 1000px;
	border:none;
}