<%
metamodel http://www.kerblue.org/view/1.0
%>

<%script type="view.ViewCollection" name="content_chapter"%>
<%for (views){%>
	<text:h text:style-name="Heading_20_1" text:outline-level="1"><%name%></text:h>
		<%viewFields%>
		<%viewInnerView%>
<%}%>

<%script type="view.AbstractView" name="viewFields"%>
<text:h text:style-name="Heading_20_2" text:outline-level="2"><%name%> Fields</text:h>
<table:table table:style-name="Tableau1">
	<table:table-column table:style-name="Tableau1.A" table:number-columns-repeated="2" />
		<table:table-row>
			<table:table-cell table:style-name="Tableau1.D1" office:value-type="string">
				<text:p text:style-name="Table_20_Heading">Field Name</text:p>
			</table:table-cell>
			<table:table-cell table:style-name="Tableau1.D1" office:value-type="string">
				<text:p text:style-name="Table_20_Heading">Field Documentation</text:p>
			</table:table-cell>
		</table:table-row>
<%for (getFields()){%>
	<table:table-row>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Contents">
				<%name%>
			</text:p>
		</table:table-cell>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Contents">
				<%if (documentation != null){%><%documentation%><%}%>
			</text:p>
		</table:table-cell>
	</table:table-row>
<%}%>
</table:table>

<%script type="view.AbstractView" name="viewInnerView"%>
<%if (getInnerView().length() > 0){%>
	<%for (getInnerView()){%>
		<text:h text:style-name="Heading_20_2" text:outline-level="2"><%name%> inner view</text:h>
		<%viewInnerView%>
	<%}%>
<%}%>