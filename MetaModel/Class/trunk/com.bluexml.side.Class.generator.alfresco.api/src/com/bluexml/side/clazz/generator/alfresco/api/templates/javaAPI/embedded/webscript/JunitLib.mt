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
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.api.service.ValueGenerator
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.lib
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.embedded.javaAPI
import com.bluexml.side.clazz.generator.alfresco.api.templates.services.classes
%>


<%script type="Clazz" name="generateCreateStatementWebscript" post="trim()"%>
<%generatePropertiesStatement(args(1))%>
String qstring<%args(1)%>="";
<%getPropertiesQueryString(args(1),"qstring"+args(1))%>
String json<%args(1)%> = getResponse(getCreateURL("<%getJavaModelObjectName()%>", qstring<%args(1)%>));
JSONObject jsa<%args(1)%> =(JSONObject) JSONSerializer.toJSON(json<%args(1)%>);
<%getJavaModelObjectName%> <%args(0)%> = <%javaWebScriptFactoryAdapterName()%>.load<%getJavaModelObjectName()%>(jsa<%args(1)%>);
created.add(<%args(0)%>.getUuid());

<%script type="AbstractClass" name="getPropertiesQueryString" post="trim()"%>
<%for (getAllSortedAttibutes()){%>
<%if (isMultivalued()){%>
<%args(1)%> += "&<%name%>=" + URLEncoder.encode(JSONSerializer.toJSON(<%name%><%args(0)%>).toString(),"UTF-8");
<%}else{%>
<%args(1)%> += "&<%name%>=" + URLEncoder.encode(<%name%><%args(0)%>,"UTF-8");
<%}%>
<%}%>

<%script type="AbstractClass" name="generatePropertiesAssertionWebscript" %>
// assert for properties
<%for (getAllSortedAttibutes()){%>
	<%if (metainfo[key.equalsIgnoreCase("multiple")].nSize()>0){%>
String[] tab = <%args(1)+".replaceFirst(\"&"+name+"=\\\\[([^&]*)\\\\]\", \"$1\").split(\",\")"%>;
assertEquals(<%name%><%args(0)%>.size(), tab.length);
for (int i = 0; i<<%name%><%args(0)%>.size(); i++) {
	<%getPropertyTestAssertion(name+args(0)+".get(i)", "tab[i]")%>;
}
	<%}else{%>
<%getPropertyTestAssertion(name+args(0),args(1)+".replaceFirst(\"&"+name+"=([^&]*)\", \"$1\")")%>
	<%}%>
<%}%>
