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

import templates.alfrescoshare.uploadForm.file-upload-js-get-lib
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices
%>

<%--
  -- This file is a patched copy of Alfresco Share's
  -- Contains additional message for custom Types 
  --%>
  
<%script type="clazz.ClassPackage" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.core.site-webscripts")%>org/alfresco/components/documentlibrary/repo-documentlist.get_ja.properties<%}%>
## Buttons
button.folders.show=\u30d5\u30a9\u30eb\u30c0\u306e\u8868\u793a
button.folders.hide=\u30d5\u30a9\u30eb\u30c0\u3092\u96a0\u3059
button.view.simple=\u30b7\u30f3\u30d7\u30eb\u8868\u793a
button.view.detailed=\u8a73\u7d30\u8868\u793a

## Drop-down Menus
menu.select=\u9078\u629e
menu.select.all=\u5168\u3066
menu.select.none=\u306a\u3057
menu.select.invert=\u9078\u629e\u306e\u53cd\u8ee2
menu.select.folders=\u30d5\u30a9\u30eb\u30c0
menu.select.documents=\u6587\u66f8

## Document Details
details.link-to=\u30ea\u30f3\u30af\u5148: {0}
details.created.on=\u4f5c\u6210\u65e5:
details.created.by=\u4f5c\u6210\u8005:
details.modified.on=\u5909\u66f4\u65e5:
details.modified.by=\u5909\u66f4\u8005:
details.editing-started.on=\u7de8\u96c6\u958b\u59cb\u65e5:
details.editing-started.by=\u521d\u7248\u7de8\u96c6\u8005:
details.by=\u62c5\u5f53\u8005:
details.version=\u30d0\u30fc\u30b8\u30e7\u30f3:
details.size=\u30b5\u30a4\u30ba:
details.description=\u8aac\u660e:
details.description.none=(\u306a\u3057)
details.comments=\u30b3\u30e1\u30f3\u30c8:
details.tags=\u30bf\u30b0:
details.tags.none=(\u306a\u3057)
details.categories=\u30ab\u30c6\u30b4\u30ea:
details.categories.none=(\u306a\u3057)

## Details Banners
details.banner.editing=\u3053\u306e\u6587\u66f8\u306f\u3001\u3042\u306a\u305f\u304c\u30aa\u30d5\u30e9\u30a4\u30f3\u7de8\u96c6\u7528\u306b\u30ed\u30c3\u30af\u3057\u3066\u3044\u307e\u3059\u3002
details.banner.lock-owner=\u3053\u306e\u6587\u66f8\u306f\u3042\u306a\u305f\u304c\u30ed\u30c3\u30af\u3057\u3066\u3044\u307e\u3059\u3002
details.banner.locked=\u3053\u306e\u6587\u66f8\u306f\u3001{0} \u304c\u7de8\u96c6\u7528\u306b\u30ed\u30c3\u30af\u3057\u3066\u3044\u307e\u3059\u3002
details.banner.google-docs.link=Google Docs
details.banner.google-docs-owner=\u3053\u306e\u6587\u66f8\u306f\u3001\u3042\u306a\u305f\u304c\u7de8\u96c6\u7528\u306b {0} \u3078\u30a2\u30c3\u30d7\u30ed\u30fc\u30c9\u3057\u307e\u3057\u305f\u3002
details.banner.google-docs-locked=\u3053\u306e\u6587\u66f8\u306f\u3001{0} \u304c\u7de8\u96c6\u7528\u306b {1} \u3078\u30a2\u30c3\u30d7\u30ed\u30fc\u30c9\u3057\u307e\u3057\u305f\u3002

## Actions - moved to slingshot.properties
actions.more=\u305d\u306e\u4ed6\u306e\u30a2\u30af\u30b7\u30e7\u30f3...

