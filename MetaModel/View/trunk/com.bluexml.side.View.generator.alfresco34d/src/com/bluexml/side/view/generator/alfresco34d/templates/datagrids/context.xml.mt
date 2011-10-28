<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.view.generator.alfresco34d.ViewGenerator
%>

  
<%script type="view.ViewCollection" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext")%>/<%getModuleIdService(getRootPackage().name)%>/module-context.xml<%}%>

<%script type="view.ViewCollection" name="generate" file="<%fileName()%>" %>
<?xml version='1.0' encoding='UTF-8'?>
<!DOCTYPE beans PUBLIC '-//SPRING//DTD BEAN//EN' 'http://www.springframework.org/dtd/spring-beans.dtd'>

<beans>

  

   <!-- Provide <%getRootPackage().name%> form config -->
	<bean id="<%getRootPackage().name%>FormsURLConfig"
		class="org.springframework.extensions.config.source.UrlConfigSource">
		<constructor-arg>
			<list>
				<value>webapp:WEB-INF/classes/alfresco/web-extension/<%getModuleIdService(getRootPackage().name)%>/share-datagrid-config.xml</value>
				<value>webapp:WEB-INF/classes/alfresco/web-extension/<%getModuleIdService(getRootPackage().name)%>/share-datagrid-config-custom.xml</value>
			</list>
		</constructor-arg>
	</bean>

	<!-- Provide default form config -->
	<bean id="<%getRootPackage().name%>FormsClientConfig"
		class="com.bluexml.side.Framework.alfresco.share.formExtension.MyConfigBootStrap"
		init-method="register">
		<property name="configService" ref="web.config" />
		<property name="urlConfigSource" ref="<%getRootPackage().name%>FormsURLConfig" />
	</bean>
</beans>