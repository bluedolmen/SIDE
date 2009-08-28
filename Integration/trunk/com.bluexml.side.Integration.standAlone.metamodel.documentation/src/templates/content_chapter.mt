<%
metamodel http://www.eclipse.org/emf/2002/Ecore
import com.bluexml.side.integration.standalone.metamodel.documentation.CustomDocumentationService
%>

<%script type="ecore.EPackage" name="content_chapter"%>
<!-- Break line -->
<%for (eClassifiers().sort()){%>
	<text:h text:style-name="Heading_20_1" text:outline-level="1">
		<%name%>
	</text:h>
	<%if (null == null){%>
	<text:p text:style-name="Table_20_Heading">
		<%null%>
	</text:p>
	<%}%>
	<%content_class%>
<%}%>
<%script type="ecore.EClassifier" name="content_class"%>
<text:h text:style-name="Heading_20_2" text:outline-level="2"><%name%> attributes</text:h>
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
<%for (getSrvEAttributes()){%>
	<table:table-row>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Heading">
				 <%name%> 
			</text:p>
		</table:table-cell>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Heading">
				<%if (null != null){%><%null%><%}%>
			</text:p>
		</table:table-cell>
	</table:table-row>
<%}%>
</table:table>
<text:h text:style-name="Heading_20_2" text:outline-level="2"><%name%> associations</text:h>
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
<%for (getSrvAssociations()){%>
	<table:table-row>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Heading">
				<%name%>
			</text:p>
		</table:table-cell>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Heading">
				<text:a xlink:type="simple"
                    xlink:href="#1.<%eType().name%>|outline"><%eType().name%></text:a>
			</text:p>
		</table:table-cell>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Heading">
				<%if (null != null){%><%null%><%}%>
			</text:p>
		</table:table-cell>
	</table:table-row>
<%}%>
</table:table>

<text:h text:style-name="Heading_20_2" text:outline-level="2"><%name%> validation rules</text:h>
<%if (null==null){%>
	<text:p text:style-name="Table_20_Heading">
		<%null%>
	</text:p>
<%}%>
<%if (null != null){%>
<table:table table:style-name="Tableau2">
	<table:table-column table:style-name="Tableau1.A" table:number-columns-repeated="2" />
		<table:table-row>
			<table:table-cell table:style-name="Tableau1.D1" office:value-type="string">
				<text:p text:style-name="Table_20_Heading">Rule name</text:p>
			</table:table-cell>
			<table:table-cell table:style-name="Tableau1.D1" office:value-type="string">
				<text:p text:style-name="Table_20_Heading">Rule details</text:p>
			</table:table-cell>
		</table:table-row>
<%for (null){%>
	<table:table-row>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Heading">
				<%getKey()%>
			</text:p>
		</table:table-cell>
		<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			<text:p text:style-name="Table_20_Heading">
				<%getValue()%>
			</text:p>
		</table:table-cell>
	</table:table-row>	
<%}%>
</table:table>
<%}%>


