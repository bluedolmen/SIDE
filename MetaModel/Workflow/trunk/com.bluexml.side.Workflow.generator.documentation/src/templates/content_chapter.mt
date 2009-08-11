<%
metamodel http://www.kerblue.org/workflow/1.0
%>

<%script type="workflow.Process" name="content_chapter"%>
<%for (swimlane) {%>
	<text:h text:style-name="Heading_20_1" text:outline-level="1"><%name%></text:h>
	<%if (documentation != null){%><%documentation%><%}%>
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

<%script type="State" name="displayStateDoc"%>
	<text:h text:style-name="Heading_20_1" text:outline-level="1"><%name%></text:h>
	<%if (documentation != null){%><%documentation%><%}%>
	<%if (cast("ProcessState")){%>
		<%displayStateTableHeader("Variable")%>
		<%for (current("ProcessState").variable){%>
			<%displayStateRow(name,documentation)%>
		<%}%>
		<%displayStateTableFooter%>
	<%}%>
	<%if (cast("UserTask")){%>
		<%displayStateTableHeader("Attribute")%>
		<%for (current("UserTask").attributes){%>
			<%displayStateRow(name,documentation)%>
		<%}%>
		<%displayStateTableFooter%>
	<%}%>
	<%if (cast("TransitionTask")){%>
		<%displayStateTableHeader("Transition")%>
		<%for (current("TransitionTask").transition){%>
			<%displayStateRow(name,documentation)%>
		<%}%>
		<%displayStateTableFooter%>
	<%}%>
	<%if (event.length() > 0){%>
		<%displayStateTableHeader('Event')%>
		<%for (event){%>
			<%displayStateRow(type,documentation)%>
		<%}%>
		<%displayStateTableFooter%>
	<%}%>

<%script type="State" name="displayStateTableHeader"%>
<%-- args[0] : object name --%>
<text:h text:style-name="Heading_20_2" text:outline-level="2"><%name%> <%args(0)%></text:h>
	<table:table table:style-name="Tableau1">
		<table:table-column table:style-name="Tableau1.A" table:number-columns-repeated="2" />
		<table:table-row>
			<table:table-cell table:style-name="Tableau1.D1" office:value-type="string">
				<text:p text:style-name="Table_20_Heading"><%args(0)%> Name</text:p>
			</table:table-cell>
			<table:table-cell table:style-name="Tableau1.D1" office:value-type="string">
				<text:p text:style-name="Table_20_Heading"><%args(0)%> Documentation</text:p>
			</table:table-cell>
		</table:table-row>

<%script type="State" name="displayStateTableFooter"%>
</table:table>

<%script type="EObject" name="displayStateRow"%>
<%--
	 args[0] : name
     args[1] : documentation
--%>
<table:table-row>
	<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
		<text:p text:style-name="Table_20_Heading">
			<%args(0)%>
		</text:p>
	</table:table-cell>
	<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
		<text:p text:style-name="Table_20_Heading">
			<%if (args(1) != null){%><%args(1)%><%}%>
		</text:p>
	</table:table-cell>
</table:table-row>