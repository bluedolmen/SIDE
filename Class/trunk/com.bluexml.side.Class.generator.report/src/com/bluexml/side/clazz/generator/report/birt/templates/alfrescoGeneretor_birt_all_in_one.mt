<%
metamodel http://www.kerblue.org/class/1.0

import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import templates.servicesTemplates.Association
import com.bluexml.side.clazz.generator.alfresco.services.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.services.ClassServices
import com.bluexml.side.clazz.generator.alfresco.services.ParameterServices
%>

<%script type="clazz.ClassPackage" name="alfrescoGeneretor_birt_allInOne" file="shared/classes/alfresco/extension/report/BIRTReport/All_in_one.rptdesign"%>
<?xml version="1.0" encoding="ISO-8859-1"?>
<report xmlns="http://www.eclipse.org/birt/2005/design" version="3.2.15" id="1">
    <property name="createdBy">BlueXML Developer Studio - Alfresco Generator</property>
    <property name="units">in</property>
    <property name="comments">BlueXML</property>
    <html-property name="description">List of All.</html-property>
    <text-property name="displayName">List of All.</text-property>
    <property name="iconFile">/templates/blank_report.gif</property>
    <data-sources>
    <%for (getAllClasses()){%>
        <oda-data-source extensionID="org.eclipse.datatools.enablement.oda.xml" name="Data Source <%name%>" id="10<%i()%>">
            <text-property name="displayName"></text-property>
            <property name="FILELIST">${url.context}/service/<%service::getQualifiedName()%>.xml</property>
        </oda-data-source>
    <%}%>
    </data-sources>
    
    <data-sets> 
