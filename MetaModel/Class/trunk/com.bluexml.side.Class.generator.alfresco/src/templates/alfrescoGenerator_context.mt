<%--
Copyright (C) BlueXML 2005-2008

This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 --%>
<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator

%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getConfModulePath()%>/module-context.xml<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version='1.0' encoding='ISO-8859-1'?>
<!DOCTYPE beans PUBLIC '-//SPRING//DTD BEAN//EN' 'http://www.springframework.org/dtd/spring-beans.dtd'>

<beans>
    <!-- Registration of new models -->
    <bean id="<%getModuleIdService(name)%>.dictionaryBootstrap" parent="dictionaryModelBootstrap" depends-on="SIDEdictionaryBootstrap">
        <property name="models">
            <list>
                <value><%getModulePath()%>/model/<%name%>Model.xml</value>
            </list>
        </property>
		<property name="labels">
            <list>
                <value><%getModulePath()%>/model/messages</value>
            </list>
        </property>
    </bean>
    <!--
    <bean id="<%getModuleIdService(name)%>_configBootstrap" class="org.alfresco.web.config.WebClientConfigBootstrap" init-method="init">
      <property name="configs">
        <list>
           <value>classpath:<%getModulePath()%>/web-client-config-custom.xml</value>
        </list>
      </property>
   </bean>
    -->
   <bean id="<%getModuleIdService(name)%>_configBootstrap"
		class="com.bluexml.side.Framework.alfresco.hotdeployer.WebClientRepoBootstrap"
		init-method="init">
		<constructor-arg >
			<ref
				bean="<%getModuleIdService(name)%>_webClientConfigSource" />
		</constructor-arg>
	</bean>
	<bean
		id="<%getModuleIdService(name)%>_webClientConfigSource"
		parent="repoUrlConfigSource">
		<constructor-arg>
			<list>
				<value>classpath:<%getModulePath()%>/web-client-config-custom.xml</value>
				<value>workspace://SpacesStore/${spaces.company_home.childname}/${spaces.dictionary.childname}/app:webclient_extension/cm:<%getModuleIdService(name)%>_web-client-config-custom.xml</value>
			</list>
		</constructor-arg>
	</bean> 
    <!-- http://wiki.alfresco.com/wiki/Security_and_Authentication#The_default_permission_model_and_simple_extensions -->
</beans>
