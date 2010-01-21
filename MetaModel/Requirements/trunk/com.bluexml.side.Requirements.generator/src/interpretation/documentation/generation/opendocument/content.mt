<%--encoding=utf-8--%>
<%
metamodel http://www.bluexml.com/rwm/documentation/1.0/
import interpretation.documentation.generation.opendocument.content_chapter
%>

<%script type="Documentation.Book" name="content_book" file="cdc/content.xml"%>
<?xml version="1.0" encoding="UTF-8"?>
<office:document-content
	xmlns:office="urn:oasis:names:tc:opendocument:xmlns:office:1.0"
	xmlns:style="urn:oasis:names:tc:opendocument:xmlns:style:1.0"
	xmlns:text="urn:oasis:names:tc:opendocument:xmlns:text:1.0"
	xmlns:table="urn:oasis:names:tc:opendocument:xmlns:table:1.0"
	xmlns:draw="urn:oasis:names:tc:opendocument:xmlns:drawing:1.0"
	xmlns:fo="urn:oasis:names:tc:opendocument:xmlns:xsl-fo-compatible:1.0"
	xmlns:xlink="http://www.w3.org/1999/xlink"
	xmlns:dc="http://purl.org/dc/elements/1.1/"
	xmlns:meta="urn:oasis:names:tc:opendocument:xmlns:meta:1.0"
	xmlns:number="urn:oasis:names:tc:opendocument:xmlns:datastyle:1.0"
	xmlns:svg="urn:oasis:names:tc:opendocument:xmlns:svg-compatible:1.0"
	xmlns:chart="urn:oasis:names:tc:opendocument:xmlns:chart:1.0"
	xmlns:dr3d="urn:oasis:names:tc:opendocument:xmlns:dr3d:1.0"
	xmlns:math="http://www.w3.org/1998/Math/MathML"
	xmlns:form="urn:oasis:names:tc:opendocument:xmlns:form:1.0"
	xmlns:script="urn:oasis:names:tc:opendocument:xmlns:script:1.0"
	xmlns:ooo="http://openoffice.org/2004/office"
	xmlns:ooow="http://openoffice.org/2004/writer"
	xmlns:oooc="http://openoffice.org/2004/calc"
	xmlns:dom="http://www.w3.org/2001/xml-events"
	xmlns:xforms="http://www.w3.org/2002/xforms"
	xmlns:xsd="http://www.w3.org/2001/XMLSchema"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:rpt="http://openoffice.org/2005/report"
	xmlns:of="urn:oasis:names:tc:opendocument:xmlns:of:1.2"
	xmlns:rdfa="http://docs.oasis-open.org/opendocument/meta/rdfa#"
	office:version="1.2">
	<office:scripts />
	<office:font-face-decls>
		<style:font-face style:name="OpenSymbol"
			svg:font-family="OpenSymbol" />
		<style:font-face style:name="Tahoma1" svg:font-family="Tahoma" />
		<style:font-face style:name="Times New Roman"
			svg:font-family="&apos;Times New Roman&apos;"
			style:font-family-generic="roman" style:font-pitch="variable" />
		<style:font-face style:name="Arial" svg:font-family="Arial"
			style:font-family-generic="swiss" style:font-pitch="variable" />
		<style:font-face style:name="Arial Unicode MS"
			svg:font-family="&apos;Arial Unicode MS&apos;"
			style:font-family-generic="system" style:font-pitch="variable" />
		<style:font-face style:name="Tahoma" svg:font-family="Tahoma"
			style:font-family-generic="system" style:font-pitch="variable" />
	</office:font-face-decls>
	<office:automatic-styles>
		<style:style style:name="P1" style:family="paragraph"
			style:parent-style-name="Text_20_body">
			<style:paragraph-properties fo:break-before="page" />
		</style:style>
		<style:style style:name="P2" style:family="paragraph"
			style:parent-style-name="Text_20_body">
			<style:text-properties fo:font-weight="bold"
				style:font-weight-asian="bold" style:font-weight-complex="bold" />
		</style:style>
		<style:style style:name="P3" style:family="paragraph"
			style:parent-style-name="Text_20_body" style:list-style-name="L1" />
		<style:style style:name="T1" style:family="text">
			<style:text-properties fo:font-weight="bold"
				style:font-weight-asian="bold" style:font-weight-complex="bold" />
		</style:style>
		<style:style style:name="Sect1" style:family="section">
			<style:section-properties style:editable="false">
				<style:columns fo:column-count="1" fo:column-gap="0cm" />
			</style:section-properties>
		</style:style>
		<text:list-style style:name="L1">
			<text:list-level-style-bullet text:level="1"
				text:style-name="Bullet_20_Symbols" style:num-suffix="."
				text:bullet-char="°">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="1.27cm" fo:text-indent="-0.635cm"
						fo:margin-left="1.27cm" />
				</style:list-level-properties>
			</text:list-level-style-bullet>
			<text:list-level-style-bullet text:level="2"
				text:style-name="Bullet_20_Symbols" style:num-suffix="."
				text:bullet-char="°">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="1.905cm" fo:text-indent="-0.635cm"
						fo:margin-left="1.905cm" />
				</style:list-level-properties>
			</text:list-level-style-bullet>
			<text:list-level-style-bullet text:level="3"
				text:style-name="Bullet_20_Symbols" style:num-suffix="."
				text:bullet-char="°">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="2.54cm" fo:text-indent="-0.635cm"
						fo:margin-left="2.54cm" />
				</style:list-level-properties>
			</text:list-level-style-bullet>
			<text:list-level-style-bullet text:level="4"
				text:style-name="Bullet_20_Symbols" style:num-suffix="."
				text:bullet-char="°">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="3.175cm" fo:text-indent="-0.635cm"
						fo:margin-left="3.175cm" />
				</style:list-level-properties>
			</text:list-level-style-bullet>
			<text:list-level-style-bullet text:level="5"
				text:style-name="Bullet_20_Symbols" style:num-suffix="."
				text:bullet-char="°">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="3.81cm" fo:text-indent="-0.635cm"
						fo:margin-left="3.81cm" />
				</style:list-level-properties>
			</text:list-level-style-bullet>
			<text:list-level-style-bullet text:level="6"
				text:style-name="Bullet_20_Symbols" style:num-suffix="."
				text:bullet-char="°">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="4.445cm" fo:text-indent="-0.635cm"
						fo:margin-left="4.445cm" />
				</style:list-level-properties>
			</text:list-level-style-bullet>
			<text:list-level-style-bullet text:level="7"
				text:style-name="Bullet_20_Symbols" style:num-suffix="."
				text:bullet-char="°">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="5.08cm" fo:text-indent="-0.635cm"
						fo:margin-left="5.08cm" />
				</style:list-level-properties>
			</text:list-level-style-bullet>
			<text:list-level-style-bullet text:level="8"
				text:style-name="Bullet_20_Symbols" style:num-suffix="."
				text:bullet-char="°">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="5.715cm" fo:text-indent="-0.635cm"
						fo:margin-left="5.715cm" />
				</style:list-level-properties>
			</text:list-level-style-bullet>
			<text:list-level-style-bullet text:level="9"
				text:style-name="Bullet_20_Symbols" style:num-suffix="."
				text:bullet-char="°">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="6.35cm" fo:text-indent="-0.635cm"
						fo:margin-left="6.35cm" />
				</style:list-level-properties>
			</text:list-level-style-bullet>
			<text:list-level-style-bullet text:level="10"
				text:style-name="Bullet_20_Symbols" style:num-suffix="."
				text:bullet-char="°">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="6.985cm" fo:text-indent="-0.635cm"
						fo:margin-left="6.985cm" />
				</style:list-level-properties>
			</text:list-level-style-bullet>
		</text:list-style>
		<style:style style:name="Tableau1" style:family="table">
			<style:table-properties style:width="16.999cm"
				table:align="margins" />
		</style:style>
		<style:style style:name="Tableau1.A"
			style:family="table-column">
			<style:table-column-properties style:column-width="4.249cm"
				style:rel-column-width="16383*" />
		</style:style>
		<style:style style:name="Tableau1.A1"
			style:family="table-cell">
			<style:table-cell-properties fo:padding="0.097cm"
				fo:border-left="0.002cm solid #000000" fo:border-right="none"
				fo:border-top="0.002cm solid #000000"
				fo:border-bottom="0.002cm solid #000000" />
		</style:style>
		<style:style style:name="Tableau1.D1"
			style:family="table-cell">
			<style:table-cell-properties fo:padding="0.097cm"
				fo:border="0.002cm solid #000000" />
		</style:style>
		<style:style style:name="Tableau1.A2"
			style:family="table-cell">
			<style:table-cell-properties fo:padding="0.097cm"
				fo:border-left="0.002cm solid #000000" fo:border-right="none"
				fo:border-top="none" fo:border-bottom="0.002cm solid #000000" />
		</style:style>
		<style:style style:name="Tableau1.D2"
			style:family="table-cell">
			<style:table-cell-properties fo:padding="0.097cm"
				fo:border-left="0.002cm solid #000000"
				fo:border-right="0.002cm solid #000000" fo:border-top="none"
				fo:border-bottom="0.002cm solid #000000" />
		</style:style>
	</office:automatic-styles>
	<office:body>
		<office:text>
			<text:sequence-decls>
				<text:sequence-decl text:display-outline-level="0"
					text:name="Illustration" />
				<text:sequence-decl text:display-outline-level="0"
					text:name="Table" />
				<text:sequence-decl text:display-outline-level="0"
					text:name="Text" />
				<text:sequence-decl text:display-outline-level="0"
					text:name="Drawing" />
			</text:sequence-decls>
			<text:p text:style-name="Heading_20_1">
				<%title.replaceAll("\n","</text:p><text:p text:style-name=\"Text_20_body\">")%>
			</text:p>
			<text:p text:style-name="P1"/>
			<text:table-of-content text:style-name="Sect1"
				text:protected="true" text:name="Sommaire1">
				<text:table-of-content-source text:outline-level="10">
					<text:index-title-template
						text:style-name="Contents_20_Heading">
						Sommaire
					</text:index-title-template>
					<text:table-of-content-entry-template
						text:outline-level="1" text:style-name="Contents_20_1">
						<text:index-entry-chapter />
						<text:index-entry-text />
						<text:index-entry-tab-stop style:type="right"
							style:leader-char="." />
						<text:index-entry-page-number />
					</text:table-of-content-entry-template>
					<text:table-of-content-entry-template
						text:outline-level="2" text:style-name="Contents_20_2">
						<text:index-entry-chapter />
						<text:index-entry-text />
						<text:index-entry-tab-stop style:type="right"
							style:leader-char="." />
						<text:index-entry-page-number />
					</text:table-of-content-entry-template>
					<text:table-of-content-entry-template
						text:outline-level="3" text:style-name="Contents_20_3">
						<text:index-entry-chapter />
						<text:index-entry-text />
						<text:index-entry-tab-stop style:type="right"
							style:leader-char="." />
						<text:index-entry-page-number />
					</text:table-of-content-entry-template>
					<text:table-of-content-entry-template
						text:outline-level="4" text:style-name="Contents_20_4">
						<text:index-entry-chapter />
						<text:index-entry-text />
						<text:index-entry-tab-stop style:type="right"
							style:leader-char="." />
						<text:index-entry-page-number />
					</text:table-of-content-entry-template>
					<text:table-of-content-entry-template
						text:outline-level="5" text:style-name="Contents_20_5">
						<text:index-entry-chapter />
						<text:index-entry-text />
						<text:index-entry-tab-stop style:type="right"
							style:leader-char="." />
						<text:index-entry-page-number />
					</text:table-of-content-entry-template>
					<text:table-of-content-entry-template
						text:outline-level="6" text:style-name="Contents_20_6">
						<text:index-entry-chapter />
						<text:index-entry-text />
						<text:index-entry-tab-stop style:type="right"
							style:leader-char="." />
						<text:index-entry-page-number />
					</text:table-of-content-entry-template>
					<text:table-of-content-entry-template
						text:outline-level="7" text:style-name="Contents_20_7">
						<text:index-entry-chapter />
						<text:index-entry-text />
						<text:index-entry-tab-stop style:type="right"
							style:leader-char="." />
						<text:index-entry-page-number />
					</text:table-of-content-entry-template>
					<text:table-of-content-entry-template
						text:outline-level="8" text:style-name="Contents_20_8">
						<text:index-entry-chapter />
						<text:index-entry-text />
						<text:index-entry-tab-stop style:type="right"
							style:leader-char="." />
						<text:index-entry-page-number />
					</text:table-of-content-entry-template>
					<text:table-of-content-entry-template
						text:outline-level="9" text:style-name="Contents_20_9">
						<text:index-entry-chapter />
						<text:index-entry-text />
						<text:index-entry-tab-stop style:type="right"
							style:leader-char="." />
						<text:index-entry-page-number />
					</text:table-of-content-entry-template>
					<text:table-of-content-entry-template
						text:outline-level="10" text:style-name="Contents_20_10">
						<text:index-entry-chapter />
						<text:index-entry-text />
						<text:index-entry-tab-stop style:type="right"
							style:leader-char="." />
						<text:index-entry-page-number />
					</text:table-of-content-entry-template>
				</text:table-of-content-source>
				<text:index-body>
					<text:index-title text:style-name="Sect1"
						text:name="Sommaire1_Head">
						<text:p text:style-name="Contents_20_Heading">
							Sommaire
						</text:p>
					</text:index-title>
				</text:index-body>				
			</text:table-of-content>			
			<%for (content){%>
				<%content_chapter()%>
			<%}%>
			
		</office:text>
	</office:body>
</office:document-content>
