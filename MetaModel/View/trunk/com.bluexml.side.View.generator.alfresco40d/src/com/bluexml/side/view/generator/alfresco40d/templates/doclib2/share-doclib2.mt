<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.view.generator.alfresco.templates.services.common

%>

<%script type="ComposedView" name="doclist_ftl" file="<%getProperty("alf.share.paths.web-ext")%>/SIDE_ViewExtension_<%service::getRootContainer().name%>/config-view-<%name%>.xml"%>
<alfresco-config>
<!-- Document Library config section -->
<config evaluator="string-compare" condition="DocumentLibrary">

      <!--
         Custom Metadata Templates for the Document Library Browse View

         Use view="simple" or view="detailed" to limit the display to one particular view.
      -->
      <metadata-templates>
         <!-- Default (fallback) -->
         <template id="default">
            <%defaultView%>
         </template>

         <!-- Working Copies -->
         <template id="isWorkingCopy">
            <evaluator>evaluator.doclib.metadata.isWorkingCopy</evaluator>
            <line index="10" id="date">{date}{size}</line>
            <line index="20" id="description" view="detailed">{description}</line>
         </template>

         <!-- Dictionary Models -->
         <template id="dictionaryModel">
            <evaluator>evaluator.doclib.metadata.isDictionaryModel</evaluator>
            <line index="10" id="date">{date}</line>
            <line index="20" id="description" view="detailed">{description}</line>
            <line index="30" id="dictionaryModel" simpleView="true">{modelActive label.modelActive}{modelName label.modelName}{modelDescription label.modelDescription}</line>
         </template>

         <!-- Replication Transfer Targets -->
         <template id="transferTarget">
            <evaluator>evaluator.doclib.metadata.isTransferTarget</evaluator>
            <line index="10" id="date">{date}</line>
            <line index="20" id="description" view="detailed">{description}</line>
            <line index="30" id="transferTarget" simpleView="true">{trx_enabled label.trx_enabled}{trx_endpointhost label.trx_endpointhost}{trx_endpointport label.trx_endpointport}</line>
         </template>
		         
         <%for (getInnerView()){%>
         <template id="<%getFullName()%>">
            <evaluator>evaluator.doclib.metadata.side.<%filter("view.AbstractViewOf").viewOf.filter("clazz.Clazz").getPrefixedQName()%>_<%filter("view.AbstractViewOf").name%></evaluator>
            <%if (metainfo[key.toLowerCase() == "sharedefault" && value =="true"] || current("ComposedView").metainfo[key.toLowerCase() == "sharedefault" && value =="true"]){%>
			<%defaultView%>
            <%}%>
            
            <line index="45" id="sideProperties">
            <%for (children){%>
				<%if (mapTo.filter("clazz.Attribute")){%>{<%mapTo.filter("clazz.Attribute").getPrefixedQName()%> <%mapTo.filter("clazz.Attribute").getPrefixedQName("_")%>}<%}%>
			<%}%>
			</line>
			
         </template>
         <%}%>
      </metadata-templates>

</config>
</alfresco-config>

<%script type="EObject" name="defaultView"%>
<line index="10" id="date">{date}{size}</line>
<line index="20" id="description" view="detailed">{description}</line>
<line index="30" id="tags" view="detailed">{tags}</line>
<line index="40" id="categories" view="detailed" evaluator="evaluator.doclib.metadata.hasCategories">{categories}</line>
<line index="50" id="social" view="detailed">{social}</line>