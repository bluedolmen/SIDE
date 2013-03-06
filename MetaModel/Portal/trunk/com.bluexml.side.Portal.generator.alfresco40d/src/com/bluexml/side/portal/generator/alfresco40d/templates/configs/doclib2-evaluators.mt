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
