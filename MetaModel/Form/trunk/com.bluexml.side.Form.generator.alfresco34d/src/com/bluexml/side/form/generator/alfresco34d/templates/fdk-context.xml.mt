<%
metamodel http://www.kerblue.org/form/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.form.generator.alfresco34d.FormGenerator
 
%>

  
<%script type="form.FormCollection" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext")%>/<%getModuleIdService()%>/module-context.xml<%}%>

<%script type="form.FormCollection" name="generate" file="<%fileName()%>" %>
<?xml version='1.0' encoding='UTF-8'?>
<!DOCTYPE beans PUBLIC '-//SPRING//DTD BEAN//EN' 'http://www.springframework.org/dtd/spring-beans.dtd'>

<beans>

   <bean id="<%getModelName()%>ResourceBundle" class="org.springframework.extensions.surf.util.ResourceBundleBootstrapComponent">
      <property name="resourceBundles">
         <list>
            <value>alfresco.web-extension.<%getModuleIdService()%>.<%getRootPackage().name%></value>
         </list>
      </property>
   </bean>

   <!-- Provide <%getModelName()%> form config -->
	<bean id="<%getModelName()%>FormsURLConfig"
		class="org.springframework.extensions.config.source.UrlConfigSource">
		<constructor-arg>
			<list>
				<value>webapp:WEB-INF/classes/alfresco/web-extension/<%getModuleIdService()%>/share-forms-config.xml</value>
				<value>webapp:WEB-INF/classes/alfresco/web-extension/<%getModuleIdService()%>/share-forms-config-custom.xml</value>
			</list>
		</constructor-arg>
	</bean>

	<!-- Provide default form config -->
	<bean id="<%getModelName()%>FormsClientConfig"
		class="com.bluexml.side.Framework.alfresco.share.formExtension.MyConfigBootStrap"
		init-method="register">
		<property name="configService" ref="web.config" />
		<property name="urlConfigSource" ref="<%getModelName()%>FormsURLConfig" />
	</bean>
</beans>