<%
metamodel http://www.kerblue.org/class/1.0
import com.bluexml.side.util.generator.documentation.services.DocumentationServices
%>

<%script type="clazz.ClassPackage" name="content_chapter"%>
<!-- Break line -->
<%for (getAllClasses().sort()){%>
	<text:h text:style-name="Heading_20_1" text:outline-level="1"><%getLabel()%></text:h>
	<%if (documentation != null){%><%documentation%><%}%>
	<%content_class%>

	<%if (getDiagImgPath.length > 0){%>
		<text:h text:style-name="Heading_20_1" text:outline-level="1">Diagrams</text:h>
		<%for (getDiagImgPath) {%>
			<text:h text:style-name="Heading_20_2" text:outline-level="2"><%current%></text:h>
			<text:p text:style-name="Standard">
				<draw:frame draw:style-name="fr1" draw:name="<%current%>" text:anchor-type="paragraph" svg:width="15cm" svg:height="15cm" draw:z-index="0">
					<draw:image xlink:href="Pictures/<%current%>" xlink:type="simple" xlink:show="embed" xlink:actuate="onLoad"/>
				</draw:frame>
			</text:p>
		<%}%>
	<%}%>
<%}%>

<%script type="clazz.Clazz" name="content_class"%>
<text:h text:style-name="Heading_20_2" text:outline-level="2"><%getLabel()%> attributes</text:h>
<text:p>
	<%if (documentation != null){%><%documentation%><%}%>
</text:p>
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
			<text:p text:style-name="Table_20_Contents">
				<%getLabel()%>
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
			<text:p text:style-name="Table_20_Contents">
				<%getLabel()%>
			</text:p>
		</table:table-cell>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Contents">
				<text:a xlink:type="simple"
                    xlink:href="#1.<%getTarget().getLabel()%>|outline"><%getTarget().getLabel()%></text:a>
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