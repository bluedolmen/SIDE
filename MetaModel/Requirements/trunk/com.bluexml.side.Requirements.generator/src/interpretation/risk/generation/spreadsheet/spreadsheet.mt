<%
metamodel http://www.bluexml.com/rwm/risk/1.0/
%>
<%script type="Risk.Diagnostic" name="Problem" file="risk.xml"%>
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
	<Style ss:ID="risk_level_0">
	  <Interior ss:Color="#00FF00" ss:Pattern="Solid" />
      <Font ss:FontName="Arial" ss:Bold="1" />
      <Alignment ss:Horizontal="Center"/>
	</Style>
	<Style ss:ID="risk_level_1">
	  <Interior ss:Color="#19E600" ss:Pattern="Solid" />
      <Font ss:FontName="Arial" ss:Bold="1" />
      <Alignment ss:Horizontal="Center"/>
	</Style>
	<Style ss:ID="risk_level_2">
	  <Interior ss:Color="#33CC00" ss:Pattern="Solid" />
      <Font ss:FontName="Arial" ss:Bold="1" />
      <Alignment ss:Horizontal="Center"/>
	</Style>
	<Style ss:ID="risk_level_3">
	  <Interior ss:Color="#4CB300" ss:Pattern="Solid" />
      <Font ss:FontName="Arial" ss:Bold="1" />
      <Alignment ss:Horizontal="Center"/>
	</Style>
	<Style ss:ID="risk_level_4">
	  <Interior ss:Color="#669900" ss:Pattern="Solid" />
      <Font ss:FontName="Arial" ss:Bold="1" />
      <Alignment ss:Horizontal="Center"/>
	</Style>
	<Style ss:ID="risk_level_5">
	  <Interior ss:Color="#7F8000" ss:Pattern="Solid" />
      <Font ss:FontName="Arial" ss:Bold="1" />
      <Alignment ss:Horizontal="Center"/>
	</Style>
	<Style ss:ID="risk_level_6">
	  <Interior ss:Color="#996600" ss:Pattern="Solid" />
      <Font ss:FontName="Arial" ss:Bold="1" />
      <Alignment ss:Horizontal="Center"/>
	</Style>
	<Style ss:ID="risk_level_7">
	  <Interior ss:Color="#996600" ss:Pattern="Solid" />
      <Font ss:FontName="Arial" ss:Bold="1" />
      <Alignment ss:Horizontal="Center"/>
	</Style>
	<Style ss:ID="risk_level_8">
	  <Interior ss:Color="#B34C00" ss:Pattern="Solid" />
      <Font ss:FontName="Arial" ss:Bold="1" />
      <Alignment ss:Horizontal="Center"/>
	</Style>
	<Style ss:ID="risk_level_9">
	  <Interior ss:Color="#CC3300" ss:Pattern="Solid" />
      <Font ss:FontName="Arial" ss:Bold="1" />
      <Alignment ss:Horizontal="Center"/>
	</Style>
	<Style ss:ID="risk_level_10">
	  <Interior ss:Color="#E61900" ss:Pattern="Solid" />
      <Font ss:FontName="Arial" ss:Bold="1" />
      <Alignment ss:Horizontal="Center"/>
	</Style>
	<Style ss:ID="risk_level_11">
	  <Interior ss:Color="#FF0000" ss:Pattern="Solid" />
      <Font ss:FontName="Arial" ss:Bold="1" />
      <Alignment ss:Horizontal="Center"/>
	</Style>
  </Styles>
  <Worksheet ss:Name="Diagnostic">
    <Table>
      <Column ss:Width="70.0"/>
      <Column ss:Width="300.0"/>
      <Column ss:Width="70.0"/>
      <Row>
        <Cell ss:StyleID="heading">
          <Data ss:Type="String">Type</Data>
        </Cell>
        <Cell ss:StyleID="heading">
          <Data ss:Type="String">Name</Data>
        </Cell>
        <Cell ss:StyleID="heading">
          <Data ss:Type="String">Risk [0;1]</Data>
        </Cell>
      </Row>
      <%for (estimation){%>
      <Row>
        <Cell ss:StyleID="bold">
          <Data ss:Type="String"><%elementType%></Data>
        </Cell>
        <Cell ss:StyleID="italic">
          <Data ss:Type="String"><%if (elementName != null) {%><%elementName%><%}%></Data>
        </Cell>
        <Cell ss:StyleID="risk_level_<%(value*11).adapt("int")%>">
          <Data ss:Type="Double"><%value%></Data>
        </Cell>
      </Row>
      <%}%>
    </Table>
  </Worksheet>
</Workbook>