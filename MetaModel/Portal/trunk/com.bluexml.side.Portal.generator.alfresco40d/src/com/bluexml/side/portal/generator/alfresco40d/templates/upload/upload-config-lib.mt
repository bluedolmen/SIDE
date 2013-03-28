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
import com.bluexml.side.clazz.service.alfresco.ClassServices
%>

<%script type="Portal" name="alfrescoGenerator"%>
<%for (pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets.associationPortlet[name.toLowerCase().trim() == "uploadabletypes" && isPortletInternal != null]){%>
<upload>
<%for (isPortletInternal.form.forms){%>
	<%if (filter("form.ClassReference").real_class.isUploadable()){%>
	<type qname="<%filter("form.ClassReference").real_class.getPrefixedQName()%>" labelId="type.<%filter("form.ClassReference").real_class.getPrefixedQName("_")%>" />
	<%}%>	
<%}%>
</upload>
<%}%>


<%script type="Portal" name="i18nGenerator" %>
<%for (portletSet[name.toLowerCase().trim() == "uploadabletypes" && isPortletInternal != null]){%>
<%for (isPortletInternal.form.forms){%>
type.<%filter("form.ClassReference").real_class.getPrefixedQName("_")%>=<%filter("form.ClassReference").real_class.getLabel()%>
<%}%>
<%}%>
