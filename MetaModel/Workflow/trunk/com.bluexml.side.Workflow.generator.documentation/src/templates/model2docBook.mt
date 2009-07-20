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
 <%--/metamodel /com.bluexml.side.Workflow/model/Workflow.ecore--%>
<%
metamodel http://www.kerblue.org/workflow/1.0
import com.bluexml.side.util.generator.documentation.services.DocumentationServices
%>
<%script type="workflow.Process" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getConfModulePath()%>/<%getModelName()%>-workflow-doc.xml<%}%>
<%script type="workflow.Process" name="docGenerator" file="<%validatedFilename%>"%>
<?xml version='1.0' encoding='ISO-8859-1'?>
<book xmlns="http://docbook.org/ns/docbook" version="4.5">
	<title>Documentation for <%getModelName()%></title>
	<%for (swimlane) {%>
		<chapter>
			<title>Process <%name%></title>
			<para><%if (documentation != null){%><%documentation%><%}%></para>
		</chapter>
	<%}%>
	<%for (startstate) {%>
		<%displayStateDoc%>
	<%}%>
	<%for (processstate) {%>
		<%displayStateDoc%>
	<%}%>
	<%for (tasknode) {%>
		<%displayStateDoc%>
	<%}%>
	<%for (endstate) {%>
		<%displayStateDoc%>
	<%}%>
</book>
<%script type="State" name="displayStateDoc"%>
<chapter>
	<title><%name%></title>
	<para><%if (documentation != null){%><%documentation%><%}%></para>
	<%--Variables--%>
	<%if (cast("ProcessState")){%>
		<sect1>
			<title>Variables for <%name%></title>
			<para></para>
			<%for (current("ProcessState").variable){%>
				<sect2>
					<title><%name%></title>
					<para><%if (documentation != null){%><%documentation%><%}%></para>
				</sect2>
			<%}%>
		</sect1>
	<%}%>
	<%--Attributes--%>
	<%if (cast("UserTask")){%>
		<sect1>
			<title>Attributes for <%name%></title>
			<para></para>
			<%for (current("UserTask").attributes){%>
				<sect2>
					<title><%name%></title>
					<para><%if (documentation != null){%><%documentation%><%}%></para>
				</sect2>
			<%}%>
		</sect1>
	<%}%>
	<%--Transitions--%>
	<%if (cast("TransitionTask")){%>
		<sect1>
			<title>Transitions for <%name%></title>
			<para></para>
			<%for (current("TransitionTask").transition){%>
				<sect2>
					<title><%name%></title>
					<para><%if (documentation != null){%><%documentation%><%}%></para>
				</sect2>
			<%}%>
		</sect1>
	<%}%>
	<%--Events--%>
	<sect1>
		<title>Events for <%name%></title>
		<para></para>
		<%for (event){%>
			<sect2>
				<title><%type%></title>
				<para><%if (documentation != null){%><%documentation%><%}%></para>
			</sect2>
		<%}%>
	</sect1>
</chapter>