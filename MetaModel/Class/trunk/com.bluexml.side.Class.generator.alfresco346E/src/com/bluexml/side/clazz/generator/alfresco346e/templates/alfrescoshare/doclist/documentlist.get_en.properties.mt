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
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.core.site-webscripts")%>org/alfresco/components/documentlibrary/documentlist.get_en.properties<%}%>

<%script type="clazz.ClassPackage" name="messages" file="<%fileName%>"%>
## Buttons
button.folders.show=Show Folders
button.folders.hide=Hide Folders
button.view.simple=Simple View
button.view.detailed=Detailed View

## Drop-down Menus
menu.select=Select
menu.select.all=All
menu.select.none=None
menu.select.invert=Invert Selection
menu.select.folders=Folders
menu.select.documents=Documents

## Document Details
details.link-to=Link to: {0}
details.created.on=Created on:
details.created.by=Created by:
details.modified.on=Modified on:
details.modified.by=Modified by:
details.editing-started.on=Editing started on:
details.editing-started.by=Editing started by:
details.by=By:
details.version=Version:
details.size=Size:
details.description=Description:
details.description.none=(None)
details.comments=Comments:
details.tags=Tags:
details.tags.none=(None)

## Details Banners
details.banner.editing=This document is locked by you for offline editing.
details.banner.lock-owner=This document is locked by you.
details.banner.locked=This document is locked by {0} for editing.
details.banner.google-docs.link=Google Docs
details.banner.google-docs-owner=This document has been uploaded by you to {0} for editing.
details.banner.google-docs-locked=This document has been uploaded by {0} to {1} for editing.

## Actions - moved to slingshot.properties
actions.more=More...

## Tips
tip.active-workflow=Belongs to {0} active workflows
tip.editing=Being edited by you
tip.favourite-document.add=Add document to favorites
tip.favourite-document.remove=Remove document from favorites
tip.favourite-folder.add=Add folder to favorites
tip.favourite-folder.remove=Remove folder from favorites
tip.google-docs-owner=Locked by you for editing with Google Docs&trade;
tip.google-docs-locked=Locked by {0} ({1}) for editing with Google Docs&trade;
tip.locked=Locked by {0} ({1})
tip.lock-owner=Locked by you
tip.rules=Folder has rules applied
tip.simple-workflow=Simple Approve/Reject workflow applied
tip.transferred-node=Transferred from another Repository

## Pop-up Messages
message.confirm.delete.title=Delete File
message.confirm.delete=Are you sure you want to delete ''{0}''?
message.delete.success=''{0}'' was deleted
message.delete.failure=Could not delete ''{0}''
message.details.success=Details updated successfully
message.details.failure=Could not update details
message.edit-offline.success=''{0}'' can now be edited
message.edit-offline.success.ie7=Download the document using the button below.
message.edit-offline.failure=You cannot edit ''{0}''.
message.edit-cancel.success=Editing ''{0}'' has been cancelled
message.edit-cancel.failure=Could not cancel editing ''{0}''.
message.loading=Loading the Document Library...
message.error=Could not access the Document Library
message.empty=No items
message.empty.subfolders=No items. Click "{0}" to see {1} subfolder(s) here.
message.empty.subfolders.link=Show Folders
message.favourite.failure=Could not update favorites list
message.simple-workflow.approved=Item marked as approved
message.simple-workflow.rejected=Item marked as rejected
message.simple-workflow.failure=Workflow action could not be completed.
message.checkout-google.inprogress=''{0}'' is being checked out to Google Docs
message.checkout-google.success=''{0}'' has been checked into Google Docs
message.checkout-google.failure=Could not checkin ''{0}'' to Google Docs
message.checkout-google.failure=You cannot checkout ''{0}'' to Google Docs
message.checkin-google.inprogress=''{0}'' is being checked in from Google Docs
message.checkin-google.success=''{0}'' has been checked in from Google Docs
message.checkin-google.failure=You cannot checkin ''{0}'' from Google Docs

## File Upload (upload new version)
label.filter-description=Same type as {0}

## Edit Details Dialog
edit-details.title=Details for {0}
edit-details.label.edit-metadata=Full Metadata Edit page...

## Customize Dialog
customize.title=Customize
customize.header.actions=Actions

# SIDE sort extension
label.sortby=Sort by : Name
search.sort.size=Size
search.sort.mimetype=MIME Type
search.sort.type=Type

## SIDE view labels
<%for (getAllReferencedAttributes()){%>
<%getPrefixedQualifiedName().replaceAll(":", "_")%>=<%getLabel()%>
<%}%>
