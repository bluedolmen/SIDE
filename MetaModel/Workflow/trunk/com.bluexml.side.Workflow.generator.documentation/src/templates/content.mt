<%--encoding=iso-8859-1--%>
<%
metamodel http://www.kerblue.org/workflow/1.0
import com.bluexml.side.util.generator.documentation.services.DocumentationServices
import templates.content_chapter
%>

<%script type="workflow.Process" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getModelName()%>/doc/content.xml<%}%>
<%script type="workflow.Process" name="docGenerator" file="<%validatedFilename%>"%>
<?xml version="1.0" encoding="iso-8859-1"?>
<office:document-content xmlns:office="urn:oasis:names:tc:opendocument:xmlns:office:1.0" xmlns:style="urn:oasis:names:tc:opendocument:xmlns:style:1.0" xmlns:text="urn:oasis:names:tc:opendocument:xmlns:text:1.0" xmlns:table="urn:oasis:names:tc:opendocument:xmlns:table:1.0" xmlns:draw="urn:oasis:names:tc:opendocument:xmlns:drawing:1.0" xmlns:fo="urn:oasis:names:tc:opendocument:xmlns:xsl-fo-compatible:1.0" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:meta="urn:oasis:names:tc:opendocument:xmlns:meta:1.0" xmlns:number="urn:oasis:names:tc:opendocument:xmlns:datastyle:1.0" xmlns:svg="urn:oasis:names:tc:opendocument:xmlns:svg-compatible:1.0" xmlns:chart="urn:oasis:names:tc:opendocument:xmlns:chart:1.0" xmlns:dr3d="urn:oasis:names:tc:opendocument:xmlns:dr3d:1.0" xmlns:math="http://www.w3.org/1998/Math/MathML" xmlns:form="urn:oasis:names:tc:opendocument:xmlns:form:1.0" xmlns:script="urn:oasis:names:tc:opendocument:xmlns:script:1.0" xmlns:ooo="http://openoffice.org/2004/office" xmlns:ooow="http://openoffice.org/2004/writer" xmlns:oooc="http://openoffice.org/2004/calc" xmlns:dom="http://www.w3.org/2001/xml-events" xmlns:xforms="http://www.w3.org/2002/xforms" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:rpt="http://openoffice.org/2005/report" xmlns:of="urn:oasis:names:tc:opendocument:xmlns:of:1.2" xmlns:rdfa="http://docs.oasis-open.org/opendocument/meta/rdfa#" xmlns:field="urn:openoffice:names:experimental:ooo-ms-interop:xmlns:field:1.0" office:version="1.2">
 <office:scripts/>
 <office:font-face-decls>
  <style:font-face style:name="Tahoma1" svg:font-family="Tahoma"/>
  <style:font-face style:name="Times New Roman" svg:font-family="&apos;Times New Roman&apos;" style:font-family-generic="roman" style:font-pitch="variable"/>
  <style:font-face style:name="Arial" svg:font-family="Arial" style:font-family-generic="swiss" style:font-pitch="variable"/>
  <style:font-face style:name="Arial Unicode MS" svg:font-family="&apos;Arial Unicode MS&apos;" style:font-family-generic="system" style:font-pitch="variable"/>
  <style:font-face style:name="Tahoma" svg:font-family="Tahoma" style:font-family-generic="system" style:font-pitch="variable"/>
 </office:font-face-decls>
 <office:automatic-styles>
  <style:style style:name="LBREAK" style:family="paragraph" style:parent-style-name="Text_20_body">
   <style:paragraph-properties fo:break-after="page"/>
  </style:style>
  <style:style style:name="P1" style:family="paragraph" style:parent-style-name="Standard">
   <style:paragraph-properties fo:text-align="start" style:justify-single-word="false"/>
  </style:style>
  <style:style style:name="P2" style:family="paragraph" style:parent-style-name="Text_20_body">
   <style:paragraph-properties fo:margin-left="3.752cm" fo:margin-right="0cm" fo:text-indent="-1.524cm" style:auto-text-indent="false"/>
  </style:style>
  <style:style style:name="P3" style:family="paragraph" style:parent-style-name="Heading">
   <style:text-properties fo:font-size="15pt" style:font-size-asian="15pt" style:font-size-complex="15pt"/>
  </style:style>
  <style:style style:name="P4" style:family="paragraph" style:parent-style-name="Heading_20_4">
   <style:paragraph-properties fo:text-align="start" style:justify-single-word="false"/>
  </style:style>
  <style:style style:name="P5" style:family="paragraph" style:parent-style-name="Contents_20_1">
   <style:paragraph-properties>
    <style:tab-stops>
     <style:tab-stop style:position="16.999cm" style:type="right" style:leader-style="dotted" style:leader-text="."/>
    </style:tab-stops>
   </style:paragraph-properties>
  </style:style>
  <style:style style:name="P6" style:family="paragraph" style:parent-style-name="Contents_20_2">
   <style:paragraph-properties>
    <style:tab-stops>
     <style:tab-stop style:position="16.499cm" style:type="right" style:leader-style="dotted" style:leader-text="."/>
    </style:tab-stops>
   </style:paragraph-properties>
  </style:style>
  <style:style style:name="P7" style:family="paragraph" style:parent-style-name="Contents_20_3">
   <style:paragraph-properties>
    <style:tab-stops>
     <style:tab-stop style:position="16cm" style:type="right" style:leader-style="dotted" style:leader-text="."/>
    </style:tab-stops>
   </style:paragraph-properties>
  </style:style>
  <style:style style:name="P8" style:family="paragraph" style:parent-style-name="Contents_20_4">
   <style:paragraph-properties>
    <style:tab-stops>
     <style:tab-stop style:position="15.501cm" style:type="right" style:leader-style="dotted" style:leader-text="."/>
    </style:tab-stops>
   </style:paragraph-properties>
  </style:style>
  <style:style style:name="Sect1" style:family="section">
   <style:section-properties style:editable="false">
    <style:columns fo:column-count="1" fo:column-gap="0cm"/>
   </style:section-properties>
  </style:style>
 </office:automatic-styles>
 <office:body>
  <office:text>
   <text:sequence-decls>
    <text:sequence-decl text:display-outline-level="0" text:name="Illustration"/>
    <text:sequence-decl text:display-outline-level="0" text:name="Table"/>
    <text:sequence-decl text:display-outline-level="0" text:name="Text"/>
    <text:sequence-decl text:display-outline-level="0" text:name="Drawing"/>
   </text:sequence-decls>
   <text:p text:style-name="P3"><%getModelName()%></text:p>
   <text:p text:style-name="P1"/>
   <text:table-of-content text:style-name="Sect1" text:protected="true" text:name="Table des matires1">
    <text:table-of-content-source text:outline-level="10">
     <text:index-title-template text:style-name="Contents_20_Heading">Index</text:index-title-template>
     <text:table-of-content-entry-template text:outline-level="1" text:style-name="Contents_20_1">
      <text:index-entry-link-start text:style-name="Internet_20_link"/>
      <text:index-entry-chapter/>
      <text:index-entry-text/>
      <text:index-entry-tab-stop style:type="right" style:leader-char="."/>
      <text:index-entry-page-number/>
      <text:index-entry-link-end/>
     </text:table-of-content-entry-template>
     <text:table-of-content-entry-template text:outline-level="2" text:style-name="Contents_20_2">
      <text:index-entry-link-start text:style-name="Internet_20_link"/>
      <text:index-entry-chapter/>
      <text:index-entry-text/>
      <text:index-entry-tab-stop style:type="right" style:leader-char="."/>
      <text:index-entry-page-number/>
      <text:index-entry-link-end/>
     </text:table-of-content-entry-template>
     <text:table-of-content-entry-template text:outline-level="3" text:style-name="Contents_20_3">
      <text:index-entry-chapter/>
      <text:index-entry-text/>
      <text:index-entry-tab-stop style:type="right" style:leader-char="."/>
      <text:index-entry-page-number/>
     </text:table-of-content-entry-template>
     <text:table-of-content-entry-template text:outline-level="4" text:style-name="Contents_20_4">
      <text:index-entry-chapter/>
      <text:index-entry-text/>
      <text:index-entry-tab-stop style:type="right" style:leader-char="."/>
      <text:index-entry-page-number/>
     </text:table-of-content-entry-template>
     <text:table-of-content-entry-template text:outline-level="5" text:style-name="Contents_20_5">
      <text:index-entry-chapter/>
      <text:index-entry-text/>
      <text:index-entry-tab-stop style:type="right" style:leader-char="."/>
      <text:index-entry-page-number/>
     </text:table-of-content-entry-template>
     <text:table-of-content-entry-template text:outline-level="6" text:style-name="Contents_20_6">
      <text:index-entry-chapter/>
      <text:index-entry-text/>
      <text:index-entry-tab-stop style:type="right" style:leader-char="."/>
      <text:index-entry-page-number/>
     </text:table-of-content-entry-template>
     <text:table-of-content-entry-template text:outline-level="7" text:style-name="Contents_20_7">
      <text:index-entry-chapter/>
      <text:index-entry-text/>
      <text:index-entry-tab-stop style:type="right" style:leader-char="."/>
      <text:index-entry-page-number/>
     </text:table-of-content-entry-template>
     <text:table-of-content-entry-template text:outline-level="8" text:style-name="Contents_20_8">
      <text:index-entry-chapter/>
      <text:index-entry-text/>
      <text:index-entry-tab-stop style:type="right" style:leader-char="."/>
      <text:index-entry-page-number/>
     </text:table-of-content-entry-template>
     <text:table-of-content-entry-template text:outline-level="9" text:style-name="Contents_20_9">
      <text:index-entry-chapter/>
      <text:index-entry-text/>
      <text:index-entry-tab-stop style:type="right" style:leader-char="."/>
      <text:index-entry-page-number/>
     </text:table-of-content-entry-template>
     <text:table-of-content-entry-template text:outline-level="10" text:style-name="Contents_20_10">
      <text:index-entry-chapter/>
      <text:index-entry-text/>
      <text:index-entry-tab-stop style:type="right" style:leader-char="."/>
      <text:index-entry-page-number/>
     </text:table-of-content-entry-template>
    </text:table-of-content-source>
    <text:index-body>
     <text:index-title text:style-name="Sect1" text:name="Table des matires1_Head">
      <text:p text:style-name="Contents_20_Heading">Index</text:p>
     </text:index-title>
		<%for (swimlane) {%>
		<text:p text:style-name="P5">
			<text:a xlink:type="simple" xlink:href="#1.<%name%>|outline" text:style-name="Internet_20_link" text:visited-style-name="Internet_20_link"><%name%><text:tab/></text:a>
			<text:a xlink:type="simple" xlink:href="#1.<%name%>|outline" text:style-name="Internet_20_link" text:visited-style-name="Internet_20_link">1</text:a>
		</text:p>
		<%}%>
		<%for (startstate) {%>
		<text:p text:style-name="P5">
			<text:a xlink:type="simple" xlink:href="#1.<%name%>|outline" text:style-name="Internet_20_link" text:visited-style-name="Internet_20_link"><%name%><text:tab/></text:a>
			<text:a xlink:type="simple" xlink:href="#1.<%name%>|outline" text:style-name="Internet_20_link" text:visited-style-name="Internet_20_link">1</text:a>
		</text:p>
		<%}%>
		<%for (processstate) {%>
		<text:p text:style-name="P5">
			<text:a xlink:type="simple" xlink:href="#1.<%name%>|outline" text:style-name="Internet_20_link" text:visited-style-name="Internet_20_link"><%name%><text:tab/></text:a>
			<text:a xlink:type="simple" xlink:href="#1.<%name%>|outline" text:style-name="Internet_20_link" text:visited-style-name="Internet_20_link">1</text:a>
		</text:p>
		<%}%>
		<%for (tasknode) {%>
		<text:p text:style-name="P5">
			<text:a xlink:type="simple" xlink:href="#1.<%name%>|outline" text:style-name="Internet_20_link" text:visited-style-name="Internet_20_link"><%name%><text:tab/></text:a>
			<text:a xlink:type="simple" xlink:href="#1.<%name%>|outline" text:style-name="Internet_20_link" text:visited-style-name="Internet_20_link">1</text:a>
		</text:p>
		<%}%>
		<%for (endstate) {%>
		<text:p text:style-name="P5">
			<text:a xlink:type="simple" xlink:href="#1.<%name%>|outline" text:style-name="Internet_20_link" text:visited-style-name="Internet_20_link"><%name%><text:tab/></text:a>
			<text:a xlink:type="simple" xlink:href="#1.<%name%>|outline" text:style-name="Internet_20_link" text:visited-style-name="Internet_20_link">1</text:a>
		</text:p>
		<%}%>
			<%if (getDiagImgPath.length > 0){%>
		<text:p text:style-name="P5">
			<text:a xlink:type="simple" xlink:href="#1.<%getName()%>|outline" text:style-name="Internet_20_link" text:visited-style-name="Internet_20_link">Diagrams<text:tab/></text:a>
			<text:a xlink:type="simple" xlink:href="#1.<%getName()%>|outline" text:style-name="Internet_20_link" text:visited-style-name="Internet_20_link">1</text:a>
		</text:p>
    	<%for (getDiagImgPath) {%>
		<text:p text:style-name="P6">
			<text:a xlink:type="simple" xlink:href="#2.<%current%>|outline" text:style-name="Internet_20_link" text:visited-style-name="Internet_20_link"><%current%><text:tab/></text:a>
			<text:a xlink:type="simple" xlink:href="#2.<%current%>|outline" text:style-name="Internet_20_link" text:visited-style-name="Internet_20_link">1</text:a>
		</text:p>
        <%}%>
	<%}%>
    </text:index-body>
   </text:table-of-content>
   <text:p text:style-name="LBREAK"/>
   <%content_chapter()%>
  </office:text>
 </office:body>
</office:document-content>