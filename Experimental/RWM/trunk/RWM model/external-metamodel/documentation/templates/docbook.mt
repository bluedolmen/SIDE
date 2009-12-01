<%
metamodel http://www.bluexml.com/rwm/documentation/1.0/
%>
<%script type="Documentation.Book" name="Documentation" file="docbook_out.xml"%>
<?xml version='1.0' encoding='iso-8859-1'?>
<!DOCTYPE book PUBLIC "-//OASIS//DTD DocBook XML V4.5//EN" "./docbook-4.5/docbookx.dtd">
<book lang="fr">
  <bookinfo>
    <title><%title%></title>
  </bookinfo>
  
  <%if (preface){%>
  <preface>
    <title>Preface</title>
    <%for (preface.para){%>
    <%cast("Part").Documentation_Part%>  
    <%}%>
  </preface>
  <%}%>
  
  <%for (content){%>
    <%Documentation_Part%>
  <%}%>
  
</book>

<%script type="Documentation.Part" name="Documentation_Part"%>
  <%if (eClass().name == "Chapter"){%>
    <%cast("Chapter").Documentation_Chapter%>
  <%}%>
  <%if (eClass().name == "Section"){%>
    <%cast("Section").Documentation_Section%>
  <%}%>
  <%if (eClass().name == "Paragraph"){%>
    <%cast("Paragraph").Documentation_Paragraph%>
  <%}%>
<%script type="Documentation.Chapter" name="Documentation_Chapter"%>
<chapter id="<%id%>">
  <title><%title%></title>
  <titleabbrev id="<%id%>_short"><%title%></titleabbrev>
  <%for (section){%>
    <%Documentation_Part%>
  <%}%>
</chapter>
<%script type="Documentation.Section" name="Documentation_Section"%>
<sect<%level%> <%if (id != null) {%>id="<%id%>"<%}%>>
  <title><%title%></title>
  <%if (id != null) {%><titleabbrev id="<%id%>_short"><%title%></titleabbrev><%}%>
  <%for (para){%>
    <%Documentation_Part%>
  <%}%>
  
  <%for (section){%>
    <%Documentation_Part%>
  <%}%>
</sect<%level%>>
<%script type="Documentation.Paragraph" name="Documentation_Paragraph"%>
<para>
  <%for (values){%>
    <%if (eClass().name == "TextualValue"){%>
      <%cast("TextualValue").Documentation_Paragraph_TextualValue%>
    <%}%>
    <%if (eClass().name == "ItemizedListValue"){%>
      <%cast("ItemizedListValue").Documentation_Paragraph_ItemizedListValue%>
    <%}%>
    <%if (eClass().name == "EmphasisValue"){%>
      <%cast("EmphasisValue").Documentation_Paragraph_EmphasisValue%>
    <%}%>
    <%if (eClass().name == "XRefValue"){%>
      <%cast("XRefValue").Documentation_Paragraph_XRefValue%>
    <%}%>
    <%if (eClass().name == "InformalTableValue"){%>
      <%cast("InformalTableValue").Documentation_Paragraph_InformalTableValue%>
    <%}%>
  <%}%>  
</para>
<%script type="Documentation.TextualValue" name="Documentation_Paragraph_TextualValue"%>
<%value%>
<%script type="Documentation.EmphasisValue" name="Documentation_Paragraph_EmphasisValue"%>
<emphasis <%if (role != null) {%>role="<%role%>"<%}%>><%value%></emphasis>
<%script type="Documentation.XRefValue" name="Documentation_Paragraph_XRefValue"%>
<xref linkend="<%linkend.id%>"/>
<%script type="Documentation.ItemizedListValue" name="Documentation_Paragraph_ItemizedListValue"%>
<itemizedlist mark="<%mark%>">
  <%for (items){%>
    <listitem><%Documentation_Paragraph%></listitem>
  <%}%>
</itemizedlist>
<%script type="Documentation.InformalTableValue" name="Documentation_Paragraph_InformalTableValue"%>
<informaltable>
  <%for (tgroup){%>
  <tgroup cols="<%cols%>">
    <%for (colspec){%>
    <colspec colnum="<%colnum%>" colwidth="<%colwidth%>"/>
    <%}%>
    <%for (thead){%>
      <thead>
        <%for (rows){%>
        <row>
          <%for (entry){%>
            <entry <%if (align != null){%>align="<%align%>"<%}%>><%value%></entry>
          <%}%>
        </row>
        <%}%>
      </thead>
    <%}%>
    <%for (tbody){%>
      <tbody>
        <%for (rows){%>
        <row>
          <%for (entry){%>
            <entry <%if (align != null){%>align="<%align%>"<%}%>><%value%></entry>
          <%}%>
        </row>
        <%}%>
      </tbody>
    <%}%>
  </tgroup>
  <%}%>
</informaltable>