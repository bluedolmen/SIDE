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
%>
<%script type="clazz.Association" name="getAssociationVariableName"%>
<%if (associationType.toString().equalsIgnoreCase("composition")) {%>
childAssocs<%}else{%>
assocs<%}%>
<%script type="clazz.Association" name="getAssociationType"%>
<%if (associationType.toString().equalsIgnoreCase("composition") || associationType.toString().equalsIgnoreCase("aggregation")) {%>
child-association<%}else{%>
association<%}%>
