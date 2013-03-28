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
import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.api.service.ValueGenerator

%>
<%script type="clazz.Attribute" name="getExtJSEditor" post="trim()" %>
<%if (valueList != null){%>combo_enum_<%valueList.name%><%}else{%>
<%if (typ.toString().equalsIgnoreCase("String")){%>new Ext.form.TextField({allowBlank: <%isMandatory()%>})<%}else{%>
<%if (typ.toString().equalsIgnoreCase("Date")){%>new Ext.form.TextField({allowBlank: <%isMandatory()%>})<%}else{%>new Ext.form.NumberField({allowBlank: <%isMandatory()%>})<%}%>
<%}%>
<%}%>
<%script type="clazz.Attribute" name="getExtJSType" post="trim()" %>
<%if (typ.toString().equalsIgnoreCase("String")){%>String<%}else{%>
<%if (typ.toString().equalsIgnoreCase("Date")){%>String<%}else{%><%typ.toString()%><%}%>
<%}%>
