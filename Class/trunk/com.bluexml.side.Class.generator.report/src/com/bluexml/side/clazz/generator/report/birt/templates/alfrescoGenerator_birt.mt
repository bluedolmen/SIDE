<%--
Copyright (C) BlueXML 2005-2008

This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 --%>
<%
metamodel http://www.kerblue.org/class/1.0

import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import templates.servicesTemplates.Association
import com.bluexml.side.clazz.generator.alfresco.services.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.services.ClassServices
%>

<%script type="clazz.Clazz" name="validatedFilename"%>
shared/classes/alfresco/extension/report/BIRTReport/<%getQualifiedName()%>.rptdesign
<%script type="clazz.Clazz" name="alfrescoGenerator_birt" file="<%validatedFilename%>"%>
<?xml version="1.0" encoding="ISO-8859-1"?>
<report xmlns="http://www.eclipse.org/birt/2005/design" version="3.2.15" id="1">
    <property name="createdBy">BlueXML Developer Studio - Alfresco Generator</property>
    <property name="units">in</property>
    <property name="comments">BlueXML</property>
    <html-property name="description">List of <%name%>.</html-property>
    <text-property name="displayName">List of <%name%>.</text-property>
    <property name="iconFile">/templates/blank_report.gif</property>
    <data-sources>
        <oda-data-source extensionID="org.eclipse.datatools.enablement.oda.xml" name="Data Source" id="7">
            <text-property name="displayName"></text-property>
            <property name="FILELIST">${url.context}/service/<%getQualifiedName()%>.xml</property>
        </oda-data-source>
    </data-sources>
    <data-sets>
        <oda-data-set extensionID="org.eclipse.datatools.enablement.oda.xml.dataSet" name="Data Set" id="8">
            <structure name="cachedMetaData">
                <list-property name="resultSet">
                <%for (getAllAttributes()){%>
                	<structure>
                        <property name="position"><%i()+1%></property>
                        <property name="name"><%getLabel()%></property>
                        <property name="dataType">string</property>
                    </structure>
                <%}%>
                </list-property>
            </structure>
            <property name="dataSource">Data Source</property>
            <list-property name="resultSet">
                <%for (getAllAttributes()){%>
                	<structure>
                        <property name="position"><%i()+1%></property>
                        <property name="name"><%getLabel()%></property>
                        <property name="nativeName"><%getLabel()%></property>
                        <property name="dataType">string</property>
                        <property name="nativeDataType">12</property>
                    </structure>
                <%}%>
            </list-property>
            <%getAllAttributes().nLast().put("last")%>
            <property name="queryText">table0#-TNAME-#table0#:#[/records/items/item]#:#<%for (getAllAttributes()){%>{<%getLabel()%>;STRING;/<%getQualifiedName()%>}<%if (current() != get("last")) {%>,<%}%><%}%></property>
            <xml-property name="designerValues"><![CDATA[<?xml version="1.0" encoding="ISO-8859-1"?>
<model:DesignValues xmlns:design="http://www.eclipse.org/datatools/connectivity/oda/design" xmlns:model="http://www.eclipse.org/birt/report/model/adapter/odaModel">
  <Version>1.0</Version>
  <design:ResultSets derivedMetaData="true">
    <design:resultSetDefinitions>
      <design:resultSetColumns>
        <%for (getAllAttributes()){%>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name><%getLabel()%></design:name>
            <design:position><%i()+1%></design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>-1</design:precision>
            <design:scale>-1</design:scale>
            <design:nullability>Unknown</design:nullability>
          </design:attributes>
          <design:usageHints>
            <design:label><%getLabel()%></design:label>
            <design:formattingHints/>
          </design:usageHints>
        </design:resultColumnDefinitions>
        <%}%>
      </design:resultSetColumns>
    </design:resultSetDefinitions>
  </design:ResultSets>
</model:DesignValues>
]]></xml-property>
            <list-property name="privateDriverProperties">
                <ex-property>
                    <name>MAX_ROW</name>
                    <value>-1</value>
                </ex-property>
                <ex-property>
                    <name>XML_FILE</name>
                </ex-property>
            </list-property>
        </oda-data-set>
    </data-sets>
    <styles>
        <style name="crosstab" id="4">
            <property name="borderBottomColor">#CCCCCC</property>
            <property name="borderBottomStyle">solid</property>
            <property name="borderBottomWidth">1pt</property>
            <property name="borderLeftColor">#CCCCCC</property>
            <property name="borderLeftStyle">solid</property>
            <property name="borderLeftWidth">1pt</property>
            <property name="borderRightColor">#CCCCCC</property>
            <property name="borderRightStyle">solid</property>
            <property name="borderRightWidth">1pt</property>
            <property name="borderTopColor">#CCCCCC</property>
            <property name="borderTopStyle">solid</property>
            <property name="borderTopWidth">1pt</property>
        </style>
        <style name="crosstab-cell" id="5">
            <property name="borderBottomColor">#CCCCCC</property>
            <property name="borderBottomStyle">solid</property>
            <property name="borderBottomWidth">1pt</property>
            <property name="borderLeftColor">#CCCCCC</property>
            <property name="borderLeftStyle">solid</property>
            <property name="borderLeftWidth">1pt</property>
            <property name="borderRightColor">#CCCCCC</property>
            <property name="borderRightStyle">solid</property>
            <property name="borderRightWidth">1pt</property>
            <property name="borderTopColor">#CCCCCC</property>
            <property name="borderTopStyle">solid</property>
            <property name="borderTopWidth">1pt</property>
        </style>
    </styles>
    <page-setup>
        <simple-master-page name="Simple MasterPage" id="2">
        	<property name="type">a4</property>
            <property name="topMargin">10mm</property>
            <property name="leftMargin">10mm</property>
            <property name="bottomMargin">10mm</property>
            <property name="rightMargin">10mm</property>
            <property name="headerHeight">0in</property>
            <property name="footerHeight">0in</property>
            <page-footer>
                <text id="3">
                    <property name="fontStyle">italic</property>
                    <property name="marginTop">10pt</property>
                    <property name="textAlign">right</property>
                    <property name="contentType">html</property>
                    <text-property name="content"><![CDATA[<value-of>new Date()</value-of>]]></text-property>
                </text>
            </page-footer>
        </simple-master-page>
    </page-setup>
    <body>
        <text id="6">
            <property name="backgroundColor">#BFE3F7</property>
            <property name="fontFamily">"Tahoma"</property>
            <property name="fontSize">larger</property>
            <property name="fontWeight">bold</property>
            <property name="marginTop">0pt</property>
            <property name="marginBottom">10pt</property>
            <property name="paddingTop">1pt</property>
            <property name="contentType">auto</property>
            <text-property name="content"><![CDATA[List of <%name%>]]></text-property>
        </text>
        <table id="9">
            <property name="width">100%</property>
            <property name="dataSet">Data Set</property>
            <list-property name="boundDataColumns">
            	<%for (getAllAttributes()){%>
            	<structure>
                    <property name="name"><%getLabel()%></property>
                    <expression name="expression">dataSetRow["<%getLabel()%>"]</expression>
                    <property name="dataType">string</property>
                </structure>
            	<%}%>
            </list-property>
            <column id="19"/>
            <column id="20"/>
            <detail>
            	<%for (getAllAttributes()){%>
                <row id="<%33+5*i()%>">
                    <cell id="<%34+5*i()%>">
                    	<%if (i() == 0){%>
                    	<property name="fontFamily">"Tahoma"</property>
                        <property name="borderLeftColor">#000000</property>
                        <property name="borderLeftStyle">solid</property>
                        <property name="borderLeftWidth">medium</property>
                        <property name="borderRightColor">#000000</property>
                        <property name="borderRightStyle">dotted</property>
                        <property name="borderRightWidth">thin</property>
                        <property name="borderTopColor">#000000</property>
                        <property name="borderTopStyle">solid</property>
                        <property name="borderTopWidth">medium</property>
                        <property name="paddingTop">1pt</property>
                    	<%}else if(current() == get("last")){%>
                    	<property name="fontFamily">"Tahoma"</property>
                        <property name="borderLeftColor">#000000</property>
                        <property name="borderLeftStyle">solid</property>
                        <property name="borderLeftWidth">medium</property>
                        <property name="borderRightColor">#000000</property>
                        <property name="borderRightStyle">dotted</property>
                        <property name="borderRightWidth">thin</property>
                        <property name="borderBottomColor">#000000</property>
                        <property name="borderBottomStyle">solid</property>
                        <property name="borderBottomWidth">medium</property>
                        <property name="paddingTop">1pt</property>
                    	<%}else{%>
                    	<property name="fontFamily">"Tahoma"</property>
                        <property name="borderLeftColor">#000000</property>
                        <property name="borderLeftStyle">solid</property>
                        <property name="borderLeftWidth">medium</property>
                        <property name="borderRightColor">#000000</property>
                        <property name="borderRightStyle">dotted</property>
                        <property name="borderRightWidth">thin</property>
                        <property name="paddingTop">1pt</property>
                    	<%}%>
                        <label id="<%35+5*i()%>">
                            <property name="fontWeight">bold</property>
                            <text-property name="text"><%getLabel()%></text-property>
                        </label>
                    </cell>
                    <cell id="<%36+5*i()%>">
                    	<%if (i() == 0){%>
                        <property name="fontFamily">"Tahoma"</property>
                        <property name="borderRightColor">#000000</property>
                        <property name="borderRightStyle">solid</property>
                        <property name="borderRightWidth">medium</property>
                        <property name="borderTopColor">#000000</property>
                        <property name="borderTopStyle">solid</property>
                        <property name="borderTopWidth">medium</property>
                        <property name="paddingTop">1pt</property>
                        <%}else if(current() == get("last")){%>
                        <property name="fontFamily">"Tahoma"</property>
                        <property name="borderRightColor">#000000</property>
                        <property name="borderRightStyle">solid</property>
                        <property name="borderRightWidth">medium</property>
                        <property name="borderBottomColor">#000000</property>
                        <property name="borderBottomStyle">solid</property>
                        <property name="borderBottomWidth">medium</property>
                        <property name="paddingTop">1pt</property>
                        <%}else{%>
                        <property name="fontFamily">"Tahoma"</property>
                        <property name="borderRightColor">#000000</property>
                        <property name="borderRightStyle">solid</property>
                        <property name="borderRightWidth">medium</property>
                        <property name="borderTopColor">#000000</property>
                        <property name="borderTopStyle">dotted</property>
                        <property name="borderTopWidth">thin</property>
                        <property name="borderBottomColor">#000000</property>
                        <property name="borderBottomStyle">dotted</property>
                        <property name="borderBottomWidth">thin</property>
                        <property name="paddingTop">1pt</property>
                        <%}%>
                        <data id="<%37+5*i()%>">
                            <property name="fontStyle">italic</property>
                            <property name="resultSetColumn"><%getLabel()%></property>
                        </data>
                    </cell>
                </row>
                <%}%>
            </detail>
        </table>
    </body>
</report>