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
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
%>

<%-- 
     
--%>

<%script type="clazz.ClassPackage" name="getCustomWebFwkContextOutputFile"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext")%>/custom-web-framework-application-context.xml<%}%>
<%script type="clazz.ClassPackage" name="custom-web-framework" file="<%getCustomWebFwkContextOutputFile%>"%>
<?xml version='1.0' encoding='UTF-8'?>
<!DOCTYPE beans PUBLIC '-//SPRING//DTD BEAN//EN' 'http://www.springframework.org/dtd/spring-beans-2.0.dtd'>

<beans>
	<bean id="webframework.configsource" class="org.alfresco.config.source.UrlConfigSource">
		<constructor-arg>
			<list>
				<!-- Web Script Framework -->
				<value>classpath:alfresco/webscript-framework-config.xml</value>
				<value>classpath:alfresco/web-extension/webscript-framework-config-custom.xml</value>
				<value>jar:*!/META-INF/webscript-framework-config-custom.xml</value>

				<!--  Start TEST dependencies -->
				<!--  These is included because some of the tests require it  -->
				<!--  These should be removed -->
				<value>classpath:alfresco/webscript-framework-config-test.xml</value>
				<!--  End TEST dependencies -->

				<!-- Web Framework -->
				<value>classpath:alfresco/web-framework-config.xml</value>
				<value>classpath:alfresco/web-framework-config-remote.xml</value>
				<value>classpath:alfresco/web-framework-config-commons.xml</value>
				<value>classpath:alfresco/web-framework-config-application.xml</value>
				<value>classpath:alfresco/web-extension/web-framework-config-defaults.xml</value>
				<value>classpath:alfresco/web-extension/web-framework-config-custom.xml</value>

				<value>jar:*!/META-INF/web-framework-config-custom.xml</value>

			</list>
		</constructor-arg>
	</bean>
</beans>
