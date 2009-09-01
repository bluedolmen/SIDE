<%
metamodel http://www.kerblue.org/form/1.0
%>

<%script type="form.FormCollection" name="content_chapter"%>
<!-- Break line -->
<%for (forms.sort()){%>
	<text:h text:style-name="Heading_20_1" text:outline-level="1"><%getLabel()%></text:h>
	<%if (cast("FormClass")){%>
		Link to class : <%current("FormClass").real_class.getLabel()%>
	<%}%>
	<%if (cast("FormWorkflow")){%>
		Link to workflow : <%current("FormClass").ref.cast("Workflow").getLabel()%>
	<%}%>

	<%if (documentation != null){%><%documentation%><%}%>
	<%content_form%>
<%}%>

<%script type="form.FormContainer" name="content_form"%>
<text:h text:style-name="Heading_20_2" text:outline-level="2"><%getLabel()%> Fields</text:h>
<table:table table:style-name="Tableau1">
	<table:table-column table:style-name="Tableau1.A" table:number-columns-repeated="3" />
		<table:table-row>
			<table:table-cell table:style-name="Tableau1.D1" office:value-type="string">
				<text:p text:style-name="Table_20_Heading">Field Name</text:p>
			</table:table-cell>
			<table:table-cell table:style-name="Tableau1.D1" office:value-type="string">
				<text:p text:style-name="Table_20_Heading">Field Documentation</text:p>
			</table:table-cell>
			<table:table-cell table:style-name="Tableau1.D1" office:value-type="string">
				<text:p text:style-name="Table_20_Heading">Additional Informations</text:p>
			</table:table-cell>
		</table:table-row>
<%for (getFields()){%>
	<table:table-row>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Contents">
				<%getLabel()%>
			</text:p>
		</table:table-cell>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Contents">
				<%if (documentation != null){%><%documentation%><%}%>
			</text:p>
		</table:table-cell>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Contents">
				<%if (cast("Reference")){%>
					Link to Form :
					<%for (current("Reference").target) {%>
						<text:a xlink:type="simple"
                   		 xlink:href="#1.<%current("FormContainer").getLabel()%>|outline"><%current("FormContainer").getLabel()%></text:a>
					<%}%>
                    for Class <%current("Reference").real_class.getLabel()%>.
				<%}%>
			</text:p>
		</table:table-cell>
	</table:table-row>
<%}%>
</table:table>