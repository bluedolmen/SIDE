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
<%for (getInnerView()){%>
   <!-- Display Template: Type=<%filter("view.AbstractViewOf").viewOf.filter("clazz.Clazz").getPrefixedQName()%>, view= <%filter("view.AbstractViewOf").name%>-->
   <bean id="evaluator.doclib.metadata.side.<%filter("view.AbstractViewOf").viewOf.filter("clazz.Clazz").getPrefixedQName()%>_<%filter("view.AbstractViewOf").name%>"
      parent="evaluator.doclib.action.nodeType">
      <property name="types">
      <list>
         <value><%filter("view.AbstractViewOf").viewOf.filter("clazz.Clazz").getPrefixedQName()%></value>
      </list>
      </property>
	</bean>
<%}%><%}%><%}%><%}%>
