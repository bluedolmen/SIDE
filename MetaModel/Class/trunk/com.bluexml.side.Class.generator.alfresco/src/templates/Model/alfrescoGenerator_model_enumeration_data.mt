<%--encoding=iso-8859-1--%>
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

<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%>extras/model/<%name%>_enumeration_data.xml<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version='1.0' encoding='iso-8859-1'?>
<root_node>
<%for (getAllEnumerations().nSort("name")){%>
<%if (dynamic){%>
<!-- Enumeration <%name%> -->
<!-- Enumeration for Static tables -->

<%for (literals.nSort("name")){%>
<Class qualifiedName="<%current("Enumeration").getQualifiedName()%>" id="<%i()%>">
	<attributes>
		<attribute qualifiedName="<%current("Enumeration").getQualifiedName()%>_code">
			<%if (value != null){%>
			<value><%value%></value>
			<%}else{%>
			<value><%name%></value>
			<%}%>
		</attribute>	
		<attribute qualifiedName="<%current("Enumeration").getQualifiedName()%>_label">
			<value><%name%></value>
		</attribute>	
	</attributes>
</Class>

<Class qualifiedName="Litteral" id="<%i()%>">
	<attributes>
		<attribute qualifiedName="Litteral_code">
			<%if (name != null){%>
			<value><%name%></value>
			<%}else{%>
			<value><%value%></value>
			<%}%>
		</attribute>
		<associations>
			<association qualifiedName="Litteral_translated_LitteralTranslation" type="simple" action="add/replace">
				<target qualifiedName="LitteralTranslation"><%i()%></target>
			</association>
		</associations>
	</attributes>
</Class>
<Class qualifiedName="LitteralTranslation" id="<%i()%>">
	<attributes>
		<attribute qualifiedName="LitteralTranslation_lang">
			<value>fr</value>
		</attribute>
		<attribute qualifiedName="LitteralTranslation_value">
			<%if (value != null){%>
			<value><%value%></value>
			<%}else{%>
			<value><%name%></value>
			<%}%>
		</attribute>
	</attributes>
</Class>
<%}%> <%-- END for (literals) --%>
<Class qualifiedName="EnumerationType" id="<%i()%>">
	<attributes>
		<attribute qualifiedName="EnumerationType_name">
			<value><%getQualifiedName()%></value>
		</attribute>
		<associations>
			<association qualifiedName="EnumerationType_typeOf_Litteral" type="simple" action="add/replace">
				<%for (literals.nSort("name")){%>
					<target qualifiedName="Litteral"><%i()%></target>
				<%}%> <%-- END for (literals) --%>	
			</association>
		</associations>
	</attributes>
</Class>
<%}%> <%-- END if (dynamic) --%>
<%}%> <%-- END for (enumerationSet) --%>
</root_node>