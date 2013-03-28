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
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService

import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>

<%-- Templates creation --%>
<%script type="Portal" name="createDoclib2View" %>
<%for (portletSet){%>
<%if (name.toLowerCase() == "documentlibrary" && isPortletInternal != null && isPortletInternal.view != null) {%>
<%for (isPortletInternal.view){%>
   <bean id="doclib2PortalConfig_<%service::getRootContainer().name%>_<%name%>" class="org.springframework.extensions.config.ConfigBootstrap"
      init-method="register">
      <property name="configService" ref="web.config" />
      <property name="configs">
      <list>
         <value>classpath:alfresco/web-extension/SIDE_ViewExtension_<%service::getRootContainer().name%>/config-view-<%name%>.xml</value>
      </list>
      </property>
   </bean>
<%}%><%}%><%}%>
