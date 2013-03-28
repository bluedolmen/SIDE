<%--
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.service.ValueGenerator
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.embedded.javaAPI
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.lib
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices


%>
<%script type="clazz.Aspect" name="validatedFilename"%>
<%getProperty("javaEmbeddedAPIPath")%>/<%getProperty("moduleWebscript")%>/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/model-<%name%>-crud.get.desc.xml
<%script type="clazz.Aspect" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<webscript>
  <shortname>Webscript to Add/remove <%name%> Aspect</shortname>
  	<description><![CDATA[
  	
]]>
  </description>
  <url>/model/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/api/<%name%>?action={action}</url>
  <url>/model/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/api/<%name%>?action=add&amp;nodeRef={nodeRef}<%for (getAllSortedAttibutes){%><%name%>={<%name%>}<%}%></url>
  <url>/model/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/api/<%name%>?action=remove&amp;nodeRef={nodeRef}</url>
  <url>/model/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/api/<%name%>?action=update&amp;nodeRef={nodeRef}<%for (getAllSortedAttibutes){%><%name%>={<%name%>}&amp;<%}%></url>
  <format default="json" />
  <authentication>user</authentication>
  <family><%service::getRootContainer().name%> Model</family>
</webscript>
