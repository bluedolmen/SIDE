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
		<type name="<%getFolder()%>:<%getQualifiedName()%>">
			<%if (title != null){%>
			<title><%title%></title>
			<%}%>
			<%if (description != null){%>
			<description> <% description %> </description>
			<%}%>
			<%if (generalizations.nSize()>0){%>
			
			<!-- Generalization -->
			<%}%>
			
			<%for (generalizations){%>
			    <%if (i() > 0){%>
			<!-- <parent><%getFolder()%>:<%getQualifiedName()%></parent> -->
			    <%}else{%>
            <parent><%getFolder()%>:<%getQualifiedName()%></parent>			    
			    <%}%>
			<%}%>
			<%if (generalizations.nSize() == 0){%>
				<%if (metainfo[key.equalsIgnoreCase("isContainer")].nSize()>0){%>
			<parent>cm:folder</parent>
			 	<%}else{%>
			<parent>cm:content</parent>
				<%}%>
			<%}else{%>
				<%if (metainfo[key.equalsIgnoreCase("isContainer")].nSize()>0){%>
			<!-- <parent>cm:folder</parent> -->
				<%}%>
			<%}%>
			
			<%if (attributes.nSize() > 0){%>

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
						<%if (!valueList.dynamic){%>
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
				<%}%>
			</properties>
			<%}%>


			<!-- Associations -->
			<associations>
			<%for (eContainer("ClassPackage").eAllContents("AssociationEnd")[linkedClass == current("Clazz") && getOpposite().navigable]){%>
				<<%eContainer().getAssociationType()%> name="<%eContainer().getFolder()%>:<%eContainer().getQualifiedName(current("AssociationEnd"))%>">							

					<title><%getRoleOrTitleFromSource()%></title>
					<source>
						<mandatory><%if (isMandatory()){%>true<%}else{%>false<%}%></mandatory>
						<many><%if (isMany()){%>true<%}else{%>false<%}%></many>
					</source>
					<target>
						<class><%getOpposite().getFolder()%>:<%getOpposite().linkedClass.getQualifiedName()%></class>
						<mandatory><%if (getOpposite().isMandatory()){%>true<%}else{%>false<%}%></mandatory>
						<many><%if (getOpposite().isMany()){%>true<%}else{%>false<%}%></many>
					</target>
				</<%eContainer().getAssociationType()%>>							
			<%}%>
			</associations>

						
			<!-- Aspects -->
		<%if (aspects.nSize()>0){%>
			<mandatory-aspects>
			<%for (aspects){%>
				<aspect><%getFolder()%>:<%getQualifiedName()%></aspect>
			<%}%>
			</mandatory-aspects>
		<%}%>
		</type>
		
