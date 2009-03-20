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
shared/classes/alfresco/extension/generated/bpm/<%name%>-model.xml.temporary
<%script type="workflow.Process" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version="1.0" encoding="ISO-8859-1"?>

<model name="wfbx:workflowmodel" xmlns="http://www.alfresco.org/model/dictionary/1.0">

  <imports>
  		<!-- Import Alfresco Definitions -->
		<import uri="http://www.alfresco.org/model/dictionary/1.0" prefix="d" />
		<import uri="http://www.alfresco.org/model/content/1.0" prefix="cm" />
		<import uri="http://www.alfresco.org/model/application/1.0" prefix="app"/>
		<import uri="http://www.alfresco.org/model/wcmmodel/1.0" prefix="wcm"/>
      	<import uri="http://www.alfresco.org/model/forum/1.0" prefix="fm"/>
      	<import uri="http://www.alfresco.org/model/bpm/1.0" prefix="bpm" />
      	<import uri="http://www.alfresco.org/model/blogintegration/1.0" prefix="blg"/>
  </imports>

  <namespaces>
     <namespace uri="http://www.bluexml.com/model/workflow/1.0" prefix="wfbx"/>
  </namespaces>
  
	<constraints>
		<constraint name="wfbx:constraint:mail" type="REGEX">
	        <parameter name="expression"><value>^(|[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]+)$</value></parameter>
	        <parameter name="requiresMatch"><value>true</value></parameter>
	     </constraint>
	 </constraints>
      
  <types>

  <%for (startstate){%>
	<type name="wfbx:<%name%>">
		<parent>bpm:startTask</parent>
			<!-- Properties -->
			<properties>
				<%for (attributes) {%>
				<property name="wfbx:<%name%>">
					<%if (title != null) {%>
					<title> <%title%> </title>
					<%}%>
					<type><%getPropertyType()%></type>
				</property>
				<%}%>
			</properties>
			
			<!-- Associations -->
			<associations>
			<%for (clazz){%>
				<association name="wfbx:<%name%>">
					<target>
						<class><%getRootContainer().name%>:<%name%></class>
					</target>
				</association>
			<%}%>
			</associations>
	</type>
  <%}%>

 <%for (tasknode){%>
	<type name="wfbx:<%name%>">
		<parent>bpm:workflowTask</parent>
			<!-- Properties -->
			<properties>
				<%for (attributes){%>
				<property name="wfbx:<%name%>">
					<%if (title != null) {%>
					<title> <%title%> </title>
					<%}%>
					<type><%getPropertyType()%></type>
				</property>
				<%}%>
			</properties>
			
			<!-- Associations -->
			<associations>
			<%for (clazz){%>
				<association name="wfbx:<%name%>">
					<target>
						<class><%getRootContainer().name%>:<%name%></class>
					</target>
				</association>
			<%}%>
			</associations>
	</type>
  <%}%>
    
  </types>
   
</model>
<%script type="workflow.Attribute" name="getPropertyType"%>
<%if (typ.toString().equalsIgnoreCase("boolean")){%>d:boolean<%}%>
<%if (typ.toString().equalsIgnoreCase("byte")){%>d:int<%}%>
<%if (typ.toString().equalsIgnoreCase("char")){%>d:text<%}%>
<%if (typ.toString().equalsIgnoreCase("date")){%>d:date<%}%>
<%if (typ.toString().equalsIgnoreCase("datetime")){%>d:datetime<%}%>
<%if (typ.toString().equalsIgnoreCase("double")){%>d:double<%}%>
<%if (typ.toString().equalsIgnoreCase("float")){%>d:float<%}%>
<%if (typ.toString().equalsIgnoreCase("int")){%>d:int<%}%>
<%if (typ.toString().equalsIgnoreCase("long")){%>d:long<%}%>
<%if (typ.toString().equalsIgnoreCase("object")){%>d:content<%}%>
<%if (typ.toString().equalsIgnoreCase("short")){%>d:int<%}%>
<%if (typ.toString().equalsIgnoreCase("string")){%>d:text<%}%>
<%if (typ.toString().equalsIgnoreCase("void")){%>d:any<%}%>