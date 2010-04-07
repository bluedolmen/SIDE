<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
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
   <config evaluator="node-type" condition="<%getContentType()%>">
   	<forms>
	      <form>
	         <field-visibility>
	            <!-- START default Alfresco form configuration (cm:content) -->
	            <%getDefaultAlfrescoContentFormConfiguration()%>  
	            <!-- END default Alfresco form configuration (cm:content) -->
	            
	            <!-- START BlueXML custom form configuration (<%getContentType()%>) -->
	            <%--getCustomBlueXMLFormConfiguration()--%>
	            <!-- END BlueXML custom form configuration (<%getContentType()%>) -->
	         </field-visibility>
	      </form>
	</forms>          
   </config>    
   <!-- END BlueXML custom form configuration for the <%getContentType()%> content type -->    
   <%--}--%>      
   <%}%>
  
</alfresco-config>

<%script type="clazz.Clazz" name="getContentType"%>
<%getFolder()%>:<%getQualifiedName()%>

<%script type="clazz.Clazz" name="getCustomBlueXMLFormConfiguration"%>
<%for (getAllSortedAttibutes()){%>  
<show id="<%getFolder()%>:<%getQualifiedName()%>"/>
<%}%> 

<%-- These default values are taken from web-framework-config-commons.xml --%>
<%script type="clazz.Clazz" name="getDefaultAlfrescoContentFormConfiguration"%>
<show id="cm:name" />
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
