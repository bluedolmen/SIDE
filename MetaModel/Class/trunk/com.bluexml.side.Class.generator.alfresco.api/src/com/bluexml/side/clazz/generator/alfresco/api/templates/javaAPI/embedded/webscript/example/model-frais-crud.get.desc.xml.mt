<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.service.ValueGenerator
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.embedded.javaAPI
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.lib
import com.bluexml.side.clazz.generator.alfresco.api.templates.services.classes
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices


%>
<%script type="clazz.Clazz" name="validatedFilename"%>
<%getProperty("javaEmbeddedAPIPath")%>/<%getProperty("moduleWebscript")%>/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/model-<%name%>-crud.get.desc.xml
<%script type="clazz.Clazz" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<webscript>
  <shortname>Webscript to perform CRUD action on <%name%> Object</shortname>
  	<description><![CDATA[
  	
]]>
  </description>
  <url>/model/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/api/<%name%>?action={action}</url>
  <url>/model/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/api/<%name%>?action=create&amp;<%for (getAllSortedAttibutes){%><%name%>={<%name%>}<%}%></url>
  <url>/model/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/api/<%name%>?action=request&amp;<%for (getAllSearchableSortedAttibutes){%><%name%>={<%name%>}&amp;<%}%></url>
  <url>/model/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/api/<%name%>?action=update&amp;nodeRef={nodeRef}<%for (getAllSortedAttibutes){%><%name%>={<%name%>}&amp;<%}%></url>
  <url>/model/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/api/<%name%>?action=delete&amp;nodeRef={nodeRef}</url>
  
  <%for (getAllSourceAssociationEnds()){%>
  <url>/model/<%service::getRootContainer().name%>/<%current("Clazz").eContainer().getQualifiedName().replaceAll("_","/")%>/api/<%current("Clazz").name%>?action=createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>&amp;source={nodeRef}&amp;target={nodeRef}</url>
  <%}%>
  <format default="json" />
  <authentication>user</authentication>
  <family><%service::getRootContainer().name%> Model</family>
</webscript>