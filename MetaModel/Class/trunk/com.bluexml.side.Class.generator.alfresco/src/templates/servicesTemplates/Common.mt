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
metamodel http://www.kerblue.org/class/1.0
import com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>
<%script type="clazz.ClassModelElement" name="getFolder" description="Get the folder to export" %>
<%if (getRootContainer().name != null && getRootContainer().name.length() > 0){%>
<%getRootContainer().name%><%}else{%>
tmp<%}%>
<%script type="clazz.ClassPackage" name="getFolder" description="Get the folder to export" %>
<%if (getRootContainer().name != null && getRootContainer().name.length() > 0){%>
<%getRootContainer().name%><%}else{%>
tmp<%}%>
<%script type="clazz.Enumeration" name="getFolder" description="Get the folder to export" %>
<%if (getRootContainer().name != null && getRootContainer().name.length() > 0){%>
<%getRootContainer().name%><%}else{%>
tmp<%}%>
<%script type="clazz.Association" name="getQualifiedName" post="trim()"%>
<%-- args(0) is the SOURCE association end --%>
<%-- Overrides the definition in the java services by using a simple deterministic mean --%>
<%args(0).linkedClass.getQualifiedName()%>_<%name%><%if (args(0).getOpposite().name != ""){%>_<%args(0).getOpposite().name%><%}%>_<%args(0).getOpposite().linkedClass.getQualifiedName()%>
<%-- 
<%getAssociationName(args(0))%>
--%>
<%script type="clazz.AssociationEnd" name="getRoleOrTitleFromSource" post="trim()"%>
<%if (eContainer().title != ""){%>
<%eContainer().title%>
<%}else{%>
<%eContainer().name%><%if (getOpposite().name != ""){%>_<%getOpposite().name%><%}%>
<%}%>

<%script type="common.NamedModelElement" name="getQualifiedName"%>
<%getFullName().replaceAll("\.","_")%>
<%script type="clazz.ClassModelElement" name="getNameSpace"%>
http://www.bluexml.com/model/content/<%getFolder()%>/1.0
<%script type="clazz.ClassPackage" name="getModulePath"%>
alfresco/module/<%getModuleIdService(name)%>
<%script type="clazz.ClassPackage" name="getConfModulePath"%>
config/<%getModulePath()%>
<%script type="common.NamedModelElement" name="getPrefixedQualifiedName"%>
<%getFolder()%>:<%getQualifiedName()%>