<%
metamodel http://www.kerblue.org/form/1.0
%>

<%script type="form.FormCollection" name="content_chapter"%>
<!-- Break line -->
<%for (forms.sort()){%>
	<text:h text:style-name="Heading_20_1" text:outline-level="1"><%getLabel()%></text:h>
	<%if (documentation != null){%><%documentation%><%}%>
	<%content_form%>
<%}%>

<%script type="form.FormContainer" name="content_form"%>
<text:h text:style-name="Heading_20_2" text:outline-level="2"><%getLabel()%> fields</text:h>
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
			<text:p text:style-name="Table_20_Heading">
				<%getLabel()%>
			</text:p>
		</table:table-cell>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Heading">
				<%if (documentation != null){%><%documentation%><%}%>
			</text:p>
		</table:table-cell>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Heading">
				<%if (cast("ModelChoiceField")){%>
					Link to Class <%current("ModelChoiceField").association_class.getLabel()%> with Form
					<text:a xlink:type="simple"
                    xlink:href="#1.<%current("ModelChoiceField").ref.cast("FormContainer").getLabel()%>|outline"><%current("ModelChoiceField").ref.cast("FormContainer").getLabel()%></text:a>
				<%}%>
			</text:p>
		</table:table-cell>
	</table:table-row>
<%}%>
</table:table>