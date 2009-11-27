<%
metamodel http://www.bluexml.com/rwm/webproject/1.0/
%>
<%-----------------------------------------------------------------------%>
<%--                                                                   --%>
<%-- RELATIONAL SCHEMA                                                 --%>
<%--                                                                   --%>
<%-----------------------------------------------------------------------%>
<%script type="WebProject.Schema" name="RelationalSchema" file="<%eContainer().name%>/schema.sql"%>
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