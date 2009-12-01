<%
metamodel http://www.oasis-open.org/docbook/xml/4.5/
%>
<%script type="DocBook.Book" name="DocBook" file="docbook_out.xml"%>
<?xml version='1.0' encoding='iso-8859-1'?>
<!DOCTYPE book PUBLIC "-//OASIS//DTD DocBook XML V4.5//EN" "./docbook-4.5/docbookx.dtd">
<book lang="fr">
  <%if (bookinfo){%>
  <bookinfo>
    <title><%bookinfo.title%></title>
  </bookinfo>
  <%}%>
  
  <%if (preface){%>
  <preface>
    <title>Preface</title>
    <%for (preface.para){%>
    <%cast("Part").DocBook_Part%>  
    <%}%>
  </preface>
  <%}%>
  
  <%for (content){%>
    <%DocBook_Part%>
  <%}%>
  
</book>

<%script type="DocBook.Part" name="DocBook_Part"%>
  <%if (eClass().name == "Chapter"){%>
    <%cast("Chapter").DocBook_Chapter%>
  <%}%>
  <%if (eClass().name == "Section"){%>
    <%cast("Section").DocBook_Section%>
  <%}%>
  <%if (eClass().name == "Paragraph"){%>
    <%cast("Paragraph").DocBook_Paragraph%>
  <%}%>
<%script type="DocBook.Chapter" name="DocBook_Chapter"%>
<chapter id="<%id%>">
  <title><%title%></title>
  <titleabbrev id="<%id%>_short"><%title%></titleabbrev>
  <%for (section){%>
    <%DocBook_Part%>
  <%}%>
</chapter>
<%script type="DocBook.Section" name="DocBook_Section"%>
<sect<%level%> <%if (id != null) {%>id="<%id%>"<%}%>>
  <title><%title%></title>
  <%if (id != null) {%><titleabbrev id="<%id%>_short"><%title%></titleabbrev><%}%>
  <%for (para){%>
    <%DocBook_Part%>
  <%}%>
  
  <%for (section){%>
    <%DocBook_Part%>
  <%}%>
</sect<%level%>>
<%script type="DocBook.Paragraph" name="DocBook_Paragraph"%>
<para>
  <%for (values){%>
    <%if (eClass().name == "TextualValue"){%>
      <%cast("TextualValue").DocBook_Paragraph_TextualValue%>
    <%}%>
    <%if (eClass().name == "ItemizedListValue"){%>
      <%cast("ItemizedListValue").DocBook_Paragraph_ItemizedListValue%>
    <%}%>
    <%if (eClass().name == "EmphasisValue"){%>
      <%cast("EmphasisValue").DocBook_Paragraph_EmphasisValue%>
    <%}%>
    <%if (eClass().name == "XRefValue"){%>
      <%cast("XRefValue").DocBook_Paragraph_XRefValue%>
    <%}%>
    <%if (eClass().name == "InformalTableValue"){%>
      <%cast("InformalTableValue").DocBook_Paragraph_InformalTableValue%>
    <%}%>
  <%}%>  
</para>
<%script type="DocBook.TextualValue" name="DocBook_Paragraph_TextualValue"%>
<%value%>
<%script type="DocBook.EmphasisValue" name="DocBook_Paragraph_EmphasisValue"%>
<emphasis <%if (role != null) {%>role="<%role%>"<%}%>><%value%></emphasis>
<%script type="DocBook.XRefValue" name="DocBook_Paragraph_XRefValue"%>
<xref linkend="<%linkend.id%>"/>
<%script type="DocBook.ItemizedListValue" name="DocBook_Paragraph_ItemizedListValue"%>
<itemizedlist mark="<%mark%>">
  <%for (items){%>
    <listitem><%DocBook_Paragraph%></listitem>
  <%}%>
</itemizedlist>
<%script type="DocBook.InformalTableValue" name="DocBook_Paragraph_InformalTableValue"%>
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