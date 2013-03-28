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
%>

<%--
  -- This file is a patched copy of Alfresco Share's default flash upload webscript, but it is kept
  -- separate for ease of maintainability. It should be removed when Alfresco release their own 
  -- "webscript that returns a list of content types (has been asked for on the wiki)"
  --%>


<%script type="clazz.ClassPackage" name="getFlashUploadJsOutputFile"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.core.site-webscripts")%>org/alfresco/components/upload/flash-upload.get.js<%}%>

<%script type="clazz.ClassPackage" name="flashUploadJs" file="<%getFlashUploadJsOutputFile%>"%>
<%getHtmlOrFlashUploadJs()%>

