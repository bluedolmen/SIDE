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
import com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator
%>

<%script type="clazz.AbstractClass" name="alfrescoGenerator_abstractClass_title" %>
<%if (title != null){%>
<title><%title%></title>
<%}%>
<%script type="clazz.AbstractClass" name="alfrescoGenerator_abstractClass_description" %>
<%if (description != null){%>
<description><%description%></description>
<%}%>
<%script type="clazz.Clazz" name="alfrescoGenerator_abstractClass_parent" %>
<%alfrescoGenerator_parent%>
<%if (generalizations.nSize() == 0){%>
	<%if (metainfo[key.equalsIgnoreCase("isContainer")].nSize()>0){%>
<parent>cm:folder</parent>
 	<%}else{%>
<parent>bxcm:content</parent>
	<%}%>
<%}%>
<%script type="clazz.AbstractClass" name="alfrescoGenerator_parent" %>
<%for (generalizations.nSort("name")){%>
    <%if (i() > 0){%>
<!-- <parent><%getPrefixedQName()%></parent> -->
    <%}else{%>
<parent><%getPrefixedQName()%></parent>			    
    <%}%>
<%}%>
<%script type="clazz.Aspect" name="alfrescoGenerator_abstractClass_parent" %>
<%alfrescoGenerator_parent%>

<%script type="clazz.AbstractClass" name="alfrescoGenerator_abstractClass_archive" %>
<archive><%if (metainfo[key.equalsIgnoreCase("archive")].nSize()>0){%>true<%}else{%>false<%}%></archive>

<%script type="clazz.AbstractClass" name="alfrescoGenerator_abstractClass_properties" %>
<properties>
	<%for (getSortedAttibutes()){%>
	<property name="<%getPrefixedQName()%>">
		<%if (title != null) {%>
		<title><%title%></title>
		<%}%>
		<%if (description != null) {%>
		<description><%description%></description>
		<%}%>
		<type><%getPropertyType()%></type>
		<%if (metainfo[key.equalsIgnoreCase("required")].nSize()>0){%>
		<mandatory <%if (metainfo[key.equalsIgnoreCase("enforced")].nSize()>0){%>enforced="true"<%}%>>true</mandatory>
		<%}%>
		<%if (metainfo[key.equalsIgnoreCase("multiple")].nSize()>0){%>
		<multiple>true</multiple>
		<%}%>
		<%if (initialValue != null){%>
		<default><%initialValue%></default>
		<%}%>
		<%-- index if unique since we use lucene search for unicity --%>				
		<%if (metainfo[key.equalsIgnoreCase("propertySearched")].nSize() > 0 && metainfo[key.equalsIgnoreCase("propertySearched")].nFirst().value.equalsIgnoreCase("true")
			|| unique){%>
          <index enabled="true">
          	 <atomic><%if (metainfo[key.equalsIgnoreCase("index.atomic")].nSize()>0){%>true<%}else{%>false<%}%></atomic>
          	 <stored><%if (metainfo[key.equalsIgnoreCase("index.stored")].nSize()>0){%>true<%}else{%>false<%}%></stored>
          	 <tokenised><%if (metainfo[key.equalsIgnoreCase("index.tokenised")].nSize()>0){%><%metainfo[key.equalsIgnoreCase("index.tokenised")].nFirst().value%><%}else{%>false<%}%></tokenised>
          </index>
        <%}else{%>					
		  <index enabled="false"/>
		<%}%>
		
		<%if (metainfo[key.equalsIgnoreCase("email")].nSize() > 0
		 || valueList != null
		 || metainfo[key.endsWith("-length")].nSize()>0
		 || metainfo[key.equalsIgnoreCase("regular-expression")].nSize()>0
		 || constraints.nSize() > 0){%>
		<constraints>
		
		<%if (metainfo[key.equalsIgnoreCase("email")].nSize()>0){%>
			<constraint ref="<%getFolder()%>:constraint:mail"/>
		<%}%>
		<%if (valueList) {%>
			<constraint ref="<%valueList.getListContraintPrefixedQName()%>"/>
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
    		    <parameter name="requiresMatch"><value><%if (metainfo[key.equalsIgnoreCase("regular-expression.match")].nSize()>0){%>true<%}else{%>false<%}%></value></parameter>
             </constraint>
		<%}%>
		
		<%for (constraints){%>
		     <constraint ref="<%getPrefixedQName()%>" />
		<%}%>
		
		</constraints>
		<%}%>
	</property>
	<%}%>	
	<%if (IsSearchInAssociation()){%>
	<!-- Properties to search associations -->	
	<%generate_searchFieldForAssociation()%>
	<%}%>
</properties>

<%script type="clazz.AbstractClass" name="alfrescoGenerator_abstractClass_associations" %>
<%if (getSourceAssociationEnds().nSize() > 0){%>
<associations>
<%for (getSourceAssociationEnds()){%>
	<<%eContainer().getAssociationType()%> name="<%eContainer().getPrefixedAssociationQName(current("AssociationEnd"))%>">
		<title><%getRoleOrTitleFromSource()%></title>
		<source>
			<%if (name != null && name != ""){%>
			<role><%getPrefix()%>:<%name%></role>
			<%}%>
			<mandatory><%if (isMandatory()){%>true<%}else{%>false<%}%></mandatory>
			<many><%if (isMany()){%>true<%}else{%>false<%}%></many>						
		</source>
		<target>
			<class><%getOpposite().linkedClass.getPrefixedQName()%></class>
			<%if (getOpposite().name != null && getOpposite().name != ""){%>
			<role><%getPrefix()%>:<%getOpposite().name%></role>
			<%}%>
			<mandatory><%if (getOpposite().isMandatory()){%>true<%}else{%>false<%}%></mandatory>
			<many><%if (getOpposite().isMany()){%>true<%}else{%>false<%}%></many>						
		</target>
		<%if (eContainer().getAssociationType().equalsIgnoreCase("child-association")){%>
		<duplicate><%if (metainfo[key.equalsIgnoreCase("duplicate")].nSize()>0){%>true<%}else{%>false<%}%></duplicate>
		<propagateTimestamps><%if (metainfo[key.equalsIgnoreCase("propagateTimestamps")].nSize()>0){%>true<%}else{%>false<%}%></propagateTimestamps>
		<%}%>
	</<%eContainer().getAssociationType()%>>							
<%}%>
</associations>
<%}%>
			
<%script type="clazz.AbstractClass" name="alfrescoGenerator_abstractClass" %>
			<%alfrescoGenerator_abstractClass_title()%>
			<%alfrescoGenerator_abstractClass_description()%>
			<%alfrescoGenerator_abstractClass_parent()%>
			<%alfrescoGenerator_abstractClass_archive()%>
			<%alfrescoGenerator_abstractClass_properties()%>
			<%alfrescoGenerator_abstractClass_associations()%>

<%script type="clazz.AbstractClass" name="generate_searchFieldForAssociation" %>
<!-- generate_searchFieldForAssociation -->
<%if (getSourceAssociationEnds().nSize() > 0){%>
<!-- properties to store searchable associations target -->
<%for (getSourceAssociationEnds()){%>	
<%if (eContainer().filter("Association").metainfo[key.equalsIgnoreCase("searchable")].nSize()>0){%>
<property name="<%eContainer().getPrefixedAssociationQName(current("AssociationEnd"))%>search">
	<type>d:text</type>
	<multiple>true</multiple>
	<index enabled="true">
  	 	<atomic>true</atomic>
  	 	<stored>false</stored>
  		<tokenised>true</tokenised>
	</index>
</property>
<%}%>
<%}%>
<%}%>