## Tips
tip.active-workflow=\u30ef\u30fc\u30af\u30d5\u30ed\u30fc {0} \u306b\u5c5e\u3057\u3066\u3044\u307e\u3059
tip.editing=\u3042\u306a\u305f\u304c\u7de8\u96c6\u4e2d\u3067\u3059\u3002
tip.favourite-document.add=\u304a\u6c17\u306b\u5165\u308a\u306b\u6587\u66f8\u3092\u8ffd\u52a0
tip.favourite-document.remove=\u304a\u6c17\u306b\u5165\u308a\u304b\u3089\u6587\u66f8\u3092\u524a\u9664
tip.favourite-folder.add=\u304a\u6c17\u306b\u5165\u308a\u306b\u30d5\u30a9\u30eb\u30c0\u3092\u8ffd\u52a0
tip.favourite-folder.remove=\u304a\u6c17\u306b\u5165\u308a\u304b\u3089\u30d5\u30a9\u30eb\u30c0\u3092\u524a\u9664
tip.google-docs-owner=Google Docs&trade;\u7de8\u96c6\u7528\u306b\u3042\u306a\u305f\u304c\u30ed\u30c3\u30af\u3057\u3066\u3044\u307e\u3059\u3002
tip.google-docs-locked=Google Docs&trade;\u7de8\u96c6\u7528\u306b {0} ({1}) \u304c\u30ed\u30c3\u30af\u3057\u3066\u3044\u307e\u3059\u3002
tip.locked={0} ({1}) \u304c\u30ed\u30c3\u30af
tip.lock-owner=\u3042\u306a\u305f\u304c\u30ed\u30c3\u30af\u3057\u3066\u3044\u307e\u3059\u3002
tip.rules=\u30d5\u30a9\u30eb\u30c0\u306b\u306f\u9069\u7528\u3055\u308c\u305f\u30eb\u30fc\u30eb\u304c\u3042\u308a\u307e\u3059\u3002
tip.simple-workflow=\u627f\u8a8d/\u5374\u4e0b\u306e\u30b7\u30f3\u30d7\u30eb\u30ef\u30fc\u30af\u30d5\u30ed\u30fc\u304c\u9069\u7528\u3055\u308c\u3066\u3044\u307e\u3059
tip.transferred-node=\u5225\u30ea\u30dd\u30b8\u30c8\u30ea\u304b\u3089\u8ee2\u9001\u3055\u308c\u307e\u3057\u305f

