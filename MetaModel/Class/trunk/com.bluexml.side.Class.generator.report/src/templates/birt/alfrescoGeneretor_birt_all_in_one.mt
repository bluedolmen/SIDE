<%
metamodel http://www.kerblue.org/class/1.0

import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import templates.servicesTemplates.Association
import com.bluexml.side.clazz.generator.alfresco.services.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.services.ClassServices
import com.bluexml.side.clazz.generator.alfresco.services.ParameterServices
%>

<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%>webapps/birt/Content_type_report_<%name%>.rptdesign<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGeneretor_birt_allInOne" file="<%validatedFilename%>"%>
<?xml version="1.0" encoding="ISO-8859-1"?>
<report xmlns="http://www.eclipse.org/birt/2005/design" version="3.2.17" id="1">
    <property name="createdBy">BlueXML Developer Studio - Alfresco Generator</property>
    <property name="units">in</property>
    <property name="comments">BlueXML</property>
    <html-property name="description">List of All.</html-property>
    <text-property name="displayName">List of All.</text-property>
    <property name="iconFile">/templates/blank_report.gif</property>
    <parameters>
        <scalar-parameter name="Type" id="2">
            <property name="valueType">dynamic</property>
            <property name="dataType">string</property>
            <property name="paramType">multi-value</property>
            <property name="isRequired">true</property>
            <property name="controlType">list-box</property>
            <property name="dataSetName">Data Set type</property>
            <expression name="valueExpr">dataSetRow["type"]</expression>
            <expression name="labelExpr">dataSetRow["type"]</expression>
            <property name="mustMatch">true</property>
            <property name="fixedOrder">false</property>
            <property name="defaultValue">All</property>
            <property name="distinct">true</property>
            <expression name="sortByColumn">dataSetRow["type"]</expression>
            <property name="sortDirection">asc</property>
            <structure name="format">
                <property name="category">Unformatted</property>
            </structure>
        </scalar-parameter>
        <scalar-parameter name="Header" id="3">
            <property name="valueType">static</property>
            <property name="dataType">boolean</property>
            <property name="paramType">simple</property>
			<text-property name="promptText">Display informations per type</text-property>
            <property name="controlType">check-box</property>
            <property name="defaultValue">true</property>
            <property name="distinct">true</property>
            <structure name="format"/>
        </scalar-parameter>
        <scalar-parameter name="Footer" id="4">
            <property name="valueType">static</property>
            <property name="dataType">boolean</property>
            <property name="paramType">simple</property>
			<text-property name="promptText">Display details per type</text-property>
            <property name="controlType">check-box</property>
            <property name="defaultValue">true</property>
            <property name="distinct">true</property>
            <structure name="format"/>
        </scalar-parameter>
    </parameters>
    <data-sources>
    	<oda-data-source extensionID="org.eclipse.datatools.enablement.oda.xml" name="Data Source all" id="100">
            <property name="FILELIST"><%getAlfrescoURL()%>/service/com/bluexml/side/contentType/<%name%>/all.xml?guest=true</property>
        </oda-data-source>
    <%for (getAllClasses()){%>
        <oda-data-source extensionID="org.eclipse.datatools.enablement.oda.xml" name="Data Source <%name%>" id="10<%i()+1%>">
            <text-property name="displayName"></text-property>
            <property name="FILELIST"><%getAlfrescoURL()%>/service/com/bluexml/side/contentType/<%getFolder()%>/<%getQualifiedName()%>.xml?guest=true</property>
        </oda-data-source>
    <%}%>
    </data-sources>
    
    <data-sets>
        <oda-data-set extensionID="org.eclipse.datatools.enablement.oda.xml.dataSet" name="Data Set" id="5">
            <list-property name="computedColumns">
                <structure>
                    <property name="name">totalsize</property>
                    <property name="dataType">float</property>
                    <property name="aggregateFunction">SUM</property>
                    <list-property name="arguments">
                        <structure>
                            <property name="name">Expression</property>
                            <expression name="value">row["size"]</expression>
                        </structure>
                    </list-property>
                </structure>
            </list-property>
            <list-property name="filter">
                <structure>
                    <property name="operator">in</property>
                    <expression name="expr">row["type"]</expression>
                    <simple-property-list name="value1">
                        <value>params["Type"].value</value>
                    </simple-property-list>
                </structure>
            </list-property>
            <structure name="cachedMetaData">
                <list-property name="resultSet">
                    <structure>
                        <property name="position">1</property>
                        <property name="name">type</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">2</property>
                        <property name="name">totalcount</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">3</property>
                        <property name="name">size</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">4</property>
                        <property name="name">createdDate</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">5</property>
                        <property name="name">modifyDate</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">6</property>
                        <property name="name">totalsize</property>
                        <property name="dataType">float</property>
                    </structure>
                </list-property>
            </structure>
            <property name="dataSource">Data Source all</property>
            <list-property name="resultSet">
                <structure>
                    <property name="position">1</property>
                    <property name="name">type</property>
                    <property name="nativeName">type</property>
                    <property name="dataType">string</property>
                    <property name="nativeDataType">12</property>
                </structure>
                <structure>
                    <property name="position">2</property>
                    <property name="name">totalcount</property>
                    <property name="nativeName">totalcount</property>
                    <property name="dataType">string</property>
                    <property name="nativeDataType">12</property>
                </structure>
                <structure>
                    <property name="position">3</property>
                    <property name="name">size</property>
                    <property name="nativeName">size</property>
                    <property name="dataType">string</property>
                    <property name="nativeDataType">12</property>
                </structure>
                <structure>
                    <property name="position">4</property>
                    <property name="name">createdDate</property>
                    <property name="nativeName">createdDate</property>
                    <property name="dataType">string</property>
                    <property name="nativeDataType">12</property>
                </structure>
                <structure>
                    <property name="position">5</property>
                    <property name="name">modifyDate</property>
                    <property name="nativeName">modifyDate</property>
                    <property name="dataType">string</property>
                    <property name="nativeDataType">12</property>
                </structure>
            </list-property>
            <property name="queryText">table0#-TNAME-#table0#:#[/items/item/instance]#:#{type;STRING;../type},{totalcount;STRING;../totalcount},{size;STRING;/size},{createdDate;STRING;/createdDate},{modifyDate;STRING;/modifyDate}</property>
            <xml-property name="designerValues"><![CDATA[<?xml version="1.0" encoding="UTF-8"?>
<model:DesignValues xmlns:design="http://www.eclipse.org/datatools/connectivity/oda/design" xmlns:model="http://www.eclipse.org/birt/report/model/adapter/odaModel">
  <Version>1.0</Version>
  <design:ResultSets derivedMetaData="true">
    <design:resultSetDefinitions>
      <design:resultSetColumns>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>size</design:name>
            <design:position>1</design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>-1</design:precision>
            <design:scale>-1</design:scale>
            <design:nullability>Unknown</design:nullability>
          </design:attributes>
          <design:usageHints>
            <design:label>size</design:label>
            <design:formattingHints/>
          </design:usageHints>
        </design:resultColumnDefinitions>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>createdDate</design:name>
            <design:position>2</design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>-1</design:precision>
            <design:scale>-1</design:scale>
            <design:nullability>Unknown</design:nullability>
          </design:attributes>
          <design:usageHints>
            <design:label>createdDate</design:label>
            <design:formattingHints/>
          </design:usageHints>
        </design:resultColumnDefinitions>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>modifyDate</design:name>
            <design:position>3</design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>-1</design:precision>
            <design:scale>-1</design:scale>
            <design:nullability>Unknown</design:nullability>
          </design:attributes>
          <design:usageHints>
            <design:label>modifyDate</design:label>
            <design:formattingHints/>
          </design:usageHints>
        </design:resultColumnDefinitions>
      </design:resultSetColumns>
    </design:resultSetDefinitions>
  </design:ResultSets>
</model:DesignValues>]]></xml-property>
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
        <oda-data-set extensionID="org.eclipse.datatools.enablement.oda.xml.dataSet" name="Data Set count" id="60">
            <list-property name="computedColumns">
                <structure>
                    <property name="name">total</property>
                    <property name="dataType">integer</property>
                    <property name="aggregateFunction">SUM</property>
                    <list-property name="arguments">
                        <structure>
                            <property name="name">Expression</property>
                            <expression name="value">row["totalcount"]</expression>
                        </structure>
                    </list-property>
                </structure>
            </list-property>
            <list-property name="filter">
                <structure>
                    <property name="operator">in</property>
                    <expression name="expr">row["type"]</expression>
                    <simple-property-list name="value1">
                        <value>params["Type"].value</value>
                    </simple-property-list>
                </structure>
            </list-property>
            <structure name="cachedMetaData">
                <list-property name="resultSet">
                    <structure>
                        <property name="position">1</property>
                        <property name="name">type</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">2</property>
                        <property name="name">totalcount</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">3</property>
                        <property name="name">total</property>
                        <property name="dataType">integer</property>
                    </structure>
                </list-property>
            </structure>
            <property name="dataSource">Data Source all</property>
            <list-property name="resultSet">
                <structure>
                    <property name="position">1</property>
                    <property name="name">type</property>
                    <property name="nativeName">type</property>
                    <property name="dataType">string</property>
                    <property name="nativeDataType">12</property>
                </structure>
                <structure>
                    <property name="position">2</property>
                    <property name="name">totalcount</property>
                    <property name="nativeName">totalcount</property>
                    <property name="dataType">string</property>
                    <property name="nativeDataType">12</property>
                </structure>
            </list-property>
            <property name="queryText">table0#-TNAME-#table0#:#[/items/item]#:#{type;STRING;/type},{totalcount;STRING;/totalcount}</property>
            <xml-property name="designerValues"><![CDATA[<?xml version="1.0" encoding="UTF-8"?>
<model:DesignValues xmlns:design="http://www.eclipse.org/datatools/connectivity/oda/design" xmlns:model="http://www.eclipse.org/birt/report/model/adapter/odaModel">
  <Version>1.0</Version>
  <design:ResultSets derivedMetaData="true">
    <design:resultSetDefinitions>
      <design:resultSetColumns>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>type</design:name>
            <design:position>1</design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>-1</design:precision>
            <design:scale>-1</design:scale>
            <design:nullability>Unknown</design:nullability>
          </design:attributes>
          <design:usageHints>
            <design:label>type</design:label>
            <design:formattingHints/>
          </design:usageHints>
        </design:resultColumnDefinitions>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>totalcount</design:name>
            <design:position>2</design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>-1</design:precision>
            <design:scale>-1</design:scale>
            <design:nullability>Unknown</design:nullability>
          </design:attributes>
          <design:usageHints>
            <design:label>totalcount</design:label>
            <design:formattingHints/>
          </design:usageHints>
        </design:resultColumnDefinitions>
      </design:resultSetColumns>
    </design:resultSetDefinitions>
  </design:ResultSets>
</model:DesignValues>]]></xml-property>
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
        <oda-data-set extensionID="org.eclipse.datatools.enablement.oda.xml.dataSet" name="Data Set type" id="59">
            <structure name="cachedMetaData">
                <list-property name="resultSet">
                    <structure>
                        <property name="position">1</property>
                        <property name="name">type</property>
                        <property name="dataType">string</property>
                    </structure>
                </list-property>
            </structure>
            <property name="dataSource">Data Source all</property>
            <list-property name="resultSet">
                <structure>
                    <property name="position">1</property>
                    <property name="name">type</property>
                    <property name="nativeName">type</property>
                    <property name="dataType">string</property>
                    <property name="nativeDataType">12</property>
                </structure>
            </list-property>
            <property name="queryText">table0#-TNAME-#table0#:#[/items/item]#:#{type;STRING;/type}</property>
            <xml-property name="designerValues"><![CDATA[<?xml version="1.0" encoding="UTF-8"?>
<model:DesignValues xmlns:design="http://www.eclipse.org/datatools/connectivity/oda/design" xmlns:model="http://www.eclipse.org/birt/report/model/adapter/odaModel">
  <Version>1.0</Version>
  <design:ResultSets derivedMetaData="true">
    <design:resultSetDefinitions>
      <design:resultSetColumns>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>type</design:name>
            <design:position>1</design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>-1</design:precision>
            <design:scale>-1</design:scale>
            <design:nullability>Unknown</design:nullability>
          </design:attributes>
          <design:usageHints>
            <design:label>type</design:label>
            <design:formattingHints/>
          </design:usageHints>
        </design:resultColumnDefinitions>
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
        <oda-data-set extensionID="org.eclipse.datatools.enablement.oda.xml.dataSet" name="Data Set All" id="58">
            <list-property name="computedColumns">
                <structure>
                    <property name="name">totalsize</property>
                    <property name="dataType">float</property>
                    <property name="aggregateFunction">SUM</property>
                    <list-property name="arguments">
                        <structure>
                            <property name="name">Expression</property>
                            <expression name="value">row["size"]</expression>
                        </structure>
                    </list-property>
                </structure>
            </list-property>
            <structure name="cachedMetaData">
                <list-property name="resultSet">
                    <structure>
                        <property name="position">1</property>
                        <property name="name">totalcount</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">2</property>
                        <property name="name">size</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">3</property>
                        <property name="name">createdDate</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">4</property>
                        <property name="name">modifyDate</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">5</property>
                        <property name="name">totalsize</property>
                        <property name="dataType">float</property>
                    </structure>
                </list-property>
            </structure>
            <property name="dataSource">Data Source all</property>
            <list-property name="resultSet">
                <structure>
                    <property name="position">1</property>
                    <property name="name">totalcount</property>
                    <property name="nativeName">totalcount</property>
                    <property name="dataType">string</property>
                    <property name="nativeDataType">12</property>
                </structure>
                <structure>
                    <property name="position">2</property>
                    <property name="name">size</property>
                    <property name="nativeName">size</property>
                    <property name="dataType">string</property>
                    <property name="nativeDataType">12</property>
                </structure>
                <structure>
                    <property name="position">3</property>
                    <property name="name">createdDate</property>
                    <property name="nativeName">createdDate</property>
                    <property name="dataType">string</property>
                    <property name="nativeDataType">12</property>
                </structure>
                <structure>
                    <property name="position">4</property>
                    <property name="name">modifyDate</property>
                    <property name="nativeName">modifyDate</property>
                    <property name="dataType">string</property>
                    <property name="nativeDataType">12</property>
                </structure>
            </list-property>
            <property name="queryText">table0#-TNAME-#table0#:#[/items/item/instance]#:#{totalcount;STRING;../totalcount},{size;STRING;/size},{createdDate;STRING;/createdDate},{modifyDate;STRING;/modifyDate}</property>
            <xml-property name="designerValues"><![CDATA[<?xml version="1.0" encoding="UTF-8"?>
<model:DesignValues xmlns:design="http://www.eclipse.org/datatools/connectivity/oda/design" xmlns:model="http://www.eclipse.org/birt/report/model/adapter/odaModel">
  <Version>1.0</Version>
  <design:ResultSets derivedMetaData="true">
    <design:resultSetDefinitions>
      <design:resultSetColumns>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>size</design:name>
            <design:position>1</design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>-1</design:precision>
            <design:scale>-1</design:scale>
            <design:nullability>Unknown</design:nullability>
          </design:attributes>
          <design:usageHints>
            <design:label>size</design:label>
            <design:formattingHints/>
          </design:usageHints>
        </design:resultColumnDefinitions>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>createdDate</design:name>
            <design:position>2</design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>-1</design:precision>
            <design:scale>-1</design:scale>
            <design:nullability>Unknown</design:nullability>
          </design:attributes>
          <design:usageHints>
            <design:label>createdDate</design:label>
            <design:formattingHints/>
          </design:usageHints>
        </design:resultColumnDefinitions>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>modifyDate</design:name>
            <design:position>3</design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>-1</design:precision>
            <design:scale>-1</design:scale>
            <design:nullability>Unknown</design:nullability>
          </design:attributes>
          <design:usageHints>
            <design:label>modifyDate</design:label>
            <design:formattingHints/>
          </design:usageHints>
        </design:resultColumnDefinitions>
      </design:resultSetColumns>
    </design:resultSetDefinitions>
  </design:ResultSets>
</model:DesignValues>]]></xml-property>
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
        <oda-data-set extensionID="org.eclipse.datatools.enablement.oda.xml.dataSet" name="Data Set count All" id="57">
            <list-property name="computedColumns">
                <structure>
                    <property name="name">total</property>
                    <property name="dataType">integer</property>
                    <property name="aggregateFunction">SUM</property>
                    <list-property name="arguments">
                        <structure>
                            <property name="name">Expression</property>
                            <expression name="value">row["totalcount"]</expression>
                        </structure>
                    </list-property>
                </structure>
            </list-property>
            <structure name="cachedMetaData">
                <list-property name="resultSet">
                    <structure>
                        <property name="position">1</property>
                        <property name="name">type</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">2</property>
                        <property name="name">totalcount</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">3</property>
                        <property name="name">total</property>
                        <property name="dataType">integer</property>
                    </structure>
                </list-property>
            </structure>
            <property name="dataSource">Data Source all</property>
            <list-property name="resultSet">
                <structure>
                    <property name="position">1</property>
                    <property name="name">type</property>
                    <property name="nativeName">type</property>
                    <property name="dataType">string</property>
                    <property name="nativeDataType">12</property>
                </structure>
                <structure>
                    <property name="position">2</property>
                    <property name="name">totalcount</property>
                    <property name="nativeName">totalcount</property>
                    <property name="dataType">string</property>
                    <property name="nativeDataType">12</property>
                </structure>
            </list-property>
            <property name="queryText">table0#-TNAME-#table0#:#[/items/item]#:#{type;STRING;/type},{totalcount;STRING;/totalcount}</property>
            <xml-property name="designerValues"><![CDATA[<?xml version="1.0" encoding="UTF-8"?>
<model:DesignValues xmlns:design="http://www.eclipse.org/datatools/connectivity/oda/design" xmlns:model="http://www.eclipse.org/birt/report/model/adapter/odaModel">
  <Version>1.0</Version>
  <design:ResultSets derivedMetaData="true">
    <design:resultSetDefinitions>
      <design:resultSetColumns>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>type</design:name>
            <design:position>1</design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>-1</design:precision>
            <design:scale>-1</design:scale>
            <design:nullability>Unknown</design:nullability>
          </design:attributes>
          <design:usageHints>
            <design:label>type</design:label>
            <design:formattingHints/>
          </design:usageHints>
        </design:resultColumnDefinitions>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>totalcount</design:name>
            <design:position>2</design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>-1</design:precision>
            <design:scale>-1</design:scale>
            <design:nullability>Unknown</design:nullability>
          </design:attributes>
          <design:usageHints>
            <design:label>totalcount</design:label>
            <design:formattingHints/>
          </design:usageHints>
        </design:resultColumnDefinitions>
      </design:resultSetColumns>
    </design:resultSetDefinitions>
  </design:ResultSets>
</model:DesignValues>]]></xml-property>
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
<%for (getAllClasses()){%>
        <oda-data-set extensionID="org.eclipse.datatools.enablement.oda.xml.dataSet" name="Data Set <%name%>" id="7<%i()%>">
            <list-property name="computedColumns">
                <structure>
                    <property name="name">TotalSize</property>
                    <property name="dataType">float</property>
                    <property name="aggregateFunction">SUM</property>
                    <list-property name="arguments">
                        <structure>
                            <property name="name">Expression</property>
                            <expression name="value">row["size"]</expression>
                        </structure>
                    </list-property>
                </structure>
            </list-property>
            <structure name="cachedMetaData">
                <list-property name="resultSet">
					<structure>
                        <property name="position">1</property>
                        <property name="name">size</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">2</property>
                        <property name="name">createdDate</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">3</property>
                        <property name="name">modifyDate</property>
                        <property name="dataType">string</property>
                    </structure>
					<structure>
                        <property name="position">4</property>
                        <property name="name">totalCount</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">5</property>
                        <property name="name">TotalSize</property>
                        <property name="dataType">float</property>
                    </structure>
                    <%for (getAllAttributes()){%>
                	<structure>
                        <property name="position"><%i()+6%></property>
                        <property name="name"><%getQualifiedName()%></property>
                        <property name="dataType">string</property>
                    </structure>
					<%}%>
                </list-property>
            </structure>
            <property name="dataSource">Data Source <%name%></property>
            <list-property name="resultSet">
					<structure>
						<property name="position">1</property>
						<property name="name">size</property>
						<property name="nativeName">size</property>
						<property name="dataType">string</property>
						<property name="nativeDataType">12</property>
					</structure>
					<structure>
						<property name="position">2</property>
						<property name="name">createdDate</property>
						<property name="nativeName">createdDate</property>
						<property name="dataType">string</property>
						<property name="nativeDataType">12</property>
					</structure>
					<structure>
						<property name="position">3</property>
						<property name="name">modifyDate</property>
						<property name="nativeName">modifyDate</property>
						<property name="dataType">string</property>
						<property name="nativeDataType">12</property>
					</structure>
					<structure>
						<property name="position">4</property>
						<property name="name">totalCount</property>
						<property name="nativeName">totalCount</property>
						<property name="dataType">string</property>
						<property name="nativeDataType">12</property>
					</structure>
					<%for (getAllAttributes()){%>
                	<structure>
                        <property name="position"><%i()+5%></property>
                        <property name="name"><%getQualifiedName()%></property>
                        <property name="nativeName"><%getQualifiedName()%></property>
                        <property name="dataType">string</property>
                        <property name="nativeDataType">12</property>
                    </structure>
					<%}%>
            </list-property>
            <%getAllAttributes().nLast().put("last")%>
            <property name="queryText">
table0#-TNAME-#table0#:#[/records/items/item]#:#{size;STRING;/size},{createdDate;STRING;/createdDate},{modifyDate;STRING;/modifyDate},<%for (getAllAttributes()){%>{<%getQualifiedName()%>;STRING;/<%getQualifiedName%>},<%}%>{totalCount;STRING;../../totalCount}</property>
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
        <style name="crosstab" id="8">
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
        <style name="crosstab-cell" id="9">
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
        <simple-master-page name="Simple MasterPage" id="10">
            <property name="type">a4</property>
            <property name="topMargin">10mm</property>
            <property name="leftMargin">10mm</property>
            <property name="bottomMargin">10mm</property>
            <property name="rightMargin">10mm</property>
            <property name="headerHeight">0in</property>
            <property name="footerHeight">0in</property>
            <page-footer>
                <text id="11">
                    <property name="fontStyle">italic</property>
                    <property name="marginTop">10pt</property>
                    <property name="textAlign">right</property>
                    <property name="contentType">html</property>
                    <text-property name="content">Generated by S-IDE on <![CDATA[<value-of>new Date()</value-of>]]></text-property>
                </text>
            </page-footer>
        </simple-master-page>
    </page-setup>
    
    <body>
   		 <label id="12">
            <property name="fontFamily">"Tahoma"</property>
            <property name="fontSize">x-large</property>
            <property name="fontWeight">bold</property>
            <property name="textAlign">center</property>
            <text-property name="text">Alfresco content type list</text-property>
        </label>
        <grid id="13">
			<property name="width">100%</property>
            <property name="dataSet">Data Set count</property>
            <list-property name="boundDataColumns">
                <structure>
                    <property name="name">total</property>
                    <property name="displayName">total</property>
                    <expression name="expression">dataSetRow["total"]</expression>
                    <property name="dataType">integer</property>
                </structure>
            </list-property>
            <property name="width">100%</property>
            <column id="14"/>
            <column id="15"/>
            	 <row id="16">
                    <cell id="17">
                        <label id="18">
                            <property name="fontFamily">"Tahoma"</property>
                            <property name="fontWeight">bold</property>
                            <text-property name="text">Report created by: </text-property>
                        </label>
                    </cell>
                    <cell id="19">
                        <label id="20">
							<property name="fontFamily">"Tahoma"</property>
							<property name="fontStyle">italic</property>
                            <text-property name="text"><%getAuthor()%></text-property>
                        </label>
                    </cell>
                </row>
                <row id="21">
                    <cell id="22">
                        <label id="23">
                            <property name="fontFamily">"Tahoma"</property>
                            <property name="fontWeight">bold</property>
                            <text-property name="text">Report created on: </text-property>
                        </label>
                    </cell>
                    <cell id="24">
                        <label id="25">
							<property name="fontFamily">"Tahoma"</property>
							<property name="fontStyle">italic</property>
                            <text-property name="text"><%getDate()%></text-property>
                        </label>
                    </cell>
                </row>
                <row id="26">
                    <cell id="27">
                        <label id="28">
                            <property name="fontFamily">"Tahoma"</property>
                            <property name="fontWeight">bold</property>
                            <text-property name="text">Number of types: </text-property>
                        </label>
                    </cell>
                    <cell id="29">
                        <label id="30">
	                        <%for (getAllClasses()){%>
	                        	<%i().push()%>
	                        <%}%>
							<property name="fontFamily">"Tahoma"</property>
							<property name="fontStyle">italic</property>
                            <text-property name="text"><%peek()+1%></text-property>
                        </label>
                    </cell>
                </row>
        </grid>
		<!--grid id="31">
            <property name="width">100%</property>
            <list-property name="visibility">
                <structure>
                    <property name="format">all</property>
                    <expression name="valueExpr">var retour = false;
if(params["Header"].value){
	for(i = 0; i&lt;params["Type"].value.length ; i++){
		if(params["Type"].value[i] == "All"){
			retour = true;
		}
	}
}
retour;</expression>
                </structure>
            </list-property>
            <column id="32"/>
            <column id="33"/>
            <row id="34">
                <cell id="35">
                    <label id="36">
                        <property name="fontFamily">"Tahoma"</property>
                        <property name="fontWeight">bold</property>
                        <text-property name="text">Number of files: </text-property>
                    </label>
                </cell>
                <cell id="37">
                    <data id="38">
                        <property name="fontFamily">"Tahoma"</property>
                        <property name="fontStyle">italic</property>
                        <property name="dataSet">Data Set count</property>
                        <list-property name="boundDataColumns">
                            <structure>
                                <property name="name">total</property>
                                <property name="displayName">total</property>
                                <expression name="expression">dataSetRow["total"]</expression>
                                <property name="dataType">integer</property>
                            </structure>
                        </list-property>
                        <property name="resultSetColumn">total</property>
                    </data>
                </cell>
            </row>
            <row id="39">
                <cell id="40">
                    <label id="41">
                        <property name="fontFamily">"Tahoma"</property>
                        <property name="fontWeight">bold</property>
                        <text-property name="text">Total Size of files: </text-property>
                    </label>
                </cell>
                <cell id="42">
                    <data id="43">
                        <property name="fontFamily">"Tahoma"</property>
                        <property name="fontStyle">italic</property>
                        <structure name="numberFormat">
                            <property name="category">Custom</property>
                            <property name="pattern">###,##0.00 Bytes</property>
                        </structure>
                        <property name="dataSet">Data Set</property>
                        <list-property name="boundDataColumns">
                            <structure>
                                <property name="name">TotalSize</property>
                                <property name="displayName">TotalSize</property>
                                <expression name="expression">dataSetRow["totalsize"]</expression>
                                <property name="dataType">float</property>
                            </structure>
                        </list-property>
                        <property name="resultSetColumn">TotalSize</property>
                    </data>
                </cell>
            </row>
        </grid-->
        <grid id="44">
            <property name="width">100%</property>
            <list-property name="visibility">
                <structure>
                    <property name="format">all</property>
                    <expression name="valueExpr">var retour = true;
if(params["Header"].value){
	for(i = 0; i&lt;params["Type"].value.length ; i++){
		if(params["Type"].value[i] == "All"){
			retour = false;
		}
	}
}
retour;</expression>
                </structure>
            </list-property>
            <column id="45"/>
            <column id="46"/>
            <row id="47">
                <cell id="48">
                    <label id="49">
                        <property name="fontFamily">"Tahoma"</property>
                        <property name="fontWeight">bold</property>
                        <text-property name="text">Number of files: </text-property>
                    </label>
                </cell>
                <cell id="50">
                    <data id="51">
                        <property name="fontFamily">"Tahoma"</property>
                        <property name="fontStyle">italic</property>
                        <property name="dataSet">Data Set count All</property>
                        <list-property name="boundDataColumns">
                            <structure>
                                <property name="name">total</property>
                                <property name="displayName">total</property>
                                <expression name="expression">dataSetRow["total"]</expression>
                                <property name="dataType">integer</property>
                            </structure>
                        </list-property>
                        <property name="resultSetColumn">total</property>
                    </data>
                </cell>
            </row>
            <row id="52">
                <cell id="53">
                    <label id="54">
                        <property name="fontFamily">"Tahoma"</property>
                        <property name="fontWeight">bold</property>
                        <text-property name="text">Total Size of files: </text-property>
                    </label>
                </cell>
                <cell id="55">
                    <data id="56">
                        <property name="fontFamily">"Tahoma"</property>
                        <property name="fontStyle">italic</property>
                        <property name="dataSet">Data Set All</property>
                        <list-property name="boundDataColumns">
                            <structure>
                                <property name="name">totalsize</property>
                                <property name="displayName">TotalSize</property>
                                <expression name="expression">dataSetRow["totalsize"]</expression>
                                <property name="dataType">float</property>
                            </structure>
                        </list-property>
                        <property name="resultSetColumn">totalsize</property>
                    </data>
                </cell>
            </row>
        </grid>
    	<%for (getAllClasses()){%>
        <text id="41<%i()%>">
			<list-property name="visibility">
                <structure>
                    <property name="format">all</property>
                    <expression name="valueExpr">
var retour = true;
if(params["Header"].value){
	for(i = 0; i&lt;params["Type"].value.length ; i++){
		if(params["Type"].value[i] == "<%getLabel()%>" || params["Type"].value[i] == "All"){
			retour = false;
		}
	}
}
retour;</expression>
                </structure>
            </list-property>
            <property name="backgroundColor">#BFE3F7</property>
            <property name="fontFamily">"Tahoma"</property>
            <property name="fontSize">larger</property>
            <property name="fontWeight">bold</property>
            <property name="marginTop">0pt</property>
            <property name="marginBottom">10pt</property>
            <property name="paddingTop">1pt</property>
            <property name="contentType">auto</property>
            <text-property name="content"><![CDATA[<%name%>]]></text-property>
        </text>
        <grid id="42<%i()%>">
            <property name="width">100%</property>
			<list-property name="visibility">
                <structure>
                    <property name="format">all</property>
                    <expression name="valueExpr">var retour = true;
if(params["Header"].value){
	for(i = 0; i&lt;params["Type"].value.length ; i++){
		if(params["Type"].value[i] == "<%getLabel()%>" || params["Type"].value[i] == "All"){
			retour = false;
		}
	}
}
retour;</expression>
                </structure>
            </list-property>
            <column id="43<%i()%>"/>
            <column id="44<%i()%>"/>
                <row id="45<%i()%>">
                    <cell id="46<%i()%>">
                        <label id="47<%i()%>">
                            <property name="fontFamily">"Tahoma"</property>
                            <property name="fontWeight">bold</property>
                            <text-property name="text">Number of attributes</text-property>
                        </label>
                    </cell>
                    <cell id="48<%i()%>">
                        <label id="49<%i()%>">
	                        <%for (getAllAttributes()){%>
	                        	<%i().push()%>
	                        <%}%>
							<property name="fontFamily">"Tahoma"</property>
							<property name="fontStyle">italic</property>
                            <text-property name="text"><%peek()+1%></text-property>
                        </label>
                    </cell>
                </row>
            <row id="53<%i()%>">
                <cell id="54<%i()%>">
                    <label id="55<%i()%>">
						<property name="fontFamily">"Tahoma"</property>
                        <property name="fontWeight">bold</property>
                        <text-property name="text">Disk Size</text-property>
                    </label>
                </cell>
                <cell id="56<%i()%>">
                    <data id="57<%i()%>">
						<property name="fontFamily">"Tahoma"</property>
                        <property name="fontStyle">italic</property>
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
            <row id="61<%i()%>">
                <cell id="62<%i()%>">
                    <label id="63<%i()%>">
						<property name="fontFamily">"Tahoma"</property>
						<property name="fontWeight">bold</property>
                        <text-property name="text">Number of Files</text-property>
                    </label>
                </cell>
                <cell id="64<%i()%>">
                    <data id="65<%i()%>">
						<property name="fontFamily">"Tahoma"</property>
						<property name="fontStyle">italic</property>
                        <property name="dataSet">Data Set <%name%></property>
                        <list-property name="boundDataColumns">
                            <structure>
                                <property name="name">totalCount</property>
                                <property name="displayName">totalCount</property>
                                <expression name="expression">dataSetRow["totalCount"]</expression>
                                <property name="dataType">Integer</property>
                            </structure>
                        </list-property>
                        <property name="resultSetColumn">totalCount</property>
                    </data>
                </cell>
            </row>
            <!--
			<row id="69<%i()%>">
                <cell id="70<%i()%>">
                    <label id="71<%i()%>">
						<property name="fontFamily">"Tahoma"</property>
                        <property name="fontWeight">bold</property>
                        <text-property name="text">First created file</text-property>
                    </label>
                </cell>
                <cell id="72<%i()%>">
                    <data id="73<%i()%>">
						<property name="fontFamily">"Tahoma"</property>
						<property name="fontStyle">italic</property>
                        <property name="dataSet">Data Set <%name%></property>
                        <list-property name="boundDataColumns">
                            <structure>
                                <property name="name">FirstCreatedDate</property>
                                <property name="displayName">FirstCreatedDate</property>
                                <expression name="expression">dataSetRow["FirstCreatedDate"]</expression>
                                <property name="dataType">Integer</property>
                            </structure>
                        </list-property>
                        <property name="resultSetColumn">FirstCreatedDate</property>
                    </data>
                </cell>
			</row>
            <row id="77<%i()%>">
                <cell id="78<%i()%>">
                    <label id="79<%i()%>">
						<property name="fontFamily">"Tahoma"</property>
                        <property name="fontWeight">bold</property>
                        <text-property name="text">Last modified file</text-property>
                    </label>
                </cell>
                <cell id="80<%i()%>">
                    <data id="81<%i()%>">
						<property name="fontFamily">"Tahoma"</property>
						<property name="fontStyle">italic</property>
                        <property name="dataSet">Data Set <%name%></property>
                        <list-property name="boundDataColumns">
                            <structure>
                                <property name="name">LastCreatedDate</property>
                                <property name="displayName">LastCreatedDate</property>
                                <expression name="expression">dataSetRow["LastCreatedDate"]</expression>
                                <property name="dataType">Integer</property>
                            </structure>
                        </list-property>
                        <property name="resultSetColumn">LastCreatedDate</property>
                    </data>
                </cell>
            </row>
			-->
        </grid>
        <%}%>
       <%for (getAllClasses()){%>
        <text id="82<%i()%>">
			<list-property name="visibility">
                <structure>
                    <property name="format">all</property>
                    <expression name="valueExpr">
var retour = true;
if(params["Footer"].value){
	for(i = 0; i&lt;params["Type"].value.length ; i++){
		if(params["Type"].value[i] == "<%getLabel()%>" || params["Type"].value[i] == "All"){
			retour = false;
		}
	}
}
retour;</expression>
                </structure>
            </list-property>
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
        <table id="83<%i()%>">
            <property name="width">100%</property>
			<list-property name="visibility">
                <structure>
                    <property name="format">all</property>
                    <expression name="valueExpr">var retour = true;
if(params["Footer"].value){
	for(i = 0; i&lt;params["Type"].value.length ; i++){
		if(params["Type"].value[i] == "<%getLabel()%>" || params["Type"].value[i] == "All"){
			retour = false;
		}
	}
}
retour;</expression>
                </structure>
            </list-property>
            <property name="dataSet">Data Set <%name%></property>
            <list-property name="boundDataColumns">
            	<structure>
                    <property name="name">size</property>
                    <property name="displayName">size</property>
                    <expression name="expression">dataSetRow["size"]</expression>
                    <property name="dataType">string</property>
                </structure>
                <structure>
                    <property name="name">createdDate</property>
                    <property name="displayName">createdDate</property>
                    <expression name="expression">dataSetRow["createdDate"]</expression>
                    <property name="dataType">string</property>
                </structure>
                <structure>
                    <property name="name">modifyDate</property>
                    <property name="displayName">modifyDate</property>
                    <expression name="expression">dataSetRow["modifyDate"]</expression>
                    <property name="dataType">string</property>
                </structure>
            	<%i().push()%>
            	<%for (getAllAttributes()){%>
            	<structure>
                    <property name="name"><%getQualifiedName%></property>
                    <expression name="expression">dataSetRow["<%getQualifiedName%>"]</expression>
                    <property name="dataType">string</property>
                </structure>
            	<%}%>
				<structure>
                    <property name="name">TotalSize</property>
                    <expression name="expression">dataSetRow["TotalSize"]</expression>
                    <property name="dataType">float</property>
                </structure>
            </list-property>
            <column id="84<%i()%>"/>
            <column id="85<%i()%>"/>
            <detail>
            	<row id="86<%peek()%><%i()%>">
                    <cell id="87<%peek()%><%i()%>">
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
                        <label id="88<%peek()%><%i()%>">
                            <property name="fontWeight">bold</property>
                            <text-property name="text">Size</text-property>
                        </label>
                    </cell>
                    <cell id="89<%peek()%><%i()%>">
                        <property name="fontFamily">"Tahoma"</property>
                        <property name="borderRightColor">#000000</property>
                        <property name="borderRightStyle">solid</property>
                        <property name="borderRightWidth">medium</property>
                        <property name="borderTopColor">#000000</property>
                        <property name="borderTopStyle">solid</property>
                        <property name="borderTopWidth">medium</property>
                        <property name="borderBottomColor">#000000</property>
                        <property name="borderBottomStyle">dotted</property>
                        <property name="borderBottomWidth">thin</property>
                        <property name="paddingTop">1pt</property>
                        <data id="90<%peek()%><%i()%>">
                            <property name="fontStyle">italic</property>
							<structure name="numberFormat">
                            <property name="category">Custom</property>
                            <property name="pattern">###,##0.00 Bytes</property>
                        </structure>
                            <property name="resultSetColumn">size</property>
                        </data>
                    </cell>
                </row>
                <row id="91<%peek()%><%i()%>">
                    <cell id="92<%peek()%><%i()%>">
                    	<property name="fontFamily">"Tahoma"</property>
                        <property name="borderLeftColor">#000000</property>
                        <property name="borderLeftStyle">solid</property>
                        <property name="borderLeftWidth">medium</property>
                        <property name="borderRightColor">#000000</property>
                        <property name="borderRightStyle">dotted</property>
                        <property name="borderRightWidth">thin</property>
                        <property name="paddingTop">1pt</property>
                        <label id="93<%peek()%><%i()%>">
                            <property name="fontWeight">bold</property>
                            <text-property name="text">Created Date</text-property>
                        </label>
                    </cell>
                    <cell id="94<%peek()%><%i()%>">
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
                        <data id="95<%peek()%><%i()%>">
                            <property name="fontStyle">italic</property>
                            <property name="resultSetColumn">createdDate</property>
                        </data>
                    </cell>
                </row>
                <row id="96<%peek()%><%i()%>">
                    <cell id="98<%peek()%><%i()%>">
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
                        <label id="99<%peek()%><%i()%>">
                            <property name="fontWeight">bold</property>
                            <text-property name="text">Modified Date</text-property>
                        </label>
                    </cell>
                    <cell id="100<%peek()%><%i()%>">
                        <property name="fontFamily">"Tahoma"</property>
                        <property name="borderRightColor">#000000</property>
                        <property name="borderRightStyle">solid</property>
                        <property name="borderRightWidth">medium</property>
                        <property name="borderBottomColor">#000000</property>
                        <property name="borderBottomStyle">solid</property>
                        <property name="borderBottomWidth">medium</property>
                        <property name="paddingTop">1pt</property>
                        <data id="101<%peek()%><%i()%>">
                            <property name="fontStyle">italic</property>
                            <property name="resultSetColumn">modifyDate</property>
                        </data>
                    </cell>
                </row>

                <%for (getAllAttributes()){%>
	                <%i().put("end")%>
	            <%}%>
            	<%for (getAllAttributes()){%>
                <row id="102<%peek()%><%i()%>">
                    <cell id="103<%peek()%><%i()%>">
                   		<property name="fontFamily">"Tahoma"</property>
                   		<property name="borderLeftColor">#000000</property>
                        <property name="borderLeftStyle">solid</property>
                        <property name="borderLeftWidth">medium</property>
                        <property name="borderRightColor">#000000</property>
                        <property name="borderRightStyle">dotted</property>
                        <property name="borderRightWidth">thin</property>
                        <property name="paddingTop">1pt</property>
                    	<%if (i() == 0){%>
                        <property name="borderTopColor">#000000</property>
                        <property name="borderTopStyle">solid</property>
                        <property name="borderTopWidth">medium</property>
                    	<%}%>
                    	<%if (i() == get("end")){%>
                        <property name="borderBottomColor">#000000</property>
                        <property name="borderBottomStyle">solid</property>
                        <property name="borderBottomWidth">medium</property>
                        <%}%>
                        <label id="104<%peek()%><%i()%>">
                            <property name="fontWeight">bold</property>
                            <text-property name="text"><%getLabel()%></text-property>
                        </label>
                    </cell>
                    <cell id="105<%peek()%><%i()%>">
                    	<property name="fontFamily">"Tahoma"</property>
                        <property name="borderRightColor">#000000</property>
                        <property name="borderRightStyle">solid</property>
                        <property name="borderRightWidth">medium</property>
                        <property name="paddingTop">1pt</property>
                    	<%if (i() == 0){%>
                        <property name="borderTopColor">#000000</property>
                        <property name="borderTopStyle">solid</property>
                        <property name="borderTopWidth">medium</property>
                    	<%}%>
                    	<%if (i() == get("end")){%>
                        <property name="borderBottomColor">#000000</property>
                        <property name="borderBottomStyle">solid</property>
                        <property name="borderBottomWidth">medium</property>
                        <%}%>
                        <data id="106<%peek()%><%i()%>">
                            <property name="fontStyle">italic</property>
                            <property name="resultSetColumn"><%getQualifiedName%></property>
                        </data>
                    </cell>
                </row>
                <%}%>
            </detail>
         </table>
        <%}%>
    </body>
</report>