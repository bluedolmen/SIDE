<%
metamodel http://www.kerblue.org/class/1.0

import templates.servicesTemplates.Common
%>

<%--
  -- This file is a patched copy of Alfresco Share's
  -- Contains additional message for custom Types 
  --%>
  
<%script type="clazz.ClassPackage" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext")%><%name%>/module-context.xml<%}%>

<%script type="clazz.ClassPackage" name="messages" file="<%fileName%>"%>
<?xml version='1.0' encoding='UTF-8'?>
<!DOCTYPE beans PUBLIC '-//SPRING//DTD BEAN//EN' 'http://www.springframework.org/dtd/spring-beans-2.0.dtd'>

<beans>
	<bean id="<%name%>.messages.resources" class="org.springframework.extensions.surf.util.ResourceBundleBootstrapComponent">
      <property name="resourceBundles">
         <list>
            <value>alfresco.web-extension.<%name%>.messages.slingshot</value>
         </list>
      </property>
   </bean>
</beans>