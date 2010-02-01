<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.portal.generator.alfresco.service.ShareGeneratorServices
%>

<%-- Templates creation --%>
<%script type="HavePortlet" name="createTemplates"%>
<%if (associationPortlet.isPortletInternal != null && associationPortlet.isPortletInternal.form != null){%>
<%for (associationPortlet.isPortletInternal.form){%>
	<%if (current("HavePortlet").associationPortlet.name.toLowerCase() =="advanced-search" && current("HavePortlet").associationPortlet.isFormPortlet()){%>
<%getProperty("alf.share.paths.web-ext.components")%>/template.advanced-search.<%current("HavePortlet").associationPage.ID%>.xml
	<%}%>
<%}%>
<%}%>

<%script type="HavePortlet" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
<?xml version='1.0' encoding='UTF-8'?>
<component>
   <scope>template</scope>
   <region-id>facetmap</region-id>
   <source-id><%associationPage.ID%></source-id>
   <url>/components/XformSearch</url>
   <properties>
   		
   		<%if (associationPortlet.isPortletInternal != null && associationPortlet.isPortletInternal.form != null){%>
			<searchForm>[<%for (associationPortlet.isPortletInternal.form.forms){%>'<%id%>'<%if (current("HavePortlet").associationPortlet.isPortletInternal.form.forms.nLast() !=current()){%>,<%}%><%}%>]</searchForm>
   		<%}%>
   		
   		
   </properties>
</component>