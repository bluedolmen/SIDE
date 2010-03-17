<%--
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
import templates.servicesTemplates.Attribute
import templates.servicesTemplates.Association

import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>
<%script type="clazz.Enumeration" name="alfrescoGeneratorEnums" %>
	<%if (dynamic) {%>
	<type name="<%getFolder()%>:<%getQualifiedName()%>">
		<title><%name%></title>		
		<description><%name%></description>		
		<parent>bxcm:content</parent>
		
		<properties>
			<property name="<%getFolder()%>:<%getQualifiedName()%>_label">
				<title>Label</title>
				<type>d:text</type>
				<mandatory>true</mandatory>
				<index enabled="true">
		                 <atomic>true</atomic>
		                 <stored>false</stored>
		                 <tokenised>false</tokenised>
		        </index>
			</property>						
			
			<property name="<%getFolder()%>:<%getQualifiedName()%>_code">
				<title>Code</title>
				<type>d:text</type>
				<mandatory>true</mandatory>
		              <index enabled="true">
		                 <atomic>true</atomic>
		                 <stored>false</stored>
		                 <tokenised>false</tokenised>
		              </index>
			</property>
		</properties>
	</type>	
	<%}%>