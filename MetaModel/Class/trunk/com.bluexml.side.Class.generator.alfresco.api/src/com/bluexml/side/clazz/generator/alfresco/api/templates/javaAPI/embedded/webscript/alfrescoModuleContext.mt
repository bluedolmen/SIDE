<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.lib
import com.bluexml.side.clazz.generator.alfresco.api.templates.services.classes
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.embedded.javaAPI
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("javaEmbeddedAPIPath")%>/<%getProperty("moduleConfig")%>/module-context.xml<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version='1.0' encoding='UTF-8'?>
<!--
	Licensed to the Apache Software Foundation (ASF) under one or more
	contributor license agreements.  See the NOTICE file distributed with
	this work for additional information regarding copyright ownership.
	The ASF licenses this file to You under the Apache License, Version 2.0
	(the "License"); you may not use this file except in compliance with
	the License.  You may obtain a copy of the License at
	
	http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
	
-->
<!DOCTYPE beans PUBLIC '-//SPRING//DTD BEAN//EN' 'http://www.springframework.org/dtd/spring-beans.dtd'>

<beans>
<%for (getAllSortedAbstractClasses()){%>

	<bean id="<%getBeanId(getJavaAPIName())%>" class="<%getJavaAPIPackage()%>.<%getJavaAPIName%>">		
		<property name="nodeService" ref="NodeService" />
		<property name="serviceRegistry" ref="ServiceRegistry" />
		<%if (filter("Clazz")){%>
		<property name="factoryRegistry" ref="side.api.<%getRootContainer().name%>.<%getProperty("java.classes.factoryRegistry")%>" />
		<%}%>		
	</bean>
	
	<bean id="<%getBeanId(javaWebScriptFactoryAdapterName())%>" class="<%getJavaAPIPackage()%>.script.<%javaWebScriptFactoryAdapterName()%>" parent="baseJavaScriptExtension">
		<property name="extensionName">
			<value>side<%getJavaAPIName%></value>
		</property>
		<property name="serviceRegistry" ref="ServiceRegistry" />
		<property name="imageResolver" ref="webscripts.repo.imageresolver" />
		<property name="<%getJavaAPIName().toL1Case()%>" ref="<%getBeanId(getJavaAPIName())%>" />
	</bean>
<%}%>

	<bean id="side.api.<%getRootContainer().name%>.<%getProperty("java.classes.factoryRegistry")%>" class="<%getProperty("javaPackageAPI")%>.<%service::getRootContainer().name%>.<%getProperty("java.classes.factoryRegistry")%>">
		<%for (getAllSortedAbstractClasses()){%>
		<property name="<%getJavaAPIName().toL1Case()%>" ref="<%getBeanId(getJavaAPIName())%>" />
		<%}%>		
	</bean>

	<bean id="side.api.<%getRootContainer().name%>.<%getProperty("java.classes.factoryRegistryAdapter")%>" class="<%getProperty("javaPackageAPI")%>.<%service::getRootContainer().name%>.<%getProperty("java.classes.factoryRegistryAdapter")%>" parent="baseJavaScriptExtension">
		<property name="extensionName">
			<value>sideFactory</value>
		</property>
		<%for (getAllSortedAbstractClasses()){%>
		<property name="<%javaWebScriptFactoryAdapterName().toL1Case()%>" ref="<%getBeanId(javaWebScriptFactoryAdapterName())%>" />
		<%}%>		
	</bean>	
	
</beans>

