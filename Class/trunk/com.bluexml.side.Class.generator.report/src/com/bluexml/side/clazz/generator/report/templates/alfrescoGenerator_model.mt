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

import com.bluexml.side.clazz.generator.alfresco.services.AttributeServices

import com.bluexml.side.clazz.generator.alfresco.services.AssociationServices
%>


<%script type="clazz.Clazz" name="alfrescoGenerator_class" %>
<model>		
		<class name="<%getFolder()%>:<%getQualifiedName()%>">
			<%if (title != null){%>
			<title><%title%></title>
			<%}%>
			<%if (description != null){%>
			<description> <% description %> </description>
			<%}%>

			<!-- Properties -->
			<properties>
				<%for (attributes){%>
				<property name="<%getFolder()%>:<%current(1).getQualifiedName()%>_<%name%>">
					<%if (title != null) {%>
					<title> <%title%> </title>
					<%}%>
					<%if (description != null) {%>
					<description> <%description%> </description>
					<%}%>
					<type><%getPropertyType()%></type>
					<%if (metainfo[key.equalsIgnoreCase("required")].nSize()>0){%>
					<mandatory>true</mandatory>
					<%}%>
					<%if (metainfo[key.equalsIgnoreCase("multiple")].nSize()>0){%>
					<multiple>true</multiple>
					<%}%>
					<%if (initialValue != null){%>
					<default><%initialValue%></default>
					<%}%>					
					
		              <index enabled="true">
		                 <atomic>true</atomic>
		                 <stored>false</stored>
		                 <tokenised>true</tokenised>
		              </index>					
					<constraints>
					<%if (metainfo[key.equalsIgnoreCase("email")].nSize()>0){%>
						<constraint ref="bxds:constraint:mail"/>
					<%}%>
					<%if valueList {%>
						<%if (!valueList.isDynamic){%>
							<constraint ref="<%getFolder()%>:nomenclature:<%valueList.getQualifiedName()%>"/>
						<%}else{%>
							<!--<constraint ref="<%getFolder()%>:Litteral"/>-->
							<constraint ref="<%getFolder()%>:enumU:<%valueList.getQualifiedName()%>"/>
						<%}%>
					<%}%>
					<%if (metainfo[key.endsWith("-length")].nSize()>0) {%>
		                 <constraint type="LENGTH">
        		            <parameter name="minLength"><value><%metainfo[key.equalsIgnoreCase("min-length")].nFirst().value%></value></parameter>
                		    <parameter name="maxLength"><value><%metainfo[key.equalsIgnoreCase("max-length")].nFirst().value%></value></parameter>
		                 </constraint>
					<%}%>
					<%if (metainfo[key.equalsIgnoreCase("regular-expression")].nSize()>0) {%>
		                 <constraint type="REGEX">
        		            <parameter name="expression"><value><%metainfo[key.equalsIgnoreCase("regular-expression")].nFirst().value%></value></parameter>
                		    <parameter name="requiresMatch"><value>true</value></parameter>
		                 </constraint>
					<%}%>
					</constraints>
				</property>
			</properties>
		</type>