<%for (getAllClasses()){%>
        <oda-data-set extensionID="org.eclipse.datatools.enablement.oda.xml.dataSet" name="Data Set <%name%>" id="6<%i()%>">
            <list-property name="computedColumns">
                <structure>
                    <property name="name">TotalSize</property>
                    <property name="dataType">float</property>
                    <property name="aggregateFunction">SUM</property>
                    <list-property name="arguments">
                        <structure>
                            <property name="name">Expression</property>
                            <expression name="value">row["Size"]</expression>
                        </structure>
                    </list-property>
                </structure>
            </list-property>
            <structure name="cachedMetaData">
                <list-property name="resultSet">
                <%for (getAllAttributes()){%>
                	<structure>
                        <property name="position"><%i()+1%></property>
                        <property name="name"><%getLabel()%></property>
                        <property name="dataType">string</property>
                    </structure>
                    <%pop()%>
	                <%i().push()%>
                <%}%>
                	<structure>
                        <property name="position"><%peek()+2%></property>
                        <property name="name">Size</property>
                        <property name="dataType">string</property>
                    </structure>
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
                	<structure>
                        <property name="position"><%peek()+2%></property>
                        <property name="name">Size</property>
                        <property name="nativeName">Size</property>
                        <property name="dataType">string</property>
                        <property name="nativeDataType">12</property>
                    </structure>
                    <structure>
                        <property name="position"><%peek()+3%></property>
                        <property name="name">TotalSize</property>
                        <property name="nativeName">TotalSize</property>
                        <property name="dataType">float</property>
                        <property name="nativeDataType">8</property>
                    </structure>
            </list-property>
            <%getAllAttributes().nLast().put("last")%>
            <property name="queryText">table0#-TNAME-#table0#:#[/records/items/item]#:#<%for (getAllAttributes()){%>{<%getLabel()%>;STRING;/<%current().getQualifiedName%>}<%}%>,{Size:STRING;/size}</property>
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
<%}%>
    </data-sets>

	<styles>
        <style name="crosstab" id="2">
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
        <style name="crosstab-cell" id="3">
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
        <simple-master-page name="Simple MasterPage" id="4">
            <property name="type">a4</property>
            <property name="topMargin">10mm</property>
            <property name="leftMargin">10mm</property>
            <property name="bottomMargin">10mm</property>
            <property name="rightMargin">10mm</property>
            <property name="headerHeight">0in</property>
            <property name="footerHeight">0in</property>
            <page-footer>
                <text id="5">
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
   		 <label id="6">
            <property name="fontFamily">"Tahoma"</property>
            <property name="fontSize">x-large</property>
            <property name="fontWeight">bold</property>
            <property name="textAlign">center</property>
            <text-property name="text">Report</text-property>
        </label>
        <table id="7">
            <property name="width">100%</property>
            <column id="8"/>
            <column id="9"/>
            <detail>
                <row id="10">
                    <cell id="11">
                        <label id="12">
                            <property name="fontFamily">"Tahoma"</property>
                            <property name="fontWeight">bold</property>
                            <text-property name="text">Number of classes: </text-property>
                        </label>
                    </cell>
                    <cell id="13">
                        <label id="14">
	                        <%for (getAllClasses()){%>
	                        	<%pop()%>
	                        	<%i().push()%>
	                        <%}%>
                            <text-property name="text"><%peek()+1%></text-property>
                        </label>
                    </cell>
                </row>
                <row id="32">
                    <cell id="33">
                        <label id="34">
                            <property name="fontFamily">"Tahoma"</property>
                            <property name="fontWeight">bold</property>
                            <text-property name="text">Last modification: </text-property>
                        </label>
                    </cell>
                    <cell id="35">
                        <label id="36">
                            <text-property name="text"><%getDate()%></text-property>
                        </label>
                    </cell>
                </row>
                <row id="37">
                    <cell id="38">
                        <label id="39">
                            <property name="fontFamily">"Tahoma"</property>
                            <property name="fontWeight">bold</property>
                            <text-property name="text">Last modification by: </text-property>
                        </label>
                    </cell>
                    <cell id="40">
                        <label id="41">
                            <text-property name="text"><%getAuthor()%></text-property>
                        </label>
                    </cell>
                </row>
            </detail>
        </table>
    	<%for (getAllClasses()){%>
        <text id="15<%i()%>">
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
        <table id="16<%i()%>">
            <property name="width">100%</property>
            <column id="17<%i()%>"/>
            <column id="18<%i()%>"/>
            <detail>
                <row id="19<%i()%>">
                    <cell id="20<%i()%>">
                        <label id="21<%i()%>">
                            <property name="fontFamily">"Tahoma"</property>
                            <property name="fontWeight">bold</property>
                            <text-property name="text">Number of attributes</text-property>
                        </label>
                    </cell>
                    <cell id="22<%i()%>">
                        <label id="23<%i()%>">
	                        <%for (getAllAttributes()){%>
	                        	<%pop()%>
	                        	<%i().push()%>
	                        <%}%>
                            <text-property name="text"><%peek()+1%></text-property>
                        </label>
                    </cell>
                </row>
            </detail>
        </table>
        <grid id="24">
            <property name="width">100%</property>
            <column id="25"/>
            <column id="26"/>
            <row id="27">
                <cell id="28">
                    <label id="29">
                        <text-property name="text">Disk Size</text-property>
                    </label>
                </cell>
                <cell id="30">
                    <data id="31">
                    	<structure name="numberFormat">
                            <property name="category">Custom</property>
                            <property name="pattern">###,##0.00 Bytes</property>
                        </structure>
                        <property name="dataSet">Data Set <%name%></property>
                        <list-property name="boundDataColumns">
                            <structure>
                                <property name="name">TotalSize</property>
                                <property name="displayName">TotalSize</property>
                                <expression name="expression">dataSetRow["TotalSize"]</expression>
                                <property name="dataType">float</property>
                            </structure>
                        </list-property>
                        <property name="resultSetColumn">TotalSize</property>
                    </data>
                </cell>
            </row>
        </grid>
        <table id="24<%i()%>">
            <property name="width">100%</property>
            <property name="dataSet">Data Set <%name%></property>
            <list-property name="boundDataColumns">
            	<%pop()%>
            	<%i().push()%>
            	<%for (getAllAttributes()){%>
            	<structure>
                    <property name="name"><%getLabel()%></property>
                    <expression name="expression">dataSetRow["<%getLabel()%>"]</expression>
                    <property name="dataType">string</property>
                </structure>
            	<%}%>
            </list-property>
            <column id="25<%i()%>"/>
            <column id="26<%i()%>"/>
            <detail>
            	<%for (getAllAttributes()){%>
                <row id="27<%peek()%><%i()%>">
                    <cell id="28<%peek()%><%i()%>">
                    	<%if (i() == 0 && current() == get("last")){%>
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
                        <property name="borderBottomColor">#000000</property>
                        <property name="borderBottomStyle">solid</property>
                        <property name="borderBottomWidth">medium</property>
                        <property name="paddingTop">1pt</property>
                        <%}else if (i() == 0){%>
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
                        <label id="29<%peek()%><%i()%>">
                            <property name="fontWeight">bold</property>
                            <text-property name="text"><%getLabel()%></text-property>
                        </label>
                    </cell>
                    <cell id="30<%peek()%><%i()%>">
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
                        <data id="31<%peek()%><%i()%>">
                            <property name="fontStyle">italic</property>
                            <property name="resultSetColumn"><%getLabel()%></property>
                        </data>
                    </cell>
                </row>
                <%}%>
            </detail>
         </table>
        <%}%>
    </body>
</report>