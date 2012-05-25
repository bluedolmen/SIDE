<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService

import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.portal.generator.alfresco40d.templates.configs.doclib2-evaluators
%>

<%-- Templates creation --%>
<%script type="Portal" name="createMessages" post="trim()" %>
<%if (eContainer() == null) {%>
<%getProperty("alf.share.paths.web-ext")%>/sharePortal<%name%>Extensions/module-context.xml
<%}%>
<%script type="Portal" name="alfrescoGenerator" file="<%createMessages%>"%>
<?xml version='1.0' encoding='UTF-8'?>
<!DOCTYPE beans PUBLIC '-//SPRING//DTD BEAN//EN' 'http://www.springframework.org/dtd/spring-beans.dtd'>

<beans>

   <bean id="<%name%>.resourceBundle" class="org.springframework.extensions.surf.util.ResourceBundleBootstrapComponent">
      <property name="resourceBundles">
         <list>
            <value>alfresco.web-extension.sharePortal<%name%>Extensions.messages</value>
         </list>
      </property>
   </bean>
	
	<%createDoclib2View()%>
</beans>
