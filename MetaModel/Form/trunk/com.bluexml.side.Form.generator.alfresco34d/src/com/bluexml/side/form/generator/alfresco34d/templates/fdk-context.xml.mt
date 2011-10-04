<%
metamodel http://www.kerblue.org/form/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.form.generator.alfresco34d.FormGenerator
 
%>

  
<%script type="form.FormCollection" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext")%>/<%getModuleIdService(getRootPackage().name)%>/module-context.xml<%}%>

<%script type="form.FormCollection" name="generate" file="<%fileName()%>" %>
<?xml version='1.0' encoding='UTF-8'?>
<!DOCTYPE beans PUBLIC '-//SPRING//DTD BEAN//EN' 'http://www.springframework.org/dtd/spring-beans.dtd'>

<beans>

   <bean id="<%getRootPackage().name%>ResourceBundle" class="org.springframework.extensions.surf.util.ResourceBundleBootstrapComponent">
      <property name="resourceBundles">
         <list>
            <value>alfresco.web-extension.<%getModuleIdService(getRootPackage().name)%>.<%getRootPackage().name%></value>
         </list>
      </property>
   </bean>

   <!-- Provide <%getRootPackage().name%> form config -->
   <bean id="<%getRootPackage().name%>FormsClientConfig" class="org.springframework.extensions.config.ConfigBootstrap" 
         init-method="register">
      <property name="configService" ref="web.config" />
      <property name="configs">
         <list>
            <value>classpath:alfresco/web-extension/<%getModuleIdService(getRootPackage().name)%>/share-forms-config.xml</value>
            <value>classpath:alfresco/web-extension/<%getModuleIdService(getRootPackage().name)%>/share-forms-config-custom.xml</value>
         </list>
      </property>
   </bean>

</beans>