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
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>

<%script type="clazz.Aspect" name="alfrescoGeneratorModelAspect" %>
	
		<aspect name="<%getPrefixedQName()%>">
			<%if (title != null){%>
			<title><%title%></title>
			<%}%>
			<%if (description != null){%>
			<description> <% description %> </description>
			<%}%>
			
		<%if (attributes.nSize() > 0){%>			
			<!-- Properties -->
			<properties>
				<%for (attributes){%>
				<property name="<%getPrefixedQName()%>">

					<%if (title != null){%>
					<title> <%title%> </title>
					<%}%>
					<%if (description != null){%>
					<description> <%description%> </description>
					<%}%>
					<type><%getPropertyType()%></type>
					<%if (metainfo[key.equalsIgnoreCase("required")].nSize()>0){%>
					<mandatory>true</mandatory>
					<%}%>
					<%if (initialValue != null){%>
					<default><%initialValue%></default>
					<%}%>					
		              <index enabled="true">
		                 <atomic>true</atomic>
		                 <stored>false</stored>
		                 <tokenised>false</tokenised>
		              </index>					
					<constraints>
					<%if (metainfo[key.equalsIgnoreCase("email")].nSize()>0){%>
						<constraint ref="bxds:constraint:mail"/>
					<%}%>					
					
					<%if (valueList) {%>

						<%if (!valueList.dynamic){%>
							<constraint ref="<%getPrefixe()%>:nomenclature:<%valueList.getQualifiedName()%>"/>
						<%}else{%>
							<constraint ref="<%getPrefixe()%>:enum:<%valueList.getQualifiedName()%>"/>
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
				<%}%>
			</properties>			
			<%}%>
		</aspect>
