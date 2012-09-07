<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
%>
<%script type="Portal" name="validatedFilename" post="trim()" %>
<%if (pageSet[ID.toLowerCase().trim() == "documentlibrary"].nSize() > 0 &&
 pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets[associationPortlet.name.toLowerCase().trim() == "toolbar-create-content" && associationPortlet.isPortletInternal != null].nSize() > 0){%>
 <%getProperty("alf.share.paths.web-ext.alf.components")%>/documentlibrary/toolbar.get.config.xml
<%}%>

<%script type="Portal" name="toolbar-create-content" file="<%validatedFilename%>"%>
<%for (pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets.associationPortlet[(name.toLowerCase().trim() == "toolbar-create-content") && isPortletInternal != null]){%>
<%generate_toolbar_portlet()%>
<%}%>

<%script type="Portlet" name="generate_toolbar_portlet"%>
<toolbar>
   <createContent>
   	  <%if (metainfo[key == "default-create-content"].nSize() == 1 && metainfo[key == "default-create-content"].nGet(0).value.trim() == "true"){%>
      <content mimetype="text/plain" icon="plain-text" label="menu.create-content.text" itemid="cm:content"/>
      <content mimetype="text/html" icon="html" label="menu.create-content.html" itemid="cm:content"/>
      <content mimetype="text/xml" icon="xml" label="menu.create-content.xml" itemid="cm:content"/>
      <%}%>
      <%-- disabled since MM back before adding metainfoGroup
      <%if (metainfoGroup[key == 'mimetypes'].nSize() > 0){%>
      	<%for (metainfoGroup[key == 'mimetypes']){%>
      <content mimetype="<%if (children[key == 'mimetype']){%><%children[key == 'mimetype'].filter("common.metaInfo").value%><%}else{%>text/plain<%}%>" icon="plain-text" label="menu.create-content.<%children[key == 'form'].filter("common.metaInfo").EObjectValue.filter("form.ClassReference").real_class.name%>" itemid="<%children[key == 'form'].filter("common.metaInfo").EObjectValue.filter("form.ClassReference").real_class.getPrefixedQName()%>"<%if (children[key == 'form'].filter("common.metaInfo").EObjectValue.eContainer("FormCollection").name != "default"){%> formid="<%children[key == 'form'].filter("common.metaInfo").EObjectValue.eContainer("FormCollection").name%>"<%}%>/>
      	<%}%>
      <%}else{%>
      --%>
      	<%for (isPortletInternal.form.forms.filter("form.FormClass")){%>
      <content mimetype="text/plain" icon="plain-text" label="menu.create-content.<%real_class.name%>" itemid="<%real_class.getPrefixedQName()%>"<%if (eContainer("form.FormCollection").name != "default"){%> formid="<%eContainer("form.FormCollection").name%>"<%}%> nodetype="<%if (real_class.isFolder()){%>folder<%}else{%>document<%}%>"/>
      	<%}%>
      <%--
      <%}%>
      --%>      
   </createContent>
   <actionSets>
      <actionSet id="default">
         <action type="action-link" id="onActionCopyTo" label="menu.selected-items.copy" />
         <action type="action-link" id="onActionMoveTo" permission="delete" label="menu.selected-items.move" />
         <action type="action-link" id="onActionDelete" permission="delete" label="menu.selected-items.delete" />
         <action type="action-link" id="onActionAssignWorkflow" asset="document" label="menu.selected-items.assign-workflow" />
         <action type="action-link" id="onActionManagePermissions" permission="permissions" label="menu.selected-items.manage-permissions" />
      </actionSet>
   </actionSets>
</toolbar>