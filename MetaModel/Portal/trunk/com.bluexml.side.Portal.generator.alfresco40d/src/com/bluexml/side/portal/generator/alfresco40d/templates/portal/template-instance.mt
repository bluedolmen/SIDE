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
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
%>


<%-- Navigation component templates creation --%>
<%script type="Page" name="createNavigationComponentTemplate"%>
<%if (generate){%>
<%getProperty("alf.share.paths.web-ext.template-instances")%><%ID.toLowerCase()%>.xml
<%}%>
<%script type="Page" name="alfrescoGenerator" file="<%createNavigationComponentTemplate%>" post="trim()"%>
<?xml version='1.0' encoding='UTF-8'?>
<template-instance>
   <template-type>com/bluexml/<%ID.toLowerCase()%></template-type>
   <properties>
   <%for (metainfo[key.startsWith("prop_")]){%>
   	  <%key.substring(5).put("propertyTag")%>
      <<%get("propertyTag")%>><%value%></<%get("propertyTag")%>>
   <%}%>
   </properties>
   <components>
<%for (portlets){%>
<!-- <%associationPortlet.name%> -->
<%if (associationPortlet.isInstanceOfPortletType.instances[instanceOf.name == "scope"].value != "global"){%>
	<%compute_component()%>
<%}%>
<%}%>
   </components>
</template-instance>
<%script type="HavePortlet" name="compute_component" post="trim()"%>
  <component>
     <region-id><%associationPortlet.name%></region-id>
     <%associationPortlet.computeUrlTag()%>
     <%associationPortlet.computeProperties()%>
     <%compute_subComponent()%>
  </component>
<%script type="HavePortlet" name="compute_subComponent" post="trim()"%>
<%if (associationPortlet.subPortlets.nSize() >0){%>
     <sub-components>
        <%for (associationPortlet.subPortlets){%>
        <sub-component id="<%metainfo[key =="subComponent_id"].value%>">
           <evaluations>
        <%for (subPortlets){%>
              <evaluation id="<%metainfo[key =="evaluation_id"].value%>">
                 <%if (metainfo[key =="evaluator_type"] != null){%>
                 <evaluators>
                    <evaluator type="<%metainfo[key =="evaluator_type"].value%>">
                    <%if (metainfo[key.startsWith("params-")] != null){%>
                       <params>
                          <%for (metainfo[key.startsWith("params-")]){%>
                          <%key.substring(7).put("paramTag")%>
                          <<%get("paramTag")%>><%value%></<%get("paramTag")%>>
                          <%}%>
                       </params>
                     <%}%>
                    </evaluator>
                 </evaluators>
                 <%}%>
                 <%computeUrlTag()%>
                 <%computeProperties()%>
              </evaluation>
        <%}%>
           </evaluations>
        </sub-component>
        <%}%>
     </sub-components>
     <%}%>
<%script type="Portlet" name="computeUrlTag" post="trim()"%>
<%for (isInstanceOfPortletType.instances){%>
<%if (instanceOf.name == "url"){%><url><%value.replaceAll("&","&amp;")%></url><%}%><%}%>
<%script type="Portlet" name="computeProperties" post="trim()"%>
<%if (isInstanceOfPortletType.instances[instanceOf.name != "scope" && instanceOf.name != "url" && instanceOf.name != "region-id" && instanceOf.name != "source-id" && value != null && value != ""].nSize() > 0){%>
<properties>
	<%for (isInstanceOfPortletType.instances){%>
   		<%if (instanceOf.name != "scope" && instanceOf.name != "url" && instanceOf.name != "region-id" && instanceOf.name != "source-id" && value != null && value != ""){%>
	<<%instanceOf.name%>><%if (metainfo[key.equalsIgnoreCase("escape")].nSize() > 0 && metainfo[key.equalsIgnoreCase("escape")].nFirst().value.equalsIgnoreCase("false")){%><%value%><%}else{%><%value.replaceAll("\{", "%7B").replaceAll("\}", "%7D")%><%}%></<%instanceOf.name%>>
   		<%}%>
   	<%}%>
</properties>
<%}%>
