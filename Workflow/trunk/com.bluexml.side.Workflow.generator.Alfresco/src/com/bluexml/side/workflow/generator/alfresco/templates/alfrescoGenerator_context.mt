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
metamodel http://www.kerblue.org/workflow/1.0
%>
<%script type="workflow.Process" name="validatedFilename"%>
shared/classes/alfresco/extension/workflow-<%name%>-context.xml
<%script type="workflow.Process" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version='1.0' encoding='ISO-8859-1'?>
<!DOCTYPE beans PUBLIC '-//SPRING//DTD BEAN//EN' 'http://www.springframework.org/dtd/spring-beans.dtd'>
 
<beans>
 
    <bean id="BlueXML_Workflows.workflowBootstrap" parent="workflowDeployer">
		<property name="workflowDefinitions">
			<list>
				<props>
					<prop key="engineId">jbpm</prop>
					<prop key="location">alfresco/extension/BxDS/generated/bpm/<%name%>_processdefinition.xml</prop>
					<prop key="mimetype">text/xml</prop>
					<prop key="redeploy">true</prop>
				</props>
			</list>
		</property>
		<property name="models">
			<list>
                <value>alfresco/extension/BxDS/generated/bpm/model-<%name%>.xml</value>
			</list>
		</property>
		<property name="labels">
			<list>
                <value>alfresco/extension/BxDS/generated/bpm/workflow-<%name%>-messages</value>
			</list>
		</property>
	</bean>
 
</beans>