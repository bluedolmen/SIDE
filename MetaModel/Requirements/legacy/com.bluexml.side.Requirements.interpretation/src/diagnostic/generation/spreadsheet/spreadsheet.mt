<%--
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%
metamodel http://www.bluexml.com/rwm/diagnostic/1.0/
%>
<%script type="Diagnostic.Diagnostic" name="Problem" file="diagnostic.xml"%>
<?xml version="1.0" encoding="ISO-8859-1"?>
<?mso-application progid="Excel.Sheet"?>
<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet" xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet">
  <Styles>
    <Style ss:ID="heading">
	  <Interior ss:Color="#AFAFAF" ss:Pattern="Solid" />
      <Font ss:FontName="Arial" ss:Bold="1" />
      <Alignment ss:Horizontal="Center"/>
	</Style>
	<Style ss:ID="italic">
      <Font ss:FontName="Arial" ss:Italic="1" />
	</Style>
	<Style ss:ID="bold">
      <Font ss:FontName="Arial" ss:Bold="1" />
	</Style>
	<Style ss:ID="center_error">
      <Alignment ss:Horizontal="Center"/>
	  <Interior ss:Color="#FF0000" ss:Pattern="Solid" />
	</Style>
	<Style ss:ID="center_critic">
      <Alignment ss:Horizontal="Center"/>
	  <Interior ss:Color="#FF9933" ss:Pattern="Solid" />
	</Style>
	<Style ss:ID="center_warning">
      <Alignment ss:Horizontal="Center"/>
	  <Interior ss:Color="#FFCCCC" ss:Pattern="Solid" />
	</Style>
  </Styles>
  <Worksheet ss:Name="Diagnostic">
    <Table>
      <Column ss:Width="100.0"/>
      <Column ss:Width="100.0"/>
      <Column ss:Width="70.0"/>
      <Column ss:Width="300.0"/>
      <Row>
        <Cell ss:StyleID="heading">
          <Data ss:Type="String">Type</Data>
        </Cell>
        <Cell ss:StyleID="heading">
          <Data ss:Type="String">Name</Data>
        </Cell>
        <Cell ss:StyleID="heading">
          <Data ss:Type="String">Severity</Data>
        </Cell>
        <Cell ss:StyleID="heading">
          <Data ss:Type="String">Description</Data>
        </Cell>
      </Row>
      <%for (problem){%>
      <Row>
        <Cell ss:StyleID="bold">
          <Data ss:Type="String"><%elementType%></Data>
        </Cell>
        <Cell ss:StyleID="italic">
          <Data ss:Type="String"><%if (elementName != null) {%><%elementName%><%}%></Data>
        </Cell>
        <Cell ss:StyleID="center_<%severity.toString()%>">
          <Data ss:Type="String"><%severity.toString()%></Data>
        </Cell>
        <Cell ss:StyleID="italic">
          <Data ss:Type="String"><%description%></Data>
        </Cell>
      </Row>
      <%}%>
    </Table>
  </Worksheet>
</Workbook>
