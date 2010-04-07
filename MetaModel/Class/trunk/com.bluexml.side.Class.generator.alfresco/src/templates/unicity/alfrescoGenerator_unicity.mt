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
<%--encoding=iso-8859-1--%>
<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
%>

<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getConfModulePath()%>/unicity.xml<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version='1.0' encoding='iso-8859-1'?>
<unicity>
	<%for (getAllClasses().nSort("name")) {%>
	<type qname="<%getPrefixedQualifiedName()%>">
	<%for (getAllSortedAttibutes()){%>
		<%--<%if (metainfo[key.equalsIgnoreCase("required")].nSize()>0){%>--%>
		<%if (unique){%>
		<attribute qname="<%getPrefixedQualifiedName()%>"/>
		<%}%>
	<%}%>
	</type>
	<%}%>
</unicity>
