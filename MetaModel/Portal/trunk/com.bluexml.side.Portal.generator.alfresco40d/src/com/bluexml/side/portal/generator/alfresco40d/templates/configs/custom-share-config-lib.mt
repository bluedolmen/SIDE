<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
%>

<%script type="Page" name="generateDocumentLibraryConfiguration"%>
<!-- Document Library config section -->
<config evaluator="string-compare" condition="DocumentLibrary"<%if (getFirstMetainfoValue("replace","false") == "true"){%> replace="true"<%}%>>
<%if (getFirstMetainfoValue("replace","false") == "true"){%>
      <tree>
         <!--
            Whether the folder Tree component should enumerate child folders or not.
            This is a relatively expensive operation, so should be set to "false" for Repositories with broad folder structures.
         -->
         <evaluate-child-folders>true</evaluate-child-folders>
         
         <!--
            Optionally limit the number of folders shown in treeview throughout Share.
         -->
         <maximum-folder-count>-1</maximum-folder-count>
      </tree>
      
      
      <%generateAvailableTypeHierachy_page()%>
      

      <!--
         If set, will present a WebDAV link for the current item on the Document and Folder details pages.
         Also used to generate the "View in Alfresco Explorer" action for folders.
      -->
      <!--
      <repository-url>http://localhost:8080/alfresco</repository-url>
      -->
      
      <!--
         Google Docs� integration
      -->
      <google-docs>
         <!--
            Google Docs UI integration is now toggled by the respective subsystem state within the Repository.
         -->
         
         <!--
            The mimetypes of documents Google Docs allows you to create via the Share interface.
            The I18N label is created from the "type" attribute, e.g. google-docs.doc=Google Docs&trade; Document
         -->
         <creatable-types>
            <creatable type="doc">application/msword</creatable>
            <creatable type="xls">application/vnd.ms-excel</creatable>
            <creatable type="ppt">application/vnd.ms-powerpoint</creatable>
         </creatable-types>
      </google-docs>
      
      <!--
         File upload configuration
      -->
      <file-upload>
         <!--
            Adobe Flash�
            In certain environments, an HTTP request originating from Flash cannot be authenticated using an existing session.
            See: http://bugs.adobe.com/jira/browse/FP-4830
            For these cases, it is useful to disable the Flash-based uploader for Share Document Libraries.
         -->
         <adobe-flash-enabled>true</adobe-flash-enabled>
         
         <!--
            In order to support drag-and-drop file upload a browser must be able to support the HTML5 drag-and-drop events, however
            if the browser does not support the FormData type (that allows streamed multipart file uploads) then all files need to be
            loaded into the browser's memory before being uploaded to the server. In order to prevent potential memory related errors,
            a limit is set for the sum of all file sizes being uploaded in a single operation (specified in bytes).
            As of April 2011, the only known browser that requires this restriction is Firefox 3.6.
          -->
         <in-memory-limit>262144000</in-memory-limit>
      </file-upload>

      <!--
         Sorting Options

         The 'sort' config element contains the name of the content model field to sort against and can be of the form:
         - short QName string e.g. "cm:name"
         - any other valid sortable fts-alfresco special field e.g. "TYPE"
         
         The field can be optionally followed by |true or |false to indicate sort direction,
         where true = ascending, false = descending
      -->
      <sorting>
         <sort label="label.name">cm:name|true</sort>
         <sort label="label.popularity">cm:likesRatingSchemeCount|false</sort>
         <sort label="label.title">cm:title</sort>
         <sort label="label.description">cm:description</sort>
         <sort label="label.created">cm:created</sort>
         <sort label="label.creator">cm:creator</sort>
         <sort label="label.modified">cm:modified</sort>
         <sort label="label.modifier">cm:modifier</sort>
         <sort label="label.size">cm:content.size</sort>
         <sort label="label.mimetype">cm:content.mimetype</sort>
         <sort label="label.type">TYPE</sort>
      </sorting>
      
      <!--
         Filter configurations
      -->
      <filters>
         <!--
            The maximum number of tags to display in the Tags filter, can be set to -1 for all tags
         -->
         <maximum-tag-count>100</maximum-tag-count>
      </filters>
      
      <!--
         Status Indicators
      -->
      <indicators>
         <!-- Editing -->
         <indicator id="editing" index="10">
            <evaluator>evaluator.doclib.indicator.editing</evaluator>
         </indicator>
         <indicator id="lock-owner" index="10">
            <evaluator>evaluator.doclib.indicator.lockOwner</evaluator>
         </indicator>
         <indicator id="locked" index="10">
            <evaluator>evaluator.doclib.indicator.locked</evaluator>
            <labelParam index="0">{jsNode.properties.owner.displayName}</labelParam>
            <labelParam index="1">{jsNode.properties.owner.userName}</labelParam>
         </indicator>
         <!-- Google Docs Editing -->
         <indicator id="google-docs-editing" index="10">
            <evaluator>evaluator.doclib.indicator.googleDocsEditing</evaluator>
            <override>editing</override>
         </indicator>
         <indicator id="google-docs-owner" index="10">
            <evaluator>evaluator.doclib.indicator.googleDocsOwner</evaluator>
            <override>lock-owner</override>
         </indicator>
         <indicator id="google-docs-locked" index="10">
            <evaluator>evaluator.doclib.indicator.googleDocsLocked</evaluator>
            <labelParam index="0">{jsNode.properties.owner.displayName}</labelParam>
            <labelParam index="1">{jsNode.properties.owner.userName}</labelParam>
            <override>locked</override>
         </indicator>
         <!-- Workflows -->
         <indicator id="active-workflows" index="20">
            <evaluator>evaluator.doclib.indicator.activeWorkflows</evaluator>
            <labelParam index="0">{activeWorkflows}</labelParam>
         </indicator>
         <indicator id="simple-workflow" index="30">
            <evaluator>evaluator.doclib.indicator.simpleWorkflow</evaluator>
         </indicator>
         <!-- Everything Else -->
         <indicator id="rules" index="40">
            <evaluator>evaluator.doclib.indicator.rules</evaluator>
         </indicator>
         <indicator id="exif" index="40">
            <evaluator>evaluator.doclib.indicator.exifMetadata</evaluator>
         </indicator>
         <indicator id="geographic" index="50">
            <evaluator>evaluator.doclib.indicator.geographicMetadata</evaluator>
         </indicator>
         <indicator id="transferred-node" index="60">
            <evaluator>evaluator.doclib.indicator.transferredNode</evaluator>
         </indicator>
      </indicators>

      <!--
         Custom Metadata Templates for the Document Library Browse View

         Use view="simple" or view="detailed" to limit the display to one particular view.
      -->
      <metadata-templates>
         <!-- Default (fallback) -->
         <template id="default">
            <line index="10" id="date">{date}{size}</line>
            <line index="20" id="description" view="detailed">{description}</line>
            <line index="30" id="tags" view="detailed">{tags}</line>
            <line index="40" id="categories" view="detailed" evaluator="evaluator.doclib.metadata.hasCategories">{categories}</line>
            <line index="50" id="social" view="detailed">{social}</line>
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
      </metadata-templates>

      <%generateCreateContentConfig()%>

      <create-content-by-template>true</create-content-by-template>

   
<%}else{%>
<%generateAvailableTypeHierachy_page()%>

<%generateCreateContentConfig()%>
<%}%>
</config>

