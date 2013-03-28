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
