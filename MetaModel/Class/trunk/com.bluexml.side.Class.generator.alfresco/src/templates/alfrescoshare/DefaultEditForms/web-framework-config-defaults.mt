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
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%-- 
     
--%>

<%script type="clazz.ClassPackage" name="getCustomWebFwkConfigOutputFile"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext")%>web-framework-config-defaults.xml<%}%>

<%script type="clazz.ClassPackage" name="customWebFrameworkConfig" file="<%getCustomWebFwkConfigOutputFile%>"%>
<alfresco-config>

   <!--plug-ins>
      <evaluators>
           <evaluator id="node-type" class="org.alfresco.web.config.NodeTypeEvaluator" />
           <evaluator id="aspect-name" class="org.alfresco.web.config.AspectEvaluator" />
      </evaluators>
   </plug-ins-->

   <%for (getAllClasses().nSort("name")){%>    
   <%-- TODO: do we need to generate this for abstract classes or not? --%>
   <%--if (metainfo[key.equalsIgnoreCase("isContainer")].nSize()>0 && !isAbstract){--%>
   
   <!-- START BlueXML custom form configuration for the <%getContentType()%> content type -->   
   <config evaluator="node-type" condition="<%getContentType()%>" replace="true">
   	<forms>
	      <form>
	         <field-visibility>
	            <!-- START default Alfresco form configuration (cm:content) -->
	            <%getDefaultAlfrescoContentFormConfiguration()%>  
	            <!-- END default Alfresco form configuration (cm:content) -->
	            <%--
	            <!-- START BlueXML custom form configuration (<%getContentType()%>) -->
	            <%getCustomBlueXMLFormConfiguration()%>
	            <!-- END BlueXML custom form configuration (<%getContentType()%>) -->
	            --%>
	         </field-visibility>
	      </form>
	</forms>          
   </config>    
   <!-- END BlueXML custom form configuration for the <%getContentType()%> content type -->    
   <%--}--%>      
   <%}%>
  
</alfresco-config>

<%script type="clazz.Clazz" name="getContentType"%>
<%getPrefixedQName()%>

<%script type="clazz.Clazz" name="getCustomBlueXMLFormConfiguration"%>
<%for (getAllSortedAttibutes()){%>  
<show id="<%getPrefixedQName()%>"/>
<%}%> 

<%-- These default values are taken from web-framework-config-commons.xml --%>
<%script type="clazz.Clazz" name="getDefaultAlfrescoContentFormConfiguration"%>
<show id="cm:name" />
<%--
<!-- <show id="mimetype" /> TODO: Need to extract from content property -->
<show id="cm:title" />
<show id="cm:description" />
<show id="cm:author" />
<!-- <show id="size" /> TODO: Need to extract from content property -->
<show id="cm:description" />
<show id="cm:creator" />
<show id="cm:created" />
<show id="cm:modifier" />
<show id="cm:modified" />
--%>
