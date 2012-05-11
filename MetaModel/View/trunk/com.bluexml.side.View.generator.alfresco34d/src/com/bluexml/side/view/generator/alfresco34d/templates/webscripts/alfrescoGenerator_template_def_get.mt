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
metamodel http://www.kerblue.org/view/1.0

import com.bluexml.side.view.generator.alfresco.templates.services.common

import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>

<%script type="view.AbstractViewOf" name="validatedFilename" post="trim()" %>
<%if (eContainer() == getRootContainer()){%>webapps/alfresco/WEB-INF/classes/alfresco/webscripts/extension/com/bluexml/side/data/<%viewOf.getPrefixedQName("_")%>/<%name%>/<%name%>.get.desc.xml
<%}else if (eContainer().filter("ComposedView") != null){%>webapps/alfresco/WEB-INF/classes/alfresco/webscripts/extension/com/bluexml/side/data/<%viewOf.getPrefixedQName("_")%>/<%eContainer().filter("ComposedView").name%>/<%eContainer().filter("ComposedView").name%>.get.desc.xml
<%}%>
<%script type="view.AbstractViewOf" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version="1.0" encoding="ISO-8859-1"?>
<webscript>
  <shortname><%if (eContainer().filter("ComposedView") != null){%><%eContainer().filter("ComposedView").name%><%}else{%><%name%><%}%></shortname>
  <description><![CDATA[
Data access linked to the view called <%if (eContainer().filter("ComposedView") != null){%><%eContainer().filter("ComposedView").name%><%}else{%><%name%><%}%><br/>
<br/>
Parameters :<br/>
nodeRef : <br/>
years 	:<br/>
months 	:<br/>
days 	:<br/>
hours 	:<br/>
minutes	:<br/>
]]>
  </description>
  <url>/com/bluexml/side/view/<%if (eContainer().filter("ComposedView") != null){%><%viewOf.getPrefixedQName("_")%>/<%eContainer().filter("ComposedView").name%><%}else{%><%getRootContainer().name%>/<%name%><%}%>?nodeRef={nodeRef}&amp;years={years}&amp;months={months}&amp;days={days}&amp;hours={hours}&amp;minutes={minutes}</url>
  <format default="json">any</format>
  <authentication>guest</authentication>
  <family>SIDE</family>
</webscript>