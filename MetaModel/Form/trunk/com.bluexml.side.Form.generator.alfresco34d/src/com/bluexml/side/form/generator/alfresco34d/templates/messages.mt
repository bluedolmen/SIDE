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
metamodel http://www.kerblue.org/form/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.form.generator.alfresco34d.FormGenerator
import com.bluexml.side.form.generator.alfresco34d.templates.formGenerator
import com.bluexml.side.form.generator.alfresco34d.templates.formGenerator-util
import com.bluexml.side.form.generator.alfresco34d.templates.services.form
%>
  
<%script type="form.FormCollection" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext")%>/<%getModuleIdService()%>/<%getRootPackage().name%>.properties<%}%>

<%script type="form.FormCollection" name="generate" file="<%fileName()%>" %>
<%for (forms){%>
form.set.label.<%getPrefixedQualifiedName()%>.general=Details
<%}%>
<%generate_messages()%>
<%script type="form.FormCollection" name="generate_messages" %>
<%for (forms){%>
form.set.label.<%getPrefixedQualifiedName()%>=<%getLabelOrName()%>
<%for (getAllSubGroups()){%>
<%getGroupLabelId()%>=<%getLabelOrName()%>
<%}%>
<%for (getAllKindFields()){%>
<%getFieldLabelId("")%>=<%getLabelOrName()%>
<%if (help_text != null && help_text != ""){%>
<%getFieldLabelId("help.")%>=<%help_text%>
<%}%>
<%if (description != null && description != ""){%>
<%getFieldLabelId("description.")%>=<%description%>
<%}%>
<%}%>
<%}%>
<%for (forms.filter("FormSearch")){%>
search.form.label.<%real_class.getPrefixedQName("_")%>=<%getLabelOrName()%>
search.form.desc.<%real_class.getPrefixedQName("_")%>=<%if (description != null){%><%description%><%}%>
<%}%>
