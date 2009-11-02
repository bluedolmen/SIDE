<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%script type="Portal" name="buildForms"%>
<%for (portletSet){%>
<%if (name.toLowerCase().trim() == "documentdetails" && isPortletInternal != null && isPortletInternal.view != null) {%>
<%for (isPortletInternal.view.getInnerView()){%>
<!-- START BlueXML custom form configuration for the <%filter("view.AbstractViewOf").viewOf.filter("clazz.Clazz").getContentType()%> content type -->   
   <config evaluator="node-type" condition="<%filter("view.AbstractViewOf").viewOf.filter("clazz.Clazz").getContentType()%>">
   	<forms>
	      <form>
	         <field-visibility>
	            <!-- START default Alfresco form configuration (cm:content) -->
	            <%getDefaultAlfrescoContentFormConfiguration()%>  
	            <!-- END default Alfresco form configuration (cm:content) -->
	            
	            <!-- START BlueXML custom form configuration (<%filter("view.AbstractViewOf").viewOf.filter("clazz.Clazz").getContentType()%>) -->
	            <%for (children){%>
					<%if ( mapTo.filter("common.NamedModelElement")){%>
					<show id="<%mapTo.filter("common.NamedModelElement").getFolder()%>:<%mapTo.filter("common.NamedModelElement").getQualifiedName()%>"/>
					<%}%>
				<%}%>
	            <!-- END BlueXML custom form configuration (<%filter("view.AbstractViewOf").viewOf.filter("clazz.Clazz").getContentType()%>) -->
	         </field-visibility>
	      </form>
	</forms>          
   </config>    
   <!-- END BlueXML custom form configuration for the <%filter("view.AbstractViewOf").viewOf.filter("clazz.Clazz").getContentType()%> content type -->
<%}%>
<%}%>
<%}%>

<%-- These default values are taken from web-framework-config-commons.xml --%>
<%script type="EObject" name="getDefaultAlfrescoContentFormConfiguration"%>
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
