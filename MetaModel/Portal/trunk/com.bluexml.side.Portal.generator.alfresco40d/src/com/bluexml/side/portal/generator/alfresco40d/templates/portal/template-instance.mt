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
   <template-type>com/bluexml/alfresco/<%ID%></template-type>
   <properties>
   <%for (metainfo[key.startsWith("prop_")]){%>
      <<%key%>><%value%></<%key%>>
   <%}%>
   </properties>
   <components>
<%for (portlets){%>
      <!-- Document Workflows -->
      <component>
         <region-id><%associationPortlet.name%></region-id>
         <%associationPortlet.computeUrl()%>
         <properties>
            <%associationPortlet.computeProperties()%>
         </properties>
         
         <%if (associationPortlet.subPortlets.nSize() >0){%>
         <sub-components>
            <sub-component id="<%associationPortlet.subPortlets.nFirst().metainfo[key =="subComponent_id"].value%>">
               <evaluations>
         	<%for (associationPortlet.subPortlets){%>
                  <evaluation>
                     <evaluators>
                        <evaluator type="<%metainfo[key =="evaluator_type"].value%>">
                        <%if (metainfo[key =="params-element"] != null){%>                        
                           <params>
                              <element><%metainfo[key =="params-element"].value%></element>
                           </params>
                         <%}%>
                        </evaluator>
                     </evaluators>
                     <%computeUrl()%>
                     <properties>
                        <%computeProperties()%>
                     </properties>
                  </evaluation>
         	<%}%>
               </evaluations>
            </sub-component>
         </sub-components>
         <%}%>
      </component>
<%}%>
   </components>
</template-instance>
<%script type="Portlet" name="computeUrl" post="trim()"%>
<%for (isInstanceOfPortletType.instances){%>
<%if (instanceOf.name == "url"){%><url><%value%></url><%}%><%}%>
<%script type="Portlet" name="computeProperties" post="trim()"%>
<%for (isInstanceOfPortletType.instances){%>
   		<%if (instanceOf.name != "scope" && instanceOf.name != "url" && instanceOf.name != "region-id" && instanceOf.name != "source-id" && value != null && value != ""){%>   
<<%instanceOf.name%>><%if (instanceOf.metainfo[key.equalsIgnoreCase("escape")].nSize() > 0 && instanceOf.metainfo[key.equalsIgnoreCase("escape")].nFirst().value.equalsIgnoreCase("false")){%><%value%><%}else{%><%value.replaceAll("\{", "%7B").replaceAll("\}", "%7D")%><%}%></<%instanceOf.name%>>
   		<%}%>   	
   	<%}%>