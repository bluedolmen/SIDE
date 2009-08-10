<%
metamodel http://www.kerblue.org/class/1.0
%>

<%script type="clazz.ClassPackage" name="content_chapter"%>
<!-- Break line -->
<%for (getAllClasses().sort()){%>
	<text:h text:style-name="Heading_20_1" text:outline-level="1">
		<%getLabel()%>
	</text:h>
	<%if (documentation != null){%><%documentation%><%}%>
	<%content_class%>
<%}%>

<%script type="clazz.Clazz" name="content_class"%>
<text:h text:style-name="Heading_20_2" text:outline-level="2"><%getLabel()%> attributes</text:h>
<table:table table:style-name="Tableau1">
	<table:table-column table:style-name="Tableau1.A" table:number-columns-repeated="2" />
		<table:table-row>
			<table:table-cell table:style-name="Tableau1.D1" office:value-type="string">
				<text:p text:style-name="Table_20_Heading">Attribute Name</text:p>
			</table:table-cell>
			<table:table-cell table:style-name="Tableau1.D1" office:value-type="string">
				<text:p text:style-name="Table_20_Heading">Attribute Documentation</text:p>
			</table:table-cell>
		</table:table-row>
<%for (getAllAttributes()){%>
	<table:table-row>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Heading">
				<%getLabel()%>
			</text:p>
		</table:table-cell>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Heading">
				<%if (documentation != null){%><%documentation%><%}%>
			</text:p>
		</table:table-cell>
	</table:table-row>
<%}%>
</table:table>

<text:h text:style-name="Heading_20_2" text:outline-level="2"><%getLabel()%> associations</text:h>
<table:table table:style-name="Tableau2">
	<table:table-column table:style-name="Tableau1.A" table:number-columns-repeated="3" />
		<table:table-row>
			<table:table-cell table:style-name="Tableau1.D1" office:value-type="string">
				<text:p text:style-name="Table_20_Heading">Association Name</text:p>
			</table:table-cell>
			<table:table-cell table:style-name="Tableau1.D1" office:value-type="string">
				<text:p text:style-name="Table_20_Heading">Associated to</text:p>
			</table:table-cell>
			<table:table-cell table:style-name="Tableau1.D1" office:value-type="string">
				<text:p text:style-name="Table_20_Heading">Association Documentation</text:p>
			</table:table-cell>
		</table:table-row>
<%for (getSourceAssociations()){%>
	<table:table-row>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Heading">
				<%getLabel()%>
			</text:p>
		</table:table-cell>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Heading">
				<text:a xlink:type="simple"
                    xlink:href="#1.<%getTarget().getLabel()%>|outline"><%getTarget().getLabel()%></text:a>
			</text:p>
		</table:table-cell>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Heading">
				<%if (documentation != null){%><%documentation%><%}%>
			</text:p>
		</table:table-cell>
	</table:table-row>
<%}%>
</table:table>