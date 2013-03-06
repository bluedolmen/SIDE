<%
metamodel http://www.kerblue.org/class/1.0

import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>

<%-- Templates creation --%>
<%script type="Model" name="createMessages" post="trim()" %>
<%if (eContainer() == null) {%>
<%getProperty("alf.share.paths.web-ext")%>/sharePortalClass<%name%>Extensions/module-context.xml
<%}%>
<%script type="Model" name="alfrescoGenerator" file="<%createMessages%>"%>
<?xml version='1.0' encoding='UTF-8'?>
<!DOCTYPE beans PUBLIC '-//SPRING//DTD BEAN//EN' 'http://www.springframework.org/dtd/spring-beans.dtd'>

<beans>
	<!-- Type evaluators -->
   <%for (getAllClasses()){%>
    <bean id="evaluator.doclib.metadata.side.<%getPrefixedQName()%>"
      parent="evaluator.doclib.action.nodeType">
      <property name="types">
      <list>
         <value><%getPrefixedQName()%></value>
      </list>
      </property>
	</bean>
   <%}%>   
</beans>
