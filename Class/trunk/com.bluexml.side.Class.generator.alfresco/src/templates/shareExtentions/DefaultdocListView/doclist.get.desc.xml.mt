<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("alf.paths.extension.client.webscripts")%>/org/alfresco/slingshot/documentlibrary/doclist.get.desc.xml<%}%>
<%script type="clazz.ClassPackage" name="doclist_get_desc_xml" file="<%validatedFilename%>"%>
<webscript>
  <shortname>doclist</shortname>
  <description>Document List Component - doclist data webscript</description>
  <url>/slingshot/doclib/doclist/node/{store_type}/{store_id}/{id}</url>
  <url>/slingshot/doclib/doclist/{type}/site/{site}/{container}/{path}</url>
  <url>/slingshot/doclib/doclist/{type}/site/{site}/{container}</url>
  <url>/slingshot/doclib/doclist/{type}/node/{store_type}/{store_id}/{id}/{path}</url>
  <url>/slingshot/doclib/doclist/{type}/node/{store_type}/{store_id}/{id}</url>
  <format default="json">argument</format>
  <authentication>user</authentication>
  <transaction>required</transaction>
</webscript>