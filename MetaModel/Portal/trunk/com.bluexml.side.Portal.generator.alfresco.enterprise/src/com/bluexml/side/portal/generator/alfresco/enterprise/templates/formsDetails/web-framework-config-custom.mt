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
					<show id="<%mapTo.filter("common.NamedModelElement").getPrefixedQName()%>"/>
					<%}%>
				<%}%>
			    <!-- END BlueXML custom form configuration (<%filter("view.AbstractViewOf").viewOf.filter("clazz.Clazz").getContentType()%>) -->
			</field-visibility>
		</form>
		<!-- Document Library pop-up Edit Metadata form -->
		<form id="doclib-simple-metadata">
		   <field-visibility>
		      <show id="cm:name" />
		      <show id="cm:title" force="true" />
		      <show id="cm:description" force="true" />
		      <!-- tags and categories -->
		      <show id="cm:taggable" for-mode="edit" force="true" />
		      <show id="cm:categories" />
		   </field-visibility>
		   <edit-form template="../documentlibrary/forms/doclib-simple-metadata.ftl" />
		   <appearance>
		      <field id="cm:title">
		         <control template="/org/alfresco/components/form/controls/textfield.ftl" />
		      </field>
		      <field id="cm:description">
		         <control>
		            <control-param name="activateLinks">true</control-param>
		         </control>
		      </field>
		      <field id="cm:taggable">
		         <control>
		            <control-param name="compactMode">true</control-param>
		            <control-param name="params">aspect=cm:taggable</control-param>
		            <control-param name="createNewItemUri">/api/tag/workspace/SpacesStore</control-param>
		            <control-param name="createNewItemIcon">tag</control-param>
		         </control>
		      </field>
		      <field id="cm:categories">
		         <control>
		            <control-param name="compactMode">true</control-param>
		         </control>
		      </field>
		   </appearance>
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
