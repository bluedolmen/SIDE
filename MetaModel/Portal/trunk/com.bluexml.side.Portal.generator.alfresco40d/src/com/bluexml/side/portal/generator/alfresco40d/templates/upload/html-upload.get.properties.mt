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
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.portal.generator.alfresco40d.templates.upload.upload-config-lib

%>
<%script type="Portal" name="validatedFilename" post="trim()" %>
<%getProperty("alf.share.paths.web-ext.alf.components")%>/upload/html-upload.get.properties
<%script type="Portal" name="generator" file="<%validatedFilename%>"%>
header.singleUpload=Upload File
header.singleUpdate=Update File

label.singleUploadTip=With the latest Flash Player installed, you can upload multiple files. \
  Download it from the <a href="http://www.adobe.com/go/getflashplayer">Adobe Flash Player Download Center</a>.

label.singleUpdateTip=Upload a new version of {0}
button.upload=Upload File

section.file=Choose File
label.file=File

section.version=Version Information
label.version=This version has:
label.minorVersion=minor changes
label.majorVersion=major changes
label.minorVersion.more=minor changes ({0})
label.majorVersion.more=major changes ({0})
label.comments=Comments

message.uploading=Document is being uploaded...
message.success=Document successfully uploaded
message.failure=Document could not be uploaded
message.failure.413=Quota Exceeded
message.illegalCharacters=Filenames cannot contain characters that are illegal on any operation system.

type.cm_content=Content
<%i18nGenerator%>