<%script type="Page" name="generateAvailableTypeHierachy_page"%>
<%for (portlets.associationPortlet[name.toLowerCase().trim() == "subtypes" && isPortletInternal != null && isPortletInternal.view != null]){%>
	<%generateDocumentLibraryConfiguration(isPortletInternal.view.getInnerView().filter("view.AbstractViewOf").viewOf)%>
<%}%>
<%for (portlets.associationPortlet[name.toLowerCase().trim() == "subtypes" && isPortletInternal != null && isPortletInternal.form != null]){%>
	<%generateAvailableTypeHierachy(isPortletInternal.form.forms.filter("form.ClassReference").real_class)%>
<%}%>

<%script type="Portlet" name="generateAvailableTypeHierachy"%>
<types>
	<type name="cm:content">
	<%for (args(0).filter("clazz.Clazz")[generalizations.nSize() == 0 && !abstract]){%>
		<%-- class that inerite from cm:content by default --%>			
		<subtype name="<%getPrefixedQName()%>" />	
	<%}%>
	</type>
	
	<%for (args(0).filter("clazz.Clazz")[!abstract]){%>
	<type name="<%getPrefixedQName()%>">
		<%for (getAllSubTypes().filter("clazz.Clazz")[!abstract]){%>
		<subtype name="<%getPrefixedQName()%>" />
		<%}%>
	</type>
	<%}%>
</types>

<aspects>         
	<visible>
	<%for (args(0).filter("clazz.Aspect")){%>
		<aspect name="<%getPrefixedQName()%>" />
	<%}%>
	</visible>
</aspects>

<%script type="Page" name="generateCreateContentConfig"%>
<%for (portlets.associationPortlet[(name.toLowerCase().trim() == "toolbar-create-content" || name.toLowerCase().trim() == "repo-toolbar-create-content") && isPortletInternal != null]){%>
<!--
   Create Content menu items
-->
<create-content>
<%if (metainfo[key == "default-create-content"].nSize() == 1 && metainfo[key == "default-create-content"].nGet(0).value.trim() == "true"){%>
   <content id="plain-text" mimetype="text/plain" label="create-content.text" itemid="cm:content" icon="text"/>
   <content id="html" mimetype="text/html"  label="create-content.html" itemid="cm:content"/>
   <content id="xml" mimetype="text/xml" label="create-content.xml" itemid="cm:content"/>
<%}%>
<%for (isPortletInternal.form.forms.filter("form.FormClass")){%>
   <content mimetype="" icon="plain-text" label="menu.create-content.<%real_class.name%>" itemid="<%real_class.getPrefixedQName()%>"<%if (eContainer("form.FormCollection").name != "default"){%> formid="<%eContainer("form.FormCollection").name%>"<%}%> isContainer="<%real_class.isFolder()%>"/>
<%}%>
</create-content>
<%}%>