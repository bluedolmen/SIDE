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

--
-- Table structure for table `annotation`
--

CREATE TABLE IF NOT EXISTS `annotation` (
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	`elementId` VARCHAR( 30 ) NOT NULL ,
	`author` VARCHAR( 255 ) NOT NULL ,
	`annotation` TEXT NOT NULL ,
	`comment` TEXT NOT NULL ,
	`date` DATE NOT NULL
) ENGINE = MYISAM;

<%for (getRootContainer().eAllContents("Annotation")){%>
INSERT OR REPLACE INTO `reqs_prototype` (
	`id` ,
	`elementId` ,
	`author` ,
	`annotation` ,
	`comment` ,
	`date`
)
VALUES (
	'<%id%>', '<%eContainer().id%>', '<%author%>', '<%annotation%>', '<%comment%>', '<%date%>'
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