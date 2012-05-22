<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.portal.generator.alfresco40d.templates.upload.upload-config-lib
%>
<%script type="Portal" name="validatedFilename_flash" post="trim()" %>
<%if (pageSet[ID.toLowerCase().trim() == "documentlibrary"].nSize() > 0 &&
 pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets[associationPortlet.name.toLowerCase().trim() == "uploadabletypes" && associationPortlet.isPortletInternal != null].nSize() > 0){%><%getProperty("alf.share.paths.web-ext.alf.components")%>/upload/flash-upload.get.config.xml
<%}%>
<%script type="Portal" name="forFlash" file="<%validatedFilename_flash%>"%>
<%alfrescoGenerator%>