## Pop-up Messages
message.confirm.delete.title=\u30d5\u30a1\u30a4\u30eb\u306e\u524a\u9664
message.confirm.delete=''{0}'' \u3092\u524a\u9664\u3057\u307e\u3059\u304b?
message.delete.success=''{0}'' \u304c\u524a\u9664\u3055\u308c\u307e\u3057\u305f\u3002
message.delete.failure=''{0}'' \u3092\u524a\u9664\u3067\u304d\u307e\u305b\u3093\u3067\u3057\u305f\u3002
message.details.success=\u8a73\u7d30\u60c5\u5831\u306e\u66f4\u65b0\u306b\u6210\u529f\u3057\u307e\u3057\u305f\u3002
message.details.failure=\u8a73\u7d30\u60c5\u5831\u3092\u66f4\u65b0\u3067\u304d\u307e\u305b\u3093\u3067\u3057\u305f\u3002
message.edit-offline.success=''{0}'' \u304c\u7de8\u96c6\u3067\u304d\u308b\u3088\u3046\u306b\u306a\u308a\u307e\u3057\u305f\u3002
message.edit-offline.success.ie7=\u4ee5\u4e0b\u306e\u30dc\u30bf\u30f3\u3092\u4f7f\u7528\u3057\u3066\u6587\u66f8\u3092\u30c0\u30a6\u30f3\u30ed\u30fc\u30c9\u3057\u307e\u3059\u3002
message.edit-offline.failure=''{0}'' \u3092\u7de8\u96c6\u3059\u308b\u3053\u3068\u306f\u3067\u304d\u307e\u305b\u3093\u3002
message.edit-cancel.success=\u30e6\u30fc\u30b6 ''{0}'' \u306f\u3059\u3067\u306b\u30ad\u30e3\u30f3\u30bb\u30eb\u3055\u308c\u3066\u3044\u307e\u3059\u3002
message.edit-cancel.failure=''{0}'' \u306e\u7de8\u96c6\u3092\u30ad\u30e3\u30f3\u30bb\u30eb\u3067\u304d\u307e\u305b\u3093\u3067\u3057\u305f\u3002
message.loading=\u6587\u66f8\u30e9\u30a4\u30d6\u30e9\u30ea\u3092\u30ed\u30fc\u30c9\u4e2d\u3067\u3059\u30fb\u30fb\u30fb
message.error=\u6587\u66f8\u30e9\u30a4\u30d6\u30e9\u30ea\u306b\u30a2\u30af\u30bb\u30b9\u3067\u304d\u307e\u305b\u3093\u3067\u3057\u305f\u3002
message.empty=\u30a2\u30a4\u30c6\u30e0\u306a\u3057
message.empty.subfolders=\u30a2\u30a4\u30c6\u30e0\u304c\u3042\u308a\u307e\u305b\u3093\u3002\u3053\u3053\u3067 {1} \u30b5\u30d6\u30d5\u30a9\u30eb\u30c0\u3092\u8868\u793a\u3059\u308b\u306b\u306f\u3001 "{0}" \u3092\u30af\u30ea\u30c3\u30af\u3057\u307e\u3059\u3002
message.empty.subfolders.link=\u30d5\u30a9\u30eb\u30c0\u306e\u8868\u793a
message.favourite.failure=\u304a\u6c17\u306b\u5165\u308a\u30ea\u30b9\u30c8\u3092\u66f4\u65b0\u3067\u304d\u307e\u305b\u3093\u3067\u3057\u305f\u3002
message.simple-workflow.approved=\u627f\u8a8d\u6e08\u3068\u3057\u3066\u30de\u30fc\u30af\u3055\u308c\u305f\u30a2\u30a4\u30c6\u30e0
message.simple-workflow.rejected=\u5374\u4e0b\u3068\u3057\u3066\u30de\u30fc\u30af\u3055\u308c\u305f\u30a2\u30a4\u30c6\u30e0
message.simple-workflow.failed=\u30ef\u30fc\u30af\u30d5\u30ed\u30fc\u30a2\u30af\u30b7\u30e7\u30f3\u3092\u5b8c\u4e86\u3067\u304d\u307e\u305b\u3093\u3067\u3057\u305f\u3002
message.checkout-google.inprogress=''{0}'' \u304c Google Docs\u306b\u30c1\u30a7\u30c3\u30af\u30a2\u30a6\u30c8\u3055\u308c\u3066\u3044\u307e\u3059\u3002
message.checkout-google.success=''{0}'' \u304cGoogle Docs\u306b\u30c1\u30a7\u30c3\u30af\u30a4\u30f3\u3055\u308c\u3066\u3044\u307e\u3059\u3002
message.checkout-google.failure=Google Docs\u306b ''{0}'' \u3092\u30c1\u30a7\u30c3\u30af\u30a4\u30f3\u3067\u304d\u307e\u305b\u3093\u3067\u3057\u305f\u3002
message.checkout-google.failure=Google Docs\u306b ''{0}'' \u3092\u30c1\u30a7\u30c3\u30af\u30a2\u30a6\u30c8\u3067\u304d\u307e\u305b\u3093\u3002
message.checkin-google.inprogress=''{0}'' \u304c Google Docs\u304b\u3089\u30c1\u30a7\u30c3\u30af\u30a4\u30f3\u3055\u308c\u3066\u3044\u307e\u3059\u3002
message.checkin-google.success=''{0}'' \u304c Google Docs\u304b\u3089\u30c1\u30a7\u30c3\u30af\u30a4\u30f3\u3055\u308c\u307e\u3057\u305f\u3002
message.checkin-google.failure=Google Docs\u304b\u3089 ''{0}'' \u3092\u30c1\u30a7\u30c3\u30af\u30a4\u30f3\u3067\u304d\u307e\u305b\u3093\u3002

## File Upload (upload new version)
label.filter-description={0} \u3068\u540c\u3058\u30bf\u30a4\u30d7

## Edit Details Dialog
edit-details.title={0}\u306e\u8a73\u7d30\u60c5\u5831
edit-details.label.edit-metadata=\u8a73\u7d30\u30e1\u30bf\u30c7\u30fc\u30bf\u306e\u7de8\u96c6\u30da\u30fc\u30b8...

## Customize Dialog
customize.title=\u30ab\u30b9\u30bf\u30de\u30a4\u30ba\u3059\u308b
customize.header.actions=\u30a2\u30af\u30b7\u30e7\u30f3

# SIDE sort extension
menu.sort=Sort by : Name
search.sort.size=Size
search.sort.mimetype=MIME Type
search.sort.type=Type

## SIDE view labels
<%for (getAllReferencedAttributes()){%>
<%getPrefixedQualifiedName().replaceAll(":", "_")%>=<%getLabel()%>
<%}%>
