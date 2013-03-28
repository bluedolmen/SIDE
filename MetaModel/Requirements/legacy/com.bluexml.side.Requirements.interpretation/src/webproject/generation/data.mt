<%--
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%
metamodel http://www.bluexml.com/rwm/webproject/1.0/
%>
<%-----------------------------------------------------------------------%>
<%--                                                                   --%>
<%-- RELATIONAL SCHEMA                                                 --%>
<%--                                                                   --%>
<%-----------------------------------------------------------------------%>
<%script type="WebProject.Schema" name="RelationalSchema" file="<%eContainer().name%>/create_schema.sql"%>
-- Script generated
-- Database : reqs_prototype

-- --------------------------------------------------------
<%for (tables){%>
--
-- Table structure for table `<%name%>`
--

CREATE TABLE IF NOT EXISTS `<%name%>` (
  <%for (fields){%>
  `<%name%>` <%RelationalSchema_FieldType%> <%if (autoincrement) {%>auto_increment<%}%>,
  <%}%>
  PRIMARY KEY  (`<%primaryKey.name.sep("`,`")%>`)
);

<%}%>
<%script type="WebProject.Field" name="RelationalSchema_FieldType"%>
<%if (dataType == "integer"){%>
int(10)<%}else{%>
<%if (dataType == "string"){%>
varchar(256)<%}else{%>
<%if (dataType == "real"){%>
real<%}else{%>
<%if (dataType == "dateTime"){%>
datetime<%}else{%>
varchar(256)
<%}%><%}%><%}%><%}%>
