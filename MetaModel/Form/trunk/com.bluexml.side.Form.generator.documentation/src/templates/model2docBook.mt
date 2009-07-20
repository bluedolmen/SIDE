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
 <%--/metamodel /com.bluexml.side.Form/model/Forms.ecore--%>
<%
metamodel http://www.kerblue.org/form/1.0
import com.bluexml.side.util.generator.documentation.services.DocumentationServices
%>
<%script type="form.FormCollection" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getConfModulePath()%>/<%getModelName()%>-data-doc.xml<%}%>
<%script type="form.FormCollection" name="docGenerator" file="<%validatedFilename%>"%>
<?xml version='1.0' encoding='ISO-8859-1'?>
<book xmlns="http://docbook.org/ns/docbook" version="4.5">
	<title>Documentation for <%getModelName()%></title>
	<%for (forms.sort()) {%>
	<chapter>
		<title><%getLabel()%></title>
		<para><%if (documentation != null){%><%documentation%><%}%></para>
		<sect1>
			<title>Fields for <%getLabel()%></title>
			<para></para>
			<%for (getFields()){%>
				<sect2>
					<title><%getLabel()%></title>
					<para><%if (documentation != null){%><%documentation%><%}%></para>
				</sect2>
			<%}%>
		</sect1>
	</chapter>
	<%}%>
</book>