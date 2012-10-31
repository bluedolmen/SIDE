<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%script type="Portlet" name="generateDocumentLibraryConfiguration"%>
<!-- Document Library config section -->
<config evaluator="string-compare" condition="DocumentLibrary"<%if (getFirstMetainfoValue("replace","false") == "true"){%> replace="true"<%}%>>
<%if (getFirstMetainfoValue("replace","false") == "true"){%>
	<%-- so we need to define all configuration item --%>
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

	<%generateAvailableTypeHierachy(args(0))%>

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
        Enable/disable the Google Docs UI integration (Extra types on Create Content menu, Google Docs actions).
     -->
     <enabled>false</enabled>
     
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
  </file-upload>
  
  <!--
     Filter configurations
  -->
  <filters>
     <!--
        The maximum number of tags to display in the Tags filter, can be set to -1 for all tags
     -->
     <maximum-tag-count>100</maximum-tag-count>
  </filters>
<%}else{%>
	<%generateAvailableTypeHierachy(args(0))%>
<%}%>
</config>

<%script type="Portlet" name="generateAvailableTypeHierachy"%>
<types>
	<%if (metainfo[key.toLowerCase() == "flat"].nSize() > 0){%>
	<%for (args(0).filter("clazz.Clazz")[!abstract]){%>
	<%getPrefixedQName().put("currentqname")%>
	<type name="<%get("currentqname")%>">
		<%for (args(0).filter("clazz.Clazz")[!abstract && getPrefixedQName() != get("currentqname")]){%>
		<subtype name="<%getPrefixedQName()%>" />
		<%}%>
	</type>
	<%}%>
	<%}else{%>
	<type name="cm:content">
	<%for (args(0).filter("clazz.Clazz")[generalizations.nSize() == 0 && !abstract || isChildOfCmContent()]){%>
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
	
	<%}%>
	
	
</types>

<aspects>
	<visible>
	<%for (args(0).filter("clazz.Aspect")){%>
		<aspect name="<%getPrefixedQName()%>" />
	<%}%>
	</visible>
</aspects>